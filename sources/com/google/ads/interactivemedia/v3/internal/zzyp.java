package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.Type;
import java.util.Collection;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzyp implements zzwk {
    private final zzxl zza;

    public zzyp(zzxl zzxl) {
        this.zza = zzxl;
    }

    public final zzwj zza(zzvr zzvr, zzaca zzaca) {
        Type zzd = zzaca.zzd();
        Class zzc = zzaca.zzc();
        if (!Collection.class.isAssignableFrom(zzc)) {
            return null;
        }
        Type zzd2 = zzwr.zzd(zzd, zzc);
        return new zzyo(zzvr, zzd2, zzvr.zza(zzaca.zzb(zzd2)), this.zza.zza(zzaca));
    }
}
