package com.android.support.ThemesData.DZ;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.android.support.base.FloatingWindowManager;
import com.android.support.interfaces.IMenuData;
import com.android.support.interfaces.IMenuTheme;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
public class DZTheme implements IMenuTheme {
    DZShared shared;
    FloatingWindowManager windowManager;

    @Override // com.android.support.interfaces.IMenuTheme
    public void Init(Context context, final IMenuData iMenuData) {
        DZShared dZShared = new DZShared();
        this.shared = dZShared;
        this.windowManager = new FloatingWindowManager(context, dZShared);
        new DZMain(context, this.shared).setData(new IMenuData() { // from class: com.android.support.ThemesData.DZ.DZTheme.1
            @Override // com.android.support.interfaces.IMenuData
            public String[] GetFeatureList() {
                return iMenuData.GetFeatureList();
            }

            @Override // com.android.support.interfaces.IMenuData
            public String Icon() {
                return iMenuData.Icon();
            }

            @Override // com.android.support.interfaces.IMenuData
            public String IconWebViewData() {
                return iMenuData.IconWebViewData();
            }

            @Override // com.android.support.interfaces.IMenuData
            public void Init(Context context2, TextView textView, TextView textView2) {
                iMenuData.Init(context2, textView, textView2);
            }

            @Override // com.android.support.interfaces.IMenuData
            public boolean IsGameLibLoaded() {
                return iMenuData.IsGameLibLoaded();
            }

            @Override // com.android.support.interfaces.IMenuData
            public String[] SettingsList() {
                return iMenuData.SettingsList();
            }

            @Override // com.android.support.interfaces.IMenuData
            public View.OnTouchListener onTouchListener() {
                return DZTheme.this.windowManager.onTouchListener();
            }
        });
        this.shared.menu.build();
    }

    @Override // com.android.support.interfaces.IMenuTheme
    public void SetWindowManagerActivity() {
        this.windowManager.SetWindowManagerActivity();
    }

    @Override // com.android.support.interfaces.IMenuTheme
    public void SetWindowManagerWindowService() {
        this.windowManager.SetWindowManagerWindowService();
    }

    @Override // com.android.support.interfaces.IMenuTheme
    public void ShowMenu() {
        this.shared.menu.show();
    }

    @Override // com.android.support.interfaces.IMenuTheme
    public void onDestroy() {
        this.shared.menu.destroy();
    }

    @Override // com.android.support.interfaces.IMenuTheme
    public void setVisibility(int i) {
        this.shared.menu.setVisibility(i);
    }
}
