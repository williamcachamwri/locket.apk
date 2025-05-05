package com.iab.omid.library.adsbynimbus.utils;

import android.webkit.WebView;
import androidx.webkit.WebViewCompat;
import java.util.Set;

public class i {
    public void a(WebView webView, String str) {
        WebViewCompat.removeWebMessageListener(webView, str);
    }

    public void a(WebView webView, String str, Set<String> set, WebViewCompat.WebMessageListener webMessageListener) {
        WebViewCompat.addWebMessageListener(webView, str, set, webMessageListener);
    }
}
