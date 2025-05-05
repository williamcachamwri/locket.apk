package com.google.common.graph;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.errorprone.annotations.Immutable;
import java.util.Comparator;
import java.util.Map;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@Immutable
public final class ElementOrder<T> {
    @CheckForNull
    private final Comparator<T> comparator;
    private final Type type;

    public enum Type {
        UNORDERED,
        STABLE,
        INSERTION,
        SORTED
    }

    /* access modifiers changed from: package-private */
    public <T1 extends T> ElementOrder<T1> cast() {
        return this;
    }

    private ElementOrder(Type type2, @CheckForNull Comparator<T> comparator2) {
        this.type = (Type) Preconditions.checkNotNull(type2);
        this.comparator = comparator2;
        Preconditions.checkState((type2 == Type.SORTED) != (comparator2 != null) ? false : true);
    }

    public static <S> ElementOrder<S> unordered() {
        return new ElementOrder<>(Type.UNORDERED, (Comparator) null);
    }

    public static <S> ElementOrder<S> stable() {
        return new ElementOrder<>(Type.STABLE, (Comparator) null);
    }

    public static <S> ElementOrder<S> insertion() {
        return new ElementOrder<>(Type.INSERTION, (Comparator) null);
    }

    public static <S extends Comparable<? super S>> ElementOrder<S> natural() {
        return new ElementOrder<>(Type.SORTED, Ordering.natural());
    }

    public static <S> ElementOrder<S> sorted(Comparator<S> comparator2) {
        return new ElementOrder<>(Type.SORTED, (Comparator) Preconditions.checkNotNull(comparator2));
    }

    public Type type() {
        return this.type;
    }

    public Comparator<T> comparator() {
        Comparator<T> comparator2 = this.comparator;
        if (comparator2 != null) {
            return comparator2;
        }
        throw new UnsupportedOperationException("This ordering does not define a comparator.");
    }

    public boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ElementOrder)) {
            return false;
        }
        ElementOrder elementOrder = (ElementOrder) obj;
        if (this.type != elementOrder.type || !Objects.equal(this.comparator, elementOrder.comparator)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(this.type, this.comparator);
    }

    public String toString() {
        MoreObjects.ToStringHelper add = MoreObjects.toStringHelper((Object) this).add("type", (Object) this.type);
        Comparator<T> comparator2 = this.comparator;
        if (comparator2 != null) {
            add.add("comparator", (Object) comparator2);
        }
        return add.toString();
    }

    /* access modifiers changed from: package-private */
    public <K extends T, V> Map<K, V> createMap(int i) {
        int ordinal = this.type.ordinal();
        if (ordinal == 0) {
            return Maps.newHashMapWithExpectedSize(i);
        }
        if (ordinal == 1 || ordinal == 2) {
            return Maps.newLinkedHashMapWithExpectedSize(i);
        }
        if (ordinal == 3) {
            return Maps.newTreeMap(comparator());
        }
        throw new AssertionError();
    }
}
