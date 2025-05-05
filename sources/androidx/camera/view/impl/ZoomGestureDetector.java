package androidx.camera.view.impl;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 -2\u00020\u0001:\u0003-./B+\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010(\u001a\u00020\fH\u0002J\b\u0010)\u001a\u00020\u0012H\u0002J\u0010\u0010*\u001a\u00020\u00122\u0006\u0010+\u001a\u00020,H\u0007R\u000e\u0010\n\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0015\u001a\u00020\u00058\u0002@\u0002X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0016\u001a\u00020\u00058\u0002@\u0002X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u00020\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001c\"\u0004\b \u0010\u001eR\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0014X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010%\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b&\u0010'¨\u00060"}, d2 = {"Landroidx/camera/view/impl/ZoomGestureDetector;", "", "context", "Landroid/content/Context;", "spanSlop", "", "minSpan", "listener", "Landroidx/camera/view/impl/ZoomGestureDetector$OnZoomGestureListener;", "(Landroid/content/Context;IILandroidx/camera/view/impl/ZoomGestureDetector$OnZoomGestureListener;)V", "anchoredZoomMode", "anchoredZoomStartX", "", "anchoredZoomStartY", "currentSpan", "currentSpanX", "currentSpanY", "eventBeforeOrAboveStartingGestureEvent", "", "eventTime", "", "focusX", "focusY", "gestureDetector", "Landroid/view/GestureDetector;", "initialSpan", "isInProgress", "isQuickZoomEnabled", "()Z", "setQuickZoomEnabled", "(Z)V", "isStylusZoomEnabled", "setStylusZoomEnabled", "prevTime", "previousSpan", "previousSpanX", "previousSpanY", "timeDelta", "getTimeDelta", "()J", "getIncrementalScaleFactor", "inAnchoredZoomMode", "onTouchEvent", "event", "Landroid/view/MotionEvent;", "Companion", "OnZoomGestureListener", "ZoomEvent", "camera-view_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ZoomGestureDetector.kt */
public final class ZoomGestureDetector {
    private static final int ANCHORED_ZOOM_MODE_DOUBLE_TAP = 1;
    private static final int ANCHORED_ZOOM_MODE_NONE = 0;
    private static final int ANCHORED_ZOOM_MODE_STYLUS = 2;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int DEFAULT_MIN_SPAN = 0;
    private static final float SCALE_FACTOR = 0.5f;
    /* access modifiers changed from: private */
    public int anchoredZoomMode;
    /* access modifiers changed from: private */
    public float anchoredZoomStartX;
    /* access modifiers changed from: private */
    public float anchoredZoomStartY;
    private final Context context;
    private float currentSpan;
    private float currentSpanX;
    private float currentSpanY;
    private boolean eventBeforeOrAboveStartingGestureEvent;
    private long eventTime;
    private int focusX;
    private int focusY;
    private GestureDetector gestureDetector;
    private float initialSpan;
    private boolean isInProgress;
    private boolean isQuickZoomEnabled;
    private boolean isStylusZoomEnabled;
    private final OnZoomGestureListener listener;
    private final int minSpan;
    private long prevTime;
    private float previousSpan;
    private float previousSpanX;
    private float previousSpanY;
    private final int spanSlop;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'ø\u0001\u0000\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Landroidx/camera/view/impl/ZoomGestureDetector$OnZoomGestureListener;", "", "onZoomEvent", "", "zoomEvent", "Landroidx/camera/view/impl/ZoomGestureDetector$ZoomEvent;", "camera-view_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ZoomGestureDetector.kt */
    public interface OnZoomGestureListener {
        boolean onZoomEvent(ZoomEvent zoomEvent);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ZoomGestureDetector(Context context2, int i, OnZoomGestureListener onZoomGestureListener) {
        this(context2, i, 0, onZoomGestureListener, 4, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(onZoomGestureListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ZoomGestureDetector(Context context2, OnZoomGestureListener onZoomGestureListener) {
        this(context2, 0, 0, onZoomGestureListener, 6, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(onZoomGestureListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
    }

    public ZoomGestureDetector(Context context2, int i, int i2, OnZoomGestureListener onZoomGestureListener) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(onZoomGestureListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.context = context2;
        this.spanSlop = i;
        this.minSpan = i2;
        this.listener = onZoomGestureListener;
        this.isQuickZoomEnabled = true;
        this.isStylusZoomEnabled = true;
        this.gestureDetector = new GestureDetector(context2, new ZoomGestureDetector$gestureDetector$1(this));
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ZoomGestureDetector(Context context2, int i, int i2, OnZoomGestureListener onZoomGestureListener, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context2, (i3 & 2) != 0 ? ViewConfiguration.get(context2).getScaledTouchSlop() * 2 : i, (i3 & 4) != 0 ? 0 : i2, onZoomGestureListener);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\b&\u0018\u00002\u00020\u0001:\u0003\r\u000e\u000fB%\b\u0002\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0010"}, d2 = {"Landroidx/camera/view/impl/ZoomGestureDetector$ZoomEvent;", "", "eventTime", "", "focusX", "", "focusY", "(JII)V", "getEventTime", "()J", "getFocusX", "()I", "getFocusY", "Begin", "End", "Move", "camera-view_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ZoomGestureDetector.kt */
    public static abstract class ZoomEvent {
        private final long eventTime;
        private final int focusX;
        private final int focusY;

        public /* synthetic */ ZoomEvent(long j, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(j, i, i2);
        }

        private ZoomEvent(long j, int i, int i2) {
            this.eventTime = j;
            this.focusX = i;
            this.focusY = i2;
        }

        public final long getEventTime() {
            return this.eventTime;
        }

        public final int getFocusX() {
            return this.focusX;
        }

        public final int getFocusY() {
            return this.focusY;
        }

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B#\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/camera/view/impl/ZoomGestureDetector$ZoomEvent$Begin;", "Landroidx/camera/view/impl/ZoomGestureDetector$ZoomEvent;", "eventTime", "", "focusX", "", "focusY", "(JII)V", "camera-view_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* compiled from: ZoomGestureDetector.kt */
        public static final class Begin extends ZoomEvent {
            public Begin(long j, int i, int i2) {
                super(j, i, i2, (DefaultConstructorMarker) null);
            }
        }

        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\u0018\u00002\u00020\u0001B-\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/camera/view/impl/ZoomGestureDetector$ZoomEvent$Move;", "Landroidx/camera/view/impl/ZoomGestureDetector$ZoomEvent;", "eventTime", "", "focusX", "", "focusY", "incrementalScaleFactor", "", "(JIIF)V", "getIncrementalScaleFactor", "()F", "camera-view_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* compiled from: ZoomGestureDetector.kt */
        public static final class Move extends ZoomEvent {
            private final float incrementalScaleFactor;

            public final float getIncrementalScaleFactor() {
                return this.incrementalScaleFactor;
            }

            public Move(long j, int i, int i2, float f) {
                super(j, i, i2, (DefaultConstructorMarker) null);
                this.incrementalScaleFactor = f;
            }
        }

        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\u0018\u00002\u00020\u0001B-\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/camera/view/impl/ZoomGestureDetector$ZoomEvent$End;", "Landroidx/camera/view/impl/ZoomGestureDetector$ZoomEvent;", "eventTime", "", "focusX", "", "focusY", "incrementalScaleFactor", "", "(JIIF)V", "getIncrementalScaleFactor", "()F", "camera-view_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* compiled from: ZoomGestureDetector.kt */
        public static final class End extends ZoomEvent {
            private final float incrementalScaleFactor;

            public final float getIncrementalScaleFactor() {
                return this.incrementalScaleFactor;
            }

            public End(long j, int i, int i2, float f) {
                super(j, i, i2, (DefaultConstructorMarker) null);
                this.incrementalScaleFactor = f;
            }
        }
    }

    public final boolean isQuickZoomEnabled() {
        return this.isQuickZoomEnabled;
    }

    public final void setQuickZoomEnabled(boolean z) {
        this.isQuickZoomEnabled = z;
    }

    public final boolean isStylusZoomEnabled() {
        return this.isStylusZoomEnabled;
    }

    public final void setStylusZoomEnabled(boolean z) {
        this.isStylusZoomEnabled = z;
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        float f;
        float f2;
        float f3;
        MotionEvent motionEvent2 = motionEvent;
        Intrinsics.checkNotNullParameter(motionEvent2, NotificationCompat.CATEGORY_EVENT);
        this.eventTime = motionEvent.getEventTime();
        int actionMasked = motionEvent.getActionMasked();
        if (this.isQuickZoomEnabled) {
            this.gestureDetector.onTouchEvent(motionEvent2);
        }
        int pointerCount = motionEvent.getPointerCount();
        boolean z = (motionEvent.getButtonState() & 32) != 0;
        boolean z2 = this.anchoredZoomMode == 2 && !z;
        boolean z3 = actionMasked == 1 || actionMasked == 3 || z2;
        float f4 = 0.0f;
        if (actionMasked == 0 || z3) {
            if (this.isInProgress) {
                this.listener.onZoomEvent(new ZoomEvent.End(this.eventTime, this.focusX, this.focusY, getIncrementalScaleFactor()));
                this.isInProgress = false;
                this.initialSpan = 0.0f;
                this.anchoredZoomMode = 0;
            } else if (inAnchoredZoomMode() && z3) {
                this.isInProgress = false;
                this.initialSpan = 0.0f;
                this.anchoredZoomMode = 0;
            }
            if (z3) {
                return true;
            }
        }
        if (!this.isInProgress && this.isStylusZoomEnabled && !inAnchoredZoomMode() && !z3 && z) {
            this.anchoredZoomStartX = motionEvent.getX();
            this.anchoredZoomStartY = motionEvent.getY();
            this.anchoredZoomMode = 2;
            this.initialSpan = 0.0f;
        }
        boolean z4 = actionMasked == 0 || actionMasked == 6 || actionMasked == 5 || z2;
        boolean z5 = actionMasked == 6;
        int actionIndex = z5 ? motionEvent.getActionIndex() : -1;
        int i = z5 ? pointerCount - 1 : pointerCount;
        if (inAnchoredZoomMode()) {
            f2 = this.anchoredZoomStartX;
            f = this.anchoredZoomStartY;
            this.eventBeforeOrAboveStartingGestureEvent = motionEvent.getY() < f;
        } else {
            float f5 = 0.0f;
            float f6 = 0.0f;
            for (int i2 = 0; i2 < pointerCount; i2++) {
                if (actionIndex != i2) {
                    f5 += motionEvent2.getX(i2);
                    f6 += motionEvent2.getY(i2);
                }
            }
            float f7 = (float) i;
            float f8 = f5 / f7;
            f = f6 / f7;
            f2 = f8;
        }
        float f9 = 0.0f;
        for (int i3 = 0; i3 < pointerCount; i3++) {
            if (actionIndex != i3) {
                f4 += Math.abs(motionEvent2.getX(i3) - f2);
                f9 += Math.abs(motionEvent2.getY(i3) - f);
            }
        }
        float f10 = (float) i;
        float f11 = f4 / f10;
        float f12 = f9 / f10;
        float f13 = (float) 2;
        float f14 = f11 * f13;
        float f15 = f12 * f13;
        if (inAnchoredZoomMode()) {
            f3 = f15;
        } else {
            f3 = (float) Math.hypot((double) f14, (double) f15);
        }
        boolean z6 = this.isInProgress;
        this.focusX = MathKt.roundToInt(f2);
        this.focusY = MathKt.roundToInt(f);
        if (!inAnchoredZoomMode() && this.isInProgress && (f3 < ((float) this.minSpan) || z4)) {
            this.listener.onZoomEvent(new ZoomEvent.End(this.eventTime, this.focusX, this.focusY, getIncrementalScaleFactor()));
            this.isInProgress = false;
            this.initialSpan = f3;
        }
        if (z4) {
            this.currentSpanX = f14;
            this.previousSpanX = f14;
            this.currentSpanY = f15;
            this.previousSpanY = f15;
            this.currentSpan = f3;
            this.previousSpan = f3;
            this.initialSpan = f3;
        }
        int i4 = inAnchoredZoomMode() ? this.spanSlop : this.minSpan;
        if (!this.isInProgress && f3 >= ((float) i4) && (z6 || Math.abs(f3 - this.initialSpan) > ((float) this.spanSlop))) {
            this.currentSpanX = f14;
            this.previousSpanX = f14;
            this.currentSpanY = f15;
            this.previousSpanY = f15;
            this.currentSpan = f3;
            this.previousSpan = f3;
            this.prevTime = this.eventTime;
            this.isInProgress = this.listener.onZoomEvent(new ZoomEvent.Begin(this.eventTime, this.focusX, this.focusY));
        }
        if (actionMasked == 2) {
            this.currentSpanX = f14;
            this.currentSpanY = f15;
            this.currentSpan = f3;
            if (this.isInProgress ? this.listener.onZoomEvent(new ZoomEvent.Move(this.eventTime, this.focusX, this.focusY, getIncrementalScaleFactor())) : true) {
                this.previousSpanX = this.currentSpanX;
                this.previousSpanY = this.currentSpanY;
                this.previousSpan = this.currentSpan;
                this.prevTime = this.eventTime;
            }
        }
        return true;
    }

    private final boolean inAnchoredZoomMode() {
        return this.anchoredZoomMode != 0;
    }

    private final float getIncrementalScaleFactor() {
        if (inAnchoredZoomMode()) {
            boolean z = this.eventBeforeOrAboveStartingGestureEvent;
            boolean z2 = (z && this.currentSpan < this.previousSpan) || (!z && this.currentSpan > this.previousSpan);
            float abs = Math.abs(((float) 1) - (this.currentSpan / this.previousSpan)) * 0.5f;
            if (this.previousSpan <= ((float) this.spanSlop)) {
                return 1.0f;
            }
            return z2 ? 1.0f + abs : 1.0f - abs;
        }
        float f = this.previousSpan;
        if (f > 0.0f) {
            return this.currentSpan / f;
        }
        return 1.0f;
    }

    public final long getTimeDelta() {
        return this.eventTime - this.prevTime;
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/camera/view/impl/ZoomGestureDetector$Companion;", "", "()V", "ANCHORED_ZOOM_MODE_DOUBLE_TAP", "", "ANCHORED_ZOOM_MODE_NONE", "ANCHORED_ZOOM_MODE_STYLUS", "DEFAULT_MIN_SPAN", "SCALE_FACTOR", "", "camera-view_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ZoomGestureDetector.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
