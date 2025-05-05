package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
final class zziz implements Runnable {
    private final /* synthetic */ zzon zza;
    private final /* synthetic */ zzo zzb;
    private final /* synthetic */ zzic zzc;

    zziz(zzic zzic, zzon zzon, zzo zzo) {
        this.zza = zzon;
        this.zzb = zzo;
        this.zzc = zzic;
    }

    public final void run() {
        this.zzc.zza.zzr();
        if (this.zza.zza() == null) {
            this.zzc.zza.zza(this.zza.zza, this.zzb);
        } else {
            this.zzc.zza.zza(this.zza, this.zzb);
        }
    }
}
