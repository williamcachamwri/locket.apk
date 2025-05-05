package com.google.mlkit.common.sdkinternal;

import java.util.concurrent.Executor;

/* compiled from: com.google.mlkit:common@@18.11.0 */
enum zzh implements Executor {
    INSTANCE;

    public final void execute(Runnable runnable) {
        MLTaskExecutor.getInstance().zzc.post(runnable);
    }
}
