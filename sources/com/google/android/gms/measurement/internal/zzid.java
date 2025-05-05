package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzid implements Runnable {
    private final /* synthetic */ zzjo zza;
    private final /* synthetic */ zzhy zzb;

    zzid(zzhy zzhy, zzjo zzjo) {
        this.zza = zzjo;
        this.zzb = zzhy;
    }

    public final void run() {
        zzhy.zza(this.zzb, this.zza);
        this.zzb.zza(this.zza.zzg);
    }
}
