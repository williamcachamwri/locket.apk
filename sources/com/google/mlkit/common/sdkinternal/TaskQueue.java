package com.google.mlkit.common.sdkinternal;

import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public class TaskQueue {
    private final Object zza = new Object();
    private boolean zzb;
    private final Queue zzc = new ArrayDeque();
    /* access modifiers changed from: private */
    public final AtomicReference zzd = new AtomicReference();

    /* access modifiers changed from: private */
    public final void zzc() {
        synchronized (this.zza) {
            if (this.zzc.isEmpty()) {
                this.zzb = false;
                return;
            }
            zzv zzv = (zzv) this.zzc.remove();
            zzd(zzv.zza, zzv.zzb);
        }
    }

    private final void zzd(Executor executor, Runnable runnable) {
        try {
            executor.execute(new zzt(this, runnable));
        } catch (RejectedExecutionException unused) {
            zzc();
        }
    }

    public void checkIsRunningOnCurrentThread() {
        Preconditions.checkState(Thread.currentThread().equals(this.zzd.get()));
    }

    public void submit(Executor executor, Runnable runnable) {
        synchronized (this.zza) {
            if (this.zzb) {
                this.zzc.add(new zzv(executor, runnable, (zzu) null));
                return;
            }
            this.zzb = true;
            zzd(executor, runnable);
        }
    }
}
