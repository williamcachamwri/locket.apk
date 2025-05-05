package io.grpc.internal;

import io.grpc.Attributes;
import io.grpc.NameResolver;
import io.grpc.Status;
import io.grpc.SynchronizationContext;

final class RetryingNameResolver extends ForwardingNameResolver {
    static final Attributes.Key<ResolutionResultListener> RESOLUTION_RESULT_LISTENER_KEY = Attributes.Key.create("io.grpc.internal.RetryingNameResolver.RESOLUTION_RESULT_LISTENER_KEY");
    private final NameResolver retriedNameResolver;
    /* access modifiers changed from: private */
    public final RetryScheduler retryScheduler;
    /* access modifiers changed from: private */
    public final SynchronizationContext syncContext;

    RetryingNameResolver(NameResolver nameResolver, RetryScheduler retryScheduler2, SynchronizationContext synchronizationContext) {
        super(nameResolver);
        this.retriedNameResolver = nameResolver;
        this.retryScheduler = retryScheduler2;
        this.syncContext = synchronizationContext;
    }

    public void start(NameResolver.Listener2 listener2) {
        super.start((NameResolver.Listener2) new RetryingListener(listener2));
    }

    public void shutdown() {
        super.shutdown();
        this.retryScheduler.reset();
    }

    /* access modifiers changed from: package-private */
    public NameResolver getRetriedNameResolver() {
        return this.retriedNameResolver;
    }

    class DelayedNameResolverRefresh implements Runnable {
        DelayedNameResolverRefresh() {
        }

        public void run() {
            RetryingNameResolver.this.refresh();
        }
    }

    private class RetryingListener extends NameResolver.Listener2 {
        private NameResolver.Listener2 delegateListener;

        RetryingListener(NameResolver.Listener2 listener2) {
            this.delegateListener = listener2;
        }

        public void onResult(NameResolver.ResolutionResult resolutionResult) {
            if (resolutionResult.getAttributes().get(RetryingNameResolver.RESOLUTION_RESULT_LISTENER_KEY) == null) {
                this.delegateListener.onResult(resolutionResult.toBuilder().setAttributes(resolutionResult.getAttributes().toBuilder().set(RetryingNameResolver.RESOLUTION_RESULT_LISTENER_KEY, new ResolutionResultListener()).build()).build());
                return;
            }
            throw new IllegalStateException("RetryingNameResolver can only be used once to wrap a NameResolver");
        }

        public void onError(Status status) {
            this.delegateListener.onError(status);
            RetryingNameResolver.this.syncContext.execute(new RetryingNameResolver$RetryingListener$$ExternalSyntheticLambda0(this));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onError$0$io-grpc-internal-RetryingNameResolver$RetryingListener  reason: not valid java name */
        public /* synthetic */ void m2329lambda$onError$0$iogrpcinternalRetryingNameResolver$RetryingListener() {
            RetryingNameResolver.this.retryScheduler.schedule(new DelayedNameResolverRefresh());
        }
    }

    class ResolutionResultListener {
        ResolutionResultListener() {
        }

        public void resolutionAttempted(Status status) {
            if (status.isOk()) {
                RetryingNameResolver.this.retryScheduler.reset();
            } else {
                RetryingNameResolver.this.retryScheduler.schedule(new DelayedNameResolverRefresh());
            }
        }
    }
}
