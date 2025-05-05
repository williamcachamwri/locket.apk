package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzakv  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzakv implements zzald {
    private zzald[] zza;

    public final zzala zza(Class<?> cls) {
        for (zzald zzald : this.zza) {
            if (zzald.zzb(cls)) {
                return zzald.zza(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: " + cls.getName());
    }

    zzakv(zzald... zzaldArr) {
        this.zza = zzaldArr;
    }

    public final boolean zzb(Class<?> cls) {
        for (zzald zzb : this.zza) {
            if (zzb.zzb(cls)) {
                return true;
            }
        }
        return false;
    }
}
