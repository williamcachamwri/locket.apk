package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzdm;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgi  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzgi {
    private static final zzzc zza;
    private static final zzon<zzdm, zzpf> zzb = zzon.zza(new zzgh(), zzdm.class, zzpf.class);
    private static final zzoj<zzpf> zzc;
    private static final zzmw<zzdh, zzpc> zzd = zzmw.zza(new zzgj(), zzdh.class, zzpc.class);
    private static final zzms<zzpc> zze;

    /* access modifiers changed from: private */
    public static zzdh zzb(zzpc zzpc, @Nullable zzcn zzcn) throws GeneralSecurityException {
        if (zzpc.zzf().equals("type.googleapis.com/google.crypto.tink.AesEaxKey")) {
            try {
                zzsn zza2 = zzsn.zza(zzpc.zzd(), zzajk.zza());
                if (zza2.zza() == 0) {
                    return zzdh.zzb().zza(zzdm.zze().zzb(zza2.zze().zzb()).zza(zza2.zzd().zza()).zzc(16).zza(zza(zzpc.zzb())).zza()).zza(zzze.zza(zza2.zze().zzd(), zzcn.zza(zzcn))).zza(zzpc.zze()).zza();
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzakf unused) {
                throw new GeneralSecurityException("Parsing AesEaxcKey failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to AesEaxProtoSerialization.parseKey");
        }
    }

    private static zzdm.zza zza(zzws zzws) throws GeneralSecurityException {
        int i = zzgl.zza[zzws.ordinal()];
        if (i == 1) {
            return zzdm.zza.zza;
        }
        if (i == 2 || i == 3) {
            return zzdm.zza.zzb;
        }
        if (i == 4) {
            return zzdm.zza.zzc;
        }
        throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zzws.zza());
    }

    /* access modifiers changed from: private */
    public static zzdm zzb(zzpf zzpf) throws GeneralSecurityException {
        if (zzpf.zza().zzf().equals("type.googleapis.com/google.crypto.tink.AesEaxKey")) {
            try {
                zzsq zza2 = zzsq.zza(zzpf.zza().zze(), zzajk.zza());
                return zzdm.zze().zzb(zza2.zza()).zza(zza2.zzd().zza()).zzc(16).zza(zza(zzpf.zza().zzd())).zza();
            } catch (zzakf e) {
                throw new GeneralSecurityException("Parsing AesEaxParameters failed: ", e);
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to AesEaxProtoSerialization.parseParameters: " + zzpf.zza().zzf());
        }
    }

    private static zzst zzb(zzdm zzdm) throws GeneralSecurityException {
        if (zzdm.zzd() == 16) {
            return (zzst) ((zzajy) zzst.zzb().zza(zzdm.zzb()).zze());
        }
        throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d. Currently Tink only supports aes eax keys with tag size equal to 16 bytes.", new Object[]{Integer.valueOf(zzdm.zzd())}));
    }

    private static zzws zza(zzdm.zza zza2) throws GeneralSecurityException {
        if (zzdm.zza.zza.equals(zza2)) {
            return zzws.TINK;
        }
        if (zzdm.zza.zzb.equals(zza2)) {
            return zzws.CRUNCHY;
        }
        if (zzdm.zza.zzc.equals(zza2)) {
            return zzws.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: " + String.valueOf(zza2));
    }

    static {
        zzzc zzb2 = zzpr.zzb("type.googleapis.com/google.crypto.tink.AesEaxKey");
        zza = zzb2;
        zzc = zzoj.zza(new zzgk(), zzb2, zzpf.class);
        zze = zzms.zza(new zzgm(), zzb2, zzpc.class);
    }

    public static void zza() throws GeneralSecurityException {
        zzof zza2 = zzof.zza();
        zza2.zza(zzb);
        zza2.zza(zzc);
        zza2.zza(zzd);
        zza2.zza(zze);
    }
}
