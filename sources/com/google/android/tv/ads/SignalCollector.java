package com.google.android.tv.ads;

import android.content.Context;
import com.google.ads.interactivemedia.pal.PlatformSignalCollector;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public final class SignalCollector implements PlatformSignalCollector {
    public static final /* synthetic */ int zza = 0;

    public Task<Map<String, String>> collectSignals(Context context, ExecutorService executorService) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        context.getClass();
        executorService.getClass();
        executorService.execute(new zzg(taskCompletionSource, context));
        return taskCompletionSource.getTask();
    }
}
