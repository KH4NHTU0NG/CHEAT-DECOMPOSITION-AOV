package com.android.support.ThemesData.DZ;

import android.content.res.Resources;
import android.graphics.Color;
import android.util.DisplayMetrics;
import com.android.support.interfaces.IMenuStyle;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public class DZStyle extends IMenuStyle {
    private static final int ACCENT;
    private static final int ACTION;
    private static final int CONTENT_BACKGROUND;
    private static final int MENU_BACKGROUND;
    private static final int OUTLINE;
    private static final int SURFACE;
    private static final int SURFACE_STRONG;
    public static final String TAG = "Mod_Menu";
    private static final int TEXT_PRIMARY;
    private static final int TEXT_SECONDARY;

    static {
        int iRgb = Color.rgb(93, 226, 183);
        ACCENT = iRgb;
        TEXT_PRIMARY = Color.rgb(242, 246, 248);
        TEXT_SECONDARY = Color.rgb(152, 164, 175);
        MENU_BACKGROUND = Color.rgb(20, 24, 29);
        CONTENT_BACKGROUND = Color.rgb(14, 18, 23);
        SURFACE = Color.rgb(27, 33, 40);
        SURFACE_STRONG = Color.rgb(36, 43, 51);
        OUTLINE = Color.rgb(48, 56, 66);
        ACTION = iRgb;
    }

    public int getAccentMutedColor() {
        return Color.rgb(25, 61, 51);
    }

    public int getActionColor() {
        return ACTION;
    }

    public int getActionTextColor() {
        return Color.rgb(9, 29, 23);
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getBtnColor() {
        return SURFACE_STRONG;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getBtnOFF() {
        return SURFACE_STRONG;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getBtnON() {
        return Color.rgb(34, 105, 83);
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getCategoryBG() {
        return SURFACE;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getCheckBoxColor() {
        return ACCENT;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getCollapseColor() {
        return SURFACE;
    }

    public int getDangerColor() {
        return Color.rgb(198, 78, 88);
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public float getIconAlpha() {
        return 0.92f;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getIconSize() {
        return 42;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getMenuBgColor() {
        return MENU_BACKGROUND;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public float getMenuCorner() {
        return 12.0f;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getMenuFeatureBgColor() {
        return CONTENT_BACKGROUND;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getMenuHeight() {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        return Math.max(280, Math.min(440, (int) (Math.max(1.0f, Resources.getSystem().getConfiguration().fontScale) * ((int) (displayMetrics.heightPixels / displayMetrics.density)) * 0.55f)));
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getMenuWidth() {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        int iMin = (int) (Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) / displayMetrics.density);
        float f = Resources.getSystem().getConfiguration().fontScale;
        int iMin2 = (int) (iMin * 0.82f);
        if (f > 1.0f) {
            iMin2 = (int) (Math.min(1.15f, f) * iMin2);
        }
        return Math.max(300, Math.min(380, iMin2));
    }

    public int getNewColor() {
        return ACCENT;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public String getNumberTxtColor() {
        return "#5DE2B7";
    }

    public int getOutlineColor() {
        return OUTLINE;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getPosX() {
        return 0;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getPosY() {
        return 0;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getRadioColor() {
        return ACCENT;
    }

    public int getSecondaryTextColor() {
        return TEXT_SECONDARY;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getSeekBarColor() {
        return ACCENT;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getSeekBarProgressColor() {
        return ACCENT;
    }

    public int getSurfaceColor() {
        return SURFACE;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public String getTag() {
        return TAG;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getTextColor() {
        return ACCENT;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getTextColor2() {
        return TEXT_PRIMARY;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getToggleOFF() {
        return Color.rgb(99, 110, 124);
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getToggleON() {
        return ACCENT;
    }
}
