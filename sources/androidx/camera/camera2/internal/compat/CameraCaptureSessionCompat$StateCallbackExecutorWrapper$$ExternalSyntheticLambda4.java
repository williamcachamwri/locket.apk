package androidx.camera.camera2.internal.compat;

import android.hardware.camera2.CameraCaptureSession;
import androidx.camera.camera2.internal.compat.CameraCaptureSessionCompat;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CameraCaptureSessionCompat$StateCallbackExecutorWrapper$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ CameraCaptureSessionCompat.StateCallbackExecutorWrapper f$0;
    public final /* synthetic */ CameraCaptureSession f$1;

    public /* synthetic */ CameraCaptureSessionCompat$StateCallbackExecutorWrapper$$ExternalSyntheticLambda4(CameraCaptureSessionCompat.StateCallbackExecutorWrapper stateCallbackExecutorWrapper, CameraCaptureSession cameraCaptureSession) {
        this.f$0 = stateCallbackExecutorWrapper;
        this.f$1 = cameraCaptureSession;
    }

    public final void run() {
        this.f$0.m112lambda$onConfigureFailed$1$androidxcameracamera2internalcompatCameraCaptureSessionCompat$StateCallbackExecutorWrapper(this.f$1);
    }
}
