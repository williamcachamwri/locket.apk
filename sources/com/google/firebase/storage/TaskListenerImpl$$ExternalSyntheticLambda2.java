package com.google.firebase.storage;

import com.google.firebase.storage.StorageTask;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TaskListenerImpl$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ TaskListenerImpl f$0;
    public final /* synthetic */ Object f$1;
    public final /* synthetic */ StorageTask.ProvideError f$2;

    public /* synthetic */ TaskListenerImpl$$ExternalSyntheticLambda2(TaskListenerImpl taskListenerImpl, Object obj, StorageTask.ProvideError provideError) {
        this.f$0 = taskListenerImpl;
        this.f$1 = obj;
        this.f$2 = provideError;
    }

    public final void run() {
        this.f$0.m852lambda$addListener$1$comgooglefirebasestorageTaskListenerImpl(this.f$1, this.f$2);
    }
}
