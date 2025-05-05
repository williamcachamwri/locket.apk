package com.google.firebase.appcheck.debug.internal;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.appcheck.internal.AppCheckTokenResponse;
import com.google.firebase.appcheck.internal.DefaultAppCheckToken;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DebugAppCheckProvider$$ExternalSyntheticLambda3 implements SuccessContinuation {
    public final Task then(Object obj) {
        return Tasks.forResult(DefaultAppCheckToken.constructFromAppCheckTokenResponse((AppCheckTokenResponse) obj));
    }
}
