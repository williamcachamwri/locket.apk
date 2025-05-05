package com.google.common.collect;

import java.util.Comparator;
import java.util.function.Function;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ImmutableSortedMultiset$$ExternalSyntheticLambda4 implements Function {
    public final /* synthetic */ Comparator f$0;

    public /* synthetic */ ImmutableSortedMultiset$$ExternalSyntheticLambda4(Comparator comparator) {
        this.f$0 = comparator;
    }

    public final Object apply(Object obj) {
        return ImmutableSortedMultiset.copyOfSortedEntries(this.f$0, ((Multiset) obj).entrySet());
    }
}
