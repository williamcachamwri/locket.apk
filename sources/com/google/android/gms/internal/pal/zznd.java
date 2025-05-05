package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zznd extends zzoz {
    final /* synthetic */ zzne zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zznd(zzne zzne, Class cls) {
        super(cls);
        this.zza = zzne;
    }

    public final /* bridge */ /* synthetic */ zzaef zza(zzaef zzaef) throws GeneralSecurityException {
        zzte zzc = zztf.zzc();
        zzc.zza(zzaby.zzn(zzyq.zza(((zzti) zzaef).zza())));
        zzc.zzb(0);
        return (zztf) zzc.zzan();
    }

    public final /* synthetic */ zzaef zzb(zzaby zzaby) throws zzadi {
        return zzti.zze(zzaby, zzacm.zza());
    }

    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        zzth zzc = zzti.zzc();
        zzc.zza(64);
        hashMap.put("AES256_SIV", new zzoy((zzti) zzc.zzan(), 1));
        zzth zzc2 = zzti.zzc();
        zzc2.zza(64);
        hashMap.put("AES256_SIV_RAW", new zzoy((zzti) zzc2.zzan(), 3));
        return Collections.unmodifiableMap(hashMap);
    }

    public final /* bridge */ /* synthetic */ void zzd(zzaef zzaef) throws GeneralSecurityException {
        zzti zzti = (zzti) zzaef;
        if (zzti.zza() != 64) {
            int zza2 = zzti.zza();
            throw new InvalidAlgorithmParameterException("invalid key size: " + zza2 + ". Valid keys must have 64 bytes.");
        }
    }
}
