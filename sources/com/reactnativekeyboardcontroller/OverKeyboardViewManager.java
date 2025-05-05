package com.reactnativekeyboardcontroller;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.reactnativekeyboardcontroller.managers.OverKeyboardViewManagerImpl;
import com.reactnativekeyboardcontroller.views.overlay.OverKeyboardHostShadowNode;
import com.reactnativekeyboardcontroller.views.overlay.OverKeyboardHostView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0014J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0015H\u0007R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/reactnativekeyboardcontroller/OverKeyboardViewManager;", "Lcom/facebook/react/uimanager/ViewGroupManager;", "Lcom/reactnativekeyboardcontroller/views/overlay/OverKeyboardHostView;", "mReactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "manager", "Lcom/reactnativekeyboardcontroller/managers/OverKeyboardViewManagerImpl;", "createShadowNodeInstance", "Lcom/facebook/react/uimanager/LayoutShadowNode;", "createViewInstance", "reactContext", "Lcom/facebook/react/uimanager/ThemedReactContext;", "getName", "", "getShadowNodeClass", "Ljava/lang/Class;", "setVisible", "", "view", "value", "", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: OverKeyboardViewManager.kt */
public final class OverKeyboardViewManager extends ViewGroupManager<OverKeyboardHostView> {
    private final OverKeyboardViewManagerImpl manager;

    public String getName() {
        return OverKeyboardViewManagerImpl.NAME;
    }

    public Class<? extends LayoutShadowNode> getShadowNodeClass() {
        return OverKeyboardHostShadowNode.class;
    }

    public OverKeyboardViewManager(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "mReactContext");
        this.manager = new OverKeyboardViewManagerImpl(reactApplicationContext);
    }

    /* access modifiers changed from: protected */
    public OverKeyboardHostView createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "reactContext");
        return this.manager.createViewInstance(themedReactContext);
    }

    public LayoutShadowNode createShadowNodeInstance() {
        return new OverKeyboardHostShadowNode();
    }

    @ReactProp(name = "visible")
    public final void setVisible(OverKeyboardHostView overKeyboardHostView, boolean z) {
        Intrinsics.checkNotNullParameter(overKeyboardHostView, "view");
        this.manager.setVisible(overKeyboardHostView, z);
    }
}
