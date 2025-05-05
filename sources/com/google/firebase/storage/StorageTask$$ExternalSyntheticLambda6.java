package com.google.firebase.storage;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class StorageTask$$ExternalSyntheticLambda6 implements OnCompleteListener {
    public final /* synthetic */ StorageTask f$0;
    public final /* synthetic */ Continuation f$1;
    public final /* synthetic */ TaskCompletionSource f$2;

    public /* synthetic */ StorageTask$$ExternalSyntheticLambda6(StorageTask storageTask, Continuation continuation, TaskCompletionSource taskCompletionSource) {
        this.f$0 = storageTask;
        this.f$1 = continuation;
        this.f$2 = taskCompletionSource;
    }

    public final void onComplete(Task task) {
        this.f$0.m844lambda$continueWithImpl$4$comgooglefirebasestorageStorageTask(this.f$1, this.f$2, task);
    }
}
