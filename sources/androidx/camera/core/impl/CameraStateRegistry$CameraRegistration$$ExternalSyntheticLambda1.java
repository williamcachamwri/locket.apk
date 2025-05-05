package androidx.camera.core.impl;

import androidx.camera.core.impl.CameraStateRegistry;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CameraStateRegistry$CameraRegistration$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ CameraStateRegistry.OnConfigureAvailableListener f$0;

    public /* synthetic */ CameraStateRegistry$CameraRegistration$$ExternalSyntheticLambda1(CameraStateRegistry.OnConfigureAvailableListener onConfigureAvailableListener) {
        this.f$0 = onConfigureAvailableListener;
    }

    public final void run() {
        this.f$0.onConfigureAvailable();
    }
}
