package com.mrousavy.camera.frameprocessors;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VisionCameraProxy$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ VisionCameraProxy f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ FrameProcessor f$2;

    public /* synthetic */ VisionCameraProxy$$ExternalSyntheticLambda0(VisionCameraProxy visionCameraProxy, int i, FrameProcessor frameProcessor) {
        this.f$0 = visionCameraProxy;
        this.f$1 = i;
        this.f$2 = frameProcessor;
    }

    public final void run() {
        VisionCameraProxy.setFrameProcessor$lambda$0(this.f$0, this.f$1, this.f$2);
    }
}
