package com.google.ads.interactivemedia.v3.internal;

import java.util.Comparator;
import java.util.SortedSet;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzsr {
    public static boolean zza(Comparator comparator, Iterable iterable) {
        Object obj;
        comparator.getClass();
        iterable.getClass();
        if (iterable instanceof SortedSet) {
            obj = ((SortedSet) iterable).comparator();
            if (obj == null) {
                obj = zzsb.zza;
            }
        } else if (!(iterable instanceof zzsq)) {
            return false;
        } else {
            obj = ((zzsq) iterable).comparator();
        }
        return comparator.equals(obj);
    }
}
