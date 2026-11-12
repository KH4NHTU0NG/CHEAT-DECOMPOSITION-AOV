package com.android.support.ThemesData.CYGNUS;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public final class CygnusIconDrawable extends Drawable {
    public static final int AUTO_BANGSUONG = 27;
    public static final int AUTO_BOCPHA = 25;
    public static final int AUTO_TRUNGTRI = 26;
    public static final int BELL = 10;
    public static final int BOLT = 13;
    public static final int CHECK = 21;
    public static final int CHEVRON = 17;
    public static final int CLOCK = 2;
    public static final int CLOSE = 16;
    public static final int EYE = 1;
    public static final int EYE_OFF = 28;
    public static final int FLAME = 4;
    public static final int GAVEL = 5;
    public static final int GEAR = 15;
    public static final int GRID = 12;
    public static final int HEART = 7;
    public static final int LOGOUT = 18;
    public static final int MAP = 0;
    public static final int MUSIC = 29;
    public static final int PASTE = 20;
    public static final int PLAY = 24;
    public static final int SEARCH = 19;
    public static final int SHIELD = 22;
    public static final int SHIRT = 9;
    public static final int SNOW = 6;
    public static final int SPARKLE = 14;
    public static final int SQUARE = 11;
    public static final int STAR = 23;
    public static final int TARGET = 8;
    public static final int ZAP = 3;
    private int color;
    private final Paint fill;
    private final int kind;
    private final Paint stroke;
    private float strokeWidth;

    public CygnusIconDrawable(int i, int i2) {
        Paint paint = new Paint(1);
        this.stroke = paint;
        Paint paint2 = new Paint(1);
        this.fill = paint2;
        this.strokeWidth = 1.7f;
        this.kind = i;
        this.color = i2;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint2.setStyle(Paint.Style.FILL);
    }

    private void drawAutoBangSuong(Canvas canvas) {
        canvas.drawCircle(12.0f, 9.0f, 6.0f, this.stroke);
        canvas.drawLine(12.0f, 4.0f, 12.0f, 14.0f, this.stroke);
        canvas.drawLine(7.0f, 9.0f, 17.0f, 9.0f, this.stroke);
        canvas.drawLine(8.3f, 5.3f, 15.7f, 12.7f, this.stroke);
        canvas.drawLine(15.7f, 5.3f, 8.3f, 12.7f, this.stroke);
        canvas.drawCircle(4.0f, 4.5f, 0.8f, this.fill);
        canvas.drawCircle(20.0f, 5.0f, 0.7f, this.fill);
        canvas.drawRoundRect(new RectF(6.0f, 17.5f, 18.0f, 20.5f), 1.5f, 1.5f, this.stroke);
        canvas.drawLine(8.0f, 20.5f, 7.0f, 22.0f, this.stroke);
        canvas.drawLine(16.0f, 20.5f, 17.0f, 22.0f, this.stroke);
    }

    private void drawAutoBocPha(Canvas canvas) {
        Path path = new Path();
        path.moveTo(12.0f, 2.0f);
        path.lineTo(10.6f, 8.0f);
        path.lineTo(13.4f, 8.0f);
        path.close();
        path.moveTo(22.0f, 12.0f);
        path.lineTo(16.0f, 10.6f);
        path.lineTo(16.0f, 13.4f);
        path.close();
        path.moveTo(12.0f, 22.0f);
        path.lineTo(10.6f, 16.0f);
        path.lineTo(13.4f, 16.0f);
        path.close();
        path.moveTo(2.0f, 12.0f);
        path.lineTo(8.0f, 10.6f);
        path.lineTo(8.0f, 13.4f);
        path.close();
        canvas.drawPath(path, this.fill);
        canvas.drawLine(5.5f, 5.5f, 9.0f, 9.0f, this.stroke);
        canvas.drawLine(18.5f, 5.5f, 15.0f, 9.0f, this.stroke);
        canvas.drawLine(18.5f, 18.5f, 15.0f, 15.0f, this.stroke);
        canvas.drawLine(5.5f, 18.5f, 9.0f, 15.0f, this.stroke);
        canvas.drawCircle(12.0f, 12.0f, 2.4f, this.stroke);
        canvas.drawCircle(12.0f, 12.0f, 1.2f, this.fill);
    }

    private void drawAutoTrungTri(Canvas canvas) {
        Path path = new Path();
        path.moveTo(13.0f, 2.0f);
        path.lineTo(6.0f, 12.0f);
        path.lineTo(11.0f, 12.0f);
        path.lineTo(9.0f, 20.0f);
        canvas.drawPath(path, this.stroke);
        canvas.drawLine(3.0f, 22.0f, 21.0f, 22.0f, this.stroke);
        Path path2 = new Path();
        path2.moveTo(18.0f, 4.0f);
        path2.lineTo(16.0f, 8.0f);
        canvas.drawPath(path2, this.stroke);
        Path path3 = new Path();
        path3.moveTo(4.0f, 6.0f);
        path3.lineTo(6.5f, 9.0f);
        canvas.drawPath(path3, this.stroke);
        canvas.drawCircle(9.0f, 21.0f, 0.8f, this.fill);
    }

    private void drawBell(Canvas canvas) {
        Path path = new Path();
        path.moveTo(5.0f, 17.0f);
        path.lineTo(6.0f, 17.0f);
        path.cubicTo(6.0f, 17.0f, 7.0f, 15.0f, 7.0f, 11.0f);
        path.cubicTo(7.0f, 8.24f, 9.24f, 6.0f, 12.0f, 6.0f);
        path.cubicTo(14.76f, 6.0f, 17.0f, 8.24f, 17.0f, 11.0f);
        path.cubicTo(17.0f, 15.0f, 18.0f, 17.0f, 18.0f, 17.0f);
        path.lineTo(19.0f, 17.0f);
        path.close();
        canvas.drawPath(path, this.stroke);
        Path path2 = new Path();
        path2.moveTo(10.0f, 20.0f);
        path2.cubicTo(10.0f, 21.1f, 10.9f, 22.0f, 12.0f, 22.0f);
        path2.cubicTo(13.1f, 22.0f, 14.0f, 21.1f, 14.0f, 20.0f);
        canvas.drawPath(path2, this.stroke);
    }

    private void drawCheck(Canvas canvas) {
        canvas.drawLine(4.0f, 12.0f, 10.0f, 18.0f, this.stroke);
        canvas.drawLine(10.0f, 18.0f, 20.0f, 6.0f, this.stroke);
    }

    private void drawChevron(Canvas canvas) {
        canvas.drawLine(6.0f, 9.0f, 12.0f, 15.0f, this.stroke);
        canvas.drawLine(12.0f, 15.0f, 18.0f, 9.0f, this.stroke);
    }

    private void drawClock(Canvas canvas) {
        canvas.drawCircle(12.0f, 12.0f, 9.0f, this.stroke);
        canvas.drawLine(12.0f, 7.0f, 12.0f, 12.0f, this.stroke);
        canvas.drawLine(12.0f, 12.0f, 15.0f, 14.0f, this.stroke);
    }

    private void drawClose(Canvas canvas) {
        canvas.drawLine(6.0f, 6.0f, 18.0f, 18.0f, this.stroke);
        canvas.drawLine(18.0f, 6.0f, 6.0f, 18.0f, this.stroke);
    }

    private void drawEye(Canvas canvas) {
        Path path = new Path();
        path.moveTo(2.0f, 12.0f);
        path.cubicTo(2.0f, 12.0f, 5.5f, 5.0f, 12.0f, 5.0f);
        path.cubicTo(18.5f, 5.0f, 22.0f, 12.0f, 22.0f, 12.0f);
        path.cubicTo(22.0f, 12.0f, 18.5f, 19.0f, 12.0f, 19.0f);
        path.cubicTo(5.5f, 19.0f, 2.0f, 12.0f, 2.0f, 12.0f);
        path.close();
        canvas.drawPath(path, this.stroke);
        canvas.drawCircle(12.0f, 12.0f, 3.0f, this.stroke);
    }

    private void drawEyeOff(Canvas canvas) {
        Path path = new Path();
        path.moveTo(2.0f, 12.0f);
        path.cubicTo(2.0f, 12.0f, 5.5f, 5.0f, 12.0f, 5.0f);
        path.cubicTo(18.5f, 5.0f, 22.0f, 12.0f, 22.0f, 12.0f);
        path.cubicTo(22.0f, 12.0f, 18.5f, 19.0f, 12.0f, 19.0f);
        path.cubicTo(5.5f, 19.0f, 2.0f, 12.0f, 2.0f, 12.0f);
        path.close();
        canvas.drawPath(path, this.stroke);
        canvas.drawCircle(12.0f, 12.0f, 3.0f, this.stroke);
        canvas.drawLine(4.0f, 4.0f, 20.0f, 20.0f, this.stroke);
    }

    private void drawFlame(Canvas canvas) {
        Path path = new Path();
        path.moveTo(12.0f, 2.0f);
        path.cubicTo(12.0f, 6.0f, 8.0f, 7.0f, 8.0f, 11.0f);
        path.cubicTo(8.0f, 13.2f, 9.8f, 15.0f, 12.0f, 15.0f);
        path.cubicTo(14.2f, 15.0f, 16.0f, 13.2f, 16.0f, 11.0f);
        path.cubicTo(16.0f, 9.0f, 14.0f, 8.0f, 14.0f, 6.0f);
        canvas.drawPath(path, this.stroke);
        Path path2 = new Path();
        path2.moveTo(12.0f, 10.0f);
        path2.cubicTo(12.0f, 12.0f, 10.0f, 12.0f, 10.0f, 14.0f);
        path2.cubicTo(10.0f, 15.1f, 10.9f, 16.0f, 12.0f, 16.0f);
        path2.cubicTo(13.1f, 16.0f, 14.0f, 15.1f, 14.0f, 14.0f);
        canvas.drawPath(path2, this.stroke);
    }

    private void drawGavel(Canvas canvas) {
        Path path = new Path();
        path.moveTo(14.0f, 2.0f);
        path.lineTo(20.0f, 8.0f);
        path.lineTo(17.0f, 11.0f);
        path.lineTo(11.0f, 5.0f);
        path.close();
        canvas.drawPath(path, this.stroke);
        Path path2 = new Path();
        path2.moveTo(11.0f, 5.0f);
        path2.lineTo(5.0f, 11.0f);
        path2.lineTo(8.0f, 14.0f);
        path2.lineTo(14.0f, 8.0f);
        path2.close();
        canvas.drawPath(path2, this.stroke);
        canvas.drawLine(3.0f, 21.0f, 14.0f, 21.0f, this.stroke);
        canvas.drawLine(6.0f, 15.0f, 12.0f, 21.0f, this.stroke);
    }

    private void drawGear(Canvas canvas) {
        canvas.drawCircle(12.0f, 12.0f, 3.0f, this.stroke);
        for (int i = 0; i < 8; i++) {
            double d = (((double) i) * 3.141592653589793d) / 4.0d;
            canvas.drawLine(((float) (Math.cos(d) * 6.0d)) + 12.0f, ((float) (Math.sin(d) * 6.0d)) + 12.0f, ((float) (Math.cos(d) * 9.5d)) + 12.0f, ((float) (Math.sin(d) * 9.5d)) + 12.0f, this.stroke);
        }
    }

    private void drawGrid(Canvas canvas) {
        canvas.drawRoundRect(new RectF(3.0f, 3.0f, 10.0f, 10.0f), 1.5f, 1.5f, this.stroke);
        canvas.drawRoundRect(new RectF(14.0f, 3.0f, 21.0f, 10.0f), 1.5f, 1.5f, this.stroke);
        canvas.drawRoundRect(new RectF(3.0f, 14.0f, 10.0f, 21.0f), 1.5f, 1.5f, this.stroke);
        canvas.drawRoundRect(new RectF(14.0f, 14.0f, 21.0f, 21.0f), 1.5f, 1.5f, this.stroke);
    }

    private void drawHeart(Canvas canvas) {
        Path path = new Path();
        path.moveTo(12.0f, 21.0f);
        path.cubicTo(12.0f, 21.0f, 3.0f, 15.0f, 3.0f, 9.0f);
        path.cubicTo(3.0f, 6.24f, 5.24f, 4.0f, 8.0f, 4.0f);
        path.cubicTo(9.8f, 4.0f, 11.4f, 5.0f, 12.0f, 6.5f);
        path.cubicTo(12.6f, 5.0f, 14.2f, 4.0f, 16.0f, 4.0f);
        path.cubicTo(18.76f, 4.0f, 21.0f, 6.24f, 21.0f, 9.0f);
        path.cubicTo(21.0f, 15.0f, 12.0f, 21.0f, 12.0f, 21.0f);
        path.close();
        canvas.drawPath(path, this.stroke);
    }

    private void drawLogout(Canvas canvas) {
        Path path = new Path();
        path.moveTo(9.0f, 21.0f);
        path.lineTo(5.0f, 21.0f);
        path.cubicTo(3.9f, 21.0f, 3.0f, 20.1f, 3.0f, 19.0f);
        path.lineTo(3.0f, 5.0f);
        path.cubicTo(3.0f, 3.9f, 3.9f, 3.0f, 5.0f, 3.0f);
        path.lineTo(9.0f, 3.0f);
        canvas.drawPath(path, this.stroke);
        canvas.drawLine(16.0f, 17.0f, 21.0f, 12.0f, this.stroke);
        canvas.drawLine(21.0f, 12.0f, 16.0f, 7.0f, this.stroke);
        canvas.drawLine(21.0f, 12.0f, 9.0f, 12.0f, this.stroke);
    }

    private void drawMap(Canvas canvas) {
        Path path = new Path();
        path.moveTo(9.0f, 3.0f);
        path.lineTo(3.0f, 6.0f);
        path.lineTo(3.0f, 21.0f);
        path.lineTo(9.0f, 18.0f);
        path.lineTo(15.0f, 21.0f);
        path.lineTo(21.0f, 18.0f);
        path.lineTo(21.0f, 3.0f);
        path.lineTo(15.0f, 6.0f);
        path.close();
        canvas.drawPath(path, this.stroke);
        canvas.drawLine(9.0f, 3.0f, 9.0f, 18.0f, this.stroke);
        canvas.drawLine(15.0f, 6.0f, 15.0f, 21.0f, this.stroke);
    }

    private void drawPaste(Canvas canvas) {
        canvas.drawRoundRect(new RectF(8.0f, 4.0f, 20.0f, 20.0f), 2.0f, 2.0f, this.stroke);
        Path path = new Path();
        path.moveTo(16.0f, 4.0f);
        path.lineTo(16.0f, 2.0f);
        path.lineTo(10.0f, 2.0f);
        path.cubicTo(8.9f, 2.0f, 8.0f, 2.9f, 8.0f, 4.0f);
        path.lineTo(8.0f, 18.0f);
        path.cubicTo(8.0f, 19.1f, 8.9f, 20.0f, 10.0f, 20.0f);
        path.lineTo(12.0f, 20.0f);
        canvas.drawPath(path, this.stroke);
    }

    private void drawPlay(Canvas canvas) {
        Path path = new Path();
        path.moveTo(6.0f, 4.0f);
        path.lineTo(20.0f, 12.0f);
        path.lineTo(6.0f, 20.0f);
        path.close();
        canvas.drawPath(path, this.fill);
    }

    private void drawSearch(Canvas canvas) {
        canvas.drawCircle(10.5f, 10.5f, 7.0f, this.stroke);
        canvas.drawLine(16.0f, 16.0f, 21.0f, 21.0f, this.stroke);
    }

    private void drawShield(Canvas canvas) {
        Path path = new Path();
        path.moveTo(12.0f, 22.0f);
        path.cubicTo(12.0f, 22.0f, 20.0f, 18.0f, 20.0f, 11.0f);
        path.lineTo(20.0f, 5.0f);
        path.lineTo(12.0f, 2.0f);
        path.lineTo(4.0f, 5.0f);
        path.lineTo(4.0f, 11.0f);
        path.cubicTo(4.0f, 18.0f, 12.0f, 22.0f, 12.0f, 22.0f);
        path.close();
        canvas.drawPath(path, this.stroke);
    }

    private void drawShirt(Canvas canvas) {
        Path path = new Path();
        path.moveTo(6.0f, 3.0f);
        path.lineTo(9.0f, 6.0f);
        path.lineTo(12.0f, 7.0f);
        path.lineTo(15.0f, 6.0f);
        path.lineTo(18.0f, 3.0f);
        path.lineTo(21.0f, 6.0f);
        path.lineTo(18.0f, 9.0f);
        path.lineTo(18.0f, 21.0f);
        path.lineTo(6.0f, 21.0f);
        path.lineTo(6.0f, 9.0f);
        path.lineTo(3.0f, 6.0f);
        path.close();
        canvas.drawPath(path, this.stroke);
    }

    private void drawSnow(Canvas canvas) {
        canvas.drawLine(12.0f, 2.0f, 12.0f, 22.0f, this.stroke);
        canvas.drawLine(2.0f, 12.0f, 22.0f, 12.0f, this.stroke);
        canvas.drawLine(4.0f, 4.0f, 20.0f, 20.0f, this.stroke);
        canvas.drawLine(20.0f, 4.0f, 4.0f, 20.0f, this.stroke);
    }

    private void drawSparkle(Canvas canvas) {
        Path path = new Path();
        path.moveTo(12.0f, 2.0f);
        path.lineTo(14.0f, 8.0f);
        path.lineTo(20.0f, 10.0f);
        path.lineTo(14.0f, 12.0f);
        path.lineTo(12.0f, 18.0f);
        path.lineTo(10.0f, 12.0f);
        path.lineTo(4.0f, 10.0f);
        path.lineTo(10.0f, 8.0f);
        path.close();
        canvas.drawPath(path, this.stroke);
        Path path2 = new Path();
        path2.moveTo(19.0f, 15.0f);
        path2.lineTo(20.0f, 18.0f);
        path2.lineTo(23.0f, 19.0f);
        path2.lineTo(20.0f, 20.0f);
        path2.lineTo(19.0f, 23.0f);
        path2.lineTo(18.0f, 20.0f);
        path2.lineTo(15.0f, 19.0f);
        path2.lineTo(18.0f, 18.0f);
        path2.close();
        canvas.drawPath(path2, this.stroke);
    }

    private void drawSquare(Canvas canvas) {
        canvas.drawRoundRect(new RectF(4.0f, 4.0f, 20.0f, 20.0f), 3.0f, 3.0f, this.stroke);
        canvas.drawRoundRect(new RectF(9.0f, 9.0f, 15.0f, 15.0f), 1.0f, 1.0f, this.stroke);
    }

    private void drawStar(Canvas canvas) {
        Path path = new Path();
        double[] dArr = {-1.5707963267948966d, -0.3141592653589793d, 0.9424777960769379d, 2.199114857512855d, 3.4557519189487724d};
        for (int i = 0; i < 5; i++) {
            double d = dArr[i];
            double d2 = 0.6283185307179586d + d;
            double d3 = 9.5f;
            float fCos = ((float) (Math.cos(d) * d3)) + 12.0f;
            float fSin = ((float) (Math.sin(d) * d3)) + 12.0f;
            double d4 = 4.0f;
            float fCos2 = ((float) (Math.cos(d2) * d4)) + 12.0f;
            float fSin2 = ((float) (Math.sin(d2) * d4)) + 12.0f;
            if (i == 0) {
                path.moveTo(fCos, fSin);
            } else {
                path.lineTo(fCos, fSin);
            }
            path.lineTo(fCos2, fSin2);
        }
        path.close();
        canvas.drawPath(path, this.fill);
    }

    private void drawTarget(Canvas canvas) {
        canvas.drawCircle(12.0f, 12.0f, 9.0f, this.stroke);
        canvas.drawCircle(12.0f, 12.0f, 5.0f, this.stroke);
        canvas.drawCircle(12.0f, 12.0f, 1.7f, this.fill);
    }

    private void drawZap(Canvas canvas) {
        Path path = new Path();
        path.moveTo(13.0f, 2.0f);
        path.lineTo(5.0f, 14.0f);
        path.lineTo(11.0f, 14.0f);
        path.lineTo(10.0f, 22.0f);
        path.lineTo(18.0f, 10.0f);
        path.lineTo(12.0f, 10.0f);
        path.lineTo(13.0f, 2.0f);
        path.close();
        canvas.drawPath(path, this.stroke);
    }

    public static int forFeatureId(int i) {
        if (i == -100) {
            return 24;
        }
        if (i == -6) {
            return 17;
        }
        if (i == -3) {
            return 12;
        }
        if (i == -1) {
            return 21;
        }
        if (i == 6001) {
            return 10;
        }
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 1;
        }
        if (i == 3) {
            return 2;
        }
        if (i == 4) {
            return 9;
        }
        if (i == 5) {
            return 3;
        }
        switch (i) {
            case 101:
                return 8;
            case 102:
                return 25;
            case 103:
                return 26;
            case 104:
                return 27;
            case 105:
                return 7;
            default:
                return 11;
        }
    }

    public static String urlForIconKind(int i) {
        switch (i) {
            case AUTO_BOCPHA /* 25 */:
                return "https://lienquan.garena.vn/wp-content/uploads/2024/05/cc5125aca6d0af36e8e9faebc9d840d6583ec165cad261.png";
            case AUTO_TRUNGTRI /* 26 */:
                return "https://lienquan.garena.vn/wp-content/uploads/2024/05/13d652f7da1ac0e05374ce62dc3e8dee583ec18be52611.png";
            case AUTO_BANGSUONG /* 27 */:
                return "https://lienquan.garena.vn/wp-content/uploads/2024/05/iconf30f58d64f6de018d472a24497b8e8475ef414dc8c8931.png";
            default:
                return null;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        float fWidth = getBounds().width();
        float fHeight = getBounds().height();
        float fMin = Math.min(fWidth, fHeight) / 24.0f;
        float f = 24.0f * fMin;
        float f2 = ((fWidth - f) / 2.0f) + getBounds().left;
        float f3 = ((fHeight - f) / 2.0f) + getBounds().top;
        canvas.save();
        canvas.translate(f2, f3);
        canvas.scale(fMin, fMin);
        this.stroke.setColor(this.color);
        this.stroke.setStrokeWidth(this.strokeWidth);
        this.fill.setColor(this.color);
        switch (this.kind) {
            case MAP /* 0 */:
                drawMap(canvas);
                break;
            case 1:
                drawEye(canvas);
                break;
            case CLOCK /* 2 */:
                drawClock(canvas);
                break;
            case ZAP /* 3 */:
            case BOLT /* 13 */:
                drawZap(canvas);
                break;
            case FLAME /* 4 */:
                drawFlame(canvas);
                break;
            case GAVEL /* 5 */:
                drawGavel(canvas);
                break;
            case SNOW /* 6 */:
                drawSnow(canvas);
                break;
            case HEART /* 7 */:
                drawHeart(canvas);
                break;
            case TARGET /* 8 */:
                drawTarget(canvas);
                break;
            case SHIRT /* 9 */:
                drawShirt(canvas);
                break;
            case BELL /* 10 */:
                drawBell(canvas);
                break;
            case SQUARE /* 11 */:
                drawSquare(canvas);
                break;
            case GRID /* 12 */:
                drawGrid(canvas);
                break;
            case SPARKLE /* 14 */:
                drawSparkle(canvas);
                break;
            case GEAR /* 15 */:
                drawGear(canvas);
                break;
            case CLOSE /* 16 */:
                drawClose(canvas);
                break;
            case CHEVRON /* 17 */:
                drawChevron(canvas);
                break;
            case LOGOUT /* 18 */:
                drawLogout(canvas);
                break;
            case SEARCH /* 19 */:
                drawSearch(canvas);
                break;
            case PASTE /* 20 */:
                drawPaste(canvas);
                break;
            case CHECK /* 21 */:
                drawCheck(canvas);
                break;
            case SHIELD /* 22 */:
                drawShield(canvas);
                break;
            case STAR /* 23 */:
                drawStar(canvas);
                break;
            case PLAY /* 24 */:
                drawPlay(canvas);
                break;
            case AUTO_BOCPHA /* 25 */:
                drawAutoBocPha(canvas);
                break;
            case AUTO_TRUNGTRI /* 26 */:
                drawAutoTrungTri(canvas);
                break;
            case AUTO_BANGSUONG /* 27 */:
                drawAutoBangSuong(canvas);
                break;
            case EYE_OFF /* 28 */:
                drawEyeOff(canvas);
                break;
            case MUSIC /* 29 */:
                canvas.drawLine(9.0f, 18.0f, 9.0f, 5.0f, this.stroke);
                canvas.drawLine(9.0f, 5.0f, 20.0f, 3.0f, this.stroke);
                canvas.drawLine(20.0f, 3.0f, 20.0f, 16.0f, this.stroke);
                canvas.drawOval(new RectF(3.0f, 16.0f, 9.0f, 21.0f), this.fill);
                canvas.drawOval(new RectF(14.0f, 14.0f, 20.0f, 19.0f), this.fill);
                break;
        }
        canvas.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.stroke.setAlpha(i);
        this.fill.setAlpha(i);
        invalidateSelf();
    }

    public void setColor(int i) {
        this.color = i;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.stroke.setColorFilter(colorFilter);
        this.fill.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public CygnusIconDrawable setStrokeWidth(float f) {
        this.strokeWidth = f;
        invalidateSelf();
        return this;
    }
}
