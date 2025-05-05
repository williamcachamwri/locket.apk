package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzjp;
import java.security.GeneralSecurityException;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjv  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzjv extends zzkr {
    private final zzjp zza;
    @Nullable
    private final ECPoint zzb;
    @Nullable
    private final zzzc zzc;
    private final zzzc zzd;
    @Nullable
    private final Integer zze;

    public final zzjp zzb() {
        return this.zza;
    }

    public static zzjv zza(zzjp zzjp, zzzc zzzc, @Nullable Integer num) throws GeneralSecurityException {
        if (zzjp.zzd().equals(zzjp.zzc.zzd)) {
            zzb(zzjp.zzg(), num);
            if (zzzc.zza() == 32) {
                return new zzjv(zzjp, (ECPoint) null, zzzc, zza(zzjp.zzg(), num), num);
            }
            throw new GeneralSecurityException("Encoded public point byte length for X25519 curve must be 32");
        }
        throw new GeneralSecurityException("createForCurveX25519 may only be called with parameters for curve X25519");
    }

    public static zzjv zza(zzjp zzjp, ECPoint eCPoint, @Nullable Integer num) throws GeneralSecurityException {
        EllipticCurve ellipticCurve;
        if (!zzjp.zzd().equals(zzjp.zzc.zzd)) {
            zzb(zzjp.zzg(), num);
            zzjp.zzc zzd2 = zzjp.zzd();
            if (zzd2 == zzjp.zzc.zza) {
                ellipticCurve = zzmk.zza.getCurve();
            } else if (zzd2 == zzjp.zzc.zzb) {
                ellipticCurve = zzmk.zzb.getCurve();
            } else if (zzd2 == zzjp.zzc.zzc) {
                ellipticCurve = zzmk.zzc.getCurve();
            } else {
                throw new IllegalArgumentException("Unable to determine NIST curve type for " + String.valueOf(zzd2));
            }
            zzmk.zza(eCPoint, ellipticCurve);
            return new zzjv(zzjp, eCPoint, (zzzc) null, zza(zzjp.zzg(), num), num);
        }
        throw new GeneralSecurityException("createForNistCurve may only be called with parameters for NIST curve");
    }

    private static zzzc zza(zzjp.zzd zzd2, @Nullable Integer num) {
        if (zzd2 == zzjp.zzd.zzc) {
            return zzog.zza;
        }
        if (num == null) {
            throw new IllegalStateException("idRequirement must be non-null for EciesParameters.Variant: " + String.valueOf(zzd2));
        } else if (zzd2 == zzjp.zzd.zzb) {
            return zzog.zza(num.intValue());
        } else {
            if (zzd2 == zzjp.zzd.zza) {
                return zzog.zzb(num.intValue());
            }
            throw new IllegalStateException("Unknown EciesParameters.Variant: " + String.valueOf(zzd2));
        }
    }

    public final zzzc zzc() {
        return this.zzd;
    }

    @Nullable
    public final zzzc zzd() {
        return this.zzc;
    }

    @Nullable
    public final Integer zza() {
        return this.zze;
    }

    @Nullable
    public final ECPoint zze() {
        return this.zzb;
    }

    private zzjv(zzjp zzjp, @Nullable ECPoint eCPoint, @Nullable zzzc zzzc, zzzc zzzc2, @Nullable Integer num) {
        this.zza = zzjp;
        this.zzb = eCPoint;
        this.zzc = zzzc;
        this.zzd = zzzc2;
        this.zze = num;
    }

    private static void zzb(zzjp.zzd zzd2, @Nullable Integer num) throws GeneralSecurityException {
        if (!zzd2.equals(zzjp.zzd.zzc) && num == null) {
            throw new GeneralSecurityException("'idRequirement' must be non-null for " + String.valueOf(zzd2) + " variant.");
        } else if (zzd2.equals(zzjp.zzd.zzc) && num != null) {
            throw new GeneralSecurityException("'idRequirement' must be null for NO_PREFIX variant.");
        }
    }
}
