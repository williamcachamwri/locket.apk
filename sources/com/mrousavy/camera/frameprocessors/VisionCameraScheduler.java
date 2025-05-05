package com.mrousavy.camera.frameprocessors;

import com.facebook.jni.HybridData;
import com.mrousavy.camera.core.CameraQueues;

public class VisionCameraScheduler {
    private final HybridData mHybridData = initHybrid();

    private native HybridData initHybrid();

    /* access modifiers changed from: private */
    public native void trigger();

    private void scheduleTrigger() {
        CameraQueues.Companion.getVideoQueue().getHandler().post(new VisionCameraScheduler$$ExternalSyntheticLambda0(this));
    }
}
