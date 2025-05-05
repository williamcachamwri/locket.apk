package com.reactnativekeyboardcontroller.views;

import com.reactnativekeyboardcontroller.listeners.KeyboardAnimationCallback;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: EdgeToEdgeReactViewGroup.kt */
/* synthetic */ class EdgeToEdgeReactViewGroup$modalAttachedWatcher$1 extends FunctionReferenceImpl implements Function0<KeyboardAnimationCallback> {
    EdgeToEdgeReactViewGroup$modalAttachedWatcher$1(Object obj) {
        super(0, obj, EdgeToEdgeReactViewGroup.class, "getKeyboardCallback", "getKeyboardCallback()Lcom/reactnativekeyboardcontroller/listeners/KeyboardAnimationCallback;", 0);
    }

    public final KeyboardAnimationCallback invoke() {
        return ((EdgeToEdgeReactViewGroup) this.receiver).getKeyboardCallback();
    }
}
