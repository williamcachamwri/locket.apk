package com.google.ads.interactivemedia.v3.impl;

import android.content.Context;
import android.os.Message;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.google.ads.interactivemedia.v3.internal.zzgi;
import java.util.List;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzao extends WebChromeClient {
    final /* synthetic */ Context zza;
    final /* synthetic */ zzgi zzb;
    final /* synthetic */ List zzc;

    zzao(Context context, zzgi zzgi, List list) {
        this.zza = context;
        this.zzb = zzgi;
        this.zzc = list;
    }

    public final boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
        WebView webView2 = new WebView(this.zza);
        ((WebView.WebViewTransport) message.obj).setWebView(webView2);
        webView2.setWebViewClient(new zzan(this));
        message.sendToTarget();
        return true;
    }
}
