package androidx.camera.camera2.internal;

import androidx.concurrent.futures.CallbackToFutureAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Camera2CameraImpl$$ExternalSyntheticLambda20 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ Camera2CameraImpl f$0;

    public /* synthetic */ Camera2CameraImpl$$ExternalSyntheticLambda20(Camera2CameraImpl camera2CameraImpl) {
        this.f$0 = camera2CameraImpl;
    }

    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
        return this.f$0.m32lambda$openCameraConfigAndClose$1$androidxcameracamera2internalCamera2CameraImpl(completer);
    }
}
