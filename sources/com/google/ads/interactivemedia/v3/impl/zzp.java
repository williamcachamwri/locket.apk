package com.google.ads.interactivemedia.v3.impl;

import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.AdsRequest;
import com.google.ads.interactivemedia.v3.internal.zzuu;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final /* synthetic */ class zzp implements Runnable {
    public final /* synthetic */ zzy zza;
    public final /* synthetic */ zzuu zzb;
    public final /* synthetic */ AdDisplayContainer zzc;
    public final /* synthetic */ AdsRequest zzd;
    public final /* synthetic */ String zze;

    public /* synthetic */ zzp(zzy zzy, zzuu zzuu, AdDisplayContainer adDisplayContainer, AdsRequest adsRequest, String str) {
        this.zza = zzy;
        this.zzb = zzuu;
        this.zzc = adDisplayContainer;
        this.zzd = adsRequest;
        this.zze = str;
    }

    public final void run() {
        this.zza.zzp(this.zzb, this.zzc, this.zzd, this.zze);
    }
}
