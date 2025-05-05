package com.google.ads.interactivemedia.v3.internal;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzahw implements Map.Entry, Comparable, Serializable {
    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        zzahw zzahw = (zzahw) obj;
        zzahn zzahn = new zzahn();
        zzahn.zzb(zza(), zzahw.zza(), (Comparator) null);
        zzahn.zzb(zzb(), zzahw.zzb(), (Comparator) null);
        return zzahn.zza();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            return Objects.equals(zza(), entry.getKey()) && Objects.equals(zzb(), entry.getValue());
        }
    }

    public final Object getKey() {
        return zza();
    }

    public final Object getValue() {
        return zzb();
    }

    public final int hashCode() {
        return Objects.hashCode(zza()) ^ Objects.hashCode(zzb());
    }

    public final String toString() {
        String valueOf = String.valueOf(zza());
        String valueOf2 = String.valueOf(zzb());
        return "(" + valueOf + "," + valueOf2 + ")";
    }

    public abstract Object zza();

    public abstract Object zzb();
}
