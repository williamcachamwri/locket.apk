package com.google.common.collect;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import java.util.function.BinaryOperator;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CollectCollectors$$ExternalSyntheticLambda68 implements BinaryOperator {
    public final Object apply(Object obj, Object obj2) {
        return ((ImmutableSortedSet.Builder) obj).combine((ImmutableSet.Builder) (ImmutableSortedSet.Builder) obj2);
    }
}
