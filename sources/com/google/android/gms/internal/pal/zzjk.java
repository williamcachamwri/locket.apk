package com.google.android.gms.internal.pal;

import java.util.Iterator;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzjk {
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
