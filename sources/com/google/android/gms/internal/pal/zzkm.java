package com.google.android.gms.internal.pal;

import java.io.IOException;
import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzkm {
    private final zzwb zza;
    private final zzrb zzb = zzrb.zza;

    private zzkm(zzwb zzwb) {
        this.zza = zzwb;
    }

    static final zzkm zza(zzwb zzwb) throws GeneralSecurityException {
        if (zzwb != null && zzwb.zza() > 0) {
            return new zzkm(zzwb);
        }
        throw new GeneralSecurityException("empty keyset");
    }

    public static final zzkm zzb(zzkn zzkn) throws GeneralSecurityException, IOException {
        try {
            zzwb zzb2 = zzkn.zzb();
            for (zzwa zzwa : zzb2.zzg()) {
                if (zzwa.zzc().zzc() == zzvn.UNKNOWN_KEYMATERIAL || zzwa.zzc().zzc() == zzvn.SYMMETRIC || zzwa.zzc().zzc() == zzvn.ASYMMETRIC_PRIVATE) {
                    throw new GeneralSecurityException(String.format("keyset contains key material of type %s for type url %s", new Object[]{zzwa.zzc().zzc().name(), zzwa.zzc().zzg()}));
                }
            }
            return zza(zzb2);
        } catch (zzadi unused) {
            throw new GeneralSecurityException("invalid keyset");
        }
    }

    public final String toString() {
        return zzlh.zza(this.zza).toString();
    }

    public final Object zzc(Class cls) throws GeneralSecurityException {
        Class zze = zzlf.zze(cls);
        if (zze != null) {
            zzlh.zzb(this.zza);
            zzku zzku = new zzku(zze, (zzkt) null);
            zzku.zzc(this.zzb);
            for (zzwa zzwa : this.zza.zzg()) {
                if (zzwa.zzi() == 3) {
                    Object zzf = zzlf.zzf(zzwa.zzc(), zze);
                    if (zzwa.zza() == this.zza.zzc()) {
                        zzku.zza(zzf, zzwa);
                    } else {
                        zzku.zzb(zzf, zzwa);
                    }
                }
            }
            return zzlf.zzj(zzku.zzd(), cls);
        }
        throw new GeneralSecurityException("No wrapper found for ".concat(String.valueOf(cls.getName())));
    }
}
