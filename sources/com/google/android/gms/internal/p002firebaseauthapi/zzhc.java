package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzil;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.util.Arrays;
import javax.annotation.Nullable;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhc  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzhc implements zzbg {
    private static final zzil.zza zza = zzil.zza.ALGORITHM_NOT_FIPS;
    private static final byte[] zzb = zzyt.zza("808182838485868788898a8b8c8d8e8f909192939495969798999a9b9c9d9e9f");
    private static final byte[] zzc = zzyt.zza("070000004041424344454647");
    private static final byte[] zzd = zzyt.zza("a0784d7a4716f3feb4f64e7f4b39bf04");
    private static final ThreadLocal<Cipher> zze = new zzhb();
    private final SecretKey zzf;
    private final byte[] zzg;

    public static zzbg zza(zzee zzee) throws GeneralSecurityException {
        return new zzhc(zzee.zzd().zza(zzbq.zza()), zzee.zzc().zzb());
    }

    @Nullable
    static Cipher zza() {
        return zze.get();
    }

    private zzhc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (!zza.zza()) {
            throw new GeneralSecurityException("Can not use ChaCha20Poly1305 in FIPS-mode.");
        } else if (!zzb()) {
            throw new GeneralSecurityException("JCE does not support algorithm: ChaCha20-Poly1305");
        } else if (bArr.length == 32) {
            this.zzf = new SecretKeySpec(bArr, "ChaCha20");
            this.zzg = bArr2;
        } else {
            throw new InvalidKeyException("The key length in bytes must be 32.");
        }
    }

    public static boolean zzb() {
        return zze.get() != null;
    }

    /* access modifiers changed from: private */
    public static boolean zzb(Cipher cipher) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(zzc);
            byte[] bArr = zzb;
            cipher.init(2, new SecretKeySpec(bArr, "ChaCha20"), ivParameterSpec);
            byte[] bArr2 = zzd;
            if (cipher.doFinal(bArr2).length != 0) {
                return false;
            }
            cipher.init(2, new SecretKeySpec(bArr, "ChaCha20"), ivParameterSpec);
            if (cipher.doFinal(bArr2).length != 0) {
                return false;
            }
            return true;
        } catch (GeneralSecurityException unused) {
            return false;
        }
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr != null) {
            int length = bArr.length;
            byte[] bArr3 = this.zzg;
            if (length < bArr3.length + 12 + 16) {
                throw new GeneralSecurityException("ciphertext too short");
            } else if (zzpr.zza(bArr3, bArr)) {
                byte[] bArr4 = new byte[12];
                System.arraycopy(bArr, this.zzg.length, bArr4, 0, 12);
                IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr4);
                Cipher cipher = zze.get();
                cipher.init(2, this.zzf, ivParameterSpec);
                if (!(bArr2 == null || bArr2.length == 0)) {
                    cipher.updateAAD(bArr2);
                }
                byte[] bArr5 = this.zzg;
                return cipher.doFinal(bArr, bArr5.length + 12, (bArr.length - bArr5.length) - 12);
            } else {
                throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
            }
        } else {
            throw new NullPointerException("ciphertext is null");
        }
    }

    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr != null) {
            byte[] zza2 = zzpe.zza(12);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(zza2);
            Cipher cipher = zze.get();
            cipher.init(1, this.zzf, ivParameterSpec);
            if (!(bArr2 == null || bArr2.length == 0)) {
                cipher.updateAAD(bArr2);
            }
            int outputSize = cipher.getOutputSize(bArr.length);
            byte[] bArr3 = this.zzg;
            if (outputSize <= (Integer.MAX_VALUE - bArr3.length) - 12) {
                byte[] copyOf = Arrays.copyOf(bArr3, bArr3.length + 12 + outputSize);
                System.arraycopy(zza2, 0, copyOf, this.zzg.length, 12);
                if (cipher.doFinal(bArr, 0, bArr.length, copyOf, this.zzg.length + 12) == outputSize) {
                    return copyOf;
                }
                throw new GeneralSecurityException("not enough data written");
            }
            throw new GeneralSecurityException("plaintext too long");
        }
        throw new NullPointerException("plaintext is null");
    }
}
