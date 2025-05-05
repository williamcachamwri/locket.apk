package com.reactnativekeyboardcontroller.interactive;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.math.MathKt;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: KeyboardAnimationController.kt */
final class KeyboardAnimationController$animateImeToVisibility$1 extends Lambda implements Function1<Float, Unit> {
    final /* synthetic */ KeyboardAnimationController this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    KeyboardAnimationController$animateImeToVisibility$1(KeyboardAnimationController keyboardAnimationController) {
        super(1);
        this.this$0 = keyboardAnimationController;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke(((Number) obj).floatValue());
        return Unit.INSTANCE;
    }

    public final void invoke(float f) {
        this.this$0.insetTo(MathKt.roundToInt(f));
    }
}
