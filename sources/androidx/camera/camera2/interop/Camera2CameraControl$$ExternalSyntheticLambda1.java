package androidx.camera.camera2.interop;

import androidx.concurrent.futures.CallbackToFutureAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Camera2CameraControl$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ Camera2CameraControl f$0;
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$1;

    public /* synthetic */ Camera2CameraControl$$ExternalSyntheticLambda1(Camera2CameraControl camera2CameraControl, CallbackToFutureAdapter.Completer completer) {
        this.f$0 = camera2CameraControl;
        this.f$1 = completer;
    }

    public final void run() {
        this.f$0.m126lambda$addCaptureRequestOptions$2$androidxcameracamera2interopCamera2CameraControl(this.f$1);
    }
}
