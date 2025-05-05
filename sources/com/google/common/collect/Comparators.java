package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;

@ElementTypesAreNonnullByDefault
public final class Comparators {
    private Comparators() {
    }

    public static <T, S extends T> Comparator<Iterable<S>> lexicographical(Comparator<T> comparator) {
        return new LexicographicalOrdering((Comparator) Preconditions.checkNotNull(comparator));
    }

    public static <T> boolean isInOrder(Iterable<? extends T> iterable, Comparator<T> comparator) {
        Preconditions.checkNotNull(comparator);
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            return true;
        }
        Object next = it.next();
        while (it.hasNext()) {
            Object next2 = it.next();
            if (comparator.compare(next, next2) > 0) {
                return false;
            }
            next = next2;
        }
        return true;
    }

    public static <T> boolean isInStrictOrder(Iterable<? extends T> iterable, Comparator<T> comparator) {
        Preconditions.checkNotNull(comparator);
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            return true;
        }
        Object next = it.next();
        while (it.hasNext()) {
            Object next2 = it.next();
            if (comparator.compare(next, next2) >= 0) {
                return false;
            }
            next = next2;
        }
        return true;
    }

    public static <T> Collector<T, ?, List<T>> least(int i, Comparator<? super T> comparator) {
        CollectPreconditions.checkNonnegative(i, "k");
        Preconditions.checkNotNull(comparator);
        return Collector.of(new Comparators$$ExternalSyntheticLambda0(i, comparator), new Comparators$$ExternalSyntheticLambda1(), new Comparators$$ExternalSyntheticLambda2(), new Comparators$$ExternalSyntheticLambda3(), new Collector.Characteristics[]{Collector.Characteristics.UNORDERED});
    }

    public static <T> Collector<T, ?, List<T>> greatest(int i, Comparator<? super T> comparator) {
        return least(i, comparator.reversed());
    }

    public static <T extends Comparable<? super T>> T min(T t, T t2) {
        return t.compareTo(t2) <= 0 ? t : t2;
    }

    @ParametricNullness
    public static <T> T min(@ParametricNullness T t, @ParametricNullness T t2, Comparator<? super T> comparator) {
        return comparator.compare(t, t2) <= 0 ? t : t2;
    }

    public static <T extends Comparable<? super T>> T max(T t, T t2) {
        return t.compareTo(t2) >= 0 ? t : t2;
    }

    @ParametricNullness
    public static <T> T max(@ParametricNullness T t, @ParametricNullness T t2, Comparator<? super T> comparator) {
        return comparator.compare(t, t2) >= 0 ? t : t2;
    }
}
