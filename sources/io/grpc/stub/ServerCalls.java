package io.grpc.stub;

import com.google.common.base.Preconditions;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.Status;

public final class ServerCalls {
    static final String MISSING_REQUEST = "Half-closed without a request";
    static final String TOO_MANY_REQUESTS = "Too many requests";

    public interface BidiStreamingMethod<ReqT, RespT> extends StreamingRequestMethod<ReqT, RespT> {
        StreamObserver<ReqT> invoke(StreamObserver<RespT> streamObserver);
    }

    public interface ClientStreamingMethod<ReqT, RespT> extends StreamingRequestMethod<ReqT, RespT> {
        StreamObserver<ReqT> invoke(StreamObserver<RespT> streamObserver);
    }

    public interface ServerStreamingMethod<ReqT, RespT> extends UnaryRequestMethod<ReqT, RespT> {
        void invoke(ReqT reqt, StreamObserver<RespT> streamObserver);
    }

    private interface StreamingRequestMethod<ReqT, RespT> {
        StreamObserver<ReqT> invoke(StreamObserver<RespT> streamObserver);
    }

    public interface UnaryMethod<ReqT, RespT> extends UnaryRequestMethod<ReqT, RespT> {
        void invoke(ReqT reqt, StreamObserver<RespT> streamObserver);
    }

    private interface UnaryRequestMethod<ReqT, RespT> {
        void invoke(ReqT reqt, StreamObserver<RespT> streamObserver);
    }

    private ServerCalls() {
    }

    public static <ReqT, RespT> ServerCallHandler<ReqT, RespT> asyncUnaryCall(UnaryMethod<ReqT, RespT> unaryMethod) {
        return new UnaryServerCallHandler(unaryMethod, false);
    }

    public static <ReqT, RespT> ServerCallHandler<ReqT, RespT> asyncServerStreamingCall(ServerStreamingMethod<ReqT, RespT> serverStreamingMethod) {
        return new UnaryServerCallHandler(serverStreamingMethod, true);
    }

    public static <ReqT, RespT> ServerCallHandler<ReqT, RespT> asyncClientStreamingCall(ClientStreamingMethod<ReqT, RespT> clientStreamingMethod) {
        return new StreamingServerCallHandler(clientStreamingMethod, false);
    }

    public static <ReqT, RespT> ServerCallHandler<ReqT, RespT> asyncBidiStreamingCall(BidiStreamingMethod<ReqT, RespT> bidiStreamingMethod) {
        return new StreamingServerCallHandler(bidiStreamingMethod, true);
    }

    private static final class UnaryServerCallHandler<ReqT, RespT> implements ServerCallHandler<ReqT, RespT> {
        /* access modifiers changed from: private */
        public final UnaryRequestMethod<ReqT, RespT> method;
        private final boolean serverStreaming;

        UnaryServerCallHandler(UnaryRequestMethod<ReqT, RespT> unaryRequestMethod, boolean z) {
            this.method = unaryRequestMethod;
            this.serverStreaming = z;
        }

        public ServerCall.Listener<ReqT> startCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata) {
            Preconditions.checkArgument(serverCall.getMethodDescriptor().getType().clientSendsOneMessage(), "asyncUnaryRequestCall is only for clientSendsOneMessage methods");
            ServerCallStreamObserverImpl serverCallStreamObserverImpl = new ServerCallStreamObserverImpl(serverCall, this.serverStreaming);
            serverCall.request(2);
            return new UnaryServerCallListener(serverCallStreamObserverImpl, serverCall);
        }

        private final class UnaryServerCallListener extends ServerCall.Listener<ReqT> {
            private final ServerCall<ReqT, RespT> call;
            private boolean canInvoke = true;
            private ReqT request;
            private final ServerCallStreamObserverImpl<ReqT, RespT> responseObserver;
            private boolean wasReady;

            UnaryServerCallListener(ServerCallStreamObserverImpl<ReqT, RespT> serverCallStreamObserverImpl, ServerCall<ReqT, RespT> serverCall) {
                this.call = serverCall;
                this.responseObserver = serverCallStreamObserverImpl;
            }

            public void onMessage(ReqT reqt) {
                if (this.request != null) {
                    this.call.close(Status.INTERNAL.withDescription(ServerCalls.TOO_MANY_REQUESTS), new Metadata());
                    this.canInvoke = false;
                    return;
                }
                this.request = reqt;
            }

            public void onHalfClose() {
                if (this.canInvoke) {
                    if (this.request == null) {
                        this.call.close(Status.INTERNAL.withDescription(ServerCalls.MISSING_REQUEST), new Metadata());
                        return;
                    }
                    UnaryServerCallHandler.this.method.invoke(this.request, this.responseObserver);
                    this.request = null;
                    this.responseObserver.freeze();
                    if (this.wasReady) {
                        onReady();
                    }
                }
            }

            public void onCancel() {
                if (this.responseObserver.onCancelHandler != null) {
                    this.responseObserver.onCancelHandler.run();
                } else {
                    this.responseObserver.cancelled = true;
                }
            }

            public void onReady() {
                this.wasReady = true;
                if (this.responseObserver.onReadyHandler != null) {
                    this.responseObserver.onReadyHandler.run();
                }
            }

            public void onComplete() {
                if (this.responseObserver.onCloseHandler != null) {
                    this.responseObserver.onCloseHandler.run();
                }
            }
        }
    }

    private static final class StreamingServerCallHandler<ReqT, RespT> implements ServerCallHandler<ReqT, RespT> {
        private final boolean bidi;
        private final StreamingRequestMethod<ReqT, RespT> method;

        StreamingServerCallHandler(StreamingRequestMethod<ReqT, RespT> streamingRequestMethod, boolean z) {
            this.method = streamingRequestMethod;
            this.bidi = z;
        }

        public ServerCall.Listener<ReqT> startCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata) {
            ServerCallStreamObserverImpl serverCallStreamObserverImpl = new ServerCallStreamObserverImpl(serverCall, this.bidi);
            StreamObserver<ReqT> invoke = this.method.invoke(serverCallStreamObserverImpl);
            serverCallStreamObserverImpl.freeze();
            if (serverCallStreamObserverImpl.autoRequestEnabled) {
                serverCall.request(1);
            }
            return new StreamingServerCallListener(invoke, serverCallStreamObserverImpl, serverCall);
        }

        private final class StreamingServerCallListener extends ServerCall.Listener<ReqT> {
            private final ServerCall<ReqT, RespT> call;
            private boolean halfClosed = false;
            private final StreamObserver<ReqT> requestObserver;
            private final ServerCallStreamObserverImpl<ReqT, RespT> responseObserver;

            StreamingServerCallListener(StreamObserver<ReqT> streamObserver, ServerCallStreamObserverImpl<ReqT, RespT> serverCallStreamObserverImpl, ServerCall<ReqT, RespT> serverCall) {
                this.requestObserver = streamObserver;
                this.responseObserver = serverCallStreamObserverImpl;
                this.call = serverCall;
            }

            public void onMessage(ReqT reqt) {
                this.requestObserver.onNext(reqt);
                if (this.responseObserver.autoRequestEnabled) {
                    this.call.request(1);
                }
            }

            public void onHalfClose() {
                this.halfClosed = true;
                this.requestObserver.onCompleted();
            }

            public void onCancel() {
                if (this.responseObserver.onCancelHandler != null) {
                    this.responseObserver.onCancelHandler.run();
                } else {
                    this.responseObserver.cancelled = true;
                }
                if (!this.halfClosed) {
                    this.requestObserver.onError(Status.CANCELLED.withDescription("client cancelled").asRuntimeException());
                }
            }

            public void onReady() {
                if (this.responseObserver.onReadyHandler != null) {
                    this.responseObserver.onReadyHandler.run();
                }
            }

            public void onComplete() {
                if (this.responseObserver.onCloseHandler != null) {
                    this.responseObserver.onCloseHandler.run();
                }
            }
        }
    }

    private static final class ServerCallStreamObserverImpl<ReqT, RespT> extends ServerCallStreamObserver<RespT> {
        private boolean aborted = false;
        /* access modifiers changed from: private */
        public boolean autoRequestEnabled = true;
        final ServerCall<ReqT, RespT> call;
        volatile boolean cancelled;
        private boolean completed = false;
        private boolean frozen;
        /* access modifiers changed from: private */
        public Runnable onCancelHandler;
        /* access modifiers changed from: private */
        public Runnable onCloseHandler;
        /* access modifiers changed from: private */
        public Runnable onReadyHandler;
        private boolean sentHeaders;
        private final boolean serverStreamingOrBidi;

        ServerCallStreamObserverImpl(ServerCall<ReqT, RespT> serverCall, boolean z) {
            this.call = serverCall;
            this.serverStreamingOrBidi = z;
        }

        /* access modifiers changed from: private */
        public void freeze() {
            this.frozen = true;
        }

        public void setMessageCompression(boolean z) {
            this.call.setMessageCompression(z);
        }

        public void setCompression(String str) {
            this.call.setCompression(str);
        }

        public void onNext(RespT respt) {
            if (!this.cancelled || !this.serverStreamingOrBidi) {
                Preconditions.checkState(!this.aborted, "Stream was terminated by error, no further calls are allowed");
                Preconditions.checkState(!this.completed, "Stream is already completed, no further calls are allowed");
                if (!this.sentHeaders) {
                    this.call.sendHeaders(new Metadata());
                    this.sentHeaders = true;
                }
                this.call.sendMessage(respt);
                return;
            }
            throw Status.CANCELLED.withDescription("call already cancelled. Use ServerCallStreamObserver.setOnCancelHandler() to disable this exception").asRuntimeException();
        }

        public void onError(Throwable th) {
            Metadata trailersFromThrowable = Status.trailersFromThrowable(th);
            if (trailersFromThrowable == null) {
                trailersFromThrowable = new Metadata();
            }
            this.call.close(Status.fromThrowable(th), trailersFromThrowable);
            this.aborted = true;
        }

        public void onCompleted() {
            this.call.close(Status.OK, new Metadata());
            this.completed = true;
        }

        public boolean isReady() {
            return this.call.isReady();
        }

        public void setOnReadyHandler(Runnable runnable) {
            Preconditions.checkState(!this.frozen, "Cannot alter onReadyHandler after initialization. May only be called during the initial call to the application, before the service returns its StreamObserver");
            this.onReadyHandler = runnable;
        }

        public boolean isCancelled() {
            return this.call.isCancelled();
        }

        public void setOnCancelHandler(Runnable runnable) {
            Preconditions.checkState(!this.frozen, "Cannot alter onCancelHandler after initialization. May only be called during the initial call to the application, before the service returns its StreamObserver");
            this.onCancelHandler = runnable;
        }

        public void disableAutoInboundFlowControl() {
            disableAutoRequest();
        }

        public void disableAutoRequest() {
            Preconditions.checkState(!this.frozen, "Cannot disable auto flow control after initialization");
            this.autoRequestEnabled = false;
        }

        public void request(int i) {
            this.call.request(i);
        }

        public void setOnCloseHandler(Runnable runnable) {
            Preconditions.checkState(!this.frozen, "Cannot alter onCloseHandler after initialization. May only be called during the initial call to the application, before the service returns its StreamObserver");
            this.onCloseHandler = runnable;
        }
    }

    public static void asyncUnimplementedUnaryCall(MethodDescriptor<?, ?> methodDescriptor, StreamObserver<?> streamObserver) {
        Preconditions.checkNotNull(methodDescriptor, "methodDescriptor");
        Preconditions.checkNotNull(streamObserver, "responseObserver");
        streamObserver.onError(Status.UNIMPLEMENTED.withDescription(String.format("Method %s is unimplemented", new Object[]{methodDescriptor.getFullMethodName()})).asRuntimeException());
    }

    public static <ReqT> StreamObserver<ReqT> asyncUnimplementedStreamingCall(MethodDescriptor<?, ?> methodDescriptor, StreamObserver<?> streamObserver) {
        asyncUnimplementedUnaryCall(methodDescriptor, streamObserver);
        return new NoopStreamObserver();
    }

    static class NoopStreamObserver<V> implements StreamObserver<V> {
        public void onCompleted() {
        }

        public void onError(Throwable th) {
        }

        public void onNext(V v) {
        }

        NoopStreamObserver() {
        }
    }
}
