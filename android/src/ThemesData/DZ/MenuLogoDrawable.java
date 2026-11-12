package com.android.support.ThemesData.DZ;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public final class MenuLogoDrawable extends Drawable {
    private static final String CACHE_NAME = "lmk-menu-logo.jpg";
    private static final long CACHE_TTL_MS = 21600000;
    private static final int MAX_IMAGE_BYTES = 5242880;
    private static final String SOURCE_URL = "https://sf-static.upanhlaylink.com/img/image_20260811b2864efd0dd661d12eaa25e2d556e008.jpg";
    private static long lastAttempt;
    private static boolean loading;
    private static Bitmap sharedBitmap;
    private Bitmap bitmap;
    private final Paint bitmapPaint;
    private final int borderColor;
    private final Paint borderPaint;
    private final Path clipPath;
    private final Context context;
    private static final Object LOAD_LOCK = new Object();
    private static final Handler MAIN_HANDLER = new Handler(Looper.getMainLooper());
    private static final ExecutorService LOADER = Executors.newSingleThreadExecutor(new m());
    private static final ArrayList<WeakReference<MenuLogoDrawable>> INSTANCES = new ArrayList<>();
    private static final String[] LOGO_DATA = {"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", "ACiiigAoHUfWigdR9aAP/9k="};

    public MenuLogoDrawable(Context context, int i) {
        this.bitmapPaint = new Paint(7);
        Paint paint = new Paint(1);
        this.borderPaint = paint;
        this.clipPath = new Path();
        this.context = context == null ? null : context.getApplicationContext();
        this.borderColor = i;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1.0f);
        registerInstance();
        requestRemoteRefresh();
    }

    private Bitmap downloadBitmap() throws Throwable {
        HttpURLConnection httpURLConnection;
        Throwable th;
        try {
            httpURLConnection = (HttpURLConnection) new URL(SOURCE_URL).openConnection();
            try {
                httpURLConnection.setConnectTimeout(8000);
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setInstanceFollowRedirects(true);
                httpURLConnection.setRequestProperty("Accept", "image/*");
                httpURLConnection.setRequestProperty("User-Agent", "Android-Mod-Menu/1.0");
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode >= 200 && responseCode < 300) {
                    if (httpURLConnection.getContentLength() > MAX_IMAGE_BYTES) {
                        httpURLConnection.disconnect();
                        return null;
                    }
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                    try {
                        byte[] bounded = readBounded(bufferedInputStream);
                        Bitmap bitmapDecodeByteArray = bounded == null ? null : BitmapFactory.decodeByteArray(bounded, 0, bounded.length);
                        bufferedInputStream.close();
                        httpURLConnection.disconnect();
                        return bitmapDecodeByteArray;
                    } catch (Throwable th2) {
                        try {
                            bufferedInputStream.close();
                        } catch (Throwable th3) {
                            th2.addSuppressed(th3);
                        }
                        throw th2;
                    }
                }
                httpURLConnection.disconnect();
                return null;
            } catch (IOException unused) {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return null;
            } catch (Throwable th4) {
                th = th4;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (IOException unused2) {
            httpURLConnection = null;
        } catch (Throwable th5) {
            httpURLConnection = null;
            th = th5;
        }
    }

    private Bitmap getBitmap() {
        if (this.bitmap == null) {
            synchronized (LOAD_LOCK) {
                this.bitmap = sharedBitmap;
            }
        }
        if (this.bitmap == null) {
            StringBuilder sb = new StringBuilder();
            for (String str : LOGO_DATA) {
                sb.append(str);
            }
            byte[] bArrDecode = Base64.decode(sb.toString(), 0);
            this.bitmap = BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length);
        }
        return this.bitmap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$notifyInstances$2() {
        synchronized (LOAD_LOCK) {
            try {
                Iterator<WeakReference<MenuLogoDrawable>> it = INSTANCES.iterator();
                while (it.hasNext()) {
                    MenuLogoDrawable menuLogoDrawable = it.next().get();
                    if (menuLogoDrawable == null) {
                        it.remove();
                    } else {
                        menuLogoDrawable.bitmap = sharedBitmap;
                        menuLogoDrawable.invalidateSelf();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$requestRemoteRefresh$1() {
        Bitmap bitmapDecodeFile = null;
        try {
            File file = new File(this.context.getCacheDir(), CACHE_NAME);
            if (file.isFile() && file.length() > 0 && (bitmapDecodeFile = BitmapFactory.decodeFile(file.getAbsolutePath())) != null && System.currentTimeMillis() - file.lastModified() < CACHE_TTL_MS) {
                publishBitmap(bitmapDecodeFile);
                synchronized (LOAD_LOCK) {
                    try {
                        loading = false;
                        if (sharedBitmap == null) {
                            sharedBitmap = bitmapDecodeFile;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                notifyInstances();
                return;
            }
            Bitmap bitmapDownloadBitmap = downloadBitmap();
            if (bitmapDownloadBitmap != null) {
                try {
                    writeCache(file, bitmapDownloadBitmap);
                    bitmapDecodeFile = bitmapDownloadBitmap;
                } catch (Throwable unused) {
                    bitmapDecodeFile = bitmapDownloadBitmap;
                    synchronized (LOAD_LOCK) {
                        try {
                            loading = false;
                            if (bitmapDecodeFile != null && sharedBitmap == null) {
                                sharedBitmap = bitmapDecodeFile;
                            }
                        } catch (Throwable th2) {
                            throw th2;
                        }
                    }
                }
            }
            synchronized (LOAD_LOCK) {
                try {
                    loading = false;
                    if (bitmapDecodeFile != null && sharedBitmap == null) {
                        sharedBitmap = bitmapDecodeFile;
                    }
                } catch (Throwable th3) {
                    throw th3;
                }
            }
            notifyInstances();
        } catch (Throwable unused2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Thread lambda$static$0(Runnable runnable) {
        Thread thread = new Thread(runnable, "menu-logo-loader");
        thread.setDaemon(true);
        return thread;
    }

    private static void notifyInstances() {
        MAIN_HANDLER.post(new com.android.support.d(5));
    }

    private void publishBitmap(Bitmap bitmap) {
        synchronized (LOAD_LOCK) {
            try {
                if (sharedBitmap == null && bitmap != null) {
                    sharedBitmap = bitmap;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        notifyInstances();
    }

    private byte[] readBounded(BufferedInputStream bufferedInputStream) throws IOException {
        byte[] bArr = new byte[8192];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (true) {
            int i2 = bufferedInputStream.read(bArr);
            if (i2 == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            i += i2;
            if (i > MAX_IMAGE_BYTES) {
                return null;
            }
            byteArrayOutputStream.write(bArr, 0, i2);
        }
    }

    private void registerInstance() {
        synchronized (LOAD_LOCK) {
            INSTANCES.add(new WeakReference<>(this));
        }
    }

    private void requestRemoteRefresh() {
        if (this.context == null) {
            return;
        }
        synchronized (LOAD_LOCK) {
            try {
                if (sharedBitmap == null && !loading) {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    if (jCurrentTimeMillis - lastAttempt < CACHE_TTL_MS) {
                        return;
                    }
                    loading = true;
                    lastAttempt = jCurrentTimeMillis;
                    LOADER.execute(new com.android.support.c(this, 3));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static String sourceUrl() {
        return SOURCE_URL;
    }

    private void writeCache(File file, Bitmap bitmap) {
        if (file == null || bitmap == null) {
            return;
        }
        File file2 = new File(file.getParentFile(), "lmk-menu-logo.jpg.tmp");
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
            try {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bufferedOutputStream);
                bufferedOutputStream.flush();
                if (!file2.renameTo(file) && file.delete()) {
                    file2.renameTo(file);
                }
                bufferedOutputStream.close();
            } catch (Throwable th) {
                try {
                    bufferedOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException unused) {
            file2.delete();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        float fMin = Math.min(bounds.width(), bounds.height());
        if (fMin <= 0.0f) {
            return;
        }
        float fWidth = (bounds.width() * 0.5f) + bounds.left;
        float fHeight = (bounds.height() * 0.5f) + bounds.top;
        float f = fMin * 0.5f;
        RectF rectF = new RectF(fWidth - f, fHeight - f, fWidth + f, fHeight + f);
        Bitmap bitmap = getBitmap();
        canvas.save();
        this.clipPath.reset();
        this.clipPath.addCircle(fWidth, fHeight, f, Path.Direction.CW);
        canvas.clipPath(this.clipPath);
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, (Rect) null, rectF, this.bitmapPaint);
        } else {
            canvas.drawColor(-15460323);
        }
        canvas.restore();
        this.borderPaint.setColor(this.borderColor);
        this.borderPaint.setAlpha(this.bitmapPaint.getAlpha());
        float fMax = Math.max(1.0f, fMin * 0.025f);
        this.borderPaint.setStrokeWidth(fMax);
        canvas.drawCircle(fWidth, fHeight, f - (fMax * 0.5f), this.borderPaint);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 192;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 192;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.bitmapPaint.setAlpha(i);
        this.borderPaint.setAlpha(i);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.bitmapPaint.setColorFilter(colorFilter);
        this.borderPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public MenuLogoDrawable(int i) {
        this(null, i);
    }
}
