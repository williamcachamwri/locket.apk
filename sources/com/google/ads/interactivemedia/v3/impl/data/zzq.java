package com.google.ads.interactivemedia.v3.impl.data;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzq extends zzbb {
    private final int height;
    private final int left;
    private final int top;
    private final int width;

    private zzq(int i, int i2, int i3, int i4) {
        this.left = i;
        this.top = i2;
        this.height = i3;
        this.width = i4;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzbb) {
            zzbb zzbb = (zzbb) obj;
            return this.left == zzbb.left() && this.top == zzbb.top() && this.height == zzbb.height() && this.width == zzbb.width();
        }
    }

    public int hashCode() {
        return ((((((this.left ^ 1000003) * 1000003) ^ this.top) * 1000003) ^ this.height) * 1000003) ^ this.width;
    }

    public int height() {
        return this.height;
    }

    public int left() {
        return this.left;
    }

    public String toString() {
        return "BoundingRectData{left=" + this.left + ", top=" + this.top + ", height=" + this.height + ", width=" + this.width + "}";
    }

    public int top() {
        return this.top;
    }

    public int width() {
        return this.width;
    }
}
