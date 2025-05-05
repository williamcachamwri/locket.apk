package com.google.ads.interactivemedia.v3.internal;

import java.lang.Thread;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzve implements ThreadFactory {
    final /* synthetic */ ThreadFactory zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ AtomicLong zzc;

    zzve(ThreadFactory threadFactory, String str, AtomicLong atomicLong, Boolean bool, Integer num, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.zza = threadFactory;
        this.zzb = str;
        this.zzc = atomicLong;
    }

    public final Thread newThread(Runnable runnable) {
        Thread newThread = this.zza.newThread(runnable);
        Objects.requireNonNull(newThread);
        String str = this.zzb;
        if (str != null) {
            newThread.setName(String.format(Locale.ROOT, str, new Object[]{Long.valueOf(((AtomicLong) Objects.requireNonNull(this.zzc)).getAndIncrement())}));
        }
        return newThread;
    }
}
