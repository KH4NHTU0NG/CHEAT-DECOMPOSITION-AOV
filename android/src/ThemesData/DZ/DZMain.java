package com.android.support.ThemesData.DZ;

import android.content.Context;
import com.android.support.interfaces.IMenuData;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public class DZMain {
    DZShared shared;

    public DZMain(Context context, DZShared dZShared) {
        this.shared = dZShared;
        dZShared.MenuStyle = new DZStyle();
        DZShared dZShared2 = this.shared;
        dZShared2.getContext = context;
        dZShared2.components = new DZMenuComponents(dZShared);
        this.shared.menu = new DZMenu(dZShared);
    }

    public void setData(IMenuData iMenuData) {
        this.shared.MenuData = iMenuData;
    }
}
