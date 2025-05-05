package io.grpc.okhttp;

import androidx.media3.muxer.MuxerUtil;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.perf.FirebasePerformance;
import io.grpc.Attributes;
import io.grpc.InternalChannelz;
import io.grpc.InternalLogId;
import io.grpc.InternalStatus;
import io.grpc.Metadata;
import io.grpc.ServerStreamTracer;
import io.grpc.Status;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.KeepAliveEnforcer;
import io.grpc.internal.KeepAliveManager;
import io.grpc.internal.LogExceptionRunnable;
import io.grpc.internal.MaxConnectionIdleManager;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.SerializingExecutor;
import io.grpc.internal.ServerTransport;
import io.grpc.internal.ServerTransportListener;
import io.grpc.internal.TransportTracer;
import io.grpc.okhttp.ExceptionHandlingFrameWriter;
import io.grpc.okhttp.HandshakerSocketFactory;
import io.grpc.okhttp.OkHttpFrameLogger;
import io.grpc.okhttp.OutboundFlowController;
import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.FrameReader;
import io.grpc.okhttp.internal.framed.Http2;
import io.grpc.okhttp.internal.framed.Settings;
import io.grpc.okhttp.internal.framed.Variant;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.http2.Header;
import okio.Buffer;
import okio.ByteString;
import okio.Okio;
import okio.Sink;

final class OkHttpServerTransport implements ServerTransport, ExceptionHandlingFrameWriter.TransportExceptionHandler, OutboundFlowController.Transport {
    /* access modifiers changed from: private */
    public static final ByteString AUTHORITY = ByteString.encodeUtf8(Header.TARGET_AUTHORITY_UTF8);
    /* access modifiers changed from: private */
    public static final ByteString CONNECTION = ByteString.encodeUtf8("connection");
    /* access modifiers changed from: private */
    public static final ByteString CONNECT_METHOD = ByteString.encodeUtf8(FirebasePerformance.HttpMethod.CONNECT);
    /* access modifiers changed from: private */
    public static final ByteString CONTENT_LENGTH = ByteString.encodeUtf8("content-length");
    /* access modifiers changed from: private */
    public static final ByteString CONTENT_TYPE = ByteString.encodeUtf8("content-type");
    private static final int GRACEFUL_SHUTDOWN_PING = 4369;
    private static final long GRACEFUL_SHUTDOWN_PING_TIMEOUT_NANOS = TimeUnit.SECONDS.toNanos(1);
    /* access modifiers changed from: private */
    public static final ByteString HOST = ByteString.encodeUtf8("host");
    /* access modifiers changed from: private */
    public static final ByteString HTTP_METHOD = ByteString.encodeUtf8(Header.TARGET_METHOD_UTF8);
    private static final int KEEPALIVE_PING = 57005;
    /* access modifiers changed from: private */
    public static final ByteString PATH = ByteString.encodeUtf8(Header.TARGET_PATH_UTF8);
    /* access modifiers changed from: private */
    public static final ByteString POST_METHOD = ByteString.encodeUtf8("POST");
    /* access modifiers changed from: private */
    public static final ByteString SCHEME = ByteString.encodeUtf8(Header.TARGET_SCHEME_UTF8);
    /* access modifiers changed from: private */
    public static final ByteString TE = ByteString.encodeUtf8("te");
    /* access modifiers changed from: private */
    public static final ByteString TE_TRAILERS = ByteString.encodeUtf8(GrpcUtil.TE_TRAILERS);
    /* access modifiers changed from: private */
    public static final Logger log = Logger.getLogger(OkHttpServerTransport.class.getName());
    private boolean abruptShutdown;
    /* access modifiers changed from: private */
    public Attributes attributes;
    /* access modifiers changed from: private */
    public final Config config;
    private ScheduledFuture<?> forcefulCloseTimer;
    /* access modifiers changed from: private */
    public ExceptionHandlingFrameWriter frameWriter;
    /* access modifiers changed from: private */
    public Status goAwayStatus;
    /* access modifiers changed from: private */
    public int goAwayStreamId = Integer.MAX_VALUE;
    private boolean gracefulShutdown;
    private Long gracefulShutdownPeriod = null;
    private boolean handshakeShutdown;
    /* access modifiers changed from: private */
    public final KeepAliveEnforcer keepAliveEnforcer;
    /* access modifiers changed from: private */
    public KeepAliveManager keepAliveManager;
    /* access modifiers changed from: private */
    public int lastStreamId;
    /* access modifiers changed from: private */
    public ServerTransportListener listener;
    /* access modifiers changed from: private */
    public final Object lock = new Object();
    private final InternalLogId logId;
    private ScheduledFuture<?> maxConnectionAgeMonitor;
    /* access modifiers changed from: private */
    public MaxConnectionIdleManager maxConnectionIdleManager;
    /* access modifiers changed from: private */
    public OutboundFlowController outboundFlow;
    private ScheduledExecutorService scheduledExecutorService;
    private ScheduledFuture<?> secondGoawayTimer;
    private InternalChannelz.Security securityInfo;
    /* access modifiers changed from: private */
    public Socket socket;
    /* access modifiers changed from: private */
    public final Map<Integer, StreamState> streams = new TreeMap();
    /* access modifiers changed from: private */
    public final TransportTracer tracer;
    private Executor transportExecutor;
    private final Variant variant = new Http2();

    interface StreamState {
        OutboundFlowController.StreamState getOutboundFlowState();

        boolean hasReceivedEndOfStream();

        void inboundDataReceived(Buffer buffer, int i, int i2, boolean z);

        void inboundRstReceived(Status status);

        int inboundWindowAvailable();

        void transportReportStatus(Status status);
    }

    public OkHttpServerTransport(Config config2, Socket socket2) {
        this.config = (Config) Preconditions.checkNotNull(config2, "config");
        this.socket = (Socket) Preconditions.checkNotNull(socket2, "bareSocket");
        TransportTracer create = config2.transportTracerFactory.create();
        this.tracer = create;
        create.setFlowControlWindowReader(new OkHttpServerTransport$$ExternalSyntheticLambda5(this));
        this.logId = InternalLogId.allocate(getClass(), this.socket.getRemoteSocketAddress().toString());
        this.transportExecutor = config2.transportExecutorPool.getObject();
        this.scheduledExecutorService = config2.scheduledExecutorServicePool.getObject();
        this.keepAliveEnforcer = new KeepAliveEnforcer(config2.permitKeepAliveWithoutCalls, config2.permitKeepAliveTimeInNanos, TimeUnit.NANOSECONDS);
    }

    public void start(ServerTransportListener serverTransportListener) {
        this.listener = (ServerTransportListener) Preconditions.checkNotNull(serverTransportListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        SerializingExecutor serializingExecutor = new SerializingExecutor(this.transportExecutor);
        serializingExecutor.execute(new OkHttpServerTransport$$ExternalSyntheticLambda2(this, serializingExecutor));
    }

    /* access modifiers changed from: private */
    /* renamed from: startIo */
    public void m2333lambda$start$0$iogrpcokhttpOkHttpServerTransport(SerializingExecutor serializingExecutor) {
        try {
            synchronized (this.lock) {
                this.socket.setTcpNoDelay(true);
            }
            HandshakerSocketFactory.HandshakeResult handshake = this.config.handshakerSocketFactory.handshake(this.socket, Attributes.EMPTY);
            synchronized (this.lock) {
                this.socket = handshake.socket;
            }
            this.attributes = handshake.attributes;
            AsyncSink sink = AsyncSink.sink(serializingExecutor, this, 10000);
            sink.becomeConnected(Okio.sink(this.socket), this.socket);
            AnonymousClass1 r1 = new ForwardingFrameWriter(sink.limitControlFramesWriter(this.variant.newWriter(Okio.buffer((Sink) sink), false))) {
                public void synReply(boolean z, int i, List<io.grpc.okhttp.internal.framed.Header> list) throws IOException {
                    OkHttpServerTransport.this.keepAliveEnforcer.resetCounters();
                    super.synReply(z, i, list);
                }

                public void headers(int i, List<io.grpc.okhttp.internal.framed.Header> list) throws IOException {
                    OkHttpServerTransport.this.keepAliveEnforcer.resetCounters();
                    super.headers(i, list);
                }

                public void data(boolean z, int i, Buffer buffer, int i2) throws IOException {
                    OkHttpServerTransport.this.keepAliveEnforcer.resetCounters();
                    super.data(z, i, buffer, i2);
                }
            };
            synchronized (this.lock) {
                this.securityInfo = handshake.securityInfo;
                this.frameWriter = new ExceptionHandlingFrameWriter(this, r1);
                this.outboundFlow = new OutboundFlowController(this, this.frameWriter);
                this.frameWriter.connectionPreface();
                Settings settings = new Settings();
                OkHttpSettingsUtil.set(settings, 7, this.config.flowControlWindow);
                OkHttpSettingsUtil.set(settings, 6, this.config.maxInboundMetadataSize);
                this.frameWriter.settings(settings);
                if (this.config.flowControlWindow > 65535) {
                    this.frameWriter.windowUpdate(0, (long) (this.config.flowControlWindow - 65535));
                }
                this.frameWriter.flush();
            }
            if (this.config.keepAliveTimeNanos != Long.MAX_VALUE) {
                KeepAliveManager keepAliveManager2 = new KeepAliveManager(new KeepAlivePinger(), this.scheduledExecutorService, this.config.keepAliveTimeNanos, this.config.keepAliveTimeoutNanos, true);
                this.keepAliveManager = keepAliveManager2;
                keepAliveManager2.onTransportStarted();
            }
            if (this.config.maxConnectionIdleNanos != Long.MAX_VALUE) {
                MaxConnectionIdleManager maxConnectionIdleManager2 = new MaxConnectionIdleManager(this.config.maxConnectionIdleNanos);
                this.maxConnectionIdleManager = maxConnectionIdleManager2;
                maxConnectionIdleManager2.start(new OkHttpServerTransport$$ExternalSyntheticLambda3(this), this.scheduledExecutorService);
            }
            if (this.config.maxConnectionAgeInNanos != Long.MAX_VALUE) {
                this.maxConnectionAgeMonitor = this.scheduledExecutorService.schedule(new LogExceptionRunnable(new OkHttpServerTransport$$ExternalSyntheticLambda4(this)), (long) (((Math.random() * 0.2d) + 0.9d) * ((double) this.config.maxConnectionAgeInNanos)), TimeUnit.NANOSECONDS);
            }
            this.transportExecutor.execute(new FrameHandler(this.variant.newReader(Okio.buffer(Okio.source(this.socket)), false)));
        } catch (IOException | Error | RuntimeException e) {
            synchronized (this.lock) {
                if (!this.handshakeShutdown) {
                    log.log(Level.INFO, "Socket failed to handshake", e);
                }
                GrpcUtil.closeQuietly((Closeable) this.socket);
                terminated();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$startIo$1$io-grpc-okhttp-OkHttpServerTransport  reason: not valid java name */
    public /* synthetic */ void m2334lambda$startIo$1$iogrpcokhttpOkHttpServerTransport() {
        shutdown(Long.valueOf(this.config.maxConnectionAgeGraceInNanos));
    }

    public void shutdown() {
        shutdown((Long) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0048, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void shutdown(@javax.annotation.Nullable java.lang.Long r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.lock
            monitor-enter(r0)
            boolean r1 = r5.gracefulShutdown     // Catch:{ all -> 0x004b }
            if (r1 != 0) goto L_0x0049
            boolean r1 = r5.abruptShutdown     // Catch:{ all -> 0x004b }
            if (r1 == 0) goto L_0x000c
            goto L_0x0049
        L_0x000c:
            r1 = 1
            r5.gracefulShutdown = r1     // Catch:{ all -> 0x004b }
            r5.gracefulShutdownPeriod = r6     // Catch:{ all -> 0x004b }
            io.grpc.okhttp.ExceptionHandlingFrameWriter r6 = r5.frameWriter     // Catch:{ all -> 0x004b }
            if (r6 != 0) goto L_0x001d
            r5.handshakeShutdown = r1     // Catch:{ all -> 0x004b }
            java.net.Socket r6 = r5.socket     // Catch:{ all -> 0x004b }
            io.grpc.internal.GrpcUtil.closeQuietly((java.io.Closeable) r6)     // Catch:{ all -> 0x004b }
            goto L_0x0047
        L_0x001d:
            java.util.concurrent.ScheduledExecutorService r6 = r5.scheduledExecutorService     // Catch:{ all -> 0x004b }
            io.grpc.okhttp.OkHttpServerTransport$$ExternalSyntheticLambda0 r1 = new io.grpc.okhttp.OkHttpServerTransport$$ExternalSyntheticLambda0     // Catch:{ all -> 0x004b }
            r1.<init>(r5)     // Catch:{ all -> 0x004b }
            long r2 = GRACEFUL_SHUTDOWN_PING_TIMEOUT_NANOS     // Catch:{ all -> 0x004b }
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.NANOSECONDS     // Catch:{ all -> 0x004b }
            java.util.concurrent.ScheduledFuture r6 = r6.schedule(r1, r2, r4)     // Catch:{ all -> 0x004b }
            r5.secondGoawayTimer = r6     // Catch:{ all -> 0x004b }
            io.grpc.okhttp.ExceptionHandlingFrameWriter r6 = r5.frameWriter     // Catch:{ all -> 0x004b }
            io.grpc.okhttp.internal.framed.ErrorCode r1 = io.grpc.okhttp.internal.framed.ErrorCode.NO_ERROR     // Catch:{ all -> 0x004b }
            r2 = 0
            byte[] r3 = new byte[r2]     // Catch:{ all -> 0x004b }
            r4 = 2147483647(0x7fffffff, float:NaN)
            r6.goAway(r4, r1, r3)     // Catch:{ all -> 0x004b }
            io.grpc.okhttp.ExceptionHandlingFrameWriter r6 = r5.frameWriter     // Catch:{ all -> 0x004b }
            r1 = 4369(0x1111, float:6.122E-42)
            r6.ping(r2, r2, r1)     // Catch:{ all -> 0x004b }
            io.grpc.okhttp.ExceptionHandlingFrameWriter r6 = r5.frameWriter     // Catch:{ all -> 0x004b }
            r6.flush()     // Catch:{ all -> 0x004b }
        L_0x0047:
            monitor-exit(r0)     // Catch:{ all -> 0x004b }
            return
        L_0x0049:
            monitor-exit(r0)     // Catch:{ all -> 0x004b }
            return
        L_0x004b:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x004b }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.okhttp.OkHttpServerTransport.shutdown(java.lang.Long):void");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004c, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void triggerGracefulSecondGoaway() {
        /*
            r6 = this;
            java.lang.Object r0 = r6.lock
            monitor-enter(r0)
            java.util.concurrent.ScheduledFuture<?> r1 = r6.secondGoawayTimer     // Catch:{ all -> 0x004d }
            if (r1 != 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x004d }
            return
        L_0x0009:
            r2 = 0
            r1.cancel(r2)     // Catch:{ all -> 0x004d }
            r1 = 0
            r6.secondGoawayTimer = r1     // Catch:{ all -> 0x004d }
            io.grpc.okhttp.ExceptionHandlingFrameWriter r1 = r6.frameWriter     // Catch:{ all -> 0x004d }
            int r3 = r6.lastStreamId     // Catch:{ all -> 0x004d }
            io.grpc.okhttp.internal.framed.ErrorCode r4 = io.grpc.okhttp.internal.framed.ErrorCode.NO_ERROR     // Catch:{ all -> 0x004d }
            byte[] r2 = new byte[r2]     // Catch:{ all -> 0x004d }
            r1.goAway(r3, r4, r2)     // Catch:{ all -> 0x004d }
            int r1 = r6.lastStreamId     // Catch:{ all -> 0x004d }
            r6.goAwayStreamId = r1     // Catch:{ all -> 0x004d }
            java.util.Map<java.lang.Integer, io.grpc.okhttp.OkHttpServerTransport$StreamState> r1 = r6.streams     // Catch:{ all -> 0x004d }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x004d }
            if (r1 == 0) goto L_0x002d
            io.grpc.okhttp.ExceptionHandlingFrameWriter r1 = r6.frameWriter     // Catch:{ all -> 0x004d }
            r1.close()     // Catch:{ all -> 0x004d }
            goto L_0x0032
        L_0x002d:
            io.grpc.okhttp.ExceptionHandlingFrameWriter r1 = r6.frameWriter     // Catch:{ all -> 0x004d }
            r1.flush()     // Catch:{ all -> 0x004d }
        L_0x0032:
            java.lang.Long r1 = r6.gracefulShutdownPeriod     // Catch:{ all -> 0x004d }
            if (r1 == 0) goto L_0x004b
            java.util.concurrent.ScheduledExecutorService r1 = r6.scheduledExecutorService     // Catch:{ all -> 0x004d }
            io.grpc.okhttp.OkHttpServerTransport$$ExternalSyntheticLambda1 r2 = new io.grpc.okhttp.OkHttpServerTransport$$ExternalSyntheticLambda1     // Catch:{ all -> 0x004d }
            r2.<init>(r6)     // Catch:{ all -> 0x004d }
            java.lang.Long r3 = r6.gracefulShutdownPeriod     // Catch:{ all -> 0x004d }
            long r3 = r3.longValue()     // Catch:{ all -> 0x004d }
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.NANOSECONDS     // Catch:{ all -> 0x004d }
            java.util.concurrent.ScheduledFuture r1 = r1.schedule(r2, r3, r5)     // Catch:{ all -> 0x004d }
            r6.forcefulCloseTimer = r1     // Catch:{ all -> 0x004d }
        L_0x004b:
            monitor-exit(r0)     // Catch:{ all -> 0x004d }
            return
        L_0x004d:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x004d }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.okhttp.OkHttpServerTransport.triggerGracefulSecondGoaway():void");
    }

    public void shutdownNow(Status status) {
        synchronized (this.lock) {
            if (this.frameWriter == null) {
                this.handshakeShutdown = true;
                GrpcUtil.closeQuietly((Closeable) this.socket);
                return;
            }
            abruptShutdown(ErrorCode.NO_ERROR, "", status, true);
        }
    }

    public void onException(Throwable th) {
        Preconditions.checkNotNull(th, "failureCause");
        abruptShutdown(ErrorCode.INTERNAL_ERROR, "I/O failure", Status.UNAVAILABLE.withCause(th), false);
    }

    /* access modifiers changed from: private */
    public void abruptShutdown(ErrorCode errorCode, String str, Status status, boolean z) {
        synchronized (this.lock) {
            if (!this.abruptShutdown) {
                this.abruptShutdown = true;
                this.goAwayStatus = status;
                ScheduledFuture<?> scheduledFuture = this.secondGoawayTimer;
                if (scheduledFuture != null) {
                    scheduledFuture.cancel(false);
                    this.secondGoawayTimer = null;
                }
                for (Map.Entry next : this.streams.entrySet()) {
                    if (z) {
                        this.frameWriter.rstStream(((Integer) next.getKey()).intValue(), ErrorCode.CANCEL);
                    }
                    ((StreamState) next.getValue()).transportReportStatus(status);
                }
                this.streams.clear();
                this.frameWriter.goAway(this.lastStreamId, errorCode, str.getBytes(GrpcUtil.US_ASCII));
                this.goAwayStreamId = this.lastStreamId;
                this.frameWriter.close();
                this.forcefulCloseTimer = this.scheduledExecutorService.schedule(new OkHttpServerTransport$$ExternalSyntheticLambda1(this), 1, TimeUnit.SECONDS);
            }
        }
    }

    /* access modifiers changed from: private */
    public void triggerForcefulClose() {
        GrpcUtil.closeQuietly((Closeable) this.socket);
    }

    /* access modifiers changed from: private */
    public void terminated() {
        synchronized (this.lock) {
            ScheduledFuture<?> scheduledFuture = this.forcefulCloseTimer;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
                this.forcefulCloseTimer = null;
            }
        }
        KeepAliveManager keepAliveManager2 = this.keepAliveManager;
        if (keepAliveManager2 != null) {
            keepAliveManager2.onTransportTermination();
        }
        MaxConnectionIdleManager maxConnectionIdleManager2 = this.maxConnectionIdleManager;
        if (maxConnectionIdleManager2 != null) {
            maxConnectionIdleManager2.onTransportTermination();
        }
        ScheduledFuture<?> scheduledFuture2 = this.maxConnectionAgeMonitor;
        if (scheduledFuture2 != null) {
            scheduledFuture2.cancel(false);
        }
        this.transportExecutor = this.config.transportExecutorPool.returnObject(this.transportExecutor);
        this.scheduledExecutorService = this.config.scheduledExecutorServicePool.returnObject(this.scheduledExecutorService);
        this.listener.transportTerminated();
    }

    public ScheduledExecutorService getScheduledExecutorService() {
        return this.scheduledExecutorService;
    }

    public ListenableFuture<InternalChannelz.SocketStats> getStats() {
        ListenableFuture<InternalChannelz.SocketStats> immediateFuture;
        synchronized (this.lock) {
            immediateFuture = Futures.immediateFuture(new InternalChannelz.SocketStats(this.tracer.getStats(), this.socket.getLocalSocketAddress(), this.socket.getRemoteSocketAddress(), Utils.getSocketOptions(this.socket), this.securityInfo));
        }
        return immediateFuture;
    }

    /* access modifiers changed from: private */
    public TransportTracer.FlowControlWindows readFlowControlWindow() {
        TransportTracer.FlowControlWindows flowControlWindows;
        synchronized (this.lock) {
            OutboundFlowController outboundFlowController = this.outboundFlow;
            flowControlWindows = new TransportTracer.FlowControlWindows(outboundFlowController == null ? -1 : (long) outboundFlowController.windowUpdate((OutboundFlowController.StreamState) null, 0), (long) (((float) this.config.flowControlWindow) * 0.5f));
        }
        return flowControlWindows;
    }

    public InternalLogId getLogId() {
        return this.logId;
    }

    public OutboundFlowController.StreamState[] getActiveStreams() {
        OutboundFlowController.StreamState[] streamStateArr;
        synchronized (this.lock) {
            streamStateArr = new OutboundFlowController.StreamState[this.streams.size()];
            int i = 0;
            for (StreamState outboundFlowState : this.streams.values()) {
                streamStateArr[i] = outboundFlowState.getOutboundFlowState();
                i++;
            }
        }
        return streamStateArr;
    }

    /* access modifiers changed from: package-private */
    public void streamClosed(int i, boolean z) {
        synchronized (this.lock) {
            this.streams.remove(Integer.valueOf(i));
            if (this.streams.isEmpty()) {
                this.keepAliveEnforcer.onTransportIdle();
                MaxConnectionIdleManager maxConnectionIdleManager2 = this.maxConnectionIdleManager;
                if (maxConnectionIdleManager2 != null) {
                    maxConnectionIdleManager2.onTransportIdle();
                }
            }
            if (this.gracefulShutdown && this.streams.isEmpty()) {
                this.frameWriter.close();
            } else if (z) {
                this.frameWriter.flush();
            }
        }
    }

    /* access modifiers changed from: private */
    public static String asciiString(ByteString byteString) {
        for (int i = 0; i < byteString.size(); i++) {
            if (byteString.getByte(i) < 0) {
                return byteString.string(GrpcUtil.US_ASCII);
            }
        }
        return byteString.utf8();
    }

    /* access modifiers changed from: private */
    public static int headerFind(List<io.grpc.okhttp.internal.framed.Header> list, ByteString byteString, int i) {
        while (i < list.size()) {
            if (list.get(i).name.equals(byteString)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /* access modifiers changed from: private */
    public static boolean headerContains(List<io.grpc.okhttp.internal.framed.Header> list, ByteString byteString) {
        return headerFind(list, byteString, 0) != -1;
    }

    /* access modifiers changed from: private */
    public static void headerRemove(List<io.grpc.okhttp.internal.framed.Header> list, ByteString byteString) {
        int i = 0;
        while (true) {
            i = headerFind(list, byteString, i);
            if (i != -1) {
                list.remove(i);
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public static ByteString headerGetRequiredSingle(List<io.grpc.okhttp.internal.framed.Header> list, ByteString byteString) {
        int headerFind = headerFind(list, byteString, 0);
        if (headerFind != -1 && headerFind(list, byteString, headerFind + 1) == -1) {
            return list.get(headerFind).value;
        }
        return null;
    }

    static final class Config {
        final int flowControlWindow;
        final HandshakerSocketFactory handshakerSocketFactory;
        final long keepAliveTimeNanos;
        final long keepAliveTimeoutNanos;
        final long maxConnectionAgeGraceInNanos;
        final long maxConnectionAgeInNanos;
        final long maxConnectionIdleNanos;
        final int maxInboundMessageSize;
        final int maxInboundMetadataSize;
        final long permitKeepAliveTimeInNanos;
        final boolean permitKeepAliveWithoutCalls;
        final ObjectPool<ScheduledExecutorService> scheduledExecutorServicePool;
        final List<? extends ServerStreamTracer.Factory> streamTracerFactories;
        final ObjectPool<Executor> transportExecutorPool;
        final TransportTracer.Factory transportTracerFactory;

        public Config(OkHttpServerBuilder okHttpServerBuilder, List<? extends ServerStreamTracer.Factory> list) {
            this.streamTracerFactories = (List) Preconditions.checkNotNull(list, "streamTracerFactories");
            this.transportExecutorPool = (ObjectPool) Preconditions.checkNotNull(okHttpServerBuilder.transportExecutorPool, "transportExecutorPool");
            this.scheduledExecutorServicePool = (ObjectPool) Preconditions.checkNotNull(okHttpServerBuilder.scheduledExecutorServicePool, "scheduledExecutorServicePool");
            this.transportTracerFactory = (TransportTracer.Factory) Preconditions.checkNotNull(okHttpServerBuilder.transportTracerFactory, "transportTracerFactory");
            this.handshakerSocketFactory = (HandshakerSocketFactory) Preconditions.checkNotNull(okHttpServerBuilder.handshakerSocketFactory, "handshakerSocketFactory");
            this.keepAliveTimeNanos = okHttpServerBuilder.keepAliveTimeNanos;
            this.keepAliveTimeoutNanos = okHttpServerBuilder.keepAliveTimeoutNanos;
            this.flowControlWindow = okHttpServerBuilder.flowControlWindow;
            this.maxInboundMessageSize = okHttpServerBuilder.maxInboundMessageSize;
            this.maxInboundMetadataSize = okHttpServerBuilder.maxInboundMetadataSize;
            this.maxConnectionIdleNanos = okHttpServerBuilder.maxConnectionIdleInNanos;
            this.permitKeepAliveWithoutCalls = okHttpServerBuilder.permitKeepAliveWithoutCalls;
            this.permitKeepAliveTimeInNanos = okHttpServerBuilder.permitKeepAliveTimeInNanos;
            this.maxConnectionAgeInNanos = okHttpServerBuilder.maxConnectionAgeInNanos;
            this.maxConnectionAgeGraceInNanos = okHttpServerBuilder.maxConnectionAgeGraceInNanos;
        }
    }

    class FrameHandler implements FrameReader.Handler, Runnable {
        private int connectionUnacknowledgedBytesRead;
        private final OkHttpFrameLogger frameLogger = new OkHttpFrameLogger(Level.FINE, (Class<?>) OkHttpServerTransport.class);
        private final FrameReader frameReader;
        private boolean receivedSettings;

        public void ackSettings() {
        }

        public void alternateService(int i, String str, ByteString byteString, String str2, int i2, long j) {
        }

        public FrameHandler(FrameReader frameReader2) {
            this.frameReader = frameReader2;
        }

        public void run() {
            InputStream inputStream;
            Status access$400;
            InputStream inputStream2;
            String name = Thread.currentThread().getName();
            Thread.currentThread().setName("OkHttpServerTransport");
            try {
                this.frameReader.readConnectionPreface();
                if (!this.frameReader.nextFrame(this)) {
                    connectionError(ErrorCode.INTERNAL_ERROR, "Failed to read initial SETTINGS");
                    try {
                        inputStream2 = OkHttpServerTransport.this.socket.getInputStream();
                    } catch (IOException unused) {
                    }
                } else if (!this.receivedSettings) {
                    connectionError(ErrorCode.PROTOCOL_ERROR, "First HTTP/2 frame must be SETTINGS. RFC7540 section 3.5");
                    inputStream2 = OkHttpServerTransport.this.socket.getInputStream();
                } else {
                    while (this.frameReader.nextFrame(this)) {
                        if (OkHttpServerTransport.this.keepAliveManager != null) {
                            OkHttpServerTransport.this.keepAliveManager.onDataReceived();
                        }
                    }
                    synchronized (OkHttpServerTransport.this.lock) {
                        access$400 = OkHttpServerTransport.this.goAwayStatus;
                    }
                    if (access$400 == null) {
                        access$400 = Status.UNAVAILABLE.withDescription("TCP connection closed or IOException");
                    }
                    OkHttpServerTransport.this.abruptShutdown(ErrorCode.INTERNAL_ERROR, "I/O failure", access$400, false);
                    try {
                        inputStream = OkHttpServerTransport.this.socket.getInputStream();
                        GrpcUtil.exhaust(inputStream);
                    } catch (IOException unused2) {
                    }
                    GrpcUtil.closeQuietly((Closeable) OkHttpServerTransport.this.socket);
                    OkHttpServerTransport.this.terminated();
                    Thread.currentThread().setName(name);
                    return;
                }
                GrpcUtil.exhaust(inputStream2);
                GrpcUtil.closeQuietly((Closeable) OkHttpServerTransport.this.socket);
                OkHttpServerTransport.this.terminated();
                Thread.currentThread().setName(name);
            } catch (Throwable th) {
                try {
                    OkHttpServerTransport.log.log(Level.WARNING, "Error decoding HTTP/2 frames", th);
                    OkHttpServerTransport.this.abruptShutdown(ErrorCode.INTERNAL_ERROR, "Error in frame decoder", Status.INTERNAL.withDescription("Error decoding HTTP/2 frames").withCause(th), false);
                    inputStream = OkHttpServerTransport.this.socket.getInputStream();
                } catch (Throwable th2) {
                    try {
                        GrpcUtil.exhaust(OkHttpServerTransport.this.socket.getInputStream());
                    } catch (IOException unused3) {
                    }
                    GrpcUtil.closeQuietly((Closeable) OkHttpServerTransport.this.socket);
                    OkHttpServerTransport.this.terminated();
                    Thread.currentThread().setName(name);
                    throw th2;
                }
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:100:0x01ca, code lost:
            r15 = io.grpc.okhttp.OkHttpServerTransport.access$2300(r10).substring(1);
            r9 = io.grpc.okhttp.OkHttpServerTransport.access$2500(r1, io.grpc.okhttp.OkHttpServerTransport.access$2400());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:101:0x01da, code lost:
            if (r9 != null) goto L_0x01ec;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:102:0x01dc, code lost:
            respondWithHttpError(r24, r23, 415, io.grpc.Status.Code.INTERNAL, "Content-Type is missing or duplicated");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:103:0x01eb, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:104:0x01ec, code lost:
            r9 = io.grpc.okhttp.OkHttpServerTransport.access$2300(r9);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:105:0x01f4, code lost:
            if (io.grpc.internal.GrpcUtil.isGrpcContentType(r9) != false) goto L_0x0213;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:106:0x01f6, code lost:
            respondWithHttpError(r24, r23, 415, io.grpc.Status.Code.INTERNAL, "Content-Type is not supported: " + r9);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:107:0x0212, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:109:0x021b, code lost:
            if (io.grpc.okhttp.OkHttpServerTransport.access$2600().equals(r8) != false) goto L_0x023e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:110:0x021d, code lost:
            respondWithHttpError(r24, r23, 405, io.grpc.Status.Code.INTERNAL, "HTTP Method is not supported: " + io.grpc.okhttp.OkHttpServerTransport.access$2300(r8));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:111:0x023d, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:112:0x023e, code lost:
            r8 = io.grpc.okhttp.OkHttpServerTransport.access$2500(r1, io.grpc.okhttp.OkHttpServerTransport.access$2700());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:113:0x024e, code lost:
            if (io.grpc.okhttp.OkHttpServerTransport.access$2800().equals(r8) != false) goto L_0x0274;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:114:0x0250, code lost:
            r1 = io.grpc.Status.Code.INTERNAL;
            r4 = new java.lang.Object[2];
            r4[0] = io.grpc.okhttp.OkHttpServerTransport.access$2300(io.grpc.okhttp.OkHttpServerTransport.access$2800());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:115:0x0261, code lost:
            if (r8 != null) goto L_0x0266;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:116:0x0263, code lost:
            r5 = "<missing>";
         */
        /* JADX WARNING: Code restructure failed: missing block: B:117:0x0266, code lost:
            r5 = io.grpc.okhttp.OkHttpServerTransport.access$2300(r8);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:118:0x026a, code lost:
            r4[1] = r5;
            respondWithGrpcError(r2, r0, r1, java.lang.String.format("Expected header TE: %s, but %s is received. Some intermediate proxy may not support trailers", r4));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:119:0x0273, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:120:0x0274, code lost:
            io.grpc.okhttp.OkHttpServerTransport.access$1200(r1, io.grpc.okhttp.OkHttpServerTransport.access$2900());
            r1 = io.grpc.okhttp.Utils.convertHeaders(r26);
            r6 = io.grpc.internal.StatsTraceContext.newServerContext(io.grpc.okhttp.OkHttpServerTransport.access$1100(r7.this$0).streamTracerFactories, r15, r1);
            r19 = io.grpc.okhttp.OkHttpServerTransport.access$300(r7.this$0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:121:0x0291, code lost:
            monitor-enter(r19);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:123:?, code lost:
            r9 = r7.this$0;
            r22 = r8;
            r25 = r15;
            r8 = new io.grpc.okhttp.OkHttpServerStream.TransportState(r9, r24, io.grpc.okhttp.OkHttpServerTransport.access$1100(r9).maxInboundMessageSize, r6, io.grpc.okhttp.OkHttpServerTransport.access$300(r7.this$0), io.grpc.okhttp.OkHttpServerTransport.access$3000(r7.this$0), io.grpc.okhttp.OkHttpServerTransport.access$3100(r7.this$0), io.grpc.okhttp.OkHttpServerTransport.access$1100(r7.this$0).flowControlWindow, io.grpc.okhttp.OkHttpServerTransport.access$3200(r7.this$0), r25);
            r10 = io.grpc.okhttp.OkHttpServerTransport.access$3300(r7.this$0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:124:0x02db, code lost:
            if (r4 != null) goto L_0x02de;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:126:0x02de, code lost:
            r3 = io.grpc.okhttp.OkHttpServerTransport.access$2300(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:127:0x02e2, code lost:
            r8 = new io.grpc.okhttp.OkHttpServerStream(r22, r10, r3, r6, io.grpc.okhttp.OkHttpServerTransport.access$3200(r7.this$0));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:128:0x02fa, code lost:
            if (io.grpc.okhttp.OkHttpServerTransport.access$2000(r7.this$0).isEmpty() == false) goto L_0x0316;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:129:0x02fc, code lost:
            io.grpc.okhttp.OkHttpServerTransport.access$000(r7.this$0).onTransportActive();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:130:0x030b, code lost:
            if (io.grpc.okhttp.OkHttpServerTransport.access$3400(r7.this$0) == null) goto L_0x0316;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:131:0x030d, code lost:
            io.grpc.okhttp.OkHttpServerTransport.access$3400(r7.this$0).onTransportActive();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:132:0x0316, code lost:
            r4 = r22;
            io.grpc.okhttp.OkHttpServerTransport.access$2000(r7.this$0).put(java.lang.Integer.valueOf(r24), r4);
            io.grpc.okhttp.OkHttpServerTransport.access$3500(r7.this$0).streamCreated(r8, r25, r1);
            r4.onStreamAllocated();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:133:0x0333, code lost:
            if (r0 == false) goto L_0x033d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:134:0x0335, code lost:
            r4.inboundDataReceived(new okio.Buffer(), 0, 0, r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:135:0x033d, code lost:
            monitor-exit(r19);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:136:0x033e, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:140:0x0342, code lost:
            respondWithHttpError(r24, r23, 404, io.grpc.Status.Code.UNIMPLEMENTED, "Expected path to start with /: " + io.grpc.okhttp.OkHttpServerTransport.access$2300(r10));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:141:0x0362, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0041, code lost:
            r3 = headerBlockSize(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x004d, code lost:
            if (r3 <= io.grpc.okhttp.OkHttpServerTransport.access$1100(r7.this$0).maxInboundMetadataSize) goto L_0x0079;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x004f, code lost:
            respondWithHttpError(r24, r23, 431, io.grpc.Status.Code.RESOURCE_EXHAUSTED, java.lang.String.format(java.util.Locale.US, "Request metadata larger than %d: %d", new java.lang.Object[]{java.lang.Integer.valueOf(io.grpc.okhttp.OkHttpServerTransport.access$1100(r7.this$0).maxInboundMetadataSize), java.lang.Integer.valueOf(r3)}));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0078, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0079, code lost:
            io.grpc.okhttp.OkHttpServerTransport.access$1200(r1, okio.ByteString.EMPTY);
            r3 = null;
            r8 = null;
            r9 = null;
            r10 = null;
            r11 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0089, code lost:
            if (r26.size() <= 0) goto L_0x00eb;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0097, code lost:
            if (r1.get(0).name.getByte(0) != 58) goto L_0x00eb;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0099, code lost:
            r12 = r1.remove(0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x00a9, code lost:
            if (io.grpc.okhttp.OkHttpServerTransport.access$1300().equals(r12.name) == false) goto L_0x00b0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x00ab, code lost:
            if (r8 != null) goto L_0x00b0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x00ad, code lost:
            r8 = r12.value;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x00ba, code lost:
            if (io.grpc.okhttp.OkHttpServerTransport.access$1400().equals(r12.name) == false) goto L_0x00c1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x00bc, code lost:
            if (r9 != null) goto L_0x00c1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x00be, code lost:
            r9 = r12.value;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x00cb, code lost:
            if (io.grpc.okhttp.OkHttpServerTransport.access$1500().equals(r12.name) == false) goto L_0x00d2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x00cd, code lost:
            if (r10 != null) goto L_0x00d2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x00cf, code lost:
            r10 = r12.value;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x00dc, code lost:
            if (io.grpc.okhttp.OkHttpServerTransport.access$1600().equals(r12.name) == false) goto L_0x00e3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x00de, code lost:
            if (r11 != null) goto L_0x00e3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:0x00e0, code lost:
            r11 = r12.value;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x00e3, code lost:
            streamError(r2, io.grpc.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR, "Unexpected pseudo header. RFC7540 section 8.1.2.1");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x00ea, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x00eb, code lost:
            r12 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:0x00f0, code lost:
            if (r12 >= r26.size()) goto L_0x010b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x00fe, code lost:
            if (r1.get(r12).name.getByte(0) != 58) goto L_0x0108;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x0100, code lost:
            streamError(r2, io.grpc.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR, "Pseudo header not before regular headers. RFC7540 section 8.1.2.1");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:0x0107, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:0x0108, code lost:
            r12 = r12 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:54:0x0113, code lost:
            if (io.grpc.okhttp.OkHttpServerTransport.access$1700().equals(r8) != false) goto L_0x0125;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:55:0x0115, code lost:
            if (r4 == false) goto L_0x0125;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:56:0x0117, code lost:
            if (r8 == null) goto L_0x011d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:57:0x0119, code lost:
            if (r9 == null) goto L_0x011d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:58:0x011b, code lost:
            if (r10 != null) goto L_0x0125;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:59:0x011d, code lost:
            streamError(r2, io.grpc.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR, "Missing required pseudo header. RFC7540 section 8.1.2.3");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:60:0x0124, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:62:0x012d, code lost:
            if (io.grpc.okhttp.OkHttpServerTransport.access$1900(r1, io.grpc.okhttp.OkHttpServerTransport.access$1800()) == false) goto L_0x0137;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:63:0x012f, code lost:
            streamError(r2, io.grpc.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR, "Connection-specific headers not permitted. RFC7540 section 8.1.2.2");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:64:0x0136, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:65:0x0137, code lost:
            if (r4 != false) goto L_0x0181;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:66:0x0139, code lost:
            if (r0 == false) goto L_0x0179;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:67:0x013b, code lost:
            r1 = io.grpc.okhttp.OkHttpServerTransport.access$300(r7.this$0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:68:0x0141, code lost:
            monitor-enter(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:70:?, code lost:
            r0 = (io.grpc.okhttp.OkHttpServerTransport.StreamState) io.grpc.okhttp.OkHttpServerTransport.access$2000(r7.this$0).get(java.lang.Integer.valueOf(r24));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:71:0x0152, code lost:
            if (r0 != null) goto L_0x015d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:72:0x0154, code lost:
            streamError(r2, io.grpc.okhttp.internal.framed.ErrorCode.STREAM_CLOSED, "Received headers for closed stream");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:73:0x015b, code lost:
            monitor-exit(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:74:0x015c, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:76:0x0161, code lost:
            if (r0.hasReceivedEndOfStream() == false) goto L_0x016c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:77:0x0163, code lost:
            streamError(r2, io.grpc.okhttp.internal.framed.ErrorCode.STREAM_CLOSED, "Received HEADERS for half-closed (remote) stream. RFC7540 section 5.1");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:78:0x016a, code lost:
            monitor-exit(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:79:0x016b, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:80:0x016c, code lost:
            r0.inboundDataReceived(new okio.Buffer(), 0, 0, true);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:81:0x0174, code lost:
            monitor-exit(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:82:0x0175, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:86:0x0179, code lost:
            streamError(r2, io.grpc.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR, "Headers disallowed in the middle of the stream. RFC7540 section 8.1");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:87:0x0180, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:88:0x0181, code lost:
            if (r11 != null) goto L_0x01b2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:89:0x0183, code lost:
            r4 = io.grpc.okhttp.OkHttpServerTransport.access$2200(r1, io.grpc.okhttp.OkHttpServerTransport.access$2100(), 0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:90:0x018c, code lost:
            if (r4 == -1) goto L_0x01b2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:92:0x0198, code lost:
            if (io.grpc.okhttp.OkHttpServerTransport.access$2200(r1, io.grpc.okhttp.OkHttpServerTransport.access$2100(), r4 + 1) == -1) goto L_0x01aa;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:93:0x019a, code lost:
            respondWithHttpError(r24, r23, 400, io.grpc.Status.Code.INTERNAL, "Multiple host headers disallowed. RFC7230 section 5.4");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:94:0x01a9, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:95:0x01aa, code lost:
            r11 = r1.get(r4).value;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:96:0x01b2, code lost:
            r4 = r11;
            io.grpc.okhttp.OkHttpServerTransport.access$1200(r1, io.grpc.okhttp.OkHttpServerTransport.access$2100());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:97:0x01be, code lost:
            if (r10.size() == 0) goto L_0x0342;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:99:0x01c6, code lost:
            if (r10.getByte(0) == 47) goto L_0x01ca;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void headers(boolean r22, boolean r23, int r24, int r25, java.util.List<io.grpc.okhttp.internal.framed.Header> r26, io.grpc.okhttp.internal.framed.HeadersMode r27) {
            /*
                r21 = this;
                r7 = r21
                r0 = r23
                r2 = r24
                r1 = r26
                io.grpc.okhttp.OkHttpFrameLogger r3 = r7.frameLogger
                io.grpc.okhttp.OkHttpFrameLogger$Direction r4 = io.grpc.okhttp.OkHttpFrameLogger.Direction.INBOUND
                r3.logHeaders(r4, r2, r1, r0)
                r3 = r2 & 1
                if (r3 != 0) goto L_0x001b
                io.grpc.okhttp.internal.framed.ErrorCode r0 = io.grpc.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR
                java.lang.String r1 = "Clients cannot open even numbered streams. RFC7540 section 5.1.1"
                r7.connectionError(r0, r1)
                return
            L_0x001b:
                io.grpc.okhttp.OkHttpServerTransport r3 = io.grpc.okhttp.OkHttpServerTransport.this
                java.lang.Object r3 = r3.lock
                monitor-enter(r3)
                io.grpc.okhttp.OkHttpServerTransport r4 = io.grpc.okhttp.OkHttpServerTransport.this     // Catch:{ all -> 0x0363 }
                int r4 = r4.goAwayStreamId     // Catch:{ all -> 0x0363 }
                if (r2 <= r4) goto L_0x002c
                monitor-exit(r3)     // Catch:{ all -> 0x0363 }
                return
            L_0x002c:
                io.grpc.okhttp.OkHttpServerTransport r4 = io.grpc.okhttp.OkHttpServerTransport.this     // Catch:{ all -> 0x0363 }
                int r4 = r4.lastStreamId     // Catch:{ all -> 0x0363 }
                r5 = 0
                r6 = 1
                if (r2 <= r4) goto L_0x0038
                r4 = r6
                goto L_0x0039
            L_0x0038:
                r4 = r5
            L_0x0039:
                if (r4 == 0) goto L_0x0040
                io.grpc.okhttp.OkHttpServerTransport r8 = io.grpc.okhttp.OkHttpServerTransport.this     // Catch:{ all -> 0x0363 }
                int unused = r8.lastStreamId = r2     // Catch:{ all -> 0x0363 }
            L_0x0040:
                monitor-exit(r3)     // Catch:{ all -> 0x0363 }
                int r3 = r7.headerBlockSize(r1)
                io.grpc.okhttp.OkHttpServerTransport r8 = io.grpc.okhttp.OkHttpServerTransport.this
                io.grpc.okhttp.OkHttpServerTransport$Config r8 = r8.config
                int r8 = r8.maxInboundMetadataSize
                if (r3 <= r8) goto L_0x0079
                r4 = 431(0x1af, float:6.04E-43)
                io.grpc.Status$Code r5 = io.grpc.Status.Code.RESOURCE_EXHAUSTED
                java.util.Locale r1 = java.util.Locale.US
                java.lang.String r6 = "Request metadata larger than %d: %d"
                io.grpc.okhttp.OkHttpServerTransport r8 = io.grpc.okhttp.OkHttpServerTransport.this
                io.grpc.okhttp.OkHttpServerTransport$Config r8 = r8.config
                int r8 = r8.maxInboundMetadataSize
                java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
                java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                java.lang.Object[] r3 = new java.lang.Object[]{r8, r3}
                java.lang.String r6 = java.lang.String.format(r1, r6, r3)
                r1 = r21
                r2 = r24
                r3 = r23
                r1.respondWithHttpError(r2, r3, r4, r5, r6)
                return
            L_0x0079:
                okio.ByteString r3 = okio.ByteString.EMPTY
                io.grpc.okhttp.OkHttpServerTransport.headerRemove(r1, r3)
                r3 = 0
                r8 = r3
                r9 = r8
                r10 = r9
                r11 = r10
            L_0x0083:
                int r12 = r26.size()
                r13 = 58
                if (r12 <= 0) goto L_0x00eb
                java.lang.Object r12 = r1.get(r5)
                io.grpc.okhttp.internal.framed.Header r12 = (io.grpc.okhttp.internal.framed.Header) r12
                okio.ByteString r12 = r12.name
                byte r12 = r12.getByte(r5)
                if (r12 != r13) goto L_0x00eb
                java.lang.Object r12 = r1.remove(r5)
                io.grpc.okhttp.internal.framed.Header r12 = (io.grpc.okhttp.internal.framed.Header) r12
                okio.ByteString r13 = io.grpc.okhttp.OkHttpServerTransport.HTTP_METHOD
                okio.ByteString r14 = r12.name
                boolean r13 = r13.equals(r14)
                if (r13 == 0) goto L_0x00b0
                if (r8 != 0) goto L_0x00b0
                okio.ByteString r8 = r12.value
                goto L_0x0083
            L_0x00b0:
                okio.ByteString r13 = io.grpc.okhttp.OkHttpServerTransport.SCHEME
                okio.ByteString r14 = r12.name
                boolean r13 = r13.equals(r14)
                if (r13 == 0) goto L_0x00c1
                if (r9 != 0) goto L_0x00c1
                okio.ByteString r9 = r12.value
                goto L_0x0083
            L_0x00c1:
                okio.ByteString r13 = io.grpc.okhttp.OkHttpServerTransport.PATH
                okio.ByteString r14 = r12.name
                boolean r13 = r13.equals(r14)
                if (r13 == 0) goto L_0x00d2
                if (r10 != 0) goto L_0x00d2
                okio.ByteString r10 = r12.value
                goto L_0x0083
            L_0x00d2:
                okio.ByteString r13 = io.grpc.okhttp.OkHttpServerTransport.AUTHORITY
                okio.ByteString r14 = r12.name
                boolean r13 = r13.equals(r14)
                if (r13 == 0) goto L_0x00e3
                if (r11 != 0) goto L_0x00e3
                okio.ByteString r11 = r12.value
                goto L_0x0083
            L_0x00e3:
                io.grpc.okhttp.internal.framed.ErrorCode r0 = io.grpc.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR
                java.lang.String r1 = "Unexpected pseudo header. RFC7540 section 8.1.2.1"
                r7.streamError(r2, r0, r1)
                return
            L_0x00eb:
                r12 = r5
            L_0x00ec:
                int r14 = r26.size()
                if (r12 >= r14) goto L_0x010b
                java.lang.Object r14 = r1.get(r12)
                io.grpc.okhttp.internal.framed.Header r14 = (io.grpc.okhttp.internal.framed.Header) r14
                okio.ByteString r14 = r14.name
                byte r14 = r14.getByte(r5)
                if (r14 != r13) goto L_0x0108
                io.grpc.okhttp.internal.framed.ErrorCode r0 = io.grpc.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR
                java.lang.String r1 = "Pseudo header not before regular headers. RFC7540 section 8.1.2.1"
                r7.streamError(r2, r0, r1)
                return
            L_0x0108:
                int r12 = r12 + 1
                goto L_0x00ec
            L_0x010b:
                okio.ByteString r12 = io.grpc.okhttp.OkHttpServerTransport.CONNECT_METHOD
                boolean r12 = r12.equals(r8)
                if (r12 != 0) goto L_0x0125
                if (r4 == 0) goto L_0x0125
                if (r8 == 0) goto L_0x011d
                if (r9 == 0) goto L_0x011d
                if (r10 != 0) goto L_0x0125
            L_0x011d:
                io.grpc.okhttp.internal.framed.ErrorCode r0 = io.grpc.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR
                java.lang.String r1 = "Missing required pseudo header. RFC7540 section 8.1.2.3"
                r7.streamError(r2, r0, r1)
                return
            L_0x0125:
                okio.ByteString r9 = io.grpc.okhttp.OkHttpServerTransport.CONNECTION
                boolean r9 = io.grpc.okhttp.OkHttpServerTransport.headerContains(r1, r9)
                if (r9 == 0) goto L_0x0137
                io.grpc.okhttp.internal.framed.ErrorCode r0 = io.grpc.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR
                java.lang.String r1 = "Connection-specific headers not permitted. RFC7540 section 8.1.2.2"
                r7.streamError(r2, r0, r1)
                return
            L_0x0137:
                if (r4 != 0) goto L_0x0181
                if (r0 == 0) goto L_0x0179
                io.grpc.okhttp.OkHttpServerTransport r0 = io.grpc.okhttp.OkHttpServerTransport.this
                java.lang.Object r1 = r0.lock
                monitor-enter(r1)
                io.grpc.okhttp.OkHttpServerTransport r0 = io.grpc.okhttp.OkHttpServerTransport.this     // Catch:{ all -> 0x0176 }
                java.util.Map r0 = r0.streams     // Catch:{ all -> 0x0176 }
                java.lang.Integer r3 = java.lang.Integer.valueOf(r24)     // Catch:{ all -> 0x0176 }
                java.lang.Object r0 = r0.get(r3)     // Catch:{ all -> 0x0176 }
                io.grpc.okhttp.OkHttpServerTransport$StreamState r0 = (io.grpc.okhttp.OkHttpServerTransport.StreamState) r0     // Catch:{ all -> 0x0176 }
                if (r0 != 0) goto L_0x015d
                io.grpc.okhttp.internal.framed.ErrorCode r0 = io.grpc.okhttp.internal.framed.ErrorCode.STREAM_CLOSED     // Catch:{ all -> 0x0176 }
                java.lang.String r3 = "Received headers for closed stream"
                r7.streamError(r2, r0, r3)     // Catch:{ all -> 0x0176 }
                monitor-exit(r1)     // Catch:{ all -> 0x0176 }
                return
            L_0x015d:
                boolean r3 = r0.hasReceivedEndOfStream()     // Catch:{ all -> 0x0176 }
                if (r3 == 0) goto L_0x016c
                io.grpc.okhttp.internal.framed.ErrorCode r0 = io.grpc.okhttp.internal.framed.ErrorCode.STREAM_CLOSED     // Catch:{ all -> 0x0176 }
                java.lang.String r3 = "Received HEADERS for half-closed (remote) stream. RFC7540 section 5.1"
                r7.streamError(r2, r0, r3)     // Catch:{ all -> 0x0176 }
                monitor-exit(r1)     // Catch:{ all -> 0x0176 }
                return
            L_0x016c:
                okio.Buffer r2 = new okio.Buffer     // Catch:{ all -> 0x0176 }
                r2.<init>()     // Catch:{ all -> 0x0176 }
                r0.inboundDataReceived(r2, r5, r5, r6)     // Catch:{ all -> 0x0176 }
                monitor-exit(r1)     // Catch:{ all -> 0x0176 }
                return
            L_0x0176:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x0176 }
                throw r0
            L_0x0179:
                io.grpc.okhttp.internal.framed.ErrorCode r0 = io.grpc.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR
                java.lang.String r1 = "Headers disallowed in the middle of the stream. RFC7540 section 8.1"
                r7.streamError(r2, r0, r1)
                return
            L_0x0181:
                if (r11 != 0) goto L_0x01b2
                okio.ByteString r4 = io.grpc.okhttp.OkHttpServerTransport.HOST
                int r4 = io.grpc.okhttp.OkHttpServerTransport.headerFind(r1, r4, r5)
                r9 = -1
                if (r4 == r9) goto L_0x01b2
                okio.ByteString r11 = io.grpc.okhttp.OkHttpServerTransport.HOST
                int r12 = r4 + 1
                int r11 = io.grpc.okhttp.OkHttpServerTransport.headerFind(r1, r11, r12)
                if (r11 == r9) goto L_0x01aa
                r4 = 400(0x190, float:5.6E-43)
                io.grpc.Status$Code r5 = io.grpc.Status.Code.INTERNAL
                java.lang.String r6 = "Multiple host headers disallowed. RFC7230 section 5.4"
                r1 = r21
                r2 = r24
                r3 = r23
                r1.respondWithHttpError(r2, r3, r4, r5, r6)
                return
            L_0x01aa:
                java.lang.Object r4 = r1.get(r4)
                io.grpc.okhttp.internal.framed.Header r4 = (io.grpc.okhttp.internal.framed.Header) r4
                okio.ByteString r11 = r4.value
            L_0x01b2:
                r4 = r11
                okio.ByteString r9 = io.grpc.okhttp.OkHttpServerTransport.HOST
                io.grpc.okhttp.OkHttpServerTransport.headerRemove(r1, r9)
                int r9 = r10.size()
                if (r9 == 0) goto L_0x0342
                byte r9 = r10.getByte(r5)
                r11 = 47
                if (r9 == r11) goto L_0x01ca
                goto L_0x0342
            L_0x01ca:
                java.lang.String r9 = io.grpc.okhttp.OkHttpServerTransport.asciiString(r10)
                java.lang.String r15 = r9.substring(r6)
                okio.ByteString r9 = io.grpc.okhttp.OkHttpServerTransport.CONTENT_TYPE
                okio.ByteString r9 = io.grpc.okhttp.OkHttpServerTransport.headerGetRequiredSingle(r1, r9)
                if (r9 != 0) goto L_0x01ec
                r4 = 415(0x19f, float:5.82E-43)
                io.grpc.Status$Code r5 = io.grpc.Status.Code.INTERNAL
                java.lang.String r6 = "Content-Type is missing or duplicated"
                r1 = r21
                r2 = r24
                r3 = r23
                r1.respondWithHttpError(r2, r3, r4, r5, r6)
                return
            L_0x01ec:
                java.lang.String r9 = io.grpc.okhttp.OkHttpServerTransport.asciiString(r9)
                boolean r10 = io.grpc.internal.GrpcUtil.isGrpcContentType(r9)
                if (r10 != 0) goto L_0x0213
                r4 = 415(0x19f, float:5.82E-43)
                io.grpc.Status$Code r5 = io.grpc.Status.Code.INTERNAL
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r3 = "Content-Type is not supported: "
                r1.<init>(r3)
                java.lang.StringBuilder r1 = r1.append(r9)
                java.lang.String r6 = r1.toString()
                r1 = r21
                r2 = r24
                r3 = r23
                r1.respondWithHttpError(r2, r3, r4, r5, r6)
                return
            L_0x0213:
                okio.ByteString r9 = io.grpc.okhttp.OkHttpServerTransport.POST_METHOD
                boolean r9 = r9.equals(r8)
                if (r9 != 0) goto L_0x023e
                r4 = 405(0x195, float:5.68E-43)
                io.grpc.Status$Code r5 = io.grpc.Status.Code.INTERNAL
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r3 = "HTTP Method is not supported: "
                r1.<init>(r3)
                java.lang.String r3 = io.grpc.okhttp.OkHttpServerTransport.asciiString(r8)
                java.lang.StringBuilder r1 = r1.append(r3)
                java.lang.String r6 = r1.toString()
                r1 = r21
                r2 = r24
                r3 = r23
                r1.respondWithHttpError(r2, r3, r4, r5, r6)
                return
            L_0x023e:
                okio.ByteString r8 = io.grpc.okhttp.OkHttpServerTransport.TE
                okio.ByteString r8 = io.grpc.okhttp.OkHttpServerTransport.headerGetRequiredSingle(r1, r8)
                okio.ByteString r9 = io.grpc.okhttp.OkHttpServerTransport.TE_TRAILERS
                boolean r9 = r9.equals(r8)
                if (r9 != 0) goto L_0x0274
                io.grpc.Status$Code r1 = io.grpc.Status.Code.INTERNAL
                java.lang.String r3 = "Expected header TE: %s, but %s is received. Some intermediate proxy may not support trailers"
                r4 = 2
                java.lang.Object[] r4 = new java.lang.Object[r4]
                okio.ByteString r9 = io.grpc.okhttp.OkHttpServerTransport.TE_TRAILERS
                java.lang.String r9 = io.grpc.okhttp.OkHttpServerTransport.asciiString(r9)
                r4[r5] = r9
                if (r8 != 0) goto L_0x0266
                java.lang.String r5 = "<missing>"
                goto L_0x026a
            L_0x0266:
                java.lang.String r5 = io.grpc.okhttp.OkHttpServerTransport.asciiString(r8)
            L_0x026a:
                r4[r6] = r5
                java.lang.String r3 = java.lang.String.format(r3, r4)
                r7.respondWithGrpcError(r2, r0, r1, r3)
                return
            L_0x0274:
                okio.ByteString r6 = io.grpc.okhttp.OkHttpServerTransport.CONTENT_LENGTH
                io.grpc.okhttp.OkHttpServerTransport.headerRemove(r1, r6)
                io.grpc.Metadata r1 = io.grpc.okhttp.Utils.convertHeaders(r26)
                io.grpc.okhttp.OkHttpServerTransport r6 = io.grpc.okhttp.OkHttpServerTransport.this
                io.grpc.okhttp.OkHttpServerTransport$Config r6 = r6.config
                java.util.List<? extends io.grpc.ServerStreamTracer$Factory> r6 = r6.streamTracerFactories
                io.grpc.internal.StatsTraceContext r6 = io.grpc.internal.StatsTraceContext.newServerContext(r6, r15, r1)
                io.grpc.okhttp.OkHttpServerTransport r8 = io.grpc.okhttp.OkHttpServerTransport.this
                java.lang.Object r19 = r8.lock
                monitor-enter(r19)
                io.grpc.okhttp.OkHttpServerStream$TransportState r14 = new io.grpc.okhttp.OkHttpServerStream$TransportState     // Catch:{ all -> 0x033f }
                io.grpc.okhttp.OkHttpServerTransport r9 = io.grpc.okhttp.OkHttpServerTransport.this     // Catch:{ all -> 0x033f }
                io.grpc.okhttp.OkHttpServerTransport$Config r8 = r9.config     // Catch:{ all -> 0x033f }
                int r11 = r8.maxInboundMessageSize     // Catch:{ all -> 0x033f }
                io.grpc.okhttp.OkHttpServerTransport r8 = io.grpc.okhttp.OkHttpServerTransport.this     // Catch:{ all -> 0x033f }
                java.lang.Object r13 = r8.lock     // Catch:{ all -> 0x033f }
                io.grpc.okhttp.OkHttpServerTransport r8 = io.grpc.okhttp.OkHttpServerTransport.this     // Catch:{ all -> 0x033f }
                io.grpc.okhttp.ExceptionHandlingFrameWriter r16 = r8.frameWriter     // Catch:{ all -> 0x033f }
                io.grpc.okhttp.OkHttpServerTransport r8 = io.grpc.okhttp.OkHttpServerTransport.this     // Catch:{ all -> 0x033f }
                io.grpc.okhttp.OutboundFlowController r17 = r8.outboundFlow     // Catch:{ all -> 0x033f }
                io.grpc.okhttp.OkHttpServerTransport r8 = io.grpc.okhttp.OkHttpServerTransport.this     // Catch:{ all -> 0x033f }
                io.grpc.okhttp.OkHttpServerTransport$Config r8 = r8.config     // Catch:{ all -> 0x033f }
                int r12 = r8.flowControlWindow     // Catch:{ all -> 0x033f }
                io.grpc.okhttp.OkHttpServerTransport r8 = io.grpc.okhttp.OkHttpServerTransport.this     // Catch:{ all -> 0x033f }
                io.grpc.internal.TransportTracer r18 = r8.tracer     // Catch:{ all -> 0x033f }
                r8 = r14
                r10 = r24
                r20 = r12
                r12 = r6
                r22 = r14
                r14 = r16
                r25 = r15
                r15 = r17
                r16 = r20
                r17 = r18
                r18 = r25
                r8.<init>(r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)     // Catch:{ all -> 0x033f }
                io.grpc.okhttp.OkHttpServerStream r14 = new io.grpc.okhttp.OkHttpServerStream     // Catch:{ all -> 0x033f }
                io.grpc.okhttp.OkHttpServerTransport r8 = io.grpc.okhttp.OkHttpServerTransport.this     // Catch:{ all -> 0x033f }
                io.grpc.Attributes r10 = r8.attributes     // Catch:{ all -> 0x033f }
                if (r4 != 0) goto L_0x02de
                goto L_0x02e2
            L_0x02de:
                java.lang.String r3 = io.grpc.okhttp.OkHttpServerTransport.asciiString(r4)     // Catch:{ all -> 0x033f }
            L_0x02e2:
                r11 = r3
                io.grpc.okhttp.OkHttpServerTransport r3 = io.grpc.okhttp.OkHttpServerTransport.this     // Catch:{ all -> 0x033f }
                io.grpc.internal.TransportTracer r13 = r3.tracer     // Catch:{ all -> 0x033f }
                r8 = r14
                r9 = r22
                r12 = r6
                r8.<init>(r9, r10, r11, r12, r13)     // Catch:{ all -> 0x033f }
                io.grpc.okhttp.OkHttpServerTransport r3 = io.grpc.okhttp.OkHttpServerTransport.this     // Catch:{ all -> 0x033f }
                java.util.Map r3 = r3.streams     // Catch:{ all -> 0x033f }
                boolean r3 = r3.isEmpty()     // Catch:{ all -> 0x033f }
                if (r3 == 0) goto L_0x0316
                io.grpc.okhttp.OkHttpServerTransport r3 = io.grpc.okhttp.OkHttpServerTransport.this     // Catch:{ all -> 0x033f }
                io.grpc.internal.KeepAliveEnforcer r3 = r3.keepAliveEnforcer     // Catch:{ all -> 0x033f }
                r3.onTransportActive()     // Catch:{ all -> 0x033f }
                io.grpc.okhttp.OkHttpServerTransport r3 = io.grpc.okhttp.OkHttpServerTransport.this     // Catch:{ all -> 0x033f }
                io.grpc.internal.MaxConnectionIdleManager r3 = r3.maxConnectionIdleManager     // Catch:{ all -> 0x033f }
                if (r3 == 0) goto L_0x0316
                io.grpc.okhttp.OkHttpServerTransport r3 = io.grpc.okhttp.OkHttpServerTransport.this     // Catch:{ all -> 0x033f }
                io.grpc.internal.MaxConnectionIdleManager r3 = r3.maxConnectionIdleManager     // Catch:{ all -> 0x033f }
                r3.onTransportActive()     // Catch:{ all -> 0x033f }
            L_0x0316:
                io.grpc.okhttp.OkHttpServerTransport r3 = io.grpc.okhttp.OkHttpServerTransport.this     // Catch:{ all -> 0x033f }
                java.util.Map r3 = r3.streams     // Catch:{ all -> 0x033f }
                java.lang.Integer r2 = java.lang.Integer.valueOf(r24)     // Catch:{ all -> 0x033f }
                r4 = r22
                r3.put(r2, r4)     // Catch:{ all -> 0x033f }
                io.grpc.okhttp.OkHttpServerTransport r2 = io.grpc.okhttp.OkHttpServerTransport.this     // Catch:{ all -> 0x033f }
                io.grpc.internal.ServerTransportListener r2 = r2.listener     // Catch:{ all -> 0x033f }
                r3 = r25
                r2.streamCreated(r14, r3, r1)     // Catch:{ all -> 0x033f }
                r4.onStreamAllocated()     // Catch:{ all -> 0x033f }
                if (r0 == 0) goto L_0x033d
                okio.Buffer r1 = new okio.Buffer     // Catch:{ all -> 0x033f }
                r1.<init>()     // Catch:{ all -> 0x033f }
                r4.inboundDataReceived(r1, r5, r5, r0)     // Catch:{ all -> 0x033f }
            L_0x033d:
                monitor-exit(r19)     // Catch:{ all -> 0x033f }
                return
            L_0x033f:
                r0 = move-exception
                monitor-exit(r19)     // Catch:{ all -> 0x033f }
                throw r0
            L_0x0342:
                r4 = 404(0x194, float:5.66E-43)
                io.grpc.Status$Code r5 = io.grpc.Status.Code.UNIMPLEMENTED
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r3 = "Expected path to start with /: "
                r1.<init>(r3)
                java.lang.String r3 = io.grpc.okhttp.OkHttpServerTransport.asciiString(r10)
                java.lang.StringBuilder r1 = r1.append(r3)
                java.lang.String r6 = r1.toString()
                r1 = r21
                r2 = r24
                r3 = r23
                r1.respondWithHttpError(r2, r3, r4, r5, r6)
                return
            L_0x0363:
                r0 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x0363 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.okhttp.OkHttpServerTransport.FrameHandler.headers(boolean, boolean, int, int, java.util.List, io.grpc.okhttp.internal.framed.HeadersMode):void");
        }

        private int headerBlockSize(List<io.grpc.okhttp.internal.framed.Header> list) {
            long j = 0;
            for (int i = 0; i < list.size(); i++) {
                io.grpc.okhttp.internal.framed.Header header = list.get(i);
                j += (long) (header.name.size() + 32 + header.value.size());
            }
            return (int) Math.min(j, 2147483647L);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0083, code lost:
            r7 = r6.connectionUnacknowledgedBytesRead + r11;
            r6.connectionUnacknowledgedBytesRead = r7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x0097, code lost:
            if (((float) r7) < (((float) io.grpc.okhttp.OkHttpServerTransport.access$1100(r6.this$0).flowControlWindow) * 0.5f)) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x0099, code lost:
            r7 = io.grpc.okhttp.OkHttpServerTransport.access$300(r6.this$0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x009f, code lost:
            monitor-enter(r7);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
            io.grpc.okhttp.OkHttpServerTransport.access$3000(r6.this$0).windowUpdate(0, (long) r6.connectionUnacknowledgedBytesRead);
            io.grpc.okhttp.OkHttpServerTransport.access$3000(r6.this$0).flush();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x00b6, code lost:
            monitor-exit(r7);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x00b7, code lost:
            r6.connectionUnacknowledgedBytesRead = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void data(boolean r7, int r8, okio.BufferedSource r9, int r10, int r11) throws java.io.IOException {
            /*
                r6 = this;
                io.grpc.okhttp.OkHttpFrameLogger r0 = r6.frameLogger
                io.grpc.okhttp.OkHttpFrameLogger$Direction r1 = io.grpc.okhttp.OkHttpFrameLogger.Direction.INBOUND
                okio.Buffer r3 = r9.getBuffer()
                r2 = r8
                r4 = r10
                r5 = r7
                r0.logData(r1, r2, r3, r4, r5)
                if (r8 != 0) goto L_0x0018
                io.grpc.okhttp.internal.framed.ErrorCode r7 = io.grpc.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR
                java.lang.String r8 = "Stream 0 is reserved for control messages. RFC7540 section 5.1.1"
                r6.connectionError(r7, r8)
                return
            L_0x0018:
                r0 = r8 & 1
                if (r0 != 0) goto L_0x0024
                io.grpc.okhttp.internal.framed.ErrorCode r7 = io.grpc.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR
                java.lang.String r8 = "Clients cannot open even numbered streams. RFC7540 section 5.1.1"
                r6.connectionError(r7, r8)
                return
            L_0x0024:
                long r0 = (long) r10
                r9.require(r0)
                io.grpc.okhttp.OkHttpServerTransport r2 = io.grpc.okhttp.OkHttpServerTransport.this
                java.lang.Object r2 = r2.lock
                monitor-enter(r2)
                io.grpc.okhttp.OkHttpServerTransport r3 = io.grpc.okhttp.OkHttpServerTransport.this     // Catch:{ all -> 0x00be }
                java.util.Map r3 = r3.streams     // Catch:{ all -> 0x00be }
                java.lang.Integer r4 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x00be }
                java.lang.Object r3 = r3.get(r4)     // Catch:{ all -> 0x00be }
                io.grpc.okhttp.OkHttpServerTransport$StreamState r3 = (io.grpc.okhttp.OkHttpServerTransport.StreamState) r3     // Catch:{ all -> 0x00be }
                if (r3 != 0) goto L_0x004d
                r9.skip(r0)     // Catch:{ all -> 0x00be }
                io.grpc.okhttp.internal.framed.ErrorCode r7 = io.grpc.okhttp.internal.framed.ErrorCode.STREAM_CLOSED     // Catch:{ all -> 0x00be }
                java.lang.String r9 = "Received data for closed stream"
                r6.streamError(r8, r7, r9)     // Catch:{ all -> 0x00be }
                monitor-exit(r2)     // Catch:{ all -> 0x00be }
                return
            L_0x004d:
                boolean r4 = r3.hasReceivedEndOfStream()     // Catch:{ all -> 0x00be }
                if (r4 == 0) goto L_0x005f
                r9.skip(r0)     // Catch:{ all -> 0x00be }
                io.grpc.okhttp.internal.framed.ErrorCode r7 = io.grpc.okhttp.internal.framed.ErrorCode.STREAM_CLOSED     // Catch:{ all -> 0x00be }
                java.lang.String r9 = "Received DATA for half-closed (remote) stream. RFC7540 section 5.1"
                r6.streamError(r8, r7, r9)     // Catch:{ all -> 0x00be }
                monitor-exit(r2)     // Catch:{ all -> 0x00be }
                return
            L_0x005f:
                int r4 = r3.inboundWindowAvailable()     // Catch:{ all -> 0x00be }
                if (r4 >= r11) goto L_0x0071
                r9.skip(r0)     // Catch:{ all -> 0x00be }
                io.grpc.okhttp.internal.framed.ErrorCode r7 = io.grpc.okhttp.internal.framed.ErrorCode.FLOW_CONTROL_ERROR     // Catch:{ all -> 0x00be }
                java.lang.String r9 = "Received DATA size exceeded window size. RFC7540 section 6.9"
                r6.streamError(r8, r7, r9)     // Catch:{ all -> 0x00be }
                monitor-exit(r2)     // Catch:{ all -> 0x00be }
                return
            L_0x0071:
                okio.Buffer r8 = new okio.Buffer     // Catch:{ all -> 0x00be }
                r8.<init>()     // Catch:{ all -> 0x00be }
                okio.Buffer r9 = r9.getBuffer()     // Catch:{ all -> 0x00be }
                r8.write((okio.Buffer) r9, (long) r0)     // Catch:{ all -> 0x00be }
                int r9 = r11 - r10
                r3.inboundDataReceived(r8, r10, r9, r7)     // Catch:{ all -> 0x00be }
                monitor-exit(r2)     // Catch:{ all -> 0x00be }
                int r7 = r6.connectionUnacknowledgedBytesRead
                int r7 = r7 + r11
                r6.connectionUnacknowledgedBytesRead = r7
                float r7 = (float) r7
                io.grpc.okhttp.OkHttpServerTransport r8 = io.grpc.okhttp.OkHttpServerTransport.this
                io.grpc.okhttp.OkHttpServerTransport$Config r8 = r8.config
                int r8 = r8.flowControlWindow
                float r8 = (float) r8
                r9 = 1056964608(0x3f000000, float:0.5)
                float r8 = r8 * r9
                int r7 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
                if (r7 < 0) goto L_0x00bd
                io.grpc.okhttp.OkHttpServerTransport r7 = io.grpc.okhttp.OkHttpServerTransport.this
                java.lang.Object r7 = r7.lock
                monitor-enter(r7)
                io.grpc.okhttp.OkHttpServerTransport r8 = io.grpc.okhttp.OkHttpServerTransport.this     // Catch:{ all -> 0x00ba }
                io.grpc.okhttp.ExceptionHandlingFrameWriter r8 = r8.frameWriter     // Catch:{ all -> 0x00ba }
                int r9 = r6.connectionUnacknowledgedBytesRead     // Catch:{ all -> 0x00ba }
                long r9 = (long) r9     // Catch:{ all -> 0x00ba }
                r11 = 0
                r8.windowUpdate(r11, r9)     // Catch:{ all -> 0x00ba }
                io.grpc.okhttp.OkHttpServerTransport r8 = io.grpc.okhttp.OkHttpServerTransport.this     // Catch:{ all -> 0x00ba }
                io.grpc.okhttp.ExceptionHandlingFrameWriter r8 = r8.frameWriter     // Catch:{ all -> 0x00ba }
                r8.flush()     // Catch:{ all -> 0x00ba }
                monitor-exit(r7)     // Catch:{ all -> 0x00ba }
                r6.connectionUnacknowledgedBytesRead = r11
                goto L_0x00bd
            L_0x00ba:
                r8 = move-exception
                monitor-exit(r7)     // Catch:{ all -> 0x00ba }
                throw r8
            L_0x00bd:
                return
            L_0x00be:
                r7 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x00be }
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.okhttp.OkHttpServerTransport.FrameHandler.data(boolean, int, okio.BufferedSource, int, int):void");
        }

        public void rstStream(int i, ErrorCode errorCode) {
            this.frameLogger.logRstStream(OkHttpFrameLogger.Direction.INBOUND, i, errorCode);
            if (!ErrorCode.NO_ERROR.equals(errorCode) && !ErrorCode.CANCEL.equals(errorCode) && !ErrorCode.STREAM_CLOSED.equals(errorCode)) {
                OkHttpServerTransport.log.log(Level.INFO, "Received RST_STREAM: " + errorCode);
            }
            Status withDescription = GrpcUtil.Http2Error.statusForCode((long) errorCode.httpCode).withDescription("RST_STREAM");
            synchronized (OkHttpServerTransport.this.lock) {
                StreamState streamState = (StreamState) OkHttpServerTransport.this.streams.get(Integer.valueOf(i));
                if (streamState != null) {
                    streamState.inboundRstReceived(withDescription);
                    OkHttpServerTransport.this.streamClosed(i, false);
                }
            }
        }

        public void settings(boolean z, Settings settings) {
            boolean z2;
            this.frameLogger.logSettings(OkHttpFrameLogger.Direction.INBOUND, settings);
            synchronized (OkHttpServerTransport.this.lock) {
                if (OkHttpSettingsUtil.isSet(settings, 7)) {
                    z2 = OkHttpServerTransport.this.outboundFlow.initialOutboundWindowSize(OkHttpSettingsUtil.get(settings, 7));
                } else {
                    z2 = false;
                }
                OkHttpServerTransport.this.frameWriter.ackSettings(settings);
                OkHttpServerTransport.this.frameWriter.flush();
                if (!this.receivedSettings) {
                    this.receivedSettings = true;
                    OkHttpServerTransport okHttpServerTransport = OkHttpServerTransport.this;
                    Attributes unused = okHttpServerTransport.attributes = okHttpServerTransport.listener.transportReady(OkHttpServerTransport.this.attributes);
                }
                if (z2) {
                    OkHttpServerTransport.this.outboundFlow.writeStreams();
                }
            }
        }

        public void ping(boolean z, int i, int i2) {
            if (!OkHttpServerTransport.this.keepAliveEnforcer.pingAcceptable()) {
                OkHttpServerTransport.this.abruptShutdown(ErrorCode.ENHANCE_YOUR_CALM, "too_many_pings", Status.RESOURCE_EXHAUSTED.withDescription("Too many pings from client"), false);
                return;
            }
            long j = (((long) i) << 32) | (((long) i2) & MuxerUtil.UNSIGNED_INT_MAX_VALUE);
            if (!z) {
                this.frameLogger.logPing(OkHttpFrameLogger.Direction.INBOUND, j);
                synchronized (OkHttpServerTransport.this.lock) {
                    OkHttpServerTransport.this.frameWriter.ping(true, i, i2);
                    OkHttpServerTransport.this.frameWriter.flush();
                }
                return;
            }
            this.frameLogger.logPingAck(OkHttpFrameLogger.Direction.INBOUND, j);
            if (57005 != j) {
                if (4369 == j) {
                    OkHttpServerTransport.this.triggerGracefulSecondGoaway();
                } else {
                    OkHttpServerTransport.log.log(Level.INFO, "Received unexpected ping ack: " + j);
                }
            }
        }

        public void goAway(int i, ErrorCode errorCode, ByteString byteString) {
            this.frameLogger.logGoAway(OkHttpFrameLogger.Direction.INBOUND, i, errorCode, byteString);
            Status withDescription = GrpcUtil.Http2Error.statusForCode((long) errorCode.httpCode).withDescription(String.format("Received GOAWAY: %s '%s'", new Object[]{errorCode, byteString.utf8()}));
            if (!ErrorCode.NO_ERROR.equals(errorCode)) {
                OkHttpServerTransport.log.log(Level.WARNING, "Received GOAWAY: {0} {1}", new Object[]{errorCode, byteString.utf8()});
            }
            synchronized (OkHttpServerTransport.this.lock) {
                Status unused = OkHttpServerTransport.this.goAwayStatus = withDescription;
            }
        }

        public void pushPromise(int i, int i2, List<io.grpc.okhttp.internal.framed.Header> list) throws IOException {
            this.frameLogger.logPushPromise(OkHttpFrameLogger.Direction.INBOUND, i, i2, list);
            connectionError(ErrorCode.PROTOCOL_ERROR, "PUSH_PROMISE only allowed on peer-initiated streams. RFC7540 section 6.6");
        }

        public void windowUpdate(int i, long j) {
            this.frameLogger.logWindowsUpdate(OkHttpFrameLogger.Direction.INBOUND, i, j);
            synchronized (OkHttpServerTransport.this.lock) {
                if (i == 0) {
                    OkHttpServerTransport.this.outboundFlow.windowUpdate((OutboundFlowController.StreamState) null, (int) j);
                } else {
                    StreamState streamState = (StreamState) OkHttpServerTransport.this.streams.get(Integer.valueOf(i));
                    if (streamState != null) {
                        OkHttpServerTransport.this.outboundFlow.windowUpdate(streamState.getOutboundFlowState(), (int) j);
                    }
                }
            }
        }

        public void priority(int i, int i2, int i3, boolean z) {
            this.frameLogger.logPriority(OkHttpFrameLogger.Direction.INBOUND, i, i2, i3, z);
        }

        private void connectionError(ErrorCode errorCode, String str) {
            OkHttpServerTransport.this.abruptShutdown(errorCode, str, GrpcUtil.Http2Error.statusForCode((long) errorCode.httpCode).withDescription(String.format("HTTP2 connection error: %s '%s'", new Object[]{errorCode, str})), false);
        }

        private void streamError(int i, ErrorCode errorCode, String str) {
            if (errorCode == ErrorCode.PROTOCOL_ERROR) {
                OkHttpServerTransport.log.log(Level.FINE, "Responding with RST_STREAM {0}: {1}", new Object[]{errorCode, str});
            }
            synchronized (OkHttpServerTransport.this.lock) {
                OkHttpServerTransport.this.frameWriter.rstStream(i, errorCode);
                OkHttpServerTransport.this.frameWriter.flush();
                StreamState streamState = (StreamState) OkHttpServerTransport.this.streams.get(Integer.valueOf(i));
                if (streamState != null) {
                    streamState.transportReportStatus(Status.INTERNAL.withDescription(String.format("Responded with RST_STREAM %s: %s", new Object[]{errorCode, str})));
                    OkHttpServerTransport.this.streamClosed(i, false);
                }
            }
        }

        private void respondWithHttpError(int i, boolean z, int i2, Status.Code code, String str) {
            Metadata metadata = new Metadata();
            metadata.put(InternalStatus.CODE_KEY, code.toStatus());
            metadata.put(InternalStatus.MESSAGE_KEY, str);
            List<io.grpc.okhttp.internal.framed.Header> createHttpResponseHeaders = Headers.createHttpResponseHeaders(i2, "text/plain; charset=utf-8", metadata);
            Buffer writeUtf8 = new Buffer().writeUtf8(str);
            synchronized (OkHttpServerTransport.this.lock) {
                Http2ErrorStreamState http2ErrorStreamState = new Http2ErrorStreamState(i, OkHttpServerTransport.this.lock, OkHttpServerTransport.this.outboundFlow, OkHttpServerTransport.this.config.flowControlWindow);
                if (OkHttpServerTransport.this.streams.isEmpty()) {
                    OkHttpServerTransport.this.keepAliveEnforcer.onTransportActive();
                    if (OkHttpServerTransport.this.maxConnectionIdleManager != null) {
                        OkHttpServerTransport.this.maxConnectionIdleManager.onTransportActive();
                    }
                }
                OkHttpServerTransport.this.streams.put(Integer.valueOf(i), http2ErrorStreamState);
                if (z) {
                    http2ErrorStreamState.inboundDataReceived(new Buffer(), 0, 0, true);
                }
                OkHttpServerTransport.this.frameWriter.headers(i, createHttpResponseHeaders);
                OkHttpServerTransport.this.outboundFlow.data(true, http2ErrorStreamState.getOutboundFlowState(), writeUtf8, true);
                OkHttpServerTransport.this.outboundFlow.notifyWhenNoPendingData(http2ErrorStreamState.getOutboundFlowState(), new OkHttpServerTransport$FrameHandler$$ExternalSyntheticLambda0(this, http2ErrorStreamState));
            }
        }

        /* access modifiers changed from: private */
        /* renamed from: rstOkAtEndOfHttpError */
        public void m2335lambda$respondWithHttpError$0$iogrpcokhttpOkHttpServerTransport$FrameHandler(Http2ErrorStreamState http2ErrorStreamState) {
            synchronized (OkHttpServerTransport.this.lock) {
                if (!http2ErrorStreamState.hasReceivedEndOfStream()) {
                    OkHttpServerTransport.this.frameWriter.rstStream(http2ErrorStreamState.streamId, ErrorCode.NO_ERROR);
                }
                OkHttpServerTransport.this.streamClosed(http2ErrorStreamState.streamId, true);
            }
        }

        private void respondWithGrpcError(int i, boolean z, Status.Code code, String str) {
            Metadata metadata = new Metadata();
            metadata.put(InternalStatus.CODE_KEY, code.toStatus());
            metadata.put(InternalStatus.MESSAGE_KEY, str);
            List<io.grpc.okhttp.internal.framed.Header> createResponseTrailers = Headers.createResponseTrailers(metadata, false);
            synchronized (OkHttpServerTransport.this.lock) {
                OkHttpServerTransport.this.frameWriter.synReply(true, i, createResponseTrailers);
                if (!z) {
                    OkHttpServerTransport.this.frameWriter.rstStream(i, ErrorCode.NO_ERROR);
                }
                OkHttpServerTransport.this.frameWriter.flush();
            }
        }
    }

    private final class KeepAlivePinger implements KeepAliveManager.KeepAlivePinger {
        private KeepAlivePinger() {
        }

        public void ping() {
            synchronized (OkHttpServerTransport.this.lock) {
                OkHttpServerTransport.this.frameWriter.ping(false, 0, OkHttpServerTransport.KEEPALIVE_PING);
                OkHttpServerTransport.this.frameWriter.flush();
            }
            OkHttpServerTransport.this.tracer.reportKeepAliveSent();
        }

        public void onPingTimeout() {
            synchronized (OkHttpServerTransport.this.lock) {
                Status unused = OkHttpServerTransport.this.goAwayStatus = Status.UNAVAILABLE.withDescription("Keepalive failed. Considering connection dead");
                GrpcUtil.closeQuietly((Closeable) OkHttpServerTransport.this.socket);
            }
        }
    }

    static class Http2ErrorStreamState implements StreamState, OutboundFlowController.Stream {
        private final Object lock;
        private final OutboundFlowController.StreamState outboundFlowState;
        private boolean receivedEndOfStream;
        /* access modifiers changed from: private */
        public final int streamId;
        private int window;

        public void inboundRstReceived(Status status) {
        }

        public void onSentBytes(int i) {
        }

        public void transportReportStatus(Status status) {
        }

        Http2ErrorStreamState(int i, Object obj, OutboundFlowController outboundFlowController, int i2) {
            this.streamId = i;
            this.lock = obj;
            this.outboundFlowState = outboundFlowController.createState(this, i);
            this.window = i2;
        }

        public void inboundDataReceived(Buffer buffer, int i, int i2, boolean z) {
            synchronized (this.lock) {
                if (z) {
                    this.receivedEndOfStream = true;
                }
                this.window -= i + i2;
                try {
                    buffer.skip(buffer.size());
                } catch (IOException e) {
                    throw new AssertionError(e);
                }
            }
        }

        public boolean hasReceivedEndOfStream() {
            boolean z;
            synchronized (this.lock) {
                z = this.receivedEndOfStream;
            }
            return z;
        }

        public int inboundWindowAvailable() {
            int i;
            synchronized (this.lock) {
                i = this.window;
            }
            return i;
        }

        public OutboundFlowController.StreamState getOutboundFlowState() {
            OutboundFlowController.StreamState streamState;
            synchronized (this.lock) {
                streamState = this.outboundFlowState;
            }
            return streamState;
        }
    }
}
