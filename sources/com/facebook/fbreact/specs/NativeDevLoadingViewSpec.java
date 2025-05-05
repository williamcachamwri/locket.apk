package com.facebook.fbreact.specs;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class NativeDevLoadingViewSpec extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "DevLoadingView";

    @Nonnull
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public abstract void hide();

    @ReactMethod
    public abstract void showMessage(String str, @Nullable Double d, @Nullable Double d2);

    public NativeDevLoadingViewSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
}
