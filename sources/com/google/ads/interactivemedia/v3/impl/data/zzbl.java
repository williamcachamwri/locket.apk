package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.api.zzb;
import com.google.ads.interactivemedia.v3.internal.zzps;
import java.util.ArrayList;
import java.util.List;

@zzps(zza = zzaa.class)
/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzbl implements zzb {
    public abstract String alternateText();

    public abstract int duration();

    public abstract List<zzbk> fallbackImages();

    public int getDuration() {
        return duration();
    }

    public int getHeight() {
        return height();
    }

    public List getIconClickFallbackImages() {
        ArrayList arrayList = new ArrayList();
        for (zzbk add : fallbackImages()) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public int getId() {
        return id();
    }

    public int getOffset() {
        return offset();
    }

    public double getPixelRatio() {
        return pixelRatio();
    }

    public String getResourceUri() {
        return imageUrl();
    }

    public int getWidth() {
        return width();
    }

    public String getXPosition() {
        return xPosition();
    }

    public String getYPosition() {
        return yPosition();
    }

    public abstract int height();

    public abstract int id();

    public abstract String imageUrl();

    public abstract int offset();

    public abstract double pixelRatio();

    public abstract int width();

    public abstract String xPosition();

    public abstract String yPosition();
}
