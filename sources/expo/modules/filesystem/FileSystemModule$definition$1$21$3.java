package expo.modules.filesystem;

import expo.modules.filesystem.FileSystemModule;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.filesystem.FileSystemModule$definition$1$21$3", f = "FileSystemModule.kt", i = {}, l = {681}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: FileSystemModule.kt */
final class FileSystemModule$definition$1$21$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ FileSystemModule.DownloadResumableTaskParams $params;
    int label;
    final /* synthetic */ FileSystemModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FileSystemModule$definition$1$21$3(FileSystemModule fileSystemModule, FileSystemModule.DownloadResumableTaskParams downloadResumableTaskParams, Continuation<? super FileSystemModule$definition$1$21$3> continuation) {
        super(2, continuation);
        this.this$0 = fileSystemModule;
        this.$params = downloadResumableTaskParams;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FileSystemModule$definition$1$21$3(this.this$0, this.$params, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FileSystemModule$definition$1$21$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (this.this$0.downloadResumableTask(this.$params, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
