package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zznm;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzkx implements Runnable {
    private final /* synthetic */ zzje zza;
    private final /* synthetic */ long zzb;
    private final /* synthetic */ long zzc;
    private final /* synthetic */ boolean zzd;
    private final /* synthetic */ zzje zze;
    private final /* synthetic */ zzjq zzf;

    zzkx(zzjq zzjq, zzje zzje, long j, long j2, boolean z, zzje zzje2) {
        this.zza = zzje;
        this.zzb = j;
        this.zzc = j2;
        this.zzd = z;
        this.zze = zzje2;
        this.zzf = zzjq;
    }

    public final void run() {
        this.zzf.zza(this.zza);
        if (!zznm.zza() || !this.zzf.zze().zza(zzbh.zzcx)) {
            this.zzf.zza(this.zzb, false);
        }
        zzjq.zza(this.zzf, this.zza, this.zzc, true, this.zzd);
        zzjq.zza(this.zzf, this.zza, this.zze);
    }
}
