package com.facebook.fbreact.specs;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import javax.annotation.Nonnull;

public abstract class NativeRedBoxSpec extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "RedBox";

    @ReactMethod
    public abstract void dismiss();

    @Nonnull
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public abstract void setExtraData(ReadableMap readableMap, String str);

    public NativeRedBoxSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
}
