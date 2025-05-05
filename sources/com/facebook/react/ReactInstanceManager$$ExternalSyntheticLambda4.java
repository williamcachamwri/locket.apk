package com.facebook.react;

import com.facebook.react.bridge.ReactApplicationContext;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactInstanceManager$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ ReactInstanceManager f$0;
    public final /* synthetic */ ReactApplicationContext f$1;

    public /* synthetic */ ReactInstanceManager$$ExternalSyntheticLambda4(ReactInstanceManager reactInstanceManager, ReactApplicationContext reactApplicationContext) {
        this.f$0 = reactInstanceManager;
        this.f$1 = reactApplicationContext;
    }

    public final void run() {
        this.f$0.lambda$runCreateReactContextOnNewThread$1(this.f$1);
    }
}
