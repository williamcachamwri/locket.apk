package com.facebook.react.runtime;

import com.facebook.react.bridge.queue.QueueThreadExceptionHandler;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactHostImpl$$ExternalSyntheticLambda8 implements QueueThreadExceptionHandler {
    public final /* synthetic */ ReactHostImpl f$0;

    public /* synthetic */ ReactHostImpl$$ExternalSyntheticLambda8(ReactHostImpl reactHostImpl) {
        this.f$0 = reactHostImpl;
    }

    public final void handleException(Exception exc) {
        this.f$0.handleHostException(exc);
    }
}
