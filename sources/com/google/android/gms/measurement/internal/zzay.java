package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzay implements Runnable {
    private final /* synthetic */ zzjc zza;
    private final /* synthetic */ zzav zzb;

    zzay(zzav zzav, zzjc zzjc) {
        this.zza = zzjc;
        this.zzb = zzav;
    }

    public final void run() {
        this.zza.zzd();
        if (zzab.zza()) {
            this.zza.zzl().zzb((Runnable) this);
            return;
        }
        boolean zzc = this.zzb.zzc();
        this.zzb.zzd = 0;
        if (zzc) {
            this.zzb.zzb();
        }
    }
}
