package com.mrousavy.camera.react;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.mrousavy.camera.core.CameraError;
import com.mrousavy.camera.core.types.RecordVideoOptions;
import com.mrousavy.camera.react.utils.CallbackPromiseKt;
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
@DebugMetadata(c = "com.mrousavy.camera.react.CameraViewModule$startRecording$1", f = "CameraViewModule.kt", i = {}, l = {121}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: CameraViewModule.kt */
final class CameraViewModule$startRecording$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReadableMap $jsOptions;
    final /* synthetic */ Callback $onRecordCallback;
    final /* synthetic */ int $viewTag;
    int label;
    final /* synthetic */ CameraViewModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CameraViewModule$startRecording$1(CameraViewModule cameraViewModule, int i, ReadableMap readableMap, Callback callback, Continuation<? super CameraViewModule$startRecording$1> continuation) {
        super(2, continuation);
        this.this$0 = cameraViewModule;
        this.$viewTag = i;
        this.$jsOptions = readableMap;
        this.$onRecordCallback = callback;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CameraViewModule$startRecording$1(this.this$0, this.$viewTag, this.$jsOptions, this.$onRecordCallback, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CameraViewModule$startRecording$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
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
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        CameraView cameraView = (CameraView) obj;
        try {
            RecordVideoOptions.Companion companion = RecordVideoOptions.Companion;
            ReactApplicationContext access$getReactApplicationContext = this.this$0.getReactApplicationContext();
            Intrinsics.checkNotNullExpressionValue(access$getReactApplicationContext, "access$getReactApplicationContext(...)");
            CameraView_RecordVideoKt.startRecording(cameraView, companion.fromJSValue(access$getReactApplicationContext, this.$jsOptions), this.$onRecordCallback);
        } catch (CameraError e) {
            String domain = e.getDomain();
            this.$onRecordCallback.invoke(null, CallbackPromiseKt.makeErrorMap$default(domain + "/" + e.getId(), e.getMessage(), e, (WritableMap) null, 8, (Object) null));
        } catch (Throwable th) {
            Throwable th2 = th;
            this.$onRecordCallback.invoke(null, CallbackPromiseKt.makeErrorMap$default("capture/unknown", "An unknown error occurred while trying to start a video recording! " + th2.getMessage(), th2, (WritableMap) null, 8, (Object) null));
        }
        return Unit.INSTANCE;
    }
}
