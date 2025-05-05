package com.google.ads.interactivemedia.v3.impl.data;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzan extends zzce {
    private final Integer height;
    private final Integer width;
    private final Integer x;
    private final Integer y;

    zzan(Integer num, Integer num2, Integer num3, Integer num4) {
        if (num != null) {
            this.x = num;
            if (num2 != null) {
                this.y = num2;
                if (num3 != null) {
                    this.width = num3;
                    if (num4 != null) {
                        this.height = num4;
                        return;
                    }
                    throw new NullPointerException("Null height");
                }
                throw new NullPointerException("Null width");
            }
            throw new NullPointerException("Null y");
        }
        throw new NullPointerException("Null x");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzce) {
            zzce zzce = (zzce) obj;
            return this.x.equals(zzce.x()) && this.y.equals(zzce.y()) && this.width.equals(zzce.width()) && this.height.equals(zzce.height());
        }
    }

    public int hashCode() {
        return ((((((this.x.hashCode() ^ 1000003) * 1000003) ^ this.y.hashCode()) * 1000003) ^ this.width.hashCode()) * 1000003) ^ this.height.hashCode();
    }

    public Integer height() {
        return this.height;
    }

    public Integer width() {
        return this.width;
    }

    public Integer x() {
        return this.x;
    }

    public Integer y() {
        return this.y;
    }
}
