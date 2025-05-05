package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzft;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfp  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzfp extends zzcu {
    private final zzft zza;
    private final zzze zzb;
    private final zzzc zzc;
    @Nullable
    private final Integer zzd;

    public static zzfp zza(zzft zzft, zzze zzze, @Nullable Integer num) throws GeneralSecurityException {
        zzzc zzzc;
        if (zzft.zzc() != zzft.zza.zzb && num == null) {
            throw new GeneralSecurityException("For given Variant " + String.valueOf(zzft.zzc()) + " the value of idRequirement must be non-null");
        } else if (zzft.zzc() == zzft.zza.zzb && num != null) {
            throw new GeneralSecurityException("For given Variant NO_PREFIX the value of idRequirement must be null");
        } else if (zzze.zza() == 32) {
            if (zzft.zzc() == zzft.zza.zzb) {
                zzzc = zzog.zza;
            } else if (zzft.zzc() == zzft.zza.zza) {
                zzzc = zzog.zzb(num.intValue());
            } else {
                throw new IllegalStateException("Unknown Variant: " + String.valueOf(zzft.zzc()));
            }
            return new zzfp(zzft, zzze, zzzc, num);
        } else {
            throw new GeneralSecurityException("XAesGcmKey key must be constructed with key of length 32 bytes, not " + zzze.zza());
        }
    }

    public final zzft zzb() {
        return this.zza;
    }

    public final zzzc zzc() {
        return this.zzc;
    }

    public final zzze zzd() {
        return this.zzb;
    }

    @Nullable
    public final Integer zza() {
        return this.zzd;
    }

    private zzfp(zzft zzft, zzze zzze, zzzc zzzc, @Nullable Integer num) {
        this.zza = zzft;
        this.zzb = zzze;
        this.zzc = zzzc;
        this.zzd = num;
    }
}
