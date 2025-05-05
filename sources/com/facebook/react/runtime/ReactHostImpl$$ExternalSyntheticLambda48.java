package com.facebook.react.runtime;

import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.runtime.internal.bolts.Continuation;
import com.facebook.react.runtime.internal.bolts.Task;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactHostImpl$$ExternalSyntheticLambda48 implements Continuation {
    public final /* synthetic */ ReactHostImpl f$0;
    public final /* synthetic */ BridgelessReactContext f$1;
    public final /* synthetic */ DevSupportManager f$2;

    public /* synthetic */ ReactHostImpl$$ExternalSyntheticLambda48(ReactHostImpl reactHostImpl, BridgelessReactContext bridgelessReactContext, DevSupportManager devSupportManager) {
        this.f$0 = reactHostImpl;
        this.f$1 = bridgelessReactContext;
        this.f$2 = devSupportManager;
    }

    public final Object then(Task task) {
        return this.f$0.lambda$oldGetOrCreateReactInstanceTask$31(this.f$1, this.f$2, task);
    }
}
