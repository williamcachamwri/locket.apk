package com.google.ads.interactivemedia.v3.internal;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzvb {
    public static zzuv zza(ExecutorService executorService) {
        zzuv zzuv;
        if (executorService instanceof zzuv) {
            return (zzuv) executorService;
        }
        if (executorService instanceof ScheduledExecutorService) {
            zzuv = new zzva((ScheduledExecutorService) executorService);
        } else {
            zzuv = new zzux(executorService);
        }
        return zzuv;
    }

    public static Executor zzb() {
        return zzua.INSTANCE;
    }

    static Executor zzc(Executor executor, zztg zztg) {
        executor.getClass();
        return executor == zzua.INSTANCE ? executor : new zzuw(executor, zztg);
    }
}
