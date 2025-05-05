package io.sentry.util;

import io.sentry.PropagationContext;
import io.sentry.Scope;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TracingUtils$$ExternalSyntheticLambda0 implements Scope.IWithPropagationContext {
    public final /* synthetic */ Scope f$0;

    public /* synthetic */ TracingUtils$$ExternalSyntheticLambda0(Scope scope) {
        this.f$0 = scope;
    }

    public final void accept(PropagationContext propagationContext) {
        this.f$0.setPropagationContext(new PropagationContext());
    }
}
