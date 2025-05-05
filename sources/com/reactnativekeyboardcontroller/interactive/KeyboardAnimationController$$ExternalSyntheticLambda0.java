package com.reactnativekeyboardcontroller.interactive;

import androidx.dynamicanimation.animation.DynamicAnimation;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class KeyboardAnimationController$$ExternalSyntheticLambda0 implements DynamicAnimation.OnAnimationEndListener {
    public final /* synthetic */ KeyboardAnimationController f$0;

    public /* synthetic */ KeyboardAnimationController$$ExternalSyntheticLambda0(KeyboardAnimationController keyboardAnimationController) {
        this.f$0 = keyboardAnimationController;
    }

    public final void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
        KeyboardAnimationController.animateImeToVisibility$lambda$3$lambda$2(this.f$0, dynamicAnimation, z, f, f2);
    }
}
