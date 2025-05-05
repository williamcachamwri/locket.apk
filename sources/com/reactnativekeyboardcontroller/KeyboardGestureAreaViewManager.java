package com.reactnativekeyboardcontroller;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.view.ReactViewManager;
import com.reactnativekeyboardcontroller.managers.KeyboardGestureAreaViewManagerImpl;
import com.reactnativekeyboardcontroller.views.KeyboardGestureAreaReactViewGroup;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0007J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\fH\u0007J\u0018\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\u0018\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\u0018\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\fH\u0007R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/reactnativekeyboardcontroller/KeyboardGestureAreaViewManager;", "Lcom/facebook/react/views/view/ReactViewManager;", "mReactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "manager", "Lcom/reactnativekeyboardcontroller/managers/KeyboardGestureAreaViewManagerImpl;", "createViewInstance", "Lcom/reactnativekeyboardcontroller/views/KeyboardGestureAreaReactViewGroup;", "reactContext", "Lcom/facebook/react/uimanager/ThemedReactContext;", "getName", "", "setInterpolator", "", "view", "offset", "", "interpolator", "setScrollKeyboardOffScreenWhenVisible", "value", "", "setScrollKeyboardOnScreenWhenNotVisible", "setTextInputNativeID", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: KeyboardGestureAreaViewManager.kt */
public final class KeyboardGestureAreaViewManager extends ReactViewManager {
    private final KeyboardGestureAreaViewManagerImpl manager;

    public String getName() {
        return KeyboardGestureAreaViewManagerImpl.NAME;
    }

    @ReactProp(name = "textInputNativeID")
    public final void setTextInputNativeID(KeyboardGestureAreaReactViewGroup keyboardGestureAreaReactViewGroup, String str) {
        Intrinsics.checkNotNullParameter(keyboardGestureAreaReactViewGroup, "view");
        Intrinsics.checkNotNullParameter(str, "value");
    }

    public KeyboardGestureAreaViewManager(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "mReactContext");
        this.manager = new KeyboardGestureAreaViewManagerImpl(reactApplicationContext);
    }

    public KeyboardGestureAreaReactViewGroup createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "reactContext");
        return this.manager.createViewInstance(themedReactContext);
    }

    @ReactProp(name = "offset")
    public final void setInterpolator(KeyboardGestureAreaReactViewGroup keyboardGestureAreaReactViewGroup, double d) {
        Intrinsics.checkNotNullParameter(keyboardGestureAreaReactViewGroup, "view");
        this.manager.setOffset(keyboardGestureAreaReactViewGroup, d);
    }

    @ReactProp(name = "interpolator")
    public final void setInterpolator(KeyboardGestureAreaReactViewGroup keyboardGestureAreaReactViewGroup, String str) {
        Intrinsics.checkNotNullParameter(keyboardGestureAreaReactViewGroup, "view");
        Intrinsics.checkNotNullParameter(str, "interpolator");
        this.manager.setInterpolator(keyboardGestureAreaReactViewGroup, str);
    }

    @ReactProp(name = "showOnSwipeUp")
    public final void setScrollKeyboardOnScreenWhenNotVisible(KeyboardGestureAreaReactViewGroup keyboardGestureAreaReactViewGroup, boolean z) {
        Intrinsics.checkNotNullParameter(keyboardGestureAreaReactViewGroup, "view");
        this.manager.setScrollKeyboardOnScreenWhenNotVisible(keyboardGestureAreaReactViewGroup, z);
    }

    @ReactProp(name = "enableSwipeToDismiss")
    public final void setScrollKeyboardOffScreenWhenVisible(KeyboardGestureAreaReactViewGroup keyboardGestureAreaReactViewGroup, boolean z) {
        Intrinsics.checkNotNullParameter(keyboardGestureAreaReactViewGroup, "view");
        this.manager.setScrollKeyboardOffScreenWhenVisible(keyboardGestureAreaReactViewGroup, z);
    }
}
