package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Objects;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfa  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzfa extends zzcw {
    private final zzb zza;
    private final String zzb;
    private final zzc zzc;
    private final zzcw zzd;

    public final int hashCode() {
        return Objects.hash(new Object[]{zzfa.class, this.zzb, this.zzc, this.zzd, this.zza});
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfa$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static class zza {
        @Nullable
        private zzb zza;
        @Nullable
        private String zzb;
        @Nullable
        private zzc zzc;
        @Nullable
        private zzcw zzd;

        public final zza zza(zzcw zzcw) {
            this.zzd = zzcw;
            return this;
        }

        public final zza zza(zzc zzc2) {
            this.zzc = zzc2;
            return this;
        }

        public final zza zza(String str) {
            this.zzb = str;
            return this;
        }

        public final zza zza(zzb zzb2) {
            this.zza = zzb2;
            return this;
        }

        public final zzfa zza() throws GeneralSecurityException {
            if (this.zza == null) {
                this.zza = zzb.zzb;
            }
            if (this.zzb == null) {
                throw new GeneralSecurityException("kekUri must be set");
            } else if (this.zzc != null) {
                zzcw zzcw = this.zzd;
                if (zzcw == null) {
                    throw new GeneralSecurityException("dekParametersForNewKeys must be set");
                } else if (!zzcw.zza()) {
                    zzc zzc2 = this.zzc;
                    zzcw zzcw2 = this.zzd;
                    boolean z = true;
                    if ((!zzc2.equals(zzc.zza) || !(zzcw2 instanceof zzdv)) && ((!zzc2.equals(zzc.zzc) || !(zzcw2 instanceof zzei)) && ((!zzc2.equals(zzc.zzb) || !(zzcw2 instanceof zzga)) && ((!zzc2.equals(zzc.zzd) || !(zzcw2 instanceof zzdg)) && ((!zzc2.equals(zzc.zze) || !(zzcw2 instanceof zzdm)) && (!zzc2.equals(zzc.zzf) || !(zzcw2 instanceof zzec))))))) {
                        z = false;
                    }
                    if (z) {
                        return new zzfa(this.zza, this.zzb, this.zzc, this.zzd);
                    }
                    String zzc3 = this.zzc.toString();
                    throw new GeneralSecurityException("Cannot use parsing strategy " + zzc3 + " when new keys are picked according to " + String.valueOf(this.zzd) + ".");
                } else {
                    throw new GeneralSecurityException("dekParametersForNewKeys must not have ID Requirements");
                }
            } else {
                throw new GeneralSecurityException("dekParsingStrategy must be set");
            }
        }

        private zza() {
        }
    }

    public final zzcw zzb() {
        return this.zzd;
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfa$zzb */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zzb {
        public static final zzb zza = new zzb("TINK");
        public static final zzb zzb = new zzb("NO_PREFIX");
        private final String zzc;

        public final String toString() {
            return this.zzc;
        }

        private zzb(String str) {
            this.zzc = str;
        }
    }

    public final zzb zzc() {
        return this.zza;
    }

    public final String zzd() {
        return this.zzb;
    }

    public final String toString() {
        String str = this.zzb;
        String valueOf = String.valueOf(this.zzc);
        String valueOf2 = String.valueOf(this.zzd);
        return "LegacyKmsEnvelopeAead Parameters (kekUri: " + str + ", dekParsingStrategy: " + valueOf + ", dekParametersForNewKeys: " + valueOf2 + ", variant: " + String.valueOf(this.zza) + ")";
    }

    private zzfa(zzb zzb2, String str, zzc zzc2, zzcw zzcw) {
        this.zza = zzb2;
        this.zzb = str;
        this.zzc = zzc2;
        this.zzd = zzcw;
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfa$zzc */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zzc {
        public static final zzc zza = new zzc("ASSUME_AES_GCM");
        public static final zzc zzb = new zzc("ASSUME_XCHACHA20POLY1305");
        public static final zzc zzc = new zzc("ASSUME_CHACHA20POLY1305");
        public static final zzc zzd = new zzc("ASSUME_AES_CTR_HMAC");
        public static final zzc zze = new zzc("ASSUME_AES_EAX");
        public static final zzc zzf = new zzc("ASSUME_AES_GCM_SIV");
        private final String zzg;

        public final String toString() {
            return this.zzg;
        }

        private zzc(String str) {
            this.zzg = str;
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzfa)) {
            return false;
        }
        zzfa zzfa = (zzfa) obj;
        if (!zzfa.zzc.equals(this.zzc) || !zzfa.zzd.equals(this.zzd) || !zzfa.zzb.equals(this.zzb) || !zzfa.zza.equals(this.zza)) {
            return false;
        }
        return true;
    }

    public final boolean zza() {
        return this.zza != zzb.zzb;
    }
}
