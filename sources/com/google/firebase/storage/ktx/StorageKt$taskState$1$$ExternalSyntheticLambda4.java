package com.google.firebase.storage.ktx;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class StorageKt$taskState$1$$ExternalSyntheticLambda4 implements OnCompleteListener {
    public final /* synthetic */ ProducerScope f$0;

    public /* synthetic */ StorageKt$taskState$1$$ExternalSyntheticLambda4(ProducerScope producerScope) {
        this.f$0 = producerScope;
    }

    public final void onComplete(Task task) {
        StorageKt$taskState$1.invokeSuspend$lambda$4(this.f$0, task);
    }
}
