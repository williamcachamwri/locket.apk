package androidx.camera.camera2.internal;

import androidx.camera.camera2.internal.Camera2CapturePipeline;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Camera2CapturePipeline$Pipeline$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ Camera2CapturePipeline.Pipeline f$0;

    public /* synthetic */ Camera2CapturePipeline$Pipeline$$ExternalSyntheticLambda4(Camera2CapturePipeline.Pipeline pipeline) {
        this.f$0 = pipeline;
    }

    public final void run() {
        this.f$0.executePostCapture();
    }
}
