package com.google.common.util.concurrent;

import java.util.concurrent.Future;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Futures$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Future f$0;

    public /* synthetic */ Futures$$ExternalSyntheticLambda0(Future future) {
        this.f$0 = future;
    }

    public final void run() {
        this.f$0.cancel(false);
    }
}
