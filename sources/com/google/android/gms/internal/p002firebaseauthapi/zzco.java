package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzco  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzco {
    @Deprecated
    static zzvq zza(String str, zzaip zzaip) throws GeneralSecurityException {
        zzbs<?> zza = zzmt.zza().zza(str);
        if (zza instanceof zzcj) {
            return ((zzcj) zza).zzc(zzaip);
        }
        throw new GeneralSecurityException("manager for key type " + str + " is not a PrivateKeyManager");
    }

    @Nullable
    public static Class<?> zza(Class<?> cls) {
        try {
            return zzoc.zza().zza(cls);
        } catch (GeneralSecurityException unused) {
            return null;
        }
    }

    public static <P> P zza(zzvq zzvq, Class<P> cls) throws GeneralSecurityException {
        String zzf = zzvq.zzf();
        return zzmt.zza().zza(zzf, cls).zzb(zzvq.zze());
    }

    public static <B, P> P zza(zzoz<B> zzoz, Class<P> cls) throws GeneralSecurityException {
        return zzoc.zza().zza(zzoz, cls);
    }

    static {
        Logger.getLogger(zzco.class.getName());
        new ConcurrentHashMap();
        HashSet hashSet = new HashSet();
        hashSet.add(zzbg.class);
        hashSet.add(zzbl.class);
        hashSet.add(zzcq.class);
        hashSet.add(zzbn.class);
        hashSet.add(zzbo.class);
        hashSet.add(zzci.class);
        hashSet.add(zzrq.class);
        hashSet.add(zzcm.class);
        hashSet.add(zzcl.class);
        Collections.unmodifiableSet(hashSet);
    }

    private zzco() {
    }
}
