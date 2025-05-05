package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Objects;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzer  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzer extends zzcw {
    private final String zza;
    private final zza zzb;

    public final int hashCode() {
        return Objects.hash(new Object[]{zzer.class, this.zza, this.zzb});
    }

    public final zza zzb() {
        return this.zzb;
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzer$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza {
        public static final zza zza = new zza("TINK");
        public static final zza zzb = new zza("NO_PREFIX");
        private final String zzc;

        public final String toString() {
            return this.zzc;
        }

        private zza(String str) {
            this.zzc = str;
        }
    }

    public static zzer zza(String str, zza zza2) {
        return new zzer(str, zza2);
    }

    public final String zzc() {
        return this.zza;
    }

    public final String toString() {
        String str = this.zza;
        return "LegacyKmsAead Parameters (keyUri: " + str + ", variant: " + String.valueOf(this.zzb) + ")";
    }

    private zzer(String str, zza zza2) {
        this.zza = str;
        this.zzb = zza2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzer)) {
            return false;
        }
        zzer zzer = (zzer) obj;
        if (!zzer.zza.equals(this.zza) || !zzer.zzb.equals(this.zzb)) {
            return false;
        }
        return true;
    }

    public final boolean zza() {
        return this.zzb != zza.zzb;
    }
}
