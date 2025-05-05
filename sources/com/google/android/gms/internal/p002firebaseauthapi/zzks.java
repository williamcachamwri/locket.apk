package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzks  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzks {
    /* access modifiers changed from: private */
    public static final byte[] zza = new byte[0];

    public static zzkw zza(zzjp zzjp) throws GeneralSecurityException {
        zzch zzb = zzjp.zzb();
        if (zzb instanceof zzdv) {
            return new zzku((zzdv) zzb);
        }
        if (zzb instanceof zzdg) {
            return new zzkv((zzdg) zzb);
        }
        if (zzb instanceof zziv) {
            return new zzkx((zziv) zzb);
        }
        throw new GeneralSecurityException("Unsupported DEM parameters: " + String.valueOf(zzb));
    }
}
