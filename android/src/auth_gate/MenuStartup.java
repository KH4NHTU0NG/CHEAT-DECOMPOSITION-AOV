package com.android.support.auth;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.android.support.InGameLoginDialog;
import com.android.support.Main;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: /tmp/apk_work/classes9.dex */
public final class MenuStartup {
    private static boolean installed;
    private static final Handler MAIN = new Handler(Looper.getMainLooper());
    private static final Set<Activity> RESUMED = Collections.newSetFromMap(new WeakHashMap());
    private static final WeakHashMap<Activity, Request> PENDING = new WeakHashMap<>();
    private static WeakReference<Activity> active = new WeakReference<>(null);

    private MenuStartup() {
    }

    public static void install(Application application) {
        if (installed) {
            return;
        }
        installed = true;
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.android.support.auth.MenuStartup.1
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
                MenuStartup.RESUMED.add(activity);
                Request request = (Request) MenuStartup.PENDING.get(activity);
                if (request != null) {
                    MenuStartup.MAIN.removeCallbacks(request);
                    MenuStartup.MAIN.post(request);
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
                MenuStartup.RESUMED.remove(activity);
                Request request = (Request) MenuStartup.PENDING.get(activity);
                if (request != null) {
                    MenuStartup.MAIN.removeCallbacks(request);
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
                MenuStartup.RESUMED.remove(activity);
                Request request = (Request) MenuStartup.PENDING.remove(activity);
                if (request != null) {
                    MenuStartup.MAIN.removeCallbacks(request);
                }
                if (MenuStartup.active.get() == activity) {
                    MenuStartup.active.clear();
                    try {
                        Main.stopActivityMenu();
                    } catch (LinkageError | RuntimeException e) {
                        Log.w("ApkMenu", "Could not detach destroyed Activity menu: " + e.getClass().getSimpleName());
                    }
                }
            }
        });
    }

    public static void start(Context context) {
        if (!(context instanceof Activity)) {
            Log.w("ApkMenu", "Menu requires an Activity");
            return;
        }
        final Activity activity = (Activity) context;
        if (Looper.myLooper() != Looper.getMainLooper()) {
            MAIN.post(new Runnable() { // from class: com.android.support.auth.MenuStartup$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    MenuStartup.start(activity);
                }
            });
            return;
        }
        if (activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        if (active.get() == activity && Main.isStarted()) {
            return;
        }
        WeakHashMap<Activity, Request> weakHashMap = PENDING;
        if (weakHashMap.containsKey(activity)) {
            return;
        }
        Main.PrepareRuntime();
        Request request = new Request(activity);
        weakHashMap.put(activity, request);
        MAIN.post(request);
    }

    private static final class Request implements Runnable {
        final long created = SystemClock.elapsedRealtime();
        final WeakReference<Activity> host;

        Request(Activity activity) {
            this.host = new WeakReference<>(activity);
        }

        @Override // java.lang.Runnable
        public void run() {
            Activity activity = this.host.get();
            if (activity == null || MenuStartup.PENDING.get(activity) != this) {
                return;
            }
            if (activity.isFinishing() || activity.isDestroyed() || !AuthSessionManager.isAuthenticated()) {
                MenuStartup.PENDING.remove(activity);
                return;
            }
            if (MenuStartup.RESUMED.contains(activity)) {
                View decorView = activity.getWindow() == null ? null : activity.getWindow().getDecorView();
                if (decorView == null || !decorView.isAttachedToWindow() || decorView.getWindowToken() == null) {
                    MenuStartup.MAIN.postDelayed(this, 100L);
                    return;
                }
                String strGetRuntimeStatus = Main.GetRuntimeStatus();
                if (("waiting_for_runtime".equals(strGetRuntimeStatus) || "stopped".equals(strGetRuntimeStatus) || "report_subset_applied_native_pending".equals(strGetRuntimeStatus)) && SystemClock.elapsedRealtime() - this.created < 190000) {
                    MenuStartup.MAIN.postDelayed(this, 200L);
                    return;
                }
                try {
                    if (!"applied".equals(strGetRuntimeStatus)) {
                        MenuStartup.PENDING.remove(activity);
                        Log.e("ApkMenu", "Runtime not ready; menu disabled: " + strGetRuntimeStatus);
                        try {
                            Main.revokeNative();
                        } catch (LinkageError | RuntimeException unused) {
                        }
                        AuthSessionManager.clear();
                        Toast.makeText(activity, "Không thể khởi tạo module (" + strGetRuntimeStatus + "). Vui lòng gửi log.", 1).show();
                        InGameLoginDialog.show(activity);
                    } else {
                        MenuStartup.PENDING.remove(activity);
                        if (MenuStartup.active.get() == activity && Main.isStarted()) {
                            return;
                        }
                        try {
                            Main.StartWithoutPermissionNow(activity);
                            if (Main.isStarted()) {
                                WeakReference unused2 = MenuStartup.active = new WeakReference(activity);
                            }
                        } catch (LinkageError | RuntimeException e) {
                            Log.e("ApkMenu", "Menu initialization failed: " + e.getClass().getSimpleName());
                            try {
                                Main.revokeNative();
                            } catch (LinkageError | RuntimeException unused3) {
                            }
                            MenuStartup.active.clear();
                            AuthSessionManager.clear();
                            if (activity.isFinishing() || activity.isDestroyed() || !MenuStartup.RESUMED.contains(activity)) {
                            } else {
                                InGameLoginDialog.show(activity);
                            }
                        }
                    }
                } catch (LinkageError | RuntimeException unused4) {
                }
            }
        }
    }
}
