package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzny  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzny {
    private static final zzny zza = new zzny();
    private static final zzob zzb = new zzob();
    private final AtomicReference<zznl> zzc = new AtomicReference<>();

    public final zznl zza() {
        zznl zznl = this.zzc.get();
        return zznl == null ? zzb : zznl;
    }

    public static zzny zzb() {
        return zza;
    }
}
