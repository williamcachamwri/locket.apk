package com.google.mlkit.common.sdkinternal;

import java.lang.ref.ReferenceQueue;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public class Cleaner {
    private final ReferenceQueue zza = new ReferenceQueue();
    private final Set zzb = Collections.synchronizedSet(new HashSet());

    /* compiled from: com.google.mlkit:common@@18.11.0 */
    public interface Cleanable {
        void clean();
    }

    private Cleaner() {
    }

    public static Cleaner create() {
        Cleaner cleaner = new Cleaner();
        cleaner.register(cleaner, new zza());
        Thread thread = new Thread(new zzb(cleaner.zza, cleaner.zzb), "MlKitCleaner");
        thread.setDaemon(true);
        thread.start();
        return cleaner;
    }

    public Cleanable register(Object obj, Runnable runnable) {
        zzd zzd = new zzd(obj, this.zza, this.zzb, runnable, (zzc) null);
        this.zzb.add(zzd);
        return zzd;
    }
}
