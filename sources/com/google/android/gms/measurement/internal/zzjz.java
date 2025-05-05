package com.google.android.gms.measurement.internal;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzjz implements Executor {
    private final /* synthetic */ zzjq zza;

    zzjz(zzjq zzjq) {
        this.zza = zzjq;
    }

    public final void execute(Runnable runnable) {
        this.zza.zzl().zzb(runnable);
    }
}
