package com.facebook.react.runtime;

import com.facebook.react.runtime.ReactHostImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactHostImpl$$ExternalSyntheticLambda35 implements ReactHostImpl.VeniceThenable {
    public final /* synthetic */ int f$0;

    public /* synthetic */ ReactHostImpl$$ExternalSyntheticLambda35(int i) {
        this.f$0 = i;
    }

    public final void then(Object obj) {
        ((ReactInstance) obj).handleMemoryPressure(this.f$0);
    }
}
