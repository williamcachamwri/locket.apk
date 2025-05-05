package com.reactnativekeyboardcontroller.listeners;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/reactnativekeyboardcontroller/listeners/Dimensions;", "", "width", "", "height", "(DD)V", "getHeight", "()D", "getWidth", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: WindowDimensionListener.kt */
public final class Dimensions {
    private final double height;
    private final double width;

    public static /* synthetic */ Dimensions copy$default(Dimensions dimensions, double d, double d2, int i, Object obj) {
        if ((i & 1) != 0) {
            d = dimensions.width;
        }
        if ((i & 2) != 0) {
            d2 = dimensions.height;
        }
        return dimensions.copy(d, d2);
    }

    public final double component1() {
        return this.width;
    }

    public final double component2() {
        return this.height;
    }

    public final Dimensions copy(double d, double d2) {
        return new Dimensions(d, d2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Dimensions)) {
            return false;
        }
        Dimensions dimensions = (Dimensions) obj;
        return Double.compare(this.width, dimensions.width) == 0 && Double.compare(this.height, dimensions.height) == 0;
    }

    public int hashCode() {
        return (Double.hashCode(this.width) * 31) + Double.hashCode(this.height);
    }

    public String toString() {
        double d = this.width;
        return "Dimensions(width=" + d + ", height=" + this.height + ")";
    }

    public Dimensions(double d, double d2) {
        this.width = d;
        this.height = d2;
    }

    public final double getWidth() {
        return this.width;
    }

    public final double getHeight() {
        return this.height;
    }
}
