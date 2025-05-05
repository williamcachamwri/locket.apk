package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.Executor;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaei  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public class zzaei {
    zzadh zza;
    Executor zzb;

    public final <ResultT> Task<ResultT> zza(zzaek<ResultT> zzaek) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zzb.execute(new zzaeh(this, zzaek, taskCompletionSource));
        return taskCompletionSource.getTask();
    }
}
