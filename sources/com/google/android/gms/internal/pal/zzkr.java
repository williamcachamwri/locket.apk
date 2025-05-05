package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;

@Deprecated
/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzkr {
    @Deprecated
    public static final zzkm zza(byte[] bArr) throws GeneralSecurityException {
        try {
            zzwb zzf = zzwb.zzf(bArr, zzacm.zza());
            for (zzwa zzwa : zzf.zzg()) {
                if (zzwa.zzc().zzc() == zzvn.UNKNOWN_KEYMATERIAL || zzwa.zzc().zzc() == zzvn.SYMMETRIC || zzwa.zzc().zzc() == zzvn.ASYMMETRIC_PRIVATE) {
                    throw new GeneralSecurityException("keyset contains secret key material");
                }
            }
            return zzkm.zza(zzf);
        } catch (zzadi unused) {
            throw new GeneralSecurityException("invalid keyset");
        }
    }
}
