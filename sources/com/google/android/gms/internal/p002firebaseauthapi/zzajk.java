package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzajk  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzajk {
    static final zzajk zza = new zzajk(true);
    private static volatile boolean zzb = false;
    private final Map<zzajn, zzajy.zzd<?, ?>> zzc;

    public static zzajk zza() {
        return zza;
    }

    public final <ContainingType extends zzalc> zzajy.zzd<ContainingType, ?> zza(ContainingType containingtype, int i) {
        return this.zzc.get(new zzajn(containingtype, i));
    }

    zzajk() {
        this.zzc = new HashMap();
    }

    private zzajk(boolean z) {
        this.zzc = Collections.emptyMap();
    }
}
