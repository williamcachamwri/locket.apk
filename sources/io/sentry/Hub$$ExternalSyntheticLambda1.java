package io.sentry;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Hub$$ExternalSyntheticLambda1 implements ScopeCallback {
    public final /* synthetic */ ITransaction f$0;

    public /* synthetic */ Hub$$ExternalSyntheticLambda1(ITransaction iTransaction) {
        this.f$0 = iTransaction;
    }

    public final void run(Scope scope) {
        scope.setTransaction(this.f$0);
    }
}
