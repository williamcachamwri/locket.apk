package com.facebook.fbreact.specs;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class NativeShareModuleSpec extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "ShareModule";

    @Nonnull
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public abstract void share(ReadableMap readableMap, @Nullable String str, Promise promise);

    public NativeShareModuleSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
}
