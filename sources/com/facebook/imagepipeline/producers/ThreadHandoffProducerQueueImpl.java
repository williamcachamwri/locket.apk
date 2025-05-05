package com.facebook.imagepipeline.producers;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tH\u0016J\b\u0010\r\u001a\u00020\u000bH\u0002J\b\u0010\u000e\u001a\u00020\u0006H\u0016J\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tH\u0016J\b\u0010\u0010\u001a\u00020\u000bH\u0016J\b\u0010\u0011\u001a\u00020\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/facebook/imagepipeline/producers/ThreadHandoffProducerQueueImpl;", "Lcom/facebook/imagepipeline/producers/ThreadHandoffProducerQueue;", "executor", "Ljava/util/concurrent/Executor;", "(Ljava/util/concurrent/Executor;)V", "queueing", "", "runnableList", "Ljava/util/Deque;", "Ljava/lang/Runnable;", "addToQueueOrExecute", "", "runnable", "execInQueue", "isQueueing", "remove", "startQueueing", "stopQueuing", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ThreadHandoffProducerQueueImpl.kt */
public final class ThreadHandoffProducerQueueImpl implements ThreadHandoffProducerQueue {
    private final Executor executor;
    private boolean queueing;
    private final Deque<Runnable> runnableList = new ArrayDeque();

    public ThreadHandoffProducerQueueImpl(Executor executor2) {
        Intrinsics.checkNotNullParameter(executor2, "executor");
        this.executor = executor2;
    }

    public synchronized void addToQueueOrExecute(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        if (this.queueing) {
            this.runnableList.add(runnable);
        } else {
            this.executor.execute(runnable);
        }
    }

    public synchronized void startQueueing() {
        this.queueing = true;
    }

    public synchronized void stopQueuing() {
        this.queueing = false;
        execInQueue();
    }

    private final void execInQueue() {
        while (!this.runnableList.isEmpty()) {
            this.executor.execute(this.runnableList.pop());
        }
        this.runnableList.clear();
    }

    public synchronized void remove(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        this.runnableList.remove(runnable);
    }

    public synchronized boolean isQueueing() {
        return this.queueing;
    }
}
