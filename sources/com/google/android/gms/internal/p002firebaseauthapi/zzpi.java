package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpi  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzpi {
    /* access modifiers changed from: private */
    public final Map<zzpn, zzmw<?, ?>> zza;
    /* access modifiers changed from: private */
    public final Map<zzpk, zzms<?>> zzb;
    /* access modifiers changed from: private */
    public final Map<zzpn, zzon<?, ?>> zzc;
    /* access modifiers changed from: private */
    public final Map<zzpk, zzoj<?>> zzd;

    public final <SerializationT extends zzpj> zzbp zza(SerializationT serializationt, @Nullable zzcn zzcn) throws GeneralSecurityException {
        zzpk zzpk = new zzpk(serializationt.getClass(), serializationt.zzc());
        if (this.zzb.containsKey(zzpk)) {
            return this.zzb.get(zzpk).zza(serializationt, zzcn);
        }
        throw new GeneralSecurityException("No Key Parser for requested key type " + String.valueOf(zzpk) + " available");
    }

    public final <SerializationT extends zzpj> zzch zza(SerializationT serializationt) throws GeneralSecurityException {
        zzpk zzpk = new zzpk(serializationt.getClass(), serializationt.zzc());
        if (this.zzd.containsKey(zzpk)) {
            return this.zzd.get(zzpk).zza(serializationt);
        }
        throw new GeneralSecurityException("No Parameters Parser for requested key type " + String.valueOf(zzpk) + " available");
    }

    public final <KeyT extends zzbp, SerializationT extends zzpj> SerializationT zza(KeyT keyt, Class<SerializationT> cls, @Nullable zzcn zzcn) throws GeneralSecurityException {
        zzpn zzpn = new zzpn(keyt.getClass(), cls);
        if (this.zza.containsKey(zzpn)) {
            return this.zza.get(zzpn).zza(keyt, zzcn);
        }
        throw new GeneralSecurityException("No Key serializer for " + String.valueOf(zzpn) + " available");
    }

    public final <ParametersT extends zzch, SerializationT extends zzpj> SerializationT zza(ParametersT parameterst, Class<SerializationT> cls) throws GeneralSecurityException {
        zzpn zzpn = new zzpn(parameterst.getClass(), cls);
        if (this.zzc.containsKey(zzpn)) {
            return this.zzc.get(zzpn).zza(parameterst);
        }
        throw new GeneralSecurityException("No Key Format serializer for " + String.valueOf(zzpn) + " available");
    }

    private zzpi(zzpl zzpl) {
        this.zza = new HashMap(zzpl.zza);
        this.zzb = new HashMap(zzpl.zzb);
        this.zzc = new HashMap(zzpl.zzc);
        this.zzd = new HashMap(zzpl.zzd);
    }

    public final <SerializationT extends zzpj> boolean zzb(SerializationT serializationt) {
        return this.zzb.containsKey(new zzpk(serializationt.getClass(), serializationt.zzc()));
    }

    public final <SerializationT extends zzpj> boolean zzc(SerializationT serializationt) {
        return this.zzd.containsKey(new zzpk(serializationt.getClass(), serializationt.zzc()));
    }
}
