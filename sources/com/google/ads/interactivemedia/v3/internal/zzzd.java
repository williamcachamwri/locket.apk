package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.Type;
import java.util.Map;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzzd implements zzwk {
    private final zzxl zza;

    public zzzd(zzxl zzxl, boolean z) {
        this.zza = zzxl;
    }

    public final zzwj zza(zzvr zzvr, zzaca zzaca) {
        zzwj zzwj;
        Type zzd = zzaca.zzd();
        Class zzc = zzaca.zzc();
        if (!Map.class.isAssignableFrom(zzc)) {
            return null;
        }
        Type[] zzh = zzwr.zzh(zzd, zzc);
        Type type = zzh[0];
        if (type == Boolean.TYPE || type == Boolean.class) {
            zzwj = zzabh.zzf;
        } else {
            zzwj = zzvr.zza(zzaca.zzb(type));
        }
        zzwj zza2 = zzvr.zza(zzaca.zzb(zzh[1]));
        zzya zza3 = this.zza.zza(zzaca);
        return new zzzc(this, zzvr, zzh[0], zzwj, zzh[1], zza2, zza3);
    }
}
