package io.sentry.android.core;

import io.sentry.Scope;
import io.sentry.ScopeCallback;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class InternalSentrySdk$$ExternalSyntheticLambda0 implements ScopeCallback {
    public final /* synthetic */ AtomicReference f$0;

    public /* synthetic */ InternalSentrySdk$$ExternalSyntheticLambda0(AtomicReference atomicReference) {
        this.f$0 = atomicReference;
    }

    public final void run(Scope scope) {
        this.f$0.set(new Scope(scope));
    }
}
