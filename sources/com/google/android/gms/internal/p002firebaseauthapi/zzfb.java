package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzfa;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfb  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzfb {
    private static final zzzc zza;
    private static final zzon<zzfa, zzpf> zzb = zzon.zza(new zzfe(), zzfa.class, zzpf.class);
    private static final zzoj<zzpf> zzc;
    private static final zzmw<zzey, zzpc> zzd = zzmw.zza(new zzfg(), zzey.class, zzpc.class);
    private static final zzms<zzpc> zze;

    /* access modifiers changed from: private */
    public static zzey zzb(zzpc zzpc, @Nullable zzcn zzcn) throws GeneralSecurityException {
        if (zzpc.zzf().equals("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey")) {
            try {
                zzwm zza2 = zzwm.zza(zzpc.zzd(), zzajk.zza());
                if (zza2.zza() == 0) {
                    return zzey.zza(zza(zza2.zzd(), zzpc.zzb()), zzpc.zze());
                }
                throw new GeneralSecurityException("KmsEnvelopeAeadKeys are only accepted with version 0, got " + String.valueOf(zza2));
            } catch (zzakf e) {
                throw new GeneralSecurityException("Parsing KmsEnvelopeAeadKey failed: ", e);
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to LegacyKmsEnvelopeAeadProtoSerialization.parseKey");
        }
    }

    /* access modifiers changed from: private */
    public static zzfa zzb(zzpf zzpf) throws GeneralSecurityException {
        if (zzpf.zza().zzf().equals("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey")) {
            try {
                return zza(zzwp.zza(zzpf.zza().zze(), zzajk.zza()), zzpf.zza().zzd());
            } catch (zzakf e) {
                throw new GeneralSecurityException("Parsing KmsEnvelopeAeadKeyFormat failed: ", e);
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to LegacyKmsEnvelopeAeadProtoSerialization.parseParameters: " + zzpf.zza().zzf());
        }
    }

    private static zzfa zza(zzwp zzwp, zzws zzws) throws GeneralSecurityException {
        zzfa.zzc zzc2;
        zzfa.zzb zzb2;
        zzch zza2 = zzcp.zza(((zzvu) ((zzajy) zzvu.zza().zza(zzwp.zza().zzf()).zza(zzwp.zza().zze()).zza(zzws.RAW).zze())).zzk());
        if (zza2 instanceof zzdv) {
            zzc2 = zzfa.zzc.zza;
        } else if (zza2 instanceof zzei) {
            zzc2 = zzfa.zzc.zzc;
        } else if (zza2 instanceof zzga) {
            zzc2 = zzfa.zzc.zzb;
        } else if (zza2 instanceof zzdg) {
            zzc2 = zzfa.zzc.zzd;
        } else if (zza2 instanceof zzdm) {
            zzc2 = zzfa.zzc.zze;
        } else if (zza2 instanceof zzec) {
            zzc2 = zzfa.zzc.zzf;
        } else {
            throw new GeneralSecurityException("Unsupported DEK parameters when parsing " + String.valueOf(zza2));
        }
        zzfa.zza zza3 = new zzfa.zza();
        int i = zzfi.zza[zzws.ordinal()];
        if (i == 1) {
            zzb2 = zzfa.zzb.zza;
        } else if (i == 2) {
            zzb2 = zzfa.zzb.zzb;
        } else {
            throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zzws.zza());
        }
        return zza3.zza(zzb2).zza(zzwp.zze()).zza((zzcw) zza2).zza(zzc2).zza();
    }

    private static zzwp zzb(zzfa zzfa) throws GeneralSecurityException {
        try {
            return (zzwp) ((zzajy) zzwp.zzb().zza(zzfa.zzd()).zza(zzvu.zza(zzcp.zza((zzch) zzfa.zzb()), zzajk.zza())).zze());
        } catch (zzakf e) {
            throw new GeneralSecurityException("Parsing KmsEnvelopeAeadKeyFormat failed: ", e);
        }
    }

    private static zzws zza(zzfa.zzb zzb2) throws GeneralSecurityException {
        if (zzfa.zzb.zza.equals(zzb2)) {
            return zzws.TINK;
        }
        if (zzfa.zzb.zzb.equals(zzb2)) {
            return zzws.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: " + String.valueOf(zzb2));
    }

    static {
        zzzc zzb2 = zzpr.zzb("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey");
        zza = zzb2;
        zzc = zzoj.zza(new zzfd(), zzb2, zzpf.class);
        zze = zzms.zza(new zzff(), zzb2, zzpc.class);
    }

    public static void zza() throws GeneralSecurityException {
        zzof zza2 = zzof.zza();
        zza2.zza(zzb);
        zza2.zza(zzc);
        zza2.zza(zzd);
        zza2.zza(zze);
    }
}
