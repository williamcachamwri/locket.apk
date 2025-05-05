package com.google.common.util.concurrent;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ClosingFuture$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ AutoCloseable f$0;

    public /* synthetic */ ClosingFuture$$ExternalSyntheticLambda0(AutoCloseable autoCloseable) {
        this.f$0 = autoCloseable;
    }

    public final void run() {
        ClosingFuture.lambda$closeQuietly$0(this.f$0);
    }
}
