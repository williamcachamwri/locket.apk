package com.google.mlkit.common.sdkinternal;

import com.google.mlkit.common.sdkinternal.Cleaner;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.Set;

/* compiled from: com.google.mlkit:common@@18.11.0 */
final class zzd extends PhantomReference implements Cleaner.Cleanable {
    private final Set zza;
    private final Runnable zzb;

    /* synthetic */ zzd(Object obj, ReferenceQueue referenceQueue, Set set, Runnable runnable, zzc zzc) {
        super(obj, referenceQueue);
        this.zza = set;
        this.zzb = runnable;
    }

    public final void clean() {
        if (this.zza.remove(this)) {
            clear();
            this.zzb.run();
        }
    }
}
