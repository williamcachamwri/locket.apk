package androidx.camera.camera2.internal;

import android.hardware.camera2.TotalCaptureResult;
import androidx.camera.camera2.internal.Camera2CameraControlImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FocusMeteringControl$$ExternalSyntheticLambda1 implements Camera2CameraControlImpl.CaptureResultListener {
    public final /* synthetic */ FocusMeteringControl f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ FocusMeteringControl$$ExternalSyntheticLambda1(FocusMeteringControl focusMeteringControl, boolean z, long j) {
        this.f$0 = focusMeteringControl;
        this.f$1 = z;
        this.f$2 = j;
    }

    public final boolean onCaptureResult(TotalCaptureResult totalCaptureResult) {
        return this.f$0.m74lambda$executeMeteringAction$7$androidxcameracamera2internalFocusMeteringControl(this.f$1, this.f$2, totalCaptureResult);
    }
}
