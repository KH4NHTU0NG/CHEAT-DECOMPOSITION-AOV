package com.android.support.ThemesData.CYGNUS;

import android.app.Dialog;
import android.view.KeyEvent;
import android.view.View;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public final /* synthetic */ class u implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ KeyEvent.Callback b;

    public /* synthetic */ u(KeyEvent.Callback callback, int i) {
        this.a = i;
        this.b = callback;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.a) {
            case CygnusIconDrawable.MAP /* 0 */:
                ((EquipmentPanel) this.b).lambda$videoGuide$3(view);
                break;
            default:
                ((Dialog) this.b).dismiss();
                break;
        }
    }
}
