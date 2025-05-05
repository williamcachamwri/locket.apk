package kotlinx.coroutines;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0011\u0010\u0003\u001a\u00020\u0001*\u00020\u0004H\u0007¢\u0006\u0002\b\u0005\u001a\u0011\u0010\u0003\u001a\u00020\u0006*\u00020\u0007H\u0007¢\u0006\u0002\b\u0005\u001a\n\u0010\b\u001a\u00020\u0007*\u00020\u0006*\u0010\b\u0007\u0010\u0000\"\u00020\u00012\u00020\u0001B\u0002\b\u0002¨\u0006\t"}, d2 = {"CloseableCoroutineDispatcher", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "Lkotlinx/coroutines/ExperimentalCoroutinesApi;", "asCoroutineDispatcher", "Ljava/util/concurrent/ExecutorService;", "from", "Lkotlinx/coroutines/CoroutineDispatcher;", "Ljava/util/concurrent/Executor;", "asExecutor", "kotlinx-coroutines-core"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: Executors.kt */
public final class ExecutorsKt {
    public static /* synthetic */ void CloseableCoroutineDispatcher$annotations() {
    }

    public static final ExecutorCoroutineDispatcher from(ExecutorService executorService) {
        return new ExecutorCoroutineDispatcherImpl(executorService);
    }

    public static final CoroutineDispatcher from(Executor executor) {
        CoroutineDispatcher coroutineDispatcher;
        DispatcherExecutor dispatcherExecutor = executor instanceof DispatcherExecutor ? (DispatcherExecutor) executor : null;
        return (dispatcherExecutor == null || (coroutineDispatcher = dispatcherExecutor.dispatcher) == null) ? new ExecutorCoroutineDispatcherImpl(executor) : coroutineDispatcher;
    }

    public static final Executor asExecutor(CoroutineDispatcher coroutineDispatcher) {
        Executor executor;
        ExecutorCoroutineDispatcher executorCoroutineDispatcher = coroutineDispatcher instanceof ExecutorCoroutineDispatcher ? (ExecutorCoroutineDispatcher) coroutineDispatcher : null;
        return (executorCoroutineDispatcher == null || (executor = executorCoroutineDispatcher.getExecutor()) == null) ? new DispatcherExecutor(coroutineDispatcher) : executor;
    }
}
