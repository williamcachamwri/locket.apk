package com.facebook.fbreact.specs;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class NativeDevToolsSettingsManagerSpec extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "DevToolsSettingsManager";

    @Nullable
    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract String getConsolePatchSettings();

    @Nonnull
    public String getName() {
        return NAME;
    }

    @Nullable
    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getProfilingSettings() {
        return null;
    }

    @ReactMethod
    public abstract void setConsolePatchSettings(String str);

    @ReactMethod
    public void setProfilingSettings(String str) {
    }

    public NativeDevToolsSettingsManagerSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
}
