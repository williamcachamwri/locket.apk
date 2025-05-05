package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlv  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzlv implements zzbn {
    public static zzbn zza(zznc zznc) throws GeneralSecurityException {
        byte[] bArr;
        zzpc zza = zznc.zza(zzbq.zza());
        zzbn zzbn = (zzbn) zzco.zza((zzvq) ((zzajy) zzvq.zza().zza(zza.zzf()).zza(zza.zzd()).zza(zza.zza()).zze()), zzbn.class);
        zzws zzb = zza.zzb();
        int i = zzlu.zza[zzb.ordinal()];
        if (i == 1) {
            bArr = zzog.zza.zzb();
        } else if (i == 2 || i == 3) {
            bArr = zzog.zza(zznc.zza().intValue()).zzb();
        } else if (i == 4) {
            bArr = zzog.zzb(zznc.zza().intValue()).zzb();
        } else {
            throw new GeneralSecurityException("unknown output prefix type " + String.valueOf(zzb));
        }
        return new zzlv(zzbn, bArr);
    }

    private zzlv(zzbn zzbn, byte[] bArr) {
    }
}
