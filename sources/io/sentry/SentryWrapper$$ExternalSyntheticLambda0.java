package io.sentry;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SentryWrapper$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ IHub f$0;
    public final /* synthetic */ Supplier f$1;
    public final /* synthetic */ IHub f$2;

    public /* synthetic */ SentryWrapper$$ExternalSyntheticLambda0(IHub iHub, Supplier supplier, IHub iHub2) {
        this.f$0 = iHub;
        this.f$1 = supplier;
        this.f$2 = iHub2;
    }

    public final Object get() {
        return SentryWrapper.lambda$wrapSupplier$1(this.f$0, this.f$1, this.f$2);
    }
}
