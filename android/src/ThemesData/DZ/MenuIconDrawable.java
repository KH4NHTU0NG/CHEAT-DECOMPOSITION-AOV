package com.android.support.ThemesData.DZ;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
final class MenuIconDrawable extends Drawable {
    static final int BRAND = 0;
    static final int HIDE = 2;
    static final int MINIMIZE = 1;
    static final int POWER = 3;
    private int color;
    private final int kind;
    private final Paint paint;

    public MenuIconDrawable(int i, int i2) {
        Paint paint = new Paint(1);
        this.paint = paint;
        this.kind = i;
        this.color = i2;
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }

    private void drawBrand(Canvas canvas) {
        canvas.drawLine(4.0f, 7.0f, 20.0f, 7.0f, this.paint);
        canvas.drawLine(4.0f, 12.0f, 20.0f, 12.0f, this.paint);
        canvas.drawLine(4.0f, 17.0f, 20.0f, 17.0f, this.paint);
        this.paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(8.0f, 7.0f, 2.0f, this.paint);
        canvas.drawCircle(15.0f, 12.0f, 2.0f, this.paint);
        canvas.drawCircle(11.0f, 17.0f, 2.0f, this.paint);
    }

    private float fortyFive() {
        return 45.0f;
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
        this.paint.setColor(this.color);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(this.kind == 0 ? 1.5f : 1.8f);
        int i = this.kind;
        if (i == 0) {
            drawBrand(canvas);
        } else if (i == 1) {
            canvas.drawLine(6.0f, 12.0f, 18.0f, 12.0f, this.paint);
        } else if (i == 2) {
            canvas.drawOval(new RectF(4.0f, 7.0f, 20.0f, 17.0f), this.paint);
            canvas.drawCircle(12.0f, 12.0f, 2.2f, this.paint);
            canvas.drawLine(5.0f, 5.0f, 19.0f, 19.0f, this.paint);
        } else if (i == 3) {
            canvas.drawArc(new RectF(5.0f, 5.0f, 19.0f, 19.0f), fortyFive(), 270.0f, false, this.paint);
            canvas.drawLine(12.0f, 3.5f, 12.0f, 11.0f, this.paint);
        }
        canvas.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.paint.setAlpha(i);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.paint.setColorFilter(colorFilter);
        invalidateSelf();
    }
}
