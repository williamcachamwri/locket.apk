package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzei;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhe  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzhe {
    private static final zzzc zza;
    private static final zzon<zzei, zzpf> zzb = zzon.zza(new zzhd(), zzei.class, zzpf.class);
    private static final zzoj<zzpf> zzc;
    private static final zzmw<zzee, zzpc> zzd = zzmw.zza(new zzhf(), zzee.class, zzpc.class);
    private static final zzms<zzpc> zze;

    /* access modifiers changed from: private */
    public static zzee zzb(zzpc zzpc, @Nullable zzcn zzcn) throws GeneralSecurityException {
        if (zzpc.zzf().equals("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key")) {
            try {
                zzto zza2 = zzto.zza(zzpc.zzd(), zzajk.zza());
                if (zza2.zza() == 0) {
                    return zzee.zza(zza(zzpc.zzb()), zzze.zza(zza2.zzd().zzd(), zzcn.zza(zzcn)), zzpc.zze());
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzakf unused) {
                throw new GeneralSecurityException("Parsing ChaCha20Poly1305Key failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to ChaCha20Poly1305ProtoSerialization.parseKey");
        }
    }

    private static zzei.zza zza(zzws zzws) throws GeneralSecurityException {
        int i = zzhh.zza[zzws.ordinal()];
        if (i == 1) {
            return zzei.zza.zza;
        }
        if (i == 2 || i == 3) {
            return zzei.zza.zzb;
        }
        if (i == 4) {
            return zzei.zza.zzc;
        }
        throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zzws.zza());
    }

    /* access modifiers changed from: private */
    public static zzei zzb(zzpf zzpf) throws GeneralSecurityException {
        if (zzpf.zza().zzf().equals("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key")) {
            try {
                zztr.zza(zzpf.zza().zze(), zzajk.zza());
                return zzei.zza(zza(zzpf.zza().zzd()));
            } catch (zzakf e) {
                throw new GeneralSecurityException("Parsing ChaCha20Poly1305Parameters failed: ", e);
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to ChaCha20Poly1305ProtoSerialization.parseParameters: " + zzpf.zza().zzf());
        }
    }

    private static zzws zza(zzei.zza zza2) throws GeneralSecurityException {
        if (zzei.zza.zza.equals(zza2)) {
            return zzws.TINK;
        }
        if (zzei.zza.zzb.equals(zza2)) {
            return zzws.CRUNCHY;
        }
        if (zzei.zza.zzc.equals(zza2)) {
            return zzws.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: " + String.valueOf(zza2));
    }

    static {
        zzzc zzb2 = zzpr.zzb("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key");
        zza = zzb2;
        zzc = zzoj.zza(new zzhg(), zzb2, zzpf.class);
        zze = zzms.zza(new zzhi(), zzb2, zzpc.class);
    }

    public static void zza() throws GeneralSecurityException {
        zzof zza2 = zzof.zza();
        zza2.zza(zzb);
        zza2.zza(zzc);
        zza2.zza(zzd);
        zza2.zza(zze);
    }
}
