package com.facebook.react.runtime;

import com.facebook.react.runtime.ReactHostImpl;
import com.facebook.react.runtime.internal.bolts.Continuation;
import com.facebook.react.runtime.internal.bolts.Task;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactHostImpl$$ExternalSyntheticLambda30 implements Continuation {
    public final /* synthetic */ ReactHostImpl f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ ReactHostImpl.VeniceThenable f$2;

    public /* synthetic */ ReactHostImpl$$ExternalSyntheticLambda30(ReactHostImpl reactHostImpl, String str, ReactHostImpl.VeniceThenable veniceThenable) {
        this.f$0 = reactHostImpl;
        this.f$1 = str;
        this.f$2 = veniceThenable;
    }

    public final Object then(Task task) {
        return this.f$0.lambda$callAfterGetOrCreateReactInstance$22(this.f$1, this.f$2, task);
    }
}
