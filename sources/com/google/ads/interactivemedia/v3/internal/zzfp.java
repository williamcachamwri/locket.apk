package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import com.google.ads.interactivemedia.pal.PlatformSignalCollector;
import com.google.ads.interactivemedia.v3.impl.data.TestingConfiguration;
import com.google.ads.interactivemedia.v3.impl.data.zzbp;
import com.google.ads.interactivemedia.v3.impl.data.zzbq;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.android.tv.ads.SignalCollector;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzfp {
    private final Context zza;
    private final ExecutorService zzb;
    private final PlatformSignalCollector zzc;
    private final zzfd zzd;
    private final TaskCompletionSource zze;

    public zzfp(Context context, ExecutorService executorService, zzfd zzfd, TestingConfiguration testingConfiguration) {
        SignalCollector signalCollector;
        if (!zzel.zzc(context, testingConfiguration)) {
            signalCollector = null;
        } else {
            signalCollector = new SignalCollector();
        }
        this.zze = new TaskCompletionSource();
        this.zza = context;
        this.zzb = executorService;
        this.zzd = zzfd;
        this.zzc = signalCollector;
    }

    public final Task zza() {
        return this.zze.getTask();
    }

    public final void zzb(Integer num) {
        PlatformSignalCollector platformSignalCollector = this.zzc;
        if (platformSignalCollector == null || num == null) {
            this.zze.setResult(null);
            return;
        }
        Task<T> withTimeout = Tasks.withTimeout(platformSignalCollector.collectSignals(this.zza, this.zzb), (long) num.intValue(), TimeUnit.MILLISECONDS);
        TaskCompletionSource taskCompletionSource = this.zze;
        Objects.requireNonNull(taskCompletionSource);
        withTimeout.addOnSuccessListener(new zzfn(taskCompletionSource));
        withTimeout.addOnFailureListener(new zzfo(this));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(Exception exc) {
        this.zzd.zzg(zzbp.PLATFORM_SIGNAL_COLLECTOR, zzbq.PLATFORM_COLLECT_SIGNALS, exc);
        this.zze.setException(exc);
    }
}
