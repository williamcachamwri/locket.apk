package com.reactnativekeyboardcontroller.events;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003JO\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020\u0003HÖ\u0001J\t\u0010!\u001a\u00020\"HÖ\u0001R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r¨\u0006#"}, d2 = {"Lcom/reactnativekeyboardcontroller/events/FocusedInputSelectionChangedEventData;", "", "target", "", "startX", "", "startY", "endX", "endY", "start", "end", "(IDDDDII)V", "getEnd", "()I", "getEndX", "()D", "getEndY", "getStart", "getStartX", "getStartY", "getTarget", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FocusedInputSelectionChangedEvent.kt */
public final class FocusedInputSelectionChangedEventData {
    private final int end;
    private final double endX;
    private final double endY;
    private final int start;
    private final double startX;
    private final double startY;
    private final int target;

    public static /* synthetic */ FocusedInputSelectionChangedEventData copy$default(FocusedInputSelectionChangedEventData focusedInputSelectionChangedEventData, int i, double d, double d2, double d3, double d4, int i2, int i3, int i4, Object obj) {
        FocusedInputSelectionChangedEventData focusedInputSelectionChangedEventData2 = focusedInputSelectionChangedEventData;
        return focusedInputSelectionChangedEventData.copy((i4 & 1) != 0 ? focusedInputSelectionChangedEventData2.target : i, (i4 & 2) != 0 ? focusedInputSelectionChangedEventData2.startX : d, (i4 & 4) != 0 ? focusedInputSelectionChangedEventData2.startY : d2, (i4 & 8) != 0 ? focusedInputSelectionChangedEventData2.endX : d3, (i4 & 16) != 0 ? focusedInputSelectionChangedEventData2.endY : d4, (i4 & 32) != 0 ? focusedInputSelectionChangedEventData2.start : i2, (i4 & 64) != 0 ? focusedInputSelectionChangedEventData2.end : i3);
    }

    public final int component1() {
        return this.target;
    }

    public final double component2() {
        return this.startX;
    }

    public final double component3() {
        return this.startY;
    }

    public final double component4() {
        return this.endX;
    }

    public final double component5() {
        return this.endY;
    }

    public final int component6() {
        return this.start;
    }

    public final int component7() {
        return this.end;
    }

    public final FocusedInputSelectionChangedEventData copy(int i, double d, double d2, double d3, double d4, int i2, int i3) {
        return new FocusedInputSelectionChangedEventData(i, d, d2, d3, d4, i2, i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FocusedInputSelectionChangedEventData)) {
            return false;
        }
        FocusedInputSelectionChangedEventData focusedInputSelectionChangedEventData = (FocusedInputSelectionChangedEventData) obj;
        return this.target == focusedInputSelectionChangedEventData.target && Double.compare(this.startX, focusedInputSelectionChangedEventData.startX) == 0 && Double.compare(this.startY, focusedInputSelectionChangedEventData.startY) == 0 && Double.compare(this.endX, focusedInputSelectionChangedEventData.endX) == 0 && Double.compare(this.endY, focusedInputSelectionChangedEventData.endY) == 0 && this.start == focusedInputSelectionChangedEventData.start && this.end == focusedInputSelectionChangedEventData.end;
    }

    public int hashCode() {
        return (((((((((((Integer.hashCode(this.target) * 31) + Double.hashCode(this.startX)) * 31) + Double.hashCode(this.startY)) * 31) + Double.hashCode(this.endX)) * 31) + Double.hashCode(this.endY)) * 31) + Integer.hashCode(this.start)) * 31) + Integer.hashCode(this.end);
    }

    public String toString() {
        int i = this.target;
        double d = this.startX;
        double d2 = this.startY;
        double d3 = this.endX;
        double d4 = this.endY;
        int i2 = this.start;
        return "FocusedInputSelectionChangedEventData(target=" + i + ", startX=" + d + ", startY=" + d2 + ", endX=" + d3 + ", endY=" + d4 + ", start=" + i2 + ", end=" + this.end + ")";
    }

    public FocusedInputSelectionChangedEventData(int i, double d, double d2, double d3, double d4, int i2, int i3) {
        this.target = i;
        this.startX = d;
        this.startY = d2;
        this.endX = d3;
        this.endY = d4;
        this.start = i2;
        this.end = i3;
    }

    public final int getTarget() {
        return this.target;
    }

    public final double getStartX() {
        return this.startX;
    }

    public final double getStartY() {
        return this.startY;
    }

    public final double getEndX() {
        return this.endX;
    }

    public final double getEndY() {
        return this.endY;
    }

    public final int getStart() {
        return this.start;
    }

    public final int getEnd() {
        return this.end;
    }
}
