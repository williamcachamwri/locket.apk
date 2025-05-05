package com.google.firebase.storage;

import com.google.firebase.storage.StorageTask;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class StorageKt$taskState$1$$ExternalSyntheticLambda2 implements OnPausedListener {
    public final /* synthetic */ ProducerScope f$0;

    public /* synthetic */ StorageKt$taskState$1$$ExternalSyntheticLambda2(ProducerScope producerScope) {
        this.f$0 = producerScope;
    }

    public final void onPaused(Object obj) {
        StorageKt$taskState$1.invokeSuspend$lambda$3(this.f$0, (StorageTask.SnapshotBase) obj);
    }
}
