package com.reactnativekeyboardcontroller.views.overlay;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.app.NotificationCompat;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.uimanager.JSTouchDispatcher;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.view.ReactViewGroup;
import com.reactnativekeyboardcontroller.views.overlay.RootViewCompat;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0017H\u0014J\u0018\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u001a\u0010 \u001a\u00020\u00172\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010!\u001a\u00020\u0017H\u0014J\u0010\u0010\"\u001a\u00020\r2\u0006\u0010#\u001a\u00020\u001fH\u0016J\u0010\u0010$\u001a\u00020\r2\u0006\u0010#\u001a\u00020\u001fH\u0016J\u0010\u0010%\u001a\u00020\r2\u0006\u0010#\u001a\u00020\u001fH\u0016J\u0010\u0010&\u001a\u00020\r2\u0006\u0010#\u001a\u00020\u001fH\u0017J\u0010\u0010'\u001a\u00020\u00172\u0006\u0010(\u001a\u00020\rH\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/reactnativekeyboardcontroller/views/overlay/OverKeyboardRootViewGroup;", "Lcom/facebook/react/views/view/ReactViewGroup;", "Lcom/reactnativekeyboardcontroller/views/overlay/RootViewCompat;", "reactContext", "Lcom/facebook/react/uimanager/ThemedReactContext;", "(Lcom/facebook/react/uimanager/ThemedReactContext;)V", "eventDispatcher", "Lcom/facebook/react/uimanager/events/EventDispatcher;", "getEventDispatcher$react_native_keyboard_controller_release", "()Lcom/facebook/react/uimanager/events/EventDispatcher;", "setEventDispatcher$react_native_keyboard_controller_release", "(Lcom/facebook/react/uimanager/events/EventDispatcher;)V", "isAttached", "", "isAttached$react_native_keyboard_controller_release", "()Z", "setAttached$react_native_keyboard_controller_release", "(Z)V", "jsPointerDispatcher", "Lcom/reactnativekeyboardcontroller/views/overlay/JSPointerDispatcherCompat;", "jsTouchDispatcher", "Lcom/facebook/react/uimanager/JSTouchDispatcher;", "handleException", "", "t", "", "onAttachedToWindow", "onChildEndedNativeGesture", "childView", "Landroid/view/View;", "ev", "Landroid/view/MotionEvent;", "onChildStartedNativeGesture", "onDetachedFromWindow", "onHoverEvent", "event", "onInterceptHoverEvent", "onInterceptTouchEvent", "onTouchEvent", "requestDisallowInterceptTouchEvent", "disallowIntercept", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: OverKeyboardViewGroup.kt */
public final class OverKeyboardRootViewGroup extends ReactViewGroup implements RootViewCompat {
    private EventDispatcher eventDispatcher;
    private boolean isAttached;
    private JSPointerDispatcherCompat jsPointerDispatcher;
    private final JSTouchDispatcher jsTouchDispatcher;
    private final ThemedReactContext reactContext;

    public void requestDisallowInterceptTouchEvent(boolean z) {
    }

    @Deprecated(message = "This method shouldn't be used anymore.", replaceWith = @ReplaceWith(expression = "onChildStartedNativeGesture(View childView, MotionEvent ev)", imports = {}))
    public void onChildStartedNativeGesture(MotionEvent motionEvent) {
        RootViewCompat.DefaultImpls.onChildStartedNativeGesture(this, motionEvent);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OverKeyboardRootViewGroup(ThemedReactContext themedReactContext) {
        super(themedReactContext);
        Intrinsics.checkNotNullParameter(themedReactContext, "reactContext");
        this.reactContext = themedReactContext;
        ViewGroup viewGroup = this;
        this.jsTouchDispatcher = new JSTouchDispatcher(viewGroup);
        if (ReactFeatureFlags.dispatchPointerEvents) {
            this.jsPointerDispatcher = new JSPointerDispatcherCompat(viewGroup);
        }
    }

    public final EventDispatcher getEventDispatcher$react_native_keyboard_controller_release() {
        return this.eventDispatcher;
    }

    public final void setEventDispatcher$react_native_keyboard_controller_release(EventDispatcher eventDispatcher2) {
        this.eventDispatcher = eventDispatcher2;
    }

    public final boolean isAttached$react_native_keyboard_controller_release() {
        return this.isAttached;
    }

    public final void setAttached$react_native_keyboard_controller_release(boolean z) {
        this.isAttached = z;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.isAttached = true;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.isAttached = false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, NotificationCompat.CATEGORY_EVENT);
        EventDispatcher eventDispatcher2 = this.eventDispatcher;
        if (eventDispatcher2 != null) {
            this.jsTouchDispatcher.handleTouchEvent(motionEvent, eventDispatcher2);
            JSPointerDispatcherCompat jSPointerDispatcherCompat = this.jsPointerDispatcher;
            if (jSPointerDispatcherCompat != null) {
                jSPointerDispatcherCompat.handleMotionEventCompat(motionEvent, eventDispatcher2, true);
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, NotificationCompat.CATEGORY_EVENT);
        EventDispatcher eventDispatcher2 = this.eventDispatcher;
        if (eventDispatcher2 != null) {
            this.jsTouchDispatcher.handleTouchEvent(motionEvent, eventDispatcher2);
            JSPointerDispatcherCompat jSPointerDispatcherCompat = this.jsPointerDispatcher;
            if (jSPointerDispatcherCompat != null) {
                jSPointerDispatcherCompat.handleMotionEventCompat(motionEvent, eventDispatcher2, false);
            }
        }
        super.onTouchEvent(motionEvent);
        return true;
    }

    public boolean onInterceptHoverEvent(MotionEvent motionEvent) {
        JSPointerDispatcherCompat jSPointerDispatcherCompat;
        Intrinsics.checkNotNullParameter(motionEvent, NotificationCompat.CATEGORY_EVENT);
        EventDispatcher eventDispatcher2 = this.eventDispatcher;
        if (!(eventDispatcher2 == null || (jSPointerDispatcherCompat = this.jsPointerDispatcher) == null)) {
            jSPointerDispatcherCompat.handleMotionEventCompat(motionEvent, eventDispatcher2, true);
        }
        return super.onHoverEvent(motionEvent);
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        JSPointerDispatcherCompat jSPointerDispatcherCompat;
        Intrinsics.checkNotNullParameter(motionEvent, NotificationCompat.CATEGORY_EVENT);
        EventDispatcher eventDispatcher2 = this.eventDispatcher;
        if (!(eventDispatcher2 == null || (jSPointerDispatcherCompat = this.jsPointerDispatcher) == null)) {
            jSPointerDispatcherCompat.handleMotionEventCompat(motionEvent, eventDispatcher2, false);
        }
        return super.onHoverEvent(motionEvent);
    }

    public void onChildStartedNativeGesture(View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "ev");
        EventDispatcher eventDispatcher2 = this.eventDispatcher;
        if (eventDispatcher2 != null) {
            this.jsTouchDispatcher.onChildStartedNativeGesture(motionEvent, eventDispatcher2);
            JSPointerDispatcherCompat jSPointerDispatcherCompat = this.jsPointerDispatcher;
            if (jSPointerDispatcherCompat != null) {
                jSPointerDispatcherCompat.onChildStartedNativeGesture(view, motionEvent, eventDispatcher2);
            }
        }
    }

    public void onChildEndedNativeGesture(View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(view, "childView");
        Intrinsics.checkNotNullParameter(motionEvent, "ev");
        EventDispatcher eventDispatcher2 = this.eventDispatcher;
        if (eventDispatcher2 != null) {
            this.jsTouchDispatcher.onChildEndedNativeGesture(motionEvent, eventDispatcher2);
        }
        JSPointerDispatcherCompat jSPointerDispatcherCompat = this.jsPointerDispatcher;
        if (jSPointerDispatcherCompat != null) {
            jSPointerDispatcherCompat.onChildEndedNativeGesture();
        }
    }

    public void handleException(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "t");
        this.reactContext.getReactApplicationContext().handleException(new RuntimeException(th));
    }
}
