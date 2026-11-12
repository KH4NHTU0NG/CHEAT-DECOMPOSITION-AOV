package com.android.support.ThemesData.DZ;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.android.support.GameAccountLogout;
import com.android.support.Preferences;
import com.android.support.ThemesData.CYGNUS.CygnusIconDrawable;
import com.android.support.ThemesData.DZ.DZMenu;
import com.android.support.auth.AuthNavigator;
import com.android.support.auth.AuthSession;
import com.android.support.auth.AuthSessionManager;
import com.android.support.interfaces.IMenuBuilder;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public class DZMenu implements IMenuBuilder {
    private static final String MENU_TITLE = "Map Sáng Android V4";
    private boolean destroyed;
    private int featuresScrollY;
    private TextView featuresTab;
    private Runnable libraryCheck;
    private final Handler libraryHandler = new Handler(Looper.getMainLooper());
    private TextView menuStatus;
    private boolean settingsOpen;
    private int settingsScrollY;
    private TextView settingsTab;
    private final DZShared sharedData;

    public DZMenu(DZShared dZShared) {
        this.sharedData = dZShared;
        Preferences.context = dZShared.getContext;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String accountStatusText(String str) {
        AuthSession session = AuthSessionManager.getSession();
        if (session == null) {
            return str;
        }
        return str + "  /  " + session.getDisplayName() + "  /  HẾT HẠN " + session.getExpiry();
    }

    private void addAccountDivider(LinearLayout linearLayout) {
        View view = new View(this.sharedData.getContext);
        view.setBackgroundColor(this.sharedData.MenuStyle.getOutlineColor());
        linearLayout.addView(view, new LinearLayout.LayoutParams(-1, dp(1)));
    }

    private void applyAccountHeader(TextView textView, TextView textView2) {
        textView.setText("Map Sáng Android V4");
        if (AuthSessionManager.getSession() == null) {
            return;
        }
        String strTrim = textView2.getText() == null ? "" : textView2.getText().toString().trim();
        if (TextUtils.isEmpty(strTrim)) {
            strTrim = "SẴN SÀNG";
        }
        textView2.setText(accountStatusText(strTrim));
    }

    private LinearLayout buildAccountDetailRow(String str, String str2) {
        LinearLayout linearLayout = new LinearLayout(this.sharedData.getContext);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        linearLayout.setMinimumHeight(dp(38));
        TextView textView = new TextView(this.sharedData.getContext);
        textView.setText(str);
        textView.setTextColor(this.sharedData.MenuStyle.getSecondaryTextColor());
        textView.setTextSize(2, 10.0f);
        textView.setTypeface(Typeface.DEFAULT, 1);
        textView.setSingleLine(true);
        textView.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        TextView textView2 = new TextView(this.sharedData.getContext);
        textView2.setText(str2);
        textView2.setTextColor(this.sharedData.MenuStyle.getTextColor2());
        textView2.setTextSize(2, 12.0f);
        textView2.setGravity(8388629);
        textView2.setSingleLine(true);
        textView2.setEllipsize(TextUtils.TruncateAt.END);
        textView2.setMaxWidth(dp(150));
        linearLayout.addView(textView);
        linearLayout.addView(textView2);
        return linearLayout;
    }

    private void buildAccountSection(LinearLayout linearLayout) {
        AuthSession session = AuthSessionManager.getSession();
        if (session == null) {
            return;
        }
        TextView textView = new TextView(this.sharedData.getContext);
        textView.setText("TÀI KHOẢN");
        textView.setTextColor(this.sharedData.MenuStyle.getSecondaryTextColor());
        textView.setTextSize(2, 10.0f);
        Typeface typeface = Typeface.DEFAULT;
        textView.setTypeface(typeface, 1);
        textView.setGravity(8388627);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, dp(30));
        layoutParams.setMargins(dp(4), 0, dp(4), dp(2));
        textView.setLayoutParams(layoutParams);
        linearLayout.addView(textView);
        LinearLayout linearLayout2 = new LinearLayout(this.sharedData.getContext);
        linearLayout2.setOrientation(1);
        linearLayout2.setPadding(dp(12), dp(10), dp(12), dp(8));
        GradientDrawable gradientDrawableRoundedDrawable = roundedDrawable(this.sharedData.MenuStyle.getSurfaceColor(), 8.0f);
        gradientDrawableRoundedDrawable.setStroke(dp(1), this.sharedData.MenuStyle.getOutlineColor());
        linearLayout2.setBackground(gradientDrawableRoundedDrawable);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.setMargins(dp(2), 0, dp(2), dp(10));
        linearLayout2.setLayoutParams(layoutParams2);
        LinearLayout linearLayout3 = new LinearLayout(this.sharedData.getContext);
        linearLayout3.setOrientation(0);
        linearLayout3.setGravity(16);
        linearLayout3.setPadding(0, 0, 0, dp(8));
        TextView textView2 = new TextView(this.sharedData.getContext);
        textView2.setText(session.getDisplayName());
        textView2.setTextColor(this.sharedData.MenuStyle.getTextColor2());
        textView2.setTextSize(2, 15.0f);
        textView2.setTypeface(typeface, 1);
        textView2.setSingleLine(true);
        textView2.setEllipsize(TextUtils.TruncateAt.END);
        textView2.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        TextView textView3 = new TextView(this.sharedData.getContext);
        textView3.setText("ĐANG HOẠT ĐỘNG");
        textView3.setTextColor(this.sharedData.MenuStyle.getTextColor());
        textView3.setTextSize(2, 10.0f);
        textView3.setTypeface(typeface, 1);
        textView3.setGravity(8388629);
        linearLayout3.addView(textView2);
        linearLayout3.addView(textView3);
        linearLayout2.addView(linearLayout3);
        addAccountDivider(linearLayout2);
        linearLayout2.addView(buildAccountDetailRow("HẾT HẠN", session.getExpiry()));
        addAccountDivider(linearLayout2);
        linearLayout2.addView(buildAccountDetailRow("KEY", session.getLicenseHint()));
        TextView textView4 = new TextView(this.sharedData.getContext);
        textView4.setText("ĐĂNG XUẤT");
        textView4.setTextColor(this.sharedData.MenuStyle.getDangerColor());
        textView4.setTextSize(2, 11.0f);
        textView4.setTypeface(typeface, 1);
        textView4.setGravity(17);
        textView4.setSingleLine(true);
        textView4.setClickable(true);
        textView4.setFocusable(true);
        textView4.setContentDescription("Đăng xuất tài khoản");
        textView4.setBackground(buttonBackground(this.sharedData.MenuStyle.getBtnColor(), 6.0f));
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, dp(44));
        layoutParams3.setMargins(0, dp(8), 0, 0);
        textView4.setLayoutParams(layoutParams3);
        textView4.setOnClickListener(new b(this, 5));
        linearLayout2.addView(textView4);
        TextView textView5 = new TextView(this.sharedData.getContext);
        textView5.setText("ĐĂNG XUẤT ACC BỊ BAN");
        textView5.setTextColor(this.sharedData.MenuStyle.getDangerColor());
        textView5.setTextSize(2, 11.0f);
        textView5.setTypeface(typeface, 1);
        textView5.setGravity(17);
        textView5.setSingleLine(true);
        textView5.setClickable(true);
        textView5.setFocusable(true);
        textView5.setContentDescription("Xoá token tài khoản game và khởi động lại");
        textView5.setBackground(buttonBackground(this.sharedData.MenuStyle.getBtnColor(), 6.0f));
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, dp(44));
        layoutParams4.setMargins(0, dp(6), 0, 0);
        textView5.setLayoutParams(layoutParams4);
        textView5.setOnClickListener(new b(this, 6));
        linearLayout2.addView(textView5);
        linearLayout.addView(linearLayout2);
    }

    private void buildBox() {
        this.sharedData.mExpanded = new LinearLayout(this.sharedData.getContext);
        this.sharedData.mExpanded.setVisibility(8);
        this.sharedData.mExpanded.setOrientation(1);
        this.sharedData.mExpanded.setPadding(0, 0, 0, 0);
        this.sharedData.mExpanded.setLayoutParams(new LinearLayout.LayoutParams(Math.min(dp(this.sharedData.MenuStyle.getMenuWidth()), this.sharedData.getContext.getResources().getDisplayMetrics().widthPixels - dp(24)), -2));
        GradientDrawable gradientDrawableRoundedDrawable = roundedDrawable(this.sharedData.MenuStyle.getMenuBgColor(), this.sharedData.MenuStyle.getMenuCorner());
        gradientDrawableRoundedDrawable.setStroke(dp(1), this.sharedData.MenuStyle.getOutlineColor());
        this.sharedData.mExpanded.setBackground(gradientDrawableRoundedDrawable);
        this.sharedData.mExpanded.setClipChildren(false);
        this.sharedData.mExpanded.setClipToPadding(false);
    }

    private LinearLayout buildHeader(TextView textView, TextView textView2) {
        LinearLayout linearLayout = new LinearLayout(this.sharedData.getContext);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        linearLayout.setPadding(dp(8), dp(5), dp(6), dp(4));
        linearLayout.setMinimumHeight(dp(62));
        linearLayout.setClipChildren(false);
        linearLayout.setClipToPadding(false);
        linearLayout.setBackgroundColor(this.sharedData.MenuStyle.getMenuBgColor());
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        ImageView imageView = new ImageView(this.sharedData.getContext);
        DZShared dZShared = this.sharedData;
        imageView.setImageDrawable(new MenuLogoDrawable(dZShared.getContext, dZShared.MenuStyle.getTextColor()));
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setPadding(dp(2), dp(2), dp(2), dp(2));
        imageView.setBackground(roundedDrawable(this.sharedData.MenuStyle.getAccentMutedColor(), 50.0f));
        imageView.setLayoutParams(new LinearLayout.LayoutParams(dp(30), dp(30)));
        LinearLayout linearLayout2 = new LinearLayout(this.sharedData.getContext);
        linearLayout2.setOrientation(1);
        linearLayout2.setGravity(16);
        linearLayout2.setPadding(dp(8), 0, dp(4), 0);
        linearLayout2.setClipChildren(false);
        linearLayout2.setClipToPadding(false);
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        linearLayout2.addView(textView);
        linearLayout2.addView(textView2);
        LinearLayout linearLayout3 = new LinearLayout(this.sharedData.getContext);
        linearLayout3.setOrientation(0);
        linearLayout3.setGravity(16);
        linearLayout3.setClipChildren(false);
        linearLayout3.setClipToPadding(false);
        linearLayout3.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        linearLayout3.addView(buildHeaderAction(1, this.sharedData.MenuStyle.getSecondaryTextColor(), "Thu nhỏ menu", new b(this, 2)));
        linearLayout3.addView(buildHeaderAction(2, this.sharedData.MenuStyle.getSecondaryTextColor(), "Ẩn menu", new b(this, 3)));
        linearLayout.addView(imageView);
        linearLayout.addView(linearLayout2);
        linearLayout.addView(linearLayout3);
        return linearLayout;
    }

    private ImageView buildHeaderAction(int i, int i2, String str, View.OnClickListener onClickListener) {
        ImageView imageView = new ImageView(this.sharedData.getContext);
        imageView.setImageDrawable(new MenuIconDrawable(i, i2));
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setPadding(dp(9), dp(11), dp(9), dp(11));
        imageView.setBackground(headerActionBackground());
        imageView.setContentDescription(str);
        imageView.setClickable(true);
        imageView.setFocusable(true);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(dp(40), dp(44)));
        imageView.setOnClickListener(onClickListener);
        if (Build.VERSION.SDK_INT >= 26) {
            imageView.setTooltipText(str);
        }
        return imageView;
    }

    private void buildIcon() {
        this.sharedData.startimage = new ImageView(this.sharedData.getContext);
        int iDp = dp(this.sharedData.MenuStyle.getIconSize());
        this.sharedData.startimage.setLayoutParams(new RelativeLayout.LayoutParams(iDp, iDp));
        this.sharedData.startimage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        ImageView imageView = this.sharedData.startimage;
        DZShared dZShared = this.sharedData;
        imageView.setImageDrawable(new MenuLogoDrawable(dZShared.getContext, dZShared.MenuStyle.getTextColor()));
        this.sharedData.startimage.setPadding(dp(2), dp(2), dp(2), dp(2));
        this.sharedData.startimage.setBackground(iconBackground());
        this.sharedData.startimage.setContentDescription("Mở menu");
        DZShared dZShared2 = this.sharedData;
        dZShared2.startimage.setOnTouchListener(dZShared2.MenuData.onTouchListener());
        this.sharedData.startimage.setOnClickListener(new b(this, 4));
    }

    private TextView buildSubtitle() {
        TextView textView = new TextView(this.sharedData.getContext);
        textView.setSingleLine(true);
        enableMarquee(textView);
        textView.setTextColor(this.sharedData.MenuStyle.getSecondaryTextColor());
        textView.setTextSize(2, 9.0f);
        textView.setGravity(8388627);
        textView.setIncludeFontPadding(false);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        return textView;
    }

    private TextView buildTab(String str, boolean z) {
        TextView textView = new TextView(this.sharedData.getContext);
        textView.setText(str);
        textView.setTextSize(2, 11.0f);
        textView.setTypeface(Typeface.DEFAULT, 1);
        textView.setGravity(17);
        textView.setSingleLine(true);
        textView.setSelected(z);
        textView.setTextColor(z ? this.sharedData.MenuStyle.getTextColor() : this.sharedData.MenuStyle.getSecondaryTextColor());
        textView.setBackground(tabBackground());
        textView.setLayoutParams(new LinearLayout.LayoutParams(0, -1, 1.0f));
        return textView;
    }

    private LinearLayout buildTabs() {
        LinearLayout linearLayout = new LinearLayout(this.sharedData.getContext);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        linearLayout.setPadding(dp(3), dp(3), dp(3), dp(3));
        linearLayout.setBackground(roundedDrawable(this.sharedData.MenuStyle.getSurfaceColor(), 8.0f));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, dp(40));
        layoutParams.setMargins(dp(10), dp(2), dp(10), dp(6));
        linearLayout.setLayoutParams(layoutParams);
        this.featuresTab = buildTab("CHỨC NĂNG", true);
        this.settingsTab = buildTab("CÀI ĐẶT", false);
        linearLayout.addView(this.featuresTab);
        linearLayout.addView(this.settingsTab);
        this.featuresTab.setOnClickListener(new b(this, 0));
        this.settingsTab.setOnClickListener(new b(this, 1));
        return linearLayout;
    }

    private TextView buildTitle() {
        TextView textView = new TextView(this.sharedData.getContext);
        textView.setTextColor(this.sharedData.MenuStyle.getTextColor2());
        textView.setTextSize(2, 14.0f);
        textView.setTypeface(Typeface.DEFAULT, 1);
        textView.setGravity(8388627);
        textView.setSingleLine(true);
        textView.setIncludeFontPadding(false);
        enableMarquee(textView);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        return textView;
    }

    private StateListDrawable buttonBackground(int i, float f) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_pressed}, roundedDrawable(pressedColor(i), f));
        stateListDrawable.addState(new int[]{R.attr.state_focused}, roundedDrawable(pressedColor(i), f));
        stateListDrawable.addState(new int[0], roundedDrawable(i, f));
        return stateListDrawable;
    }

    private void configureScrollView() {
        DisplayMetrics displayMetrics = this.sharedData.getContext.getResources().getDisplayMetrics();
        float f = this.sharedData.getContext.getResources().getConfiguration().fontScale;
        int iMax = Math.max(dp(160), displayMetrics.heightPixels - dp(72));
        int iDp = dp(this.sharedData.MenuStyle.getMenuHeight());
        if (f > 1.0f) {
            iDp = (int) (Math.min(1.4f, f) * iDp);
        }
        int iMin = Math.min(iDp, iMax);
        int iMin2 = Math.min(dp(112) + iMin, iMax);
        this.sharedData.scrollView = new ScrollView(this.sharedData.getContext);
        this.sharedData.scrlLL = new LinearLayout.LayoutParams(-1, iMin);
        this.sharedData.scrlLLExpanded = new LinearLayout.LayoutParams(-1, iMin2);
        DZShared dZShared = this.sharedData;
        dZShared.scrollView.setLayoutParams(Preferences.isExpanded ? dZShared.scrlLLExpanded : dZShared.scrlLL);
        DZShared dZShared2 = this.sharedData;
        dZShared2.scrollView.setBackgroundColor(dZShared2.MenuStyle.getMenuFeatureBgColor());
        this.sharedData.scrollView.setClipChildren(true);
        this.sharedData.scrollView.setClipToPadding(true);
        this.sharedData.scrollView.setVerticalScrollBarEnabled(true);
        this.sharedData.scrollView.setScrollbarFadingEnabled(true);
        this.sharedData.scrollView.setOverScrollMode(1);
        this.sharedData.mods = new LinearLayout(this.sharedData.getContext);
        this.sharedData.mods.setOrientation(1);
        this.sharedData.mods.setClipChildren(false);
        this.sharedData.mods.setClipToPadding(false);
        this.sharedData.mods.setPadding(dp(6), dp(4), dp(6), dp(8));
    }

    private void confirmBannedAccountLogout() {
        showConfirmationDialog(new AlertDialog.Builder(this.sharedData.getContext, R.style.Theme.Material.Dialog.Alert).setTitle("Đăng xuất Acc bị Ban?").setMessage("Token đăng nhập game sẽ bị xoá, sau đó game sẽ thoát và thử khởi động lại.").setPositiveButton("XOÁ TOKEN", new a(this, 1)).setNegativeButton("HUỶ", (DialogInterface.OnClickListener) null).create(), this.sharedData.MenuStyle.getDangerColor());
    }

    private void confirmLogout() {
        showConfirmationDialog(new AlertDialog.Builder(this.sharedData.getContext, R.style.Theme.Material.Dialog.Alert).setTitle("Đăng xuất tài khoản?").setMessage("Key đã lưu và phiên tài khoản hiện tại sẽ được xoá.").setPositiveButton("ĐĂNG XUẤT", new a(this, 0)).setNegativeButton("HUỶ", (DialogInterface.OnClickListener) null).create(), this.sharedData.MenuStyle.getDangerColor());
    }

    private int dp(int i) {
        return Math.round(TypedValue.applyDimension(1, i, this.sharedData.getContext.getResources().getDisplayMetrics()));
    }

    private void enableMarquee(TextView textView) {
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView.setMarqueeRepeatLimit(-1);
        textView.setHorizontallyScrolling(true);
        textView.setSelected(true);
    }

    private StateListDrawable headerActionBackground() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_pressed}, roundedDrawable(this.sharedData.MenuStyle.getSurfaceColor(), 6.0f));
        stateListDrawable.addState(new int[]{R.attr.state_focused}, roundedDrawable(this.sharedData.MenuStyle.getSurfaceColor(), 6.0f));
        stateListDrawable.addState(new int[0], roundedDrawable(0, 6.0f));
        return stateListDrawable;
    }

    private void hideMenu() {
        this.sharedData.mCollapsed.setVisibility(0);
        this.sharedData.mCollapsed.setAlpha(0.0f);
        this.sharedData.mExpanded.setVisibility(8);
        this.sharedData.startimage.setContentDescription("Hiện menu");
    }

    private GradientDrawable iconBackground() {
        GradientDrawable gradientDrawableRoundedDrawable = roundedDrawable(this.sharedData.MenuStyle.getSurfaceColor(), 50.0f);
        gradientDrawableRoundedDrawable.setStroke(dp(1), this.sharedData.MenuStyle.getOutlineColor());
        return gradientDrawableRoundedDrawable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$buildAccountSection$5(View view) {
        confirmLogout();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$buildAccountSection$6(View view) {
        confirmBannedAccountLogout();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$buildHeader$0(View view) {
        minimizeMenu();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$buildHeader$1(View view) {
        hideMenu();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$buildIcon$4(View view) {
        openMenu();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$buildTabs$2(View view) {
        showPanel(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$buildTabs$3(View view) {
        showPanel(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$confirmBannedAccountLogout$9(DialogInterface dialogInterface, int i) {
        GameAccountLogout.resetAndRestart(this.sharedData.getContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$confirmLogout$8(DialogInterface dialogInterface, int i) {
        logout();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showPanel$7(int i) {
        this.sharedData.scrollView.scrollTo(0, i);
    }

    private void logout() {
        destroy();
        AuthNavigator.logout(this.sharedData.getContext);
    }

    private void minimizeMenu() {
        this.sharedData.mCollapsed.setVisibility(0);
        DZShared dZShared = this.sharedData;
        dZShared.mCollapsed.setAlpha(dZShared.MenuStyle.getIconAlpha());
        this.sharedData.mExpanded.setVisibility(8);
        this.sharedData.startimage.setContentDescription("Mở menu");
    }

    private void openMenu() {
        this.sharedData.mCollapsed.setVisibility(8);
        DZShared dZShared = this.sharedData;
        dZShared.mCollapsed.setAlpha(dZShared.MenuStyle.getIconAlpha());
        this.sharedData.mExpanded.setVisibility(0);
        this.sharedData.startimage.setContentDescription("Mở menu");
    }

    private int pressedColor(int i) {
        return Color.argb(Color.alpha(i), Math.max(0, Color.red(i) - 18), Math.max(0, Color.green(i) - 18), Math.max(0, Color.blue(i) - 18));
    }

    private GradientDrawable roundedDrawable(int i, float f) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(i);
        gradientDrawable.setCornerRadius(dp(f));
        return gradientDrawable;
    }

    private void showConfirmationDialog(AlertDialog alertDialog, int i) {
        Window window = alertDialog.getWindow();
        if (this.sharedData.overlayRequired && window != null) {
            window.setType(Build.VERSION.SDK_INT >= 26 ? 2038 : 2002);
        }
        alertDialog.show();
        Window window2 = alertDialog.getWindow();
        if (window2 != null) {
            window2.setBackgroundDrawable(roundedDrawable(this.sharedData.MenuStyle.getSurfaceColor(), 8.0f));
            window2.addFlags(2);
            WindowManager.LayoutParams attributes = window2.getAttributes();
            attributes.dimAmount = 0.58f;
            window2.setAttributes(attributes);
        }
        alertDialog.getButton(-1).setTextColor(i);
        alertDialog.getButton(-2).setTextColor(this.sharedData.MenuStyle.getSecondaryTextColor());
    }

    private void showPanel(boolean z) {
        ScrollView scrollView = this.sharedData.scrollView;
        if (scrollView == null || this.featuresTab == null || this.settingsTab == null) {
            return;
        }
        if (this.settingsOpen) {
            this.settingsScrollY = scrollView.getScrollY();
        } else {
            this.featuresScrollY = scrollView.getScrollY();
        }
        this.settingsOpen = z;
        this.sharedData.scrollView.removeAllViews();
        DZShared dZShared = this.sharedData;
        dZShared.scrollView.addView(this.settingsOpen ? dZShared.mSettings : dZShared.mods);
        final int i = this.settingsOpen ? this.settingsScrollY : this.featuresScrollY;
        final int i2 = 1;
        this.sharedData.scrollView.post(new Runnable() { // from class: com.android.support.o
            @Override // java.lang.Runnable
            public final void run() {
                switch (i2) {
                    case CygnusIconDrawable.MAP /* 0 */:
                        Main.a((Activity) this, i);
                        break;
                    default:
                        ((DZMenu) this).lambda$showPanel$7(i);
                        break;
                }
            }
        });
        this.featuresTab.setSelected(!this.settingsOpen);
        this.settingsTab.setSelected(this.settingsOpen);
        this.featuresTab.setTextColor(!this.settingsOpen ? this.sharedData.MenuStyle.getTextColor() : this.sharedData.MenuStyle.getSecondaryTextColor());
        this.settingsTab.setTextColor(this.settingsOpen ? this.sharedData.MenuStyle.getTextColor() : this.sharedData.MenuStyle.getSecondaryTextColor());
    }

    private StateListDrawable tabBackground() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_selected}, roundedDrawable(this.sharedData.MenuStyle.getAccentMutedColor(), 6.0f));
        stateListDrawable.addState(new int[]{R.attr.state_pressed}, roundedDrawable(this.sharedData.MenuStyle.getBtnColor(), 6.0f));
        stateListDrawable.addState(new int[0], roundedDrawable(0, 6.0f));
        return stateListDrawable;
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
        DZShared dZShared = this.sharedData;
        dZShared.mCollapsed.setAlpha(dZShared.MenuStyle.getIconAlpha());
        buildBox();
        buildIcon();
        this.sharedData.mSettings = new LinearLayout(this.sharedData.getContext);
        this.sharedData.mSettings.setOrientation(1);
        this.sharedData.mSettings.setPadding(dp(8), dp(6), dp(8), dp(10));
        buildAccountSection(this.sharedData.mSettings);
        String[] strArrSettingsList = this.sharedData.MenuData.SettingsList();
        if (strArrSettingsList != null) {
            buildFeature(strArrSettingsList, this.sharedData.mSettings);
        }
        TextView textViewBuildTitle = buildTitle();
        TextView textViewBuildSubtitle = buildSubtitle();
        this.menuStatus = textViewBuildSubtitle;
        LinearLayout linearLayoutBuildHeader = buildHeader(textViewBuildTitle, textViewBuildSubtitle);
        LinearLayout linearLayoutBuildTabs = buildTabs();
        configureScrollView();
        DZShared dZShared2 = this.sharedData;
        dZShared2.mRootContainer.addView(dZShared2.mCollapsed);
        DZShared dZShared3 = this.sharedData;
        dZShared3.mRootContainer.addView(dZShared3.mExpanded);
        DZShared dZShared4 = this.sharedData;
        dZShared4.mCollapsed.addView(dZShared4.startimage);
        this.sharedData.mExpanded.addView(linearLayoutBuildHeader);
        this.sharedData.mExpanded.addView(linearLayoutBuildTabs);
        DZShared dZShared5 = this.sharedData;
        dZShared5.scrollView.addView(dZShared5.mods);
        DZShared dZShared6 = this.sharedData;
        dZShared6.mExpanded.addView(dZShared6.scrollView);
        DZShared dZShared7 = this.sharedData;
        dZShared7.MenuData.Init(dZShared7.getContext, textViewBuildTitle, textViewBuildSubtitle);
        applyAccountHeader(textViewBuildTitle, textViewBuildSubtitle);
        linearLayoutBuildHeader.setOnTouchListener(this.sharedData.MenuData.onTouchListener());
        DZShared dZShared8 = this.sharedData;
        dZShared8.rootFrame.setOnTouchListener(dZShared8.MenuData.onTouchListener());
    }

    /* JADX WARN: Code duplicated, block: B:82:0x0122  */
    @Override // com.android.support.interfaces.IMenuBuilder
    public void buildFeature(String[] strArr, LinearLayout linearLayout) {
        boolean z;
        LinearLayout linearLayout2;
        int i;
        if (strArr == null) {
            return;
        }
        LinearLayout linearLayout3 = null;
        int i2 = 0;
        for (int i3 = 0; i3 < strArr.length; i3++) {
            String strSubstring = strArr[i3];
            if (!TextUtils.isEmpty(strSubstring)) {
                if (strSubstring.contains("_True")) {
                    strSubstring = strSubstring.replaceFirst("_True", "");
                    z = true;
                } else {
                    z = false;
                }
                if (strSubstring.contains("CollapseAdd_")) {
                    LinearLayout linearLayout4 = linearLayout3 == null ? linearLayout : linearLayout3;
                    strSubstring = strSubstring.replaceFirst("CollapseAdd_", "");
                    linearLayout2 = linearLayout4;
                } else {
                    linearLayout2 = linearLayout;
                }
                int iIndexOf = strSubstring.indexOf(95);
                if (iIndexOf >= 1) {
                    String strSubstring2 = strSubstring.substring(0, iIndexOf);
                    try {
                        if (strSubstring2.matches("-?[0-9]+")) {
                            int i4 = Integer.parseInt(strSubstring2);
                            strSubstring = strSubstring.substring(iIndexOf + 1);
                            i2++;
                            i = i4;
                        } else {
                            i = i3 - i2;
                        }
                        String[] strArrSplit = strSubstring.split("_");
                        if (strArrSplit.length >= 2) {
                            switch (strArrSplit[0]) {
                                case "Toggle":
                                    this.sharedData.components.Switch(linearLayout2, i, strArrSplit[1], z);
                                    break;
                                case "SeekBar":
                                    if (strArrSplit.length < 4) {
                                        break;
                                    } else {
                                        int i5 = Integer.parseInt(strArrSplit[2]);
                                        int i6 = Integer.parseInt(strArrSplit[3]);
                                        if (strArrSplit.length >= 5) {
                                            this.sharedData.components.SeekBar(linearLayout2, i, strArrSplit[1], i5, i6, Integer.parseInt(strArrSplit[4]));
                                        } else {
                                            this.sharedData.components.SeekBar(linearLayout2, i, strArrSplit[1], i5, i6);
                                        }
                                        break;
                                    }
                                    break;
                                case "Button":
                                    this.sharedData.components.Button(linearLayout2, i, strArrSplit[1]);
                                    break;
                                case "ButtonOnOff":
                                    this.sharedData.components.ButtonOnOff(linearLayout2, i, strArrSplit[1], z);
                                    break;
                                case "Spinner":
                                    if (strArrSplit.length < 3) {
                                        break;
                                    } else {
                                        this.sharedData.components.Spinner(linearLayout2, i, strArrSplit[1], strArrSplit[2]);
                                        break;
                                    }
                                    break;
                                case "InputText":
                                    this.sharedData.components.InputText(linearLayout2, i, strArrSplit[1]);
                                    break;
                                case "InputValue":
                                    if (strArrSplit.length != 3) {
                                        this.sharedData.components.InputNum(linearLayout2, i, strArrSplit[1], 0);
                                        break;
                                    } else {
                                        this.sharedData.components.InputNum(linearLayout2, i, strArrSplit[2], Integer.parseInt(strArrSplit[1]));
                                        break;
                                    }
                                    break;
                                case "InputLValue":
                                    if (strArrSplit.length != 3) {
                                        this.sharedData.components.InputLNum(linearLayout2, i, strArrSplit[1], 0L);
                                        break;
                                    } else {
                                        this.sharedData.components.InputLNum(linearLayout2, i, strArrSplit[2], Long.parseLong(strArrSplit[1]));
                                        break;
                                    }
                                    break;
                                case "CheckBox":
                                    this.sharedData.components.CheckBox(linearLayout2, i, strArrSplit[1], z);
                                    break;
                                case "RadioButton":
                                    if (strArrSplit.length < 3) {
                                        break;
                                    } else {
                                        this.sharedData.components.RadioButton(linearLayout2, i, strArrSplit[1], strArrSplit[2]);
                                        break;
                                    }
                                    break;
                                case "Collapse":
                                    this.sharedData.components.Collapse(linearLayout2, strArrSplit[1], z);
                                    linearLayout3 = this.sharedData.mCollapse;
                                    i2++;
                                    break;
                                case "ButtonLink":
                                    if (strArrSplit.length < 3) {
                                        break;
                                    } else {
                                        i2++;
                                        this.sharedData.components.ButtonLink(linearLayout2, strArrSplit[1], strArrSplit[2]);
                                        break;
                                    }
                                    break;
                                case "Category":
                                    i2++;
                                    this.sharedData.components.Category(linearLayout2, strArrSplit[1]);
                                    break;
                                case "RichTextView":
                                    i2++;
                                    this.sharedData.components.TextView(linearLayout2, strArrSplit[1]);
                                    break;
                                case "RichWebView":
                                    i2++;
                                    this.sharedData.components.WebTextView(linearLayout2, strArrSplit[1]);
                                    break;
                            }
                        }
                    } catch (NumberFormatException unused) {
                    }
                }
            }
        }
    }

    @Override // com.android.support.interfaces.IMenuBuilder
    public void destroy() {
        if (this.destroyed) {
            return;
        }
        this.destroyed = true;
        Runnable runnable = this.libraryCheck;
        if (runnable != null) {
            this.libraryHandler.removeCallbacks(runnable);
        }
        FrameLayout frameLayout = this.sharedData.rootFrame;
        if (frameLayout == null || frameLayout.getParent() == null) {
            return;
        }
        DZShared dZShared = this.sharedData;
        dZShared.mWindowManager.removeView(dZShared.rootFrame);
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
        DZShared dZShared = this.sharedData;
        dZShared.rootFrame.addView(dZShared.mRootContainer);
        Runnable runnable = new Runnable() { // from class: com.android.support.ThemesData.DZ.DZMenu.1
            boolean viewLoaded;

            @Override // java.lang.Runnable
            public void run() {
                if (!Preferences.loadPref || DZMenu.this.sharedData.MenuData.IsGameLibLoaded() || DZMenu.this.sharedData.stopChecking) {
                    DZMenu.this.menuStatus.setText(DZMenu.this.accountStatusText(""));
                    DZMenu.this.sharedData.mods.removeAllViews();
                    DZMenu dZMenu = DZMenu.this;
                    dZMenu.buildFeature(dZMenu.sharedData.MenuData.GetFeatureList(), DZMenu.this.sharedData.mods);
                    return;
                }
                DZMenu.this.menuStatus.setText(DZMenu.this.accountStatusText("ĐANG CHỜ THƯ VIỆN GAME"));
                if (!this.viewLoaded) {
                    DZMenu.this.sharedData.components.Category(DZMenu.this.sharedData.mods, "ĐANG CHỜ THƯ VIỆN GAME");
                    DZMenu.this.sharedData.components.TextView(DZMenu.this.sharedData.mods, "Thiết lập đã lưu sẽ được nạp khi thư viện game sẵn sàng.");
                    DZMenu.this.sharedData.components.Button(DZMenu.this.sharedData.mods, -100, "Nạp chức năng ngay");
                    this.viewLoaded = true;
                }
                DZMenu.this.libraryHandler.postDelayed(this, 600L);
            }
        };
        this.libraryCheck = runnable;
        this.libraryHandler.postDelayed(runnable, 500L);
    }

    public void showFeatures() {
        showPanel(false);
    }

    private int dp(float f) {
        return Math.round(TypedValue.applyDimension(1, f, this.sharedData.getContext.getResources().getDisplayMetrics()));
    }
}
