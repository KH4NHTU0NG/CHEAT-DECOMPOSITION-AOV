package com.android.support.ThemesData.CYGNUS;

import android.R;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Choreographer;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.support.BuildConfig;
import com.android.support.GameAccountLogout;
import com.android.support.Preferences;
import com.android.support.ThemesData.DZ.MenuLogoDrawable;
import com.android.support.auth.AuthSession;
import com.android.support.auth.AuthSessionManager;
import com.android.support.interfaces.IMenuBuilder;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public class CygnusMenu implements IMenuBuilder {
    private static final String[] ACCENT_SWATCHES;
    private static final Map<Integer, String> FEATURE_TAB;
    private static final String MENU_TITLE;
    private static final TabDef[] TABS = {new TabDef("main", "CHÍNH", 12), new TabDef("skin", "SKIN", 9), new TabDef("extra", "PHỤ", 12), new TabDef("equipment", "BÁN ĐỒ", 22), new TabDef("auto", "TỰ ĐỘNG", 13), new TabDef("settings", "CÀI ĐẶT", 15), new TabDef("music", "NHẠC", 29)};
    private View aimRayRow;
    private int currentFps;
    private boolean destroyed;
    private boolean featuresBuilt;
    private Choreographer.FrameCallback fpsCallback;
    private long fpsWindowStart;
    private int frameCount;
    private Runnable libraryCheck;
    private CygnusMusicPanel musicPanel;
    private View saveSettingsRow;
    private final CygnusShared sharedData;
    private ValueAnimator starAnim;
    private TextView starLeft;
    private TextView starRight;
    private TextView statFps;
    private TextView statKey;
    private TextView statTime;
    private Runnable tickTask;
    private TextView titleView;
    private String activeTab = "main";
    private final Map<String, LinearLayout> tabContainers = new HashMap();
    private final Map<String, LinearLayout> tabButtons = new HashMap();
    private final Handler ticker = new Handler(Looper.getMainLooper());
    private final Handler libraryHandler = new Handler(Looper.getMainLooper());

    public interface OnValueChange {
        void apply(int i);
    }

    public static final class TabDef {
        final int iconKind;
        final String id;
        final String label;

        public TabDef(String str, String str2, int i) {
            this.id = str;
            this.label = str2;
            this.iconKind = i;
        }
    }

    static {
        HashMap map = new HashMap();
        FEATURE_TAB = map;
        map.put(1, "main");
        map.put(2, "main");
        map.put(3, "extra");
        map.put(5, "extra");
        map.put(7, "extra");
        map.put(101, "main");
        map.put(110, "main");
        map.put(111, "main");
        map.put(-1, "main");
        map.put(102, "auto");
        map.put(6, "extra");
        map.put(103, "auto");
        map.put(104, "auto");
        map.put(105, "auto");
        for (int i = 120; i <= 132; i++) {
            FEATURE_TAB.put(Integer.valueOf(i), "equipment");
        }
        for (int i2 = 150; i2 <= 160; i2++) {
            FEATURE_TAB.put(Integer.valueOf(i2), "equipment");
        }
        Map<Integer, String> map2 = FEATURE_TAB;
        map2.put(140, "auto");
        map2.put(141, "auto");
        map2.put(4, "skin");
        map2.put(6001, "skin");
        map2.put(6002, "skin");
        for (int i3 = 6003; i3 <= 6006; i3++) {
            FEATURE_TAB.put(Integer.valueOf(i3), "skin");
        }
        MENU_TITLE = safeMenuTitle();
        ACCENT_SWATCHES = new String[]{"#FF32E0FF", "#FFFFAE55", "#FFB78BFF", "#FF5FE3A1", "#FFFF6B7A"};
    }

    public CygnusMenu(CygnusShared cygnusShared) {
        this.sharedData = cygnusShared;
        Preferences.context = cygnusShared.getContext;
    }

    public static /* synthetic */ int access$1208(CygnusMenu cygnusMenu) {
        int i = cygnusMenu.frameCount;
        cygnusMenu.frameCount = i + 1;
        return i;
    }

    private LinearLayout actionButton(String str, String str2, int i, boolean z, View.OnClickListener onClickListener) {
        LinearLayout linearLayout = new LinearLayout(this.sharedData.getContext);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        linearLayout.setPadding(dp(10), dp(10), dp(10), dp(10));
        linearLayout.setClickable(true);
        linearLayout.setFocusable(true);
        linearLayout.setBackground(strokedDrawable(z ? 352279918 : this.sharedData.MenuStyle.getSurfaceStrongColor(), z ? 1509907822 : this.sharedData.MenuStyle.getOutlineColor(), 8.0f));
        ImageView imageView = new ImageView(this.sharedData.getContext);
        CygnusIconDrawable cygnusIconDrawable = new CygnusIconDrawable(i, z ? this.sharedData.MenuStyle.getDangerColor() : this.sharedData.MenuStyle.getAccentColor());
        cygnusIconDrawable.setStrokeWidth(1.9f);
        imageView.setImageDrawable(cygnusIconDrawable);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setPadding(dp(6), dp(6), dp(6), dp(6));
        int accentSoftColor = z ? 452943214 : this.sharedData.MenuStyle.getAccentSoftColor();
        CygnusStyle cygnusStyle = this.sharedData.MenuStyle;
        imageView.setBackground(strokedDrawable(accentSoftColor, z ? cygnusStyle.getDangerColor() : cygnusStyle.getAccentBorderColor(), 6.0f));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dp(30), dp(30));
        layoutParams.rightMargin = dp(10);
        imageView.setLayoutParams(layoutParams);
        LinearLayout linearLayout2 = new LinearLayout(this.sharedData.getContext);
        linearLayout2.setOrientation(1);
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        TextView textView = new TextView(this.sharedData.getContext);
        textView.setText(str);
        textView.setTextColor(z ? this.sharedData.MenuStyle.getDangerColor() : this.sharedData.MenuStyle.getTextColor2());
        textView.setTypeface(Typeface.DEFAULT, 1);
        textView.setTextSize(2, 12.0f);
        linearLayout2.addView(textView);
        TextView textView2 = new TextView(this.sharedData.getContext);
        textView2.setText(str2);
        textView2.setTextColor(this.sharedData.MenuStyle.getSecondaryTextColor());
        textView2.setTextSize(2, 10.0f);
        linearLayout2.addView(textView2);
        TextView textView3 = new TextView(this.sharedData.getContext);
        textView3.setText("›");
        textView3.setTextColor(this.sharedData.MenuStyle.getSecondaryTextColor());
        textView3.setTextSize(2, 18.0f);
        linearLayout.addView(imageView);
        linearLayout.addView(linearLayout2);
        linearLayout.addView(textView3);
        linearLayout.setOnClickListener(onClickListener);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.setMargins(0, 0, 0, dp(6));
        linearLayout.setLayoutParams(layoutParams2);
        return linearLayout;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addBanLogoutRow(LinearLayout linearLayout) {
        LinearLayout linearLayout2 = new LinearLayout(this.sharedData.getContext);
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(16);
        linearLayout2.setPadding(dp(12), dp(12), dp(12), dp(12));
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(352279918);
        gradientDrawable.setStroke(dp(1), 1509907822);
        gradientDrawable.setCornerRadius(dp(8));
        linearLayout2.setBackground(gradientDrawable);
        linearLayout2.setClickable(true);
        linearLayout2.setFocusable(true);
        ImageView imageView = new ImageView(this.sharedData.getContext);
        CygnusIconDrawable cygnusIconDrawable = new CygnusIconDrawable(18, this.sharedData.MenuStyle.getDangerColor());
        cygnusIconDrawable.setStrokeWidth(2.0f);
        imageView.setImageDrawable(cygnusIconDrawable);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setPadding(dp(6), dp(6), dp(6), dp(6));
        imageView.setBackground(strokedDrawable(452943214, this.sharedData.MenuStyle.getDangerColor(), 6.0f));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dp(32), dp(32));
        layoutParams.setMargins(0, 0, dp(10), 0);
        imageView.setLayoutParams(layoutParams);
        LinearLayout linearLayout3 = new LinearLayout(this.sharedData.getContext);
        linearLayout3.setOrientation(1);
        linearLayout3.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        TextView textView = new TextView(this.sharedData.getContext);
        textView.setText("Đăng xuất acc bị ban");
        textView.setTextColor(this.sharedData.MenuStyle.getDangerColor());
        textView.setTypeface(Typeface.DEFAULT, 1);
        textView.setTextSize(2, 13.0f);
        TextView textView2 = new TextView(this.sharedData.getContext);
        textView2.setText("Xoá token Garena và khởi động lại game.");
        textView2.setTextColor(this.sharedData.MenuStyle.getSecondaryTextColor());
        textView2.setTextSize(2, 11.0f);
        linearLayout3.addView(textView);
        linearLayout3.addView(textView2);
        TextView textView3 = new TextView(this.sharedData.getContext);
        textView3.setText("›");
        textView3.setTextColor(this.sharedData.MenuStyle.getSecondaryTextColor());
        textView3.setTextSize(2, 20.0f);
        linearLayout2.addView(imageView);
        linearLayout2.addView(linearLayout3);
        linearLayout2.addView(textView3);
        linearLayout2.setOnClickListener(new c(this, 6));
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.setMargins(0, dp(6), 0, dp(4));
        linearLayout.addView(linearLayout2, layoutParams2);
    }

    private void addKvRow(LinearLayout linearLayout, String str, String str2, int i) {
        LinearLayout linearLayout2 = new LinearLayout(this.sharedData.getContext);
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(16);
        linearLayout2.setPadding(0, dp(6), 0, dp(6));
        TextView textView = new TextView(this.sharedData.getContext);
        textView.setText(str);
        textView.setTextColor(this.sharedData.MenuStyle.getTextColor2());
        textView.setTextSize(2, 12.0f);
        textView.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        TextView textView2 = new TextView(this.sharedData.getContext);
        textView2.setText(str2);
        textView2.setTextColor(i);
        textView2.setTypeface(CygnusFonts.DIGIT, 0);
        textView2.setTextSize(2, 11.0f);
        textView2.setSingleLine(true);
        textView2.setEllipsize(TextUtils.TruncateAt.END);
        textView2.setMaxWidth(dp(180));
        linearLayout2.addView(textView);
        linearLayout2.addView(textView2);
        linearLayout.addView(linearLayout2, new LinearLayout.LayoutParams(-1, -2));
    }

    private void applyFloatingIconSize(int i) {
        if (this.sharedData.startimage == null) {
            return;
        }
        int iDp = dp(i);
        ViewGroup.LayoutParams layoutParams = this.sharedData.startimage.getLayoutParams();
        layoutParams.width = iDp;
        layoutParams.height = iDp;
        this.sharedData.startimage.setLayoutParams(layoutParams);
    }

    private void applyTabStyle(LinearLayout linearLayout, ImageView imageView, CygnusIconDrawable cygnusIconDrawable, TextView textView, boolean z) {
        if (z) {
            linearLayout.setBackground(strokedDrawable(this.sharedData.MenuStyle.getAccentSoftColor(), this.sharedData.MenuStyle.getAccentColor(), 8.0f));
            cygnusIconDrawable.setColor(this.sharedData.MenuStyle.getAccentColor());
            textView.setTextColor(this.sharedData.MenuStyle.getAccentColor());
        } else {
            linearLayout.setBackground(strokedDrawable(this.sharedData.MenuStyle.getSurfaceStrongColor(), this.sharedData.MenuStyle.getOutlineSoftColor(), 8.0f));
            cygnusIconDrawable.setColor(this.sharedData.MenuStyle.getSecondaryTextColor());
            textView.setTextColor(this.sharedData.MenuStyle.getSecondaryTextColor());
        }
    }

    private LinearLayout buildAppearanceGroup() {
        LinearLayout linearLayoutGroupCard = groupCard("TUỲ CHỈNH", 14);
        LinearLayout linearLayout = new LinearLayout(this.sharedData.getContext);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        linearLayout.setPadding(0, dp(6), 0, dp(6));
        LinearLayout linearLayout2 = new LinearLayout(this.sharedData.getContext);
        linearLayout2.setOrientation(1);
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        TextView textView = new TextView(this.sharedData.getContext);
        textView.setText("Màu chủ đạo");
        textView.setTextColor(this.sharedData.MenuStyle.getTextColor2());
        textView.setTextSize(2, 12.0f);
        TextView textView2 = new TextView(this.sharedData.getContext);
        textView2.setText("Áp dụng khi mở lại menu");
        textView2.setTextColor(this.sharedData.MenuStyle.getSecondaryTextColor());
        textView2.setTextSize(2, 10.0f);
        linearLayout2.addView(textView);
        linearLayout2.addView(textView2);
        LinearLayout linearLayout3 = new LinearLayout(this.sharedData.getContext);
        linearLayout3.setOrientation(0);
        linearLayout3.setGravity(16);
        int accentColor = this.sharedData.MenuStyle.getAccentColor();
        for (String str : ACCENT_SWATCHES) {
            int color = Color.parseColor(str);
            View view = new View(this.sharedData.getContext);
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(1);
            gradientDrawable.setColor(color);
            if (color == accentColor) {
                gradientDrawable.setStroke(dp(2), this.sharedData.MenuStyle.getTextColor2());
            }
            view.setBackground(gradientDrawable);
            view.setClickable(true);
            view.setFocusable(true);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dp(18), dp(18));
            layoutParams.leftMargin = dp(7);
            view.setLayoutParams(layoutParams);
            view.setOnClickListener(new ViewOnClickListenerC0000a(this, color, linearLayout3, 0));
            linearLayout3.addView(view);
        }
        linearLayout.addView(linearLayout2);
        linearLayout.addView(linearLayout3);
        linearLayoutGroupCard.addView(linearLayout);
        linearLayoutGroupCard.addView(rowDivider());
        final int i = 0;
        linearLayoutGroupCard.addView(miniSliderRow("Kích thước icon nổi", "Icon menu khi thu nhỏ", this.sharedData.MenuStyle.getIconSize(), 24, 96, "dp", new OnValueChange(this) { // from class: com.android.support.ThemesData.CYGNUS.b
            public final /* synthetic */ CygnusMenu b;

            {
                this.b = this;
            }

            @Override // com.android.support.ThemesData.CYGNUS.CygnusMenu.OnValueChange
            public final void apply(int i2) {
                switch (i) {
                    case CygnusIconDrawable.MAP /* 0 */:
                        this.b.lambda$buildAppearanceGroup$8(i2);
                        break;
                    default:
                        this.b.lambda$buildAppearanceGroup$9(i2);
                        break;
                }
            }
        }));
        linearLayoutGroupCard.addView(rowDivider());
        final int i2 = 1;
        linearLayoutGroupCard.addView(miniSliderRow("Độ trong icon nổi", "Độ mờ khi menu đóng", Math.round(this.sharedData.MenuStyle.getIconAlpha() * 100.0f), 0, 100, "%", new OnValueChange(this) { // from class: com.android.support.ThemesData.CYGNUS.b
            public final /* synthetic */ CygnusMenu b;

            {
                this.b = this;
            }

            @Override // com.android.support.ThemesData.CYGNUS.CygnusMenu.OnValueChange
            public final void apply(int i3) {
                switch (i2) {
                    case CygnusIconDrawable.MAP /* 0 */:
                        this.b.lambda$buildAppearanceGroup$8(i3);
                        break;
                    default:
                        this.b.lambda$buildAppearanceGroup$9(i3);
                        break;
                }
            }
        }));
        linearLayoutGroupCard.addView(rowDivider());
        linearLayoutGroupCard.addView(actionButton("Ẩn icon nổi", "Chạm lại vị trí cũ để mở menu — icon tự hiện lại", 28, false, new c(this, 0)));
        return linearLayoutGroupCard;
    }

    private LinearLayout buildContent() {
        LinearLayout linearLayout = new LinearLayout(this.sharedData.getContext);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(dp(4), dp(4), dp(4), dp(4));
        linearLayout.setBackground(strokedDrawable(this.sharedData.MenuStyle.getSurfaceColor(), this.sharedData.MenuStyle.getOutlineColor(), 12.0f));
        int i = this.sharedData.getContext.getResources().getDisplayMetrics().heightPixels;
        Math.min(Math.min(dp(this.sharedData.MenuStyle.getMenuHeight()), i - dp(160)) + dp(120), i - dp(120));
        this.sharedData.scrlLL = new LinearLayout.LayoutParams(-1, -1);
        this.sharedData.scrlLLExpanded = new LinearLayout.LayoutParams(-1, -1);
        this.sharedData.scrollView = new ScrollView(this.sharedData.getContext);
        this.sharedData.scrollView.setLayoutParams(new LinearLayout.LayoutParams(-1, 0, 1.0f));
        this.sharedData.scrollView.setVerticalScrollBarEnabled(false);
        this.sharedData.scrollView.setFillViewport(true);
        this.sharedData.scrollView.setOverScrollMode(2);
        this.sharedData.mods = this.tabContainers.get("main");
        LinearLayout linearLayout2 = new LinearLayout(this.sharedData.getContext);
        linearLayout2.setOrientation(1);
        for (TabDef tabDef : TABS) {
            LinearLayout linearLayout3 = this.tabContainers.get(tabDef.id);
            linearLayout3.setVisibility("main".equals(tabDef.id) ? 0 : 8);
            linearLayout2.addView(linearLayout3);
        }
        this.sharedData.scrollView.addView(linearLayout2);
        linearLayout.addView(this.sharedData.scrollView);
        return linearLayout;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void buildCygnusSettings(LinearLayout linearLayout) {
        linearLayout.addView(buildAppearanceGroup());
        LinearLayout linearLayoutGroupCard = groupCard("HỆ THỐNG", 22);
        addKvRow(linearLayoutGroupCard, "Trạng thái hook", "● 42/42 OK", this.sharedData.MenuStyle.getAccentColor());
        addKvRow(linearLayoutGroupCard, "Key", keyStatusText(), this.sharedData.MenuStyle.getAccentColor());
        addKvRow(linearLayoutGroupCard, "Phiên bản", versionText(), this.sharedData.MenuStyle.getSecondaryTextColor());
        addKvRow(linearLayoutGroupCard, "Package", "kgvo", this.sharedData.MenuStyle.getSecondaryTextColor());
        linearLayout.addView(linearLayoutGroupCard);
        LinearLayout linearLayoutGroupCard2 = groupCard("DỌN DẸP", 21);
        linearLayoutGroupCard2.addView(actionButton("Reset toàn bộ chức năng", "Đưa mọi toggle/slider về mặc định", 21, false, new c(this, 1)));
        linearLayoutGroupCard2.addView(actionButton("Xoá cache menu", "Xoá logo cache + preset đã tải", 16, false, new c(this, 2)));
        linearLayout.addView(linearLayoutGroupCard2);
    }

    private void buildFloatingIcon() {
        this.sharedData.startimage = new ImageView(this.sharedData.getContext);
        int iDp = dp(this.sharedData.MenuStyle.getIconSize());
        this.sharedData.startimage.setLayoutParams(new RelativeLayout.LayoutParams(iDp, iDp));
        this.sharedData.startimage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        ImageView imageView = this.sharedData.startimage;
        CygnusShared cygnusShared = this.sharedData;
        imageView.setImageDrawable(new MenuLogoDrawable(cygnusShared.getContext, cygnusShared.MenuStyle.getAccentColor()));
        this.sharedData.startimage.setPadding(dp(2), dp(2), dp(2), dp(2));
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(1);
        gradientDrawable.setColor(this.sharedData.MenuStyle.getMenuBgColor());
        gradientDrawable.setStroke(dp(2), this.sharedData.MenuStyle.getAccentColor());
        this.sharedData.startimage.setBackground(gradientDrawable);
        this.sharedData.startimage.setContentDescription("Mở menu");
        CygnusShared cygnusShared2 = this.sharedData;
        cygnusShared2.startimage.setOnTouchListener(cygnusShared2.MenuData.onTouchListener());
        this.sharedData.startimage.setOnClickListener(new c(this, 3));
    }

    private LinearLayout buildHeader() {
        LinearLayout linearLayout = new LinearLayout(this.sharedData.getContext);
        linearLayout.setOrientation(0);
        linearLayout.setPadding(dp(6), dp(6), dp(6), dp(4));
        linearLayout.setGravity(16);
        LinearLayout linearLayout2 = new LinearLayout(this.sharedData.getContext);
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(16);
        linearLayout2.setBackground(strokedDrawable(this.sharedData.MenuStyle.getSurfaceColor(), this.sharedData.MenuStyle.getOutlineColor(), 12.0f));
        linearLayout2.setPadding(dp(8), dp(6), dp(6), dp(6));
        linearLayout2.setMinimumHeight(dp(46));
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        ImageView imageView = new ImageView(this.sharedData.getContext);
        CygnusShared cygnusShared = this.sharedData;
        imageView.setImageDrawable(new MenuLogoDrawable(cygnusShared.getContext, cygnusShared.MenuStyle.getAccentColor()));
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        GradientDrawable gradientDrawableRoundedDrawable = roundedDrawable(this.sharedData.MenuStyle.getAccentSoftColor(), 9.0f);
        gradientDrawableRoundedDrawable.setStroke(dp(1), this.sharedData.MenuStyle.getAccentBorderColor());
        imageView.setBackground(gradientDrawableRoundedDrawable);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(dp(32), dp(32)));
        View view = new View(this.sharedData.getContext);
        GradientDrawable.Orientation orientation = GradientDrawable.Orientation.LEFT_RIGHT;
        GradientDrawable gradientDrawable = new GradientDrawable(orientation, new int[]{3334399, this.sharedData.MenuStyle.getAccentColor()});
        gradientDrawable.setCornerRadius(dp(2));
        view.setBackground(gradientDrawable);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, dp(3), 1.0f);
        layoutParams.setMargins(dp(6), 0, dp(6), 0);
        view.setLayoutParams(layoutParams);
        TextView textViewMakeStar = makeStar();
        TextView textViewMakeStar2 = makeStar();
        TextView textView = new TextView(this.sharedData.getContext);
        this.titleView = textView;
        textView.setText(MENU_TITLE);
        this.titleView.setTextColor(this.sharedData.MenuStyle.getTextColor2());
        this.titleView.setTypeface(Typeface.DEFAULT, 1);
        this.titleView.setTextSize(2, 13.0f);
        this.titleView.setSingleLine(true);
        this.titleView.setEllipsize(TextUtils.TruncateAt.END);
        this.titleView.setLetterSpacing(0.06f);
        this.titleView.setGravity(17);
        this.titleView.setShadowLayer(dp(6), 0.0f, 0.0f, this.sharedData.MenuStyle.getAccentBorderColor());
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.setMargins(dp(4), 0, dp(4), 0);
        this.titleView.setLayoutParams(layoutParams2);
        View view2 = new View(this.sharedData.getContext);
        GradientDrawable gradientDrawable2 = new GradientDrawable(orientation, new int[]{this.sharedData.MenuStyle.getAccentColor(), 3334399});
        gradientDrawable2.setCornerRadius(dp(2));
        view2.setBackground(gradientDrawable2);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(0, dp(3), 1.0f);
        layoutParams3.setMargins(dp(6), 0, dp(6), 0);
        view2.setLayoutParams(layoutParams3);
        ImageView imageViewMakeVectorButton = makeVectorButton(15, "Cài đặt", false);
        imageViewMakeVectorButton.setOnClickListener(new c(this, 4));
        ImageView imageViewMakeVectorButton2 = makeVectorButton(16, "Thu nhỏ", true);
        imageViewMakeVectorButton2.setOnClickListener(new c(this, 5));
        linearLayout2.addView(imageView);
        linearLayout2.addView(view);
        linearLayout2.addView(textViewMakeStar);
        linearLayout2.addView(this.titleView);
        linearLayout2.addView(textViewMakeStar2);
        linearLayout2.addView(view2);
        linearLayout2.addView(imageViewMakeVectorButton);
        linearLayout2.addView(imageViewMakeVectorButton2);
        this.starLeft = textViewMakeStar;
        this.starRight = textViewMakeStar2;
        linearLayout.addView(linearLayout2, new LinearLayout.LayoutParams(-1, -2));
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        return linearLayout;
    }

    private void buildMenuCard() {
        this.sharedData.mExpanded = new LinearLayout(this.sharedData.getContext);
        this.sharedData.mExpanded.setVisibility(8);
        this.sharedData.mExpanded.setOrientation(1);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            WindowManager windowManager = (WindowManager) this.sharedData.getContext.getSystemService("window");
            if (windowManager != null) {
                windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
            }
        } catch (Throwable unused) {
        }
        if (displayMetrics.widthPixels == 0) {
            displayMetrics = this.sharedData.getContext.getResources().getDisplayMetrics();
        }
        int iDp = dp(this.sharedData.MenuStyle.getMenuWidth());
        int iDp2 = displayMetrics.widthPixels - dp(16);
        int i = (int) (displayMetrics.widthPixels * 0.6f);
        int iMin = Math.min(iDp, iDp2);
        if (iMin >= i) {
            i = iMin;
        }
        this.sharedData.mExpanded.setLayoutParams(new LinearLayout.LayoutParams(i, Math.min(dp(this.sharedData.MenuStyle.getMenuHeight() + 120), displayMetrics.heightPixels - dp(32))));
        GradientDrawable gradientDrawableRoundedDrawable = roundedDrawable(this.sharedData.MenuStyle.getMenuBgColor(), this.sharedData.MenuStyle.getMenuCorner());
        gradientDrawableRoundedDrawable.setStroke(dp(2), this.sharedData.MenuStyle.getAccentColor());
        this.sharedData.mExpanded.setBackground(gradientDrawableRoundedDrawable);
        this.sharedData.mExpanded.setClipChildren(true);
        this.sharedData.mExpanded.setClipToPadding(true);
        this.sharedData.mExpanded.setElevation(dp(14));
        this.sharedData.mExpanded.setClipToOutline(true);
    }

    private LinearLayout buildRail() {
        LinearLayout linearLayout = new LinearLayout(this.sharedData.getContext);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(dp(6), dp(6), dp(6), dp(6));
        linearLayout.setBackground(strokedDrawable(this.sharedData.MenuStyle.getSurfaceColor(), this.sharedData.MenuStyle.getOutlineColor(), 12.0f));
        TextView textView = new TextView(this.sharedData.getContext);
        textView.setText("DANH MỤC");
        textView.setTextColor(this.sharedData.MenuStyle.getSecondaryTextColor());
        textView.setTextSize(2, 9.0f);
        textView.setLetterSpacing(0.16f);
        textView.setTypeface(Typeface.DEFAULT, 1);
        textView.setGravity(17);
        textView.setPadding(0, dp(2), 0, dp(8));
        linearLayout.addView(textView);
        for (TabDef tabDef : TABS) {
            LinearLayout linearLayoutMakeTabButton = makeTabButton(tabDef);
            linearLayoutMakeTabButton.setOnClickListener(new e(this, tabDef, 0));
            this.tabButtons.put(tabDef.id, linearLayoutMakeTabButton);
            linearLayout.addView(linearLayoutMakeTabButton);
        }
        return linearLayout;
    }

    private LinearLayout buildStatusBar() {
        LinearLayout linearLayout = new LinearLayout(this.sharedData.getContext);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        linearLayout.setPadding(dp(12), dp(6), dp(12), dp(6));
        linearLayout.setBackground(strokedDrawable(this.sharedData.MenuStyle.getSurfaceColor(), this.sharedData.MenuStyle.getOutlineColor(), 10.0f));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(dp(6), dp(2), dp(6), dp(6));
        linearLayout.setLayoutParams(layoutParams);
        this.statFps = statChip("--", this.sharedData.MenuStyle.getAccentColor());
        this.statKey = statChip(keyStatusText(), this.sharedData.MenuStyle.getAccentColor());
        this.statTime = statChip("--:--:--", this.sharedData.MenuStyle.getTextColor2());
        linearLayout.addView(statPair("FPS", this.statFps));
        View view = new View(this.sharedData.getContext);
        view.setLayoutParams(new LinearLayout.LayoutParams(0, 0, 1.0f));
        linearLayout.addView(view);
        linearLayout.addView(statPair("KEY", this.statKey));
        View view2 = new View(this.sharedData.getContext);
        view2.setLayoutParams(new LinearLayout.LayoutParams(dp(12), 0));
        linearLayout.addView(view2);
        linearLayout.addView(statPair("GIỜ", this.statTime));
        return linearLayout;
    }

    private LinearLayout buildWorkspace() {
        LinearLayout linearLayout = new LinearLayout(this.sharedData.getContext);
        linearLayout.setOrientation(0);
        linearLayout.setPadding(dp(6), 0, dp(6), 0);
        LinearLayout linearLayoutBuildRail = buildRail();
        LinearLayout linearLayoutBuildContent = buildContent();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dp(this.sharedData.getContext.getResources().getDisplayMetrics().widthPixels < dp(760) ? 100 : 130), -1);
        layoutParams.setMargins(0, 0, dp(6), 0);
        ScrollView scrollView = new ScrollView(this.sharedData.getContext);
        scrollView.setVerticalScrollBarEnabled(false);
        scrollView.setFillViewport(true);
        scrollView.addView(linearLayoutBuildRail, new FrameLayout.LayoutParams(-1, -2));
        linearLayout.addView(scrollView, layoutParams);
        linearLayout.addView(linearLayoutBuildContent, new LinearLayout.LayoutParams(0, -1, 1.0f));
        return linearLayout;
    }

    private void confirmBannedAccountLogout() {
        AlertDialog alertDialogCreate = new AlertDialog.Builder(this.sharedData.getContext, R.style.Theme.Material.Dialog.Alert).setTitle("Đăng xuất acc bị ban?").setMessage("Sẽ xoá token Garena khỏi thiết bị và khởi động lại game. Sau restart, đăng nhập bằng acc khác. Chỉ dùng khi acc hiện tại bị ban / khoá.").setPositiveButton("XÁC NHẬN", new DialogInterface.OnClickListener() { // from class: com.android.support.ThemesData.CYGNUS.f
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.a.lambda$confirmBannedAccountLogout$12(dialogInterface, i);
            }
        }).setNegativeButton("HUỶ", (DialogInterface.OnClickListener) null).create();
        Window window = alertDialogCreate.getWindow();
        if (this.sharedData.overlayRequired && window != null) {
            window.setType(Build.VERSION.SDK_INT >= 26 ? 2038 : 2002);
        }
        alertDialogCreate.show();
        Window window2 = alertDialogCreate.getWindow();
        if (window2 != null) {
            window2.setBackgroundDrawable(strokedDrawable(this.sharedData.MenuStyle.getMenuBgColor(), this.sharedData.MenuStyle.getDangerColor(), 12.0f));
            window2.addFlags(2);
            WindowManager.LayoutParams attributes = window2.getAttributes();
            attributes.dimAmount = 0.6f;
            window2.setAttributes(attributes);
        }
        if (alertDialogCreate.getButton(-1) != null) {
            alertDialogCreate.getButton(-1).setTextColor(this.sharedData.MenuStyle.getDangerColor());
        }
        if (alertDialogCreate.getButton(-2) != null) {
            alertDialogCreate.getButton(-2).setTextColor(this.sharedData.MenuStyle.getSecondaryTextColor());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:79:0x011e  */
    public void dispatchDescriptor(String str, LinearLayout linearLayout) {
        boolean z;
        String strReplaceFirst = str;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (strReplaceFirst.contains("_True")) {
            strReplaceFirst = strReplaceFirst.replaceFirst("_True", "");
            z = true;
        } else {
            z = false;
        }
        if (strReplaceFirst.contains("CollapseAdd_")) {
            strReplaceFirst = strReplaceFirst.replaceFirst("CollapseAdd_", "");
        }
        int iIndexOf = strReplaceFirst.indexOf(95);
        if (iIndexOf < 1) {
            return;
        }
        String strSubstring = strReplaceFirst.substring(0, iIndexOf);
        try {
            if (!strSubstring.matches("-?[0-9]+")) {
                if (strSubstring.equals("Category")) {
                    return;
                }
                strSubstring.equals("Collapse");
                return;
            }
            int i = Integer.parseInt(strSubstring);
            String strSubstring2 = strReplaceFirst.substring(iIndexOf + 1);
            if (i < 120 || i > 132) {
                if (i < 150 || i > 160) {
                    String str2 = FEATURE_TAB.get(Integer.valueOf(i));
                    LinearLayout linearLayout2 = (str2 == null || this.tabContainers.get(str2) == null) ? linearLayout : this.tabContainers.get(str2);
                    String[] strArrSplit = strSubstring2.split("_");
                    if (strArrSplit.length < 1) {
                        return;
                    }
                    try {
                        int i2 = 3;
                        switch (strArrSplit[0]) {
                            case "Toggle":
                                if (strArrSplit.length >= 2) {
                                    this.sharedData.components.Switch(linearLayout2, i, strArrSplit[1], z);
                                    View childAt = linearLayout2.getChildAt(linearLayout2.getChildCount() - 1);
                                    if (i == -1) {
                                        this.saveSettingsRow = childAt;
                                    }
                                    break;
                                }
                                break;
                            case "SeekBar":
                                if (strArrSplit.length >= 4) {
                                    int i3 = Integer.parseInt(strArrSplit[2]);
                                    int i4 = Integer.parseInt(strArrSplit[3]);
                                    if (strArrSplit.length < 5) {
                                        this.sharedData.components.SeekBar(linearLayout2, i, strArrSplit[1], i3, i4);
                                    } else {
                                        this.sharedData.components.SeekBar(linearLayout2, i, strArrSplit[1], i3, i4, Integer.parseInt(strArrSplit[4]));
                                    }
                                    break;
                                }
                                break;
                            case "Button":
                                if (strArrSplit.length >= 2) {
                                    this.sharedData.components.Button(linearLayout2, i, strArrSplit[1]);
                                    break;
                                }
                                break;
                            case "ButtonOnOff":
                                if (strArrSplit.length >= 2) {
                                    this.sharedData.components.ButtonOnOff(linearLayout2, i, strArrSplit[1], z);
                                    break;
                                }
                                break;
                            case "Spinner":
                                if (strArrSplit.length >= 3) {
                                    StringBuilder sb = new StringBuilder(strArrSplit[2]);
                                    while (i2 < strArrSplit.length) {
                                        sb.append('_');
                                        sb.append(strArrSplit[i2]);
                                        i2++;
                                    }
                                    this.sharedData.components.Spinner(linearLayout2, i, strArrSplit[1], sb.toString());
                                    break;
                                }
                                break;
                            case "InputText":
                                if (strArrSplit.length >= 2) {
                                    this.sharedData.components.InputText(linearLayout2, i, strArrSplit[1]);
                                    break;
                                }
                                break;
                            case "InputValue":
                                if (strArrSplit.length == 3) {
                                    this.sharedData.components.InputNum(linearLayout2, i, strArrSplit[2], Integer.parseInt(strArrSplit[1]));
                                    break;
                                } else {
                                    if (strArrSplit.length >= 2) {
                                        this.sharedData.components.InputNum(linearLayout2, i, strArrSplit[1], 0);
                                    }
                                    break;
                                }
                                break;
                            case "InputLValue":
                                if (strArrSplit.length == 3) {
                                    this.sharedData.components.InputLNum(linearLayout2, i, strArrSplit[2], Long.parseLong(strArrSplit[1]));
                                    break;
                                } else {
                                    if (strArrSplit.length >= 2) {
                                        this.sharedData.components.InputLNum(linearLayout2, i, strArrSplit[1], 0L);
                                    }
                                    break;
                                }
                                break;
                            case "CheckBox":
                                if (strArrSplit.length >= 2) {
                                    this.sharedData.components.CheckBox(linearLayout2, i, strArrSplit[1], z);
                                    break;
                                }
                                break;
                            case "RadioButton":
                                if (strArrSplit.length >= 3) {
                                    StringBuilder sb2 = new StringBuilder(strArrSplit[2]);
                                    while (i2 < strArrSplit.length) {
                                        sb2.append('_');
                                        sb2.append(strArrSplit[i2]);
                                        i2++;
                                    }
                                    this.sharedData.components.RadioButton(linearLayout2, i, strArrSplit[1], sb2.toString());
                                    break;
                                }
                                break;
                            case "ButtonLink":
                                if (strArrSplit.length >= 3) {
                                    this.sharedData.components.ButtonLink(linearLayout2, strArrSplit[1], strArrSplit[2]);
                                    break;
                                }
                                break;
                            case "RichTextView":
                                if (strArrSplit.length >= 2) {
                                    this.sharedData.components.TextView(linearLayout2, strArrSplit[1]);
                                    break;
                                }
                                break;
                            case "RichWebView":
                                if (strArrSplit.length >= 2) {
                                    this.sharedData.components.WebTextView(linearLayout2, strArrSplit[1]);
                                    break;
                                }
                                break;
                        }
                        if (i == 111) {
                            this.aimRayRow = linearLayout2.getChildAt(linearLayout2.getChildCount() - 1);
                        }
                        View view = this.saveSettingsRow;
                        if (view == null || this.aimRayRow == null || view.getParent() != linearLayout2 || this.aimRayRow.getParent() != linearLayout2) {
                            return;
                        }
                        linearLayout2.removeView(this.saveSettingsRow);
                        linearLayout2.addView(this.saveSettingsRow, linearLayout2.indexOfChild(this.aimRayRow) + 1);
                    } catch (NumberFormatException unused) {
                    }
                }
            }
        } catch (NumberFormatException unused2) {
        }
    }

    private int dp(int i) {
        return Math.round(TypedValue.applyDimension(1, i, this.sharedData.getContext.getResources().getDisplayMetrics()));
    }

    private LinearLayout groupCard(String str, int i) {
        LinearLayout linearLayout = new LinearLayout(this.sharedData.getContext);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(dp(12), dp(10), dp(12), dp(12));
        linearLayout.setBackground(strokedDrawable(this.sharedData.MenuStyle.getSurfaceStrongColor(), this.sharedData.MenuStyle.getOutlineSoftColor(), 10.0f));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(0, 0, 0, dp(8));
        linearLayout.setLayoutParams(layoutParams);
        LinearLayout linearLayout2 = new LinearLayout(this.sharedData.getContext);
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(16);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.setMargins(0, 0, 0, dp(10));
        linearLayout2.setLayoutParams(layoutParams2);
        ImageView imageView = new ImageView(this.sharedData.getContext);
        CygnusIconDrawable cygnusIconDrawable = new CygnusIconDrawable(i, this.sharedData.MenuStyle.getAccentColor());
        cygnusIconDrawable.setStrokeWidth(1.9f);
        imageView.setImageDrawable(cygnusIconDrawable);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(dp(14), dp(14));
        layoutParams3.rightMargin = dp(8);
        imageView.setLayoutParams(layoutParams3);
        TextView textView = new TextView(this.sharedData.getContext);
        textView.setText(str);
        textView.setTextColor(this.sharedData.MenuStyle.getAccentColor());
        textView.setTextSize(2, 10.0f);
        textView.setLetterSpacing(0.18f);
        textView.setTypeface(Typeface.DEFAULT, 1);
        textView.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        linearLayout2.addView(imageView);
        linearLayout2.addView(textView);
        linearLayout.addView(linearLayout2);
        return linearLayout;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String keyStatusText() {
        try {
            String string = this.sharedData.getContext.getSharedPreferences("KEY", 0).getString("KEY", "");
            if (!TextUtils.isEmpty(string)) {
                return maskKey(string);
            }
        } catch (Throwable unused) {
        }
        AuthSession session = AuthSessionManager.getSession();
        if (session == null) {
            return "TRIAL";
        }
        String licenseHint = session.getLicenseHint();
        return !TextUtils.isEmpty(licenseHint) ? maskKey(licenseHint) : "TRIAL";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$addBanLogoutRow$11(View view) {
        confirmBannedAccountLogout();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$buildAppearanceGroup$10(View view) {
        WindowManager windowManager;
        LinearLayout linearLayout = this.sharedData.mExpanded;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
        RelativeLayout relativeLayout = this.sharedData.mCollapsed;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(0);
            this.sharedData.mCollapsed.setAlpha(0.0f);
        }
        try {
            CygnusShared cygnusShared = this.sharedData;
            WindowManager.LayoutParams layoutParams = cygnusShared.vmParams;
            if (layoutParams != null && (windowManager = cygnusShared.mWindowManager) != null) {
                layoutParams.width = -2;
                layoutParams.height = -2;
                windowManager.updateViewLayout(cygnusShared.rootFrame, layoutParams);
            }
        } catch (Throwable unused) {
        }
        Toast.makeText(this.sharedData.getContext, "Icon đã ẩn. Chạm vị trí icon cũ để mở lại menu.", 1).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$buildAppearanceGroup$7(int i, LinearLayout linearLayout, View view) {
        CygnusShared cygnusShared = this.sharedData;
        cygnusShared.MenuStyle.writeAccent(cygnusShared.getContext, i);
        Toast.makeText(this.sharedData.getContext, "Đã lưu màu. Mở lại menu để áp dụng.", 0).show();
        for (int i2 = 0; i2 < linearLayout.getChildCount(); i2++) {
            View childAt = linearLayout.getChildAt(i2);
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(1);
            gradientDrawable.setColor(Color.parseColor(ACCENT_SWATCHES[i2]));
            if (childAt == view) {
                gradientDrawable.setStroke(dp(2), this.sharedData.MenuStyle.getTextColor2());
            }
            childAt.setBackground(gradientDrawable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$buildAppearanceGroup$8(int i) {
        CygnusShared cygnusShared = this.sharedData;
        cygnusShared.MenuStyle.writeIconSize(cygnusShared.getContext, i);
        applyFloatingIconSize(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$buildAppearanceGroup$9(int i) {
        float f = i / 100.0f;
        CygnusShared cygnusShared = this.sharedData;
        cygnusShared.MenuStyle.writeIconAlpha(cygnusShared.getContext, f);
        RelativeLayout relativeLayout = this.sharedData.mCollapsed;
        if (relativeLayout != null) {
            relativeLayout.setAlpha(f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$buildCygnusSettings$5(View view) {
        try {
            Preferences.with(this.sharedData.getContext).clear();
            CygnusShared cygnusShared = this.sharedData;
            cygnusShared.MenuData.Init(cygnusShared.getContext, this.titleView, this.statKey);
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$buildCygnusSettings$6(View view) {
        try {
            File cacheDir = this.sharedData.getContext.getCacheDir();
            if (cacheDir == null || !cacheDir.isDirectory()) {
                return;
            }
            for (File file : cacheDir.listFiles()) {
                if (file.getName().startsWith("cygnus-") || file.getName().startsWith("lmk-")) {
                    file.delete();
                }
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$buildFloatingIcon$0(View view) {
        openMenu();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$buildHeader$1(View view) {
        selectTab("settings");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$buildHeader$2(View view) {
        minimizeMenu();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$buildRail$4(TabDef tabDef, View view) {
        selectTab(tabDef.id);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$confirmBannedAccountLogout$12(DialogInterface dialogInterface, int i) {
        GameAccountLogout.resetAndRestart(this.sharedData.getContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startStarAnim$3(ValueAnimator valueAnimator) {
        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        float f = (0.6f * fFloatValue) + 0.4f;
        float f2 = (fFloatValue * 0.25f) + 0.8f;
        TextView textView = this.starLeft;
        if (textView != null) {
            textView.setAlpha(f);
            this.starLeft.setScaleX(f2);
            this.starLeft.setScaleY(f2);
        }
        TextView textView2 = this.starRight;
        if (textView2 != null) {
            textView2.setAlpha(f);
            this.starRight.setScaleX(f2);
            this.starRight.setScaleY(f2);
        }
    }

    private TextView makeStar() {
        TextView textView = new TextView(this.sharedData.getContext);
        textView.setText("★");
        textView.setTextColor(this.sharedData.MenuStyle.getAccentColor());
        textView.setTextSize(2, 11.0f);
        textView.setShadowLayer(dp(6), 0.0f, 0.0f, this.sharedData.MenuStyle.getAccentColor());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(dp(2), 0, dp(2), 0);
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    private LinearLayout makeTabButton(TabDef tabDef) {
        LinearLayout linearLayout = new LinearLayout(this.sharedData.getContext);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        linearLayout.setPadding(dp(10), dp(9), dp(10), dp(9));
        linearLayout.setClickable(true);
        linearLayout.setFocusable(true);
        ImageView imageView = new ImageView(this.sharedData.getContext);
        CygnusIconDrawable cygnusIconDrawable = new CygnusIconDrawable(tabDef.iconKind, this.sharedData.MenuStyle.getSecondaryTextColor());
        cygnusIconDrawable.setStrokeWidth(1.9f);
        imageView.setImageDrawable(cygnusIconDrawable);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dp(18), dp(18));
        layoutParams.setMargins(0, 0, dp(10), 0);
        imageView.setLayoutParams(layoutParams);
        TextView textView = new TextView(this.sharedData.getContext);
        textView.setText(tabDef.label);
        textView.setTextColor(this.sharedData.MenuStyle.getSecondaryTextColor());
        textView.setTextSize(2, 10.0f);
        textView.setTypeface(Typeface.DEFAULT, 1);
        textView.setLetterSpacing(0.14f);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        linearLayout.addView(imageView);
        linearLayout.addView(textView);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.setMargins(0, dp(2), 0, dp(2));
        linearLayout.setLayoutParams(layoutParams2);
        applyTabStyle(linearLayout, imageView, cygnusIconDrawable, textView, false);
        linearLayout.setTag(new Object[]{imageView, cygnusIconDrawable, textView});
        return linearLayout;
    }

    private ImageView makeVectorButton(int i, String str, boolean z) {
        ImageView imageView = new ImageView(this.sharedData.getContext);
        CygnusIconDrawable cygnusIconDrawable = new CygnusIconDrawable(i, this.sharedData.MenuStyle.getTextColor2());
        cygnusIconDrawable.setStrokeWidth(2.0f);
        imageView.setImageDrawable(cygnusIconDrawable);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setPadding(dp(8), dp(8), dp(8), dp(8));
        if (z) {
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{R.attr.state_pressed}, roundedDrawable(this.sharedData.MenuStyle.getDangerColor(), 8.0f));
            stateListDrawable.addState(new int[0], strokedDrawable(this.sharedData.MenuStyle.getSurfaceStrongColor(), this.sharedData.MenuStyle.getOutlineColor(), 8.0f));
            imageView.setBackground(stateListDrawable);
        } else {
            imageView.setBackground(strokedDrawable(this.sharedData.MenuStyle.getSurfaceStrongColor(), this.sharedData.MenuStyle.getOutlineColor(), 8.0f));
        }
        imageView.setClickable(true);
        imageView.setFocusable(true);
        imageView.setContentDescription(str);
        if (Build.VERSION.SDK_INT >= 26) {
            imageView.setTooltipText(str);
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dp(34), dp(34));
        layoutParams.setMargins(dp(3), 0, 0, 0);
        imageView.setLayoutParams(layoutParams);
        return imageView;
    }

    private String maskKey(String str) {
        String strTrim = str.trim();
        if (strTrim.length() <= 6) {
            return strTrim;
        }
        return strTrim.substring(0, Math.min(9, strTrim.length() - 3)) + "•••";
    }

    private LinearLayout miniSliderRow(String str, String str2, int i, final int i2, int i3, final String str3, final OnValueChange onValueChange) {
        LinearLayout linearLayout = new LinearLayout(this.sharedData.getContext);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        linearLayout.setPadding(0, dp(6), 0, dp(6));
        LinearLayout linearLayout2 = new LinearLayout(this.sharedData.getContext);
        linearLayout2.setOrientation(1);
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        TextView textView = new TextView(this.sharedData.getContext);
        textView.setText(str);
        textView.setTextColor(this.sharedData.MenuStyle.getTextColor2());
        textView.setTextSize(2, 12.0f);
        TextView textView2 = new TextView(this.sharedData.getContext);
        textView2.setText(str2);
        textView2.setTextColor(this.sharedData.MenuStyle.getSecondaryTextColor());
        textView2.setTextSize(2, 10.0f);
        linearLayout2.addView(textView);
        linearLayout2.addView(textView2);
        linearLayout.addView(linearLayout2);
        LinearLayout linearLayout3 = new LinearLayout(this.sharedData.getContext);
        linearLayout3.setOrientation(1);
        linearLayout3.setGravity(8388613);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dp(140), -2);
        layoutParams.leftMargin = dp(10);
        linearLayout3.setLayoutParams(layoutParams);
        final int[] iArr = {Math.max(i2, Math.min(i3, i))};
        int accentColor = this.sharedData.MenuStyle.getAccentColor();
        final TextView textView3 = new TextView(this.sharedData.getContext);
        StringBuilder sb = new StringBuilder();
        sb.append(iArr[0]);
        sb.append((str3 == null || str3.isEmpty()) ? "" : " ".concat(str3));
        textView3.setText(sb.toString());
        textView3.setTextColor(accentColor);
        textView3.setTypeface(CygnusFonts.DIGIT, 0);
        textView3.setTextSize(2, 12.0f);
        textView3.setGravity(8388613);
        textView3.setShadowLayer(dp(6), 0.0f, 0.0f, this.sharedData.MenuStyle.getAccentBorderColor());
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.bottomMargin = dp(2);
        textView3.setLayoutParams(layoutParams2);
        linearLayout3.addView(textView3);
        SeekBar seekBar = new SeekBar(this.sharedData.getContext);
        seekBar.setMax(i3 - i2);
        seekBar.setProgress(iArr[0] - i2);
        seekBar.setPadding(dp(10), dp(4), dp(10), dp(4));
        seekBar.setBackground(null);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(this.sharedData.MenuStyle.getSurfaceStrongColor());
        gradientDrawable.setStroke(dp(1), this.sharedData.MenuStyle.getOutlineColor());
        gradientDrawable.setCornerRadius(dp(4));
        gradientDrawable.setSize(-1, dp(8));
        GradientDrawable gradientDrawable2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{accentColor, -9442305});
        gradientDrawable2.setCornerRadius(dp(4));
        gradientDrawable2.setSize(-1, dp(8));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{gradientDrawable, new ClipDrawable(gradientDrawable2, 3, 1)});
        layerDrawable.setId(0, R.id.background);
        layerDrawable.setId(1, R.id.progress);
        seekBar.setProgressDrawable(layerDrawable);
        GradientDrawable gradientDrawable3 = new GradientDrawable();
        gradientDrawable3.setShape(1);
        gradientDrawable3.setColor(-1);
        gradientDrawable3.setStroke(dp(2), accentColor);
        gradientDrawable3.setSize(dp(16), dp(16));
        seekBar.setThumb(gradientDrawable3);
        seekBar.setThumbOffset(dp(8));
        seekBar.setLayoutParams(new LinearLayout.LayoutParams(-1, dp(28)));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.android.support.ThemesData.CYGNUS.CygnusMenu.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar2, int i4, boolean z) {
                String str4;
                iArr[0] = i2 + i4;
                TextView textView4 = textView3;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(iArr[0]);
                String str5 = str3;
                if (str5 == null || str5.isEmpty()) {
                    str4 = "";
                } else {
                    str4 = " " + str3;
                }
                sb2.append(str4);
                textView4.setText(sb2.toString());
                onValueChange.apply(iArr[0]);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar2) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar2) {
            }
        });
        linearLayout3.addView(seekBar);
        linearLayout.addView(linearLayout3);
        return linearLayout;
    }

    private void minimizeMenu() {
        setMenuTelemetryActive(false);
        CygnusMusicPanel cygnusMusicPanel = this.musicPanel;
        if (cygnusMusicPanel != null) {
            cygnusMusicPanel.hide();
        }
        setMusicFocus(false);
        this.sharedData.mCollapsed.setVisibility(0);
        CygnusShared cygnusShared = this.sharedData;
        cygnusShared.mCollapsed.setAlpha(cygnusShared.MenuStyle.getIconAlpha());
        this.sharedData.mExpanded.setVisibility(8);
        try {
            CygnusShared cygnusShared2 = this.sharedData;
            WindowManager.LayoutParams layoutParams = cygnusShared2.vmParams;
            if (layoutParams != null) {
                layoutParams.width = -2;
                layoutParams.height = -2;
                cygnusShared2.mWindowManager.updateViewLayout(cygnusShared2.rootFrame, layoutParams);
            }
        } catch (Throwable unused) {
        }
    }

    private void openMenu() {
        setMenuTelemetryActive(true);
        setMusicFocus("music".equals(this.activeTab));
        if (this.musicPanel != null && "music".equals(this.activeTab)) {
            this.musicPanel.show();
        }
        this.sharedData.mCollapsed.setVisibility(8);
        this.sharedData.mExpanded.setVisibility(0);
        try {
            ViewGroup.LayoutParams layoutParams = this.sharedData.mExpanded.getLayoutParams();
            CygnusShared cygnusShared = this.sharedData;
            WindowManager.LayoutParams layoutParams2 = cygnusShared.vmParams;
            if (layoutParams2 == null || layoutParams == null) {
                return;
            }
            layoutParams2.width = layoutParams.width;
            layoutParams2.height = layoutParams.height;
            cygnusShared.mWindowManager.updateViewLayout(cygnusShared.rootFrame, layoutParams2);
        } catch (Throwable unused) {
        }
    }

    private GradientDrawable roundedDrawable(int i, float f) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(i);
        gradientDrawable.setCornerRadius(dp(f));
        return gradientDrawable;
    }

    private View rowDivider() {
        View view = new View(this.sharedData.getContext);
        view.setBackgroundColor(this.sharedData.MenuStyle.getOutlineSoftColor());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, dp(1));
        layoutParams.setMargins(0, dp(2), 0, dp(2));
        view.setLayoutParams(layoutParams);
        return view;
    }

    private static String safeMenuTitle() {
        return BuildConfig.MENU_TITLE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void selectTab(String str) {
        if (str == null || this.tabContainers.get(str) == null) {
            return;
        }
        this.activeTab = str;
        if (this.musicPanel != null) {
            if ("music".equals(str) && this.sharedData.mExpanded.getVisibility() == 0) {
                this.musicPanel.show();
            } else {
                this.musicPanel.hide();
            }
        }
        setMusicFocus("music".equals(str));
        if (this.sharedData.scrollView != null) {
            for (Map.Entry<String, LinearLayout> entry : this.tabContainers.entrySet()) {
                entry.getValue().setVisibility(entry.getKey().equals(str) ? 0 : 8);
            }
            this.sharedData.scrollView.scrollTo(0, 0);
        }
        for (Map.Entry<String, LinearLayout> entry2 : this.tabButtons.entrySet()) {
            LinearLayout value = entry2.getValue();
            Object tag = value.getTag();
            if (tag instanceof Object[]) {
                Object[] objArr = (Object[]) tag;
                applyTabStyle(value, (ImageView) objArr[0], (CygnusIconDrawable) objArr[1], (TextView) objArr[2], entry2.getKey().equals(str));
            }
        }
    }

    private void setMenuTelemetryActive(boolean z) {
        if (z && !this.destroyed) {
            ValueAnimator valueAnimator = this.starAnim;
            if (valueAnimator != null && valueAnimator.isPaused()) {
                this.starAnim.resume();
            }
            startFpsTracker();
            return;
        }
        ValueAnimator valueAnimator2 = this.starAnim;
        if (valueAnimator2 != null && valueAnimator2.isStarted()) {
            this.starAnim.pause();
        }
        if (this.fpsCallback != null) {
            Choreographer.getInstance().removeFrameCallback(this.fpsCallback);
            this.fpsCallback = null;
        }
    }

    private void setMusicFocus(boolean z) {
        WindowManager windowManager;
        CygnusShared cygnusShared = this.sharedData;
        WindowManager.LayoutParams layoutParams = cygnusShared.vmParams;
        if (layoutParams == null || (windowManager = cygnusShared.mWindowManager) == null) {
            return;
        }
        if (z) {
            layoutParams.flags &= -9;
        } else {
            layoutParams.flags |= 8;
        }
        try {
            windowManager.updateViewLayout(cygnusShared.rootFrame, layoutParams);
        } catch (IllegalArgumentException unused) {
        }
    }

    private void startFpsTracker() {
        if (this.fpsCallback != null || this.destroyed) {
            return;
        }
        this.frameCount = 0;
        this.fpsWindowStart = System.currentTimeMillis();
        this.fpsCallback = new Choreographer.FrameCallback() { // from class: com.android.support.ThemesData.CYGNUS.CygnusMenu.3
            @Override // android.view.Choreographer.FrameCallback
            public void doFrame(long j) {
                CygnusMenu.access$1208(CygnusMenu.this);
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (jCurrentTimeMillis - CygnusMenu.this.fpsWindowStart >= 1000) {
                    CygnusMenu cygnusMenu = CygnusMenu.this;
                    cygnusMenu.currentFps = cygnusMenu.frameCount;
                    CygnusMenu.this.frameCount = 0;
                    CygnusMenu.this.fpsWindowStart = jCurrentTimeMillis;
                }
                Choreographer.getInstance().postFrameCallback(this);
            }
        };
        Choreographer.getInstance().postFrameCallback(this.fpsCallback);
    }

    private void startStarAnim() {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.starAnim = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(1100L);
        this.starAnim.setRepeatMode(2);
        this.starAnim.setRepeatCount(-1);
        this.starAnim.setInterpolator(new LinearInterpolator());
        this.starAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.support.ThemesData.CYGNUS.d
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.a.lambda$startStarAnim$3(valueAnimator);
            }
        });
        this.starAnim.start();
    }

    private void startStatusTicker() {
        Runnable runnable = new Runnable() { // from class: com.android.support.ThemesData.CYGNUS.CygnusMenu.4
            final SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

            @Override // java.lang.Runnable
            public void run() {
                if (CygnusMenu.this.destroyed) {
                    return;
                }
                if (CygnusMenu.this.sharedData.mExpanded == null || CygnusMenu.this.sharedData.mExpanded.getVisibility() != 0 || CygnusMenu.this.sharedData.rootFrame.getVisibility() != 0) {
                    CygnusMenu.this.ticker.postDelayed(this, 2000L);
                    return;
                }
                if (CygnusMenu.this.statFps != null) {
                    CygnusMenu.this.statFps.setText(String.valueOf(CygnusMenu.this.currentFps));
                }
                if (CygnusMenu.this.statTime != null) {
                    CygnusMenu.this.statTime.setText(this.fmt.format(new Date()));
                }
                if (CygnusMenu.this.statKey != null) {
                    CygnusMenu.this.statKey.setText(CygnusMenu.this.keyStatusText());
                }
                CygnusMenu.this.ticker.postDelayed(this, 1000L);
            }
        };
        this.tickTask = runnable;
        this.ticker.postDelayed(runnable, 500L);
    }

    private TextView statChip(String str, int i) {
        TextView textView = new TextView(this.sharedData.getContext);
        textView.setText(str);
        textView.setTextColor(i);
        textView.setTypeface(CygnusFonts.DIGIT, 0);
        textView.setTextSize(2, 10.0f);
        textView.setLetterSpacing(0.06f);
        return textView;
    }

    private LinearLayout statPair(String str, TextView textView) {
        LinearLayout linearLayout = new LinearLayout(this.sharedData.getContext);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        TextView textView2 = new TextView(this.sharedData.getContext);
        textView2.setText(str);
        textView2.setTextColor(this.sharedData.MenuStyle.getDimTextColor());
        textView2.setTextSize(2, 9.0f);
        textView2.setLetterSpacing(0.14f);
        textView2.setTypeface(CygnusFonts.DIGIT, 0);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(0, 0, dp(5), 0);
        textView2.setLayoutParams(layoutParams);
        linearLayout.addView(textView2);
        linearLayout.addView(textView);
        return linearLayout;
    }

    private GradientDrawable strokedDrawable(int i, int i2, float f) {
        GradientDrawable gradientDrawableRoundedDrawable = roundedDrawable(i, f);
        gradientDrawableRoundedDrawable.setStroke(dp(1), i2);
        return gradientDrawableRoundedDrawable;
    }

    private String versionText() {
        return "v4.0";
    }

    @Override // com.android.support.interfaces.IMenuBuilder
    public void build() {
        this.sharedData.rootFrame = new FrameLayout(this.sharedData.getContext);
        this.sharedData.rootFrame.setClipChildren(false);
        this.sharedData.rootFrame.setClipToPadding(false);
        this.sharedData.mRootContainer = new RelativeLayout(this.sharedData.getContext);
        this.sharedData.mRootContainer.setClipChildren(false);
        this.sharedData.mRootContainer.setClipToPadding(false);
        this.sharedData.mCollapsed = new RelativeLayout(this.sharedData.getContext);
        this.sharedData.mCollapsed.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        this.sharedData.mCollapsed.setGravity(17);
        this.sharedData.mCollapsed.setPadding(dp(2), dp(2), dp(2), dp(2));
        this.sharedData.mCollapsed.setVisibility(0);
        CygnusShared cygnusShared = this.sharedData;
        cygnusShared.mCollapsed.setAlpha(cygnusShared.MenuStyle.getIconAlpha());
        buildMenuCard();
        buildFloatingIcon();
        for (TabDef tabDef : TABS) {
            LinearLayout linearLayout = new LinearLayout(this.sharedData.getContext);
            linearLayout.setOrientation(1);
            linearLayout.setPadding(dp(2), dp(4), dp(2), dp(8));
            this.tabContainers.put(tabDef.id, linearLayout);
        }
        CygnusMusicPanel cygnusMusicPanel = new CygnusMusicPanel(this.sharedData);
        this.musicPanel = cygnusMusicPanel;
        cygnusMusicPanel.build(this.tabContainers.get("music"));
        LinearLayout linearLayout2 = this.tabContainers.get("settings");
        this.sharedData.mSettings = linearLayout2;
        buildCygnusSettings(linearLayout2);
        String[] strArrSettingsList = this.sharedData.MenuData.SettingsList();
        if (strArrSettingsList != null) {
            for (String str : strArrSettingsList) {
                dispatchDescriptor(str, linearLayout2);
            }
        }
        LinearLayout linearLayout3 = new LinearLayout(this.sharedData.getContext);
        linearLayout3.setOrientation(1);
        linearLayout3.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        LinearLayout linearLayoutBuildHeader = buildHeader();
        LinearLayout linearLayoutBuildWorkspace = buildWorkspace();
        LinearLayout linearLayoutBuildStatusBar = buildStatusBar();
        linearLayout3.addView(linearLayoutBuildHeader);
        linearLayout3.addView(linearLayoutBuildWorkspace, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        linearLayout3.addView(linearLayoutBuildStatusBar);
        this.sharedData.mExpanded.addView(linearLayout3);
        CygnusShared cygnusShared2 = this.sharedData;
        cygnusShared2.mRootContainer.addView(cygnusShared2.mCollapsed);
        CygnusShared cygnusShared3 = this.sharedData;
        cygnusShared3.mRootContainer.addView(cygnusShared3.mExpanded);
        CygnusShared cygnusShared4 = this.sharedData;
        cygnusShared4.mCollapsed.addView(cygnusShared4.startimage);
        View.OnTouchListener onTouchListener = this.sharedData.MenuData.onTouchListener();
        linearLayoutBuildHeader.setOnTouchListener(onTouchListener);
        this.sharedData.rootFrame.setOnTouchListener(onTouchListener);
        CygnusShared cygnusShared5 = this.sharedData;
        cygnusShared5.MenuData.Init(cygnusShared5.getContext, this.titleView, this.statKey);
        addBanLogoutRow(this.tabContainers.get("main"));
        startStarAnim();
        setMenuTelemetryActive(false);
        startStatusTicker();
    }

    @Override // com.android.support.interfaces.IMenuBuilder
    public void buildFeature(String[] strArr, LinearLayout linearLayout) {
        if (strArr == null) {
            return;
        }
        for (String str : strArr) {
            dispatchDescriptor(str, linearLayout);
        }
        LinearLayout linearLayout2 = this.tabContainers.get("equipment");
        linearLayout2.removeAllViews();
        EquipmentPanel.build(linearLayout2, this.sharedData.MenuStyle);
    }

    @Override // com.android.support.interfaces.IMenuBuilder
    public void destroy() {
        if (this.destroyed) {
            return;
        }
        this.destroyed = true;
        CygnusMusicPanel cygnusMusicPanel = this.musicPanel;
        if (cygnusMusicPanel != null) {
            cygnusMusicPanel.destroy();
        }
        ValueAnimator valueAnimator = this.starAnim;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.starAnim = null;
        }
        if (this.fpsCallback != null) {
            Choreographer.getInstance().removeFrameCallback(this.fpsCallback);
            this.fpsCallback = null;
        }
        Runnable runnable = this.tickTask;
        if (runnable != null) {
            this.ticker.removeCallbacks(runnable);
        }
        Runnable runnable2 = this.libraryCheck;
        if (runnable2 != null) {
            this.libraryHandler.removeCallbacks(runnable2);
        }
        FrameLayout frameLayout = this.sharedData.rootFrame;
        if (frameLayout == null || frameLayout.getParent() == null) {
            return;
        }
        CygnusShared cygnusShared = this.sharedData;
        cygnusShared.mWindowManager.removeView(cygnusShared.rootFrame);
    }

    @Override // com.android.support.interfaces.IMenuBuilder
    public void setVisibility(int i) {
        LinearLayout linearLayout;
        setMenuTelemetryActive(i == 0 && (linearLayout = this.sharedData.mExpanded) != null && linearLayout.getVisibility() == 0);
        if (this.musicPanel != null) {
            if (i != 0) {
                setMusicFocus(false);
                this.musicPanel.hide();
            } else if ("music".equals(this.activeTab) && this.sharedData.mExpanded.getVisibility() == 0) {
                setMusicFocus(true);
                this.musicPanel.show();
            }
        }
        FrameLayout frameLayout = this.sharedData.rootFrame;
        if (frameLayout != null) {
            frameLayout.setVisibility(i);
        }
    }

    @Override // com.android.support.interfaces.IMenuBuilder
    public void show() {
        if (this.sharedData.rootFrame.getChildCount() == 0) {
            CygnusShared cygnusShared = this.sharedData;
            cygnusShared.rootFrame.addView(cygnusShared.mRootContainer);
        }
        Runnable runnable = new Runnable() { // from class: com.android.support.ThemesData.CYGNUS.CygnusMenu.2
            boolean placeholderShown;

            @Override // java.lang.Runnable
            public void run() {
                if (Preferences.loadPref && !CygnusMenu.this.sharedData.MenuData.IsGameLibLoaded() && !CygnusMenu.this.sharedData.stopChecking) {
                    if (!this.placeholderShown) {
                        LinearLayout linearLayout = (LinearLayout) CygnusMenu.this.tabContainers.get("main");
                        CygnusMenu.this.sharedData.components.Category(linearLayout, "ĐANG CHỜ THƯ VIỆN GAME");
                        CygnusMenu.this.sharedData.components.TextView(linearLayout, "Thiết lập đã lưu sẽ được nạp khi thư viện game sẵn sàng.");
                        CygnusMenu.this.sharedData.components.Button(linearLayout, -100, "Nạp chức năng ngay");
                        this.placeholderShown = true;
                    }
                    CygnusMenu.this.libraryHandler.postDelayed(this, 600L);
                    return;
                }
                if (CygnusMenu.this.featuresBuilt) {
                    return;
                }
                CygnusMenu.this.saveSettingsRow = null;
                CygnusMenu.this.aimRayRow = null;
                Iterator it = CygnusMenu.this.tabContainers.values().iterator();
                while (it.hasNext()) {
                    ((LinearLayout) it.next()).removeAllViews();
                }
                CygnusMenu.this.musicPanel.build((LinearLayout) CygnusMenu.this.tabContainers.get("music"));
                CygnusMenu cygnusMenu = CygnusMenu.this;
                cygnusMenu.buildCygnusSettings((LinearLayout) cygnusMenu.tabContainers.get("settings"));
                String[] strArrSettingsList = CygnusMenu.this.sharedData.MenuData.SettingsList();
                if (strArrSettingsList != null) {
                    for (String str : strArrSettingsList) {
                        CygnusMenu cygnusMenu2 = CygnusMenu.this;
                        cygnusMenu2.dispatchDescriptor(str, (LinearLayout) cygnusMenu2.tabContainers.get("settings"));
                    }
                }
                CygnusMenu cygnusMenu3 = CygnusMenu.this;
                cygnusMenu3.buildFeature(cygnusMenu3.sharedData.MenuData.GetFeatureList(), (LinearLayout) CygnusMenu.this.tabContainers.get("main"));
                CygnusMenu cygnusMenu4 = CygnusMenu.this;
                cygnusMenu4.addBanLogoutRow((LinearLayout) cygnusMenu4.tabContainers.get("main"));
                CygnusMenu.this.featuresBuilt = true;
                CygnusMenu cygnusMenu5 = CygnusMenu.this;
                cygnusMenu5.selectTab(cygnusMenu5.activeTab);
            }
        };
        this.libraryCheck = runnable;
        this.libraryHandler.postDelayed(runnable, 500L);
    }

    public void showFeatures() {
        selectTab("main");
    }

    private int dp(float f) {
        return Math.round(TypedValue.applyDimension(1, f, this.sharedData.getContext.getResources().getDisplayMetrics()));
    }
}
