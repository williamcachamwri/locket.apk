package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
final class zzky implements zzkv {
    public final int zza(int i, Object obj, Object obj2) {
        zzkw zzkw = (zzkw) obj;
        zzku zzku = (zzku) obj2;
        if (zzkw.isEmpty()) {
            return 0;
        }
        Iterator it = zzkw.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }

    public final zzkt<?, ?> zza(Object obj) {
        zzku zzku = (zzku) obj;
        throw new NoSuchMethodError();
    }

    public final Object zza(Object obj, Object obj2) {
        zzkw zzkw = (zzkw) obj;
        zzkw zzkw2 = (zzkw) obj2;
        if (!zzkw2.isEmpty()) {
            if (!zzkw.zzd()) {
                zzkw = zzkw.zzb();
            }
            zzkw.zza(zzkw2);
        }
        return zzkw;
    }

    public final Object zzb(Object obj) {
        return zzkw.zza().zzb();
    }

    public final Object zzc(Object obj) {
        ((zzkw) obj).zzc();
        return obj;
    }

    public final Map<?, ?> zzd(Object obj) {
        return (zzkw) obj;
    }

    public final Map<?, ?> zze(Object obj) {
        return (zzkw) obj;
    }

    zzky() {
    }

    public final boolean zzf(Object obj) {
        return !((zzkw) obj).zzd();
    }
}
