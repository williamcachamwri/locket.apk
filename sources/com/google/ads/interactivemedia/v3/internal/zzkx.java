package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzkx implements Callable {
    protected final zzjj zza;
    protected final String zzb;
    protected final String zzc;
    protected final zzan zzd;
    protected Method zze;
    protected final int zzf;
    protected final int zzg;

    public zzkx(zzjj zzjj, String str, String str2, zzan zzan, int i, int i2) {
        getClass().getSimpleName();
        this.zza = zzjj;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = zzan;
        this.zzf = i;
        this.zzg = i2;
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
            Method zzj = this.zza.zzj(this.zzb, this.zzc);
            this.zze = zzj;
            if (zzj == null) {
                return null;
            }
            zza();
            zzhz zzd2 = this.zza.zzd();
            if (zzd2 == null || (i = this.zzf) == Integer.MIN_VALUE) {
                return null;
            }
            zzd2.zzc(this.zzg, i, (System.nanoTime() - nanoTime) / 1000, (String) null, (Exception) null);
            return null;
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }
}
