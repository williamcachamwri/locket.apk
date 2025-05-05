package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
public final class zzku<K, V> {
    static <K, V> int zza(zzkt<K, V> zzkt, K k, V v) {
        return zzjm.zza(zzkt.zza, 1, k) + zzjm.zza(zzkt.zzc, 2, v);
    }

    static <K, V> void zza(zzjc zzjc, zzkt<K, V> zzkt, K k, V v) throws IOException {
        zzjm.zza(zzjc, zzkt.zza, 1, k);
        zzjm.zza(zzjc, zzkt.zzc, 2, v);
    }
}
