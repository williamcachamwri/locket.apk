package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzc  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzzc {
    private final byte[] zza;

    public final int hashCode() {
        return Arrays.hashCode(this.zza);
    }

    public final int zza() {
        return this.zza.length;
    }

    public static zzzc zza(byte[] bArr) {
        if (bArr != null) {
            int length = bArr.length;
            if (bArr != null) {
                if (length > bArr.length) {
                    length = bArr.length;
                }
                return new zzzc(bArr, 0, length);
            }
            throw new NullPointerException("data must be non-null");
        }
        throw new NullPointerException("data must be non-null");
    }

    public final String toString() {
        return "Bytes(" + zzyt.zza(this.zza) + ")";
    }

    private zzzc(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        this.zza = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, i2);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzzc)) {
            return false;
        }
        return Arrays.equals(((zzzc) obj).zza, this.zza);
    }

    public final byte[] zzb() {
        byte[] bArr = this.zza;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }
}
