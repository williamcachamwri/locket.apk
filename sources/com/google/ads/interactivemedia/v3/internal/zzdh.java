package com.google.ads.interactivemedia.v3.internal;

import android.webkit.RenderProcessGoneDetail;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import io.sentry.android.core.SentryLogcatAdapter;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzdh extends WebViewClient {
    final /* synthetic */ zzdj zza;

    zzdh(zzdj zzdj) {
        this.zza = zzdj;
    }

    public final boolean onRenderProcessGone(WebView webView, RenderProcessGoneDetail renderProcessGoneDetail) {
        String obj = renderProcessGoneDetail.toString();
        String valueOf = String.valueOf(webView);
        SentryLogcatAdapter.w("NativeBridge", "WebView renderer gone: " + obj + "for WebView: " + valueOf);
        if (this.zza.zza() == webView) {
            SentryLogcatAdapter.w("NativeBridge", "Deallocating the Native bridge as it is unusable. No further events will be generated for this session.");
            this.zza.zzm((WebView) null);
        }
        webView.destroy();
        return true;
    }
}
