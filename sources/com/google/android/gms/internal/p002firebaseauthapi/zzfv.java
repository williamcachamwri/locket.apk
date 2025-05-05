package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzga;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfv  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzfv extends zzcu {
    private final zzga zza;
    private final zzze zzb;
    private final zzzc zzc;
    @Nullable
    private final Integer zzd;

    public static zzfv zza(zzga.zza zza2, zzze zzze, @Nullable Integer num) throws GeneralSecurityException {
        zzzc zzzc;
        if (zza2 != zzga.zza.zzc && num == null) {
            throw new GeneralSecurityException("For given Variant " + String.valueOf(zza2) + " the value of idRequirement must be non-null");
        } else if (zza2 == zzga.zza.zzc && num != null) {
            throw new GeneralSecurityException("For given Variant NO_PREFIX the value of idRequirement must be null");
        } else if (zzze.zza() == 32) {
            zzga zza3 = zzga.zza(zza2);
            if (zza3.zzb() == zzga.zza.zzc) {
                zzzc = zzog.zza;
            } else if (zza3.zzb() == zzga.zza.zzb) {
                zzzc = zzog.zza(num.intValue());
            } else if (zza3.zzb() == zzga.zza.zza) {
                zzzc = zzog.zzb(num.intValue());
            } else {
                throw new IllegalStateException("Unknown Variant: " + String.valueOf(zza3.zzb()));
            }
            return new zzfv(zza3, zzze, zzzc, num);
        } else {
            throw new GeneralSecurityException("XChaCha20Poly1305 key must be constructed with key of length 32 bytes, not " + zzze.zza());
        }
    }

    public final zzga zzb() {
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

    private zzfv(zzga zzga, zzze zzze, zzzc zzzc, @Nullable Integer num) {
        this.zza = zzga;
        this.zzb = zzze;
        this.zzc = zzzc;
        this.zzd = num;
    }
}
