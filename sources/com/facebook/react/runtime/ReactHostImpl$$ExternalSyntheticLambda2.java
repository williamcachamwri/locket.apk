package com.facebook.react.runtime;

import com.facebook.react.runtime.ReactHostImpl;
import com.facebook.react.runtime.internal.bolts.Continuation;
import com.facebook.react.runtime.internal.bolts.Task;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactHostImpl$$ExternalSyntheticLambda2 implements Continuation {
    public final /* synthetic */ ReactHostImpl f$0;
    public final /* synthetic */ ReactHostImpl.ReactInstanceTaskUnwrapper f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ ReactHostImpl$$ExternalSyntheticLambda2(ReactHostImpl reactHostImpl, ReactHostImpl.ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapper, String str) {
        this.f$0 = reactHostImpl;
        this.f$1 = reactInstanceTaskUnwrapper;
        this.f$2 = str;
    }

    public final Object then(Task task) {
        return this.f$0.lambda$newGetOrCreateDestroyTask$46(this.f$1, this.f$2, task);
    }
}
