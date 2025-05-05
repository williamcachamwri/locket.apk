package com.google.ads.interactivemedia.v3.impl.data;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaq extends zzch {
    private final Integer height;
    private final Integer width;

    zzaq(Integer num, Integer num2) {
        if (num != null) {
            this.width = num;
            if (num2 != null) {
                this.height = num2;
                return;
            }
            throw new NullPointerException("Null height");
        }
        throw new NullPointerException("Null width");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzch) {
            zzch zzch = (zzch) obj;
            return this.width.equals(zzch.width()) && this.height.equals(zzch.height());
        }
    }

    public int hashCode() {
        return ((this.width.hashCode() ^ 1000003) * 1000003) ^ this.height.hashCode();
    }

    public Integer height() {
        return this.height;
    }

    public String toString() {
        return "SizeData{width=" + this.width + ", height=" + this.height + "}";
    }

    public Integer width() {
        return this.width;
    }
}
