package com.swmansion.reanimated.layoutReanimation;

import com.facebook.react.uimanager.layoutanimation.LayoutAnimationListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReaLayoutAnimator$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ LayoutAnimationListener f$0;

    public /* synthetic */ ReaLayoutAnimator$$ExternalSyntheticLambda0(LayoutAnimationListener layoutAnimationListener) {
        this.f$0 = layoutAnimationListener;
    }

    public final void run() {
        this.f$0.onAnimationEnd();
    }
}
