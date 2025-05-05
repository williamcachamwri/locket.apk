package com.google.common.collect;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

@ElementTypesAreNonnullByDefault
final class Platform {
    static int reduceExponentIfGwt(int i) {
        return i;
    }

    static int reduceIterationsIfGwt(int i) {
        return i;
    }

    static <K, V> Map<K, V> newHashMapWithExpectedSize(int i) {
        return CompactHashMap.createWithExpectedSize(i);
    }

    static <K, V> Map<K, V> newLinkedHashMapWithExpectedSize(int i) {
        return CompactLinkedHashMap.createWithExpectedSize(i);
    }

    static <E> Set<E> newHashSetWithExpectedSize(int i) {
        return CompactHashSet.createWithExpectedSize(i);
    }

    static <E> Set<E> newLinkedHashSetWithExpectedSize(int i) {
        return CompactLinkedHashSet.createWithExpectedSize(i);
    }

    static <K, V> Map<K, V> preservesInsertionOrderOnPutsMap() {
        return CompactHashMap.create();
    }

    static <K, V> Map<K, V> preservesInsertionOrderOnPutsMapWithExpectedSize(int i) {
        return Maps.newLinkedHashMapWithExpectedSize(i);
    }

    static <E> Set<E> preservesInsertionOrderOnAddsSet() {
        return CompactHashSet.create();
    }

    static <T> T[] newArray(T[] tArr, int i) {
        if (tArr.length != 0) {
            tArr = Arrays.copyOf(tArr, 0);
        }
        return Arrays.copyOf(tArr, i);
    }

    static <T> T[] copy(Object[] objArr, int i, int i2, T[] tArr) {
        return Arrays.copyOfRange(objArr, i, i2, tArr.getClass());
    }

    static MapMaker tryWeakKeys(MapMaker mapMaker) {
        return mapMaker.weakKeys();
    }

    static <E extends Enum<E>> Class<E> getDeclaringClassOrObjectForJ2cl(E e) {
        return e.getDeclaringClass();
    }

    private Platform() {
    }
}
