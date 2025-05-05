package com.google.android.gms.internal.pal;

import android.os.ConditionVariable;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzcp {
    protected static volatile zzhp zza = null;
    /* access modifiers changed from: private */
    public static final ConditionVariable zzc = new ConditionVariable();
    private static volatile Random zzd = null;
    protected volatile Boolean zzb;
    /* access modifiers changed from: private */
    public final zzdu zze;

    public zzcp(zzdu zzdu) {
        this.zze = zzdu;
        zzdu.zzk().execute(new zzco(this));
    }

    public static final int zzd() {
        try {
            return ThreadLocalRandom.current().nextInt();
        } catch (RuntimeException unused) {
            return zze().nextInt();
        }
    }

    private static Random zze() {
        if (zzd == null) {
            synchronized (zzcp.class) {
                if (zzd == null) {
                    zzd = new Random();
                }
            }
        }
        return zzd;
    }

    public final void zzc(int i, int i2, long j, String str, Exception exc) {
        try {
            zzc.block();
            if (this.zzb.booleanValue() && zza != null) {
                zzb zza2 = zzf.zza();
                zza2.zza(this.zze.zza.getPackageName());
                zza2.zzd(j);
                if (str != null) {
                    zza2.zzb(str);
                }
                if (exc != null) {
                    StringWriter stringWriter = new StringWriter();
                    exc.printStackTrace(new PrintWriter(stringWriter));
                    zza2.zze(stringWriter.toString());
                    zza2.zzc(exc.getClass().getName());
                }
                zzho zza3 = zza.zza(((zzf) zza2.zzan()).zzas());
                zza3.zza(i);
                if (i2 != -1) {
                    zza3.zzb(i2);
                }
                zza3.zzc();
            }
        } catch (Exception unused) {
        }
    }
}
