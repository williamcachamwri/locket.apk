package expo.modules.camera.next;

import androidx.camera.lifecycle.ProcessCameraProvider;
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
@DebugMetadata(c = "expo.modules.camera.next.ExpoCameraView$releaseCamera$1", f = "ExpoCameraView.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ExpoCameraView.kt */
final class ExpoCameraView$releaseCamera$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ ExpoCameraView this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ExpoCameraView$releaseCamera$1(ExpoCameraView expoCameraView, Continuation<? super ExpoCameraView$releaseCamera$1> continuation) {
        super(2, continuation);
        this.this$0 = expoCameraView;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ExpoCameraView$releaseCamera$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ExpoCameraView$releaseCamera$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            ProcessCameraProvider access$getCameraProvider$p = this.this$0.cameraProvider;
            if (access$getCameraProvider$p != null) {
                access$getCameraProvider$p.unbindAll();
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
