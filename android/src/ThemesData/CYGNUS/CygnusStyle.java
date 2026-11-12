package com.android.support.ThemesData.CYGNUS;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import com.android.support.interfaces.IMenuStyle;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public class CygnusStyle extends IMenuStyle {
    static final String DEFAULT_ACCENT = "#FF32E0FF";
    static final float DEFAULT_ICON_A = 0.95f;
    static final int DEFAULT_ICON_SZ = 44;
    static final String KEY_ACCENT = "accent_hex";
    static final String KEY_ICON_A = "icon_alpha";
    static final String KEY_ICON_SZ = "icon_size_dp";
    static final String PREFS_FILE = "cygnus_style";
    public static final String TAG = "Cygnus_Menu";
    private int accent;
    private int accentBorder;
    private int accentSoft;
    private float iconAlpha;
    private int iconSize;
    private static final int TEXT_PRIMARY = Color.parseColor("#FFFFFFFF");
    private static final int TEXT_SECONDARY = Color.parseColor("#FF96A2AE");
    private static final int TEXT_DIM = Color.parseColor("#FF6C7885");
    private static final int MENU_BACKGROUND = Color.parseColor("#F00B0D12");
    private static final int SURFACE = Color.parseColor("#CC0B0D12");
    private static final int SURFACE_STRONG = Color.parseColor("#22FFFFFF");
    private static final int OUTLINE = Color.parseColor("#33FFFFFF");
    private static final int OUTLINE_SOFT = Color.parseColor("#1AFFFFFF");
    private static final int SCRIM = Color.parseColor("#73000000");
    private static final int DANGER = Color.parseColor("#FFFF5D6E");

    public CygnusStyle() {
        int color = Color.parseColor(DEFAULT_ACCENT);
        this.accent = color;
        this.iconSize = DEFAULT_ICON_SZ;
        this.iconAlpha = DEFAULT_ICON_A;
        recomputeAccent(color);
    }

    private static String colorHex(int i) {
        return String.format("#%06X", Integer.valueOf(i & 16777215));
    }

    private void recomputeAccent(int i) {
        int i2 = i | (-16777216);
        this.accent = i2;
        int iRed = Color.red(i2);
        int iGreen = Color.green(this.accent);
        int iBlue = Color.blue(this.accent);
        this.accentSoft = Color.argb(26, iRed, iGreen, iBlue);
        this.accentBorder = Color.argb(128, iRed, iGreen, iBlue);
    }

    public int getAccentBorderColor() {
        return this.accentBorder;
    }

    public int getAccentColor() {
        return this.accent;
    }

    public int getAccentSoftColor() {
        return this.accentSoft;
    }

    public int getActionColor() {
        return this.accent;
    }

    public int getActionTextColor() {
        return Color.parseColor("#FF050810");
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
        return this.accentSoft;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getCategoryBG() {
        return SURFACE;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getCheckBoxColor() {
        return this.accent;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getCollapseColor() {
        return SURFACE;
    }

    public int getDangerColor() {
        return DANGER;
    }

    public int getDimTextColor() {
        return TEXT_DIM;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public float getIconAlpha() {
        return this.iconAlpha;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getIconSize() {
        return this.iconSize;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getMenuBgColor() {
        return MENU_BACKGROUND;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public float getMenuCorner() {
        return 16.0f;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getMenuFeatureBgColor() {
        return SURFACE;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getMenuHeight() {
        return 420;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getMenuWidth() {
        return 720;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public String getNumberTxtColor() {
        return colorHex(this.accent);
    }

    public int getOutlineColor() {
        return OUTLINE;
    }

    public int getOutlineSoftColor() {
        return OUTLINE_SOFT;
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
        return this.accent;
    }

    public int getScrimColor() {
        return SCRIM;
    }

    public int getSecondaryTextColor() {
        return TEXT_SECONDARY;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getSeekBarColor() {
        return this.accent;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getSeekBarProgressColor() {
        return this.accent;
    }

    public int getSurfaceColor() {
        return SURFACE;
    }

    public int getSurfaceStrongColor() {
        return SURFACE_STRONG;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public String getTag() {
        return TAG;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getTextColor() {
        return this.accent;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getTextColor2() {
        return TEXT_PRIMARY;
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getToggleOFF() {
        return Color.parseColor("#55FFFFFF");
    }

    @Override // com.android.support.interfaces.IMenuStyle
    public int getToggleON() {
        return this.accent;
    }

    public void loadFromPrefs(Context context) {
        if (context == null) {
            return;
        }
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_FILE, 0);
            try {
                recomputeAccent(Color.parseColor(sharedPreferences.getString(KEY_ACCENT, DEFAULT_ACCENT)));
            } catch (Throwable unused) {
                recomputeAccent(Color.parseColor(DEFAULT_ACCENT));
            }
            this.iconSize = sharedPreferences.getInt(KEY_ICON_SZ, DEFAULT_ICON_SZ);
            this.iconAlpha = sharedPreferences.getFloat(KEY_ICON_A, DEFAULT_ICON_A);
        } catch (Throwable unused2) {
        }
    }

    public void writeAccent(Context context, int i) {
        recomputeAccent(i);
        try {
            context.getSharedPreferences(PREFS_FILE, 0).edit().putString(KEY_ACCENT, String.format("#FF%06X", Integer.valueOf(i & 16777215))).apply();
        } catch (Throwable unused) {
        }
    }

    public void writeIconAlpha(Context context, float f) {
        this.iconAlpha = Math.max(0.0f, Math.min(1.0f, f));
        try {
            context.getSharedPreferences(PREFS_FILE, 0).edit().putFloat(KEY_ICON_A, this.iconAlpha).apply();
        } catch (Throwable unused) {
        }
    }

    public void writeIconSize(Context context, int i) {
        this.iconSize = Math.max(24, Math.min(96, i));
        try {
            context.getSharedPreferences(PREFS_FILE, 0).edit().putInt(KEY_ICON_SZ, this.iconSize).apply();
        } catch (Throwable unused) {
        }
    }
}
