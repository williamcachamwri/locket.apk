package androidx.camera.camera2.internal;

import androidx.camera.core.impl.CameraCaptureCallback;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Camera2CameraControlImpl$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Camera2CameraControlImpl f$0;
    public final /* synthetic */ CameraCaptureCallback f$1;

    public /* synthetic */ Camera2CameraControlImpl$$ExternalSyntheticLambda0(Camera2CameraControlImpl camera2CameraControlImpl, CameraCaptureCallback cameraCaptureCallback) {
        this.f$0 = camera2CameraControlImpl;
        this.f$1 = cameraCaptureCallback;
    }

    public final void run() {
        this.f$0.m13lambda$removeSessionCameraCaptureCallback$9$androidxcameracamera2internalCamera2CameraControlImpl(this.f$1);
    }
}
