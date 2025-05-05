package io.grpc.okhttp;

import androidx.media3.muxer.MuxerUtil;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.net.HttpHeaders;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.ClientStreamTracer;
import io.grpc.Grpc;
import io.grpc.HttpConnectProxiedSocketAddress;
import io.grpc.InternalChannelz;
import io.grpc.InternalLogId;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.SecurityLevel;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.ConnectionClientTransport;
import io.grpc.internal.GrpcAttributes;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.Http2Ping;
import io.grpc.internal.InUseStateAggregator;
import io.grpc.internal.KeepAliveManager;
import io.grpc.internal.ManagedClientTransport;
import io.grpc.internal.SerializingExecutor;
import io.grpc.internal.StatsTraceContext;
import io.grpc.internal.TransportTracer;
import io.grpc.okhttp.ExceptionHandlingFrameWriter;
import io.grpc.okhttp.OkHttpChannelBuilder;
import io.grpc.okhttp.OkHttpClientStream;
import io.grpc.okhttp.OkHttpFrameLogger;
import io.grpc.okhttp.OutboundFlowController;
import io.grpc.okhttp.internal.ConnectionSpec;
import io.grpc.okhttp.internal.Credentials;
import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.FrameReader;
import io.grpc.okhttp.internal.framed.FrameWriter;
import io.grpc.okhttp.internal.framed.Header;
import io.grpc.okhttp.internal.framed.HeadersMode;
import io.grpc.okhttp.internal.framed.Http2;
import io.grpc.okhttp.internal.framed.Settings;
import io.grpc.okhttp.internal.framed.Variant;
import io.grpc.okhttp.internal.proxy.HttpUrl;
import io.grpc.okhttp.internal.proxy.Request;
import io.perfmark.PerfMark;
import io.sentry.SentryLockReason;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URI;
import java.util.Collections;
import java.util.Deque;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;

class OkHttpClientTransport implements ConnectionClientTransport, ExceptionHandlingFrameWriter.TransportExceptionHandler, OutboundFlowController.Transport {
    private static final Map<ErrorCode, Status> ERROR_CODE_TO_STATUS = buildErrorCodeToStatusMap();
    /* access modifiers changed from: private */
    public static final Logger log = Logger.getLogger(OkHttpClientTransport.class.getName());
    /* access modifiers changed from: private */
    public final InetSocketAddress address;
    /* access modifiers changed from: private */
    public Attributes attributes;
    /* access modifiers changed from: private */
    public ClientFrameHandler clientFrameHandler;
    SettableFuture<Void> connectedFuture;
    Runnable connectingCallback;
    /* access modifiers changed from: private */
    public final ConnectionSpec connectionSpec;
    /* access modifiers changed from: private */
    public int connectionUnacknowledgedBytesRead;
    private final String defaultAuthority;
    private boolean enableKeepAlive;
    /* access modifiers changed from: private */
    public final Executor executor;
    /* access modifiers changed from: private */
    public ExceptionHandlingFrameWriter frameWriter;
    private boolean goAwaySent;
    /* access modifiers changed from: private */
    public Status goAwayStatus;
    private boolean hasStream;
    /* access modifiers changed from: private */
    public HostnameVerifier hostnameVerifier;
    private final InUseStateAggregator<OkHttpClientStream> inUseState;
    /* access modifiers changed from: private */
    public final int initialWindowSize;
    /* access modifiers changed from: private */
    public KeepAliveManager keepAliveManager;
    private long keepAliveTimeNanos;
    private long keepAliveTimeoutNanos;
    private boolean keepAliveWithoutCalls;
    /* access modifiers changed from: private */
    public ManagedClientTransport.Listener listener;
    /* access modifiers changed from: private */
    public final Object lock;
    private final InternalLogId logId;
    /* access modifiers changed from: private */
    public int maxConcurrentStreams;
    /* access modifiers changed from: private */
    public final int maxInboundMetadataSize;
    private final int maxMessageSize;
    private int nextStreamId;
    /* access modifiers changed from: private */
    public OutboundFlowController outboundFlow;
    private final Deque<OkHttpClientStream> pendingStreams;
    /* access modifiers changed from: private */
    public Http2Ping ping;
    @Nullable
    final HttpConnectProxiedSocketAddress proxiedAddr;
    int proxySocketTimeout;
    private final Random random;
    private final ScheduledExecutorService scheduler;
    /* access modifiers changed from: private */
    public InternalChannelz.Security securityInfo;
    private final SerializingExecutor serializingExecutor;
    /* access modifiers changed from: private */
    public Socket socket;
    /* access modifiers changed from: private */
    public final SocketFactory socketFactory;
    /* access modifiers changed from: private */
    public SSLSocketFactory sslSocketFactory;
    private boolean stopped;
    private final Supplier<Stopwatch> stopwatchFactory;
    /* access modifiers changed from: private */
    public final Map<Integer, OkHttpClientStream> streams;
    /* access modifiers changed from: private */
    public final Runnable tooManyPingsRunnable;
    private final TransportTracer transportTracer;
    private final boolean useGetForSafeMethods;
    private final String userAgent;
    /* access modifiers changed from: private */
    public final Variant variant;

    static /* synthetic */ int access$2412(OkHttpClientTransport okHttpClientTransport, int i) {
        int i2 = okHttpClientTransport.connectionUnacknowledgedBytesRead + i;
        okHttpClientTransport.connectionUnacknowledgedBytesRead = i2;
        return i2;
    }

    private static Map<ErrorCode, Status> buildErrorCodeToStatusMap() {
        EnumMap enumMap = new EnumMap(ErrorCode.class);
        enumMap.put(ErrorCode.NO_ERROR, Status.INTERNAL.withDescription("No error: A GRPC status of OK should have been sent"));
        enumMap.put(ErrorCode.PROTOCOL_ERROR, Status.INTERNAL.withDescription("Protocol error"));
        enumMap.put(ErrorCode.INTERNAL_ERROR, Status.INTERNAL.withDescription("Internal error"));
        enumMap.put(ErrorCode.FLOW_CONTROL_ERROR, Status.INTERNAL.withDescription("Flow control error"));
        enumMap.put(ErrorCode.STREAM_CLOSED, Status.INTERNAL.withDescription("Stream closed"));
        enumMap.put(ErrorCode.FRAME_TOO_LARGE, Status.INTERNAL.withDescription("Frame too large"));
        enumMap.put(ErrorCode.REFUSED_STREAM, Status.UNAVAILABLE.withDescription("Refused stream"));
        enumMap.put(ErrorCode.CANCEL, Status.CANCELLED.withDescription("Cancelled"));
        enumMap.put(ErrorCode.COMPRESSION_ERROR, Status.INTERNAL.withDescription("Compression error"));
        enumMap.put(ErrorCode.CONNECT_ERROR, Status.INTERNAL.withDescription("Connect error"));
        enumMap.put(ErrorCode.ENHANCE_YOUR_CALM, Status.RESOURCE_EXHAUSTED.withDescription("Enhance your calm"));
        enumMap.put(ErrorCode.INADEQUATE_SECURITY, Status.PERMISSION_DENIED.withDescription("Inadequate security"));
        return Collections.unmodifiableMap(enumMap);
    }

    public OkHttpClientTransport(OkHttpChannelBuilder.OkHttpTransportFactory okHttpTransportFactory, InetSocketAddress inetSocketAddress, String str, @Nullable String str2, Attributes attributes2, @Nullable HttpConnectProxiedSocketAddress httpConnectProxiedSocketAddress, Runnable runnable) {
        this(okHttpTransportFactory, inetSocketAddress, str, str2, attributes2, GrpcUtil.STOPWATCH_SUPPLIER, new Http2(), httpConnectProxiedSocketAddress, runnable);
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [java.lang.Object, com.google.common.base.Supplier<com.google.common.base.Stopwatch>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private OkHttpClientTransport(io.grpc.okhttp.OkHttpChannelBuilder.OkHttpTransportFactory r2, java.net.InetSocketAddress r3, java.lang.String r4, @javax.annotation.Nullable java.lang.String r5, io.grpc.Attributes r6, com.google.common.base.Supplier<com.google.common.base.Stopwatch> r7, io.grpc.okhttp.internal.framed.Variant r8, @javax.annotation.Nullable io.grpc.HttpConnectProxiedSocketAddress r9, java.lang.Runnable r10) {
        /*
            r1 = this;
            r1.<init>()
            java.util.Random r0 = new java.util.Random
            r0.<init>()
            r1.random = r0
            java.lang.Object r0 = new java.lang.Object
            r0.<init>()
            r1.lock = r0
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r1.streams = r0
            r0 = 0
            r1.maxConcurrentStreams = r0
            java.util.LinkedList r0 = new java.util.LinkedList
            r0.<init>()
            r1.pendingStreams = r0
            io.grpc.okhttp.OkHttpClientTransport$1 r0 = new io.grpc.okhttp.OkHttpClientTransport$1
            r0.<init>()
            r1.inUseState = r0
            r0 = 30000(0x7530, float:4.2039E-41)
            r1.proxySocketTimeout = r0
            java.lang.String r0 = "address"
            java.lang.Object r0 = com.google.common.base.Preconditions.checkNotNull(r3, r0)
            java.net.InetSocketAddress r0 = (java.net.InetSocketAddress) r0
            r1.address = r0
            r1.defaultAuthority = r4
            int r4 = r2.maxMessageSize
            r1.maxMessageSize = r4
            int r4 = r2.flowControlWindow
            r1.initialWindowSize = r4
            java.util.concurrent.Executor r4 = r2.executor
            java.lang.String r0 = "executor"
            java.lang.Object r4 = com.google.common.base.Preconditions.checkNotNull(r4, r0)
            java.util.concurrent.Executor r4 = (java.util.concurrent.Executor) r4
            r1.executor = r4
            io.grpc.internal.SerializingExecutor r4 = new io.grpc.internal.SerializingExecutor
            java.util.concurrent.Executor r0 = r2.executor
            r4.<init>(r0)
            r1.serializingExecutor = r4
            java.util.concurrent.ScheduledExecutorService r4 = r2.scheduledExecutorService
            java.lang.String r0 = "scheduledExecutorService"
            java.lang.Object r4 = com.google.common.base.Preconditions.checkNotNull(r4, r0)
            java.util.concurrent.ScheduledExecutorService r4 = (java.util.concurrent.ScheduledExecutorService) r4
            r1.scheduler = r4
            r4 = 3
            r1.nextStreamId = r4
            javax.net.SocketFactory r4 = r2.socketFactory
            if (r4 != 0) goto L_0x006e
            javax.net.SocketFactory r4 = javax.net.SocketFactory.getDefault()
            goto L_0x0070
        L_0x006e:
            javax.net.SocketFactory r4 = r2.socketFactory
        L_0x0070:
            r1.socketFactory = r4
            javax.net.ssl.SSLSocketFactory r4 = r2.sslSocketFactory
            r1.sslSocketFactory = r4
            javax.net.ssl.HostnameVerifier r4 = r2.hostnameVerifier
            r1.hostnameVerifier = r4
            io.grpc.okhttp.internal.ConnectionSpec r4 = r2.connectionSpec
            java.lang.String r0 = "connectionSpec"
            java.lang.Object r4 = com.google.common.base.Preconditions.checkNotNull(r4, r0)
            io.grpc.okhttp.internal.ConnectionSpec r4 = (io.grpc.okhttp.internal.ConnectionSpec) r4
            r1.connectionSpec = r4
            java.lang.String r4 = "stopwatchFactory"
            java.lang.Object r4 = com.google.common.base.Preconditions.checkNotNull(r7, r4)
            com.google.common.base.Supplier r4 = (com.google.common.base.Supplier) r4
            r1.stopwatchFactory = r4
            java.lang.String r4 = "variant"
            java.lang.Object r4 = com.google.common.base.Preconditions.checkNotNull(r8, r4)
            io.grpc.okhttp.internal.framed.Variant r4 = (io.grpc.okhttp.internal.framed.Variant) r4
            r1.variant = r4
            java.lang.String r4 = "okhttp"
            java.lang.String r4 = io.grpc.internal.GrpcUtil.getGrpcUserAgent(r4, r5)
            r1.userAgent = r4
            r1.proxiedAddr = r9
            java.lang.String r4 = "tooManyPingsRunnable"
            java.lang.Object r4 = com.google.common.base.Preconditions.checkNotNull(r10, r4)
            java.lang.Runnable r4 = (java.lang.Runnable) r4
            r1.tooManyPingsRunnable = r4
            int r4 = r2.maxInboundMetadataSize
            r1.maxInboundMetadataSize = r4
            io.grpc.internal.TransportTracer$Factory r4 = r2.transportTracerFactory
            io.grpc.internal.TransportTracer r4 = r4.create()
            r1.transportTracer = r4
            java.lang.Class r4 = r1.getClass()
            java.lang.String r3 = r3.toString()
            io.grpc.InternalLogId r3 = io.grpc.InternalLogId.allocate((java.lang.Class<?>) r4, (java.lang.String) r3)
            r1.logId = r3
            io.grpc.Attributes$Builder r3 = io.grpc.Attributes.newBuilder()
            io.grpc.Attributes$Key<io.grpc.Attributes> r4 = io.grpc.internal.GrpcAttributes.ATTR_CLIENT_EAG_ATTRS
            io.grpc.Attributes$Builder r3 = r3.set(r4, r6)
            io.grpc.Attributes r3 = r3.build()
            r1.attributes = r3
            boolean r2 = r2.useGetForSafeMethods
            r1.useGetForSafeMethods = r2
            r1.initTransportTracer()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.okhttp.OkHttpClientTransport.<init>(io.grpc.okhttp.OkHttpChannelBuilder$OkHttpTransportFactory, java.net.InetSocketAddress, java.lang.String, java.lang.String, io.grpc.Attributes, com.google.common.base.Supplier, io.grpc.okhttp.internal.framed.Variant, io.grpc.HttpConnectProxiedSocketAddress, java.lang.Runnable):void");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    OkHttpClientTransport(OkHttpChannelBuilder.OkHttpTransportFactory okHttpTransportFactory, String str, Supplier<Stopwatch> supplier, Variant variant2, @Nullable Runnable runnable, SettableFuture<Void> settableFuture, Runnable runnable2) {
        this(okHttpTransportFactory, new InetSocketAddress("127.0.0.1", 80), "notarealauthority:80", str, Attributes.EMPTY, supplier, variant2, (HttpConnectProxiedSocketAddress) null, runnable2);
        this.connectingCallback = runnable;
        this.connectedFuture = (SettableFuture) Preconditions.checkNotNull(settableFuture, "connectedFuture");
    }

    /* access modifiers changed from: package-private */
    public boolean isUsingPlaintext() {
        return this.sslSocketFactory == null;
    }

    private void initTransportTracer() {
        synchronized (this.lock) {
            this.transportTracer.setFlowControlWindowReader(new TransportTracer.FlowControlReader() {
                public TransportTracer.FlowControlWindows read() {
                    TransportTracer.FlowControlWindows flowControlWindows;
                    synchronized (OkHttpClientTransport.this.lock) {
                        flowControlWindows = new TransportTracer.FlowControlWindows(OkHttpClientTransport.this.outboundFlow == null ? -1 : (long) OkHttpClientTransport.this.outboundFlow.windowUpdate((OutboundFlowController.StreamState) null, 0), (long) (((float) OkHttpClientTransport.this.initialWindowSize) * 0.5f));
                    }
                    return flowControlWindows;
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void enableKeepAlive(boolean z, long j, long j2, boolean z2) {
        this.enableKeepAlive = z;
        this.keepAliveTimeNanos = j;
        this.keepAliveTimeoutNanos = j2;
        this.keepAliveWithoutCalls = z2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0050, code lost:
        r1.addCallback(r9, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0053, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void ping(io.grpc.internal.ClientTransport.PingCallback r9, java.util.concurrent.Executor r10) {
        /*
            r8 = this;
            java.lang.Object r0 = r8.lock
            monitor-enter(r0)
            io.grpc.okhttp.ExceptionHandlingFrameWriter r1 = r8.frameWriter     // Catch:{ all -> 0x0054 }
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x000b
            r1 = r2
            goto L_0x000c
        L_0x000b:
            r1 = r3
        L_0x000c:
            com.google.common.base.Preconditions.checkState(r1)     // Catch:{ all -> 0x0054 }
            boolean r1 = r8.stopped     // Catch:{ all -> 0x0054 }
            if (r1 == 0) goto L_0x001c
            java.lang.Throwable r1 = r8.getPingFailure()     // Catch:{ all -> 0x0054 }
            io.grpc.internal.Http2Ping.notifyFailed(r9, r10, r1)     // Catch:{ all -> 0x0054 }
            monitor-exit(r0)     // Catch:{ all -> 0x0054 }
            return
        L_0x001c:
            io.grpc.internal.Http2Ping r1 = r8.ping     // Catch:{ all -> 0x0054 }
            if (r1 == 0) goto L_0x0024
            r4 = 0
            r2 = r3
            goto L_0x0042
        L_0x0024:
            java.util.Random r1 = r8.random     // Catch:{ all -> 0x0054 }
            long r4 = r1.nextLong()     // Catch:{ all -> 0x0054 }
            com.google.common.base.Supplier<com.google.common.base.Stopwatch> r1 = r8.stopwatchFactory     // Catch:{ all -> 0x0054 }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x0054 }
            com.google.common.base.Stopwatch r1 = (com.google.common.base.Stopwatch) r1     // Catch:{ all -> 0x0054 }
            r1.start()     // Catch:{ all -> 0x0054 }
            io.grpc.internal.Http2Ping r6 = new io.grpc.internal.Http2Ping     // Catch:{ all -> 0x0054 }
            r6.<init>(r4, r1)     // Catch:{ all -> 0x0054 }
            r8.ping = r6     // Catch:{ all -> 0x0054 }
            io.grpc.internal.TransportTracer r1 = r8.transportTracer     // Catch:{ all -> 0x0054 }
            r1.reportKeepAliveSent()     // Catch:{ all -> 0x0054 }
            r1 = r6
        L_0x0042:
            if (r2 == 0) goto L_0x004f
            io.grpc.okhttp.ExceptionHandlingFrameWriter r2 = r8.frameWriter     // Catch:{ all -> 0x0054 }
            r6 = 32
            long r6 = r4 >>> r6
            int r6 = (int) r6     // Catch:{ all -> 0x0054 }
            int r4 = (int) r4     // Catch:{ all -> 0x0054 }
            r2.ping(r3, r6, r4)     // Catch:{ all -> 0x0054 }
        L_0x004f:
            monitor-exit(r0)     // Catch:{ all -> 0x0054 }
            r1.addCallback(r9, r10)
            return
        L_0x0054:
            r9 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0054 }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.okhttp.OkHttpClientTransport.ping(io.grpc.internal.ClientTransport$PingCallback, java.util.concurrent.Executor):void");
    }

    public OkHttpClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions, ClientStreamTracer[] clientStreamTracerArr) {
        Object obj;
        Metadata metadata2 = metadata;
        Preconditions.checkNotNull(methodDescriptor, "method");
        Preconditions.checkNotNull(metadata2, "headers");
        StatsTraceContext newClientContext = StatsTraceContext.newClientContext(clientStreamTracerArr, getAttributes(), metadata2);
        Object obj2 = this.lock;
        synchronized (obj2) {
            try {
                obj = obj2;
                OkHttpClientStream okHttpClientStream = new OkHttpClientStream(methodDescriptor, metadata, this.frameWriter, this, this.outboundFlow, this.lock, this.maxMessageSize, this.initialWindowSize, this.defaultAuthority, this.userAgent, newClientContext, this.transportTracer, callOptions, this.useGetForSafeMethods);
                return okHttpClientStream;
            } catch (Throwable th) {
                th = th;
                throw th;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void streamReadyToStart(OkHttpClientStream okHttpClientStream) {
        if (this.goAwayStatus != null) {
            okHttpClientStream.transportState().transportReportStatus(this.goAwayStatus, ClientStreamListener.RpcProgress.MISCARRIED, true, new Metadata());
        } else if (this.streams.size() >= this.maxConcurrentStreams) {
            this.pendingStreams.add(okHttpClientStream);
            setInUse(okHttpClientStream);
        } else {
            startStream(okHttpClientStream);
        }
    }

    private void startStream(OkHttpClientStream okHttpClientStream) {
        Preconditions.checkState(okHttpClientStream.transportState().id() == -1, "StreamId already assigned");
        this.streams.put(Integer.valueOf(this.nextStreamId), okHttpClientStream);
        setInUse(okHttpClientStream);
        okHttpClientStream.transportState().start(this.nextStreamId);
        if (!(okHttpClientStream.getType() == MethodDescriptor.MethodType.UNARY || okHttpClientStream.getType() == MethodDescriptor.MethodType.SERVER_STREAMING) || okHttpClientStream.useGet()) {
            this.frameWriter.flush();
        }
        int i = this.nextStreamId;
        if (i >= 2147483645) {
            this.nextStreamId = Integer.MAX_VALUE;
            startGoAway(Integer.MAX_VALUE, ErrorCode.NO_ERROR, Status.UNAVAILABLE.withDescription("Stream ids exhausted"));
            return;
        }
        this.nextStreamId = i + 2;
    }

    /* access modifiers changed from: private */
    public boolean startPendingStreams() {
        boolean z = false;
        while (!this.pendingStreams.isEmpty() && this.streams.size() < this.maxConcurrentStreams) {
            startStream(this.pendingStreams.poll());
            z = true;
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public void removePendingStream(OkHttpClientStream okHttpClientStream) {
        this.pendingStreams.remove(okHttpClientStream);
        maybeClearInUse(okHttpClientStream);
    }

    /* JADX INFO: finally extract failed */
    public Runnable start(ManagedClientTransport.Listener listener2) {
        this.listener = (ManagedClientTransport.Listener) Preconditions.checkNotNull(listener2, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        if (this.enableKeepAlive) {
            KeepAliveManager keepAliveManager2 = new KeepAliveManager(new KeepAliveManager.ClientKeepAlivePinger(this), this.scheduler, this.keepAliveTimeNanos, this.keepAliveTimeoutNanos, this.keepAliveWithoutCalls);
            this.keepAliveManager = keepAliveManager2;
            keepAliveManager2.onTransportStarted();
        }
        final AsyncSink sink = AsyncSink.sink(this.serializingExecutor, this, 10000);
        FrameWriter limitControlFramesWriter = sink.limitControlFramesWriter(this.variant.newWriter(Okio.buffer((Sink) sink), true));
        synchronized (this.lock) {
            this.frameWriter = new ExceptionHandlingFrameWriter(this, limitControlFramesWriter);
            this.outboundFlow = new OutboundFlowController(this, this.frameWriter);
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.serializingExecutor.execute(new Runnable() {
            public void run() {
                OkHttpClientTransport okHttpClientTransport;
                ClientFrameHandler clientFrameHandler;
                Socket access$600;
                SSLSocket sSLSocket;
                SSLSession sSLSession;
                try {
                    countDownLatch.await();
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
                BufferedSource buffer = Okio.buffer((Source) new Source() {
                    public void close() {
                    }

                    public long read(Buffer buffer, long j) {
                        return -1;
                    }

                    public Timeout timeout() {
                        return Timeout.NONE;
                    }
                });
                try {
                    if (OkHttpClientTransport.this.proxiedAddr == null) {
                        access$600 = OkHttpClientTransport.this.socketFactory.createSocket(OkHttpClientTransport.this.address.getAddress(), OkHttpClientTransport.this.address.getPort());
                    } else if (OkHttpClientTransport.this.proxiedAddr.getProxyAddress() instanceof InetSocketAddress) {
                        OkHttpClientTransport okHttpClientTransport2 = OkHttpClientTransport.this;
                        access$600 = okHttpClientTransport2.createHttpProxySocket(okHttpClientTransport2.proxiedAddr.getTargetAddress(), (InetSocketAddress) OkHttpClientTransport.this.proxiedAddr.getProxyAddress(), OkHttpClientTransport.this.proxiedAddr.getUsername(), OkHttpClientTransport.this.proxiedAddr.getPassword());
                    } else {
                        throw Status.INTERNAL.withDescription("Unsupported SocketAddress implementation " + OkHttpClientTransport.this.proxiedAddr.getProxyAddress().getClass()).asException();
                    }
                    Socket socket = access$600;
                    if (OkHttpClientTransport.this.sslSocketFactory != null) {
                        SSLSocket upgrade = OkHttpTlsUpgrader.upgrade(OkHttpClientTransport.this.sslSocketFactory, OkHttpClientTransport.this.hostnameVerifier, socket, OkHttpClientTransport.this.getOverridenHost(), OkHttpClientTransport.this.getOverridenPort(), OkHttpClientTransport.this.connectionSpec);
                        sSLSession = upgrade.getSession();
                        sSLSocket = upgrade;
                    } else {
                        sSLSession = null;
                        sSLSocket = socket;
                    }
                    sSLSocket.setTcpNoDelay(true);
                    BufferedSource buffer2 = Okio.buffer(Okio.source(sSLSocket));
                    sink.becomeConnected(Okio.sink(sSLSocket), sSLSocket);
                    OkHttpClientTransport okHttpClientTransport3 = OkHttpClientTransport.this;
                    Attributes unused2 = okHttpClientTransport3.attributes = okHttpClientTransport3.attributes.toBuilder().set(Grpc.TRANSPORT_ATTR_REMOTE_ADDR, sSLSocket.getRemoteSocketAddress()).set(Grpc.TRANSPORT_ATTR_LOCAL_ADDR, sSLSocket.getLocalSocketAddress()).set(Grpc.TRANSPORT_ATTR_SSL_SESSION, sSLSession).set(GrpcAttributes.ATTR_SECURITY_LEVEL, sSLSession == null ? SecurityLevel.NONE : SecurityLevel.PRIVACY_AND_INTEGRITY).build();
                    OkHttpClientTransport okHttpClientTransport4 = OkHttpClientTransport.this;
                    OkHttpClientTransport okHttpClientTransport5 = OkHttpClientTransport.this;
                    ClientFrameHandler unused3 = okHttpClientTransport4.clientFrameHandler = new ClientFrameHandler(okHttpClientTransport5.variant.newReader(buffer2, true));
                    synchronized (OkHttpClientTransport.this.lock) {
                        Socket unused4 = OkHttpClientTransport.this.socket = (Socket) Preconditions.checkNotNull(sSLSocket, "socket");
                        if (sSLSession != null) {
                            InternalChannelz.Security unused5 = OkHttpClientTransport.this.securityInfo = new InternalChannelz.Security(new InternalChannelz.Tls(sSLSession));
                        }
                    }
                    return;
                } catch (StatusException e) {
                    OkHttpClientTransport.this.startGoAway(0, ErrorCode.INTERNAL_ERROR, e.getStatus());
                    okHttpClientTransport = OkHttpClientTransport.this;
                    OkHttpClientTransport okHttpClientTransport6 = OkHttpClientTransport.this;
                    clientFrameHandler = new ClientFrameHandler(okHttpClientTransport6.variant.newReader(buffer, true));
                } catch (Exception e2) {
                    OkHttpClientTransport.this.onException(e2);
                    okHttpClientTransport = OkHttpClientTransport.this;
                    OkHttpClientTransport okHttpClientTransport7 = OkHttpClientTransport.this;
                    clientFrameHandler = new ClientFrameHandler(okHttpClientTransport7.variant.newReader(buffer, true));
                } catch (Throwable th) {
                    OkHttpClientTransport okHttpClientTransport8 = OkHttpClientTransport.this;
                    OkHttpClientTransport okHttpClientTransport9 = OkHttpClientTransport.this;
                    ClientFrameHandler unused6 = okHttpClientTransport8.clientFrameHandler = new ClientFrameHandler(okHttpClientTransport9.variant.newReader(buffer, true));
                    throw th;
                }
                ClientFrameHandler unused7 = okHttpClientTransport.clientFrameHandler = clientFrameHandler;
            }
        });
        try {
            sendConnectionPrefaceAndSettings();
            countDownLatch.countDown();
            this.serializingExecutor.execute(new Runnable() {
                public void run() {
                    if (OkHttpClientTransport.this.connectingCallback != null) {
                        OkHttpClientTransport.this.connectingCallback.run();
                    }
                    OkHttpClientTransport.this.executor.execute(OkHttpClientTransport.this.clientFrameHandler);
                    synchronized (OkHttpClientTransport.this.lock) {
                        int unused = OkHttpClientTransport.this.maxConcurrentStreams = Integer.MAX_VALUE;
                        boolean unused2 = OkHttpClientTransport.this.startPendingStreams();
                    }
                    if (OkHttpClientTransport.this.connectedFuture != null) {
                        OkHttpClientTransport.this.connectedFuture.set(null);
                    }
                }
            });
            return null;
        } catch (Throwable th) {
            countDownLatch.countDown();
            throw th;
        }
    }

    private void sendConnectionPrefaceAndSettings() {
        synchronized (this.lock) {
            this.frameWriter.connectionPreface();
            Settings settings = new Settings();
            OkHttpSettingsUtil.set(settings, 7, this.initialWindowSize);
            this.frameWriter.settings(settings);
            int i = this.initialWindowSize;
            if (i > 65535) {
                this.frameWriter.windowUpdate(0, (long) (i - 65535));
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:19|20|21|25|26|27|28|29) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x00ed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.net.Socket createHttpProxySocket(java.net.InetSocketAddress r10, java.net.InetSocketAddress r11, java.lang.String r12, java.lang.String r13) throws io.grpc.StatusException {
        /*
            r9 = this;
            java.lang.String r0 = "\r\n"
            r1 = 0
            java.net.InetAddress r2 = r11.getAddress()     // Catch:{ IOException -> 0x0115 }
            if (r2 == 0) goto L_0x0018
            javax.net.SocketFactory r2 = r9.socketFactory     // Catch:{ IOException -> 0x0115 }
            java.net.InetAddress r3 = r11.getAddress()     // Catch:{ IOException -> 0x0115 }
            int r11 = r11.getPort()     // Catch:{ IOException -> 0x0115 }
            java.net.Socket r11 = r2.createSocket(r3, r11)     // Catch:{ IOException -> 0x0115 }
            goto L_0x0026
        L_0x0018:
            javax.net.SocketFactory r2 = r9.socketFactory     // Catch:{ IOException -> 0x0115 }
            java.lang.String r3 = r11.getHostName()     // Catch:{ IOException -> 0x0115 }
            int r11 = r11.getPort()     // Catch:{ IOException -> 0x0115 }
            java.net.Socket r11 = r2.createSocket(r3, r11)     // Catch:{ IOException -> 0x0115 }
        L_0x0026:
            r1 = r11
            r11 = 1
            r1.setTcpNoDelay(r11)     // Catch:{ IOException -> 0x0115 }
            int r2 = r9.proxySocketTimeout     // Catch:{ IOException -> 0x0115 }
            r1.setSoTimeout(r2)     // Catch:{ IOException -> 0x0115 }
            okio.Source r2 = okio.Okio.source((java.net.Socket) r1)     // Catch:{ IOException -> 0x0115 }
            okio.Sink r3 = okio.Okio.sink((java.net.Socket) r1)     // Catch:{ IOException -> 0x0115 }
            okio.BufferedSink r3 = okio.Okio.buffer((okio.Sink) r3)     // Catch:{ IOException -> 0x0115 }
            io.grpc.okhttp.internal.proxy.Request r10 = r9.createHttpProxyRequest(r10, r12, r13)     // Catch:{ IOException -> 0x0115 }
            io.grpc.okhttp.internal.proxy.HttpUrl r12 = r10.httpUrl()     // Catch:{ IOException -> 0x0115 }
            java.util.Locale r13 = java.util.Locale.US     // Catch:{ IOException -> 0x0115 }
            java.lang.String r4 = "CONNECT %s:%d HTTP/1.1"
            r5 = 2
            java.lang.Object[] r6 = new java.lang.Object[r5]     // Catch:{ IOException -> 0x0115 }
            java.lang.String r7 = r12.host()     // Catch:{ IOException -> 0x0115 }
            r8 = 0
            r6[r8] = r7     // Catch:{ IOException -> 0x0115 }
            int r12 = r12.port()     // Catch:{ IOException -> 0x0115 }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch:{ IOException -> 0x0115 }
            r6[r11] = r12     // Catch:{ IOException -> 0x0115 }
            java.lang.String r12 = java.lang.String.format(r13, r4, r6)     // Catch:{ IOException -> 0x0115 }
            okio.BufferedSink r12 = r3.writeUtf8(r12)     // Catch:{ IOException -> 0x0115 }
            r12.writeUtf8(r0)     // Catch:{ IOException -> 0x0115 }
            io.grpc.okhttp.internal.Headers r12 = r10.headers()     // Catch:{ IOException -> 0x0115 }
            int r12 = r12.size()     // Catch:{ IOException -> 0x0115 }
            r13 = r8
        L_0x0070:
            if (r13 >= r12) goto L_0x0096
            io.grpc.okhttp.internal.Headers r4 = r10.headers()     // Catch:{ IOException -> 0x0115 }
            java.lang.String r4 = r4.name(r13)     // Catch:{ IOException -> 0x0115 }
            okio.BufferedSink r4 = r3.writeUtf8(r4)     // Catch:{ IOException -> 0x0115 }
            java.lang.String r6 = ": "
            okio.BufferedSink r4 = r4.writeUtf8(r6)     // Catch:{ IOException -> 0x0115 }
            io.grpc.okhttp.internal.Headers r6 = r10.headers()     // Catch:{ IOException -> 0x0115 }
            java.lang.String r6 = r6.value(r13)     // Catch:{ IOException -> 0x0115 }
            okio.BufferedSink r4 = r4.writeUtf8(r6)     // Catch:{ IOException -> 0x0115 }
            r4.writeUtf8(r0)     // Catch:{ IOException -> 0x0115 }
            int r13 = r13 + 1
            goto L_0x0070
        L_0x0096:
            r3.writeUtf8(r0)     // Catch:{ IOException -> 0x0115 }
            r3.flush()     // Catch:{ IOException -> 0x0115 }
            java.lang.String r10 = readUtf8LineStrictUnbuffered(r2)     // Catch:{ IOException -> 0x0115 }
            io.grpc.okhttp.internal.StatusLine r10 = io.grpc.okhttp.internal.StatusLine.parse(r10)     // Catch:{ IOException -> 0x0115 }
        L_0x00a4:
            java.lang.String r12 = readUtf8LineStrictUnbuffered(r2)     // Catch:{ IOException -> 0x0115 }
            java.lang.String r13 = ""
            boolean r12 = r12.equals(r13)     // Catch:{ IOException -> 0x0115 }
            if (r12 != 0) goto L_0x00b1
            goto L_0x00a4
        L_0x00b1:
            int r12 = r10.code     // Catch:{ IOException -> 0x0115 }
            r13 = 200(0xc8, float:2.8E-43)
            if (r12 < r13) goto L_0x00c1
            int r12 = r10.code     // Catch:{ IOException -> 0x0115 }
            r13 = 300(0x12c, float:4.2E-43)
            if (r12 >= r13) goto L_0x00c1
            r1.setSoTimeout(r8)     // Catch:{ IOException -> 0x0115 }
            return r1
        L_0x00c1:
            okio.Buffer r12 = new okio.Buffer     // Catch:{ IOException -> 0x0115 }
            r12.<init>()     // Catch:{ IOException -> 0x0115 }
            r1.shutdownOutput()     // Catch:{ IOException -> 0x00cf }
            r3 = 1024(0x400, double:5.06E-321)
            r2.read(r12, r3)     // Catch:{ IOException -> 0x00cf }
            goto L_0x00ea
        L_0x00cf:
            r13 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0115 }
            r0.<init>()     // Catch:{ IOException -> 0x0115 }
            java.lang.String r2 = "Unable to read body: "
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ IOException -> 0x0115 }
            java.lang.String r13 = r13.toString()     // Catch:{ IOException -> 0x0115 }
            java.lang.StringBuilder r13 = r0.append(r13)     // Catch:{ IOException -> 0x0115 }
            java.lang.String r13 = r13.toString()     // Catch:{ IOException -> 0x0115 }
            r12.writeUtf8((java.lang.String) r13)     // Catch:{ IOException -> 0x0115 }
        L_0x00ea:
            r1.close()     // Catch:{ IOException -> 0x00ed }
        L_0x00ed:
            java.util.Locale r13 = java.util.Locale.US     // Catch:{ IOException -> 0x0115 }
            java.lang.String r0 = "Response returned from proxy was not successful (expected 2xx, got %d %s). Response body:\n%s"
            r2 = 3
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ IOException -> 0x0115 }
            int r3 = r10.code     // Catch:{ IOException -> 0x0115 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ IOException -> 0x0115 }
            r2[r8] = r3     // Catch:{ IOException -> 0x0115 }
            java.lang.String r10 = r10.message     // Catch:{ IOException -> 0x0115 }
            r2[r11] = r10     // Catch:{ IOException -> 0x0115 }
            java.lang.String r10 = r12.readUtf8()     // Catch:{ IOException -> 0x0115 }
            r2[r5] = r10     // Catch:{ IOException -> 0x0115 }
            java.lang.String r10 = java.lang.String.format(r13, r0, r2)     // Catch:{ IOException -> 0x0115 }
            io.grpc.Status r11 = io.grpc.Status.UNAVAILABLE     // Catch:{ IOException -> 0x0115 }
            io.grpc.Status r10 = r11.withDescription(r10)     // Catch:{ IOException -> 0x0115 }
            io.grpc.StatusException r10 = r10.asException()     // Catch:{ IOException -> 0x0115 }
            throw r10     // Catch:{ IOException -> 0x0115 }
        L_0x0115:
            r10 = move-exception
            if (r1 == 0) goto L_0x011b
            io.grpc.internal.GrpcUtil.closeQuietly((java.io.Closeable) r1)
        L_0x011b:
            io.grpc.Status r11 = io.grpc.Status.UNAVAILABLE
            java.lang.String r12 = "Failed trying to connect with proxy"
            io.grpc.Status r11 = r11.withDescription(r12)
            io.grpc.Status r10 = r11.withCause(r10)
            io.grpc.StatusException r10 = r10.asException()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.okhttp.OkHttpClientTransport.createHttpProxySocket(java.net.InetSocketAddress, java.net.InetSocketAddress, java.lang.String, java.lang.String):java.net.Socket");
    }

    private Request createHttpProxyRequest(InetSocketAddress inetSocketAddress, String str, String str2) {
        HttpUrl build = new HttpUrl.Builder().scheme("https").host(inetSocketAddress.getHostName()).port(inetSocketAddress.getPort()).build();
        Request.Builder header = new Request.Builder().url(build).header(HttpHeaders.HOST, build.host() + ":" + build.port()).header(HttpHeaders.USER_AGENT, this.userAgent);
        if (!(str == null || str2 == null)) {
            header.header(HttpHeaders.PROXY_AUTHORIZATION, Credentials.basic(str, str2));
        }
        return header.build();
    }

    private static String readUtf8LineStrictUnbuffered(Source source) throws IOException {
        Buffer buffer = new Buffer();
        while (source.read(buffer, 1) != -1) {
            if (buffer.getByte(buffer.size() - 1) == 10) {
                return buffer.readUtf8LineStrict();
            }
        }
        throw new EOFException("\\n not found: " + buffer.readByteString().hex());
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("logId", this.logId.getId()).add(SentryLockReason.JsonKeys.ADDRESS, (Object) this.address).toString();
    }

    public InternalLogId getLogId() {
        return this.logId;
    }

    /* access modifiers changed from: package-private */
    public String getOverridenHost() {
        URI authorityToUri = GrpcUtil.authorityToUri(this.defaultAuthority);
        if (authorityToUri.getHost() != null) {
            return authorityToUri.getHost();
        }
        return this.defaultAuthority;
    }

    /* access modifiers changed from: package-private */
    public int getOverridenPort() {
        URI authorityToUri = GrpcUtil.authorityToUri(this.defaultAuthority);
        if (authorityToUri.getPort() != -1) {
            return authorityToUri.getPort();
        }
        return this.address.getPort();
    }

    public void shutdown(Status status) {
        synchronized (this.lock) {
            if (this.goAwayStatus == null) {
                this.goAwayStatus = status;
                this.listener.transportShutdown(status);
                stopIfNecessary();
            }
        }
    }

    public void shutdownNow(Status status) {
        shutdown(status);
        synchronized (this.lock) {
            Iterator<Map.Entry<Integer, OkHttpClientStream>> it = this.streams.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry next = it.next();
                it.remove();
                ((OkHttpClientStream) next.getValue()).transportState().transportReportStatus(status, false, new Metadata());
                maybeClearInUse((OkHttpClientStream) next.getValue());
            }
            for (OkHttpClientStream next2 : this.pendingStreams) {
                next2.transportState().transportReportStatus(status, ClientStreamListener.RpcProgress.MISCARRIED, true, new Metadata());
                maybeClearInUse(next2);
            }
            this.pendingStreams.clear();
            stopIfNecessary();
        }
    }

    public Attributes getAttributes() {
        return this.attributes;
    }

    public OutboundFlowController.StreamState[] getActiveStreams() {
        OutboundFlowController.StreamState[] streamStateArr;
        synchronized (this.lock) {
            streamStateArr = new OutboundFlowController.StreamState[this.streams.size()];
            int i = 0;
            for (OkHttpClientStream transportState : this.streams.values()) {
                streamStateArr[i] = transportState.transportState().getOutboundFlowState();
                i++;
            }
        }
        return streamStateArr;
    }

    /* access modifiers changed from: package-private */
    public ClientFrameHandler getHandler() {
        return this.clientFrameHandler;
    }

    /* access modifiers changed from: package-private */
    public SocketFactory getSocketFactory() {
        return this.socketFactory;
    }

    /* access modifiers changed from: package-private */
    public int getPendingStreamSize() {
        int size;
        synchronized (this.lock) {
            size = this.pendingStreams.size();
        }
        return size;
    }

    /* access modifiers changed from: package-private */
    public void setNextStreamId(int i) {
        synchronized (this.lock) {
            this.nextStreamId = i;
        }
    }

    public void onException(Throwable th) {
        Preconditions.checkNotNull(th, "failureCause");
        startGoAway(0, ErrorCode.INTERNAL_ERROR, Status.UNAVAILABLE.withCause(th));
    }

    /* access modifiers changed from: private */
    public void onError(ErrorCode errorCode, String str) {
        startGoAway(0, errorCode, toGrpcStatus(errorCode).augmentDescription(str));
    }

    /* access modifiers changed from: private */
    public void startGoAway(int i, ErrorCode errorCode, Status status) {
        synchronized (this.lock) {
            if (this.goAwayStatus == null) {
                this.goAwayStatus = status;
                this.listener.transportShutdown(status);
            }
            if (errorCode != null && !this.goAwaySent) {
                this.goAwaySent = true;
                this.frameWriter.goAway(0, errorCode, new byte[0]);
            }
            Iterator<Map.Entry<Integer, OkHttpClientStream>> it = this.streams.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry next = it.next();
                if (((Integer) next.getKey()).intValue() > i) {
                    it.remove();
                    ((OkHttpClientStream) next.getValue()).transportState().transportReportStatus(status, ClientStreamListener.RpcProgress.REFUSED, false, new Metadata());
                    maybeClearInUse((OkHttpClientStream) next.getValue());
                }
            }
            for (OkHttpClientStream next2 : this.pendingStreams) {
                next2.transportState().transportReportStatus(status, ClientStreamListener.RpcProgress.MISCARRIED, true, new Metadata());
                maybeClearInUse(next2);
            }
            this.pendingStreams.clear();
            stopIfNecessary();
        }
    }

    /* access modifiers changed from: package-private */
    public void finishStream(int i, @Nullable Status status, ClientStreamListener.RpcProgress rpcProgress, boolean z, @Nullable ErrorCode errorCode, @Nullable Metadata metadata) {
        synchronized (this.lock) {
            OkHttpClientStream remove = this.streams.remove(Integer.valueOf(i));
            if (remove != null) {
                if (errorCode != null) {
                    this.frameWriter.rstStream(i, ErrorCode.CANCEL);
                }
                if (status != null) {
                    OkHttpClientStream.TransportState transportState = remove.transportState();
                    if (metadata == null) {
                        metadata = new Metadata();
                    }
                    transportState.transportReportStatus(status, rpcProgress, z, metadata);
                }
                if (!startPendingStreams()) {
                    stopIfNecessary();
                    maybeClearInUse(remove);
                }
            }
        }
    }

    private void stopIfNecessary() {
        if (this.goAwayStatus != null && this.streams.isEmpty() && this.pendingStreams.isEmpty() && !this.stopped) {
            this.stopped = true;
            KeepAliveManager keepAliveManager2 = this.keepAliveManager;
            if (keepAliveManager2 != null) {
                keepAliveManager2.onTransportTermination();
            }
            Http2Ping http2Ping = this.ping;
            if (http2Ping != null) {
                http2Ping.failed(getPingFailure());
                this.ping = null;
            }
            if (!this.goAwaySent) {
                this.goAwaySent = true;
                this.frameWriter.goAway(0, ErrorCode.NO_ERROR, new byte[0]);
            }
            this.frameWriter.close();
        }
    }

    private void maybeClearInUse(OkHttpClientStream okHttpClientStream) {
        if (this.hasStream && this.pendingStreams.isEmpty() && this.streams.isEmpty()) {
            this.hasStream = false;
            KeepAliveManager keepAliveManager2 = this.keepAliveManager;
            if (keepAliveManager2 != null) {
                keepAliveManager2.onTransportIdle();
            }
        }
        if (okHttpClientStream.shouldBeCountedForInUse()) {
            this.inUseState.updateObjectInUse(okHttpClientStream, false);
        }
    }

    private void setInUse(OkHttpClientStream okHttpClientStream) {
        if (!this.hasStream) {
            this.hasStream = true;
            KeepAliveManager keepAliveManager2 = this.keepAliveManager;
            if (keepAliveManager2 != null) {
                keepAliveManager2.onTransportActive();
            }
        }
        if (okHttpClientStream.shouldBeCountedForInUse()) {
            this.inUseState.updateObjectInUse(okHttpClientStream, true);
        }
    }

    private Throwable getPingFailure() {
        synchronized (this.lock) {
            Status status = this.goAwayStatus;
            if (status != null) {
                StatusException asException = status.asException();
                return asException;
            }
            StatusException asException2 = Status.UNAVAILABLE.withDescription("Connection closed").asException();
            return asException2;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean mayHaveCreatedStream(int i) {
        boolean z;
        synchronized (this.lock) {
            if (i < this.nextStreamId) {
                z = true;
                if ((i & 1) == 1) {
                }
            }
            z = false;
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public OkHttpClientStream getStream(int i) {
        OkHttpClientStream okHttpClientStream;
        synchronized (this.lock) {
            okHttpClientStream = this.streams.get(Integer.valueOf(i));
        }
        return okHttpClientStream;
    }

    static Status toGrpcStatus(ErrorCode errorCode) {
        Status status = ERROR_CODE_TO_STATUS.get(errorCode);
        return status != null ? status : Status.UNKNOWN.withDescription("Unknown http2 error code: " + errorCode.httpCode);
    }

    public ListenableFuture<InternalChannelz.SocketStats> getStats() {
        SettableFuture create = SettableFuture.create();
        synchronized (this.lock) {
            if (this.socket == null) {
                create.set(new InternalChannelz.SocketStats(this.transportTracer.getStats(), (SocketAddress) null, (SocketAddress) null, new InternalChannelz.SocketOptions.Builder().build(), (InternalChannelz.Security) null));
            } else {
                create.set(new InternalChannelz.SocketStats(this.transportTracer.getStats(), this.socket.getLocalSocketAddress(), this.socket.getRemoteSocketAddress(), Utils.getSocketOptions(this.socket), this.securityInfo));
            }
        }
        return create;
    }

    class ClientFrameHandler implements FrameReader.Handler, Runnable {
        boolean firstSettings = true;
        FrameReader frameReader;
        private final OkHttpFrameLogger logger = new OkHttpFrameLogger(Level.FINE, (Class<?>) OkHttpClientTransport.class);

        public void ackSettings() {
        }

        public void alternateService(int i, String str, ByteString byteString, String str2, int i2, long j) {
        }

        public void priority(int i, int i2, int i3, boolean z) {
        }

        ClientFrameHandler(FrameReader frameReader2) {
            this.frameReader = frameReader2;
        }

        public void run() {
            String str;
            Status access$2000;
            String name = Thread.currentThread().getName();
            Thread.currentThread().setName("OkHttpClientTransport");
            while (this.frameReader.nextFrame(this)) {
                try {
                    if (OkHttpClientTransport.this.keepAliveManager != null) {
                        OkHttpClientTransport.this.keepAliveManager.onDataReceived();
                    }
                } catch (Throwable th) {
                    try {
                        OkHttpClientTransport.this.startGoAway(0, ErrorCode.PROTOCOL_ERROR, Status.INTERNAL.withDescription("error in frame handler").withCause(th));
                        try {
                        } catch (IOException e) {
                        } catch (RuntimeException e2) {
                            if (!"bio == null".equals(e2.getMessage())) {
                                throw e2;
                            }
                        }
                    } finally {
                        try {
                            this.frameReader.close();
                        } catch (IOException e3) {
                            str = "Exception closing frame reader";
                            OkHttpClientTransport.log.log(Level.INFO, str, e3);
                        } catch (RuntimeException e4) {
                            if (!"bio == null".equals(e4.getMessage())) {
                                throw e4;
                            }
                        }
                        OkHttpClientTransport.this.listener.transportTerminated();
                        Thread.currentThread().setName(name);
                    }
                }
            }
            synchronized (OkHttpClientTransport.this.lock) {
                access$2000 = OkHttpClientTransport.this.goAwayStatus;
            }
            if (access$2000 == null) {
                access$2000 = Status.UNAVAILABLE.withDescription("End of stream or IOException");
            }
            OkHttpClientTransport.this.startGoAway(0, ErrorCode.INTERNAL_ERROR, access$2000);
            try {
                this.frameReader.close();
            } catch (IOException e5) {
                e = e5;
            } catch (RuntimeException e6) {
                if (!"bio == null".equals(e6.getMessage())) {
                    throw e6;
                }
            }
        }

        public void data(boolean z, int i, BufferedSource bufferedSource, int i2, int i3) throws IOException {
            this.logger.logData(OkHttpFrameLogger.Direction.INBOUND, i, bufferedSource.getBuffer(), i2, z);
            OkHttpClientStream stream = OkHttpClientTransport.this.getStream(i);
            if (stream != null) {
                long j = (long) i2;
                bufferedSource.require(j);
                Buffer buffer = new Buffer();
                buffer.write(bufferedSource.getBuffer(), j);
                PerfMark.event("OkHttpClientTransport$ClientFrameHandler.data", stream.transportState().tag());
                synchronized (OkHttpClientTransport.this.lock) {
                    stream.transportState().transportDataReceived(buffer, z, i3 - i2);
                }
            } else if (OkHttpClientTransport.this.mayHaveCreatedStream(i)) {
                synchronized (OkHttpClientTransport.this.lock) {
                    OkHttpClientTransport.this.frameWriter.rstStream(i, ErrorCode.STREAM_CLOSED);
                }
                bufferedSource.skip((long) i2);
            } else {
                OkHttpClientTransport.this.onError(ErrorCode.PROTOCOL_ERROR, "Received data for unknown stream: " + i);
                return;
            }
            OkHttpClientTransport.access$2412(OkHttpClientTransport.this, i3);
            if (((float) OkHttpClientTransport.this.connectionUnacknowledgedBytesRead) >= ((float) OkHttpClientTransport.this.initialWindowSize) * 0.5f) {
                synchronized (OkHttpClientTransport.this.lock) {
                    OkHttpClientTransport.this.frameWriter.windowUpdate(0, (long) OkHttpClientTransport.this.connectionUnacknowledgedBytesRead);
                }
                int unused = OkHttpClientTransport.this.connectionUnacknowledgedBytesRead = 0;
            }
        }

        public void headers(boolean z, boolean z2, int i, int i2, List<Header> list, HeadersMode headersMode) {
            Status status;
            int headerBlockSize;
            this.logger.logHeaders(OkHttpFrameLogger.Direction.INBOUND, i, list, z2);
            boolean z3 = true;
            if (OkHttpClientTransport.this.maxInboundMetadataSize == Integer.MAX_VALUE || (headerBlockSize = headerBlockSize(list)) <= OkHttpClientTransport.this.maxInboundMetadataSize) {
                status = null;
            } else {
                Status status2 = Status.RESOURCE_EXHAUSTED;
                Locale locale = Locale.US;
                Object[] objArr = new Object[3];
                objArr[0] = z2 ? "trailer" : "header";
                objArr[1] = Integer.valueOf(OkHttpClientTransport.this.maxInboundMetadataSize);
                objArr[2] = Integer.valueOf(headerBlockSize);
                status = status2.withDescription(String.format(locale, "Response %s metadata larger than %d: %d", objArr));
            }
            synchronized (OkHttpClientTransport.this.lock) {
                OkHttpClientStream okHttpClientStream = (OkHttpClientStream) OkHttpClientTransport.this.streams.get(Integer.valueOf(i));
                if (okHttpClientStream == null) {
                    if (OkHttpClientTransport.this.mayHaveCreatedStream(i)) {
                        OkHttpClientTransport.this.frameWriter.rstStream(i, ErrorCode.STREAM_CLOSED);
                    }
                } else if (status == null) {
                    PerfMark.event("OkHttpClientTransport$ClientFrameHandler.headers", okHttpClientStream.transportState().tag());
                    okHttpClientStream.transportState().transportHeadersReceived(list, z2);
                } else {
                    if (!z2) {
                        OkHttpClientTransport.this.frameWriter.rstStream(i, ErrorCode.CANCEL);
                    }
                    okHttpClientStream.transportState().transportReportStatus(status, false, new Metadata());
                }
                z3 = false;
            }
            if (z3) {
                OkHttpClientTransport.this.onError(ErrorCode.PROTOCOL_ERROR, "Received header for unknown stream: " + i);
            }
        }

        private int headerBlockSize(List<Header> list) {
            long j = 0;
            for (int i = 0; i < list.size(); i++) {
                Header header = list.get(i);
                j += (long) (header.name.size() + 32 + header.value.size());
            }
            return (int) Math.min(j, 2147483647L);
        }

        public void rstStream(int i, ErrorCode errorCode) {
            this.logger.logRstStream(OkHttpFrameLogger.Direction.INBOUND, i, errorCode);
            Status augmentDescription = OkHttpClientTransport.toGrpcStatus(errorCode).augmentDescription("Rst Stream");
            boolean z = augmentDescription.getCode() == Status.Code.CANCELLED || augmentDescription.getCode() == Status.Code.DEADLINE_EXCEEDED;
            synchronized (OkHttpClientTransport.this.lock) {
                OkHttpClientStream okHttpClientStream = (OkHttpClientStream) OkHttpClientTransport.this.streams.get(Integer.valueOf(i));
                if (okHttpClientStream != null) {
                    PerfMark.event("OkHttpClientTransport$ClientFrameHandler.rstStream", okHttpClientStream.transportState().tag());
                    OkHttpClientTransport.this.finishStream(i, augmentDescription, errorCode == ErrorCode.REFUSED_STREAM ? ClientStreamListener.RpcProgress.REFUSED : ClientStreamListener.RpcProgress.PROCESSED, z, (ErrorCode) null, (Metadata) null);
                }
            }
        }

        public void settings(boolean z, Settings settings) {
            boolean z2;
            this.logger.logSettings(OkHttpFrameLogger.Direction.INBOUND, settings);
            synchronized (OkHttpClientTransport.this.lock) {
                if (OkHttpSettingsUtil.isSet(settings, 4)) {
                    int unused = OkHttpClientTransport.this.maxConcurrentStreams = OkHttpSettingsUtil.get(settings, 4);
                }
                if (OkHttpSettingsUtil.isSet(settings, 7)) {
                    z2 = OkHttpClientTransport.this.outboundFlow.initialOutboundWindowSize(OkHttpSettingsUtil.get(settings, 7));
                } else {
                    z2 = false;
                }
                if (this.firstSettings) {
                    OkHttpClientTransport okHttpClientTransport = OkHttpClientTransport.this;
                    Attributes unused2 = okHttpClientTransport.attributes = okHttpClientTransport.listener.filterTransport(OkHttpClientTransport.this.attributes);
                    OkHttpClientTransport.this.listener.transportReady();
                    this.firstSettings = false;
                }
                OkHttpClientTransport.this.frameWriter.ackSettings(settings);
                if (z2) {
                    OkHttpClientTransport.this.outboundFlow.writeStreams();
                }
                boolean unused3 = OkHttpClientTransport.this.startPendingStreams();
            }
        }

        public void ping(boolean z, int i, int i2) {
            Http2Ping http2Ping;
            long j = (((long) i) << 32) | (((long) i2) & MuxerUtil.UNSIGNED_INT_MAX_VALUE);
            this.logger.logPing(OkHttpFrameLogger.Direction.INBOUND, j);
            if (!z) {
                synchronized (OkHttpClientTransport.this.lock) {
                    OkHttpClientTransport.this.frameWriter.ping(true, i, i2);
                }
                return;
            }
            synchronized (OkHttpClientTransport.this.lock) {
                http2Ping = null;
                if (OkHttpClientTransport.this.ping == null) {
                    OkHttpClientTransport.log.warning("Received unexpected ping ack. No ping outstanding");
                } else if (OkHttpClientTransport.this.ping.payload() == j) {
                    Http2Ping access$2700 = OkHttpClientTransport.this.ping;
                    Http2Ping unused = OkHttpClientTransport.this.ping = null;
                    http2Ping = access$2700;
                } else {
                    OkHttpClientTransport.log.log(Level.WARNING, String.format(Locale.US, "Received unexpected ping ack. Expecting %d, got %d", new Object[]{Long.valueOf(OkHttpClientTransport.this.ping.payload()), Long.valueOf(j)}));
                }
            }
            if (http2Ping != null) {
                http2Ping.complete();
            }
        }

        public void goAway(int i, ErrorCode errorCode, ByteString byteString) {
            this.logger.logGoAway(OkHttpFrameLogger.Direction.INBOUND, i, errorCode, byteString);
            if (errorCode == ErrorCode.ENHANCE_YOUR_CALM) {
                String utf8 = byteString.utf8();
                OkHttpClientTransport.log.log(Level.WARNING, String.format("%s: Received GOAWAY with ENHANCE_YOUR_CALM. Debug data: %s", new Object[]{this, utf8}));
                if ("too_many_pings".equals(utf8)) {
                    OkHttpClientTransport.this.tooManyPingsRunnable.run();
                }
            }
            Status augmentDescription = GrpcUtil.Http2Error.statusForCode((long) errorCode.httpCode).augmentDescription("Received Goaway");
            if (byteString.size() > 0) {
                augmentDescription = augmentDescription.augmentDescription(byteString.utf8());
            }
            OkHttpClientTransport.this.startGoAway(i, (ErrorCode) null, augmentDescription);
        }

        public void pushPromise(int i, int i2, List<Header> list) throws IOException {
            this.logger.logPushPromise(OkHttpFrameLogger.Direction.INBOUND, i, i2, list);
            synchronized (OkHttpClientTransport.this.lock) {
                OkHttpClientTransport.this.frameWriter.rstStream(i, ErrorCode.PROTOCOL_ERROR);
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0073, code lost:
            if (r9 == false) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0075, code lost:
            io.grpc.okhttp.OkHttpClientTransport.access$2300(r7.this$0, io.grpc.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR, "Received window_update for unknown stream: " + r8);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void windowUpdate(int r8, long r9) {
            /*
                r7 = this;
                io.grpc.okhttp.OkHttpFrameLogger r0 = r7.logger
                io.grpc.okhttp.OkHttpFrameLogger$Direction r1 = io.grpc.okhttp.OkHttpFrameLogger.Direction.INBOUND
                r0.logWindowsUpdate(r1, r8, r9)
                r0 = 0
                int r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
                if (r0 != 0) goto L_0x002c
                java.lang.String r9 = "Received 0 flow control window increment."
                if (r8 != 0) goto L_0x0019
                io.grpc.okhttp.OkHttpClientTransport r8 = io.grpc.okhttp.OkHttpClientTransport.this
                io.grpc.okhttp.internal.framed.ErrorCode r10 = io.grpc.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR
                r8.onError(r10, r9)
                goto L_0x002b
            L_0x0019:
                io.grpc.okhttp.OkHttpClientTransport r0 = io.grpc.okhttp.OkHttpClientTransport.this
                io.grpc.Status r10 = io.grpc.Status.INTERNAL
                io.grpc.Status r2 = r10.withDescription(r9)
                io.grpc.internal.ClientStreamListener$RpcProgress r3 = io.grpc.internal.ClientStreamListener.RpcProgress.PROCESSED
                r4 = 0
                io.grpc.okhttp.internal.framed.ErrorCode r5 = io.grpc.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR
                r6 = 0
                r1 = r8
                r0.finishStream(r1, r2, r3, r4, r5, r6)
            L_0x002b:
                return
            L_0x002c:
                io.grpc.okhttp.OkHttpClientTransport r0 = io.grpc.okhttp.OkHttpClientTransport.this
                java.lang.Object r0 = r0.lock
                monitor-enter(r0)
                if (r8 != 0) goto L_0x0042
                io.grpc.okhttp.OkHttpClientTransport r8 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ all -> 0x008c }
                io.grpc.okhttp.OutboundFlowController r8 = r8.outboundFlow     // Catch:{ all -> 0x008c }
                r1 = 0
                int r9 = (int) r9     // Catch:{ all -> 0x008c }
                r8.windowUpdate(r1, r9)     // Catch:{ all -> 0x008c }
                monitor-exit(r0)     // Catch:{ all -> 0x008c }
                return
            L_0x0042:
                io.grpc.okhttp.OkHttpClientTransport r1 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ all -> 0x008c }
                java.util.Map r1 = r1.streams     // Catch:{ all -> 0x008c }
                java.lang.Integer r2 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x008c }
                java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x008c }
                io.grpc.okhttp.OkHttpClientStream r1 = (io.grpc.okhttp.OkHttpClientStream) r1     // Catch:{ all -> 0x008c }
                if (r1 == 0) goto L_0x0067
                io.grpc.okhttp.OkHttpClientTransport r2 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ all -> 0x008c }
                io.grpc.okhttp.OutboundFlowController r2 = r2.outboundFlow     // Catch:{ all -> 0x008c }
                io.grpc.okhttp.OkHttpClientStream$TransportState r1 = r1.transportState()     // Catch:{ all -> 0x008c }
                io.grpc.okhttp.OutboundFlowController$StreamState r1 = r1.getOutboundFlowState()     // Catch:{ all -> 0x008c }
                int r9 = (int) r9     // Catch:{ all -> 0x008c }
                r2.windowUpdate(r1, r9)     // Catch:{ all -> 0x008c }
                goto L_0x0071
            L_0x0067:
                io.grpc.okhttp.OkHttpClientTransport r9 = io.grpc.okhttp.OkHttpClientTransport.this     // Catch:{ all -> 0x008c }
                boolean r9 = r9.mayHaveCreatedStream(r8)     // Catch:{ all -> 0x008c }
                if (r9 != 0) goto L_0x0071
                r9 = 1
                goto L_0x0072
            L_0x0071:
                r9 = 0
            L_0x0072:
                monitor-exit(r0)     // Catch:{ all -> 0x008c }
                if (r9 == 0) goto L_0x008b
                io.grpc.okhttp.OkHttpClientTransport r9 = io.grpc.okhttp.OkHttpClientTransport.this
                io.grpc.okhttp.internal.framed.ErrorCode r10 = io.grpc.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r1 = "Received window_update for unknown stream: "
                r0.<init>(r1)
                java.lang.StringBuilder r8 = r0.append(r8)
                java.lang.String r8 = r8.toString()
                r9.onError(r10, r8)
            L_0x008b:
                return
            L_0x008c:
                r8 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x008c }
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.okhttp.OkHttpClientTransport.ClientFrameHandler.windowUpdate(int, long):void");
        }
    }
}
