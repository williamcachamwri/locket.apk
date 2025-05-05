package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzka implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zzjq zzb;

    zzka(zzjq zzjq, AtomicReference atomicReference) {
        this.zza = atomicReference;
        this.zzb = zzjq;
    }

    public final void run() {
        synchronized (this.zza) {
            try {
                this.zza.set(Boolean.valueOf(this.zzb.zze().zzi(this.zzb.zzg().zzad())));
                this.zza.notify();
            } catch (Throwable th) {
                this.zza.notify();
                throw th;
            }
        }
    }
}
