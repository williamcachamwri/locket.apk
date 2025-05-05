package com.reactnativekeyboardcontroller;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.view.ReactViewGroup;
import com.facebook.react.views.view.ReactViewManager;
import com.reactnativekeyboardcontroller.managers.KeyboardControllerViewManagerImpl;
import com.reactnativekeyboardcontroller.views.EdgeToEdgeReactViewGroup;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0014\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fH\u0016J\b\u0010\u000f\u001a\u00020\rH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0014J\u0018\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0007J\u0018\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u0016H\u0007J\u0018\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u0016H\u0007J\u0018\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u0016H\u0007R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/reactnativekeyboardcontroller/KeyboardControllerViewManager;", "Lcom/facebook/react/views/view/ReactViewManager;", "mReactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "manager", "Lcom/reactnativekeyboardcontroller/managers/KeyboardControllerViewManagerImpl;", "createViewInstance", "Lcom/reactnativekeyboardcontroller/views/EdgeToEdgeReactViewGroup;", "reactContext", "Lcom/facebook/react/uimanager/ThemedReactContext;", "getExportedCustomDirectEventTypeConstants", "", "", "", "getName", "onAfterUpdateTransaction", "", "view", "Lcom/facebook/react/views/view/ReactViewGroup;", "setEnabled", "enabled", "", "setNavigationBarTranslucent", "isNavigationBarTranslucent", "setPreserveEdgeToEdge", "isPreservingEdgeToEdge", "setStatusBarTranslucent", "isStatusBarTranslucent", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: KeyboardControllerViewManager.kt */
public final class KeyboardControllerViewManager extends ReactViewManager {
    private final KeyboardControllerViewManagerImpl manager;

    public String getName() {
        return KeyboardControllerViewManagerImpl.NAME;
    }

    public KeyboardControllerViewManager(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "mReactContext");
        this.manager = new KeyboardControllerViewManagerImpl(reactApplicationContext);
    }

    public EdgeToEdgeReactViewGroup createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "reactContext");
        return this.manager.createViewInstance(themedReactContext);
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(ReactViewGroup reactViewGroup) {
        Intrinsics.checkNotNullParameter(reactViewGroup, "view");
        super.onAfterUpdateTransaction(reactViewGroup);
        this.manager.setEdgeToEdge((EdgeToEdgeReactViewGroup) reactViewGroup);
    }

    @ReactProp(name = "enabled")
    public final void setEnabled(EdgeToEdgeReactViewGroup edgeToEdgeReactViewGroup, boolean z) {
        Intrinsics.checkNotNullParameter(edgeToEdgeReactViewGroup, "view");
        this.manager.setEnabled(edgeToEdgeReactViewGroup, z);
    }

    @ReactProp(name = "statusBarTranslucent")
    public final void setStatusBarTranslucent(EdgeToEdgeReactViewGroup edgeToEdgeReactViewGroup, boolean z) {
        Intrinsics.checkNotNullParameter(edgeToEdgeReactViewGroup, "view");
        this.manager.setStatusBarTranslucent(edgeToEdgeReactViewGroup, z);
    }

    @ReactProp(name = "navigationBarTranslucent")
    public final void setNavigationBarTranslucent(EdgeToEdgeReactViewGroup edgeToEdgeReactViewGroup, boolean z) {
        Intrinsics.checkNotNullParameter(edgeToEdgeReactViewGroup, "view");
        this.manager.setNavigationBarTranslucent(edgeToEdgeReactViewGroup, z);
    }

    @ReactProp(name = "preserveEdgeToEdge")
    public final void setPreserveEdgeToEdge(EdgeToEdgeReactViewGroup edgeToEdgeReactViewGroup, boolean z) {
        Intrinsics.checkNotNullParameter(edgeToEdgeReactViewGroup, "view");
        this.manager.setPreserveEdgeToEdge(edgeToEdgeReactViewGroup, z);
    }

    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return this.manager.getExportedCustomDirectEventTypeConstants();
    }
}
