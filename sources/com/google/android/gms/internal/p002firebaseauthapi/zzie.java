package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzga;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzie  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzie {
    private static final zzzc zza;
    private static final zzon<zzga, zzpf> zzb = zzon.zza(new zzid(), zzga.class, zzpf.class);
    private static final zzoj<zzpf> zzc;
    private static final zzmw<zzfv, zzpc> zzd = zzmw.zza(new zzif(), zzfv.class, zzpc.class);
    private static final zzms<zzpc> zze;

    /* access modifiers changed from: private */
    public static zzfv zzb(zzpc zzpc, @Nullable zzcn zzcn) throws GeneralSecurityException {
        if (zzpc.zzf().equals("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key")) {
            try {
                zzxh zza2 = zzxh.zza(zzpc.zzd(), zzajk.zza());
                if (zza2.zza() == 0) {
                    return zzfv.zza(zza(zzpc.zzb()), zzze.zza(zza2.zzd().zzd(), zzcn.zza(zzcn)), zzpc.zze());
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzakf unused) {
                throw new GeneralSecurityException("Parsing XChaCha20Poly1305Key failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to XChaCha20Poly1305ProtoSerialization.parseKey");
        }
    }

    private static zzga.zza zza(zzws zzws) throws GeneralSecurityException {
        int i = zzih.zza[zzws.ordinal()];
        if (i == 1) {
            return zzga.zza.zza;
        }
        if (i == 2 || i == 3) {
            return zzga.zza.zzb;
        }
        if (i == 4) {
            return zzga.zza.zzc;
        }
        throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zzws.zza());
    }

    /* access modifiers changed from: private */
    public static zzga zzb(zzpf zzpf) throws GeneralSecurityException {
        if (zzpf.zza().zzf().equals("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key")) {
            try {
                if (zzxk.zza(zzpf.zza().zze(), zzajk.zza()).zza() == 0) {
                    return zzga.zza(zza(zzpf.zza().zzd()));
                }
                throw new GeneralSecurityException("Only version 0 parameters are accepted");
            } catch (zzakf e) {
                throw new GeneralSecurityException("Parsing XChaCha20Poly1305Parameters failed: ", e);
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to XChaCha20Poly1305ProtoSerialization.parseParameters: " + zzpf.zza().zzf());
        }
    }

    private static zzws zza(zzga.zza zza2) throws GeneralSecurityException {
        if (zzga.zza.zza.equals(zza2)) {
            return zzws.TINK;
        }
        if (zzga.zza.zzb.equals(zza2)) {
            return zzws.CRUNCHY;
        }
        if (zzga.zza.zzc.equals(zza2)) {
            return zzws.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: " + String.valueOf(zza2));
    }

    static {
        zzzc zzb2 = zzpr.zzb("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key");
        zza = zzb2;
        zzc = zzoj.zza(new zzig(), zzb2, zzpf.class);
        zze = zzms.zza(new zzii(), zzb2, zzpc.class);
    }

    public static void zza() throws GeneralSecurityException {
        zzof zza2 = zzof.zza();
        zza2.zza(zzb);
        zza2.zza(zzc);
        zza2.zza(zzd);
        zza2.zza(zze);
    }
}
