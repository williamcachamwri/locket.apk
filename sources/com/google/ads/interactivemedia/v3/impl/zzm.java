package com.google.ads.interactivemedia.v3.impl;

import com.google.ads.interactivemedia.v3.api.StreamRequest;
import com.google.ads.interactivemedia.v3.internal.zzuu;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final /* synthetic */ class zzm implements Runnable {
    public final /* synthetic */ zzy zza;
    public final /* synthetic */ zzuu zzb;
    public final /* synthetic */ StreamRequest zzc;
    public final /* synthetic */ String zzd;

    public /* synthetic */ zzm(zzy zzy, zzuu zzuu, StreamRequest streamRequest, String str) {
        this.zza = zzy;
        this.zzb = zzuu;
        this.zzc = streamRequest;
        this.zzd = str;
    }

    public final void run() {
        this.zza.zzq(this.zzb, this.zzc, this.zzd);
    }
}
