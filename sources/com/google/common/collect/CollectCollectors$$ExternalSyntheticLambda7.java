package com.google.common.collect;

import java.util.function.Function;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CollectCollectors$$ExternalSyntheticLambda7 implements Function {
    public final Object apply(Object obj) {
        return ImmutableMultiset.copyFromEntries(((Multiset) obj).entrySet());
    }
}
