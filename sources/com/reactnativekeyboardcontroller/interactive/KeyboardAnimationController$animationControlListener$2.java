package com.reactnativekeyboardcontroller.interactive;

import androidx.core.view.WindowInsetsAnimationControlListenerCompat;
import androidx.core.view.WindowInsetsAnimationControllerCompat;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\t\n\u0000\n\u0002\b\u0003*\u0001\u0001\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "com/reactnativekeyboardcontroller/interactive/KeyboardAnimationController$animationControlListener$2$1", "invoke", "()Lcom/reactnativekeyboardcontroller/interactive/KeyboardAnimationController$animationControlListener$2$1;"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: KeyboardAnimationController.kt */
final class KeyboardAnimationController$animationControlListener$2 extends Lambda implements Function0<AnonymousClass1> {
    final /* synthetic */ KeyboardAnimationController this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    KeyboardAnimationController$animationControlListener$2(KeyboardAnimationController keyboardAnimationController) {
        super(0);
        this.this$0 = keyboardAnimationController;
    }

    public final AnonymousClass1 invoke() {
        final KeyboardAnimationController keyboardAnimationController = this.this$0;
        return new WindowInsetsAnimationControlListenerCompat() {
            public void onReady(WindowInsetsAnimationControllerCompat windowInsetsAnimationControllerCompat, int i) {
                Intrinsics.checkNotNullParameter(windowInsetsAnimationControllerCompat, "controller");
                keyboardAnimationController.onRequestReady(windowInsetsAnimationControllerCompat);
            }

            public void onFinished(WindowInsetsAnimationControllerCompat windowInsetsAnimationControllerCompat) {
                Intrinsics.checkNotNullParameter(windowInsetsAnimationControllerCompat, "controller");
                keyboardAnimationController.reset();
            }

            public void onCancelled(WindowInsetsAnimationControllerCompat windowInsetsAnimationControllerCompat) {
                keyboardAnimationController.reset();
            }
        };
    }
}
