package com.ijzerenhein.sharedelement;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.UIManagerModule;
import java.util.Objects;

@ReactModule(name = "RNSharedElementTransition")
public class RNSharedElementModule extends ReactContextBaseJavaModule {
    public static final String MODULE_NAME = "RNSharedElementTransition";
    private final RNSharedElementNodeManager mNodeManager;

    public String getName() {
        return "RNSharedElementTransition";
    }

    public RNSharedElementModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mNodeManager = new RNSharedElementNodeManager(reactApplicationContext);
    }

    /* access modifiers changed from: package-private */
    public RNSharedElementNodeManager getNodeManager() {
        return this.mNodeManager;
    }

    @ReactMethod
    public void configure(ReadableMap readableMap, Promise promise) {
        RNSharedElementNodeManager rNSharedElementNodeManager = this.mNodeManager;
        Objects.requireNonNull(rNSharedElementNodeManager);
        ((UIManagerModule) getReactApplicationContext().getNativeModule(UIManagerModule.class)).prependUIBlock(new RNSharedElementModule$$ExternalSyntheticLambda0(rNSharedElementNodeManager));
        promise.resolve(true);
    }
}
