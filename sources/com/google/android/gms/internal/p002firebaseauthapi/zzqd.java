package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzqk;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqd  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzqd extends zzqo {
    private final zzqk zza;
    private final zzze zzb;
    private final zzzc zzc;
    @Nullable
    private final Integer zzd;

    public static zza zzb() {
        return new zza();
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqd$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static class zza {
        @Nullable
        private zzqk zza;
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

        public final zza zza(zzqk zzqk) {
            this.zza = zzqk;
            return this;
        }

        public final zzqd zza() throws GeneralSecurityException {
            zzzc zza2;
            zzqk zzqk = this.zza;
            if (zzqk == null || this.zzb == null) {
                throw new GeneralSecurityException("Cannot build without parameters and/or key material");
            } else if (zzqk.zzc() != this.zzb.zza()) {
                throw new GeneralSecurityException("Key size mismatch");
            } else if (this.zza.zza() && this.zzc == null) {
                throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
            } else if (this.zza.zza() || this.zzc == null) {
                if (this.zza.zzf() == zzqk.zzb.zzd) {
                    zza2 = zzog.zza;
                } else if (this.zza.zzf() == zzqk.zzb.zzc || this.zza.zzf() == zzqk.zzb.zzb) {
                    zza2 = zzog.zza(this.zzc.intValue());
                } else if (this.zza.zzf() == zzqk.zzb.zza) {
                    zza2 = zzog.zzb(this.zzc.intValue());
                } else {
                    throw new IllegalStateException("Unknown HmacParameters.Variant: " + String.valueOf(this.zza.zzf()));
                }
                return new zzqd(this.zza, this.zzb, zza2, this.zzc);
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

    public final zzqk zzc() {
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

    private zzqd(zzqk zzqk, zzze zzze, zzzc zzzc, @Nullable Integer num) {
        this.zza = zzqk;
        this.zzb = zzze;
        this.zzc = zzzc;
        this.zzd = num;
    }
}
