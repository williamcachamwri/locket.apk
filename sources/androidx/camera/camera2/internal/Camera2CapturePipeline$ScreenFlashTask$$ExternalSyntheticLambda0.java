package androidx.camera.camera2.internal;

import androidx.camera.camera2.internal.Camera2CapturePipeline;
import androidx.camera.core.ImageCapture;
import androidx.concurrent.futures.CallbackToFutureAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Camera2CapturePipeline$ScreenFlashTask$$ExternalSyntheticLambda0 implements ImageCapture.ScreenFlashListener {
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$0;

    public /* synthetic */ Camera2CapturePipeline$ScreenFlashTask$$ExternalSyntheticLambda0(CallbackToFutureAdapter.Completer completer) {
        this.f$0 = completer;
    }

    public final void onCompleted() {
        Camera2CapturePipeline.ScreenFlashTask.lambda$preCapture$0(this.f$0);
    }
}
