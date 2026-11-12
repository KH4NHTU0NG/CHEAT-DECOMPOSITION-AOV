package com.android.support.ThemesData.CYGNUS;

import android.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.os.Build;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.android.support.Preferences;
import com.android.support.interfaces.IMenuComponentFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public class CygnusMenuComponents implements IMenuComponentFactory {
    private final CygnusShared sharedData;

    /* JADX INFO: renamed from: com.android.support.ThemesData.CYGNUS.CygnusMenuComponents$3, reason: invalid class name */
    public class AnonymousClass3 implements Runnable {
        final /* synthetic */ int[] val$currentSel;
        final /* synthetic */ Dialog val$dialog;
        final /* synthetic */ LinearLayout val$list;
        final /* synthetic */ OptionPick val$onPick;
        final /* synthetic */ List val$options;
        final /* synthetic */ EditText val$search;

        public AnonymousClass3(LinearLayout linearLayout, EditText editText, List list, int[] iArr, OptionPick optionPick, Dialog dialog) {
            this.val$list = linearLayout;
            this.val$search = editText;
            this.val$options = list;
            this.val$currentSel = iArr;
            this.val$onPick = optionPick;
            this.val$dialog = dialog;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$run$0(int[] iArr, int i, OptionPick optionPick, Dialog dialog, View view) {
            iArr[0] = i;
            optionPick.onPick(i);
            dialog.dismiss();
        }

        @Override // java.lang.Runnable
        public void run() {
            Drawable drawableRowBackground;
            this.val$list.removeAllViews();
            String lowerCase = this.val$search.getText().toString().trim().toLowerCase();
            final int i = 0;
            while (i < this.val$options.size()) {
                String str = (String) this.val$options.get(i);
                if (lowerCase.isEmpty() || str.toLowerCase().contains(lowerCase)) {
                    LinearLayout linearLayout = new LinearLayout(CygnusMenuComponents.this.sharedData.getContext);
                    linearLayout.setOrientation(0);
                    linearLayout.setGravity(16);
                    linearLayout.setPadding(CygnusMenuComponents.this.dp(12), CygnusMenuComponents.this.dp(10), CygnusMenuComponents.this.dp(12), CygnusMenuComponents.this.dp(10));
                    boolean z = i == this.val$currentSel[0];
                    if (z) {
                        CygnusMenuComponents cygnusMenuComponents = CygnusMenuComponents.this;
                        drawableRowBackground = cygnusMenuComponents.roundedDrawable(cygnusMenuComponents.sharedData.MenuStyle.getAccentSoftColor(), 6.0f);
                    } else {
                        drawableRowBackground = CygnusMenuComponents.this.rowBackground();
                    }
                    linearLayout.setBackground(drawableRowBackground);
                    linearLayout.setClickable(true);
                    TextView textView = new TextView(CygnusMenuComponents.this.sharedData.getContext);
                    textView.setText(str);
                    textView.setTextColor(z ? CygnusMenuComponents.this.sharedData.MenuStyle.getAccentColor() : CygnusMenuComponents.this.sharedData.MenuStyle.getTextColor2());
                    textView.setTypeface(z ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
                    textView.setTextSize(2, 12.0f);
                    textView.setSingleLine(true);
                    textView.setEllipsize(TextUtils.TruncateAt.END);
                    textView.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                    linearLayout.addView(textView);
                    if (z) {
                        TextView textView2 = new TextView(CygnusMenuComponents.this.sharedData.getContext);
                        textView2.setText("✓");
                        textView2.setTextColor(CygnusMenuComponents.this.sharedData.MenuStyle.getAccentColor());
                        textView2.setTextSize(2, 14.0f);
                        textView2.setTypeface(Typeface.DEFAULT, 1);
                        linearLayout.addView(textView2);
                    }
                    final int[] iArr = this.val$currentSel;
                    final OptionPick optionPick = this.val$onPick;
                    final Dialog dialog = this.val$dialog;
                    linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.android.support.ThemesData.CYGNUS.q
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            CygnusMenuComponents.AnonymousClass3.lambda$run$0(iArr, i, optionPick, dialog, view);
                        }
                    });
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                    layoutParams.setMargins(0, CygnusMenuComponents.this.dp(1), 0, CygnusMenuComponents.this.dp(1));
                    linearLayout.setLayoutParams(layoutParams);
                    this.val$list.addView(linearLayout);
                }
                i++;
            }
            if (this.val$list.getChildCount() == 0) {
                TextView textView3 = new TextView(CygnusMenuComponents.this.sharedData.getContext);
                textView3.setText("— Không tìm thấy —");
                textView3.setTextColor(CygnusMenuComponents.this.sharedData.MenuStyle.getDimTextColor());
                textView3.setTextSize(2, 12.0f);
                textView3.setGravity(17);
                textView3.setPadding(CygnusMenuComponents.this.dp(20), CygnusMenuComponents.this.dp(30), CygnusMenuComponents.this.dp(20), CygnusMenuComponents.this.dp(30));
                this.val$list.addView(textView3);
            }
        }
    }

    public interface InputCommit {
        void apply(String str);
    }

    public interface OptionPick {
        void onPick(int i);
    }

    public static class ToggleRow {
        final LinearLayout container;
        final ImageView icon;
        final TextView label;
        final View thumb;
        final FrameLayout track;

        public ToggleRow(LinearLayout linearLayout, TextView textView, FrameLayout frameLayout, View view, ImageView imageView) {
            this.container = linearLayout;
            this.label = textView;
            this.track = frameLayout;
            this.thumb = view;
            this.icon = imageView;
        }
    }

    public static class ValueRow {
        final LinearLayout container;
        final ImageView icon;
        final TextView label;
        final TextView value;

        public ValueRow(LinearLayout linearLayout, TextView textView, TextView textView2, ImageView imageView) {
            this.container = linearLayout;
            this.label = textView;
            this.value = textView2;
            this.icon = imageView;
        }
    }

    public CygnusMenuComponents(CygnusShared cygnusShared) {
        this.sharedData = cygnusShared;
    }

    private StateListDrawable actionButtonBackground() {
        int accentColor = this.sharedData.MenuStyle.getAccentColor();
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_pressed}, roundedDrawable(pressed(accentColor), 8.0f));
        stateListDrawable.addState(new int[]{R.attr.state_focused}, roundedDrawable(pressed(accentColor), 8.0f));
        stateListDrawable.addState(new int[0], roundedDrawable(accentColor, 8.0f));
        return stateListDrawable;
    }

    private void addDisabledValueRow(LinearLayout linearLayout, String str) {
        ValueRow valueRowCreateValueRow = createValueRow(str, "CHƯA CÀI", 11);
        valueRowCreateValueRow.container.setEnabled(false);
        valueRowCreateValueRow.value.setTextColor(this.sharedData.MenuStyle.getDimTextColor());
        addRow(linearLayout, valueRowCreateValueRow.container);
    }

    private void addRow(LinearLayout linearLayout, View view) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(0, 0, 0, dp(4));
        if (view.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) view.getLayoutParams();
            layoutParams.width = layoutParams2.width;
            layoutParams.height = layoutParams2.height;
        }
        int childCount = linearLayout.getChildCount();
        view.setAlpha(0.0f);
        view.setTranslationY(dp(8));
        linearLayout.addView(view, layoutParams);
        view.animate().alpha(1.0f).translationY(0.0f).setStartDelay(Math.min(300L, ((long) childCount) * 45)).setDuration(320L).start();
    }

    private String colorHex(int i) {
        return String.format("#%06X", Integer.valueOf(i & 16777215));
    }

    private ToggleRow createToggleRow(String str, boolean z, int i) {
        LinearLayout linearLayout = new LinearLayout(this.sharedData.getContext);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        linearLayout.setPadding(dp(10), dp(9), dp(10), dp(9));
        linearLayout.setBackground(rowBackground());
        linearLayout.setClickable(true);
        linearLayout.setFocusable(true);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        linearLayout.setMinimumHeight(dp(50));
        ImageView imageViewFeatureIcon = featureIcon(i, this.sharedData.MenuStyle.getSecondaryTextColor(), false);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dp(28), dp(28));
        layoutParams.setMargins(0, 0, dp(8), 0);
        imageViewFeatureIcon.setLayoutParams(layoutParams);
        TextView textViewLabel = label(str, 13, this.sharedData.MenuStyle.getTextColor2());
        textViewLabel.setSingleLine(true);
        textViewLabel.setEllipsize(TextUtils.TruncateAt.END);
        textViewLabel.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        FrameLayout frameLayout = new FrameLayout(this.sharedData.getContext);
        frameLayout.setPadding(dp(2), dp(2), dp(2), dp(2));
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(dp(40), dp(22));
        layoutParams2.leftMargin = dp(6);
        frameLayout.setLayoutParams(layoutParams2);
        View view = new View(this.sharedData.getContext);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(dp(18), dp(18));
        layoutParams3.gravity = 8388627;
        view.setLayoutParams(layoutParams3);
        view.setElevation(dp(2));
        frameLayout.addView(view);
        linearLayout.addView(imageViewFeatureIcon);
        linearLayout.addView(textViewLabel);
        linearLayout.addView(frameLayout);
        ToggleRow toggleRow = new ToggleRow(linearLayout, textViewLabel, frameLayout, view, imageViewFeatureIcon);
        updateToggleRow(toggleRow, z, false);
        return toggleRow;
    }

    private ValueRow createValueRow(String str, String str2, int i) {
        LinearLayout linearLayout = new LinearLayout(this.sharedData.getContext);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        linearLayout.setPadding(dp(10), dp(8), dp(10), dp(8));
        linearLayout.setBackground(rowBackground());
        linearLayout.setClickable(true);
        linearLayout.setFocusable(true);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        linearLayout.setMinimumHeight(dp(50));
        linearLayout.setContentDescription(str + ", " + str2);
        ImageView imageViewFeatureIcon = featureIcon(i, this.sharedData.MenuStyle.getAccentColor(), false);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dp(28), dp(28));
        layoutParams.setMargins(0, 0, dp(8), 0);
        imageViewFeatureIcon.setLayoutParams(layoutParams);
        TextView textViewLabel = label(str, 13, this.sharedData.MenuStyle.getTextColor2());
        textViewLabel.setSingleLine(true);
        TextUtils.TruncateAt truncateAt = TextUtils.TruncateAt.END;
        textViewLabel.setEllipsize(truncateAt);
        textViewLabel.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        TextView textViewValueText = valueText(str2);
        textViewValueText.setMaxWidth(dp(140));
        textViewValueText.setGravity(8388629);
        textViewValueText.setPadding(dp(8), 0, 0, 0);
        textViewValueText.setSingleLine(true);
        textViewValueText.setEllipsize(truncateAt);
        textViewValueText.setTypeface(CygnusFonts.DIGIT, 0);
        linearLayout.addView(imageViewFeatureIcon);
        linearLayout.addView(textViewLabel);
        linearLayout.addView(textViewValueText);
        return new ValueRow(linearLayout, textViewLabel, textViewValueText, imageViewFeatureIcon);
    }

    private String displayTextValue(String str) {
        return (str == null || str.isEmpty()) ? "TRỐNG" : str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int dp(int i) {
        return Math.round(TypedValue.applyDimension(1, i, this.sharedData.getContext.getResources().getDisplayMetrics()));
    }

    private ImageView featureIcon(int i, int i2, boolean z) {
        ImageView imageView = new ImageView(this.sharedData.getContext);
        String strUrlForIconKind = CygnusIconDrawable.urlForIconKind(i);
        CygnusIconDrawable cygnusIconDrawable = new CygnusIconDrawable(i, i2);
        cygnusIconDrawable.setStrokeWidth(1.9f);
        if (strUrlForIconKind != null) {
            imageView.setImageDrawable(new CygnusRemoteIcon(this.sharedData.getContext, strUrlForIconKind, cygnusIconDrawable));
            imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView.setImageDrawable(cygnusIconDrawable);
            imageView.setPadding(dp(6), dp(6), dp(6), dp(6));
        }
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setBackground(strokedDrawable(z ? this.sharedData.MenuStyle.getAccentSoftColor() : this.sharedData.MenuStyle.getSurfaceStrongColor(), z ? this.sharedData.MenuStyle.getAccentBorderColor() : this.sharedData.MenuStyle.getOutlineSoftColor(), 8.0f));
        return imageView;
    }

    private TextView label(String str, int i, int i2) {
        TextView textView = new TextView(this.sharedData.getContext);
        textView.setText(str);
        textView.setTextColor(i2);
        textView.setTextSize(2, i);
        textView.setSingleLine(true);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        return textView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$Button$2(int i, String str, View view) {
        if (i == -6) {
            this.sharedData.menu.showFeatures();
        } else if (i == -100) {
            this.sharedData.stopChecking = true;
        }
        Preferences.changeFeatureInt(str, i, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$ButtonLink$3(String str, View view) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.setFlags(268435456);
        this.sharedData.getContext.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$ButtonOnOff$4(boolean[] zArr, String str, int i, ToggleRow toggleRow, View view) {
        boolean z = !zArr[0];
        zArr[0] = z;
        Preferences.changeFeatureBool(str, i, z);
        updateToggleRow(toggleRow, zArr[0], true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$CheckBox$13(boolean[] zArr, ToggleRow toggleRow, String str, int i, View view) {
        boolean z = !zArr[0];
        zArr[0] = z;
        updateToggleRow(toggleRow, z, true);
        Preferences.changeFeatureBool(str, i, zArr[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$Collapse$16(boolean[] zArr, LinearLayout linearLayout, TextView textView, View view) {
        boolean z = zArr[0];
        zArr[0] = !z;
        linearLayout.setVisibility(!z ? 0 : 8);
        textView.setText(zArr[0] ? "▴" : "▾");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$InputLNum$10(final String str, final long j, final long[] jArr, final ValueRow valueRow, final int i, View view) {
        String str2;
        String str3 = "Sửa " + str;
        if (j == 0) {
            str2 = null;
        } else {
            str2 = "Tối đa " + j;
        }
        showInputDialog(str3, str2, String.valueOf(jArr[0]), 4098, DigitsKeyListener.getInstance("0123456789-"), 20, new InputCommit() { // from class: com.android.support.ThemesData.CYGNUS.g
            @Override // com.android.support.ThemesData.CYGNUS.CygnusMenuComponents.InputCommit
            public final void apply(String str4) {
                CygnusMenuComponents.lambda$InputLNum$9(j, jArr, valueRow, str, i, str4);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$InputLNum$9(long j, long[] jArr, ValueRow valueRow, String str, int i, String str2) {
        long jMin;
        String strTrim = str2.trim();
        try {
            if (strTrim.isEmpty()) {
                strTrim = "0";
            }
            jMin = Long.parseLong(strTrim);
        } catch (NumberFormatException unused) {
            jMin = j == 0 ? Long.MAX_VALUE : j;
        }
        if (j != 0) {
            jMin = Math.min(jMin, j);
        }
        jArr[0] = jMin;
        valueRow.value.setText(String.valueOf(jMin));
        Preferences.changeFeatureLong(str, i, jMin);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$InputNum$7(int i, int[] iArr, ValueRow valueRow, String str, int i2, String str2) {
        int iMin;
        String strTrim = str2.trim();
        try {
            if (strTrim.isEmpty()) {
                strTrim = "0";
            }
            iMin = Integer.parseInt(strTrim);
        } catch (NumberFormatException unused) {
            iMin = i == 0 ? Integer.MAX_VALUE : i;
        }
        if (i != 0) {
            iMin = Math.min(iMin, i);
        }
        iArr[0] = iMin;
        valueRow.value.setText(String.valueOf(iMin));
        Preferences.changeFeatureInt(str, i2, iMin);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$InputNum$8(final String str, final int i, final int[] iArr, final ValueRow valueRow, final int i2, View view) {
        String str2;
        String str3 = "Sửa " + str;
        if (i == 0) {
            str2 = null;
        } else {
            str2 = "Tối đa " + i;
        }
        showInputDialog(str3, str2, String.valueOf(iArr[0]), 4098, DigitsKeyListener.getInstance("0123456789-"), 10, new InputCommit() { // from class: com.android.support.ThemesData.CYGNUS.n
            @Override // com.android.support.ThemesData.CYGNUS.CygnusMenuComponents.InputCommit
            public final void apply(String str4) {
                CygnusMenuComponents.lambda$InputNum$7(i, iArr, valueRow, str, i2, str4);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$InputText$11(String[] strArr, ValueRow valueRow, String str, int i, String str2) {
        strArr[0] = str2;
        valueRow.value.setText(displayTextValue(str2));
        Preferences.changeFeatureString(str, i, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$InputText$12(String str, String[] strArr, ValueRow valueRow, int i, View view) {
        showInputDialog("Sửa " + str, null, strArr[0], 1, null, 80, new i(i, valueRow, this, str, strArr));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$RadioButton$14(int[] iArr, ValueRow valueRow, List list, String str, int i, int i2) {
        iArr[0] = i2;
        valueRow.value.setText((CharSequence) list.get(i2));
        Preferences.changeFeatureInt(str, i, i2 + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$RadioButton$15(String str, List list, int[] iArr, ValueRow valueRow, int i, View view) {
        showSearchDropdown(str, list, iArr[0], new i(iArr, valueRow, list, str, i, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$Spinner$5(int[] iArr, ValueRow valueRow, List list, String str, int i, int i2) {
        iArr[0] = i2;
        valueRow.value.setText((CharSequence) list.get(i2));
        Preferences.changeFeatureInt(str, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$Spinner$6(String str, List list, int[] iArr, ValueRow valueRow, int i, View view) {
        showSearchDropdown(str, list, iArr[0], new i(iArr, valueRow, list, str, i, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$Switch$0(boolean[] zArr, ToggleRow toggleRow, String str, int i, View view) {
        boolean z = !zArr[0];
        zArr[0] = z;
        updateToggleRow(toggleRow, z, true);
        Preferences.changeFeatureBool(str, i, zArr[0]);
        if (i == -1) {
            Preferences.with(toggleRow.container.getContext()).writeBoolean(-1, zArr[0]);
            if (zArr[0]) {
                return;
            }
            Preferences.with(toggleRow.container.getContext()).clear();
            return;
        }
        if (i == -3) {
            boolean z2 = zArr[0];
            Preferences.isExpanded = z2;
            CygnusShared cygnusShared = this.sharedData;
            ScrollView scrollView = cygnusShared.scrollView;
            if (scrollView != null) {
                scrollView.setLayoutParams(z2 ? cygnusShared.scrlLLExpanded : cygnusShared.scrlLL);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$SwitchWithSlider$1(boolean[] zArr, ToggleRow toggleRow, String str, int i, LinearLayout linearLayout, SeekBar seekBar, View view) {
        boolean z = !zArr[0];
        zArr[0] = z;
        updateToggleRow(toggleRow, z, true);
        Preferences.changeFeatureBool(str, i, zArr[0]);
        linearLayout.setAlpha(zArr[0] ? 1.0f : 0.5f);
        seekBar.setEnabled(zArr[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showInputDialog$18(InputCommit inputCommit, EditText editText, DialogInterface dialogInterface, int i) {
        inputCommit.apply(editText.getText().toString());
    }

    private List<String> parseOptions(String str) {
        ArrayList arrayList = new ArrayList();
        if (str == null) {
            return arrayList;
        }
        for (String str2 : str.split(",")) {
            String strTrim = str2.trim();
            if (!strTrim.isEmpty()) {
                arrayList.add(strTrim);
            }
        }
        return arrayList;
    }

    private int pressed(int i) {
        return Color.argb(Color.alpha(i), Math.max(0, Color.red(i) - 18), Math.max(0, Color.green(i) - 18), Math.max(0, Color.blue(i) - 18));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public GradientDrawable roundedDrawable(int i, float f) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(i);
        gradientDrawable.setCornerRadius(dp(f));
        return gradientDrawable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public StateListDrawable rowBackground() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_pressed}, strokedDrawable(this.sharedData.MenuStyle.getAccentSoftColor(), this.sharedData.MenuStyle.getAccentBorderColor(), 8.0f));
        stateListDrawable.addState(new int[]{R.attr.state_focused}, strokedDrawable(this.sharedData.MenuStyle.getSurfaceStrongColor(), this.sharedData.MenuStyle.getOutlineColor(), 8.0f));
        stateListDrawable.addState(new int[0], strokedDrawable(this.sharedData.MenuStyle.getSurfaceStrongColor(), this.sharedData.MenuStyle.getOutlineSoftColor(), 8.0f));
        return stateListDrawable;
    }

    private GradientDrawable seekBarThumb() {
        int accentColor = this.sharedData.MenuStyle.getAccentColor();
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(1);
        gradientDrawable.setColor(-1);
        gradientDrawable.setStroke(dp(2), accentColor);
        gradientDrawable.setSize(dp(18), dp(18));
        return gradientDrawable;
    }

    private LayerDrawable seekBarTrack() {
        int surfaceStrongColor = this.sharedData.MenuStyle.getSurfaceStrongColor();
        int outlineColor = this.sharedData.MenuStyle.getOutlineColor();
        int accentColor = this.sharedData.MenuStyle.getAccentColor();
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(surfaceStrongColor);
        gradientDrawable.setStroke(dp(1), outlineColor);
        gradientDrawable.setCornerRadius(dp(4));
        gradientDrawable.setSize(-1, dp(8));
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setColor(surfaceStrongColor);
        gradientDrawable2.setCornerRadius(dp(4));
        gradientDrawable2.setSize(-1, dp(8));
        GradientDrawable gradientDrawable3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{accentColor, -9442305});
        gradientDrawable3.setCornerRadius(dp(4));
        gradientDrawable3.setSize(-1, dp(8));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{gradientDrawable, new ClipDrawable(gradientDrawable2, 3, 1), new ClipDrawable(gradientDrawable3, 3, 1)});
        layerDrawable.setId(0, R.id.background);
        layerDrawable.setId(1, R.id.secondaryProgress);
        layerDrawable.setId(2, R.id.progress);
        return layerDrawable;
    }

    private void showInputDialog(String str, String str2, String str3, int i, DigitsKeyListener digitsKeyListener, int i2, final InputCommit inputCommit) {
        final EditText editText = new EditText(this.sharedData.getContext);
        editText.setText(str3);
        editText.setSelection(editText.length());
        editText.setInputType(i);
        if (digitsKeyListener != null) {
            editText.setKeyListener(digitsKeyListener);
        }
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(i2)});
        if (str2 != null) {
            editText.setHint(str2);
        }
        editText.setTextColor(this.sharedData.MenuStyle.getTextColor2());
        editText.setHintTextColor(this.sharedData.MenuStyle.getDimTextColor());
        editText.setTextSize(2, 14.0f);
        editText.setSingleLine(true);
        editText.setMinHeight(dp(46));
        editText.setPadding(dp(12), 0, dp(12), 0);
        editText.setBackground(strokedDrawable(this.sharedData.MenuStyle.getSurfaceStrongColor(), this.sharedData.MenuStyle.getAccentBorderColor(), 6.0f));
        LinearLayout linearLayout = new LinearLayout(this.sharedData.getContext);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(dp(20), dp(10), dp(20), 0);
        linearLayout.addView(editText);
        AlertDialog alertDialogCreate = new AlertDialog.Builder(this.sharedData.getContext, R.style.Theme.Material.Dialog.Alert).setTitle(str).setView(linearLayout).setPositiveButton("LƯU", new DialogInterface.OnClickListener() { // from class: com.android.support.ThemesData.CYGNUS.m
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i3) {
                CygnusMenuComponents.lambda$showInputDialog$18(inputCommit, editText, dialogInterface, i3);
            }
        }).setNegativeButton("HUỶ", (DialogInterface.OnClickListener) null).create();
        Window window = alertDialogCreate.getWindow();
        if (this.sharedData.overlayRequired && window != null) {
            window.setType(Build.VERSION.SDK_INT >= 26 ? 2038 : 2002);
        }
        alertDialogCreate.show();
        Window window2 = alertDialogCreate.getWindow();
        if (window2 != null) {
            window2.setBackgroundDrawable(roundedDrawable(this.sharedData.MenuStyle.getMenuBgColor(), 12.0f));
            window2.addFlags(2);
            WindowManager.LayoutParams attributes = window2.getAttributes();
            attributes.dimAmount = 0.58f;
            window2.setAttributes(attributes);
        }
        if (alertDialogCreate.getButton(-1) != null) {
            alertDialogCreate.getButton(-1).setTextColor(this.sharedData.MenuStyle.getAccentColor());
        }
        if (alertDialogCreate.getButton(-2) != null) {
            alertDialogCreate.getButton(-2).setTextColor(this.sharedData.MenuStyle.getSecondaryTextColor());
        }
        if (window2 != null) {
            editText.requestFocus();
            window2.setSoftInputMode(5);
        }
    }

    private void showSearchDropdown(String str, List<String> list, int i, OptionPick optionPick) {
        Dialog dialog = new Dialog(this.sharedData.getContext);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.requestFeature(1);
        window.setBackgroundDrawable(roundedDrawable(0, 12.0f));
        if (this.sharedData.overlayRequired) {
            window.setType(Build.VERSION.SDK_INT >= 26 ? 2038 : 2002);
        }
        LinearLayout linearLayout = new LinearLayout(this.sharedData.getContext);
        linearLayout.setOrientation(1);
        linearLayout.setBackground(strokedDrawable(this.sharedData.MenuStyle.getMenuBgColor(), this.sharedData.MenuStyle.getAccentColor(), 12.0f));
        LinearLayout linearLayout2 = new LinearLayout(this.sharedData.getContext);
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(16);
        linearLayout2.setPadding(dp(14), dp(10), dp(8), dp(10));
        linearLayout2.setBackgroundColor(this.sharedData.MenuStyle.getSurfaceColor());
        TextView textView = new TextView(this.sharedData.getContext);
        textView.setText(str);
        textView.setTextColor(this.sharedData.MenuStyle.getTextColor2());
        textView.setTypeface(Typeface.DEFAULT, 1);
        textView.setTextSize(2, 13.0f);
        textView.setSingleLine(true);
        textView.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        TextView textView2 = new TextView(this.sharedData.getContext);
        textView2.setText("✕");
        textView2.setTextColor(this.sharedData.MenuStyle.getSecondaryTextColor());
        textView2.setTextSize(2, 15.0f);
        textView2.setGravity(17);
        textView2.setClickable(true);
        textView2.setFocusable(true);
        textView2.setPadding(dp(6), 0, dp(6), 0);
        textView2.setLayoutParams(new LinearLayout.LayoutParams(dp(30), dp(30)));
        textView2.setOnClickListener(new u(dialog, 1));
        linearLayout2.addView(textView);
        linearLayout2.addView(textView2);
        linearLayout.addView(linearLayout2);
        EditText editText = new EditText(this.sharedData.getContext);
        editText.setHint("Tìm preset...");
        editText.setSingleLine(true);
        editText.setTextColor(this.sharedData.MenuStyle.getTextColor2());
        editText.setHintTextColor(this.sharedData.MenuStyle.getDimTextColor());
        editText.setTextSize(2, 13.0f);
        editText.setTypeface(CygnusFonts.REGULAR);
        editText.setBackground(strokedDrawable(this.sharedData.MenuStyle.getSurfaceStrongColor(), this.sharedData.MenuStyle.getOutlineColor(), 6.0f));
        editText.setPadding(dp(12), dp(8), dp(12), dp(8));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, dp(40));
        layoutParams.setMargins(dp(10), dp(8), dp(10), dp(4));
        editText.setLayoutParams(layoutParams);
        linearLayout.addView(editText);
        ScrollView scrollView = new ScrollView(this.sharedData.getContext);
        scrollView.setVerticalScrollBarEnabled(false);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, dp(260));
        layoutParams2.setMargins(dp(6), dp(4), dp(6), dp(10));
        scrollView.setLayoutParams(layoutParams2);
        LinearLayout linearLayout3 = new LinearLayout(this.sharedData.getContext);
        linearLayout3.setOrientation(1);
        scrollView.addView(linearLayout3);
        linearLayout.addView(scrollView);
        final AnonymousClass3 anonymousClass3 = new AnonymousClass3(linearLayout3, editText, list, new int[]{i}, optionPick, dialog);
        editText.addTextChangedListener(new TextWatcher() { // from class: com.android.support.ThemesData.CYGNUS.CygnusMenuComponents.4
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                anonymousClass3.run();
            }
        });
        anonymousClass3.run();
        dialog.setContentView(linearLayout, new WindowManager.LayoutParams(dp(300), -2));
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = dp(300);
        attributes.height = -2;
        window.setAttributes(attributes);
        window.addFlags(2);
        window.setDimAmount(0.6f);
        dialog.show();
    }

    private GradientDrawable strokedDrawable(int i, int i2, float f) {
        GradientDrawable gradientDrawableRoundedDrawable = roundedDrawable(i, f);
        gradientDrawableRoundedDrawable.setStroke(dp(1), i2);
        return gradientDrawableRoundedDrawable;
    }

    private void updateToggleRow(ToggleRow toggleRow, boolean z, boolean z2) {
        int accentColor = this.sharedData.MenuStyle.getAccentColor();
        int surfaceStrongColor = this.sharedData.MenuStyle.getSurfaceStrongColor();
        int outlineColor = this.sharedData.MenuStyle.getOutlineColor();
        if (z) {
            toggleRow.track.setBackground(roundedDrawable(accentColor, 13.0f));
            toggleRow.track.setElevation(dp(4));
        } else {
            toggleRow.track.setBackground(strokedDrawable(surfaceStrongColor, outlineColor, 13.0f));
            toggleRow.track.setElevation(0.0f);
        }
        toggleRow.thumb.setBackground(roundedDrawable(-1, 11.0f));
        ImageView imageView = toggleRow.icon;
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof CygnusIconDrawable) {
                CygnusIconDrawable cygnusIconDrawable = (CygnusIconDrawable) drawable;
                if (!z) {
                    accentColor = this.sharedData.MenuStyle.getSecondaryTextColor();
                }
                cygnusIconDrawable.setColor(accentColor);
            }
            toggleRow.icon.setBackground(z ? strokedDrawable(this.sharedData.MenuStyle.getAccentSoftColor(), this.sharedData.MenuStyle.getAccentBorderColor(), 8.0f) : strokedDrawable(this.sharedData.MenuStyle.getSurfaceStrongColor(), this.sharedData.MenuStyle.getOutlineSoftColor(), 8.0f));
        }
        float fDp = z ? dp(18) : 0.0f;
        toggleRow.thumb.animate().cancel();
        if (z2) {
            toggleRow.thumb.animate().translationX(fDp).setDuration(260L).setInterpolator(new OvershootInterpolator(1.6f)).start();
        } else {
            toggleRow.thumb.setTranslationX(fDp);
        }
        LinearLayout linearLayout = toggleRow.container;
        StringBuilder sb = new StringBuilder();
        sb.append((Object) toggleRow.label.getText());
        sb.append(", ");
        sb.append(z ? "bật" : "tắt");
        linearLayout.setContentDescription(sb.toString());
    }

    private TextView valueText(String str) {
        TextView textViewLabel = label(str, 11, this.sharedData.MenuStyle.getSecondaryTextColor());
        textViewLabel.setTypeface(Typeface.DEFAULT, 1);
        return textViewLabel;
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void Button(LinearLayout linearLayout, int i, String str) {
        Button button = new Button(this.sharedData.getContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, dp(44));
        layoutParams.setMargins(dp(4), dp(5), dp(4), dp(5));
        button.setLayoutParams(layoutParams);
        button.setText(Html.fromHtml(str));
        button.setAllCaps(false);
        button.setBackground(actionButtonBackground());
        button.setTextColor(this.sharedData.MenuStyle.getActionTextColor());
        button.setTextSize(2, 13.0f);
        button.setTypeface(Typeface.DEFAULT, 1);
        button.setPadding(dp(14), dp(4), dp(14), dp(4));
        button.setOnClickListener(new ViewOnClickListenerC0000a(this, i, str, 1));
        linearLayout.addView(button);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void ButtonLink(LinearLayout linearLayout, String str, String str2) {
        ValueRow valueRowCreateValueRow = createValueRow(str, "MỞ ↗", 14);
        valueRowCreateValueRow.value.setTextColor(this.sharedData.MenuStyle.getAccentColor());
        valueRowCreateValueRow.container.setOnClickListener(new e(this, str2, 1));
        addRow(linearLayout, valueRowCreateValueRow.container);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void ButtonOnOff(LinearLayout linearLayout, int i, String str, boolean z) {
        boolean[] zArr = {Preferences.loadPrefBool(str, i, z)};
        ToggleRow toggleRowCreateToggleRow = createToggleRow(str.replace("OnOff_", ""), zArr[0], CygnusIconDrawable.forFeatureId(i));
        toggleRowCreateToggleRow.container.setOnClickListener(new h(this, zArr, str, i, toggleRowCreateToggleRow));
        addRow(linearLayout, toggleRowCreateToggleRow.container);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void Category(LinearLayout linearLayout, String str) {
        TextView textView = new TextView(this.sharedData.getContext);
        textView.setText(Html.fromHtml(str));
        textView.setTextColor(this.sharedData.MenuStyle.getAccentColor());
        textView.setTextSize(2, 10.0f);
        textView.setLetterSpacing(0.16f);
        textView.setTypeface(Typeface.DEFAULT, 1);
        textView.setGravity(8388627);
        textView.setPadding(dp(12), dp(14), dp(12), dp(6));
        linearLayout.addView(textView);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void CheckBox(LinearLayout linearLayout, int i, String str, boolean z) {
        boolean[] zArr = {Preferences.loadPrefBool(str, i, z)};
        ToggleRow toggleRowCreateToggleRow = createToggleRow(str, zArr[0], CygnusIconDrawable.forFeatureId(i));
        toggleRowCreateToggleRow.container.setOnClickListener(new h(this, zArr, toggleRowCreateToggleRow, str, i, 0));
        addRow(linearLayout, toggleRowCreateToggleRow.container);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void Collapse(LinearLayout linearLayout, String str, boolean z) {
        LinearLayout linearLayout2 = new LinearLayout(this.sharedData.getContext);
        linearLayout2.setOrientation(1);
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        LinearLayout linearLayout3 = new LinearLayout(this.sharedData.getContext);
        int i = 0;
        linearLayout3.setOrientation(0);
        linearLayout3.setGravity(16);
        linearLayout3.setPadding(dp(12), 0, dp(4), 0);
        linearLayout3.setBackground(rowBackground());
        linearLayout3.setClickable(true);
        linearLayout3.setFocusable(true);
        linearLayout3.setLayoutParams(new LinearLayout.LayoutParams(-1, dp(48)));
        TextView textViewLabel = label(str, 13, this.sharedData.MenuStyle.getTextColor2());
        textViewLabel.setTypeface(Typeface.DEFAULT, 1);
        textViewLabel.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        TextView textViewValueText = valueText(z ? "▴" : "▾");
        textViewValueText.setTextColor(this.sharedData.MenuStyle.getAccentColor());
        textViewValueText.setGravity(17);
        textViewValueText.setLayoutParams(new LinearLayout.LayoutParams(dp(36), -1));
        LinearLayout linearLayout4 = new LinearLayout(this.sharedData.getContext);
        linearLayout4.setOrientation(1);
        linearLayout4.setPadding(dp(8), 0, 0, dp(4));
        linearLayout4.setVisibility(z ? 0 : 8);
        this.sharedData.mCollapse = linearLayout4;
        linearLayout3.setOnClickListener(new p(new boolean[]{z}, linearLayout4, textViewValueText, i));
        linearLayout3.addView(textViewLabel);
        linearLayout3.addView(textViewValueText);
        linearLayout2.addView(linearLayout3);
        linearLayout2.addView(linearLayout4);
        addRow(linearLayout, linearLayout2);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void InputLNum(LinearLayout linearLayout, final int i, final String str, final long j) {
        long jLoadPrefLong = Preferences.loadPrefLong(str, i);
        final long[] jArr = {jLoadPrefLong};
        final ValueRow valueRowCreateValueRow = createValueRow(str, String.valueOf(jLoadPrefLong), CygnusIconDrawable.forFeatureId(i));
        valueRowCreateValueRow.value.setTextColor(this.sharedData.MenuStyle.getAccentColor());
        valueRowCreateValueRow.container.setOnClickListener(new View.OnClickListener() { // from class: com.android.support.ThemesData.CYGNUS.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CygnusMenuComponents.ValueRow valueRow = valueRowCreateValueRow;
                this.a.lambda$InputLNum$10(str, j, jArr, valueRow, i, view);
            }
        });
        addRow(linearLayout, valueRowCreateValueRow.container);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void InputNum(LinearLayout linearLayout, final int i, final String str, final int i2) {
        int iLoadPrefInt = Preferences.loadPrefInt(str, i);
        final int[] iArr = {iLoadPrefInt};
        final ValueRow valueRowCreateValueRow = createValueRow(str, String.valueOf(iLoadPrefInt), CygnusIconDrawable.forFeatureId(i));
        valueRowCreateValueRow.value.setTextColor(this.sharedData.MenuStyle.getAccentColor());
        valueRowCreateValueRow.container.setOnClickListener(new View.OnClickListener() { // from class: com.android.support.ThemesData.CYGNUS.o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CygnusMenuComponents.ValueRow valueRow = valueRowCreateValueRow;
                this.a.lambda$InputNum$8(str, i2, iArr, valueRow, i, view);
            }
        });
        addRow(linearLayout, valueRowCreateValueRow.container);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void InputText(LinearLayout linearLayout, int i, String str) {
        String strLoadPrefString = Preferences.loadPrefString(str, i);
        String[] strArr = {strLoadPrefString};
        ValueRow valueRowCreateValueRow = createValueRow(str, displayTextValue(strLoadPrefString), CygnusIconDrawable.forFeatureId(i));
        valueRowCreateValueRow.value.setTextColor(this.sharedData.MenuStyle.getAccentColor());
        valueRowCreateValueRow.container.setOnClickListener(new h(i, valueRowCreateValueRow, this, str, strArr));
        addRow(linearLayout, valueRowCreateValueRow.container);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void RadioButton(LinearLayout linearLayout, int i, String str, String str2) {
        List<String> options = parseOptions(str2);
        if (options.isEmpty()) {
            addDisabledValueRow(linearLayout, str);
            return;
        }
        int iLoadPrefInt = Preferences.loadPrefInt(str, i);
        int[] iArr = {iLoadPrefInt > 0 ? Math.min(iLoadPrefInt - 1, options.size() - 1) : 0};
        ArrayList arrayList = new ArrayList(options.size());
        Iterator<String> it = options.iterator();
        while (it.hasNext()) {
            arrayList.add(CygnusDisplayNames.pretty(it.next()));
        }
        ValueRow valueRowCreateValueRow = createValueRow(str, (String) arrayList.get(iArr[0]), CygnusIconDrawable.forFeatureId(i));
        valueRowCreateValueRow.value.setTextColor(this.sharedData.MenuStyle.getAccentColor());
        valueRowCreateValueRow.container.setOnClickListener(new k(this, str, arrayList, iArr, valueRowCreateValueRow, i, 0));
        addRow(linearLayout, valueRowCreateValueRow.container);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void SeekBar(LinearLayout linearLayout, int i, String str, int i2, int i3) {
        SeekBar(linearLayout, i, str, i2, i3, i2);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void Spinner(LinearLayout linearLayout, int i, String str, String str2) {
        int i2;
        List<String> options = parseOptions(str2);
        if (options.isEmpty()) {
            addDisabledValueRow(linearLayout, str);
            return;
        }
        ArrayList arrayList = new ArrayList(options.size());
        Iterator<String> it = options.iterator();
        while (it.hasNext()) {
            arrayList.add(CygnusDisplayNames.pretty(it.next()));
        }
        if (i < 150 || i > 160) {
            i2 = i == 111 ? 1 : 0;
        } else {
            i2 = i - 150;
        }
        int[] iArr = {Math.max(0, Math.min(arrayList.size() - 1, Preferences.loadPrefInt(str, i, i2)))};
        ValueRow valueRowCreateValueRow = createValueRow(str, (String) arrayList.get(iArr[0]), CygnusIconDrawable.forFeatureId(i));
        valueRowCreateValueRow.value.setTextColor(this.sharedData.MenuStyle.getAccentColor());
        valueRowCreateValueRow.container.setOnClickListener(new k(this, str, arrayList, iArr, valueRowCreateValueRow, i, 1));
        addRow(linearLayout, valueRowCreateValueRow.container);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void Switch(LinearLayout linearLayout, int i, String str, boolean z) {
        boolean[] zArr = {Preferences.loadPrefBool(str, i, z)};
        ToggleRow toggleRowCreateToggleRow = createToggleRow(str, zArr[0], CygnusIconDrawable.forFeatureId(i));
        toggleRowCreateToggleRow.container.setOnClickListener(new h(this, zArr, toggleRowCreateToggleRow, str, i, 1));
        addRow(linearLayout, toggleRowCreateToggleRow.container);
    }

    public void SwitchWithSlider(LinearLayout linearLayout, final int i, final String str, boolean z, final int i2, final String str2, int i3, int i4, int i5) {
        final boolean[] zArr = {Preferences.loadPrefBool(str, i, z)};
        final int iMin = Math.min(i3, i4);
        int iMax = Math.max(i3, i4);
        int iMax2 = Math.max(iMin, Math.min(iMax, Preferences.loadPrefInt(str2, i2, Math.max(iMin, Math.min(iMax, i5)))));
        final ToggleRow toggleRowCreateToggleRow = createToggleRow(str, zArr[0], CygnusIconDrawable.forFeatureId(i));
        LinearLayout linearLayout2 = new LinearLayout(this.sharedData.getContext);
        linearLayout2.setOrientation(1);
        linearLayout2.setBackground(rowBackground());
        linearLayout2.setPadding(dp(12), dp(10), dp(12), dp(12));
        LinearLayout linearLayout3 = new LinearLayout(this.sharedData.getContext);
        linearLayout3.setOrientation(0);
        linearLayout3.setGravity(16);
        int childCount = toggleRowCreateToggleRow.container.getChildCount();
        View[] viewArr = new View[childCount];
        for (int i6 = 0; i6 < childCount; i6++) {
            viewArr[i6] = toggleRowCreateToggleRow.container.getChildAt(i6);
        }
        toggleRowCreateToggleRow.container.removeAllViews();
        int i7 = 0;
        while (i7 < childCount) {
            linearLayout3.addView(viewArr[i7]);
            i7++;
            childCount = childCount;
        }
        linearLayout3.setClickable(true);
        linearLayout3.setFocusable(true);
        final LinearLayout linearLayout4 = new LinearLayout(this.sharedData.getContext);
        linearLayout4.setOrientation(1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(dp(42), dp(8), 0, 0);
        linearLayout4.setLayoutParams(layoutParams);
        LinearLayout linearLayout5 = new LinearLayout(this.sharedData.getContext);
        linearLayout5.setOrientation(0);
        linearLayout5.setGravity(16);
        TextView textView = new TextView(this.sharedData.getContext);
        textView.setText(str2 == null ? "" : str2.toUpperCase());
        textView.setTextColor(this.sharedData.MenuStyle.getSecondaryTextColor());
        Typeface typeface = CygnusFonts.DIGIT;
        textView.setTypeface(typeface, 0);
        textView.setTextSize(2, 9.5f);
        textView.setLetterSpacing(0.14f);
        textView.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        final TextView textView2 = new TextView(this.sharedData.getContext);
        textView2.setText(String.valueOf(iMax2));
        textView2.setTextColor(this.sharedData.MenuStyle.getAccentColor());
        textView2.setTypeface(typeface, 0);
        textView2.setTextSize(2, 14.0f);
        textView2.setShadowLayer(dp(6), 0.0f, 0.0f, this.sharedData.MenuStyle.getAccentBorderColor());
        TextView textView3 = new TextView(this.sharedData.getContext);
        textView3.setText("/" + iMax);
        textView3.setTextColor(this.sharedData.MenuStyle.getDimTextColor());
        textView3.setTypeface(CygnusFonts.REGULAR);
        textView3.setTextSize(2, 10.5f);
        LinearLayout linearLayout6 = new LinearLayout(this.sharedData.getContext);
        linearLayout6.setOrientation(0);
        linearLayout6.setGravity(8388693);
        linearLayout6.addView(textView2);
        linearLayout6.addView(textView3);
        linearLayout5.addView(textView);
        linearLayout5.addView(linearLayout6);
        final SeekBar seekBar = new SeekBar(this.sharedData.getContext);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, dp(30));
        layoutParams2.setMargins(0, dp(4), 0, 0);
        seekBar.setLayoutParams(layoutParams2);
        seekBar.setBackground(null);
        seekBar.setPadding(dp(11), dp(6), dp(11), dp(6));
        int i8 = iMax - iMin;
        seekBar.setMax(i8);
        seekBar.setProgress(iMax2 - iMin);
        seekBar.setProgressDrawable(seekBarTrack());
        seekBar.setThumb(seekBarThumb());
        seekBar.setThumbOffset(dp(9));
        seekBar.setSplitTrack(false);
        seekBar.setEnabled(iMax > iMin);
        seekBar.setContentDescription(str2 + ", " + iMax2);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.android.support.ThemesData.CYGNUS.CygnusMenuComponents.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar2, int i9, boolean z2) {
                int i10 = iMin + i9;
                textView2.setText(String.valueOf(i10));
                seekBar2.setContentDescription(str2 + ", " + i10);
                Preferences.changeFeatureInt(str2, i2, i10);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar2) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar2) {
            }
        });
        LinearLayout linearLayout7 = new LinearLayout(this.sharedData.getContext);
        linearLayout7.setOrientation(0);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams3.setMargins(0, dp(4), 0, 0);
        linearLayout7.setLayoutParams(layoutParams3);
        float f = iMin;
        float f2 = i8;
        int[] iArr = {iMin, Math.round((f2 / 3.0f) + f), Math.round(((f2 * 2.0f) / 3.0f) + f), iMax};
        for (int i9 = 0; i9 < 4; i9++) {
            TextView textView4 = new TextView(this.sharedData.getContext);
            textView4.setText(String.valueOf(iArr[i9]));
            textView4.setTextColor(this.sharedData.MenuStyle.getDimTextColor());
            textView4.setTypeface(CygnusFonts.REGULAR);
            textView4.setTextSize(2, 9.0f);
            LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(0, -2, 1.0f);
            if (i9 == 0) {
                textView4.setGravity(8388611);
            } else if (i9 == 3) {
                textView4.setGravity(8388613);
            } else {
                textView4.setGravity(17);
            }
            textView4.setLayoutParams(layoutParams4);
            linearLayout7.addView(textView4);
        }
        linearLayout4.addView(linearLayout5);
        linearLayout4.addView(seekBar);
        linearLayout4.addView(linearLayout7);
        linearLayout3.setOnClickListener(new View.OnClickListener() { // from class: com.android.support.ThemesData.CYGNUS.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LinearLayout linearLayout8 = linearLayout4;
                SeekBar seekBar2 = seekBar;
                this.a.lambda$SwitchWithSlider$1(zArr, toggleRowCreateToggleRow, str, i, linearLayout8, seekBar2, view);
            }
        });
        linearLayout4.setAlpha(zArr[0] ? 1.0f : 0.5f);
        seekBar.setEnabled(zArr[0]);
        linearLayout2.addView(linearLayout3);
        linearLayout2.addView(linearLayout4);
        addRow(linearLayout, linearLayout2);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void TextView(LinearLayout linearLayout, String str) {
        TextView textView = new TextView(this.sharedData.getContext);
        textView.setText(Html.fromHtml(str));
        textView.setTextColor(this.sharedData.MenuStyle.getSecondaryTextColor());
        textView.setLinkTextColor(this.sharedData.MenuStyle.getAccentColor());
        textView.setTextSize(2, 12.0f);
        textView.setLineSpacing(0.0f, 1.35f);
        textView.setPadding(dp(12), dp(7), dp(12), dp(7));
        linearLayout.addView(textView);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void WebTextView(LinearLayout linearLayout, String str) {
        WebView webView = new WebView(this.sharedData.getContext);
        webView.loadData(("<style>body{color:" + colorHex(this.sharedData.MenuStyle.getTextColor2()) + ";background:transparent;font-family:sans-serif;font-size:13px;margin:8px}a{color:" + colorHex(this.sharedData.MenuStyle.getAccentColor()) + "}</style>") + str, "text/html", "utf-8");
        webView.setBackgroundColor(0);
        WebSettings settings = webView.getSettings();
        settings.setDefaultFontSize(13);
        settings.setDefaultTextEncodingName("utf-8");
        linearLayout.addView(webView);
    }

    public void SeekBar(LinearLayout linearLayout, final int i, final String str, int i2, int i3, int i4) {
        final int iMin = Math.min(i2, i3);
        int iMax = Math.max(i2, i3);
        int iMax2 = Math.max(iMin, Math.min(iMax, Preferences.loadPrefInt(str, i, Math.max(iMin, Math.min(iMax, i4)))));
        LinearLayout linearLayout2 = new LinearLayout(this.sharedData.getContext);
        linearLayout2.setOrientation(1);
        linearLayout2.setBackground(rowBackground());
        linearLayout2.setPadding(dp(12), dp(10), dp(12), dp(12));
        LinearLayout linearLayout3 = new LinearLayout(this.sharedData.getContext);
        linearLayout3.setOrientation(0);
        linearLayout3.setGravity(16);
        ImageView imageViewFeatureIcon = featureIcon(CygnusIconDrawable.forFeatureId(i), this.sharedData.MenuStyle.getAccentColor(), false);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dp(32), dp(32));
        layoutParams.setMargins(0, 0, dp(10), 0);
        imageViewFeatureIcon.setLayoutParams(layoutParams);
        TextView textViewLabel = label(str, 13, this.sharedData.MenuStyle.getTextColor2());
        textViewLabel.setTypeface(Typeface.DEFAULT, 1);
        textViewLabel.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        final TextView textView = new TextView(this.sharedData.getContext);
        textView.setText(String.valueOf(iMax2));
        textView.setTextColor(this.sharedData.MenuStyle.getAccentColor());
        textView.setTypeface(CygnusFonts.DIGIT, 0);
        textView.setTextSize(2, 15.0f);
        textView.setShadowLayer(dp(6), 0.0f, 0.0f, this.sharedData.MenuStyle.getAccentBorderColor());
        TextView textView2 = new TextView(this.sharedData.getContext);
        textView2.setText("/" + iMax);
        textView2.setTextColor(this.sharedData.MenuStyle.getDimTextColor());
        textView2.setTypeface(CygnusFonts.REGULAR);
        textView2.setTextSize(2, 11.0f);
        LinearLayout linearLayout4 = new LinearLayout(this.sharedData.getContext);
        linearLayout4.setOrientation(0);
        linearLayout4.setGravity(8388693);
        linearLayout4.addView(textView);
        linearLayout4.addView(textView2);
        linearLayout3.addView(imageViewFeatureIcon);
        linearLayout3.addView(textViewLabel);
        linearLayout3.addView(linearLayout4);
        LinearLayout linearLayout5 = new LinearLayout(this.sharedData.getContext);
        linearLayout5.setOrientation(1);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.setMargins(dp(42), dp(8), 0, 0);
        linearLayout5.setLayoutParams(layoutParams2);
        SeekBar seekBar = new SeekBar(this.sharedData.getContext);
        seekBar.setLayoutParams(new LinearLayout.LayoutParams(-1, dp(30)));
        seekBar.setBackground(null);
        seekBar.setPadding(dp(11), dp(6), dp(11), dp(6));
        int i5 = iMax - iMin;
        seekBar.setMax(i5);
        seekBar.setProgress(iMax2 - iMin);
        seekBar.setProgressDrawable(seekBarTrack());
        seekBar.setThumb(seekBarThumb());
        seekBar.setThumbOffset(dp(9));
        seekBar.setSplitTrack(false);
        seekBar.setEnabled(iMax > iMin);
        seekBar.setContentDescription(str + ", " + iMax2);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.android.support.ThemesData.CYGNUS.CygnusMenuComponents.2
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar2, int i6, boolean z) {
                int i7 = iMin + i6;
                textView.setText(String.valueOf(i7));
                seekBar2.setContentDescription(str + ", " + i7);
                Preferences.changeFeatureInt(str, i, i7);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar2) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar2) {
            }
        });
        LinearLayout linearLayout6 = new LinearLayout(this.sharedData.getContext);
        linearLayout6.setOrientation(0);
        linearLayout6.setGravity(16);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams3.setMargins(0, dp(4), 0, 0);
        linearLayout6.setLayoutParams(layoutParams3);
        float f = iMin;
        float f2 = i5;
        int[] iArr = {iMin, Math.round((f2 / 3.0f) + f), Math.round(((f2 * 2.0f) / 3.0f) + f), iMax};
        for (int i6 = 0; i6 < 4; i6++) {
            TextView textView3 = new TextView(this.sharedData.getContext);
            textView3.setText(String.valueOf(iArr[i6]));
            textView3.setTextColor(this.sharedData.MenuStyle.getDimTextColor());
            textView3.setTypeface(CygnusFonts.REGULAR);
            textView3.setTextSize(2, 9.0f);
            LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(0, -2, 1.0f);
            if (i6 == 0) {
                textView3.setGravity(8388611);
            } else if (i6 == 3) {
                textView3.setGravity(8388613);
            } else {
                textView3.setGravity(17);
            }
            textView3.setLayoutParams(layoutParams4);
            linearLayout6.addView(textView3);
        }
        linearLayout5.addView(seekBar);
        linearLayout5.addView(linearLayout6);
        linearLayout2.addView(linearLayout3);
        linearLayout2.addView(linearLayout5);
        addRow(linearLayout, linearLayout2);
    }

    private int dp(float f) {
        return Math.round(TypedValue.applyDimension(1, f, this.sharedData.getContext.getResources().getDisplayMetrics()));
    }
}
