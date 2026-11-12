package com.android.support.ThemesData.CYGNUS;

import android.widget.CompoundButton;
import com.android.support.Preferences;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public final /* synthetic */ class z implements CompoundButton.OnCheckedChangeListener {
    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        Preferences.changeFeatureBool("6 Đồ Lớn Mới Kích Hoạt", 121, z);
    }
}
