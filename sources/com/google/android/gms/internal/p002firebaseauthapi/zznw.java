package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zznw  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zznw {
    private static final zznw zza = new zznw();
    private final Map<Class<? extends zzch>, zznz<? extends zzch>> zzb = new HashMap();

    public static zznw zza() {
        return zza;
    }

    public final synchronized <ParametersT extends zzch> void zza(zznz<ParametersT> zznz, Class<ParametersT> cls) throws GeneralSecurityException {
        zznz zznz2 = this.zzb.get(cls);
        if (zznz2 != null) {
            if (!zznz2.equals(zznz)) {
                throw new GeneralSecurityException("Different key creator for parameters class already inserted");
            }
        }
        this.zzb.put(cls, zznz);
    }
}
