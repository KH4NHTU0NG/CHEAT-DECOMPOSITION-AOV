package com.android.support.ThemesData.CYGNUS;

import android.view.View;
import android.widget.LinearLayout;
import com.android.support.ThemesData.DZ.DZMenuComponents;

/* JADX INFO: renamed from: com.android.support.ThemesData.CYGNUS.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public final /* synthetic */ class ViewOnClickListenerC0000a implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ int b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;

    public /* synthetic */ ViewOnClickListenerC0000a(Object obj, int i, Object obj2, int i2) {
        this.a = i2;
        this.c = obj;
        this.b = i;
        this.d = obj2;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.a) {
            case CygnusIconDrawable.MAP /* 0 */:
                ((CygnusMenu) this.c).lambda$buildAppearanceGroup$7(this.b, (LinearLayout) this.d, view);
                break;
            case 1:
                ((CygnusMenuComponents) this.c).lambda$Button$2(this.b, (String) this.d, view);
                break;
            default:
                ((DZMenuComponents) this.c).lambda$Button$1(this.b, (String) this.d, view);
                break;
        }
    }
}
