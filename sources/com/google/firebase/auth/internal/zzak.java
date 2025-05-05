package com.google.firebase.auth.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.MultiFactorSession;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzak implements Continuation<GetTokenResult, Task<MultiFactorSession>> {
    private final /* synthetic */ zzah zza;

    public final /* synthetic */ Object then(Task task) throws Exception {
        if (!task.isSuccessful()) {
            return Tasks.forException(task.getException());
        }
        return Tasks.forResult(zzao.zza(((GetTokenResult) task.getResult()).getToken(), this.zza.zza));
    }

    zzak(zzah zzah) {
        this.zza = zzah;
    }
}
