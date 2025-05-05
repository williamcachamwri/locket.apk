package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzln extends zzoz {
    final /* synthetic */ zzlo zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzln(zzlo zzlo, Class cls) {
        super(cls);
        this.zza = zzlo;
    }

    public final /* bridge */ /* synthetic */ zzaef zza(zzaef zzaef) throws GeneralSecurityException {
        zzry zzry = (zzry) zzaef;
        new zzlr();
        zzsb zzf = zzlq.zzf(zzry.zze());
        zzaef zza2 = new zzqr().zza().zza(zzry.zzf());
        zzru zzc = zzrv.zzc();
        zzc.zza(zzf);
        zzc.zzb((zzup) zza2);
        zzc.zzc(0);
        return (zzrv) zzc.zzan();
    }

    public final /* synthetic */ zzaef zzb(zzaby zzaby) throws zzadi {
        return zzry.zzd(zzaby, zzacm.zza());
    }

    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("AES128_CTR_HMAC_SHA256", zzlo.zzg(16, 16, 32, 16, 5, 1));
        hashMap.put("AES128_CTR_HMAC_SHA256_RAW", zzlo.zzg(16, 16, 32, 16, 5, 3));
        hashMap.put("AES256_CTR_HMAC_SHA256", zzlo.zzg(32, 16, 32, 32, 5, 1));
        hashMap.put("AES256_CTR_HMAC_SHA256_RAW", zzlo.zzg(32, 16, 32, 32, 5, 3));
        return Collections.unmodifiableMap(hashMap);
    }

    public final /* bridge */ /* synthetic */ void zzd(zzaef zzaef) throws GeneralSecurityException {
        zzry zzry = (zzry) zzaef;
        ((zzlq) new zzlr().zza()).zzd(zzry.zze());
        new zzqr().zza().zzd(zzry.zzf());
        zzys.zza(zzry.zze().zza());
    }
}
