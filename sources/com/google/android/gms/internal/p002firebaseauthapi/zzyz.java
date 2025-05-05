package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzpx;
import com.google.android.gms.internal.p002firebaseauthapi.zzqk;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyz  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzyz implements zzci {
    private static final byte[] zza = {0};
    private final zzrq zzb;
    private final int zzc;
    private final byte[] zzd;
    private final byte[] zze;

    public static zzci zza(zzpq zzpq) throws GeneralSecurityException {
        return new zzyz(zzpq);
    }

    public static zzci zza(zzqd zzqd) throws GeneralSecurityException {
        return new zzyz(zzqd);
    }

    private zzyz(zzpq zzpq) throws GeneralSecurityException {
        this.zzb = new zzyv(zzpq.zze().zza(zzbq.zza()));
        this.zzc = zzpq.zzc().zzb();
        this.zzd = zzpq.zzd().zzb();
        if (zzpq.zzc().zze().equals(zzpx.zzb.zzc)) {
            byte[] bArr = zza;
            this.zze = Arrays.copyOf(bArr, bArr.length);
            return;
        }
        this.zze = new byte[0];
    }

    private zzyz(zzqd zzqd) throws GeneralSecurityException {
        this.zzb = new zzyx("HMAC" + String.valueOf(zzqd.zzc().zze()), new SecretKeySpec(zzqd.zze().zza(zzbq.zza()), "HMAC"));
        this.zzc = zzqd.zzc().zzb();
        this.zzd = zzqd.zzd().zzb();
        if (zzqd.zzc().zzf().equals(zzqk.zzb.zzc)) {
            byte[] bArr = zza;
            this.zze = Arrays.copyOf(bArr, bArr.length);
            return;
        }
        this.zze = new byte[0];
    }

    public zzyz(zzrq zzrq, int i) throws GeneralSecurityException {
        this.zzb = zzrq;
        this.zzc = i;
        this.zzd = new byte[0];
        this.zze = new byte[0];
        if (i >= 10) {
            zzrq.zza(new byte[0], i);
            return;
        }
        throw new InvalidAlgorithmParameterException("tag size too small, need at least 10 bytes");
    }

    public final void zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (!MessageDigest.isEqual(zza(bArr2), bArr)) {
            throw new GeneralSecurityException("invalid MAC");
        }
    }

    public final byte[] zza(byte[] bArr) throws GeneralSecurityException {
        byte[] bArr2 = this.zze;
        if (bArr2.length > 0) {
            return zzxv.zza(this.zzd, this.zzb.zza(zzxv.zza(bArr, bArr2), this.zzc));
        }
        return zzxv.zza(this.zzd, this.zzb.zza(bArr, this.zzc));
    }
}
