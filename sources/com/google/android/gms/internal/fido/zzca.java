package com.google.android.gms.internal.fido;

import java.util.Comparator;
import java.util.SortedSet;

/* compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
final class zzca {
    public static boolean zza(Comparator comparator, Iterable iterable) {
        Object obj;
        comparator.getClass();
        iterable.getClass();
        if (iterable instanceof SortedSet) {
            obj = ((SortedSet) iterable).comparator();
            if (obj == null) {
                obj = zzbp.zza;
            }
        } else if (!(iterable instanceof zzbz)) {
            return false;
        } else {
            obj = ((zzbz) iterable).comparator();
        }
        return comparator.equals(obj);
    }
}
