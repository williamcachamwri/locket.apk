package com.google.android.gms.internal.p002firebaseauthapi;

import kotlin.text.Typography;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvd  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public enum zzvd implements zzaka {
    AEAD_UNKNOWN(0),
    AES_128_GCM(1),
    AES_256_GCM(2),
    CHACHA20_POLY1305(3),
    UNRECOGNIZED(-1);
    
    private final int zzg;

    public final int zza() {
        if (this != UNRECOGNIZED) {
            return this.zzg;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }

    public static zzvd zza(int i) {
        if (i == 0) {
            return AEAD_UNKNOWN;
        }
        if (i == 1) {
            return AES_128_GCM;
        }
        if (i == 2) {
            return AES_256_GCM;
        }
        if (i != 3) {
            return null;
        }
        return CHACHA20_POLY1305;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("<");
        sb.append(getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)));
        if (this != UNRECOGNIZED) {
            sb.append(" number=").append(zza());
        }
        return sb.append(" name=").append(name()).append(Typography.greater).toString();
    }

    private zzvd(int i) {
        this.zzg = i;
    }
}
