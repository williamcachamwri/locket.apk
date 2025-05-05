package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Collections;
import java.util.HashMap;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zznj  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zznj {
    private HashMap<String, String> zza = new HashMap<>();

    public final zzng zza() {
        if (this.zza != null) {
            zzng zzng = new zzng(Collections.unmodifiableMap(this.zza));
            this.zza = null;
            return zzng;
        }
        throw new IllegalStateException("cannot call build() twice");
    }
}
