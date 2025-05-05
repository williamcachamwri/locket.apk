package com.google.android.gms.internal.pal;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzek extends zzfg {
    private static volatile Long zzi;
    private static final Object zzj = new Object();

    public zzek(zzdu zzdu, String str, String str2, zzr zzr, int i, int i2) {
        super(zzdu, "XFxH1z0dBuMDP7aWA+P/3WKwW9qr8sC2ASjEfciaKHfSLryjCNl4cmJgfsh2Tylb", "r0MNv9zqwvoUwASL1pBJjOA1OkDa8Kcs5NaA6VOkJEI=", zzr, i, 44);
    }

    /* access modifiers changed from: protected */
    public final void zza() throws IllegalAccessException, InvocationTargetException {
        if (zzi == null) {
            synchronized (zzj) {
                if (zzi == null) {
                    zzi = (Long) this.zzf.invoke((Object) null, new Object[0]);
                }
            }
        }
        synchronized (this.zze) {
            this.zze.zzo(zzi.longValue());
        }
    }
}
