package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final /* synthetic */ class zzni implements Runnable {
    private /* synthetic */ zznf zza;

    public /* synthetic */ zzni(zznf zznf) {
        this.zza = zznf;
    }

    public final void run() {
        zznf zznf = this.zza;
        zzng zzng = zznf.zzc;
        long j = zznf.zza;
        long j2 = zznf.zzb;
        zzng.zza.zzt();
        zzng.zza.zzj().zzc().zza("Application going to the background");
        zzng.zza.zzk().zzn.zza(true);
        zzng.zza.zza(true);
        if (!zzng.zza.zze().zzw()) {
            if (zzng.zza.zze().zza(zzbh.zzcp)) {
                zzng.zza.zza(false, false, j2);
                zzng.zza.zzb.zzb(j2);
            } else {
                zzng.zza.zzb.zzb(j2);
                zzng.zza.zza(false, false, j2);
            }
        }
        zzng.zza.zzj().zzo().zza("Application backgrounded at: timestamp_millis", Long.valueOf(j));
        if (zzng.zza.zze().zza(zzbh.zzdd)) {
            zzng.zza.zzm().zzam();
        }
    }
}
