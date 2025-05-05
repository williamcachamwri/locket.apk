package com.google.common.collect;

import java.util.concurrent.ConcurrentMap;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
public abstract class ForwardingConcurrentMap<K, V> extends ForwardingMap<K, V> implements ConcurrentMap<K, V> {
    /* access modifiers changed from: protected */
    public abstract ConcurrentMap<K, V> delegate();

    protected ForwardingConcurrentMap() {
    }

    @CheckForNull
    public V putIfAbsent(K k, V v) {
        return delegate().putIfAbsent(k, v);
    }

    public boolean remove(@CheckForNull Object obj, @CheckForNull Object obj2) {
        return delegate().remove(obj, obj2);
    }

    @CheckForNull
    public V replace(K k, V v) {
        return delegate().replace(k, v);
    }

    public boolean replace(K k, V v, V v2) {
        return delegate().replace(k, v, v2);
    }
}
