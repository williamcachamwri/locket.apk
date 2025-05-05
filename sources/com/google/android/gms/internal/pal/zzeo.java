package com.google.android.gms.internal.pal;

import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzeo extends zzfg {
    private final Map zzi;
    private final View zzj;

    public zzeo(zzdu zzdu, String str, String str2, zzr zzr, int i, int i2, Map map, View view) {
        super(zzdu, "RKC3mFMqGi7xOgQ7s39JMoZe9bnzGCFipcdUUf0vlgHDkBg7SvMkVmBGpwLs06ia", "8Xr1ilYJHo+oWZQAYAG91DIHBuqEmXK8yHtxL6KkyfU=", zzr, i, 85);
        this.zzi = map;
        this.zzj = view;
    }

    private final long zzc(int i) {
        Map map = this.zzi;
        Integer valueOf = Integer.valueOf(i);
        if (map.containsKey(valueOf)) {
            return ((Long) this.zzi.get(valueOf)).longValue();
        }
        return Long.MIN_VALUE;
    }

    /* access modifiers changed from: protected */
    public final void zza() throws IllegalAccessException, InvocationTargetException {
        long[] jArr = (long[]) this.zzf.invoke((Object) null, new Object[]{new long[]{zzc(1), zzc(2)}, this.zzb.zzb(), this.zzj});
        long j = jArr[0];
        this.zzi.put(1, Long.valueOf(jArr[1]));
        long j2 = jArr[2];
        this.zzi.put(2, Long.valueOf(jArr[3]));
        synchronized (this.zze) {
            this.zze.zzv(j);
            this.zze.zzu(j2);
        }
    }
}
