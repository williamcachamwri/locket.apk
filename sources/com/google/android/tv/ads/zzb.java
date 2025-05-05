package com.google.android.tv.ads;

import com.google.android.tv.ads.IconClickFallbackImages;
import java.util.List;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
final class zzb extends IconClickFallbackImages.Builder {
    private List zza;

    zzb() {
    }

    public final IconClickFallbackImages build() {
        List list = this.zza;
        if (list != null) {
            return new zzf(list);
        }
        throw new IllegalStateException("Missing required properties: iconClickFallbackImageList");
    }

    public final IconClickFallbackImages.Builder zza(List list) {
        if (list != null) {
            this.zza = list;
            return this;
        }
        throw new NullPointerException("Null iconClickFallbackImageList");
    }
}
