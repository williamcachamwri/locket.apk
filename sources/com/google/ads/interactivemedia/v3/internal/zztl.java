package com.google.ads.interactivemedia.v3.internal;

import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zztl extends AbstractExecutorService implements zzuv {
    /* access modifiers changed from: protected */
    public final RunnableFuture newTaskFor(Runnable runnable, Object obj) {
        return zzvh.zzs(runnable, obj);
    }

    public final /* synthetic */ Future submit(Runnable runnable) {
        return (zzuu) super.submit(runnable);
    }

    public final zzuu zza(Callable callable) {
        return (zzuu) super.submit(callable);
    }

    /* access modifiers changed from: protected */
    public final RunnableFuture newTaskFor(Callable callable) {
        return new zzvh(callable);
    }

    public final /* synthetic */ Future submit(Runnable runnable, Object obj) {
        return (zzuu) super.submit(runnable, obj);
    }

    public final /* synthetic */ Future submit(Callable callable) {
        return (zzuu) super.submit(callable);
    }
}
