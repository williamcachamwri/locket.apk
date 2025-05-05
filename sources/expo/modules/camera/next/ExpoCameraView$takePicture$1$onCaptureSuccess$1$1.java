package expo.modules.camera.next;

import android.os.Bundle;
import expo.modules.camera.next.tasks.ResolveTakenPicture;
import expo.modules.kotlin.Promise;
import java.io.File;
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
@DebugMetadata(c = "expo.modules.camera.next.ExpoCameraView$takePicture$1$onCaptureSuccess$1$1", f = "ExpoCameraView.kt", i = {}, l = {166}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ExpoCameraView.kt */
final class ExpoCameraView$takePicture$1$onCaptureSuccess$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ byte[] $data;
    final /* synthetic */ File $it;
    final /* synthetic */ PictureOptions $options;
    final /* synthetic */ Promise $promise;
    int label;
    final /* synthetic */ ExpoCameraView this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ExpoCameraView$takePicture$1$onCaptureSuccess$1$1(byte[] bArr, Promise promise, PictureOptions pictureOptions, File file, ExpoCameraView expoCameraView, Continuation<? super ExpoCameraView$takePicture$1$onCaptureSuccess$1$1> continuation) {
        super(2, continuation);
        this.$data = bArr;
        this.$promise = promise;
        this.$options = pictureOptions;
        this.$it = file;
        this.this$0 = expoCameraView;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ExpoCameraView$takePicture$1$onCaptureSuccess$1$1(this.$data, this.$promise, this.$options, this.$it, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ExpoCameraView$takePicture$1$onCaptureSuccess$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (new ResolveTakenPicture(this.$data, this.$promise, this.$options, this.$it, new ExpoCameraView$takePicture$1$onCaptureSuccess$1$1$$ExternalSyntheticLambda0(this.this$0)).resolve(this) == coroutine_suspended) {
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
    public static final void invokeSuspend$lambda$0(ExpoCameraView expoCameraView, Bundle bundle) {
        expoCameraView.onPictureSaved(bundle);
    }
}
