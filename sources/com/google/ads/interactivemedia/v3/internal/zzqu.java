package com.google.ads.interactivemedia.v3.internal;

import java.io.Serializable;
import java.util.Comparator;
import javax.annotation.CheckForNull;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzqu extends zzsd implements Serializable {
    final Comparator zza;

    public final int compare(Object obj, Object obj2) {
        return this.zza.compare(obj, obj2);
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzqu) {
            return this.zza.equals(((zzqu) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final String toString() {
        return this.zza.toString();
    }

    zzqu(Comparator comparator) {
        comparator.getClass();
        Comparator comparator2 = comparator;
        this.zza = comparator;
    }
}
