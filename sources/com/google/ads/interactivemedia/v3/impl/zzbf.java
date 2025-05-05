package com.google.ads.interactivemedia.v3.impl;

import com.google.ads.interactivemedia.v3.internal.zzvd;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final /* synthetic */ class zzbf implements Runnable {
    public final /* synthetic */ zzbk zza;
    public final /* synthetic */ String zzb;
    public final /* synthetic */ zzvd zzc;

    public /* synthetic */ zzbf(zzbk zzbk, String str, zzvd zzvd) {
        this.zza = zzbk;
        this.zzb = str;
        this.zzc = zzvd;
    }

    public final void run() {
        this.zza.zzf(this.zzb, this.zzc);
    }
}
