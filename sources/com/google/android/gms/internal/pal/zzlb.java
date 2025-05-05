package com.google.android.gms.internal.pal;

import java.util.Collection;
import java.util.concurrent.ConcurrentMap;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzlb {
    private final ConcurrentMap zza;
    private final zzkv zzb;
    private final Class zzc;
    private final zzrb zzd;

    /* synthetic */ zzlb(ConcurrentMap concurrentMap, zzkv zzkv, zzrb zzrb, Class cls, zzla zzla) {
        this.zza = concurrentMap;
        this.zzb = zzkv;
        this.zzc = cls;
        this.zzd = zzrb;
    }

    @Nullable
    public final zzkv zza() {
        return this.zzb;
    }

    public final zzrb zzb() {
        return this.zzd;
    }

    public final Class zzc() {
        return this.zzc;
    }

    public final Collection zzd() {
        return this.zza.values();
    }

    public final boolean zze() {
        return !this.zzd.zza().isEmpty();
    }
}
