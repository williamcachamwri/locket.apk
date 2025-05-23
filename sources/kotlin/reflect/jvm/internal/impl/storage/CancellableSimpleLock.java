package kotlin.reflect.jvm.internal.impl.storage;

import io.sentry.protocol.SentryStackFrame;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: locks.kt */
public final class CancellableSimpleLock extends DefaultSimpleLock {
    private final Runnable checkCancelled;
    private final Function1<InterruptedException, Unit> interruptedExceptionHandler;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CancellableSimpleLock(Lock lock, Runnable runnable, Function1<? super InterruptedException, Unit> function1) {
        super(lock);
        Intrinsics.checkNotNullParameter(lock, SentryStackFrame.JsonKeys.LOCK);
        Intrinsics.checkNotNullParameter(runnable, "checkCancelled");
        Intrinsics.checkNotNullParameter(function1, "interruptedExceptionHandler");
        this.checkCancelled = runnable;
        this.interruptedExceptionHandler = function1;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CancellableSimpleLock(Runnable runnable, Function1<? super InterruptedException, Unit> function1) {
        this(new ReentrantLock(), runnable, function1);
        Intrinsics.checkNotNullParameter(runnable, "checkCancelled");
        Intrinsics.checkNotNullParameter(function1, "interruptedExceptionHandler");
    }

    public void lock() {
        while (!getLock().tryLock(50, TimeUnit.MILLISECONDS)) {
            try {
                this.checkCancelled.run();
            } catch (InterruptedException e) {
                this.interruptedExceptionHandler.invoke(e);
                return;
            }
        }
    }
}
