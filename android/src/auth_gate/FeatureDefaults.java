package com.android.support.auth;

import android.util.Log;
import java.text.Normalizer;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/* JADX INFO: loaded from: /tmp/apk_work/classes8.dex */
public final class FeatureDefaults {
    private static final Set<Integer> initialized = new HashSet();

    private FeatureDefaults() {
    }

    public static synchronized boolean shouldReset(String str, int i) {
        if (str == null || i < 0) {
            return false;
        }
        String strTrim = Normalizer.normalize(str.replaceAll("<[^>]+>", ""), Normalizer.Form.NFD).replaceAll("\\p{M}+", "").replace((char) 272, 'D').replace((char) 273, 'd').toLowerCase(Locale.ROOT).replaceAll("\\s+", " ").trim();
        if (!"6 do moi kich hoat".equals(strTrim) && !"6 do lon moi kich hoat".equals(strTrim)) {
            return false;
        }
        if (!initialized.add(Integer.valueOf(i))) {
            return false;
        }
        Log.i("ApkDefaults", "Reset six-item startup switch, id=" + i);
        return true;
    }
}
