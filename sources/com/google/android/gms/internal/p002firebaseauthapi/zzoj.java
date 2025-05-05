package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzpj;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzoj  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public abstract class zzoj<SerializationT extends zzpj> {
    private final zzzc zza;
    private final Class<SerializationT> zzb;

    public static <SerializationT extends zzpj> zzoj<SerializationT> zza(zzol<SerializationT> zzol, zzzc zzzc, Class<SerializationT> cls) {
        return new zzoi(zzzc, cls, zzol);
    }

    public abstract zzch zza(SerializationT serializationt) throws GeneralSecurityException;

    public final zzzc zza() {
        return this.zza;
    }

    public final Class<SerializationT> zzb() {
        return this.zzb;
    }

    private zzoj(zzzc zzzc, Class<SerializationT> cls) {
        this.zza = zzzc;
        this.zzb = cls;
    }
}
