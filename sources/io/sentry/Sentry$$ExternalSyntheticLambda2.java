package io.sentry;

import java.io.File;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Sentry$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ File[] f$0;

    public /* synthetic */ Sentry$$ExternalSyntheticLambda2(File[] fileArr) {
        this.f$0 = fileArr;
    }

    public final void run() {
        Sentry.lambda$initConfigurations$3(this.f$0);
    }
}
