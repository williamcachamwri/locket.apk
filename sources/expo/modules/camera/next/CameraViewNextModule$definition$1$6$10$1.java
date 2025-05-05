package expo.modules.camera.next;

import android.os.Bundle;
import expo.modules.camera.next.tasks.PictureSavedDelegate;
import expo.modules.camera.next.tasks.ResolveTakenPicture;
import expo.modules.kotlin.Promise;
import io.sentry.protocol.Response;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.camera.next.CameraViewNextModule$definition$1$6$10$1", f = "CameraViewNextModule.kt", i = {}, l = {135}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: CameraViewNextModule.kt */
final class CameraViewNextModule$definition$1$6$10$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ byte[] $image;
    final /* synthetic */ PictureOptions $options;
    final /* synthetic */ Promise $promise;
    final /* synthetic */ ExpoCameraView $view;
    int label;
    final /* synthetic */ CameraViewNextModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CameraViewNextModule$definition$1$6$10$1(byte[] bArr, Promise promise, PictureOptions pictureOptions, CameraViewNextModule cameraViewNextModule, ExpoCameraView expoCameraView, Continuation<? super CameraViewNextModule$definition$1$6$10$1> continuation) {
        super(2, continuation);
        this.$image = bArr;
        this.$promise = promise;
        this.$options = pictureOptions;
        this.this$0 = cameraViewNextModule;
        this.$view = expoCameraView;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CameraViewNextModule$definition$1$6$10$1(this.$image, this.$promise, this.$options, this.this$0, this.$view, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CameraViewNextModule$definition$1$6$10$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            byte[] bArr = this.$image;
            Promise promise = this.$promise;
            PictureOptions pictureOptions = this.$options;
            File access$getCacheDirectory = this.this$0.getCacheDirectory();
            final ExpoCameraView expoCameraView = this.$view;
            this.label = 1;
            if (new ResolveTakenPicture(bArr, promise, pictureOptions, access$getCacheDirectory, new PictureSavedDelegate() {
                public final void onPictureSaved(Bundle bundle) {
                    Intrinsics.checkNotNullParameter(bundle, Response.TYPE);
                    expoCameraView.onPictureSaved(bundle);
                }
            }).resolve(this) == coroutine_suspended) {
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
