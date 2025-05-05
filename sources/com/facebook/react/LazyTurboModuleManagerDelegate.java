package com.facebook.react;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.common.annotations.UnstableReactNativeAPI;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UnstableReactNativeAPI
public abstract class LazyTurboModuleManagerDelegate extends ReactPackageTurboModuleManagerDelegate {
    private final Map<String, TurboModule> mModules = new HashMap();
    private final List<ReactPackage> mPackages;
    private final ReactApplicationContext mReactContext;

    public boolean unstable_isLegacyModuleRegistered(String str) {
        return false;
    }

    public boolean unstable_shouldEnableLegacyModuleInterop() {
        return false;
    }

    public boolean unstable_shouldRouteTurboModulesThroughLegacyModuleInterop() {
        return false;
    }

    public LazyTurboModuleManagerDelegate(ReactApplicationContext reactApplicationContext, List<ReactPackage> list) {
        this.mPackages = list;
        this.mReactContext = reactApplicationContext;
    }

    public TurboModule getModule(String str) {
        TurboModule turboModule = this.mModules.get(str);
        if (turboModule == null) {
            for (ReactPackage next : this.mPackages) {
                if (next instanceof TurboReactPackage) {
                    try {
                        turboModule = (TurboModule) ((TurboReactPackage) next).getModule(str, this.mReactContext);
                        continue;
                    } catch (IllegalArgumentException unused) {
                    }
                    if (turboModule != null) {
                        this.mModules.put(str, turboModule);
                        turboModule.initialize();
                        return turboModule;
                    }
                } else {
                    throw new IllegalArgumentException("ReactPackage must be an instance of TurboReactPackage");
                }
            }
        }
        return turboModule;
    }

    public boolean unstable_isModuleRegistered(String str) {
        return getModule(str) != null;
    }

    public NativeModule getLegacyModule(String str) {
        throw new UnsupportedOperationException("Legacy Modules are not supported");
    }
}
