package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zziv;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjg  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzjg {
    private static final zzzc zza;
    private static final zzon<zziv, zzpf> zzb = zzon.zza(new zzjf(), zziv.class, zzpf.class);
    private static final zzoj<zzpf> zzc;
    private static final zzmw<zzio, zzpc> zzd = zzmw.zza(new zzjh(), zzio.class, zzpc.class);
    private static final zzms<zzpc> zze;
    private static final Map<zziv.zzb, zzws> zzf;
    private static final Map<zzws, zziv.zzb> zzg;

    /* access modifiers changed from: private */
    public static zzio zzb(zzpc zzpc, @Nullable zzcn zzcn) throws GeneralSecurityException {
        if (zzpc.zzf().equals("type.googleapis.com/google.crypto.tink.AesSivKey")) {
            try {
                zzti zza2 = zzti.zza(zzpc.zzd(), zzajk.zza());
                if (zza2.zza() == 0) {
                    return zzio.zzb().zza(zziv.zzc().zza(zza2.zzd().zzb()).zza(zza(zzpc.zzb())).zza()).zza(zzze.zza(zza2.zzd().zzd(), zzcn.zza(zzcn))).zza(zzpc.zze()).zza();
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzakf unused) {
                throw new GeneralSecurityException("Parsing AesSivKey failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to AesSivParameters.parseParameters");
        }
    }

    private static zziv.zzb zza(zzws zzws) throws GeneralSecurityException {
        Map<zzws, zziv.zzb> map = zzg;
        if (map.containsKey(zzws)) {
            return map.get(zzws);
        }
        throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zzws.zza());
    }

    /* access modifiers changed from: private */
    public static zziv zzb(zzpf zzpf) throws GeneralSecurityException {
        if (zzpf.zza().zzf().equals("type.googleapis.com/google.crypto.tink.AesSivKey")) {
            try {
                zztl zza2 = zztl.zza(zzpf.zza().zze(), zzajk.zza());
                if (zza2.zzb() == 0) {
                    return zziv.zzc().zza(zza2.zza()).zza(zza(zzpf.zza().zzd())).zza();
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzakf e) {
                throw new GeneralSecurityException("Parsing AesSivParameters failed: ", e);
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to AesSivParameters.parseParameters: " + zzpf.zza().zzf());
        }
    }

    private static zzws zza(zziv.zzb zzb2) throws GeneralSecurityException {
        Map<zziv.zzb, zzws> map = zzf;
        if (map.containsKey(zzb2)) {
            return map.get(zzb2);
        }
        throw new GeneralSecurityException("Unable to serialize variant: " + String.valueOf(zzb2));
    }

    static {
        zzzc zzb2 = zzpr.zzb("type.googleapis.com/google.crypto.tink.AesSivKey");
        zza = zzb2;
        zzc = zzoj.zza(new zzji(), zzb2, zzpf.class);
        zze = zzms.zza(new zzjk(), zzb2, zzpc.class);
        HashMap hashMap = new HashMap();
        hashMap.put(zziv.zzb.zzc, zzws.RAW);
        hashMap.put(zziv.zzb.zza, zzws.TINK);
        hashMap.put(zziv.zzb.zzb, zzws.CRUNCHY);
        zzf = Collections.unmodifiableMap(hashMap);
        EnumMap enumMap = new EnumMap(zzws.class);
        enumMap.put(zzws.RAW, zziv.zzb.zzc);
        enumMap.put(zzws.TINK, zziv.zzb.zza);
        enumMap.put(zzws.CRUNCHY, zziv.zzb.zzb);
        enumMap.put(zzws.LEGACY, zziv.zzb.zzb);
        zzg = Collections.unmodifiableMap(enumMap);
    }

    public static void zza() throws GeneralSecurityException {
        zzof zza2 = zzof.zza();
        zza2.zza(zzb);
        zza2.zza(zzc);
        zza2.zza(zzd);
        zza2.zza(zze);
    }
}
