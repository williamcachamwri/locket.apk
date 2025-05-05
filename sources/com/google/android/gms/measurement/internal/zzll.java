package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzll implements Runnable {
    private final /* synthetic */ zzlk zza;
    private final /* synthetic */ zzlk zzb;
    private final /* synthetic */ long zzc;
    private final /* synthetic */ boolean zzd;
    private final /* synthetic */ zzlj zze;

    zzll(zzlj zzlj, zzlk zzlk, zzlk zzlk2, long j, boolean z) {
        this.zza = zzlk;
        this.zzb = zzlk2;
        this.zzc = j;
        this.zzd = z;
        this.zze = zzlj;
    }

    public final void run() {
        this.zze.zza(this.zza, this.zzb, this.zzc, this.zzd, (Bundle) null);
    }
}
