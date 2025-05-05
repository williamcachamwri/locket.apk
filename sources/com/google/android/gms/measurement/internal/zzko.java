package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzko implements Runnable {
    private final /* synthetic */ Bundle zza;
    private final /* synthetic */ zzjq zzb;

    zzko(zzjq zzjq, Bundle bundle) {
        this.zza = bundle;
        this.zzb = zzjq;
    }

    public final void run() {
        zzjq.zzb(this.zzb, this.zza);
    }
}
