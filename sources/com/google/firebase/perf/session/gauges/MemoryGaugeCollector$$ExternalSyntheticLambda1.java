package com.google.firebase.perf.session.gauges;

import com.google.firebase.perf.util.Timer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MemoryGaugeCollector$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ MemoryGaugeCollector f$0;
    public final /* synthetic */ Timer f$1;

    public /* synthetic */ MemoryGaugeCollector$$ExternalSyntheticLambda1(MemoryGaugeCollector memoryGaugeCollector, Timer timer) {
        this.f$0 = memoryGaugeCollector;
        this.f$1 = timer;
    }

    public final void run() {
        this.f$0.m810lambda$scheduleMemoryMetricCollectionOnce$1$comgooglefirebaseperfsessiongaugesMemoryGaugeCollector(this.f$1);
    }
}
