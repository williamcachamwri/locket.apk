package com.google.android.gms.internal.p002firebaseauthapi;

import java.math.BigInteger;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzf  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzzf {
    private final BigInteger zza;

    public static zzzf zza(BigInteger bigInteger, zzcn zzcn) {
        if (zzcn != null) {
            return new zzzf(bigInteger);
        }
        throw new NullPointerException("SecretKeyAccess required");
    }

    public final BigInteger zza(zzcn zzcn) {
        if (zzcn != null) {
            return this.zza;
        }
        throw new NullPointerException("SecretKeyAccess required");
    }

    private zzzf(BigInteger bigInteger) {
        this.zza = bigInteger;
    }
}
