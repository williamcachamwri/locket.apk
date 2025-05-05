package androidx.camera.camera2.interop;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Camera2CameraControl$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ Camera2CameraControl f$0;

    public /* synthetic */ Camera2CameraControl$$ExternalSyntheticLambda7(Camera2CameraControl camera2CameraControl) {
        this.f$0 = camera2CameraControl;
    }

    public final void run() {
        this.f$0.completeInFlightUpdate();
    }
}
