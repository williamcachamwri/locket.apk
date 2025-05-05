package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdg;
import java.lang.Thread;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzhw<V> extends FutureTask<V> implements Comparable<zzhw<V>> {
    final boolean zza;
    private final long zzb;
    private final String zzc;
    private final /* synthetic */ zzhv zzd;

    public final /* synthetic */ int compareTo(Object obj) {
        zzhw zzhw = (zzhw) obj;
        boolean z = this.zza;
        if (z != zzhw.zza) {
            return z ? -1 : 1;
        }
        long j = this.zzb;
        long j2 = zzhw.zzb;
        if (j < j2) {
            return -1;
        }
        if (j > j2) {
            return 1;
        }
        this.zzd.zzj().zzn().zza("Two tasks share the same index. index", Long.valueOf(this.zzb));
        return 0;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzhw(zzhv zzhv, Runnable runnable, boolean z, String str) {
        super(zzdg.zza().zza(runnable), (Object) null);
        this.zzd = zzhv;
        Preconditions.checkNotNull(str);
        long andIncrement = zzhv.zza.getAndIncrement();
        this.zzb = andIncrement;
        this.zzc = str;
        this.zza = z;
        if (andIncrement == Long.MAX_VALUE) {
            zzhv.zzj().zzg().zza("Tasks index overflow");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzhw(zzhv zzhv, Callable<V> callable, boolean z, String str) {
        super(zzdg.zza().zza(callable));
        this.zzd = zzhv;
        Preconditions.checkNotNull(str);
        long andIncrement = zzhv.zza.getAndIncrement();
        this.zzb = andIncrement;
        this.zzc = str;
        this.zza = z;
        if (andIncrement == Long.MAX_VALUE) {
            zzhv.zzj().zzg().zza("Tasks index overflow");
        }
    }

    /* access modifiers changed from: protected */
    public final void setException(Throwable th) {
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;
        this.zzd.zzj().zzg().zza(this.zzc, th);
        if ((th instanceof zzhu) && (defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()) != null) {
            defaultUncaughtExceptionHandler.uncaughtException(Thread.currentThread(), th);
        }
        super.setException(th);
    }
}
