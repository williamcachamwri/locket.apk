package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzkl implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zzjq zzb;

    zzkl(zzjq zzjq, long j) {
        this.zza = j;
        this.zzb = zzjq;
    }

    public final void run() {
        this.zzb.zzb(this.zza);
        this.zzb.zzo().zza((AtomicReference<String>) new AtomicReference());
    }
}
