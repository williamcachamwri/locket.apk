package androidx.camera.camera2.internal;

import androidx.camera.camera2.internal.Camera2CapturePipeline;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Camera2CapturePipeline$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Camera2CameraControlImpl f$0;
    public final /* synthetic */ Camera2CapturePipeline.ResultListener f$1;

    public /* synthetic */ Camera2CapturePipeline$$ExternalSyntheticLambda0(Camera2CameraControlImpl camera2CameraControlImpl, Camera2CapturePipeline.ResultListener resultListener) {
        this.f$0 = camera2CameraControlImpl;
        this.f$1 = resultListener;
    }

    public final void run() {
        this.f$0.removeCaptureResultListener(this.f$1);
    }
}
