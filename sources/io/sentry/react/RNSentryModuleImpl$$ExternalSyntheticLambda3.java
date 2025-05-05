package io.sentry.react;

import com.facebook.react.bridge.ReadableMap;
import io.sentry.Scope;
import io.sentry.ScopeCallback;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RNSentryModuleImpl$$ExternalSyntheticLambda3 implements ScopeCallback {
    public final /* synthetic */ ReadableMap f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ RNSentryModuleImpl$$ExternalSyntheticLambda3(ReadableMap readableMap, String str) {
        this.f$0 = readableMap;
        this.f$1 = str;
    }

    public final void run(Scope scope) {
        scope.setContexts(this.f$1, (Object) this.f$0.toHashMap());
    }
}
