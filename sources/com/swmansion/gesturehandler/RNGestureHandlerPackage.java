package com.swmansion.gesturehandler;

import com.facebook.react.TurboReactPackage;
import com.facebook.react.ViewManagerOnDemandReactPackage;
import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.uimanager.ViewManager;
import com.swmansion.gesturehandler.react.RNGestureHandlerButtonViewManager;
import com.swmansion.gesturehandler.react.RNGestureHandlerModule;
import com.swmansion.gesturehandler.react.RNGestureHandlerRootViewManager;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J&\u0010\f\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0006H\u0016J\u001e\u0010\u0011\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\r0\u00122\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u001a\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0018\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u00122\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00192\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014R'\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058BX\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\u001a"}, d2 = {"Lcom/swmansion/gesturehandler/RNGestureHandlerPackage;", "Lcom/facebook/react/TurboReactPackage;", "Lcom/facebook/react/ViewManagerOnDemandReactPackage;", "()V", "viewManagers", "", "", "Lcom/facebook/react/bridge/ModuleSpec;", "getViewManagers", "()Ljava/util/Map;", "viewManagers$delegate", "Lkotlin/Lazy;", "createViewManager", "Lcom/facebook/react/uimanager/ViewManager;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "viewManagerName", "createViewManagers", "", "getModule", "Lcom/facebook/react/bridge/NativeModule;", "name", "getReactModuleInfoProvider", "Lcom/facebook/react/module/model/ReactModuleInfoProvider;", "getViewManagerNames", "", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RNGestureHandlerPackage.kt */
public final class RNGestureHandlerPackage extends TurboReactPackage implements ViewManagerOnDemandReactPackage {
    private final Lazy viewManagers$delegate = LazyKt.lazy(RNGestureHandlerPackage$viewManagers$2.INSTANCE);

    private final Map<String, ModuleSpec> getViewManagers() {
        return (Map) this.viewManagers$delegate.getValue();
    }

    public List<ViewManager<?, ?>> createViewManagers(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        return CollectionsKt.listOf(new RNGestureHandlerRootViewManager(), new RNGestureHandlerButtonViewManager());
    }

    public List<String> getViewManagerNames(ReactApplicationContext reactApplicationContext) {
        return CollectionsKt.toList(getViewManagers().keySet());
    }

    /* access modifiers changed from: protected */
    public List<ModuleSpec> getViewManagers(ReactApplicationContext reactApplicationContext) {
        return CollectionsKt.toMutableList(getViewManagers().values());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000d, code lost:
        r2 = r2.getProvider();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.react.uimanager.ViewManager<?, ?> createViewManager(com.facebook.react.bridge.ReactApplicationContext r2, java.lang.String r3) {
        /*
            r1 = this;
            java.util.Map r2 = r1.getViewManagers()
            java.lang.Object r2 = r2.get(r3)
            com.facebook.react.bridge.ModuleSpec r2 = (com.facebook.react.bridge.ModuleSpec) r2
            r3 = 0
            if (r2 == 0) goto L_0x001a
            javax.inject.Provider r2 = r2.getProvider()
            if (r2 == 0) goto L_0x001a
            java.lang.Object r2 = r2.get()
            com.facebook.react.bridge.NativeModule r2 = (com.facebook.react.bridge.NativeModule) r2
            goto L_0x001b
        L_0x001a:
            r2 = r3
        L_0x001b:
            boolean r0 = r2 instanceof com.facebook.react.uimanager.ViewManager
            if (r0 == 0) goto L_0x0022
            r3 = r2
            com.facebook.react.uimanager.ViewManager r3 = (com.facebook.react.uimanager.ViewManager) r3
        L_0x0022:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.RNGestureHandlerPackage.createViewManager(com.facebook.react.bridge.ReactApplicationContext, java.lang.String):com.facebook.react.uimanager.ViewManager");
    }

    public NativeModule getModule(String str, ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        if (Intrinsics.areEqual((Object) str, (Object) "RNGestureHandlerModule")) {
            return new RNGestureHandlerModule(reactApplicationContext);
        }
        return null;
    }

    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        try {
            Object newInstance = Class.forName("com.swmansion.gesturehandler.RNGestureHandlerPackage$$ReactModuleInfoProvider").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type com.facebook.react.module.model.ReactModuleInfoProvider");
            return (ReactModuleInfoProvider) newInstance;
        } catch (ClassNotFoundException unused) {
            return new RNGestureHandlerPackage$$ExternalSyntheticLambda0();
        } catch (InstantiationException e) {
            throw new RuntimeException("No ReactModuleInfoProvider for RNGestureHandlerPackage$$ReactModuleInfoProvider", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("No ReactModuleInfoProvider for RNGestureHandlerPackage$$ReactModuleInfoProvider", e2);
        }
    }

    /* access modifiers changed from: private */
    public static final Map getReactModuleInfoProvider$lambda$0() {
        Annotation annotation = RNGestureHandlerModule.class.getAnnotation(ReactModule.class);
        Intrinsics.checkNotNull(annotation);
        ReactModule reactModule = (ReactModule) annotation;
        return MapsKt.mutableMapOf(TuplesKt.to("RNGestureHandlerModule", new ReactModuleInfo(reactModule.name(), RNGestureHandlerModule.class.getName(), reactModule.canOverrideExistingModule(), reactModule.needsEagerInit(), true, reactModule.isCxxModule(), true)));
    }
}
