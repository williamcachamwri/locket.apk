package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraDevice;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Camera2CameraImpl$2$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ CameraDevice f$0;

    public /* synthetic */ Camera2CameraImpl$2$$ExternalSyntheticLambda0(CameraDevice cameraDevice) {
        this.f$0 = cameraDevice;
    }

    public final void run() {
        this.f$0.close();
    }
}
