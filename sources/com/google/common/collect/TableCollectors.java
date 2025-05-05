package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

@ElementTypesAreNonnullByDefault
final class TableCollectors {
    static <T, R, C, V> Collector<T, ?, ImmutableTable<R, C, V>> toImmutableTable(Function<? super T, ? extends R> function, Function<? super T, ? extends C> function2, Function<? super T, ? extends V> function3) {
        Preconditions.checkNotNull(function, "rowFunction");
        Preconditions.checkNotNull(function2, "columnFunction");
        Preconditions.checkNotNull(function3, "valueFunction");
        return Collector.of(new TableCollectors$$ExternalSyntheticLambda3(), new TableCollectors$$ExternalSyntheticLambda4(function, function2, function3), new TableCollectors$$ExternalSyntheticLambda5(), new TableCollectors$$ExternalSyntheticLambda6(), new Collector.Characteristics[0]);
    }

    static <T, R, C, V> Collector<T, ?, ImmutableTable<R, C, V>> toImmutableTable(Function<? super T, ? extends R> function, Function<? super T, ? extends C> function2, Function<? super T, ? extends V> function3, BinaryOperator<V> binaryOperator) {
        Preconditions.checkNotNull(function, "rowFunction");
        Preconditions.checkNotNull(function2, "columnFunction");
        Preconditions.checkNotNull(function3, "valueFunction");
        Preconditions.checkNotNull(binaryOperator, "mergeFunction");
        return Collector.of(new TableCollectors$$ExternalSyntheticLambda8(), new TableCollectors$$ExternalSyntheticLambda9(function, function2, function3, binaryOperator), new TableCollectors$$ExternalSyntheticLambda10(binaryOperator), new TableCollectors$$ExternalSyntheticLambda1(), new Collector.Characteristics[0]);
    }

    static /* synthetic */ ImmutableTableCollectorState lambda$toImmutableTable$1() {
        return new ImmutableTableCollectorState();
    }

    static <T, R, C, V, I extends Table<R, C, V>> Collector<T, ?, I> toTable(Function<? super T, ? extends R> function, Function<? super T, ? extends C> function2, Function<? super T, ? extends V> function3, Supplier<I> supplier) {
        return toTable(function, function2, function3, new TableCollectors$$ExternalSyntheticLambda7(), supplier);
    }

    static /* synthetic */ Object lambda$toTable$5(Object obj, Object obj2) {
        throw new IllegalStateException("Conflicting values " + obj + " and " + obj2);
    }

    static <T, R, C, V, I extends Table<R, C, V>> Collector<T, ?, I> toTable(Function<? super T, ? extends R> function, Function<? super T, ? extends C> function2, Function<? super T, ? extends V> function3, BinaryOperator<V> binaryOperator, Supplier<I> supplier) {
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(function2);
        Preconditions.checkNotNull(function3);
        Preconditions.checkNotNull(binaryOperator);
        Preconditions.checkNotNull(supplier);
        return Collector.of(supplier, new TableCollectors$$ExternalSyntheticLambda0(function, function2, function3, binaryOperator), new TableCollectors$$ExternalSyntheticLambda2(binaryOperator), new Collector.Characteristics[0]);
    }

    static /* synthetic */ Table lambda$toTable$7(BinaryOperator binaryOperator, Table table, Table table2) {
        for (Table.Cell cell : table2.cellSet()) {
            mergeTables(table, cell.getRowKey(), cell.getColumnKey(), cell.getValue(), binaryOperator);
        }
        return table;
    }

    private static final class ImmutableTableCollectorState<R, C, V> {
        final List<MutableCell<R, C, V>> insertionOrder;
        final Table<R, C, MutableCell<R, C, V>> table;

        private ImmutableTableCollectorState() {
            this.insertionOrder = new ArrayList();
            this.table = HashBasedTable.create();
        }

        /* access modifiers changed from: package-private */
        public void put(R r, C c, V v, BinaryOperator<V> binaryOperator) {
            MutableCell mutableCell = this.table.get(r, c);
            if (mutableCell == null) {
                MutableCell mutableCell2 = new MutableCell(r, c, v);
                this.insertionOrder.add(mutableCell2);
                this.table.put(r, c, mutableCell2);
                return;
            }
            mutableCell.merge(v, binaryOperator);
        }

        /* access modifiers changed from: package-private */
        public ImmutableTableCollectorState<R, C, V> combine(ImmutableTableCollectorState<R, C, V> immutableTableCollectorState, BinaryOperator<V> binaryOperator) {
            for (MutableCell next : immutableTableCollectorState.insertionOrder) {
                put(next.getRowKey(), next.getColumnKey(), next.getValue(), binaryOperator);
            }
            return this;
        }

        /* access modifiers changed from: package-private */
        public ImmutableTable<R, C, V> toTable() {
            return ImmutableTable.copyOf(this.insertionOrder);
        }
    }

    private static final class MutableCell<R, C, V> extends Tables.AbstractCell<R, C, V> {
        private final C column;
        private final R row;
        private V value;

        MutableCell(R r, C c, V v) {
            this.row = Preconditions.checkNotNull(r, "row");
            this.column = Preconditions.checkNotNull(c, "column");
            this.value = Preconditions.checkNotNull(v, "value");
        }

        public R getRowKey() {
            return this.row;
        }

        public C getColumnKey() {
            return this.column;
        }

        public V getValue() {
            return this.value;
        }

        /* access modifiers changed from: package-private */
        public void merge(V v, BinaryOperator<V> binaryOperator) {
            Preconditions.checkNotNull(v, "value");
            this.value = Preconditions.checkNotNull(binaryOperator.apply(this.value, v), "mergeFunction.apply");
        }
    }

    /* access modifiers changed from: private */
    public static <R, C, V> void mergeTables(Table<R, C, V> table, @ParametricNullness R r, @ParametricNullness C c, V v, BinaryOperator<V> binaryOperator) {
        Preconditions.checkNotNull(v);
        V v2 = table.get(r, c);
        if (v2 == null) {
            table.put(r, c, v);
            return;
        }
        Object apply = binaryOperator.apply(v2, v);
        if (apply == null) {
            table.remove(r, c);
        } else {
            table.put(r, c, apply);
        }
    }

    private TableCollectors() {
    }
}
