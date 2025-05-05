package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzju;
import java.security.GeneralSecurityException;
import java.security.spec.EllipticCurve;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzke  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzke extends zzkr {
    private final zzju zza;
    private final zzzc zzb;
    private final zzzc zzc;
    @Nullable
    private final Integer zzd;

    public final zzju zzb() {
        return this.zza;
    }

    public static zzke zza(zzju zzju, zzzc zzzc, @Nullable Integer num) throws GeneralSecurityException {
        zzzc zzzc2;
        EllipticCurve ellipticCurve;
        zzju.zze zzf = zzju.zzf();
        if (!zzf.equals(zzju.zze.zzc) && num == null) {
            throw new GeneralSecurityException("'idRequirement' must be non-null for " + String.valueOf(zzf) + " variant.");
        } else if (!zzf.equals(zzju.zze.zzc) || num == null) {
            zzju.zzf zze = zzju.zze();
            int zza2 = zzzc.zza();
            String str = "Encoded public key byte length for " + String.valueOf(zze) + " must be %d, not " + zza2;
            if (zze == zzju.zzf.zza) {
                if (zza2 != 65) {
                    throw new GeneralSecurityException(String.format(str, new Object[]{65}));
                }
            } else if (zze == zzju.zzf.zzb) {
                if (zza2 != 97) {
                    throw new GeneralSecurityException(String.format(str, new Object[]{97}));
                }
            } else if (zze == zzju.zzf.zzc) {
                if (zza2 != 133) {
                    throw new GeneralSecurityException(String.format(str, new Object[]{133}));
                }
            } else if (zze != zzju.zzf.zzd) {
                throw new GeneralSecurityException("Unable to validate public key length for " + String.valueOf(zze));
            } else if (zza2 != 32) {
                throw new GeneralSecurityException(String.format(str, new Object[]{32}));
            }
            if (zze == zzju.zzf.zza || zze == zzju.zzf.zzb || zze == zzju.zzf.zzc) {
                if (zze == zzju.zzf.zza) {
                    ellipticCurve = zzmk.zza.getCurve();
                } else if (zze == zzju.zzf.zzb) {
                    ellipticCurve = zzmk.zzb.getCurve();
                } else if (zze == zzju.zzf.zzc) {
                    ellipticCurve = zzmk.zzc.getCurve();
                } else {
                    throw new IllegalArgumentException("Unable to determine NIST curve type for " + String.valueOf(zze));
                }
                zzmk.zza(zzyb.zza(ellipticCurve, zzyd.UNCOMPRESSED, zzzc.zzb()), ellipticCurve);
            }
            zzju.zze zzf2 = zzju.zzf();
            if (zzf2 == zzju.zze.zzc) {
                zzzc2 = zzog.zza;
            } else if (num == null) {
                throw new IllegalStateException("idRequirement must be non-null for HpkeParameters.Variant " + String.valueOf(zzf2));
            } else if (zzf2 == zzju.zze.zzb) {
                zzzc2 = zzog.zza(num.intValue());
            } else if (zzf2 == zzju.zze.zza) {
                zzzc2 = zzog.zzb(num.intValue());
            } else {
                throw new IllegalStateException("Unknown HpkeParameters.Variant: " + String.valueOf(zzf2));
            }
            return new zzke(zzju, zzzc, zzzc2, num);
        } else {
            throw new GeneralSecurityException("'idRequirement' must be null for NO_PREFIX variant.");
        }
    }

    public final zzzc zzc() {
        return this.zzc;
    }

    public final zzzc zzd() {
        return this.zzb;
    }

    @Nullable
    public final Integer zza() {
        return this.zzd;
    }

    private zzke(zzju zzju, zzzc zzzc, zzzc zzzc2, @Nullable Integer num) {
        this.zza = zzju;
        this.zzb = zzzc;
        this.zzc = zzzc2;
        this.zzd = num;
    }
}
