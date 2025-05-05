package com.google.common.collect;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.ToIntFunction;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ImmutableSortedMultiset$$ExternalSyntheticLambda2 implements BiConsumer {
    public final /* synthetic */ Function f$0;
    public final /* synthetic */ ToIntFunction f$1;

    public /* synthetic */ ImmutableSortedMultiset$$ExternalSyntheticLambda2(Function function, ToIntFunction toIntFunction) {
        this.f$0 = function;
        this.f$1 = toIntFunction;
    }

    public final void accept(Object obj, Object obj2) {
        ImmutableSortedMultiset.mapAndAdd(obj2, (Multiset) obj, this.f$0, this.f$1);
    }
}
