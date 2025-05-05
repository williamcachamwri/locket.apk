package com.google.firebase.appcheck.playintegrity.internal;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.integrity.IntegrityTokenResponse;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PlayIntegrityAppCheckProvider$$ExternalSyntheticLambda3 implements SuccessContinuation {
    public final /* synthetic */ PlayIntegrityAppCheckProvider f$0;

    public /* synthetic */ PlayIntegrityAppCheckProvider$$ExternalSyntheticLambda3(PlayIntegrityAppCheckProvider playIntegrityAppCheckProvider) {
        this.f$0 = playIntegrityAppCheckProvider;
    }

    public final Task then(Object obj) {
        return this.f$0.m581lambda$getToken$1$comgooglefirebaseappcheckplayintegrityinternalPlayIntegrityAppCheckProvider((IntegrityTokenResponse) obj);
    }
}
