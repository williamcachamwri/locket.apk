package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Objects;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzei  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzei extends zzcw {
    private final zza zza;

    public final int hashCode() {
        return Objects.hash(new Object[]{zzei.class, this.zza});
    }

    public final zza zzb() {
        return this.zza;
    }

    public static zzei zza(zza zza2) {
        return new zzei(zza2);
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzei$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza {
        public static final zza zza = new zza("TINK");
        public static final zza zzb = new zza("CRUNCHY");
        public static final zza zzc = new zza("NO_PREFIX");
        private final String zzd;

        public final String toString() {
            return this.zzd;
        }

        private zza(String str) {
            this.zzd = str;
        }
    }

    public final String toString() {
        return "ChaCha20Poly1305 Parameters (variant: " + String.valueOf(this.zza) + ")";
    }

    private zzei(zza zza2) {
        this.zza = zza2;
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof zzei) && ((zzei) obj).zza == this.zza) {
            return true;
        }
        return false;
    }

    public final boolean zza() {
        return this.zza != zza.zzc;
    }
}
