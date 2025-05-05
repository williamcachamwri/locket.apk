package com.facebook.fbreact.specs;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import javax.annotation.Nonnull;

public abstract class NativeClipboardSpec extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "Clipboard";

    @Nonnull
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public abstract void getString(Promise promise);

    @ReactMethod
    public abstract void setString(String str);

    public NativeClipboardSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
}
