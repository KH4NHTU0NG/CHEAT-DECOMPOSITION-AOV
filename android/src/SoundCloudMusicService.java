package com.android.support;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public class SoundCloudMusicService extends Service {
    private static final String CHANNEL = "soundcloud_music";
    private static final int NOTIFICATION_ID = 2048;
    private static WeakReference<Controls> controls = new WeakReference<>(null);
    private static boolean running;
    private boolean playing;
    private MediaSession session;
    private PowerManager.WakeLock wakeLock;
    private final Handler handler = new Handler();
    private final Runnable renewWakeLock = new Runnable() { // from class: com.android.support.SoundCloudMusicService.1
        @Override // java.lang.Runnable
        public void run() {
            if (!SoundCloudMusicService.this.playing || SoundCloudMusicService.this.wakeLock == null) {
                return;
            }
            if (SoundCloudMusicService.this.wakeLock.isHeld()) {
                SoundCloudMusicService.this.wakeLock.release();
            }
            SoundCloudMusicService.this.wakeLock.acquire(3600000L);
            SoundCloudMusicService.this.handler.postDelayed(this, 1800000L);
        }
    };

    public interface Controls {
        void pause();

        void play();
    }

    private PendingIntent action(String str, int i) {
        return PendingIntent.getService(this, i, new Intent(this, (Class<?>) SoundCloudMusicService.class).setAction(str), 201326592);
    }

    public static void connect(Controls controls2) {
        controls = new WeakReference<>(controls2);
    }

    public static void disconnect(Context context, Controls controls2) {
        if (controls.get() == controls2) {
            controls.clear();
            context.stopService(new Intent(context, (Class<?>) SoundCloudMusicService.class));
        }
    }

    public static void report(Context context, boolean z) {
        if (z || running) {
            Intent intentPutExtra = new Intent(context, (Class<?>) SoundCloudMusicService.class).setAction("STATE").putExtra("playing", z);
            if (Build.VERSION.SDK_INT >= 26) {
                context.startForegroundService(intentPutExtra);
            } else {
                context.startService(intentPutExtra);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopMusic() {
        running = false;
        Controls controls2 = controls.get();
        if (controls2 != null) {
            controls2.pause();
        }
        stopForeground(true);
        stopSelf();
    }

    private void update(boolean z) {
        this.playing = z;
        this.handler.removeCallbacks(this.renewWakeLock);
        if (this.playing) {
            this.renewWakeLock.run();
        } else if (this.wakeLock.isHeld()) {
            this.wakeLock.release();
        }
        MediaSession mediaSession = this.session;
        PlaybackState.Builder actions = new PlaybackState.Builder().setActions(7L);
        boolean z2 = this.playing;
        mediaSession.setPlaybackState(actions.setState(z2 ? 3 : 2, -1L, z2 ? 1.0f : 0.0f).build());
        Notification.Builder builderA = Build.VERSION.SDK_INT >= 26 ? AbstractC0001r.a(this) : new Notification.Builder(this);
        int i = android.R.drawable.ic_media_play;
        Notification.Builder priority = builderA.setSmallIcon(android.R.drawable.ic_media_play).setContentTitle("Nhạc SoundCloud").setContentText(this.playing ? "Đang phát • tiếp tục khi thu nhỏ menu" : "Đã tạm dừng").setOngoing(this.playing).setOnlyAlertOnce(true).setPriority(-1);
        boolean z3 = this.playing;
        if (z3) {
            i = android.R.drawable.ic_media_pause;
        }
        startForeground(NOTIFICATION_ID, priority.addAction(i, z3 ? "Tạm dừng" : "Phát", action(z3 ? "PAUSE" : "PLAY", 1)).addAction(android.R.drawable.ic_menu_close_clear_cancel, "Dừng", action("STOP", 2)).setStyle(new Notification.MediaStyle().setMediaSession(this.session.getSessionToken()).setShowActionsInCompactView(0, 1)).build());
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        running = true;
        if (Build.VERSION.SDK_INT >= 26) {
            ((NotificationManager) getSystemService("notification")).createNotificationChannel(AbstractC0001r.b());
        }
        PowerManager.WakeLock wakeLockNewWakeLock = ((PowerManager) getSystemService("power")).newWakeLock(1, getPackageName() + ":SoundCloud");
        this.wakeLock = wakeLockNewWakeLock;
        wakeLockNewWakeLock.setReferenceCounted(false);
        MediaSession mediaSession = new MediaSession(this, "SoundCloud");
        this.session = mediaSession;
        mediaSession.setCallback(new MediaSession.Callback() { // from class: com.android.support.SoundCloudMusicService.2
            @Override // android.media.session.MediaSession.Callback
            public void onPause() {
                Controls controls2 = (Controls) SoundCloudMusicService.controls.get();
                if (controls2 != null) {
                    controls2.pause();
                }
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPlay() {
                Controls controls2 = (Controls) SoundCloudMusicService.controls.get();
                if (controls2 != null) {
                    controls2.play();
                }
            }

            @Override // android.media.session.MediaSession.Callback
            public void onStop() {
                SoundCloudMusicService.this.stopMusic();
            }
        });
        this.session.setActive(true);
    }

    @Override // android.app.Service
    public void onDestroy() {
        running = false;
        this.handler.removeCallbacksAndMessages(null);
        PowerManager.WakeLock wakeLock = this.wakeLock;
        if (wakeLock != null && wakeLock.isHeld()) {
            this.wakeLock.release();
        }
        MediaSession mediaSession = this.session;
        if (mediaSession != null) {
            mediaSession.release();
        }
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        String action = intent == null ? "STOP" : intent.getAction();
        Controls controls2 = controls.get();
        if ("STOP".equals(action) || controls2 == null) {
            stopMusic();
            return 2;
        }
        if ("PLAY".equals(action)) {
            update(this.playing);
            controls2.play();
        } else if ("PAUSE".equals(action)) {
            update(false);
            controls2.pause();
        } else {
            update(intent.getBooleanExtra("playing", false));
        }
        return 2;
    }
}
