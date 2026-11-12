package com.android.support;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import com.android.support.ThemesData.CYGNUS.CygnusIconDrawable;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public final class ComplaintUi {
    private static AlertDialog active;
    private static boolean running;
    private static final Handler UI = new Handler(Looper.getMainLooper());
    private static WeakReference<Activity> activity = new WeakReference<>(null);
    private static final ArrayDeque<JSONObject> pending = new ArrayDeque<>();
    private static String status = "Đang chờ bộ đọc tố cáo";
    private static String accountUid = "0";
    private static final Runnable POLL = new Runnable() { // from class: com.android.support.ComplaintUi.1
        @Override // java.lang.Runnable
        public void run() {
            if (ComplaintUi.running) {
                if (!ComplaintUi.usable((Activity) ComplaintUi.activity.get())) {
                    ComplaintUi.stop();
                    return;
                }
                try {
                    JSONObject jSONObject = new JSONObject(ComplaintUi.nativeSnapshot(false));
                    String unused = ComplaintUi.status = jSONObject.optString("status", ComplaintUi.status);
                    String strOptString = jSONObject.optString("accountUid", "0");
                    if (!ComplaintUi.accountUid.equals(strOptString)) {
                        String unused2 = ComplaintUi.accountUid = strOptString;
                        ComplaintUi.pending.clear();
                        if (ComplaintUi.active != null) {
                            ComplaintUi.active.dismiss();
                            AlertDialog unused3 = ComplaintUi.active = null;
                        }
                    }
                    if (jSONObject.optBoolean("enabled")) {
                        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("events");
                        if (jSONArrayOptJSONArray != null) {
                            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                                if (jSONObjectOptJSONObject != null) {
                                    if (ComplaintUi.pending.size() >= 32) {
                                        ComplaintUi.pending.removeFirst();
                                    }
                                    ComplaintUi.pending.addLast(jSONObjectOptJSONObject);
                                }
                            }
                        }
                        ComplaintUi.showNext();
                    } else {
                        ComplaintUi.pending.clear();
                        if (ComplaintUi.active != null) {
                            ComplaintUi.active.dismiss();
                            AlertDialog unused4 = ComplaintUi.active = null;
                        }
                    }
                } catch (Exception | LinkageError unused5) {
                    String unused6 = ComplaintUi.status = "Chưa đọc được trạng thái tố cáo";
                }
                if (ComplaintUi.running) {
                    ComplaintUi.UI.postDelayed(this, 750L);
                }
            }
        }
    };

    private ComplaintUi() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showHistory$5() {
        Activity gameActivity = activity.get();
        if (!usable(gameActivity)) {
            gameActivity = Main.getGameActivity();
        }
        if (usable(gameActivity)) {
            StringBuilder sb = new StringBuilder();
            try {
                JSONObject jSONObject = new JSONObject(nativeSnapshot(true));
                sb.append(jSONObject.optString("status", status));
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("entries");
                if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() == 0) {
                    sb.append("\n\nChưa ghi nhận tố cáo mới trong phiên này.");
                } else {
                    for (int length = jSONArrayOptJSONArray.length() - 1; length >= 0; length--) {
                        JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(length);
                        sb.append("\n\n");
                        sb.append(jSONObject2.optString("name", "Không rõ"));
                        sb.append("\n");
                        sb.append(jSONObject2.optString("reason", "Không rõ"));
                    }
                }
            } catch (Exception | LinkageError unused) {
                sb.append(status);
            }
            new AlertDialog.Builder(gameActivity).setTitle("Tự Động Check Tố Cáo").setMessage(sb.toString().trim()).setPositiveButton("Đóng", (DialogInterface.OnClickListener) null).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showNext$2(DialogInterface dialogInterface, int i) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showNext$3(DialogInterface dialogInterface, int i) {
        UI.post(new d(0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showNext$4(DialogInterface dialogInterface) {
        active = null;
        UI.postDelayed(new d(3), 350L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$start$0(Activity activity2) {
        activity = new WeakReference<>(activity2);
        Handler handler = UI;
        Runnable runnable = POLL;
        handler.removeCallbacks(runnable);
        running = true;
        handler.post(runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$stop$1() {
        running = false;
        UI.removeCallbacks(POLL);
        pending.clear();
        AlertDialog alertDialog = active;
        if (alertDialog != null) {
            alertDialog.dismiss();
            active = null;
        }
        activity.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native String nativeSnapshot(boolean z);

    public static void showHistory() {
        UI.post(new d(1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void showNext() {
        Activity activity2 = activity.get();
        if (active == null) {
            ArrayDeque<JSONObject> arrayDeque = pending;
            if (arrayDeque.isEmpty() || !usable(activity2)) {
                return;
            }
            JSONObject jSONObjectRemoveFirst = arrayDeque.removeFirst();
            final int i = 0;
            AlertDialog.Builder positiveButton = new AlertDialog.Builder(activity2).setTitle("Phát hiện tố cáo mới").setMessage("Người tố cáo: " + jSONObjectRemoveFirst.optString("name", "Không rõ") + "\nLý do: " + jSONObjectRemoveFirst.optString("reason", "Không rõ") + "\n\nThông tin nhận từ lịch sử tố cáo của game.").setPositiveButton("Đã xem", new DialogInterface.OnClickListener() { // from class: com.android.support.a
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    switch (i) {
                        case CygnusIconDrawable.MAP /* 0 */:
                            ComplaintUi.lambda$showNext$2(dialogInterface, i2);
                            break;
                        default:
                            ComplaintUi.lambda$showNext$3(dialogInterface, i2);
                            break;
                    }
                }
            });
            final int i2 = 1;
            AlertDialog alertDialogCreate = positiveButton.setNeutralButton("Lịch sử", new DialogInterface.OnClickListener() { // from class: com.android.support.a
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i3) {
                    switch (i2) {
                        case CygnusIconDrawable.MAP /* 0 */:
                            ComplaintUi.lambda$showNext$2(dialogInterface, i3);
                            break;
                        default:
                            ComplaintUi.lambda$showNext$3(dialogInterface, i3);
                            break;
                    }
                }
            }).create();
            active = alertDialogCreate;
            alertDialogCreate.setOnDismissListener(new b());
            active.show();
        }
    }

    public static void start(Activity activity2) {
        UI.post(new c(activity2, 0));
    }

    public static void stop() {
        UI.post(new d(2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean usable(Activity activity2) {
        return (activity2 == null || activity2.isFinishing() || activity2.isDestroyed()) ? false : true;
    }
}
