package androidx.camera.camera2.internal;

import androidx.camera.camera2.internal.Camera2CapturePipeline;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Camera2CapturePipeline$ScreenFlashTask$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ Camera2CapturePipeline.ScreenFlashTask f$0;
    public final /* synthetic */ AtomicReference f$1;
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$2;

    public /* synthetic */ Camera2CapturePipeline$ScreenFlashTask$$ExternalSyntheticLambda6(Camera2CapturePipeline.ScreenFlashTask screenFlashTask, AtomicReference atomicReference, CallbackToFutureAdapter.Completer completer) {
        this.f$0 = screenFlashTask;
        this.f$1 = atomicReference;
        this.f$2 = completer;
    }

    public final void run() {
        this.f$0.m50lambda$preCapture$2$androidxcameracamera2internalCamera2CapturePipeline$ScreenFlashTask(this.f$1, this.f$2);
    }
}
