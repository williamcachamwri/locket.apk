package io.sentry.android.core.internal.util;

import android.os.Looper;
import io.sentry.util.thread.IMainThreadChecker;

public final class AndroidMainThreadChecker implements IMainThreadChecker {
    private static final AndroidMainThreadChecker instance = new AndroidMainThreadChecker();

    public static AndroidMainThreadChecker getInstance() {
        return instance;
    }

    private AndroidMainThreadChecker() {
    }

    public boolean isMainThread(long j) {
        return Looper.getMainLooper().getThread().getId() == j;
    }
}
