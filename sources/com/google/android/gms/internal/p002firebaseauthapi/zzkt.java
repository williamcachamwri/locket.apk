package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkt  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzkt implements zzlh {
    public final int zza() {
        return 32;
    }

    public final int zzb() {
        return 12;
    }

    zzkt() {
    }

    public final byte[] zzc() {
        return zzlq.zzk;
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4) throws GeneralSecurityException {
        if (bArr.length != 32) {
            throw new InvalidAlgorithmParameterException("Unexpected key length: 32");
        } else if (zzhq.zza()) {
            return zzhq.zza(bArr).zza(bArr2, bArr3, i, bArr4);
        } else {
            return new zzho(bArr).zza(bArr2, Arrays.copyOfRange(bArr3, i, bArr3.length), bArr4);
        }
    }
}
