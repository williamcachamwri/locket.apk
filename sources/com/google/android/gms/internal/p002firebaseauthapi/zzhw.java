package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhw  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzhw implements zzbg {
    private final byte[] zza;
    private final int zzb;
    private final zzrq zzc;

    public static zzbg zza(zzfp zzfp) throws GeneralSecurityException {
        if (zzfp.zzb().zzb() >= 8 && zzfp.zzb().zzb() <= 12) {
            return new zzhw(zzfp.zzd().zza(zzbq.zza()), zzfp.zzc(), zzfp.zzb().zzb());
        }
        throw new GeneralSecurityException("invalid salt size");
    }

    private zzhw(byte[] bArr, zzzc zzzc, int i) throws GeneralSecurityException {
        this.zzc = new zzyv(bArr);
        this.zza = zzzc.zzb();
        this.zzb = i;
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr != null) {
            int length = bArr.length;
            byte[] bArr3 = this.zza;
            if (length < bArr3.length + this.zzb + 12 + 16) {
                throw new GeneralSecurityException("ciphertext too short");
            } else if (zzpr.zza(bArr3, bArr)) {
                int length2 = this.zza.length + this.zzb;
                int i = length2 + 12;
                return new zzhj(zza(Arrays.copyOfRange(bArr, this.zza.length, length2))).zza(Arrays.copyOfRange(bArr, length2, i), bArr, i, bArr2);
            } else {
                throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
            }
        } else {
            throw new NullPointerException("ciphertext is null");
        }
    }

    private final byte[] zza(byte[] bArr) throws GeneralSecurityException {
        byte[] bArr2 = {0, 1, 88, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        byte[] bArr3 = {0, 2, 88, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        if (bArr.length > 12 || bArr.length < 8) {
            throw new GeneralSecurityException("invalid salt size");
        }
        System.arraycopy(bArr, 0, bArr2, 4, bArr.length);
        System.arraycopy(bArr, 0, bArr3, 4, bArr.length);
        byte[] bArr4 = new byte[32];
        System.arraycopy(this.zzc.zza(bArr2, 16), 0, bArr4, 0, 16);
        System.arraycopy(this.zzc.zza(bArr3, 16), 0, bArr4, 16, 16);
        return bArr4;
    }

    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr != null) {
            byte[] zza2 = zzpe.zza(this.zzb + 12);
            byte[] copyOf = Arrays.copyOf(zza2, this.zzb);
            int i = this.zzb;
            byte[] copyOfRange = Arrays.copyOfRange(zza2, i, i + 12);
            byte[] zzb2 = new zzhj(zza(copyOf)).zzb(copyOfRange, bArr, this.zza.length + this.zzb + copyOfRange.length, bArr2);
            byte[] bArr3 = this.zza;
            System.arraycopy(bArr3, 0, zzb2, 0, bArr3.length);
            System.arraycopy(zza2, 0, zzb2, this.zza.length, zza2.length);
            return zzb2;
        }
        throw new NullPointerException("plaintext is null");
    }
}
