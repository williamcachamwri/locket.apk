package com.google.android.gms.internal.p002firebaseauthapi;

import java.lang.Enum;
import java.security.GeneralSecurityException;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmm  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzmm<E extends Enum<E>, O> {
    private final Map<E, O> zza;
    private final Map<O, E> zzb;

    public static <E extends Enum<E>, O> zzmp<E, O> zza() {
        return new zzmp<>();
    }

    public final E zza(O o) throws GeneralSecurityException {
        E e = (Enum) this.zzb.get(o);
        if (e != null) {
            return e;
        }
        throw new GeneralSecurityException("Unable to convert object enum: " + String.valueOf(o));
    }

    public final O zza(E e) throws GeneralSecurityException {
        O o = this.zza.get(e);
        if (o != null) {
            return o;
        }
        throw new GeneralSecurityException("Unable to convert proto enum: " + String.valueOf(e));
    }

    private zzmm(Map<E, O> map, Map<O, E> map2) {
        this.zza = map;
        this.zzb = map2;
    }
}
