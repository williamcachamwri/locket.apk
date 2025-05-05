package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzju;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzli  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzli implements zzbn {
    public static zzbn zza(zzke zzke) throws GeneralSecurityException {
        zzju zzb = zzke.zzb();
        return new zzli(zzke.zzd(), zza(zzb.zze()), zza(zzb.zzd()), zza(zzb.zzb()), zzke.zzc());
    }

    static zzlh zza(zzju.zzb zzb) throws GeneralSecurityException {
        if (zzb.equals(zzju.zzb.zza)) {
            return new zzkq(16);
        }
        if (zzb.equals(zzju.zzb.zzb)) {
            return new zzkq(32);
        }
        if (zzb.equals(zzju.zzb.zzc)) {
            return new zzkt();
        }
        throw new GeneralSecurityException("Unrecognized HPKE AEAD identifier");
    }

    static zzll zza(zzju.zzc zzc) throws GeneralSecurityException {
        if (zzc.equals(zzju.zzc.zza)) {
            return new zzle("HmacSha256");
        }
        if (zzc.equals(zzju.zzc.zzb)) {
            return new zzle("HmacSha384");
        }
        if (zzc.equals(zzju.zzc.zzc)) {
            return new zzle("HmacSha512");
        }
        throw new GeneralSecurityException("Unrecognized HPKE KDF identifier");
    }

    static zzlk zza(zzju.zzf zzf) throws GeneralSecurityException {
        if (zzf.equals(zzju.zzf.zzd)) {
            return new zzly(new zzle("HmacSha256"));
        }
        if (zzf.equals(zzju.zzf.zza)) {
            return zzlx.zza(zzya.NIST_P256);
        }
        if (zzf.equals(zzju.zzf.zzb)) {
            return zzlx.zza(zzya.NIST_P384);
        }
        if (zzf.equals(zzju.zzf.zzc)) {
            return zzlx.zza(zzya.NIST_P521);
        }
        throw new GeneralSecurityException("Unrecognized HPKE KEM identifier");
    }

    private zzli(zzzc zzzc, zzlk zzlk, zzll zzll, zzlh zzlh, zzzc zzzc2) {
        zzzc.zzb();
        zzzc2.zzb();
    }
}
