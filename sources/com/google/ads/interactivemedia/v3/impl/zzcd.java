package com.google.ads.interactivemedia.v3.impl;

import android.view.ViewGroup;
import android.webkit.WebView;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzcd {
    private final WebView zza;
    private final ViewGroup zzb;

    public zzcd(WebView webView, ViewGroup viewGroup) {
        this.zza = webView;
        this.zzb = viewGroup;
    }

    public final void zza() {
        this.zza.setVisibility(4);
    }

    public final void zzb() {
        if (((ViewGroup) this.zza.getParent()) == null) {
            this.zzb.addView(this.zza, new ViewGroup.LayoutParams(-1, -1));
        }
        this.zza.setVisibility(0);
        this.zzb.bringChildToFront(this.zza);
    }
}
