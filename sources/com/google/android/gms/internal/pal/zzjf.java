package com.google.android.gms.internal.pal;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.AbstractMap;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzjf extends zziz {
    final /* synthetic */ zzjg zza;

    zzjf(zzjg zzjg) {
        this.zza = zzjg;
    }

    public final /* bridge */ /* synthetic */ Object get(int i) {
        zzip.zza(i, this.zza.zzc, FirebaseAnalytics.Param.INDEX);
        zzjg zzjg = this.zza;
        int i2 = i + i;
        Object obj = zzjg.zzb[i2];
        obj.getClass();
        Object obj2 = zzjg.zzb[i2 + 1];
        obj2.getClass();
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    public final int size() {
        return this.zza.zzc;
    }
}
