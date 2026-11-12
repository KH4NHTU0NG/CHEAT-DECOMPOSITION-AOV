package com.android.support.ThemesData.LGL;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Base64;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.support.Preferences;
import com.android.support.interfaces.IMenuBuilder;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public class LGLMenu implements IMenuBuilder {
    LGLShared sharedData;

    public LGLMenu(LGLShared lGLShared) {
        this.sharedData = lGLShared;
        Preferences.context = lGLShared.getContext;
    }

    private int convertDipToPixels(int i) {
        return (int) ((i * this.sharedData.getContext.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private int dp(int i) {
        return (int) TypedValue.applyDimension(1, i, this.sharedData.getContext.getResources().getDisplayMetrics());
    }

    @Override // com.android.support.interfaces.IMenuBuilder
    public void build() {
        this.sharedData.rootFrame = new FrameLayout(this.sharedData.getContext);
        LGLShared lGLShared = this.sharedData;
        lGLShared.rootFrame.setOnTouchListener(lGLShared.MenuData.onTouchListener());
        this.sharedData.mRootContainer = new RelativeLayout(this.sharedData.getContext);
        this.sharedData.mCollapsed = new RelativeLayout(this.sharedData.getContext);
        this.sharedData.mCollapsed.setVisibility(0);
        LGLShared lGLShared2 = this.sharedData;
        lGLShared2.mCollapsed.setAlpha(lGLShared2.MenuStyle.getIconAlpha());
        buildTheBox();
        buildOpenIcon();
        WebView webViewBuildOpenIconWeb = buildOpenIconWeb();
        TextView textViewBuildSettings = buildSettings();
        this.sharedData.mSettings = new LinearLayout(this.sharedData.getContext);
        this.sharedData.mSettings.setOrientation(1);
        buildFeature(this.sharedData.MenuData.SettingsList(), this.sharedData.mSettings);
        RelativeLayout relativeLayout = new RelativeLayout(this.sharedData.getContext);
        relativeLayout.setPadding(10, 5, 10, 5);
        relativeLayout.setVerticalGravity(16);
        TextView textViewBuildTitle = buildTitle();
        TextView textViewBuildSubTitle = buildSubTitle();
        this.sharedData.scrollView = new ScrollView(this.sharedData.getContext);
        this.sharedData.scrlLL = new LinearLayout.LayoutParams(-1, dp(this.sharedData.MenuStyle.getMenuHeight()));
        this.sharedData.scrlLLExpanded = new LinearLayout.LayoutParams(this.sharedData.mExpanded.getLayoutParams());
        LGLShared lGLShared3 = this.sharedData;
        LinearLayout.LayoutParams layoutParams = lGLShared3.scrlLLExpanded;
        layoutParams.weight = 1.0f;
        ScrollView scrollView = lGLShared3.scrollView;
        if (!Preferences.isExpanded) {
            layoutParams = lGLShared3.scrlLL;
        }
        scrollView.setLayoutParams(layoutParams);
        LGLShared lGLShared4 = this.sharedData;
        lGLShared4.scrollView.setBackgroundColor(lGLShared4.MenuStyle.getMenuFeatureBgColor());
        this.sharedData.mods = new LinearLayout(this.sharedData.getContext);
        this.sharedData.mods.setOrientation(1);
        RelativeLayout relativeLayout2 = new RelativeLayout(this.sharedData.getContext);
        relativeLayout2.setPadding(10, 3, 10, 3);
        relativeLayout2.setVerticalGravity(17);
        Button buttonBuildHideButton = buildHideButton();
        Button buttonBuildCloseButton = buildCloseButton();
        LGLShared lGLShared5 = this.sharedData;
        lGLShared5.mRootContainer.addView(lGLShared5.mCollapsed);
        LGLShared lGLShared6 = this.sharedData;
        lGLShared6.mRootContainer.addView(lGLShared6.mExpanded);
        if (this.sharedData.MenuData.IconWebViewData() != null) {
            this.sharedData.mCollapsed.addView(webViewBuildOpenIconWeb);
        } else {
            LGLShared lGLShared7 = this.sharedData;
            lGLShared7.mCollapsed.addView(lGLShared7.startimage);
        }
        relativeLayout.addView(textViewBuildTitle);
        relativeLayout.addView(textViewBuildSettings);
        this.sharedData.mExpanded.addView(relativeLayout);
        this.sharedData.mExpanded.addView(textViewBuildSubTitle);
        LGLShared lGLShared8 = this.sharedData;
        lGLShared8.scrollView.addView(lGLShared8.mods);
        LGLShared lGLShared9 = this.sharedData;
        lGLShared9.mExpanded.addView(lGLShared9.scrollView);
        relativeLayout2.addView(buttonBuildHideButton);
        relativeLayout2.addView(buttonBuildCloseButton);
        this.sharedData.mExpanded.addView(relativeLayout2);
        LGLShared lGLShared10 = this.sharedData;
        lGLShared10.MenuData.Init(lGLShared10.getContext, textViewBuildTitle, textViewBuildSubTitle);
    }

    public Button buildCloseButton() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(11);
        Button button = new Button(this.sharedData.getContext);
        button.setLayoutParams(layoutParams);
        button.setBackgroundColor(0);
        button.setText("MINIMIZE");
        button.setTextColor(this.sharedData.MenuStyle.getTextColor());
        button.setOnClickListener(new View.OnClickListener() { // from class: com.android.support.ThemesData.LGL.LGLMenu.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LGLMenu.this.sharedData.mCollapsed.setVisibility(0);
                LGLShared lGLShared = LGLMenu.this.sharedData;
                lGLShared.mCollapsed.setAlpha(lGLShared.MenuStyle.getIconAlpha());
                LGLMenu.this.sharedData.mExpanded.setVisibility(8);
            }
        });
        return button;
    }

    @Override // com.android.support.interfaces.IMenuBuilder
    public void buildFeature(String[] strArr, LinearLayout linearLayout) {
        boolean z;
        LinearLayout linearLayout2;
        int i;
        int i2 = 0;
        for (int i3 = 0; i3 < strArr.length; i3++) {
            String strReplaceFirst = strArr[i3];
            if (strReplaceFirst.contains("_True")) {
                strReplaceFirst = strReplaceFirst.replaceFirst("_True", "");
                z = true;
            } else {
                z = false;
            }
            if (strReplaceFirst.contains("CollapseAdd_")) {
                LinearLayout linearLayout3 = this.sharedData.mCollapse;
                strReplaceFirst = strReplaceFirst.replaceFirst("CollapseAdd_", "");
                linearLayout2 = linearLayout3;
            } else {
                linearLayout2 = linearLayout;
            }
            String[] strArrSplit = strReplaceFirst.split("_");
            if (TextUtils.isDigitsOnly(strArrSplit[0]) || strArrSplit[0].matches("-[0-9]*")) {
                int i4 = Integer.parseInt(strArrSplit[0]);
                strReplaceFirst = strReplaceFirst.replaceFirst(strArrSplit[0] + "_", "");
                i2++;
                i = i4;
            } else {
                i = i3 - i2;
            }
            String[] strArrSplit2 = strReplaceFirst.split("_");
            String str = strArrSplit2[0];
            str.getClass();
            switch (str) {
                case "ButtonLink":
                    i2++;
                    this.sharedData.components.ButtonLink(linearLayout2, strArrSplit2[1], strArrSplit2[2]);
                    break;
                case "Toggle":
                    this.sharedData.components.Switch(linearLayout2, i, strArrSplit2[1], z);
                    break;
                case "InputValue":
                    if (strArrSplit2.length == 3) {
                        this.sharedData.components.InputNum(linearLayout2, i, strArrSplit2[2], Integer.parseInt(strArrSplit2[1]));
                    }
                    if (strArrSplit2.length == 2) {
                        this.sharedData.components.InputNum(linearLayout2, i, strArrSplit2[1], 0);
                        break;
                    } else {
                        break;
                    }
                    break;
                case "SeekBar":
                    this.sharedData.components.SeekBar(linearLayout2, i, strArrSplit2[1], Integer.parseInt(strArrSplit2[2]), Integer.parseInt(strArrSplit2[3]));
                    break;
                case "InputText":
                    this.sharedData.components.InputText(linearLayout2, i, strArrSplit2[1]);
                    break;
                case "Collapse":
                    this.sharedData.components.Collapse(linearLayout2, strArrSplit2[1], z);
                    i2++;
                    break;
                case "Spinner":
                    this.sharedData.components.TextView(linearLayout2, strArrSplit2[1]);
                    this.sharedData.components.Spinner(linearLayout2, i, strArrSplit2[1], strArrSplit2[2]);
                    break;
                case "ButtonOnOff":
                    this.sharedData.components.ButtonOnOff(linearLayout2, i, strArrSplit2[1], z);
                    break;
                case "RichTextView":
                    i2++;
                    this.sharedData.components.TextView(linearLayout2, strArrSplit2[1]);
                    break;
                case "Category":
                    i2++;
                    this.sharedData.components.Category(linearLayout2, strArrSplit2[1]);
                    break;
                case "RichWebView":
                    i2++;
                    this.sharedData.components.WebTextView(linearLayout2, strArrSplit2[1]);
                    break;
                case "RadioButton":
                    this.sharedData.components.RadioButton(linearLayout2, i, strArrSplit2[1], strArrSplit2[2]);
                    break;
                case "InputLValue":
                    if (strArrSplit2.length == 3) {
                        this.sharedData.components.InputLNum(linearLayout2, i, strArrSplit2[2], Long.parseLong(strArrSplit2[1]));
                    }
                    if (strArrSplit2.length == 2) {
                        this.sharedData.components.InputLNum(linearLayout2, i, strArrSplit2[1], 0L);
                        break;
                    } else {
                        break;
                    }
                    break;
                case "CheckBox":
                    this.sharedData.components.CheckBox(linearLayout2, i, strArrSplit2[1], z);
                    break;
                case "Button":
                    this.sharedData.components.Button(linearLayout2, i, strArrSplit2[1]);
                    break;
            }
        }
    }

    public Button buildHideButton() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(9);
        Button button = new Button(this.sharedData.getContext);
        button.setLayoutParams(layoutParams);
        button.setBackgroundColor(0);
        button.setText("HIDE/KILL (Hold)");
        button.setTextColor(this.sharedData.MenuStyle.getTextColor());
        button.setOnClickListener(new View.OnClickListener() { // from class: com.android.support.ThemesData.LGL.LGLMenu.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LGLMenu.this.sharedData.mCollapsed.setVisibility(0);
                LGLMenu.this.sharedData.mCollapsed.setAlpha(0.0f);
                LGLMenu.this.sharedData.mExpanded.setVisibility(8);
                Toast.makeText(view.getContext(), "Icon hidden. Remember the hidden icon position", 1).show();
            }
        });
        button.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.android.support.ThemesData.LGL.LGLMenu.4
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                Toast.makeText(view.getContext(), "Menu killed", 1).show();
                LGLShared lGLShared = LGLMenu.this.sharedData;
                lGLShared.rootFrame.removeView(lGLShared.mRootContainer);
                LGLShared lGLShared2 = LGLMenu.this.sharedData;
                lGLShared2.mWindowManager.removeView(lGLShared2.rootFrame);
                return false;
            }
        });
        return button;
    }

    public void buildOpenIcon() {
        this.sharedData.startimage = new ImageView(this.sharedData.getContext);
        this.sharedData.startimage.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        int iApplyDimension = (int) TypedValue.applyDimension(1, this.sharedData.MenuStyle.getIconSize(), this.sharedData.getContext.getResources().getDisplayMetrics());
        this.sharedData.startimage.getLayoutParams().height = iApplyDimension;
        this.sharedData.startimage.getLayoutParams().width = iApplyDimension;
        this.sharedData.startimage.setScaleType(ImageView.ScaleType.FIT_XY);
        byte[] bArrDecode = Base64.decode(this.sharedData.MenuData.Icon(), 0);
        this.sharedData.startimage.setImageBitmap(BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length));
        ((ViewGroup.MarginLayoutParams) this.sharedData.startimage.getLayoutParams()).topMargin = convertDipToPixels(10);
        LGLShared lGLShared = this.sharedData;
        lGLShared.startimage.setOnTouchListener(lGLShared.MenuData.onTouchListener());
        this.sharedData.startimage.setOnClickListener(new View.OnClickListener() { // from class: com.android.support.ThemesData.LGL.LGLMenu.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LGLMenu.this.sharedData.mCollapsed.setVisibility(8);
                LGLMenu.this.sharedData.mExpanded.setVisibility(0);
            }
        });
    }

    public WebView buildOpenIconWeb() {
        WebView webView = new WebView(this.sharedData.getContext);
        webView.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        int iApplyDimension = (int) TypedValue.applyDimension(1, this.sharedData.MenuStyle.getIconSize(), this.sharedData.getContext.getResources().getDisplayMetrics());
        webView.getLayoutParams().height = iApplyDimension;
        webView.getLayoutParams().width = iApplyDimension;
        webView.loadData("<html><head></head><body style=\"margin: 0; padding: 0\"><img src=\"" + this.sharedData.MenuData.IconWebViewData() + "\" width=\"" + this.sharedData.MenuStyle.getIconSize() + "\" height=\"" + this.sharedData.MenuStyle.getIconSize() + "\" ></body></html>", "text/html", "utf-8");
        webView.setBackgroundColor(0);
        webView.setAlpha(this.sharedData.MenuStyle.getIconAlpha());
        webView.getSettings().setCacheMode(2);
        webView.setOnTouchListener(this.sharedData.MenuData.onTouchListener());
        return webView;
    }

    public TextView buildSettings() {
        TextView textView = new TextView(this.sharedData.getContext);
        textView.setText(Build.VERSION.SDK_INT >= 23 ? "⚙" : "🔧");
        textView.setTextColor(this.sharedData.MenuStyle.getTextColor());
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setTextSize(20.0f);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(11);
        textView.setLayoutParams(layoutParams);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.android.support.ThemesData.LGL.LGLMenu.2
            boolean settingsOpen;

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    boolean z = this.settingsOpen;
                    this.settingsOpen = !z;
                    if (z) {
                        LGLShared lGLShared = LGLMenu.this.sharedData;
                        lGLShared.scrollView.removeView(lGLShared.mSettings);
                        LGLShared lGLShared2 = LGLMenu.this.sharedData;
                        lGLShared2.scrollView.addView(lGLShared2.mods);
                    } else {
                        LGLShared lGLShared3 = LGLMenu.this.sharedData;
                        lGLShared3.scrollView.removeView(lGLShared3.mods);
                        LGLShared lGLShared4 = LGLMenu.this.sharedData;
                        lGLShared4.scrollView.addView(lGLShared4.mSettings);
                        LGLMenu.this.sharedData.scrollView.scrollTo(0, 0);
                    }
                } catch (IllegalStateException unused) {
                }
            }
        });
        return textView;
    }

    public TextView buildSubTitle() {
        TextView textView = new TextView(this.sharedData.getContext);
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView.setMarqueeRepeatLimit(-1);
        textView.setSingleLine(true);
        textView.setSelected(true);
        textView.setTextColor(this.sharedData.MenuStyle.getTextColor());
        textView.setTextSize(10.0f);
        textView.setGravity(17);
        textView.setPadding(0, 0, 0, 5);
        return textView;
    }

    public void buildTheBox() {
        this.sharedData.mExpanded = new LinearLayout(this.sharedData.getContext);
        this.sharedData.mExpanded.setVisibility(8);
        LGLShared lGLShared = this.sharedData;
        lGLShared.mExpanded.setBackgroundColor(lGLShared.MenuStyle.getMenuBgColor());
        this.sharedData.mExpanded.setOrientation(1);
        this.sharedData.mExpanded.setLayoutParams(new LinearLayout.LayoutParams(dp(this.sharedData.MenuStyle.getMenuWidth()), -2));
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(this.sharedData.MenuStyle.getMenuBgColor());
        gradientDrawable.setColor(this.sharedData.MenuStyle.getMenuBgColor());
        gradientDrawable.setStroke(1, Color.parseColor("#32cb00"));
    }

    public TextView buildTitle() {
        TextView textView = new TextView(this.sharedData.getContext);
        textView.setTextColor(this.sharedData.MenuStyle.getTextColor());
        textView.setTextSize(18.0f);
        textView.setGravity(17);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(14);
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    @Override // com.android.support.interfaces.IMenuBuilder
    public void destroy() {
        LGLShared lGLShared = this.sharedData;
        FrameLayout frameLayout = lGLShared.rootFrame;
        if (frameLayout != null) {
            lGLShared.mWindowManager.removeView(frameLayout);
        }
    }

    @Override // com.android.support.interfaces.IMenuBuilder
    public void setVisibility(int i) {
        FrameLayout frameLayout = this.sharedData.rootFrame;
        if (frameLayout != null) {
            frameLayout.setVisibility(i);
        }
    }

    @Override // com.android.support.interfaces.IMenuBuilder
    public void show() {
        LGLShared lGLShared = this.sharedData;
        lGLShared.rootFrame.addView(lGLShared.mRootContainer);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() { // from class: com.android.support.ThemesData.LGL.LGLMenu.6
            boolean viewLoaded = false;

            @Override // java.lang.Runnable
            public void run() {
                if (Preferences.loadPref && !LGLMenu.this.sharedData.MenuData.IsGameLibLoaded()) {
                    LGLShared lGLShared2 = LGLMenu.this.sharedData;
                    if (!lGLShared2.stopChecking) {
                        if (!this.viewLoaded) {
                            lGLShared2.components.Category(lGLShared2.mods, "Save preferences was been enabled. Waiting for game lib to be loaded...\n\nForce load menu may not apply mods instantly. You would need to reactivate them again");
                            LGLShared lGLShared3 = LGLMenu.this.sharedData;
                            lGLShared3.components.Button(lGLShared3.mods, -100, "Force load menu");
                            this.viewLoaded = true;
                        }
                        handler.postDelayed(this, 600L);
                        return;
                    }
                }
                LGLMenu.this.sharedData.mods.removeAllViews();
                LGLMenu lGLMenu = LGLMenu.this;
                lGLMenu.buildFeature(lGLMenu.sharedData.MenuData.GetFeatureList(), LGLMenu.this.sharedData.mods);
            }
        }, 500L);
    }
}
