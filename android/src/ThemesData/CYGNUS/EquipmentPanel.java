package com.android.support.ThemesData.CYGNUS;

import android.R;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.view.DragEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.support.Preferences;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
final class EquipmentPanel extends LinearLayout {
    private static final Bitmap[] ICONS;
    private static final String[] NAMES;
    private static final String VIDEO_GUIDE_URL = "https://youtu.be/Kfir3PriQYg?si=-fOagSi048PJ9It7";
    private int columns;
    private final TextView[] cooldownViews;
    private final GridLayout grid;
    private final Handler handler;
    private final ImageView[] iconViews;
    private boolean masterEnabled;
    private final int[] order;
    private final boolean[] selected;
    private final CygnusStyle style;
    private final Runnable ticker;
    private int tileSize;
    private final FrameLayout[] tileViews;

    public static final class GuidePlayDrawable extends Drawable {
        private int alpha;
        private final Paint paint;

        private GuidePlayDrawable() {
            this.paint = new Paint(1);
            this.alpha = 255;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            float f = getBounds().left;
            float f2 = getBounds().top;
            float fWidth = getBounds().width();
            float fHeight = getBounds().height();
            this.paint.setColor(-2150347);
            this.paint.setAlpha(this.alpha);
            float f3 = fHeight * 0.23f;
            canvas.drawRoundRect(f, f2, f + fWidth, f2 + fHeight, f3, f3, this.paint);
            this.paint.setColor(-1);
            this.paint.setAlpha(this.alpha);
            Path path = new Path();
            float f4 = (0.38f * fWidth) + f;
            path.moveTo(f4, (0.25f * fHeight) + f2);
            path.lineTo(f4, (0.75f * fHeight) + f2);
            path.lineTo((fWidth * 0.72f) + f, (fHeight * 0.5f) + f2);
            path.close();
            canvas.drawPath(path, this.paint);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -3;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int i) {
            this.alpha = i;
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter) {
            this.paint.setColorFilter(colorFilter);
            invalidateSelf();
        }
    }

    static {
        String[] strArr = {"Giáp Hồi Sinh", "Băng Sương", "Liềm Đoạt Mệnh", "Thương Khung Kiếm", "Thuẫn Nham Thạch", "Hercules", "Cung Tà Ma", "Xạ Nhật Cung", "Thề Ước Carano", "Vô Ảnh Tiễn", "Giáp Thống Khổ"};
        NAMES = strArr;
        ICONS = new Bitmap[strArr.length];
    }

    private EquipmentPanel(Context context, CygnusStyle cygnusStyle) {
        super(context);
        String[] strArr = NAMES;
        this.order = new int[strArr.length];
        this.selected = new boolean[strArr.length];
        this.cooldownViews = new TextView[strArr.length];
        this.iconViews = new ImageView[strArr.length];
        this.tileViews = new FrameLayout[strArr.length];
        this.handler = new Handler(Looper.getMainLooper());
        this.columns = 6;
        this.ticker = new Runnable() { // from class: com.android.support.ThemesData.CYGNUS.EquipmentPanel.1
            @Override // java.lang.Runnable
            public void run() {
                if (EquipmentPanel.this.isShown()) {
                    try {
                        JSONArray jSONArrayOptJSONArray = new JSONObject(EquipmentPanel.nativeSnapshot()).optJSONArray("cooldowns");
                        for (int i = 0; i < EquipmentPanel.this.cooldownViews.length; i++) {
                            int iOptInt = jSONArrayOptJSONArray == null ? 0 : jSONArrayOptJSONArray.optInt(i, 0);
                            EquipmentPanel.this.cooldownViews[i].setText((!EquipmentPanel.this.selected[i] || iOptInt <= 0) ? "" : iOptInt + "s");
                            EquipmentPanel.this.iconViews[i].setAlpha(EquipmentPanel.this.selected[i] ? iOptInt > 0 ? 0.5f : 1.0f : 0.28f);
                        }
                    } catch (Exception | LinkageError unused) {
                    }
                }
                EquipmentPanel.this.handler.postDelayed(this, 750L);
            }
        };
        this.style = cygnusStyle;
        setOrientation(1);
        setPadding(dp(10.0f), dp(12.0f), dp(10.0f), dp(12.0f));
        setBackground(panelBackground());
        TextView textViewText = text("PHẦN BÁN ĐỒ NHANH", 14.0f, cygnusStyle.getTextColor2());
        textViewText.setTypeface(Typeface.DEFAULT, 1);
        addView(textViewText);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        CheckBox checkBoxOption = option("Mua Bán Đồ Nhanh");
        boolean zLoadPrefBool = Preferences.loadPrefBool("Mua Bán Đồ Nhanh", 120, false);
        this.masterEnabled = zLoadPrefBool;
        checkBoxOption.setChecked(zLoadPrefBool);
        CheckBox checkBoxOption2 = option("6 Đồ Mới Kích Hoạt");
        checkBoxOption2.setChecked(Preferences.loadPrefBool("6 Đồ Lớn Mới Kích Hoạt", 121, true));
        linearLayout.addView(checkBoxOption, new LinearLayout.LayoutParams(0, dp(48.0f), 1.0f));
        linearLayout.addView(checkBoxOption2, new LinearLayout.LayoutParams(0, dp(48.0f), 1.0f));
        addView(linearLayout);
        checkBoxOption.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.android.support.ThemesData.CYGNUS.y
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                this.a.lambda$new$0(compoundButton, z);
            }
        });
        checkBoxOption2.setOnCheckedChangeListener(new z());
        boolean[] zArr = new boolean[strArr.length];
        for (int i = 0; i < this.order.length; i++) {
            int i2 = Preferences.loadPref ? Preferences.with(context).readInt(String.valueOf(i + 150), i) : i;
            if (i2 < 0 || i2 >= this.order.length || zArr[i2]) {
                i2 = 0;
                while (zArr[i2]) {
                    i2++;
                }
            }
            this.order[i] = i2;
            zArr[i2] = true;
        }
        int i3 = 0;
        while (true) {
            boolean[] zArr2 = this.selected;
            if (i3 >= zArr2.length) {
                publishOrder(false);
                GridLayout gridLayout = new GridLayout(context);
                this.grid = gridLayout;
                gridLayout.setColumnCount(this.columns);
                gridLayout.setAlignmentMode(0);
                gridLayout.setUseDefaultMargins(false);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                layoutParams.topMargin = dp(8.0f);
                addView(gridLayout, layoutParams);
                rebuildGrid();
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
                layoutParams2.topMargin = dp(16.0f);
                layoutParams2.bottomMargin = dp(4.0f);
                addView(videoGuide(), layoutParams2);
                gridLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.android.support.ThemesData.CYGNUS.A
                    @Override // android.view.View.OnLayoutChangeListener
                    public final void onLayoutChange(View view, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) {
                        this.a.lambda$new$2(view, i4, i5, i6, i7, i8, i9, i10, i11);
                    }
                });
                return;
            }
            zArr2[i3] = Preferences.loadPrefBool(NAMES[i3], i3 + 122, true);
            i3++;
        }
    }

    public static void build(LinearLayout linearLayout, CygnusStyle cygnusStyle) {
        linearLayout.addView(new EquipmentPanel(linearLayout.getContext(), cygnusStyle), new LinearLayout.LayoutParams(-1, -2));
    }

    private int dp(float f) {
        return Math.round(f * getResources().getDisplayMetrics().density);
    }

    private Bitmap icon(int i) {
        Bitmap[] bitmapArr = ICONS;
        if (bitmapArr[i] == null) {
            byte[] bArrDecode = Base64.decode(EquipmentIcons.encoded(i), 0);
            bitmapArr[i] = BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length);
        }
        return bitmapArr[i];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(CompoundButton compoundButton, boolean z) {
        this.masterEnabled = z;
        Preferences.changeFeatureBool("Mua Bán Đồ Nhanh", 120, z);
        updateTileAppearance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$2(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9;
        int i10 = i3 - i;
        if (i10 <= 0) {
            return;
        }
        if (i10 >= dp(420.0f)) {
            i9 = 6;
        } else {
            i9 = i10 >= dp(300.0f) ? 4 : 3;
        }
        int iMin = Math.min(dp(88.0f), (i10 - ((i9 - 1) * dp(8.0f))) / i9);
        if (i9 == this.columns && iMin == this.tileSize) {
            return;
        }
        this.columns = i9;
        this.tileSize = Math.max(dp(44.0f), iMin);
        this.grid.post(new t(this, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$rebuildGrid$4(int i, View view) {
        boolean[] zArr = this.selected;
        boolean z = !zArr[i];
        zArr[i] = z;
        Preferences.changeFeatureBool(NAMES[i], i + 122, z);
        updateTileAppearance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$rebuildGrid$5(int i, FrameLayout frameLayout, View view) {
        ClipData clipDataNewPlainText = ClipData.newPlainText("equipment-slot", String.valueOf(i));
        if (Build.VERSION.SDK_INT >= 24) {
            frameLayout.startDragAndDrop(clipDataNewPlainText, new View.DragShadowBuilder(frameLayout), Integer.valueOf(i), 0);
            return true;
        }
        frameLayout.startDrag(clipDataNewPlainText, new View.DragShadowBuilder(frameLayout), Integer.valueOf(i), 0);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$rebuildGrid$6(int i, View view, DragEvent dragEvent) {
        int action = dragEvent.getAction();
        if (action == 1) {
            return dragEvent.getLocalState() instanceof Integer;
        }
        if (action != 3) {
            if (action != 4) {
                if (action == 5) {
                    view.setScaleX(1.04f);
                    view.setScaleY(1.04f);
                    return true;
                }
                if (action != 6) {
                    return true;
                }
            }
            view.setScaleX(1.0f);
            view.setScaleY(1.0f);
            return true;
        }
        int iIntValue = ((Integer) dragEvent.getLocalState()).intValue();
        if (iIntValue < 0) {
            return false;
        }
        int[] iArr = this.order;
        if (iIntValue >= iArr.length) {
            return false;
        }
        int i2 = iArr[i];
        iArr[i] = iArr[iIntValue];
        iArr[iIntValue] = i2;
        publishOrder(true);
        this.grid.post(new t(this, 0));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$videoGuide$3(View view) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(VIDEO_GUIDE_URL));
        if (!(getContext() instanceof Activity)) {
            intent.addFlags(268435456);
        }
        try {
            getContext().startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(getContext(), "Không tìm thấy ứng dụng mở YouTube hoặc trình duyệt", 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native String nativeSnapshot();

    private CheckBox option(String str) {
        CheckBox checkBox = new CheckBox(getContext());
        checkBox.setText(str);
        checkBox.setTextColor(this.style.getTextColor2());
        checkBox.setTextSize(2, 11.0f);
        checkBox.setTypeface(Typeface.DEFAULT, 1);
        checkBox.setButtonTintList(new ColorStateList(new int[][]{new int[]{R.attr.state_checked}, new int[0]}, new int[]{this.style.getAccentColor(), this.style.getSecondaryTextColor()}));
        checkBox.setPadding(0, 0, dp(2.0f), 0);
        return checkBox;
    }

    private GradientDrawable panelBackground() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(this.style.getSurfaceColor());
        gradientDrawable.setCornerRadius(dp(10.0f));
        gradientDrawable.setStroke(dp(1.0f), this.style.getAccentBorderColor());
        return gradientDrawable;
    }

    private void publishOrder(boolean z) {
        for (int i = 0; i < this.order.length; i++) {
            if (z) {
                Preferences.changeFeatureInt("Ưu Tiên " + (i + 1), i + 150, this.order[i]);
            } else {
                Preferences.Changes(getContext(), i + 150, "Ưu Tiên " + (i + 1), this.order[i], 0L, false, null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void rebuildGrid() {
        this.grid.removeAllViews();
        this.grid.setColumnCount(this.columns);
        final int i = 0;
        while (true) {
            int[] iArr = this.order;
            if (i >= iArr.length) {
                updateTileAppearance();
                return;
            }
            final int i2 = iArr[i];
            final FrameLayout frameLayout = new FrameLayout(getContext());
            this.tileViews[i2] = frameLayout;
            frameLayout.setPadding(dp(7.0f), dp(7.0f), dp(7.0f), dp(7.0f));
            frameLayout.setClickable(true);
            frameLayout.setFocusable(true);
            StringBuilder sb = new StringBuilder();
            sb.append(NAMES[i2]);
            sb.append(", ưu tiên ");
            int i3 = i + 1;
            sb.append(i3);
            frameLayout.setContentDescription(sb.toString());
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(GridLayout.spec(i / this.columns), GridLayout.spec(i % this.columns));
            int iDp = this.tileSize;
            if (iDp <= 0) {
                iDp = dp(70.0f);
            }
            layoutParams.width = iDp;
            layoutParams.height = iDp;
            int i4 = this.columns;
            layoutParams.setMargins(0, 0, i % i4 == i4 - 1 ? 0 : dp(8.0f), dp(8.0f));
            this.grid.addView(frameLayout, layoutParams);
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setImageBitmap(icon(i2));
            imageView.setImportantForAccessibility(2);
            this.iconViews[i2] = imageView;
            frameLayout.addView(imageView, new FrameLayout.LayoutParams(-1, -1));
            TextView textViewText = text(String.valueOf(i3), 9.0f, -1);
            textViewText.setGravity(17);
            Typeface typeface = Typeface.DEFAULT;
            textViewText.setTypeface(typeface, 1);
            textViewText.setBackgroundColor(-1342177280);
            frameLayout.addView(textViewText, new FrameLayout.LayoutParams(dp(18.0f), dp(18.0f), 8388659));
            TextView textViewText2 = text("", 18.0f, -1);
            textViewText2.setGravity(17);
            textViewText2.setTypeface(typeface, 1);
            textViewText2.setShadowLayer(dp(3.0f), 0.0f, dp(1.0f), -16777216);
            this.cooldownViews[i2] = textViewText2;
            frameLayout.addView(textViewText2, new FrameLayout.LayoutParams(-1, -1));
            frameLayout.setOnClickListener(new View.OnClickListener() { // from class: com.android.support.ThemesData.CYGNUS.v
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.a.lambda$rebuildGrid$4(i2, view);
                }
            });
            frameLayout.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.android.support.ThemesData.CYGNUS.w
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    return EquipmentPanel.lambda$rebuildGrid$5(i, frameLayout, view);
                }
            });
            frameLayout.setOnDragListener(new View.OnDragListener() { // from class: com.android.support.ThemesData.CYGNUS.x
                @Override // android.view.View.OnDragListener
                public final boolean onDrag(View view, DragEvent dragEvent) {
                    return this.a.lambda$rebuildGrid$6(i, view, dragEvent);
                }
            });
            i = i3;
        }
    }

    private TextView text(String str, float f, int i) {
        TextView textView = new TextView(getContext());
        textView.setText(str);
        textView.setTextSize(2, f);
        textView.setTextColor(i);
        return textView;
    }

    private void updateTileAppearance() {
        for (int i = 0; i < this.selected.length; i++) {
            FrameLayout frameLayout = this.tileViews[i];
            if (frameLayout != null) {
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setColor(this.selected[i] ? -13616312 : -15197148);
                gradientDrawable.setCornerRadius(dp(10.0f));
                gradientDrawable.setStroke(dp(this.selected[i] ? 2.0f : 1.0f), this.selected[i] ? this.style.getAccentColor() : this.style.getOutlineColor());
                frameLayout.setBackground(gradientDrawable);
                this.iconViews[i].setAlpha(this.selected[i] ? 1.0f : 0.28f);
                if (Build.VERSION.SDK_INT >= 26) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(NAMES[i]);
                    sb.append(this.selected[i] ? " • đang chọn" : " • đã tắt");
                    frameLayout.setTooltipText(sb.toString());
                }
            }
        }
    }

    private View videoGuide() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        linearLayout.setPadding(dp(12.0f), dp(12.0f), dp(12.0f), dp(12.0f));
        linearLayout.setMinimumHeight(dp(76.0f));
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(-14080737);
        gradientDrawable.setCornerRadius(dp(12.0f));
        gradientDrawable.setStroke(dp(1.0f), -7705786);
        linearLayout.setBackground(gradientDrawable);
        linearLayout.setClickable(true);
        linearLayout.setFocusable(true);
        linearLayout.setContentDescription("Video hướng dẫn sử dụng chức năng bán đồ. Chạm để xem trên YouTube.");
        ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(new GuidePlayDrawable());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dp(44.0f), dp(40.0f));
        layoutParams.rightMargin = dp(12.0f);
        linearLayout.addView(imageView, layoutParams);
        LinearLayout linearLayout2 = new LinearLayout(getContext());
        linearLayout2.setOrientation(1);
        TextView textViewText = text("Video Hướng Dẫn Sử Dụng Chức Năng Bán Đồ", 12.5f, -858413);
        textViewText.setTypeface(Typeface.DEFAULT, 1);
        linearLayout2.addView(textViewText);
        TextView textViewText2 = text("Chạm để xem trên YouTube", 11.0f, -3818841);
        textViewText2.setPadding(0, dp(5.0f), 0, 0);
        linearLayout2.addView(textViewText2);
        linearLayout.addView(linearLayout2, new LinearLayout.LayoutParams(0, -2, 1.0f));
        TextView textViewText3 = text("›", 28.0f, -1983358);
        textViewText3.setGravity(17);
        linearLayout.addView(textViewText3, new LinearLayout.LayoutParams(dp(22.0f), dp(40.0f)));
        linearLayout.setOnClickListener(new u(this, 0));
        return linearLayout;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.handler.post(this.ticker);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        this.handler.removeCallbacks(this.ticker);
        super.onDetachedFromWindow();
    }
}
