package com.reactnativekeyboardcontroller.listeners;

import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.reactnativekeyboardcontroller.extensions.FloatKt;
import com.reactnativekeyboardcontroller.extensions.ReactContextKt;
import com.reactnativekeyboardcontroller.extensions.ThemedReactContextKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/reactnativekeyboardcontroller/listeners/WindowDimensionListener;", "", "context", "Lcom/facebook/react/uimanager/ThemedReactContext;", "(Lcom/facebook/react/uimanager/ThemedReactContext;)V", "lastDispatchedDimensions", "Lcom/reactnativekeyboardcontroller/listeners/Dimensions;", "updateWindowDimensions", "", "content", "Landroid/view/ViewGroup;", "Companion", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: WindowDimensionListener.kt */
public final class WindowDimensionListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static int listenerID = -1;
    private final ThemedReactContext context;
    private Dimensions lastDispatchedDimensions = new Dimensions(0.0d, 0.0d);

    public WindowDimensionListener(ThemedReactContext themedReactContext) {
        ViewTreeObserver viewTreeObserver;
        this.context = themedReactContext;
        if (themedReactContext != null && listenerID != themedReactContext.hashCode()) {
            listenerID = themedReactContext.hashCode();
            ViewGroup content = ReactContextKt.getContent(themedReactContext);
            updateWindowDimensions(content);
            if (content != null && (viewTreeObserver = content.getViewTreeObserver()) != null) {
                viewTreeObserver.addOnGlobalLayoutListener(new WindowDimensionListener$$ExternalSyntheticLambda0(this, content));
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void _init_$lambda$0(WindowDimensionListener windowDimensionListener, ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(windowDimensionListener, "this$0");
        windowDimensionListener.updateWindowDimensions(viewGroup);
    }

    private final void updateWindowDimensions(ViewGroup viewGroup) {
        if (viewGroup != null) {
            Dimensions dimensions = new Dimensions(FloatKt.getDp((float) viewGroup.getWidth()), FloatKt.getDp((float) viewGroup.getHeight()));
            if (!Intrinsics.areEqual((Object) dimensions, (Object) this.lastDispatchedDimensions)) {
                this.lastDispatchedDimensions = dimensions;
                ThemedReactContext themedReactContext = this.context;
                WritableMap createMap = Arguments.createMap();
                createMap.putDouble("height", dimensions.getHeight());
                createMap.putDouble("width", dimensions.getWidth());
                Unit unit = Unit.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(createMap, "apply(...)");
                ThemedReactContextKt.emitEvent(themedReactContext, "KeyboardController::windowDidResize", createMap);
            }
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/reactnativekeyboardcontroller/listeners/WindowDimensionListener$Companion;", "", "()V", "listenerID", "", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: WindowDimensionListener.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
