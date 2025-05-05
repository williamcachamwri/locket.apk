package com.facebook.react.modules.deviceinfo;

import android.content.Context;
import com.facebook.fbreact.specs.NativeDeviceInfoSpec;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import java.util.HashMap;
import java.util.Map;

@ReactModule(name = "DeviceInfo")
public class DeviceInfoModule extends NativeDeviceInfoSpec implements LifecycleEventListener {
    private float mFontScale;
    private ReadableMap mPreviousDisplayMetrics;
    private final ReactApplicationContext mReactApplicationContext;

    public void onHostDestroy() {
    }

    public void onHostPause() {
    }

    public DeviceInfoModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        DisplayMetricsHolder.initDisplayMetricsIfNotInitialized(reactApplicationContext);
        this.mFontScale = reactApplicationContext.getResources().getConfiguration().fontScale;
        this.mReactApplicationContext = reactApplicationContext;
        reactApplicationContext.addLifecycleEventListener(this);
    }

    public DeviceInfoModule(Context context) {
        super((ReactApplicationContext) null);
        this.mReactApplicationContext = null;
        DisplayMetricsHolder.initDisplayMetricsIfNotInitialized(context);
        this.mFontScale = context.getResources().getConfiguration().fontScale;
    }

    public Map<String, Object> getTypedExportedConstants() {
        WritableMap displayMetricsWritableMap = DisplayMetricsHolder.getDisplayMetricsWritableMap((double) this.mFontScale);
        this.mPreviousDisplayMetrics = displayMetricsWritableMap.copy();
        HashMap hashMap = new HashMap();
        hashMap.put("Dimensions", displayMetricsWritableMap.toHashMap());
        return hashMap;
    }

    public void onHostResume() {
        ReactApplicationContext reactApplicationContext = this.mReactApplicationContext;
        if (reactApplicationContext != null) {
            float f = reactApplicationContext.getResources().getConfiguration().fontScale;
            if (this.mFontScale != f) {
                this.mFontScale = f;
                emitUpdateDimensionsEvent();
            }
        }
    }

    public void emitUpdateDimensionsEvent() {
        ReactApplicationContext reactApplicationContext = this.mReactApplicationContext;
        if (reactApplicationContext != null) {
            if (reactApplicationContext.hasActiveReactInstance()) {
                WritableMap displayMetricsWritableMap = DisplayMetricsHolder.getDisplayMetricsWritableMap((double) this.mFontScale);
                ReadableMap readableMap = this.mPreviousDisplayMetrics;
                if (readableMap == null) {
                    this.mPreviousDisplayMetrics = displayMetricsWritableMap.copy();
                } else if (!displayMetricsWritableMap.equals(readableMap)) {
                    this.mPreviousDisplayMetrics = displayMetricsWritableMap.copy();
                    this.mReactApplicationContext.emitDeviceEvent("didUpdateDimensions", displayMetricsWritableMap);
                }
            } else {
                ReactSoftExceptionLogger.logSoftException(NativeDeviceInfoSpec.NAME, new ReactNoCrashSoftException("No active CatalystInstance, cannot emitUpdateDimensionsEvent"));
            }
        }
    }

    public void invalidate() {
        super.invalidate();
        ReactApplicationContext reactApplicationContext = this.mReactApplicationContext;
        if (reactApplicationContext != null) {
            reactApplicationContext.removeLifecycleEventListener(this);
        }
    }
}
