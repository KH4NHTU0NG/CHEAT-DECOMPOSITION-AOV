package com.android.support.ThemesData.DZ;

import android.content.DialogInterface;
import com.android.support.ThemesData.CYGNUS.CygnusIconDrawable;
import java.util.List;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public final /* synthetic */ class c implements DialogInterface.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ int[] b;
    public final /* synthetic */ DZMenuComponents.ValueRow c;
    public final /* synthetic */ List d;
    public final /* synthetic */ String e;
    public final /* synthetic */ int f;

    public /* synthetic */ c(int[] iArr, DZMenuComponents.ValueRow valueRow, List list, String str, int i, int i2) {
        this.a = i2;
        this.b = iArr;
        this.c = valueRow;
        this.d = list;
        this.e = str;
        this.f = i;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        switch (this.a) {
            case CygnusIconDrawable.MAP /* 0 */:
                DZMenuComponents.lambda$RadioButton$13(this.b, this.c, this.d, this.e, this.f, dialogInterface, i);
                break;
            default:
                DZMenuComponents.lambda$Spinner$4(this.b, this.c, this.d, this.e, this.f, dialogInterface, i);
                break;
        }
    }
}
