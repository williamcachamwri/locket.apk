package com.google.common.collect;

import java.util.Comparator;
import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ImmutableSortedMultiset$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ Comparator f$0;

    public /* synthetic */ ImmutableSortedMultiset$$ExternalSyntheticLambda1(Comparator comparator) {
        this.f$0 = comparator;
    }

    public final Object get() {
        return TreeMultiset.create(this.f$0);
    }
}
