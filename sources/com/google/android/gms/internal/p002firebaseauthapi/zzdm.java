package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Objects;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdm  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzdm extends zzcw {
    private final int zza;
    private final int zzb;
    private final int zzc;
    private final zza zzd;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdm$zzb */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zzb {
        @Nullable
        private Integer zza;
        @Nullable
        private Integer zzb;
        @Nullable
        private Integer zzc;
        private zza zzd;

        public final zzb zza(int i) throws GeneralSecurityException {
            if (i == 12 || i == 16) {
                this.zzb = Integer.valueOf(i);
                return this;
            }
            throw new GeneralSecurityException(String.format("Invalid IV size in bytes %d; acceptable values have 12 or 16 bytes", new Object[]{Integer.valueOf(i)}));
        }

        public final zzb zzb(int i) throws GeneralSecurityException {
            if (i == 16 || i == 24 || i == 32) {
                this.zza = Integer.valueOf(i);
                return this;
            }
            throw new InvalidAlgorithmParameterException(String.format("Invalid key size %d; only 16-byte, 24-byte and 32-byte AES keys are supported", new Object[]{Integer.valueOf(i)}));
        }

        public final zzb zzc(int i) throws GeneralSecurityException {
            this.zzc = 16;
            return this;
        }

        public final zzb zza(zza zza2) {
            this.zzd = zza2;
            return this;
        }

        public final zzdm zza() throws GeneralSecurityException {
            if (this.zza == null) {
                throw new GeneralSecurityException("Key size is not set");
            } else if (this.zzb == null) {
                throw new GeneralSecurityException("IV size is not set");
            } else if (this.zzd == null) {
                throw new GeneralSecurityException("Variant is not set");
            } else if (this.zzc != null) {
                return new zzdm(this.zza.intValue(), this.zzb.intValue(), this.zzc.intValue(), this.zzd);
            } else {
                throw new GeneralSecurityException("Tag size is not set");
            }
        }

        private zzb() {
            this.zza = null;
            this.zzb = null;
            this.zzc = null;
            this.zzd = zza.zzc;
        }
    }

    public final int zzb() {
        return this.zzb;
    }

    public final int zzc() {
        return this.zza;
    }

    public final int zzd() {
        return this.zzc;
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdm$zza */
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

    public final int hashCode() {
        return Objects.hash(new Object[]{zzdm.class, Integer.valueOf(this.zza), Integer.valueOf(this.zzb), Integer.valueOf(this.zzc), this.zzd});
    }

    public static zzb zze() {
        return new zzb();
    }

    public final zza zzf() {
        return this.zzd;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzd);
        int i = this.zzb;
        int i2 = this.zzc;
        return "AesEax Parameters (variant: " + valueOf + ", " + i + "-byte IV, " + i2 + "-byte tag, and " + this.zza + "-byte key)";
    }

    private zzdm(int i, int i2, int i3, zza zza2) {
        this.zza = i;
        this.zzb = i2;
        this.zzc = i3;
        this.zzd = zza2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzdm)) {
            return false;
        }
        zzdm zzdm = (zzdm) obj;
        if (zzdm.zza == this.zza && zzdm.zzb == this.zzb && zzdm.zzc == this.zzc && zzdm.zzd == this.zzd) {
            return true;
        }
        return false;
    }

    public final boolean zza() {
        return this.zzd != zza.zzc;
    }
}
