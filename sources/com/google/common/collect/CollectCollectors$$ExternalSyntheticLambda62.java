package com.google.common.collect;

import com.google.common.collect.ImmutableListMultimap;
import java.util.function.BiConsumer;
import java.util.function.Function;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CollectCollectors$$ExternalSyntheticLambda62 implements BiConsumer {
    public final /* synthetic */ Function f$0;
    public final /* synthetic */ Function f$1;

    public /* synthetic */ CollectCollectors$$ExternalSyntheticLambda62(Function function, Function function2) {
        this.f$0 = function;
        this.f$1 = function2;
    }

    public final void accept(Object obj, Object obj2) {
        ((ImmutableListMultimap.Builder) obj).put(this.f$0.apply(obj2), this.f$1.apply(obj2));
    }
}
