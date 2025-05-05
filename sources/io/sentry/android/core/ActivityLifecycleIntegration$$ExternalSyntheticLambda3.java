package io.sentry.android.core;

import io.sentry.ITransaction;
import io.sentry.Scope;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ActivityLifecycleIntegration$$ExternalSyntheticLambda3 implements Scope.IWithTransaction {
    public final /* synthetic */ ActivityLifecycleIntegration f$0;
    public final /* synthetic */ Scope f$1;
    public final /* synthetic */ ITransaction f$2;

    public /* synthetic */ ActivityLifecycleIntegration$$ExternalSyntheticLambda3(ActivityLifecycleIntegration activityLifecycleIntegration, Scope scope, ITransaction iTransaction) {
        this.f$0 = activityLifecycleIntegration;
        this.f$1 = scope;
        this.f$2 = iTransaction;
    }

    public final void accept(ITransaction iTransaction) {
        this.f$0.m2388lambda$applyScope$3$iosentryandroidcoreActivityLifecycleIntegration(this.f$1, this.f$2, iTransaction);
    }
}
