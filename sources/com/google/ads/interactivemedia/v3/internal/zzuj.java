package com.google.ads.interactivemedia.v3.internal;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzuj {
    private final zzrm zza;

    /* synthetic */ zzuj(boolean z, zzrm zzrm, zzui zzui) {
        this.zza = zzrm;
    }

    public final zzuu zza(Callable callable, Executor executor) {
        return new zztz(this.zza, false, executor, callable);
    }
}
