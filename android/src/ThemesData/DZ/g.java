package com.android.support.ThemesData.DZ;

import android.view.View;
import com.android.support.ThemesData.CYGNUS.CygnusIconDrawable;
import java.util.List;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public final /* synthetic */ class g implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ DZMenuComponents b;
    public final /* synthetic */ String c;
    public final /* synthetic */ List d;
    public final /* synthetic */ int[] e;
    public final /* synthetic */ DZMenuComponents.ValueRow f;
    public final /* synthetic */ int g;

    public /* synthetic */ g(DZMenuComponents dZMenuComponents, String str, List list, int[] iArr, DZMenuComponents.ValueRow valueRow, int i, int i2) {
        this.a = i2;
        this.b = dZMenuComponents;
        this.c = str;
        this.d = list;
        this.e = iArr;
        this.f = valueRow;
        this.g = i;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.a) {
            case CygnusIconDrawable.MAP /* 0 */:
                DZMenuComponents.ValueRow valueRow = this.f;
                this.b.lambda$Spinner$5(this.c, this.d, this.e, valueRow, this.g, view);
                break;
            default:
                DZMenuComponents.ValueRow valueRow2 = this.f;
                this.b.lambda$RadioButton$14(this.c, this.d, this.e, valueRow2, this.g, view);
                break;
        }
    }
}
