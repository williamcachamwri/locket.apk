package com.facebook.fbreact.specs;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import javax.annotation.Nonnull;

public abstract class NativeFrameRateLoggerSpec extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "FrameRateLogger";

    @ReactMethod
    public abstract void beginScroll();

    @ReactMethod
    public abstract void endScroll();

    @Nonnull
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public abstract void setContext(String str);

    @ReactMethod
    public abstract void setGlobalOptions(ReadableMap readableMap);

    public NativeFrameRateLoggerSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
}
