package com.google.firebase.storage.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.storage.StorageTaskScheduler;
import java.util.concurrent.Executor;

public class SmartHandler {
    static boolean testMode = false;
    private final Executor executor;

    public SmartHandler(Executor executor2) {
        if (executor2 != null) {
            this.executor = executor2;
        } else if (!testMode) {
            this.executor = StorageTaskScheduler.getInstance().getMainThreadExecutor();
        } else {
            this.executor = null;
        }
    }

    public void callBack(Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        Executor executor2 = this.executor;
        if (executor2 != null) {
            executor2.execute(runnable);
        } else {
            StorageTaskScheduler.getInstance().scheduleCallback(runnable);
        }
    }
}
