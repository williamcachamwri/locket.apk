package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzki {
    private static final Logger zza = Logger.getLogger(zzki.class.getName());
    private final ConcurrentMap zzb;

    zzki() {
        this.zzb = new ConcurrentHashMap();
    }

    private final synchronized zzkh zzg(String str) throws GeneralSecurityException {
        if (this.zzb.containsKey(str)) {
        } else {
            throw new GeneralSecurityException("No key manager found for key type ".concat(String.valueOf(str)));
        }
        return (zzkh) this.zzb.get(str);
    }

    private final synchronized void zzh(zzkh zzkh, boolean z) throws GeneralSecurityException {
        String zzf = zzkh.zzb().zzf();
        zzkh zzkh2 = (zzkh) this.zzb.get(zzf);
        if (zzkh2 != null) {
            if (!zzkh2.zzc().equals(zzkh.zzc())) {
                zza.logp(Level.WARNING, "com.google.crypto.tink.KeyManagerRegistry", "registerKeyManagerContainer", "Attempted overwrite of a registered key manager for key type ".concat(zzf));
                throw new GeneralSecurityException(String.format("typeUrl (%s) is already registered with %s, cannot be re-registered with %s", new Object[]{zzf, zzkh2.zzc().getName(), zzkh.zzc().getName()}));
            }
        }
        if (!z) {
            this.zzb.putIfAbsent(zzf, zzkh);
        } else {
            this.zzb.put(zzf, zzkh);
        }
    }

    /* access modifiers changed from: package-private */
    public final zzkb zza(String str, Class cls) throws GeneralSecurityException {
        zzkh zzg = zzg(str);
        if (zzg.zze().contains(cls)) {
            return zzg.zza(cls);
        }
        String name = cls.getName();
        String valueOf = String.valueOf(zzg.zzc());
        Set<Class> zze = zzg.zze();
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Class cls2 : zze) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(cls2.getCanonicalName());
            z = false;
        }
        String sb2 = sb.toString();
        throw new GeneralSecurityException("Primitive type " + name + " not supported by key manager of type " + valueOf + ", supported primitives: " + sb2);
    }

    /* access modifiers changed from: package-private */
    public final zzkb zzb(String str) throws GeneralSecurityException {
        return zzg(str).zzb();
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zzc(zzpr zzpr, zzpa zzpa) throws GeneralSecurityException {
        Class zzd;
        int zzf = zzpa.zzf();
        if (!zzna.zza(1)) {
            String valueOf = String.valueOf(zzpr.getClass());
            throw new GeneralSecurityException("failed to register key manager " + valueOf + " as it is not FIPS compatible.");
        } else if (zzna.zza(zzf)) {
            String zzd2 = zzpr.zzd();
            String zzd3 = zzpa.zzd();
            if (!(!this.zzb.containsKey(zzd2) || ((zzkh) this.zzb.get(zzd2)).zzd() == null || (zzd = ((zzkh) this.zzb.get(zzd2)).zzd()) == null)) {
                if (!zzd.getName().equals(zzpa.getClass().getName())) {
                    Logger logger = zza;
                    Level level = Level.WARNING;
                    logger.logp(level, "com.google.crypto.tink.KeyManagerRegistry", "registerAsymmetricKeyManagers", "Attempted overwrite of a registered key manager for key type " + zzd2 + " with inconsistent public key type " + zzd3);
                    throw new GeneralSecurityException(String.format("public key manager corresponding to %s is already registered with %s, cannot be re-registered with %s", new Object[]{zzpr.getClass().getName(), zzd.getName(), zzpa.getClass().getName()}));
                }
            }
            zzh(new zzkg(zzpr, zzpa), true);
            zzh(new zzkf(zzpa), false);
        } else {
            String valueOf2 = String.valueOf(zzpa.getClass());
            throw new GeneralSecurityException("failed to register key manager " + valueOf2 + " as it is not FIPS compatible.");
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zzd(zzkb zzkb) throws GeneralSecurityException {
        if (zzna.zza(1)) {
            zzh(new zzke(zzkb), false);
        } else {
            throw new GeneralSecurityException("Registering key managers is not supported in FIPS mode");
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zze(zzpa zzpa) throws GeneralSecurityException {
        if (zzna.zza(zzpa.zzf())) {
            zzh(new zzkf(zzpa), false);
        } else {
            String valueOf = String.valueOf(zzpa.getClass());
            throw new GeneralSecurityException("failed to register key manager " + valueOf + " as it is not FIPS compatible.");
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzf(String str) {
        return this.zzb.containsKey(str);
    }

    zzki(zzki zzki) {
        this.zzb = new ConcurrentHashMap(zzki.zzb);
    }
}
