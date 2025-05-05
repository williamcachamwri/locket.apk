package com.google.ads.interactivemedia.pal;

import android.content.Context;
import com.google.android.gms.tasks.Task;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public interface PlatformSignalCollector {
    Task<Map<String, String>> collectSignals(Context context, ExecutorService executorService);
}
