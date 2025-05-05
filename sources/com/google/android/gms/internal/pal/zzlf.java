package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzlf {
    private static final Logger zza = Logger.getLogger(zzlf.class.getName());
    private static final AtomicReference zzb = new AtomicReference(new zzki());
    private static final ConcurrentMap zzc = new ConcurrentHashMap();
    private static final ConcurrentMap zzd = new ConcurrentHashMap();
    private static final ConcurrentMap zze = new ConcurrentHashMap();
    private static final ConcurrentMap zzf = new ConcurrentHashMap();
    private static final ConcurrentMap zzg = new ConcurrentHashMap();

    private zzlf() {
    }

    @Deprecated
    public static zzju zza(String str) throws GeneralSecurityException {
        if (str != null) {
            zzju zzju = (zzju) zze.get(str.toLowerCase(Locale.US));
            if (zzju != null) {
                return zzju;
            }
            String format = String.format("no catalogue found for %s. ", new Object[]{str});
            if (str.toLowerCase(Locale.US).startsWith("tinkaead")) {
                format = String.valueOf(format).concat("Maybe call AeadConfig.register().");
            }
            if (str.toLowerCase(Locale.US).startsWith("tinkdeterministicaead")) {
                format = String.valueOf(format).concat("Maybe call DeterministicAeadConfig.register().");
            } else if (str.toLowerCase(Locale.US).startsWith("tinkstreamingaead")) {
                format = String.valueOf(format).concat("Maybe call StreamingAeadConfig.register().");
            } else if (str.toLowerCase(Locale.US).startsWith("tinkhybriddecrypt") || str.toLowerCase(Locale.US).startsWith("tinkhybridencrypt")) {
                format = String.valueOf(format).concat("Maybe call HybridConfig.register().");
            } else if (str.toLowerCase(Locale.US).startsWith("tinkmac")) {
                format = String.valueOf(format).concat("Maybe call MacConfig.register().");
            } else if (str.toLowerCase(Locale.US).startsWith("tinkpublickeysign") || str.toLowerCase(Locale.US).startsWith("tinkpublickeyverify")) {
                format = String.valueOf(format).concat("Maybe call SignatureConfig.register().");
            } else if (str.toLowerCase(Locale.US).startsWith("tink")) {
                format = String.valueOf(format).concat("Maybe call TinkConfig.register().");
            }
            throw new GeneralSecurityException(format);
        }
        throw new IllegalArgumentException("catalogueName must be non-null.");
    }

    public static zzkb zzb(String str) throws GeneralSecurityException {
        return ((zzki) zzb.get()).zzb(str);
    }

    public static synchronized zzvo zzc(zzvt zzvt) throws GeneralSecurityException {
        zzvo zza2;
        synchronized (zzlf.class) {
            zzkb zzb2 = zzb(zzvt.zzf());
            if (((Boolean) zzd.get(zzvt.zzf())).booleanValue()) {
                zza2 = zzb2.zza(zzvt.zze());
            } else {
                throw new GeneralSecurityException("newKey-operation not permitted for key type ".concat(String.valueOf(zzvt.zzf())));
            }
        }
        return zza2;
    }

    public static synchronized zzaef zzd(zzvt zzvt) throws GeneralSecurityException {
        zzaef zzb2;
        synchronized (zzlf.class) {
            zzkb zzb3 = zzb(zzvt.zzf());
            if (((Boolean) zzd.get(zzvt.zzf())).booleanValue()) {
                zzb2 = zzb3.zzb(zzvt.zze());
            } else {
                throw new GeneralSecurityException("newKey-operation not permitted for key type ".concat(String.valueOf(zzvt.zzf())));
            }
        }
        return zzb2;
    }

    @Nullable
    public static Class zze(Class cls) {
        zzlc zzlc = (zzlc) zzf.get(cls);
        if (zzlc == null) {
            return null;
        }
        return zzlc.zza();
    }

    public static Object zzf(zzvo zzvo, Class cls) throws GeneralSecurityException {
        return zzg(zzvo.zzg(), zzvo.zzf(), cls);
    }

    public static Object zzg(String str, zzaby zzaby, Class cls) throws GeneralSecurityException {
        return ((zzki) zzb.get()).zza(str, cls).zzd(zzaby);
    }

    public static Object zzh(String str, zzaef zzaef, Class cls) throws GeneralSecurityException {
        return ((zzki) zzb.get()).zza(str, cls).zze(zzaef);
    }

    public static Object zzi(String str, byte[] bArr, Class cls) throws GeneralSecurityException {
        return zzg(str, zzaby.zzn(bArr), cls);
    }

    public static Object zzj(zzlb zzlb, Class cls) throws GeneralSecurityException {
        zzlc zzlc = (zzlc) zzf.get(cls);
        if (zzlc == null) {
            throw new GeneralSecurityException("No wrapper found for ".concat(String.valueOf(zzlb.zzc().getName())));
        } else if (zzlc.zza().equals(zzlb.zzc())) {
            return zzlc.zzc(zzlb);
        } else {
            String obj = zzlc.zza().toString();
            String obj2 = zzlb.zzc().toString();
            throw new GeneralSecurityException("Wrong input primitive class, expected " + obj + ", got " + obj2);
        }
    }

    static synchronized Map zzk() {
        Map unmodifiableMap;
        synchronized (zzlf.class) {
            unmodifiableMap = Collections.unmodifiableMap(zzg);
        }
        return unmodifiableMap;
    }

    public static synchronized void zzl(zzpr zzpr, zzpa zzpa, boolean z) throws GeneralSecurityException {
        synchronized (zzlf.class) {
            AtomicReference atomicReference = zzb;
            zzki zzki = new zzki((zzki) atomicReference.get());
            zzki.zzc(zzpr, zzpa);
            String zzd2 = zzpr.zzd();
            String zzd3 = zzpa.zzd();
            zzp(zzd2, zzpr.zza().zzc(), true);
            zzp(zzd3, Collections.emptyMap(), false);
            if (!((zzki) atomicReference.get()).zzf(zzd2)) {
                zzc.put(zzd2, new zzle(zzpr));
                zzq(zzpr.zzd(), zzpr.zza().zzc());
            }
            ConcurrentMap concurrentMap = zzd;
            concurrentMap.put(zzd2, true);
            concurrentMap.put(zzd3, false);
            atomicReference.set(zzki);
        }
    }

    public static synchronized void zzn(zzpa zzpa, boolean z) throws GeneralSecurityException {
        synchronized (zzlf.class) {
            AtomicReference atomicReference = zzb;
            zzki zzki = new zzki((zzki) atomicReference.get());
            zzki.zze(zzpa);
            String zzd2 = zzpa.zzd();
            zzp(zzd2, zzpa.zza().zzc(), true);
            if (!((zzki) atomicReference.get()).zzf(zzd2)) {
                zzc.put(zzd2, new zzle(zzpa));
                zzq(zzd2, zzpa.zza().zzc());
            }
            zzd.put(zzd2, true);
            atomicReference.set(zzki);
        }
    }

    public static synchronized void zzo(zzlc zzlc) throws GeneralSecurityException {
        synchronized (zzlf.class) {
            if (zzlc != null) {
                Class zzb2 = zzlc.zzb();
                ConcurrentMap concurrentMap = zzf;
                if (concurrentMap.containsKey(zzb2)) {
                    zzlc zzlc2 = (zzlc) concurrentMap.get(zzb2);
                    if (!zzlc.getClass().getName().equals(zzlc2.getClass().getName())) {
                        zza.logp(Level.WARNING, "com.google.crypto.tink.Registry", "registerPrimitiveWrapper", "Attempted overwrite of a registered PrimitiveWrapper for type ".concat(zzb2.toString()));
                        throw new GeneralSecurityException(String.format("PrimitiveWrapper for primitive (%s) is already registered to be %s, cannot be re-registered with %s", new Object[]{zzb2.getName(), zzlc2.getClass().getName(), zzlc.getClass().getName()}));
                    }
                }
                concurrentMap.put(zzb2, zzlc);
            } else {
                throw new IllegalArgumentException("wrapper must be non-null");
            }
        }
    }

    private static synchronized void zzp(String str, Map map, boolean z) throws GeneralSecurityException {
        synchronized (zzlf.class) {
            if (z) {
                ConcurrentMap concurrentMap = zzd;
                if (concurrentMap.containsKey(str)) {
                    if (!((Boolean) concurrentMap.get(str)).booleanValue()) {
                        throw new GeneralSecurityException("New keys are already disallowed for key type ".concat(str));
                    }
                }
                if (((zzki) zzb.get()).zzf(str)) {
                    for (Map.Entry entry : map.entrySet()) {
                        if (!zzg.containsKey(entry.getKey())) {
                            throw new GeneralSecurityException("Attempted to register a new key template " + ((String) entry.getKey()) + " from an existing key manager of type " + str);
                        }
                    }
                } else {
                    for (Map.Entry entry2 : map.entrySet()) {
                        if (zzg.containsKey(entry2.getKey())) {
                            throw new GeneralSecurityException("Attempted overwrite of a registered key template ".concat(String.valueOf((String) entry2.getKey())));
                        }
                    }
                }
            }
        }
    }

    /* JADX WARNING: type inference failed for: r3v2, types: [com.google.android.gms.internal.pal.zzaef, java.lang.Object] */
    private static void zzq(String str, Map map) {
        for (Map.Entry entry : map.entrySet()) {
            zzg.put((String) entry.getKey(), zzkk.zzd(str, ((zzoy) entry.getValue()).zza.zzas(), ((zzoy) entry.getValue()).zzb));
        }
    }

    public static synchronized void zzm(zzkb zzkb, boolean z) throws GeneralSecurityException {
        synchronized (zzlf.class) {
            if (zzkb != null) {
                try {
                    AtomicReference atomicReference = zzb;
                    zzki zzki = new zzki((zzki) atomicReference.get());
                    zzki.zzd(zzkb);
                    if (zzna.zza(1)) {
                        String zzf2 = zzkb.zzf();
                        zzp(zzf2, Collections.emptyMap(), z);
                        zzd.put(zzf2, Boolean.valueOf(z));
                        atomicReference.set(zzki);
                    } else {
                        throw new GeneralSecurityException("Registering key managers is not supported in FIPS mode");
                    }
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                throw new IllegalArgumentException("key manager must be non-null.");
            }
        }
    }
}
