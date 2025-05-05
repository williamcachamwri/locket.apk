package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.BaseDisplayContainer;
import com.google.ads.interactivemedia.v3.impl.data.zzce;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzfq {
    public static boolean zza(BaseDisplayContainer baseDisplayContainer, zzce zzce) {
        return zzce.x().intValue() >= 0 && zzce.width().intValue() >= 0 && zzce.x().intValue() + zzce.width().intValue() <= baseDisplayContainer.getAdContainer().getWidth() && zzce.y().intValue() >= 0 && zzce.height().intValue() >= 0 && zzce.y().intValue() + zzce.height().intValue() <= baseDisplayContainer.getAdContainer().getHeight();
    }
}
