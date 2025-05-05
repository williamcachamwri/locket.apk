package com.google.common.collect;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSetMultimap;
import java.util.function.BinaryOperator;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CollectCollectors$$ExternalSyntheticLambda38 implements BinaryOperator {
    public final Object apply(Object obj, Object obj2) {
        return ((ImmutableSetMultimap.Builder) obj).combine((ImmutableMultimap.Builder) (ImmutableSetMultimap.Builder) obj2);
    }
}
