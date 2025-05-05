package com.google.android.gms.internal.pal;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzff {
    protected static final String zza = "zzff";
    private final zzdu zzb;
    private final String zzc;
    private final String zzd;
    private volatile Method zze = null;
    private final Class[] zzf;
    private final CountDownLatch zzg = new CountDownLatch(1);

    public zzff(zzdu zzdu, String str, String str2, Class... clsArr) {
        this.zzb = zzdu;
        this.zzc = str;
        this.zzd = str2;
        this.zzf = clsArr;
        zzdu.zzk().submit(new zzfe(this));
    }

    static /* bridge */ /* synthetic */ void zzb(zzff zzff) {
        CountDownLatch countDownLatch;
        try {
            zzdu zzdu = zzff.zzb;
            Class loadClass = zzdu.zzi().loadClass(zzff.zzc(zzdu.zzu(), zzff.zzc));
            if (loadClass == null) {
                countDownLatch = zzff.zzg;
            } else {
                zzff.zze = loadClass.getMethod(zzff.zzc(zzff.zzb.zzu(), zzff.zzd), zzff.zzf);
                if (zzff.zze == null) {
                    countDownLatch = zzff.zzg;
                }
                countDownLatch = zzff.zzg;
            }
        } catch (zzda | UnsupportedEncodingException | ClassNotFoundException | NoSuchMethodException unused) {
        } catch (NullPointerException unused2) {
            countDownLatch = zzff.zzg;
        } catch (Throwable th) {
            zzff.zzg.countDown();
            throw th;
        }
        countDownLatch.countDown();
    }

    private final String zzc(byte[] bArr, String str) throws zzda, UnsupportedEncodingException {
        return new String(this.zzb.zze().zzb(bArr, str), "UTF-8");
    }

    public final Method zza() {
        if (this.zze != null) {
            return this.zze;
        }
        try {
            if (!this.zzg.await(2, TimeUnit.SECONDS)) {
                return null;
            }
            return this.zze;
        } catch (InterruptedException unused) {
            return null;
        }
    }
}
