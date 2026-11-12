package com.android.support.ThemesData.CYGNUS;

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
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public final class CygnusRemoteIcon extends Drawable {
    private static final long CACHE_TTL_MS = 86400000;
    private static final int MAX_BYTES = 4194304;
    private Bitmap bitmap;
    private final Context context;
    private final Drawable fallback;
    private final String url;
    private static final Handler MAIN = new Handler(Looper.getMainLooper());
    private static final ExecutorService LOADER = Executors.newFixedThreadPool(2, new s());
    private static final Map<String, Bitmap> MEMORY = new HashMap();
    private static final Map<String, Boolean> LOADING = new HashMap();
    private final Paint paint = new Paint(7);
    private final Path clip = new Path();

    public CygnusRemoteIcon(Context context, String str, Drawable drawable) {
        this.context = context == null ? null : context.getApplicationContext();
        this.url = str;
        this.fallback = drawable;
        loadIfNeeded();
    }

    private File cacheFile() {
        return new File(this.context.getCacheDir(), "cygnus-icon-" + Math.abs(this.url.hashCode()) + ".png");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doLoad() {
        try {
            File fileCacheFile = cacheFile();
            final Bitmap bitmapDecodeFile = (!fileCacheFile.isFile() || fileCacheFile.length() <= 0 || System.currentTimeMillis() - fileCacheFile.lastModified() >= CACHE_TTL_MS) ? null : BitmapFactory.decodeFile(fileCacheFile.getAbsolutePath());
            if (bitmapDecodeFile == null && (bitmapDecodeFile = download()) != null) {
                writeCache(fileCacheFile, bitmapDecodeFile);
            }
            if (bitmapDecodeFile != null) {
                Map<String, Bitmap> map = MEMORY;
                synchronized (map) {
                    map.put(this.url, bitmapDecodeFile);
                }
                MAIN.post(new Runnable() { // from class: com.android.support.ThemesData.CYGNUS.r
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.a.lambda$doLoad$1(bitmapDecodeFile);
                    }
                });
            }
            synchronized (MEMORY) {
                LOADING.remove(this.url);
            }
        } catch (Throwable unused) {
            synchronized (MEMORY) {
                LOADING.remove(this.url);
            }
        }
    }

    private Bitmap download() throws Throwable {
        HttpURLConnection httpURLConnection;
        Throwable th;
        try {
            httpURLConnection = (HttpURLConnection) new URL(this.url).openConnection();
            try {
                httpURLConnection.setConnectTimeout(8000);
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setInstanceFollowRedirects(true);
                httpURLConnection.setRequestProperty("Accept", "image/*");
                httpURLConnection.setRequestProperty("User-Agent", "Cygnus/1.0");
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode >= 200 && responseCode < 300) {
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                    try {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        byte[] bArr = new byte[8192];
                        int i = 0;
                        while (true) {
                            int i2 = bufferedInputStream.read(bArr);
                            if (i2 == -1) {
                                byte[] byteArray = byteArrayOutputStream.toByteArray();
                                Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                                bufferedInputStream.close();
                                httpURLConnection.disconnect();
                                return bitmapDecodeByteArray;
                            }
                            i += i2;
                            if (i > MAX_BYTES) {
                                bufferedInputStream.close();
                                httpURLConnection.disconnect();
                                return null;
                            }
                            byteArrayOutputStream.write(bArr, 0, i2);
                        }
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

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$doLoad$1(Bitmap bitmap) {
        this.bitmap = bitmap;
        invalidateSelf();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Thread lambda$static$0(Runnable runnable) {
        Thread thread = new Thread(runnable, "cygnus-icon-loader");
        thread.setDaemon(true);
        return thread;
    }

    private void loadIfNeeded() {
        if (this.context == null || this.url == null) {
            return;
        }
        Map<String, Bitmap> map = MEMORY;
        synchronized (map) {
            try {
                Bitmap bitmap = map.get(this.url);
                if (bitmap != null) {
                    this.bitmap = bitmap;
                    return;
                }
                Boolean bool = Boolean.TRUE;
                Map<String, Boolean> map2 = LOADING;
                if (bool.equals(map2.get(this.url))) {
                    return;
                }
                map2.put(this.url, bool);
                LOADER.execute(new t(this, 1));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void writeCache(File file, Bitmap bitmap) {
        File file2 = new File(file.getParentFile(), file.getName() + ".tmp");
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
            try {
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, bufferedOutputStream);
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
        if (this.bitmap == null) {
            Drawable drawable = this.fallback;
            if (drawable != null) {
                drawable.setBounds(bounds);
                this.fallback.draw(canvas);
                return;
            }
            return;
        }
        float fCenterX = bounds.centerX();
        float fCenterY = bounds.centerY();
        float fMin = Math.min(bounds.width(), bounds.height()) / 2.0f;
        this.clip.reset();
        this.clip.addCircle(fCenterX, fCenterY, fMin, Path.Direction.CW);
        canvas.save();
        canvas.clipPath(this.clip);
        canvas.drawBitmap(this.bitmap, (Rect) null, new RectF(fCenterX - fMin, fCenterY - fMin, fCenterX + fMin, fCenterY + fMin), this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 96;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 96;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.paint.setAlpha(i);
        Drawable drawable = this.fallback;
        if (drawable != null) {
            drawable.setAlpha(i);
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.paint.setColorFilter(colorFilter);
        Drawable drawable = this.fallback;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        }
        invalidateSelf();
    }
}
