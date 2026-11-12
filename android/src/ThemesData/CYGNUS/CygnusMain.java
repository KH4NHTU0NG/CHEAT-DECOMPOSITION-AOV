package com.android.support.ThemesData.CYGNUS;

import android.content.Context;
import com.android.support.interfaces.IMenuData;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public class CygnusMain {
    CygnusShared shared;

    public CygnusMain(Context context, CygnusShared cygnusShared) {
        this.shared = cygnusShared;
        cygnusShared.MenuStyle = new CygnusStyle();
        this.shared.MenuStyle.loadFromPrefs(context);
        CygnusShared cygnusShared2 = this.shared;
        cygnusShared2.getContext = context;
        cygnusShared2.components = new CygnusMenuComponents(cygnusShared);
        this.shared.menu = new CygnusMenu(cygnusShared);
    }

    public void setData(IMenuData iMenuData) {
        this.shared.MenuData = iMenuData;
    }
}
