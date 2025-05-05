package androidx.camera.camera2.interop;

import androidx.concurrent.futures.CallbackToFutureAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Camera2CameraControl$$ExternalSyntheticLambda4 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ Camera2CameraControl f$0;

    public /* synthetic */ Camera2CameraControl$$ExternalSyntheticLambda4(Camera2CameraControl camera2CameraControl) {
        this.f$0 = camera2CameraControl;
    }

    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
        return this.f$0.m127lambda$addCaptureRequestOptions$3$androidxcameracamera2interopCamera2CameraControl(completer);
    }
}
