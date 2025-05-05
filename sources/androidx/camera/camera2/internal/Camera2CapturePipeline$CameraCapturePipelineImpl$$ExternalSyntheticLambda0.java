package androidx.camera.camera2.internal;

import androidx.camera.camera2.internal.Camera2CapturePipeline;
import androidx.concurrent.futures.CallbackToFutureAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Camera2CapturePipeline$CameraCapturePipelineImpl$$ExternalSyntheticLambda0 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ Camera2CapturePipeline.CameraCapturePipelineImpl f$0;

    public /* synthetic */ Camera2CapturePipeline$CameraCapturePipelineImpl$$ExternalSyntheticLambda0(Camera2CapturePipeline.CameraCapturePipelineImpl cameraCapturePipelineImpl) {
        this.f$0 = cameraCapturePipelineImpl;
    }

    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
        return this.f$0.m43lambda$invokePostCapture$1$androidxcameracamera2internalCamera2CapturePipeline$CameraCapturePipelineImpl(completer);
    }
}
