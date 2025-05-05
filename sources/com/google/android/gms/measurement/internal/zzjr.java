package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final /* synthetic */ class zzjr implements Runnable {
    private /* synthetic */ zzjq zza;
    private /* synthetic */ AtomicReference zzb;

    public /* synthetic */ zzjr(zzjq zzjq, AtomicReference atomicReference) {
        this.zza = zzjq;
        this.zzb = atomicReference;
    }

    public final void run() {
        this.zza.zza(this.zzb);
    }
}
