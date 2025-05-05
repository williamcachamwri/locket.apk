package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzjp;
import com.google.android.gms.internal.p002firebaseauthapi.zzug;
import com.google.android.gms.internal.p002firebaseauthapi.zzum;
import com.google.android.gms.internal.p002firebaseauthapi.zzvq;
import java.security.GeneralSecurityException;
import java.security.spec.ECPoint;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkz  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzkz {
    private static final zzzc zza;
    private static final zzzc zzb;
    private static final zzon<zzjp, zzpf> zzc = zzon.zza(new zzky(), zzjp.class, zzpf.class);
    private static final zzoj<zzpf> zzd;
    private static final zzmw<zzjv, zzpc> zze = zzmw.zza(new zzla(), zzjv.class, zzpc.class);
    private static final zzms<zzpc> zzf;
    private static final zzmw<zzjs, zzpc> zzg = zzmw.zza(new zzlc(), zzjs.class, zzpc.class);
    private static final zzms<zzpc> zzh;
    private static final zzmm<zzws, zzjp.zzd> zzi = zzmm.zza().zza(zzws.RAW, zzjp.zzd.zzc).zza(zzws.TINK, zzjp.zzd.zza).zza(zzws.LEGACY, zzjp.zzd.zzb).zza(zzws.CRUNCHY, zzjp.zzd.zzb).zza();
    private static final zzmm<zzur, zzjp.zzb> zzj = zzmm.zza().zza(zzur.SHA1, zzjp.zzb.zza).zza(zzur.SHA224, zzjp.zzb.zzb).zza(zzur.SHA256, zzjp.zzb.zzc).zza(zzur.SHA384, zzjp.zzb.zzd).zza(zzur.SHA512, zzjp.zzb.zze).zza();
    private static final zzmm<zzup, zzjp.zzc> zzk = zzmm.zza().zza(zzup.NIST_P256, zzjp.zzc.zza).zza(zzup.NIST_P384, zzjp.zzc.zzb).zza(zzup.NIST_P521, zzjp.zzc.zzc).zza(zzup.CURVE25519, zzjp.zzc.zzd).zza();
    private static final zzmm<zztu, zzjp.zze> zzl = zzmm.zza().zza(zztu.UNCOMPRESSED, zzjp.zze.zzb).zza(zztu.COMPRESSED, zzjp.zze.zza).zza(zztu.DO_NOT_USE_CRUNCHY_UNCOMPRESSED, zzjp.zze.zzc).zza();

    private static int zza(zzjp.zzc zzc2) throws GeneralSecurityException {
        if (zzjp.zzc.zza.equals(zzc2)) {
            return 33;
        }
        if (zzjp.zzc.zzb.equals(zzc2)) {
            return 49;
        }
        if (zzjp.zzc.zzc.equals(zzc2)) {
            return 67;
        }
        throw new GeneralSecurityException("Unable to serialize CurveType " + String.valueOf(zzc2));
    }

    private static zzjp zza(zzws zzws, zzud zzud) throws GeneralSecurityException {
        zzjp.zza zza2 = zzjp.zzc().zza(zzi.zza(zzws)).zza(zzk.zza(zzud.zzf().zzd())).zza(zzj.zza(zzud.zzf().zze())).zza(zzcp.zza(((zzvu) ((zzajy) zzvu.zza().zza(zzud.zzb().zzd().zzf()).zza(zzws.RAW).zza(zzud.zzb().zzd().zze()).zze())).zzk())).zza(zzzc.zza(zzud.zzf().zzf().zzd()));
        if (!zzud.zzf().zzd().equals(zzup.CURVE25519)) {
            zza2.zza(zzl.zza(zzud.zza()));
        } else if (!zzud.zza().equals(zztu.COMPRESSED)) {
            throw new GeneralSecurityException("For CURVE25519 EcPointFormat must be compressed");
        }
        return zza2.zza();
    }

    /* access modifiers changed from: private */
    public static zzjp zzb(zzpf zzpf) throws GeneralSecurityException {
        if (zzpf.zza().zzf().equals("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey")) {
            try {
                return zza(zzpf.zza().zzd(), zzua.zza(zzpf.zza().zze(), zzajk.zza()).zzc());
            } catch (zzakf e) {
                throw new GeneralSecurityException("Parsing EciesParameters failed: ", e);
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to EciesProtoSerialization.parseParameters: " + zzpf.zza().zzf());
        }
    }

    /* access modifiers changed from: private */
    public static zzjs zzc(zzpc zzpc, @Nullable zzcn zzcn) throws GeneralSecurityException {
        if (zzpc.zzf().equals("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey")) {
            try {
                zzug zza2 = zzug.zza(zzpc.zzd(), zzajk.zza());
                if (zza2.zza() == 0) {
                    zzuj zzd2 = zza2.zzd();
                    if (zzd2.zza() == 0) {
                        zzjp zza3 = zza(zzpc.zzb(), zzd2.zzb());
                        if (zza3.zzd().equals(zzjp.zzc.zzd)) {
                            return zzjs.zza(zzjv.zza(zza3, zzzc.zza(zzd2.zzf().zzd()), zzpc.zze()), zzze.zza(zza2.zze().zzd(), zzcn.zza(zzcn)));
                        }
                        return zzjs.zza(zzjv.zza(zza3, new ECPoint(zzmj.zza(zzd2.zzf().zzd()), zzmj.zza(zzd2.b_().zzd())), zzpc.zze()), zzzf.zza(zzmj.zza(zza2.zze().zzd()), zzcn.zza(zzcn)));
                    }
                    throw new GeneralSecurityException("Only version 0 keys are accepted");
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzakf | IllegalArgumentException unused) {
                throw new GeneralSecurityException("Parsing EcdsaPrivateKey failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to EciesProtoSerialization.parsePrivateKey: " + zzpc.zzf());
        }
    }

    /* access modifiers changed from: private */
    public static zzjv zzd(zzpc zzpc, @Nullable zzcn zzcn) throws GeneralSecurityException {
        if (zzpc.zzf().equals("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey")) {
            try {
                zzuj zza2 = zzuj.zza(zzpc.zzd(), zzajk.zza());
                if (zza2.zza() == 0) {
                    zzjp zza3 = zza(zzpc.zzb(), zza2.zzb());
                    if (!zza3.zzd().equals(zzjp.zzc.zzd)) {
                        return zzjv.zza(zza3, new ECPoint(zzmj.zza(zza2.zzf().zzd()), zzmj.zza(zza2.b_().zzd())), zzpc.zze());
                    }
                    if (zza2.b_().zzb() == 0) {
                        return zzjv.zza(zza3, zzzc.zza(zza2.zzf().zzd()), zzpc.zze());
                    }
                    throw new GeneralSecurityException("Y must be empty for X25519 points");
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzakf | IllegalArgumentException unused) {
                throw new GeneralSecurityException("Parsing EcdsaPublicKey failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to EciesProtoSerialization.parsePublicKey: " + zzpc.zzf());
        }
    }

    public static /* synthetic */ zzpc zza(zzjs zzjs, zzcn zzcn) {
        zzug.zza zza2 = zzug.zzb().zza(0).zza(zza((zzjv) ((zzkr) zzjs.zzb())));
        if (zzjs.zzc().zzd().equals(zzjp.zzc.zzd)) {
            zza2.zza(zzaip.zza(zzjs.zzf().zza(zzcn.zza(zzcn))));
        } else {
            zza2.zza(zzaip.zza(zzmj.zza(zzjs.zze().zza(zzcn.zza(zzcn)), zza(zzjs.zzc().zzd()))));
        }
        return zzpc.zza("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey", ((zzug) ((zzajy) zza2.zze())).a_(), zzvq.zzb.ASYMMETRIC_PRIVATE, zzi.zza(zzjs.zzc().zzg()), zzjs.zza());
    }

    private static zzud zzb(zzjp zzjp) throws GeneralSecurityException {
        zzum.zza zza2 = zzum.zza().zza(zzk.zza(zzjp.zzd())).zza(zzj.zza(zzjp.zze()));
        if (zzjp.zzh() != null && zzjp.zzh().zza() > 0) {
            zza2.zza(zzaip.zza(zzjp.zzh().zzb()));
        }
        zzum zzum = (zzum) ((zzajy) zza2.zze());
        try {
            zzvu zza3 = zzvu.zza(zzcp.zza(zzjp.zzb()), zzajk.zza());
            zztx zztx = (zztx) ((zzajy) zztx.zza().zza((zzvu) ((zzajy) zzvu.zza().zza(zza3.zzf()).zza(zzws.TINK).zza(zza3.zze()).zze())).zze());
            zzjp.zze zzf2 = zzjp.zzf();
            if (zzf2 == null) {
                zzf2 = zzjp.zze.zza;
            }
            return (zzud) ((zzajy) zzud.zzc().zza(zzum).zza(zztx).zza(zzl.zza(zzf2)).zze());
        } catch (zzakf e) {
            throw new GeneralSecurityException("Parsing EciesParameters failed: ", e);
        }
    }

    private static zzuj zza(zzjv zzjv) throws GeneralSecurityException {
        if (zzjv.zzb().zzd().equals(zzjp.zzc.zzd)) {
            return (zzuj) ((zzajy) zzuj.zzc().zza(0).zza(zzb(zzjv.zzb())).zza(zzaip.zza(zzjv.zzd().zzb())).zzb(zzaip.zza).zze());
        }
        int zza2 = zza(zzjv.zzb().zzd());
        ECPoint zze2 = zzjv.zze();
        if (zze2 != null) {
            return (zzuj) ((zzajy) zzuj.zzc().zza(0).zza(zzb(zzjv.zzb())).zza(zzaip.zza(zzmj.zza(zze2.getAffineX(), zza2))).zzb(zzaip.zza(zzmj.zza(zze2.getAffineY(), zza2))).zze());
        }
        throw new GeneralSecurityException("NistCurvePoint was null for NIST curve");
    }

    static {
        zzzc zzb2 = zzpr.zzb("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey");
        zza = zzb2;
        zzzc zzb3 = zzpr.zzb("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey");
        zzb = zzb3;
        zzd = zzoj.zza(new zzlb(), zzb2, zzpf.class);
        zzf = zzms.zza(new zzld(), zzb3, zzpc.class);
        zzh = zzms.zza(new zzlf(), zzb2, zzpc.class);
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
