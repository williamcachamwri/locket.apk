package expo.modules.camera.next.tasks;

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
@DebugMetadata(c = "expo.modules.camera.next.tasks.ResolveTakenPicture$resolve$2", f = "ResolveTakenPicture.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ResolveTakenPicture.kt */
final class ResolveTakenPicture$resolve$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ ResolveTakenPicture this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ResolveTakenPicture$resolve$2(ResolveTakenPicture resolveTakenPicture, Continuation<? super ResolveTakenPicture$resolve$2> continuation) {
        super(2, continuation);
        this.this$0 = resolveTakenPicture;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ResolveTakenPicture$resolve$2(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ResolveTakenPicture$resolve$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.onComplete(this.this$0.processImage());
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
