package androidx.camera.core.impl.utils;

import android.os.Handler;
import android.os.Looper;
import androidx.core.util.Preconditions;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public final class Threads {
    private static final long TIMEOUT_RUN_ON_MAIN_MS = 30000;

    private Threads() {
    }

    public static boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public static boolean isBackgroundThread() {
        return !isMainThread();
    }

    public static void checkMainThread() {
        Preconditions.checkState(isMainThread(), "Not in application's main thread");
    }

    public static void checkBackgroundThread() {
        Preconditions.checkState(isBackgroundThread(), "In application's main thread");
    }

    public static void runOnMain(Runnable runnable) {
        if (isMainThread()) {
            runnable.run();
        } else {
            Preconditions.checkState(getMainHandler().post(runnable), "Unable to post to main thread");
        }
    }

    public static void runOnMainSync(Runnable runnable) {
        if (isMainThread()) {
            runnable.run();
            return;
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Preconditions.checkState(getMainHandler().post(new Threads$$ExternalSyntheticLambda0(runnable, countDownLatch)), "Unable to post to main thread");
        try {
            if (!countDownLatch.await(30000, TimeUnit.MILLISECONDS)) {
                throw new IllegalStateException("Timeout to wait main thread execution");
            }
        } catch (InterruptedException e) {
            throw new InterruptedRuntimeException((Throwable) e);
        }
    }

    static /* synthetic */ void lambda$runOnMainSync$0(Runnable runnable, CountDownLatch countDownLatch) {
        try {
            runnable.run();
        } finally {
            countDownLatch.countDown();
        }
    }

    private static Handler getMainHandler() {
        return new Handler(Looper.getMainLooper());
    }
}
