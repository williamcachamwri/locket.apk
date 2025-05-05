package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
final class zziu implements Runnable {
    private final /* synthetic */ zzbf zza;
    private final /* synthetic */ zzo zzb;
    private final /* synthetic */ zzic zzc;

    zziu(zzic zzic, zzbf zzbf, zzo zzo) {
        this.zza = zzbf;
        this.zzb = zzo;
        this.zzc = zzic;
    }

    public final void run() {
        this.zzc.zzc(this.zzc.zzb(this.zza, this.zzb), this.zzb);
    }
}
