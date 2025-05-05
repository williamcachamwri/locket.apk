package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzlt extends zzoz {
    final /* synthetic */ zzlu zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzlt(zzlu zzlu, Class cls) {
        super(cls);
        this.zza = zzlu;
    }

    public final /* bridge */ /* synthetic */ zzaef zza(zzaef zzaef) throws GeneralSecurityException {
        zzsn zzsn = (zzsn) zzaef;
        zzsj zzc = zzsk.zzc();
        zzc.zza(zzaby.zzn(zzyq.zza(zzsn.zza())));
        zzc.zzb(zzsn.zzf());
        zzc.zzc(0);
        return (zzsk) zzc.zzan();
    }

    public final /* synthetic */ zzaef zzb(zzaby zzaby) throws zzadi {
        return zzsn.zze(zzaby, zzacm.zza());
    }

    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("AES128_EAX", zzlu.zzg(16, 16, 1));
        hashMap.put("AES128_EAX_RAW", zzlu.zzg(16, 16, 3));
        hashMap.put("AES256_EAX", zzlu.zzg(32, 16, 1));
        hashMap.put("AES256_EAX_RAW", zzlu.zzg(32, 16, 3));
        return Collections.unmodifiableMap(hashMap);
    }

    public final /* bridge */ /* synthetic */ void zzd(zzaef zzaef) throws GeneralSecurityException {
        zzsn zzsn = (zzsn) zzaef;
        zzys.zza(zzsn.zza());
        if (zzsn.zzf().zza() != 12 && zzsn.zzf().zza() != 16) {
            throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
        }
    }
}
