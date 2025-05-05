package com.google.ads.interactivemedia.v3.impl;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.ads.interactivemedia.v3.internal.zzahg;
import com.google.ads.interactivemedia.v3.internal.zzahh;
import com.google.ads.interactivemedia.v3.internal.zzahj;
import com.google.ads.interactivemedia.v3.internal.zzfk;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzbj extends WebViewClient {
    final /* synthetic */ zzbk zza;
    private final zzahj zzb;
    private long zzc;

    zzbj(zzbk zzbk, zzahj zzahj) {
        this.zza = zzbk;
        this.zzb = zzahj;
    }

    public final void onPageFinished(WebView webView, String str) {
        zzahg zzc2 = zzahh.zzc();
        zzc2.zzb(this.zzc);
        zzc2.zza(System.currentTimeMillis());
        this.zzb.zzd(zzc2);
        zzfk.zzc("Finished loading WebView".concat(String.valueOf(str)));
    }

    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        this.zzc = System.currentTimeMillis();
        zzfk.zzc("Started loading WebView".concat(String.valueOf(str)));
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        zzfk.zzc("Error: " + i + " " + str + " " + str2);
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (!str.startsWith("gmsg://")) {
            return false;
        }
        this.zza.zzh(str, "0");
        return true;
    }
}
