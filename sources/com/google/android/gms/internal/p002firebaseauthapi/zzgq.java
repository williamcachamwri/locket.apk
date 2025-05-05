package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzdv;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgq  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzgq {
    private static final zzzc zza;
    private static final zzon<zzdv, zzpf> zzb = zzon.zza(new zzgp(), zzdv.class, zzpf.class);
    private static final zzoj<zzpf> zzc;
    private static final zzmw<zzdo, zzpc> zzd = zzmw.zza(new zzgr(), zzdo.class, zzpc.class);
    private static final zzms<zzpc> zze;

    /* access modifiers changed from: private */
    public static zzdo zzb(zzpc zzpc, @Nullable zzcn zzcn) throws GeneralSecurityException {
        if (zzpc.zzf().equals("type.googleapis.com/google.crypto.tink.AesGcmKey")) {
            try {
                zzsw zza2 = zzsw.zza(zzpc.zzd(), zzajk.zza());
                if (zza2.zza() == 0) {
                    return zzdo.zzb().zza(zzdv.zze().zzb(zza2.zzd().zzb()).zza(12).zzc(16).zza(zza(zzpc.zzb())).zza()).zza(zzze.zza(zza2.zzd().zzd(), zzcn.zza(zzcn))).zza(zzpc.zze()).zza();
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzakf unused) {
                throw new GeneralSecurityException("Parsing AesGcmKey failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to AesGcmProtoSerialization.parseKey");
        }
    }

    private static zzdv.zzb zza(zzws zzws) throws GeneralSecurityException {
        int i = zzgt.zza[zzws.ordinal()];
        if (i == 1) {
            return zzdv.zzb.zza;
        }
        if (i == 2 || i == 3) {
            return zzdv.zzb.zzb;
        }
        if (i == 4) {
            return zzdv.zzb.zzc;
        }
        throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zzws.zza());
    }

    /* access modifiers changed from: private */
    public static zzdv zzb(zzpf zzpf) throws GeneralSecurityException {
        if (zzpf.zza().zzf().equals("type.googleapis.com/google.crypto.tink.AesGcmKey")) {
            try {
                zzsz zza2 = zzsz.zza(zzpf.zza().zze(), zzajk.zza());
                if (zza2.zzb() == 0) {
                    return zzdv.zze().zzb(zza2.zza()).zza(12).zzc(16).zza(zza(zzpf.zza().zzd())).zza();
                }
                throw new GeneralSecurityException("Only version 0 parameters are accepted");
            } catch (zzakf e) {
                throw new GeneralSecurityException("Parsing AesGcmParameters failed: ", e);
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to AesGcmProtoSerialization.parseParameters: " + zzpf.zza().zzf());
        }
    }

    private static zzws zza(zzdv.zzb zzb2) throws GeneralSecurityException {
        if (zzdv.zzb.zza.equals(zzb2)) {
            return zzws.TINK;
        }
        if (zzdv.zzb.zzb.equals(zzb2)) {
            return zzws.CRUNCHY;
        }
        if (zzdv.zzb.zzc.equals(zzb2)) {
            return zzws.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: " + String.valueOf(zzb2));
    }

    static {
        zzzc zzb2 = zzpr.zzb("type.googleapis.com/google.crypto.tink.AesGcmKey");
        zza = zzb2;
        zzc = zzoj.zza(new zzgs(), zzb2, zzpf.class);
        zze = zzms.zza(new zzgu(), zzb2, zzpc.class);
    }

    public static void zza() throws GeneralSecurityException {
        zzof zza2 = zzof.zza();
        zza2.zza(zzb);
        zza2.zza(zzc);
        zza2.zza(zzd);
        zza2.zza(zze);
    }

    /* access modifiers changed from: private */
    public static void zzb(zzdv zzdv) throws GeneralSecurityException {
        if (zzdv.zzd() != 16) {
            throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d. Currently Tink only supports serialization of AES GCM keys with tag size equal to 16 bytes.", new Object[]{Integer.valueOf(zzdv.zzd())}));
        } else if (zzdv.zzb() != 12) {
            throw new GeneralSecurityException(String.format("Invalid IV size in bytes %d. Currently Tink only supports serialization of AES GCM keys with IV size equal to 12 bytes.", new Object[]{Integer.valueOf(zzdv.zzb())}));
        }
    }
}
