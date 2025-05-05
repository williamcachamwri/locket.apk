package com.google.firebase.perf.transport;

import com.google.firebase.perf.v1.ApplicationProcessState;
import com.google.firebase.perf.v1.TraceMetric;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TransportManager$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ TransportManager f$0;
    public final /* synthetic */ TraceMetric f$1;
    public final /* synthetic */ ApplicationProcessState f$2;

    public /* synthetic */ TransportManager$$ExternalSyntheticLambda0(TransportManager transportManager, TraceMetric traceMetric, ApplicationProcessState applicationProcessState) {
        this.f$0 = transportManager;
        this.f$1 = traceMetric;
        this.f$2 = applicationProcessState;
    }

    public final void run() {
        this.f$0.m814lambda$log$2$comgooglefirebaseperftransportTransportManager(this.f$1, this.f$2);
    }
}
