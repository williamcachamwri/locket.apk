package com.reactnativekeyboardcontroller;

import com.facebook.react.TurboReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.uimanager.ViewManager;
import com.reactnativekeyboardcontroller.modules.KeyboardControllerModuleImpl;
import com.reactnativekeyboardcontroller.modules.StatusBarManagerCompatModuleImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u001a\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"Lcom/reactnativekeyboardcontroller/KeyboardControllerPackage;", "Lcom/facebook/react/TurboReactPackage;", "()V", "createViewManagers", "", "Lcom/facebook/react/uimanager/ViewManager;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "getModule", "Lcom/facebook/react/bridge/NativeModule;", "name", "", "getReactModuleInfoProvider", "Lcom/facebook/react/module/model/ReactModuleInfoProvider;", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: KeyboardControllerPackage.kt */
public final class KeyboardControllerPackage extends TurboReactPackage {
    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        return new KeyboardControllerPackage$$ExternalSyntheticLambda0();
    }

    public NativeModule getModule(String str, ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        if (Intrinsics.areEqual((Object) str, (Object) KeyboardControllerModuleImpl.NAME)) {
            return new KeyboardControllerModule(reactApplicationContext);
        }
        if (Intrinsics.areEqual((Object) str, (Object) StatusBarManagerCompatModuleImpl.NAME)) {
            return new StatusBarManagerCompatModule(reactApplicationContext);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static final Map getReactModuleInfoProvider$lambda$0() {
        Map hashMap = new HashMap();
        hashMap.put(KeyboardControllerModuleImpl.NAME, new ReactModuleInfo(KeyboardControllerModuleImpl.NAME, KeyboardControllerModuleImpl.NAME, false, false, true, false, false));
        hashMap.put(StatusBarManagerCompatModuleImpl.NAME, new ReactModuleInfo(StatusBarManagerCompatModuleImpl.NAME, StatusBarManagerCompatModuleImpl.NAME, false, false, true, false, false));
        return hashMap;
    }

    public List<ViewManager<?, ?>> createViewManagers(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        return CollectionsKt.listOf(new KeyboardControllerViewManager(reactApplicationContext), new KeyboardGestureAreaViewManager(reactApplicationContext), new OverKeyboardViewManager(reactApplicationContext));
    }
}
