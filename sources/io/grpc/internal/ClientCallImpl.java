package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.ClientCall;
import io.grpc.ClientStreamTracer;
import io.grpc.Codec;
import io.grpc.Compressor;
import io.grpc.CompressorRegistry;
import io.grpc.Context;
import io.grpc.Contexts;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.InternalConfigSelector;
import io.grpc.InternalDecompressorRegistry;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.ManagedChannelServiceConfig;
import io.grpc.internal.StreamListener;
import io.perfmark.Link;
import io.perfmark.PerfMark;
import io.perfmark.Tag;
import io.perfmark.TaskCloseable;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import org.apache.commons.codec.CharEncoding;

final class ClientCallImpl<ReqT, RespT> extends ClientCall<ReqT, RespT> {
    private static final byte[] FULL_STREAM_DECOMPRESSION_ENCODINGS = "gzip".getBytes(Charset.forName(CharEncoding.US_ASCII));
    /* access modifiers changed from: private */
    public static final double NANO_TO_SECS = (((double) TimeUnit.SECONDS.toNanos(1)) * 1.0d);
    private static final Logger log = Logger.getLogger(ClientCallImpl.class.getName());
    /* access modifiers changed from: private */
    public final Executor callExecutor;
    private final boolean callExecutorIsDirect;
    /* access modifiers changed from: private */
    public CallOptions callOptions;
    private boolean cancelCalled;
    /* access modifiers changed from: private */
    public volatile boolean cancelListenersShouldBeRemoved;
    private final ClientCallImpl<ReqT, RespT>.ContextCancellationListener cancellationListener = new ContextCancellationListener();
    /* access modifiers changed from: private */
    public final CallTracer channelCallsTracer;
    private final ClientStreamProvider clientStreamProvider;
    private CompressorRegistry compressorRegistry = CompressorRegistry.getDefaultInstance();
    /* access modifiers changed from: private */
    public final Context context;
    private final ScheduledExecutorService deadlineCancellationExecutor;
    private volatile ScheduledFuture<?> deadlineCancellationFuture;
    private DecompressorRegistry decompressorRegistry = DecompressorRegistry.getDefaultInstance();
    private boolean fullStreamDecompression;
    private boolean halfCloseCalled;
    /* access modifiers changed from: private */
    public final MethodDescriptor<ReqT, RespT> method;
    /* access modifiers changed from: private */
    public ClientStream stream;
    /* access modifiers changed from: private */
    public final Tag tag;
    private final boolean unaryRequest;

    interface ClientStreamProvider {
        ClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, CallOptions callOptions, Metadata metadata, Context context);
    }

    ClientCallImpl(MethodDescriptor<ReqT, RespT> methodDescriptor, Executor executor, CallOptions callOptions2, ClientStreamProvider clientStreamProvider2, ScheduledExecutorService scheduledExecutorService, CallTracer callTracer, @Nullable InternalConfigSelector internalConfigSelector) {
        this.method = methodDescriptor;
        Tag createTag = PerfMark.createTag(methodDescriptor.getFullMethodName(), (long) System.identityHashCode(this));
        this.tag = createTag;
        boolean z = true;
        if (executor == MoreExecutors.directExecutor()) {
            this.callExecutor = new SerializeReentrantCallsDirectExecutor();
            this.callExecutorIsDirect = true;
        } else {
            this.callExecutor = new SerializingExecutor(executor);
            this.callExecutorIsDirect = false;
        }
        this.channelCallsTracer = callTracer;
        this.context = Context.current();
        if (!(methodDescriptor.getType() == MethodDescriptor.MethodType.UNARY || methodDescriptor.getType() == MethodDescriptor.MethodType.SERVER_STREAMING)) {
            z = false;
        }
        this.unaryRequest = z;
        this.callOptions = callOptions2;
        this.clientStreamProvider = clientStreamProvider2;
        this.deadlineCancellationExecutor = scheduledExecutorService;
        PerfMark.event("ClientCall.<init>", createTag);
    }

    private final class ContextCancellationListener implements Context.CancellationListener {
        private ContextCancellationListener() {
        }

        public void cancelled(Context context) {
            ClientCallImpl.this.stream.cancel(Contexts.statusFromCancelled(context));
        }
    }

    /* access modifiers changed from: package-private */
    public ClientCallImpl<ReqT, RespT> setFullStreamDecompression(boolean z) {
        this.fullStreamDecompression = z;
        return this;
    }

    /* access modifiers changed from: package-private */
    public ClientCallImpl<ReqT, RespT> setDecompressorRegistry(DecompressorRegistry decompressorRegistry2) {
        this.decompressorRegistry = decompressorRegistry2;
        return this;
    }

    /* access modifiers changed from: package-private */
    public ClientCallImpl<ReqT, RespT> setCompressorRegistry(CompressorRegistry compressorRegistry2) {
        this.compressorRegistry = compressorRegistry2;
        return this;
    }

    static void prepareHeaders(Metadata metadata, DecompressorRegistry decompressorRegistry2, Compressor compressor, boolean z) {
        metadata.discardAll(GrpcUtil.CONTENT_LENGTH_KEY);
        metadata.discardAll(GrpcUtil.MESSAGE_ENCODING_KEY);
        if (compressor != Codec.Identity.NONE) {
            metadata.put(GrpcUtil.MESSAGE_ENCODING_KEY, compressor.getMessageEncoding());
        }
        metadata.discardAll(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY);
        byte[] rawAdvertisedMessageEncodings = InternalDecompressorRegistry.getRawAdvertisedMessageEncodings(decompressorRegistry2);
        if (rawAdvertisedMessageEncodings.length != 0) {
            metadata.put(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY, rawAdvertisedMessageEncodings);
        }
        metadata.discardAll(GrpcUtil.CONTENT_ENCODING_KEY);
        metadata.discardAll(GrpcUtil.CONTENT_ACCEPT_ENCODING_KEY);
        if (z) {
            metadata.put(GrpcUtil.CONTENT_ACCEPT_ENCODING_KEY, FULL_STREAM_DECOMPRESSION_ENCODINGS);
        }
    }

    public void start(ClientCall.Listener<RespT> listener, Metadata metadata) {
        TaskCloseable traceTask = PerfMark.traceTask("ClientCall.start");
        try {
            PerfMark.attachTag(this.tag);
            startInternal(listener, metadata);
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

    private void startInternal(final ClientCall.Listener<RespT> listener, Metadata metadata) {
        Compressor compressor;
        double d;
        Preconditions.checkState(this.stream == null, "Already started");
        Preconditions.checkState(!this.cancelCalled, "call was cancelled");
        Preconditions.checkNotNull(listener, "observer");
        Preconditions.checkNotNull(metadata, "headers");
        if (this.context.isCancelled()) {
            this.stream = NoopClientStream.INSTANCE;
            this.callExecutor.execute(new ContextRunnable() {
                public void runInContext() {
                    ClientCallImpl clientCallImpl = ClientCallImpl.this;
                    clientCallImpl.closeObserver(listener, Contexts.statusFromCancelled(clientCallImpl.context), new Metadata());
                }
            });
            return;
        }
        applyMethodConfig();
        final String compressor2 = this.callOptions.getCompressor();
        if (compressor2 != null) {
            compressor = this.compressorRegistry.lookupCompressor(compressor2);
            if (compressor == null) {
                this.stream = NoopClientStream.INSTANCE;
                this.callExecutor.execute(new ContextRunnable() {
                    public void runInContext() {
                        ClientCallImpl.this.closeObserver(listener, Status.INTERNAL.withDescription(String.format("Unable to find compressor by name %s", new Object[]{compressor2})), new Metadata());
                    }
                });
                return;
            }
        } else {
            compressor = Codec.Identity.NONE;
        }
        prepareHeaders(metadata, this.decompressorRegistry, compressor, this.fullStreamDecompression);
        Deadline effectiveDeadline = effectiveDeadline();
        if (!(effectiveDeadline != null && effectiveDeadline.isExpired())) {
            logIfContextNarrowedTimeout(effectiveDeadline, this.context.getDeadline(), this.callOptions.getDeadline());
            this.stream = this.clientStreamProvider.newStream(this.method, this.callOptions, metadata, this.context);
        } else {
            ClientStreamTracer[] clientStreamTracers = GrpcUtil.getClientStreamTracers(this.callOptions, metadata, 0, false);
            String str = isFirstMin(this.callOptions.getDeadline(), this.context.getDeadline()) ? "CallOptions" : "Context";
            Long l = (Long) this.callOptions.getOption(ClientStreamTracer.NAME_RESOLUTION_DELAYED);
            Object[] objArr = new Object[3];
            objArr[0] = str;
            double d2 = NANO_TO_SECS;
            objArr[1] = Double.valueOf(((double) effectiveDeadline.timeRemaining(TimeUnit.NANOSECONDS)) / d2);
            if (l == null) {
                d = 0.0d;
            } else {
                d = ((double) l.longValue()) / d2;
            }
            objArr[2] = Double.valueOf(d);
            this.stream = new FailingClientStream(Status.DEADLINE_EXCEEDED.withDescription(String.format("ClientCall started after %s deadline was exceeded %.9f seconds ago. Name resolution delay %.9f seconds.", objArr)), clientStreamTracers);
        }
        if (this.callExecutorIsDirect) {
            this.stream.optimizeForDirectExecutor();
        }
        if (this.callOptions.getAuthority() != null) {
            this.stream.setAuthority(this.callOptions.getAuthority());
        }
        if (this.callOptions.getMaxInboundMessageSize() != null) {
            this.stream.setMaxInboundMessageSize(this.callOptions.getMaxInboundMessageSize().intValue());
        }
        if (this.callOptions.getMaxOutboundMessageSize() != null) {
            this.stream.setMaxOutboundMessageSize(this.callOptions.getMaxOutboundMessageSize().intValue());
        }
        if (effectiveDeadline != null) {
            this.stream.setDeadline(effectiveDeadline);
        }
        this.stream.setCompressor(compressor);
        boolean z = this.fullStreamDecompression;
        if (z) {
            this.stream.setFullStreamDecompression(z);
        }
        this.stream.setDecompressorRegistry(this.decompressorRegistry);
        this.channelCallsTracer.reportCallStarted();
        this.stream.start(new ClientStreamListenerImpl(listener));
        this.context.addListener(this.cancellationListener, MoreExecutors.directExecutor());
        if (!(effectiveDeadline == null || effectiveDeadline.equals(this.context.getDeadline()) || this.deadlineCancellationExecutor == null)) {
            this.deadlineCancellationFuture = startDeadlineTimer(effectiveDeadline);
        }
        if (this.cancelListenersShouldBeRemoved) {
            removeContextListenerAndCancelDeadlineFuture();
        }
    }

    private void applyMethodConfig() {
        ManagedChannelServiceConfig.MethodInfo methodInfo = (ManagedChannelServiceConfig.MethodInfo) this.callOptions.getOption(ManagedChannelServiceConfig.MethodInfo.KEY);
        if (methodInfo != null) {
            if (methodInfo.timeoutNanos != null) {
                Deadline after = Deadline.after(methodInfo.timeoutNanos.longValue(), TimeUnit.NANOSECONDS);
                Deadline deadline = this.callOptions.getDeadline();
                if (deadline == null || after.compareTo(deadline) < 0) {
                    this.callOptions = this.callOptions.withDeadline(after);
                }
            }
            if (methodInfo.waitForReady != null) {
                this.callOptions = methodInfo.waitForReady.booleanValue() ? this.callOptions.withWaitForReady() : this.callOptions.withoutWaitForReady();
            }
            if (methodInfo.maxInboundMessageSize != null) {
                Integer maxInboundMessageSize = this.callOptions.getMaxInboundMessageSize();
                if (maxInboundMessageSize != null) {
                    this.callOptions = this.callOptions.withMaxInboundMessageSize(Math.min(maxInboundMessageSize.intValue(), methodInfo.maxInboundMessageSize.intValue()));
                } else {
                    this.callOptions = this.callOptions.withMaxInboundMessageSize(methodInfo.maxInboundMessageSize.intValue());
                }
            }
            if (methodInfo.maxOutboundMessageSize != null) {
                Integer maxOutboundMessageSize = this.callOptions.getMaxOutboundMessageSize();
                if (maxOutboundMessageSize != null) {
                    this.callOptions = this.callOptions.withMaxOutboundMessageSize(Math.min(maxOutboundMessageSize.intValue(), methodInfo.maxOutboundMessageSize.intValue()));
                } else {
                    this.callOptions = this.callOptions.withMaxOutboundMessageSize(methodInfo.maxOutboundMessageSize.intValue());
                }
            }
        }
    }

    private static void logIfContextNarrowedTimeout(Deadline deadline, @Nullable Deadline deadline2, @Nullable Deadline deadline3) {
        Logger logger = log;
        if (logger.isLoggable(Level.FINE) && deadline != null && deadline.equals(deadline2)) {
            StringBuilder sb = new StringBuilder(String.format(Locale.US, "Call timeout set to '%d' ns, due to context deadline.", new Object[]{Long.valueOf(Math.max(0, deadline.timeRemaining(TimeUnit.NANOSECONDS)))}));
            if (deadline3 == null) {
                sb.append(" Explicit call timeout was not set.");
            } else {
                sb.append(String.format(Locale.US, " Explicit call timeout was '%d' ns.", new Object[]{Long.valueOf(deadline3.timeRemaining(TimeUnit.NANOSECONDS))}));
            }
            logger.fine(sb.toString());
        }
    }

    /* access modifiers changed from: private */
    public void removeContextListenerAndCancelDeadlineFuture() {
        this.context.removeListener(this.cancellationListener);
        ScheduledFuture<?> scheduledFuture = this.deadlineCancellationFuture;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
    }

    private class DeadlineTimer implements Runnable {
        private final long remainingNanos;

        DeadlineTimer(long j) {
            this.remainingNanos = j;
        }

        public void run() {
            double d;
            InsightBuilder insightBuilder = new InsightBuilder();
            ClientCallImpl.this.stream.appendTimeoutInsight(insightBuilder);
            long abs = Math.abs(this.remainingNanos) / TimeUnit.SECONDS.toNanos(1);
            long abs2 = Math.abs(this.remainingNanos) % TimeUnit.SECONDS.toNanos(1);
            StringBuilder sb = new StringBuilder("deadline exceeded after ");
            if (this.remainingNanos < 0) {
                sb.append('-');
            }
            sb.append(abs);
            sb.append(String.format(Locale.US, ".%09d", new Object[]{Long.valueOf(abs2)}));
            sb.append("s. ");
            Long l = (Long) ClientCallImpl.this.callOptions.getOption(ClientStreamTracer.NAME_RESOLUTION_DELAYED);
            Locale locale = Locale.US;
            Object[] objArr = new Object[1];
            if (l == null) {
                d = 0.0d;
            } else {
                d = ((double) l.longValue()) / ClientCallImpl.NANO_TO_SECS;
            }
            objArr[0] = Double.valueOf(d);
            sb.append(String.format(locale, "Name resolution delay %.9f seconds. ", objArr));
            sb.append(insightBuilder);
            ClientCallImpl.this.stream.cancel(Status.DEADLINE_EXCEEDED.augmentDescription(sb.toString()));
        }
    }

    private ScheduledFuture<?> startDeadlineTimer(Deadline deadline) {
        long timeRemaining = deadline.timeRemaining(TimeUnit.NANOSECONDS);
        return this.deadlineCancellationExecutor.schedule(new LogExceptionRunnable(new DeadlineTimer(timeRemaining)), timeRemaining, TimeUnit.NANOSECONDS);
    }

    /* access modifiers changed from: private */
    @Nullable
    public Deadline effectiveDeadline() {
        return min(this.callOptions.getDeadline(), this.context.getDeadline());
    }

    @Nullable
    private static Deadline min(@Nullable Deadline deadline, @Nullable Deadline deadline2) {
        if (deadline == null) {
            return deadline2;
        }
        return deadline2 == null ? deadline : deadline.minimum(deadline2);
    }

    private static boolean isFirstMin(@Nullable Deadline deadline, @Nullable Deadline deadline2) {
        if (deadline == null) {
            return false;
        }
        if (deadline2 == null) {
            return true;
        }
        return deadline.isBefore(deadline2);
    }

    public void request(int i) {
        TaskCloseable traceTask = PerfMark.traceTask("ClientCall.request");
        try {
            PerfMark.attachTag(this.tag);
            boolean z = true;
            Preconditions.checkState(this.stream != null, "Not started");
            if (i < 0) {
                z = false;
            }
            Preconditions.checkArgument(z, "Number requested must be non-negative");
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

    public void cancel(@Nullable String str, @Nullable Throwable th) {
        TaskCloseable traceTask = PerfMark.traceTask("ClientCall.cancel");
        try {
            PerfMark.attachTag(this.tag);
            cancelInternal(str, th);
            if (traceTask != null) {
                traceTask.close();
                return;
            }
            return;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    private void cancelInternal(@Nullable String str, @Nullable Throwable th) {
        Status status;
        if (str == null && th == null) {
            th = new CancellationException("Cancelled without a message or cause");
            log.log(Level.WARNING, "Cancelling without a message or cause is suboptimal", th);
        }
        if (!this.cancelCalled) {
            this.cancelCalled = true;
            try {
                if (this.stream != null) {
                    Status status2 = Status.CANCELLED;
                    if (str != null) {
                        status = status2.withDescription(str);
                    } else {
                        status = status2.withDescription("Call cancelled without message");
                    }
                    if (th != null) {
                        status = status.withCause(th);
                    }
                    this.stream.cancel(status);
                }
            } finally {
                removeContextListenerAndCancelDeadlineFuture();
            }
        }
    }

    public void halfClose() {
        TaskCloseable traceTask = PerfMark.traceTask("ClientCall.halfClose");
        try {
            PerfMark.attachTag(this.tag);
            halfCloseInternal();
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

    private void halfCloseInternal() {
        Preconditions.checkState(this.stream != null, "Not started");
        Preconditions.checkState(!this.cancelCalled, "call was cancelled");
        Preconditions.checkState(!this.halfCloseCalled, "call already half-closed");
        this.halfCloseCalled = true;
        this.stream.halfClose();
    }

    public void sendMessage(ReqT reqt) {
        TaskCloseable traceTask = PerfMark.traceTask("ClientCall.sendMessage");
        try {
            PerfMark.attachTag(this.tag);
            sendMessageInternal(reqt);
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

    private void sendMessageInternal(ReqT reqt) {
        Preconditions.checkState(this.stream != null, "Not started");
        Preconditions.checkState(!this.cancelCalled, "call was cancelled");
        Preconditions.checkState(!this.halfCloseCalled, "call was half-closed");
        try {
            ClientStream clientStream = this.stream;
            if (clientStream instanceof RetriableStream) {
                ((RetriableStream) clientStream).sendMessage(reqt);
            } else {
                clientStream.writeMessage(this.method.streamRequest(reqt));
            }
            if (!this.unaryRequest) {
                this.stream.flush();
            }
        } catch (RuntimeException e) {
            this.stream.cancel(Status.CANCELLED.withCause(e).withDescription("Failed to stream message"));
        } catch (Error e2) {
            this.stream.cancel(Status.CANCELLED.withDescription("Client sendMessage() failed with Error"));
            throw e2;
        }
    }

    public void setMessageCompression(boolean z) {
        Preconditions.checkState(this.stream != null, "Not started");
        this.stream.setMessageCompression(z);
    }

    public boolean isReady() {
        if (this.halfCloseCalled) {
            return false;
        }
        return this.stream.isReady();
    }

    public Attributes getAttributes() {
        ClientStream clientStream = this.stream;
        if (clientStream != null) {
            return clientStream.getAttributes();
        }
        return Attributes.EMPTY;
    }

    /* access modifiers changed from: private */
    public void closeObserver(ClientCall.Listener<RespT> listener, Status status, Metadata metadata) {
        listener.onClose(status, metadata);
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("method", (Object) this.method).toString();
    }

    private class ClientStreamListenerImpl implements ClientStreamListener {
        /* access modifiers changed from: private */
        public Status exceptionStatus;
        /* access modifiers changed from: private */
        public final ClientCall.Listener<RespT> observer;

        public ClientStreamListenerImpl(ClientCall.Listener<RespT> listener) {
            this.observer = (ClientCall.Listener) Preconditions.checkNotNull(listener, "observer");
        }

        /* access modifiers changed from: private */
        public void exceptionThrown(Status status) {
            this.exceptionStatus = status;
            ClientCallImpl.this.stream.cancel(status);
        }

        public void headersRead(final Metadata metadata) {
            TaskCloseable traceTask = PerfMark.traceTask("ClientStreamListener.headersRead");
            try {
                PerfMark.attachTag(ClientCallImpl.this.tag);
                final Link linkOut = PerfMark.linkOut();
                ClientCallImpl.this.callExecutor.execute(new ContextRunnable() {
                    public void runInContext() {
                        TaskCloseable traceTask = PerfMark.traceTask("ClientCall$Listener.headersRead");
                        try {
                            PerfMark.attachTag(ClientCallImpl.this.tag);
                            PerfMark.linkIn(linkOut);
                            runInternal();
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

                    private void runInternal() {
                        if (ClientStreamListenerImpl.this.exceptionStatus == null) {
                            try {
                                ClientStreamListenerImpl.this.observer.onHeaders(metadata);
                            } catch (Throwable th) {
                                ClientStreamListenerImpl.this.exceptionThrown(Status.CANCELLED.withCause(th).withDescription("Failed to read headers"));
                            }
                        }
                    }
                });
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

        public void messagesAvailable(final StreamListener.MessageProducer messageProducer) {
            TaskCloseable traceTask = PerfMark.traceTask("ClientStreamListener.messagesAvailable");
            try {
                PerfMark.attachTag(ClientCallImpl.this.tag);
                final Link linkOut = PerfMark.linkOut();
                ClientCallImpl.this.callExecutor.execute(new ContextRunnable() {
                    public void runInContext() {
                        TaskCloseable traceTask = PerfMark.traceTask("ClientCall$Listener.messagesAvailable");
                        try {
                            PerfMark.attachTag(ClientCallImpl.this.tag);
                            PerfMark.linkIn(linkOut);
                            runInternal();
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

                    private void runInternal() {
                        InputStream next;
                        if (ClientStreamListenerImpl.this.exceptionStatus != null) {
                            GrpcUtil.closeQuietly(messageProducer);
                            return;
                        }
                        while (true) {
                            try {
                                next = messageProducer.next();
                                if (next != null) {
                                    ClientStreamListenerImpl.this.observer.onMessage(ClientCallImpl.this.method.parseResponse(next));
                                    next.close();
                                } else {
                                    return;
                                }
                            } catch (Throwable th) {
                                GrpcUtil.closeQuietly(messageProducer);
                                ClientStreamListenerImpl.this.exceptionThrown(Status.CANCELLED.withCause(th).withDescription("Failed to read message."));
                                return;
                            }
                        }
                    }
                });
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

        public void closed(Status status, ClientStreamListener.RpcProgress rpcProgress, Metadata metadata) {
            TaskCloseable traceTask = PerfMark.traceTask("ClientStreamListener.closed");
            try {
                PerfMark.attachTag(ClientCallImpl.this.tag);
                closedInternal(status, rpcProgress, metadata);
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

        private void closedInternal(final Status status, ClientStreamListener.RpcProgress rpcProgress, final Metadata metadata) {
            Deadline access$1200 = ClientCallImpl.this.effectiveDeadline();
            if (status.getCode() == Status.Code.CANCELLED && access$1200 != null && access$1200.isExpired()) {
                InsightBuilder insightBuilder = new InsightBuilder();
                ClientCallImpl.this.stream.appendTimeoutInsight(insightBuilder);
                status = Status.DEADLINE_EXCEEDED.augmentDescription("ClientCall was cancelled at or after deadline. " + insightBuilder);
                metadata = new Metadata();
            }
            final Link linkOut = PerfMark.linkOut();
            ClientCallImpl.this.callExecutor.execute(new ContextRunnable() {
                public void runInContext() {
                    TaskCloseable traceTask = PerfMark.traceTask("ClientCall$Listener.onClose");
                    try {
                        PerfMark.attachTag(ClientCallImpl.this.tag);
                        PerfMark.linkIn(linkOut);
                        runInternal();
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

                private void runInternal() {
                    Status status = status;
                    Metadata metadata = metadata;
                    if (ClientStreamListenerImpl.this.exceptionStatus != null) {
                        status = ClientStreamListenerImpl.this.exceptionStatus;
                        metadata = new Metadata();
                    }
                    boolean unused = ClientCallImpl.this.cancelListenersShouldBeRemoved = true;
                    try {
                        ClientCallImpl.this.closeObserver(ClientStreamListenerImpl.this.observer, status, metadata);
                    } finally {
                        ClientCallImpl.this.removeContextListenerAndCancelDeadlineFuture();
                        ClientCallImpl.this.channelCallsTracer.reportCallEnded(status.isOk());
                    }
                }
            });
        }

        public void onReady() {
            if (!ClientCallImpl.this.method.getType().clientSendsOneMessage()) {
                TaskCloseable traceTask = PerfMark.traceTask("ClientStreamListener.onReady");
                try {
                    PerfMark.attachTag(ClientCallImpl.this.tag);
                    final Link linkOut = PerfMark.linkOut();
                    ClientCallImpl.this.callExecutor.execute(new ContextRunnable() {
                        public void runInContext() {
                            TaskCloseable traceTask = PerfMark.traceTask("ClientCall$Listener.onReady");
                            try {
                                PerfMark.attachTag(ClientCallImpl.this.tag);
                                PerfMark.linkIn(linkOut);
                                runInternal();
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

                        private void runInternal() {
                            if (ClientStreamListenerImpl.this.exceptionStatus == null) {
                                try {
                                    ClientStreamListenerImpl.this.observer.onReady();
                                } catch (Throwable th) {
                                    ClientStreamListenerImpl.this.exceptionThrown(Status.CANCELLED.withCause(th).withDescription("Failed to call onReady."));
                                }
                            }
                        }
                    });
                    if (traceTask != null) {
                        traceTask.close();
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            } else {
                return;
            }
            throw th;
        }
    }
}
