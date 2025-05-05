package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzne implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zznb zzb;

    zzne(zznb zznb, long j) {
        this.zza = j;
        this.zzb = zznb;
    }

    public final void run() {
        zznb.zzb(this.zzb, this.zza);
    }
}
