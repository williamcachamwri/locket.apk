package io.sentry.react;

import com.facebook.react.bridge.ReadableMap;
import io.sentry.Scope;
import io.sentry.ScopeCallback;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RNSentryModuleImpl$$ExternalSyntheticLambda4 implements ScopeCallback {
    public final /* synthetic */ ReadableMap f$0;

    public /* synthetic */ RNSentryModuleImpl$$ExternalSyntheticLambda4(ReadableMap readableMap) {
        this.f$0 = readableMap;
    }

    public final void run(Scope scope) {
        RNSentryModuleImpl.lambda$addBreadcrumb$4(this.f$0, scope);
    }
}
