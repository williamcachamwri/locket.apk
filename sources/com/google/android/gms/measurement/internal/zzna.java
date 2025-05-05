package com.google.android.gms.measurement.internal;

import android.content.Intent;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
public final /* synthetic */ class zzna implements Runnable {
    private /* synthetic */ zzmy zza;
    private /* synthetic */ int zzb;
    private /* synthetic */ zzgo zzc;
    private /* synthetic */ Intent zzd;

    public /* synthetic */ zzna(zzmy zzmy, int i, zzgo zzgo, Intent intent) {
        this.zza = zzmy;
        this.zzb = i;
        this.zzc = zzgo;
        this.zzd = intent;
    }

    public final void run() {
        this.zza.zza(this.zzb, this.zzc, this.zzd);
    }
}
