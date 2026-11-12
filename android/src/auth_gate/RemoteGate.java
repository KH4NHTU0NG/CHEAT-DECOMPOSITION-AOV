package com.android.support.auth;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* JADX INFO: loaded from: /tmp/apk_work/classes8.dex */
public final class RemoteGate {
    public static final String APP_ID = "map-sang-v4-20261103-r2";
    private static final String ENDPOINT = "https://hackgame.lienquan.io.vn/apk-manager/status.php";
    private static final Object LOCK = new Object();
    private static long closeDeadline = 0;
    private static ScheduledFuture<?> closeTask = null;
    private static Context context = null;
    private static volatile boolean exiting = false;
    private static String owner = "";
    private static ScheduledFuture<?> pollTask;
    private static ScheduledThreadPoolExecutor scheduler;

    private RemoteGate() {
    }

    public static void initialize(Context context2) {
        if (context2 == null) {
            return;
        }
        synchronized (LOCK) {
            context = context2.getApplicationContext();
        }
    }

    public static void start(String str) {
        if (str == null || str.trim().length() == 0) {
            return;
        }
        synchronized (LOCK) {
            if (context != null && !exiting) {
                if (!str.equals(owner)) {
                    cancelClose();
                    owner = str;
                }
                if (scheduler == null) {
                    ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(2);
                    scheduler = scheduledThreadPoolExecutor;
                    scheduledThreadPoolExecutor.setRemoveOnCancelPolicy(true);
                }
                ScheduledFuture<?> scheduledFuture = pollTask;
                if (scheduledFuture == null || scheduledFuture.isCancelled()) {
                    pollTask = scheduler.scheduleWithFixedDelay(new Runnable() { // from class: com.android.support.auth.RemoteGate.1
                        @Override // java.lang.Runnable
                        public void run() throws Throwable {
                            RemoteGate.poll();
                        }
                    }, 0L, 3L, TimeUnit.SECONDS);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void poll() throws Throwable {
        String str;
        synchronized (LOCK) {
            str = owner;
        }
        SystemClock.elapsedRealtime();
        HttpURLConnection httpURLConnection = null;
        try {
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(ENDPOINT).openConnection();
            try {
                httpURLConnection2.setConnectTimeout(4000);
                httpURLConnection2.setReadTimeout(4000);
                httpURLConnection2.setInstanceFollowRedirects(false);
                httpURLConnection2.setUseCaches(false);
                httpURLConnection2.setRequestMethod("POST");
                httpURLConnection2.setDoOutput(true);
                httpURLConnection2.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                httpURLConnection2.setRequestProperty("Accept", "application/json");
                byte[] bytes = ("code=" + URLEncoder.encode(str, "UTF-8") + "&app_id=map-sang-v4-20261103-r2&package=com.garena.game.kgvo&phienban=11").getBytes("UTF-8");
                httpURLConnection2.setFixedLengthStreamingMode(bytes.length);
                OutputStream outputStream = httpURLConnection2.getOutputStream();
                try {
                    outputStream.write(bytes);
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (httpURLConnection2.getResponseCode() != 200) {
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                            return;
                        }
                        return;
                    }
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    InputStream inputStream = httpURLConnection2.getInputStream();
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int i = inputStream.read(bArr);
                            if (i == -1) {
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                                JSONObject jSONObject = new JSONObject(byteArrayOutputStream.toString("UTF-8"));
                                if ("ok".equals(jSONObject.optString("status")) && (jSONObject.opt("enabled") instanceof Boolean)) {
                                    boolean z = jSONObject.getBoolean("enabled");
                                    final String strOptString = jSONObject.optString("message", "APK đang bảo trì. Vui lòng liên hệ quản trị viên.");
                                    if (strOptString.length() > 300) {
                                        strOptString = strOptString.substring(0, 300);
                                    }
                                    synchronized (LOCK) {
                                        if (!str.equals(owner) || exiting) {
                                            if (httpURLConnection2 != null) {
                                                httpURLConnection2.disconnect();
                                                return;
                                            }
                                        } else if (z) {
                                            cancelClose();
                                            if (httpURLConnection2 != null) {
                                                httpURLConnection2.disconnect();
                                                return;
                                            }
                                        } else {
                                            ScheduledFuture<?> scheduledFuture = closeTask;
                                            if (scheduledFuture == null || scheduledFuture.isCancelled()) {
                                                cancelClose();
                                                closeDeadline = SystemClock.elapsedRealtime() + 700;
                                                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.android.support.auth.RemoteGate.2
                                                    @Override // java.lang.Runnable
                                                    public void run() {
                                                        try {
                                                            Intent className = new Intent().setClassName(RemoteGate.context.getPackageName(), "com.android.support.auth.MaintenanceActivity");
                                                            className.addFlags(402653184);
                                                            className.putExtra("maintenance_message", strOptString);
                                                            RemoteGate.context.startActivity(className);
                                                        } catch (RuntimeException unused) {
                                                        }
                                                    }
                                                });
                                                closeTask = scheduler.schedule(new Runnable() { // from class: com.android.support.auth.RemoteGate.3
                                                    @Override // java.lang.Runnable
                                                    public void run() {
                                                        RemoteGate.closeOwnGame();
                                                    }
                                                }, 700L, TimeUnit.MILLISECONDS);
                                                if (httpURLConnection2 != null) {
                                                    httpURLConnection2.disconnect();
                                                    return;
                                                }
                                            } else if (httpURLConnection2 != null) {
                                                httpURLConnection2.disconnect();
                                                return;
                                            }
                                        }
                                        return;
                                    }
                                }
                                if (httpURLConnection2 != null) {
                                    httpURLConnection2.disconnect();
                                    return;
                                }
                                return;
                            }
                            if (byteArrayOutputStream.size() + i > 16384) {
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                                if (httpURLConnection2 != null) {
                                    httpURLConnection2.disconnect();
                                    return;
                                }
                                return;
                            }
                            byteArrayOutputStream.write(bArr, 0, i);
                            httpURLConnection = httpURLConnection2;
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                                return;
                            }
                            return;
                        }
                    } catch (Throwable th) {
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (Throwable th4) {
                            th3.addSuppressed(th4);
                        }
                    }
                    throw th3;
                }
            } catch (Exception unused) {
                httpURLConnection = httpURLConnection2;
            } catch (Throwable th5) {
                th = th5;
                httpURLConnection = httpURLConnection2;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (Exception unused2) {
        } catch (Throwable th6) {
            th = th6;
        }
    }

    private static void cancelClose() {
        ScheduledFuture<?> scheduledFuture = closeTask;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        closeTask = null;
        closeDeadline = 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void closeOwnGame() {
        synchronized (LOCK) {
            if (closeTask != null && !exiting) {
                exiting = true;
                String packageName = context.getPackageName();
                int iMyPid = Process.myPid();
                int iMyUid = Process.myUid();
                ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
                if (activityManager != null) {
                    try {
                        List<ActivityManager.RunningServiceInfo> runningServices = activityManager.getRunningServices(Integer.MAX_VALUE);
                        if (runningServices != null) {
                            for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServices) {
                                if (runningServiceInfo.uid == iMyUid && packageName.equals(runningServiceInfo.service.getPackageName())) {
                                    if ((packageName + ":apkGate").equals(runningServiceInfo.process)) {
                                        continue;
                                    } else {
                                        context.stopService(new Intent().setComponent(runningServiceInfo.service));
                                    }
                                }
                            }
                        }
                    } catch (RuntimeException unused) {
                    }
                    try {
                        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
                        if (runningAppProcesses != null) {
                            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                                String str = runningAppProcessInfo.processName;
                                if (runningAppProcessInfo.pid != iMyPid && runningAppProcessInfo.uid == iMyUid && str != null) {
                                    if (!str.equals(packageName + ":apkGate")) {
                                        if (!str.equals(packageName)) {
                                            if (str.startsWith(packageName + ":")) {
                                            }
                                        }
                                        Process.killProcess(runningAppProcessInfo.pid);
                                    }
                                }
                            }
                        }
                    } catch (RuntimeException unused2) {
                    }
                }
                Process.killProcess(iMyPid);
                System.exit(0);
            }
        }
    }
}
