package com.google.android.gms.internal.mlkit_common;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public abstract class zzav extends zzx implements ExecutorService {
    protected zzav() {
    }

    public final boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return zzb().awaitTermination(j, timeUnit);
    }

    public void execute(Runnable runnable) {
        zzb().execute(runnable);
    }

    public final List invokeAll(Collection collection) throws InterruptedException {
        return zzb().invokeAll(collection);
    }

    public final Object invokeAny(Collection collection) throws InterruptedException, ExecutionException {
        return zzb().invokeAny(collection);
    }

    public final boolean isShutdown() {
        return zzb().isShutdown();
    }

    public final boolean isTerminated() {
        return zzb().isTerminated();
    }

    public final void shutdown() {
        zzb().shutdown();
    }

    public final List shutdownNow() {
        return zzb().shutdownNow();
    }

    public final Future submit(Runnable runnable) {
        return zzb().submit(runnable);
    }

    /* access modifiers changed from: protected */
    public /* bridge */ /* synthetic */ Object zza() {
        throw null;
    }

    /* access modifiers changed from: protected */
    public abstract ExecutorService zzb();

    public final List invokeAll(Collection collection, long j, TimeUnit timeUnit) throws InterruptedException {
        return zzb().invokeAll(collection, j, timeUnit);
    }

    public final Object invokeAny(Collection collection, long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return zzb().invokeAny(collection, j, timeUnit);
    }

    public final Future submit(Runnable runnable, Object obj) {
        return zzb().submit(runnable, obj);
    }

    public final Future submit(Callable callable) {
        return zzb().submit(callable);
    }
}
