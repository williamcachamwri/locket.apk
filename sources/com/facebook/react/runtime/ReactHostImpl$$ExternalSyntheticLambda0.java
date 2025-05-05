package com.facebook.react.runtime;

import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.runtime.ReactHostImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactHostImpl$$ExternalSyntheticLambda0 implements ReactHostImpl.VeniceThenable {
    public final /* synthetic */ ReactHostImpl f$0;
    public final /* synthetic */ JSBundleLoader f$1;

    public /* synthetic */ ReactHostImpl$$ExternalSyntheticLambda0(ReactHostImpl reactHostImpl, JSBundleLoader jSBundleLoader) {
        this.f$0 = reactHostImpl;
        this.f$1 = jSBundleLoader;
    }

    public final void then(Object obj) {
        this.f$0.lambda$loadBundle$13(this.f$1, (ReactInstance) obj);
    }
}
