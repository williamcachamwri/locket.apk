package com.swmansion.reanimated.nativeProxy;

import com.facebook.jni.HybridData;

public class KeyboardEventDataUpdater {
    private final HybridData mHybridData;

    public native void keyboardEventDataUpdater(int i, int i2);

    private KeyboardEventDataUpdater(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
