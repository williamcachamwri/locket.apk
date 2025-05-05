package com.facebook.fbreact.specs;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import javax.annotation.Nonnull;

public abstract class NativeAlertManagerSpec extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "AlertManager";

    @ReactMethod
    public abstract void alertWithArgs(ReadableMap readableMap, Callback callback);

    @Nonnull
    public String getName() {
        return NAME;
    }

    public NativeAlertManagerSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
}
