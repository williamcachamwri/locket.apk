package com.facebook.react;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.CxxModuleWrapper;
import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.turbomodule.core.TurboModuleManagerDelegate;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.inject.Provider;

public abstract class ReactPackageTurboModuleManagerDelegate extends TurboModuleManagerDelegate {
    private final List<ModuleProvider> mModuleProviders = new ArrayList();
    private final Map<ModuleProvider, Map<String, ReactModuleInfo>> mPackageModuleInfos = new HashMap();
    private final boolean mShouldEnableLegacyModuleInterop;
    private final boolean mShouldRouteTurboModulesThroughLegacyModuleInterop;

    interface ModuleProvider {
        NativeModule getModule(String str);
    }

    protected ReactPackageTurboModuleManagerDelegate() {
        boolean z = true;
        boolean z2 = ReactFeatureFlags.enableBridgelessArchitecture && ReactFeatureFlags.unstable_useTurboModuleInterop;
        this.mShouldEnableLegacyModuleInterop = z2;
        this.mShouldRouteTurboModulesThroughLegacyModuleInterop = (!z2 || !ReactFeatureFlags.unstable_useTurboModuleInteropForAllTurboModules) ? false : z;
    }

    protected ReactPackageTurboModuleManagerDelegate(ReactApplicationContext reactApplicationContext, List<ReactPackage> list) {
        ReactModuleInfo reactModuleInfo;
        ReactApplicationContext reactApplicationContext2 = reactApplicationContext;
        boolean z = true;
        boolean z2 = ReactFeatureFlags.enableBridgelessArchitecture && ReactFeatureFlags.unstable_useTurboModuleInterop;
        this.mShouldEnableLegacyModuleInterop = z2;
        this.mShouldRouteTurboModulesThroughLegacyModuleInterop = (!z2 || !ReactFeatureFlags.unstable_useTurboModuleInteropForAllTurboModules) ? false : z;
        for (ReactPackage next : list) {
            if (next instanceof TurboReactPackage) {
                TurboReactPackage turboReactPackage = (TurboReactPackage) next;
                ReactPackageTurboModuleManagerDelegate$$ExternalSyntheticLambda0 reactPackageTurboModuleManagerDelegate$$ExternalSyntheticLambda0 = new ReactPackageTurboModuleManagerDelegate$$ExternalSyntheticLambda0(turboReactPackage, reactApplicationContext2);
                this.mModuleProviders.add(reactPackageTurboModuleManagerDelegate$$ExternalSyntheticLambda0);
                this.mPackageModuleInfos.put(reactPackageTurboModuleManagerDelegate$$ExternalSyntheticLambda0, turboReactPackage.getReactModuleInfoProvider().getReactModuleInfos());
            } else if (shouldSupportLegacyPackages() && (next instanceof LazyReactPackage)) {
                LazyReactPackage lazyReactPackage = (LazyReactPackage) next;
                List<ModuleSpec> nativeModules = lazyReactPackage.getNativeModules(reactApplicationContext2);
                HashMap hashMap = new HashMap();
                for (ModuleSpec next2 : nativeModules) {
                    hashMap.put(next2.getName(), next2.getProvider());
                }
                ReactPackageTurboModuleManagerDelegate$$ExternalSyntheticLambda1 reactPackageTurboModuleManagerDelegate$$ExternalSyntheticLambda1 = new ReactPackageTurboModuleManagerDelegate$$ExternalSyntheticLambda1(hashMap);
                this.mModuleProviders.add(reactPackageTurboModuleManagerDelegate$$ExternalSyntheticLambda1);
                this.mPackageModuleInfos.put(reactPackageTurboModuleManagerDelegate$$ExternalSyntheticLambda1, lazyReactPackage.getReactModuleInfoProvider().getReactModuleInfos());
            } else if ((!shouldSupportLegacyPackages() || !(next instanceof ReactInstancePackage)) && shouldSupportLegacyPackages()) {
                List<NativeModule> createNativeModules = next.createNativeModules(reactApplicationContext2);
                HashMap hashMap2 = new HashMap();
                HashMap hashMap3 = new HashMap();
                for (NativeModule next3 : createNativeModules) {
                    Class<?> cls = next3.getClass();
                    ReactModule reactModule = (ReactModule) cls.getAnnotation(ReactModule.class);
                    String name = reactModule != null ? reactModule.name() : next3.getName();
                    if (reactModule != null) {
                        reactModuleInfo = new ReactModuleInfo(name, cls.getName(), reactModule.canOverrideExistingModule(), true, reactModule.isCxxModule(), TurboModule.class.isAssignableFrom(cls));
                    } else {
                        reactModuleInfo = new ReactModuleInfo(name, cls.getName(), next3.canOverrideExistingModule(), true, CxxModuleWrapper.class.isAssignableFrom(cls), TurboModule.class.isAssignableFrom(cls));
                    }
                    hashMap3.put(name, reactModuleInfo);
                    hashMap2.put(name, next3);
                }
                Objects.requireNonNull(hashMap2);
                ReactPackageTurboModuleManagerDelegate$$ExternalSyntheticLambda2 reactPackageTurboModuleManagerDelegate$$ExternalSyntheticLambda2 = new ReactPackageTurboModuleManagerDelegate$$ExternalSyntheticLambda2(hashMap2);
                this.mModuleProviders.add(reactPackageTurboModuleManagerDelegate$$ExternalSyntheticLambda2);
                this.mPackageModuleInfos.put(reactPackageTurboModuleManagerDelegate$$ExternalSyntheticLambda2, hashMap3);
            }
        }
    }

    static /* synthetic */ NativeModule lambda$new$1(Map map, String str) {
        Provider provider = (Provider) map.get(str);
        if (provider != null) {
            return (NativeModule) provider.get();
        }
        return null;
    }

    public boolean unstable_shouldEnableLegacyModuleInterop() {
        return this.mShouldEnableLegacyModuleInterop;
    }

    public boolean unstable_shouldRouteTurboModulesThroughLegacyModuleInterop() {
        return this.mShouldRouteTurboModulesThroughLegacyModuleInterop;
    }

    public TurboModule getModule(String str) {
        NativeModule module;
        NativeModule nativeModule = null;
        for (ModuleProvider next : this.mModuleProviders) {
            try {
                ReactModuleInfo reactModuleInfo = (ReactModuleInfo) this.mPackageModuleInfos.get(next).get(str);
                if (reactModuleInfo != null && reactModuleInfo.isTurboModule()) {
                    if ((nativeModule == null || reactModuleInfo.canOverrideExistingModule()) && (module = next.getModule(str)) != null) {
                        nativeModule = module;
                    }
                }
            } catch (IllegalArgumentException unused) {
            }
        }
        if (!(nativeModule instanceof TurboModule)) {
            return null;
        }
        return (TurboModule) nativeModule;
    }

    public boolean unstable_isModuleRegistered(String str) {
        for (ModuleProvider moduleProvider : this.mModuleProviders) {
            ReactModuleInfo reactModuleInfo = (ReactModuleInfo) this.mPackageModuleInfos.get(moduleProvider).get(str);
            if (reactModuleInfo != null && reactModuleInfo.isTurboModule()) {
                return true;
            }
        }
        return false;
    }

    public boolean unstable_isLegacyModuleRegistered(String str) {
        for (ModuleProvider moduleProvider : this.mModuleProviders) {
            ReactModuleInfo reactModuleInfo = (ReactModuleInfo) this.mPackageModuleInfos.get(moduleProvider).get(str);
            if (reactModuleInfo != null && !reactModuleInfo.isTurboModule()) {
                return true;
            }
        }
        return false;
    }

    public NativeModule getLegacyModule(String str) {
        NativeModule module;
        if (!unstable_shouldEnableLegacyModuleInterop()) {
            return null;
        }
        NativeModule nativeModule = null;
        for (ModuleProvider next : this.mModuleProviders) {
            try {
                ReactModuleInfo reactModuleInfo = (ReactModuleInfo) this.mPackageModuleInfos.get(next).get(str);
                if (reactModuleInfo != null && !reactModuleInfo.isTurboModule()) {
                    if ((nativeModule == null || reactModuleInfo.canOverrideExistingModule()) && (module = next.getModule(str)) != null) {
                        nativeModule = module;
                    }
                }
            } catch (IllegalArgumentException unused) {
            }
        }
        if (!(!(nativeModule instanceof TurboModule))) {
            return null;
        }
        return nativeModule;
    }

    public List<String> getEagerInitModuleNames() {
        ArrayList arrayList = new ArrayList();
        for (ModuleProvider moduleProvider : this.mModuleProviders) {
            for (ReactModuleInfo reactModuleInfo : this.mPackageModuleInfos.get(moduleProvider).values()) {
                if (reactModuleInfo.isTurboModule() && reactModuleInfo.needsEagerInit()) {
                    arrayList.add(reactModuleInfo.name());
                }
            }
        }
        return arrayList;
    }

    private boolean shouldSupportLegacyPackages() {
        return unstable_shouldEnableLegacyModuleInterop();
    }

    public static abstract class Builder {
        private ReactApplicationContext mContext;
        private List<ReactPackage> mPackages;

        /* access modifiers changed from: protected */
        public abstract ReactPackageTurboModuleManagerDelegate build(ReactApplicationContext reactApplicationContext, List<ReactPackage> list);

        public Builder setPackages(List<ReactPackage> list) {
            this.mPackages = new ArrayList(list);
            return this;
        }

        public Builder setReactApplicationContext(ReactApplicationContext reactApplicationContext) {
            this.mContext = reactApplicationContext;
            return this;
        }

        public ReactPackageTurboModuleManagerDelegate build() {
            Assertions.assertNotNull(this.mContext, "The ReactApplicationContext must be provided to create ReactPackageTurboModuleManagerDelegate");
            Assertions.assertNotNull(this.mPackages, "A set of ReactPackages must be provided to create ReactPackageTurboModuleManagerDelegate");
            return build(this.mContext, this.mPackages);
        }
    }
}
