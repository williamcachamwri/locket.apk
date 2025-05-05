package androidx.camera.camera2.internal;

import androidx.concurrent.futures.CallbackToFutureAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Camera2CameraControlImpl$$ExternalSyntheticLambda8 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ Camera2CameraControlImpl f$0;
    public final /* synthetic */ long f$1;

    public /* synthetic */ Camera2CameraControlImpl$$ExternalSyntheticLambda8(Camera2CameraControlImpl camera2CameraControlImpl, long j) {
        this.f$0 = camera2CameraControlImpl;
        this.f$1 = j;
    }

    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
        return this.f$0.m17lambda$waitForSessionUpdateId$3$androidxcameracamera2internalCamera2CameraControlImpl(this.f$1, completer);
    }
}
