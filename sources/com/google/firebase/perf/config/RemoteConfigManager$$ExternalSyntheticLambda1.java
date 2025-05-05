package com.google.firebase.perf.config;

import com.google.android.gms.tasks.OnFailureListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RemoteConfigManager$$ExternalSyntheticLambda1 implements OnFailureListener {
    public final /* synthetic */ RemoteConfigManager f$0;

    public /* synthetic */ RemoteConfigManager$$ExternalSyntheticLambda1(RemoteConfigManager remoteConfigManager) {
        this.f$0 = remoteConfigManager;
    }

    public final void onFailure(Exception exc) {
        this.f$0.m802lambda$triggerFirebaseRemoteConfigFetchAndActivateOnSuccessfulFetch$1$comgooglefirebaseperfconfigRemoteConfigManager(exc);
    }
}
