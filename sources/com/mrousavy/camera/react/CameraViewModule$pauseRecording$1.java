package com.mrousavy.camera.react;

import com.facebook.react.bridge.Promise;
import com.mrousavy.camera.core.CameraError;
import com.mrousavy.camera.core.UnknownCameraError;
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
@DebugMetadata(c = "com.mrousavy.camera.react.CameraViewModule$pauseRecording$1", f = "CameraViewModule.kt", i = {0}, l = {140}, m = "invokeSuspend", n = {"promise$iv"}, s = {"L$0"})
/* compiled from: CameraViewModule.kt */
final class CameraViewModule$pauseRecording$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Promise $promise;
    final /* synthetic */ int $viewTag;
    Object L$0;
    int label;
    final /* synthetic */ CameraViewModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CameraViewModule$pauseRecording$1(Promise promise, CameraViewModule cameraViewModule, int i, Continuation<? super CameraViewModule$pauseRecording$1> continuation) {
        super(2, continuation);
        this.$promise = promise;
        this.this$0 = cameraViewModule;
        this.$viewTag = i;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CameraViewModule$pauseRecording$1(this.$promise, this.this$0, this.$viewTag, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CameraViewModule$pauseRecording$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CameraError th;
        Promise promise;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Promise promise2 = this.$promise;
            CameraViewModule cameraViewModule = this.this$0;
            int i2 = this.$viewTag;
            try {
                this.L$0 = promise2;
                this.label = 1;
                Object access$findCameraView = cameraViewModule.findCameraView(i2, this);
                if (access$findCameraView == coroutine_suspended) {
                    return coroutine_suspended;
                }
                promise = promise2;
                obj = access$findCameraView;
            } catch (Throwable th2) {
                CameraError cameraError = th2;
                promise = promise2;
                th = cameraError;
                th.printStackTrace();
                CameraError unknownCameraError = th instanceof CameraError ? th : new UnknownCameraError(th);
                promise.reject(unknownCameraError.getDomain() + "/" + unknownCameraError.getId(), unknownCameraError.getMessage(), unknownCameraError.getCause());
                return Unit.INSTANCE;
            }
        } else if (i == 1) {
            promise = (Promise) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th3) {
                th = th3;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        CameraView_RecordVideoKt.pauseRecording((CameraView) obj);
        promise.resolve((Object) null);
        return Unit.INSTANCE;
    }
}
