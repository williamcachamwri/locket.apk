package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.AbstractMap;
import java.util.Objects;

/* compiled from: com.google.mlkit:common@@18.11.0 */
final class zzam extends zzaf {
    final /* synthetic */ zzan zza;

    zzam(zzan zzan) {
        this.zza = zzan;
    }

    public final /* bridge */ /* synthetic */ Object get(int i) {
        zzt.zza(i, this.zza.zzc, FirebaseAnalytics.Param.INDEX);
        int i2 = i + i;
        return new AbstractMap.SimpleImmutableEntry(Objects.requireNonNull(this.zza.zzb[i2]), Objects.requireNonNull(this.zza.zzb[i2 + 1]));
    }

    public final int size() {
        return this.zza.zzc;
    }
}
