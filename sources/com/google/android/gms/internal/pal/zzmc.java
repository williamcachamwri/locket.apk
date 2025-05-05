package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzmc extends zzoz {
    final /* synthetic */ zzmd zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzmc(zzmd zzmd, Class cls) {
        super(cls);
        this.zza = zzmd;
    }

    public final /* bridge */ /* synthetic */ zzaef zza(zzaef zzaef) throws GeneralSecurityException {
        zzto zzto = (zzto) zzaef;
        zztk zzc = zztl.zzc();
        zzc.zzb(0);
        zzc.zza(zzaby.zzn(zzyq.zza(32)));
        return (zztl) zzc.zzan();
    }

    public final /* synthetic */ zzaef zzb(zzaby zzaby) throws zzadi {
        return zzto.zzd(zzaby, zzacm.zza());
    }

    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("CHACHA20_POLY1305", new zzoy(zzto.zzc(), 1));
        hashMap.put("CHACHA20_POLY1305_RAW", new zzoy(zzto.zzc(), 3));
        return Collections.unmodifiableMap(hashMap);
    }

    public final /* bridge */ /* synthetic */ void zzd(zzaef zzaef) throws GeneralSecurityException {
        zzto zzto = (zzto) zzaef;
    }
}
