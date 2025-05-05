package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.MultimapBuilder;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
final class CollectCollectors {
    private static final Collector<Object, ?, ImmutableList<Object>> TO_IMMUTABLE_LIST = Collector.of(new CollectCollectors$$ExternalSyntheticLambda18(), new CollectCollectors$$ExternalSyntheticLambda21(), new CollectCollectors$$ExternalSyntheticLambda23(), new CollectCollectors$$ExternalSyntheticLambda24(), new Collector.Characteristics[0]);
    private static final Collector<Range<Comparable<?>>, ?, ImmutableRangeSet<Comparable<?>>> TO_IMMUTABLE_RANGE_SET = Collector.of(new CollectCollectors$$ExternalSyntheticLambda29(), new CollectCollectors$$ExternalSyntheticLambda30(), new CollectCollectors$$ExternalSyntheticLambda19(), new CollectCollectors$$ExternalSyntheticLambda20(), new Collector.Characteristics[0]);
    private static final Collector<Object, ?, ImmutableSet<Object>> TO_IMMUTABLE_SET = Collector.of(new CollectCollectors$$ExternalSyntheticLambda25(), new CollectCollectors$$ExternalSyntheticLambda26(), new CollectCollectors$$ExternalSyntheticLambda27(), new CollectCollectors$$ExternalSyntheticLambda28(), new Collector.Characteristics[0]);

    public static /* synthetic */ LinkedHashMap $r8$lambda$Rr5wHpoNM5boD4b5K8greFBFLF8() {
        return new LinkedHashMap();
    }

    static <E> Collector<E, ?, ImmutableList<E>> toImmutableList() {
        return TO_IMMUTABLE_LIST;
    }

    static <E> Collector<E, ?, ImmutableSet<E>> toImmutableSet() {
        return TO_IMMUTABLE_SET;
    }

    static <E> Collector<E, ?, ImmutableSortedSet<E>> toImmutableSortedSet(Comparator<? super E> comparator) {
        Preconditions.checkNotNull(comparator);
        return Collector.of(new CollectCollectors$$ExternalSyntheticLambda65(comparator), new CollectCollectors$$ExternalSyntheticLambda67(), new CollectCollectors$$ExternalSyntheticLambda68(), new CollectCollectors$$ExternalSyntheticLambda69(), new Collector.Characteristics[0]);
    }

    static /* synthetic */ ImmutableSortedSet.Builder lambda$toImmutableSortedSet$0(Comparator comparator) {
        return new ImmutableSortedSet.Builder(comparator);
    }

    static <E extends Enum<E>> Collector<E, ?, ImmutableSet<E>> toImmutableEnumSet() {
        return EnumSetAccumulator.TO_IMMUTABLE_ENUM_SET;
    }

    /* access modifiers changed from: private */
    public static <E extends Enum<E>> Collector<E, EnumSetAccumulator<E>, ImmutableSet<E>> toImmutableEnumSetGeneric() {
        return Collector.of(new CollectCollectors$$ExternalSyntheticLambda46(), new CollectCollectors$$ExternalSyntheticLambda47(), new CollectCollectors$$ExternalSyntheticLambda48(), new CollectCollectors$$ExternalSyntheticLambda49(), new Collector.Characteristics[]{Collector.Characteristics.UNORDERED});
    }

    static /* synthetic */ EnumSetAccumulator lambda$toImmutableEnumSetGeneric$1() {
        return new EnumSetAccumulator();
    }

    private static final class EnumSetAccumulator<E extends Enum<E>> {
        static final Collector<Enum<?>, ?, ImmutableSet<? extends Enum<?>>> TO_IMMUTABLE_ENUM_SET = CollectCollectors.toImmutableEnumSetGeneric();
        @CheckForNull
        private EnumSet<E> set;

        private EnumSetAccumulator() {
        }

        /* access modifiers changed from: package-private */
        public void add(E e) {
            EnumSet<E> enumSet = this.set;
            if (enumSet == null) {
                this.set = EnumSet.of(e);
            } else {
                enumSet.add(e);
            }
        }

        /* access modifiers changed from: package-private */
        public EnumSetAccumulator<E> combine(EnumSetAccumulator<E> enumSetAccumulator) {
            EnumSet<E> enumSet = this.set;
            if (enumSet == null) {
                return enumSetAccumulator;
            }
            EnumSet<E> enumSet2 = enumSetAccumulator.set;
            if (enumSet2 == null) {
                return this;
            }
            enumSet.addAll(enumSet2);
            return this;
        }

        /* access modifiers changed from: package-private */
        public ImmutableSet<E> toImmutableSet() {
            EnumSet<E> enumSet = this.set;
            if (enumSet == null) {
                return ImmutableSet.of();
            }
            ImmutableSet<E> asImmutable = ImmutableEnumSet.asImmutable(enumSet);
            this.set = null;
            return asImmutable;
        }
    }

    static <E extends Comparable<? super E>> Collector<Range<E>, ?, ImmutableRangeSet<E>> toImmutableRangeSet() {
        return TO_IMMUTABLE_RANGE_SET;
    }

    static <T, E> Collector<T, ?, ImmutableMultiset<E>> toImmutableMultiset(Function<? super T, ? extends E> function, ToIntFunction<? super T> toIntFunction) {
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(toIntFunction);
        return Collector.of(new CollectCollectors$$ExternalSyntheticLambda4(), new CollectCollectors$$ExternalSyntheticLambda5(function, toIntFunction), new CollectCollectors$$ExternalSyntheticLambda6(), new CollectCollectors$$ExternalSyntheticLambda7(), new Collector.Characteristics[0]);
    }

    static <T, E, M extends Multiset<E>> Collector<T, ?, M> toMultiset(Function<? super T, E> function, ToIntFunction<? super T> toIntFunction, Supplier<M> supplier) {
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(toIntFunction);
        Preconditions.checkNotNull(supplier);
        return Collector.of(supplier, new CollectCollectors$$ExternalSyntheticLambda0(function, toIntFunction), new CollectCollectors$$ExternalSyntheticLambda11(), new Collector.Characteristics[0]);
    }

    static <T, K, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableMap(Function<? super T, ? extends K> function, Function<? super T, ? extends V> function2) {
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(function2);
        return Collector.of(new CollectCollectors$$ExternalSyntheticLambda50(), new CollectCollectors$$ExternalSyntheticLambda51(function, function2), new CollectCollectors$$ExternalSyntheticLambda52(), new CollectCollectors$$ExternalSyntheticLambda53(), new Collector.Characteristics[0]);
    }

    static <T, K, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableMap(Function<? super T, ? extends K> function, Function<? super T, ? extends V> function2, BinaryOperator<V> binaryOperator) {
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(function2);
        Preconditions.checkNotNull(binaryOperator);
        return Collectors.collectingAndThen(Collectors.toMap(function, function2, binaryOperator, new CollectCollectors$$ExternalSyntheticLambda2()), new CollectCollectors$$ExternalSyntheticLambda3());
    }

    static <T, K, V> Collector<T, ?, ImmutableSortedMap<K, V>> toImmutableSortedMap(Comparator<? super K> comparator, Function<? super T, ? extends K> function, Function<? super T, ? extends V> function2) {
        Preconditions.checkNotNull(comparator);
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(function2);
        return Collector.of(new CollectCollectors$$ExternalSyntheticLambda57(comparator), new CollectCollectors$$ExternalSyntheticLambda58(function, function2), new CollectCollectors$$ExternalSyntheticLambda59(), new CollectCollectors$$ExternalSyntheticLambda60(), new Collector.Characteristics[]{Collector.Characteristics.UNORDERED});
    }

    static /* synthetic */ ImmutableSortedMap.Builder lambda$toImmutableSortedMap$8(Comparator comparator) {
        return new ImmutableSortedMap.Builder(comparator);
    }

    static <T, K, V> Collector<T, ?, ImmutableSortedMap<K, V>> toImmutableSortedMap(Comparator<? super K> comparator, Function<? super T, ? extends K> function, Function<? super T, ? extends V> function2, BinaryOperator<V> binaryOperator) {
        Preconditions.checkNotNull(comparator);
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(function2);
        Preconditions.checkNotNull(binaryOperator);
        return Collectors.collectingAndThen(Collectors.toMap(function, function2, binaryOperator, new CollectCollectors$$ExternalSyntheticLambda22(comparator)), new CollectCollectors$$ExternalSyntheticLambda33());
    }

    static /* synthetic */ TreeMap lambda$toImmutableSortedMap$10(Comparator comparator) {
        return new TreeMap(comparator);
    }

    static <T, K, V> Collector<T, ?, ImmutableBiMap<K, V>> toImmutableBiMap(Function<? super T, ? extends K> function, Function<? super T, ? extends V> function2) {
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(function2);
        return Collector.of(new CollectCollectors$$ExternalSyntheticLambda14(), new CollectCollectors$$ExternalSyntheticLambda15(function, function2), new CollectCollectors$$ExternalSyntheticLambda16(), new CollectCollectors$$ExternalSyntheticLambda17(), new Collector.Characteristics[0]);
    }

    static <T, K extends Enum<K>, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableEnumMap(Function<? super T, ? extends K> function, Function<? super T, ? extends V> function2) {
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(function2);
        return Collector.of(new CollectCollectors$$ExternalSyntheticLambda8(), new CollectCollectors$$ExternalSyntheticLambda9(function, function2), new CollectCollectors$$ExternalSyntheticLambda10(), new CollectCollectors$$ExternalSyntheticLambda12(), new Collector.Characteristics[]{Collector.Characteristics.UNORDERED});
    }

    static /* synthetic */ EnumMapAccumulator lambda$toImmutableEnumMap$13() {
        return new EnumMapAccumulator(new CollectCollectors$$ExternalSyntheticLambda1());
    }

    static /* synthetic */ Object lambda$toImmutableEnumMap$12(Object obj, Object obj2) {
        throw new IllegalArgumentException("Multiple values for key: " + obj + ", " + obj2);
    }

    static <T, K extends Enum<K>, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableEnumMap(Function<? super T, ? extends K> function, Function<? super T, ? extends V> function2, BinaryOperator<V> binaryOperator) {
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(function2);
        Preconditions.checkNotNull(binaryOperator);
        return Collector.of(new CollectCollectors$$ExternalSyntheticLambda54(binaryOperator), new CollectCollectors$$ExternalSyntheticLambda56(function, function2), new CollectCollectors$$ExternalSyntheticLambda10(), new CollectCollectors$$ExternalSyntheticLambda12(), new Collector.Characteristics[0]);
    }

    static /* synthetic */ EnumMapAccumulator lambda$toImmutableEnumMap$15(BinaryOperator binaryOperator) {
        return new EnumMapAccumulator(binaryOperator);
    }

    private static class EnumMapAccumulator<K extends Enum<K>, V> {
        @CheckForNull
        private EnumMap<K, V> map = null;
        private final BinaryOperator<V> mergeFunction;

        EnumMapAccumulator(BinaryOperator<V> binaryOperator) {
            this.mergeFunction = binaryOperator;
        }

        /* access modifiers changed from: package-private */
        public void put(K k, V v) {
            EnumMap<K, V> enumMap = this.map;
            if (enumMap == null) {
                this.map = new EnumMap<>(Collections.singletonMap(k, v));
            } else {
                enumMap.merge(k, v, this.mergeFunction);
            }
        }

        /* access modifiers changed from: package-private */
        public EnumMapAccumulator<K, V> combine(EnumMapAccumulator<K, V> enumMapAccumulator) {
            if (this.map == null) {
                return enumMapAccumulator;
            }
            EnumMap<K, V> enumMap = enumMapAccumulator.map;
            if (enumMap == null) {
                return this;
            }
            enumMap.forEach(new CollectCollectors$EnumMapAccumulator$$ExternalSyntheticLambda0(this));
            return this;
        }

        /* access modifiers changed from: package-private */
        public ImmutableMap<K, V> toImmutableMap() {
            EnumMap<K, V> enumMap = this.map;
            return enumMap == null ? ImmutableMap.of() : ImmutableEnumMap.asImmutable(enumMap);
        }
    }

    static <T, K extends Comparable<? super K>, V> Collector<T, ?, ImmutableRangeMap<K, V>> toImmutableRangeMap(Function<? super T, Range<K>> function, Function<? super T, ? extends V> function2) {
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(function2);
        return Collector.of(new CollectCollectors$$ExternalSyntheticLambda41(), new CollectCollectors$$ExternalSyntheticLambda42(function, function2), new CollectCollectors$$ExternalSyntheticLambda43(), new CollectCollectors$$ExternalSyntheticLambda45(), new Collector.Characteristics[0]);
    }

    static <T, K, V> Collector<T, ?, ImmutableListMultimap<K, V>> toImmutableListMultimap(Function<? super T, ? extends K> function, Function<? super T, ? extends V> function2) {
        Preconditions.checkNotNull(function, "keyFunction");
        Preconditions.checkNotNull(function2, "valueFunction");
        return Collector.of(new CollectCollectors$$ExternalSyntheticLambda61(), new CollectCollectors$$ExternalSyntheticLambda62(function, function2), new CollectCollectors$$ExternalSyntheticLambda63(), new CollectCollectors$$ExternalSyntheticLambda64(), new Collector.Characteristics[0]);
    }

    static <T, K, V> Collector<T, ?, ImmutableListMultimap<K, V>> flatteningToImmutableListMultimap(Function<? super T, ? extends K> function, Function<? super T, ? extends Stream<? extends V>> function2) {
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(function2);
        CollectCollectors$$ExternalSyntheticLambda44 collectCollectors$$ExternalSyntheticLambda44 = new CollectCollectors$$ExternalSyntheticLambda44(function);
        CollectCollectors$$ExternalSyntheticLambda55 collectCollectors$$ExternalSyntheticLambda55 = new CollectCollectors$$ExternalSyntheticLambda55(function2);
        MultimapBuilder.ListMultimapBuilder<Object, Object> arrayListValues = MultimapBuilder.linkedHashKeys().arrayListValues();
        Objects.requireNonNull(arrayListValues);
        return Collectors.collectingAndThen(flatteningToMultimap(collectCollectors$$ExternalSyntheticLambda44, collectCollectors$$ExternalSyntheticLambda55, new CollectCollectors$$ExternalSyntheticLambda66(arrayListValues)), new CollectCollectors$$ExternalSyntheticLambda72());
    }

    static <T, K, V> Collector<T, ?, ImmutableSetMultimap<K, V>> toImmutableSetMultimap(Function<? super T, ? extends K> function, Function<? super T, ? extends V> function2) {
        Preconditions.checkNotNull(function, "keyFunction");
        Preconditions.checkNotNull(function2, "valueFunction");
        return Collector.of(new CollectCollectors$$ExternalSyntheticLambda36(), new CollectCollectors$$ExternalSyntheticLambda37(function, function2), new CollectCollectors$$ExternalSyntheticLambda38(), new CollectCollectors$$ExternalSyntheticLambda39(), new Collector.Characteristics[0]);
    }

    static <T, K, V> Collector<T, ?, ImmutableSetMultimap<K, V>> flatteningToImmutableSetMultimap(Function<? super T, ? extends K> function, Function<? super T, ? extends Stream<? extends V>> function2) {
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(function2);
        CollectCollectors$$ExternalSyntheticLambda31 collectCollectors$$ExternalSyntheticLambda31 = new CollectCollectors$$ExternalSyntheticLambda31(function);
        CollectCollectors$$ExternalSyntheticLambda32 collectCollectors$$ExternalSyntheticLambda32 = new CollectCollectors$$ExternalSyntheticLambda32(function2);
        MultimapBuilder.SetMultimapBuilder<Object, Object> linkedHashSetValues = MultimapBuilder.linkedHashKeys().linkedHashSetValues();
        Objects.requireNonNull(linkedHashSetValues);
        return Collectors.collectingAndThen(flatteningToMultimap(collectCollectors$$ExternalSyntheticLambda31, collectCollectors$$ExternalSyntheticLambda32, new CollectCollectors$$ExternalSyntheticLambda34(linkedHashSetValues)), new CollectCollectors$$ExternalSyntheticLambda35());
    }

    static <T, K, V, M extends Multimap<K, V>> Collector<T, ?, M> toMultimap(Function<? super T, ? extends K> function, Function<? super T, ? extends V> function2, Supplier<M> supplier) {
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(function2);
        Preconditions.checkNotNull(supplier);
        return Collector.of(supplier, new CollectCollectors$$ExternalSyntheticLambda70(function, function2), new CollectCollectors$$ExternalSyntheticLambda71(), new Collector.Characteristics[0]);
    }

    static <T, K, V, M extends Multimap<K, V>> Collector<T, ?, M> flatteningToMultimap(Function<? super T, ? extends K> function, Function<? super T, ? extends Stream<? extends V>> function2, Supplier<M> supplier) {
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(function2);
        Preconditions.checkNotNull(supplier);
        return Collector.of(supplier, new CollectCollectors$$ExternalSyntheticLambda73(function, function2), new CollectCollectors$$ExternalSyntheticLambda74(), new Collector.Characteristics[0]);
    }

    static /* synthetic */ void lambda$flatteningToMultimap$26(Function function, Function function2, Multimap multimap, Object obj) {
        Collection collection = multimap.get(function.apply(obj));
        Objects.requireNonNull(collection);
        ((Stream) function2.apply(obj)).forEachOrdered(new CollectCollectors$$ExternalSyntheticLambda40(collection));
    }

    private CollectCollectors() {
    }
}
