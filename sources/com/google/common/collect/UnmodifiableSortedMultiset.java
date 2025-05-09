package com.google.common.collect;

import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.Comparator;
import java.util.NavigableSet;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
final class UnmodifiableSortedMultiset<E> extends Multisets.UnmodifiableMultiset<E> implements SortedMultiset<E> {
    private static final long serialVersionUID = 0;
    @CheckForNull
    @LazyInit
    private transient UnmodifiableSortedMultiset<E> descendingMultiset;

    UnmodifiableSortedMultiset(SortedMultiset<E> sortedMultiset) {
        super(sortedMultiset);
    }

    /* access modifiers changed from: protected */
    public SortedMultiset<E> delegate() {
        return (SortedMultiset) super.delegate();
    }

    public Comparator<? super E> comparator() {
        return delegate().comparator();
    }

    /* access modifiers changed from: package-private */
    public NavigableSet<E> createElementSet() {
        return Sets.unmodifiableNavigableSet(delegate().elementSet());
    }

    public NavigableSet<E> elementSet() {
        return (NavigableSet) super.elementSet();
    }

    public SortedMultiset<E> descendingMultiset() {
        UnmodifiableSortedMultiset<E> unmodifiableSortedMultiset = this.descendingMultiset;
        if (unmodifiableSortedMultiset != null) {
            return unmodifiableSortedMultiset;
        }
        UnmodifiableSortedMultiset<E> unmodifiableSortedMultiset2 = new UnmodifiableSortedMultiset<>(delegate().descendingMultiset());
        unmodifiableSortedMultiset2.descendingMultiset = this;
        this.descendingMultiset = unmodifiableSortedMultiset2;
        return unmodifiableSortedMultiset2;
    }

    @CheckForNull
    public Multiset.Entry<E> firstEntry() {
        return delegate().firstEntry();
    }

    @CheckForNull
    public Multiset.Entry<E> lastEntry() {
        return delegate().lastEntry();
    }

    @CheckForNull
    public Multiset.Entry<E> pollFirstEntry() {
        throw new UnsupportedOperationException();
    }

    @CheckForNull
    public Multiset.Entry<E> pollLastEntry() {
        throw new UnsupportedOperationException();
    }

    public SortedMultiset<E> headMultiset(@ParametricNullness E e, BoundType boundType) {
        return Multisets.unmodifiableSortedMultiset(delegate().headMultiset(e, boundType));
    }

    public SortedMultiset<E> subMultiset(@ParametricNullness E e, BoundType boundType, @ParametricNullness E e2, BoundType boundType2) {
        return Multisets.unmodifiableSortedMultiset(delegate().subMultiset(e, boundType, e2, boundType2));
    }

    public SortedMultiset<E> tailMultiset(@ParametricNullness E e, BoundType boundType) {
        return Multisets.unmodifiableSortedMultiset(delegate().tailMultiset(e, boundType));
    }
}
