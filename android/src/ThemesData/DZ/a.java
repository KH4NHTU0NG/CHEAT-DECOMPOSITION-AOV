package com.android.support.ThemesData.DZ;

import android.content.DialogInterface;
import com.android.support.ThemesData.CYGNUS.CygnusIconDrawable;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public final /* synthetic */ class a implements DialogInterface.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ DZMenu b;

    public /* synthetic */ a(DZMenu dZMenu, int i) {
        this.a = i;
        this.b = dZMenu;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        switch (this.a) {
            case CygnusIconDrawable.MAP /* 0 */:
                this.b.lambda$confirmLogout$8(dialogInterface, i);
                break;
            default:
                this.b.lambda$confirmBannedAccountLogout$9(dialogInterface, i);
                break;
        }
    }
}
