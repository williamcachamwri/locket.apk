package com.google.android.recaptcha.internal;

import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzbm {
    public static final /* synthetic */ int zza = 0;
    private static final ConcurrentHashMap zzb = new ConcurrentHashMap();

    public static final void zza(int i, long j) {
        ConcurrentHashMap concurrentHashMap = zzb;
        Integer valueOf = Integer.valueOf(i);
        Object obj = concurrentHashMap.get(valueOf);
        if (obj == null) {
            obj = new zzbl();
        }
        zzbl zzbl = (zzbl) obj;
        zzbl.zzg(zzbl.zzb() + 1);
        zzbl.zzf(zzbl.zzd() + j);
        zzbl.zze(Math.max(j, zzbl.zzc()));
        concurrentHashMap.put(valueOf, zzbl);
    }
}
