package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzke extends zzkx {
    private final Map zzh;
    private final View zzi;
    private final Context zzj;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzke(zzjj zzjj, String str, String str2, zzan zzan, int i, int i2, Map map, View view, Context context) {
        super(zzjj, "CySZ92smVj1VEbgo+eF7z9VJhaK3iCCfIVA3l/ENPWde309cuYGU/6wJ84OShHXw", "6FIIxFiGgkuuKEaa3ojkyxNzol7dOTz9phQiHKYrz68=", zzan, i, 85);
        this.zzh = map;
        this.zzi = view;
        this.zzj = context;
    }

    private final long zzc(int i) {
        Map map = this.zzh;
        Integer valueOf = Integer.valueOf(i);
        if (map.containsKey(valueOf)) {
            return ((Long) this.zzh.get(valueOf)).longValue();
        }
        return Long.MIN_VALUE;
    }

    /* access modifiers changed from: protected */
    public final void zza() throws IllegalAccessException, InvocationTargetException {
        long[] jArr = {zzc(1), zzc(2)};
        Context context = this.zzj;
        if (context == null) {
            context = this.zza.zzb();
        }
        long[] jArr2 = (long[]) this.zze.invoke((Object) null, new Object[]{jArr, context, this.zzi});
        long j = jArr2[0];
        this.zzh.put(1, Long.valueOf(jArr2[1]));
        long j2 = jArr2[2];
        this.zzh.put(2, Long.valueOf(jArr2[3]));
        synchronized (this.zzd) {
            this.zzd.zzv(j);
            this.zzd.zzu(j2);
        }
    }
}
