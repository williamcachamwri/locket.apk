package com.google.firebase.remoteconfig.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.Date;
import java.util.Map;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ConfigFetchHandler$$ExternalSyntheticLambda2 implements Continuation {
    public final /* synthetic */ ConfigFetchHandler f$0;
    public final /* synthetic */ Task f$1;
    public final /* synthetic */ Task f$2;
    public final /* synthetic */ Date f$3;
    public final /* synthetic */ Map f$4;

    public /* synthetic */ ConfigFetchHandler$$ExternalSyntheticLambda2(ConfigFetchHandler configFetchHandler, Task task, Task task2, Date date, Map map) {
        this.f$0 = configFetchHandler;
        this.f$1 = task;
        this.f$2 = task2;
        this.f$3 = date;
        this.f$4 = map;
    }

    public final Object then(Task task) {
        return this.f$0.m827lambda$fetchIfCacheExpiredAndNotThrottled$2$comgooglefirebaseremoteconfiginternalConfigFetchHandler(this.f$1, this.f$2, this.f$3, this.f$4, task);
    }
}
