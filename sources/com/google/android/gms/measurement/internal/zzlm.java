package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzlm implements Runnable {
    private final /* synthetic */ Bundle zza;
    private final /* synthetic */ zzlk zzb;
    private final /* synthetic */ zzlk zzc;
    private final /* synthetic */ long zzd;
    private final /* synthetic */ zzlj zze;

    zzlm(zzlj zzlj, Bundle bundle, zzlk zzlk, zzlk zzlk2, long j) {
        this.zza = bundle;
        this.zzb = zzlk;
        this.zzc = zzlk2;
        this.zzd = j;
        this.zze = zzlj;
    }

    public final void run() {
        zzlj.zza(this.zze, this.zza, this.zzb, this.zzc, this.zzd);
    }
}
