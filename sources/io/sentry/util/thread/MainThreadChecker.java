package io.sentry.util.thread;

public final class MainThreadChecker implements IMainThreadChecker {
    private static final MainThreadChecker instance = new MainThreadChecker();
    private static final long mainThreadId = Thread.currentThread().getId();

    public static MainThreadChecker getInstance() {
        return instance;
    }

    private MainThreadChecker() {
    }

    public boolean isMainThread(long j) {
        return mainThreadId == j;
    }
}
