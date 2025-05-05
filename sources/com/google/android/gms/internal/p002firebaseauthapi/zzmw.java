package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzbp;
import com.google.android.gms.internal.p002firebaseauthapi.zzpj;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmw  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public abstract class zzmw<KeyT extends zzbp, SerializationT extends zzpj> {
    private final Class<KeyT> zza;
    private final Class<SerializationT> zzb;

    public static <KeyT extends zzbp, SerializationT extends zzpj> zzmw<KeyT, SerializationT> zza(zzmy<KeyT, SerializationT> zzmy, Class<KeyT> cls, Class<SerializationT> cls2) {
        return new zzmz(cls, cls2, zzmy);
    }

    public abstract SerializationT zza(KeyT keyt, @Nullable zzcn zzcn) throws GeneralSecurityException;

    public final Class<KeyT> zza() {
        return this.zza;
    }

    public final Class<SerializationT> zzb() {
        return this.zzb;
    }

    private zzmw(Class<KeyT> cls, Class<SerializationT> cls2) {
        this.zza = cls;
        this.zzb = cls2;
    }
}
