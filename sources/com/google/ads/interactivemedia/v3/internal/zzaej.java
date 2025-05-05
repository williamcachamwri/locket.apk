package com.google.ads.interactivemedia.v3.internal;

import java.util.Map;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaej implements Map.Entry {
    private final Map.Entry zza;

    /* synthetic */ zzaej(Map.Entry entry, zzaei zzaei) {
        this.zza = entry;
    }

    public final Object getKey() {
        return this.zza.getKey();
    }

    public final Object getValue() {
        if (((zzael) this.zza.getValue()) == null) {
            return null;
        }
        throw null;
    }

    public final Object setValue(Object obj) {
        if (obj instanceof zzafb) {
            return ((zzael) this.zza.getValue()).zzc((zzafb) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }

    public final zzael zza() {
        return (zzael) this.zza.getValue();
    }
}
