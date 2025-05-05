package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzil;
import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhj  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzhj {
    private static final zzil.zza zza = zzil.zza.ALGORITHM_REQUIRES_BORINGCRYPTO;
    private final SecretKey zzb;

    public zzhj(byte[] bArr) throws GeneralSecurityException {
        if (zza.zza()) {
            this.zzb = zzgo.zzb(bArr);
            return;
        }
        throw new GeneralSecurityException("Can not use AES-GCM in FIPS-mode, as BoringCrypto module is not available.");
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) throws GeneralSecurityException {
        if (bArr.length != 12) {
            throw new GeneralSecurityException("iv is wrong size");
        } else if (bArr2.length >= i + 16) {
            AlgorithmParameterSpec zza2 = zzgo.zza(bArr);
            Cipher zza3 = zzgo.zza();
            zza3.init(2, this.zzb, zza2);
            if (!(bArr3 == null || bArr3.length == 0)) {
                zza3.updateAAD(bArr3);
            }
            return zza3.doFinal(bArr2, i, bArr2.length - i);
        } else {
            throw new GeneralSecurityException("ciphertext too short");
        }
    }

    public final byte[] zzb(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) throws GeneralSecurityException {
        if (bArr.length == 12) {
            AlgorithmParameterSpec zza2 = zzgo.zza(bArr);
            Cipher zza3 = zzgo.zza();
            zza3.init(1, this.zzb, zza2);
            if (!(bArr3 == null || bArr3.length == 0)) {
                zza3.updateAAD(bArr3);
            }
            int outputSize = zza3.getOutputSize(bArr2.length);
            if (outputSize <= Integer.MAX_VALUE - i) {
                byte[] bArr4 = new byte[(i + outputSize)];
                if (zza3.doFinal(bArr2, 0, bArr2.length, bArr4, i) == outputSize) {
                    return bArr4;
                }
                throw new GeneralSecurityException("not enough data written");
            }
            throw new GeneralSecurityException("plaintext too long");
        }
        throw new GeneralSecurityException("iv is wrong size");
    }
}
