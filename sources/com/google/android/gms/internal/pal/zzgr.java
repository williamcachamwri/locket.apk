package com.google.android.gms.internal.pal;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzgr {
    static final AtomicBoolean zza = new AtomicBoolean();
    private static final AtomicReference zzb = new AtomicReference();
    private static final AtomicReference zzc = new AtomicReference();

    static zzgp zza() {
        return (zzgp) zzb.get();
    }

    static zzgq zzb() {
        return (zzgq) zzc.get();
    }

    public static void zzc(zzgp zzgp) {
        zzb.set(zzgp);
    }
}
