package com.facebook.react.runtime;

import java.util.concurrent.Callable;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactHostImpl$$ExternalSyntheticLambda28 implements Callable {
    public final /* synthetic */ ReactHostImpl f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ Exception f$2;

    public /* synthetic */ ReactHostImpl$$ExternalSyntheticLambda28(ReactHostImpl reactHostImpl, String str, Exception exc) {
        this.f$0 = reactHostImpl;
        this.f$1 = str;
        this.f$2 = exc;
    }

    public final Object call() {
        return this.f$0.lambda$destroy$9(this.f$1, this.f$2);
    }
}
