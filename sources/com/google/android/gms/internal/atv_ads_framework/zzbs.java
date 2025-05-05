package com.google.android.gms.internal.atv_ads_framework;

import java.util.Iterator;
import java.util.Set;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public final class zzbs {
    static int zza(Set set) {
        Iterator it = set.iterator();
        int i = 0;
        while (it.hasNext()) {
            Object next = it.next();
            i += next != null ? next.hashCode() : 0;
        }
        return i;
    }
}
