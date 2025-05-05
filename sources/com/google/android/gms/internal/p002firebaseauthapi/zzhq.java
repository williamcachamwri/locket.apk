package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzil;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhq  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzhq {
    private static final zzil.zza zza = zzil.zza.ALGORITHM_NOT_FIPS;
    private final SecretKey zzb;

    public static zzhq zza(byte[] bArr) throws GeneralSecurityException {
        return new zzhq(bArr);
    }

    private zzhq(byte[] bArr) throws GeneralSecurityException {
        if (!zza.zza()) {
            throw new GeneralSecurityException("Can not use ChaCha20Poly1305 in FIPS-mode.");
        } else if (!zza()) {
            throw new GeneralSecurityException("JCE does not support algorithm: ChaCha20-Poly1305");
        } else if (bArr.length == 32) {
            this.zzb = new SecretKeySpec(bArr, "ChaCha20");
        } else {
            throw new InvalidKeyException("The key length in bytes must be 32.");
        }
    }

    public static boolean zza() {
        return zzhc.zza() != null;
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) throws GeneralSecurityException {
        if (bArr2 == null) {
            throw new NullPointerException("ciphertext is null");
        } else if (bArr.length != 12) {
            throw new GeneralSecurityException("nonce length must be 12 bytes.");
        } else if (bArr2.length >= i + 16) {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr);
            Cipher zza2 = zzhc.zza();
            zza2.init(2, this.zzb, ivParameterSpec);
            if (!(bArr3 == null || bArr3.length == 0)) {
                zza2.updateAAD(bArr3);
            }
            return zza2.doFinal(bArr2, i, bArr2.length - i);
        } else {
            throw new GeneralSecurityException("ciphertext too short");
        }
    }
}
