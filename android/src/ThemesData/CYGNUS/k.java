package com.android.support.ThemesData.CYGNUS;

import android.view.View;
import java.util.ArrayList;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public final /* synthetic */ class k implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ CygnusMenuComponents b;
    public final /* synthetic */ String c;
    public final /* synthetic */ ArrayList d;
    public final /* synthetic */ int[] e;
    public final /* synthetic */ CygnusMenuComponents.ValueRow f;
    public final /* synthetic */ int g;

    public /* synthetic */ k(CygnusMenuComponents cygnusMenuComponents, String str, ArrayList arrayList, int[] iArr, CygnusMenuComponents.ValueRow valueRow, int i, int i2) {
        this.a = i2;
        this.b = cygnusMenuComponents;
        this.c = str;
        this.d = arrayList;
        this.e = iArr;
        this.f = valueRow;
        this.g = i;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.a) {
            case CygnusIconDrawable.MAP /* 0 */:
                CygnusMenuComponents.ValueRow valueRow = this.f;
                this.b.lambda$RadioButton$15(this.c, this.d, this.e, valueRow, this.g, view);
                break;
            default:
                CygnusMenuComponents.ValueRow valueRow2 = this.f;
                this.b.lambda$Spinner$6(this.c, this.d, this.e, valueRow2, this.g, view);
                break;
        }
    }
}
