package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzla implements Runnable {
    private final /* synthetic */ zzje zza;
    private final /* synthetic */ long zzb;
    private final /* synthetic */ boolean zzc;
    private final /* synthetic */ zzje zzd;
    private final /* synthetic */ zzjq zze;

    zzla(zzjq zzjq, zzje zzje, long j, boolean z, zzje zzje2) {
        this.zza = zzje;
        this.zzb = j;
        this.zzc = z;
        this.zzd = zzje2;
        this.zze = zzjq;
    }

    public final void run() {
        this.zze.zza(this.zza);
        zzjq.zza(this.zze, this.zza, this.zzb, false, this.zzc);
        zzjq.zza(this.zze, this.zza, this.zzd);
    }
}
