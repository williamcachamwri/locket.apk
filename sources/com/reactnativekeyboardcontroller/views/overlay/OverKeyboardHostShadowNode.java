package com.reactnativekeyboardcontroller.views.overlay;

import android.graphics.Point;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ReactShadowNodeImpl;
import com.facebook.react.uimanager.ThemedReactContext;
import com.reactnativekeyboardcontroller.extensions.ContextKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/reactnativekeyboardcontroller/views/overlay/OverKeyboardHostShadowNode;", "Lcom/facebook/react/uimanager/LayoutShadowNode;", "()V", "addChildAt", "", "child", "Lcom/facebook/react/uimanager/ReactShadowNodeImpl;", "i", "", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: OverKeyboardHostShadowNode.kt */
public final class OverKeyboardHostShadowNode extends LayoutShadowNode {
    public void addChildAt(ReactShadowNodeImpl reactShadowNodeImpl, int i) {
        Intrinsics.checkNotNullParameter(reactShadowNodeImpl, "child");
        super.addChildAt(reactShadowNodeImpl, i);
        ThemedReactContext themedContext = getThemedContext();
        Intrinsics.checkNotNullExpressionValue(themedContext, "getThemedContext(...)");
        Point displaySize = ContextKt.getDisplaySize(themedContext);
        reactShadowNodeImpl.setStyleWidth((float) displaySize.x);
        reactShadowNodeImpl.setStyleHeight((float) displaySize.y);
    }
}
