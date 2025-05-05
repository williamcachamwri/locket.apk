package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzakj  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzakj<K> implements Map.Entry<K, Object> {
    private Map.Entry<K, zzakg> zza;

    public final zzakg zza() {
        return this.zza.getValue();
    }

    public final K getKey() {
        return this.zza.getKey();
    }

    public final Object getValue() {
        if (this.zza.getValue() == null) {
            return null;
        }
        throw new NoSuchMethodError();
    }

    public final Object setValue(Object obj) {
        if (obj instanceof zzalc) {
            return this.zza.getValue().zza((zzalc) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }

    private zzakj(Map.Entry<K, zzakg> entry) {
        this.zza = entry;
    }
}
