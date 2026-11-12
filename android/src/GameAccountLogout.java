package com.android.support;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;
import java.io.File;
import java.util.List;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public final class GameAccountLogout {
    private static final long RESTART_DELAY_MS = 400;
    private static final int RESTART_REQUEST_CODE = 19795;
    private static final String TAG = "GameAccountLogout";
    private static final String TOKEN_PREFS = "com.garena.msdk.token_cache";

    private GameAccountLogout() {
    }

    private static boolean clearTokenCache(Context context) {
        boolean zCommit;
        boolean z = false;
        try {
            zCommit = context.getSharedPreferences(TOKEN_PREFS, 0).edit().clear().commit();
        } catch (RuntimeException e) {
            Log.w(TAG, "SharedPreferences clear failed", e);
            zCommit = false;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                context.deleteSharedPreferences("com.garena.msdk.token_cache");
            } catch (RuntimeException e2) {
                Log.w(TAG, "deleteSharedPreferences failed", e2);
            }
        }
        File file = new File(context.getApplicationInfo().dataDir, "shared_prefs");
        File file2 = new File(file, "com.garena.msdk.token_cache.xml");
        File file3 = new File(file, "com.garena.msdk.token_cache.xml.bak");
        boolean zDeleteIfPresent = deleteIfPresent(file2);
        boolean zDeleteIfPresent2 = deleteIfPresent(file3);
        if (zDeleteIfPresent && zDeleteIfPresent2 && !file2.exists() && !file3.exists()) {
            z = true;
        }
        if (!zCommit && z) {
            Log.w(TAG, "Preference commit failed, but token files were removed directly");
        }
        return z;
    }

    private static boolean deleteIfPresent(File file) {
        return !file.exists() || file.delete();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$resetAndRestart$0() {
        Process.killProcess(Process.myPid());
        Runtime.getRuntime().exit(0);
    }

    private static boolean launchViaRestartTask(Context context) {
        try {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
            if (launchIntentForPackage != null && launchIntentForPackage.getComponent() != null) {
                Intent intentMakeRestartActivityTask = Intent.makeRestartActivityTask(launchIntentForPackage.getComponent());
                intentMakeRestartActivityTask.setPackage(context.getPackageName());
                if (launchIntentForPackage.getExtras() != null) {
                    intentMakeRestartActivityTask.putExtras(launchIntentForPackage.getExtras());
                }
                intentMakeRestartActivityTask.addFlags(270565376);
                context.startActivity(intentMakeRestartActivityTask);
                return true;
            }
            return false;
        } catch (Throwable th) {
            Log.w(TAG, "Restart via makeRestartActivityTask failed", th);
            return false;
        }
    }

    public static void resetAndRestart(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        if (context == null) {
            return;
        }
        Context applicationContext = context.getApplicationContext();
        if (!clearTokenCache(applicationContext)) {
            Toast.makeText(context, "Không thể xoá token tài khoản.", 1).show();
            return;
        }
        try {
            Main.revokeNative();
        } catch (LinkageError | RuntimeException e) {
            Log.w(TAG, "Native teardown failed before account reset", e);
        }
        applicationContext.stopService(new Intent(applicationContext, (Class<?>) Launcher.class));
        ActivityManager activityManager = (ActivityManager) applicationContext.getSystemService("activity");
        if (activityManager != null && (runningAppProcesses = activityManager.getRunningAppProcesses()) != null) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo != null && runningAppProcessInfo.pid != Process.myPid() && runningAppProcessInfo.uid == Process.myUid() && runningAppProcessInfo.processName != null) {
                    String packageName = applicationContext.getPackageName();
                    if (!runningAppProcessInfo.processName.equals(packageName)) {
                        if (runningAppProcessInfo.processName.startsWith(packageName + ":")) {
                        }
                    }
                    Process.killProcess(runningAppProcessInfo.pid);
                }
            }
        }
        if (!clearTokenCache(applicationContext)) {
            Toast.makeText(context, "Token tài khoản chưa được xoá hoàn toàn.", 1).show();
            return;
        }
        boolean zTriggerRestart = triggerRestart(applicationContext);
        Toast.makeText(context, zTriggerRestart ? "Đã xoá token. Game đang khởi động lại." : "Đã xoá token. Hãy mở lại game.", 0).show();
        Log.i(TAG, "Token cache cleared; restartTriggered=" + zTriggerRestart);
        new Handler(Looper.getMainLooper()).postDelayed(new d(4), RESTART_DELAY_MS);
    }

    private static boolean scheduleAlarmRestart(Context context) {
        try {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
            AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
            if (launchIntentForPackage != null && alarmManager != null) {
                launchIntentForPackage.addFlags(268468224);
                int i = Build.VERSION.SDK_INT;
                PendingIntent activity = PendingIntent.getActivity(context, RESTART_REQUEST_CODE, launchIntentForPackage, i >= 23 ? 335544320 : 268435456);
                long jElapsedRealtime = SystemClock.elapsedRealtime() + 1200;
                if (i >= 23) {
                    alarmManager.setExactAndAllowWhileIdle(2, jElapsedRealtime, activity);
                    return true;
                }
                alarmManager.set(2, jElapsedRealtime, activity);
                return true;
            }
            return false;
        } catch (Throwable th) {
            Log.w(TAG, "Alarm-based restart failed", th);
            return false;
        }
    }

    private static boolean triggerRestart(Context context) {
        return launchViaRestartTask(context) || scheduleAlarmRestart(context);
    }
}
