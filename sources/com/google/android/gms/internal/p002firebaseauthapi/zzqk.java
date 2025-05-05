package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Objects;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqk  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzqk extends zzqn {
    private final int zza;
    private final int zzb;
    private final zzb zzc;
    private final zzc zzd;

    public final int zzb() {
        return this.zzb;
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqk$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza {
        @Nullable
        private Integer zza;
        @Nullable
        private Integer zzb;
        private zzc zzc;
        private zzb zzd;

        public final zza zza(zzc zzc2) {
            this.zzc = zzc2;
            return this;
        }

        public final zza zza(int i) throws GeneralSecurityException {
            this.zza = Integer.valueOf(i);
            return this;
        }

        public final zza zzb(int i) throws GeneralSecurityException {
            this.zzb = Integer.valueOf(i);
            return this;
        }

        public final zza zza(zzb zzb2) {
            this.zzd = zzb2;
            return this;
        }

        public final zzqk zza() throws GeneralSecurityException {
            Integer num = this.zza;
            if (num == null) {
                throw new GeneralSecurityException("key size is not set");
            } else if (this.zzb == null) {
                throw new GeneralSecurityException("tag size is not set");
            } else if (this.zzc == null) {
                throw new GeneralSecurityException("hash type is not set");
            } else if (this.zzd == null) {
                throw new GeneralSecurityException("variant is not set");
            } else if (num.intValue() >= 16) {
                int intValue = this.zzb.intValue();
                zzc zzc2 = this.zzc;
                if (intValue >= 10) {
                    if (zzc2 == zzc.zza) {
                        if (intValue > 20) {
                            throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 20 bytes for SHA1", new Object[]{Integer.valueOf(intValue)}));
                        }
                    } else if (zzc2 == zzc.zzb) {
                        if (intValue > 28) {
                            throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 28 bytes for SHA224", new Object[]{Integer.valueOf(intValue)}));
                        }
                    } else if (zzc2 == zzc.zzc) {
                        if (intValue > 32) {
                            throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 32 bytes for SHA256", new Object[]{Integer.valueOf(intValue)}));
                        }
                    } else if (zzc2 == zzc.zzd) {
                        if (intValue > 48) {
                            throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 48 bytes for SHA384", new Object[]{Integer.valueOf(intValue)}));
                        }
                    } else if (zzc2 != zzc.zze) {
                        throw new GeneralSecurityException("unknown hash type; must be SHA256, SHA384 or SHA512");
                    } else if (intValue > 64) {
                        throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 64 bytes for SHA512", new Object[]{Integer.valueOf(intValue)}));
                    }
                    return new zzqk(this.zza.intValue(), this.zzb.intValue(), this.zzd, this.zzc);
                }
                throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; must be at least 10 bytes", new Object[]{Integer.valueOf(intValue)}));
            } else {
                throw new InvalidAlgorithmParameterException(String.format("Invalid key size in bytes %d; must be at least 16 bytes", new Object[]{this.zza}));
            }
        }

        private zza() {
            this.zza = null;
            this.zzb = null;
            this.zzc = null;
            this.zzd = zzb.zzd;
        }
    }

    public final int zzc() {
        return this.zza;
    }

    private final int zzg() {
        int i;
        if (this.zzc == zzb.zzd) {
            return this.zzb;
        }
        if (this.zzc == zzb.zza) {
            i = this.zzb;
        } else if (this.zzc == zzb.zzb) {
            i = this.zzb;
        } else if (this.zzc == zzb.zzc) {
            i = this.zzb;
        } else {
            throw new IllegalStateException("Unknown variant");
        }
        return i + 5;
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqk$zzb */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zzb {
        public static final zzb zza = new zzb("TINK");
        public static final zzb zzb = new zzb("CRUNCHY");
        public static final zzb zzc = new zzb("LEGACY");
        public static final zzb zzd = new zzb("NO_PREFIX");
        private final String zze;

        public final String toString() {
            return this.zze;
        }

        private zzb(String str) {
            this.zze = str;
        }
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqk$zzc */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zzc {
        public static final zzc zza = new zzc("SHA1");
        public static final zzc zzb = new zzc("SHA224");
        public static final zzc zzc = new zzc("SHA256");
        public static final zzc zzd = new zzc("SHA384");
        public static final zzc zze = new zzc("SHA512");
        private final String zzf;

        public final String toString() {
            return this.zzf;
        }

        private zzc(String str) {
            this.zzf = str;
        }
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{zzqk.class, Integer.valueOf(this.zza), Integer.valueOf(this.zzb), this.zzc, this.zzd});
    }

    public static zza zzd() {
        return new zza();
    }

    public final zzc zze() {
        return this.zzd;
    }

    public final zzb zzf() {
        return this.zzc;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzc);
        String valueOf2 = String.valueOf(this.zzd);
        int i = this.zzb;
        return "HMAC Parameters (variant: " + valueOf + ", hashType: " + valueOf2 + ", " + i + "-byte tags, and " + this.zza + "-byte key)";
    }

    private zzqk(int i, int i2, zzb zzb2, zzc zzc2) {
        this.zza = i;
        this.zzb = i2;
        this.zzc = zzb2;
        this.zzd = zzc2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzqk)) {
            return false;
        }
        zzqk zzqk = (zzqk) obj;
        if (zzqk.zza == this.zza && zzqk.zzg() == zzg() && zzqk.zzc == this.zzc && zzqk.zzd == this.zzd) {
            return true;
        }
        return false;
    }

    public final boolean zza() {
        return this.zzc != zzb.zzd;
    }
}
