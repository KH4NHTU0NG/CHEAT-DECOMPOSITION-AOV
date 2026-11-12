package com.android.support.ThemesData.CYGNUS;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import com.android.support.SoundCloudMusicService;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: /tmp/apk_work/classes7.dex */
final class CygnusMusicPanel implements SoundCloudMusicService.Controls {
    private String currentTrack;
    private boolean disposed;
    private boolean initialized;
    private final String origin;
    private WebView player;
    private boolean playing;
    private final SharedPreferences prefs;
    private WebView searchBrowser;
    private boolean searchLoaded;
    private String searchUrl = "https://soundcloud.com/search/sounds?q=nh%E1%BA%A1c";
    private final CygnusShared shared;
    private TextView status;

    public CygnusMusicPanel(CygnusShared cygnusShared) {
        this.shared = cygnusShared;
        this.prefs = cygnusShared.getContext.getSharedPreferences("cygnus_soundcloud", 0);
        this.origin = "https://" + cygnusShared.getContext.getPackageName() + ".soundcloud-menu.local/";
    }

    private WebView browser() {
        WebView webView = new WebView(this.shared.getContext);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        webView.getSettings().setAllowFileAccess(false);
        webView.getSettings().setAllowContentAccess(false);
        if (Build.VERSION.SDK_INT >= 26) {
            webView.setRendererPriorityPolicy(2, false);
        }
        webView.setBackgroundColor(this.shared.MenuStyle.getMenuBgColor());
        webView.setOnTouchListener(new View.OnTouchListener() { // from class: com.android.support.ThemesData.CYGNUS.CygnusMusicPanel.5
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (view.getParent() != null) {
                    view.getParent().requestDisallowInterceptTouchEvent((motionEvent.getActionMasked() == 1 || motionEvent.getActionMasked() == 3) ? false : true);
                }
                return false;
            }
        });
        return webView;
    }

    private Button button(String str, View.OnClickListener onClickListener) {
        Button button = new Button(this.shared.getContext);
        button.setText(str);
        button.setOnClickListener(onClickListener);
        return button;
    }

    private void createBrowsers() {
        this.disposed = false;
        SoundCloudMusicService.connect(this);
        WebView webViewBrowser = browser();
        this.player = webViewBrowser;
        useDesktopBrowser(webViewBrowser);
        this.player.setWebViewClient(new WebViewClient() { // from class: com.android.support.ThemesData.CYGNUS.CygnusMusicPanel.6
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                String host = Uri.parse(str).getHost();
                return ("w.soundcloud.com".equals(host) || "api.soundcloud.com".equals(host)) ? false : true;
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                return webResourceRequest.isForMainFrame();
            }
        });
        this.player.setWebChromeClient(new WebChromeClient() { // from class: com.android.support.ThemesData.CYGNUS.CygnusMusicPanel.7
            @Override // android.webkit.WebChromeClient
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                if (CygnusMusicPanel.this.origin.equals(consoleMessage.sourceId()) && !CygnusMusicPanel.this.disposed) {
                    if ("SC_MENU_PLAY".equals(consoleMessage.message())) {
                        CygnusMusicPanel.this.playing = true;
                        try {
                            SoundCloudMusicService.report(CygnusMusicPanel.this.shared.getContext, true);
                        } catch (RuntimeException unused) {
                            CygnusMusicPanel.this.status.setText("Không khởi động được phát nền. Kiểm tra service/quyền trong APK.");
                        }
                        return true;
                    }
                    if ("SC_MENU_PAUSE".equals(consoleMessage.message())) {
                        CygnusMusicPanel.this.playing = false;
                        try {
                            SoundCloudMusicService.report(CygnusMusicPanel.this.shared.getContext, false);
                        } catch (RuntimeException unused2) {
                            CygnusMusicPanel.this.status.setText("Không cập nhật được thông báo phát nền.");
                        }
                        return true;
                    }
                    if ("SC_MENU_ERROR".equals(consoleMessage.message())) {
                        CygnusMusicPanel.this.playing = false;
                        try {
                            SoundCloudMusicService.report(CygnusMusicPanel.this.shared.getContext, false);
                        } catch (RuntimeException unused3) {
                        }
                        CygnusMusicPanel.this.status.setText("Bài này không phát được hoặc không cho phép nhúng. Thử bài khác.");
                        return true;
                    }
                }
                return false;
            }
        });
        WebView webViewBrowser2 = browser();
        this.searchBrowser = webViewBrowser2;
        useDesktopBrowser(webViewBrowser2);
        this.searchBrowser.getSettings().setUseWideViewPort(true);
        this.searchBrowser.getSettings().setLoadWithOverviewMode(true);
        this.searchBrowser.getSettings().setBuiltInZoomControls(true);
        this.searchBrowser.getSettings().setDisplayZoomControls(false);
        this.searchBrowser.setWebChromeClient(new WebChromeClient());
        this.searchBrowser.setWebViewClient(new WebViewClient() { // from class: com.android.support.ThemesData.CYGNUS.CygnusMusicPanel.8
            private boolean navigate(String str) {
                Uri uri = Uri.parse(str);
                if ("m.soundcloud.com".equals(uri.getHost())) {
                    CygnusMusicPanel.this.searchBrowser.loadUrl(uri.buildUpon().authority("soundcloud.com").build().toString());
                    return true;
                }
                String strTrackUrl = CygnusMusicPanel.trackUrl(str);
                if (strTrackUrl == null) {
                    return !CygnusMusicPanel.soundCloudUrl(str);
                }
                CygnusMusicPanel.this.loadTrack(strTrackUrl);
                return true;
            }

            @Override // android.webkit.WebViewClient
            public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
                String strTrackUrl = CygnusMusicPanel.trackUrl(str);
                if (strTrackUrl != null) {
                    CygnusMusicPanel.this.loadTrack(strTrackUrl);
                }
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView, String str) {
                if (!CygnusMusicPanel.this.disposed && CygnusMusicPanel.this.searchLoaded && CygnusMusicPanel.soundCloudUrl(str)) {
                    webView.evaluateJavascript("(function(){var m=document.querySelector('meta[name=viewport]');if(!m){m=document.createElement('meta');m.name='viewport';document.head.appendChild(m);}m.content='" + ("width=960,initial-scale=" + Float.toString(Math.max(0.25f, Math.min(1.0f, (webView.getWidth() / CygnusMusicPanel.this.shared.getContext.getResources().getDisplayMetrics().density) / 960.0f))) + ",minimum-scale=0.25,maximum-scale=3,user-scalable=yes") + "';})()", null);
                    if (CygnusMusicPanel.soundCloudUrl(str)) {
                        webView.evaluateJavascript(CygnusMusicPanel.dismissAccountPromptScript(), null);
                    }
                    CygnusMusicPanel.this.status.setText("Chạm TÊN BÀI trong kết quả để phát. Nếu trang trống, bấm Tải lại hoặc dán link bài.");
                }
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                if (!webResourceRequest.isForMainFrame() || CygnusMusicPanel.this.disposed) {
                    return;
                }
                CygnusMusicPanel.this.status.setText("Không tải được SoundCloud: " + ((Object) webResourceError.getDescription()) + ". Bấm Tải lại.");
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                return navigate(str);
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                return webResourceRequest.isForMainFrame() && navigate(webResourceRequest.getUrl().toString());
            }
        });
    }

    private void detach(View view) {
        if (view == null || !(view.getParent() instanceof ViewGroup)) {
            return;
        }
        ((ViewGroup) view.getParent()).removeView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String dismissAccountPromptScript() {
        return "(function(){if(window.__menuTrackClickHandler)return;window.__menuTrackClickHandler=function(e){var el=e.target instanceof Element?e.target:null;if(!el)return;var title=el.closest('a.soundTitle__title');var play=el.closest('.soundTitle__playButton .playButton');if(!title&&play){var row=play.closest('.soundTitle__titleContainer');if(row)title=row.querySelector('a.soundTitle__title');}if(!title||!title.href)return;var u=new URL(title.href,location.href);if(u.protocol!=='https:'||u.hostname!=='soundcloud.com')return;e.preventDefault();e.stopImmediatePropagation();window.location.href=u.href;};document.addEventListener('click',window.__menuTrackClickHandler,true);if(window.__menuAccountPromptObserver)window.__menuAccountPromptObserver.disconnect();if(window.__menuAccountPromptTimer)clearInterval(window.__menuAccountPromptTimer);function closePrompt(){document.querySelectorAll('.modal.auth-modal').forEach(function(m){if(!m.getClientRects().length)return;var b=m.querySelector('button.modal__closeButton');if(b&&!b.disabled)b.click();});}var queued=false;window.__menuAccountPromptObserver=new MutationObserver(function(){if(queued)return;queued=true;setTimeout(function(){queued=false;closePrompt();},0);});window.__menuAccountPromptObserver.observe(document.documentElement,{childList:true,subtree:true});closePrompt();})()";
    }

    private int dp(int i) {
        return (int) (i * this.shared.getContext.getResources().getDisplayMetrics().density);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadTrack(String str) {
        if (this.disposed || this.player == null) {
            return;
        }
        releaseSearchPage();
        if (str.equals(this.currentTrack)) {
            play();
            return;
        }
        this.currentTrack = str;
        this.prefs.edit().putString("last", str).apply();
        this.player.loadDataWithBaseURL(this.origin, "<!doctype html><html><head><meta name='viewport' content='width=device-width,initial-scale=1'></head><body style='margin:0;background:#15171b'><iframe id='sc' width='100%' height='166' scrolling='no' frameborder='0' allow='autoplay' src='" + ("https://w.soundcloud.com/player/?url=" + Uri.encode(str) + "&auto_play=true&visual=false&show_artwork=false&show_comments=false&show_user=false&show_reposts=false&show_teaser=false").replace("&", "&amp;") + "'></iframe><script src='https://w.soundcloud.com/player/api.js'></script><script>window.sc=SC.Widget(document.getElementById('sc'));sc.bind(SC.Widget.Events.PLAY,function(){console.log('SC_MENU_PLAY');});sc.bind(SC.Widget.Events.PAUSE,function(){console.log('SC_MENU_PAUSE');});sc.bind(SC.Widget.Events.FINISH,function(){console.log('SC_MENU_PAUSE');});sc.bind(SC.Widget.Events.ERROR,function(){console.log('SC_MENU_ERROR');});</script></body></html>", "text/html", "UTF-8", null);
        this.status.setText("Chạm Play trên trình phát nếu bài chưa tự chạy. Điều khiển từ thông báo; bấm Tìm / Dán link để đổi bài.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openSearchPage() {
        WebView webView;
        if (this.disposed || (webView = this.searchBrowser) == null) {
            return;
        }
        webView.setVisibility(0);
        this.searchBrowser.onResume();
        this.searchLoaded = true;
        this.searchBrowser.loadUrl(this.searchUrl);
    }

    private void releaseSearchPage() {
        WebView webView = this.searchBrowser;
        if (webView == null || !this.searchLoaded) {
            return;
        }
        this.searchLoaded = false;
        webView.setVisibility(8);
        this.searchBrowser.stopLoading();
        this.searchBrowser.loadUrl("about:blank");
        this.searchBrowser.onPause();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSearch() {
        final EditText editText = new EditText(this.shared.getContext);
        editText.setSingleLine(true);
        editText.setHint("Tên bài, ca sĩ hoặc link SoundCloud đầy đủ");
        AlertDialog alertDialogCreate = new AlertDialog.Builder(this.shared.getContext).setTitle("Tìm nhạc SoundCloud").setView(editText).setNegativeButton("Huỷ", (DialogInterface.OnClickListener) null).setPositiveButton("Tìm / Phát", new DialogInterface.OnClickListener() { // from class: com.android.support.ThemesData.CYGNUS.CygnusMusicPanel.9
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                String strTrim = editText.getText().toString().trim();
                if (strTrim.isEmpty()) {
                    return;
                }
                String strTrackUrl = CygnusMusicPanel.trackUrl(strTrim);
                if (strTrackUrl != null) {
                    CygnusMusicPanel.this.loadTrack(strTrackUrl);
                    return;
                }
                CygnusMusicPanel.this.searchUrl = "https://soundcloud.com/search/sounds?q=" + Uri.encode(strTrim);
                CygnusMusicPanel.this.openSearchPage();
            }
        }).create();
        if (this.shared.overlayRequired && alertDialogCreate.getWindow() != null) {
            alertDialogCreate.getWindow().setType(Build.VERSION.SDK_INT >= 26 ? 2038 : 2002);
        }
        alertDialogCreate.show();
        editText.requestFocus();
        if (alertDialogCreate.getWindow() != null) {
            alertDialogCreate.getWindow().setSoftInputMode(5);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean soundCloudUrl(String str) {
        Uri uri = Uri.parse(str);
        String host = uri.getHost();
        return "https".equals(uri.getScheme()) && host != null && (host.equals("soundcloud.com") || host.equals("www.soundcloud.com") || host.equals("m.soundcloud.com"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String trackUrl(String str) {
        if (!soundCloudUrl(str)) {
            return null;
        }
        Uri uri = Uri.parse(str);
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments.size() != 2 && (pathSegments.size() != 3 || !"sets".equals(pathSegments.get(1)))) {
            return null;
        }
        if (Arrays.asList("search", "discover", "you", "charts", "upload", "pages", "settings", "terms-of-use", "privacy", "signin", "stations", "stream").contains(pathSegments.get(0))) {
            return null;
        }
        if (pathSegments.size() == 2 && Arrays.asList("tracks", "albums", "sets", "reposts", "likes", "followers", "following", "popular-tracks").contains(pathSegments.get(1))) {
            return null;
        }
        return uri.buildUpon().scheme("https").authority("soundcloud.com").fragment(null).build().toString();
    }

    private void useDesktopBrowser(WebView webView) {
        webView.getSettings().setUserAgentString(webView.getSettings().getUserAgentString().replaceFirst("\\([^)]*\\)", "(Windows NT 10.0; Win64; x64)").replace(" Mobile", "").replace(" Version/4.0", ""));
    }

    public void build(LinearLayout linearLayout) {
        TextView textView = new TextView(this.shared.getContext);
        textView.setText("NHẠC • SOUNDCLOUD");
        textView.setTextColor(this.shared.MenuStyle.getAccentColor());
        textView.setTextSize(16.0f);
        linearLayout.addView(textView);
        LinearLayout linearLayout2 = new LinearLayout(this.shared.getContext);
        linearLayout2.addView(button("Tìm / Dán link", new View.OnClickListener() { // from class: com.android.support.ThemesData.CYGNUS.CygnusMusicPanel.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CygnusMusicPanel.this.showSearch();
            }
        }), new LinearLayout.LayoutParams(0, -2, 1.0f));
        linearLayout2.addView(button("Phát / Dừng", new View.OnClickListener() { // from class: com.android.support.ThemesData.CYGNUS.CygnusMusicPanel.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CygnusMusicPanel.this.playing) {
                    CygnusMusicPanel.this.pause();
                } else {
                    CygnusMusicPanel.this.play();
                }
            }
        }), new LinearLayout.LayoutParams(0, -2, 1.0f));
        linearLayout2.addView(button("Tải lại", new View.OnClickListener() { // from class: com.android.support.ThemesData.CYGNUS.CygnusMusicPanel.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CygnusMusicPanel.this.openSearchPage();
            }
        }), new LinearLayout.LayoutParams(0, -2, 1.0f));
        linearLayout.addView(linearLayout2);
        Switch r0 = new Switch(this.shared.getContext);
        r0.setText("Tự phát bài đã lưu lần đầu mở mục Nhạc");
        r0.setTextColor(this.shared.MenuStyle.getTextColor2());
        r0.setChecked(this.prefs.getBoolean("auto", false));
        r0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.android.support.ThemesData.CYGNUS.CygnusMusicPanel.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                CygnusMusicPanel.this.prefs.edit().putBoolean("auto", z).apply();
            }
        });
        linearLayout.addView(r0);
        TextView textView2 = new TextView(this.shared.getContext);
        this.status = textView2;
        textView2.setTextColor(this.shared.MenuStyle.getTextColor2());
        this.status.setText("Tìm nhạc rồi chạm TÊN BÀI để phát. Nhạc tiếp tục khi chuyển tab hoặc thu nhỏ menu.");
        linearLayout.addView(this.status);
        if (this.player == null) {
            createBrowsers();
        }
        detach(this.player);
        detach(this.searchBrowser);
        linearLayout.addView(this.player, new LinearLayout.LayoutParams(-1, dp(166)));
        linearLayout.addView(this.searchBrowser, new LinearLayout.LayoutParams(-1, dp(300)));
    }

    public void destroy() {
        this.disposed = true;
        this.playing = false;
        SoundCloudMusicService.disconnect(this.shared.getContext, this);
        WebView webView = this.player;
        if (webView != null) {
            detach(webView);
            this.player.stopLoading();
            this.player.destroy();
            this.player = null;
        }
        WebView webView2 = this.searchBrowser;
        if (webView2 != null) {
            detach(webView2);
            this.searchBrowser.stopLoading();
            this.searchBrowser.destroy();
            this.searchBrowser = null;
        }
    }

    public void hide() {
        releaseSearchPage();
    }

    @Override // com.android.support.SoundCloudMusicService.Controls
    public void pause() {
        WebView webView = this.player;
        if (webView == null || this.disposed) {
            return;
        }
        webView.evaluateJavascript("if(window.sc)sc.pause();", null);
    }

    @Override // com.android.support.SoundCloudMusicService.Controls
    public void play() {
        WebView webView = this.player;
        if (webView == null || this.disposed) {
            return;
        }
        webView.evaluateJavascript("if(window.sc)sc.play();", null);
    }

    public void show() {
        if (this.player == null || this.disposed) {
            return;
        }
        if (this.initialized) {
            if (this.currentTrack != null || this.searchLoaded) {
                return;
            }
            openSearchPage();
            return;
        }
        this.initialized = true;
        String string = this.prefs.getString("last", "");
        if (!this.prefs.getBoolean("auto", false) || trackUrl(string) == null) {
            openSearchPage();
        } else {
            loadTrack(string);
        }
    }
}
