package androidx.camera.camera2.internal;

import android.hardware.camera2.TotalCaptureResult;
import androidx.camera.camera2.internal.Camera2CapturePipeline;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Camera2CapturePipeline$TorchTask$$ExternalSyntheticLambda0 implements Camera2CapturePipeline.ResultListener.Checker {
    public final boolean check(TotalCaptureResult totalCaptureResult) {
        return Camera2CapturePipeline.is3AConverged(totalCaptureResult, true);
    }
}
