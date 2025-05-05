package io.grpc.stub;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.StatusRuntimeException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

public final class ClientCalls {
    static final CallOptions.Key<StubType> STUB_TYPE_OPTION = CallOptions.Key.create("internal-stub-type");
    private static final Logger logger = Logger.getLogger(ClientCalls.class.getName());
    static boolean rejectRunnableOnExecutor = (!Strings.isNullOrEmpty(System.getenv("GRPC_CLIENT_CALL_REJECT_RUNNABLE")) && Boolean.parseBoolean(System.getenv("GRPC_CLIENT_CALL_REJECT_RUNNABLE")));

    enum StubType {
        BLOCKING,
        FUTURE,
        ASYNC
    }

    private ClientCalls() {
    }

    public static <ReqT, RespT> void asyncUnaryCall(ClientCall<ReqT, RespT> clientCall, ReqT reqt, StreamObserver<RespT> streamObserver) {
        Preconditions.checkNotNull(streamObserver, "responseObserver");
        asyncUnaryRequestCall(clientCall, reqt, streamObserver, false);
    }

    public static <ReqT, RespT> void asyncServerStreamingCall(ClientCall<ReqT, RespT> clientCall, ReqT reqt, StreamObserver<RespT> streamObserver) {
        Preconditions.checkNotNull(streamObserver, "responseObserver");
        asyncUnaryRequestCall(clientCall, reqt, streamObserver, true);
    }

    public static <ReqT, RespT> StreamObserver<ReqT> asyncClientStreamingCall(ClientCall<ReqT, RespT> clientCall, StreamObserver<RespT> streamObserver) {
        Preconditions.checkNotNull(streamObserver, "responseObserver");
        return asyncStreamingRequestCall(clientCall, streamObserver, false);
    }

    public static <ReqT, RespT> StreamObserver<ReqT> asyncBidiStreamingCall(ClientCall<ReqT, RespT> clientCall, StreamObserver<RespT> streamObserver) {
        Preconditions.checkNotNull(streamObserver, "responseObserver");
        return asyncStreamingRequestCall(clientCall, streamObserver, true);
    }

    public static <ReqT, RespT> RespT blockingUnaryCall(ClientCall<ReqT, RespT> clientCall, ReqT reqt) {
        try {
            return getUnchecked(futureUnaryCall(clientCall, reqt));
        } catch (Error | RuntimeException e) {
            throw cancelThrow(clientCall, e);
        }
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [io.grpc.MethodDescriptor, io.grpc.MethodDescriptor<ReqT, RespT>] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0052  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <ReqT, RespT> RespT blockingUnaryCall(io.grpc.Channel r3, io.grpc.MethodDescriptor<ReqT, RespT> r4, io.grpc.CallOptions r5, ReqT r6) {
        /*
            io.grpc.stub.ClientCalls$ThreadlessExecutor r0 = new io.grpc.stub.ClientCalls$ThreadlessExecutor
            r0.<init>()
            io.grpc.CallOptions$Key<io.grpc.stub.ClientCalls$StubType> r1 = STUB_TYPE_OPTION
            io.grpc.stub.ClientCalls$StubType r2 = io.grpc.stub.ClientCalls.StubType.BLOCKING
            io.grpc.CallOptions r5 = r5.withOption(r1, r2)
            io.grpc.CallOptions r5 = r5.withExecutor(r0)
            io.grpc.ClientCall r3 = r3.newCall(r4, r5)
            r4 = 0
            com.google.common.util.concurrent.ListenableFuture r5 = futureUnaryCall(r3, r6)     // Catch:{ RuntimeException -> 0x004a, Error -> 0x0048 }
        L_0x001a:
            boolean r6 = r5.isDone()     // Catch:{ RuntimeException -> 0x004a, Error -> 0x0048 }
            if (r6 != 0) goto L_0x0035
            r0.waitAndDrain()     // Catch:{ InterruptedException -> 0x0024 }
            goto L_0x001a
        L_0x0024:
            r4 = move-exception
            r6 = 1
            java.lang.String r1 = "Thread interrupted"
            r3.cancel(r1, r4)     // Catch:{ RuntimeException -> 0x0032, Error -> 0x0030, all -> 0x002d }
            r4 = r6
            goto L_0x001a
        L_0x002d:
            r3 = move-exception
            r4 = r6
            goto L_0x0050
        L_0x0030:
            r5 = move-exception
            goto L_0x0033
        L_0x0032:
            r5 = move-exception
        L_0x0033:
            r4 = r6
            goto L_0x004b
        L_0x0035:
            r0.shutdown()     // Catch:{ RuntimeException -> 0x004a, Error -> 0x0048 }
            java.lang.Object r3 = getUnchecked(r5)     // Catch:{ RuntimeException -> 0x004a, Error -> 0x0048 }
            if (r4 == 0) goto L_0x0045
            java.lang.Thread r4 = java.lang.Thread.currentThread()
            r4.interrupt()
        L_0x0045:
            return r3
        L_0x0046:
            r3 = move-exception
            goto L_0x0050
        L_0x0048:
            r5 = move-exception
            goto L_0x004b
        L_0x004a:
            r5 = move-exception
        L_0x004b:
            java.lang.RuntimeException r3 = cancelThrow(r3, r5)     // Catch:{ all -> 0x0046 }
            throw r3     // Catch:{ all -> 0x0046 }
        L_0x0050:
            if (r4 == 0) goto L_0x0059
            java.lang.Thread r4 = java.lang.Thread.currentThread()
            r4.interrupt()
        L_0x0059:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.stub.ClientCalls.blockingUnaryCall(io.grpc.Channel, io.grpc.MethodDescriptor, io.grpc.CallOptions, java.lang.Object):java.lang.Object");
    }

    public static <ReqT, RespT> Iterator<RespT> blockingServerStreamingCall(ClientCall<ReqT, RespT> clientCall, ReqT reqt) {
        BlockingResponseStream blockingResponseStream = new BlockingResponseStream(clientCall);
        asyncUnaryRequestCall(clientCall, reqt, blockingResponseStream.listener());
        return blockingResponseStream;
    }

    public static <ReqT, RespT> Iterator<RespT> blockingServerStreamingCall(Channel channel, MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, ReqT reqt) {
        ClientCall<RequestT, ResponseT> newCall = channel.newCall(methodDescriptor, callOptions.withOption(STUB_TYPE_OPTION, StubType.BLOCKING));
        BlockingResponseStream blockingResponseStream = new BlockingResponseStream(newCall);
        asyncUnaryRequestCall(newCall, reqt, blockingResponseStream.listener());
        return blockingResponseStream;
    }

    public static <ReqT, RespT> ListenableFuture<RespT> futureUnaryCall(ClientCall<ReqT, RespT> clientCall, ReqT reqt) {
        GrpcFuture grpcFuture = new GrpcFuture(clientCall);
        asyncUnaryRequestCall(clientCall, reqt, new UnaryStreamToFuture(grpcFuture));
        return grpcFuture;
    }

    private static <V> V getUnchecked(Future<V> future) {
        try {
            return future.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw Status.CANCELLED.withDescription("Thread interrupted").withCause(e).asRuntimeException();
        } catch (ExecutionException e2) {
            throw toStatusRuntimeException(e2.getCause());
        }
    }

    private static StatusRuntimeException toStatusRuntimeException(Throwable th) {
        Throwable th2 = (Throwable) Preconditions.checkNotNull(th, "t");
        while (th2 != null) {
            if (th2 instanceof StatusException) {
                StatusException statusException = (StatusException) th2;
                return new StatusRuntimeException(statusException.getStatus(), statusException.getTrailers());
            } else if (th2 instanceof StatusRuntimeException) {
                StatusRuntimeException statusRuntimeException = (StatusRuntimeException) th2;
                return new StatusRuntimeException(statusRuntimeException.getStatus(), statusRuntimeException.getTrailers());
            } else {
                th2 = th2.getCause();
            }
        }
        return Status.UNKNOWN.withDescription("unexpected exception").withCause(th).asRuntimeException();
    }

    private static RuntimeException cancelThrow(ClientCall<?, ?> clientCall, Throwable th) {
        try {
            clientCall.cancel((String) null, th);
        } catch (Error | RuntimeException e) {
            logger.log(Level.SEVERE, "RuntimeException encountered while closing call", e);
        }
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        } else if (th instanceof Error) {
            throw ((Error) th);
        } else {
            throw new AssertionError(th);
        }
    }

    private static <ReqT, RespT> void asyncUnaryRequestCall(ClientCall<ReqT, RespT> clientCall, ReqT reqt, StreamObserver<RespT> streamObserver, boolean z) {
        asyncUnaryRequestCall(clientCall, reqt, new StreamObserverToCallListenerAdapter(streamObserver, new CallToStreamObserverAdapter(clientCall, z)));
    }

    private static <ReqT, RespT> void asyncUnaryRequestCall(ClientCall<ReqT, RespT> clientCall, ReqT reqt, StartableListener<RespT> startableListener) {
        startCall(clientCall, startableListener);
        try {
            clientCall.sendMessage(reqt);
            clientCall.halfClose();
        } catch (Error | RuntimeException e) {
            throw cancelThrow(clientCall, e);
        }
    }

    private static <ReqT, RespT> StreamObserver<ReqT> asyncStreamingRequestCall(ClientCall<ReqT, RespT> clientCall, StreamObserver<RespT> streamObserver, boolean z) {
        CallToStreamObserverAdapter callToStreamObserverAdapter = new CallToStreamObserverAdapter(clientCall, z);
        startCall(clientCall, new StreamObserverToCallListenerAdapter(streamObserver, callToStreamObserverAdapter));
        return callToStreamObserverAdapter;
    }

    private static <ReqT, RespT> void startCall(ClientCall<ReqT, RespT> clientCall, StartableListener<RespT> startableListener) {
        clientCall.start(startableListener, new Metadata());
        startableListener.onStart();
    }

    private static abstract class StartableListener<T> extends ClientCall.Listener<T> {
        /* access modifiers changed from: package-private */
        public abstract void onStart();

        private StartableListener() {
        }
    }

    private static final class CallToStreamObserverAdapter<ReqT> extends ClientCallStreamObserver<ReqT> {
        private boolean aborted = false;
        /* access modifiers changed from: private */
        public boolean autoRequestEnabled = true;
        private final ClientCall<ReqT, ?> call;
        private boolean completed = false;
        private boolean frozen;
        /* access modifiers changed from: private */
        public int initialRequest = 1;
        /* access modifiers changed from: private */
        public Runnable onReadyHandler;
        /* access modifiers changed from: private */
        public final boolean streamingResponse;

        CallToStreamObserverAdapter(ClientCall<ReqT, ?> clientCall, boolean z) {
            this.call = clientCall;
            this.streamingResponse = z;
        }

        /* access modifiers changed from: private */
        public void freeze() {
            this.frozen = true;
        }

        public void onNext(ReqT reqt) {
            Preconditions.checkState(!this.aborted, "Stream was terminated by error, no further calls are allowed");
            Preconditions.checkState(!this.completed, "Stream is already completed, no further calls are allowed");
            this.call.sendMessage(reqt);
        }

        public void onError(Throwable th) {
            this.call.cancel("Cancelled by client with StreamObserver.onError()", th);
            this.aborted = true;
        }

        public void onCompleted() {
            this.call.halfClose();
            this.completed = true;
        }

        public boolean isReady() {
            return this.call.isReady();
        }

        public void setOnReadyHandler(Runnable runnable) {
            if (!this.frozen) {
                this.onReadyHandler = runnable;
                return;
            }
            throw new IllegalStateException("Cannot alter onReadyHandler after call started. Use ClientResponseObserver");
        }

        public void disableAutoInboundFlowControl() {
            disableAutoRequestWithInitial(1);
        }

        public void disableAutoRequestWithInitial(int i) {
            if (!this.frozen) {
                Preconditions.checkArgument(i >= 0, "Initial requests must be non-negative");
                this.initialRequest = i;
                this.autoRequestEnabled = false;
                return;
            }
            throw new IllegalStateException("Cannot disable auto flow control after call started. Use ClientResponseObserver");
        }

        public void request(int i) {
            if (this.streamingResponse || i != 1) {
                this.call.request(i);
            } else {
                this.call.request(2);
            }
        }

        public void setMessageCompression(boolean z) {
            this.call.setMessageCompression(z);
        }

        public void cancel(@Nullable String str, @Nullable Throwable th) {
            this.call.cancel(str, th);
        }
    }

    private static final class StreamObserverToCallListenerAdapter<ReqT, RespT> extends StartableListener<RespT> {
        private final CallToStreamObserverAdapter<ReqT> adapter;
        private boolean firstResponseReceived;
        private final StreamObserver<RespT> observer;

        public void onHeaders(Metadata metadata) {
        }

        StreamObserverToCallListenerAdapter(StreamObserver<RespT> streamObserver, CallToStreamObserverAdapter<ReqT> callToStreamObserverAdapter) {
            super();
            this.observer = streamObserver;
            this.adapter = callToStreamObserverAdapter;
            if (streamObserver instanceof ClientResponseObserver) {
                ((ClientResponseObserver) streamObserver).beforeStart(callToStreamObserverAdapter);
            }
            callToStreamObserverAdapter.freeze();
        }

        public void onMessage(RespT respt) {
            if (!this.firstResponseReceived || this.adapter.streamingResponse) {
                this.firstResponseReceived = true;
                this.observer.onNext(respt);
                if (this.adapter.streamingResponse && this.adapter.autoRequestEnabled) {
                    this.adapter.request(1);
                    return;
                }
                return;
            }
            throw Status.INTERNAL.withDescription("More than one responses received for unary or client-streaming call").asRuntimeException();
        }

        public void onClose(Status status, Metadata metadata) {
            if (status.isOk()) {
                this.observer.onCompleted();
            } else {
                this.observer.onError(status.asRuntimeException(metadata));
            }
        }

        public void onReady() {
            if (this.adapter.onReadyHandler != null) {
                this.adapter.onReadyHandler.run();
            }
        }

        /* access modifiers changed from: package-private */
        public void onStart() {
            if (this.adapter.initialRequest > 0) {
                CallToStreamObserverAdapter<ReqT> callToStreamObserverAdapter = this.adapter;
                callToStreamObserverAdapter.request(callToStreamObserverAdapter.initialRequest);
            }
        }
    }

    private static final class UnaryStreamToFuture<RespT> extends StartableListener<RespT> {
        private boolean isValueReceived = false;
        private final GrpcFuture<RespT> responseFuture;
        private RespT value;

        public void onHeaders(Metadata metadata) {
        }

        UnaryStreamToFuture(GrpcFuture<RespT> grpcFuture) {
            super();
            this.responseFuture = grpcFuture;
        }

        public void onMessage(RespT respt) {
            if (!this.isValueReceived) {
                this.value = respt;
                this.isValueReceived = true;
                return;
            }
            throw Status.INTERNAL.withDescription("More than one value received for unary call").asRuntimeException();
        }

        public void onClose(Status status, Metadata metadata) {
            if (status.isOk()) {
                if (!this.isValueReceived) {
                    this.responseFuture.setException(Status.INTERNAL.withDescription("No value received for unary call").asRuntimeException(metadata));
                }
                this.responseFuture.set(this.value);
                return;
            }
            this.responseFuture.setException(status.asRuntimeException(metadata));
        }

        /* access modifiers changed from: package-private */
        public void onStart() {
            this.responseFuture.call.request(2);
        }
    }

    private static final class GrpcFuture<RespT> extends AbstractFuture<RespT> {
        /* access modifiers changed from: private */
        public final ClientCall<?, RespT> call;

        GrpcFuture(ClientCall<?, RespT> clientCall) {
            this.call = clientCall;
        }

        /* access modifiers changed from: protected */
        public void interruptTask() {
            this.call.cancel("GrpcFuture was cancelled", (Throwable) null);
        }

        /* access modifiers changed from: protected */
        public boolean set(@Nullable RespT respt) {
            return super.set(respt);
        }

        /* access modifiers changed from: protected */
        public boolean setException(Throwable th) {
            return super.setException(th);
        }

        /* access modifiers changed from: protected */
        public String pendingToString() {
            return MoreObjects.toStringHelper((Object) this).add("clientCall", (Object) this.call).toString();
        }
    }

    private static final class BlockingResponseStream<T> implements Iterator<T> {
        /* access modifiers changed from: private */
        public final BlockingQueue<Object> buffer = new ArrayBlockingQueue(3);
        /* access modifiers changed from: private */
        public final ClientCall<?, T> call;
        private Object last;
        private final StartableListener<T> listener = new QueuingListener();

        BlockingResponseStream(ClientCall<?, T> clientCall) {
            this.call = clientCall;
        }

        /* access modifiers changed from: package-private */
        public StartableListener<T> listener() {
            return this.listener;
        }

        /* JADX WARNING: Removed duplicated region for block: B:15:0x0024  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private java.lang.Object waitForNext() {
            /*
                r5 = this;
                r0 = 0
            L_0x0001:
                java.util.concurrent.BlockingQueue<java.lang.Object> r1 = r5.buffer     // Catch:{ InterruptedException -> 0x0013, all -> 0x0011 }
                java.lang.Object r1 = r1.take()     // Catch:{ InterruptedException -> 0x0013, all -> 0x0011 }
                if (r0 == 0) goto L_0x0010
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                r0.interrupt()
            L_0x0010:
                return r1
            L_0x0011:
                r1 = move-exception
                goto L_0x0022
            L_0x0013:
                r0 = move-exception
                r1 = 1
                io.grpc.ClientCall<?, T> r2 = r5.call     // Catch:{ all -> 0x001e }
                java.lang.String r3 = "Thread interrupted"
                r2.cancel(r3, r0)     // Catch:{ all -> 0x001e }
                r0 = r1
                goto L_0x0001
            L_0x001e:
                r0 = move-exception
                r4 = r1
                r1 = r0
                r0 = r4
            L_0x0022:
                if (r0 == 0) goto L_0x002b
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                r0.interrupt()
            L_0x002b:
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.stub.ClientCalls.BlockingResponseStream.waitForNext():java.lang.Object");
        }

        public boolean hasNext() {
            Object obj;
            while (true) {
                obj = this.last;
                if (obj != null) {
                    break;
                }
                this.last = waitForNext();
            }
            if (!(obj instanceof StatusRuntimeException)) {
                return obj != this;
            }
            StatusRuntimeException statusRuntimeException = (StatusRuntimeException) obj;
            throw statusRuntimeException.getStatus().asRuntimeException(statusRuntimeException.getTrailers());
        }

        public T next() {
            Object obj = this.last;
            if (!(obj instanceof StatusRuntimeException) && obj != this) {
                this.call.request(1);
            }
            if (hasNext()) {
                T t = this.last;
                this.last = null;
                return t;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        private final class QueuingListener extends StartableListener<T> {
            private boolean done = false;

            public void onHeaders(Metadata metadata) {
            }

            QueuingListener() {
                super();
            }

            public void onMessage(T t) {
                Preconditions.checkState(!this.done, "ClientCall already closed");
                BlockingResponseStream.this.buffer.add(t);
            }

            public void onClose(Status status, Metadata metadata) {
                Preconditions.checkState(!this.done, "ClientCall already closed");
                if (status.isOk()) {
                    BlockingResponseStream.this.buffer.add(BlockingResponseStream.this);
                } else {
                    BlockingResponseStream.this.buffer.add(status.asRuntimeException(metadata));
                }
                this.done = true;
            }

            /* access modifiers changed from: package-private */
            public void onStart() {
                BlockingResponseStream.this.call.request(1);
            }
        }
    }

    private static final class ThreadlessExecutor extends ConcurrentLinkedQueue<Runnable> implements Executor {
        private static final Object SHUTDOWN = new Object();
        private static final Logger log = Logger.getLogger(ThreadlessExecutor.class.getName());
        private volatile Object waiter;

        ThreadlessExecutor() {
        }

        /* JADX INFO: finally extract failed */
        public void waitAndDrain() throws InterruptedException {
            Runnable runnable;
            throwIfInterrupted();
            Runnable runnable2 = (Runnable) poll();
            if (runnable2 == null) {
                this.waiter = Thread.currentThread();
                while (true) {
                    try {
                        runnable = (Runnable) poll();
                        if (runnable != null) {
                            break;
                        }
                        LockSupport.park(this);
                        throwIfInterrupted();
                    } catch (Throwable th) {
                        this.waiter = null;
                        throw th;
                    }
                }
                this.waiter = null;
                runnable2 = runnable;
            }
            do {
                runQuietly(runnable2);
                runnable2 = (Runnable) poll();
            } while (runnable2 != null);
        }

        public void shutdown() {
            this.waiter = SHUTDOWN;
            while (true) {
                Runnable runnable = (Runnable) poll();
                if (runnable != null) {
                    runQuietly(runnable);
                } else {
                    return;
                }
            }
        }

        private static void runQuietly(Runnable runnable) {
            try {
                runnable.run();
            } catch (Throwable th) {
                log.log(Level.WARNING, "Runnable threw exception", th);
            }
        }

        private static void throwIfInterrupted() throws InterruptedException {
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
        }

        public void execute(Runnable runnable) {
            add(runnable);
            Object obj = this.waiter;
            if (obj != SHUTDOWN) {
                LockSupport.unpark((Thread) obj);
            } else if (remove(runnable) && ClientCalls.rejectRunnableOnExecutor) {
                throw new RejectedExecutionException();
            }
        }
    }
}
