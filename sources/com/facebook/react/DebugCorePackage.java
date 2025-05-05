package com.facebook.react;

import com.facebook.fbreact.specs.NativeJSCHeapCaptureSpec;
import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.devsupport.JSCHeapCapture;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.views.traceupdateoverlay.TraceUpdateOverlayManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Provider;

public class DebugCorePackage extends TurboReactPackage implements ViewManagerOnDemandReactPackage {
    private Map<String, ModuleSpec> mViewManagers;

    static /* synthetic */ Map lambda$getReactModuleInfoProvider$0(Map map) {
        return map;
    }

    public NativeModule getModule(String str, ReactApplicationContext reactApplicationContext) {
        str.hashCode();
        if (str.equals(NativeJSCHeapCaptureSpec.NAME)) {
            return new JSCHeapCapture(reactApplicationContext);
        }
        throw new IllegalArgumentException("In DebugCorePackage, could not find Native module for " + str);
    }

    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        try {
            return (ReactModuleInfoProvider) Class.forName("com.facebook.react.DebugCorePackage$$ReactModuleInfoProvider").newInstance();
        } catch (ClassNotFoundException unused) {
            HashMap hashMap = new HashMap();
            Class cls = new Class[]{JSCHeapCapture.class}[0];
            ReactModule reactModule = (ReactModule) cls.getAnnotation(ReactModule.class);
            hashMap.put(reactModule.name(), new ReactModuleInfo(reactModule.name(), cls.getName(), reactModule.canOverrideExistingModule(), reactModule.needsEagerInit(), reactModule.isCxxModule(), TurboModule.class.isAssignableFrom(cls)));
            return new DebugCorePackage$$ExternalSyntheticLambda0(hashMap);
        } catch (InstantiationException e) {
            throw new RuntimeException("No ReactModuleInfoProvider for DebugCorePackage$$ReactModuleInfoProvider", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("No ReactModuleInfoProvider for DebugCorePackage$$ReactModuleInfoProvider", e2);
        }
    }

    private static void appendMap(Map<String, ModuleSpec> map, String str, Provider<? extends NativeModule> provider) {
        map.put(str, ModuleSpec.viewManagerSpec(provider));
    }

    private Map<String, ModuleSpec> getViewManagersMap() {
        if (this.mViewManagers == null) {
            HashMap hashMap = new HashMap();
            appendMap(hashMap, TraceUpdateOverlayManager.REACT_CLASS, new DebugCorePackage$$ExternalSyntheticLambda1());
            this.mViewManagers = hashMap;
        }
        return this.mViewManagers;
    }

    public List<ModuleSpec> getViewManagers(ReactApplicationContext reactApplicationContext) {
        return new ArrayList(getViewManagersMap().values());
    }

    public Collection<String> getViewManagerNames(ReactApplicationContext reactApplicationContext) {
        return getViewManagersMap().keySet();
    }

    public ViewManager createViewManager(ReactApplicationContext reactApplicationContext, String str) {
        ModuleSpec moduleSpec = getViewManagersMap().get(str);
        if (moduleSpec != null) {
            return (ViewManager) moduleSpec.getProvider().get();
        }
        return null;
    }
}
