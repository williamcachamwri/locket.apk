package com.reactnativekeyboardcontroller.listeners;

import android.view.View;
import android.view.ViewTreeObserver;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FocusedInputObserver$$ExternalSyntheticLambda2 implements ViewTreeObserver.OnGlobalFocusChangeListener {
    public final /* synthetic */ FocusedInputObserver f$0;

    public /* synthetic */ FocusedInputObserver$$ExternalSyntheticLambda2(FocusedInputObserver focusedInputObserver) {
        this.f$0 = focusedInputObserver;
    }

    public final void onGlobalFocusChanged(View view, View view2) {
        FocusedInputObserver.focusListener$lambda$4(this.f$0, view, view2);
    }
}
