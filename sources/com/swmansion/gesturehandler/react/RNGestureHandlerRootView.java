package com.swmansion.gesturehandler.react;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.core.app.NotificationCompat;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.views.view.ReactViewGroup;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u000fH\u0016J\b\u0010\u0012\u001a\u00020\nH\u0014J\u0010\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0006H\u0016J\u0006\u0010\u0015\u001a\u00020\nR\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerRootView;", "Lcom/facebook/react/views/view/ReactViewGroup;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_enabled", "", "rootHelper", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerRootHelper;", "activateNativeHandlers", "", "view", "Landroid/view/View;", "dispatchGenericMotionEvent", "event", "Landroid/view/MotionEvent;", "dispatchTouchEvent", "ev", "onAttachedToWindow", "requestDisallowInterceptTouchEvent", "disallowIntercept", "tearDown", "Companion", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RNGestureHandlerRootView.kt */
public final class RNGestureHandlerRootView extends ReactViewGroup {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private boolean _enabled;
    private RNGestureHandlerRootHelper rootHelper;

    public RNGestureHandlerRootView(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewGroup viewGroup = this;
        boolean z = !Companion.hasGestureHandlerEnabledRootView(viewGroup);
        this._enabled = z;
        if (!z) {
            Log.i(ReactConstants.TAG, "[GESTURE HANDLER] Gesture handler is already enabled for a parent view");
        }
        if (this._enabled && this.rootHelper == null) {
            Context context = getContext();
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
            this.rootHelper = new RNGestureHandlerRootHelper((ReactContext) context, viewGroup);
        }
    }

    public final void tearDown() {
        RNGestureHandlerRootHelper rNGestureHandlerRootHelper = this.rootHelper;
        if (rNGestureHandlerRootHelper != null) {
            rNGestureHandlerRootHelper.tearDown();
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "ev");
        if (this._enabled) {
            RNGestureHandlerRootHelper rNGestureHandlerRootHelper = this.rootHelper;
            Intrinsics.checkNotNull(rNGestureHandlerRootHelper);
            if (rNGestureHandlerRootHelper.dispatchTouchEvent(motionEvent)) {
                return true;
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, NotificationCompat.CATEGORY_EVENT);
        if (this._enabled) {
            RNGestureHandlerRootHelper rNGestureHandlerRootHelper = this.rootHelper;
            Intrinsics.checkNotNull(rNGestureHandlerRootHelper);
            if (rNGestureHandlerRootHelper.dispatchTouchEvent(motionEvent)) {
                return true;
            }
        }
        return super.dispatchGenericMotionEvent(motionEvent);
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (this._enabled) {
            RNGestureHandlerRootHelper rNGestureHandlerRootHelper = this.rootHelper;
            Intrinsics.checkNotNull(rNGestureHandlerRootHelper);
            rNGestureHandlerRootHelper.requestDisallowInterceptTouchEvent();
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    public final void activateNativeHandlers(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        RNGestureHandlerRootHelper rNGestureHandlerRootHelper = this.rootHelper;
        if (rNGestureHandlerRootHelper != null) {
            rNGestureHandlerRootHelper.activateNativeHandlers(view);
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¨\u0006\u0007"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerRootView$Companion;", "", "()V", "hasGestureHandlerEnabledRootView", "", "viewGroup", "Landroid/view/ViewGroup;", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: RNGestureHandlerRootView.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final boolean hasGestureHandlerEnabledRootView(ViewGroup viewGroup) {
            UiThreadUtil.assertOnUiThread();
            for (ViewParent parent = viewGroup.getParent(); parent != null; parent = parent.getParent()) {
                if ((parent instanceof RNGestureHandlerEnabledRootView) || (parent instanceof RNGestureHandlerRootView)) {
                    return true;
                }
                if (parent instanceof RootView) {
                    return false;
                }
            }
            return false;
        }
    }
}
