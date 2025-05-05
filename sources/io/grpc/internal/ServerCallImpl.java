package io.grpc.internal;

import androidx.core.app.NotificationCompat;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.util.concurrent.MoreExecutors;
import io.grpc.Attributes;
import io.grpc.Codec;
import io.grpc.Compressor;
import io.grpc.CompressorRegistry;
import io.grpc.Context;
import io.grpc.DecompressorRegistry;
import io.grpc.InternalDecompressorRegistry;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.SecurityLevel;
import io.grpc.ServerCall;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.internal.StreamListener;
import io.perfmark.PerfMark;
import io.perfmark.Tag;
import io.perfmark.TaskCloseable;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

final class ServerCallImpl<ReqT, RespT> extends ServerCall<ReqT, RespT> {
    static final String MISSING_RESPONSE = "Completed without a response";
    static final String TOO_MANY_RESPONSES = "Too many responses";
    private static final Logger log = Logger.getLogger(ServerCallImpl.class.getName());
    /* access modifiers changed from: private */
    public volatile boolean cancelled;
    private boolean closeCalled;
    private Compressor compressor;
    private final CompressorRegistry compressorRegistry;
    private final Context.CancellableContext context;
    private final DecompressorRegistry decompressorRegistry;
    private final byte[] messageAcceptEncoding;
    private boolean messageSent;
    /* access modifiers changed from: private */
    public final MethodDescriptor<ReqT, RespT> method;
    private boolean sendHeadersCalled;
    private CallTracer serverCallTracer;
    private final ServerStream stream;
    /* access modifiers changed from: private */
    public final Tag tag;

    ServerCallImpl(ServerStream serverStream, MethodDescriptor<ReqT, RespT> methodDescriptor, Metadata metadata, Context.CancellableContext cancellableContext, DecompressorRegistry decompressorRegistry2, CompressorRegistry compressorRegistry2, CallTracer callTracer, Tag tag2) {
        this.stream = serverStream;
        this.method = methodDescriptor;
        this.context = cancellableContext;
        this.messageAcceptEncoding = (byte[]) metadata.get(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY);
        this.decompressorRegistry = decompressorRegistry2;
        this.compressorRegistry = compressorRegistry2;
        this.serverCallTracer = callTracer;
        callTracer.reportCallStarted();
        this.tag = tag2;
    }

    public void request(int i) {
        TaskCloseable traceTask = PerfMark.traceTask("ServerCall.request");
        try {
            PerfMark.attachTag(this.tag);
            this.stream.request(i);
            if (traceTask != null) {
                traceTask.close();
                return;
            }
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public void sendHeaders(Metadata metadata) {
        TaskCloseable traceTask = PerfMark.traceTask("ServerCall.sendHeaders");
        try {
            PerfMark.attachTag(this.tag);
            sendHeadersInternal(metadata);
            if (traceTask != null) {
                traceTask.close();
                return;
            }
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private void sendHeadersInternal(Metadata metadata) {
        Preconditions.checkState(!this.sendHeadersCalled, "sendHeaders has already been called");
        Preconditions.checkState(!this.closeCalled, "call is closed");
        metadata.discardAll(GrpcUtil.CONTENT_LENGTH_KEY);
        metadata.discardAll(GrpcUtil.MESSAGE_ENCODING_KEY);
        if (this.compressor == null) {
            this.compressor = Codec.Identity.NONE;
        } else if (this.messageAcceptEncoding == null) {
            this.compressor = Codec.Identity.NONE;
        } else if (!GrpcUtil.iterableContains(GrpcUtil.ACCEPT_ENCODING_SPLITTER.split(new String(this.messageAcceptEncoding, GrpcUtil.US_ASCII)), this.compressor.getMessageEncoding())) {
            this.compressor = Codec.Identity.NONE;
        }
        metadata.put(GrpcUtil.MESSAGE_ENCODING_KEY, this.compressor.getMessageEncoding());
        this.stream.setCompressor(this.compressor);
        metadata.discardAll(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY);
        byte[] rawAdvertisedMessageEncodings = InternalDecompressorRegistry.getRawAdvertisedMessageEncodings(this.decompressorRegistry);
        if (rawAdvertisedMessageEncodings.length != 0) {
            metadata.put(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY, rawAdvertisedMessageEncodings);
        }
        this.sendHeadersCalled = true;
        this.stream.writeHeaders(metadata, true ^ getMethodDescriptor().getType().serverSendsOneMessage());
    }

    public void sendMessage(RespT respt) {
        TaskCloseable traceTask = PerfMark.traceTask("ServerCall.sendMessage");
        try {
            PerfMark.attachTag(this.tag);
            sendMessageInternal(respt);
            if (traceTask != null) {
                traceTask.close();
                return;
            }
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private void sendMessageInternal(RespT respt) {
        Preconditions.checkState(this.sendHeadersCalled, "sendHeaders has not been called");
        Preconditions.checkState(!this.closeCalled, "call is closed");
        if (!this.method.getType().serverSendsOneMessage() || !this.messageSent) {
            this.messageSent = true;
            try {
                this.stream.writeMessage(this.method.streamResponse(respt));
                if (!getMethodDescriptor().getType().serverSendsOneMessage()) {
                    this.stream.flush();
                }
            } catch (RuntimeException e) {
                handleInternalError(e);
            } catch (Error e2) {
                close(Status.CANCELLED.withDescription("Server sendMessage() failed with Error"), new Metadata());
                throw e2;
            }
        } else {
            handleInternalError(Status.INTERNAL.withDescription(TOO_MANY_RESPONSES).asRuntimeException());
        }
    }

    public void setMessageCompression(boolean z) {
        this.stream.setMessageCompression(z);
    }

    public void setCompression(String str) {
        boolean z = true;
        Preconditions.checkState(!this.sendHeadersCalled, "sendHeaders has been called");
        Compressor lookupCompressor = this.compressorRegistry.lookupCompressor(str);
        this.compressor = lookupCompressor;
        if (lookupCompressor == null) {
            z = false;
        }
        Preconditions.checkArgument(z, "Unable to find compressor by name %s", (Object) str);
    }

    public boolean isReady() {
        if (this.closeCalled) {
            return false;
        }
        return this.stream.isReady();
    }

    public void close(Status status, Metadata metadata) {
        TaskCloseable traceTask = PerfMark.traceTask("ServerCall.close");
        try {
            PerfMark.attachTag(this.tag);
            closeInternal(status, metadata);
            if (traceTask != null) {
                traceTask.close();
                return;
            }
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private void closeInternal(Status status, Metadata metadata) {
        Preconditions.checkState(!this.closeCalled, "call already closed");
        try {
            this.closeCalled = true;
            if (!status.isOk() || !this.method.getType().serverSendsOneMessage() || this.messageSent) {
                this.stream.close(status, metadata);
                this.serverCallTracer.reportCallEnded(status.isOk());
                return;
            }
            handleInternalError(Status.INTERNAL.withDescription(MISSING_RESPONSE).asRuntimeException());
        } finally {
            this.serverCallTracer.reportCallEnded(status.isOk());
        }
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    /* access modifiers changed from: package-private */
    public ServerStreamListener newServerStreamListener(ServerCall.Listener<ReqT> listener) {
        return new ServerStreamListenerImpl(this, listener, this.context);
    }

    public Attributes getAttributes() {
        return this.stream.getAttributes();
    }

    public String getAuthority() {
        return this.stream.getAuthority();
    }

    public MethodDescriptor<ReqT, RespT> getMethodDescriptor() {
        return this.method;
    }

    public SecurityLevel getSecurityLevel() {
        Attributes attributes = getAttributes();
        if (attributes == null) {
            return super.getSecurityLevel();
        }
        SecurityLevel securityLevel = (SecurityLevel) attributes.get(GrpcAttributes.ATTR_SECURITY_LEVEL);
        return securityLevel == null ? super.getSecurityLevel() : securityLevel;
    }

    private void handleInternalError(Throwable th) {
        Status status;
        log.log(Level.WARNING, "Cancelling the stream because of internal error", th);
        if (th instanceof StatusRuntimeException) {
            status = ((StatusRuntimeException) th).getStatus();
        } else {
            status = Status.INTERNAL.withCause(th).withDescription("Internal error so cancelling stream.");
        }
        this.stream.cancel(status);
        this.serverCallTracer.reportCallEnded(false);
    }

    static final class ServerStreamListenerImpl<ReqT> implements ServerStreamListener {
        /* access modifiers changed from: private */
        public final ServerCallImpl<ReqT, ?> call;
        private final Context.CancellableContext context;
        private final ServerCall.Listener<ReqT> listener;

        public ServerStreamListenerImpl(ServerCallImpl<ReqT, ?> serverCallImpl, ServerCall.Listener<ReqT> listener2, Context.CancellableContext cancellableContext) {
            this.call = (ServerCallImpl) Preconditions.checkNotNull(serverCallImpl, NotificationCompat.CATEGORY_CALL);
            this.listener = (ServerCall.Listener) Preconditions.checkNotNull(listener2, "listener must not be null");
            Context.CancellableContext cancellableContext2 = (Context.CancellableContext) Preconditions.checkNotNull(cancellableContext, "context");
            this.context = cancellableContext2;
            cancellableContext2.addListener(new Context.CancellationListener() {
                public void cancelled(Context context) {
                    if (context.cancellationCause() != null) {
                        boolean unused = ServerStreamListenerImpl.this.call.cancelled = true;
                    }
                }
            }, MoreExecutors.directExecutor());
        }

        public void messagesAvailable(StreamListener.MessageProducer messageProducer) {
            TaskCloseable traceTask = PerfMark.traceTask("ServerStreamListener.messagesAvailable");
            try {
                PerfMark.attachTag(this.call.tag);
                messagesAvailableInternal(messageProducer);
                if (traceTask != null) {
                    traceTask.close();
                    return;
                }
                return;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        }

        private void messagesAvailableInternal(StreamListener.MessageProducer messageProducer) {
            InputStream next;
            if (this.call.cancelled) {
                GrpcUtil.closeQuietly(messageProducer);
                return;
            }
            while (true) {
                try {
                    next = messageProducer.next();
                    if (next != null) {
                        this.listener.onMessage(this.call.method.parseRequest(next));
                        next.close();
                    } else {
                        return;
                    }
                } catch (Throwable th) {
                    GrpcUtil.closeQuietly(messageProducer);
                    Throwables.throwIfUnchecked(th);
                    throw new RuntimeException(th);
                }
            }
        }

        public void halfClosed() {
            TaskCloseable traceTask = PerfMark.traceTask("ServerStreamListener.halfClosed");
            try {
                PerfMark.attachTag(this.call.tag);
                if (!this.call.cancelled) {
                    this.listener.onHalfClose();
                    if (traceTask != null) {
                        traceTask.close();
                        return;
                    }
                    return;
                } else if (traceTask != null) {
                    traceTask.close();
                    return;
                } else {
                    return;
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        }

        public void closed(Status status) {
            TaskCloseable traceTask = PerfMark.traceTask("ServerStreamListener.closed");
            try {
                PerfMark.attachTag(this.call.tag);
                closedInternal(status);
                if (traceTask != null) {
                    traceTask.close();
                    return;
                }
                return;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Throwable, io.grpc.Metadata] */
        /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable] */
        /* JADX WARNING: type inference failed for: r0v3 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void closedInternal(io.grpc.Status r3) {
            /*
                r2 = this;
                r0 = 0
                boolean r3 = r3.isOk()     // Catch:{ all -> 0x002b }
                if (r3 == 0) goto L_0x000d
                io.grpc.ServerCall$Listener<ReqT> r3 = r2.listener     // Catch:{ all -> 0x002b }
                r3.onComplete()     // Catch:{ all -> 0x002b }
                goto L_0x0025
            L_0x000d:
                io.grpc.internal.ServerCallImpl<ReqT, ?> r3 = r2.call     // Catch:{ all -> 0x002b }
                r1 = 1
                boolean unused = r3.cancelled = r1     // Catch:{ all -> 0x002b }
                io.grpc.ServerCall$Listener<ReqT> r3 = r2.listener     // Catch:{ all -> 0x002b }
                r3.onCancel()     // Catch:{ all -> 0x002b }
                io.grpc.Status r3 = io.grpc.Status.CANCELLED     // Catch:{ all -> 0x002b }
                java.lang.String r1 = "RPC cancelled"
                io.grpc.Status r3 = r3.withDescription(r1)     // Catch:{ all -> 0x002b }
                r1 = 0
                io.grpc.StatusRuntimeException r0 = io.grpc.InternalStatus.asRuntimeException(r3, r0, r1)     // Catch:{ all -> 0x002b }
            L_0x0025:
                io.grpc.Context$CancellableContext r3 = r2.context
                r3.cancel(r0)
                return
            L_0x002b:
                r3 = move-exception
                io.grpc.Context$CancellableContext r1 = r2.context
                r1.cancel(r0)
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.ServerCallImpl.ServerStreamListenerImpl.closedInternal(io.grpc.Status):void");
        }

        public void onReady() {
            TaskCloseable traceTask = PerfMark.traceTask("ServerStreamListener.onReady");
            try {
                PerfMark.attachTag(this.call.tag);
                if (!this.call.cancelled) {
                    this.listener.onReady();
                    if (traceTask != null) {
                        traceTask.close();
                        return;
                    }
                    return;
                } else if (traceTask != null) {
                    traceTask.close();
                    return;
                } else {
                    return;
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        }
    }
}
