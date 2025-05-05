package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
final class zzfj {
    zzfj() {
    }

    public static final int zza(int i, Object obj, Object obj2) {
        zzfi zzfi = (zzfi) obj;
        zzfh zzfh = (zzfh) obj2;
        if (zzfi.isEmpty()) {
            return 0;
        }
        Iterator it = zzfi.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw null;
    }

    public static final Object zzb(Object obj, Object obj2) {
        zzfi zzfi = (zzfi) obj;
        zzfi zzfi2 = (zzfi) obj2;
        if (!zzfi2.isEmpty()) {
            if (!zzfi.zze()) {
                zzfi = zzfi.zzb();
            }
            zzfi.zzd(zzfi2);
        }
        return zzfi;
    }
}
