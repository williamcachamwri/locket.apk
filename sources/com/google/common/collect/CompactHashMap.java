package com.google.common.collect;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.annotation.CheckForNull;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

@ElementTypesAreNonnullByDefault
class CompactHashMap<K, V> extends AbstractMap<K, V> implements Serializable {
    static final double HASH_FLOODING_FPP = 0.001d;
    private static final int MAX_HASH_BUCKET_LENGTH = 9;
    /* access modifiers changed from: private */
    public static final Object NOT_FOUND = new Object();
    @CheckForNull
    transient int[] entries;
    @CheckForNull
    @LazyInit
    private transient Set<Map.Entry<K, V>> entrySetView;
    @CheckForNull
    @LazyInit
    private transient Set<K> keySetView;
    @CheckForNull
    transient Object[] keys;
    /* access modifiers changed from: private */
    public transient int metadata;
    private transient int size;
    @CheckForNull
    private transient Object table;
    @CheckForNull
    transient Object[] values;
    @CheckForNull
    @LazyInit
    private transient Collection<V> valuesView;

    /* access modifiers changed from: package-private */
    public void accessEntry(int i) {
    }

    /* access modifiers changed from: package-private */
    public int adjustAfterRemove(int i, int i2) {
        return i - 1;
    }

    static /* synthetic */ int access$1210(CompactHashMap compactHashMap) {
        int i = compactHashMap.size;
        compactHashMap.size = i - 1;
        return i;
    }

    public static <K, V> CompactHashMap<K, V> create() {
        return new CompactHashMap<>();
    }

    public static <K, V> CompactHashMap<K, V> createWithExpectedSize(int i) {
        return new CompactHashMap<>(i);
    }

    CompactHashMap() {
        init(3);
    }

    CompactHashMap(int i) {
        init(i);
    }

    /* access modifiers changed from: package-private */
    public void init(int i) {
        Preconditions.checkArgument(i >= 0, "Expected size must be >= 0");
        this.metadata = Ints.constrainToRange(i, 1, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
    }

    /* access modifiers changed from: package-private */
    public boolean needsAllocArrays() {
        return this.table == null;
    }

    /* access modifiers changed from: package-private */
    public int allocArrays() {
        Preconditions.checkState(needsAllocArrays(), "Arrays already allocated");
        int i = this.metadata;
        int tableSize = CompactHashing.tableSize(i);
        this.table = CompactHashing.createTable(tableSize);
        setHashTableMask(tableSize - 1);
        this.entries = new int[i];
        this.keys = new Object[i];
        this.values = new Object[i];
        return i;
    }

    /* access modifiers changed from: package-private */
    @CheckForNull
    public Map<K, V> delegateOrNull() {
        Object obj = this.table;
        if (obj instanceof Map) {
            return (Map) obj;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public Map<K, V> createHashFloodingResistantDelegate(int i) {
        return new LinkedHashMap(i, 1.0f);
    }

    /* access modifiers changed from: package-private */
    public Map<K, V> convertToHashFloodingResistantImplementation() {
        Map<K, V> createHashFloodingResistantDelegate = createHashFloodingResistantDelegate(hashTableMask() + 1);
        int firstEntryIndex = firstEntryIndex();
        while (firstEntryIndex >= 0) {
            createHashFloodingResistantDelegate.put(key(firstEntryIndex), value(firstEntryIndex));
            firstEntryIndex = getSuccessor(firstEntryIndex);
        }
        this.table = createHashFloodingResistantDelegate;
        this.entries = null;
        this.keys = null;
        this.values = null;
        incrementModCount();
        return createHashFloodingResistantDelegate;
    }

    private void setHashTableMask(int i) {
        this.metadata = CompactHashing.maskCombine(this.metadata, 32 - Integer.numberOfLeadingZeros(i), 31);
    }

    /* access modifiers changed from: private */
    public int hashTableMask() {
        return (1 << (this.metadata & 31)) - 1;
    }

    /* access modifiers changed from: package-private */
    public void incrementModCount() {
        this.metadata += 32;
    }

    @CheckForNull
    public V put(@ParametricNullness K k, @ParametricNullness V v) {
        int i;
        int resizeTable;
        if (needsAllocArrays()) {
            allocArrays();
        }
        Map delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.put(k, v);
        }
        int[] requireEntries = requireEntries();
        Object[] requireKeys = requireKeys();
        V[] requireValues = requireValues();
        int i2 = this.size;
        int i3 = i2 + 1;
        int smearedHash = Hashing.smearedHash(k);
        int hashTableMask = hashTableMask();
        int i4 = smearedHash & hashTableMask;
        int tableGet = CompactHashing.tableGet(requireTable(), i4);
        if (tableGet != 0) {
            int hashPrefix = CompactHashing.getHashPrefix(smearedHash, hashTableMask);
            int i5 = 0;
            while (true) {
                int i6 = tableGet - 1;
                int i7 = requireEntries[i6];
                if (CompactHashing.getHashPrefix(i7, hashTableMask) != hashPrefix || !Objects.equal(k, requireKeys[i6])) {
                    int next = CompactHashing.getNext(i7, hashTableMask);
                    i5++;
                    if (next != 0) {
                        tableGet = next;
                    } else if (i5 >= 9) {
                        return convertToHashFloodingResistantImplementation().put(k, v);
                    } else {
                        if (i3 > hashTableMask) {
                            resizeTable = resizeTable(hashTableMask, CompactHashing.newCapacity(hashTableMask), smearedHash, i2);
                        } else {
                            requireEntries[i6] = CompactHashing.maskCombine(i7, i3, hashTableMask);
                        }
                    }
                } else {
                    V v2 = requireValues[i6];
                    requireValues[i6] = v;
                    accessEntry(i6);
                    return v2;
                }
            }
            i = hashTableMask;
            resizeMeMaybe(i3);
            insertEntry(i2, k, v, smearedHash, i);
            this.size = i3;
            incrementModCount();
            return null;
        } else if (i3 > hashTableMask) {
            resizeTable = resizeTable(hashTableMask, CompactHashing.newCapacity(hashTableMask), smearedHash, i2);
        } else {
            CompactHashing.tableSet(requireTable(), i4, i3);
            i = hashTableMask;
            resizeMeMaybe(i3);
            insertEntry(i2, k, v, smearedHash, i);
            this.size = i3;
            incrementModCount();
            return null;
        }
        i = resizeTable;
        resizeMeMaybe(i3);
        insertEntry(i2, k, v, smearedHash, i);
        this.size = i3;
        incrementModCount();
        return null;
    }

    /* access modifiers changed from: package-private */
    public void insertEntry(int i, @ParametricNullness K k, @ParametricNullness V v, int i2, int i3) {
        setEntry(i, CompactHashing.maskCombine(i2, 0, i3));
        setKey(i, k);
        setValue(i, v);
    }

    private void resizeMeMaybe(int i) {
        int min;
        int length = requireEntries().length;
        if (i > length && (min = Math.min(LockFreeTaskQueueCore.MAX_CAPACITY_MASK, (Math.max(1, length >>> 1) + length) | 1)) != length) {
            resizeEntries(min);
        }
    }

    /* access modifiers changed from: package-private */
    public void resizeEntries(int i) {
        this.entries = Arrays.copyOf(requireEntries(), i);
        this.keys = Arrays.copyOf(requireKeys(), i);
        this.values = Arrays.copyOf(requireValues(), i);
    }

    private int resizeTable(int i, int i2, int i3, int i4) {
        Object createTable = CompactHashing.createTable(i2);
        int i5 = i2 - 1;
        if (i4 != 0) {
            CompactHashing.tableSet(createTable, i3 & i5, i4 + 1);
        }
        Object requireTable = requireTable();
        int[] requireEntries = requireEntries();
        for (int i6 = 0; i6 <= i; i6++) {
            int tableGet = CompactHashing.tableGet(requireTable, i6);
            while (tableGet != 0) {
                int i7 = tableGet - 1;
                int i8 = requireEntries[i7];
                int hashPrefix = CompactHashing.getHashPrefix(i8, i) | i6;
                int i9 = hashPrefix & i5;
                int tableGet2 = CompactHashing.tableGet(createTable, i9);
                CompactHashing.tableSet(createTable, i9, tableGet);
                requireEntries[i7] = CompactHashing.maskCombine(hashPrefix, tableGet2, i5);
                tableGet = CompactHashing.getNext(i8, i);
            }
        }
        this.table = createTable;
        setHashTableMask(i5);
        return i5;
    }

    /* access modifiers changed from: private */
    public int indexOf(@CheckForNull Object obj) {
        if (needsAllocArrays()) {
            return -1;
        }
        int smearedHash = Hashing.smearedHash(obj);
        int hashTableMask = hashTableMask();
        int tableGet = CompactHashing.tableGet(requireTable(), smearedHash & hashTableMask);
        if (tableGet == 0) {
            return -1;
        }
        int hashPrefix = CompactHashing.getHashPrefix(smearedHash, hashTableMask);
        do {
            int i = tableGet - 1;
            int entry = entry(i);
            if (CompactHashing.getHashPrefix(entry, hashTableMask) == hashPrefix && Objects.equal(obj, key(i))) {
                return i;
            }
            tableGet = CompactHashing.getNext(entry, hashTableMask);
        } while (tableGet != 0);
        return -1;
    }

    public boolean containsKey(@CheckForNull Object obj) {
        Map delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.containsKey(obj);
        }
        return indexOf(obj) != -1;
    }

    @CheckForNull
    public V get(@CheckForNull Object obj) {
        Map delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.get(obj);
        }
        int indexOf = indexOf(obj);
        if (indexOf == -1) {
            return null;
        }
        accessEntry(indexOf);
        return value(indexOf);
    }

    @CheckForNull
    public V remove(@CheckForNull Object obj) {
        Map delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.remove(obj);
        }
        V removeHelper = removeHelper(obj);
        if (removeHelper == NOT_FOUND) {
            return null;
        }
        return removeHelper;
    }

    /* access modifiers changed from: private */
    public Object removeHelper(@CheckForNull Object obj) {
        if (needsAllocArrays()) {
            return NOT_FOUND;
        }
        int hashTableMask = hashTableMask();
        int remove = CompactHashing.remove(obj, (Object) null, hashTableMask, requireTable(), requireEntries(), requireKeys(), (Object[]) null);
        if (remove == -1) {
            return NOT_FOUND;
        }
        Object value = value(remove);
        moveLastEntry(remove, hashTableMask);
        this.size--;
        incrementModCount();
        return value;
    }

    /* access modifiers changed from: package-private */
    public void moveLastEntry(int i, int i2) {
        Object requireTable = requireTable();
        int[] requireEntries = requireEntries();
        Object[] requireKeys = requireKeys();
        Object[] requireValues = requireValues();
        int size2 = size() - 1;
        if (i < size2) {
            Object obj = requireKeys[size2];
            requireKeys[i] = obj;
            requireValues[i] = requireValues[size2];
            requireKeys[size2] = null;
            requireValues[size2] = null;
            requireEntries[i] = requireEntries[size2];
            requireEntries[size2] = 0;
            int smearedHash = Hashing.smearedHash(obj) & i2;
            int tableGet = CompactHashing.tableGet(requireTable, smearedHash);
            int i3 = size2 + 1;
            if (tableGet == i3) {
                CompactHashing.tableSet(requireTable, smearedHash, i + 1);
                return;
            }
            while (true) {
                int i4 = tableGet - 1;
                int i5 = requireEntries[i4];
                int next = CompactHashing.getNext(i5, i2);
                if (next == i3) {
                    requireEntries[i4] = CompactHashing.maskCombine(i5, i + 1, i2);
                    return;
                }
                tableGet = next;
            }
        } else {
            requireKeys[i] = null;
            requireValues[i] = null;
            requireEntries[i] = 0;
        }
    }

    /* access modifiers changed from: package-private */
    public int firstEntryIndex() {
        return isEmpty() ? -1 : 0;
    }

    /* access modifiers changed from: package-private */
    public int getSuccessor(int i) {
        int i2 = i + 1;
        if (i2 < this.size) {
            return i2;
        }
        return -1;
    }

    private abstract class Itr<T> implements Iterator<T> {
        int currentIndex;
        int expectedMetadata;
        int indexToRemove;

        /* access modifiers changed from: package-private */
        @ParametricNullness
        public abstract T getOutput(int i);

        private Itr() {
            this.expectedMetadata = CompactHashMap.this.metadata;
            this.currentIndex = CompactHashMap.this.firstEntryIndex();
            this.indexToRemove = -1;
        }

        public boolean hasNext() {
            return this.currentIndex >= 0;
        }

        @ParametricNullness
        public T next() {
            checkForConcurrentModification();
            if (hasNext()) {
                int i = this.currentIndex;
                this.indexToRemove = i;
                T output = getOutput(i);
                this.currentIndex = CompactHashMap.this.getSuccessor(this.currentIndex);
                return output;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            checkForConcurrentModification();
            CollectPreconditions.checkRemove(this.indexToRemove >= 0);
            incrementExpectedModCount();
            CompactHashMap compactHashMap = CompactHashMap.this;
            compactHashMap.remove(compactHashMap.key(this.indexToRemove));
            this.currentIndex = CompactHashMap.this.adjustAfterRemove(this.currentIndex, this.indexToRemove);
            this.indexToRemove = -1;
        }

        /* access modifiers changed from: package-private */
        public void incrementExpectedModCount() {
            this.expectedMetadata += 32;
        }

        private void checkForConcurrentModification() {
            if (CompactHashMap.this.metadata != this.expectedMetadata) {
                throw new ConcurrentModificationException();
            }
        }
    }

    public Set<K> keySet() {
        Set<K> set = this.keySetView;
        if (set != null) {
            return set;
        }
        Set<K> createKeySet = createKeySet();
        this.keySetView = createKeySet;
        return createKeySet;
    }

    /* access modifiers changed from: package-private */
    public Set<K> createKeySet() {
        return new KeySetView();
    }

    class KeySetView extends AbstractSet<K> {
        KeySetView() {
        }

        public int size() {
            return CompactHashMap.this.size();
        }

        public boolean contains(@CheckForNull Object obj) {
            return CompactHashMap.this.containsKey(obj);
        }

        public boolean remove(@CheckForNull Object obj) {
            Map delegateOrNull = CompactHashMap.this.delegateOrNull();
            if (delegateOrNull != null) {
                return delegateOrNull.keySet().remove(obj);
            }
            return CompactHashMap.this.removeHelper(obj) != CompactHashMap.NOT_FOUND;
        }

        public Iterator<K> iterator() {
            return CompactHashMap.this.keySetIterator();
        }

        public void clear() {
            CompactHashMap.this.clear();
        }
    }

    /* access modifiers changed from: package-private */
    public Iterator<K> keySetIterator() {
        Map delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.keySet().iterator();
        }
        return new CompactHashMap<K, V>.Itr<K>() {
            /* access modifiers changed from: package-private */
            @ParametricNullness
            public K getOutput(int i) {
                return CompactHashMap.this.key(i);
            }
        };
    }

    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.entrySetView;
        if (set != null) {
            return set;
        }
        Set<Map.Entry<K, V>> createEntrySet = createEntrySet();
        this.entrySetView = createEntrySet;
        return createEntrySet;
    }

    /* access modifiers changed from: package-private */
    public Set<Map.Entry<K, V>> createEntrySet() {
        return new EntrySetView();
    }

    class EntrySetView extends AbstractSet<Map.Entry<K, V>> {
        EntrySetView() {
        }

        public int size() {
            return CompactHashMap.this.size();
        }

        public void clear() {
            CompactHashMap.this.clear();
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            return CompactHashMap.this.entrySetIterator();
        }

        public boolean contains(@CheckForNull Object obj) {
            Map delegateOrNull = CompactHashMap.this.delegateOrNull();
            if (delegateOrNull != null) {
                return delegateOrNull.entrySet().contains(obj);
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            int access$500 = CompactHashMap.this.indexOf(entry.getKey());
            if (access$500 == -1 || !Objects.equal(CompactHashMap.this.value(access$500), entry.getValue())) {
                return false;
            }
            return true;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0021, code lost:
            r0 = com.google.common.collect.CompactHashMap.access$700(r9.this$0);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean remove(@javax.annotation.CheckForNull java.lang.Object r10) {
            /*
                r9 = this;
                com.google.common.collect.CompactHashMap r0 = com.google.common.collect.CompactHashMap.this
                java.util.Map r0 = r0.delegateOrNull()
                if (r0 == 0) goto L_0x0011
                java.util.Set r0 = r0.entrySet()
                boolean r10 = r0.remove(r10)
                return r10
            L_0x0011:
                boolean r0 = r10 instanceof java.util.Map.Entry
                r1 = 0
                if (r0 == 0) goto L_0x0061
                java.util.Map$Entry r10 = (java.util.Map.Entry) r10
                com.google.common.collect.CompactHashMap r0 = com.google.common.collect.CompactHashMap.this
                boolean r0 = r0.needsAllocArrays()
                if (r0 == 0) goto L_0x0021
                return r1
            L_0x0021:
                com.google.common.collect.CompactHashMap r0 = com.google.common.collect.CompactHashMap.this
                int r0 = r0.hashTableMask()
                java.lang.Object r2 = r10.getKey()
                java.lang.Object r3 = r10.getValue()
                com.google.common.collect.CompactHashMap r10 = com.google.common.collect.CompactHashMap.this
                java.lang.Object r5 = r10.requireTable()
                com.google.common.collect.CompactHashMap r10 = com.google.common.collect.CompactHashMap.this
                int[] r6 = r10.requireEntries()
                com.google.common.collect.CompactHashMap r10 = com.google.common.collect.CompactHashMap.this
                java.lang.Object[] r7 = r10.requireKeys()
                com.google.common.collect.CompactHashMap r10 = com.google.common.collect.CompactHashMap.this
                java.lang.Object[] r8 = r10.requireValues()
                r4 = r0
                int r10 = com.google.common.collect.CompactHashing.remove(r2, r3, r4, r5, r6, r7, r8)
                r2 = -1
                if (r10 != r2) goto L_0x0050
                return r1
            L_0x0050:
                com.google.common.collect.CompactHashMap r1 = com.google.common.collect.CompactHashMap.this
                r1.moveLastEntry(r10, r0)
                com.google.common.collect.CompactHashMap r10 = com.google.common.collect.CompactHashMap.this
                com.google.common.collect.CompactHashMap.access$1210(r10)
                com.google.common.collect.CompactHashMap r10 = com.google.common.collect.CompactHashMap.this
                r10.incrementModCount()
                r10 = 1
                return r10
            L_0x0061:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.CompactHashMap.EntrySetView.remove(java.lang.Object):boolean");
        }
    }

    /* access modifiers changed from: package-private */
    public Iterator<Map.Entry<K, V>> entrySetIterator() {
        Map delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.entrySet().iterator();
        }
        return new CompactHashMap<K, V>.Itr<Map.Entry<K, V>>() {
            /* access modifiers changed from: package-private */
            public Map.Entry<K, V> getOutput(int i) {
                return new MapEntry(i);
            }
        };
    }

    final class MapEntry extends AbstractMapEntry<K, V> {
        @ParametricNullness
        private final K key;
        private int lastKnownIndex;

        MapEntry(int i) {
            this.key = CompactHashMap.this.key(i);
            this.lastKnownIndex = i;
        }

        @ParametricNullness
        public K getKey() {
            return this.key;
        }

        private void updateLastKnownIndex() {
            int i = this.lastKnownIndex;
            if (i == -1 || i >= CompactHashMap.this.size() || !Objects.equal(this.key, CompactHashMap.this.key(this.lastKnownIndex))) {
                this.lastKnownIndex = CompactHashMap.this.indexOf(this.key);
            }
        }

        @ParametricNullness
        public V getValue() {
            Map delegateOrNull = CompactHashMap.this.delegateOrNull();
            if (delegateOrNull != null) {
                return NullnessCasts.uncheckedCastNullableTToT(delegateOrNull.get(this.key));
            }
            updateLastKnownIndex();
            int i = this.lastKnownIndex;
            return i == -1 ? NullnessCasts.unsafeNull() : CompactHashMap.this.value(i);
        }

        @ParametricNullness
        public V setValue(@ParametricNullness V v) {
            Map delegateOrNull = CompactHashMap.this.delegateOrNull();
            if (delegateOrNull != null) {
                return NullnessCasts.uncheckedCastNullableTToT(delegateOrNull.put(this.key, v));
            }
            updateLastKnownIndex();
            int i = this.lastKnownIndex;
            if (i == -1) {
                CompactHashMap.this.put(this.key, v);
                return NullnessCasts.unsafeNull();
            }
            V access$600 = CompactHashMap.this.value(i);
            CompactHashMap.this.setValue(this.lastKnownIndex, v);
            return access$600;
        }
    }

    public int size() {
        Map delegateOrNull = delegateOrNull();
        return delegateOrNull != null ? delegateOrNull.size() : this.size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean containsValue(@CheckForNull Object obj) {
        Map delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.containsValue(obj);
        }
        for (int i = 0; i < this.size; i++) {
            if (Objects.equal(obj, value(i))) {
                return true;
            }
        }
        return false;
    }

    public Collection<V> values() {
        Collection<V> collection = this.valuesView;
        if (collection != null) {
            return collection;
        }
        Collection<V> createValues = createValues();
        this.valuesView = createValues;
        return createValues;
    }

    /* access modifiers changed from: package-private */
    public Collection<V> createValues() {
        return new ValuesView();
    }

    class ValuesView extends AbstractCollection<V> {
        ValuesView() {
        }

        public int size() {
            return CompactHashMap.this.size();
        }

        public void clear() {
            CompactHashMap.this.clear();
        }

        public Iterator<V> iterator() {
            return CompactHashMap.this.valuesIterator();
        }
    }

    /* access modifiers changed from: package-private */
    public Iterator<V> valuesIterator() {
        Map delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.values().iterator();
        }
        return new CompactHashMap<K, V>.Itr<V>() {
            /* access modifiers changed from: package-private */
            @ParametricNullness
            public V getOutput(int i) {
                return CompactHashMap.this.value(i);
            }
        };
    }

    public void trimToSize() {
        if (!needsAllocArrays()) {
            Map delegateOrNull = delegateOrNull();
            if (delegateOrNull != null) {
                Map createHashFloodingResistantDelegate = createHashFloodingResistantDelegate(size());
                createHashFloodingResistantDelegate.putAll(delegateOrNull);
                this.table = createHashFloodingResistantDelegate;
                return;
            }
            int i = this.size;
            if (i < requireEntries().length) {
                resizeEntries(i);
            }
            int tableSize = CompactHashing.tableSize(i);
            int hashTableMask = hashTableMask();
            if (tableSize < hashTableMask) {
                resizeTable(hashTableMask, tableSize, 0, 0);
            }
        }
    }

    public void clear() {
        if (!needsAllocArrays()) {
            incrementModCount();
            Map delegateOrNull = delegateOrNull();
            if (delegateOrNull != null) {
                this.metadata = Ints.constrainToRange(size(), 3, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
                delegateOrNull.clear();
                this.table = null;
                this.size = 0;
                return;
            }
            Arrays.fill(requireKeys(), 0, this.size, (Object) null);
            Arrays.fill(requireValues(), 0, this.size, (Object) null);
            CompactHashing.tableClear(requireTable());
            Arrays.fill(requireEntries(), 0, this.size, 0);
            this.size = 0;
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(size());
        Iterator entrySetIterator = entrySetIterator();
        while (entrySetIterator.hasNext()) {
            Map.Entry entry = (Map.Entry) entrySetIterator.next();
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeObject(entry.getValue());
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        if (readInt >= 0) {
            init(readInt);
            for (int i = 0; i < readInt; i++) {
                put(objectInputStream.readObject(), objectInputStream.readObject());
            }
            return;
        }
        throw new InvalidObjectException("Invalid size: " + readInt);
    }

    /* access modifiers changed from: private */
    public Object requireTable() {
        return java.util.Objects.requireNonNull(this.table);
    }

    /* access modifiers changed from: private */
    public int[] requireEntries() {
        return (int[]) java.util.Objects.requireNonNull(this.entries);
    }

    /* access modifiers changed from: private */
    public Object[] requireKeys() {
        return (Object[]) java.util.Objects.requireNonNull(this.keys);
    }

    /* access modifiers changed from: private */
    public Object[] requireValues() {
        return (Object[]) java.util.Objects.requireNonNull(this.values);
    }

    /* access modifiers changed from: private */
    public K key(int i) {
        return requireKeys()[i];
    }

    /* access modifiers changed from: private */
    public V value(int i) {
        return requireValues()[i];
    }

    private int entry(int i) {
        return requireEntries()[i];
    }

    private void setKey(int i, K k) {
        requireKeys()[i] = k;
    }

    /* access modifiers changed from: private */
    public void setValue(int i, V v) {
        requireValues()[i] = v;
    }

    private void setEntry(int i, int i2) {
        requireEntries()[i] = i2;
    }
}
