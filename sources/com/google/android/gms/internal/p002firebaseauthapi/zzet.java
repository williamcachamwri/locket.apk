package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzer;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzet  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzet {
    private static final zzzc zza;
    private static final zzon<zzer, zzpf> zzb = zzon.zza(new zzev(), zzer.class, zzpf.class);
    private static final zzoj<zzpf> zzc;
    private static final zzmw<zzes, zzpc> zzd = zzmw.zza(new zzex(), zzes.class, zzpc.class);
    private static final zzms<zzpc> zze;

    /* access modifiers changed from: private */
    public static zzes zzb(zzpc zzpc, @Nullable zzcn zzcn) throws GeneralSecurityException {
        if (zzpc.zzf().equals("type.googleapis.com/google.crypto.tink.KmsAeadKey")) {
            try {
                zzwg zza2 = zzwg.zza(zzpc.zzd(), zzajk.zza());
                if (zza2.zza() == 0) {
                    return zzes.zza(zzer.zza(zza2.zzd().zzd(), zza(zzpc.zzb())), zzpc.zze());
                }
                throw new GeneralSecurityException("KmsAeadKey are only accepted with version 0, got " + String.valueOf(zza2));
            } catch (zzakf e) {
                throw new GeneralSecurityException("Parsing KmsAeadKey failed: ", e);
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to LegacyKmsAeadProtoSerialization.parseKey");
        }
    }

    private static zzer.zza zza(zzws zzws) throws GeneralSecurityException {
        int i = zzez.zza[zzws.ordinal()];
        if (i == 1) {
            return zzer.zza.zza;
        }
        if (i == 2) {
            return zzer.zza.zzb;
        }
        throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zzws.zza());
    }

    /* access modifiers changed from: private */
    public static zzer zzb(zzpf zzpf) throws GeneralSecurityException {
        if (zzpf.zza().zzf().equals("type.googleapis.com/google.crypto.tink.KmsAeadKey")) {
            try {
                return zzer.zza(zzwj.zza(zzpf.zza().zze(), zzajk.zza()).zzd(), zza(zzpf.zza().zzd()));
            } catch (zzakf e) {
                throw new GeneralSecurityException("Parsing KmsAeadKeyFormat failed: ", e);
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to LegacyKmsAeadProtoSerialization.parseParameters: " + zzpf.zza().zzf());
        }
    }

    private static zzws zza(zzer.zza zza2) throws GeneralSecurityException {
        if (zzer.zza.zza.equals(zza2)) {
            return zzws.TINK;
        }
        if (zzer.zza.zzb.equals(zza2)) {
            return zzws.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: " + String.valueOf(zza2));
    }

    static {
        zzzc zzb2 = zzpr.zzb("type.googleapis.com/google.crypto.tink.KmsAeadKey");
        zza = zzb2;
        zzc = zzoj.zza(new zzeu(), zzb2, zzpf.class);
        zze = zzms.zza(new zzew(), zzb2, zzpc.class);
    }

    public static void zza() throws GeneralSecurityException {
        zzof zza2 = zzof.zza();
        zza2.zza(zzb);
        zza2.zza(zzc);
        zza2.zza(zzd);
        zza2.zza(zze);
    }
}
