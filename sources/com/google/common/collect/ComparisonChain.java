package com.google.common.collect;

import java.util.Comparator;

@ElementTypesAreNonnullByDefault
public abstract class ComparisonChain {
    /* access modifiers changed from: private */
    public static final ComparisonChain ACTIVE = new ComparisonChain() {
        public int result() {
            return 0;
        }

        public ComparisonChain compare(Comparable<?> comparable, Comparable<?> comparable2) {
            return classify(comparable.compareTo(comparable2));
        }

        public <T> ComparisonChain compare(@ParametricNullness T t, @ParametricNullness T t2, Comparator<T> comparator) {
            return classify(comparator.compare(t, t2));
        }

        public ComparisonChain compare(int i, int i2) {
            return classify(Integer.compare(i, i2));
        }

        public ComparisonChain compare(long j, long j2) {
            return classify(Long.compare(j, j2));
        }

        public ComparisonChain compare(float f, float f2) {
            return classify(Float.compare(f, f2));
        }

        public ComparisonChain compare(double d, double d2) {
            return classify(Double.compare(d, d2));
        }

        public ComparisonChain compareTrueFirst(boolean z, boolean z2) {
            return classify(Boolean.compare(z2, z));
        }

        public ComparisonChain compareFalseFirst(boolean z, boolean z2) {
            return classify(Boolean.compare(z, z2));
        }

        /* access modifiers changed from: package-private */
        public ComparisonChain classify(int i) {
            if (i < 0) {
                return ComparisonChain.LESS;
            }
            return i > 0 ? ComparisonChain.GREATER : ComparisonChain.ACTIVE;
        }
    };
    /* access modifiers changed from: private */
    public static final ComparisonChain GREATER = new InactiveComparisonChain(1);
    /* access modifiers changed from: private */
    public static final ComparisonChain LESS = new InactiveComparisonChain(-1);

    public abstract ComparisonChain compare(double d, double d2);

    public abstract ComparisonChain compare(float f, float f2);

    public abstract ComparisonChain compare(int i, int i2);

    public abstract ComparisonChain compare(long j, long j2);

    public abstract ComparisonChain compare(Comparable<?> comparable, Comparable<?> comparable2);

    public abstract <T> ComparisonChain compare(@ParametricNullness T t, @ParametricNullness T t2, Comparator<T> comparator);

    public abstract ComparisonChain compareFalseFirst(boolean z, boolean z2);

    public abstract ComparisonChain compareTrueFirst(boolean z, boolean z2);

    public abstract int result();

    private ComparisonChain() {
    }

    public static ComparisonChain start() {
        return ACTIVE;
    }

    private static final class InactiveComparisonChain extends ComparisonChain {
        final int result;

        public ComparisonChain compare(double d, double d2) {
            return this;
        }

        public ComparisonChain compare(float f, float f2) {
            return this;
        }

        public ComparisonChain compare(int i, int i2) {
            return this;
        }

        public ComparisonChain compare(long j, long j2) {
            return this;
        }

        public ComparisonChain compare(Comparable<?> comparable, Comparable<?> comparable2) {
            return this;
        }

        public <T> ComparisonChain compare(@ParametricNullness T t, @ParametricNullness T t2, Comparator<T> comparator) {
            return this;
        }

        public ComparisonChain compareFalseFirst(boolean z, boolean z2) {
            return this;
        }

        public ComparisonChain compareTrueFirst(boolean z, boolean z2) {
            return this;
        }

        InactiveComparisonChain(int i) {
            super();
            this.result = i;
        }

        public int result() {
            return this.result;
        }
    }

    @Deprecated
    public final ComparisonChain compare(Boolean bool, Boolean bool2) {
        return compareFalseFirst(bool.booleanValue(), bool2.booleanValue());
    }
}
