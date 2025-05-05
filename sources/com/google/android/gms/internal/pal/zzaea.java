package com.google.android.gms.internal.pal;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzaea {
    zzaea() {
    }

    public static final int zza(int i, Object obj, Object obj2) {
        zzadz zzadz = (zzadz) obj;
        zzady zzady = (zzady) obj2;
        if (zzadz.isEmpty()) {
            return 0;
        }
        Iterator it = zzadz.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw null;
    }

    public static final boolean zzb(Object obj) {
        return !((zzadz) obj).zze();
    }

    public static final Object zzc(Object obj, Object obj2) {
        zzadz zzadz = (zzadz) obj;
        zzadz zzadz2 = (zzadz) obj2;
        if (!zzadz2.isEmpty()) {
            if (!zzadz.zze()) {
                zzadz = zzadz.zzb();
            }
            zzadz.zzd(zzadz2);
        }
        return zzadz;
    }
}
