package io.sentry.util;

import io.sentry.Scope;
import io.sentry.ScopeCallback;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TracingUtils$$ExternalSyntheticLambda1 implements ScopeCallback {
    public final void run(Scope scope) {
        scope.withPropagationContext(new TracingUtils$$ExternalSyntheticLambda0(scope));
    }
}
