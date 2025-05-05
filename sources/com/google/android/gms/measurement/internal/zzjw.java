package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final /* synthetic */ class zzjw implements Runnable {
    private /* synthetic */ zzjq zza;
    private /* synthetic */ Bundle zzb;
    private /* synthetic */ long zzc;

    public /* synthetic */ zzjw(zzjq zzjq, Bundle bundle, long j) {
        this.zza = zzjq;
        this.zzb = bundle;
        this.zzc = j;
    }

    public final void run() {
        this.zza.zza(this.zzb, this.zzc);
    }
}
