package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfs  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzfs {
    private static final zznx<zzft> zza = new zzfr();
    private static final zzor<zzfp, zzbg> zzb = zzor.zza(new zzfu(), zzfp.class, zzbg.class);

    public static void zza(boolean z) throws GeneralSecurityException {
        zzhv.zza();
        zzod zza2 = zzod.zza();
        HashMap hashMap = new HashMap();
        hashMap.put("X_AES_GCM_8_BYTE_SALT_NO_PREFIX", zzfh.zzg);
        zza2.zza(Collections.unmodifiableMap(hashMap));
        zzoc.zza().zza(zzb);
        zznv.zza().zza(zza, zzft.class);
    }
}
