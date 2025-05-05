package com.facebook.fbreact.specs;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import javax.annotation.Nonnull;

public abstract class NativeLinkingManagerSpec extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "LinkingManager";

    @ReactMethod
    public abstract void addListener(String str);

    @ReactMethod
    public abstract void canOpenURL(String str, Promise promise);

    @ReactMethod
    public abstract void getInitialURL(Promise promise);

    @Nonnull
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public abstract void openSettings(Promise promise);

    @ReactMethod
    public abstract void openURL(String str, Promise promise);

    @ReactMethod
    public abstract void removeListeners(double d);

    public NativeLinkingManagerSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
}
