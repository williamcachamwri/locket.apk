package com.google.common.collect;

import com.google.common.collect.CollectCollectors;
import java.util.function.BiConsumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CollectCollectors$EnumMapAccumulator$$ExternalSyntheticLambda0 implements BiConsumer {
    public final /* synthetic */ CollectCollectors.EnumMapAccumulator f$0;

    public /* synthetic */ CollectCollectors$EnumMapAccumulator$$ExternalSyntheticLambda0(CollectCollectors.EnumMapAccumulator enumMapAccumulator) {
        this.f$0 = enumMapAccumulator;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.put((Enum) obj, obj2);
    }
}
