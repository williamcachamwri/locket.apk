package com.facebook.react.bridge.queue;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public interface MessageQueueThread {
    void assertIsOnThread();

    void assertIsOnThread(String str);

    <T> Future<T> callOnQueue(Callable<T> callable);

    MessageQueueThreadPerfStats getPerfStats();

    boolean isIdle();

    boolean isOnThread();

    void quitSynchronous();

    void resetPerfStats();

    boolean runOnQueue(Runnable runnable);
}
