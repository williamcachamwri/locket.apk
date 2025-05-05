package com.google.firebase.storage.ktx;

import com.google.firebase.storage.StorageTask;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class StorageKt$taskState$1$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ ProducerScope f$0;
    public final /* synthetic */ StorageTask.SnapshotBase f$1;

    public /* synthetic */ StorageKt$taskState$1$$ExternalSyntheticLambda1(ProducerScope producerScope, StorageTask.SnapshotBase snapshotBase) {
        this.f$0 = producerScope;
        this.f$1 = snapshotBase;
    }

    public final void run() {
        StorageKt$taskState$1.invokeSuspend$lambda$3$lambda$2(this.f$0, this.f$1);
    }
}
