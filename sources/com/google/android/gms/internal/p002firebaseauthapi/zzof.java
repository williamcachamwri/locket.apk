package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzof  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzof {
    private static final zzof zza = ((zzof) zzpp.zza(new zzoe()));
    private final AtomicReference<zzpi> zzb = new AtomicReference<>(new zzpl().zza());

    public final <SerializationT extends zzpj> zzbp zza(SerializationT serializationt, @Nullable zzcn zzcn) throws GeneralSecurityException {
        return this.zzb.get().zza(serializationt, zzcn);
    }

    public final <SerializationT extends zzpj> zzch zza(SerializationT serializationt) throws GeneralSecurityException {
        return this.zzb.get().zza(serializationt);
    }

    public static zzof zza() {
        return zza;
    }

    public final <KeyT extends zzbp, SerializationT extends zzpj> SerializationT zza(KeyT keyt, Class<SerializationT> cls, @Nullable zzcn zzcn) throws GeneralSecurityException {
        return this.zzb.get().zza(keyt, cls, zzcn);
    }

    public final <ParametersT extends zzch, SerializationT extends zzpj> SerializationT zza(ParametersT parameterst, Class<SerializationT> cls) throws GeneralSecurityException {
        return this.zzb.get().zza(parameterst, cls);
    }

    public final synchronized <SerializationT extends zzpj> void zza(zzms<SerializationT> zzms) throws GeneralSecurityException {
        this.zzb.set(new zzpl(this.zzb.get()).zza(zzms).zza());
    }

    public final synchronized <KeyT extends zzbp, SerializationT extends zzpj> void zza(zzmw<KeyT, SerializationT> zzmw) throws GeneralSecurityException {
        this.zzb.set(new zzpl(this.zzb.get()).zza(zzmw).zza());
    }

    public final synchronized <SerializationT extends zzpj> void zza(zzoj<SerializationT> zzoj) throws GeneralSecurityException {
        this.zzb.set(new zzpl(this.zzb.get()).zza(zzoj).zza());
    }

    public final synchronized <ParametersT extends zzch, SerializationT extends zzpj> void zza(zzon<ParametersT, SerializationT> zzon) throws GeneralSecurityException {
        this.zzb.set(new zzpl(this.zzb.get()).zza(zzon).zza());
    }

    public final <SerializationT extends zzpj> boolean zzb(SerializationT serializationt) {
        return this.zzb.get().zzb(serializationt);
    }

    public final <SerializationT extends zzpj> boolean zzc(SerializationT serializationt) {
        return this.zzb.get().zzc(serializationt);
    }
}
