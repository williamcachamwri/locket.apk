package com.google.ads.interactivemedia.v3.internal;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzkw {
    private final zzjj zza;
    private final String zzb;
    private final String zzc;
    private volatile Method zzd = null;
    private final Class[] zze;
    private final CountDownLatch zzf = new CountDownLatch(1);

    public zzkw(zzjj zzjj, String str, String str2, Class... clsArr) {
        this.zza = zzjj;
        this.zzb = str;
        this.zzc = str2;
        this.zze = clsArr;
        zzjj.zzk().submit(new zzkv(this));
    }

    static /* bridge */ /* synthetic */ void zzb(zzkw zzkw) {
        try {
            zzjj zzjj = zzkw.zza;
            Class loadClass = zzjj.zzi().loadClass(zzkw.zzc(zzjj.zzu(), zzkw.zzb));
            if (loadClass != null) {
                zzkw.zzd = loadClass.getMethod(zzkw.zzc(zzkw.zza.zzu(), zzkw.zzc), zzkw.zze);
                Method method = zzkw.zzd;
            }
        } catch (zzin | UnsupportedEncodingException | ClassNotFoundException | NoSuchMethodException | NullPointerException unused) {
        } catch (Throwable th) {
            zzkw.zzf.countDown();
            throw th;
        }
        zzkw.zzf.countDown();
    }

    private final String zzc(byte[] bArr, String str) throws zzin, UnsupportedEncodingException {
        return new String(this.zza.zze().zzb(bArr, str), "UTF-8");
    }

    public final Method zza() {
        if (this.zzd != null) {
            return this.zzd;
        }
        try {
            if (!this.zzf.await(2, TimeUnit.SECONDS)) {
                return null;
            }
            return this.zzd;
        } catch (InterruptedException unused) {
            return null;
        }
    }
}
