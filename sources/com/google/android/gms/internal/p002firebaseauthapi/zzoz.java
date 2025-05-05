package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzoz  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzoz<P> {
    private final Map<zzzc, List<zzpb<P>>> zza;
    private final zzpb<P> zzb;
    private final Class<P> zzc;
    private final zzng zzd;

    public final zzng zza() {
        return this.zzd;
    }

    public static <P> zzoy<P> zza(Class<P> cls) {
        return new zzoy<>(cls);
    }

    @Nullable
    public final zzpb<P> zzb() {
        return this.zzb;
    }

    public final Class<P> zzc() {
        return this.zzc;
    }

    public final Collection<List<zzpb<P>>> zzd() {
        return this.zza.values();
    }

    public final List<zzpb<P>> zza(byte[] bArr) {
        List<zzpb<P>> list = this.zza.get(zzzc.zza(bArr));
        if (list != null) {
            return list;
        }
        return Collections.emptyList();
    }

    public final List<zzpb<P>> zze() {
        return zza(zzbj.zza);
    }

    private zzoz(Map<zzzc, List<zzpb<P>>> map, List<zzpb<P>> list, zzpb<P> zzpb, zzng zzng, Class<P> cls) {
        this.zza = map;
        this.zzb = zzpb;
        this.zzc = cls;
        this.zzd = zzng;
    }

    public final boolean zzf() {
        return !this.zzd.zza().isEmpty();
    }
}
