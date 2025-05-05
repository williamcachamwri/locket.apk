package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzka extends zzkx {
    private final long zzh;

    public zzka(zzjj zzjj, String str, String str2, zzan zzan, long j, int i, int i2) {
        super(zzjj, "M15xBiwjCN96Wfw63Rr/fs0Y0GhtAeawHW/RMMdlzRuKFoPsxc8VRKvehmju67Mq", "pi9ztiAbRuPTirdH6Q55wZRVdhOPRi3ZtgfWyCi26hI=", zzan, i, 25);
        this.zzh = j;
    }

    /* access modifiers changed from: protected */
    public final void zza() throws IllegalAccessException, InvocationTargetException {
        long longValue = ((Long) this.zze.invoke((Object) null, new Object[0])).longValue();
        synchronized (this.zzd) {
            this.zzd.zzt(longValue);
            long j = this.zzh;
            if (j != 0) {
                this.zzd.zzT(longValue - j);
                this.zzd.zzU(this.zzh);
            }
        }
    }
}
