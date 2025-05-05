package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzga;
import com.google.android.gms.internal.p002firebaseauthapi.zzil;
import com.google.android.gms.internal.p002firebaseauthapi.zzvq;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfx  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzfx {
    private static final zzor<zzfv, zzbg> zza = zzor.zza(new zzfw(), zzfv.class, zzbg.class);
    private static final zzbs<zzbg> zzb = zzna.zza("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key", zzbg.class, zzvq.zzb.SYMMETRIC, zzxh.zze());
    private static final zznz<zzga> zzc = new zzfz();
    private static final zznx<zzga> zzd = new zzfy();

    static String zza() {
        return "type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key";
    }

    public static /* synthetic */ zzbg zza(zzfv zzfv) {
        if (zzib.zza()) {
            return zzib.zza(zzfv);
        }
        return zzzd.zza(zzfv);
    }

    static zzfv zza(zzga zzga, @Nullable Integer num) throws GeneralSecurityException {
        return zzfv.zza(zzga.zzb(), zzze.zza(32), num);
    }

    public static void zza(boolean z) throws GeneralSecurityException {
        if (zzil.zza.ALGORITHM_NOT_FIPS.zza()) {
            zzie.zza();
            zzoc.zza().zza(zza);
            zzod zza2 = zzod.zza();
            HashMap hashMap = new HashMap();
            hashMap.put("XCHACHA20_POLY1305", zzga.zza(zzga.zza.zza));
            hashMap.put("XCHACHA20_POLY1305_RAW", zzga.zza(zzga.zza.zzc));
            zza2.zza(Collections.unmodifiableMap(hashMap));
            zznv.zza().zza(zzd, zzga.class);
            zznw.zza().zza(zzc, zzga.class);
            zzmt.zza().zza(zzb, true);
            return;
        }
        throw new GeneralSecurityException("Registering XChaCha20Poly1305 is not supported in FIPS mode");
    }
}
