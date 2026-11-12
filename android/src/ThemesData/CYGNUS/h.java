package com.android.support.ThemesData.CYGNUS;

import android.view.View;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public final /* synthetic */ class h implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ CygnusMenuComponents b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ String e;
    public final /* synthetic */ int f;

    public /* synthetic */ h(int i, CygnusMenuComponents.ValueRow valueRow, CygnusMenuComponents cygnusMenuComponents, String str, String[] strArr) {
        this.a = 3;
        this.b = cygnusMenuComponents;
        this.e = str;
        this.c = strArr;
        this.d = valueRow;
        this.f = i;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.a) {
            case CygnusIconDrawable.MAP /* 0 */:
                this.b.lambda$CheckBox$13((boolean[]) this.c, (CygnusMenuComponents.ToggleRow) this.d, this.e, this.f, view);
                break;
            case 1:
                this.b.lambda$Switch$0((boolean[]) this.c, (CygnusMenuComponents.ToggleRow) this.d, this.e, this.f, view);
                break;
            case CygnusIconDrawable.CLOCK /* 2 */:
                CygnusMenuComponents.ToggleRow toggleRow = (CygnusMenuComponents.ToggleRow) this.d;
                this.b.lambda$ButtonOnOff$4((boolean[]) this.c, this.e, this.f, toggleRow, view);
                break;
            default:
                CygnusMenuComponents.ValueRow valueRow = (CygnusMenuComponents.ValueRow) this.d;
                this.b.lambda$InputText$12(this.e, (String[]) this.c, valueRow, this.f, view);
                break;
        }
    }

    public /* synthetic */ h(CygnusMenuComponents cygnusMenuComponents, boolean[] zArr, CygnusMenuComponents.ToggleRow toggleRow, String str, int i, int i2) {
        this.a = i2;
        this.b = cygnusMenuComponents;
        this.c = zArr;
        this.d = toggleRow;
        this.e = str;
        this.f = i;
    }

    public /* synthetic */ h(CygnusMenuComponents cygnusMenuComponents, boolean[] zArr, String str, int i, CygnusMenuComponents.ToggleRow toggleRow) {
        this.a = 2;
        this.b = cygnusMenuComponents;
        this.c = zArr;
        this.e = str;
        this.f = i;
        this.d = toggleRow;
    }
}
