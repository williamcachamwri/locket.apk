package com.facebook.imagepipeline.producers;

import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\f\u001a\u00020\u0006H\u0016J\b\u0010\r\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/facebook/imagepipeline/producers/ExperimentalThreadHandoffProducerQueueImpl;", "Lcom/facebook/imagepipeline/producers/ThreadHandoffProducerQueue;", "executor", "Ljava/util/concurrent/Executor;", "(Ljava/util/concurrent/Executor;)V", "addToQueueOrExecute", "", "runnable", "Ljava/lang/Runnable;", "isQueueing", "", "remove", "startQueueing", "stopQueuing", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ExperimentalThreadHandoffProducerQueueImpl.kt */
public final class ExperimentalThreadHandoffProducerQueueImpl implements ThreadHandoffProducerQueue {
    private final Executor executor;

    public boolean isQueueing() {
        return false;
    }

    public void remove(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
    }

    public ExperimentalThreadHandoffProducerQueueImpl(Executor executor2) {
        if (executor2 != null) {
            this.executor = executor2;
            return;
        }
        throw new IllegalStateException("Required value was null.".toString());
    }

    public void addToQueueOrExecute(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        this.executor.execute(runnable);
    }

    public void startQueueing() {
        throw new UnsupportedOperationException();
    }

    public void stopQueuing() {
        throw new UnsupportedOperationException();
    }
}
