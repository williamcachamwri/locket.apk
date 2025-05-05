package io.sentry.android.core;

import io.sentry.Scope;
import io.sentry.ScopeCallback;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ActivityLifecycleIntegration$$ExternalSyntheticLambda8 implements ScopeCallback {
    public final /* synthetic */ String f$0;

    public /* synthetic */ ActivityLifecycleIntegration$$ExternalSyntheticLambda8(String str) {
        this.f$0 = str;
    }

    public final void run(Scope scope) {
        scope.setScreen(this.f$0);
    }
}
