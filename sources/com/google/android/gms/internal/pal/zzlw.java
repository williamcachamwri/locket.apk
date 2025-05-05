package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzlw extends zzoz {
    final /* synthetic */ zzlx zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzlw(zzlx zzlx, Class cls) {
        super(cls);
        this.zza = zzlx;
    }

    public final /* bridge */ /* synthetic */ zzaef zza(zzaef zzaef) throws GeneralSecurityException {
        zzss zzc = zzst.zzc();
        zzc.zza(zzaby.zzn(zzyq.zza(((zzsw) zzaef).zza())));
        zzc.zzb(0);
        return (zzst) zzc.zzan();
    }

    public final /* synthetic */ zzaef zzb(zzaby zzaby) throws zzadi {
        return zzsw.zze(zzaby, zzacm.zza());
    }

    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("AES128_GCM", zzlx.zzg(16, 1));
        hashMap.put("AES128_GCM_RAW", zzlx.zzg(16, 3));
        hashMap.put("AES256_GCM", zzlx.zzg(32, 1));
        hashMap.put("AES256_GCM_RAW", zzlx.zzg(32, 3));
        return Collections.unmodifiableMap(hashMap);
    }

    public final /* synthetic */ void zzd(zzaef zzaef) throws GeneralSecurityException {
        zzys.zza(((zzsw) zzaef).zza());
    }
}
