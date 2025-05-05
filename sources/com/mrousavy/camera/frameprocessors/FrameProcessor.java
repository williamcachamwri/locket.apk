package com.mrousavy.camera.frameprocessors;

import com.facebook.jni.HybridData;

public final class FrameProcessor {
    private final HybridData mHybridData;

    public native void call(Frame frame);

    public FrameProcessor(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
