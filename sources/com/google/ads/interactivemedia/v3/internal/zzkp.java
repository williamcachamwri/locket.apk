package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzkp extends zzkx {
    private final StackTraceElement[] zzh;

    public zzkp(zzjj zzjj, String str, String str2, zzan zzan, int i, int i2, StackTraceElement[] stackTraceElementArr) {
        super(zzjj, "Tr7fGRhozrcGWgreSsweTKh/4NOM+Jnt9yuIucqZU1XFuQj1cofQtHqK781u41Fk", "JHli6WI5R8sw7EkxbHsVjy9IYG7FikIpacvBlSmCeKs=", zzan, i, 45);
        this.zzh = stackTraceElementArr;
    }

    /* access modifiers changed from: protected */
    public final void zza() throws IllegalAccessException, InvocationTargetException {
        StackTraceElement[] stackTraceElementArr = this.zzh;
        if (stackTraceElementArr != null) {
            zzja zzja = new zzja((String) this.zze.invoke((Object) null, new Object[]{stackTraceElementArr}));
            synchronized (this.zzd) {
                this.zzd.zzF(zzja.zza.longValue());
                if (zzja.zzb.booleanValue()) {
                    zzan zzan = this.zzd;
                    int i = 1;
                    if (true != zzja.zzc.booleanValue()) {
                        i = 2;
                    }
                    zzan.zzac(i);
                } else {
                    this.zzd.zzac(3);
                }
            }
        }
    }
}
