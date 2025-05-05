package com.google.firebase.perf.transport;

import com.google.firebase.perf.v1.ApplicationProcessState;
import com.google.firebase.perf.v1.NetworkRequestMetric;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TransportManager$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ TransportManager f$0;
    public final /* synthetic */ NetworkRequestMetric f$1;
    public final /* synthetic */ ApplicationProcessState f$2;

    public /* synthetic */ TransportManager$$ExternalSyntheticLambda2(TransportManager transportManager, NetworkRequestMetric networkRequestMetric, ApplicationProcessState applicationProcessState) {
        this.f$0 = transportManager;
        this.f$1 = networkRequestMetric;
        this.f$2 = applicationProcessState;
    }

    public final void run() {
        this.f$0.m815lambda$log$3$comgooglefirebaseperftransportTransportManager(this.f$1, this.f$2);
    }
}
