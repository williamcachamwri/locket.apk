package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.internal.zzps;

@zzps(zza = zzz.class)
/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzbk {
    public static zzbk create(int i, int i2, String str, String str2, String str3) {
        return new zzz(i, i2, str, str2, str3);
    }

    public abstract String alternateText();

    public abstract String creativeType();

    public String getAlternateText() {
        return alternateText();
    }

    public String getCreativeType() {
        return creativeType();
    }

    public int getHeight() {
        return height();
    }

    public String getResourceUri() {
        return imageUrl();
    }

    public int getWidth() {
        return width();
    }

    public abstract int height();

    public abstract String imageUrl();

    public final String toString() {
        int width = width();
        int height = height();
        String imageUrl = imageUrl();
        String alternateText = alternateText();
        String creativeType = creativeType();
        return "IconClickFallbackImageMsgData [width=" + width + ", height=" + height + ", imageUrl=" + imageUrl + ", alternateText=" + alternateText + ", creativeType=" + creativeType + "]";
    }

    public abstract int width();
}
