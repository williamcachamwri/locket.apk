package com.google.common.util.concurrent;

import com.google.common.base.Supplier;
import com.google.common.util.concurrent.Service;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@ElementTypesAreNonnullByDefault
public abstract class AbstractExecutionThreadService implements Service {
    /* access modifiers changed from: private */
    public static final LazyLogger logger = new LazyLogger(AbstractExecutionThreadService.class);
    private final Service delegate = new AbstractService() {
        /* access modifiers changed from: protected */
        public final void doStart() {
            MoreExecutors.renamingDecorator(AbstractExecutionThreadService.this.executor(), (Supplier<String>) new AbstractExecutionThreadService$1$$ExternalSyntheticLambda0(this)).execute(new AbstractExecutionThreadService$1$$ExternalSyntheticLambda1(this));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$doStart$0$com-google-common-util-concurrent-AbstractExecutionThreadService$1  reason: not valid java name */
        public /* synthetic */ String m556lambda$doStart$0$comgooglecommonutilconcurrentAbstractExecutionThreadService$1() {
            return AbstractExecutionThreadService.this.serviceName();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$doStart$1$com-google-common-util-concurrent-AbstractExecutionThreadService$1  reason: not valid java name */
        public /* synthetic */ void m557lambda$doStart$1$comgooglecommonutilconcurrentAbstractExecutionThreadService$1() {
            try {
                AbstractExecutionThreadService.this.startUp();
                notifyStarted();
                if (isRunning()) {
                    AbstractExecutionThreadService.this.run();
                }
                AbstractExecutionThreadService.this.shutDown();
                notifyStopped();
            } catch (Throwable th) {
                Platform.restoreInterruptIfIsInterruptedException(th);
                notifyFailed(th);
            }
        }

        /* access modifiers changed from: protected */
        public void doStop() {
            AbstractExecutionThreadService.this.triggerShutdown();
        }

        public String toString() {
            return AbstractExecutionThreadService.this.toString();
        }
    };

    /* access modifiers changed from: protected */
    public abstract void run() throws Exception;

    /* access modifiers changed from: protected */
    public void shutDown() throws Exception {
    }

    /* access modifiers changed from: protected */
    public void startUp() throws Exception {
    }

    /* access modifiers changed from: protected */
    public void triggerShutdown() {
    }

    protected AbstractExecutionThreadService() {
    }

    /* access modifiers changed from: protected */
    public Executor executor() {
        return new AbstractExecutionThreadService$$ExternalSyntheticLambda0(this);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$executor$0$com-google-common-util-concurrent-AbstractExecutionThreadService  reason: not valid java name */
    public /* synthetic */ void m555lambda$executor$0$comgooglecommonutilconcurrentAbstractExecutionThreadService(Runnable runnable) {
        MoreExecutors.newThread(serviceName(), runnable).start();
    }

    public String toString() {
        return serviceName() + " [" + state() + "]";
    }

    public final boolean isRunning() {
        return this.delegate.isRunning();
    }

    public final Service.State state() {
        return this.delegate.state();
    }

    public final void addListener(Service.Listener listener, Executor executor) {
        this.delegate.addListener(listener, executor);
    }

    public final Throwable failureCause() {
        return this.delegate.failureCause();
    }

    public final Service startAsync() {
        this.delegate.startAsync();
        return this;
    }

    public final Service stopAsync() {
        this.delegate.stopAsync();
        return this;
    }

    public final void awaitRunning() {
        this.delegate.awaitRunning();
    }

    public final void awaitRunning(long j, TimeUnit timeUnit) throws TimeoutException {
        this.delegate.awaitRunning(j, timeUnit);
    }

    public final void awaitTerminated() {
        this.delegate.awaitTerminated();
    }

    public final void awaitTerminated(long j, TimeUnit timeUnit) throws TimeoutException {
        this.delegate.awaitTerminated(j, timeUnit);
    }

    /* access modifiers changed from: protected */
    public String serviceName() {
        return getClass().getSimpleName();
    }
}
