package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Objects;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzec  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzec extends zzcw {
    private final int zza;
    private final zza zzb;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzec$zzb */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zzb {
        @Nullable
        private Integer zza;
        private zza zzb;

        public final zzb zza(int i) throws GeneralSecurityException {
            if (i == 16 || i == 32) {
                this.zza = Integer.valueOf(i);
                return this;
            }
            throw new InvalidAlgorithmParameterException(String.format("Invalid key size %d; only 16-byte and 32-byte AES keys are supported", new Object[]{Integer.valueOf(i)}));
        }

        public final zzb zza(zza zza2) {
            this.zzb = zza2;
            return this;
        }

        public final zzec zza() throws GeneralSecurityException {
            if (this.zza == null) {
                throw new GeneralSecurityException("Key size is not set");
            } else if (this.zzb != null) {
                return new zzec(this.zza.intValue(), this.zzb);
            } else {
                throw new GeneralSecurityException("Variant is not set");
            }
        }

        private zzb() {
            this.zza = null;
            this.zzb = zza.zzc;
        }
    }

    public final int zzb() {
        return this.zza;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{zzec.class, Integer.valueOf(this.zza), this.zzb});
    }

    public static zzb zzc() {
        return new zzb();
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzec$zza */
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

    public final zza zzd() {
        return this.zzb;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzb);
        return "AesGcmSiv Parameters (variant: " + valueOf + ", " + this.zza + "-byte key)";
    }

    private zzec(int i, zza zza2) {
        this.zza = i;
        this.zzb = zza2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzec)) {
            return false;
        }
        zzec zzec = (zzec) obj;
        if (zzec.zza == this.zza && zzec.zzb == this.zzb) {
            return true;
        }
        return false;
    }

    public final boolean zza() {
        return this.zzb != zza.zzc;
    }
}
