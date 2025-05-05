package com.google.ads.interactivemedia.v3.impl;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzan extends WebViewClient {
    final /* synthetic */ zzao zza;

    zzan(zzao zzao) {
        this.zza = zzao;
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (!this.zza.zzb.zzb(str)) {
            return true;
        }
        for (CompanionAdSlot.ClickListener onCompanionAdClick : this.zza.zzc) {
            onCompanionAdClick.onCompanionAdClick();
        }
        return true;
    }
}
