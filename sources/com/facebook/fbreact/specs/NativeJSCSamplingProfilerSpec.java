package com.facebook.fbreact.specs;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class NativeJSCSamplingProfilerSpec extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "JSCSamplingProfiler";

    @Nonnull
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public abstract void operationComplete(double d, @Nullable String str, @Nullable String str2);

    public NativeJSCSamplingProfilerSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
}
