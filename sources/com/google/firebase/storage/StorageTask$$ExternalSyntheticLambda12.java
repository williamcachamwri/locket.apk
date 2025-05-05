package com.google.firebase.storage;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.TaskListenerImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class StorageTask$$ExternalSyntheticLambda12 implements TaskListenerImpl.OnRaise {
    public final /* synthetic */ StorageTask f$0;

    public /* synthetic */ StorageTask$$ExternalSyntheticLambda12(StorageTask storageTask) {
        this.f$0 = storageTask;
    }

    public final void raise(Object obj, Object obj2) {
        this.f$0.m850lambda$new$3$comgooglefirebasestorageStorageTask((OnCanceledListener) obj, (StorageTask.ProvideError) obj2);
    }
}
