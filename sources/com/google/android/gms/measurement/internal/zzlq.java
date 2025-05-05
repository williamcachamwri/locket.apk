package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzlq implements Runnable {
    private final /* synthetic */ zzlk zza;
    private final /* synthetic */ long zzb;
    private final /* synthetic */ zzlj zzc;

    zzlq(zzlj zzlj, zzlk zzlk, long j) {
        this.zza = zzlk;
        this.zzb = j;
        this.zzc = zzlj;
    }

    public final void run() {
        this.zzc.zza(this.zza, false, this.zzb);
        this.zzc.zza = null;
        this.zzc.zzo().zza((zzlk) null);
    }
}
