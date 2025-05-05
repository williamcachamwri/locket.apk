package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzkv implements Runnable {
    private final /* synthetic */ Boolean zza;
    private final /* synthetic */ zzjq zzb;

    zzkv(zzjq zzjq, Boolean bool) {
        this.zza = bool;
        this.zzb = zzjq;
    }

    public final void run() {
        this.zzb.zza(this.zza, true);
    }
}
