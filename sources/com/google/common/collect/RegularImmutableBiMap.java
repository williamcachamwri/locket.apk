package com.google.common.collect;

import com.google.common.collect.RegularImmutableMap;
import java.util.Map;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
final class RegularImmutableBiMap<K, V> extends ImmutableBiMap<K, V> {
    static final RegularImmutableBiMap<Object, Object> EMPTY = new RegularImmutableBiMap<>();
    final transient Object[] alternatingKeysAndValues;
    private final transient RegularImmutableBiMap<V, K> inverse;
    @CheckForNull
    private final transient Object keyHashTable;
    private final transient int keyOffset;
    private final transient int size;

    /* access modifiers changed from: package-private */
    public boolean isPartialView() {
        return false;
    }

    private RegularImmutableBiMap() {
        this.keyHashTable = null;
        this.alternatingKeysAndValues = new Object[0];
        this.keyOffset = 0;
        this.size = 0;
        this.inverse = this;
    }

    RegularImmutableBiMap(Object[] objArr, int i) {
        this.alternatingKeysAndValues = objArr;
        this.size = i;
        this.keyOffset = 0;
        int chooseTableSize = i >= 2 ? ImmutableSet.chooseTableSize(i) : 0;
        this.keyHashTable = RegularImmutableMap.createHashTableOrThrow(objArr, i, chooseTableSize, 0);
        this.inverse = new RegularImmutableBiMap<>(RegularImmutableMap.createHashTableOrThrow(objArr, i, chooseTableSize, 1), objArr, i, this);
    }

    private RegularImmutableBiMap(@CheckForNull Object obj, Object[] objArr, int i, RegularImmutableBiMap<V, K> regularImmutableBiMap) {
        this.keyHashTable = obj;
        this.alternatingKeysAndValues = objArr;
        this.keyOffset = 1;
        this.size = i;
        this.inverse = regularImmutableBiMap;
    }

    public int size() {
        return this.size;
    }

    public ImmutableBiMap<V, K> inverse() {
        return this.inverse;
    }

    @CheckForNull
    public V get(@CheckForNull Object obj) {
        V v = RegularImmutableMap.get(this.keyHashTable, this.alternatingKeysAndValues, this.size, this.keyOffset, obj);
        if (v == null) {
            return null;
        }
        return v;
    }

    /* access modifiers changed from: package-private */
    public ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        return new RegularImmutableMap.EntrySet(this, this.alternatingKeysAndValues, this.keyOffset, this.size);
    }

    /* access modifiers changed from: package-private */
    public ImmutableSet<K> createKeySet() {
        return new RegularImmutableMap.KeySet(this, new RegularImmutableMap.KeysOrValuesAsList(this.alternatingKeysAndValues, this.keyOffset, this.size));
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return super.writeReplace();
    }
}
