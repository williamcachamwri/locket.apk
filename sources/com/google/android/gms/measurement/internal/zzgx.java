package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
final class zzgx implements Runnable {
    private final /* synthetic */ boolean zza;
    private final /* synthetic */ zzgy zzb;

    zzgx(zzgy zzgy, boolean z) {
        this.zza = z;
        this.zzb = zzgy;
    }

    public final void run() {
        this.zzb.zza.zza(this.zza);
    }
}
