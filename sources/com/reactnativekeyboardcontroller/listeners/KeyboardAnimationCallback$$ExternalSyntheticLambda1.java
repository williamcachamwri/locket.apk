package com.reactnativekeyboardcontroller.listeners;

import androidx.core.view.WindowInsetsAnimationCompat;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class KeyboardAnimationCallback$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ KeyboardAnimationCallback f$0;
    public final /* synthetic */ WindowInsetsAnimationCompat f$1;

    public /* synthetic */ KeyboardAnimationCallback$$ExternalSyntheticLambda1(KeyboardAnimationCallback keyboardAnimationCallback, WindowInsetsAnimationCompat windowInsetsAnimationCompat) {
        this.f$0 = keyboardAnimationCallback;
        this.f$1 = windowInsetsAnimationCompat;
    }

    public final void run() {
        KeyboardAnimationCallback.onEnd$lambda$5(this.f$0, this.f$1);
    }
}
