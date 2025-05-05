package com.google.firebase.perf.session.gauges;

import com.google.firebase.inject.Provider;
import java.util.concurrent.Executors;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class GaugeManager$$ExternalSyntheticLambda2 implements Provider {
    public final Object get() {
        return Executors.newSingleThreadScheduledExecutor();
    }
}
