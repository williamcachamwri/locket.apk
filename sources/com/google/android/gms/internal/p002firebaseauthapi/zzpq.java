package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzpx;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpq  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzpq extends zzqo {
    private final zzpx zza;
    private final zzze zzb;
    private final zzzc zzc;
    @Nullable
    private final Integer zzd;

    public static zza zzb() {
        return new zza();
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpq$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static class zza {
        @Nullable
        private zzpx zza;
        @Nullable
        private zzze zzb;
        @Nullable
        private Integer zzc;

        public final zza zza(zzze zzze) throws GeneralSecurityException {
            this.zzb = zzze;
            return this;
        }

        public final zza zza(@Nullable Integer num) {
            this.zzc = num;
            return this;
        }

        public final zza zza(zzpx zzpx) {
            this.zza = zzpx;
            return this;
        }

        public final zzpq zza() throws GeneralSecurityException {
            zzzc zza2;
            zzpx zzpx = this.zza;
            if (zzpx == null || this.zzb == null) {
                throw new GeneralSecurityException("Cannot build without parameters and/or key material");
            } else if (zzpx.zzc() != this.zzb.zza()) {
                throw new GeneralSecurityException("Key size mismatch");
            } else if (this.zza.zza() && this.zzc == null) {
                throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
            } else if (this.zza.zza() || this.zzc == null) {
                if (this.zza.zze() == zzpx.zzb.zzd) {
                    zza2 = zzog.zza;
                } else if (this.zza.zze() == zzpx.zzb.zzc || this.zza.zze() == zzpx.zzb.zzb) {
                    zza2 = zzog.zza(this.zzc.intValue());
                } else if (this.zza.zze() == zzpx.zzb.zza) {
                    zza2 = zzog.zzb(this.zzc.intValue());
                } else {
                    throw new IllegalStateException("Unknown AesCmacParametersParameters.Variant: " + String.valueOf(this.zza.zze()));
                }
                return new zzpq(this.zza, this.zzb, zza2, this.zzc);
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

    public final zzpx zzc() {
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

    private zzpq(zzpx zzpx, zzze zzze, zzzc zzzc, @Nullable Integer num) {
        this.zza = zzpx;
        this.zzb = zzze;
        this.zzc = zzzc;
        this.zzd = num;
    }
}
