package com.mrousavy.camera.react;

import android.util.Log;
import androidx.camera.core.Preview;
import androidx.camera.view.PreviewView;
import com.mrousavy.camera.core.CameraConfiguration;
import com.mrousavy.camera.core.CameraSession;
import com.mrousavy.camera.core.types.CodeScannerOptions;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.mrousavy.camera.react.CameraView$update$1", f = "CameraView.kt", i = {}, l = {157}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: CameraView.kt */
final class CameraView$update$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $now;
    int label;
    final /* synthetic */ CameraView this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CameraView$update$1(CameraView cameraView, long j, Continuation<? super CameraView$update$1> continuation) {
        super(2, continuation);
        this.this$0 = cameraView;
        this.$now = j;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CameraView$update$1(this.this$0, this.$now, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CameraView$update$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CameraSession cameraSession$react_native_vision_camera_release = this.this$0.getCameraSession$react_native_vision_camera_release();
            final CameraView cameraView = this.this$0;
            final long j = this.$now;
            this.label = 1;
            if (cameraSession$react_native_vision_camera_release.configure(new Function1<CameraConfiguration, Unit>() {
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((CameraConfiguration) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(CameraConfiguration cameraConfiguration) {
                    Intrinsics.checkNotNullParameter(cameraConfiguration, "config");
                    if (cameraView.currentConfigureCall == j) {
                        cameraConfiguration.setCameraId(cameraView.getCameraId());
                        PreviewView previewView$react_native_vision_camera_release = cameraView.getPreviewView$react_native_vision_camera_release();
                        if (previewView$react_native_vision_camera_release != null) {
                            CameraConfiguration.Output.Enabled.Companion companion = CameraConfiguration.Output.Enabled.Companion;
                            Preview.SurfaceProvider surfaceProvider = previewView$react_native_vision_camera_release.getSurfaceProvider();
                            Intrinsics.checkNotNullExpressionValue(surfaceProvider, "getSurfaceProvider(...)");
                            cameraConfiguration.setPreview(companion.create(new CameraConfiguration.Preview(surfaceProvider)));
                        } else {
                            cameraConfiguration.setPreview(CameraConfiguration.Output.Disabled.Companion.create());
                        }
                        if (cameraView.getPhoto()) {
                            cameraConfiguration.setPhoto(CameraConfiguration.Output.Enabled.Companion.create(new CameraConfiguration.Photo(cameraView.isMirrored(), cameraView.getPhotoHdr(), cameraView.getPhotoQualityBalance())));
                        } else {
                            cameraConfiguration.setPhoto(CameraConfiguration.Output.Disabled.Companion.create());
                        }
                        if (cameraView.getVideo() || cameraView.getEnableFrameProcessor()) {
                            cameraConfiguration.setVideo(CameraConfiguration.Output.Enabled.Companion.create(new CameraConfiguration.Video(cameraView.isMirrored(), cameraView.getVideoHdr(), cameraView.getVideoBitRateOverride(), cameraView.getVideoBitRateMultiplier())));
                        } else {
                            cameraConfiguration.setVideo(CameraConfiguration.Output.Disabled.Companion.create());
                        }
                        if (cameraView.getEnableFrameProcessor()) {
                            cameraConfiguration.setFrameProcessor(CameraConfiguration.Output.Enabled.Companion.create(new CameraConfiguration.FrameProcessor(cameraView.isMirrored(), cameraView.getPixelFormat())));
                        } else {
                            cameraConfiguration.setFrameProcessor(CameraConfiguration.Output.Disabled.Companion.create());
                        }
                        if (cameraView.getAudio()) {
                            cameraConfiguration.setAudio(CameraConfiguration.Output.Enabled.Companion.create(new CameraConfiguration.Audio(Unit.INSTANCE)));
                        } else {
                            cameraConfiguration.setAudio(CameraConfiguration.Output.Disabled.Companion.create());
                        }
                        cameraConfiguration.setEnableLocation(cameraView.getEnableLocation() && cameraView.isActive());
                        CodeScannerOptions codeScannerOptions = cameraView.getCodeScannerOptions();
                        if (codeScannerOptions != null) {
                            cameraConfiguration.setCodeScanner(CameraConfiguration.Output.Enabled.Companion.create(new CameraConfiguration.CodeScanner(codeScannerOptions.getCodeTypes())));
                        } else {
                            cameraConfiguration.setCodeScanner(CameraConfiguration.Output.Disabled.Companion.create());
                        }
                        cameraConfiguration.setOutputOrientation(cameraView.getOutputOrientation());
                        cameraConfiguration.setFormat(cameraView.getFormat());
                        cameraConfiguration.setMinFps(cameraView.getMinFps());
                        cameraConfiguration.setMaxFps(cameraView.getMaxFps());
                        cameraConfiguration.setEnableLowLightBoost(cameraView.getLowLightBoost());
                        cameraConfiguration.setTorch(cameraView.getTorch());
                        cameraConfiguration.setExposure(Double.valueOf(cameraView.getExposure()));
                        cameraConfiguration.setZoom(cameraView.getZoom());
                        cameraConfiguration.setActive(cameraView.isActive());
                        return;
                    }
                    Log.i("CameraView", "A new configure { ... } call arrived, aborting this one...");
                    throw new CameraConfiguration.AbortThrow();
                }
            }, this) == coroutine_suspended) {
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
