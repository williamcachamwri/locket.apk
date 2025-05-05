package io.sentry.cache;

import io.sentry.protocol.SdkVersion;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PersistingOptionsObserver$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ PersistingOptionsObserver f$0;
    public final /* synthetic */ SdkVersion f$1;

    public /* synthetic */ PersistingOptionsObserver$$ExternalSyntheticLambda4(PersistingOptionsObserver persistingOptionsObserver, SdkVersion sdkVersion) {
        this.f$0 = persistingOptionsObserver;
        this.f$1 = sdkVersion;
    }

    public final void run() {
        this.f$0.m2414lambda$setSdkVersion$3$iosentrycachePersistingOptionsObserver(this.f$1);
    }
}
