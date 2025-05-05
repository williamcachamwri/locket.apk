package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzay  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzay extends zzal<Map.Entry<K, V>> {
    private final /* synthetic */ zzav zza;

    public final int size() {
        return this.zza.zzc;
    }

    public final boolean zze() {
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        zzy.zza(i, this.zza.zzc);
        int i2 = i * 2;
        return new AbstractMap.SimpleImmutableEntry(Objects.requireNonNull(this.zza.zzb[i2]), Objects.requireNonNull(this.zza.zzb[i2 + 1]));
    }

    zzay(zzav zzav) {
        this.zza = zzav;
    }
}
