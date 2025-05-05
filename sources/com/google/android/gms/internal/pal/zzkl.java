package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzkl {
    public static zzkk zza(String str) throws GeneralSecurityException {
        zzkk zzkk = (zzkk) zzlf.zzk().get(str);
        if (zzkk != null) {
            return zzkk;
        }
        throw new GeneralSecurityException("cannot find key template: ".concat(str));
    }
}
