package com.jimmydaddy.imagemarker.base;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, d2 = {"Lcom/jimmydaddy/imagemarker/base/MarkerInsets;", "", "top", "", "left", "bottom", "right", "(IIII)V", "getBottom", "()I", "getLeft", "getRight", "getTop", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MarkerInsets.kt */
public final class MarkerInsets {
    private final int bottom;
    private final int left;
    private final int right;
    private final int top;

    public static /* synthetic */ MarkerInsets copy$default(MarkerInsets markerInsets, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = markerInsets.top;
        }
        if ((i5 & 2) != 0) {
            i2 = markerInsets.left;
        }
        if ((i5 & 4) != 0) {
            i3 = markerInsets.bottom;
        }
        if ((i5 & 8) != 0) {
            i4 = markerInsets.right;
        }
        return markerInsets.copy(i, i2, i3, i4);
    }

    public final int component1() {
        return this.top;
    }

    public final int component2() {
        return this.left;
    }

    public final int component3() {
        return this.bottom;
    }

    public final int component4() {
        return this.right;
    }

    public final MarkerInsets copy(int i, int i2, int i3, int i4) {
        return new MarkerInsets(i, i2, i3, i4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MarkerInsets)) {
            return false;
        }
        MarkerInsets markerInsets = (MarkerInsets) obj;
        return this.top == markerInsets.top && this.left == markerInsets.left && this.bottom == markerInsets.bottom && this.right == markerInsets.right;
    }

    public int hashCode() {
        return (((((Integer.hashCode(this.top) * 31) + Integer.hashCode(this.left)) * 31) + Integer.hashCode(this.bottom)) * 31) + Integer.hashCode(this.right);
    }

    public String toString() {
        int i = this.top;
        int i2 = this.left;
        int i3 = this.bottom;
        return "MarkerInsets(top=" + i + ", left=" + i2 + ", bottom=" + i3 + ", right=" + this.right + ")";
    }

    public MarkerInsets(int i, int i2, int i3, int i4) {
        this.top = i;
        this.left = i2;
        this.bottom = i3;
        this.right = i4;
    }

    public final int getBottom() {
        return this.bottom;
    }

    public final int getLeft() {
        return this.left;
    }

    public final int getRight() {
        return this.right;
    }

    public final int getTop() {
        return this.top;
    }
}
