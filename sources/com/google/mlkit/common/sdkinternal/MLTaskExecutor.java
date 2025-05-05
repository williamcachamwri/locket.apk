package com.google.mlkit.common.sdkinternal;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.google.android.gms.internal.mlkit_common.zza;
import com.google.android.gms.internal.mlkit_common.zzaw;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public class MLTaskExecutor {
    private static final Object zza = new Object();
    private static MLTaskExecutor zzb;
    /* access modifiers changed from: private */
    public final Handler zzc;

    private MLTaskExecutor(Looper looper) {
        this.zzc = new zza(looper);
    }

    public static MLTaskExecutor getInstance() {
        MLTaskExecutor mLTaskExecutor;
        synchronized (zza) {
            if (zzb == null) {
                HandlerThread handlerThread = new HandlerThread("MLHandler", 9);
                handlerThread.start();
                zzb = new MLTaskExecutor(handlerThread.getLooper());
            }
            mLTaskExecutor = zzb;
        }
        return mLTaskExecutor;
    }

    public static Executor workerThreadExecutor() {
        return zzh.INSTANCE;
    }

    public Handler getHandler() {
        return this.zzc;
    }

    public <ResultT> Task<ResultT> scheduleCallable(Callable<ResultT> callable) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        scheduleRunnable(new zzf(callable, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    public void scheduleRunnable(Runnable runnable) {
        workerThreadExecutor().execute(runnable);
    }

    public void scheduleRunnableDelayed(Runnable runnable, long j) {
        this.zzc.postDelayed(runnable, j);
    }

    public <ResultT> Task<ResultT> scheduleTaskCallable(Callable<Task<ResultT>> callable) {
        return scheduleCallable(callable).continueWithTask(zzaw.zza(), new zzg());
    }
}
