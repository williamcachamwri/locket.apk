package com.facebook.fbreact.specs;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import javax.annotation.Nonnull;

public abstract class NativeImageEditorSpec extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "ImageEditingManager";

    @ReactMethod
    public abstract void cropImage(String str, ReadableMap readableMap, Callback callback, Callback callback2);

    @Nonnull
    public String getName() {
        return NAME;
    }

    public NativeImageEditorSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
}
