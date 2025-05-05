package com.facebook.react.runtime;

import com.facebook.react.bridge.MemoryPressureListener;
import java.lang.ref.WeakReference;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactHostImpl$$ExternalSyntheticLambda55 implements MemoryPressureListener {
    public final /* synthetic */ ReactHostImpl f$0;
    public final /* synthetic */ WeakReference f$1;

    public /* synthetic */ ReactHostImpl$$ExternalSyntheticLambda55(ReactHostImpl reactHostImpl, WeakReference weakReference) {
        this.f$0 = reactHostImpl;
        this.f$1 = weakReference;
    }

    public final void handleMemoryPressure(int i) {
        this.f$0.lambda$createMemoryPressureListener$11(this.f$1, i);
    }
}
