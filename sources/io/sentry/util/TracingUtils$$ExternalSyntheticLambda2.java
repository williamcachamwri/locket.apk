package io.sentry.util;

import io.sentry.PropagationContext;
import io.sentry.Scope;
import io.sentry.SentryOptions;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TracingUtils$$ExternalSyntheticLambda2 implements Scope.IWithPropagationContext {
    public final /* synthetic */ SentryOptions f$0;
    public final /* synthetic */ Scope f$1;

    public /* synthetic */ TracingUtils$$ExternalSyntheticLambda2(SentryOptions sentryOptions, Scope scope) {
        this.f$0 = sentryOptions;
        this.f$1 = scope;
    }

    public final void accept(PropagationContext propagationContext) {
        TracingUtils.lambda$maybeUpdateBaggage$3(this.f$0, this.f$1, propagationContext);
    }
}
