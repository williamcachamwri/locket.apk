package com.google.firebase.remoteconfig.internal.rollouts;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.internal.ConfigContainer;
import com.google.firebase.remoteconfig.interop.rollouts.RolloutsStateSubscriber;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RolloutsStateSubscriptionsHandler$$ExternalSyntheticLambda2 implements OnSuccessListener {
    public final /* synthetic */ RolloutsStateSubscriptionsHandler f$0;
    public final /* synthetic */ Task f$1;
    public final /* synthetic */ RolloutsStateSubscriber f$2;

    public /* synthetic */ RolloutsStateSubscriptionsHandler$$ExternalSyntheticLambda2(RolloutsStateSubscriptionsHandler rolloutsStateSubscriptionsHandler, Task task, RolloutsStateSubscriber rolloutsStateSubscriber) {
        this.f$0 = rolloutsStateSubscriptionsHandler;
        this.f$1 = task;
        this.f$2 = rolloutsStateSubscriber;
    }

    public final void onSuccess(Object obj) {
        this.f$0.m832lambda$registerRolloutsStateSubscriber$1$comgooglefirebaseremoteconfiginternalrolloutsRolloutsStateSubscriptionsHandler(this.f$1, this.f$2, (ConfigContainer) obj);
    }
}
