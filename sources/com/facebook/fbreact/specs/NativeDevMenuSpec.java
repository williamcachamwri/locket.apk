package com.facebook.fbreact.specs;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import javax.annotation.Nonnull;

public abstract class NativeDevMenuSpec extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "DevMenu";

    @ReactMethod
    public abstract void debugRemotely(boolean z);

    @Nonnull
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public abstract void reload();

    @ReactMethod
    public abstract void setHotLoadingEnabled(boolean z);

    @ReactMethod
    public abstract void setProfilingEnabled(boolean z);

    @ReactMethod
    public abstract void show();

    public NativeDevMenuSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
}
