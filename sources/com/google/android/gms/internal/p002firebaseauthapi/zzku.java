package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzdv;
import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzku  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzku implements zzkw {
    private final int zza;

    public final int zza() {
        return this.zza;
    }

    public zzku(zzdv zzdv) throws GeneralSecurityException {
        if (zzdv.zzb() != 12) {
            throw new GeneralSecurityException("invalid IV size");
        } else if (zzdv.zzd() != 16) {
            throw new GeneralSecurityException("invalid tag size");
        } else if (zzdv.zzf() == zzdv.zzb.zzc) {
            this.zza = zzdv.zzc();
        } else {
            throw new GeneralSecurityException("invalid variant");
        }
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2, int i) throws GeneralSecurityException {
        if (bArr2.length < i) {
            throw new GeneralSecurityException("ciphertext too short");
        } else if (bArr.length == this.zza) {
            SecretKey zzb = zzgo.zzb(bArr);
            int i2 = i + 12;
            if (bArr2.length >= i2 + 16) {
                AlgorithmParameterSpec zza2 = zzgo.zza(bArr2, i, 12);
                Cipher zza3 = zzgo.zza();
                zza3.init(2, zzb, zza2);
                return zza3.doFinal(bArr2, i2, (bArr2.length - i) - 12);
            }
            throw new GeneralSecurityException("ciphertext too short");
        } else {
            throw new GeneralSecurityException("invalid key size");
        }
    }
}
