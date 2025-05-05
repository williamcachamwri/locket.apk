package com.reactnativekeyboardcontroller.listeners;

import android.view.View;
import android.view.ViewTreeObserver;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class KeyboardAnimationCallback$$ExternalSyntheticLambda0 implements ViewTreeObserver.OnGlobalFocusChangeListener {
    public final /* synthetic */ KeyboardAnimationCallback f$0;

    public /* synthetic */ KeyboardAnimationCallback$$ExternalSyntheticLambda0(KeyboardAnimationCallback keyboardAnimationCallback) {
        this.f$0 = keyboardAnimationCallback;
    }

    public final void onGlobalFocusChanged(View view, View view2) {
        KeyboardAnimationCallback.focusListener$lambda$0(this.f$0, view, view2);
    }
}
