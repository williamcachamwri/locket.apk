package androidx.camera.camera2.internal.compat;

import android.hardware.camera2.CameraDevice;
import androidx.camera.camera2.internal.compat.CameraDeviceCompat;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CameraDeviceCompat$StateCallbackExecutorWrapper$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ CameraDeviceCompat.StateCallbackExecutorWrapper f$0;
    public final /* synthetic */ CameraDevice f$1;

    public /* synthetic */ CameraDeviceCompat$StateCallbackExecutorWrapper$$ExternalSyntheticLambda2(CameraDeviceCompat.StateCallbackExecutorWrapper stateCallbackExecutorWrapper, CameraDevice cameraDevice) {
        this.f$0 = stateCallbackExecutorWrapper;
        this.f$1 = cameraDevice;
    }

    public final void run() {
        this.f$0.m119lambda$onOpened$0$androidxcameracamera2internalcompatCameraDeviceCompat$StateCallbackExecutorWrapper(this.f$1);
    }
}
