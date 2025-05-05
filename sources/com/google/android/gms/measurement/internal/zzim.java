package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
final class zzim implements Runnable {
    private final /* synthetic */ zzae zza;
    private final /* synthetic */ zzic zzb;

    zzim(zzic zzic, zzae zzae) {
        this.zza = zzae;
        this.zzb = zzic;
    }

    public final void run() {
        this.zzb.zza.zzr();
        if (this.zza.zzc.zza() == null) {
            this.zzb.zza.zza(this.zza);
        } else {
            this.zzb.zza.zzb(this.zza);
        }
    }
}
