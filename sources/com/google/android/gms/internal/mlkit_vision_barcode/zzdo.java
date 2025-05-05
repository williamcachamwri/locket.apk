package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
final class zzdo extends zzcs {
    private final transient Object[] zza;
    private final transient int zzb;
    private final transient int zzc = 1;

    zzdo(Object[] objArr, int i, int i2) {
        this.zza = objArr;
        this.zzb = i;
    }

    public final Object get(int i) {
        zzaz.zza(i, this.zzc, FirebaseAnalytics.Param.INDEX);
        return Objects.requireNonNull(this.zza[i + i + this.zzb]);
    }

    public final int size() {
        return this.zzc;
    }
}
