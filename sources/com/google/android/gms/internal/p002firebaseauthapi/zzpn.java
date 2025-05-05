package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Objects;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpn  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzpn {
    private final Class<?> zza;
    private final Class<? extends zzpj> zzb;

    public final int hashCode() {
        return Objects.hash(new Object[]{this.zza, this.zzb});
    }

    public final String toString() {
        String simpleName = this.zza.getSimpleName();
        return simpleName + " with serialization type: " + this.zzb.getSimpleName();
    }

    private zzpn(Class<?> cls, Class<? extends zzpj> cls2) {
        this.zza = cls;
        this.zzb = cls2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzpn)) {
            return false;
        }
        zzpn zzpn = (zzpn) obj;
        if (!zzpn.zza.equals(this.zza) || !zzpn.zzb.equals(this.zzb)) {
            return false;
        }
        return true;
    }
}
