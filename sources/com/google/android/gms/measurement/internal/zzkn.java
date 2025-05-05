package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzkn implements Runnable {
    private final /* synthetic */ Bundle zza;
    private final /* synthetic */ zzjq zzb;

    zzkn(zzjq zzjq, Bundle bundle) {
        this.zza = bundle;
        this.zzb = zzjq;
    }

    public final void run() {
        zzjq.zza(this.zzb, this.zza);
    }
}
