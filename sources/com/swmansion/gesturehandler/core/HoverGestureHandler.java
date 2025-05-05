package com.swmansion.gesturehandler.core;

import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.core.app.NotificationCompat;
import com.swmansion.gesturehandler.react.RNViewConfigurationHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u00192\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0019B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0002J\u0015\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\u0001H\u0004J)\u0010\f\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0002\u0010\u0010J\u0018\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0014J\u0018\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0014J\u0014\u0010\u0016\u001a\u00020\n2\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0001H\u0016J\u0014\u0010\u0017\u001a\u00020\n2\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0001H\u0016J\u0014\u0010\u0018\u001a\u00020\n2\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0001H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/swmansion/gesturehandler/core/HoverGestureHandler;", "Lcom/swmansion/gesturehandler/core/GestureHandler;", "()V", "finishRunnable", "Ljava/lang/Runnable;", "handler", "Landroid/os/Handler;", "finish", "", "isAncestorOf", "", "other", "isViewDisplayedOverAnother", "view", "Landroid/view/View;", "rootView", "(Landroid/view/View;Landroid/view/View;Landroid/view/View;)Ljava/lang/Boolean;", "onHandle", "event", "Landroid/view/MotionEvent;", "sourceEvent", "onHandleHover", "shouldBeCancelledBy", "shouldRecognizeSimultaneously", "shouldRequireToWaitForFailure", "Companion", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: HoverGestureHandler.kt */
public final class HoverGestureHandler extends GestureHandler<HoverGestureHandler> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final RNViewConfigurationHelper viewConfigHelper = new RNViewConfigurationHelper();
    private Runnable finishRunnable = new HoverGestureHandler$$ExternalSyntheticLambda0(this);
    private Handler handler;

    /* access modifiers changed from: private */
    public static final void finishRunnable$lambda$0(HoverGestureHandler hoverGestureHandler) {
        Intrinsics.checkNotNullParameter(hoverGestureHandler, "this$0");
        hoverGestureHandler.finish();
    }

    private final boolean isAncestorOf(GestureHandler<?> gestureHandler) {
        View view = gestureHandler.getView();
        while (view != null) {
            if (Intrinsics.areEqual((Object) view, (Object) getView())) {
                return true;
            }
            ViewParent parent = view.getParent();
            view = parent instanceof View ? (View) parent : null;
        }
        return false;
    }

    static /* synthetic */ Boolean isViewDisplayedOverAnother$default(HoverGestureHandler hoverGestureHandler, View view, View view2, View view3, int i, Object obj) {
        if ((i & 4) != 0) {
            view3 = view.getRootView();
            Intrinsics.checkNotNullExpressionValue(view3, "getRootView(...)");
        }
        return hoverGestureHandler.isViewDisplayedOverAnother(view, view2, view3);
    }

    private final Boolean isViewDisplayedOverAnother(View view, View view2, View view3) {
        if (Intrinsics.areEqual((Object) view3, (Object) view2)) {
            return true;
        }
        if (Intrinsics.areEqual((Object) view3, (Object) view)) {
            return false;
        }
        if (!(view3 instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view3;
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            Boolean isViewDisplayedOverAnother = isViewDisplayedOverAnother(view, view2, viewConfigHelper.getChildInDrawingOrderAtIndex(viewGroup, i));
            if (isViewDisplayedOverAnother != null) {
                return isViewDisplayedOverAnother;
            }
        }
        return null;
    }

    public boolean shouldBeCancelledBy(GestureHandler<?> gestureHandler) {
        Intrinsics.checkNotNullParameter(gestureHandler, "handler");
        if (!(gestureHandler instanceof HoverGestureHandler) || ((HoverGestureHandler) gestureHandler).isAncestorOf(this)) {
            return super.shouldBeCancelledBy(gestureHandler);
        }
        View view = gestureHandler.getView();
        Intrinsics.checkNotNull(view);
        View view2 = getView();
        Intrinsics.checkNotNull(view2);
        Boolean isViewDisplayedOverAnother$default = isViewDisplayedOverAnother$default(this, view, view2, (View) null, 4, (Object) null);
        Intrinsics.checkNotNull(isViewDisplayedOverAnother$default);
        return isViewDisplayedOverAnother$default.booleanValue();
    }

    public boolean shouldRequireToWaitForFailure(GestureHandler<?> gestureHandler) {
        Intrinsics.checkNotNullParameter(gestureHandler, "handler");
        if ((gestureHandler instanceof HoverGestureHandler) && !isAncestorOf(gestureHandler) && !((HoverGestureHandler) gestureHandler).isAncestorOf(this)) {
            View view = getView();
            Intrinsics.checkNotNull(view);
            View view2 = gestureHandler.getView();
            Intrinsics.checkNotNull(view2);
            Boolean isViewDisplayedOverAnother$default = isViewDisplayedOverAnother$default(this, view, view2, (View) null, 4, (Object) null);
            if (isViewDisplayedOverAnother$default != null) {
                return isViewDisplayedOverAnother$default.booleanValue();
            }
        }
        return super.shouldRequireToWaitForFailure(gestureHandler);
    }

    public boolean shouldRecognizeSimultaneously(GestureHandler<?> gestureHandler) {
        Intrinsics.checkNotNullParameter(gestureHandler, "handler");
        if (!(gestureHandler instanceof HoverGestureHandler) || (!isAncestorOf(gestureHandler) && !((HoverGestureHandler) gestureHandler).isAncestorOf(this))) {
            return super.shouldRecognizeSimultaneously(gestureHandler);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onHandle(MotionEvent motionEvent, MotionEvent motionEvent2) {
        Intrinsics.checkNotNullParameter(motionEvent, NotificationCompat.CATEGORY_EVENT);
        Intrinsics.checkNotNullParameter(motionEvent2, "sourceEvent");
        if (motionEvent.getAction() == 0) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.removeCallbacksAndMessages((Object) null);
            }
            this.handler = null;
        } else if (motionEvent.getAction() == 1 && !isWithinBounds()) {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onHandleHover(MotionEvent motionEvent, MotionEvent motionEvent2) {
        Intrinsics.checkNotNullParameter(motionEvent, NotificationCompat.CATEGORY_EVENT);
        Intrinsics.checkNotNullParameter(motionEvent2, "sourceEvent");
        if (motionEvent.getAction() == 10) {
            if (this.handler == null) {
                this.handler = new Handler(Looper.getMainLooper());
            }
            Handler handler2 = this.handler;
            Intrinsics.checkNotNull(handler2);
            handler2.postDelayed(this.finishRunnable, 4);
        } else if (!isWithinBounds()) {
            finish();
        } else if (getState() != 0) {
        } else {
            if (motionEvent.getAction() == 7 || motionEvent.getAction() == 9) {
                begin();
                activate();
            }
        }
    }

    private final void finish() {
        int state = getState();
        if (state == 0) {
            cancel();
        } else if (state == 2) {
            fail();
        } else if (state == 4) {
            end();
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/swmansion/gesturehandler/core/HoverGestureHandler$Companion;", "", "()V", "viewConfigHelper", "Lcom/swmansion/gesturehandler/react/RNViewConfigurationHelper;", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: HoverGestureHandler.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
