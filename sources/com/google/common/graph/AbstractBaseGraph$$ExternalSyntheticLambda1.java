package com.google.common.graph;

import com.google.common.base.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AbstractBaseGraph$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ Object f$0;

    public /* synthetic */ AbstractBaseGraph$$ExternalSyntheticLambda1(Object obj) {
        this.f$0 = obj;
    }

    public final Object get() {
        return String.format("Node %s that was used to generate this set is no longer in the graph.", new Object[]{this.f$0});
    }
}
