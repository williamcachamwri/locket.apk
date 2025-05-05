package com.google.mlkit.common.sdkinternal;

import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public abstract class LazyInstanceMap<K, V> {
    private final Map zza = new HashMap();

    /* access modifiers changed from: protected */
    public abstract V create(K k);

    public V get(K k) {
        synchronized (this.zza) {
            if (this.zza.containsKey(k)) {
                V v = this.zza.get(k);
                return v;
            }
            V create = create(k);
            this.zza.put(k, create);
            return create;
        }
    }
}
