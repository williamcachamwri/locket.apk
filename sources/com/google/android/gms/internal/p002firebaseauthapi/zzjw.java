package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzju;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.spec.ECParameterSpec;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjw  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzjw extends zzko {
    private final zzke zza;
    private final zzze zzb;

    public final /* synthetic */ zzbp zzb() {
        return (zzke) zzb();
    }

    public final zzju zzc() {
        return this.zza.zzb();
    }

    public static zzjw zza(zzke zzke, zzze zzze) throws GeneralSecurityException {
        ECParameterSpec eCParameterSpec;
        if (zzke == null) {
            throw new GeneralSecurityException("HPKE private key cannot be constructed without an HPKE public key");
        } else if (zzze != null) {
            zzju.zzf zze = zzke.zzb().zze();
            int zza2 = zzze.zza();
            String str = "Encoded private key byte length for " + String.valueOf(zze) + " must be %d, not " + zza2;
            if (zze == zzju.zzf.zza) {
                if (zza2 != 32) {
                    throw new GeneralSecurityException(String.format(str, new Object[]{32}));
                }
            } else if (zze == zzju.zzf.zzb) {
                if (zza2 != 48) {
                    throw new GeneralSecurityException(String.format(str, new Object[]{48}));
                }
            } else if (zze == zzju.zzf.zzc) {
                if (zza2 != 66) {
                    throw new GeneralSecurityException(String.format(str, new Object[]{66}));
                }
            } else if (zze != zzju.zzf.zzd) {
                throw new GeneralSecurityException("Unable to validate private key length for " + String.valueOf(zze));
            } else if (zza2 != 32) {
                throw new GeneralSecurityException(String.format(str, new Object[]{32}));
            }
            zzju.zzf zze2 = zzke.zzb().zze();
            byte[] zzb2 = zzke.zzd().zzb();
            byte[] zza3 = zzze.zza(zzbq.zza());
            if (zze2 == zzju.zzf.zza || zze2 == zzju.zzf.zzb || zze2 == zzju.zzf.zzc) {
                if (zze2 == zzju.zzf.zza) {
                    eCParameterSpec = zzmk.zza;
                } else if (zze2 == zzju.zzf.zzb) {
                    eCParameterSpec = zzmk.zzb;
                } else if (zze2 == zzju.zzf.zzc) {
                    eCParameterSpec = zzmk.zzc;
                } else {
                    throw new IllegalArgumentException("Unable to determine NIST curve params for " + String.valueOf(zze2));
                }
                BigInteger order = eCParameterSpec.getOrder();
                BigInteger zza4 = zzmj.zza(zza3);
                if (zza4.signum() <= 0 || zza4.compareTo(order) >= 0) {
                    throw new GeneralSecurityException("Invalid private key.");
                } else if (!zzmk.zza(zza4, eCParameterSpec).equals(zzyb.zza(eCParameterSpec.getCurve(), zzyd.UNCOMPRESSED, zzb2))) {
                    throw new GeneralSecurityException("Invalid private key for public key.");
                }
            } else if (zze2 != zzju.zzf.zzd) {
                throw new IllegalArgumentException("Unable to validate key pair for " + String.valueOf(zze2));
            } else if (!Arrays.equals(zzza.zza(zza3), zzb2)) {
                throw new GeneralSecurityException("Invalid private key for public key.");
            }
            return new zzjw(zzke, zzze);
        } else {
            throw new GeneralSecurityException("HPKE private key cannot be constructed without secret");
        }
    }

    public final /* synthetic */ zzkr zzd() {
        return this.zza;
    }

    public final zzze zze() {
        return this.zzb;
    }

    private zzjw(zzke zzke, zzze zzze) {
        this.zza = zzke;
        this.zzb = zzze;
    }
}
