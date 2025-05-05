package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.media3.extractor.ts.TsExtractor;
import com.google.android.gms.internal.p002firebaseauthapi.zzil;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.AEADBadTagException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxp  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzxp implements zzbg {
    private static final zzil.zza zza = zzil.zza.ALGORITHM_NOT_FIPS;
    private static final ThreadLocal<Cipher> zzb = new zzxo();
    private static final ThreadLocal<Cipher> zzc = new zzxr();
    private final byte[] zzd;
    private final byte[] zze;
    private final byte[] zzf;
    private final SecretKeySpec zzg;
    private final int zzh;

    public static zzbg zza(zzdh zzdh) throws GeneralSecurityException {
        if (!zza.zza()) {
            throw new GeneralSecurityException("Can not use AES-EAX in FIPS-mode.");
        } else if (zzdh.zzc().zzd() == 16) {
            return new zzxp(zzdh.zze().zza(zzbq.zza()), zzdh.zzc().zzb(), zzdh.zzd().zzb());
        } else {
            throw new GeneralSecurityException("AesEaxJce only supports 16 byte tag size, not " + zzdh.zzc().zzd());
        }
    }

    private zzxp(byte[] bArr, int i, byte[] bArr2) throws GeneralSecurityException {
        if (!zza.zza()) {
            throw new GeneralSecurityException("Can not use AES-EAX in FIPS-mode.");
        } else if (i == 12 || i == 16) {
            this.zzh = i;
            zzzb.zza(bArr.length);
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
            this.zzg = secretKeySpec;
            Cipher cipher = zzb.get();
            cipher.init(1, secretKeySpec);
            byte[] zza2 = zza(cipher.doFinal(new byte[16]));
            this.zzd = zza2;
            this.zze = zza(zza2);
            this.zzf = bArr2;
        } else {
            throw new IllegalArgumentException("IV size should be either 12 or 16 bytes");
        }
    }

    private static void zzc(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int length = bArr.length;
        byte[] bArr3 = this.zzf;
        int length2 = ((length - bArr3.length) - this.zzh) - 16;
        if (length2 < 0) {
            throw new GeneralSecurityException("ciphertext too short");
        } else if (zzpr.zza(bArr3, bArr)) {
            Cipher cipher = zzb.get();
            cipher.init(1, this.zzg);
            byte[] zza2 = zza(cipher, 0, bArr, this.zzf.length, this.zzh);
            if (bArr2 == null) {
                bArr2 = new byte[0];
            }
            byte[] bArr4 = bArr2;
            byte[] zza3 = zza(cipher, 1, bArr4, 0, bArr4.length);
            byte[] zza4 = zza(cipher, 2, bArr, this.zzf.length + this.zzh, length2);
            int length3 = bArr.length - 16;
            byte b = 0;
            for (int i = 0; i < 16; i++) {
                b = (byte) (b | (((bArr[length3 + i] ^ zza3[i]) ^ zza2[i]) ^ zza4[i]));
            }
            if (b == 0) {
                Cipher cipher2 = zzc.get();
                cipher2.init(1, this.zzg, new IvParameterSpec(zza2));
                return cipher2.doFinal(bArr, this.zzf.length + this.zzh, length2);
            }
            throw new AEADBadTagException("tag mismatch");
        } else {
            throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
        }
    }

    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArr3 = bArr;
        int length = bArr3.length;
        byte[] bArr4 = this.zzf;
        int length2 = Integer.MAX_VALUE - bArr4.length;
        int i = this.zzh;
        if (length <= (length2 - i) - 16) {
            byte[] copyOf = Arrays.copyOf(bArr4, bArr4.length + i + bArr3.length + 16);
            byte[] zza2 = zzpe.zza(this.zzh);
            System.arraycopy(zza2, 0, copyOf, this.zzf.length, this.zzh);
            Cipher cipher = zzb.get();
            cipher.init(1, this.zzg);
            byte[] zza3 = zza(cipher, 0, zza2, 0, zza2.length);
            byte[] bArr5 = bArr2 == null ? new byte[0] : bArr2;
            byte[] zza4 = zza(cipher, 1, bArr5, 0, bArr5.length);
            Cipher cipher2 = zzc.get();
            cipher2.init(1, this.zzg, new IvParameterSpec(zza3));
            cipher2.doFinal(bArr, 0, bArr3.length, copyOf, this.zzf.length + this.zzh);
            byte[] zza5 = zza(cipher, 2, copyOf, this.zzf.length + this.zzh, bArr3.length);
            int length3 = this.zzf.length + bArr3.length + this.zzh;
            for (int i2 = 0; i2 < 16; i2++) {
                copyOf[length3 + i2] = (byte) ((zza4[i2] ^ zza3[i2]) ^ zza5[i2]);
            }
            return copyOf;
        }
        throw new GeneralSecurityException("plaintext too long");
    }

    private static byte[] zza(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        int i = 0;
        while (i < 15) {
            int i2 = i + 1;
            bArr2[i] = (byte) ((bArr[i] << 1) ^ ((bArr[i2] & 255) >>> 7));
            i = i2;
        }
        bArr2[15] = (byte) (((bArr[0] >> 7) & TsExtractor.TS_STREAM_TYPE_E_AC3) ^ (bArr[15] << 1));
        return bArr2;
    }

    private final byte[] zza(Cipher cipher, int i, byte[] bArr, int i2, int i3) throws IllegalBlockSizeException, BadPaddingException, ShortBufferException {
        byte[] bArr2 = new byte[16];
        bArr2[15] = (byte) i;
        if (i3 == 0) {
            zzc(bArr2, this.zzd);
            return cipher.doFinal(bArr2);
        }
        byte[] bArr3 = new byte[16];
        cipher.doFinal(bArr2, 0, 16, bArr3);
        int i4 = 0;
        while (true) {
            byte[] bArr4 = bArr2;
            bArr2 = bArr3;
            bArr3 = bArr4;
            if (i3 - i4 <= 16) {
                break;
            }
            for (int i5 = 0; i5 < 16; i5++) {
                bArr2[i5] = (byte) (bArr2[i5] ^ bArr[(i2 + i4) + i5]);
            }
            cipher.doFinal(bArr2, 0, 16, bArr3);
            i4 += 16;
        }
        byte[] copyOfRange = Arrays.copyOfRange(bArr, i4 + i2, i2 + i3);
        if (copyOfRange.length == 16) {
            zzc(copyOfRange, this.zzd);
        } else {
            byte[] copyOf = Arrays.copyOf(this.zze, 16);
            for (int i6 = 0; i6 < copyOfRange.length; i6++) {
                copyOf[i6] = (byte) (copyOf[i6] ^ copyOfRange[i6]);
            }
            copyOf[copyOfRange.length] = (byte) (copyOf[copyOfRange.length] ^ 128);
            copyOfRange = copyOf;
        }
        zzc(bArr2, copyOfRange);
        cipher.doFinal(bArr2, 0, 16, bArr3);
        return bArr3;
    }
}
