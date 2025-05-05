package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
final class zzil implements Runnable {
    private final /* synthetic */ zzo zza;
    private final /* synthetic */ zzic zzb;

    zzil(zzic zzic, zzo zzo) {
        this.zza = zzo;
        this.zzb = zzic;
    }

    public final void run() {
        this.zzb.zza.zzr();
        this.zzb.zza.zzc(this.zza);
    }
}
