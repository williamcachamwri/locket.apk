package com.swmansion.gesturehandler.core;

import android.view.VelocityTracker;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0000H\u0002J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0003R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/swmansion/gesturehandler/core/Vector;", "", "x", "", "y", "(DD)V", "magnitude", "getMagnitude", "()D", "unitX", "unitY", "getX", "getY", "computeSimilarity", "vector", "isSimilar", "", "threshold", "Companion", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Vector.kt */
public final class Vector {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final double MINIMAL_MAGNITUDE = 0.1d;
    /* access modifiers changed from: private */
    public static final Vector VECTOR_DOWN = new Vector(0.0d, 1.0d);
    /* access modifiers changed from: private */
    public static final Vector VECTOR_LEFT = new Vector(-1.0d, 0.0d);
    /* access modifiers changed from: private */
    public static final Vector VECTOR_LEFT_DOWN = new Vector(-1.0d, 1.0d);
    /* access modifiers changed from: private */
    public static final Vector VECTOR_LEFT_UP = new Vector(-1.0d, -1.0d);
    /* access modifiers changed from: private */
    public static final Vector VECTOR_RIGHT = new Vector(1.0d, 0.0d);
    /* access modifiers changed from: private */
    public static final Vector VECTOR_RIGHT_DOWN = new Vector(1.0d, 1.0d);
    /* access modifiers changed from: private */
    public static final Vector VECTOR_RIGHT_UP = new Vector(1.0d, -1.0d);
    /* access modifiers changed from: private */
    public static final Vector VECTOR_UP = new Vector(0.0d, -1.0d);
    /* access modifiers changed from: private */
    public static final Vector VECTOR_ZERO = new Vector(0.0d, 0.0d);
    private final double magnitude;
    private final double unitX;
    private final double unitY;
    private final double x;
    private final double y;

    public Vector(double d, double d2) {
        this.x = d;
        this.y = d2;
        double hypot = Math.hypot(d, d2);
        this.magnitude = hypot;
        boolean z = hypot > MINIMAL_MAGNITUDE;
        double d3 = 0.0d;
        this.unitX = z ? d / hypot : 0.0d;
        this.unitY = z ? d2 / hypot : d3;
    }

    public final double getX() {
        return this.x;
    }

    public final double getY() {
        return this.y;
    }

    public final double getMagnitude() {
        return this.magnitude;
    }

    private final double computeSimilarity(Vector vector) {
        return (this.unitX * vector.unitX) + (this.unitY * vector.unitY);
    }

    public final boolean isSimilar(Vector vector, double d) {
        Intrinsics.checkNotNullParameter(vector, "vector");
        return computeSimilarity(vector) > d;
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/swmansion/gesturehandler/core/Vector$Companion;", "", "()V", "MINIMAL_MAGNITUDE", "", "VECTOR_DOWN", "Lcom/swmansion/gesturehandler/core/Vector;", "VECTOR_LEFT", "VECTOR_LEFT_DOWN", "VECTOR_LEFT_UP", "VECTOR_RIGHT", "VECTOR_RIGHT_DOWN", "VECTOR_RIGHT_UP", "VECTOR_UP", "VECTOR_ZERO", "fromDirection", "direction", "", "fromVelocity", "tracker", "Landroid/view/VelocityTracker;", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: Vector.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Vector fromDirection(int i) {
            switch (i) {
                case 1:
                    return Vector.VECTOR_RIGHT;
                case 2:
                    return Vector.VECTOR_LEFT;
                case 4:
                    return Vector.VECTOR_UP;
                case 5:
                    return Vector.VECTOR_RIGHT_UP;
                case 6:
                    return Vector.VECTOR_LEFT_UP;
                case 8:
                    return Vector.VECTOR_DOWN;
                case 9:
                    return Vector.VECTOR_RIGHT_DOWN;
                case 10:
                    return Vector.VECTOR_LEFT_DOWN;
                default:
                    return Vector.VECTOR_ZERO;
            }
        }

        public final Vector fromVelocity(VelocityTracker velocityTracker) {
            Intrinsics.checkNotNullParameter(velocityTracker, "tracker");
            velocityTracker.computeCurrentVelocity(1000);
            return new Vector((double) velocityTracker.getXVelocity(), (double) velocityTracker.getYVelocity());
        }
    }
}
