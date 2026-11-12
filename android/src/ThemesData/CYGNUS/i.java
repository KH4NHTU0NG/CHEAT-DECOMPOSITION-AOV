package com.android.support.ThemesData.CYGNUS;

import java.util.List;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public final /* synthetic */ class i implements CygnusMenuComponents.OptionPick, CygnusMenuComponents.InputCommit {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ CygnusMenuComponents.ValueRow c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ String e;
    public final /* synthetic */ int f;

    public /* synthetic */ i(int i, CygnusMenuComponents.ValueRow valueRow, CygnusMenuComponents cygnusMenuComponents, String str, String[] strArr) {
        this.a = 2;
        this.b = cygnusMenuComponents;
        this.d = strArr;
        this.c = valueRow;
        this.e = str;
        this.f = i;
    }

    @Override // com.android.support.ThemesData.CYGNUS.CygnusMenuComponents.InputCommit
    public void apply(String str) {
        ((CygnusMenuComponents) this.b).lambda$InputText$11((String[]) this.d, this.c, this.e, this.f, str);
    }

    @Override // com.android.support.ThemesData.CYGNUS.CygnusMenuComponents.OptionPick
    public void onPick(int i) {
        switch (this.a) {
            case CygnusIconDrawable.MAP /* 0 */:
                CygnusMenuComponents.lambda$Spinner$5((int[]) this.b, this.c, (List) this.d, this.e, this.f, i);
                break;
            default:
                CygnusMenuComponents.lambda$RadioButton$14((int[]) this.b, this.c, (List) this.d, this.e, this.f, i);
                break;
        }
    }

    public /* synthetic */ i(int[] iArr, CygnusMenuComponents.ValueRow valueRow, List list, String str, int i, int i2) {
        this.a = i2;
        this.b = iArr;
        this.c = valueRow;
        this.d = list;
        this.e = str;
        this.f = i;
    }
}
