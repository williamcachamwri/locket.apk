package com.google.firebase.appcheck.playintegrity.internal;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PlayIntegrityAppCheckProvider$$ExternalSyntheticLambda1 implements SuccessContinuation {
    public final /* synthetic */ PlayIntegrityAppCheckProvider f$0;

    public /* synthetic */ PlayIntegrityAppCheckProvider$$ExternalSyntheticLambda1(PlayIntegrityAppCheckProvider playIntegrityAppCheckProvider) {
        this.f$0 = playIntegrityAppCheckProvider;
    }

    public final Task then(Object obj) {
        return this.f$0.m579lambda$getPlayIntegrityAttestation$4$comgooglefirebaseappcheckplayintegrityinternalPlayIntegrityAppCheckProvider((GeneratePlayIntegrityChallengeResponse) obj);
    }
}
