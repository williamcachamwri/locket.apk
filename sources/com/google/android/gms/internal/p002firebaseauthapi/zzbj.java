package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzwa;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbj  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzbj {
    public static final byte[] zza = new byte[0];

    public static byte[] zza(zzwa.zzb zzb) throws GeneralSecurityException {
        int i = zzbm.zza[zzb.zzf().ordinal()];
        if (i == 1 || i == 2) {
            return zzog.zza(zzb.zza()).zzb();
        }
        if (i == 3) {
            return zzog.zzb(zzb.zza()).zzb();
        }
        if (i == 4) {
            return zza;
        }
        throw new GeneralSecurityException("unknown output prefix type");
    }
}
