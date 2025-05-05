package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Objects;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzju  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzju extends zzkp {
    private final zzf zza;
    private final zzc zzb;
    private final zzb zzc;
    private final zze zzd;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzju$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    private static class zza {
        private final String zza;
        private final int zzb;

        public String toString() {
            return String.format("%s(0x%04x)", new Object[]{this.zza, Integer.valueOf(this.zzb)});
        }

        private zza(String str, int i) {
            this.zza = str;
            this.zzb = i;
        }
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{zzju.class, this.zza, this.zzb, this.zzc, this.zzd});
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzju$zzd */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zzd {
        private zzf zza;
        private zzc zzb;
        private zzb zzc;
        private zze zzd;

        public final zzd zza(zzb zzb2) {
            this.zzc = zzb2;
            return this;
        }

        public final zzd zza(zzc zzc2) {
            this.zzb = zzc2;
            return this;
        }

        public final zzd zza(zzf zzf) {
            this.zza = zzf;
            return this;
        }

        public final zzd zza(zze zze) {
            this.zzd = zze;
            return this;
        }

        public final zzju zza() throws GeneralSecurityException {
            if (this.zza == null) {
                throw new GeneralSecurityException("HPKE KEM parameter is not set");
            } else if (this.zzb == null) {
                throw new GeneralSecurityException("HPKE KDF parameter is not set");
            } else if (this.zzc == null) {
                throw new GeneralSecurityException("HPKE AEAD parameter is not set");
            } else if (this.zzd != null) {
                return new zzju(this.zza, this.zzb, this.zzc, this.zzd);
            } else {
                throw new GeneralSecurityException("HPKE variant is not set");
            }
        }

        private zzd() {
            this.zza = null;
            this.zzb = null;
            this.zzc = null;
            this.zzd = zze.zzc;
        }
    }

    public final zzb zzb() {
        return this.zzc;
    }

    public static zzd zzc() {
        return new zzd();
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzju$zzb */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zzb extends zza {
        public static final zzb zza = new zzb("AES_128_GCM", 1);
        public static final zzb zzb = new zzb("AES_256_GCM", 2);
        public static final zzb zzc = new zzb("CHACHA20_POLY1305", 3);

        public final /* bridge */ /* synthetic */ String toString() {
            return super.toString();
        }

        private zzb(String str, int i) {
            super(str, i);
        }
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzju$zzc */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zzc extends zza {
        public static final zzc zza = new zzc("HKDF_SHA256", 1);
        public static final zzc zzb = new zzc("HKDF_SHA384", 2);
        public static final zzc zzc = new zzc("HKDF_SHA512", 3);

        public final /* bridge */ /* synthetic */ String toString() {
            return super.toString();
        }

        private zzc(String str, int i) {
            super(str, i);
        }
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzju$zze */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zze {
        public static final zze zza = new zze("TINK");
        public static final zze zzb = new zze("CRUNCHY");
        public static final zze zzc = new zze("NO_PREFIX");
        private final String zzd;

        public final String toString() {
            return this.zzd;
        }

        private zze(String str) {
            this.zzd = str;
        }
    }

    public final zzc zzd() {
        return this.zzb;
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzju$zzf */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zzf extends zza {
        public static final zzf zza = new zzf("DHKEM_P256_HKDF_SHA256", 16);
        public static final zzf zzb = new zzf("DHKEM_P384_HKDF_SHA384", 17);
        public static final zzf zzc = new zzf("DHKEM_P521_HKDF_SHA512", 18);
        public static final zzf zzd = new zzf("DHKEM_X25519_HKDF_SHA256", 32);

        public final /* bridge */ /* synthetic */ String toString() {
            return super.toString();
        }

        private zzf(String str, int i) {
            super(str, i);
        }
    }

    public final zzf zze() {
        return this.zza;
    }

    public final zze zzf() {
        return this.zzd;
    }

    private zzju(zzf zzf2, zzc zzc2, zzb zzb2, zze zze2) {
        this.zza = zzf2;
        this.zzb = zzc2;
        this.zzc = zzb2;
        this.zzd = zze2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzju)) {
            return false;
        }
        zzju zzju = (zzju) obj;
        if (this.zza == zzju.zza && this.zzb == zzju.zzb && this.zzc == zzju.zzc && this.zzd == zzju.zzd) {
            return true;
        }
        return false;
    }

    public final boolean zza() {
        return this.zzd != zze.zzc;
    }
}
