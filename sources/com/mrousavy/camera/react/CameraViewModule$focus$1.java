package com.mrousavy.camera.react;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
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
@DebugMetadata(c = "com.mrousavy.camera.react.CameraViewModule$focus$1", f = "CameraViewModule.kt", i = {1}, l = {183, 185}, m = "invokeSuspend", n = {"promise$iv"}, s = {"L$0"})
/* compiled from: CameraViewModule.kt */
final class CameraViewModule$focus$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReadableMap $point;
    final /* synthetic */ Promise $promise;
    final /* synthetic */ int $viewTag;
    Object L$0;
    int label;
    final /* synthetic */ CameraViewModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CameraViewModule$focus$1(CameraViewModule cameraViewModule, int i, Promise promise, ReadableMap readableMap, Continuation<? super CameraViewModule$focus$1> continuation) {
        super(2, continuation);
        this.this$0 = cameraViewModule;
        this.$viewTag = i;
        this.$promise = promise;
        this.$point = readableMap;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CameraViewModule$focus$1(this.this$0, this.$viewTag, this.$promise, this.$point, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CameraViewModule$focus$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Promise promise;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = this.this$0.findCameraView(this.$viewTag, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            promise = (Promise) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                promise.resolve((Object) null);
            } catch (Throwable th) {
                th = th;
            }
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        CameraView cameraView = (CameraView) obj;
        Promise promise2 = this.$promise;
        ReadableMap readableMap = this.$point;
        try {
            this.L$0 = promise2;
            this.label = 2;
            if (CameraView_FocusKt.focus(cameraView, readableMap, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            promise = promise2;
            promise.resolve((Object) null);
            return Unit.INSTANCE;
        } catch (Throwable th2) {
            th = th2;
            promise = promise2;
            th.printStackTrace();
            CameraError unknownCameraError = th instanceof CameraError ? th : new UnknownCameraError(th);
            promise.reject(unknownCameraError.getDomain() + "/" + unknownCameraError.getId(), unknownCameraError.getMessage(), unknownCameraError.getCause());
            return Unit.INSTANCE;
        }
    }
}
