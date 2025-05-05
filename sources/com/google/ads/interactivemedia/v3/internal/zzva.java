package com.google.ads.interactivemedia.v3.internal;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzva extends zzux implements ScheduledExecutorService, zzuv {
    final ScheduledExecutorService zza;

    zzva(ScheduledExecutorService scheduledExecutorService) {
        super(scheduledExecutorService);
        scheduledExecutorService.getClass();
        ScheduledExecutorService scheduledExecutorService2 = scheduledExecutorService;
        this.zza = scheduledExecutorService;
    }

    public final /* bridge */ /* synthetic */ ScheduledFuture schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        ScheduledExecutorService scheduledExecutorService = this.zza;
        zzvh zzs = zzvh.zzs(runnable, (Object) null);
        return new zzuy(zzs, scheduledExecutorService.schedule(zzs, j, timeUnit));
    }

    public final /* bridge */ /* synthetic */ ScheduledFuture scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        zzuz zzuz = new zzuz(runnable);
        return new zzuy(zzuz, this.zza.scheduleAtFixedRate(zzuz, j, j2, timeUnit));
    }

    public final /* bridge */ /* synthetic */ ScheduledFuture scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        zzuz zzuz = new zzuz(runnable);
        return new zzuy(zzuz, this.zza.scheduleWithFixedDelay(zzuz, j, j2, timeUnit));
    }

    public final /* bridge */ /* synthetic */ ScheduledFuture schedule(Callable callable, long j, TimeUnit timeUnit) {
        zzvh zzvh = new zzvh(callable);
        return new zzuy(zzvh, this.zza.schedule(zzvh, j, timeUnit));
    }
}
