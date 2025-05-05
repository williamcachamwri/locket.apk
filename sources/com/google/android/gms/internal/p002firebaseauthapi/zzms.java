package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzpj;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzms  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public abstract class zzms<SerializationT extends zzpj> {
    private final zzzc zza;
    private final Class<SerializationT> zzb;

    public static <SerializationT extends zzpj> zzms<SerializationT> zza(zzmu<SerializationT> zzmu, zzzc zzzc, Class<SerializationT> cls) {
        return new zzmv(zzzc, cls, zzmu);
    }

    public abstract zzbp zza(SerializationT serializationt, @Nullable zzcn zzcn) throws GeneralSecurityException;

    public final zzzc zza() {
        return this.zza;
    }

    public final Class<SerializationT> zzb() {
        return this.zzb;
    }

    private zzms(zzzc zzzc, Class<SerializationT> cls) {
        this.zza = zzzc;
        this.zzb = cls;
    }
}
