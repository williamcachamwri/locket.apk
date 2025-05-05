package com.google.ads.interactivemedia.v3.internal;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzuw implements Executor {
    final /* synthetic */ Executor zza;
    final /* synthetic */ zztg zzb;

    zzuw(Executor executor, zztg zztg) {
        this.zza = executor;
        this.zzb = zztg;
    }

    public final void execute(Runnable runnable) {
        try {
            this.zza.execute(runnable);
        } catch (RejectedExecutionException e) {
            this.zzb.zzd(e);
        }
    }
}
