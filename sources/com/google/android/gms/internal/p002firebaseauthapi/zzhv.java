package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzft;
import java.security.GeneralSecurityException;
import java.util.Objects;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhv  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzhv {
    private static final zzzc zza;
    private static final zzon<zzft, zzpf> zzb = zzon.zza(new zzhy(), zzft.class, zzpf.class);
    private static final zzoj<zzpf> zzc;
    private static final zzmw<zzfp, zzpc> zzd = zzmw.zza(new zzia(), zzfp.class, zzpc.class);
    private static final zzms<zzpc> zze;

    /* access modifiers changed from: private */
    public static zzfp zzb(zzpc zzpc, @Nullable zzcn zzcn) throws GeneralSecurityException {
        if (zzpc.zzf().equals("type.googleapis.com/google.crypto.tink.XAesGcmKey")) {
            try {
                zzwy zza2 = zzwy.zza(zzpc.zzd(), zzajk.zza());
                if (zza2.zza() != 0) {
                    throw new GeneralSecurityException("Only version 0 keys are accepted");
                } else if (zza2.zze().zzb() == 32) {
                    return zzfp.zza(zzft.zza(zza(zzpc.zzb()), zza2.zzd().zza()), zzze.zza(zza2.zze().zzd(), zzcn.zza(zzcn)), zzpc.zze());
                } else {
                    throw new GeneralSecurityException("Only 32 byte key size is accepted");
                }
            } catch (zzakf unused) {
                throw new GeneralSecurityException("Parsing XAesGcmKey failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to XAesGcmProtoSerialization.parseKey");
        }
    }

    private static zzft.zza zza(zzws zzws) throws GeneralSecurityException {
        int i = zzic.zza[zzws.ordinal()];
        if (i == 1) {
            return zzft.zza.zza;
        }
        if (i == 2) {
            return zzft.zza.zzb;
        }
        throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zzws.zza());
    }

    /* access modifiers changed from: private */
    public static zzft zzb(zzpf zzpf) throws GeneralSecurityException {
        if (zzpf.zza().zzf().equals("type.googleapis.com/google.crypto.tink.XAesGcmKey")) {
            try {
                zzxb zza2 = zzxb.zza(zzpf.zza().zze(), zzajk.zza());
                if (zza2.zza() == 0) {
                    return zzft.zza(zza(zzpf.zza().zzd()), zza2.zzd().zza());
                }
                throw new GeneralSecurityException("Only version 0 parameters are accepted");
            } catch (zzakf e) {
                throw new GeneralSecurityException("Parsing XAesGcmParameters failed: ", e);
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to XAesGcmProtoSerialization.parseParameters: " + zzpf.zza().zzf());
        }
    }

    private static zzws zza(zzft.zza zza2) throws GeneralSecurityException {
        if (Objects.equals(zza2, zzft.zza.zza)) {
            return zzws.TINK;
        }
        if (Objects.equals(zza2, zzft.zza.zzb)) {
            return zzws.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: " + String.valueOf(zza2));
    }

    static {
        zzzc zzb2 = zzpr.zzb("type.googleapis.com/google.crypto.tink.XAesGcmKey");
        zza = zzb2;
        zzc = zzoj.zza(new zzhx(), zzb2, zzpf.class);
        zze = zzms.zza(new zzhz(), zzb2, zzpc.class);
    }

    public static void zza() throws GeneralSecurityException {
        zzof zza2 = zzof.zza();
        zza2.zza(zzb);
        zza2.zza(zzc);
        zza2.zza(zzd);
        zza2.zza(zze);
    }
}
