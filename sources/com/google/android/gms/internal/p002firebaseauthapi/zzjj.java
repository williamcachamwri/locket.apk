package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjj  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzjj implements zzbl {
    private final zzbl zza;
    private final zzws zzb;
    private final byte[] zzc;

    public static zzbl zza(zznc zznc) throws GeneralSecurityException {
        byte[] bArr;
        zzpc zza2 = zznc.zza(zzbq.zza());
        zzpg.zza();
        zzbl zzbl = (zzbl) zzpg.zza((zzvq) ((zzajy) zzvq.zza().zza(zza2.zzf()).zza(zza2.zzd()).zza(zza2.zza()).zze()), zzbl.class);
        zzws zzb2 = zza2.zzb();
        int i = zzjm.zza[zzb2.ordinal()];
        if (i == 1) {
            bArr = zzog.zza.zzb();
        } else if (i == 2 || i == 3) {
            bArr = zzog.zza(zznc.zza().intValue()).zzb();
        } else if (i == 4) {
            bArr = zzog.zzb(zznc.zza().intValue()).zzb();
        } else {
            throw new GeneralSecurityException("unknown output prefix type " + zzb2.zza());
        }
        return new zzjj(zzbl, zzb2, bArr);
    }

    private zzjj(zzbl zzbl, zzws zzws, byte[] bArr) {
        this.zza = zzbl;
        this.zzb = zzws;
        this.zzc = bArr;
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (this.zzb == zzws.RAW) {
            return this.zza.zza(bArr, bArr2);
        }
        if (zzpr.zza(this.zzc, bArr)) {
            return this.zza.zza(Arrays.copyOfRange(bArr, 5, bArr.length), bArr2);
        }
        throw new GeneralSecurityException("wrong prefix");
    }
}
