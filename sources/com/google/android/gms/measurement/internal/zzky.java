package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzky implements Runnable {
    private final /* synthetic */ zzax zza;
    private final /* synthetic */ zzjq zzb;

    zzky(zzjq zzjq, zzax zzax) {
        this.zza = zzax;
        this.zzb = zzjq;
    }

    public final void run() {
        if (this.zzb.zzk().zza(this.zza)) {
            this.zzb.zzj().zzp().zza("Setting DMA consent(FE)", this.zza);
            if (this.zzb.zzo().zzao()) {
                this.zzb.zzo().zzaj();
            } else {
                this.zzb.zzo().zza(false);
            }
        } else {
            this.zzb.zzj().zzo().zza("Lower precedence consent source ignored, proposed source", Integer.valueOf(this.zza.zza()));
        }
    }
}
