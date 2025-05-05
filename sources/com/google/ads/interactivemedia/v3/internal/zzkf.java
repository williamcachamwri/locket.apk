package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzkf extends zzkx {
    private static volatile Long zzh;
    private static final Object zzi = new Object();

    public zzkf(zzjj zzjj, String str, String str2, zzan zzan, int i, int i2) {
        super(zzjj, "Yg7XTmV44rTPXCawjL+LLnad7Fgn9Aqg1oEqF/5ILJmBvjYFNR2q4oPr2MLzmzFr", "OmskNefI5KGTHj+9JnPSsNTlAsLQMDTHxEai8PMBc7Y=", zzan, i, 22);
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
            this.zzd.zzy(zzh.longValue());
        }
    }
}
