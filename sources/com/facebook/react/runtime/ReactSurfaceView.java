package com.facebook.react.runtime;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import com.facebook.common.logging.FLog;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.JSPointerDispatcher;
import com.facebook.react.uimanager.JSTouchDispatcher;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.systrace.Systrace;
import java.util.Objects;

public class ReactSurfaceView extends ReactRootView {
    private static final String TAG = "ReactSurfaceView";
    private int mHeightMeasureSpec = 0;
    private JSPointerDispatcher mJSPointerDispatcher;
    private final JSTouchDispatcher mJSTouchDispatcher;
    private final ReactSurfaceImpl mSurface;
    private boolean mWasMeasured = false;
    private int mWidthMeasureSpec = 0;

    public int getUIManagerType() {
        return 2;
    }

    public ReactSurfaceView(Context context, ReactSurfaceImpl reactSurfaceImpl) {
        super(context);
        this.mSurface = reactSurfaceImpl;
        this.mJSTouchDispatcher = new JSTouchDispatcher(this);
        if (ReactFeatureFlags.dispatchPointerEvents) {
            this.mJSPointerDispatcher = new JSPointerDispatcher(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        Systrace.beginSection(0, "ReactSurfaceView.onMeasure");
        int mode = View.MeasureSpec.getMode(i);
        if (mode == Integer.MIN_VALUE || mode == 0) {
            i3 = 0;
            for (int i5 = 0; i5 < getChildCount(); i5++) {
                View childAt = getChildAt(i5);
                i3 = Math.max(i3, childAt.getLeft() + childAt.getMeasuredWidth() + childAt.getPaddingLeft() + childAt.getPaddingRight());
            }
        } else {
            i3 = View.MeasureSpec.getSize(i);
        }
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode2 == Integer.MIN_VALUE || mode2 == 0) {
            int i6 = 0;
            for (int i7 = 0; i7 < getChildCount(); i7++) {
                View childAt2 = getChildAt(i7);
                i6 = Math.max(i6, childAt2.getTop() + childAt2.getMeasuredHeight() + childAt2.getPaddingTop() + childAt2.getPaddingBottom());
            }
            i4 = i6;
        } else {
            i4 = View.MeasureSpec.getSize(i2);
        }
        setMeasuredDimension(i3, i4);
        this.mWasMeasured = true;
        this.mWidthMeasureSpec = i;
        this.mHeightMeasureSpec = i2;
        Point viewportOffset = getViewportOffset();
        this.mSurface.updateLayoutSpecs(this.mWidthMeasureSpec, this.mHeightMeasureSpec, viewportOffset.x, viewportOffset.y);
        Systrace.endSection(0);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.mWasMeasured && z) {
            Point viewportOffset = getViewportOffset();
            this.mSurface.updateLayoutSpecs(this.mWidthMeasureSpec, this.mHeightMeasureSpec, viewportOffset.x, viewportOffset.y);
        }
    }

    private Point getViewportOffset() {
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        Rect rect = new Rect();
        getWindowVisibleDisplayFrame(rect);
        iArr[0] = iArr[0] - rect.left;
        iArr[1] = iArr[1] - rect.top;
        return new Point(iArr[0], iArr[1]);
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    public void onChildStartedNativeGesture(View view, MotionEvent motionEvent) {
        JSPointerDispatcher jSPointerDispatcher;
        EventDispatcher eventDispatcher = this.mSurface.getEventDispatcher();
        if (eventDispatcher != null) {
            JSTouchDispatcher jSTouchDispatcher = this.mJSTouchDispatcher;
            if (jSTouchDispatcher != null) {
                jSTouchDispatcher.onChildStartedNativeGesture(motionEvent, eventDispatcher);
            }
            if (view != null && (jSPointerDispatcher = this.mJSPointerDispatcher) != null) {
                jSPointerDispatcher.onChildStartedNativeGesture(view, motionEvent, eventDispatcher);
            }
        }
    }

    public void onChildEndedNativeGesture(View view, MotionEvent motionEvent) {
        EventDispatcher eventDispatcher = this.mSurface.getEventDispatcher();
        if (eventDispatcher != null) {
            if (!(this.mJSTouchDispatcher == null || this.mSurface.getEventDispatcher() == null)) {
                this.mJSTouchDispatcher.onChildEndedNativeGesture(motionEvent, this.mSurface.getEventDispatcher());
            }
            JSPointerDispatcher jSPointerDispatcher = this.mJSPointerDispatcher;
            if (jSPointerDispatcher != null) {
                jSPointerDispatcher.onChildStartedNativeGesture(view, motionEvent, eventDispatcher);
            }
        }
    }

    public void handleException(Throwable th) {
        ReactHostImpl reactHost = this.mSurface.getReactHost();
        if (reactHost != null) {
            reactHost.handleHostException(new IllegalViewOperationException(Objects.toString(th.getMessage(), ""), this, th));
        }
    }

    public void setIsFabric(boolean z) {
        super.setIsFabric(true);
    }

    /* access modifiers changed from: protected */
    public void dispatchJSTouchEvent(MotionEvent motionEvent) {
        if (this.mJSTouchDispatcher == null) {
            FLog.w(TAG, "Unable to dispatch touch events to JS before the dispatcher is available");
            return;
        }
        EventDispatcher eventDispatcher = this.mSurface.getEventDispatcher();
        if (eventDispatcher != null) {
            this.mJSTouchDispatcher.handleTouchEvent(motionEvent, eventDispatcher);
        } else {
            FLog.w(TAG, "Unable to dispatch touch events to JS as the React instance has not been attached");
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchJSPointerEvent(MotionEvent motionEvent, boolean z) {
        if (this.mJSPointerDispatcher != null) {
            EventDispatcher eventDispatcher = this.mSurface.getEventDispatcher();
            if (eventDispatcher != null) {
                this.mJSPointerDispatcher.handleMotionEvent(motionEvent, eventDispatcher, z);
            } else {
                FLog.w(TAG, "Unable to dispatch pointer events to JS as the React instance has not been attached");
            }
        } else if (ReactFeatureFlags.dispatchPointerEvents) {
            FLog.w(TAG, "Unable to dispatch pointer events to JS before the dispatcher is available");
        }
    }

    public boolean hasActiveReactContext() {
        return this.mSurface.isAttached() && this.mSurface.getReactHost().getCurrentReactContext() != null;
    }

    public boolean hasActiveReactInstance() {
        return this.mSurface.isAttached() && this.mSurface.getReactHost().isInstanceInitialized();
    }

    public ReactContext getCurrentReactContext() {
        if (this.mSurface.isAttached()) {
            return this.mSurface.getReactHost().getCurrentReactContext();
        }
        return null;
    }

    public boolean isViewAttachedToReactInstance() {
        return this.mSurface.isAttached();
    }
}
