package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzoc  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzoc {
    private static zzoc zza = new zzoc();
    private final AtomicReference<zzov> zzb = new AtomicReference<>(new zzou().zza());

    public static zzoc zza() {
        return zza;
    }

    public final <WrapperPrimitiveT> Class<?> zza(Class<WrapperPrimitiveT> cls) throws GeneralSecurityException {
        return this.zzb.get().zza((Class<?>) cls);
    }

    public final <KeyT extends zzbp, PrimitiveT> PrimitiveT zza(KeyT keyt, Class<PrimitiveT> cls) throws GeneralSecurityException {
        return this.zzb.get().zza(keyt, cls);
    }

    public final <InputPrimitiveT, WrapperPrimitiveT> WrapperPrimitiveT zza(zzoz<InputPrimitiveT> zzoz, Class<WrapperPrimitiveT> cls) throws GeneralSecurityException {
        return this.zzb.get().zza(zzoz, cls);
    }

    zzoc() {
    }

    public final synchronized <KeyT extends zzbp, PrimitiveT> void zza(zzor<KeyT, PrimitiveT> zzor) throws GeneralSecurityException {
        this.zzb.set(zzov.zza(this.zzb.get()).zza(zzor).zza());
    }

    public final synchronized <InputPrimitiveT, WrapperPrimitiveT> void zza(zzpd<InputPrimitiveT, WrapperPrimitiveT> zzpd) throws GeneralSecurityException {
        this.zzb.set(zzov.zza(this.zzb.get()).zza(zzpd).zza());
    }
}
