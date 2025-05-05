package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzjz extends zzkx {
    private static volatile Long zzh;
    private static final Object zzi = new Object();

    public zzjz(zzjj zzjj, String str, String str2, zzan zzan, int i, int i2) {
        super(zzjj, "cjSsFcxVax6EwbsuZafYPPxAHkUT7a2SIb/oCbet6iQURCCVL9GhJgHBmqsITnDG", "2GoTKU7iwzLx50MI3wGMB3wuOh4ehkqUJCToqX/EZgk=", zzan, i, 44);
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
            this.zzd.zzo(zzh.longValue());
        }
    }
}
