package com.android.support.ThemesData.LGL;

import android.content.Context;
import com.android.support.interfaces.IMenuData;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public class LGLMain {
    LGLShared shared;

    public LGLMain(Context context, LGLShared lGLShared) {
        this.shared = lGLShared;
        lGLShared.MenuStyle = new LGLStyle();
        LGLShared lGLShared2 = this.shared;
        lGLShared2.getContext = context;
        lGLShared2.components = new LGLMenuComponents(lGLShared);
        this.shared.menu = new LGLMenu(lGLShared);
    }

    public void setData(IMenuData iMenuData) {
        this.shared.MenuData = iMenuData;
    }
}
