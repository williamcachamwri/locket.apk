package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.AbstractMap;

/* compiled from: com.google.mlkit:vision-common@@17.3.0 */
final class zzv extends zzp {
    final /* synthetic */ zzw zza;

    zzv(zzw zzw) {
        this.zza = zzw;
    }

    public final /* bridge */ /* synthetic */ Object get(int i) {
        zzf.zza(i, this.zza.zzc, FirebaseAnalytics.Param.INDEX);
        zzw zzw = this.zza;
        int i2 = i + i;
        Object obj = zzw.zzb[i2];
        obj.getClass();
        Object obj2 = zzw.zzb[i2 + 1];
        obj2.getClass();
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    public final int size() {
        return this.zza.zzc;
    }
}
