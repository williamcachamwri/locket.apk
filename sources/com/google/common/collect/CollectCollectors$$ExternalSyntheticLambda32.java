package com.google.common.collect;

import java.util.function.Function;
import java.util.stream.Stream;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CollectCollectors$$ExternalSyntheticLambda32 implements Function {
    public final /* synthetic */ Function f$0;

    public /* synthetic */ CollectCollectors$$ExternalSyntheticLambda32(Function function) {
        this.f$0 = function;
    }

    public final Object apply(Object obj) {
        return ((Stream) this.f$0.apply(obj)).peek(new CollectCollectors$$ExternalSyntheticLambda13());
    }
}
