package com.facebook.react.runtime;

import com.facebook.react.runtime.internal.bolts.Continuation;
import com.facebook.react.runtime.internal.bolts.Task;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactHostImpl$$ExternalSyntheticLambda49 implements Continuation {
    public final /* synthetic */ ReactHostImpl f$0;
    public final /* synthetic */ BridgelessReactContext f$1;

    public /* synthetic */ ReactHostImpl$$ExternalSyntheticLambda49(ReactHostImpl reactHostImpl, BridgelessReactContext bridgelessReactContext) {
        this.f$0 = reactHostImpl;
        this.f$1 = bridgelessReactContext;
    }

    public final Object then(Task task) {
        return this.f$0.lambda$oldGetOrCreateReactInstanceTask$32(this.f$1, task);
    }
}
