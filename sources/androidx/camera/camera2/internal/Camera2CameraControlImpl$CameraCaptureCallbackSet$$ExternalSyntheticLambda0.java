package androidx.camera.camera2.internal;

import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraCaptureFailure;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Camera2CameraControlImpl$CameraCaptureCallbackSet$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ CameraCaptureCallback f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ CameraCaptureFailure f$2;

    public /* synthetic */ Camera2CameraControlImpl$CameraCaptureCallbackSet$$ExternalSyntheticLambda0(CameraCaptureCallback cameraCaptureCallback, int i, CameraCaptureFailure cameraCaptureFailure) {
        this.f$0 = cameraCaptureCallback;
        this.f$1 = i;
        this.f$2 = cameraCaptureFailure;
    }

    public final void run() {
        this.f$0.onCaptureFailed(this.f$1, this.f$2);
    }
}
