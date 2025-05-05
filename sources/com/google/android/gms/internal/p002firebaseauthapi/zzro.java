package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzro  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzro implements zzci {
    private static final byte[] zza = {0};
    private final zzci zzb;
    private final zzws zzc;
    private final byte[] zzd;

    public static zzci zza(zznc zznc) throws GeneralSecurityException {
        byte[] bArr;
        zzpc zza2 = zznc.zza(zzbq.zza());
        zzpg.zza();
        zzci zzci = (zzci) zzpg.zza((zzvq) ((zzajy) zzvq.zza().zza(zza2.zzf()).zza(zza2.zzd()).zza(zza2.zza()).zze()), zzci.class);
        zzws zzb2 = zza2.zzb();
        int i = zzrn.zza[zzb2.ordinal()];
        if (i == 1) {
            bArr = zzog.zza.zzb();
        } else if (i == 2 || i == 3) {
            bArr = zzog.zza(zznc.zza().intValue()).zzb();
        } else if (i == 4) {
            bArr = zzog.zzb(zznc.zza().intValue()).zzb();
        } else {
            throw new GeneralSecurityException("unknown output prefix type");
        }
        return new zzro(zzci, zzb2, bArr);
    }

    private zzro(zzci zzci, zzws zzws, byte[] bArr) {
        this.zzb = zzci;
        this.zzc = zzws;
        this.zzd = bArr;
    }

    public final void zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length >= 10) {
            if (this.zzc.equals(zzws.LEGACY)) {
                bArr2 = zzxv.zza(bArr2, zza);
            }
            byte[] bArr3 = new byte[0];
            if (!this.zzc.equals(zzws.RAW)) {
                byte[] copyOf = Arrays.copyOf(bArr, 5);
                bArr = Arrays.copyOfRange(bArr, 5, bArr.length);
                bArr3 = copyOf;
            }
            if (Arrays.equals(this.zzd, bArr3)) {
                this.zzb.zza(bArr, bArr2);
                return;
            }
            throw new GeneralSecurityException("wrong prefix");
        }
        throw new GeneralSecurityException("tag too short");
    }

    public final byte[] zza(byte[] bArr) throws GeneralSecurityException {
        if (this.zzc.equals(zzws.LEGACY)) {
            bArr = zzxv.zza(bArr, zza);
        }
        return zzxv.zza(this.zzd, this.zzb.zza(bArr));
    }
}
