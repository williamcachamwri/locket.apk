package com.facebook.fbreact.specs;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import javax.annotation.Nonnull;

public abstract class NativeExceptionsManagerSpec extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "ExceptionsManager";

    @ReactMethod
    public void dismissRedbox() {
    }

    @Nonnull
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void reportException(ReadableMap readableMap) {
    }

    @ReactMethod
    public abstract void reportFatalException(String str, ReadableArray readableArray, double d);

    @ReactMethod
    public abstract void reportSoftException(String str, ReadableArray readableArray, double d);

    @ReactMethod
    public abstract void updateExceptionMessage(String str, ReadableArray readableArray, double d);

    public NativeExceptionsManagerSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
}
