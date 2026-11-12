package com.android.support.ThemesData.CYGNUS;

import android.view.View;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public final /* synthetic */ class e implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;

    public /* synthetic */ e(Object obj, Object obj2, int i) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.a) {
            case CygnusIconDrawable.MAP /* 0 */:
                ((CygnusMenu) this.b).lambda$buildRail$4((CygnusMenu.TabDef) this.c, view);
                break;
            default:
                ((CygnusMenuComponents) this.b).lambda$ButtonLink$3((String) this.c, view);
                break;
        }
    }
}
