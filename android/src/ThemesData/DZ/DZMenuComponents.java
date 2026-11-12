package com.android.support.ThemesData.DZ;

import android.R;
import android.app.AlertDialog;
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
import android.text.Html;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.android.support.Preferences;
import com.android.support.ThemesData.CYGNUS.ViewOnClickListenerC0000a;
import com.android.support.ThemesData.CYGNUS.p;
import com.android.support.interfaces.IMenuComponentFactory;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public class DZMenuComponents implements IMenuComponentFactory {
    private final DZShared sharedData;

    public interface InputCommit {
        void apply(String str);
    }

    public static class ToggleRow {
        final LinearLayout container;
        final TextView label;
        final View thumb;
        final FrameLayout track;

        public ToggleRow(LinearLayout linearLayout, TextView textView, FrameLayout frameLayout, View view) {
            this.container = linearLayout;
            this.label = textView;
            this.track = frameLayout;
            this.thumb = view;
        }
    }

    public static class ValueRow {
        final LinearLayout container;
        final TextView label;
        final TextView value;

        public ValueRow(LinearLayout linearLayout, TextView textView, TextView textView2) {
            this.container = linearLayout;
            this.label = textView;
            this.value = textView2;
        }
    }

    public DZMenuComponents(DZShared dZShared) {
        this.sharedData = dZShared;
    }

    private void addDisabledValueRow(LinearLayout linearLayout, String str) {
        ValueRow valueRowCreateValueRow = createValueRow(str, "CHƯA CÀI");
        valueRowCreateValueRow.container.setEnabled(false);
        valueRowCreateValueRow.value.setTextColor(this.sharedData.MenuStyle.getSecondaryTextColor());
        addSeparatedRow(linearLayout, valueRowCreateValueRow.container);
    }

    private void addSeparatedRow(LinearLayout linearLayout, View view) {
        if (linearLayout.getChildCount() > 0) {
            View view2 = new View(this.sharedData.getContext);
            view2.setBackgroundColor(this.sharedData.MenuStyle.getOutlineColor());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, dp(1));
            layoutParams.setMargins(dp(12), 0, dp(12), 0);
            view2.setLayoutParams(layoutParams);
            linearLayout.addView(view2);
        }
        linearLayout.addView(view);
    }

    private StateListDrawable buttonBackground(int i) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{-16842910}, roundedDrawable(Color.argb(95, Color.red(i), Color.green(i), Color.blue(i)), 6.0f));
        stateListDrawable.addState(new int[]{R.attr.state_pressed}, roundedDrawable(pressedColor(i), 6.0f));
        stateListDrawable.addState(new int[]{R.attr.state_focused}, roundedDrawable(pressedColor(i), 6.0f));
        stateListDrawable.addState(new int[0], roundedDrawable(i, 6.0f));
        return stateListDrawable;
    }

    private String colorHex(int i) {
        return String.format("#%06X", Integer.valueOf(i & 16777215));
    }

    private ToggleRow createToggleRow(String str, boolean z) {
        LinearLayout linearLayout = new LinearLayout(this.sharedData.getContext);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        linearLayout.setPadding(dp(12), 0, dp(10), 0);
        linearLayout.setBackground(roundedDrawable(0, 6.0f));
        linearLayout.setClickable(true);
        linearLayout.setFocusable(true);
        if (Build.VERSION.SDK_INT >= 26) {
            linearLayout.setDefaultFocusHighlightEnabled(false);
        }
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        linearLayout.setMinimumHeight(dp(48));
        linearLayout.setPadding(dp(12), dp(6), dp(10), dp(6));
        TextView textViewLabel = label(str, 14, this.sharedData.MenuStyle.getTextColor2());
        textViewLabel.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        FrameLayout frameLayout = new FrameLayout(this.sharedData.getContext);
        frameLayout.setPadding(dp(3), dp(3), dp(3), dp(3));
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(dp(46), dp(26)));
        frameLayout.setImportantForAccessibility(2);
        View view = new View(this.sharedData.getContext);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(dp(20), dp(20));
        layoutParams.gravity = 8388627;
        view.setLayoutParams(layoutParams);
        view.setElevation(dp(2));
        frameLayout.addView(view);
        linearLayout.addView(textViewLabel);
        linearLayout.addView(frameLayout);
        ToggleRow toggleRow = new ToggleRow(linearLayout, textViewLabel, frameLayout, view);
        updateToggleRow(toggleRow, z, false);
        return toggleRow;
    }

    private ValueRow createValueRow(String str, String str2) {
        LinearLayout linearLayout = new LinearLayout(this.sharedData.getContext);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        linearLayout.setPadding(dp(12), 0, dp(10), 0);
        linearLayout.setBackground(flatRowBackground());
        linearLayout.setClickable(true);
        linearLayout.setFocusable(true);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        linearLayout.setMinimumHeight(dp(48));
        linearLayout.setPadding(dp(12), dp(6), dp(10), dp(6));
        linearLayout.setContentDescription(str + ", " + str2);
        TextView textViewLabel = label(str, 14, this.sharedData.MenuStyle.getTextColor2());
        textViewLabel.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        TextView textViewValueText = valueText(str2);
        textViewValueText.setMaxWidth(dp(132));
        textViewValueText.setGravity(8388629);
        textViewValueText.setPadding(dp(12), 0, 0, 0);
        linearLayout.addView(textViewLabel);
        linearLayout.addView(textViewValueText);
        return new ValueRow(linearLayout, textViewLabel, textViewValueText);
    }

    private AlertDialog.Builder dialogBuilder() {
        return new AlertDialog.Builder(this.sharedData.getContext, R.style.Theme.Material.Dialog.Alert);
    }

    private String displayTextValue(String str) {
        if (str == null || str.isEmpty()) {
            str = "TRỐNG";
        }
        return str.concat("  ›");
    }

    private int dp(int i) {
        return Math.round(TypedValue.applyDimension(1, i, this.sharedData.getContext.getResources().getDisplayMetrics()));
    }

    private StateListDrawable flatRowBackground() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{-16842910}, roundedDrawable(0, 6.0f));
        stateListDrawable.addState(new int[]{R.attr.state_pressed}, roundedDrawable(this.sharedData.MenuStyle.getSurfaceColor(), 6.0f));
        stateListDrawable.addState(new int[]{R.attr.state_focused}, roundedDrawable(this.sharedData.MenuStyle.getSurfaceColor(), 6.0f));
        stateListDrawable.addState(new int[0], roundedDrawable(0, 6.0f));
        return stateListDrawable;
    }

    private TextView label(String str, int i, int i2) {
        TextView textView = new TextView(this.sharedData.getContext);
        textView.setText(str);
        textView.setTextColor(i2);
        textView.setTextSize(2, i);
        textView.setMaxLines(2);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setLineSpacing(0.0f, 1.05f);
        textView.setIncludeFontPadding(false);
        return textView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$Button$1(int i, String str, View view) {
        if (i == -6) {
            this.sharedData.menu.showFeatures();
        } else if (i == -100) {
            this.sharedData.stopChecking = true;
        }
        Preferences.changeFeatureInt(str, i, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$ButtonLink$2(String str, View view) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.setFlags(268435456);
        this.sharedData.getContext.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$ButtonOnOff$3(boolean[] zArr, String str, int i, ToggleRow toggleRow, View view) {
        boolean z = !zArr[0];
        zArr[0] = z;
        Preferences.changeFeatureBool(str, i, z);
        updateToggleRow(toggleRow, zArr[0], true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$CheckBox$12(boolean[] zArr, ToggleRow toggleRow, String str, int i, View view) {
        boolean z = !zArr[0];
        zArr[0] = z;
        updateToggleRow(toggleRow, z, true);
        Preferences.changeFeatureBool(str, i, zArr[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$Collapse$15(boolean[] zArr, LinearLayout linearLayout, TextView textView, View view) {
        boolean z = zArr[0];
        zArr[0] = !z;
        linearLayout.setVisibility(!z ? 0 : 8);
        textView.setText(zArr[0] ? "▴" : "▾");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$InputLNum$8(long j, long[] jArr, ValueRow valueRow, String str, int i, String str2) {
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
        valueRow.value.setText(jMin + "  ›");
        Preferences.changeFeatureLong(str, i, jMin);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$InputLNum$9(final String str, final long j, final long[] jArr, final ValueRow valueRow, final int i, View view) {
        String str2;
        String str3 = "Edit " + str;
        if (j == 0) {
            str2 = null;
        } else {
            str2 = "Maximum " + j;
        }
        showInputDialog(str3, str2, String.valueOf(jArr[0]), 4098, DigitsKeyListener.getInstance("0123456789-"), 20, new InputCommit() { // from class: com.android.support.ThemesData.DZ.l
            @Override // com.android.support.ThemesData.DZ.DZMenuComponents.InputCommit
            public final void apply(String str4) {
                DZMenuComponents.lambda$InputLNum$8(j, jArr, valueRow, str, i, str4);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$InputNum$6(int i, int[] iArr, ValueRow valueRow, String str, int i2, String str2) {
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
        valueRow.value.setText(iMin + "  ›");
        Preferences.changeFeatureInt(str, i2, iMin);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$InputNum$7(final String str, final int i, final int[] iArr, final ValueRow valueRow, final int i2, View view) {
        String str2;
        String str3 = "Edit " + str;
        if (i == 0) {
            str2 = null;
        } else {
            str2 = "Maximum " + i;
        }
        showInputDialog(str3, str2, String.valueOf(iArr[0]), 4098, DigitsKeyListener.getInstance("0123456789-"), 10, new InputCommit() { // from class: com.android.support.ThemesData.DZ.i
            @Override // com.android.support.ThemesData.DZ.DZMenuComponents.InputCommit
            public final void apply(String str4) {
                DZMenuComponents.lambda$InputNum$6(i, iArr, valueRow, str, i2, str4);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$InputText$10(String[] strArr, ValueRow valueRow, String str, int i, String str2) {
        strArr[0] = str2;
        valueRow.value.setText(displayTextValue(str2));
        Preferences.changeFeatureString(str, i, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$InputText$11(final String str, final String[] strArr, final ValueRow valueRow, final int i, View view) {
        showInputDialog("Edit " + str, null, strArr[0], 1, null, 80, new InputCommit() { // from class: com.android.support.ThemesData.DZ.k
            @Override // com.android.support.ThemesData.DZ.DZMenuComponents.InputCommit
            public final void apply(String str2) {
                this.lambda$InputText$10(strArr, valueRow, str, i, str2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$RadioButton$13(int[] iArr, ValueRow valueRow, List list, String str, int i, DialogInterface dialogInterface, int i2) {
        iArr[0] = i2;
        valueRow.value.setText(((String) list.get(i2)) + "  ›");
        Preferences.changeFeatureInt(str, i, i2 + 1);
        dialogInterface.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$RadioButton$14(String str, List list, int[] iArr, ValueRow valueRow, int i, View view) {
        showDialog(dialogBuilder().setTitle(str).setSingleChoiceItems((CharSequence[]) list.toArray(new String[0]), iArr[0], new c(iArr, valueRow, list, str, i, 0)).setNegativeButton("HUỶ", (DialogInterface.OnClickListener) null).create(), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$Spinner$4(int[] iArr, ValueRow valueRow, List list, String str, int i, DialogInterface dialogInterface, int i2) {
        iArr[0] = i2;
        valueRow.value.setText(((String) list.get(i2)) + "  ›");
        Preferences.changeFeatureInt(str, i, i2);
        dialogInterface.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$Spinner$5(String str, List list, int[] iArr, ValueRow valueRow, int i, View view) {
        showDialog(dialogBuilder().setTitle(str).setSingleChoiceItems((CharSequence[]) list.toArray(new String[0]), iArr[0], new c(iArr, valueRow, list, str, i, 1)).setNegativeButton("HUỶ", (DialogInterface.OnClickListener) null).create(), null);
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
            DZShared dZShared = this.sharedData;
            dZShared.scrollView.setLayoutParams(z2 ? dZShared.scrlLLExpanded : dZShared.scrlLL);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showInputDialog$16(InputCommit inputCommit, EditText editText, DialogInterface dialogInterface, int i) {
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

    private int pressedColor(int i) {
        return Color.argb(Color.alpha(i), Math.max(0, Color.red(i) - 18), Math.max(0, Color.green(i) - 18), Math.max(0, Color.blue(i) - 18));
    }

    private GradientDrawable roundedDrawable(int i, float f) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(i);
        gradientDrawable.setCornerRadius(dp(f));
        return gradientDrawable;
    }

    private GradientDrawable seekBarThumb() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(1);
        gradientDrawable.setColor(this.sharedData.MenuStyle.getSeekBarProgressColor());
        gradientDrawable.setStroke(dp(3), this.sharedData.MenuStyle.getMenuFeatureBgColor());
        gradientDrawable.setSize(dp(18), dp(18));
        return gradientDrawable;
    }

    private LayerDrawable seekBarTrack() {
        GradientDrawable gradientDrawableRoundedDrawable = roundedDrawable(this.sharedData.MenuStyle.getOutlineColor(), 2.0f);
        gradientDrawableRoundedDrawable.setSize(-1, dp(4));
        GradientDrawable gradientDrawableRoundedDrawable2 = roundedDrawable(this.sharedData.MenuStyle.getOutlineColor(), 2.0f);
        gradientDrawableRoundedDrawable2.setSize(-1, dp(4));
        GradientDrawable gradientDrawableRoundedDrawable3 = roundedDrawable(this.sharedData.MenuStyle.getSeekBarProgressColor(), 2.0f);
        gradientDrawableRoundedDrawable3.setSize(-1, dp(4));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{gradientDrawableRoundedDrawable, new ClipDrawable(gradientDrawableRoundedDrawable2, 3, 1), new ClipDrawable(gradientDrawableRoundedDrawable3, 3, 1)});
        layerDrawable.setId(0, R.id.background);
        layerDrawable.setId(1, R.id.secondaryProgress);
        layerDrawable.setId(2, R.id.progress);
        return layerDrawable;
    }

    private void showDialog(AlertDialog alertDialog, EditText editText) {
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
        if (alertDialog.getButton(-1) != null) {
            alertDialog.getButton(-1).setTextColor(this.sharedData.MenuStyle.getTextColor());
        }
        if (alertDialog.getButton(-2) != null) {
            alertDialog.getButton(-2).setTextColor(this.sharedData.MenuStyle.getSecondaryTextColor());
        }
        ListView listView = alertDialog.getListView();
        if (listView != null) {
            listView.setDividerHeight(0);
            listView.setBackgroundColor(0);
        }
        if (editText == null || window2 == null) {
            return;
        }
        editText.requestFocus();
        window2.setSoftInputMode(5);
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
        styleEditText(editText);
        LinearLayout linearLayout = new LinearLayout(this.sharedData.getContext);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(dp(20), 0, dp(20), 0);
        linearLayout.addView(editText);
        showDialog(dialogBuilder().setTitle(str).setView(linearLayout).setPositiveButton("LƯU", new DialogInterface.OnClickListener() { // from class: com.android.support.ThemesData.DZ.h
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i3) {
                DZMenuComponents.lambda$showInputDialog$16(inputCommit, editText, dialogInterface, i3);
            }
        }).setNegativeButton("HUỶ", (DialogInterface.OnClickListener) null).create(), editText);
    }

    private GradientDrawable strokedDrawable(int i, int i2, float f) {
        GradientDrawable gradientDrawableRoundedDrawable = roundedDrawable(i, f);
        gradientDrawableRoundedDrawable.setStroke(dp(1), i2);
        return gradientDrawableRoundedDrawable;
    }

    private void styleActionButton(Button button) {
        button.setBackground(buttonBackground(this.sharedData.MenuStyle.getActionColor()));
        button.setTextColor(this.sharedData.MenuStyle.getActionTextColor());
        button.setTextSize(2, 13.0f);
        button.setTypeface(Typeface.DEFAULT, 1);
        button.setGravity(17);
        button.setMinHeight(0);
        button.setMinimumHeight(0);
        button.setMinWidth(0);
        button.setMinimumWidth(0);
        button.setPadding(dp(14), dp(4), dp(14), dp(4));
    }

    private void styleEditText(EditText editText) {
        editText.setTextColor(this.sharedData.MenuStyle.getTextColor2());
        editText.setHintTextColor(this.sharedData.MenuStyle.getSecondaryTextColor());
        editText.setTextSize(2, 15.0f);
        editText.setSingleLine(true);
        editText.setMinHeight(dp(48));
        editText.setPadding(dp(12), 0, dp(12), 0);
        editText.setBackground(strokedDrawable(this.sharedData.MenuStyle.getSurfaceColor(), this.sharedData.MenuStyle.getOutlineColor(), 6.0f));
    }

    private void updateToggleRow(ToggleRow toggleRow, boolean z, boolean z2) {
        toggleRow.track.setBackground(z ? strokedDrawable(this.sharedData.MenuStyle.getAccentMutedColor(), this.sharedData.MenuStyle.getTextColor(), 13.0f) : strokedDrawable(this.sharedData.MenuStyle.getSurfaceColor(), this.sharedData.MenuStyle.getOutlineColor(), 13.0f));
        toggleRow.thumb.setBackground(roundedDrawable(z ? this.sharedData.MenuStyle.getTextColor() : this.sharedData.MenuStyle.getSecondaryTextColor(), 10.0f));
        float fDp = z ? dp(20) : 0.0f;
        toggleRow.thumb.animate().cancel();
        if (z2) {
            toggleRow.thumb.animate().translationX(fDp).setDuration(160L).start();
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
        TextView textViewLabel = label(str, 12, this.sharedData.MenuStyle.getSecondaryTextColor());
        textViewLabel.setTypeface(Typeface.DEFAULT, 1);
        return textViewLabel;
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void Button(LinearLayout linearLayout, int i, String str) {
        Button button = new Button(this.sharedData.getContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(dp(4), dp(5), dp(4), dp(5));
        button.setLayoutParams(layoutParams);
        button.setMinimumHeight(dp(46));
        button.setText(Html.fromHtml(str));
        button.setAllCaps(false);
        styleActionButton(button);
        button.setOnClickListener(new ViewOnClickListenerC0000a(this, i, str, 2));
        linearLayout.addView(button);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void ButtonLink(LinearLayout linearLayout, String str, final String str2) {
        ValueRow valueRowCreateValueRow = createValueRow(str, "MỞ ↗");
        valueRowCreateValueRow.value.setTextColor(this.sharedData.MenuStyle.getTextColor());
        valueRowCreateValueRow.container.setContentDescription("Mở " + str);
        valueRowCreateValueRow.container.setOnClickListener(new View.OnClickListener() { // from class: com.android.support.ThemesData.DZ.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.a.lambda$ButtonLink$2(str2, view);
            }
        });
        addSeparatedRow(linearLayout, valueRowCreateValueRow.container);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void ButtonOnOff(LinearLayout linearLayout, int i, String str, boolean z) {
        boolean[] zArr = {Preferences.loadPrefBool(str, i, z)};
        ToggleRow toggleRowCreateToggleRow = createToggleRow(str.replace("OnOff_", ""), zArr[0]);
        toggleRowCreateToggleRow.container.setOnClickListener(new e(this, zArr, str, i, toggleRowCreateToggleRow));
        addSeparatedRow(linearLayout, toggleRowCreateToggleRow.container);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void Category(LinearLayout linearLayout, String str) {
        TextView textView = new TextView(this.sharedData.getContext);
        textView.setText(Html.fromHtml(str));
        textView.setTextColor(this.sharedData.MenuStyle.getTextColor());
        textView.setTextSize(2, 11.0f);
        textView.setTypeface(Typeface.DEFAULT, 1);
        textView.setGravity(8388627);
        textView.setPadding(dp(12), dp(16), dp(12), dp(6));
        linearLayout.addView(textView);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void CheckBox(LinearLayout linearLayout, int i, String str, boolean z) {
        boolean[] zArr = {Preferences.loadPrefBool(str, i, z)};
        ToggleRow toggleRowCreateToggleRow = createToggleRow(str, zArr[0]);
        toggleRowCreateToggleRow.container.setOnClickListener(new e(this, zArr, toggleRowCreateToggleRow, str, i, 1));
        addSeparatedRow(linearLayout, toggleRowCreateToggleRow.container);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void Collapse(LinearLayout linearLayout, String str, boolean z) {
        LinearLayout linearLayout2 = new LinearLayout(this.sharedData.getContext);
        int i = 1;
        linearLayout2.setOrientation(1);
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        LinearLayout linearLayout3 = new LinearLayout(this.sharedData.getContext);
        linearLayout3.setOrientation(0);
        linearLayout3.setGravity(16);
        linearLayout3.setPadding(dp(12), 0, dp(2), 0);
        linearLayout3.setBackground(flatRowBackground());
        linearLayout3.setClickable(true);
        linearLayout3.setFocusable(true);
        linearLayout3.setContentDescription(str + ", mục có thể mở rộng");
        linearLayout3.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        linearLayout3.setMinimumHeight(dp(48));
        linearLayout3.setPadding(dp(12), dp(6), dp(2), dp(6));
        TextView textViewLabel = label(str, 13, this.sharedData.MenuStyle.getTextColor2());
        textViewLabel.setTypeface(Typeface.DEFAULT, 1);
        textViewLabel.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        TextView textViewValueText = valueText(z ? "▴" : "▾");
        textViewValueText.setGravity(17);
        textViewValueText.setLayoutParams(new LinearLayout.LayoutParams(dp(40), -1));
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
        addSeparatedRow(linearLayout, linearLayout2);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void InputLNum(LinearLayout linearLayout, final int i, final String str, final long j) {
        long jLoadPrefLong = Preferences.loadPrefLong(str, i);
        final long[] jArr = {jLoadPrefLong};
        final ValueRow valueRowCreateValueRow = createValueRow(str, String.valueOf(jLoadPrefLong) + "  ›");
        valueRowCreateValueRow.value.setTextColor(this.sharedData.MenuStyle.getTextColor());
        valueRowCreateValueRow.container.setOnClickListener(new View.OnClickListener() { // from class: com.android.support.ThemesData.DZ.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DZMenuComponents.ValueRow valueRow = valueRowCreateValueRow;
                this.a.lambda$InputLNum$9(str, j, jArr, valueRow, i, view);
            }
        });
        addSeparatedRow(linearLayout, valueRowCreateValueRow.container);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void InputNum(LinearLayout linearLayout, final int i, final String str, final int i2) {
        int iLoadPrefInt = Preferences.loadPrefInt(str, i);
        final int[] iArr = {iLoadPrefInt};
        final ValueRow valueRowCreateValueRow = createValueRow(str, String.valueOf(iLoadPrefInt) + "  ›");
        valueRowCreateValueRow.value.setTextColor(this.sharedData.MenuStyle.getTextColor());
        valueRowCreateValueRow.container.setOnClickListener(new View.OnClickListener() { // from class: com.android.support.ThemesData.DZ.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DZMenuComponents.ValueRow valueRow = valueRowCreateValueRow;
                this.a.lambda$InputNum$7(str, i2, iArr, valueRow, i, view);
            }
        });
        addSeparatedRow(linearLayout, valueRowCreateValueRow.container);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void InputText(LinearLayout linearLayout, int i, String str) {
        String strLoadPrefString = Preferences.loadPrefString(str, i);
        String[] strArr = {strLoadPrefString};
        ValueRow valueRowCreateValueRow = createValueRow(str, displayTextValue(strLoadPrefString));
        valueRowCreateValueRow.value.setTextColor(this.sharedData.MenuStyle.getTextColor());
        valueRowCreateValueRow.container.setOnClickListener(new e(i, valueRowCreateValueRow, this, str, strArr));
        addSeparatedRow(linearLayout, valueRowCreateValueRow.container);
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
        ValueRow valueRowCreateValueRow = createValueRow(str, options.get(iArr[0]) + "  ›");
        valueRowCreateValueRow.container.setOnClickListener(new g(this, str, options, iArr, valueRowCreateValueRow, i, 1));
        addSeparatedRow(linearLayout, valueRowCreateValueRow.container);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void SeekBar(LinearLayout linearLayout, int i, String str, int i2, int i3) {
        SeekBar(linearLayout, i, str, i2, i3, i2);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void Spinner(LinearLayout linearLayout, int i, String str, String str2) {
        List<String> options = parseOptions(str2);
        if (options.isEmpty()) {
            addDisabledValueRow(linearLayout, str);
            return;
        }
        int[] iArr = {Math.max(0, Math.min(options.size() - 1, Preferences.loadPrefInt(str, i)))};
        ValueRow valueRowCreateValueRow = createValueRow(str, options.get(iArr[0]) + "  ›");
        valueRowCreateValueRow.container.setOnClickListener(new g(this, str, options, iArr, valueRowCreateValueRow, i, 0));
        addSeparatedRow(linearLayout, valueRowCreateValueRow.container);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void Switch(LinearLayout linearLayout, int i, String str, boolean z) {
        boolean[] zArr = {Preferences.loadPrefBool(str, i, z)};
        ToggleRow toggleRowCreateToggleRow = createToggleRow(str, zArr[0]);
        toggleRowCreateToggleRow.container.setOnClickListener(new e(this, zArr, toggleRowCreateToggleRow, str, i, 2));
        addSeparatedRow(linearLayout, toggleRowCreateToggleRow.container);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void TextView(LinearLayout linearLayout, String str) {
        TextView textView = new TextView(this.sharedData.getContext);
        textView.setText(Html.fromHtml(str));
        textView.setTextColor(this.sharedData.MenuStyle.getSecondaryTextColor());
        textView.setLinkTextColor(this.sharedData.MenuStyle.getTextColor());
        textView.setTextSize(2, 13.0f);
        textView.setLineSpacing(0.0f, 1.12f);
        textView.setPadding(dp(12), dp(7), dp(12), dp(7));
        linearLayout.addView(textView);
    }

    @Override // com.android.support.interfaces.IMenuComponentFactory
    public void WebTextView(LinearLayout linearLayout, String str) {
        WebView webView = new WebView(this.sharedData.getContext);
        webView.loadData(("<style>body{color:" + colorHex(this.sharedData.MenuStyle.getTextColor2()) + ";background:transparent;font-family:sans-serif;font-size:13px;margin:8px}a{color:" + colorHex(this.sharedData.MenuStyle.getTextColor()) + "}</style>") + str, "text/html", "utf-8");
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
        linearLayout2.setPadding(dp(12), dp(10), dp(12), dp(8));
        LinearLayout linearLayout3 = new LinearLayout(this.sharedData.getContext);
        linearLayout3.setGravity(16);
        TextView textViewLabel = label(str, 14, this.sharedData.MenuStyle.getTextColor2());
        textViewLabel.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        final TextView textViewValueText = valueText(String.valueOf(iMax2));
        textViewValueText.setTextColor(this.sharedData.MenuStyle.getTextColor());
        textViewValueText.setGravity(17);
        textViewValueText.setMinWidth(dp(42));
        textViewValueText.setPadding(dp(8), dp(2), dp(8), dp(2));
        textViewValueText.setBackground(strokedDrawable(this.sharedData.MenuStyle.getAccentMutedColor(), this.sharedData.MenuStyle.getTextColor(), 6.0f));
        linearLayout3.addView(textViewLabel);
        linearLayout3.addView(textViewValueText);
        SeekBar seekBar = new SeekBar(this.sharedData.getContext);
        seekBar.setLayoutParams(new LinearLayout.LayoutParams(-1, dp(36)));
        seekBar.setBackground(null);
        if (Build.VERSION.SDK_INT >= 26) {
            seekBar.setDefaultFocusHighlightEnabled(false);
        }
        seekBar.setPadding(dp(10), dp(6), dp(10), 0);
        seekBar.setMax(iMax - iMin);
        seekBar.setProgress(iMax2 - iMin);
        seekBar.setProgressDrawable(seekBarTrack());
        seekBar.setThumb(seekBarThumb());
        seekBar.setThumbOffset(dp(9));
        seekBar.setSplitTrack(false);
        seekBar.setEnabled(iMax > iMin);
        seekBar.setContentDescription(str + ", " + iMax2);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.android.support.ThemesData.DZ.DZMenuComponents.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar2, int i5, boolean z) {
                int i6 = iMin + i5;
                textViewValueText.setText(String.valueOf(i6));
                seekBar2.setContentDescription(str + ", " + i6);
                Preferences.changeFeatureInt(str, i, i6);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar2) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar2) {
            }
        });
        linearLayout2.addView(linearLayout3);
        linearLayout2.addView(seekBar);
        addSeparatedRow(linearLayout, linearLayout2);
    }

    private int dp(float f) {
        return Math.round(TypedValue.applyDimension(1, f, this.sharedData.getContext.getResources().getDisplayMetrics()));
    }
}
