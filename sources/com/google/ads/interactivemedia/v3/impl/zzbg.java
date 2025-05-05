package com.google.ads.interactivemedia.v3.impl;

import android.net.Uri;
import android.webkit.WebView;
import androidx.webkit.JavaScriptReplyProxy;
import androidx.webkit.WebMessageCompat;
import androidx.webkit.WebViewCompat;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzbg implements WebViewCompat.WebMessageListener {
    final /* synthetic */ zzbk zza;

    zzbg(zzbk zzbk) {
        this.zza = zzbk;
    }

    public final void onPostMessage(WebView webView, WebMessageCompat webMessageCompat, Uri uri, boolean z, JavaScriptReplyProxy javaScriptReplyProxy) {
        this.zza.zzh(webMessageCompat.getData(), "4");
    }
}
