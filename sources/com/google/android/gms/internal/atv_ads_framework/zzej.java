package com.google.android.gms.internal.atv_ads_framework;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
final class zzej {
    zzej() {
    }

    public static final int zza(int i, Object obj, Object obj2) {
        zzei zzei = (zzei) obj;
        zzeh zzeh = (zzeh) obj2;
        if (zzei.isEmpty()) {
            return 0;
        }
        Iterator it = zzei.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw null;
    }
}
