package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
final class zznx implements Runnable {
    private final /* synthetic */ zzok zza;
    private final /* synthetic */ zznv zzb;

    zznx(zznv zznv, zzok zzok) {
        this.zza = zzok;
        this.zzb = zznv;
    }

    public final void run() {
        zznv.zza(this.zzb, this.zza);
        this.zzb.zzv();
    }
}
