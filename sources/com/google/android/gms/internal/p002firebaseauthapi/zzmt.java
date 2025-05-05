package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzil;
import java.security.GeneralSecurityException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmt  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzmt {
    private static final Logger zza = Logger.getLogger(zzmt.class.getName());
    private static final zzmt zzb = new zzmt();
    private ConcurrentMap<String, zzbs<?>> zzc = new ConcurrentHashMap();
    private ConcurrentMap<String, Boolean> zzd = new ConcurrentHashMap();

    public final <P> zzbs<P> zza(String str, Class<P> cls) throws GeneralSecurityException {
        zzbs<?> zzc2 = zzc(str);
        if (zzc2.zza().equals(cls)) {
            return zzc2;
        }
        String name = cls.getName();
        String valueOf = String.valueOf(zzc2.getClass());
        throw new GeneralSecurityException("Primitive type " + name + " not supported by key manager of type " + valueOf + ", which only supports: " + String.valueOf(zzc2.zza()));
    }

    private final synchronized zzbs<?> zzc(String str) throws GeneralSecurityException {
        if (this.zzc.containsKey(str)) {
        } else {
            throw new GeneralSecurityException("No key manager found for key type " + str);
        }
        return (zzbs) this.zzc.get(str);
    }

    public final zzbs<?> zza(String str) throws GeneralSecurityException {
        return zzc(str);
    }

    public static zzmt zza() {
        return zzb;
    }

    private final synchronized void zza(zzbs<?> zzbs, boolean z, boolean z2) throws GeneralSecurityException {
        String zzb2 = zzbs.zzb();
        if (z2 && this.zzd.containsKey(zzb2)) {
            if (!((Boolean) this.zzd.get(zzb2)).booleanValue()) {
                throw new GeneralSecurityException("New keys are already disallowed for key type " + zzb2);
            }
        }
        zzbs zzbs2 = (zzbs) this.zzc.get(zzb2);
        if (zzbs2 != null) {
            if (!zzbs2.getClass().equals(zzbs.getClass())) {
                zza.logp(Level.WARNING, "com.google.crypto.tink.internal.KeyManagerRegistry", "insertKeyManager", "Attempted overwrite of a registered key manager for key type " + zzb2);
                throw new GeneralSecurityException(String.format("typeUrl (%s) is already registered with %s, cannot be re-registered with %s", new Object[]{zzb2, zzbs2.getClass().getName(), zzbs.getClass().getName()}));
            }
        }
        this.zzc.putIfAbsent(zzb2, zzbs);
        this.zzd.put(zzb2, Boolean.valueOf(z2));
    }

    public final synchronized <P> void zza(zzbs<P> zzbs, boolean z) throws GeneralSecurityException {
        zza(zzbs, zzil.zza.ALGORITHM_NOT_FIPS, z);
    }

    public final synchronized <P> void zza(zzbs<P> zzbs, zzil.zza zza2, boolean z) throws GeneralSecurityException {
        if (zza2.zza()) {
            zza((zzbs<?>) zzbs, false, z);
        } else {
            throw new GeneralSecurityException("Cannot register key manager: FIPS compatibility insufficient");
        }
    }

    public final boolean zzb(String str) {
        return ((Boolean) this.zzd.get(str)).booleanValue();
    }
}
