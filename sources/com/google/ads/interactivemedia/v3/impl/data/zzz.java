package com.google.ads.interactivemedia.v3.impl.data;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzz extends zzbk {
    private final String alternateText;
    private final String creativeType;
    private final int height;
    private final String imageUrl;
    private final int width;

    zzz(int i, int i2, String str, String str2, String str3) {
        this.width = i;
        this.height = i2;
        if (str != null) {
            this.imageUrl = str;
            if (str2 != null) {
                this.alternateText = str2;
                if (str3 != null) {
                    this.creativeType = str3;
                    return;
                }
                throw new NullPointerException("Null creativeType");
            }
            throw new NullPointerException("Null alternateText");
        }
        throw new NullPointerException("Null imageUrl");
    }

    public String alternateText() {
        return this.alternateText;
    }

    public String creativeType() {
        return this.creativeType;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzbk) {
            zzbk zzbk = (zzbk) obj;
            return this.width == zzbk.width() && this.height == zzbk.height() && this.imageUrl.equals(zzbk.imageUrl()) && this.alternateText.equals(zzbk.alternateText()) && this.creativeType.equals(zzbk.creativeType());
        }
    }

    public int hashCode() {
        return ((((((((this.width ^ 1000003) * 1000003) ^ this.height) * 1000003) ^ this.imageUrl.hashCode()) * 1000003) ^ this.alternateText.hashCode()) * 1000003) ^ this.creativeType.hashCode();
    }

    public int height() {
        return this.height;
    }

    public String imageUrl() {
        return this.imageUrl;
    }

    public int width() {
        return this.width;
    }
}
