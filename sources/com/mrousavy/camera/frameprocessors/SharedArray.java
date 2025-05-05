package com.mrousavy.camera.frameprocessors;

import com.facebook.jni.HybridData;
import java.nio.ByteBuffer;

public final class SharedArray {
    private final HybridData mHybridData;

    private native HybridData initHybrid(VisionCameraProxy visionCameraProxy, int i);

    private native HybridData initHybrid(VisionCameraProxy visionCameraProxy, ByteBuffer byteBuffer);

    public native ByteBuffer getByteBuffer();

    public native int getSize();

    private SharedArray(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    public SharedArray(VisionCameraProxy visionCameraProxy, int i) {
        this.mHybridData = initHybrid(visionCameraProxy, i);
    }

    public SharedArray(VisionCameraProxy visionCameraProxy, ByteBuffer byteBuffer) {
        this.mHybridData = initHybrid(visionCameraProxy, byteBuffer);
    }
}
