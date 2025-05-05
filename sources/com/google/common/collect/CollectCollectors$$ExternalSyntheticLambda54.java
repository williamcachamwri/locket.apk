package com.google.common.collect;

import java.util.function.BinaryOperator;
import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CollectCollectors$$ExternalSyntheticLambda54 implements Supplier {
    public final /* synthetic */ BinaryOperator f$0;

    public /* synthetic */ CollectCollectors$$ExternalSyntheticLambda54(BinaryOperator binaryOperator) {
        this.f$0 = binaryOperator;
    }

    public final Object get() {
        return CollectCollectors.lambda$toImmutableEnumMap$15(this.f$0);
    }
}
