package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzju;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlq  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzlq {
    public static final byte[] zza = zza(1, 0);
    public static final byte[] zzb = zza(2, 32);
    public static final byte[] zzc = zza(2, 16);
    public static final byte[] zzd = zza(2, 17);
    public static final byte[] zze = zza(2, 18);
    public static final byte[] zzf = zza(2, 1);
    public static final byte[] zzg = zza(2, 2);
    public static final byte[] zzh = zza(2, 3);
    public static final byte[] zzi = zza(2, 1);
    public static final byte[] zzj = zza(2, 2);
    public static final byte[] zzk = zza(2, 3);
    public static final byte[] zzl = new byte[0];
    private static final byte[] zzm = "KEM".getBytes(zzpr.zza);
    private static final byte[] zzn = "HPKE".getBytes(zzpr.zza);
    private static final byte[] zzo = "HPKE-v1".getBytes(zzpr.zza);

    public static int zza(zzju.zzf zzf2) throws GeneralSecurityException {
        if (zzf2 == zzju.zzf.zzd || zzf2 == zzju.zzf.zza) {
            return 32;
        }
        if (zzf2 == zzju.zzf.zzb) {
            return 48;
        }
        if (zzf2 == zzju.zzf.zzc) {
            return 66;
        }
        throw new GeneralSecurityException("Unrecognized HPKE KEM identifier");
    }

    public static int zzb(zzju.zzf zzf2) throws GeneralSecurityException {
        if (zzf2 == zzju.zzf.zzd) {
            return 32;
        }
        if (zzf2 == zzju.zzf.zza) {
            return 65;
        }
        if (zzf2 == zzju.zzf.zzb) {
            return 97;
        }
        if (zzf2 == zzju.zzf.zzc) {
            return 133;
        }
        throw new GeneralSecurityException("Unrecognized HPKE KEM identifier");
    }

    static zzya zzc(zzju.zzf zzf2) throws GeneralSecurityException {
        if (zzf2 == zzju.zzf.zza) {
            return zzya.NIST_P256;
        }
        if (zzf2 == zzju.zzf.zzb) {
            return zzya.NIST_P384;
        }
        if (zzf2 == zzju.zzf.zzc) {
            return zzya.NIST_P521;
        }
        throw new GeneralSecurityException("Unrecognized NIST HPKE KEM identifier");
    }

    static {
        zza(1, 2);
    }

    static byte[] zza(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        return zzxv.zza(zzn, bArr, bArr2, bArr3);
    }

    private static byte[] zza(int i, int i2) {
        if (i > 4 || i < 0) {
            throw new IllegalArgumentException("capacity must be between 0 and 4");
        } else if (i2 < 0 || (i < 4 && i2 >= (1 << (i << 3)))) {
            throw new IllegalArgumentException("value too large");
        } else {
            byte[] bArr = new byte[i];
            for (int i3 = 0; i3 < i; i3++) {
                bArr[i3] = (byte) (i2 >> (((i - i3) - 1) * 8));
            }
            return bArr;
        }
    }

    static byte[] zza(byte[] bArr) throws GeneralSecurityException {
        return zzxv.zza(zzm, bArr);
    }

    static byte[] zza(String str, byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return zzxv.zza(zzo, bArr2, str.getBytes(zzpr.zza), bArr);
    }

    static byte[] zza(String str, byte[] bArr, byte[] bArr2, int i) throws GeneralSecurityException {
        return zzxv.zza(zza(2, i), zzo, bArr2, str.getBytes(zzpr.zza), bArr);
    }
}
