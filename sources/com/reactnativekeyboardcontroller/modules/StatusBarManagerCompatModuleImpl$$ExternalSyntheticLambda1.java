package com.reactnativekeyboardcontroller.modules;

import android.animation.ValueAnimator;
import android.view.Window;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class StatusBarManagerCompatModuleImpl$$ExternalSyntheticLambda1 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ Window f$0;

    public /* synthetic */ StatusBarManagerCompatModuleImpl$$ExternalSyntheticLambda1(Window window) {
        this.f$0 = window;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        StatusBarManagerCompatModuleImpl.setColor$lambda$2$lambda$1(this.f$0, valueAnimator);
    }
}
