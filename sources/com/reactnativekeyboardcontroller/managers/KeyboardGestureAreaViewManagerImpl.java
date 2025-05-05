package com.reactnativekeyboardcontroller.managers;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ThemedReactContext;
import com.reactnativekeyboardcontroller.views.KeyboardGestureAreaReactViewGroup;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rJ\u0016\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0013J\u0016\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0013¨\u0006\u0016"}, d2 = {"Lcom/reactnativekeyboardcontroller/managers/KeyboardGestureAreaViewManagerImpl;", "", "mReactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "createViewInstance", "Lcom/reactnativekeyboardcontroller/views/KeyboardGestureAreaReactViewGroup;", "reactContext", "Lcom/facebook/react/uimanager/ThemedReactContext;", "setInterpolator", "", "view", "interpolator", "", "setOffset", "offset", "", "setScrollKeyboardOffScreenWhenVisible", "value", "", "setScrollKeyboardOnScreenWhenNotVisible", "Companion", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: KeyboardGestureAreaViewManagerImpl.kt */
public final class KeyboardGestureAreaViewManagerImpl {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String NAME = "KeyboardGestureArea";

    public KeyboardGestureAreaViewManagerImpl(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "mReactContext");
    }

    public final KeyboardGestureAreaReactViewGroup createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "reactContext");
        return new KeyboardGestureAreaReactViewGroup(themedReactContext);
    }

    public final void setOffset(KeyboardGestureAreaReactViewGroup keyboardGestureAreaReactViewGroup, double d) {
        Intrinsics.checkNotNullParameter(keyboardGestureAreaReactViewGroup, "view");
        keyboardGestureAreaReactViewGroup.setOffset(d);
    }

    public final void setInterpolator(KeyboardGestureAreaReactViewGroup keyboardGestureAreaReactViewGroup, String str) {
        Intrinsics.checkNotNullParameter(keyboardGestureAreaReactViewGroup, "view");
        Intrinsics.checkNotNullParameter(str, "interpolator");
        keyboardGestureAreaReactViewGroup.setInterpolator(str);
    }

    public final void setScrollKeyboardOffScreenWhenVisible(KeyboardGestureAreaReactViewGroup keyboardGestureAreaReactViewGroup, boolean z) {
        Intrinsics.checkNotNullParameter(keyboardGestureAreaReactViewGroup, "view");
        keyboardGestureAreaReactViewGroup.setScrollKeyboardOffScreenWhenVisible(z);
    }

    public final void setScrollKeyboardOnScreenWhenNotVisible(KeyboardGestureAreaReactViewGroup keyboardGestureAreaReactViewGroup, boolean z) {
        Intrinsics.checkNotNullParameter(keyboardGestureAreaReactViewGroup, "view");
        keyboardGestureAreaReactViewGroup.setScrollKeyboardOnScreenWhenNotVisible(z);
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/reactnativekeyboardcontroller/managers/KeyboardGestureAreaViewManagerImpl$Companion;", "", "()V", "NAME", "", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: KeyboardGestureAreaViewManagerImpl.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
