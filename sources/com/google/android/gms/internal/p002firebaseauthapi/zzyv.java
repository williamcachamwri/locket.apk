package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzil;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyv  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzyv implements zzrq {
    private static final zzil.zza zza = zzil.zza.ALGORITHM_NOT_FIPS;
    private static final ThreadLocal<Cipher> zzb = new zzyu();
    private final SecretKey zzc;
    private byte[] zzd;
    private byte[] zze;

    private static Cipher zza() throws GeneralSecurityException {
        if (zza.zza()) {
            return zzb.get();
        }
        throw new GeneralSecurityException("Can not use AES-CMAC in FIPS-mode.");
    }

    public zzyv(byte[] bArr) throws GeneralSecurityException {
        zzzb.zza(bArr.length);
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        this.zzc = secretKeySpec;
        Cipher zza2 = zza();
        zza2.init(1, secretKeySpec);
        byte[] zzb2 = zzrg.zzb(zza2.doFinal(new byte[16]));
        this.zzd = zzb2;
        this.zze = zzrg.zzb(zzb2);
    }

    private static void zza(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) {
        for (int i2 = 0; i2 < 16; i2++) {
            bArr3[i2] = (byte) (bArr[i2] ^ bArr2[i2 + i]);
        }
    }

    public final byte[] zza(byte[] bArr, int i) throws GeneralSecurityException {
        int i2;
        byte[] bArr2;
        if (i <= 16) {
            Cipher zza2 = zza();
            zza2.init(1, this.zzc);
            int length = bArr.length;
            if (length == 0) {
                i2 = 1;
            } else {
                i2 = ((length - 1) / 16) + 1;
            }
            if ((i2 << 4) == bArr.length) {
                bArr2 = zzxv.zza(bArr, (i2 - 1) << 4, this.zzd, 0, 16);
            } else {
                bArr2 = zzxv.zza(zzrg.zza(Arrays.copyOfRange(bArr, (i2 - 1) << 4, bArr.length)), this.zze);
            }
            byte[] bArr3 = new byte[16];
            byte[] bArr4 = new byte[16];
            int i3 = 0;
            while (i3 < i2 - 1) {
                zza(bArr3, bArr, i3 << 4, bArr4);
                if (zza2.doFinal(bArr4, 0, 16, bArr3) == 16) {
                    i3++;
                } else {
                    throw new IllegalStateException("Cipher didn't write full block");
                }
            }
            zza(bArr3, bArr2, 0, bArr4);
            if (zza2.doFinal(bArr4, 0, 16, bArr3) != 16) {
                throw new IllegalStateException("Cipher didn't write full block");
            } else if (16 == i) {
                return bArr3;
            } else {
                return Arrays.copyOf(bArr3, i);
            }
        } else {
            throw new InvalidAlgorithmParameterException("outputLength too large, max is 16 bytes");
        }
    }
}
