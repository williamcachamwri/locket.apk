package com.google.android.tv.ads;

import android.os.Parcelable;
import com.google.android.gms.internal.atv_ads_framework.zzbe;
import java.util.List;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public abstract class IconClickFallbackImages implements Parcelable {

    /* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
    public static abstract class Builder {
        public abstract IconClickFallbackImages build();
    }

    public abstract List<IconClickFallbackImage> getIconClickFallbackImageList();

    public static Builder builder(List<IconClickFallbackImage> list) {
        list.getClass();
        zzb zzb = new zzb();
        zzb.zza(zzbe.zzj(list));
        return zzb;
    }
}
