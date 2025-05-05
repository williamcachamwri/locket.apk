package androidx.camera.camera2.internal;

import androidx.camera.camera2.internal.Camera2CapturePipeline;
import androidx.concurrent.futures.CallbackToFutureAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Camera2CapturePipeline$ScreenFlashTask$$ExternalSyntheticLambda5 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ Camera2CapturePipeline.ScreenFlashTask f$0;

    public /* synthetic */ Camera2CapturePipeline$ScreenFlashTask$$ExternalSyntheticLambda5(Camera2CapturePipeline.ScreenFlashTask screenFlashTask) {
        this.f$0 = screenFlashTask;
    }

    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
        return this.f$0.m53lambda$preCapture$5$androidxcameracamera2internalCamera2CapturePipeline$ScreenFlashTask(completer);
    }
}
