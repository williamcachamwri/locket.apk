package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcp  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzcp {
    public static zzch zza(byte[] bArr) throws GeneralSecurityException {
        try {
            zzvu zza = zzvu.zza(bArr, zzajk.zza());
            zzof zza2 = zzof.zza();
            zzpf zza3 = zzpf.zza(zza);
            if (!zza2.zzc(zza3)) {
                return new zzne(zza3);
            }
            return zza2.zza(zza3);
        } catch (IOException e) {
            throw new GeneralSecurityException("Failed to parse proto", e);
        }
    }

    public static byte[] zza(zzch zzch) throws GeneralSecurityException {
        if (zzch instanceof zzne) {
            return ((zzne) zzch).zzb().zza().zzk();
        }
        return ((zzpf) zzof.zza().zza(zzch, zzpf.class)).zza().zzk();
    }
}
