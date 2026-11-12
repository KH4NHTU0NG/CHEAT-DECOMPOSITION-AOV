package com.android.support;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;
import com.android.support.auth.AuthSessionManager;
import com.android.support.auth.MenuStartup;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: /tmp/apk_work/classes9.dex */
public class Main {
    private static Menu activityMenu;
    private static WeakReference<Activity> gameActivity = new WeakReference<>(null);
    private static volatile boolean nativeLoaded;
    private static volatile boolean started;

    private static native void CheckOverlayPermission(Context context);

    private static native String GetRuntimeStatusNative();

    private static native void PrepareRuntimeNative();

    private static native void SetAuthorized(boolean z);

    private static synchronized void ensureNativeLoaded() {
        if (nativeLoaded) {
            return;
        }
        System.loadLibrary("MyLibName");
        nativeLoaded = true;
    }

    public static void PrepareRuntime() {
        if (isMaintenanceProcess()) {
            return;
        }
        try {
            ensureNativeLoaded();
            PrepareRuntimeNative();
        } catch (LinkageError | RuntimeException e) {
            Log.e("Mod_Menu", "Runtime load failed: " + e.getClass().getSimpleName());
        }
    }

    private static boolean isMaintenanceProcess() {
        try {
            String processName = Build.VERSION.SDK_INT >= 28 ? Application.getProcessName() : null;
            if (processName == null) {
                byte[] bArr = new byte[256];
                FileInputStream fileInputStream = new FileInputStream("/proc/self/cmdline");
                int i = 0;
                while (i < 256) {
                    try {
                        int i2 = fileInputStream.read();
                        if (i2 <= 0) {
                            break;
                        }
                        int i3 = i + 1;
                        bArr[i] = (byte) i2;
                        i = i3;
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
                processName = new String(bArr, 0, i, StandardCharsets.UTF_8);
            }
            return processName.endsWith(":apkGate");
        } catch (IOException | RuntimeException unused) {
            Log.w("Mod_Menu", "Could not identify runtime process");
            return true;
        }
    }

    public static String GetRuntimeStatus() {
        try {
            ensureNativeLoaded();
            return GetRuntimeStatusNative();
        } catch (LinkageError | RuntimeException unused) {
            return "native_load_failed";
        }
    }

    public static synchronized void revokeNative() {
        ComplaintUi.stop();
        if (nativeLoaded) {
            SetAuthorized(false);
        }
        stopActivityMenu();
    }

    public static boolean isStarted() {
        return started;
    }

    public static boolean IsAuthorized() {
        return AuthSessionManager.isAuthenticated();
    }

    public static void BeginGameLaunch(Activity activity) {
        Intent intent;
        if (activity != null && (intent = activity.getIntent()) != null && "android.intent.action.MAIN".equals(intent.getAction()) && intent.hasCategory("android.intent.category.LAUNCHER")) {
            AuthSessionManager.clear();
            revokeNative();
        }
    }

    public static void ShowInGameLogin(Activity activity) {
        if (activity == null) {
            return;
        }
        gameActivity = new WeakReference<>(activity);
        scheduleLoginDialog(activity, 0);
    }

    private static void scheduleLoginDialog(final Activity activity, final int i) {
        if (activity == null) {
            return;
        }
        activity.getWindow().getDecorView().postDelayed(new Runnable() { // from class: com.android.support.Main$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                Main.completeLoginDialog(activity, i);
            }
        }, i == 0 ? 220L : 500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void completeLoginDialog(Activity activity, int i) {
        if (activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        Log.d("Mod_Menu", "login dialog attempt " + i);
        if (AuthSessionManager.isAuthenticated()) {
            if (started) {
                return;
            }
            StartWithoutPermission(activity);
        } else {
            InGameLoginDialog.show(activity);
            if (i < 2) {
                scheduleLoginDialog(activity, i + 1);
            }
        }
    }

    public static void a(Activity activity, int i) {
        completeLoginDialog(activity, i);
    }

    public static void DestroyInGameLogin(Activity activity) {
        InGameLoginDialog.destroy(activity);
        if (gameActivity.get() == activity) {
            gameActivity.clear();
        }
    }

    public static Activity getGameActivity() {
        return gameActivity.get();
    }

    public static void markStopped() {
        started = false;
    }

    public static synchronized void stopActivityMenu() {
        Menu menu = activityMenu;
        if (menu != null) {
            menu.onDestroy();
            activityMenu = null;
        }
        started = false;
    }

    public static void StartWithoutPermissionNow(Context context) {
        if (AuthSessionManager.isAuthenticated()) {
            ensureNativeLoaded();
            if (!"applied".equals(GetRuntimeStatusNative())) {
                throw new IllegalStateException("Runtime is not ready: " + GetRuntimeStatusNative());
            }
            SetAuthorized(true);
            CrashHandler.init(context.getApplicationContext(), true);
            if (context instanceof Activity) {
                stopActivityMenu();
                Menu menu = new Menu(context);
                activityMenu = menu;
                menu.SetWindowManagerActivity();
                activityMenu.ShowMenu();
                ComplaintUi.start((Activity) context);
                started = true;
                return;
            }
            Toast.makeText(context, "Không thể mở menu\n", 1).show();
        }
    }

    public static void Start(Context context) {
        if (AuthSessionManager.isAuthenticated()) {
            ensureNativeLoaded();
            if (!"applied".equals(GetRuntimeStatusNative())) {
                throw new IllegalStateException("Runtime is not ready: " + GetRuntimeStatusNative());
            }
            SetAuthorized(true);
            CrashHandler.init(context.getApplicationContext(), false);
            CheckOverlayPermission(context);
            started = true;
        }
    }

    public static void StartWithoutPermission(Context context) {
        MenuStartup.start(context);
    }
}
