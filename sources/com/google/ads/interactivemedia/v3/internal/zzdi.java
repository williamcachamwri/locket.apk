package com.google.ads.interactivemedia.v3.internal;

import android.webkit.WebView;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzdi implements Runnable {
    final /* synthetic */ zzdj zza;
    private final WebView zzb;

    zzdi(zzdj zzdj) {
        this.zza = zzdj;
        this.zzb = zzdj.zza;
    }

    public final void run() {
        this.zzb.destroy();
    }
}
