package com.google.android.gms.internal.pal;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzey extends zzfg {
    private final StackTraceElement[] zzi;

    public zzey(zzdu zzdu, String str, String str2, zzr zzr, int i, int i2, StackTraceElement[] stackTraceElementArr) {
        super(zzdu, "d2tnKFzXPwiZyQGi+81r0jKuUmc/wF2bs8mf3rZLUgisIeswnimQDm/skPYjpEo4", "e/DvqiTz4SkFtBEBn/3V8Pr2h2slHO4xuLOBAItCJ4w=", zzr, i, 45);
        this.zzi = stackTraceElementArr;
    }

    /* access modifiers changed from: protected */
    public final void zza() throws IllegalAccessException, InvocationTargetException {
        StackTraceElement[] stackTraceElementArr = this.zzi;
        if (stackTraceElementArr != null) {
            zzdn zzdn = new zzdn((String) this.zzf.invoke((Object) null, new Object[]{stackTraceElementArr}));
            synchronized (this.zze) {
                this.zze.zzG(zzdn.zza.longValue());
                if (zzdn.zzb.booleanValue()) {
                    zzr zzr = this.zze;
                    int i = 1;
                    if (true != zzdn.zzc.booleanValue()) {
                        i = 2;
                    }
                    zzr.zzad(i);
                } else {
                    this.zze.zzad(3);
                }
            }
        }
    }
}
