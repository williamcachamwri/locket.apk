package com.facebook.fbreact.specs;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import javax.annotation.Nonnull;

public abstract class NativeDevSettingsSpec extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "DevSettings";

    @ReactMethod
    public abstract void addListener(String str);

    @ReactMethod
    public abstract void addMenuItem(String str);

    @Nonnull
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void onFastRefresh() {
    }

    @ReactMethod
    public abstract void reload();

    @ReactMethod
    public void reloadWithReason(String str) {
    }

    @ReactMethod
    public abstract void removeListeners(double d);

    @ReactMethod
    public abstract void setHotLoadingEnabled(boolean z);

    @ReactMethod
    public abstract void setIsDebuggingRemotely(boolean z);

    @ReactMethod
    public abstract void setIsShakeToShowDevMenuEnabled(boolean z);

    @ReactMethod
    public abstract void setProfilingEnabled(boolean z);

    @ReactMethod
    public abstract void toggleElementInspector();

    public NativeDevSettingsSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
}
