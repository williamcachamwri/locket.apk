package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzqp extends zzpq {
    zzqp(Class cls) {
        super(cls);
    }

    public final /* bridge */ /* synthetic */ Object zza(zzaef zzaef) throws GeneralSecurityException {
        zzup zzup = (zzup) zzaef;
        int zzg = zzup.zzg().zzg();
        SecretKeySpec secretKeySpec = new SecretKeySpec(zzup.zzh().zzt(), "HMAC");
        int zza = zzup.zzg().zza();
        int i = zzg - 2;
        if (i == 1) {
            return new zzyo(new zzyn("HMACSHA1", secretKeySpec), zza);
        }
        if (i == 2) {
            return new zzyo(new zzyn("HMACSHA384", secretKeySpec), zza);
        }
        if (i == 3) {
            return new zzyo(new zzyn("HMACSHA256", secretKeySpec), zza);
        }
        if (i == 4) {
            return new zzyo(new zzyn("HMACSHA512", secretKeySpec), zza);
        }
        if (i == 5) {
            return new zzyo(new zzyn("HMACSHA224", secretKeySpec), zza);
        }
        throw new GeneralSecurityException("unknown hash");
    }
}
