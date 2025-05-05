package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.function.Function;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CollectCollectors$$ExternalSyntheticLambda44 implements Function {
    public final /* synthetic */ Function f$0;

    public /* synthetic */ CollectCollectors$$ExternalSyntheticLambda44(Function function) {
        this.f$0 = function;
    }

    public final Object apply(Object obj) {
        return Preconditions.checkNotNull(this.f$0.apply(obj));
    }
}
