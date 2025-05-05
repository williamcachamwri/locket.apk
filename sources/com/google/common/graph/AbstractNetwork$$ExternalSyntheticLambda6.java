package com.google.common.graph;

import com.google.common.base.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AbstractNetwork$$ExternalSyntheticLambda6 implements Supplier {
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ AbstractNetwork$$ExternalSyntheticLambda6(Object obj, Object obj2) {
        this.f$0 = obj;
        this.f$1 = obj2;
    }

    public final Object get() {
        return String.format("Node %s or node %s that were used to generate this set are no longer in the graph.", new Object[]{this.f$0, this.f$1});
    }
}
