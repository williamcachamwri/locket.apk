package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Objects;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzft  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzft extends zzcw {
    private final zza zza;
    private final int zzb;

    public final int zzb() {
        return this.zzb;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{zzft.class, this.zza, Integer.valueOf(this.zzb)});
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzft$zza */
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

    public final zza zzc() {
        return this.zza;
    }

    public static zzft zza(zza zza2, int i) throws GeneralSecurityException {
        if (i >= 8 && i <= 12) {
            return new zzft(zza2, i);
        }
        throw new GeneralSecurityException("Salt size must be between 8 and 12 bytes");
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zza);
        return "X-AES-GCM Parameters (variant: " + valueOf + "salt_size_bytes: " + this.zzb + ")";
    }

    private zzft(zza zza2, int i) {
        this.zza = zza2;
        this.zzb = i;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzft)) {
            return false;
        }
        zzft zzft = (zzft) obj;
        if (zzft.zza == this.zza && zzft.zzb == this.zzb) {
            return true;
        }
        return false;
    }

    public final boolean zza() {
        return this.zza != zza.zzb;
    }
}
