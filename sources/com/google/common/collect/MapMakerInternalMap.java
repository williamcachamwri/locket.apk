package com.google.common.collect;

import com.google.common.base.Equivalence;
import com.google.common.base.Preconditions;
import com.google.common.collect.MapMaker;
import com.google.common.collect.MapMakerInternalMap.InternalEntry;
import com.google.common.collect.MapMakerInternalMap.Segment;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.CheckForNull;

class MapMakerInternalMap<K, V, E extends InternalEntry<K, V, E>, S extends Segment<K, V, E, S>> extends AbstractMap<K, V> implements ConcurrentMap<K, V>, Serializable {
    static final int CONTAINS_VALUE_RETRIES = 3;
    static final int DRAIN_MAX = 16;
    static final int DRAIN_THRESHOLD = 63;
    static final int MAXIMUM_CAPACITY = 1073741824;
    static final int MAX_SEGMENTS = 65536;
    static final WeakValueReference<Object, Object, DummyInternalEntry> UNSET_WEAK_VALUE_REFERENCE = new WeakValueReference<Object, Object, DummyInternalEntry>() {
        public void clear() {
        }

        public WeakValueReference<Object, Object, DummyInternalEntry> copyFor(ReferenceQueue<Object> referenceQueue, DummyInternalEntry dummyInternalEntry) {
            return this;
        }

        @CheckForNull
        public Object get() {
            return null;
        }

        @CheckForNull
        public DummyInternalEntry getEntry() {
            return null;
        }
    };
    private static final long serialVersionUID = 5;
    final int concurrencyLevel;
    final transient InternalEntryHelper<K, V, E, S> entryHelper;
    @CheckForNull
    @LazyInit
    transient Set<Map.Entry<K, V>> entrySet;
    final Equivalence<Object> keyEquivalence;
    @CheckForNull
    @LazyInit
    transient Set<K> keySet;
    final transient int segmentMask;
    final transient int segmentShift;
    final transient Segment<K, V, E, S>[] segments;
    @CheckForNull
    @LazyInit
    transient Collection<V> values;

    interface InternalEntry<K, V, E extends InternalEntry<K, V, E>> {
        int getHash();

        K getKey();

        E getNext();

        V getValue();
    }

    interface InternalEntryHelper<K, V, E extends InternalEntry<K, V, E>, S extends Segment<K, V, E, S>> {
        E copy(S s, E e, @CheckForNull E e2);

        Strength keyStrength();

        E newEntry(S s, K k, int i, @CheckForNull E e);

        S newSegment(MapMakerInternalMap<K, V, E, S> mapMakerInternalMap, int i);

        void setValue(S s, E e, V v);

        Strength valueStrength();
    }

    enum Strength {
        STRONG {
            /* access modifiers changed from: package-private */
            public Equivalence<Object> defaultEquivalence() {
                return Equivalence.equals();
            }
        },
        WEAK {
            /* access modifiers changed from: package-private */
            public Equivalence<Object> defaultEquivalence() {
                return Equivalence.identity();
            }
        };

        /* access modifiers changed from: package-private */
        public abstract Equivalence<Object> defaultEquivalence();
    }

    interface StrongValueEntry<K, V, E extends InternalEntry<K, V, E>> extends InternalEntry<K, V, E> {
    }

    interface WeakValueEntry<K, V, E extends InternalEntry<K, V, E>> extends InternalEntry<K, V, E> {
        WeakValueReference<K, V, E> getValueReference();
    }

    interface WeakValueReference<K, V, E extends InternalEntry<K, V, E>> {
        void clear();

        WeakValueReference<K, V, E> copyFor(ReferenceQueue<V> referenceQueue, E e);

        @CheckForNull
        V get();

        E getEntry();
    }

    static int rehash(int i) {
        int i2 = i + ((i << 15) ^ -12931);
        int i3 = i2 ^ (i2 >>> 10);
        int i4 = i3 + (i3 << 3);
        int i5 = i4 ^ (i4 >>> 6);
        int i6 = i5 + (i5 << 2) + (i5 << 14);
        return i6 ^ (i6 >>> 16);
    }

    private MapMakerInternalMap(MapMaker mapMaker, InternalEntryHelper<K, V, E, S> internalEntryHelper) {
        this.concurrencyLevel = Math.min(mapMaker.getConcurrencyLevel(), 65536);
        this.keyEquivalence = mapMaker.getKeyEquivalence();
        this.entryHelper = internalEntryHelper;
        int min = Math.min(mapMaker.getInitialCapacity(), 1073741824);
        int i = 0;
        int i2 = 1;
        int i3 = 0;
        int i4 = 1;
        while (i4 < this.concurrencyLevel) {
            i3++;
            i4 <<= 1;
        }
        this.segmentShift = 32 - i3;
        this.segmentMask = i4 - 1;
        this.segments = newSegmentArray(i4);
        int i5 = min / i4;
        while (i2 < (i4 * i5 < min ? i5 + 1 : i5)) {
            i2 <<= 1;
        }
        while (true) {
            Segment<K, V, E, S>[] segmentArr = this.segments;
            if (i < segmentArr.length) {
                segmentArr[i] = createSegment(i2);
                i++;
            } else {
                return;
            }
        }
    }

    static <K, V> MapMakerInternalMap<K, V, ? extends InternalEntry<K, V, ?>, ?> create(MapMaker mapMaker) {
        if (mapMaker.getKeyStrength() == Strength.STRONG && mapMaker.getValueStrength() == Strength.STRONG) {
            return new MapMakerInternalMap<>(mapMaker, StrongKeyStrongValueEntry.Helper.instance());
        }
        if (mapMaker.getKeyStrength() == Strength.STRONG && mapMaker.getValueStrength() == Strength.WEAK) {
            return new MapMakerInternalMap<>(mapMaker, StrongKeyWeakValueEntry.Helper.instance());
        }
        if (mapMaker.getKeyStrength() == Strength.WEAK && mapMaker.getValueStrength() == Strength.STRONG) {
            return new MapMakerInternalMap<>(mapMaker, WeakKeyStrongValueEntry.Helper.instance());
        }
        if (mapMaker.getKeyStrength() == Strength.WEAK && mapMaker.getValueStrength() == Strength.WEAK) {
            return new MapMakerInternalMap<>(mapMaker, WeakKeyWeakValueEntry.Helper.instance());
        }
        throw new AssertionError();
    }

    static <K> MapMakerInternalMap<K, MapMaker.Dummy, ? extends InternalEntry<K, MapMaker.Dummy, ?>, ?> createWithDummyValues(MapMaker mapMaker) {
        if (mapMaker.getKeyStrength() == Strength.STRONG && mapMaker.getValueStrength() == Strength.STRONG) {
            return new MapMakerInternalMap<>(mapMaker, StrongKeyDummyValueEntry.Helper.instance());
        }
        if (mapMaker.getKeyStrength() == Strength.WEAK && mapMaker.getValueStrength() == Strength.STRONG) {
            return new MapMakerInternalMap<>(mapMaker, WeakKeyDummyValueEntry.Helper.instance());
        }
        if (mapMaker.getValueStrength() == Strength.WEAK) {
            throw new IllegalArgumentException("Map cannot have both weak and dummy values");
        }
        throw new AssertionError();
    }

    static abstract class AbstractStrongKeyEntry<K, V, E extends InternalEntry<K, V, E>> implements InternalEntry<K, V, E> {
        final int hash;
        final K key;

        @CheckForNull
        public E getNext() {
            return null;
        }

        AbstractStrongKeyEntry(K k, int i) {
            this.key = k;
            this.hash = i;
        }

        public final K getKey() {
            return this.key;
        }

        public final int getHash() {
            return this.hash;
        }
    }

    static <K, V, E extends InternalEntry<K, V, E>> WeakValueReference<K, V, E> unsetWeakValueReference() {
        return UNSET_WEAK_VALUE_REFERENCE;
    }

    static class StrongKeyStrongValueEntry<K, V> extends AbstractStrongKeyEntry<K, V, StrongKeyStrongValueEntry<K, V>> implements StrongValueEntry<K, V, StrongKeyStrongValueEntry<K, V>> {
        /* access modifiers changed from: private */
        @CheckForNull
        public volatile V value;

        private StrongKeyStrongValueEntry(K k, int i) {
            super(k, i);
            this.value = null;
        }

        @CheckForNull
        public final V getValue() {
            return this.value;
        }

        private static final class LinkedStrongKeyStrongValueEntry<K, V> extends StrongKeyStrongValueEntry<K, V> {
            private final StrongKeyStrongValueEntry<K, V> next;

            LinkedStrongKeyStrongValueEntry(K k, int i, StrongKeyStrongValueEntry<K, V> strongKeyStrongValueEntry) {
                super(k, i);
                this.next = strongKeyStrongValueEntry;
            }

            public StrongKeyStrongValueEntry<K, V> getNext() {
                return this.next;
            }
        }

        static final class Helper<K, V> implements InternalEntryHelper<K, V, StrongKeyStrongValueEntry<K, V>, StrongKeyStrongValueSegment<K, V>> {
            private static final Helper<?, ?> INSTANCE = new Helper<>();

            Helper() {
            }

            static <K, V> Helper<K, V> instance() {
                return INSTANCE;
            }

            public Strength keyStrength() {
                return Strength.STRONG;
            }

            public Strength valueStrength() {
                return Strength.STRONG;
            }

            public StrongKeyStrongValueSegment<K, V> newSegment(MapMakerInternalMap<K, V, StrongKeyStrongValueEntry<K, V>, StrongKeyStrongValueSegment<K, V>> mapMakerInternalMap, int i) {
                return new StrongKeyStrongValueSegment<>(mapMakerInternalMap, i);
            }

            public StrongKeyStrongValueEntry<K, V> copy(StrongKeyStrongValueSegment<K, V> strongKeyStrongValueSegment, StrongKeyStrongValueEntry<K, V> strongKeyStrongValueEntry, @CheckForNull StrongKeyStrongValueEntry<K, V> strongKeyStrongValueEntry2) {
                StrongKeyStrongValueEntry<K, V> newEntry = newEntry(strongKeyStrongValueSegment, strongKeyStrongValueEntry.key, strongKeyStrongValueEntry.hash, strongKeyStrongValueEntry2);
                Object unused = newEntry.value = strongKeyStrongValueEntry.value;
                return newEntry;
            }

            public void setValue(StrongKeyStrongValueSegment<K, V> strongKeyStrongValueSegment, StrongKeyStrongValueEntry<K, V> strongKeyStrongValueEntry, V v) {
                Object unused = strongKeyStrongValueEntry.value = v;
            }

            public StrongKeyStrongValueEntry<K, V> newEntry(StrongKeyStrongValueSegment<K, V> strongKeyStrongValueSegment, K k, int i, @CheckForNull StrongKeyStrongValueEntry<K, V> strongKeyStrongValueEntry) {
                if (strongKeyStrongValueEntry == null) {
                    return new StrongKeyStrongValueEntry<>(k, i);
                }
                return new LinkedStrongKeyStrongValueEntry(k, i, strongKeyStrongValueEntry);
            }
        }
    }

    static class StrongKeyWeakValueEntry<K, V> extends AbstractStrongKeyEntry<K, V, StrongKeyWeakValueEntry<K, V>> implements WeakValueEntry<K, V, StrongKeyWeakValueEntry<K, V>> {
        /* access modifiers changed from: private */
        public volatile WeakValueReference<K, V, StrongKeyWeakValueEntry<K, V>> valueReference;

        private StrongKeyWeakValueEntry(K k, int i) {
            super(k, i);
            this.valueReference = MapMakerInternalMap.unsetWeakValueReference();
        }

        @CheckForNull
        public final V getValue() {
            return this.valueReference.get();
        }

        public final WeakValueReference<K, V, StrongKeyWeakValueEntry<K, V>> getValueReference() {
            return this.valueReference;
        }

        private static final class LinkedStrongKeyWeakValueEntry<K, V> extends StrongKeyWeakValueEntry<K, V> {
            private final StrongKeyWeakValueEntry<K, V> next;

            LinkedStrongKeyWeakValueEntry(K k, int i, StrongKeyWeakValueEntry<K, V> strongKeyWeakValueEntry) {
                super(k, i);
                this.next = strongKeyWeakValueEntry;
            }

            public StrongKeyWeakValueEntry<K, V> getNext() {
                return this.next;
            }
        }

        static final class Helper<K, V> implements InternalEntryHelper<K, V, StrongKeyWeakValueEntry<K, V>, StrongKeyWeakValueSegment<K, V>> {
            private static final Helper<?, ?> INSTANCE = new Helper<>();

            Helper() {
            }

            static <K, V> Helper<K, V> instance() {
                return INSTANCE;
            }

            public Strength keyStrength() {
                return Strength.STRONG;
            }

            public Strength valueStrength() {
                return Strength.WEAK;
            }

            public StrongKeyWeakValueSegment<K, V> newSegment(MapMakerInternalMap<K, V, StrongKeyWeakValueEntry<K, V>, StrongKeyWeakValueSegment<K, V>> mapMakerInternalMap, int i) {
                return new StrongKeyWeakValueSegment<>(mapMakerInternalMap, i);
            }

            @CheckForNull
            public StrongKeyWeakValueEntry<K, V> copy(StrongKeyWeakValueSegment<K, V> strongKeyWeakValueSegment, StrongKeyWeakValueEntry<K, V> strongKeyWeakValueEntry, @CheckForNull StrongKeyWeakValueEntry<K, V> strongKeyWeakValueEntry2) {
                if (Segment.isCollected(strongKeyWeakValueEntry)) {
                    return null;
                }
                StrongKeyWeakValueEntry<K, V> newEntry = newEntry(strongKeyWeakValueSegment, strongKeyWeakValueEntry.key, strongKeyWeakValueEntry.hash, strongKeyWeakValueEntry2);
                WeakValueReference unused = newEntry.valueReference = strongKeyWeakValueEntry.valueReference.copyFor(strongKeyWeakValueSegment.queueForValues, newEntry);
                return newEntry;
            }

            public void setValue(StrongKeyWeakValueSegment<K, V> strongKeyWeakValueSegment, StrongKeyWeakValueEntry<K, V> strongKeyWeakValueEntry, V v) {
                WeakValueReference access$400 = strongKeyWeakValueEntry.valueReference;
                WeakValueReference unused = strongKeyWeakValueEntry.valueReference = new WeakValueReferenceImpl(strongKeyWeakValueSegment.queueForValues, v, strongKeyWeakValueEntry);
                access$400.clear();
            }

            public StrongKeyWeakValueEntry<K, V> newEntry(StrongKeyWeakValueSegment<K, V> strongKeyWeakValueSegment, K k, int i, @CheckForNull StrongKeyWeakValueEntry<K, V> strongKeyWeakValueEntry) {
                if (strongKeyWeakValueEntry == null) {
                    return new StrongKeyWeakValueEntry<>(k, i);
                }
                return new LinkedStrongKeyWeakValueEntry(k, i, strongKeyWeakValueEntry);
            }
        }
    }

    static class StrongKeyDummyValueEntry<K> extends AbstractStrongKeyEntry<K, MapMaker.Dummy, StrongKeyDummyValueEntry<K>> implements StrongValueEntry<K, MapMaker.Dummy, StrongKeyDummyValueEntry<K>> {
        private StrongKeyDummyValueEntry(K k, int i) {
            super(k, i);
        }

        public final MapMaker.Dummy getValue() {
            return MapMaker.Dummy.VALUE;
        }

        private static final class LinkedStrongKeyDummyValueEntry<K> extends StrongKeyDummyValueEntry<K> {
            private final StrongKeyDummyValueEntry<K> next;

            public /* bridge */ /* synthetic */ Object getValue() {
                return super.getValue();
            }

            LinkedStrongKeyDummyValueEntry(K k, int i, StrongKeyDummyValueEntry<K> strongKeyDummyValueEntry) {
                super(k, i);
                this.next = strongKeyDummyValueEntry;
            }

            public StrongKeyDummyValueEntry<K> getNext() {
                return this.next;
            }
        }

        static final class Helper<K> implements InternalEntryHelper<K, MapMaker.Dummy, StrongKeyDummyValueEntry<K>, StrongKeyDummyValueSegment<K>> {
            private static final Helper<?> INSTANCE = new Helper<>();

            public void setValue(StrongKeyDummyValueSegment<K> strongKeyDummyValueSegment, StrongKeyDummyValueEntry<K> strongKeyDummyValueEntry, MapMaker.Dummy dummy) {
            }

            Helper() {
            }

            static <K> Helper<K> instance() {
                return INSTANCE;
            }

            public Strength keyStrength() {
                return Strength.STRONG;
            }

            public Strength valueStrength() {
                return Strength.STRONG;
            }

            public StrongKeyDummyValueSegment<K> newSegment(MapMakerInternalMap<K, MapMaker.Dummy, StrongKeyDummyValueEntry<K>, StrongKeyDummyValueSegment<K>> mapMakerInternalMap, int i) {
                return new StrongKeyDummyValueSegment<>(mapMakerInternalMap, i);
            }

            public StrongKeyDummyValueEntry<K> copy(StrongKeyDummyValueSegment<K> strongKeyDummyValueSegment, StrongKeyDummyValueEntry<K> strongKeyDummyValueEntry, @CheckForNull StrongKeyDummyValueEntry<K> strongKeyDummyValueEntry2) {
                return newEntry(strongKeyDummyValueSegment, strongKeyDummyValueEntry.key, strongKeyDummyValueEntry.hash, strongKeyDummyValueEntry2);
            }

            public StrongKeyDummyValueEntry<K> newEntry(StrongKeyDummyValueSegment<K> strongKeyDummyValueSegment, K k, int i, @CheckForNull StrongKeyDummyValueEntry<K> strongKeyDummyValueEntry) {
                if (strongKeyDummyValueEntry == null) {
                    return new StrongKeyDummyValueEntry<>(k, i);
                }
                return new LinkedStrongKeyDummyValueEntry(k, i, strongKeyDummyValueEntry);
            }
        }
    }

    static abstract class AbstractWeakKeyEntry<K, V, E extends InternalEntry<K, V, E>> extends WeakReference<K> implements InternalEntry<K, V, E> {
        final int hash;

        @CheckForNull
        public E getNext() {
            return null;
        }

        AbstractWeakKeyEntry(ReferenceQueue<K> referenceQueue, K k, int i) {
            super(k, referenceQueue);
            this.hash = i;
        }

        public final K getKey() {
            return get();
        }

        public final int getHash() {
            return this.hash;
        }
    }

    static class WeakKeyDummyValueEntry<K> extends AbstractWeakKeyEntry<K, MapMaker.Dummy, WeakKeyDummyValueEntry<K>> implements StrongValueEntry<K, MapMaker.Dummy, WeakKeyDummyValueEntry<K>> {
        private WeakKeyDummyValueEntry(ReferenceQueue<K> referenceQueue, K k, int i) {
            super(referenceQueue, k, i);
        }

        public final MapMaker.Dummy getValue() {
            return MapMaker.Dummy.VALUE;
        }

        private static final class LinkedWeakKeyDummyValueEntry<K> extends WeakKeyDummyValueEntry<K> {
            private final WeakKeyDummyValueEntry<K> next;

            public /* bridge */ /* synthetic */ Object getValue() {
                return super.getValue();
            }

            private LinkedWeakKeyDummyValueEntry(ReferenceQueue<K> referenceQueue, K k, int i, WeakKeyDummyValueEntry<K> weakKeyDummyValueEntry) {
                super(referenceQueue, k, i);
                this.next = weakKeyDummyValueEntry;
            }

            public WeakKeyDummyValueEntry<K> getNext() {
                return this.next;
            }
        }

        static final class Helper<K> implements InternalEntryHelper<K, MapMaker.Dummy, WeakKeyDummyValueEntry<K>, WeakKeyDummyValueSegment<K>> {
            private static final Helper<?> INSTANCE = new Helper<>();

            public void setValue(WeakKeyDummyValueSegment<K> weakKeyDummyValueSegment, WeakKeyDummyValueEntry<K> weakKeyDummyValueEntry, MapMaker.Dummy dummy) {
            }

            Helper() {
            }

            static <K> Helper<K> instance() {
                return INSTANCE;
            }

            public Strength keyStrength() {
                return Strength.WEAK;
            }

            public Strength valueStrength() {
                return Strength.STRONG;
            }

            public WeakKeyDummyValueSegment<K> newSegment(MapMakerInternalMap<K, MapMaker.Dummy, WeakKeyDummyValueEntry<K>, WeakKeyDummyValueSegment<K>> mapMakerInternalMap, int i) {
                return new WeakKeyDummyValueSegment<>(mapMakerInternalMap, i);
            }

            @CheckForNull
            public WeakKeyDummyValueEntry<K> copy(WeakKeyDummyValueSegment<K> weakKeyDummyValueSegment, WeakKeyDummyValueEntry<K> weakKeyDummyValueEntry, @CheckForNull WeakKeyDummyValueEntry<K> weakKeyDummyValueEntry2) {
                K key = weakKeyDummyValueEntry.getKey();
                if (key == null) {
                    return null;
                }
                return newEntry(weakKeyDummyValueSegment, key, weakKeyDummyValueEntry.hash, weakKeyDummyValueEntry2);
            }

            public WeakKeyDummyValueEntry<K> newEntry(WeakKeyDummyValueSegment<K> weakKeyDummyValueSegment, K k, int i, @CheckForNull WeakKeyDummyValueEntry<K> weakKeyDummyValueEntry) {
                if (weakKeyDummyValueEntry == null) {
                    return new WeakKeyDummyValueEntry<>(weakKeyDummyValueSegment.queueForKeys, k, i);
                }
                return new LinkedWeakKeyDummyValueEntry(weakKeyDummyValueSegment.queueForKeys, k, i, weakKeyDummyValueEntry);
            }
        }
    }

    static class WeakKeyStrongValueEntry<K, V> extends AbstractWeakKeyEntry<K, V, WeakKeyStrongValueEntry<K, V>> implements StrongValueEntry<K, V, WeakKeyStrongValueEntry<K, V>> {
        /* access modifiers changed from: private */
        @CheckForNull
        public volatile V value;

        private WeakKeyStrongValueEntry(ReferenceQueue<K> referenceQueue, K k, int i) {
            super(referenceQueue, k, i);
            this.value = null;
        }

        @CheckForNull
        public final V getValue() {
            return this.value;
        }

        private static final class LinkedWeakKeyStrongValueEntry<K, V> extends WeakKeyStrongValueEntry<K, V> {
            private final WeakKeyStrongValueEntry<K, V> next;

            private LinkedWeakKeyStrongValueEntry(ReferenceQueue<K> referenceQueue, K k, int i, WeakKeyStrongValueEntry<K, V> weakKeyStrongValueEntry) {
                super(referenceQueue, k, i);
                this.next = weakKeyStrongValueEntry;
            }

            public WeakKeyStrongValueEntry<K, V> getNext() {
                return this.next;
            }
        }

        static final class Helper<K, V> implements InternalEntryHelper<K, V, WeakKeyStrongValueEntry<K, V>, WeakKeyStrongValueSegment<K, V>> {
            private static final Helper<?, ?> INSTANCE = new Helper<>();

            Helper() {
            }

            static <K, V> Helper<K, V> instance() {
                return INSTANCE;
            }

            public Strength keyStrength() {
                return Strength.WEAK;
            }

            public Strength valueStrength() {
                return Strength.STRONG;
            }

            public WeakKeyStrongValueSegment<K, V> newSegment(MapMakerInternalMap<K, V, WeakKeyStrongValueEntry<K, V>, WeakKeyStrongValueSegment<K, V>> mapMakerInternalMap, int i) {
                return new WeakKeyStrongValueSegment<>(mapMakerInternalMap, i);
            }

            @CheckForNull
            public WeakKeyStrongValueEntry<K, V> copy(WeakKeyStrongValueSegment<K, V> weakKeyStrongValueSegment, WeakKeyStrongValueEntry<K, V> weakKeyStrongValueEntry, @CheckForNull WeakKeyStrongValueEntry<K, V> weakKeyStrongValueEntry2) {
                K key = weakKeyStrongValueEntry.getKey();
                if (key == null) {
                    return null;
                }
                WeakKeyStrongValueEntry<K, V> newEntry = newEntry(weakKeyStrongValueSegment, key, weakKeyStrongValueEntry.hash, weakKeyStrongValueEntry2);
                Object unused = newEntry.value = weakKeyStrongValueEntry.value;
                return newEntry;
            }

            public void setValue(WeakKeyStrongValueSegment<K, V> weakKeyStrongValueSegment, WeakKeyStrongValueEntry<K, V> weakKeyStrongValueEntry, V v) {
                Object unused = weakKeyStrongValueEntry.value = v;
            }

            public WeakKeyStrongValueEntry<K, V> newEntry(WeakKeyStrongValueSegment<K, V> weakKeyStrongValueSegment, K k, int i, @CheckForNull WeakKeyStrongValueEntry<K, V> weakKeyStrongValueEntry) {
                if (weakKeyStrongValueEntry == null) {
                    return new WeakKeyStrongValueEntry<>(weakKeyStrongValueSegment.queueForKeys, k, i);
                }
                return new LinkedWeakKeyStrongValueEntry(weakKeyStrongValueSegment.queueForKeys, k, i, weakKeyStrongValueEntry);
            }
        }
    }

    static class WeakKeyWeakValueEntry<K, V> extends AbstractWeakKeyEntry<K, V, WeakKeyWeakValueEntry<K, V>> implements WeakValueEntry<K, V, WeakKeyWeakValueEntry<K, V>> {
        /* access modifiers changed from: private */
        public volatile WeakValueReference<K, V, WeakKeyWeakValueEntry<K, V>> valueReference = MapMakerInternalMap.unsetWeakValueReference();

        WeakKeyWeakValueEntry(ReferenceQueue<K> referenceQueue, K k, int i) {
            super(referenceQueue, k, i);
        }

        public final V getValue() {
            return this.valueReference.get();
        }

        public final WeakValueReference<K, V, WeakKeyWeakValueEntry<K, V>> getValueReference() {
            return this.valueReference;
        }

        private static final class LinkedWeakKeyWeakValueEntry<K, V> extends WeakKeyWeakValueEntry<K, V> {
            private final WeakKeyWeakValueEntry<K, V> next;

            LinkedWeakKeyWeakValueEntry(ReferenceQueue<K> referenceQueue, K k, int i, WeakKeyWeakValueEntry<K, V> weakKeyWeakValueEntry) {
                super(referenceQueue, k, i);
                this.next = weakKeyWeakValueEntry;
            }

            public WeakKeyWeakValueEntry<K, V> getNext() {
                return this.next;
            }
        }

        static final class Helper<K, V> implements InternalEntryHelper<K, V, WeakKeyWeakValueEntry<K, V>, WeakKeyWeakValueSegment<K, V>> {
            private static final Helper<?, ?> INSTANCE = new Helper<>();

            Helper() {
            }

            static <K, V> Helper<K, V> instance() {
                return INSTANCE;
            }

            public Strength keyStrength() {
                return Strength.WEAK;
            }

            public Strength valueStrength() {
                return Strength.WEAK;
            }

            public WeakKeyWeakValueSegment<K, V> newSegment(MapMakerInternalMap<K, V, WeakKeyWeakValueEntry<K, V>, WeakKeyWeakValueSegment<K, V>> mapMakerInternalMap, int i) {
                return new WeakKeyWeakValueSegment<>(mapMakerInternalMap, i);
            }

            @CheckForNull
            public WeakKeyWeakValueEntry<K, V> copy(WeakKeyWeakValueSegment<K, V> weakKeyWeakValueSegment, WeakKeyWeakValueEntry<K, V> weakKeyWeakValueEntry, @CheckForNull WeakKeyWeakValueEntry<K, V> weakKeyWeakValueEntry2) {
                K key = weakKeyWeakValueEntry.getKey();
                if (key == null || Segment.isCollected(weakKeyWeakValueEntry)) {
                    return null;
                }
                WeakKeyWeakValueEntry<K, V> newEntry = newEntry(weakKeyWeakValueSegment, key, weakKeyWeakValueEntry.hash, weakKeyWeakValueEntry2);
                WeakValueReference unused = newEntry.valueReference = weakKeyWeakValueEntry.valueReference.copyFor(weakKeyWeakValueSegment.queueForValues, newEntry);
                return newEntry;
            }

            public void setValue(WeakKeyWeakValueSegment<K, V> weakKeyWeakValueSegment, WeakKeyWeakValueEntry<K, V> weakKeyWeakValueEntry, V v) {
                WeakValueReference access$1400 = weakKeyWeakValueEntry.valueReference;
                WeakValueReference unused = weakKeyWeakValueEntry.valueReference = new WeakValueReferenceImpl(weakKeyWeakValueSegment.queueForValues, v, weakKeyWeakValueEntry);
                access$1400.clear();
            }

            public WeakKeyWeakValueEntry<K, V> newEntry(WeakKeyWeakValueSegment<K, V> weakKeyWeakValueSegment, K k, int i, @CheckForNull WeakKeyWeakValueEntry<K, V> weakKeyWeakValueEntry) {
                if (weakKeyWeakValueEntry == null) {
                    return new WeakKeyWeakValueEntry<>(weakKeyWeakValueSegment.queueForKeys, k, i);
                }
                return new LinkedWeakKeyWeakValueEntry(weakKeyWeakValueSegment.queueForKeys, k, i, weakKeyWeakValueEntry);
            }
        }
    }

    static final class DummyInternalEntry implements InternalEntry<Object, Object, DummyInternalEntry> {
        private DummyInternalEntry() {
            throw new AssertionError();
        }

        public DummyInternalEntry getNext() {
            throw new AssertionError();
        }

        public int getHash() {
            throw new AssertionError();
        }

        public Object getKey() {
            throw new AssertionError();
        }

        public Object getValue() {
            throw new AssertionError();
        }
    }

    static final class WeakValueReferenceImpl<K, V, E extends InternalEntry<K, V, E>> extends WeakReference<V> implements WeakValueReference<K, V, E> {
        final E entry;

        WeakValueReferenceImpl(ReferenceQueue<V> referenceQueue, V v, E e) {
            super(v, referenceQueue);
            this.entry = e;
        }

        public E getEntry() {
            return this.entry;
        }

        public WeakValueReference<K, V, E> copyFor(ReferenceQueue<V> referenceQueue, E e) {
            return new WeakValueReferenceImpl(referenceQueue, get(), e);
        }
    }

    /* access modifiers changed from: package-private */
    public E copyEntry(E e, E e2) {
        return segmentFor(e.getHash()).copyEntry(e, e2);
    }

    /* access modifiers changed from: package-private */
    public int hash(Object obj) {
        return rehash(this.keyEquivalence.hash(obj));
    }

    /* access modifiers changed from: package-private */
    public void reclaimValue(WeakValueReference<K, V, E> weakValueReference) {
        E entry = weakValueReference.getEntry();
        int hash = entry.getHash();
        segmentFor(hash).reclaimValue(entry.getKey(), hash, weakValueReference);
    }

    /* access modifiers changed from: package-private */
    public void reclaimKey(E e) {
        int hash = e.getHash();
        segmentFor(hash).reclaimKey(e, hash);
    }

    /* access modifiers changed from: package-private */
    public boolean isLiveForTesting(InternalEntry<K, V, ?> internalEntry) {
        return segmentFor(internalEntry.getHash()).getLiveValueForTesting(internalEntry) != null;
    }

    /* access modifiers changed from: package-private */
    public Segment<K, V, E, S> segmentFor(int i) {
        return this.segments[(i >>> this.segmentShift) & this.segmentMask];
    }

    /* access modifiers changed from: package-private */
    public Segment<K, V, E, S> createSegment(int i) {
        return this.entryHelper.newSegment(this, i);
    }

    /* access modifiers changed from: package-private */
    @CheckForNull
    public V getLiveValue(E e) {
        if (e.getKey() == null) {
            return null;
        }
        return e.getValue();
    }

    /* access modifiers changed from: package-private */
    public final Segment<K, V, E, S>[] newSegmentArray(int i) {
        return new Segment[i];
    }

    static abstract class Segment<K, V, E extends InternalEntry<K, V, E>, S extends Segment<K, V, E, S>> extends ReentrantLock {
        volatile int count;
        final MapMakerInternalMap<K, V, E, S> map;
        int modCount;
        final AtomicInteger readCount = new AtomicInteger();
        @CheckForNull
        volatile AtomicReferenceArray<E> table;
        int threshold;

        /* access modifiers changed from: package-private */
        public abstract E castForTesting(InternalEntry<K, V, ?> internalEntry);

        /* access modifiers changed from: package-private */
        public void maybeClearReferenceQueues() {
        }

        /* access modifiers changed from: package-private */
        public void maybeDrainReferenceQueues() {
        }

        /* access modifiers changed from: package-private */
        public abstract S self();

        Segment(MapMakerInternalMap<K, V, E, S> mapMakerInternalMap, int i) {
            this.map = mapMakerInternalMap;
            initTable(newEntryArray(i));
        }

        /* access modifiers changed from: package-private */
        public void setValue(E e, V v) {
            this.map.entryHelper.setValue(self(), e, v);
        }

        /* access modifiers changed from: package-private */
        @CheckForNull
        public E copyEntry(E e, E e2) {
            return this.map.entryHelper.copy(self(), e, e2);
        }

        /* access modifiers changed from: package-private */
        public AtomicReferenceArray<E> newEntryArray(int i) {
            return new AtomicReferenceArray<>(i);
        }

        /* access modifiers changed from: package-private */
        public void initTable(AtomicReferenceArray<E> atomicReferenceArray) {
            this.threshold = (atomicReferenceArray.length() * 3) / 4;
            this.table = atomicReferenceArray;
        }

        /* access modifiers changed from: package-private */
        public ReferenceQueue<K> getKeyReferenceQueueForTesting() {
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        public ReferenceQueue<V> getValueReferenceQueueForTesting() {
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        public WeakValueReference<K, V, E> getWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry) {
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        public WeakValueReference<K, V, E> newWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry, V v) {
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        public void setWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry, WeakValueReference<K, V, ? extends InternalEntry<K, V, ?>> weakValueReference) {
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        public void setTableEntryForTesting(int i, InternalEntry<K, V, ?> internalEntry) {
            this.table.set(i, castForTesting(internalEntry));
        }

        /* JADX WARNING: type inference failed for: r3v0, types: [com.google.common.collect.MapMakerInternalMap$InternalEntry, com.google.common.collect.MapMakerInternalMap$InternalEntry<K, V, ?>] */
        /* JADX WARNING: type inference failed for: r4v0, types: [com.google.common.collect.MapMakerInternalMap$InternalEntry, com.google.common.collect.MapMakerInternalMap$InternalEntry<K, V, ?>] */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Unknown variable types count: 2 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public E copyForTesting(com.google.common.collect.MapMakerInternalMap.InternalEntry<K, V, ?> r3, @javax.annotation.CheckForNull com.google.common.collect.MapMakerInternalMap.InternalEntry<K, V, ?> r4) {
            /*
                r2 = this;
                com.google.common.collect.MapMakerInternalMap<K, V, E, S> r0 = r2.map
                com.google.common.collect.MapMakerInternalMap$InternalEntryHelper<K, V, E, S> r0 = r0.entryHelper
                com.google.common.collect.MapMakerInternalMap$Segment r1 = r2.self()
                com.google.common.collect.MapMakerInternalMap$InternalEntry r3 = r2.castForTesting(r3)
                com.google.common.collect.MapMakerInternalMap$InternalEntry r4 = r2.castForTesting(r4)
                com.google.common.collect.MapMakerInternalMap$InternalEntry r3 = r0.copy(r1, r3, r4)
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.Segment.copyForTesting(com.google.common.collect.MapMakerInternalMap$InternalEntry, com.google.common.collect.MapMakerInternalMap$InternalEntry):com.google.common.collect.MapMakerInternalMap$InternalEntry");
        }

        /* JADX WARNING: type inference failed for: r3v0, types: [com.google.common.collect.MapMakerInternalMap$InternalEntry, com.google.common.collect.MapMakerInternalMap$InternalEntry<K, V, ?>] */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setValueForTesting(com.google.common.collect.MapMakerInternalMap.InternalEntry<K, V, ?> r3, V r4) {
            /*
                r2 = this;
                com.google.common.collect.MapMakerInternalMap<K, V, E, S> r0 = r2.map
                com.google.common.collect.MapMakerInternalMap$InternalEntryHelper<K, V, E, S> r0 = r0.entryHelper
                com.google.common.collect.MapMakerInternalMap$Segment r1 = r2.self()
                com.google.common.collect.MapMakerInternalMap$InternalEntry r3 = r2.castForTesting(r3)
                r0.setValue(r1, r3, r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.Segment.setValueForTesting(com.google.common.collect.MapMakerInternalMap$InternalEntry, java.lang.Object):void");
        }

        /* JADX WARNING: type inference failed for: r5v0, types: [com.google.common.collect.MapMakerInternalMap$InternalEntry, com.google.common.collect.MapMakerInternalMap$InternalEntry<K, V, ?>] */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public E newEntryForTesting(K r3, int r4, @javax.annotation.CheckForNull com.google.common.collect.MapMakerInternalMap.InternalEntry<K, V, ?> r5) {
            /*
                r2 = this;
                com.google.common.collect.MapMakerInternalMap<K, V, E, S> r0 = r2.map
                com.google.common.collect.MapMakerInternalMap$InternalEntryHelper<K, V, E, S> r0 = r0.entryHelper
                com.google.common.collect.MapMakerInternalMap$Segment r1 = r2.self()
                com.google.common.collect.MapMakerInternalMap$InternalEntry r5 = r2.castForTesting(r5)
                com.google.common.collect.MapMakerInternalMap$InternalEntry r3 = r0.newEntry(r1, r3, r4, r5)
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.Segment.newEntryForTesting(java.lang.Object, int, com.google.common.collect.MapMakerInternalMap$InternalEntry):com.google.common.collect.MapMakerInternalMap$InternalEntry");
        }

        /* JADX WARNING: type inference failed for: r1v0, types: [com.google.common.collect.MapMakerInternalMap$InternalEntry, com.google.common.collect.MapMakerInternalMap$InternalEntry<K, V, ?>] */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean removeTableEntryForTesting(com.google.common.collect.MapMakerInternalMap.InternalEntry<K, V, ?> r1) {
            /*
                r0 = this;
                com.google.common.collect.MapMakerInternalMap$InternalEntry r1 = r0.castForTesting(r1)
                boolean r1 = r0.removeEntryForTesting(r1)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.Segment.removeTableEntryForTesting(com.google.common.collect.MapMakerInternalMap$InternalEntry):boolean");
        }

        /* JADX WARNING: type inference failed for: r1v0, types: [com.google.common.collect.MapMakerInternalMap$InternalEntry, com.google.common.collect.MapMakerInternalMap$InternalEntry<K, V, ?>] */
        /* JADX WARNING: type inference failed for: r2v0, types: [com.google.common.collect.MapMakerInternalMap$InternalEntry, com.google.common.collect.MapMakerInternalMap$InternalEntry<K, V, ?>] */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Unknown variable types count: 2 */
        @javax.annotation.CheckForNull
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public E removeFromChainForTesting(com.google.common.collect.MapMakerInternalMap.InternalEntry<K, V, ?> r1, com.google.common.collect.MapMakerInternalMap.InternalEntry<K, V, ?> r2) {
            /*
                r0 = this;
                com.google.common.collect.MapMakerInternalMap$InternalEntry r1 = r0.castForTesting(r1)
                com.google.common.collect.MapMakerInternalMap$InternalEntry r2 = r0.castForTesting(r2)
                com.google.common.collect.MapMakerInternalMap$InternalEntry r1 = r0.removeFromChain(r1, r2)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.Segment.removeFromChainForTesting(com.google.common.collect.MapMakerInternalMap$InternalEntry, com.google.common.collect.MapMakerInternalMap$InternalEntry):com.google.common.collect.MapMakerInternalMap$InternalEntry");
        }

        /* JADX WARNING: type inference failed for: r1v0, types: [com.google.common.collect.MapMakerInternalMap$InternalEntry, com.google.common.collect.MapMakerInternalMap$InternalEntry<K, V, ?>] */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Unknown variable types count: 1 */
        @javax.annotation.CheckForNull
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public V getLiveValueForTesting(com.google.common.collect.MapMakerInternalMap.InternalEntry<K, V, ?> r1) {
            /*
                r0 = this;
                com.google.common.collect.MapMakerInternalMap$InternalEntry r1 = r0.castForTesting(r1)
                java.lang.Object r1 = r0.getLiveValue(r1)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.Segment.getLiveValueForTesting(com.google.common.collect.MapMakerInternalMap$InternalEntry):java.lang.Object");
        }

        /* access modifiers changed from: package-private */
        public void tryDrainReferenceQueues() {
            if (tryLock()) {
                try {
                    maybeDrainReferenceQueues();
                } finally {
                    unlock();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void drainKeyReferenceQueue(ReferenceQueue<K> referenceQueue) {
            int i = 0;
            do {
                Reference<? extends K> poll = referenceQueue.poll();
                if (poll != null) {
                    this.map.reclaimKey((InternalEntry) poll);
                    i++;
                } else {
                    return;
                }
            } while (i != 16);
        }

        /* access modifiers changed from: package-private */
        public void drainValueReferenceQueue(ReferenceQueue<V> referenceQueue) {
            int i = 0;
            do {
                Reference<? extends V> poll = referenceQueue.poll();
                if (poll != null) {
                    this.map.reclaimValue((WeakValueReference) poll);
                    i++;
                } else {
                    return;
                }
            } while (i != 16);
        }

        /* access modifiers changed from: package-private */
        public <T> void clearReferenceQueue(ReferenceQueue<T> referenceQueue) {
            do {
            } while (referenceQueue.poll() != null);
        }

        /* access modifiers changed from: package-private */
        @CheckForNull
        public E getFirst(int i) {
            AtomicReferenceArray<E> atomicReferenceArray = this.table;
            return (InternalEntry) atomicReferenceArray.get(i & (atomicReferenceArray.length() - 1));
        }

        /* access modifiers changed from: package-private */
        @CheckForNull
        public E getEntry(Object obj, int i) {
            if (this.count == 0) {
                return null;
            }
            for (E first = getFirst(i); first != null; first = first.getNext()) {
                if (first.getHash() == i) {
                    Object key = first.getKey();
                    if (key == null) {
                        tryDrainReferenceQueues();
                    } else if (this.map.keyEquivalence.equivalent(obj, key)) {
                        return first;
                    }
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        @CheckForNull
        public E getLiveEntry(Object obj, int i) {
            return getEntry(obj, i);
        }

        /* JADX INFO: finally extract failed */
        /* access modifiers changed from: package-private */
        @CheckForNull
        public V get(Object obj, int i) {
            try {
                InternalEntry liveEntry = getLiveEntry(obj, i);
                if (liveEntry == null) {
                    postReadCleanup();
                    return null;
                }
                V value = liveEntry.getValue();
                if (value == null) {
                    tryDrainReferenceQueues();
                }
                postReadCleanup();
                return value;
            } catch (Throwable th) {
                postReadCleanup();
                throw th;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean containsKey(Object obj, int i) {
            try {
                boolean z = false;
                if (this.count != 0) {
                    InternalEntry liveEntry = getLiveEntry(obj, i);
                    if (!(liveEntry == null || liveEntry.getValue() == null)) {
                        z = true;
                    }
                    return z;
                }
                postReadCleanup();
                return false;
            } finally {
                postReadCleanup();
            }
        }

        /* JADX INFO: finally extract failed */
        /* access modifiers changed from: package-private */
        public boolean containsValue(Object obj) {
            try {
                if (this.count != 0) {
                    AtomicReferenceArray<E> atomicReferenceArray = this.table;
                    int length = atomicReferenceArray.length();
                    for (int i = 0; i < length; i++) {
                        for (InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(i); internalEntry != null; internalEntry = internalEntry.getNext()) {
                            Object liveValue = getLiveValue(internalEntry);
                            if (liveValue != null) {
                                if (this.map.valueEquivalence().equivalent(obj, liveValue)) {
                                    postReadCleanup();
                                    return true;
                                }
                            }
                        }
                    }
                }
                postReadCleanup();
                return false;
            } catch (Throwable th) {
                postReadCleanup();
                throw th;
            }
        }

        /* access modifiers changed from: package-private */
        @CheckForNull
        public V put(K k, int i, V v, boolean z) {
            lock();
            try {
                preWriteCleanup();
                int i2 = this.count + 1;
                if (i2 > this.threshold) {
                    expand();
                    i2 = this.count + 1;
                }
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                InternalEntry internalEntry2 = internalEntry;
                while (internalEntry2 != null) {
                    Object key = internalEntry2.getKey();
                    if (internalEntry2.getHash() != i || key == null || !this.map.keyEquivalence.equivalent(k, key)) {
                        internalEntry2 = internalEntry2.getNext();
                    } else {
                        V value = internalEntry2.getValue();
                        if (value == null) {
                            this.modCount++;
                            setValue(internalEntry2, v);
                            this.count = this.count;
                            return null;
                        } else if (z) {
                            unlock();
                            return value;
                        } else {
                            this.modCount++;
                            setValue(internalEntry2, v);
                            unlock();
                            return value;
                        }
                    }
                }
                this.modCount++;
                E newEntry = this.map.entryHelper.newEntry(self(), k, i, internalEntry);
                setValue(newEntry, v);
                atomicReferenceArray.set(length, newEntry);
                this.count = i2;
                unlock();
                return null;
            } finally {
                unlock();
            }
        }

        /* access modifiers changed from: package-private */
        public void expand() {
            AtomicReferenceArray<E> atomicReferenceArray = this.table;
            int length = atomicReferenceArray.length();
            if (length < 1073741824) {
                int i = this.count;
                AtomicReferenceArray<E> newEntryArray = newEntryArray(length << 1);
                this.threshold = (newEntryArray.length() * 3) / 4;
                int length2 = newEntryArray.length() - 1;
                for (int i2 = 0; i2 < length; i2++) {
                    InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(i2);
                    if (internalEntry != null) {
                        InternalEntry next = internalEntry.getNext();
                        int hash = internalEntry.getHash() & length2;
                        if (next == null) {
                            newEntryArray.set(hash, internalEntry);
                        } else {
                            InternalEntry internalEntry2 = internalEntry;
                            while (next != null) {
                                int hash2 = next.getHash() & length2;
                                if (hash2 != hash) {
                                    internalEntry2 = next;
                                    hash = hash2;
                                }
                                next = next.getNext();
                            }
                            newEntryArray.set(hash, internalEntry2);
                            while (internalEntry != internalEntry2) {
                                int hash3 = internalEntry.getHash() & length2;
                                InternalEntry copyEntry = copyEntry(internalEntry, (InternalEntry) newEntryArray.get(hash3));
                                if (copyEntry != null) {
                                    newEntryArray.set(hash3, copyEntry);
                                } else {
                                    i--;
                                }
                                internalEntry = internalEntry.getNext();
                            }
                        }
                    }
                }
                this.table = newEntryArray;
                this.count = i;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean replace(K k, int i, V v, V v2) {
            lock();
            try {
                preWriteCleanup();
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                InternalEntry internalEntry2 = internalEntry;
                while (internalEntry2 != null) {
                    Object key = internalEntry2.getKey();
                    if (internalEntry2.getHash() != i || key == null || !this.map.keyEquivalence.equivalent(k, key)) {
                        internalEntry2 = internalEntry2.getNext();
                    } else {
                        Object value = internalEntry2.getValue();
                        if (value == null) {
                            if (isCollected(internalEntry2)) {
                                this.modCount++;
                                atomicReferenceArray.set(length, removeFromChain(internalEntry, internalEntry2));
                                this.count--;
                            }
                            return false;
                        } else if (this.map.valueEquivalence().equivalent(v, value)) {
                            this.modCount++;
                            setValue(internalEntry2, v2);
                            unlock();
                            return true;
                        } else {
                            unlock();
                            return false;
                        }
                    }
                }
                unlock();
                return false;
            } finally {
                unlock();
            }
        }

        /* access modifiers changed from: package-private */
        @CheckForNull
        public V replace(K k, int i, V v) {
            lock();
            try {
                preWriteCleanup();
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                InternalEntry internalEntry2 = internalEntry;
                while (internalEntry2 != null) {
                    Object key = internalEntry2.getKey();
                    if (internalEntry2.getHash() != i || key == null || !this.map.keyEquivalence.equivalent(k, key)) {
                        internalEntry2 = internalEntry2.getNext();
                    } else {
                        V value = internalEntry2.getValue();
                        if (value == null) {
                            if (isCollected(internalEntry2)) {
                                this.modCount++;
                                atomicReferenceArray.set(length, removeFromChain(internalEntry, internalEntry2));
                                this.count--;
                            }
                            return null;
                        }
                        this.modCount++;
                        setValue(internalEntry2, v);
                        unlock();
                        return value;
                    }
                }
                unlock();
                return null;
            } finally {
                unlock();
            }
        }

        /* access modifiers changed from: package-private */
        @CheckForNull
        public V remove(Object obj, int i) {
            lock();
            try {
                preWriteCleanup();
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                InternalEntry internalEntry2 = internalEntry;
                while (internalEntry2 != null) {
                    Object key = internalEntry2.getKey();
                    if (internalEntry2.getHash() != i || key == null || !this.map.keyEquivalence.equivalent(obj, key)) {
                        internalEntry2 = internalEntry2.getNext();
                    } else {
                        V value = internalEntry2.getValue();
                        if (value == null) {
                            if (!isCollected(internalEntry2)) {
                                unlock();
                                return null;
                            }
                        }
                        this.modCount++;
                        atomicReferenceArray.set(length, removeFromChain(internalEntry, internalEntry2));
                        this.count--;
                        return value;
                    }
                }
                unlock();
                return null;
            } finally {
                unlock();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean remove(Object obj, int i, Object obj2) {
            lock();
            try {
                preWriteCleanup();
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                InternalEntry internalEntry2 = internalEntry;
                while (true) {
                    boolean z = false;
                    if (internalEntry2 != null) {
                        Object key = internalEntry2.getKey();
                        if (internalEntry2.getHash() != i || key == null || !this.map.keyEquivalence.equivalent(obj, key)) {
                            internalEntry2 = internalEntry2.getNext();
                        } else {
                            if (this.map.valueEquivalence().equivalent(obj2, internalEntry2.getValue())) {
                                z = true;
                            } else if (!isCollected(internalEntry2)) {
                                unlock();
                                return false;
                            }
                            this.modCount++;
                            atomicReferenceArray.set(length, removeFromChain(internalEntry, internalEntry2));
                            this.count--;
                            return z;
                        }
                    } else {
                        unlock();
                        return false;
                    }
                }
            } finally {
                unlock();
            }
        }

        /* access modifiers changed from: package-private */
        public void clear() {
            if (this.count != 0) {
                lock();
                try {
                    AtomicReferenceArray<E> atomicReferenceArray = this.table;
                    for (int i = 0; i < atomicReferenceArray.length(); i++) {
                        atomicReferenceArray.set(i, (Object) null);
                    }
                    maybeClearReferenceQueues();
                    this.readCount.set(0);
                    this.modCount++;
                    this.count = 0;
                } finally {
                    unlock();
                }
            }
        }

        /* access modifiers changed from: package-private */
        @CheckForNull
        public E removeFromChain(E e, E e2) {
            int i = this.count;
            E next = e2.getNext();
            while (e != e2) {
                E copyEntry = copyEntry(e, next);
                if (copyEntry != null) {
                    next = copyEntry;
                } else {
                    i--;
                }
                e = e.getNext();
            }
            this.count = i;
            return next;
        }

        /* access modifiers changed from: package-private */
        public boolean reclaimKey(E e, int i) {
            lock();
            try {
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = i & (atomicReferenceArray.length() - 1);
                E e2 = (InternalEntry) atomicReferenceArray.get(length);
                for (E e3 = e2; e3 != null; e3 = e3.getNext()) {
                    if (e3 == e) {
                        this.modCount++;
                        atomicReferenceArray.set(length, removeFromChain(e2, e3));
                        this.count--;
                        return true;
                    }
                }
                unlock();
                return false;
            } finally {
                unlock();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean reclaimValue(K k, int i, WeakValueReference<K, V, E> weakValueReference) {
            lock();
            try {
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                InternalEntry internalEntry2 = internalEntry;
                while (internalEntry2 != null) {
                    Object key = internalEntry2.getKey();
                    if (internalEntry2.getHash() != i || key == null || !this.map.keyEquivalence.equivalent(k, key)) {
                        internalEntry2 = internalEntry2.getNext();
                    } else if (((WeakValueEntry) internalEntry2).getValueReference() == weakValueReference) {
                        this.modCount++;
                        atomicReferenceArray.set(length, removeFromChain(internalEntry, internalEntry2));
                        this.count--;
                        return true;
                    } else {
                        unlock();
                        return false;
                    }
                }
                unlock();
                return false;
            } finally {
                unlock();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean clearValueForTesting(K k, int i, WeakValueReference<K, V, ? extends InternalEntry<K, V, ?>> weakValueReference) {
            lock();
            try {
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(length);
                InternalEntry internalEntry2 = internalEntry;
                while (internalEntry2 != null) {
                    Object key = internalEntry2.getKey();
                    if (internalEntry2.getHash() != i || key == null || !this.map.keyEquivalence.equivalent(k, key)) {
                        internalEntry2 = internalEntry2.getNext();
                    } else if (((WeakValueEntry) internalEntry2).getValueReference() == weakValueReference) {
                        atomicReferenceArray.set(length, removeFromChain(internalEntry, internalEntry2));
                        return true;
                    } else {
                        unlock();
                        return false;
                    }
                }
                unlock();
                return false;
            } finally {
                unlock();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean removeEntryForTesting(E e) {
            int hash = e.getHash();
            AtomicReferenceArray<E> atomicReferenceArray = this.table;
            int length = hash & (atomicReferenceArray.length() - 1);
            E e2 = (InternalEntry) atomicReferenceArray.get(length);
            for (E e3 = e2; e3 != null; e3 = e3.getNext()) {
                if (e3 == e) {
                    this.modCount++;
                    atomicReferenceArray.set(length, removeFromChain(e2, e3));
                    this.count--;
                    return true;
                }
            }
            return false;
        }

        static <K, V, E extends InternalEntry<K, V, E>> boolean isCollected(E e) {
            return e.getValue() == null;
        }

        /* access modifiers changed from: package-private */
        @CheckForNull
        public V getLiveValue(E e) {
            if (e.getKey() == null) {
                tryDrainReferenceQueues();
                return null;
            }
            V value = e.getValue();
            if (value != null) {
                return value;
            }
            tryDrainReferenceQueues();
            return null;
        }

        /* access modifiers changed from: package-private */
        public void postReadCleanup() {
            if ((this.readCount.incrementAndGet() & 63) == 0) {
                runCleanup();
            }
        }

        /* access modifiers changed from: package-private */
        public void preWriteCleanup() {
            runLockedCleanup();
        }

        /* access modifiers changed from: package-private */
        public void runCleanup() {
            runLockedCleanup();
        }

        /* access modifiers changed from: package-private */
        public void runLockedCleanup() {
            if (tryLock()) {
                try {
                    maybeDrainReferenceQueues();
                    this.readCount.set(0);
                } finally {
                    unlock();
                }
            }
        }
    }

    static final class StrongKeyStrongValueSegment<K, V> extends Segment<K, V, StrongKeyStrongValueEntry<K, V>, StrongKeyStrongValueSegment<K, V>> {
        /* access modifiers changed from: package-private */
        public StrongKeyStrongValueSegment<K, V> self() {
            return this;
        }

        StrongKeyStrongValueSegment(MapMakerInternalMap<K, V, StrongKeyStrongValueEntry<K, V>, StrongKeyStrongValueSegment<K, V>> mapMakerInternalMap, int i) {
            super(mapMakerInternalMap, i);
        }

        @CheckForNull
        public StrongKeyStrongValueEntry<K, V> castForTesting(@CheckForNull InternalEntry<K, V, ?> internalEntry) {
            return (StrongKeyStrongValueEntry) internalEntry;
        }
    }

    static final class StrongKeyWeakValueSegment<K, V> extends Segment<K, V, StrongKeyWeakValueEntry<K, V>, StrongKeyWeakValueSegment<K, V>> {
        /* access modifiers changed from: private */
        public final ReferenceQueue<V> queueForValues = new ReferenceQueue<>();

        /* access modifiers changed from: package-private */
        public StrongKeyWeakValueSegment<K, V> self() {
            return this;
        }

        StrongKeyWeakValueSegment(MapMakerInternalMap<K, V, StrongKeyWeakValueEntry<K, V>, StrongKeyWeakValueSegment<K, V>> mapMakerInternalMap, int i) {
            super(mapMakerInternalMap, i);
        }

        /* access modifiers changed from: package-private */
        public ReferenceQueue<V> getValueReferenceQueueForTesting() {
            return this.queueForValues;
        }

        @CheckForNull
        public StrongKeyWeakValueEntry<K, V> castForTesting(@CheckForNull InternalEntry<K, V, ?> internalEntry) {
            return (StrongKeyWeakValueEntry) internalEntry;
        }

        public WeakValueReference<K, V, StrongKeyWeakValueEntry<K, V>> getWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry) {
            return castForTesting((InternalEntry) internalEntry).getValueReference();
        }

        public WeakValueReference<K, V, StrongKeyWeakValueEntry<K, V>> newWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry, V v) {
            return new WeakValueReferenceImpl(this.queueForValues, v, castForTesting((InternalEntry) internalEntry));
        }

        public void setWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry, WeakValueReference<K, V, ? extends InternalEntry<K, V, ?>> weakValueReference) {
            StrongKeyWeakValueEntry castForTesting = castForTesting((InternalEntry) internalEntry);
            WeakValueReference access$400 = castForTesting.valueReference;
            WeakValueReference unused = castForTesting.valueReference = weakValueReference;
            access$400.clear();
        }

        /* access modifiers changed from: package-private */
        public void maybeDrainReferenceQueues() {
            drainValueReferenceQueue(this.queueForValues);
        }

        /* access modifiers changed from: package-private */
        public void maybeClearReferenceQueues() {
            clearReferenceQueue(this.queueForValues);
        }
    }

    static final class StrongKeyDummyValueSegment<K> extends Segment<K, MapMaker.Dummy, StrongKeyDummyValueEntry<K>, StrongKeyDummyValueSegment<K>> {
        /* access modifiers changed from: package-private */
        public StrongKeyDummyValueSegment<K> self() {
            return this;
        }

        StrongKeyDummyValueSegment(MapMakerInternalMap<K, MapMaker.Dummy, StrongKeyDummyValueEntry<K>, StrongKeyDummyValueSegment<K>> mapMakerInternalMap, int i) {
            super(mapMakerInternalMap, i);
        }

        public StrongKeyDummyValueEntry<K> castForTesting(InternalEntry<K, MapMaker.Dummy, ?> internalEntry) {
            return (StrongKeyDummyValueEntry) internalEntry;
        }
    }

    static final class WeakKeyStrongValueSegment<K, V> extends Segment<K, V, WeakKeyStrongValueEntry<K, V>, WeakKeyStrongValueSegment<K, V>> {
        /* access modifiers changed from: private */
        public final ReferenceQueue<K> queueForKeys = new ReferenceQueue<>();

        /* access modifiers changed from: package-private */
        public WeakKeyStrongValueSegment<K, V> self() {
            return this;
        }

        WeakKeyStrongValueSegment(MapMakerInternalMap<K, V, WeakKeyStrongValueEntry<K, V>, WeakKeyStrongValueSegment<K, V>> mapMakerInternalMap, int i) {
            super(mapMakerInternalMap, i);
        }

        /* access modifiers changed from: package-private */
        public ReferenceQueue<K> getKeyReferenceQueueForTesting() {
            return this.queueForKeys;
        }

        public WeakKeyStrongValueEntry<K, V> castForTesting(InternalEntry<K, V, ?> internalEntry) {
            return (WeakKeyStrongValueEntry) internalEntry;
        }

        /* access modifiers changed from: package-private */
        public void maybeDrainReferenceQueues() {
            drainKeyReferenceQueue(this.queueForKeys);
        }

        /* access modifiers changed from: package-private */
        public void maybeClearReferenceQueues() {
            clearReferenceQueue(this.queueForKeys);
        }
    }

    static final class WeakKeyWeakValueSegment<K, V> extends Segment<K, V, WeakKeyWeakValueEntry<K, V>, WeakKeyWeakValueSegment<K, V>> {
        /* access modifiers changed from: private */
        public final ReferenceQueue<K> queueForKeys = new ReferenceQueue<>();
        /* access modifiers changed from: private */
        public final ReferenceQueue<V> queueForValues = new ReferenceQueue<>();

        /* access modifiers changed from: package-private */
        public WeakKeyWeakValueSegment<K, V> self() {
            return this;
        }

        WeakKeyWeakValueSegment(MapMakerInternalMap<K, V, WeakKeyWeakValueEntry<K, V>, WeakKeyWeakValueSegment<K, V>> mapMakerInternalMap, int i) {
            super(mapMakerInternalMap, i);
        }

        /* access modifiers changed from: package-private */
        public ReferenceQueue<K> getKeyReferenceQueueForTesting() {
            return this.queueForKeys;
        }

        /* access modifiers changed from: package-private */
        public ReferenceQueue<V> getValueReferenceQueueForTesting() {
            return this.queueForValues;
        }

        @CheckForNull
        public WeakKeyWeakValueEntry<K, V> castForTesting(@CheckForNull InternalEntry<K, V, ?> internalEntry) {
            return (WeakKeyWeakValueEntry) internalEntry;
        }

        public WeakValueReference<K, V, WeakKeyWeakValueEntry<K, V>> getWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry) {
            return castForTesting((InternalEntry) internalEntry).getValueReference();
        }

        public WeakValueReference<K, V, WeakKeyWeakValueEntry<K, V>> newWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry, V v) {
            return new WeakValueReferenceImpl(this.queueForValues, v, castForTesting((InternalEntry) internalEntry));
        }

        public void setWeakValueReferenceForTesting(InternalEntry<K, V, ?> internalEntry, WeakValueReference<K, V, ? extends InternalEntry<K, V, ?>> weakValueReference) {
            WeakKeyWeakValueEntry castForTesting = castForTesting((InternalEntry) internalEntry);
            WeakValueReference access$1400 = castForTesting.valueReference;
            WeakValueReference unused = castForTesting.valueReference = weakValueReference;
            access$1400.clear();
        }

        /* access modifiers changed from: package-private */
        public void maybeDrainReferenceQueues() {
            drainKeyReferenceQueue(this.queueForKeys);
            drainValueReferenceQueue(this.queueForValues);
        }

        /* access modifiers changed from: package-private */
        public void maybeClearReferenceQueues() {
            clearReferenceQueue(this.queueForKeys);
        }
    }

    static final class WeakKeyDummyValueSegment<K> extends Segment<K, MapMaker.Dummy, WeakKeyDummyValueEntry<K>, WeakKeyDummyValueSegment<K>> {
        /* access modifiers changed from: private */
        public final ReferenceQueue<K> queueForKeys = new ReferenceQueue<>();

        /* access modifiers changed from: package-private */
        public WeakKeyDummyValueSegment<K> self() {
            return this;
        }

        WeakKeyDummyValueSegment(MapMakerInternalMap<K, MapMaker.Dummy, WeakKeyDummyValueEntry<K>, WeakKeyDummyValueSegment<K>> mapMakerInternalMap, int i) {
            super(mapMakerInternalMap, i);
        }

        /* access modifiers changed from: package-private */
        public ReferenceQueue<K> getKeyReferenceQueueForTesting() {
            return this.queueForKeys;
        }

        public WeakKeyDummyValueEntry<K> castForTesting(InternalEntry<K, MapMaker.Dummy, ?> internalEntry) {
            return (WeakKeyDummyValueEntry) internalEntry;
        }

        /* access modifiers changed from: package-private */
        public void maybeDrainReferenceQueues() {
            drainKeyReferenceQueue(this.queueForKeys);
        }

        /* access modifiers changed from: package-private */
        public void maybeClearReferenceQueues() {
            clearReferenceQueue(this.queueForKeys);
        }
    }

    static final class CleanupMapTask implements Runnable {
        final WeakReference<MapMakerInternalMap<?, ?, ?, ?>> mapReference;

        public CleanupMapTask(MapMakerInternalMap<?, ?, ?, ?> mapMakerInternalMap) {
            this.mapReference = new WeakReference<>(mapMakerInternalMap);
        }

        public void run() {
            MapMakerInternalMap mapMakerInternalMap = (MapMakerInternalMap) this.mapReference.get();
            if (mapMakerInternalMap != null) {
                for (Segment<K, V, E, S> runCleanup : mapMakerInternalMap.segments) {
                    runCleanup.runCleanup();
                }
                return;
            }
            throw new CancellationException();
        }
    }

    /* access modifiers changed from: package-private */
    public Strength keyStrength() {
        return this.entryHelper.keyStrength();
    }

    /* access modifiers changed from: package-private */
    public Strength valueStrength() {
        return this.entryHelper.valueStrength();
    }

    /* access modifiers changed from: package-private */
    public Equivalence<Object> valueEquivalence() {
        return this.entryHelper.valueStrength().defaultEquivalence();
    }

    public boolean isEmpty() {
        Segment<K, V, E, S>[] segmentArr = this.segments;
        long j = 0;
        for (int i = 0; i < segmentArr.length; i++) {
            if (segmentArr[i].count != 0) {
                return false;
            }
            j += (long) segmentArr[i].modCount;
        }
        if (j == 0) {
            return true;
        }
        for (int i2 = 0; i2 < segmentArr.length; i2++) {
            if (segmentArr[i2].count != 0) {
                return false;
            }
            j -= (long) segmentArr[i2].modCount;
        }
        if (j == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        Segment<K, V, E, S>[] segmentArr = this.segments;
        long j = 0;
        for (Segment<K, V, E, S> segment : segmentArr) {
            j += (long) segment.count;
        }
        return Ints.saturatedCast(j);
    }

    @CheckForNull
    public V get(@CheckForNull Object obj) {
        if (obj == null) {
            return null;
        }
        int hash = hash(obj);
        return segmentFor(hash).get(obj, hash);
    }

    /* access modifiers changed from: package-private */
    @CheckForNull
    public E getEntry(@CheckForNull Object obj) {
        if (obj == null) {
            return null;
        }
        int hash = hash(obj);
        return segmentFor(hash).getEntry(obj, hash);
    }

    public boolean containsKey(@CheckForNull Object obj) {
        if (obj == null) {
            return false;
        }
        int hash = hash(obj);
        return segmentFor(hash).containsKey(obj, hash);
    }

    public boolean containsValue(@CheckForNull Object obj) {
        Object obj2 = obj;
        boolean z = false;
        if (obj2 == null) {
            return false;
        }
        Segment<K, V, E, S>[] segmentArr = this.segments;
        long j = -1;
        int i = 0;
        while (i < 3) {
            int length = segmentArr.length;
            long j2 = 0;
            int i2 = z;
            while (i2 < length) {
                Segment<K, V, E, S> segment = segmentArr[i2];
                int i3 = segment.count;
                AtomicReferenceArray<E> atomicReferenceArray = segment.table;
                for (int i4 = z; i4 < atomicReferenceArray.length(); i4++) {
                    for (InternalEntry internalEntry = (InternalEntry) atomicReferenceArray.get(i4); internalEntry != null; internalEntry = internalEntry.getNext()) {
                        V liveValue = segment.getLiveValue(internalEntry);
                        if (liveValue != null && valueEquivalence().equivalent(obj2, liveValue)) {
                            return true;
                        }
                    }
                }
                j2 += (long) segment.modCount;
                i2++;
                z = false;
            }
            if (j2 == j) {
                return false;
            }
            i++;
            j = j2;
            z = false;
        }
        return z;
    }

    @CheckForNull
    public V put(K k, V v) {
        Preconditions.checkNotNull(k);
        Preconditions.checkNotNull(v);
        int hash = hash(k);
        return segmentFor(hash).put(k, hash, v, false);
    }

    @CheckForNull
    public V putIfAbsent(K k, V v) {
        Preconditions.checkNotNull(k);
        Preconditions.checkNotNull(v);
        int hash = hash(k);
        return segmentFor(hash).put(k, hash, v, true);
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry next : map.entrySet()) {
            put(next.getKey(), next.getValue());
        }
    }

    @CheckForNull
    public V remove(@CheckForNull Object obj) {
        if (obj == null) {
            return null;
        }
        int hash = hash(obj);
        return segmentFor(hash).remove(obj, hash);
    }

    public boolean remove(@CheckForNull Object obj, @CheckForNull Object obj2) {
        if (obj == null || obj2 == null) {
            return false;
        }
        int hash = hash(obj);
        return segmentFor(hash).remove(obj, hash, obj2);
    }

    public boolean replace(K k, @CheckForNull V v, V v2) {
        Preconditions.checkNotNull(k);
        Preconditions.checkNotNull(v2);
        if (v == null) {
            return false;
        }
        int hash = hash(k);
        return segmentFor(hash).replace(k, hash, v, v2);
    }

    @CheckForNull
    public V replace(K k, V v) {
        Preconditions.checkNotNull(k);
        Preconditions.checkNotNull(v);
        int hash = hash(k);
        return segmentFor(hash).replace(k, hash, v);
    }

    public void clear() {
        for (Segment<K, V, E, S> clear : this.segments) {
            clear.clear();
        }
    }

    public Set<K> keySet() {
        Set<K> set = this.keySet;
        if (set != null) {
            return set;
        }
        KeySet keySet2 = new KeySet();
        this.keySet = keySet2;
        return keySet2;
    }

    public Collection<V> values() {
        Collection<V> collection = this.values;
        if (collection != null) {
            return collection;
        }
        Values values2 = new Values();
        this.values = values2;
        return values2;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        EntrySet entrySet2 = new EntrySet();
        this.entrySet = entrySet2;
        return entrySet2;
    }

    abstract class HashIterator<T> implements Iterator<T> {
        @CheckForNull
        Segment<K, V, E, S> currentSegment;
        @CheckForNull
        AtomicReferenceArray<E> currentTable;
        @CheckForNull
        MapMakerInternalMap<K, V, E, S>.WriteThroughEntry lastReturned;
        @CheckForNull
        E nextEntry;
        @CheckForNull
        MapMakerInternalMap<K, V, E, S>.WriteThroughEntry nextExternal;
        int nextSegmentIndex;
        int nextTableIndex = -1;

        public abstract T next();

        HashIterator() {
            this.nextSegmentIndex = MapMakerInternalMap.this.segments.length - 1;
            advance();
        }

        /* access modifiers changed from: package-private */
        public final void advance() {
            this.nextExternal = null;
            if (!nextInChain() && !nextInTable()) {
                while (this.nextSegmentIndex >= 0) {
                    Segment<K, V, E, S>[] segmentArr = MapMakerInternalMap.this.segments;
                    int i = this.nextSegmentIndex;
                    this.nextSegmentIndex = i - 1;
                    Segment<K, V, E, S> segment = segmentArr[i];
                    this.currentSegment = segment;
                    if (segment.count != 0) {
                        AtomicReferenceArray<E> atomicReferenceArray = this.currentSegment.table;
                        this.currentTable = atomicReferenceArray;
                        this.nextTableIndex = atomicReferenceArray.length() - 1;
                        if (nextInTable()) {
                            return;
                        }
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean nextInChain() {
            E e = this.nextEntry;
            if (e == null) {
                return false;
            }
            while (true) {
                this.nextEntry = e.getNext();
                E e2 = this.nextEntry;
                if (e2 == null) {
                    return false;
                }
                if (advanceTo(e2)) {
                    return true;
                }
                e = this.nextEntry;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean nextInTable() {
            while (true) {
                int i = this.nextTableIndex;
                if (i < 0) {
                    return false;
                }
                AtomicReferenceArray<E> atomicReferenceArray = this.currentTable;
                this.nextTableIndex = i - 1;
                E e = (InternalEntry) atomicReferenceArray.get(i);
                this.nextEntry = e;
                if (e != null && (advanceTo(e) || nextInChain())) {
                    return true;
                }
            }
        }

        /* JADX INFO: finally extract failed */
        /* access modifiers changed from: package-private */
        public boolean advanceTo(E e) {
            try {
                Object key = e.getKey();
                Object liveValue = MapMakerInternalMap.this.getLiveValue(e);
                if (liveValue != null) {
                    this.nextExternal = new WriteThroughEntry(key, liveValue);
                    this.currentSegment.postReadCleanup();
                    return true;
                }
                this.currentSegment.postReadCleanup();
                return false;
            } catch (Throwable th) {
                this.currentSegment.postReadCleanup();
                throw th;
            }
        }

        public boolean hasNext() {
            return this.nextExternal != null;
        }

        /* access modifiers changed from: package-private */
        public MapMakerInternalMap<K, V, E, S>.WriteThroughEntry nextEntry() {
            MapMakerInternalMap<K, V, E, S>.WriteThroughEntry writeThroughEntry = this.nextExternal;
            if (writeThroughEntry != null) {
                this.lastReturned = writeThroughEntry;
                advance();
                return this.lastReturned;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            CollectPreconditions.checkRemove(this.lastReturned != null);
            MapMakerInternalMap.this.remove(this.lastReturned.getKey());
            this.lastReturned = null;
        }
    }

    final class KeyIterator extends MapMakerInternalMap<K, V, E, S>.HashIterator<K> {
        KeyIterator() {
            super();
        }

        public K next() {
            return nextEntry().getKey();
        }
    }

    final class ValueIterator extends MapMakerInternalMap<K, V, E, S>.HashIterator<V> {
        ValueIterator() {
            super();
        }

        public V next() {
            return nextEntry().getValue();
        }
    }

    final class WriteThroughEntry extends AbstractMapEntry<K, V> {
        final K key;
        V value;

        WriteThroughEntry(K k, V v) {
            this.key = k;
            this.value = v;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public boolean equals(@CheckForNull Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            if (!this.key.equals(entry.getKey()) || !this.value.equals(entry.getValue())) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.key.hashCode() ^ this.value.hashCode();
        }

        public V setValue(V v) {
            V put = MapMakerInternalMap.this.put(this.key, v);
            this.value = v;
            return put;
        }
    }

    final class EntryIterator extends MapMakerInternalMap<K, V, E, S>.HashIterator<Map.Entry<K, V>> {
        EntryIterator() {
            super();
        }

        public Map.Entry<K, V> next() {
            return nextEntry();
        }
    }

    final class KeySet extends AbstractSet<K> {
        KeySet() {
        }

        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        public int size() {
            return MapMakerInternalMap.this.size();
        }

        public boolean isEmpty() {
            return MapMakerInternalMap.this.isEmpty();
        }

        public boolean contains(Object obj) {
            return MapMakerInternalMap.this.containsKey(obj);
        }

        public boolean remove(Object obj) {
            return MapMakerInternalMap.this.remove(obj) != null;
        }

        public void clear() {
            MapMakerInternalMap.this.clear();
        }
    }

    final class Values extends AbstractCollection<V> {
        Values() {
        }

        public Iterator<V> iterator() {
            return new ValueIterator();
        }

        public int size() {
            return MapMakerInternalMap.this.size();
        }

        public boolean isEmpty() {
            return MapMakerInternalMap.this.isEmpty();
        }

        public boolean contains(Object obj) {
            return MapMakerInternalMap.this.containsValue(obj);
        }

        public void clear() {
            MapMakerInternalMap.this.clear();
        }
    }

    final class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        EntrySet() {
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x0006, code lost:
            r4 = (java.util.Map.Entry) r4;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean contains(java.lang.Object r4) {
            /*
                r3 = this;
                boolean r0 = r4 instanceof java.util.Map.Entry
                r1 = 0
                if (r0 != 0) goto L_0x0006
                return r1
            L_0x0006:
                java.util.Map$Entry r4 = (java.util.Map.Entry) r4
                java.lang.Object r0 = r4.getKey()
                if (r0 != 0) goto L_0x000f
                return r1
            L_0x000f:
                com.google.common.collect.MapMakerInternalMap r2 = com.google.common.collect.MapMakerInternalMap.this
                java.lang.Object r0 = r2.get(r0)
                if (r0 == 0) goto L_0x0028
                com.google.common.collect.MapMakerInternalMap r2 = com.google.common.collect.MapMakerInternalMap.this
                com.google.common.base.Equivalence r2 = r2.valueEquivalence()
                java.lang.Object r4 = r4.getValue()
                boolean r4 = r2.equivalent(r4, r0)
                if (r4 == 0) goto L_0x0028
                r1 = 1
            L_0x0028:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.EntrySet.contains(java.lang.Object):boolean");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x0006, code lost:
            r4 = (java.util.Map.Entry) r4;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean remove(java.lang.Object r4) {
            /*
                r3 = this;
                boolean r0 = r4 instanceof java.util.Map.Entry
                r1 = 0
                if (r0 != 0) goto L_0x0006
                return r1
            L_0x0006:
                java.util.Map$Entry r4 = (java.util.Map.Entry) r4
                java.lang.Object r0 = r4.getKey()
                if (r0 == 0) goto L_0x001b
                com.google.common.collect.MapMakerInternalMap r2 = com.google.common.collect.MapMakerInternalMap.this
                java.lang.Object r4 = r4.getValue()
                boolean r4 = r2.remove(r0, r4)
                if (r4 == 0) goto L_0x001b
                r1 = 1
            L_0x001b:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.EntrySet.remove(java.lang.Object):boolean");
        }

        public int size() {
            return MapMakerInternalMap.this.size();
        }

        public boolean isEmpty() {
            return MapMakerInternalMap.this.isEmpty();
        }

        public void clear() {
            MapMakerInternalMap.this.clear();
        }
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializationProxy(this.entryHelper.keyStrength(), this.entryHelper.valueStrength(), this.keyEquivalence, this.entryHelper.valueStrength().defaultEquivalence(), this.concurrencyLevel, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializationProxy");
    }

    static abstract class AbstractSerializationProxy<K, V> extends ForwardingConcurrentMap<K, V> implements Serializable {
        private static final long serialVersionUID = 3;
        final int concurrencyLevel;
        transient ConcurrentMap<K, V> delegate;
        final Equivalence<Object> keyEquivalence;
        final Strength keyStrength;
        final Equivalence<Object> valueEquivalence;
        final Strength valueStrength;

        AbstractSerializationProxy(Strength strength, Strength strength2, Equivalence<Object> equivalence, Equivalence<Object> equivalence2, int i, ConcurrentMap<K, V> concurrentMap) {
            this.keyStrength = strength;
            this.valueStrength = strength2;
            this.keyEquivalence = equivalence;
            this.valueEquivalence = equivalence2;
            this.concurrencyLevel = i;
            this.delegate = concurrentMap;
        }

        /* access modifiers changed from: protected */
        public ConcurrentMap<K, V> delegate() {
            return this.delegate;
        }

        /* access modifiers changed from: package-private */
        public void writeMapTo(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.writeInt(this.delegate.size());
            for (Map.Entry entry : this.delegate.entrySet()) {
                objectOutputStream.writeObject(entry.getKey());
                objectOutputStream.writeObject(entry.getValue());
            }
            objectOutputStream.writeObject((Object) null);
        }

        /* access modifiers changed from: package-private */
        public MapMaker readMapMaker(ObjectInputStream objectInputStream) throws IOException {
            return new MapMaker().initialCapacity(objectInputStream.readInt()).setKeyStrength(this.keyStrength).setValueStrength(this.valueStrength).keyEquivalence(this.keyEquivalence).concurrencyLevel(this.concurrencyLevel);
        }

        /* access modifiers changed from: package-private */
        public void readEntries(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            while (true) {
                Object readObject = objectInputStream.readObject();
                if (readObject != null) {
                    this.delegate.put(readObject, objectInputStream.readObject());
                } else {
                    return;
                }
            }
        }
    }

    private static final class SerializationProxy<K, V> extends AbstractSerializationProxy<K, V> {
        private static final long serialVersionUID = 3;

        SerializationProxy(Strength strength, Strength strength2, Equivalence<Object> equivalence, Equivalence<Object> equivalence2, int i, ConcurrentMap<K, V> concurrentMap) {
            super(strength, strength2, equivalence, equivalence2, i, concurrentMap);
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            writeMapTo(objectOutputStream);
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            this.delegate = readMapMaker(objectInputStream).makeMap();
            readEntries(objectInputStream);
        }

        private Object readResolve() {
            return this.delegate;
        }
    }
}
