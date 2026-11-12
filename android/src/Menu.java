package com.android.support;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.android.support.ThemesData.CYGNUS.CygnusTheme;
import com.android.support.interfaces.IMenuData;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public class Menu extends CygnusTheme {
    public Menu(Context context) {
        super.Init(context, new IMenuData() { // from class: com.android.support.Menu.1
            @Override // com.android.support.interfaces.IMenuData
            public String[] GetFeatureList() {
                return Menu.this.GetFeatureList();
            }

            @Override // com.android.support.interfaces.IMenuData
            public String Icon() {
                return Menu.this.Icon();
            }

            @Override // com.android.support.interfaces.IMenuData
            public String IconWebViewData() {
                return Menu.this.IconWebViewData();
            }

            @Override // com.android.support.interfaces.IMenuData
            public void Init(Context context2, TextView textView, TextView textView2) {
                Menu.this.Init(context2, textView, textView2);
            }

            @Override // com.android.support.interfaces.IMenuData
            public boolean IsGameLibLoaded() {
                return Menu.this.IsGameLibLoaded();
            }

            @Override // com.android.support.interfaces.IMenuData
            public String[] SettingsList() {
                return Menu.this.SettingsList();
            }

            @Override // com.android.support.interfaces.IMenuData
            public View.OnTouchListener onTouchListener() {
                return null;
            }
        });
    }

    public native String[] GetFeatureList();

    public native String Icon();

    public native String IconWebViewData();

    public native void Init(Context context, TextView textView, TextView textView2);

    public native boolean IsGameLibLoaded();

    public native String[] SettingsList();
}
