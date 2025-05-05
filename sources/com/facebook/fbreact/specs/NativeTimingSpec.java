package com.facebook.fbreact.specs;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import javax.annotation.Nonnull;

public abstract class NativeTimingSpec extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "Timing";

    @ReactMethod
    public abstract void createTimer(double d, double d2, double d3, boolean z);

    @ReactMethod
    public abstract void deleteTimer(double d);

    @Nonnull
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public abstract void setSendIdleEvents(boolean z);

    public NativeTimingSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
}
