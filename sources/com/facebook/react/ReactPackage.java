package com.facebook.react;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.common.annotations.UnstableReactNativeAPI;
import com.facebook.react.uimanager.ViewManager;
import java.util.List;
import javax.annotation.Nullable;

public interface ReactPackage {
    List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext);

    List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext);

    @UnstableReactNativeAPI
    @Nullable
    NativeModule getModule(String str, ReactApplicationContext reactApplicationContext) {
        return null;
    }
}
