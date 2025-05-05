package com.google.ads.interactivemedia.v3.internal;

import java.io.Serializable;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzsm extends zzsd implements Serializable {
    static final zzsm zza = new zzsm();

    private zzsm() {
    }

    public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
        Comparable comparable = (Comparable) obj;
        Comparable comparable2 = (Comparable) obj2;
        comparable.getClass();
        if (comparable == comparable2) {
            return 0;
        }
        return comparable2.compareTo(comparable);
    }

    public final String toString() {
        return "Ordering.natural().reverse()";
    }

    public final zzsd zza() {
        return zzsb.zza;
    }
}
