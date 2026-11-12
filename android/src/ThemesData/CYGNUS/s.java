package com.android.support.ThemesData.CYGNUS;

import java.util.concurrent.ThreadFactory;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public final /* synthetic */ class s implements ThreadFactory {
    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        return CygnusRemoteIcon.lambda$static$0(runnable);
    }
}
