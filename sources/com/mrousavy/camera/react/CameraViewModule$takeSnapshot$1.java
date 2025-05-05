package com.mrousavy.camera.react;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;
import com.mrousavy.camera.core.types.TakeSnapshotOptions;
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
@DebugMetadata(c = "com.mrousavy.camera.react.CameraViewModule$takeSnapshot$1", f = "CameraViewModule.kt", i = {}, l = {104}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: CameraViewModule.kt */
final class CameraViewModule$takeSnapshot$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReadableMap $jsOptions;
    final /* synthetic */ Promise $promise;
    final /* synthetic */ int $viewTag;
    int label;
    final /* synthetic */ CameraViewModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CameraViewModule$takeSnapshot$1(CameraViewModule cameraViewModule, int i, ReadableMap readableMap, Promise promise, Continuation<? super CameraViewModule$takeSnapshot$1> continuation) {
        super(2, continuation);
        this.this$0 = cameraViewModule;
        this.$viewTag = i;
        this.$jsOptions = readableMap;
        this.$promise = promise;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CameraViewModule$takeSnapshot$1(this.this$0, this.$viewTag, this.$jsOptions, this.$promise, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CameraViewModule$takeSnapshot$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
        CameraViewModule cameraViewModule = this.this$0;
        ReadableMap readableMap = this.$jsOptions;
        Promise promise = this.$promise;
        if (UiThreadUtil.isOnUiThread()) {
            try {
                TakeSnapshotOptions.Companion companion = TakeSnapshotOptions.Companion;
                ReactApplicationContext access$getReactApplicationContext = cameraViewModule.getReactApplicationContext();
                Intrinsics.checkNotNullExpressionValue(access$getReactApplicationContext, "access$getReactApplicationContext(...)");
                promise.resolve(CameraView_TakeSnapshotKt.takeSnapshot(cameraView, companion.fromJSValue(access$getReactApplicationContext, readableMap)));
            } catch (Throwable th) {
                promise.reject(th);
            }
        } else {
            UiThreadUtil.runOnUiThread(new CameraViewModule$takeSnapshot$1$invokeSuspend$$inlined$runOnUiThread$1(cameraViewModule, readableMap, cameraView, promise));
        }
        return Unit.INSTANCE;
    }
}
