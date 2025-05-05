package com.adsbynimbus.render;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.ViewCompat;
import com.adsbynimbus.NimbusAd;
import com.adsbynimbus.internal.Logger;
import com.adsbynimbus.render.internal.ExposureTrackerKt;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0004\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0018\b\u0016\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003:\u0001lB%\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010T\u001a\u00020UJ\u0010\u0010V\u001a\u00020\u00142\u0006\u0010W\u001a\u00020\u001bH\u0016J\b\u0010X\u001a\u00020UH\u0016J0\u0010Y\u001a\u00020U2\u0006\u0010Z\u001a\u00020\u00142\u0006\u0010[\u001a\u00020\t2\u0006\u0010\\\u001a\u00020\t2\u0006\u0010]\u001a\u00020\t2\u0006\u0010^\u001a\u00020\tH\u0014J\u0018\u0010_\u001a\u00020U2\u0006\u0010`\u001a\u00020\t2\u0006\u0010a\u001a\u00020\tH\u0014J\b\u0010b\u001a\u00020UH\u0016J\u0010\u0010c\u001a\u00020U2\u0006\u0010d\u001a\u00020>H\u0016J\u0010\u0010e\u001a\u00020U2\u0006\u0010,\u001a\u00020\u0014H\u0016J\u0018\u0010f\u001a\u00020U2\u0006\u0010g\u001a\u00020>2\u0006\u0010h\u001a\u00020\tH\u0014J\u0010\u0010i\u001a\u00020U2\u0006\u0010h\u001a\u00020\tH\u0014J\u0010\u0010j\u001a\u00020U2\u0006\u0010k\u001a\u00020\fH\u0016R\u0012\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R$\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0014@@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR$\u0010 \u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\t@@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u0014\u0010%\u001a\u00020&X\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u001a\u0010)\u001a\u00020\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0017\"\u0004\b+\u0010\u0019R$\u0010-\u001a\u00020\u00142\u0006\u0010,\u001a\u00020\u0014@@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u0017\"\u0004\b.\u0010\u0019R\u001a\u0010/\u001a\u000200X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u0011\u00105\u001a\u0002068F¢\u0006\u0006\u001a\u0004\b7\u00108R\u001a\u00109\u001a\u00020\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u0017\"\u0004\b;\u0010\u0019R \u0010<\u001a\u000e\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u00020&0=X\u0004¢\u0006\b\n\u0000\u001a\u0004\b?\u0010@R\u0014\u0010A\u001a\u00020BX\u0004¢\u0006\b\n\u0000\u001a\u0004\bC\u0010DR\u0014\u0010E\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010F\u001a\u00020&X\u0004¢\u0006\b\n\u0000\u001a\u0004\bG\u0010(R\u0011\u0010H\u001a\u00020&¢\u0006\b\n\u0000\u001a\u0004\bI\u0010(R\u0015\u0010J\u001a\u00020K*\u00020L8F¢\u0006\u0006\u001a\u0004\bM\u0010NR\u001f\u0010O\u001a\u00020\t\"\b\b\u0000\u0010P*\u00020Q*\u0002HP8F¢\u0006\u0006\u001a\u0004\bR\u0010S¨\u0006m"}, d2 = {"Lcom/adsbynimbus/render/NimbusAdView;", "Landroid/widget/FrameLayout;", "Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;", "Landroid/view/ViewTreeObserver$OnScrollChangedListener;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "_alpha", "", "adController", "Lcom/adsbynimbus/render/AdController;", "clickDetector", "Landroidx/core/view/GestureDetectorCompat;", "getClickDetector$render_release", "()Landroidx/core/view/GestureDetectorCompat;", "<set-?>", "", "clickProtectionDisabled", "getClickProtectionDisabled", "()Z", "setClickProtectionDisabled$render_release", "(Z)V", "downEvent", "Landroid/view/MotionEvent;", "getDownEvent$render_release", "()Landroid/view/MotionEvent;", "setDownEvent$render_release", "(Landroid/view/MotionEvent;)V", "exposure", "getExposure", "()I", "setExposure$render_release", "(I)V", "exposureRect", "Landroid/graphics/Rect;", "getExposureRect$render_release", "()Landroid/graphics/Rect;", "exposureScheduled", "getExposureScheduled$render_release", "setExposureScheduled$render_release", "isVisible", "isVisibleInWindow", "setVisibleInWindow$render_release", "lastReportTime", "", "getLastReportTime$render_release", "()J", "setLastReportTime$render_release", "(J)V", "muteButton", "Landroid/widget/ImageButton;", "getMuteButton", "()Landroid/widget/ImageButton;", "needsExposureUpdate", "getNeedsExposureUpdate$render_release", "setNeedsExposureUpdate$render_release", "obstructingViewCache", "Ljava/util/WeakHashMap;", "Landroid/view/View;", "getObstructingViewCache$render_release", "()Ljava/util/WeakHashMap;", "offset", "Landroid/graphics/Point;", "getOffset$render_release", "()Landroid/graphics/Point;", "refreshingController", "tmpRect", "getTmpRect$render_release", "visibleRect", "getVisibleRect", "layoutParams", "Landroid/widget/FrameLayout$LayoutParams;", "Lcom/adsbynimbus/NimbusAd;", "getLayoutParams", "(Lcom/adsbynimbus/NimbusAd;)Landroid/widget/FrameLayout$LayoutParams;", "px", "T", "", "getPx", "(Ljava/lang/Number;)I", "destroy", "", "dispatchTouchEvent", "event", "onGlobalLayout", "onLayout", "changed", "left", "top", "right", "bottom", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onScrollChanged", "onViewAdded", "child", "onVisibilityAggregated", "onVisibilityChanged", "changedView", "visibility", "onWindowVisibilityChanged", "setAlpha", "alpha", "ClickListener", "render_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NimbusAdView.kt */
public class NimbusAdView extends FrameLayout implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    public float _alpha;
    public AdController adController;
    private final GestureDetectorCompat clickDetector;
    private boolean clickProtectionDisabled;
    private MotionEvent downEvent;
    private int exposure;
    private final Rect exposureRect;
    private boolean exposureScheduled;
    private boolean isVisibleInWindow;
    private long lastReportTime;
    private volatile boolean needsExposureUpdate;
    private final WeakHashMap<View, Rect> obstructingViewCache;
    private final Point offset;
    public AdController refreshingController;
    private final Rect tmpRect;
    private final Rect visibleRect;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public NimbusAdView(Context context) {
        this(context, (AttributeSet) null, 0, 6, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public NimbusAdView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        Intrinsics.checkNotNullParameter(view, "changedView");
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i) {
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ NimbusAdView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NimbusAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.visibleRect = new Rect();
        this.exposureRect = new Rect();
        this.needsExposureUpdate = true;
        this.obstructingViewCache = new WeakHashMap<>();
        this.offset = new Point();
        this.tmpRect = new Rect();
        this.clickDetector = new GestureDetectorCompat(context, ClickListener.INSTANCE);
        this._alpha = 1.0f;
    }

    public final ImageButton getMuteButton() {
        ImageButton imageButton = (ImageButton) findViewById(R.id.nimbus_mute);
        if (imageButton == null) {
            imageButton = new ImageButton(getContext());
            imageButton.setId(R.id.nimbus_mute);
            imageButton.setLayoutParams(new FrameLayout.LayoutParams(-2, -2, 8388693));
            ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
            shapeDrawable.setAlpha(50);
            shapeDrawable.getPaint().setColor(ViewCompat.MEASURED_STATE_MASK);
            imageButton.setBackground(shapeDrawable);
            imageButton.setOnClickListener(new NimbusAdView$$ExternalSyntheticLambda0(imageButton, this));
            imageButton.setImageResource(R.drawable.ic_nimbus_volume);
            View view = imageButton;
            int px = getPx((Number) 8);
            view.setPadding(px, px, px, px);
            AdController adController2 = this.adController;
            int volume = adController2 != null ? adController2.getVolume() : 0;
            imageButton.setContentDescription(imageButton.getContext().getString(volume == 0 ? R.string.nimbus_muted : R.string.nimbus_unmuted));
            imageButton.setImageLevel(volume);
            addView(view);
        }
        return imageButton;
    }

    /* access modifiers changed from: private */
    public static final void _get_muteButton_$lambda$5$lambda$3(ImageButton imageButton, NimbusAdView nimbusAdView, View view) {
        AdController adController2 = nimbusAdView.adController;
        int i = 0;
        if (adController2 != null) {
            if (adController2.getVolume() == 0) {
                i = 100;
            }
            adController2.setVolume(i);
            i = adController2.getVolume();
        }
        imageButton.setContentDescription(imageButton.getContext().getString(i == 0 ? R.string.nimbus_muted : R.string.nimbus_unmuted));
        imageButton.setImageLevel(i);
    }

    public final int getExposure() {
        return this.exposure;
    }

    public final void setExposure$render_release(int i) {
        this.exposure = i;
    }

    public final boolean isVisibleInWindow() {
        return this.isVisibleInWindow;
    }

    public final void setVisibleInWindow$render_release(boolean z) {
        if (this.isVisibleInWindow != z) {
            this.isVisibleInWindow = z;
            AdController adController2 = this.adController;
            if (adController2 != null) {
                adController2.dispatchViewableChange$render_release(z);
            }
            AdController adController3 = this.refreshingController;
            if (adController3 != null) {
                adController3.dispatchViewableChange$render_release(z);
            }
            if (z) {
                ExposureTrackerKt.attachListeners(this);
            } else {
                ExposureTrackerKt.removeListeners(this);
            }
            ExposureTrackerKt.scheduleExposureCheck$default(this, 0, 1, (Object) null);
        }
    }

    public final Rect getVisibleRect() {
        return this.visibleRect;
    }

    public final void destroy() {
        removeAllViews();
        ViewParent parent = getParent();
        ViewGroup viewGroup = null;
        ViewGroup viewGroup2 = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup2 != null) {
            if (this.refreshingController == null) {
                viewGroup = viewGroup2;
            }
            if (viewGroup != null) {
                viewGroup.removeView(this);
            }
        }
    }

    public final Rect getExposureRect$render_release() {
        return this.exposureRect;
    }

    public final boolean getExposureScheduled$render_release() {
        return this.exposureScheduled;
    }

    public final void setExposureScheduled$render_release(boolean z) {
        this.exposureScheduled = z;
    }

    public final long getLastReportTime$render_release() {
        return this.lastReportTime;
    }

    public final void setLastReportTime$render_release(long j) {
        this.lastReportTime = j;
    }

    public final boolean getNeedsExposureUpdate$render_release() {
        return this.needsExposureUpdate;
    }

    public final void setNeedsExposureUpdate$render_release(boolean z) {
        this.needsExposureUpdate = z;
    }

    public final WeakHashMap<View, Rect> getObstructingViewCache$render_release() {
        return this.obstructingViewCache;
    }

    public final Point getOffset$render_release() {
        return this.offset;
    }

    public final Rect getTmpRect$render_release() {
        return this.tmpRect;
    }

    public void onVisibilityAggregated(boolean z) {
        super.onVisibilityAggregated(z);
        setVisibleInWindow$render_release(z);
    }

    public void onScrollChanged() {
        ExposureTrackerKt.scheduleExposureCheck$default(this, 0, 1, (Object) null);
    }

    public void onGlobalLayout() {
        ExposureTrackerKt.scheduleExposureCheck$default(this, 0, 1, (Object) null);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        Logger.log(4, "Width: " + View.MeasureSpec.getSize(i) + " Height: " + View.MeasureSpec.getSize(i2));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        boolean z2 = false;
        View childAt = getChildAt(0);
        if (childAt != null) {
            if (childAt.getId() == R.id.nimbus_mute) {
                childAt = null;
            }
            if (childAt != null) {
                float min = Math.min(((float) getWidth()) / ((float) childAt.getWidth()), ((float) getHeight()) / ((float) childAt.getHeight()));
                if (!Float.isInfinite(min) && !Float.isNaN(min)) {
                    z2 = true;
                }
                if (z2) {
                    childAt.setScaleX(min);
                    childAt.setScaleY(min);
                }
            }
        }
    }

    public final boolean getClickProtectionDisabled() {
        return this.clickProtectionDisabled;
    }

    public final void setClickProtectionDisabled$render_release(boolean z) {
        this.clickProtectionDisabled = z;
    }

    public final GestureDetectorCompat getClickDetector$render_release() {
        return this.clickDetector;
    }

    public final MotionEvent getDownEvent$render_release() {
        return this.downEvent;
    }

    public final void setDownEvent$render_release(MotionEvent motionEvent) {
        this.downEvent = motionEvent;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        AdController adController2;
        Intrinsics.checkNotNullParameter(motionEvent, NotificationCompat.CATEGORY_EVENT);
        boolean onTouchEvent = this.clickDetector.onTouchEvent(motionEvent);
        if (onTouchEvent && (adController2 = this.adController) != null) {
            adController2.dispatchClickDetected$render_release();
        }
        if (this.clickProtectionDisabled) {
            super.dispatchTouchEvent(motionEvent);
            return true;
        } else if (onTouchEvent) {
            super.dispatchTouchEvent(this.downEvent);
            super.dispatchTouchEvent(motionEvent);
            MotionEvent motionEvent2 = this.downEvent;
            if (motionEvent2 != null) {
                motionEvent2.recycle();
            }
            this.downEvent = null;
            return true;
        } else if (motionEvent.getActionMasked() == 0) {
            this.downEvent = MotionEvent.obtain(motionEvent);
            return true;
        } else if (motionEvent.getActionMasked() != 3) {
            return true;
        } else {
            MotionEvent motionEvent3 = this.downEvent;
            if (motionEvent3 != null) {
                motionEvent3.recycle();
            }
            this.downEvent = null;
            return true;
        }
    }

    public void onViewAdded(View view) {
        Intrinsics.checkNotNullParameter(view, "child");
        super.onViewAdded(view);
        float f = this._alpha;
        if (f < 1.0f && (view instanceof WebView)) {
            ((WebView) view).setAlpha(f);
        }
    }

    public void setAlpha(float f) {
        this._alpha = f;
    }

    public final <T extends Number> int getPx(T t) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        return MathKt.roundToInt(t.floatValue() * getResources().getDisplayMetrics().density);
    }

    public final FrameLayout.LayoutParams getLayoutParams(NimbusAd nimbusAd) {
        Intrinsics.checkNotNullParameter(nimbusAd, "<this>");
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1, 17);
        if (nimbusAd.height() > 0 && nimbusAd.width() > 0) {
            layoutParams.height = getPx(Integer.valueOf(nimbusAd.height()));
            layoutParams.width = getPx(Integer.valueOf(nimbusAd.width()));
        }
        return layoutParams;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/adsbynimbus/render/NimbusAdView$ClickListener;", "Landroid/view/GestureDetector$SimpleOnGestureListener;", "()V", "onSingleTapUp", "", "e", "Landroid/view/MotionEvent;", "render_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: NimbusAdView.kt */
    public static final class ClickListener extends GestureDetector.SimpleOnGestureListener {
        public static final ClickListener INSTANCE = new ClickListener();

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            Intrinsics.checkNotNullParameter(motionEvent, "e");
            return true;
        }

        private ClickListener() {
        }
    }
}
