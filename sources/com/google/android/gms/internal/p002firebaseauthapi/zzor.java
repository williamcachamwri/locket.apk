package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzbp;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzor  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public abstract class zzor<KeyT extends zzbp, PrimitiveT> {
    private final Class<KeyT> zza;
    private final Class<PrimitiveT> zzb;

    public static <KeyT extends zzbp, PrimitiveT> zzor<KeyT, PrimitiveT> zza(zzot<KeyT, PrimitiveT> zzot, Class<KeyT> cls, Class<PrimitiveT> cls2) {
        return new zzoq(cls, cls2, zzot);
    }

    public abstract PrimitiveT zza(KeyT keyt) throws GeneralSecurityException;

    public final Class<KeyT> zza() {
        return this.zza;
    }

    public final Class<PrimitiveT> zzb() {
        return this.zzb;
    }

    private zzor(Class<KeyT> cls, Class<PrimitiveT> cls2) {
        this.zza = cls;
        this.zzb = cls2;
    }
}
