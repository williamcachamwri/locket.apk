package androidx.camera.camera2.internal;

import androidx.camera.camera2.internal.Camera2CapturePipeline;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Camera2CapturePipeline$ScreenFlashTask$$ExternalSyntheticLambda11 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ Camera2CapturePipeline.ScreenFlashTask f$0;
    public final /* synthetic */ AtomicReference f$1;

    public /* synthetic */ Camera2CapturePipeline$ScreenFlashTask$$ExternalSyntheticLambda11(Camera2CapturePipeline.ScreenFlashTask screenFlashTask, AtomicReference atomicReference) {
        this.f$0 = screenFlashTask;
        this.f$1 = atomicReference;
    }

    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
        return this.f$0.m51lambda$preCapture$3$androidxcameracamera2internalCamera2CapturePipeline$ScreenFlashTask(this.f$1, completer);
    }
}
