package com.google.android.gms.internal.atv_ads_framework;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.AbstractMap;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
final class zzbm extends zzbe {
    final /* synthetic */ zzbn zza;

    zzbm(zzbn zzbn) {
        this.zza = zzbn;
    }

    public final /* bridge */ /* synthetic */ Object get(int i) {
        zzaq.zza(i, this.zza.zzc, FirebaseAnalytics.Param.INDEX);
        zzbn zzbn = this.zza;
        int i2 = i + i;
        Object obj = zzbn.zzb[i2];
        obj.getClass();
        Object obj2 = zzbn.zzb[i2 + 1];
        obj2.getClass();
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    public final int size() {
        return this.zza.zzc;
    }

    public final boolean zzf() {
        return true;
    }
}
