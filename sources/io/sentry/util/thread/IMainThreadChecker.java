package io.sentry.util.thread;

import io.sentry.protocol.SentryThread;

public interface IMainThreadChecker {
    boolean isMainThread(long j);

    boolean isMainThread(Thread thread) {
        return isMainThread(thread.getId());
    }

    boolean isMainThread() {
        return isMainThread(Thread.currentThread());
    }

    boolean isMainThread(SentryThread sentryThread) {
        Long id = sentryThread.getId();
        return id != null && isMainThread(id.longValue());
    }
}
