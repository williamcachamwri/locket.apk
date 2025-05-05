package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcn  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzcn {
    private static final zzcn zza = new zzcn();

    static zzcn zza() {
        return zza;
    }

    public static zzcn zza(@Nullable zzcn zzcn) throws GeneralSecurityException {
        if (zzcn != null) {
            return zzcn;
        }
        throw new GeneralSecurityException("SecretKeyAccess is required");
    }

    private zzcn() {
    }
}
