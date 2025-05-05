package com.reactnativekeyboardcontroller.interactive;

import androidx.core.view.WindowInsetsAnimationControllerCompat;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Float;"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: KeyboardAnimationController.kt */
final class KeyboardAnimationController$animateImeToVisibility$2 extends Lambda implements Function0<Float> {
    final /* synthetic */ WindowInsetsAnimationControllerCompat $controller;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    KeyboardAnimationController$animateImeToVisibility$2(WindowInsetsAnimationControllerCompat windowInsetsAnimationControllerCompat) {
        super(0);
        this.$controller = windowInsetsAnimationControllerCompat;
    }

    public final Float invoke() {
        return Float.valueOf((float) this.$controller.getCurrentInsets().bottom);
    }
}
