package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzec;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgw  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzgw {
    private static final zzzc zza;
    private static final zzon<zzec, zzpf> zzb = zzon.zza(new zzgv(), zzec.class, zzpf.class);
    private static final zzoj<zzpf> zzc;
    private static final zzmw<zzdx, zzpc> zzd = zzmw.zza(new zzgx(), zzdx.class, zzpc.class);
    private static final zzms<zzpc> zze;

    /* access modifiers changed from: private */
    public static zzdx zzb(zzpc zzpc, @Nullable zzcn zzcn) throws GeneralSecurityException {
        if (zzpc.zzf().equals("type.googleapis.com/google.crypto.tink.AesGcmSivKey")) {
            try {
                zztc zza2 = zztc.zza(zzpc.zzd(), zzajk.zza());
                if (zza2.zza() == 0) {
                    return zzdx.zzb().zza(zzec.zzc().zza(zza2.zzd().zzb()).zza(zza(zzpc.zzb())).zza()).zza(zzze.zza(zza2.zzd().zzd(), zzcn.zza(zzcn))).zza(zzpc.zze()).zza();
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzakf unused) {
                throw new GeneralSecurityException("Parsing AesGcmSivKey failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to AesGcmSivProtoSerialization.parseKey");
        }
    }

    private static zzec.zza zza(zzws zzws) throws GeneralSecurityException {
        int i = zzgz.zza[zzws.ordinal()];
        if (i == 1) {
            return zzec.zza.zza;
        }
        if (i == 2 || i == 3) {
            return zzec.zza.zzb;
        }
        if (i == 4) {
            return zzec.zza.zzc;
        }
        throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zzws.zza());
    }

    /* access modifiers changed from: private */
    public static zzec zzb(zzpf zzpf) throws GeneralSecurityException {
        if (zzpf.zza().zzf().equals("type.googleapis.com/google.crypto.tink.AesGcmSivKey")) {
            try {
                zztf zza2 = zztf.zza(zzpf.zza().zze(), zzajk.zza());
                if (zza2.zzb() == 0) {
                    return zzec.zzc().zza(zza2.zza()).zza(zza(zzpf.zza().zzd())).zza();
                }
                throw new GeneralSecurityException("Only version 0 parameters are accepted");
            } catch (zzakf e) {
                throw new GeneralSecurityException("Parsing AesGcmSivParameters failed: ", e);
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to AesGcmSivProtoSerialization.parseParameters: " + zzpf.zza().zzf());
        }
    }

    private static zzws zza(zzec.zza zza2) throws GeneralSecurityException {
        if (zzec.zza.zza.equals(zza2)) {
            return zzws.TINK;
        }
        if (zzec.zza.zzb.equals(zza2)) {
            return zzws.CRUNCHY;
        }
        if (zzec.zza.zzc.equals(zza2)) {
            return zzws.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: " + String.valueOf(zza2));
    }

    static {
        zzzc zzb2 = zzpr.zzb("type.googleapis.com/google.crypto.tink.AesGcmSivKey");
        zza = zzb2;
        zzc = zzoj.zza(new zzgy(), zzb2, zzpf.class);
        zze = zzms.zza(new zzha(), zzb2, zzpc.class);
    }

    public static void zza() throws GeneralSecurityException {
        zzof zza2 = zzof.zza();
        zza2.zza(zzb);
        zza2.zza(zzc);
        zza2.zza(zzd);
        zza2.zza(zze);
    }
}
