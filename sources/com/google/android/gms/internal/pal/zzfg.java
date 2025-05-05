package com.google.android.gms.internal.pal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public abstract class zzfg implements Callable {
    protected final String zza = getClass().getSimpleName();
    protected final zzdu zzb;
    protected final String zzc;
    protected final String zzd;
    protected final zzr zze;
    protected Method zzf;
    protected final int zzg;
    protected final int zzh;

    public zzfg(zzdu zzdu, String str, String str2, zzr zzr, int i, int i2) {
        this.zzb = zzdu;
        this.zzc = str;
        this.zzd = str2;
        this.zze = zzr;
        this.zzg = i;
        this.zzh = i2;
    }

    public /* bridge */ /* synthetic */ Object call() throws Exception {
        zze();
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract void zza() throws IllegalAccessException, InvocationTargetException;

    public Void zze() throws Exception {
        int i;
        try {
            long nanoTime = System.nanoTime();
            Method zzj = this.zzb.zzj(this.zzc, this.zzd);
            this.zzf = zzj;
            if (zzj == null) {
                return null;
            }
            zza();
            zzcp zzd2 = this.zzb.zzd();
            if (!(zzd2 == null || (i = this.zzg) == Integer.MIN_VALUE)) {
                zzd2.zzc(this.zzh, i, (System.nanoTime() - nanoTime) / 1000, (String) null, (Exception) null);
            }
            return null;
        } catch (IllegalAccessException | InvocationTargetException unused) {
        }
    }
}
