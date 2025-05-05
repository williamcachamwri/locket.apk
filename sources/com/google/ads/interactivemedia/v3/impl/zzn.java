package com.google.ads.interactivemedia.v3.impl;

import com.google.ads.interactivemedia.v3.api.AdsRequest;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final /* synthetic */ class zzn implements Runnable {
    public final /* synthetic */ zzy zza;
    public final /* synthetic */ AdsRequest zzb;
    public final /* synthetic */ String zzc;

    public /* synthetic */ zzn(zzy zzy, AdsRequest adsRequest, String str) {
        this.zza = zzy;
        this.zzb = adsRequest;
        this.zzc = str;
    }

    public final void run() {
        this.zza.zzr(this.zzb, this.zzc);
    }
}
