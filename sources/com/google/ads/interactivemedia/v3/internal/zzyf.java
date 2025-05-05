package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.AccessibleObject;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzyf {
    public static boolean zza(AccessibleObject accessibleObject, Object obj) {
        return zzye.zzb.zza(accessibleObject, obj);
    }

    public static int zzb(List list, Class cls) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            int zza = ((zzwf) it.next()).zza();
            if (zza != 2) {
                return zza;
            }
        }
        return 1;
    }
}
