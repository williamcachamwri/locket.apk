package com.reactnativekeyboardcontroller.managers;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.reactnativekeyboardcontroller.events.FocusedInputLayoutChangedEvent;
import com.reactnativekeyboardcontroller.events.FocusedInputSelectionChangedEvent;
import com.reactnativekeyboardcontroller.events.FocusedInputTextChangedEvent;
import com.reactnativekeyboardcontroller.events.KeyboardTransitionEvent;
import com.reactnativekeyboardcontroller.views.EdgeToEdgeReactViewGroup;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\nJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0006J\u0016\u0010\u000f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0011J\u0016\u0010\u0014\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0011J\u0016\u0010\u0016\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0011¨\u0006\u0019"}, d2 = {"Lcom/reactnativekeyboardcontroller/managers/KeyboardControllerViewManagerImpl;", "", "mReactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "createViewInstance", "Lcom/reactnativekeyboardcontroller/views/EdgeToEdgeReactViewGroup;", "reactContext", "Lcom/facebook/react/uimanager/ThemedReactContext;", "getExportedCustomDirectEventTypeConstants", "", "", "setEdgeToEdge", "", "view", "setEnabled", "enabled", "", "setNavigationBarTranslucent", "isNavigationBarTranslucent", "setPreserveEdgeToEdge", "isPreservingEdgeToEdge", "setStatusBarTranslucent", "isStatusBarTranslucent", "Companion", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: KeyboardControllerViewManagerImpl.kt */
public final class KeyboardControllerViewManagerImpl {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String NAME = "KeyboardControllerView";

    public KeyboardControllerViewManagerImpl(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "mReactContext");
    }

    public final EdgeToEdgeReactViewGroup createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "reactContext");
        return new EdgeToEdgeReactViewGroup(themedReactContext);
    }

    public final void setEnabled(EdgeToEdgeReactViewGroup edgeToEdgeReactViewGroup, boolean z) {
        Intrinsics.checkNotNullParameter(edgeToEdgeReactViewGroup, "view");
        edgeToEdgeReactViewGroup.setActive(z);
    }

    public final void setStatusBarTranslucent(EdgeToEdgeReactViewGroup edgeToEdgeReactViewGroup, boolean z) {
        Intrinsics.checkNotNullParameter(edgeToEdgeReactViewGroup, "view");
        edgeToEdgeReactViewGroup.setStatusBarTranslucent(z);
    }

    public final void setNavigationBarTranslucent(EdgeToEdgeReactViewGroup edgeToEdgeReactViewGroup, boolean z) {
        Intrinsics.checkNotNullParameter(edgeToEdgeReactViewGroup, "view");
        edgeToEdgeReactViewGroup.setNavigationBarTranslucent(z);
    }

    public final void setPreserveEdgeToEdge(EdgeToEdgeReactViewGroup edgeToEdgeReactViewGroup, boolean z) {
        Intrinsics.checkNotNullParameter(edgeToEdgeReactViewGroup, "view");
        edgeToEdgeReactViewGroup.setPreserveEdgeToEdge(z);
    }

    public final void setEdgeToEdge(EdgeToEdgeReactViewGroup edgeToEdgeReactViewGroup) {
        Intrinsics.checkNotNullParameter(edgeToEdgeReactViewGroup, "view");
        edgeToEdgeReactViewGroup.setEdgeToEdge();
    }

    public final Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        Map<String, Object> of = MapBuilder.of(KeyboardTransitionEvent.Companion.getMove().getValue(), MapBuilder.of("registrationName", "onKeyboardMove"), KeyboardTransitionEvent.Companion.getStart().getValue(), MapBuilder.of("registrationName", "onKeyboardMoveStart"), KeyboardTransitionEvent.Companion.getEnd().getValue(), MapBuilder.of("registrationName", "onKeyboardMoveEnd"), KeyboardTransitionEvent.Companion.getInteractive().getValue(), MapBuilder.of("registrationName", "onKeyboardMoveInteractive"), FocusedInputLayoutChangedEvent.EVENT_NAME, MapBuilder.of("registrationName", "onFocusedInputLayoutChanged"), FocusedInputTextChangedEvent.EVENT_NAME, MapBuilder.of("registrationName", "onFocusedInputTextChanged"), FocusedInputSelectionChangedEvent.EVENT_NAME, MapBuilder.of("registrationName", "onFocusedInputSelectionChanged"));
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        return of;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/reactnativekeyboardcontroller/managers/KeyboardControllerViewManagerImpl$Companion;", "", "()V", "NAME", "", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: KeyboardControllerViewManagerImpl.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
