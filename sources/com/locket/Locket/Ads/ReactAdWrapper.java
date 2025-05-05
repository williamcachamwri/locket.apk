package com.locket.Locket.Ads;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0014J\b\u0010\t\u001a\u00020\bH\u0014JR\u0010\n\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u000eH\u0016J\b\u0010\u0016\u001a\u00020\bH\u0016J\b\u0010\u0017\u001a\u00020\bH\u0016¨\u0006\u0018"}, d2 = {"Lcom/locket/Locket/Ads/ReactAdWrapper;", "Landroid/widget/FrameLayout;", "Landroid/view/ViewTreeObserver$OnScrollChangedListener;", "Landroid/view/View$OnLayoutChangeListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "onAttachedToWindow", "", "onDetachedFromWindow", "onLayoutChange", "v", "Landroid/view/View;", "left", "", "top", "right", "bottom", "oldLeft", "oldTop", "oldRight", "oldBottom", "onScrollChanged", "requestLayout", "app_productionRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ReactAdWrapper.kt */
public final class ReactAdWrapper extends FrameLayout implements ViewTreeObserver.OnScrollChangedListener, View.OnLayoutChangeListener {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReactAdWrapper(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnScrollChangedListener(this);
        ViewParent parent = getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            viewGroup.addOnLayoutChangeListener(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnScrollChangedListener(this);
        ViewParent parent = getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            viewGroup.removeOnLayoutChangeListener(this);
        }
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (view != null) {
            int width = view.getWidth();
            int height = view.getHeight();
            measure(View.MeasureSpec.makeMeasureSpec(width, 1073741824), View.MeasureSpec.makeMeasureSpec(height, 1073741824));
            layout(0, 0, width, height);
        }
    }

    public void requestLayout() {
        super.requestLayout();
        post(new ReactAdWrapper$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: private */
    public static final void requestLayout$lambda$0(ReactAdWrapper reactAdWrapper) {
        Intrinsics.checkNotNullParameter(reactAdWrapper, "this$0");
        int width = reactAdWrapper.getWidth();
        int height = reactAdWrapper.getHeight();
        reactAdWrapper.measure(View.MeasureSpec.makeMeasureSpec(width, 1073741824), View.MeasureSpec.makeMeasureSpec(height, 1073741824));
        reactAdWrapper.layout(0, 0, width, height);
    }

    public void onScrollChanged() {
        requestLayout();
    }
}
