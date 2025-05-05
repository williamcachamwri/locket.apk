package com.google.ads.interactivemedia.v3.internal;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;
import javax.annotation.CheckForNull;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzvh extends zzub implements RunnableFuture {
    @CheckForNull
    private volatile zzus zza;

    zzvh(Callable callable) {
        this.zza = new zzvg(this, callable);
    }

    static zzvh zzs(Runnable runnable, Object obj) {
        return new zzvh(Executors.callable(runnable, obj));
    }

    public final void run() {
        zzus zzus = this.zza;
        if (zzus != null) {
            zzus.run();
        }
        this.zza = null;
    }

    /* access modifiers changed from: protected */
    @CheckForNull
    public final String zza() {
        zzus zzus = this.zza;
        if (zzus == null) {
            return super.zza();
        }
        String obj = zzus.toString();
        return "task=[" + obj + "]";
    }

    /* access modifiers changed from: protected */
    public final void zzb() {
        zzus zzus;
        if (zzr() && (zzus = this.zza) != null) {
            zzus.zzh();
        }
        this.zza = null;
    }
}
