package com.reactnativekeyboardcontroller.interactive;

import androidx.core.view.WindowInsetsAnimationControllerCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroidx/core/view/WindowInsetsAnimationControllerCompat;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: KeyboardAnimationController.kt */
final class KeyboardAnimationController$startAndFling$1 extends Lambda implements Function1<WindowInsetsAnimationControllerCompat, Unit> {
    final /* synthetic */ float $velocityY;
    final /* synthetic */ KeyboardAnimationController this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    KeyboardAnimationController$startAndFling$1(KeyboardAnimationController keyboardAnimationController, float f) {
        super(1);
        this.this$0 = keyboardAnimationController;
        this.$velocityY = f;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((WindowInsetsAnimationControllerCompat) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(WindowInsetsAnimationControllerCompat windowInsetsAnimationControllerCompat) {
        Intrinsics.checkNotNullParameter(windowInsetsAnimationControllerCompat, "it");
        this.this$0.animateToFinish(Float.valueOf(this.$velocityY));
    }
}
