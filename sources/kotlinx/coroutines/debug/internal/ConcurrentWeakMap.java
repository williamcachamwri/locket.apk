package kotlinx.coroutines.debug.internal;

import com.google.common.util.concurrent.Striped$SmallLazyStriped$$ExternalSyntheticBackportWithForwarding0;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.AbstractMutableMap;
import kotlin.collections.AbstractMutableSet;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.jvm.internal.markers.KMutableMap;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010#\n\u0002\b\u0003\n\u0002\u0010'\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0004:\u0003)*+B\u0011\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u0013\u001a\u00020\u0014H\u0002J\u0018\u0010\u0015\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0016\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u0017J\u001f\u0010\u0018\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0016\u001a\u00028\u00002\u0006\u0010\u0019\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u001aJ\u0017\u0010\u001b\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0016\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0017J!\u0010\u001c\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0016\u001a\u00028\u00002\b\u0010\u0019\u001a\u0004\u0018\u00018\u0001H\u0002¢\u0006\u0002\u0010\u001aJ\b\u0010$\u001a\u00020\u0014H\u0016J\u0006\u0010%\u001a\u00020\u0014J\u0014\u0010&\u001a\u00020\u00142\n\u0010'\u001a\u0006\u0012\u0002\b\u00030(H\u0002R\t\u0010\t\u001a\u00020\nX\u0004R\u001f\u0010\u000b\u001a\u0018\u0012\u0014\u0012\u00120\rR\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00000\fX\u0004R\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u000eX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u00108VX\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e8VX\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R&\u0010!\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\"0\u001e8VX\u0004¢\u0006\u0006\u001a\u0004\b#\u0010 ¨\u0006,"}, d2 = {"Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap;", "K", "", "V", "Lkotlin/collections/AbstractMutableMap;", "weakRefQueue", "", "<init>", "(Z)V", "_size", "Lkotlinx/atomicfu/AtomicInt;", "core", "Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap$Core;", "Ljava/lang/ref/ReferenceQueue;", "size", "", "getSize", "()I", "decrementSize", "", "get", "key", "(Ljava/lang/Object;)Ljava/lang/Object;", "put", "value", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "remove", "putSynchronized", "keys", "", "getKeys", "()Ljava/util/Set;", "entries", "", "getEntries", "clear", "runWeakRefQueueCleaningLoopUntilInterrupted", "cleanWeakRef", "w", "Lkotlinx/coroutines/debug/internal/HashedWeakRef;", "Core", "Entry", "KeyValueSet", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: ConcurrentWeakMap.kt */
public final class ConcurrentWeakMap<K, V> extends AbstractMutableMap<K, V> {
    /* access modifiers changed from: private */
    public static final /* synthetic */ AtomicIntegerFieldUpdater _size$volatile$FU;
    /* access modifiers changed from: private */
    public static final /* synthetic */ AtomicReferenceFieldUpdater core$volatile$FU;
    private volatile /* synthetic */ int _size$volatile;
    private volatile /* synthetic */ Object core$volatile;
    /* access modifiers changed from: private */
    public final ReferenceQueue<K> weakRefQueue;

    static {
        Class<ConcurrentWeakMap> cls = ConcurrentWeakMap.class;
        _size$volatile$FU = AtomicIntegerFieldUpdater.newUpdater(cls, "_size$volatile");
        core$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "core$volatile");
    }

    public ConcurrentWeakMap() {
        this(false, 1, (DefaultConstructorMarker) null);
    }

    /* access modifiers changed from: private */
    public static final Object _get_keys_$lambda$0(Object obj, Object obj2) {
        return obj;
    }

    private final /* synthetic */ Object getCore$volatile() {
        return this.core$volatile;
    }

    private final /* synthetic */ int get_size$volatile() {
        return this._size$volatile;
    }

    private final /* synthetic */ void setCore$volatile(Object obj) {
        this.core$volatile = obj;
    }

    private final /* synthetic */ void set_size$volatile(int i) {
        this._size$volatile = i;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ConcurrentWeakMap(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z);
    }

    public ConcurrentWeakMap(boolean z) {
        this.core$volatile = new Core(16);
        this.weakRefQueue = z ? new ReferenceQueue<>() : null;
    }

    public int getSize() {
        return _size$volatile$FU.get(this);
    }

    /* access modifiers changed from: private */
    public final void decrementSize() {
        _size$volatile$FU.decrementAndGet(this);
    }

    public V get(Object obj) {
        if (obj == null) {
            return null;
        }
        return ((Core) core$volatile$FU.get(this)).getImpl(obj);
    }

    public V put(K k, V v) {
        V putImpl$default = Core.putImpl$default((Core) core$volatile$FU.get(this), k, v, (HashedWeakRef) null, 4, (Object) null);
        if (putImpl$default == ConcurrentWeakMapKt.REHASH) {
            putImpl$default = putSynchronized(k, v);
        }
        if (putImpl$default == null) {
            _size$volatile$FU.incrementAndGet(this);
        }
        return putImpl$default;
    }

    public V remove(Object obj) {
        if (obj == null) {
            return null;
        }
        V putImpl$default = Core.putImpl$default((Core) core$volatile$FU.get(this), obj, (Object) null, (HashedWeakRef) null, 4, (Object) null);
        if (putImpl$default == ConcurrentWeakMapKt.REHASH) {
            putImpl$default = putSynchronized(obj, (Object) null);
        }
        if (putImpl$default != null) {
            _size$volatile$FU.decrementAndGet(this);
        }
        return putImpl$default;
    }

    private final synchronized V putSynchronized(K k, V v) {
        V putImpl$default;
        ConcurrentWeakMap<K, V>.Core core = (Core) core$volatile$FU.get(this);
        while (true) {
            putImpl$default = Core.putImpl$default(core, k, v, (HashedWeakRef) null, 4, (Object) null);
            if (putImpl$default == ConcurrentWeakMapKt.REHASH) {
                core = core.rehash();
                core$volatile$FU.set(this, core);
            }
        }
        return putImpl$default;
    }

    public Set<K> getKeys() {
        return new KeyValueSet<>(new ConcurrentWeakMap$$ExternalSyntheticLambda0());
    }

    /* access modifiers changed from: private */
    public static final Map.Entry _get_entries_$lambda$1(Object obj, Object obj2) {
        return new Entry(obj, obj2);
    }

    public Set<Map.Entry<K, V>> getEntries() {
        return new KeyValueSet<>(new ConcurrentWeakMap$$ExternalSyntheticLambda1());
    }

    public void clear() {
        for (Object remove : keySet()) {
            remove(remove);
        }
    }

    public final void runWeakRefQueueCleaningLoopUntilInterrupted() {
        if (this.weakRefQueue != null) {
            while (true) {
                try {
                    Reference<? extends K> remove = this.weakRefQueue.remove();
                    Intrinsics.checkNotNull(remove, "null cannot be cast to non-null type kotlinx.coroutines.debug.internal.HashedWeakRef<*>");
                    cleanWeakRef((HashedWeakRef) remove);
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        } else {
            throw new IllegalStateException("Must be created with weakRefQueue = true".toString());
        }
    }

    private final void cleanWeakRef(HashedWeakRef<?> hashedWeakRef) {
        ((Core) core$volatile$FU.get(this)).cleanWeakRef(hashedWeakRef);
    }

    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010)\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\"B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0003H\u0002J\u0015\u0010\u0010\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0011\u001a\u00028\u0000¢\u0006\u0002\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u0003H\u0002J1\u0010\u0015\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0011\u001a\u00028\u00002\b\u0010\u0016\u001a\u0004\u0018\u00018\u00012\u0010\b\u0002\u0010\u0017\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\f¢\u0006\u0002\u0010\u0018J\u0016\u0010\u0019\u001a\u00120\u0000R\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001aJ\u0012\u0010\u001b\u001a\u00020\u00142\n\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\fJ,\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u001f0\u001e\"\u0004\b\u0002\u0010\u001f2\u0018\u0010 \u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u0002H\u001f0!R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\t\u0010\b\u001a\u00020\tX\u0004R\u0017\u0010\n\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\f0\u000bX\u0004R\u0011\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000bX\u0004¨\u0006#"}, d2 = {"Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap$Core;", "", "allocated", "", "<init>", "(Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap;I)V", "shift", "threshold", "load", "Lkotlinx/atomicfu/AtomicInt;", "keys", "Lkotlinx/atomicfu/AtomicArray;", "Lkotlinx/coroutines/debug/internal/HashedWeakRef;", "values", "index", "hash", "getImpl", "key", "(Ljava/lang/Object;)Ljava/lang/Object;", "removeCleanedAt", "", "putImpl", "value", "weakKey0", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlinx/coroutines/debug/internal/HashedWeakRef;)Ljava/lang/Object;", "rehash", "Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap;", "cleanWeakRef", "weakRef", "keyValueIterator", "", "E", "factory", "Lkotlin/Function2;", "KeyValueIterator", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: ConcurrentWeakMap.kt */
    private final class Core {
        /* access modifiers changed from: private */
        public static final /* synthetic */ AtomicIntegerFieldUpdater load$volatile$FU = AtomicIntegerFieldUpdater.newUpdater(Core.class, "load$volatile");
        /* access modifiers changed from: private */
        public final int allocated;
        private final /* synthetic */ AtomicReferenceArray keys;
        private volatile /* synthetic */ int load$volatile;
        private final int shift;
        private final int threshold;
        private final /* synthetic */ AtomicReferenceArray values;

        /* access modifiers changed from: private */
        public final /* synthetic */ AtomicReferenceArray getKeys() {
            return this.keys;
        }

        private final /* synthetic */ int getLoad$volatile() {
            return this.load$volatile;
        }

        /* access modifiers changed from: private */
        public final /* synthetic */ AtomicReferenceArray getValues() {
            return this.values;
        }

        private final /* synthetic */ void setLoad$volatile(int i) {
            this.load$volatile = i;
        }

        private final /* synthetic */ void update$atomicfu(Object obj, AtomicIntegerFieldUpdater atomicIntegerFieldUpdater, Function1<? super Integer, Integer> function1) {
            int i;
            do {
                i = atomicIntegerFieldUpdater.get(obj);
            } while (!atomicIntegerFieldUpdater.compareAndSet(obj, i, function1.invoke(Integer.valueOf(i)).intValue()));
        }

        public Core(int i) {
            this.allocated = i;
            this.shift = Integer.numberOfLeadingZeros(i) + 1;
            this.threshold = (i * 2) / 3;
            this.keys = new AtomicReferenceArray(i);
            this.values = new AtomicReferenceArray(i);
        }

        private final int index(int i) {
            return (i * -1640531527) >>> this.shift;
        }

        public final V getImpl(K k) {
            int index = index(k.hashCode());
            while (true) {
                HashedWeakRef hashedWeakRef = (HashedWeakRef) getKeys().get(index);
                if (hashedWeakRef == null) {
                    return null;
                }
                Object obj = hashedWeakRef.get();
                if (Intrinsics.areEqual((Object) k, obj)) {
                    V v = getValues().get(index);
                    return v instanceof Marked ? ((Marked) v).ref : v;
                }
                if (obj == null) {
                    removeCleanedAt(index);
                }
                if (index == 0) {
                    index = this.allocated;
                }
                index--;
            }
        }

        private final void removeCleanedAt(int i) {
            Object obj;
            do {
                obj = getValues().get(i);
                if (obj == null || (obj instanceof Marked)) {
                    return;
                }
            } while (!Striped$SmallLazyStriped$$ExternalSyntheticBackportWithForwarding0.m(getValues(), i, obj, (Object) null));
            ConcurrentWeakMap.this.decrementSize();
        }

        public static /* synthetic */ Object putImpl$default(Core core, Object obj, Object obj2, HashedWeakRef hashedWeakRef, int i, Object obj3) {
            if ((i & 4) != 0) {
                hashedWeakRef = null;
            }
            return core.putImpl(obj, obj2, hashedWeakRef);
        }

        public final Object putImpl(K k, V v, HashedWeakRef<K> hashedWeakRef) {
            Object obj;
            int i;
            int index = index(k.hashCode());
            boolean z = false;
            while (true) {
                HashedWeakRef hashedWeakRef2 = (HashedWeakRef) getKeys().get(index);
                if (hashedWeakRef2 == null) {
                    if (v != null) {
                        if (!z) {
                            AtomicIntegerFieldUpdater load$volatile$FU2 = load$volatile$FU;
                            do {
                                i = load$volatile$FU2.get(this);
                                if (i >= this.threshold) {
                                    return ConcurrentWeakMapKt.REHASH;
                                }
                            } while (!load$volatile$FU2.compareAndSet(this, i, i + 1));
                            z = true;
                        }
                        if (hashedWeakRef == null) {
                            hashedWeakRef = new HashedWeakRef<>(k, ConcurrentWeakMap.this.weakRefQueue);
                        }
                        if (Striped$SmallLazyStriped$$ExternalSyntheticBackportWithForwarding0.m(getKeys(), index, (Object) null, hashedWeakRef)) {
                            break;
                        }
                    } else {
                        return null;
                    }
                } else {
                    Object obj2 = hashedWeakRef2.get();
                    if (!Intrinsics.areEqual((Object) k, obj2)) {
                        if (obj2 == null) {
                            removeCleanedAt(index);
                        }
                        if (index == 0) {
                            index = this.allocated;
                        }
                        index--;
                    } else if (z) {
                        load$volatile$FU.decrementAndGet(this);
                    }
                }
            }
            do {
                obj = getValues().get(index);
                if (obj instanceof Marked) {
                    return ConcurrentWeakMapKt.REHASH;
                }
            } while (!Striped$SmallLazyStriped$$ExternalSyntheticBackportWithForwarding0.m(getValues(), index, obj, v));
            return obj;
        }

        public final ConcurrentWeakMap<K, V>.Core rehash() {
            Object obj;
            while (true) {
                ConcurrentWeakMap<K, V>.Core core = new Core(Integer.highestOneBit(RangesKt.coerceAtLeast(ConcurrentWeakMap.this.size(), 4)) * 4);
                int i = this.allocated;
                int i2 = 0;
                while (true) {
                    if (i2 >= i) {
                        return core;
                    }
                    HashedWeakRef hashedWeakRef = (HashedWeakRef) getKeys().get(i2);
                    Object obj2 = hashedWeakRef != null ? hashedWeakRef.get() : null;
                    if (hashedWeakRef != null && obj2 == null) {
                        removeCleanedAt(i2);
                    }
                    while (true) {
                        obj = getValues().get(i2);
                        if (!(obj instanceof Marked)) {
                            if (Striped$SmallLazyStriped$$ExternalSyntheticBackportWithForwarding0.m(getValues(), i2, obj, ConcurrentWeakMapKt.mark(obj))) {
                                break;
                            }
                        } else {
                            obj = ((Marked) obj).ref;
                            break;
                        }
                    }
                    if (obj2 == null || obj == null || core.putImpl(obj2, obj, hashedWeakRef) != ConcurrentWeakMapKt.REHASH) {
                        i2++;
                    }
                }
            }
        }

        public final void cleanWeakRef(HashedWeakRef<?> hashedWeakRef) {
            int index = index(hashedWeakRef.hash);
            while (true) {
                HashedWeakRef<?> hashedWeakRef2 = (HashedWeakRef) getKeys().get(index);
                if (hashedWeakRef2 != null) {
                    if (hashedWeakRef2 == hashedWeakRef) {
                        removeCleanedAt(index);
                        return;
                    }
                    if (index == 0) {
                        index = this.allocated;
                    }
                    index--;
                } else {
                    return;
                }
            }
        }

        public final <E> Iterator<E> keyValueIterator(Function2<? super K, ? super V, ? extends E> function2) {
            return new KeyValueIterator<>(function2);
        }

        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010)\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\b\u0004\u0018\u0000*\u0004\b\u0002\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B!\u0012\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\f\u001a\u00020\rH\u0002J\t\u0010\u000e\u001a\u00020\u000fH\u0002J\u000e\u0010\u0010\u001a\u00028\u0002H\u0002¢\u0006\u0002\u0010\u0011J\b\u0010\u0012\u001a\u00020\u0013H\u0016R \u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00028\u0000X.¢\u0006\u0004\n\u0002\u0010\nR\u0010\u0010\u000b\u001a\u00028\u0001X.¢\u0006\u0004\n\u0002\u0010\n¨\u0006\u0014"}, d2 = {"Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap$Core$KeyValueIterator;", "E", "", "factory", "Lkotlin/Function2;", "<init>", "(Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap$Core;Lkotlin/jvm/functions/Function2;)V", "index", "", "key", "Ljava/lang/Object;", "value", "findNext", "", "hasNext", "", "next", "()Ljava/lang/Object;", "remove", "", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: ConcurrentWeakMap.kt */
        private final class KeyValueIterator<E> implements Iterator<E>, KMutableIterator {
            private final Function2<K, V, E> factory;
            private int index = -1;
            private K key;
            private V value;

            public KeyValueIterator(Function2<? super K, ? super V, ? extends E> function2) {
                this.factory = function2;
                findNext();
            }

            private final void findNext() {
                K k;
                while (true) {
                    int i = this.index + 1;
                    this.index = i;
                    if (i < Core.this.allocated) {
                        HashedWeakRef hashedWeakRef = (HashedWeakRef) Core.this.getKeys().get(this.index);
                        if (!(hashedWeakRef == null || (k = hashedWeakRef.get()) == null)) {
                            this.key = k;
                            V v = Core.this.getValues().get(this.index);
                            if (v instanceof Marked) {
                                v = ((Marked) v).ref;
                            }
                            if (v != null) {
                                this.value = v;
                                return;
                            }
                        }
                    } else {
                        return;
                    }
                }
            }

            public boolean hasNext() {
                return this.index < Core.this.allocated;
            }

            public E next() {
                if (this.index < Core.this.allocated) {
                    Function2<K, V, E> function2 = this.factory;
                    K k = this.key;
                    if (k == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("key");
                        k = Unit.INSTANCE;
                    }
                    V v = this.value;
                    if (v == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("value");
                        v = Unit.INSTANCE;
                    }
                    E invoke = function2.invoke(k, v);
                    findNext();
                    return invoke;
                }
                throw new NoSuchElementException();
            }

            public Void remove() {
                Void unused = ConcurrentWeakMapKt.noImpl();
                throw new KotlinNothingValueException();
            }
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010'\n\u0002\b\f\b\u0002\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B\u0017\u0012\u0006\u0010\u0004\u001a\u00028\u0002\u0012\u0006\u0010\u0005\u001a\u00028\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\f\u001a\u00028\u00032\u0006\u0010\r\u001a\u00028\u0003H\u0016¢\u0006\u0002\u0010\u000eR\u0016\u0010\u0004\u001a\u00028\u0002X\u0004¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0005\u001a\u00028\u0003X\u0004¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u000b\u0010\t¨\u0006\u000f"}, d2 = {"Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap$Entry;", "K", "V", "", "key", "value", "<init>", "(Ljava/lang/Object;Ljava/lang/Object;)V", "getKey", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getValue", "setValue", "newValue", "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: ConcurrentWeakMap.kt */
    private static final class Entry<K, V> implements Map.Entry<K, V>, KMutableMap.Entry {
        private final K key;
        private final V value;

        public Entry(K k, V v) {
            this.key = k;
            this.value = v;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public V setValue(V v) {
            Void unused = ConcurrentWeakMapKt.noImpl();
            throw new KotlinNothingValueException();
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010)\n\u0000\b\u0004\u0018\u0000*\u0004\b\u0002\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B!\u0012\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00028\u0002H\u0016¢\u0006\u0002\u0010\u000eJ\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00020\u0010H\u0002R \u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\b8VX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0011"}, d2 = {"Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap$KeyValueSet;", "E", "Lkotlin/collections/AbstractMutableSet;", "factory", "Lkotlin/Function2;", "<init>", "(Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap;Lkotlin/jvm/functions/Function2;)V", "size", "", "getSize", "()I", "add", "", "element", "(Ljava/lang/Object;)Z", "iterator", "", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: ConcurrentWeakMap.kt */
    private final class KeyValueSet<E> extends AbstractMutableSet<E> {
        private final Function2<K, V, E> factory;

        public KeyValueSet(Function2<? super K, ? super V, ? extends E> function2) {
            this.factory = function2;
        }

        public int getSize() {
            return ConcurrentWeakMap.this.size();
        }

        public boolean add(E e) {
            Void unused = ConcurrentWeakMapKt.noImpl();
            throw new KotlinNothingValueException();
        }

        public Iterator<E> iterator() {
            return ((Core) ConcurrentWeakMap.core$volatile$FU.get(ConcurrentWeakMap.this)).keyValueIterator(this.factory);
        }
    }
}
