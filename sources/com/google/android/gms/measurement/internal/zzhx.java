package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.lang.Thread;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzhx implements Thread.UncaughtExceptionHandler {
    private final String zza;
    private final /* synthetic */ zzhv zzb;

    public zzhx(zzhv zzhv, String str) {
        this.zzb = zzhv;
        Preconditions.checkNotNull(str);
        this.zza = str;
    }

    public final synchronized void uncaughtException(Thread thread, Throwable th) {
        this.zzb.zzj().zzg().zza(this.zza, th);
    }
}
