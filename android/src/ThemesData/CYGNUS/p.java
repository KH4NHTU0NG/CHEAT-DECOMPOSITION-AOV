package com.android.support.ThemesData.CYGNUS;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.support.ThemesData.DZ.DZMenuComponents;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public final /* synthetic */ class p implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ boolean[] b;
    public final /* synthetic */ LinearLayout c;
    public final /* synthetic */ TextView d;

    public /* synthetic */ p(boolean[] zArr, LinearLayout linearLayout, TextView textView, int i) {
        this.a = i;
        this.b = zArr;
        this.c = linearLayout;
        this.d = textView;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.a) {
            case CygnusIconDrawable.MAP /* 0 */:
                CygnusMenuComponents.lambda$Collapse$16(this.b, this.c, this.d, view);
                break;
            default:
                DZMenuComponents.lambda$Collapse$15(this.b, this.c, this.d, view);
                break;
        }
    }
}
