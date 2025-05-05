package com.google.firebase.storage;

import com.google.firebase.storage.StorageTask;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class StorageKt$taskState$1$$ExternalSyntheticLambda1 implements OnProgressListener {
    public final /* synthetic */ ProducerScope f$0;

    public /* synthetic */ StorageKt$taskState$1$$ExternalSyntheticLambda1(ProducerScope producerScope) {
        this.f$0 = producerScope;
    }

    public final void onProgress(Object obj) {
        StorageKt$taskState$1.invokeSuspend$lambda$1(this.f$0, (StorageTask.SnapshotBase) obj);
    }
}
