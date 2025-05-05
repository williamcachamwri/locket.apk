package com.google.ads.interactivemedia.v3.impl.data;

import java.util.List;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaa extends zzbl {
    private final String alternateText;
    private final int duration;
    private final List<zzbk> fallbackImages;
    private final int height;
    private final int id;
    private final String imageUrl;
    private final int offset;
    private final double pixelRatio;
    private final int width;
    private final String xPosition;
    private final String yPosition;

    zzaa(int i, int i2, int i3, double d, String str, String str2, int i4, int i5, String str3, String str4, List<zzbk> list) {
        this.id = i;
        this.width = i2;
        this.height = i3;
        this.pixelRatio = d;
        if (str != null) {
            this.xPosition = str;
            if (str2 != null) {
                this.yPosition = str2;
                this.offset = i4;
                this.duration = i5;
                if (str3 != null) {
                    this.imageUrl = str3;
                    if (str4 != null) {
                        this.alternateText = str4;
                        if (list != null) {
                            this.fallbackImages = list;
                            return;
                        }
                        throw new NullPointerException("Null fallbackImages");
                    }
                    throw new NullPointerException("Null alternateText");
                }
                throw new NullPointerException("Null imageUrl");
            }
            throw new NullPointerException("Null yPosition");
        }
        throw new NullPointerException("Null xPosition");
    }

    public String alternateText() {
        return this.alternateText;
    }

    public int duration() {
        return this.duration;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzbl) {
            zzbl zzbl = (zzbl) obj;
            return this.id == zzbl.id() && this.width == zzbl.width() && this.height == zzbl.height() && Double.doubleToLongBits(this.pixelRatio) == Double.doubleToLongBits(zzbl.pixelRatio()) && this.xPosition.equals(zzbl.xPosition()) && this.yPosition.equals(zzbl.yPosition()) && this.offset == zzbl.offset() && this.duration == zzbl.duration() && this.imageUrl.equals(zzbl.imageUrl()) && this.alternateText.equals(zzbl.alternateText()) && this.fallbackImages.equals(zzbl.fallbackImages());
        }
    }

    public List<zzbk> fallbackImages() {
        return this.fallbackImages;
    }

    public int hashCode() {
        long doubleToLongBits = (Double.doubleToLongBits(this.pixelRatio) >>> 32) ^ Double.doubleToLongBits(this.pixelRatio);
        int i = (int) doubleToLongBits;
        return ((((((((((((((i ^ ((((((this.id ^ 1000003) * 1000003) ^ this.width) * 1000003) ^ this.height) * 1000003)) * 1000003) ^ this.xPosition.hashCode()) * 1000003) ^ this.yPosition.hashCode()) * 1000003) ^ this.offset) * 1000003) ^ this.duration) * 1000003) ^ this.imageUrl.hashCode()) * 1000003) ^ this.alternateText.hashCode()) * 1000003) ^ this.fallbackImages.hashCode();
    }

    public int height() {
        return this.height;
    }

    public int id() {
        return this.id;
    }

    public String imageUrl() {
        return this.imageUrl;
    }

    public int offset() {
        return this.offset;
    }

    public double pixelRatio() {
        return this.pixelRatio;
    }

    public String toString() {
        String valueOf = String.valueOf(this.fallbackImages);
        return "IconData{id=" + this.id + ", width=" + this.width + ", height=" + this.height + ", pixelRatio=" + this.pixelRatio + ", xPosition=" + this.xPosition + ", yPosition=" + this.yPosition + ", offset=" + this.offset + ", duration=" + this.duration + ", imageUrl=" + this.imageUrl + ", alternateText=" + this.alternateText + ", fallbackImages=" + valueOf + "}";
    }

    public int width() {
        return this.width;
    }

    public String xPosition() {
        return this.xPosition;
    }

    public String yPosition() {
        return this.yPosition;
    }
}
