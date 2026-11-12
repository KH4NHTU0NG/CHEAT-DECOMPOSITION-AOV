package com.android.support.ThemesData.DZ;

import android.view.View;
import com.android.support.ThemesData.CYGNUS.CygnusIconDrawable;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public final /* synthetic */ class e implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ DZMenuComponents b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ String e;
    public final /* synthetic */ int f;

    public /* synthetic */ e(int i, DZMenuComponents.ValueRow valueRow, DZMenuComponents dZMenuComponents, String str, String[] strArr) {
        this.a = 3;
        this.b = dZMenuComponents;
        this.e = str;
        this.c = strArr;
        this.d = valueRow;
        this.f = i;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.a) {
            case CygnusIconDrawable.MAP /* 0 */:
                DZMenuComponents.ToggleRow toggleRow = (DZMenuComponents.ToggleRow) this.d;
                this.b.lambda$ButtonOnOff$3((boolean[]) this.c, this.e, this.f, toggleRow, view);
                break;
            case 1:
                this.b.lambda$CheckBox$12((boolean[]) this.c, (DZMenuComponents.ToggleRow) this.d, this.e, this.f, view);
                break;
            case CygnusIconDrawable.CLOCK /* 2 */:
                this.b.lambda$Switch$0((boolean[]) this.c, (DZMenuComponents.ToggleRow) this.d, this.e, this.f, view);
                break;
            default:
                DZMenuComponents.ValueRow valueRow = (DZMenuComponents.ValueRow) this.d;
                this.b.lambda$InputText$11(this.e, (String[]) this.c, valueRow, this.f, view);
                break;
        }
    }

    public /* synthetic */ e(DZMenuComponents dZMenuComponents, boolean[] zArr, DZMenuComponents.ToggleRow toggleRow, String str, int i, int i2) {
        this.a = i2;
        this.b = dZMenuComponents;
        this.c = zArr;
        this.d = toggleRow;
        this.e = str;
        this.f = i;
    }

    public /* synthetic */ e(DZMenuComponents dZMenuComponents, boolean[] zArr, String str, int i, DZMenuComponents.ToggleRow toggleRow) {
        this.a = 0;
        this.b = dZMenuComponents;
        this.c = zArr;
        this.e = str;
        this.f = i;
        this.d = toggleRow;
    }
}
