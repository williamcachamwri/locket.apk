package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhr  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzhr implements zzbg {
    private final zzbg zza;
    private final byte[] zzb;

    public static zzbg zza(zznc zznc) throws GeneralSecurityException {
        byte[] bArr;
        zzpc zza2 = zznc.zza(zzbq.zza());
        zzpg.zza();
        zzbg zzbg = (zzbg) zzpg.zza((zzvq) ((zzajy) zzvq.zza().zza(zza2.zzf()).zza(zza2.zzd()).zza(zza2.zza()).zze()), zzbg.class);
        zzws zzb2 = zza2.zzb();
        int i = zzhu.zza[zzb2.ordinal()];
        if (i == 1) {
            bArr = zzog.zza.zzb();
        } else if (i == 2 || i == 3) {
            bArr = zzog.zza(zznc.zza().intValue()).zzb();
        } else if (i == 4) {
            bArr = zzog.zzb(zznc.zza().intValue()).zzb();
        } else {
            throw new GeneralSecurityException("unknown output prefix type " + String.valueOf(zzb2));
        }
        return new zzhr(zzbg, bArr);
    }

    public static zzbg zza(zzbg zzbg, zzzc zzzc) {
        return new zzhr(zzbg, zzzc.zzb());
    }

    private zzhr(zzbg zzbg, byte[] bArr) {
        this.zza = zzbg;
        if (bArr.length == 0 || bArr.length == 5) {
            this.zzb = bArr;
            return;
        }
        throw new IllegalArgumentException("identifier has an invalid length");
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArr3 = this.zzb;
        if (bArr3.length == 0) {
            return this.zza.zza(bArr, bArr2);
        }
        if (zzpr.zza(bArr3, bArr)) {
            return this.zza.zza(Arrays.copyOfRange(bArr, 5, bArr.length), bArr2);
        }
        throw new GeneralSecurityException("wrong prefix");
    }

    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArr3 = this.zzb;
        if (bArr3.length == 0) {
            return this.zza.zzb(bArr, bArr2);
        }
        return zzxv.zza(bArr3, this.zza.zzb(bArr, bArr2));
    }
}
