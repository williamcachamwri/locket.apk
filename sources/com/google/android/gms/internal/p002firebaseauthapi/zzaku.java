package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaku  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzaku<K, V> {
    static <K, V> int zza(zzakx<K, V> zzakx, K k, V v) {
        return zzajr.zza(zzakx.zza, 1, k) + zzajr.zza(zzakx.zzc, 2, v);
    }

    static <K, V> void zza(zzajg zzajg, zzakx<K, V> zzakx, K k, V v) throws IOException {
        zzajr.zza(zzajg, zzakx.zza, 1, k);
        zzajr.zza(zzajg, zzakx.zzc, 2, v);
    }
}
