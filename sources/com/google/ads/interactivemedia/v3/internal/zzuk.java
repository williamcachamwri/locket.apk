package com.google.ads.interactivemedia.v3.internal;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzuk extends zzum {
    @SafeVarargs
    public static zzuj zza(zzuu... zzuuArr) {
        return new zzuj(false, zzrm.zzl(zzuuArr), (zzui) null);
    }

    public static zzuu zzb(Object obj) {
        if (obj == null) {
            return zzun.zza;
        }
        return new zzun(obj);
    }

    public static zzuu zzc(zzuu zzuu, zzpz zzpz, Executor executor) {
        zztn zztn = new zztn(zzuu, zzpz);
        zzuu.zzo(zztn, zzvb.zzc(executor, zztn));
        return zztn;
    }

    public static Object zzd(Future future) throws ExecutionException {
        Object obj;
        if (future.isDone()) {
            boolean z = false;
            while (true) {
                try {
                    obj = future.get();
                    break;
                } catch (InterruptedException unused) {
                    z = true;
                } catch (Throwable th) {
                    if (z) {
                        Thread.currentThread().interrupt();
                    }
                    throw th;
                }
            }
            if (z) {
                Thread.currentThread().interrupt();
            }
            return obj;
        }
        throw new IllegalStateException(zzqm.zzb("Future was expected to be done: %s", future));
    }

    public static void zze(zzuu zzuu, zzug zzug, Executor executor) {
        zzuu.zzo(new zzuh(zzuu, zzug), executor);
    }

    public static zzuu zzf(zzuu zzuu, zzgn zzgn, Executor executor) {
        int i = zzto.zze;
        executor.getClass();
        zztm zztm = new zztm(zzuu, zzgn);
        zzuu.zzo(zztm, zzvb.zzc(executor, zztm));
        return zztm;
    }
}
