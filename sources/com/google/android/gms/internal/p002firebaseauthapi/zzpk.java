package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Objects;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpk  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzpk {
    private final Class<? extends zzpj> zza;
    private final zzzc zzb;

    public final int hashCode() {
        return Objects.hash(new Object[]{this.zza, this.zzb});
    }

    public final String toString() {
        String simpleName = this.zza.getSimpleName();
        return simpleName + ", object identifier: " + String.valueOf(this.zzb);
    }

    private zzpk(Class<? extends zzpj> cls, zzzc zzzc) {
        this.zza = cls;
        this.zzb = zzzc;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzpk)) {
            return false;
        }
        zzpk zzpk = (zzpk) obj;
        if (!zzpk.zza.equals(this.zza) || !zzpk.zzb.equals(this.zzb)) {
            return false;
        }
        return true;
    }
}
