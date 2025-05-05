package com.reactnativekeyboardcontroller.events;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\nHÆ\u0003J\t\u0010\u001e\u001a\u00020\nHÆ\u0003JY\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\nHÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020\nHÖ\u0001J\t\u0010$\u001a\u00020%HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u000b\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000e¨\u0006&"}, d2 = {"Lcom/reactnativekeyboardcontroller/events/FocusedInputLayoutChangedEventData;", "", "x", "", "y", "width", "height", "absoluteX", "absoluteY", "target", "", "parentScrollViewTarget", "(DDDDDDII)V", "getAbsoluteX", "()D", "getAbsoluteY", "getHeight", "getParentScrollViewTarget", "()I", "getTarget", "getWidth", "getX", "getY", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "toString", "", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FocusedInputLayoutChangedEvent.kt */
public final class FocusedInputLayoutChangedEventData {
    private final double absoluteX;
    private final double absoluteY;
    private final double height;
    private final int parentScrollViewTarget;
    private final int target;
    private final double width;
    private final double x;
    private final double y;

    public static /* synthetic */ FocusedInputLayoutChangedEventData copy$default(FocusedInputLayoutChangedEventData focusedInputLayoutChangedEventData, double d, double d2, double d3, double d4, double d5, double d6, int i, int i2, int i3, Object obj) {
        FocusedInputLayoutChangedEventData focusedInputLayoutChangedEventData2 = focusedInputLayoutChangedEventData;
        int i4 = i3;
        return focusedInputLayoutChangedEventData.copy((i4 & 1) != 0 ? focusedInputLayoutChangedEventData2.x : d, (i4 & 2) != 0 ? focusedInputLayoutChangedEventData2.y : d2, (i4 & 4) != 0 ? focusedInputLayoutChangedEventData2.width : d3, (i4 & 8) != 0 ? focusedInputLayoutChangedEventData2.height : d4, (i4 & 16) != 0 ? focusedInputLayoutChangedEventData2.absoluteX : d5, (i4 & 32) != 0 ? focusedInputLayoutChangedEventData2.absoluteY : d6, (i4 & 64) != 0 ? focusedInputLayoutChangedEventData2.target : i, (i4 & 128) != 0 ? focusedInputLayoutChangedEventData2.parentScrollViewTarget : i2);
    }

    public final double component1() {
        return this.x;
    }

    public final double component2() {
        return this.y;
    }

    public final double component3() {
        return this.width;
    }

    public final double component4() {
        return this.height;
    }

    public final double component5() {
        return this.absoluteX;
    }

    public final double component6() {
        return this.absoluteY;
    }

    public final int component7() {
        return this.target;
    }

    public final int component8() {
        return this.parentScrollViewTarget;
    }

    public final FocusedInputLayoutChangedEventData copy(double d, double d2, double d3, double d4, double d5, double d6, int i, int i2) {
        return new FocusedInputLayoutChangedEventData(d, d2, d3, d4, d5, d6, i, i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FocusedInputLayoutChangedEventData)) {
            return false;
        }
        FocusedInputLayoutChangedEventData focusedInputLayoutChangedEventData = (FocusedInputLayoutChangedEventData) obj;
        return Double.compare(this.x, focusedInputLayoutChangedEventData.x) == 0 && Double.compare(this.y, focusedInputLayoutChangedEventData.y) == 0 && Double.compare(this.width, focusedInputLayoutChangedEventData.width) == 0 && Double.compare(this.height, focusedInputLayoutChangedEventData.height) == 0 && Double.compare(this.absoluteX, focusedInputLayoutChangedEventData.absoluteX) == 0 && Double.compare(this.absoluteY, focusedInputLayoutChangedEventData.absoluteY) == 0 && this.target == focusedInputLayoutChangedEventData.target && this.parentScrollViewTarget == focusedInputLayoutChangedEventData.parentScrollViewTarget;
    }

    public int hashCode() {
        return (((((((((((((Double.hashCode(this.x) * 31) + Double.hashCode(this.y)) * 31) + Double.hashCode(this.width)) * 31) + Double.hashCode(this.height)) * 31) + Double.hashCode(this.absoluteX)) * 31) + Double.hashCode(this.absoluteY)) * 31) + Integer.hashCode(this.target)) * 31) + Integer.hashCode(this.parentScrollViewTarget);
    }

    public String toString() {
        double d = this.x;
        double d2 = this.y;
        double d3 = this.width;
        double d4 = this.height;
        double d5 = this.absoluteX;
        double d6 = this.absoluteY;
        int i = this.target;
        return "FocusedInputLayoutChangedEventData(x=" + d + ", y=" + d2 + ", width=" + d3 + ", height=" + d4 + ", absoluteX=" + d5 + ", absoluteY=" + d6 + ", target=" + i + ", parentScrollViewTarget=" + this.parentScrollViewTarget + ")";
    }

    public FocusedInputLayoutChangedEventData(double d, double d2, double d3, double d4, double d5, double d6, int i, int i2) {
        this.x = d;
        this.y = d2;
        this.width = d3;
        this.height = d4;
        this.absoluteX = d5;
        this.absoluteY = d6;
        this.target = i;
        this.parentScrollViewTarget = i2;
    }

    public final double getX() {
        return this.x;
    }

    public final double getY() {
        return this.y;
    }

    public final double getWidth() {
        return this.width;
    }

    public final double getHeight() {
        return this.height;
    }

    public final double getAbsoluteX() {
        return this.absoluteX;
    }

    public final double getAbsoluteY() {
        return this.absoluteY;
    }

    public final int getTarget() {
        return this.target;
    }

    public final int getParentScrollViewTarget() {
        return this.parentScrollViewTarget;
    }
}
