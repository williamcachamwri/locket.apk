package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzlz extends zzoz {
    final /* synthetic */ zzma zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzlz(zzma zzma, Class cls) {
        super(cls);
        this.zza = zzma;
    }

    public final /* bridge */ /* synthetic */ zzaef zza(zzaef zzaef) throws GeneralSecurityException {
        zzsy zzc = zzsz.zzc();
        zzc.zza(zzaby.zzn(zzyq.zza(((zztc) zzaef).zza())));
        zzc.zzb(0);
        return (zzsz) zzc.zzan();
    }

    public final /* synthetic */ zzaef zzb(zzaby zzaby) throws zzadi {
        return zztc.zze(zzaby, zzacm.zza());
    }

    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("AES128_GCM_SIV", zzma.zzh(16, 1));
        hashMap.put("AES128_GCM_SIV_RAW", zzma.zzh(16, 3));
        hashMap.put("AES256_GCM_SIV", zzma.zzh(32, 1));
        hashMap.put("AES256_GCM_SIV_RAW", zzma.zzh(32, 3));
        return Collections.unmodifiableMap(hashMap);
    }

    public final /* synthetic */ void zzd(zzaef zzaef) throws GeneralSecurityException {
        zzys.zza(((zztc) zzaef).zza());
    }
}
