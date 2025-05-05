package com.google.common.graph;

import com.google.common.base.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AbstractNetwork$$ExternalSyntheticLambda4 implements Supplier {
    public final /* synthetic */ Object f$0;

    public /* synthetic */ AbstractNetwork$$ExternalSyntheticLambda4(Object obj) {
        this.f$0 = obj;
    }

    public final Object get() {
        return String.format("Edge %s that was used to generate this set is no longer in the graph.", new Object[]{this.f$0});
    }
}
