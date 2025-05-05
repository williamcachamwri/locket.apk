package com.facebook.fbreact.specs;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import javax.annotation.Nonnull;

public abstract class NativeAccessibilityInfoSpec extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "AccessibilityInfo";

    @ReactMethod
    public abstract void announceForAccessibility(String str);

    @Nonnull
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void getRecommendedTimeoutMillis(double d, Callback callback) {
    }

    @ReactMethod
    public void isAccessibilityServiceEnabled(Callback callback) {
    }

    @ReactMethod
    public abstract void isReduceMotionEnabled(Callback callback);

    @ReactMethod
    public abstract void isTouchExplorationEnabled(Callback callback);

    @ReactMethod
    public abstract void setAccessibilityFocus(double d);

    public NativeAccessibilityInfoSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
}
