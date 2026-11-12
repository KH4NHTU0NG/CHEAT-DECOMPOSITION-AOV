package com.android.support.ThemesData.CYGNUS;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public final /* synthetic */ class t implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ t(Object obj, int i) {
        this.a = i;
        this.b = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case CygnusIconDrawable.MAP /* 0 */:
                ((EquipmentPanel) this.b).rebuildGrid();
                break;
            default:
                ((CygnusRemoteIcon) this.b).doLoad();
                break;
        }
    }
}
