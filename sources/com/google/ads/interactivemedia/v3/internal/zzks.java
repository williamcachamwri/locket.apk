package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzks extends zzkx {
    private static volatile Long zzh;
    private static final Object zzi = new Object();

    public zzks(zzjj zzjj, String str, String str2, zzan zzan, int i, int i2) {
        super(zzjj, "tcR33IRFUbyN40xqCgABnI/9LsQindHOMS174YFQDeQf7OxZ+1/XT6alWsupn6gv", "9MshwtT+S3va52FSe6SYgVUb3QNeeYys8AoyRUVWlrg=", zzan, i, 33);
    }

    /* access modifiers changed from: protected */
    public final void zza() throws IllegalAccessException, InvocationTargetException {
        if (zzh == null) {
            synchronized (zzi) {
                if (zzh == null) {
                    zzh = (Long) this.zze.invoke((Object) null, new Object[0]);
                }
            }
        }
        synchronized (this.zzd) {
            this.zzd.zzV(zzh.longValue());
        }
    }
}
