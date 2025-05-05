package com.google.mlkit.common.sdkinternal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public final /* synthetic */ class zzg implements Continuation {
    public final Object then(Task task) {
        return (Task) task.getResult();
    }
}
