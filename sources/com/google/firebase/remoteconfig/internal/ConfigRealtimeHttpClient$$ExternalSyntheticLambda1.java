package com.google.firebase.remoteconfig.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ConfigRealtimeHttpClient$$ExternalSyntheticLambda1 implements Continuation {
    public final /* synthetic */ ConfigRealtimeHttpClient f$0;
    public final /* synthetic */ Task f$1;
    public final /* synthetic */ Task f$2;

    public /* synthetic */ ConfigRealtimeHttpClient$$ExternalSyntheticLambda1(ConfigRealtimeHttpClient configRealtimeHttpClient, Task task, Task task2) {
        this.f$0 = configRealtimeHttpClient;
        this.f$1 = task;
        this.f$2 = task2;
    }

    public final Object then(Task task) {
        return this.f$0.m831lambda$createRealtimeConnection$0$comgooglefirebaseremoteconfiginternalConfigRealtimeHttpClient(this.f$1, this.f$2, task);
    }
}
