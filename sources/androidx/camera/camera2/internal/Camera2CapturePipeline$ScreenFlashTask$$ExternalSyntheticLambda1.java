package androidx.camera.camera2.internal;

import androidx.camera.camera2.internal.Camera2CapturePipeline;
import androidx.camera.core.impl.utils.futures.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Camera2CapturePipeline$ScreenFlashTask$$ExternalSyntheticLambda1 implements AsyncFunction {
    public final /* synthetic */ Camera2CapturePipeline.ScreenFlashTask f$0;
    public final /* synthetic */ ListenableFuture f$1;

    public /* synthetic */ Camera2CapturePipeline$ScreenFlashTask$$ExternalSyntheticLambda1(Camera2CapturePipeline.ScreenFlashTask screenFlashTask, ListenableFuture listenableFuture) {
        this.f$0 = screenFlashTask;
        this.f$1 = listenableFuture;
    }

    public final ListenableFuture apply(Object obj) {
        return this.f$0.m55lambda$preCapture$7$androidxcameracamera2internalCamera2CapturePipeline$ScreenFlashTask(this.f$1, obj);
    }
}
