package com.facebook.react.runtime;

import com.facebook.react.runtime.ReactHostImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactHostImpl$$ExternalSyntheticLambda10 implements ReactHostImpl.VeniceThenable {
    public final /* synthetic */ ReactHostImpl f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ ReactSurfaceImpl f$2;

    public /* synthetic */ ReactHostImpl$$ExternalSyntheticLambda10(ReactHostImpl reactHostImpl, String str, ReactSurfaceImpl reactSurfaceImpl) {
        this.f$0 = reactHostImpl;
        this.f$1 = str;
        this.f$2 = reactSurfaceImpl;
    }

    public final void then(Object obj) {
        this.f$0.lambda$prerenderSurface$2(this.f$1, this.f$2, (ReactInstance) obj);
    }
}
