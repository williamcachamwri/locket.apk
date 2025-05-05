package com.google.firebase.storage;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.TaskState;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.channels.ChannelsKt;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.SendChannel;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "Lcom/google/firebase/storage/TaskState;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.google.firebase.storage.StorageKt$taskState$1", f = "Storage.kt", i = {}, l = {173}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: Storage.kt */
final class StorageKt$taskState$1 extends SuspendLambda implements Function2<ProducerScope<? super TaskState<T>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ StorageTask<T> $this_taskState;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StorageKt$taskState$1(StorageTask<T> storageTask, Continuation<? super StorageKt$taskState$1> continuation) {
        super(2, continuation);
        this.$this_taskState = storageTask;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        StorageKt$taskState$1 storageKt$taskState$1 = new StorageKt$taskState$1(this.$this_taskState, continuation);
        storageKt$taskState$1.L$0 = obj;
        return storageKt$taskState$1;
    }

    public final Object invoke(ProducerScope<? super TaskState<T>> producerScope, Continuation<? super Unit> continuation) {
        return ((StorageKt$taskState$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ProducerScope producerScope = (ProducerScope) this.L$0;
            final StorageKt$taskState$1$$ExternalSyntheticLambda1 storageKt$taskState$1$$ExternalSyntheticLambda1 = new StorageKt$taskState$1$$ExternalSyntheticLambda1(producerScope);
            final StorageKt$taskState$1$$ExternalSyntheticLambda2 storageKt$taskState$1$$ExternalSyntheticLambda2 = new StorageKt$taskState$1$$ExternalSyntheticLambda2(producerScope);
            final StorageKt$taskState$1$$ExternalSyntheticLambda3 storageKt$taskState$1$$ExternalSyntheticLambda3 = new StorageKt$taskState$1$$ExternalSyntheticLambda3(producerScope);
            this.$this_taskState.addOnProgressListener((OnProgressListener) storageKt$taskState$1$$ExternalSyntheticLambda1);
            this.$this_taskState.addOnPausedListener((OnPausedListener) storageKt$taskState$1$$ExternalSyntheticLambda2);
            this.$this_taskState.addOnCompleteListener((OnCompleteListener) storageKt$taskState$1$$ExternalSyntheticLambda3);
            final StorageTask<T> storageTask = this.$this_taskState;
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, new Function0<Unit>() {
                public final void invoke() {
                    storageTask.removeOnProgressListener(storageKt$taskState$1$$ExternalSyntheticLambda1);
                    storageTask.removeOnPausedListener(storageKt$taskState$1$$ExternalSyntheticLambda2);
                    storageTask.removeOnCompleteListener(storageKt$taskState$1$$ExternalSyntheticLambda3);
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final void invokeSuspend$lambda$1(ProducerScope producerScope, StorageTask.SnapshotBase snapshotBase) {
        StorageTaskScheduler.getInstance().scheduleCallback(new StorageKt$taskState$1$$ExternalSyntheticLambda4(producerScope, snapshotBase));
    }

    /* access modifiers changed from: private */
    public static final void invokeSuspend$lambda$1$lambda$0(ProducerScope producerScope, StorageTask.SnapshotBase snapshotBase) {
        ChannelsKt.trySendBlocking(producerScope, new TaskState.InProgress(snapshotBase));
    }

    /* access modifiers changed from: private */
    public static final void invokeSuspend$lambda$3(ProducerScope producerScope, StorageTask.SnapshotBase snapshotBase) {
        StorageTaskScheduler.getInstance().scheduleCallback(new StorageKt$taskState$1$$ExternalSyntheticLambda0(producerScope, snapshotBase));
    }

    /* access modifiers changed from: private */
    public static final void invokeSuspend$lambda$3$lambda$2(ProducerScope producerScope, StorageTask.SnapshotBase snapshotBase) {
        ChannelsKt.trySendBlocking(producerScope, new TaskState.Paused(snapshotBase));
    }

    /* access modifiers changed from: private */
    public static final void invokeSuspend$lambda$4(ProducerScope producerScope, Task task) {
        if (task.isSuccessful()) {
            SendChannel.DefaultImpls.close$default(producerScope, (Throwable) null, 1, (Object) null);
        } else {
            CoroutineScopeKt.cancel(producerScope, "Error getting the TaskState", task.getException());
        }
    }
}
