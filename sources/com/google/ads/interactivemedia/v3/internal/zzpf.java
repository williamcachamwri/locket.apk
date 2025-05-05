package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.os.Bundle;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.security.GeneralSecurityException;
import java.util.HashMap;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzpf {
    private static final HashMap zza = new HashMap();
    private final Context zzb;
    private final zzpg zzc;
    private final zznt zzd;
    private final zzno zze;
    private zzou zzf;
    private final Object zzg = new Object();

    public zzpf(Context context, zzpg zzpg, zznt zznt, zzno zzno) {
        this.zzb = context;
        this.zzc = zzpg;
        this.zzd = zznt;
        this.zze = zzno;
    }

    private final synchronized Class zzd(zzov zzov) throws zzpe {
        String zzj = zzov.zza().zzj();
        HashMap hashMap = zza;
        Class cls = (Class) hashMap.get(zzj);
        if (cls != null) {
            return cls;
        }
        try {
            if (this.zze.zza(zzov.zzc())) {
                File zzb2 = zzov.zzb();
                if (!zzb2.exists()) {
                    zzb2.mkdirs();
                }
                Class loadClass = new DexClassLoader(zzov.zzc().getAbsolutePath(), zzb2.getAbsolutePath(), (String) null, this.zzb.getClassLoader()).loadClass("com.google.ccc.abuse.droidguard.DroidGuard");
                hashMap.put(zzj, loadClass);
                return loadClass;
            }
            throw new zzpe(2026, "VM did not pass signature verification");
        } catch (GeneralSecurityException e) {
            throw new zzpe(2026, (Throwable) e);
        } catch (ClassNotFoundException | IllegalArgumentException | SecurityException e2) {
            throw new zzpe(2008, e2);
        }
    }

    public final zznw zza() {
        zzou zzou;
        synchronized (this.zzg) {
            zzou = this.zzf;
        }
        return zzou;
    }

    public final zzov zzb() {
        synchronized (this.zzg) {
            zzou zzou = this.zzf;
            if (zzou == null) {
                return null;
            }
            zzov zzf2 = zzou.zzf();
            return zzf2;
        }
    }

    public final boolean zzc(zzov zzov) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            Class zzd2 = zzd(zzov);
            zzou zzou = new zzou(zzd2.getDeclaredConstructor(new Class[]{Context.class, String.class, byte[].class, Object.class, Bundle.class, Integer.TYPE}).newInstance(new Object[]{this.zzb, "msa-r", zzov.zze(), null, new Bundle(), 2}), zzov, this.zzc, this.zzd);
            if (zzou.zzh()) {
                int zze2 = zzou.zze();
                if (zze2 == 0) {
                    synchronized (this.zzg) {
                        zzou zzou2 = this.zzf;
                        if (zzou2 != null) {
                            try {
                                zzou2.zzg();
                            } catch (zzpe e) {
                                this.zzd.zzc(e.zza(), -1, e);
                            }
                        }
                        this.zzf = zzou;
                    }
                    this.zzd.zzd(3000, System.currentTimeMillis() - currentTimeMillis);
                    return true;
                }
                throw new zzpe(4001, "ci: " + zze2);
            }
            throw new zzpe(4000, "init failed");
        } catch (Exception e2) {
            throw new zzpe(2004, (Throwable) e2);
        } catch (zzpe e3) {
            this.zzd.zzc(e3.zza(), System.currentTimeMillis() - currentTimeMillis, e3);
            return false;
        } catch (Exception e4) {
            this.zzd.zzc(4010, System.currentTimeMillis() - currentTimeMillis, e4);
            return false;
        }
    }
}
