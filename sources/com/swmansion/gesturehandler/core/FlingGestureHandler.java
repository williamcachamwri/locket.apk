package com.swmansion.gesturehandler.core;

import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 &2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001&B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u001a\u0010\u001a\u001a\u00020\u00172\b\u0010\u001b\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0010\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001f\u001a\u00020\u0017H\u0014J\u0018\u0010 \u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\u001dH\u0014J\b\u0010\"\u001a\u00020\u0017H\u0014J\b\u0010#\u001a\u00020\u0017H\u0016J\u0010\u0010$\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0010\u0010%\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001dH\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eXD¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000eXD¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0006\"\u0004\b\u0013\u0010\bR\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/swmansion/gesturehandler/core/FlingGestureHandler;", "Lcom/swmansion/gesturehandler/core/GestureHandler;", "()V", "direction", "", "getDirection", "()I", "setDirection", "(I)V", "failDelayed", "Ljava/lang/Runnable;", "handler", "Landroid/os/Handler;", "maxDurationMs", "", "maxNumberOfPointersSimultaneously", "minVelocity", "numberOfPointersRequired", "getNumberOfPointersRequired", "setNumberOfPointersRequired", "velocityTracker", "Landroid/view/VelocityTracker;", "activate", "", "force", "", "addVelocityMovement", "tracker", "event", "Landroid/view/MotionEvent;", "endFling", "onCancel", "onHandle", "sourceEvent", "onReset", "resetConfig", "startFling", "tryEndFling", "Companion", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FlingGestureHandler.kt */
public final class FlingGestureHandler extends GestureHandler<FlingGestureHandler> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final double DEFAULT_ALIGNMENT_CONE = 30.0d;
    private static final int DEFAULT_DIRECTION = 1;
    private static final long DEFAULT_MAX_DURATION_MS = 800;
    private static final long DEFAULT_MIN_VELOCITY = 2000;
    private static final int DEFAULT_NUMBER_OF_TOUCHES_REQUIRED = 1;
    private static final double MAX_AXIAL_DEVIATION = GestureUtils.INSTANCE.coneToDeviation(DEFAULT_ALIGNMENT_CONE);
    private static final double MAX_DIAGONAL_DEVIATION = GestureUtils.INSTANCE.coneToDeviation(60.0d);
    private int direction = 1;
    private final Runnable failDelayed = new FlingGestureHandler$$ExternalSyntheticLambda0(this);
    private Handler handler;
    private final long maxDurationMs = DEFAULT_MAX_DURATION_MS;
    private int maxNumberOfPointersSimultaneously;
    private final long minVelocity = 2000;
    private int numberOfPointersRequired = 1;
    private VelocityTracker velocityTracker;

    public final int getNumberOfPointersRequired() {
        return this.numberOfPointersRequired;
    }

    public final void setNumberOfPointersRequired(int i) {
        this.numberOfPointersRequired = i;
    }

    public final int getDirection() {
        return this.direction;
    }

    public final void setDirection(int i) {
        this.direction = i;
    }

    /* access modifiers changed from: private */
    public static final void failDelayed$lambda$0(FlingGestureHandler flingGestureHandler) {
        Intrinsics.checkNotNullParameter(flingGestureHandler, "this$0");
        flingGestureHandler.fail();
    }

    public void resetConfig() {
        super.resetConfig();
        this.numberOfPointersRequired = 1;
        this.direction = 1;
    }

    private final void startFling(MotionEvent motionEvent) {
        this.velocityTracker = VelocityTracker.obtain();
        begin();
        this.maxNumberOfPointersSimultaneously = 1;
        Handler handler2 = this.handler;
        if (handler2 == null) {
            this.handler = new Handler(Looper.getMainLooper());
        } else {
            Intrinsics.checkNotNull(handler2);
            handler2.removeCallbacksAndMessages((Object) null);
        }
        Handler handler3 = this.handler;
        Intrinsics.checkNotNull(handler3);
        handler3.postDelayed(this.failDelayed, this.maxDurationMs);
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00f5  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00f7  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00d0 A[EDGE_INSN: B:42:0x00d0->B:21:0x00d0 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean tryEndFling(android.view.MotionEvent r12) {
        /*
            r11 = this;
            android.view.VelocityTracker r0 = r11.velocityTracker
            r11.addVelocityMovement(r0, r12)
            com.swmansion.gesturehandler.core.Vector$Companion r12 = com.swmansion.gesturehandler.core.Vector.Companion
            android.view.VelocityTracker r0 = r11.velocityTracker
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            com.swmansion.gesturehandler.core.Vector r12 = r12.fromVelocity(r0)
            r0 = 4
            java.lang.Integer[] r1 = new java.lang.Integer[r0]
            r2 = 2
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)
            r4 = 0
            r1[r4] = r3
            r3 = 1
            java.lang.Integer r5 = java.lang.Integer.valueOf(r3)
            r1[r3] = r5
            java.lang.Integer r5 = java.lang.Integer.valueOf(r0)
            r1[r2] = r5
            r5 = 8
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r6 = 3
            r1[r6] = r5
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>(r0)
            java.util.Collection r5 = (java.util.Collection) r5
            r7 = r4
        L_0x0039:
            if (r7 >= r0) goto L_0x0053
            r8 = r1[r7]
            java.lang.Number r8 = (java.lang.Number) r8
            int r8 = r8.intValue()
            double r9 = MAX_AXIAL_DEVIATION
            boolean r8 = tryEndFling$getVelocityAlignment(r11, r12, r8, r9)
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r8)
            r5.add(r8)
            int r7 = r7 + 1
            goto L_0x0039
        L_0x0053:
            java.util.List r5 = (java.util.List) r5
            java.lang.Integer[] r1 = new java.lang.Integer[r0]
            r7 = 5
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r1[r4] = r7
            r7 = 9
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r1[r3] = r7
            r7 = 6
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r1[r2] = r7
            r2 = 10
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r1[r6] = r2
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>(r0)
            java.util.Collection r2 = (java.util.Collection) r2
            r6 = r4
        L_0x007d:
            if (r6 >= r0) goto L_0x0097
            r7 = r1[r6]
            java.lang.Number r7 = (java.lang.Number) r7
            int r7 = r7.intValue()
            double r8 = MAX_DIAGONAL_DEVIATION
            boolean r7 = tryEndFling$getVelocityAlignment(r11, r12, r7, r8)
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)
            r2.add(r7)
            int r6 = r6 + 1
            goto L_0x007d
        L_0x0097:
            java.util.List r2 = (java.util.List) r2
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            boolean r0 = r5 instanceof java.util.Collection
            if (r0 == 0) goto L_0x00aa
            r0 = r5
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x00aa
        L_0x00a8:
            r0 = r4
            goto L_0x00c1
        L_0x00aa:
            java.util.Iterator r0 = r5.iterator()
        L_0x00ae:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x00a8
            java.lang.Object r1 = r0.next()
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x00ae
            r0 = r3
        L_0x00c1:
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            boolean r1 = r2 instanceof java.util.Collection
            if (r1 == 0) goto L_0x00d2
            r1 = r2
            java.util.Collection r1 = (java.util.Collection) r1
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x00d2
        L_0x00d0:
            r1 = r4
            goto L_0x00e9
        L_0x00d2:
            java.util.Iterator r1 = r2.iterator()
        L_0x00d6:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x00d0
            java.lang.Object r2 = r1.next()
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L_0x00d6
            r1 = r3
        L_0x00e9:
            r0 = r0 | r1
            double r1 = r12.getMagnitude()
            long r5 = r11.minVelocity
            double r5 = (double) r5
            int r12 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r12 <= 0) goto L_0x00f7
            r12 = r3
            goto L_0x00f8
        L_0x00f7:
            r12 = r4
        L_0x00f8:
            int r1 = r11.maxNumberOfPointersSimultaneously
            int r2 = r11.numberOfPointersRequired
            if (r1 != r2) goto L_0x010f
            if (r0 == 0) goto L_0x010f
            if (r12 == 0) goto L_0x010f
            android.os.Handler r12 = r11.handler
            kotlin.jvm.internal.Intrinsics.checkNotNull(r12)
            r0 = 0
            r12.removeCallbacksAndMessages(r0)
            r11.activate()
            r4 = r3
        L_0x010f:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.core.FlingGestureHandler.tryEndFling(android.view.MotionEvent):boolean");
    }

    private static final boolean tryEndFling$getVelocityAlignment(FlingGestureHandler flingGestureHandler, Vector vector, int i, double d) {
        return (flingGestureHandler.direction & i) == i && vector.isSimilar(Vector.Companion.fromDirection(i), d);
    }

    public void activate(boolean z) {
        super.activate(z);
        end();
    }

    private final void endFling(MotionEvent motionEvent) {
        if (!tryEndFling(motionEvent)) {
            fail();
        }
    }

    /* access modifiers changed from: protected */
    public void onHandle(MotionEvent motionEvent, MotionEvent motionEvent2) {
        Intrinsics.checkNotNullParameter(motionEvent, NotificationCompat.CATEGORY_EVENT);
        Intrinsics.checkNotNullParameter(motionEvent2, "sourceEvent");
        if (shouldActivateWithMouse(motionEvent2)) {
            int state = getState();
            if (state == 0) {
                startFling(motionEvent2);
            }
            if (state == 2) {
                tryEndFling(motionEvent2);
                if (motionEvent2.getPointerCount() > this.maxNumberOfPointersSimultaneously) {
                    this.maxNumberOfPointersSimultaneously = motionEvent2.getPointerCount();
                }
                if (motionEvent2.getActionMasked() == 1) {
                    endFling(motionEvent2);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCancel() {
        Handler handler2 = this.handler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages((Object) null);
        }
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        VelocityTracker velocityTracker2 = this.velocityTracker;
        if (velocityTracker2 != null) {
            velocityTracker2.recycle();
        }
        this.velocityTracker = null;
        Handler handler2 = this.handler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages((Object) null);
        }
    }

    private final void addVelocityMovement(VelocityTracker velocityTracker2, MotionEvent motionEvent) {
        float rawX = motionEvent.getRawX() - motionEvent.getX();
        float rawY = motionEvent.getRawY() - motionEvent.getY();
        motionEvent.offsetLocation(rawX, rawY);
        Intrinsics.checkNotNull(velocityTracker2);
        velocityTracker2.addMovement(motionEvent);
        motionEvent.offsetLocation(-rawX, -rawY);
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/swmansion/gesturehandler/core/FlingGestureHandler$Companion;", "", "()V", "DEFAULT_ALIGNMENT_CONE", "", "DEFAULT_DIRECTION", "", "DEFAULT_MAX_DURATION_MS", "", "DEFAULT_MIN_VELOCITY", "DEFAULT_NUMBER_OF_TOUCHES_REQUIRED", "MAX_AXIAL_DEVIATION", "MAX_DIAGONAL_DEVIATION", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: FlingGestureHandler.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
