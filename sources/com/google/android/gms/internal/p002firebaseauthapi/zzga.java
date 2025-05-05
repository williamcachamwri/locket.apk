package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Objects;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzga  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzga extends zzcw {
    private final zza zza;

    public final int hashCode() {
        return Objects.hash(new Object[]{zzga.class, this.zza});
    }

    public final zza zzb() {
        return this.zza;
    }

    public static zzga zzc() {
        return new zzga(zza.zzc);
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzga$zza */
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

    public static zzga zza(zza zza2) {
        return new zzga(zza2);
    }

    public final String toString() {
        return "XChaCha20Poly1305 Parameters (variant: " + String.valueOf(this.zza) + ")";
    }

    private zzga(zza zza2) {
        this.zza = zza2;
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof zzga) && ((zzga) obj).zza == this.zza) {
            return true;
        }
        return false;
    }

    public final boolean zza() {
        return this.zza != zza.zzc;
    }
}
