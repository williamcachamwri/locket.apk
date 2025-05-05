package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Objects;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzox  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzox {
    private final Class<?> zza;
    private final Class<?> zzb;

    public final int hashCode() {
        return Objects.hash(new Object[]{this.zza, this.zzb});
    }

    public final String toString() {
        String simpleName = this.zza.getSimpleName();
        return simpleName + " with primitive type: " + this.zzb.getSimpleName();
    }

    private zzox(Class<?> cls, Class<?> cls2) {
        this.zza = cls;
        this.zzb = cls2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzox)) {
            return false;
        }
        zzox zzox = (zzox) obj;
        if (!zzox.zza.equals(this.zza) || !zzox.zzb.equals(this.zzb)) {
            return false;
        }
        return true;
    }
}
