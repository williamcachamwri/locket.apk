package com.google.firebase.remoteconfig.internal.rollouts;

import com.google.firebase.remoteconfig.interop.rollouts.RolloutsState;
import com.google.firebase.remoteconfig.interop.rollouts.RolloutsStateSubscriber;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RolloutsStateSubscriptionsHandler$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ RolloutsStateSubscriber f$0;
    public final /* synthetic */ RolloutsState f$1;

    public /* synthetic */ RolloutsStateSubscriptionsHandler$$ExternalSyntheticLambda1(RolloutsStateSubscriber rolloutsStateSubscriber, RolloutsState rolloutsState) {
        this.f$0 = rolloutsStateSubscriber;
        this.f$1 = rolloutsState;
    }

    public final void run() {
        this.f$0.onRolloutsStateChanged(this.f$1);
    }
}
