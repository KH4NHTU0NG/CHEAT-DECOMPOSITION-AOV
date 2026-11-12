package com.android.support.auth;

import android.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

/* JADX INFO: loaded from: /tmp/apk_work/classes8.dex */
public class GateActivity extends Activity {
    private boolean checking;
    private TextView countdown;
    private long deadline;
    private boolean foreground;
    private boolean leaving;
    private TextView message;
    private TextView title;
    private final Handler main = new Handler(Looper.getMainLooper());
    private final ExecutorService worker = Executors.newSingleThreadExecutor();
    private final Runnable refresh = new Runnable() { // from class: com.android.support.auth.GateActivity.1
        @Override // java.lang.Runnable
        public void run() {
            if (GateActivity.this.leaving || !GateActivity.this.foreground) {
                return;
            }
            GateActivity.this.checkServer();
        }
    };
    private final Runnable tick = new Runnable() { // from class: com.android.support.auth.GateActivity.2
        @Override // java.lang.Runnable
        public void run() {
            if (GateActivity.this.leaving || !GateActivity.this.foreground || GateActivity.this.deadline == 0) {
                return;
            }
            long jMax = (Math.max(0L, GateActivity.this.deadline - SystemClock.elapsedRealtime()) + 999) / 1000;
            GateActivity.this.countdown.setText("Tự thoát sau " + jMax + " giây");
            if (jMax == 0) {
                GateActivity.this.exitGate();
            } else {
                GateActivity.this.main.postDelayed(this, 500L);
            }
        }
    };

    protected boolean isRemoteNotice() {
        return false;
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        setTheme(R.style.Theme.Material.NoActionBar);
        super.onCreate(bundle);
        this.foreground = true;
        getWindow().setFlags(1024, 1024);
        getWindow().setBackgroundDrawable(new ColorDrawable(-16249320));
        if (isRemoteNotice()) {
            showClosed(getIntent().getStringExtra("maintenance_message"));
        } else {
            checkServer();
        }
    }

    private int dp(int i) {
        return Math.round(i * getResources().getDisplayMetrics().density);
    }

    private TextView text(String str, int i, int i2, boolean z) {
        TextView textView = new TextView(this);
        textView.setText(str);
        textView.setTextSize(i);
        textView.setTextColor(i2);
        textView.setGravity(17);
        if (z) {
            textView.setTypeface(Typeface.DEFAULT, 1);
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.bottomMargin = dp(14);
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    private void buildView() {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setGravity(17);
        linearLayout.setBackgroundColor(-16249320);
        LinearLayout linearLayout2 = new LinearLayout(this);
        linearLayout2.setOrientation(1);
        linearLayout2.setGravity(17);
        linearLayout2.setPadding(dp(28), dp(24), dp(28), dp(20));
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(-15655892);
        gradientDrawable.setCornerRadius(dp(18));
        gradientDrawable.setStroke(dp(1), -14231826);
        linearLayout2.setBackground(gradientDrawable);
        linearLayout.addView(linearLayout2, new LinearLayout.LayoutParams(Math.min(dp(540), getResources().getDisplayMetrics().widthPixels - dp(40)), -2));
        linearLayout2.addView(text("MAP SÁNG ANDROID V3", 16, -14231826, true));
        TextView textViewText = text("", 25, -1, true);
        this.title = textViewText;
        linearLayout2.addView(textViewText);
        TextView textViewText2 = text("", 18, -2168587, false);
        this.message = textViewText2;
        linearLayout2.addView(textViewText2);
        TextView textViewText3 = text("", 15, -14231826, false);
        this.countdown = textViewText3;
        linearLayout2.addView(textViewText3);
        LinearLayout linearLayout3 = new LinearLayout(this);
        linearLayout3.setGravity(17);
        Button button = new Button(this);
        button.setText("THOÁT");
        button.setOnClickListener(new View.OnClickListener() { // from class: com.android.support.auth.GateActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m0lambda$buildView$0$comandroidsupportauthGateActivity(view);
            }
        });
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, dp(50), 1.0f);
        layoutParams.setMargins(dp(4), 0, dp(4), 0);
        linearLayout3.addView(button, layoutParams);
        linearLayout2.addView(linearLayout3, new LinearLayout.LayoutParams(-1, -2));
        setContentView(linearLayout);
    }

    /* JADX INFO: renamed from: lambda$buildView$0$com-android-support-auth-GateActivity, reason: not valid java name */
    /* synthetic */ void m0lambda$buildView$0$comandroidsupportauthGateActivity(View view) {
        exitGate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkServer() {
        if (this.checking || this.leaving || !this.foreground) {
            return;
        }
        this.main.removeCallbacks(this.refresh);
        this.checking = true;
        this.worker.execute(new Runnable() { // from class: com.android.support.auth.GateActivity$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() throws Throwable {
                this.f$0.m1lambda$checkServer$0$comandroidsupportauthGateActivity();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$checkServer$0$com-android-support-auth-GateActivity, reason: not valid java name */
    /* synthetic */ void m1lambda$checkServer$0$comandroidsupportauthGateActivity() throws Throwable {
        JSONObject jSONObject;
        HttpURLConnection httpURLConnection = null;
        jSONObject = null;
        final JSONObject jSONObject2 = null;
        httpURLConnection = null;
        try {
            try {
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL("https://hackgame.lienquan.io.vn/apk-manager/status.php").openConnection();
                try {
                    try {
                        httpURLConnection2.setConnectTimeout(4000);
                        httpURLConnection2.setReadTimeout(4000);
                        httpURLConnection2.setInstanceFollowRedirects(false);
                        httpURLConnection2.setUseCaches(false);
                        httpURLConnection2.setRequestMethod("POST");
                        httpURLConnection2.setDoOutput(true);
                        httpURLConnection2.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                        byte[] bytes = "app_id=map-sang-v4-20261103-r2&package=com.garena.game.kgvo&phienban=11".getBytes("UTF-8");
                        httpURLConnection2.setFixedLengthStreamingMode(bytes.length);
                        OutputStream outputStream = httpURLConnection2.getOutputStream();
                        try {
                            outputStream.write(bytes);
                            if (outputStream != null) {
                                outputStream.close();
                            }
                            int responseCode = httpURLConnection2.getResponseCode();
                            if (responseCode != 200) {
                                throw new IOException("HTTP " + responseCode);
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
                                        jSONObject = new JSONObject(byteArrayOutputStream.toString("UTF-8"));
                                        try {
                                            if ("ok".equals(jSONObject.optString("status")) && (jSONObject.opt("enabled") instanceof Boolean)) {
                                                jSONObject2 = jSONObject;
                                            }
                                            if (httpURLConnection2 != null) {
                                                httpURLConnection2.disconnect();
                                            }
                                        } catch (Exception e) {
                                            e = e;
                                            httpURLConnection = httpURLConnection2;
                                            Log.w("ApkGate", "Status request failed: " + e.getClass().getSimpleName() + ": " + e.getMessage());
                                            if (httpURLConnection != null) {
                                                httpURLConnection.disconnect();
                                            }
                                            jSONObject2 = jSONObject;
                                        }
                                        this.main.post(new Runnable() { // from class: com.android.support.auth.GateActivity$$ExternalSyntheticLambda3
                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                this.f$0.m2lambda$checkServer$1$comandroidsupportauthGateActivity(jSONObject2);
                                            }
                                        });
                                        return;
                                    }
                                    if (byteArrayOutputStream.size() + i > 16384) {
                                        throw new Exception("size");
                                    }
                                    byteArrayOutputStream.write(bArr, 0, i);
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
                    } catch (Throwable th5) {
                        th = th5;
                        httpURLConnection = httpURLConnection2;
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        throw th;
                    }
                } catch (Exception e2) {
                    e = e2;
                    jSONObject = null;
                }
            } catch (Exception e3) {
                e = e3;
                jSONObject = null;
            }
        } catch (Throwable th6) {
            th = th6;
        }
    }

    /* JADX INFO: renamed from: lambda$checkServer$1$com-android-support-auth-GateActivity, reason: not valid java name */
    /* synthetic */ void m2lambda$checkServer$1$comandroidsupportauthGateActivity(JSONObject jSONObject) {
        this.checking = false;
        if (this.leaving || isFinishing() || !this.foreground) {
            return;
        }
        if (jSONObject != null) {
            if (jSONObject.optBoolean("enabled")) {
                launchGame();
                return;
            } else {
                showClosed(jSONObject.optString("message"));
                return;
            }
        }
        if (this.title == null) {
            buildView();
        }
        this.title.setText("CHƯA KẾT NỐI ĐƯỢC MÁY CHỦ");
        this.message.setText("Vui lòng kiểm tra kết nối mạng. APK sẽ tự kết nối lại.");
        scheduleRefresh();
    }

    private void scheduleRefresh() {
        this.main.removeCallbacks(this.refresh);
        if (this.leaving || !this.foreground) {
            return;
        }
        this.main.postDelayed(this.refresh, 3000L);
    }

    private void showClosed(String str) {
        if (this.title == null) {
            buildView();
        }
        this.title.setText("APK ĐANG BẢO TRÌ");
        if (str == null || str.trim().isEmpty()) {
            str = "Máy chủ đã tạm đóng APK. Vui lòng quay lại sau.";
        }
        TextView textView = this.message;
        if (str.length() > 600) {
            str = str.substring(0, 600);
        }
        textView.setText(str);
        Log.i("ApkGate", "Maintenance board shown; Unity not launched by gate");
        if (this.deadline == 0) {
            this.deadline = SystemClock.elapsedRealtime() + 30000;
            this.tick.run();
        }
        scheduleRefresh();
    }

    private void launchGame() {
        this.main.removeCallbacks(this.tick);
        this.main.removeCallbacks(this.refresh);
        this.deadline = 0L;
        Intent intent = new Intent(getIntent());
        intent.setClassName(getPackageName(), "com.garena.game.kgtw.SGameActivity");
        intent.setFlags(intent.getFlags() & 195);
        intent.removeExtra("maintenance_message");
        if (isRemoteNotice()) {
            intent.setAction("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
        }
        this.leaving = true;
        try {
            startActivity(intent);
            finish();
            Log.i("ApkGate", "Server open; handed off to original game launcher");
        } catch (RuntimeException unused) {
            this.leaving = false;
            if (this.title == null) {
                buildView();
            }
            this.title.setText("KHÔNG MỞ ĐƯỢC APK");
            this.message.setText("Vui lòng thử lại hoặc liên hệ quản trị viên.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void exitGate() {
        if (this.leaving) {
            return;
        }
        this.leaving = true;
        this.main.removeCallbacksAndMessages(null);
        this.worker.shutdownNow();
        finishAndRemoveTask();
        this.main.postDelayed(new Runnable() { // from class: com.android.support.auth.GateActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                Process.killProcess(Process.myPid());
            }
        }, 100L);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        exitGate();
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        this.foreground = true;
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.leaving) {
            return;
        }
        if (this.deadline != 0) {
            this.main.removeCallbacks(this.tick);
            this.tick.run();
        }
        if (this.checking) {
            return;
        }
        scheduleRefresh();
    }

    @Override // android.app.Activity
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        if (this.leaving || isChangingConfigurations()) {
            return;
        }
        exitGate();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        this.foreground = false;
        this.main.removeCallbacks(this.refresh);
        this.main.removeCallbacks(this.tick);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        this.main.removeCallbacksAndMessages(null);
        this.worker.shutdownNow();
        super.onDestroy();
    }
}
