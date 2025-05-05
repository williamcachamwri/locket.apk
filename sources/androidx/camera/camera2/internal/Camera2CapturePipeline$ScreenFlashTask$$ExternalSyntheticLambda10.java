package androidx.camera.camera2.internal;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Camera2CapturePipeline$ScreenFlashTask$$ExternalSyntheticLambda10 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ AtomicReference f$0;

    public /* synthetic */ Camera2CapturePipeline$ScreenFlashTask$$ExternalSyntheticLambda10(AtomicReference atomicReference) {
        this.f$0 = atomicReference;
    }

    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
        return this.f$0.set(new Camera2CapturePipeline$ScreenFlashTask$$ExternalSyntheticLambda0(completer));
    }
}
