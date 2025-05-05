package com.google.ads.interactivemedia.v3.internal;

import java.io.Serializable;
import javax.annotation.CheckForNull;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzsn extends zzsd implements Serializable {
    final zzsd zza;

    zzsn(zzsd zzsd) {
        zzsd zzsd2 = zzsd;
        this.zza = zzsd;
    }

    public final int compare(Object obj, Object obj2) {
        return this.zza.compare(obj2, obj);
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzsn) {
            return this.zza.equals(((zzsn) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return -this.zza.hashCode();
    }

    public final String toString() {
        return this.zza.toString().concat(".reverse()");
    }

    public final zzsd zza() {
        return this.zza;
    }
}
