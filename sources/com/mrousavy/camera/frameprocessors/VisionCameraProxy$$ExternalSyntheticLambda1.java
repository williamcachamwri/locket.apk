package com.mrousavy.camera.frameprocessors;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VisionCameraProxy$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ VisionCameraProxy f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ VisionCameraProxy$$ExternalSyntheticLambda1(VisionCameraProxy visionCameraProxy, int i) {
        this.f$0 = visionCameraProxy;
        this.f$1 = i;
    }

    public final void run() {
        VisionCameraProxy.removeFrameProcessor$lambda$1(this.f$0, this.f$1);
    }
}
