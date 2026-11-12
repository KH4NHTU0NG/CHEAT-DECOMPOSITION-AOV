package com.android.support.ThemesData.DZ;

import android.view.View;
import com.android.support.ThemesData.CYGNUS.CygnusIconDrawable;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public final /* synthetic */ class b implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ DZMenu b;

    public /* synthetic */ b(DZMenu dZMenu, int i) {
        this.a = i;
        this.b = dZMenu;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.a) {
            case CygnusIconDrawable.MAP /* 0 */:
                this.b.lambda$buildTabs$2(view);
                break;
            case 1:
                this.b.lambda$buildTabs$3(view);
                break;
            case CygnusIconDrawable.CLOCK /* 2 */:
                this.b.lambda$buildHeader$0(view);
                break;
            case CygnusIconDrawable.ZAP /* 3 */:
                this.b.lambda$buildHeader$1(view);
                break;
            case CygnusIconDrawable.FLAME /* 4 */:
                this.b.lambda$buildIcon$4(view);
                break;
            case CygnusIconDrawable.GAVEL /* 5 */:
                this.b.lambda$buildAccountSection$5(view);
                break;
            default:
                this.b.lambda$buildAccountSection$6(view);
                break;
        }
    }
}
