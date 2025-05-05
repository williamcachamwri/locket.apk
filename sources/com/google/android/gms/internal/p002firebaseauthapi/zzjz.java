package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzju;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjz  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzjz {
    private static final zzzc zza;
    private static final zzzc zzb;
    private static final zzon<zzju, zzpf> zzc = zzon.zza(new zzjy(), zzju.class, zzpf.class);
    private static final zzoj<zzpf> zzd;
    private static final zzmw<zzke, zzpc> zze = zzmw.zza(new zzka(), zzke.class, zzpc.class);
    private static final zzms<zzpc> zzf;
    private static final zzmw<zzjw, zzpc> zzg = zzmw.zza(new zzkc(), zzjw.class, zzpc.class);
    private static final zzms<zzpc> zzh;
    private static final zzmm<zzws, zzju.zze> zzi = zzmm.zza().zza(zzws.RAW, zzju.zze.zzc).zza(zzws.TINK, zzju.zze.zza).zza(zzws.LEGACY, zzju.zze.zzb).zza(zzws.CRUNCHY, zzju.zze.zzb).zza();
    private static final zzmm<zzvf, zzju.zzf> zzj = zzmm.zza().zza(zzvf.DHKEM_P256_HKDF_SHA256, zzju.zzf.zza).zza(zzvf.DHKEM_P384_HKDF_SHA384, zzju.zzf.zzb).zza(zzvf.DHKEM_P521_HKDF_SHA512, zzju.zzf.zzc).zza(zzvf.DHKEM_X25519_HKDF_SHA256, zzju.zzf.zzd).zza();
    private static final zzmm<zzvc, zzju.zzc> zzk = zzmm.zza().zza(zzvc.HKDF_SHA256, zzju.zzc.zza).zza(zzvc.HKDF_SHA384, zzju.zzc.zzb).zza(zzvc.HKDF_SHA512, zzju.zzc.zzc).zza();
    private static final zzmm<zzvd, zzju.zzb> zzl = zzmm.zza().zza(zzvd.AES_128_GCM, zzju.zzb.zza).zza(zzvd.AES_256_GCM, zzju.zzb.zzb).zza(zzvd.CHACHA20_POLY1305, zzju.zzb.zzc).zza();

    private static zzju zza(zzws zzws, zzvh zzvh) throws GeneralSecurityException {
        return zzju.zzc().zza(zzi.zza(zzws)).zza(zzj.zza(zzvh.zzc())).zza(zzk.zza(zzvh.zzb())).zza(zzl.zza(zzvh.zza())).zza();
    }

    /* access modifiers changed from: private */
    public static zzju zzb(zzpf zzpf) throws GeneralSecurityException {
        if (zzpf.zza().zzf().equals("type.googleapis.com/google.crypto.tink.HpkePrivateKey")) {
            try {
                return zza(zzpf.zza().zzd(), zzve.zza(zzpf.zza().zze(), zzajk.zza()).zzc());
            } catch (zzakf e) {
                throw new GeneralSecurityException("Parsing HpkeParameters failed: ", e);
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to HpkeProtoSerialization.parseParameters: " + zzpf.zza().zzf());
        }
    }

    /* access modifiers changed from: private */
    public static zzjw zzc(zzpc zzpc, @Nullable zzcn zzcn) throws GeneralSecurityException {
        if (zzpc.zzf().equals("type.googleapis.com/google.crypto.tink.HpkePrivateKey")) {
            try {
                zzvk zza2 = zzvk.zza(zzpc.zzd(), zzajk.zza());
                if (zza2.zza() == 0) {
                    zzvn zzd2 = zza2.zzd();
                    if (zzd2.zza() == 0) {
                        zzju zza3 = zza(zzpc.zzb(), zzd2.zzb());
                        return zzjw.zza(zzke.zza(zza3, zza(zza3.zze(), zzd2.zzf().zzd()), zzpc.zze()), zzze.zza(zzmj.zza(zzmj.zza(zza2.zze().zzd()), zzlq.zza(zza3.zze())), zzcn.zza(zzcn)));
                    }
                    throw new GeneralSecurityException("Only version 0 keys are accepted");
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzakf unused) {
                throw new GeneralSecurityException("Parsing HpkePrivateKey failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to HpkeProtoSerialization.parsePrivateKey: " + zzpc.zzf());
        }
    }

    /* access modifiers changed from: private */
    public static zzke zzd(zzpc zzpc, @Nullable zzcn zzcn) throws GeneralSecurityException {
        if (zzpc.zzf().equals("type.googleapis.com/google.crypto.tink.HpkePublicKey")) {
            try {
                zzvn zza2 = zzvn.zza(zzpc.zzd(), zzajk.zza());
                if (zza2.zza() == 0) {
                    zzju zza3 = zza(zzpc.zzb(), zza2.zzb());
                    return zzke.zza(zza3, zza(zza3.zze(), zza2.zzf().zzd()), zzpc.zze());
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzakf unused) {
                throw new GeneralSecurityException("Parsing HpkePublicKey failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to HpkeProtoSerialization.parsePublicKey: " + zzpc.zzf());
        }
    }

    private static zzvh zzb(zzju zzju) throws GeneralSecurityException {
        return (zzvh) ((zzajy) zzvh.zzd().zza(zzj.zza(zzju.zze())).zza(zzk.zza(zzju.zzd())).zza(zzl.zza(zzju.zzb())).zze());
    }

    private static zzvn zza(zzke zzke) throws GeneralSecurityException {
        return (zzvn) ((zzajy) zzvn.zzc().zza(0).zza(zzb(zzke.zzb())).zza(zzaip.zza(zzke.zzd().zzb())).zze());
    }

    private static zzzc zza(zzju.zzf zzf2, byte[] bArr) throws GeneralSecurityException {
        return zzzc.zza(zzmj.zza(zzmj.zza(bArr), zzlq.zzb(zzf2)));
    }

    static {
        zzzc zzb2 = zzpr.zzb("type.googleapis.com/google.crypto.tink.HpkePrivateKey");
        zza = zzb2;
        zzzc zzb3 = zzpr.zzb("type.googleapis.com/google.crypto.tink.HpkePublicKey");
        zzb = zzb3;
        zzd = zzoj.zza(new zzkb(), zzb2, zzpf.class);
        zzf = zzms.zza(new zzkd(), zzb3, zzpc.class);
        zzh = zzms.zza(new zzkf(), zzb2, zzpc.class);
    }

    public static void zza() throws GeneralSecurityException {
        zzof zza2 = zzof.zza();
        zza2.zza(zzc);
        zza2.zza(zzd);
        zza2.zza(zze);
        zza2.zza(zzf);
        zza2.zza(zzg);
        zza2.zza(zzh);
    }
}
