package com.google.ads.interactivemedia.v3.internal;

import android.webkit.WebView;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzcx implements Runnable {
    final /* synthetic */ WebView zza;
    final /* synthetic */ String zzb;

    zzcx(zzcy zzcy, WebView webView, String str) {
        this.zza = webView;
        this.zzb = str;
    }

    public final void run() {
        zzcy.zzj(this.zza, this.zzb);
    }
}
