package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzei;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzee  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzee extends zzcu {
    private final zzei zza;
    private final zzze zzb;
    private final zzzc zzc;
    @Nullable
    private final Integer zzd;

    public static zzee zza(zzei.zza zza2, zzze zzze, @Nullable Integer num) throws GeneralSecurityException {
        zzzc zzzc;
        if (zza2 != zzei.zza.zzc && num == null) {
            throw new GeneralSecurityException("For given Variant " + String.valueOf(zza2) + " the value of idRequirement must be non-null");
        } else if (zza2 == zzei.zza.zzc && num != null) {
            throw new GeneralSecurityException("For given Variant NO_PREFIX the value of idRequirement must be null");
        } else if (zzze.zza() == 32) {
            zzei zza3 = zzei.zza(zza2);
            if (zza3.zzb() == zzei.zza.zzc) {
                zzzc = zzog.zza;
            } else if (zza3.zzb() == zzei.zza.zzb) {
                zzzc = zzog.zza(num.intValue());
            } else if (zza3.zzb() == zzei.zza.zza) {
                zzzc = zzog.zzb(num.intValue());
            } else {
                throw new IllegalStateException("Unknown Variant: " + String.valueOf(zza3.zzb()));
            }
            return new zzee(zza3, zzze, zzzc, num);
        } else {
            throw new GeneralSecurityException("ChaCha20Poly1305 key must be constructed with key of length 32 bytes, not " + zzze.zza());
        }
    }

    public final zzei zzb() {
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

    private zzee(zzei zzei, zzze zzze, zzzc zzzc, @Nullable Integer num) {
        this.zza = zzei;
        this.zzb = zzze;
        this.zzc = zzzc;
        this.zzd = num;
    }
}
