package io.sentry.android.core;

import io.sentry.FullyDisplayedReporter;
import io.sentry.ISpan;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ActivityLifecycleIntegration$$ExternalSyntheticLambda9 implements FullyDisplayedReporter.FullyDisplayedReporterListener {
    public final /* synthetic */ ActivityLifecycleIntegration f$0;
    public final /* synthetic */ ISpan f$1;

    public /* synthetic */ ActivityLifecycleIntegration$$ExternalSyntheticLambda9(ActivityLifecycleIntegration activityLifecycleIntegration, ISpan iSpan) {
        this.f$0 = activityLifecycleIntegration;
        this.f$1 = iSpan;
    }

    public final void onFullyDrawn() {
        this.f$0.m2390lambda$onActivityCreated$7$iosentryandroidcoreActivityLifecycleIntegration(this.f$1);
    }
}
