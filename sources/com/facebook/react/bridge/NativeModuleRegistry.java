package com.facebook.react.bridge;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.systrace.Systrace;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class NativeModuleRegistry {
    private final String TAG = "NativeModuleRegistry";
    private final Map<String, ModuleHolder> mModules;
    private final ReactApplicationContext mReactApplicationContext;

    public NativeModuleRegistry(ReactApplicationContext reactApplicationContext, Map<String, ModuleHolder> map) {
        this.mReactApplicationContext = reactApplicationContext;
        this.mModules = map;
    }

    private Map<String, ModuleHolder> getModuleMap() {
        return this.mModules;
    }

    private ReactApplicationContext getReactApplicationContext() {
        return this.mReactApplicationContext;
    }

    /* access modifiers changed from: package-private */
    public Collection<JavaModuleWrapper> getJavaModules(JSInstance jSInstance) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry next : this.mModules.entrySet()) {
            if (!((ModuleHolder) next.getValue()).isCxxModule()) {
                arrayList.add(new JavaModuleWrapper(jSInstance, (ModuleHolder) next.getValue()));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public Collection<ModuleHolder> getCxxModules() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry next : this.mModules.entrySet()) {
            if (((ModuleHolder) next.getValue()).isCxxModule()) {
                arrayList.add((ModuleHolder) next.getValue());
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public void registerModules(NativeModuleRegistry nativeModuleRegistry) {
        Assertions.assertCondition(this.mReactApplicationContext.equals(nativeModuleRegistry.getReactApplicationContext()), "Extending native modules with non-matching application contexts.");
        for (Map.Entry next : nativeModuleRegistry.getModuleMap().entrySet()) {
            String str = (String) next.getKey();
            if (!this.mModules.containsKey(str)) {
                this.mModules.put(str, (ModuleHolder) next.getValue());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyJSInstanceDestroy() {
        this.mReactApplicationContext.assertOnNativeModulesQueueThread();
        Systrace.beginSection(0, "NativeModuleRegistry_notifyJSInstanceDestroy");
        try {
            for (ModuleHolder destroy : this.mModules.values()) {
                destroy.destroy();
            }
        } finally {
            Systrace.endSection(0);
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyJSInstanceInitialized() {
        this.mReactApplicationContext.assertOnNativeModulesQueueThread("From version React Native v0.44, native modules are explicitly not initialized on the UI thread.");
        ReactMarker.logMarker(ReactMarkerConstants.NATIVE_MODULE_INITIALIZE_START);
        Systrace.beginSection(0, "NativeModuleRegistry_notifyJSInstanceInitialized");
        try {
            for (ModuleHolder markInitializable : this.mModules.values()) {
                markInitializable.markInitializable();
            }
        } finally {
            Systrace.endSection(0);
            ReactMarker.logMarker(ReactMarkerConstants.NATIVE_MODULE_INITIALIZE_END);
        }
    }

    public void onBatchComplete() {
        ModuleHolder moduleHolder = this.mModules.get(UIManagerModule.NAME);
        if (moduleHolder != null && moduleHolder.hasInstance()) {
            ((OnBatchCompleteListener) moduleHolder.getModule()).onBatchComplete();
        }
    }

    public <T extends NativeModule> boolean hasModule(Class<T> cls) {
        return this.mModules.containsKey(((ReactModule) cls.getAnnotation(ReactModule.class)).name());
    }

    public <T extends NativeModule> T getModule(Class<T> cls) {
        ReactModule reactModule = (ReactModule) cls.getAnnotation(ReactModule.class);
        if (reactModule != null) {
            return ((ModuleHolder) Assertions.assertNotNull(this.mModules.get(reactModule.name()), reactModule.name() + " could not be found. Is it defined in " + cls.getName())).getModule();
        }
        throw new IllegalArgumentException("Could not find @ReactModule annotation in class " + cls.getName());
    }

    public boolean hasModule(String str) {
        return this.mModules.containsKey(str);
    }

    public NativeModule getModule(String str) {
        return ((ModuleHolder) Assertions.assertNotNull(this.mModules.get(str), "Could not find module with name " + str)).getModule();
    }

    public List<NativeModule> getAllModules() {
        ArrayList arrayList = new ArrayList();
        for (ModuleHolder module : this.mModules.values()) {
            arrayList.add(module.getModule());
        }
        return arrayList;
    }
}
