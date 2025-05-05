package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzmm extends zzoz {
    final /* synthetic */ zzmn zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzmm(zzmn zzmn, Class cls) {
        super(cls);
        this.zza = zzmn;
    }

    public final /* bridge */ /* synthetic */ zzaef zza(zzaef zzaef) throws GeneralSecurityException {
        zzxd zzxd = (zzxd) zzaef;
        zzwz zzc = zzxa.zzc();
        zzc.zzb(0);
        zzc.zza(zzaby.zzn(zzyq.zza(32)));
        return (zzxa) zzc.zzan();
    }

    public final /* synthetic */ zzaef zzb(zzaby zzaby) throws zzadi {
        return zzxd.zzd(zzaby, zzacm.zza());
    }

    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("XCHACHA20_POLY1305", new zzoy(zzxd.zzc(), 1));
        hashMap.put("XCHACHA20_POLY1305_RAW", new zzoy(zzxd.zzc(), 3));
        return Collections.unmodifiableMap(hashMap);
    }

    public final /* bridge */ /* synthetic */ void zzd(zzaef zzaef) throws GeneralSecurityException {
        zzxd zzxd = (zzxd) zzaef;
    }
}
