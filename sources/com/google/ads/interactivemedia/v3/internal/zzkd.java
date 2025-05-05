package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzkd extends zzkx {
    private final zzjk zzh;

    public zzkd(zzjj zzjj, String str, String str2, zzan zzan, int i, int i2, zzjk zzjk) {
        super(zzjj, "MYxgDIrh+gy86kN1XY6ylXIPeNjoW1IMoZZuWIGwGNUEplJDYFOwysCZ/m/vn5Hd", "SSWkXiA6wy65+39wH1IAu/x4WRBY+euODs95Kr/RwrI=", zzan, i, 85);
        this.zzh = zzjk;
    }

    /* access modifiers changed from: protected */
    public final void zza() throws IllegalAccessException, InvocationTargetException {
        long[] jArr = (long[]) this.zze.invoke((Object) null, new Object[]{Long.valueOf(this.zzh.zzd()), Long.valueOf(this.zzh.zzh()), Long.valueOf(this.zzh.zzb()), Long.valueOf(this.zzh.zzf())});
        synchronized (this.zzd) {
            this.zzd.zzv(jArr[0]);
            this.zzd.zzu(jArr[1]);
        }
    }
}
