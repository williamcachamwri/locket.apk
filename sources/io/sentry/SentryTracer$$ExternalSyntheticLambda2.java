package io.sentry;

import io.sentry.Scope;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SentryTracer$$ExternalSyntheticLambda2 implements Scope.IWithTransaction {
    public final /* synthetic */ SentryTracer f$0;
    public final /* synthetic */ Scope f$1;

    public /* synthetic */ SentryTracer$$ExternalSyntheticLambda2(SentryTracer sentryTracer, Scope scope) {
        this.f$0 = sentryTracer;
        this.f$1 = scope;
    }

    public final void accept(ITransaction iTransaction) {
        this.f$0.m2381lambda$finish$0$iosentrySentryTracer(this.f$1, iTransaction);
    }
}
