package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
final class NaturalOrdering extends Ordering<Comparable<?>> implements Serializable {
    static final NaturalOrdering INSTANCE = new NaturalOrdering();
    private static final long serialVersionUID = 0;
    @CheckForNull
    @LazyInit
    private transient Ordering<Comparable<?>> nullsFirst;
    @CheckForNull
    @LazyInit
    private transient Ordering<Comparable<?>> nullsLast;

    public String toString() {
        return "Ordering.natural()";
    }

    public int compare(Comparable<?> comparable, Comparable<?> comparable2) {
        Preconditions.checkNotNull(comparable);
        Preconditions.checkNotNull(comparable2);
        return comparable.compareTo(comparable2);
    }

    public <S extends Comparable<?>> Ordering<S> nullsFirst() {
        Ordering<Comparable<?>> ordering = this.nullsFirst;
        if (ordering != null) {
            return ordering;
        }
        Ordering<Comparable<?>> nullsFirst2 = super.nullsFirst();
        this.nullsFirst = nullsFirst2;
        return nullsFirst2;
    }

    public <S extends Comparable<?>> Ordering<S> nullsLast() {
        Ordering<Comparable<?>> ordering = this.nullsLast;
        if (ordering != null) {
            return ordering;
        }
        Ordering<Comparable<?>> nullsLast2 = super.nullsLast();
        this.nullsLast = nullsLast2;
        return nullsLast2;
    }

    public <S extends Comparable<?>> Ordering<S> reverse() {
        return ReverseNaturalOrdering.INSTANCE;
    }

    private Object readResolve() {
        return INSTANCE;
    }

    private NaturalOrdering() {
    }
}
