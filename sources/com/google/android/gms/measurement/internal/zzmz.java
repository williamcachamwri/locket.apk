package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
final class zzmz implements Runnable {
    private final /* synthetic */ zznv zza;
    private final /* synthetic */ Runnable zzb;

    zzmz(zzmy zzmy, zznv zznv, Runnable runnable) {
        this.zza = zznv;
        this.zzb = runnable;
    }

    public final void run() {
        this.zza.zzr();
        this.zza.zza(this.zzb);
        this.zza.zzw();
    }
}
