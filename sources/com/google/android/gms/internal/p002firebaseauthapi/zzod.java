package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzod  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzod {
    private static final zzod zza = new zzod();
    private final Map<String, zzch> zzb = new HashMap();

    public static zzod zza() {
        return zza;
    }

    zzod() {
    }

    private final synchronized void zza(String str, zzch zzch) throws GeneralSecurityException {
        if (!this.zzb.containsKey(str)) {
            this.zzb.put(str, zzch);
        } else if (!this.zzb.get(str).equals(zzch)) {
            String valueOf = String.valueOf(this.zzb.get(str));
            throw new GeneralSecurityException("Parameters object with name " + str + " already exists (" + valueOf + "), cannot insert " + String.valueOf(zzch));
        }
    }

    public final synchronized void zza(Map<String, zzch> map) throws GeneralSecurityException {
        for (Map.Entry next : map.entrySet()) {
            zza((String) next.getKey(), (zzch) next.getValue());
        }
    }
}
