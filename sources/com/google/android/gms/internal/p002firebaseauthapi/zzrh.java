package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzqk;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzrh  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzrh {
    private static final zzzc zza;
    private static final zzmm<zzws, zzqk.zzb> zzb = zzmm.zza().zza(zzws.RAW, zzqk.zzb.zzd).zza(zzws.TINK, zzqk.zzb.zza).zza(zzws.LEGACY, zzqk.zzb.zzc).zza(zzws.CRUNCHY, zzqk.zzb.zzb).zza();
    private static final zzmm<zzur, zzqk.zzc> zzc = zzmm.zza().zza(zzur.SHA1, zzqk.zzc.zza).zza(zzur.SHA224, zzqk.zzc.zzb).zza(zzur.SHA256, zzqk.zzc.zzc).zza(zzur.SHA384, zzqk.zzc.zzd).zza(zzur.SHA512, zzqk.zzc.zze).zza();
    private static final zzon<zzqk, zzpf> zzd = zzon.zza(new zzrk(), zzqk.class, zzpf.class);
    private static final zzoj<zzpf> zze;
    private static final zzmw<zzqd, zzpc> zzf = zzmw.zza(new zzrm(), zzqd.class, zzpc.class);
    private static final zzms<zzpc> zzg;

    /* access modifiers changed from: private */
    public static zzqd zzb(zzpc zzpc, @Nullable zzcn zzcn) throws GeneralSecurityException {
        if (zzpc.zzf().equals("type.googleapis.com/google.crypto.tink.HmacKey")) {
            try {
                zzuu zza2 = zzuu.zza(zzpc.zzd(), zzajk.zza());
                if (zza2.zza() == 0) {
                    return zzqd.zzb().zza(zzqk.zzd().zza(zza2.zzf().zzb()).zzb(zza2.zze().zza()).zza(zzc.zza(zza2.zze().zzb())).zza(zzb.zza(zzpc.zzb())).zza()).zza(zzze.zza(zza2.zzf().zzd(), zzcn.zza(zzcn))).zza(zzpc.zze()).zza();
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzakf | IllegalArgumentException unused) {
                throw new GeneralSecurityException("Parsing HmacKey failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to HmacProtoSerialization.parseKey");
        }
    }

    /* access modifiers changed from: private */
    public static zzqk zzb(zzpf zzpf) throws GeneralSecurityException {
        if (zzpf.zza().zzf().equals("type.googleapis.com/google.crypto.tink.HmacKey")) {
            try {
                zzux zza2 = zzux.zza(zzpf.zza().zze(), zzajk.zza());
                if (zza2.zzb() == 0) {
                    return zzqk.zzd().zza(zza2.zza()).zzb(zza2.zzf().zza()).zza(zzc.zza(zza2.zzf().zzb())).zza(zzb.zza(zzpf.zza().zzd())).zza();
                }
                throw new GeneralSecurityException("Parsing HmacParameters failed: unknown Version " + zza2.zzb());
            } catch (zzakf e) {
                throw new GeneralSecurityException("Parsing HmacParameters failed: ", e);
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to HmacProtoSerialization.parseParameters: " + zzpf.zza().zzf());
        }
    }

    private static zzva zzb(zzqk zzqk) throws GeneralSecurityException {
        return (zzva) ((zzajy) zzva.zzc().zza(zzqk.zzb()).zza(zzc.zza(zzqk.zze())).zze());
    }

    static {
        zzzc zzb2 = zzpr.zzb("type.googleapis.com/google.crypto.tink.HmacKey");
        zza = zzb2;
        zze = zzoj.zza(new zzrj(), zzb2, zzpf.class);
        zzg = zzms.zza(new zzrl(), zzb2, zzpc.class);
    }

    public static void zza() throws GeneralSecurityException {
        zzof zza2 = zzof.zza();
        zza2.zza(zzd);
        zza2.zza(zze);
        zza2.zza(zzf);
        zza2.zza(zzg);
    }
}
