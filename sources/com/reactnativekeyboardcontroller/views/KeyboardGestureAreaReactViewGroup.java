package com.reactnativekeyboardcontroller.views;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.views.view.ReactViewGroup;
import com.reactnativekeyboardcontroller.extensions.FloatKt;
import com.reactnativekeyboardcontroller.extensions.ViewKt;
import com.reactnativekeyboardcontroller.interactive.KeyboardAnimationController;
import com.reactnativekeyboardcontroller.interactive.interpolators.Interpolator;
import com.reactnativekeyboardcontroller.interactive.interpolators.LinearInterpolator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\t\b\u0007\u0018\u0000 -2\u00020\u0001:\u0001-B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0018\u001a\u00020\f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0017J\b\u0010\u001b\u001a\u00020\u000eH\u0003J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\u0010\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u0019\u001a\u00020\u001aH\u0003J\u0010\u0010\u001f\u001a\u00020\u001d2\u0006\u0010\u0019\u001a\u00020\u001aH\u0003J\u0010\u0010 \u001a\u00020\u001d2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010!\u001a\u00020\u001dH\u0002J\u000e\u0010\"\u001a\u00020\u001d2\u0006\u0010\t\u001a\u00020#J\u000e\u0010$\u001a\u00020\u001d2\u0006\u0010\u0013\u001a\u00020%J\u000e\u0010&\u001a\u00020\u001d2\u0006\u0010'\u001a\u00020\fJ\u000e\u0010(\u001a\u00020\u001d2\u0006\u0010)\u001a\u00020\fJ\u0018\u0010*\u001a\u00020\f2\u0006\u0010+\u001a\u00020\u00102\u0006\u0010,\u001a\u00020\fH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u000e¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/reactnativekeyboardcontroller/views/KeyboardGestureAreaReactViewGroup;", "Lcom/facebook/react/views/view/ReactViewGroup;", "reactContext", "Lcom/facebook/react/uimanager/ThemedReactContext;", "(Lcom/facebook/react/uimanager/ThemedReactContext;)V", "bounds", "Landroid/graphics/Rect;", "controller", "Lcom/reactnativekeyboardcontroller/interactive/KeyboardAnimationController;", "interpolator", "Lcom/reactnativekeyboardcontroller/interactive/interpolators/Interpolator;", "isHandling", "", "keyboardHeight", "", "lastTouchX", "", "lastTouchY", "lastWindowY", "offset", "scrollKeyboardOffScreenWhenVisible", "scrollKeyboardOnScreenWhenNotVisible", "velocityTracker", "Landroid/view/VelocityTracker;", "dispatchTouchEvent", "event", "Landroid/view/MotionEvent;", "getWindowHeight", "onActionCancel", "", "onActionDown", "onActionMove", "onActionUp", "reset", "setInterpolator", "", "setOffset", "", "setScrollKeyboardOffScreenWhenVisible", "scrollImeOffScreenWhenVisible", "setScrollKeyboardOnScreenWhenNotVisible", "scrollImeOnScreenWhenNotVisible", "shouldStartRequest", "dy", "imeVisible", "Companion", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: KeyboardGestureAreaReactViewGroup.kt */
public final class KeyboardGestureAreaReactViewGroup extends ReactViewGroup {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int VELOCITY_UNITS = 500;
    private final Rect bounds = new Rect();
    private final KeyboardAnimationController controller = new KeyboardAnimationController();
    private Interpolator interpolator = new LinearInterpolator();
    private boolean isHandling;
    private int keyboardHeight;
    private float lastTouchX;
    private float lastTouchY;
    private int lastWindowY;
    private int offset;
    private final ThemedReactContext reactContext;
    private boolean scrollKeyboardOffScreenWhenVisible = true;
    private boolean scrollKeyboardOnScreenWhenNotVisible;
    private VelocityTracker velocityTracker;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public KeyboardGestureAreaReactViewGroup(ThemedReactContext themedReactContext) {
        super(themedReactContext);
        Intrinsics.checkNotNullParameter(themedReactContext, "reactContext");
        this.reactContext = themedReactContext;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        Integer valueOf = motionEvent != null ? Integer.valueOf(motionEvent.getAction()) : null;
        if (valueOf != null && valueOf.intValue() == 0) {
            onActionDown(motionEvent);
        } else if (valueOf != null && valueOf.intValue() == 2) {
            onActionMove(motionEvent);
        } else if (valueOf != null && valueOf.intValue() == 1) {
            onActionUp(motionEvent);
        } else if (valueOf != null && valueOf.intValue() == 3) {
            onActionCancel();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public final void setOffset(double d) {
        this.offset = (int) FloatKt.getPx((float) d);
    }

    public final void setInterpolator(String str) {
        Intrinsics.checkNotNullParameter(str, "interpolator");
        Interpolator interpolator2 = KeyboardGestureAreaReactViewGroupKt.getInterpolators().get(str);
        if (interpolator2 == null) {
            interpolator2 = new LinearInterpolator();
        }
        this.interpolator = interpolator2;
    }

    public final void setScrollKeyboardOnScreenWhenNotVisible(boolean z) {
        this.scrollKeyboardOnScreenWhenNotVisible = z;
    }

    public final void setScrollKeyboardOffScreenWhenVisible(boolean z) {
        this.scrollKeyboardOffScreenWhenVisible = z;
    }

    private final void onActionDown(MotionEvent motionEvent) {
        VelocityTracker velocityTracker2 = this.velocityTracker;
        if (velocityTracker2 != null) {
            velocityTracker2.addMovement(motionEvent);
        }
        this.lastTouchX = motionEvent.getX();
        this.lastTouchY = motionEvent.getY();
        ViewKt.copyBoundsInWindow(this, this.bounds);
        this.lastWindowY = this.bounds.top;
    }

    private final void onActionMove(MotionEvent motionEvent) {
        View view = this;
        ViewKt.copyBoundsInWindow(view, this.bounds);
        int i = this.bounds.top - this.lastWindowY;
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.offsetLocation(0.0f, (float) i);
        VelocityTracker velocityTracker2 = this.velocityTracker;
        if (velocityTracker2 != null) {
            velocityTracker2.addMovement(obtain);
        }
        float x = obtain.getX() - this.lastTouchX;
        float y = obtain.getY() - this.lastTouchY;
        boolean z = true;
        if (!this.isHandling) {
            this.isHandling = Math.abs(y) > Math.abs(x) && Math.abs(y) >= ((float) ViewConfiguration.get(getContext()).getScaledTouchSlop());
        }
        if (this.isHandling) {
            if (this.controller.isInsetAnimationInProgress()) {
                if (this.keyboardHeight == 0) {
                    this.keyboardHeight = this.controller.getCurrentKeyboardHeight();
                }
                int interpolate = this.interpolator.interpolate(MathKt.roundToInt(y), getWindowHeight() - ((int) motionEvent.getRawY()), this.controller.getCurrentKeyboardHeight(), this.offset);
                if (interpolate != 0) {
                    this.controller.insetBy(interpolate);
                }
            } else if (!this.controller.isInsetAnimationRequestPending()) {
                WindowInsetsCompat rootWindowInsets = ViewCompat.getRootWindowInsets(view);
                if (rootWindowInsets == null || !rootWindowInsets.isVisible(WindowInsetsCompat.Type.ime())) {
                    z = false;
                }
                if (shouldStartRequest(y, z)) {
                    KeyboardAnimationController.startControlRequest$default(this.controller, view, (Function1) null, 2, (Object) null);
                }
            }
            this.lastTouchY = motionEvent.getY();
            this.lastTouchX = motionEvent.getX();
            this.lastWindowY = this.bounds.top;
        }
    }

    private final void onActionUp(MotionEvent motionEvent) {
        VelocityTracker velocityTracker2 = this.velocityTracker;
        if (velocityTracker2 != null) {
            velocityTracker2.addMovement(motionEvent);
        }
        VelocityTracker velocityTracker3 = this.velocityTracker;
        if (velocityTracker3 != null) {
            velocityTracker3.computeCurrentVelocity(500);
        }
        VelocityTracker velocityTracker4 = this.velocityTracker;
        Float f = null;
        Float valueOf = velocityTracker4 != null ? Float.valueOf(velocityTracker4.getYVelocity()) : null;
        if (!this.controller.isInsetAnimationInProgress() || this.keyboardHeight != this.controller.getCurrentKeyboardHeight()) {
            f = valueOf;
        }
        this.controller.animateToFinish(f);
        reset();
    }

    private final void onActionCancel() {
        this.controller.cancel();
        reset();
    }

    private final void reset() {
        this.isHandling = false;
        this.lastTouchX = 0.0f;
        this.lastTouchY = 0.0f;
        this.lastWindowY = 0;
        this.keyboardHeight = 0;
        this.bounds.setEmpty();
        VelocityTracker velocityTracker2 = this.velocityTracker;
        if (velocityTracker2 != null) {
            velocityTracker2.recycle();
        }
        this.velocityTracker = null;
    }

    private final boolean shouldStartRequest(float f, boolean z) {
        if (f < 0.0f) {
            if (!z && this.scrollKeyboardOnScreenWhenNotVisible) {
                return true;
            }
        } else if (f > 0.0f && z && this.scrollKeyboardOffScreenWhenVisible) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        r0 = r0.getWindowManager();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int getWindowHeight() {
        /*
            r1 = this;
            com.facebook.react.uimanager.ThemedReactContext r0 = r1.reactContext
            android.app.Activity r0 = r0.getCurrentActivity()
            if (r0 == 0) goto L_0x0013
            android.view.WindowManager r0 = r0.getWindowManager()
            if (r0 == 0) goto L_0x0013
            android.view.WindowMetrics r0 = r0.getCurrentWindowMetrics()
            goto L_0x0014
        L_0x0013:
            r0 = 0
        L_0x0014:
            if (r0 == 0) goto L_0x0021
            android.graphics.Rect r0 = r0.getBounds()
            if (r0 == 0) goto L_0x0021
            int r0 = r0.height()
            goto L_0x0022
        L_0x0021:
            r0 = 0
        L_0x0022:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reactnativekeyboardcontroller.views.KeyboardGestureAreaReactViewGroup.getWindowHeight():int");
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/reactnativekeyboardcontroller/views/KeyboardGestureAreaReactViewGroup$Companion;", "", "()V", "VELOCITY_UNITS", "", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: KeyboardGestureAreaReactViewGroup.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
