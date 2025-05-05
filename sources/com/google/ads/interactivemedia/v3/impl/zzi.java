package com.google.ads.interactivemedia.v3.impl;

import com.google.ads.interactivemedia.v3.api.StreamRequest;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final /* synthetic */ class zzi implements Runnable {
    public final /* synthetic */ zzy zza;
    public final /* synthetic */ StreamRequest zzb;
    public final /* synthetic */ String zzc;

    public /* synthetic */ zzi(zzy zzy, StreamRequest streamRequest, String str) {
        this.zza = zzy;
        this.zzb = streamRequest;
        this.zzc = str;
    }

    public final void run() {
        this.zza.zzk(this.zzb, this.zzc);
    }
}
