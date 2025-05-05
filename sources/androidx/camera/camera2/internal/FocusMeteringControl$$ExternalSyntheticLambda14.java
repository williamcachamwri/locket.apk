package androidx.camera.camera2.internal;

import android.hardware.camera2.TotalCaptureResult;
import androidx.camera.camera2.internal.Camera2CameraControlImpl;
import androidx.concurrent.futures.CallbackToFutureAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FocusMeteringControl$$ExternalSyntheticLambda14 implements Camera2CameraControlImpl.CaptureResultListener {
    public final /* synthetic */ FocusMeteringControl f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$2;

    public /* synthetic */ FocusMeteringControl$$ExternalSyntheticLambda14(FocusMeteringControl focusMeteringControl, long j, CallbackToFutureAdapter.Completer completer) {
        this.f$0 = focusMeteringControl;
        this.f$1 = j;
        this.f$2 = completer;
    }

    public final boolean onCaptureResult(TotalCaptureResult totalCaptureResult) {
        return this.f$0.m71lambda$enableExternalFlashAeMode$6$androidxcameracamera2internalFocusMeteringControl(this.f$1, this.f$2, totalCaptureResult);
    }
}
