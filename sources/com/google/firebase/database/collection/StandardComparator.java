package com.google.firebase.database.collection;

import java.lang.Comparable;
import java.util.Comparator;

public class StandardComparator<A extends Comparable<A>> implements Comparator<A> {
    private static StandardComparator INSTANCE = new StandardComparator();

    private StandardComparator() {
    }

    public static <T extends Comparable<T>> StandardComparator<T> getComparator(Class<T> cls) {
        return INSTANCE;
    }

    public int compare(A a2, A a3) {
        return a2.compareTo(a3);
    }
}
