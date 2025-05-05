package com.google.android.recaptcha.internal;

import com.google.android.gms.tasks.Task;
import java.util.concurrent.Executor;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Job;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzbz {
    public static final Deferred zza(Task task) {
        CompletableDeferred CompletableDeferred$default = CompletableDeferredKt.CompletableDeferred$default((Job) null, 1, (Object) null);
        task.addOnCompleteListener((Executor) zzbx.zza, new zzbw(CompletableDeferred$default));
        return new zzby(CompletableDeferred$default);
    }
}
