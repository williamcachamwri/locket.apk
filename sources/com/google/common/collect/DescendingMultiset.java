package com.google.common.collect;

import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.SortedMultisets;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
abstract class DescendingMultiset<E> extends ForwardingMultiset<E> implements SortedMultiset<E> {
    @CheckForNull
    @LazyInit
    private transient Comparator<? super E> comparator;
    @CheckForNull
    @LazyInit
    private transient NavigableSet<E> elementSet;
    @CheckForNull
    @LazyInit
    private transient Set<Multiset.Entry<E>> entrySet;

    /* access modifiers changed from: package-private */
    public abstract Iterator<Multiset.Entry<E>> entryIterator();

    /* access modifiers changed from: package-private */
    public abstract SortedMultiset<E> forwardMultiset();

    DescendingMultiset() {
    }

    public Comparator<? super E> comparator() {
        Comparator<? super E> comparator2 = this.comparator;
        if (comparator2 != null) {
            return comparator2;
        }
        Ordering reverse = Ordering.from(forwardMultiset().comparator()).reverse();
        this.comparator = reverse;
        return reverse;
    }

    public NavigableSet<E> elementSet() {
        NavigableSet<E> navigableSet = this.elementSet;
        if (navigableSet != null) {
            return navigableSet;
        }
        SortedMultisets.NavigableElementSet navigableElementSet = new SortedMultisets.NavigableElementSet(this);
        this.elementSet = navigableElementSet;
        return navigableElementSet;
    }

    @CheckForNull
    public Multiset.Entry<E> pollFirstEntry() {
        return forwardMultiset().pollLastEntry();
    }

    @CheckForNull
    public Multiset.Entry<E> pollLastEntry() {
        return forwardMultiset().pollFirstEntry();
    }

    public SortedMultiset<E> headMultiset(@ParametricNullness E e, BoundType boundType) {
        return forwardMultiset().tailMultiset(e, boundType).descendingMultiset();
    }

    public SortedMultiset<E> subMultiset(@ParametricNullness E e, BoundType boundType, @ParametricNullness E e2, BoundType boundType2) {
        return forwardMultiset().subMultiset(e2, boundType2, e, boundType).descendingMultiset();
    }

    public SortedMultiset<E> tailMultiset(@ParametricNullness E e, BoundType boundType) {
        return forwardMultiset().headMultiset(e, boundType).descendingMultiset();
    }

    /* access modifiers changed from: protected */
    public Multiset<E> delegate() {
        return forwardMultiset();
    }

    public SortedMultiset<E> descendingMultiset() {
        return forwardMultiset();
    }

    @CheckForNull
    public Multiset.Entry<E> firstEntry() {
        return forwardMultiset().lastEntry();
    }

    @CheckForNull
    public Multiset.Entry<E> lastEntry() {
        return forwardMultiset().firstEntry();
    }

    public Set<Multiset.Entry<E>> entrySet() {
        Set<Multiset.Entry<E>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        Set<Multiset.Entry<E>> createEntrySet = createEntrySet();
        this.entrySet = createEntrySet;
        return createEntrySet;
    }

    /* access modifiers changed from: package-private */
    public Set<Multiset.Entry<E>> createEntrySet() {
        return new Multisets.EntrySet<E>() {
            /* access modifiers changed from: package-private */
            public Multiset<E> multiset() {
                return DescendingMultiset.this;
            }

            public Iterator<Multiset.Entry<E>> iterator() {
                return DescendingMultiset.this.entryIterator();
            }

            public int size() {
                return DescendingMultiset.this.forwardMultiset().entrySet().size();
            }
        };
    }

    public Iterator<E> iterator() {
        return Multisets.iteratorImpl(this);
    }

    public Object[] toArray() {
        return standardToArray();
    }

    public <T> T[] toArray(T[] tArr) {
        return standardToArray(tArr);
    }

    public String toString() {
        return entrySet().toString();
    }
}
