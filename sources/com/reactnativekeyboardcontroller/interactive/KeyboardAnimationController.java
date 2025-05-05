package com.reactnativekeyboardcontroller.interactive;

import android.os.CancellationSignal;
import android.view.View;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsAnimationControllerCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.DynamicAnimationKt;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.reactnativekeyboardcontroller.interactive.KeyboardAnimationController$animationControlListener$2;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000O\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J!\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u000e2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0002¢\u0006\u0002\u0010\u0018J\u0017\u0010\u0019\u001a\u00020\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017¢\u0006\u0002\u0010\u001aJ\u0006\u0010\u001b\u001a\u00020\u0013J\u0006\u0010\u001c\u001a\u00020\u0013J\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u001eJ\u000e\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\u001eJ\u0006\u0010#\u001a\u00020\u000eJ\u0006\u0010$\u001a\u00020\u000eJ\u0006\u0010%\u001a\u00020\u000eJ\u0010\u0010&\u001a\u00020\u00132\u0006\u0010'\u001a\u00020\fH\u0002J\b\u0010(\u001a\u00020\u0013H\u0002J\u0016\u0010)\u001a\u00020\u00132\u0006\u0010*\u001a\u00020+2\u0006\u0010\u0016\u001a\u00020\u0017J&\u0010,\u001a\u00020\u00132\u0006\u0010*\u001a\u00020+2\u0016\b\u0002\u0010&\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012X\u000e¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/reactnativekeyboardcontroller/interactive/KeyboardAnimationController;", "", "()V", "animationControlListener", "com/reactnativekeyboardcontroller/interactive/KeyboardAnimationController$animationControlListener$2$1", "getAnimationControlListener", "()Lcom/reactnativekeyboardcontroller/interactive/KeyboardAnimationController$animationControlListener$2$1;", "animationControlListener$delegate", "Lkotlin/Lazy;", "currentSpringAnimation", "Landroidx/dynamicanimation/animation/SpringAnimation;", "insetsAnimationController", "Landroidx/core/view/WindowInsetsAnimationControllerCompat;", "isImeShownAtStart", "", "pendingRequestCancellationSignal", "Landroid/os/CancellationSignal;", "pendingRequestOnReady", "Lkotlin/Function1;", "", "animateImeToVisibility", "visible", "velocityY", "", "(ZLjava/lang/Float;)V", "animateToFinish", "(Ljava/lang/Float;)V", "cancel", "finish", "getCurrentKeyboardHeight", "", "insetBy", "dy", "insetTo", "inset", "isInsetAnimationFinishing", "isInsetAnimationInProgress", "isInsetAnimationRequestPending", "onRequestReady", "controller", "reset", "startAndFling", "view", "Landroid/view/View;", "startControlRequest", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: KeyboardAnimationController.kt */
public final class KeyboardAnimationController {
    private final Lazy animationControlListener$delegate = LazyKt.lazy(new KeyboardAnimationController$animationControlListener$2(this));
    private SpringAnimation currentSpringAnimation;
    private WindowInsetsAnimationControllerCompat insetsAnimationController;
    private boolean isImeShownAtStart;
    private CancellationSignal pendingRequestCancellationSignal;
    private Function1<? super WindowInsetsAnimationControllerCompat, Unit> pendingRequestOnReady;

    private final KeyboardAnimationController$animationControlListener$2.AnonymousClass1 getAnimationControlListener() {
        return (KeyboardAnimationController$animationControlListener$2.AnonymousClass1) this.animationControlListener$delegate.getValue();
    }

    public static /* synthetic */ void startControlRequest$default(KeyboardAnimationController keyboardAnimationController, View view, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        keyboardAnimationController.startControlRequest(view, function1);
    }

    public final void startControlRequest(View view, Function1<? super WindowInsetsAnimationControllerCompat, Unit> function1) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (!isInsetAnimationInProgress()) {
            WindowInsetsCompat rootWindowInsets = ViewCompat.getRootWindowInsets(view);
            boolean z = false;
            if (rootWindowInsets != null && rootWindowInsets.isVisible(WindowInsetsCompat.Type.ime())) {
                z = true;
            }
            this.isImeShownAtStart = z;
            this.pendingRequestCancellationSignal = new CancellationSignal();
            this.pendingRequestOnReady = function1;
            InteractiveKeyboardProvider.INSTANCE.setInteractive(true);
            WindowInsetsControllerCompat windowInsetsController = ViewCompat.getWindowInsetsController(view);
            if (windowInsetsController != null) {
                windowInsetsController.controlWindowInsetsAnimation(WindowInsetsCompat.Type.ime(), -1, KeyboardAnimationControllerKt.linearInterpolator, this.pendingRequestCancellationSignal, getAnimationControlListener());
                return;
            }
            return;
        }
        throw new IllegalStateException("Animation in progress. Can not start a new request to controlWindowInsetsAnimation()".toString());
    }

    public final void startAndFling(View view, float f) {
        Intrinsics.checkNotNullParameter(view, "view");
        startControlRequest(view, new KeyboardAnimationController$startAndFling$1(this, f));
    }

    public final int insetBy(int i) {
        WindowInsetsAnimationControllerCompat windowInsetsAnimationControllerCompat = this.insetsAnimationController;
        if (windowInsetsAnimationControllerCompat != null) {
            InteractiveKeyboardProvider.INSTANCE.setInteractive(true);
            return insetTo(windowInsetsAnimationControllerCompat.getCurrentInsets().bottom - i);
        }
        throw new IllegalStateException("Current WindowInsetsAnimationController is null.This should only be called if isAnimationInProgress() returns true");
    }

    public final int insetTo(int i) {
        WindowInsetsAnimationControllerCompat windowInsetsAnimationControllerCompat = this.insetsAnimationController;
        if (windowInsetsAnimationControllerCompat != null) {
            int i2 = windowInsetsAnimationControllerCompat.getHiddenStateInsets().bottom;
            int i3 = windowInsetsAnimationControllerCompat.getShownStateInsets().bottom;
            boolean z = this.isImeShownAtStart;
            int i4 = z ? i3 : i2;
            int i5 = z ? i2 : i3;
            int coerceIn = RangesKt.coerceIn(i, i2, i3);
            int i6 = windowInsetsAnimationControllerCompat.getCurrentInsets().bottom - coerceIn;
            windowInsetsAnimationControllerCompat.setInsetsAndAlpha(Insets.of(0, 0, 0, coerceIn), 1.0f, ((float) (coerceIn - i4)) / ((float) (i5 - i4)));
            return i6;
        }
        throw new IllegalStateException("Current WindowInsetsAnimationController is null.This should only be called if isAnimationInProgress() returns true");
    }

    public final boolean isInsetAnimationInProgress() {
        return this.insetsAnimationController != null;
    }

    public final boolean isInsetAnimationFinishing() {
        return this.currentSpringAnimation != null;
    }

    public final boolean isInsetAnimationRequestPending() {
        return this.pendingRequestCancellationSignal != null;
    }

    public final void cancel() {
        WindowInsetsAnimationControllerCompat windowInsetsAnimationControllerCompat = this.insetsAnimationController;
        if (windowInsetsAnimationControllerCompat != null) {
            windowInsetsAnimationControllerCompat.finish(this.isImeShownAtStart);
        }
        CancellationSignal cancellationSignal = this.pendingRequestCancellationSignal;
        if (cancellationSignal != null) {
            cancellationSignal.cancel();
        }
        SpringAnimation springAnimation = this.currentSpringAnimation;
        if (springAnimation != null) {
            springAnimation.cancel();
        }
        reset();
    }

    public final void finish() {
        WindowInsetsAnimationControllerCompat windowInsetsAnimationControllerCompat = this.insetsAnimationController;
        if (windowInsetsAnimationControllerCompat == null) {
            CancellationSignal cancellationSignal = this.pendingRequestCancellationSignal;
            if (cancellationSignal != null) {
                cancellationSignal.cancel();
                return;
            }
            return;
        }
        int i = windowInsetsAnimationControllerCompat.getCurrentInsets().bottom;
        int i2 = windowInsetsAnimationControllerCompat.getShownStateInsets().bottom;
        int i3 = windowInsetsAnimationControllerCompat.getHiddenStateInsets().bottom;
        if (i == i2) {
            windowInsetsAnimationControllerCompat.finish(true);
        } else if (i == i3) {
            windowInsetsAnimationControllerCompat.finish(false);
        } else if (windowInsetsAnimationControllerCompat.getCurrentFraction() >= 0.15f) {
            windowInsetsAnimationControllerCompat.finish(!this.isImeShownAtStart);
        } else {
            windowInsetsAnimationControllerCompat.finish(this.isImeShownAtStart);
        }
    }

    public static /* synthetic */ void animateToFinish$default(KeyboardAnimationController keyboardAnimationController, Float f, int i, Object obj) {
        if ((i & 1) != 0) {
            f = null;
        }
        keyboardAnimationController.animateToFinish(f);
    }

    public final void animateToFinish(Float f) {
        WindowInsetsAnimationControllerCompat windowInsetsAnimationControllerCompat = this.insetsAnimationController;
        if (windowInsetsAnimationControllerCompat == null) {
            CancellationSignal cancellationSignal = this.pendingRequestCancellationSignal;
            if (cancellationSignal != null) {
                cancellationSignal.cancel();
                return;
            }
            return;
        }
        boolean z = false;
        InteractiveKeyboardProvider.INSTANCE.setInteractive(false);
        int i = windowInsetsAnimationControllerCompat.getCurrentInsets().bottom;
        int i2 = windowInsetsAnimationControllerCompat.getShownStateInsets().bottom;
        int i3 = windowInsetsAnimationControllerCompat.getHiddenStateInsets().bottom;
        if (f != null) {
            if (f.floatValue() < 0.0f) {
                z = true;
            }
            animateImeToVisibility(z, f);
        } else if (i == i2) {
            windowInsetsAnimationControllerCompat.finish(true);
        } else if (i == i3) {
            windowInsetsAnimationControllerCompat.finish(false);
        } else if (windowInsetsAnimationControllerCompat.getCurrentFraction() >= 0.15f) {
            animateImeToVisibility$default(this, !this.isImeShownAtStart, (Float) null, 2, (Object) null);
        } else {
            animateImeToVisibility$default(this, this.isImeShownAtStart, (Float) null, 2, (Object) null);
        }
    }

    public final int getCurrentKeyboardHeight() {
        WindowInsetsAnimationControllerCompat windowInsetsAnimationControllerCompat = this.insetsAnimationController;
        if (windowInsetsAnimationControllerCompat != null) {
            return windowInsetsAnimationControllerCompat.getCurrentInsets().bottom;
        }
        throw new IllegalStateException("Current WindowInsetsAnimationController is null.This should only be called if isAnimationInProgress() returns true");
    }

    /* access modifiers changed from: private */
    public final void onRequestReady(WindowInsetsAnimationControllerCompat windowInsetsAnimationControllerCompat) {
        this.pendingRequestCancellationSignal = null;
        this.insetsAnimationController = windowInsetsAnimationControllerCompat;
        Function1<? super WindowInsetsAnimationControllerCompat, Unit> function1 = this.pendingRequestOnReady;
        if (function1 != null) {
            function1.invoke(windowInsetsAnimationControllerCompat);
        }
        this.pendingRequestOnReady = null;
    }

    /* access modifiers changed from: private */
    public final void reset() {
        this.insetsAnimationController = null;
        this.pendingRequestCancellationSignal = null;
        this.isImeShownAtStart = false;
        SpringAnimation springAnimation = this.currentSpringAnimation;
        if (springAnimation != null) {
            springAnimation.cancel();
        }
        this.currentSpringAnimation = null;
        this.pendingRequestOnReady = null;
    }

    static /* synthetic */ void animateImeToVisibility$default(KeyboardAnimationController keyboardAnimationController, boolean z, Float f, int i, Object obj) {
        if ((i & 2) != 0) {
            f = null;
        }
        keyboardAnimationController.animateImeToVisibility(z, f);
    }

    private final void animateImeToVisibility(boolean z, Float f) {
        int i;
        WindowInsetsAnimationControllerCompat windowInsetsAnimationControllerCompat = this.insetsAnimationController;
        if (windowInsetsAnimationControllerCompat != null) {
            Function1 keyboardAnimationController$animateImeToVisibility$1 = new KeyboardAnimationController$animateImeToVisibility$1(this);
            Function0 keyboardAnimationController$animateImeToVisibility$2 = new KeyboardAnimationController$animateImeToVisibility$2(windowInsetsAnimationControllerCompat);
            if (z) {
                i = windowInsetsAnimationControllerCompat.getShownStateInsets().bottom;
            } else {
                i = windowInsetsAnimationControllerCompat.getHiddenStateInsets().bottom;
            }
            SpringAnimation springAnimationOf = DynamicAnimationKt.springAnimationOf(keyboardAnimationController$animateImeToVisibility$1, keyboardAnimationController$animateImeToVisibility$2, (float) i);
            if (springAnimationOf.getSpring() == null) {
                springAnimationOf.setSpring(new SpringForce());
            }
            SpringForce spring = springAnimationOf.getSpring();
            Intrinsics.checkExpressionValueIsNotNull(spring, "spring");
            spring.setDampingRatio(1.0f);
            spring.setStiffness(1500.0f);
            if (f != null) {
                springAnimationOf.setStartVelocity(f.floatValue());
            }
            springAnimationOf.addEndListener(new KeyboardAnimationController$$ExternalSyntheticLambda0(this));
            springAnimationOf.start();
            this.currentSpringAnimation = springAnimationOf;
            return;
        }
        throw new IllegalStateException("Controller should not be null");
    }

    /* access modifiers changed from: private */
    public static final void animateImeToVisibility$lambda$3$lambda$2(KeyboardAnimationController keyboardAnimationController, DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
        Intrinsics.checkNotNullParameter(keyboardAnimationController, "this$0");
        if (Intrinsics.areEqual((Object) dynamicAnimation, (Object) keyboardAnimationController.currentSpringAnimation)) {
            keyboardAnimationController.currentSpringAnimation = null;
        }
        keyboardAnimationController.finish();
    }
}
