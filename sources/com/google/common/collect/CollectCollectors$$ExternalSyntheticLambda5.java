package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.ToIntFunction;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CollectCollectors$$ExternalSyntheticLambda5 implements BiConsumer {
    public final /* synthetic */ Function f$0;
    public final /* synthetic */ ToIntFunction f$1;

    public /* synthetic */ CollectCollectors$$ExternalSyntheticLambda5(Function function, ToIntFunction toIntFunction) {
        this.f$0 = function;
        this.f$1 = toIntFunction;
    }

    public final void accept(Object obj, Object obj2) {
        ((Multiset) obj).add(Preconditions.checkNotNull(this.f$0.apply(obj2)), this.f$1.applyAsInt(obj2));
    }
}
