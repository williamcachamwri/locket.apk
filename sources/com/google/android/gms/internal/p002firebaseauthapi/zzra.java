package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzpx;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzra  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzra {
    private static final zzzc zza;
    private static final zzon<zzpx, zzpf> zzb = zzon.zza(new zzqz(), zzpx.class, zzpf.class);
    private static final zzoj<zzpf> zzc;
    private static final zzmw<zzpq, zzpc> zzd = zzmw.zza(new zzrb(), zzpq.class, zzpc.class);
    private static final zzms<zzpc> zze;

    /* access modifiers changed from: private */
    public static zzpq zzb(zzpc zzpc, @Nullable zzcn zzcn) throws GeneralSecurityException {
        if (zzpc.zzf().equals("type.googleapis.com/google.crypto.tink.AesCmacKey")) {
            try {
                zzrp zza2 = zzrp.zza(zzpc.zzd(), zzajk.zza());
                if (zza2.zza() == 0) {
                    return zzpq.zzb().zza(zzpx.zzd().zza(zza2.zze().zzb()).zzb(zza2.zzd().zza()).zza(zza(zzpc.zzb())).zza()).zza(zzze.zza(zza2.zze().zzd(), zzcn.zza(zzcn))).zza(zzpc.zze()).zza();
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzakf | IllegalArgumentException unused) {
                throw new GeneralSecurityException("Parsing AesCmacKey failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to AesCmacProtoSerialization.parseKey");
        }
    }

    private static zzpx.zzb zza(zzws zzws) throws GeneralSecurityException {
        int i = zzrd.zza[zzws.ordinal()];
        if (i == 1) {
            return zzpx.zzb.zza;
        }
        if (i == 2) {
            return zzpx.zzb.zzb;
        }
        if (i == 3) {
            return zzpx.zzb.zzc;
        }
        if (i == 4) {
            return zzpx.zzb.zzd;
        }
        throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zzws.zza());
    }

    /* access modifiers changed from: private */
    public static zzpx zzb(zzpf zzpf) throws GeneralSecurityException {
        if (zzpf.zza().zzf().equals("type.googleapis.com/google.crypto.tink.AesCmacKey")) {
            try {
                zzrs zza2 = zzrs.zza(zzpf.zza().zze(), zzajk.zza());
                return zzpx.zzd().zza(zza2.zza()).zzb(zza2.zzd().zza()).zza(zza(zzpf.zza().zzd())).zza();
            } catch (zzakf e) {
                throw new GeneralSecurityException("Parsing AesCmacParameters failed: ", e);
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to AesCmacProtoSerialization.parseParameters: " + zzpf.zza().zzf());
        }
    }

    private static zzrv zzb(zzpx zzpx) {
        return (zzrv) ((zzajy) zzrv.zzb().zza(zzpx.zzb()).zze());
    }

    private static zzws zza(zzpx.zzb zzb2) throws GeneralSecurityException {
        if (zzpx.zzb.zza.equals(zzb2)) {
            return zzws.TINK;
        }
        if (zzpx.zzb.zzb.equals(zzb2)) {
            return zzws.CRUNCHY;
        }
        if (zzpx.zzb.zzd.equals(zzb2)) {
            return zzws.RAW;
        }
        if (zzpx.zzb.zzc.equals(zzb2)) {
            return zzws.LEGACY;
        }
        throw new GeneralSecurityException("Unable to serialize variant: " + String.valueOf(zzb2));
    }

    static {
        zzzc zzb2 = zzpr.zzb("type.googleapis.com/google.crypto.tink.AesCmacKey");
        zza = zzb2;
        zzc = zzoj.zza(new zzrc(), zzb2, zzpf.class);
        zze = zzms.zza(new zzre(), zzb2, zzpc.class);
    }

    public static void zza() throws GeneralSecurityException {
        zzof zza2 = zzof.zza();
        zza2.zza(zzb);
        zza2.zza(zzc);
        zza2.zza(zzd);
        zza2.zza(zze);
    }
}
