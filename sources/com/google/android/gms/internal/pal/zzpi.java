package com.google.android.gms.internal.pal;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzpi {
    private static final zzpi zza = new zzpi();
    private static final zzph zzb = new zzph((zzpg) null);
    private final AtomicReference zzc = new AtomicReference();

    public static zzpi zza() {
        return zza;
    }

    public final zzrd zzb() {
        zzrd zzrd = (zzrd) this.zzc.get();
        return zzrd == null ? zzb : zzrd;
    }
}
