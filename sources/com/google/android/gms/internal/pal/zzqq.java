package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzqq extends zzoz {
    final /* synthetic */ zzqr zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzqq(zzqr zzqr, Class cls) {
        super(cls);
        this.zza = zzqr;
    }

    public final /* bridge */ /* synthetic */ zzaef zza(zzaef zzaef) throws GeneralSecurityException {
        zzus zzus = (zzus) zzaef;
        zzuo zzc = zzup.zzc();
        zzc.zzc(0);
        zzc.zzb(zzus.zzg());
        zzc.zza(zzaby.zzn(zzyq.zza(zzus.zza())));
        return (zzup) zzc.zzan();
    }

    public final /* synthetic */ zzaef zzb(zzaby zzaby) throws zzadi {
        return zzus.zzf(zzaby, zzacm.zza());
    }

    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("HMAC_SHA256_128BITTAG", zzqr.zzm(32, 16, 5, 1));
        hashMap.put("HMAC_SHA256_128BITTAG_RAW", zzqr.zzm(32, 16, 5, 3));
        hashMap.put("HMAC_SHA256_256BITTAG", zzqr.zzm(32, 32, 5, 1));
        hashMap.put("HMAC_SHA256_256BITTAG_RAW", zzqr.zzm(32, 32, 5, 3));
        hashMap.put("HMAC_SHA512_128BITTAG", zzqr.zzm(64, 16, 6, 1));
        hashMap.put("HMAC_SHA512_128BITTAG_RAW", zzqr.zzm(64, 16, 6, 3));
        hashMap.put("HMAC_SHA512_256BITTAG", zzqr.zzm(64, 32, 6, 1));
        hashMap.put("HMAC_SHA512_256BITTAG_RAW", zzqr.zzm(64, 32, 6, 3));
        hashMap.put("HMAC_SHA512_512BITTAG", zzqr.zzm(64, 64, 6, 1));
        hashMap.put("HMAC_SHA512_512BITTAG_RAW", zzqr.zzm(64, 64, 6, 3));
        return Collections.unmodifiableMap(hashMap);
    }

    public final /* bridge */ /* synthetic */ void zzd(zzaef zzaef) throws GeneralSecurityException {
        zzus zzus = (zzus) zzaef;
        if (zzus.zza() >= 16) {
            zzqr.zzn(zzus.zzg());
            return;
        }
        throw new GeneralSecurityException("key too short");
    }
}
