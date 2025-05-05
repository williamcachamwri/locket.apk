package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzpf {
    public static final zzrc zza = new zzpe((zzpd) null);

    public static zzri zza(zzlb zzlb) {
        zzkj zzkj;
        zzre zzre = new zzre();
        zzre.zzb(zzlb.zzb());
        for (List it : zzlb.zzd()) {
            Iterator it2 = it.iterator();
            while (true) {
                if (it2.hasNext()) {
                    zzkv zzkv = (zzkv) it2.next();
                    int zze = zzkv.zze() - 2;
                    if (zze == 1) {
                        zzkj = zzkj.zza;
                    } else if (zze == 2) {
                        zzkj = zzkj.zzb;
                    } else if (zze == 3) {
                        zzkj = zzkj.zzc;
                    } else {
                        throw new IllegalStateException("Unknown key status");
                    }
                    zzre.zza(zzkj, zzkv.zza(), zzkv.zzb());
                }
            }
        }
        if (zzlb.zza() != null) {
            zzre.zzc(zzlb.zza().zza());
        }
        try {
            return zzre.zzd();
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException(e);
        }
    }
}
