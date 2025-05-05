package com.facebook.fbreact.specs;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class NativeAppearanceSpec extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "Appearance";

    @ReactMethod
    public abstract void addListener(String str);

    @Nullable
    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract String getColorScheme();

    @Nonnull
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public abstract void removeListeners(double d);

    @ReactMethod
    public void setColorScheme(String str) {
    }

    public NativeAppearanceSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
}
