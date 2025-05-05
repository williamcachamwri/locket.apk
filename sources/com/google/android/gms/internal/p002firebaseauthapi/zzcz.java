package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzdg;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcz  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzcz extends zzcu {
    private final zzdg zza;
    private final zzze zzb;
    private final zzze zzc;
    private final zzzc zzd;
    @Nullable
    private final Integer zze;

    public static zza zzb() {
        return new zza();
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcz$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static class zza {
        @Nullable
        private zzdg zza;
        @Nullable
        private zzze zzb;
        @Nullable
        private zzze zzc;
        @Nullable
        private Integer zzd;

        public final zza zza(zzze zzze) {
            this.zzb = zzze;
            return this;
        }

        public final zza zzb(zzze zzze) {
            this.zzc = zzze;
            return this;
        }

        public final zza zza(@Nullable Integer num) {
            this.zzd = num;
            return this;
        }

        public final zza zza(zzdg zzdg) {
            this.zza = zzdg;
            return this;
        }

        public final zzcz zza() throws GeneralSecurityException {
            zzzc zzb2;
            zzdg zzdg = this.zza;
            if (zzdg == null) {
                throw new GeneralSecurityException("Cannot build without parameters");
            } else if (this.zzb == null || this.zzc == null) {
                throw new GeneralSecurityException("Cannot build without key material");
            } else if (zzdg.zzb() != this.zzb.zza()) {
                throw new GeneralSecurityException("AES key size mismatch");
            } else if (this.zza.zzc() != this.zzc.zza()) {
                throw new GeneralSecurityException("HMAC key size mismatch");
            } else if (this.zza.zza() && this.zzd == null) {
                throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
            } else if (this.zza.zza() || this.zzd == null) {
                if (this.zza.zzh() == zzdg.zzb.zzc) {
                    zzb2 = zzog.zza;
                } else if (this.zza.zzh() == zzdg.zzb.zzb) {
                    zzb2 = zzog.zza(this.zzd.intValue());
                } else if (this.zza.zzh() == zzdg.zzb.zza) {
                    zzb2 = zzog.zzb(this.zzd.intValue());
                } else {
                    throw new IllegalStateException("Unknown AesCtrHmacAeadParameters.Variant: " + String.valueOf(this.zza.zzh()));
                }
                return new zzcz(this.zza, this.zzb, this.zzc, zzb2, this.zzd);
            } else {
                throw new GeneralSecurityException("Cannot create key with ID requirement with parameters without ID requirement");
            }
        }

        private zza() {
            this.zza = null;
            this.zzb = null;
            this.zzc = null;
            this.zzd = null;
        }
    }

    public final zzdg zzc() {
        return this.zza;
    }

    public final zzzc zzd() {
        return this.zzd;
    }

    public final zzze zze() {
        return this.zzb;
    }

    public final zzze zzf() {
        return this.zzc;
    }

    @Nullable
    public final Integer zza() {
        return this.zze;
    }

    private zzcz(zzdg zzdg, zzze zzze, zzze zzze2, zzzc zzzc, @Nullable Integer num) {
        this.zza = zzdg;
        this.zzb = zzze;
        this.zzc = zzze2;
        this.zzd = zzzc;
        this.zze = num;
    }
}
