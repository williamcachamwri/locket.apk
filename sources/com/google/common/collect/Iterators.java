package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.primitives.Ints;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import javax.annotation.CheckForNull;
import kotlin.text.Typography;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@ElementTypesAreNonnullByDefault
public final class Iterators {
    private Iterators() {
    }

    static <T> UnmodifiableIterator<T> emptyIterator() {
        return emptyListIterator();
    }

    static <T> UnmodifiableListIterator<T> emptyListIterator() {
        return ArrayItr.EMPTY;
    }

    private enum EmptyModifiableIterator implements Iterator<Object> {
        INSTANCE;

        public boolean hasNext() {
            return false;
        }

        public Object next() {
            throw new NoSuchElementException();
        }

        public void remove() {
            CollectPreconditions.checkRemove(false);
        }
    }

    static <T> Iterator<T> emptyModifiableIterator() {
        return EmptyModifiableIterator.INSTANCE;
    }

    public static <T> UnmodifiableIterator<T> unmodifiableIterator(final Iterator<? extends T> it) {
        Preconditions.checkNotNull(it);
        if (it instanceof UnmodifiableIterator) {
            return (UnmodifiableIterator) it;
        }
        return new UnmodifiableIterator<T>() {
            public boolean hasNext() {
                return it.hasNext();
            }

            @ParametricNullness
            public T next() {
                return it.next();
            }
        };
    }

    @Deprecated
    public static <T> UnmodifiableIterator<T> unmodifiableIterator(UnmodifiableIterator<T> unmodifiableIterator) {
        return (UnmodifiableIterator) Preconditions.checkNotNull(unmodifiableIterator);
    }

    public static int size(Iterator<?> it) {
        long j = 0;
        while (it.hasNext()) {
            it.next();
            j++;
        }
        return Ints.saturatedCast(j);
    }

    public static boolean contains(Iterator<?> it, @CheckForNull Object obj) {
        if (obj == null) {
            while (it.hasNext()) {
                if (it.next() == null) {
                    return true;
                }
            }
            return false;
        }
        while (it.hasNext()) {
            if (obj.equals(it.next())) {
                return true;
            }
        }
        return false;
    }

    public static boolean removeAll(Iterator<?> it, Collection<?> collection) {
        Preconditions.checkNotNull(collection);
        boolean z = false;
        while (it.hasNext()) {
            if (collection.contains(it.next())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    public static <T> boolean removeIf(Iterator<T> it, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(predicate);
        boolean z = false;
        while (it.hasNext()) {
            if (predicate.apply(it.next())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    public static boolean retainAll(Iterator<?> it, Collection<?> collection) {
        Preconditions.checkNotNull(collection);
        boolean z = false;
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:2:0x0006  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean elementsEqual(java.util.Iterator<?> r3, java.util.Iterator<?> r4) {
        /*
        L_0x0000:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x001d
            boolean r0 = r4.hasNext()
            r1 = 0
            if (r0 != 0) goto L_0x000e
            return r1
        L_0x000e:
            java.lang.Object r0 = r3.next()
            java.lang.Object r2 = r4.next()
            boolean r0 = com.google.common.base.Objects.equal(r0, r2)
            if (r0 != 0) goto L_0x0000
            return r1
        L_0x001d:
            boolean r3 = r4.hasNext()
            r3 = r3 ^ 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.Iterators.elementsEqual(java.util.Iterator, java.util.Iterator):boolean");
    }

    public static String toString(Iterator<?> it) {
        StringBuilder sb = new StringBuilder("[");
        boolean z = true;
        while (it.hasNext()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(it.next());
            z = false;
        }
        return sb.append(AbstractJsonLexerKt.END_LIST).toString();
    }

    @ParametricNullness
    public static <T> T getOnlyElement(Iterator<T> it) {
        T next = it.next();
        if (!it.hasNext()) {
            return next;
        }
        StringBuilder append = new StringBuilder("expected one element but was: <").append(next);
        for (int i = 0; i < 4 && it.hasNext(); i++) {
            append.append(", ").append(it.next());
        }
        if (it.hasNext()) {
            append.append(", ...");
        }
        append.append(Typography.greater);
        throw new IllegalArgumentException(append.toString());
    }

    @ParametricNullness
    public static <T> T getOnlyElement(Iterator<? extends T> it, @ParametricNullness T t) {
        return it.hasNext() ? getOnlyElement(it) : t;
    }

    public static <T> T[] toArray(Iterator<? extends T> it, Class<T> cls) {
        return Iterables.toArray(Lists.newArrayList(it), cls);
    }

    public static <T> boolean addAll(Collection<T> collection, Iterator<? extends T> it) {
        Preconditions.checkNotNull(collection);
        Preconditions.checkNotNull(it);
        boolean z = false;
        while (it.hasNext()) {
            z |= collection.add(it.next());
        }
        return z;
    }

    public static int frequency(Iterator<?> it, @CheckForNull Object obj) {
        int i = 0;
        while (contains(it, obj)) {
            i++;
        }
        return i;
    }

    public static <T> Iterator<T> cycle(final Iterable<T> iterable) {
        Preconditions.checkNotNull(iterable);
        return new Iterator<T>() {
            Iterator<T> iterator = Iterators.emptyModifiableIterator();

            public boolean hasNext() {
                return this.iterator.hasNext() || iterable.iterator().hasNext();
            }

            @ParametricNullness
            public T next() {
                if (!this.iterator.hasNext()) {
                    Iterator<T> it = iterable.iterator();
                    this.iterator = it;
                    if (!it.hasNext()) {
                        throw new NoSuchElementException();
                    }
                }
                return this.iterator.next();
            }

            public void remove() {
                this.iterator.remove();
            }
        };
    }

    @SafeVarargs
    public static <T> Iterator<T> cycle(T... tArr) {
        return cycle(Lists.newArrayList((E[]) tArr));
    }

    private static <I extends Iterator<?>> Iterator<I> consumingForArray(final I... iArr) {
        return new UnmodifiableIterator<I>() {
            int index = 0;

            public boolean hasNext() {
                return this.index < iArr.length;
            }

            public I next() {
                if (hasNext()) {
                    I i = (Iterator) Objects.requireNonNull(iArr[this.index]);
                    Iterator[] itArr = iArr;
                    int i2 = this.index;
                    itArr[i2] = null;
                    this.index = i2 + 1;
                    return i;
                }
                throw new NoSuchElementException();
            }
        };
    }

    public static <T> Iterator<T> concat(Iterator<? extends T> it, Iterator<? extends T> it2) {
        Preconditions.checkNotNull(it);
        Preconditions.checkNotNull(it2);
        return concat(consumingForArray(it, it2));
    }

    public static <T> Iterator<T> concat(Iterator<? extends T> it, Iterator<? extends T> it2, Iterator<? extends T> it3) {
        Preconditions.checkNotNull(it);
        Preconditions.checkNotNull(it2);
        Preconditions.checkNotNull(it3);
        return concat(consumingForArray(it, it2, it3));
    }

    public static <T> Iterator<T> concat(Iterator<? extends T> it, Iterator<? extends T> it2, Iterator<? extends T> it3, Iterator<? extends T> it4) {
        Preconditions.checkNotNull(it);
        Preconditions.checkNotNull(it2);
        Preconditions.checkNotNull(it3);
        Preconditions.checkNotNull(it4);
        return concat(consumingForArray(it, it2, it3, it4));
    }

    @SafeVarargs
    public static <T> Iterator<T> concat(Iterator<? extends T>... itArr) {
        return concatNoDefensiveCopy((Iterator[]) Arrays.copyOf(itArr, itArr.length));
    }

    public static <T> Iterator<T> concat(Iterator<? extends Iterator<? extends T>> it) {
        return new ConcatenatedIterator(it);
    }

    static <T> Iterator<T> concatNoDefensiveCopy(Iterator<? extends T>... itArr) {
        for (Iterator checkNotNull : (Iterator[]) Preconditions.checkNotNull(itArr)) {
            Preconditions.checkNotNull(checkNotNull);
        }
        return concat(consumingForArray(itArr));
    }

    public static <T> UnmodifiableIterator<List<T>> partition(Iterator<T> it, int i) {
        return partitionImpl(it, i, false);
    }

    public static <T> UnmodifiableIterator<List<T>> paddedPartition(Iterator<T> it, int i) {
        return partitionImpl(it, i, true);
    }

    private static <T> UnmodifiableIterator<List<T>> partitionImpl(final Iterator<T> it, final int i, final boolean z) {
        Preconditions.checkNotNull(it);
        Preconditions.checkArgument(i > 0);
        return new UnmodifiableIterator<List<T>>() {
            public boolean hasNext() {
                return it.hasNext();
            }

            public List<T> next() {
                if (hasNext()) {
                    Object[] objArr = new Object[i];
                    int i = 0;
                    while (i < i && it.hasNext()) {
                        objArr[i] = it.next();
                        i++;
                    }
                    for (int i2 = i; i2 < i; i2++) {
                        objArr[i2] = null;
                    }
                    List<T> unmodifiableList = Collections.unmodifiableList(Arrays.asList(objArr));
                    if (z || i == i) {
                        return unmodifiableList;
                    }
                    return unmodifiableList.subList(0, i);
                }
                throw new NoSuchElementException();
            }
        };
    }

    public static <T> UnmodifiableIterator<T> filter(final Iterator<T> it, final Predicate<? super T> predicate) {
        Preconditions.checkNotNull(it);
        Preconditions.checkNotNull(predicate);
        return new AbstractIterator<T>() {
            /* access modifiers changed from: protected */
            @CheckForNull
            public T computeNext() {
                while (it.hasNext()) {
                    T next = it.next();
                    if (predicate.apply(next)) {
                        return next;
                    }
                }
                return endOfData();
            }
        };
    }

    public static <T> UnmodifiableIterator<T> filter(Iterator<?> it, Class<T> cls) {
        return filter(it, Predicates.instanceOf(cls));
    }

    public static <T> boolean any(Iterator<T> it, Predicate<? super T> predicate) {
        return indexOf(it, predicate) != -1;
    }

    public static <T> boolean all(Iterator<T> it, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(predicate);
        while (it.hasNext()) {
            if (!predicate.apply(it.next())) {
                return false;
            }
        }
        return true;
    }

    @ParametricNullness
    public static <T> T find(Iterator<T> it, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(it);
        Preconditions.checkNotNull(predicate);
        while (it.hasNext()) {
            T next = it.next();
            if (predicate.apply(next)) {
                return next;
            }
        }
        throw new NoSuchElementException();
    }

    @CheckForNull
    public static <T> T find(Iterator<? extends T> it, Predicate<? super T> predicate, @CheckForNull T t) {
        Preconditions.checkNotNull(it);
        Preconditions.checkNotNull(predicate);
        while (it.hasNext()) {
            T next = it.next();
            if (predicate.apply(next)) {
                return next;
            }
        }
        return t;
    }

    public static <T> Optional<T> tryFind(Iterator<T> it, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(it);
        Preconditions.checkNotNull(predicate);
        while (it.hasNext()) {
            T next = it.next();
            if (predicate.apply(next)) {
                return Optional.of(next);
            }
        }
        return Optional.absent();
    }

    public static <T> int indexOf(Iterator<T> it, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(predicate, "predicate");
        int i = 0;
        while (it.hasNext()) {
            if (predicate.apply(it.next())) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static <F, T> Iterator<T> transform(Iterator<F> it, final Function<? super F, ? extends T> function) {
        Preconditions.checkNotNull(function);
        return new TransformedIterator<F, T>(it) {
            /* access modifiers changed from: package-private */
            @ParametricNullness
            public T transform(@ParametricNullness F f) {
                return function.apply(f);
            }
        };
    }

    @ParametricNullness
    public static <T> T get(Iterator<T> it, int i) {
        checkNonnegative(i);
        int advance = advance(it, i);
        if (it.hasNext()) {
            return it.next();
        }
        throw new IndexOutOfBoundsException("position (" + i + ") must be less than the number of elements that remained (" + advance + ")");
    }

    @ParametricNullness
    public static <T> T get(Iterator<? extends T> it, int i, @ParametricNullness T t) {
        checkNonnegative(i);
        advance(it, i);
        return getNext(it, t);
    }

    static void checkNonnegative(int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("position (" + i + ") must not be negative");
        }
    }

    @ParametricNullness
    public static <T> T getNext(Iterator<? extends T> it, @ParametricNullness T t) {
        return it.hasNext() ? it.next() : t;
    }

    @ParametricNullness
    public static <T> T getLast(Iterator<T> it) {
        T next;
        do {
            next = it.next();
        } while (it.hasNext());
        return next;
    }

    @ParametricNullness
    public static <T> T getLast(Iterator<? extends T> it, @ParametricNullness T t) {
        return it.hasNext() ? getLast(it) : t;
    }

    public static int advance(Iterator<?> it, int i) {
        Preconditions.checkNotNull(it);
        int i2 = 0;
        Preconditions.checkArgument(i >= 0, "numberToAdvance must be nonnegative");
        while (i2 < i && it.hasNext()) {
            it.next();
            i2++;
        }
        return i2;
    }

    public static <T> Iterator<T> limit(final Iterator<T> it, final int i) {
        Preconditions.checkNotNull(it);
        Preconditions.checkArgument(i >= 0, "limit is negative");
        return new Iterator<T>() {
            private int count;

            public boolean hasNext() {
                return this.count < i && it.hasNext();
            }

            @ParametricNullness
            public T next() {
                if (hasNext()) {
                    this.count++;
                    return it.next();
                }
                throw new NoSuchElementException();
            }

            public void remove() {
                it.remove();
            }
        };
    }

    public static <T> Iterator<T> consumingIterator(final Iterator<T> it) {
        Preconditions.checkNotNull(it);
        return new UnmodifiableIterator<T>() {
            public String toString() {
                return "Iterators.consumingIterator(...)";
            }

            public boolean hasNext() {
                return it.hasNext();
            }

            @ParametricNullness
            public T next() {
                T next = it.next();
                it.remove();
                return next;
            }
        };
    }

    @CheckForNull
    static <T> T pollNext(Iterator<T> it) {
        if (!it.hasNext()) {
            return null;
        }
        T next = it.next();
        it.remove();
        return next;
    }

    static void clear(Iterator<?> it) {
        Preconditions.checkNotNull(it);
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    @SafeVarargs
    public static <T> UnmodifiableIterator<T> forArray(T... tArr) {
        return forArrayWithPosition(tArr, 0);
    }

    static <T> UnmodifiableListIterator<T> forArrayWithPosition(T[] tArr, int i) {
        if (tArr.length != 0) {
            return new ArrayItr(tArr, i);
        }
        Preconditions.checkPositionIndex(i, tArr.length);
        return emptyListIterator();
    }

    private static final class ArrayItr<T> extends AbstractIndexedListIterator<T> {
        static final UnmodifiableListIterator<Object> EMPTY = new ArrayItr(new Object[0], 0);
        private final T[] array;

        ArrayItr(T[] tArr, int i) {
            super(tArr.length, i);
            this.array = tArr;
        }

        /* access modifiers changed from: protected */
        @ParametricNullness
        public T get(int i) {
            return this.array[i];
        }
    }

    public static <T> UnmodifiableIterator<T> singletonIterator(@ParametricNullness T t) {
        return new SingletonIterator(t);
    }

    private static final class SingletonIterator<T> extends UnmodifiableIterator<T> {
        private boolean done;
        private final T value;

        SingletonIterator(T t) {
            this.value = t;
        }

        public boolean hasNext() {
            return !this.done;
        }

        @ParametricNullness
        public T next() {
            if (!this.done) {
                this.done = true;
                return this.value;
            }
            throw new NoSuchElementException();
        }
    }

    public static <T> UnmodifiableIterator<T> forEnumeration(final Enumeration<T> enumeration) {
        Preconditions.checkNotNull(enumeration);
        return new UnmodifiableIterator<T>() {
            public boolean hasNext() {
                return enumeration.hasMoreElements();
            }

            @ParametricNullness
            public T next() {
                return enumeration.nextElement();
            }
        };
    }

    public static <T> Enumeration<T> asEnumeration(final Iterator<T> it) {
        Preconditions.checkNotNull(it);
        return new Enumeration<T>() {
            public boolean hasMoreElements() {
                return it.hasNext();
            }

            @ParametricNullness
            public T nextElement() {
                return it.next();
            }
        };
    }

    private static class PeekingImpl<E> implements PeekingIterator<E> {
        private boolean hasPeeked;
        private final Iterator<? extends E> iterator;
        @CheckForNull
        private E peekedElement;

        public PeekingImpl(Iterator<? extends E> it) {
            this.iterator = (Iterator) Preconditions.checkNotNull(it);
        }

        public boolean hasNext() {
            return this.hasPeeked || this.iterator.hasNext();
        }

        @ParametricNullness
        public E next() {
            if (!this.hasPeeked) {
                return this.iterator.next();
            }
            E uncheckedCastNullableTToT = NullnessCasts.uncheckedCastNullableTToT(this.peekedElement);
            this.hasPeeked = false;
            this.peekedElement = null;
            return uncheckedCastNullableTToT;
        }

        public void remove() {
            Preconditions.checkState(!this.hasPeeked, "Can't remove after you've peeked at next");
            this.iterator.remove();
        }

        @ParametricNullness
        public E peek() {
            if (!this.hasPeeked) {
                this.peekedElement = this.iterator.next();
                this.hasPeeked = true;
            }
            return NullnessCasts.uncheckedCastNullableTToT(this.peekedElement);
        }
    }

    public static <T> PeekingIterator<T> peekingIterator(Iterator<? extends T> it) {
        if (it instanceof PeekingImpl) {
            return (PeekingImpl) it;
        }
        return new PeekingImpl(it);
    }

    @Deprecated
    public static <T> PeekingIterator<T> peekingIterator(PeekingIterator<T> peekingIterator) {
        return (PeekingIterator) Preconditions.checkNotNull(peekingIterator);
    }

    public static <T> UnmodifiableIterator<T> mergeSorted(Iterable<? extends Iterator<? extends T>> iterable, Comparator<? super T> comparator) {
        Preconditions.checkNotNull(iterable, "iterators");
        Preconditions.checkNotNull(comparator, "comparator");
        return new MergingIterator(iterable, comparator);
    }

    private static class MergingIterator<T> extends UnmodifiableIterator<T> {
        final Queue<PeekingIterator<T>> queue;

        public MergingIterator(Iterable<? extends Iterator<? extends T>> iterable, Comparator<? super T> comparator) {
            this.queue = new PriorityQueue(2, new Iterators$MergingIterator$$ExternalSyntheticLambda0(comparator));
            for (Iterator it : iterable) {
                if (it.hasNext()) {
                    this.queue.add(Iterators.peekingIterator(it));
                }
            }
        }

        public boolean hasNext() {
            return !this.queue.isEmpty();
        }

        @ParametricNullness
        public T next() {
            PeekingIterator remove = this.queue.remove();
            T next = remove.next();
            if (remove.hasNext()) {
                this.queue.add(remove);
            }
            return next;
        }
    }

    private static class ConcatenatedIterator<T> implements Iterator<T> {
        private Iterator<? extends T> iterator = Iterators.emptyIterator();
        @CheckForNull
        private Deque<Iterator<? extends Iterator<? extends T>>> metaIterators;
        @CheckForNull
        private Iterator<? extends T> toRemove;
        @CheckForNull
        private Iterator<? extends Iterator<? extends T>> topMetaIterator;

        ConcatenatedIterator(Iterator<? extends Iterator<? extends T>> it) {
            this.topMetaIterator = (Iterator) Preconditions.checkNotNull(it);
        }

        @CheckForNull
        private Iterator<? extends Iterator<? extends T>> getTopMetaIterator() {
            while (true) {
                Iterator<? extends Iterator<? extends T>> it = this.topMetaIterator;
                if (it != null && it.hasNext()) {
                    return this.topMetaIterator;
                }
                Deque<Iterator<? extends Iterator<? extends T>>> deque = this.metaIterators;
                if (deque == null || deque.isEmpty()) {
                    return null;
                }
                this.topMetaIterator = this.metaIterators.removeFirst();
            }
        }

        public boolean hasNext() {
            while (!((Iterator) Preconditions.checkNotNull(this.iterator)).hasNext()) {
                Iterator<? extends Iterator<? extends T>> topMetaIterator2 = getTopMetaIterator();
                this.topMetaIterator = topMetaIterator2;
                if (topMetaIterator2 == null) {
                    return false;
                }
                Iterator<? extends T> it = (Iterator) topMetaIterator2.next();
                this.iterator = it;
                if (it instanceof ConcatenatedIterator) {
                    ConcatenatedIterator concatenatedIterator = (ConcatenatedIterator) it;
                    this.iterator = concatenatedIterator.iterator;
                    if (this.metaIterators == null) {
                        this.metaIterators = new ArrayDeque();
                    }
                    this.metaIterators.addFirst(this.topMetaIterator);
                    if (concatenatedIterator.metaIterators != null) {
                        while (!concatenatedIterator.metaIterators.isEmpty()) {
                            this.metaIterators.addFirst(concatenatedIterator.metaIterators.removeLast());
                        }
                    }
                    this.topMetaIterator = concatenatedIterator.topMetaIterator;
                }
            }
            return true;
        }

        @ParametricNullness
        public T next() {
            if (hasNext()) {
                Iterator<? extends T> it = this.iterator;
                this.toRemove = it;
                return it.next();
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            Iterator<? extends T> it = this.toRemove;
            if (it != null) {
                it.remove();
                this.toRemove = null;
                return;
            }
            throw new IllegalStateException("no calls to next() since the last call to remove()");
        }
    }
}
