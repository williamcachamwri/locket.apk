package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzko extends zzkx {
    private final boolean zzh;

    public zzko(zzjj zzjj, String str, String str2, zzan zzan, int i, int i2) {
        super(zzjj, "8Ypoat4hJmb20CWBS2vm1Bwj5rMbit3AiLM5WASq9kLQGiCpUdBOaxuIoDBxCVKn", "vUHFjnocTkwTSea4TN+zEmhwStt81G8dz02qs1gtO4U=", zzan, i, 61);
        this.zzh = zzjj.zzs();
    }

    /* access modifiers changed from: protected */
    public final void zza() throws IllegalAccessException, InvocationTargetException {
        long longValue = ((Long) this.zze.invoke((Object) null, new Object[]{this.zza.zzb(), Boolean.valueOf(this.zzh)})).longValue();
        synchronized (this.zzd) {
            this.zzd.zzE(longValue);
        }
    }
}
