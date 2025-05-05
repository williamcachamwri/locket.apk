package com.mrousavy.camera.react;

import androidx.camera.view.PreviewView;
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
@DebugMetadata(c = "com.mrousavy.camera.react.CameraView$updatePreview$1", f = "CameraView.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: CameraView.kt */
final class CameraView$updatePreview$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ CameraView this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CameraView$updatePreview$1(CameraView cameraView, Continuation<? super CameraView$updatePreview$1> continuation) {
        super(2, continuation);
        this.this$0 = cameraView;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CameraView$updatePreview$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CameraView$updatePreview$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.this$0.getPreview() && this.this$0.getPreviewView$react_native_vision_camera_release() == null) {
                CameraView cameraView = this.this$0;
                cameraView.setPreviewView$react_native_vision_camera_release(cameraView.createPreviewView());
                CameraView cameraView2 = this.this$0;
                cameraView2.addView(cameraView2.getPreviewView$react_native_vision_camera_release());
            } else if (!this.this$0.getPreview() && this.this$0.getPreviewView$react_native_vision_camera_release() != null) {
                CameraView cameraView3 = this.this$0;
                cameraView3.removeView(cameraView3.getPreviewView$react_native_vision_camera_release());
                this.this$0.setPreviewView$react_native_vision_camera_release((PreviewView) null);
            }
            PreviewView previewView$react_native_vision_camera_release = this.this$0.getPreviewView$react_native_vision_camera_release();
            if (previewView$react_native_vision_camera_release != null) {
                CameraView cameraView4 = this.this$0;
                previewView$react_native_vision_camera_release.setImplementationMode(cameraView4.getAndroidPreviewViewType().toPreviewImplementationMode());
                previewView$react_native_vision_camera_release.setScaleType(cameraView4.getResizeMode().toScaleType());
            }
            this.this$0.update();
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
