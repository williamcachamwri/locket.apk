package com.google.android.gms.internal.atv_ads_framework;

import java.util.Comparator;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
final class zzcf implements Comparator {
    zzcf() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzcn zzcn = (zzcn) obj;
        zzcn zzcn2 = (zzcn) obj2;
        zzce zzce = new zzce(zzcn);
        zzce zzce2 = new zzce(zzcn2);
        while (zzce.hasNext() && zzce2.hasNext()) {
            int compareTo = Integer.valueOf(zzce.zza() & 255).compareTo(Integer.valueOf(zzce2.zza() & 255));
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return Integer.valueOf(zzcn.zzd()).compareTo(Integer.valueOf(zzcn2.zzd()));
    }
}
