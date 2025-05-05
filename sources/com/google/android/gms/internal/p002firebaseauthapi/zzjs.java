package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzjp;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.util.Arrays;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjs  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzjs extends zzko {
    private final zzjv zza;
    @Nullable
    private final zzzf zzb;
    @Nullable
    private final zzze zzc;

    public final /* synthetic */ zzbp zzb() {
        return (zzjv) zzb();
    }

    public final zzjp zzc() {
        return this.zza.zzb();
    }

    public static zzjs zza(zzjv zzjv, zzze zzze) throws GeneralSecurityException {
        if (zzjv == null) {
            throw new GeneralSecurityException("ECIES private key cannot be constructed without an ECIES public key");
        } else if (zzjv.zzd() == null) {
            throw new GeneralSecurityException("ECIES private key for X25519 curve cannot be constructed with NIST-curve public key");
        } else if (zzze != null) {
            byte[] zza2 = zzze.zza(zzbq.zza());
            byte[] zzb2 = zzjv.zzd().zzb();
            if (zza2.length != 32) {
                throw new GeneralSecurityException("Private key bytes length for X25519 curve must be 32");
            } else if (Arrays.equals(zzza.zza(zza2), zzb2)) {
                return new zzjs(zzjv, (zzzf) null, zzze);
            } else {
                throw new GeneralSecurityException("Invalid private key for public key.");
            }
        } else {
            throw new GeneralSecurityException("ECIES private key cannot be constructed without secret");
        }
    }

    public static zzjs zza(zzjv zzjv, zzzf zzzf) throws GeneralSecurityException {
        if (zzjv == null) {
            throw new GeneralSecurityException("ECIES private key cannot be constructed without an ECIES public key");
        } else if (zzjv.zze() == null) {
            throw new GeneralSecurityException("ECIES private key for NIST curve cannot be constructed with X25519-curve public key");
        } else if (zzzf != null) {
            BigInteger zza2 = zzzf.zza(zzbq.zza());
            ECPoint zze = zzjv.zze();
            zzjp.zzc zzd = zzjv.zzb().zzd();
            BigInteger order = zza(zzd).getOrder();
            if (zza2.signum() <= 0 || zza2.compareTo(order) >= 0) {
                throw new GeneralSecurityException("Invalid private value");
            } else if (zzmk.zza(zza2, zza(zzd)).equals(zze)) {
                return new zzjs(zzjv, zzzf, (zzze) null);
            } else {
                throw new GeneralSecurityException("Invalid private value");
            }
        } else {
            throw new GeneralSecurityException("ECIES private key cannot be constructed without secret");
        }
    }

    public final /* synthetic */ zzkr zzd() {
        return this.zza;
    }

    @Nullable
    public final zzzf zze() {
        return this.zzb;
    }

    @Nullable
    public final zzze zzf() {
        return this.zzc;
    }

    private static ECParameterSpec zza(zzjp.zzc zzc2) {
        if (zzc2 == zzjp.zzc.zza) {
            return zzmk.zza;
        }
        if (zzc2 == zzjp.zzc.zzb) {
            return zzmk.zzb;
        }
        if (zzc2 == zzjp.zzc.zzc) {
            return zzmk.zzc;
        }
        throw new IllegalArgumentException("Unable to determine NIST curve type for " + String.valueOf(zzc2));
    }

    private zzjs(zzjv zzjv, @Nullable zzzf zzzf, @Nullable zzze zzze) {
        this.zza = zzjv;
        this.zzb = zzzf;
        this.zzc = zzze;
    }
}
