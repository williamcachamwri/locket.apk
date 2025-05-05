package com.locket.Locket;

import android.os.Build;
import android.os.Trace;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class SystraceModule extends ReactContextBaseJavaModule {
    public String getName() {
        return "Systrace";
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public void beginAsyncEvent(String str, int i) {
        if (Build.VERSION.SDK_INT >= 29) {
            Trace.beginAsyncSection(str, i);
        }
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public void endAsyncEvent(String str, int i) {
        if (Build.VERSION.SDK_INT >= 29) {
            Trace.endAsyncSection(str, i);
        }
    }
}
