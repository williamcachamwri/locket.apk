package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzil;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzib  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzib implements zzbg {
    private static final zzil.zza zza = zzil.zza.ALGORITHM_NOT_FIPS;
    private final byte[] zzb;
    private final byte[] zzc;

    public static zzbg zza(zzfv zzfv) throws GeneralSecurityException {
        return new zzib(zzfv.zzd().zza(zzbq.zza()), zzfv.zzc().zzb());
    }

    private zzib(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (!zza.zza()) {
            throw new GeneralSecurityException("Can not use ChaCha20Poly1305 in FIPS-mode.");
        } else if (!zza()) {
            throw new GeneralSecurityException("JCE does not support algorithm: ChaCha20-Poly1305");
        } else if (bArr.length == 32) {
            this.zzb = bArr;
            this.zzc = bArr2;
        } else {
            throw new InvalidKeyException("The key length in bytes must be 32.");
        }
    }

    public static boolean zza() {
        return zzhc.zza() != null;
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr != null) {
            int length = bArr.length;
            byte[] bArr3 = this.zzc;
            if (length < bArr3.length + 24 + 16) {
                throw new GeneralSecurityException("ciphertext too short");
            } else if (zzpr.zza(bArr3, bArr)) {
                byte[] bArr4 = new byte[24];
                System.arraycopy(bArr, this.zzc.length, bArr4, 0, 24);
                SecretKeySpec secretKeySpec = new SecretKeySpec(zzhk.zza(this.zzb, bArr4), "ChaCha20");
                IvParameterSpec ivParameterSpec = new IvParameterSpec(zza(bArr4));
                Cipher zza2 = zzhc.zza();
                zza2.init(2, secretKeySpec, ivParameterSpec);
                if (!(bArr2 == null || bArr2.length == 0)) {
                    zza2.updateAAD(bArr2);
                }
                byte[] bArr5 = this.zzc;
                return zza2.doFinal(bArr, bArr5.length + 24, (bArr.length - bArr5.length) - 24);
            } else {
                throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
            }
        } else {
            throw new NullPointerException("ciphertext is null");
        }
    }

    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr != null) {
            byte[] zza2 = zzpe.zza(24);
            SecretKeySpec secretKeySpec = new SecretKeySpec(zzhk.zza(this.zzb, zza2), "ChaCha20");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(zza(zza2));
            Cipher zza3 = zzhc.zza();
            zza3.init(1, secretKeySpec, ivParameterSpec);
            if (!(bArr2 == null || bArr2.length == 0)) {
                zza3.updateAAD(bArr2);
            }
            int outputSize = zza3.getOutputSize(bArr.length);
            byte[] bArr3 = this.zzc;
            if (outputSize <= (Integer.MAX_VALUE - bArr3.length) - 24) {
                byte[] copyOf = Arrays.copyOf(bArr3, bArr3.length + 24 + outputSize);
                System.arraycopy(zza2, 0, copyOf, this.zzc.length, 24);
                if (zza3.doFinal(bArr, 0, bArr.length, copyOf, this.zzc.length + 24) == outputSize) {
                    return copyOf;
                }
                throw new GeneralSecurityException("not enough data written");
            }
            throw new GeneralSecurityException("plaintext too long");
        }
        throw new NullPointerException("plaintext is null");
    }

    private static byte[] zza(byte[] bArr) {
        byte[] bArr2 = new byte[12];
        System.arraycopy(bArr, 16, bArr2, 4, 8);
        return bArr2;
    }
}
