package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzdv;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdo  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzdo extends zzcu {
    private final zzdv zza;
    private final zzze zzb;
    private final zzzc zzc;
    @Nullable
    private final Integer zzd;

    public static zza zzb() {
        return new zza();
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdo$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static class zza {
        @Nullable
        private zzdv zza;
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

        public final zza zza(zzdv zzdv) {
            this.zza = zzdv;
            return this;
        }

        public final zzdo zza() throws GeneralSecurityException {
            zzzc zzb2;
            zzdv zzdv = this.zza;
            if (zzdv == null || this.zzb == null) {
                throw new GeneralSecurityException("Cannot build without parameters and/or key material");
            } else if (zzdv.zzc() != this.zzb.zza()) {
                throw new GeneralSecurityException("Key size mismatch");
            } else if (this.zza.zza() && this.zzc == null) {
                throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
            } else if (this.zza.zza() || this.zzc == null) {
                if (this.zza.zzf() == zzdv.zzb.zzc) {
                    zzb2 = zzog.zza;
                } else if (this.zza.zzf() == zzdv.zzb.zzb) {
                    zzb2 = zzog.zza(this.zzc.intValue());
                } else if (this.zza.zzf() == zzdv.zzb.zza) {
                    zzb2 = zzog.zzb(this.zzc.intValue());
                } else {
                    throw new IllegalStateException("Unknown AesGcmParameters.Variant: " + String.valueOf(this.zza.zzf()));
                }
                return new zzdo(this.zza, this.zzb, zzb2, this.zzc);
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

    public final zzdv zzc() {
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

    private zzdo(zzdv zzdv, zzze zzze, zzzc zzzc, @Nullable Integer num) {
        this.zza = zzdv;
        this.zzb = zzze;
        this.zzc = zzzc;
        this.zzd = num;
    }
}
