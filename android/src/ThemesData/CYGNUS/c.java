package com.android.support.ThemesData.CYGNUS;

import android.view.View;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public final /* synthetic */ class c implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ CygnusMenu b;

    public /* synthetic */ c(CygnusMenu cygnusMenu, int i) {
        this.a = i;
        this.b = cygnusMenu;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.a) {
            case CygnusIconDrawable.MAP /* 0 */:
                this.b.lambda$buildAppearanceGroup$10(view);
                break;
            case 1:
                this.b.lambda$buildCygnusSettings$5(view);
                break;
            case CygnusIconDrawable.CLOCK /* 2 */:
                this.b.lambda$buildCygnusSettings$6(view);
                break;
            case CygnusIconDrawable.ZAP /* 3 */:
                this.b.lambda$buildFloatingIcon$0(view);
                break;
            case CygnusIconDrawable.FLAME /* 4 */:
                this.b.lambda$buildHeader$1(view);
                break;
            case CygnusIconDrawable.GAVEL /* 5 */:
                this.b.lambda$buildHeader$2(view);
                break;
            default:
                this.b.lambda$addBanLogoutRow$11(view);
                break;
        }
    }
}
