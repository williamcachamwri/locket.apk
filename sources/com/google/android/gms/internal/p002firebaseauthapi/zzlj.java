package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzju;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlj  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzlj implements zzbo {
    private static final byte[] zza = new byte[0];
    private final zzln zzb;
    private final zzlk zzc;
    private final zzll zzd;
    private final zzlh zze;
    private final int zzf;
    private final byte[] zzg;

    public static zzbo zza(zzjw zzjw) throws GeneralSecurityException {
        int i;
        zzya zzya;
        zzln zza2;
        zzju zzc2 = zzjw.zzc();
        zzlk zza3 = zzli.zza(zzc2.zze());
        zzll zza4 = zzli.zza(zzc2.zzd());
        zzlh zza5 = zzli.zza(zzc2.zzb());
        zzju.zzf zze2 = zzc2.zze();
        if (zze2.equals(zzju.zzf.zzd)) {
            i = 32;
        } else if (zze2.equals(zzju.zzf.zza)) {
            i = 65;
        } else if (zze2.equals(zzju.zzf.zzb)) {
            i = 97;
        } else if (zze2.equals(zzju.zzf.zzc)) {
            i = 133;
        } else {
            throw new GeneralSecurityException("Unrecognized HPKE KEM identifier");
        }
        int i2 = i;
        zzju.zzf zze3 = zzjw.zzc().zze();
        if (zze3.equals(zzju.zzf.zzd)) {
            zza2 = zzmb.zza(zzjw.zze().zza(zzbq.zza()));
        } else if (zze3.equals(zzju.zzf.zza) || zze3.equals(zzju.zzf.zzb) || zze3.equals(zzju.zzf.zzc)) {
            byte[] zza6 = zzjw.zze().zza(zzbq.zza());
            byte[] zzb2 = ((zzke) ((zzkr) zzjw.zzb())).zzd().zzb();
            if (zze3.equals(zzju.zzf.zza)) {
                zzya = zzya.NIST_P256;
            } else if (zze3.equals(zzju.zzf.zzb)) {
                zzya = zzya.NIST_P384;
            } else if (zze3.equals(zzju.zzf.zzc)) {
                zzya = zzya.NIST_P521;
            } else {
                throw new GeneralSecurityException("Unrecognized NIST HPKE KEM identifier");
            }
            zza2 = zzlz.zza(zza6, zzb2, zzya);
        } else {
            throw new GeneralSecurityException("Unrecognized HPKE KEM identifier");
        }
        return new zzlj(zza2, zza3, zza4, zza5, i2, zzjw.zzg());
    }

    private zzlj(zzln zzln, zzlk zzlk, zzll zzll, zzlh zzlh, int i, zzzc zzzc) {
        this.zzb = zzln;
        this.zzc = zzlk;
        this.zzd = zzll;
        this.zze = zzlh;
        this.zzf = i;
        this.zzg = zzzc.zzb();
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArr3 = this.zzg;
        int length = bArr3.length + this.zzf;
        if (bArr.length < length) {
            throw new GeneralSecurityException("Ciphertext is too short.");
        } else if (zzpr.zza(bArr3, bArr)) {
            if (bArr2 == null) {
                bArr2 = new byte[0];
            }
            return zzlg.zza(Arrays.copyOfRange(bArr, this.zzg.length, length), this.zzb, this.zzc, this.zzd, this.zze, bArr2).zza(bArr, length, zza);
        } else {
            throw new GeneralSecurityException("Invalid ciphertext (output prefix mismatch)");
        }
    }
}
