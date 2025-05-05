package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlt  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzlt implements zzbo {
    private final zzbo zza;
    private final byte[] zzb;

    public static zzbo zza(zznc zznc) throws GeneralSecurityException {
        byte[] bArr;
        zzpc zza2 = zznc.zza(zzbq.zza());
        zzbo zzbo = (zzbo) zzco.zza((zzvq) ((zzajy) zzvq.zza().zza(zza2.zzf()).zza(zza2.zzd()).zza(zza2.zza()).zze()), zzbo.class);
        zzws zzb2 = zza2.zzb();
        int i = zzls.zza[zzb2.ordinal()];
        if (i == 1) {
            bArr = zzog.zza.zzb();
        } else if (i == 2 || i == 3) {
            bArr = zzog.zza(zznc.zza().intValue()).zzb();
        } else if (i == 4) {
            bArr = zzog.zzb(zznc.zza().intValue()).zzb();
        } else {
            throw new GeneralSecurityException("unknown output prefix type " + String.valueOf(zzb2));
        }
        return new zzlt(zzbo, bArr);
    }

    private zzlt(zzbo zzbo, byte[] bArr) {
        this.zza = zzbo;
        this.zzb = bArr;
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArr3 = this.zzb;
        if (bArr3.length == 0) {
            return this.zza.zza(bArr, bArr2);
        }
        if (zzpr.zza(bArr3, bArr)) {
            return this.zza.zza(Arrays.copyOfRange(bArr, this.zzb.length, bArr.length), bArr2);
        }
        throw new GeneralSecurityException("Invalid ciphertext (output prefix mismatch)");
    }
}
