package io.sentry.util.thread;

public final class NoOpMainThreadChecker implements IMainThreadChecker {
    private static final NoOpMainThreadChecker instance = new NoOpMainThreadChecker();

    public boolean isMainThread(long j) {
        return false;
    }

    public static NoOpMainThreadChecker getInstance() {
        return instance;
    }
}
