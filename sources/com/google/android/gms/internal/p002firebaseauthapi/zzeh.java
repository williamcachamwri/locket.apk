package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzei;
import com.google.android.gms.internal.p002firebaseauthapi.zzil;
import com.google.android.gms.internal.p002firebaseauthapi.zzvq;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzeh  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzeh {
    private static final zzor<zzee, zzbg> zza = zzor.zza(new zzeg(), zzee.class, zzbg.class);
    private static final zznx<zzei> zzb = new zzej();
    private static final zzbs<zzbg> zzc = zzna.zza("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key", zzbg.class, zzvq.zzb.SYMMETRIC, zzto.zze());

    static String zza() {
        return "type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key";
    }

    public static /* synthetic */ zzbg zza(zzee zzee) {
        if (zzhc.zzb()) {
            return zzhc.zza(zzee);
        }
        return zzxu.zza(zzee);
    }

    static zzee zza(zzei zzei, @Nullable Integer num) throws GeneralSecurityException {
        return zzee.zza(zzei.zzb(), zzze.zza(32), num);
    }

    public static void zza(boolean z) throws GeneralSecurityException {
        if (zzil.zza.ALGORITHM_NOT_FIPS.zza()) {
            zzhe.zza();
            zzoc.zza().zza(zza);
            zznv.zza().zza(zzb, zzei.class);
            zzod zza2 = zzod.zza();
            HashMap hashMap = new HashMap();
            hashMap.put("CHACHA20_POLY1305", zzei.zza(zzei.zza.zza));
            hashMap.put("CHACHA20_POLY1305_RAW", zzei.zza(zzei.zza.zzc));
            zza2.zza(Collections.unmodifiableMap(hashMap));
            zzmt.zza().zza(zzc, true);
            return;
        }
        throw new GeneralSecurityException("Registering ChaCha20Poly1305 is not supported in FIPS mode");
    }
}
