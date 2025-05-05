package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.remote.Stream;
import com.google.firebase.firestore.remote.Stream.StreamCallback;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.ExponentialBackoff;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.Util;
import io.grpc.ClientCall;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

abstract class AbstractStream<ReqT, RespT, CallbackT extends Stream.StreamCallback> implements Stream<CallbackT> {
    private static final long BACKOFF_CLIENT_NETWORK_FAILURE_MAX_DELAY_MS = TimeUnit.SECONDS.toMillis(10);
    private static final double BACKOFF_FACTOR = 1.5d;
    private static final long BACKOFF_INITIAL_DELAY_MS = TimeUnit.SECONDS.toMillis(1);
    private static final long BACKOFF_MAX_DELAY_MS = TimeUnit.MINUTES.toMillis(1);
    private static final long HEALTHY_TIMEOUT_MS = TimeUnit.SECONDS.toMillis(10);
    private static final long IDLE_TIMEOUT_MS = TimeUnit.MINUTES.toMillis(1);
    final ExponentialBackoff backoff;
    private ClientCall<ReqT, RespT> call;
    /* access modifiers changed from: private */
    public long closeCount = 0;
    private final FirestoreChannel firestoreChannel;
    private AsyncQueue.DelayedTask healthCheck;
    private final AsyncQueue.TimerId healthTimerId;
    private final AbstractStream<ReqT, RespT, CallbackT>.IdleTimeoutRunnable idleTimeoutRunnable;
    private AsyncQueue.DelayedTask idleTimer;
    private final AsyncQueue.TimerId idleTimerId;
    final CallbackT listener;
    private final MethodDescriptor<ReqT, RespT> methodDescriptor;
    private Stream.State state = Stream.State.Initial;
    /* access modifiers changed from: private */
    public final AsyncQueue workerQueue;

    public abstract void onFirst(RespT respt);

    public abstract void onNext(RespT respt);

    /* access modifiers changed from: protected */
    public void tearDown() {
    }

    class CloseGuardedRunner {
        private final long initialCloseCount;

        CloseGuardedRunner(long j) {
            this.initialCloseCount = j;
        }

        /* access modifiers changed from: package-private */
        public void run(Runnable runnable) {
            AbstractStream.this.workerQueue.verifyIsCurrentThread();
            if (AbstractStream.this.closeCount == this.initialCloseCount) {
                runnable.run();
            } else {
                Logger.debug(AbstractStream.this.getClass().getSimpleName(), "stream callback skipped by CloseGuardedRunner.", new Object[0]);
            }
        }
    }

    class StreamObserver implements IncomingStreamObserver<RespT> {
        private final AbstractStream<ReqT, RespT, CallbackT>.CloseGuardedRunner dispatcher;
        private int responseCount = 0;

        StreamObserver(AbstractStream<ReqT, RespT, CallbackT>.CloseGuardedRunner closeGuardedRunner) {
            this.dispatcher = closeGuardedRunner;
        }

        public void onHeaders(Metadata metadata) {
            this.dispatcher.run(new AbstractStream$StreamObserver$$ExternalSyntheticLambda3(this, metadata));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onHeaders$0$com-google-firebase-firestore-remote-AbstractStream$StreamObserver  reason: not valid java name */
        public /* synthetic */ void m742lambda$onHeaders$0$comgooglefirebasefirestoreremoteAbstractStream$StreamObserver(Metadata metadata) {
            if (Logger.isDebugEnabled()) {
                HashMap hashMap = new HashMap();
                for (String next : metadata.keys()) {
                    if (Datastore.WHITE_LISTED_HEADERS.contains(next.toLowerCase(Locale.ENGLISH))) {
                        hashMap.put(next, (String) metadata.get(Metadata.Key.of(next, Metadata.ASCII_STRING_MARSHALLER)));
                    }
                }
                if (!hashMap.isEmpty()) {
                    Logger.debug(AbstractStream.this.getClass().getSimpleName(), "(%x) Stream received headers: %s", Integer.valueOf(System.identityHashCode(AbstractStream.this)), hashMap);
                }
            }
        }

        public void onNext(RespT respt) {
            int i = this.responseCount + 1;
            this.dispatcher.run(new AbstractStream$StreamObserver$$ExternalSyntheticLambda2(this, i, respt));
            this.responseCount = i;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onNext$1$com-google-firebase-firestore-remote-AbstractStream$StreamObserver  reason: not valid java name */
        public /* synthetic */ void m743lambda$onNext$1$comgooglefirebasefirestoreremoteAbstractStream$StreamObserver(int i, Object obj) {
            if (Logger.isDebugEnabled()) {
                Logger.debug(AbstractStream.this.getClass().getSimpleName(), "(%x) Stream received (%s): %s", Integer.valueOf(System.identityHashCode(AbstractStream.this)), Integer.valueOf(i), obj);
            }
            if (i == 1) {
                AbstractStream.this.onFirst(obj);
            } else {
                AbstractStream.this.onNext(obj);
            }
        }

        public void onOpen() {
            this.dispatcher.run(new AbstractStream$StreamObserver$$ExternalSyntheticLambda0(this));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onOpen$2$com-google-firebase-firestore-remote-AbstractStream$StreamObserver  reason: not valid java name */
        public /* synthetic */ void m744lambda$onOpen$2$comgooglefirebasefirestoreremoteAbstractStream$StreamObserver() {
            Logger.debug(AbstractStream.this.getClass().getSimpleName(), "(%x) Stream is open", Integer.valueOf(System.identityHashCode(AbstractStream.this)));
            AbstractStream.this.onOpen();
        }

        public void onClose(Status status) {
            this.dispatcher.run(new AbstractStream$StreamObserver$$ExternalSyntheticLambda1(this, status));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onClose$3$com-google-firebase-firestore-remote-AbstractStream$StreamObserver  reason: not valid java name */
        public /* synthetic */ void m741lambda$onClose$3$comgooglefirebasefirestoreremoteAbstractStream$StreamObserver(Status status) {
            if (status.isOk()) {
                Logger.debug(AbstractStream.this.getClass().getSimpleName(), "(%x) Stream closed.", Integer.valueOf(System.identityHashCode(AbstractStream.this)));
            } else {
                Logger.warn(AbstractStream.this.getClass().getSimpleName(), "(%x) Stream closed with status: %s.", Integer.valueOf(System.identityHashCode(AbstractStream.this)), status);
            }
            AbstractStream.this.handleServerClose(status);
        }
    }

    class IdleTimeoutRunnable implements Runnable {
        IdleTimeoutRunnable() {
        }

        public void run() {
            AbstractStream.this.handleIdleCloseTimer();
        }
    }

    AbstractStream(FirestoreChannel firestoreChannel2, MethodDescriptor<ReqT, RespT> methodDescriptor2, AsyncQueue asyncQueue, AsyncQueue.TimerId timerId, AsyncQueue.TimerId timerId2, AsyncQueue.TimerId timerId3, CallbackT callbackt) {
        this.firestoreChannel = firestoreChannel2;
        this.methodDescriptor = methodDescriptor2;
        AsyncQueue asyncQueue2 = asyncQueue;
        this.workerQueue = asyncQueue2;
        this.idleTimerId = timerId2;
        this.healthTimerId = timerId3;
        this.listener = callbackt;
        this.idleTimeoutRunnable = new IdleTimeoutRunnable();
        this.backoff = new ExponentialBackoff(asyncQueue2, timerId, BACKOFF_INITIAL_DELAY_MS, 1.5d, BACKOFF_MAX_DELAY_MS);
    }

    public boolean isStarted() {
        this.workerQueue.verifyIsCurrentThread();
        return this.state == Stream.State.Starting || this.state == Stream.State.Backoff || isOpen();
    }

    public boolean isOpen() {
        this.workerQueue.verifyIsCurrentThread();
        return this.state == Stream.State.Open || this.state == Stream.State.Healthy;
    }

    public void start() {
        this.workerQueue.verifyIsCurrentThread();
        boolean z = true;
        Assert.hardAssert(this.call == null, "Last call still set", new Object[0]);
        Assert.hardAssert(this.idleTimer == null, "Idle timer still set", new Object[0]);
        if (this.state == Stream.State.Error) {
            performBackoff();
            return;
        }
        if (this.state != Stream.State.Initial) {
            z = false;
        }
        Assert.hardAssert(z, "Already started", new Object[0]);
        this.call = this.firestoreChannel.runBidiStreamingRpc(this.methodDescriptor, new StreamObserver(new CloseGuardedRunner(this.closeCount)));
        this.state = Stream.State.Starting;
    }

    private void close(Stream.State state2, Status status) {
        Assert.hardAssert(isStarted(), "Only started streams should be closed.", new Object[0]);
        Assert.hardAssert(state2 == Stream.State.Error || status.isOk(), "Can't provide an error when not in an error state.", new Object[0]);
        this.workerQueue.verifyIsCurrentThread();
        if (Datastore.isMissingSslCiphers(status)) {
            Util.crashMainThread(new IllegalStateException("The Cloud Firestore client failed to establish a secure connection. This is likely a problem with your app, rather than with Cloud Firestore itself. See https://bit.ly/2XFpdma for instructions on how to enable TLS on Android 4.x devices.", status.getCause()));
        }
        cancelIdleCheck();
        cancelHealthCheck();
        this.backoff.cancel();
        this.closeCount++;
        Status.Code code = status.getCode();
        if (code == Status.Code.OK) {
            this.backoff.reset();
        } else if (code == Status.Code.RESOURCE_EXHAUSTED) {
            Logger.debug(getClass().getSimpleName(), "(%x) Using maximum backoff delay to prevent overloading the backend.", Integer.valueOf(System.identityHashCode(this)));
            this.backoff.resetToMax();
        } else if (code == Status.Code.UNAUTHENTICATED && this.state != Stream.State.Healthy) {
            this.firestoreChannel.invalidateToken();
        } else if (code == Status.Code.UNAVAILABLE && ((status.getCause() instanceof UnknownHostException) || (status.getCause() instanceof ConnectException))) {
            this.backoff.setTemporaryMaxDelay(BACKOFF_CLIENT_NETWORK_FAILURE_MAX_DELAY_MS);
        }
        if (state2 != Stream.State.Error) {
            Logger.debug(getClass().getSimpleName(), "(%x) Performing stream teardown", Integer.valueOf(System.identityHashCode(this)));
            tearDown();
        }
        if (this.call != null) {
            if (status.isOk()) {
                Logger.debug(getClass().getSimpleName(), "(%x) Closing stream client-side", Integer.valueOf(System.identityHashCode(this)));
                this.call.halfClose();
            }
            this.call = null;
        }
        this.state = state2;
        this.listener.onClose(status);
    }

    public void stop() {
        if (isStarted()) {
            close(Stream.State.Initial, Status.OK);
        }
    }

    public void inhibitBackoff() {
        Assert.hardAssert(!isStarted(), "Can only inhibit backoff after in a stopped state", new Object[0]);
        this.workerQueue.verifyIsCurrentThread();
        this.state = Stream.State.Initial;
        this.backoff.reset();
    }

    /* access modifiers changed from: protected */
    public void writeRequest(ReqT reqt) {
        this.workerQueue.verifyIsCurrentThread();
        Logger.debug(getClass().getSimpleName(), "(%x) Stream sending: %s", Integer.valueOf(System.identityHashCode(this)), reqt);
        cancelIdleCheck();
        this.call.sendMessage(reqt);
    }

    /* access modifiers changed from: private */
    public void handleIdleCloseTimer() {
        if (isOpen()) {
            close(Stream.State.Initial, Status.OK);
        }
    }

    /* access modifiers changed from: package-private */
    public void handleServerClose(Status status) {
        Assert.hardAssert(isStarted(), "Can't handle server close on non-started stream!", new Object[0]);
        close(Stream.State.Error, status);
    }

    /* access modifiers changed from: private */
    public void onOpen() {
        this.state = Stream.State.Open;
        this.listener.onOpen();
        if (this.healthCheck == null) {
            this.healthCheck = this.workerQueue.enqueueAfterDelay(this.healthTimerId, HEALTHY_TIMEOUT_MS, new AbstractStream$$ExternalSyntheticLambda0(this));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onOpen$0$com-google-firebase-firestore-remote-AbstractStream  reason: not valid java name */
    public /* synthetic */ void m739lambda$onOpen$0$comgooglefirebasefirestoreremoteAbstractStream() {
        if (isOpen()) {
            this.state = Stream.State.Healthy;
        }
    }

    private void performBackoff() {
        Assert.hardAssert(this.state == Stream.State.Error, "Should only perform backoff in an error state", new Object[0]);
        this.state = Stream.State.Backoff;
        this.backoff.backoffAndRun(new AbstractStream$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$performBackoff$1$com-google-firebase-firestore-remote-AbstractStream  reason: not valid java name */
    public /* synthetic */ void m740lambda$performBackoff$1$comgooglefirebasefirestoreremoteAbstractStream() {
        Assert.hardAssert(this.state == Stream.State.Backoff, "State should still be backoff but was %s", this.state);
        this.state = Stream.State.Initial;
        start();
        Assert.hardAssert(isStarted(), "Stream should have started", new Object[0]);
    }

    /* access modifiers changed from: package-private */
    public void markIdle() {
        if (isOpen() && this.idleTimer == null) {
            this.idleTimer = this.workerQueue.enqueueAfterDelay(this.idleTimerId, IDLE_TIMEOUT_MS, this.idleTimeoutRunnable);
        }
    }

    private void cancelIdleCheck() {
        AsyncQueue.DelayedTask delayedTask = this.idleTimer;
        if (delayedTask != null) {
            delayedTask.cancel();
            this.idleTimer = null;
        }
    }

    private void cancelHealthCheck() {
        AsyncQueue.DelayedTask delayedTask = this.healthCheck;
        if (delayedTask != null) {
            delayedTask.cancel();
            this.healthCheck = null;
        }
    }
}
