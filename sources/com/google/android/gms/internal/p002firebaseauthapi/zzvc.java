package com.google.android.gms.internal.p002firebaseauthapi;

import kotlin.text.Typography;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvc  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public enum zzvc implements zzaka {
    KDF_UNKNOWN(0),
    HKDF_SHA256(1),
    HKDF_SHA384(2),
    HKDF_SHA512(3),
    UNRECOGNIZED(-1);
    
    private final int zzg;

    public final int zza() {
        if (this != UNRECOGNIZED) {
            return this.zzg;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }

    public static zzvc zza(int i) {
        if (i == 0) {
            return KDF_UNKNOWN;
        }
        if (i == 1) {
            return HKDF_SHA256;
        }
        if (i == 2) {
            return HKDF_SHA384;
        }
        if (i != 3) {
            return null;
        }
        return HKDF_SHA512;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("<");
        sb.append(getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)));
        if (this != UNRECOGNIZED) {
            sb.append(" number=").append(zza());
        }
        return sb.append(" name=").append(name()).append(Typography.greater).toString();
    }

    private zzvc(int i) {
        this.zzg = i;
    }
}
