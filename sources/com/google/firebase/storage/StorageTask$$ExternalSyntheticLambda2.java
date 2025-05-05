package com.google.firebase.storage;

import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.TaskListenerImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class StorageTask$$ExternalSyntheticLambda2 implements TaskListenerImpl.OnRaise {
    public final void raise(Object obj, Object obj2) {
        ((OnPausedListener) obj).onPaused((StorageTask.ProvideError) obj2);
    }
}
