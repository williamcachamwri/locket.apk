package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzqg extends zzoz {
    zzqg(zzqh zzqh, Class cls) {
        super(cls);
    }

    public final /* bridge */ /* synthetic */ zzaef zza(zzaef zzaef) throws GeneralSecurityException {
        zzrp zzrp = (zzrp) zzaef;
        zzrl zzc = zzrm.zzc();
        zzc.zzc(0);
        zzc.zza(zzaby.zzn(zzyq.zza(zzrp.zza())));
        zzc.zzb(zzrp.zzf());
        return (zzrm) zzc.zzan();
    }

    public final /* synthetic */ zzaef zzb(zzaby zzaby) throws zzadi {
        return zzrp.zze(zzaby, zzacm.zza());
    }

    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        zzro zzc = zzrp.zzc();
        zzc.zza(32);
        zzrr zzc2 = zzrs.zzc();
        zzc2.zza(16);
        zzc.zzb((zzrs) zzc2.zzan());
        hashMap.put("AES_CMAC", new zzoy((zzrp) zzc.zzan(), 1));
        zzro zzc3 = zzrp.zzc();
        zzc3.zza(32);
        zzrr zzc4 = zzrs.zzc();
        zzc4.zza(16);
        zzc3.zzb((zzrs) zzc4.zzan());
        hashMap.put("AES256_CMAC", new zzoy((zzrp) zzc3.zzan(), 1));
        zzro zzc5 = zzrp.zzc();
        zzc5.zza(32);
        zzrr zzc6 = zzrs.zzc();
        zzc6.zza(16);
        zzc5.zzb((zzrs) zzc6.zzan());
        hashMap.put("AES256_CMAC_RAW", new zzoy((zzrp) zzc5.zzan(), 3));
        return Collections.unmodifiableMap(hashMap);
    }

    public final /* bridge */ /* synthetic */ void zzd(zzaef zzaef) throws GeneralSecurityException {
        zzrp zzrp = (zzrp) zzaef;
        zzqh.zzm(zzrp.zzf());
        zzqh.zzn(zzrp.zza());
    }
}
