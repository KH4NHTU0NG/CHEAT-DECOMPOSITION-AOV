package com.android.support.auth;

import android.app.ActivityManager;
import android.app.Application;
import android.os.Build;
import android.os.Process;
import android.util.Log;
import com.garena.game.kgtw.NGameApplication;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.List;

/* JADX INFO: loaded from: /tmp/apk_work/classes8.dex */
public final class GateApplication extends NGameApplication {
    /* JADX WARN: Multi-variable type inference failed */
    public void onCreate() {
        String processName;
        int i;
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = null;
        try {
            processName = Build.VERSION.SDK_INT >= 28 ? Application.getProcessName() : null;
        } catch (RuntimeException unused) {
        }
        if (processName == null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                FileInputStream fileInputStream = new FileInputStream("/proc/self/cmdline");
                while (byteArrayOutputStream.size() < 256 && (i = fileInputStream.read()) > 0) {
                    try {
                        byteArrayOutputStream.write(i);
                    } catch (Throwable th) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                }
                fileInputStream.close();
                processName = byteArrayOutputStream.toString("UTF-8");
            } catch (Exception unused2) {
            }
        }
        if (processName == null || processName.isEmpty()) {
            try {
                ActivityManager activityManager = (ActivityManager) getSystemService("activity");
                if (activityManager != null) {
                    runningAppProcesses = activityManager.getRunningAppProcesses();
                }
                if (runningAppProcesses != null) {
                    for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                        if (runningAppProcessInfo.pid == Process.myPid()) {
                            processName = runningAppProcessInfo.processName;
                            break;
                        }
                    }
                }
            } catch (RuntimeException unused3) {
            }
        }
        if ((getPackageName() + ":apkGate").equals(processName)) {
            Log.i("ApkGate", "Maintenance-only process; game application not initialized");
        } else {
            super.onCreate();
            MenuStartup.install(this);
        }
    }
}
