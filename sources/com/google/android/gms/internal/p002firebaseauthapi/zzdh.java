package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzdm;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdh  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzdh extends zzcu {
    private final zzdm zza;
    private final zzze zzb;
    private final zzzc zzc;
    @Nullable
    private final Integer zzd;

    public static zza zzb() {
        return new zza();
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdh$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static class zza {
        @Nullable
        private zzdm zza;
        @Nullable
        private zzze zzb;
        @Nullable
        private Integer zzc;

        public final zza zza(@Nullable Integer num) {
            this.zzc = num;
            return this;
        }

        public final zza zza(zzze zzze) {
            this.zzb = zzze;
            return this;
        }

        public final zza zza(zzdm zzdm) {
            this.zza = zzdm;
            return this;
        }

        public final zzdh zza() throws GeneralSecurityException {
            zzzc zzb2;
            zzdm zzdm = this.zza;
            if (zzdm == null || this.zzb == null) {
                throw new GeneralSecurityException("Cannot build without parameters and/or key material");
            } else if (zzdm.zzc() != this.zzb.zza()) {
                throw new GeneralSecurityException("Key size mismatch");
            } else if (this.zza.zza() && this.zzc == null) {
                throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
            } else if (this.zza.zza() || this.zzc == null) {
                if (this.zza.zzf() == zzdm.zza.zzc) {
                    zzb2 = zzog.zza;
                } else if (this.zza.zzf() == zzdm.zza.zzb) {
                    zzb2 = zzog.zza(this.zzc.intValue());
                } else if (this.zza.zzf() == zzdm.zza.zza) {
                    zzb2 = zzog.zzb(this.zzc.intValue());
                } else {
                    throw new IllegalStateException("Unknown AesEaxParameters.Variant: " + String.valueOf(this.zza.zzf()));
                }
                return new zzdh(this.zza, this.zzb, zzb2, this.zzc);
            } else {
                throw new GeneralSecurityException("Cannot create key with ID requirement with parameters without ID requirement");
            }
        }

        private zza() {
            this.zza = null;
            this.zzb = null;
            this.zzc = null;
        }
    }

    public final zzdm zzc() {
        return this.zza;
    }

    public final zzzc zzd() {
        return this.zzc;
    }

    public final zzze zze() {
        return this.zzb;
    }

    @Nullable
    public final Integer zza() {
        return this.zzd;
    }

    private zzdh(zzdm zzdm, zzze zzze, zzzc zzzc, @Nullable Integer num) {
        this.zza = zzdm;
        this.zzb = zzze;
        this.zzc = zzzc;
        this.zzd = num;
    }
}
