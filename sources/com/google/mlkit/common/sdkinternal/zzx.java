package com.google.mlkit.common.sdkinternal;

import com.google.android.gms.common.internal.Preconditions;
import java.io.Closeable;

/* compiled from: com.google.mlkit:common@@18.11.0 */
final class zzx implements Closeable {
    final /* synthetic */ TaskQueue zza;

    /* synthetic */ zzx(TaskQueue taskQueue, zzw zzw) {
        this.zza = taskQueue;
        Preconditions.checkState(((Thread) taskQueue.zzd.getAndSet(Thread.currentThread())) == null);
    }

    public final void close() {
        this.zza.zzd.set((Object) null);
        this.zza.zzc();
    }
}
