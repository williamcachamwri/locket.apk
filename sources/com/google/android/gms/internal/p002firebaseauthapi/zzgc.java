package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzdg;
import com.google.android.gms.internal.p002firebaseauthapi.zzva;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgc  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzgc {
    private static final zzzc zza;
    private static final zzon<zzdg, zzpf> zzb = zzon.zza(new zzgb(), zzdg.class, zzpf.class);
    private static final zzoj<zzpf> zzc;
    private static final zzmw<zzcz, zzpc> zzd = zzmw.zza(new zzgd(), zzcz.class, zzpc.class);
    private static final zzms<zzpc> zze;

    /* access modifiers changed from: private */
    public static zzcz zzb(zzpc zzpc, @Nullable zzcn zzcn) throws GeneralSecurityException {
        if (zzpc.zzf().equals("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey")) {
            try {
                zzry zza2 = zzry.zza(zzpc.zzd(), zzajk.zza());
                if (zza2.zza() != 0) {
                    throw new GeneralSecurityException("Only version 0 keys are accepted");
                } else if (zza2.zzd().zza() != 0) {
                    throw new GeneralSecurityException("Only version 0 keys inner AES CTR keys are accepted");
                } else if (zza2.zze().zza() == 0) {
                    return zzcz.zzb().zza(zzdg.zzf().zza(zza2.zzd().zzf().zzb()).zzb(zza2.zze().zzf().zzb()).zzc(zza2.zzd().zze().zza()).zzd(zza2.zze().zze().zza()).zza(zza(zza2.zze().zze().zzb())).zza(zza(zzpc.zzb())).zza()).zza(zzze.zza(zza2.zzd().zzf().zzd(), zzcn.zza(zzcn))).zzb(zzze.zza(zza2.zze().zzf().zzd(), zzcn.zza(zzcn))).zza(zzpc.zze()).zza();
                } else {
                    throw new GeneralSecurityException("Only version 0 keys inner HMAC keys are accepted");
                }
            } catch (zzakf unused) {
                throw new GeneralSecurityException("Parsing AesCtrHmacAeadKey failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to AesCtrHmacAeadProtoSerialization.parseKey");
        }
    }

    private static zzdg.zzc zza(zzur zzur) throws GeneralSecurityException {
        int i = zzgf.zzb[zzur.ordinal()];
        if (i == 1) {
            return zzdg.zzc.zza;
        }
        if (i == 2) {
            return zzdg.zzc.zzb;
        }
        if (i == 3) {
            return zzdg.zzc.zzc;
        }
        if (i == 4) {
            return zzdg.zzc.zzd;
        }
        if (i == 5) {
            return zzdg.zzc.zze;
        }
        throw new GeneralSecurityException("Unable to parse HashType: " + zzur.zza());
    }

    private static zzdg.zzb zza(zzws zzws) throws GeneralSecurityException {
        int i = zzgf.zza[zzws.ordinal()];
        if (i == 1) {
            return zzdg.zzb.zza;
        }
        if (i == 2 || i == 3) {
            return zzdg.zzb.zzb;
        }
        if (i == 4) {
            return zzdg.zzb.zzc;
        }
        throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zzws.zza());
    }

    /* access modifiers changed from: private */
    public static zzdg zzb(zzpf zzpf) throws GeneralSecurityException {
        if (zzpf.zza().zzf().equals("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey")) {
            try {
                zzsb zza2 = zzsb.zza(zzpf.zza().zze(), zzajk.zza());
                if (zza2.zzd().zzb() == 0) {
                    return zzdg.zzf().zza(zza2.zzc().zza()).zzb(zza2.zzd().zza()).zzc(zza2.zzc().zze().zza()).zzd(zza2.zzd().zzf().zza()).zza(zza(zza2.zzd().zzf().zzb())).zza(zza(zzpf.zza().zzd())).zza();
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzakf e) {
                throw new GeneralSecurityException("Parsing AesCtrHmacAeadParameters failed: ", e);
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to AesCtrHmacAeadProtoSerialization.parseParameters: " + zzpf.zza().zzf());
        }
    }

    private static zzva zzb(zzdg zzdg) throws GeneralSecurityException {
        zzur zzur;
        zzva.zza zza2 = zzva.zzc().zza(zzdg.zze());
        zzdg.zzc zzg = zzdg.zzg();
        if (zzdg.zzc.zza.equals(zzg)) {
            zzur = zzur.SHA1;
        } else if (zzdg.zzc.zzb.equals(zzg)) {
            zzur = zzur.SHA224;
        } else if (zzdg.zzc.zzc.equals(zzg)) {
            zzur = zzur.SHA256;
        } else if (zzdg.zzc.zzd.equals(zzg)) {
            zzur = zzur.SHA384;
        } else if (zzdg.zzc.zze.equals(zzg)) {
            zzur = zzur.SHA512;
        } else {
            throw new GeneralSecurityException("Unable to serialize HashType " + String.valueOf(zzg));
        }
        return (zzva) ((zzajy) zza2.zza(zzur).zze());
    }

    private static zzws zza(zzdg.zzb zzb2) throws GeneralSecurityException {
        if (zzdg.zzb.zza.equals(zzb2)) {
            return zzws.TINK;
        }
        if (zzdg.zzb.zzb.equals(zzb2)) {
            return zzws.CRUNCHY;
        }
        if (zzdg.zzb.zzc.equals(zzb2)) {
            return zzws.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: " + String.valueOf(zzb2));
    }

    static {
        zzzc zzb2 = zzpr.zzb("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey");
        zza = zzb2;
        zzc = zzoj.zza(new zzge(), zzb2, zzpf.class);
        zze = zzms.zza(new zzgg(), zzb2, zzpc.class);
    }

    public static void zza() throws GeneralSecurityException {
        zzof zza2 = zzof.zza();
        zza2.zza(zzb);
        zza2.zza(zzc);
        zza2.zza(zzd);
        zza2.zza(zze);
    }
}
