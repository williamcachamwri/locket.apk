package com.google.firebase.storage;

import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.TaskListenerImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class StorageTask$$ExternalSyntheticLambda1 implements TaskListenerImpl.OnRaise {
    public final void raise(Object obj, Object obj2) {
        ((OnProgressListener) obj).onProgress((StorageTask.ProvideError) obj2);
    }
}
