package com.facebook.react.runtime;

import com.facebook.jni.HybridData;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.modules.core.JavaScriptTimerExecutor;
import com.facebook.soloader.SoLoader;

class JSTimerExecutor implements JavaScriptTimerExecutor {
    private final HybridData mHybridData;

    private native void callTimers(WritableNativeArray writableNativeArray);

    public void callIdleCallbacks(double d) {
    }

    public void emitTimeDriftWarning(String str) {
    }

    static {
        SoLoader.loadLibrary("rninstance");
    }

    public JSTimerExecutor(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    public void callTimers(WritableArray writableArray) {
        callTimers((WritableNativeArray) writableArray);
    }
}
