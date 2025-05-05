package com.google.mlkit.common.sdkinternal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzrr;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.mlkit.common.MlKitException;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public abstract class ModelResource {
    protected final TaskQueue taskQueue;
    private final AtomicInteger zza;
    private final AtomicBoolean zzb;

    public ModelResource() {
        this.zza = new AtomicInteger(0);
        this.zzb = new AtomicBoolean(false);
        this.taskQueue = new TaskQueue();
    }

    public <T> Task<T> callAfterLoad(Executor executor, Callable<T> callable, CancellationToken cancellationToken) {
        Preconditions.checkState(this.zza.get() > 0);
        if (cancellationToken.isCancellationRequested()) {
            return Tasks.forCanceled();
        }
        CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource(cancellationTokenSource.getToken());
        this.taskQueue.submit(new zzm(executor, cancellationToken, cancellationTokenSource, taskCompletionSource), new zzn(this, cancellationToken, cancellationTokenSource, callable, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    public boolean isLoaded() {
        return this.zzb.get();
    }

    public abstract void load() throws MlKitException;

    public void pin() {
        this.zza.incrementAndGet();
    }

    /* access modifiers changed from: protected */
    public abstract void release();

    public void unpin(Executor executor) {
        unpinWithTask(executor);
    }

    public Task<Void> unpinWithTask(Executor executor) {
        Preconditions.checkState(this.zza.get() > 0);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.taskQueue.submit(executor, new zzl(this, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(CancellationToken cancellationToken, CancellationTokenSource cancellationTokenSource, Callable callable, TaskCompletionSource taskCompletionSource) {
        if (cancellationToken.isCancellationRequested()) {
            cancellationTokenSource.cancel();
            return;
        }
        try {
            if (!this.zzb.get()) {
                load();
                this.zzb.set(true);
            }
            if (cancellationToken.isCancellationRequested()) {
                cancellationTokenSource.cancel();
                return;
            }
            Object call = callable.call();
            if (cancellationToken.isCancellationRequested()) {
                cancellationTokenSource.cancel();
            } else {
                taskCompletionSource.setResult(call);
            }
        } catch (RuntimeException e) {
            throw new MlKitException("Internal error has occurred when executing ML Kit tasks", 13, e);
        } catch (Exception e2) {
            if (cancellationToken.isCancellationRequested()) {
                cancellationTokenSource.cancel();
            } else {
                taskCompletionSource.setException(e2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(TaskCompletionSource taskCompletionSource) {
        int decrementAndGet = this.zza.decrementAndGet();
        Preconditions.checkState(decrementAndGet >= 0);
        if (decrementAndGet == 0) {
            release();
            this.zzb.set(false);
        }
        zzrr.zza();
        taskCompletionSource.setResult(null);
    }

    protected ModelResource(TaskQueue taskQueue2) {
        this.zza = new AtomicInteger(0);
        this.zzb = new AtomicBoolean(false);
        this.taskQueue = taskQueue2;
    }
}
