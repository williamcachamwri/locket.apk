package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.net.HttpHeaders;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import io.grpc.Attributes;
import io.grpc.BinaryLog;
import io.grpc.CompressorRegistry;
import io.grpc.Context;
import io.grpc.Contexts;
import io.grpc.Deadline;
import io.grpc.Decompressor;
import io.grpc.DecompressorRegistry;
import io.grpc.HandlerRegistry;
import io.grpc.InternalChannelz;
import io.grpc.InternalInstrumented;
import io.grpc.InternalLogId;
import io.grpc.InternalServer;
import io.grpc.InternalServerInterceptors;
import io.grpc.InternalStatus;
import io.grpc.Metadata;
import io.grpc.Server;
import io.grpc.ServerCall;
import io.grpc.ServerCallExecutorSupplier;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.ServerMethodDefinition;
import io.grpc.ServerServiceDefinition;
import io.grpc.ServerTransportFilter;
import io.grpc.Status;
import io.grpc.internal.StreamListener;
import io.perfmark.Link;
import io.perfmark.PerfMark;
import io.perfmark.Tag;
import io.perfmark.TaskCloseable;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ServerImpl extends Server implements InternalInstrumented<InternalChannelz.ServerStats> {
    /* access modifiers changed from: private */
    public static final ServerStreamListener NOOP_LISTENER = new NoopListener();
    /* access modifiers changed from: private */
    public static final Logger log = Logger.getLogger(ServerImpl.class.getName());
    /* access modifiers changed from: private */
    public final BinaryLog binlog;
    /* access modifiers changed from: private */
    public final InternalChannelz channelz;
    /* access modifiers changed from: private */
    public final CompressorRegistry compressorRegistry;
    /* access modifiers changed from: private */
    public final DecompressorRegistry decompressorRegistry;
    /* access modifiers changed from: private */
    public Executor executor;
    private final ObjectPool<? extends Executor> executorPool;
    /* access modifiers changed from: private */
    public final ServerCallExecutorSupplier executorSupplier;
    /* access modifiers changed from: private */
    public final HandlerRegistry fallbackRegistry;
    /* access modifiers changed from: private */
    public final long handshakeTimeoutMillis;
    /* access modifiers changed from: private */
    public final ServerInterceptor[] interceptors;
    /* access modifiers changed from: private */
    public final Object lock = new Object();
    private final InternalLogId logId;
    /* access modifiers changed from: private */
    public final HandlerRegistry registry;
    /* access modifiers changed from: private */
    public final Context rootContext;
    /* access modifiers changed from: private */
    public final CallTracer serverCallTracer;
    /* access modifiers changed from: private */
    public boolean serverShutdownCallbackInvoked;
    private boolean shutdown;
    /* access modifiers changed from: private */
    public Status shutdownNowStatus;
    private boolean started;
    private boolean terminated;
    /* access modifiers changed from: private */
    public final Deadline.Ticker ticker;
    /* access modifiers changed from: private */
    public final List<ServerTransportFilter> transportFilters;
    private final InternalServer transportServer;
    /* access modifiers changed from: private */
    public boolean transportServersTerminated;
    /* access modifiers changed from: private */
    public final Set<ServerTransport> transports = new HashSet();

    ServerImpl(ServerImplBuilder serverImplBuilder, InternalServer internalServer, Context context) {
        this.executorPool = (ObjectPool) Preconditions.checkNotNull(serverImplBuilder.executorPool, "executorPool");
        this.registry = (HandlerRegistry) Preconditions.checkNotNull(serverImplBuilder.registryBuilder.build(), "registryBuilder");
        this.fallbackRegistry = (HandlerRegistry) Preconditions.checkNotNull(serverImplBuilder.fallbackRegistry, "fallbackRegistry");
        this.transportServer = (InternalServer) Preconditions.checkNotNull(internalServer, "transportServer");
        this.logId = InternalLogId.allocate(HttpHeaders.SERVER, String.valueOf(getListenSocketsIgnoringLifecycle()));
        this.rootContext = ((Context) Preconditions.checkNotNull(context, "rootContext")).fork();
        this.decompressorRegistry = serverImplBuilder.decompressorRegistry;
        this.compressorRegistry = serverImplBuilder.compressorRegistry;
        this.transportFilters = Collections.unmodifiableList(new ArrayList(serverImplBuilder.transportFilters));
        this.interceptors = (ServerInterceptor[]) serverImplBuilder.interceptors.toArray(new ServerInterceptor[serverImplBuilder.interceptors.size()]);
        this.handshakeTimeoutMillis = serverImplBuilder.handshakeTimeoutMillis;
        this.binlog = serverImplBuilder.binlog;
        InternalChannelz internalChannelz = serverImplBuilder.channelz;
        this.channelz = internalChannelz;
        this.serverCallTracer = serverImplBuilder.callTracerFactory.create();
        this.ticker = (Deadline.Ticker) Preconditions.checkNotNull(serverImplBuilder.ticker, "ticker");
        internalChannelz.addServer(this);
        this.executorSupplier = serverImplBuilder.executorSupplier;
    }

    public ServerImpl start() throws IOException {
        synchronized (this.lock) {
            boolean z = false;
            Preconditions.checkState(!this.started, "Already started");
            if (!this.shutdown) {
                z = true;
            }
            Preconditions.checkState(z, "Shutting down");
            this.transportServer.start(new ServerListenerImpl());
            this.executor = (Executor) Preconditions.checkNotNull((Executor) this.executorPool.getObject(), "executor");
            this.started = true;
        }
        return this;
    }

    public int getPort() {
        synchronized (this.lock) {
            Preconditions.checkState(this.started, "Not started");
            Preconditions.checkState(!this.terminated, "Already terminated");
            for (SocketAddress socketAddress : this.transportServer.getListenSocketAddresses()) {
                if (socketAddress instanceof InetSocketAddress) {
                    int port = ((InetSocketAddress) socketAddress).getPort();
                    return port;
                }
            }
            return -1;
        }
    }

    public List<SocketAddress> getListenSockets() {
        List<SocketAddress> listenSocketsIgnoringLifecycle;
        synchronized (this.lock) {
            Preconditions.checkState(this.started, "Not started");
            Preconditions.checkState(!this.terminated, "Already terminated");
            listenSocketsIgnoringLifecycle = getListenSocketsIgnoringLifecycle();
        }
        return listenSocketsIgnoringLifecycle;
    }

    private List<SocketAddress> getListenSocketsIgnoringLifecycle() {
        List<SocketAddress> unmodifiableList;
        synchronized (this.lock) {
            unmodifiableList = Collections.unmodifiableList(this.transportServer.getListenSocketAddresses());
        }
        return unmodifiableList;
    }

    public List<ServerServiceDefinition> getServices() {
        List<ServerServiceDefinition> services = this.fallbackRegistry.getServices();
        if (services.isEmpty()) {
            return this.registry.getServices();
        }
        List<ServerServiceDefinition> services2 = this.registry.getServices();
        ArrayList arrayList = new ArrayList(services2.size() + services.size());
        arrayList.addAll(services2);
        arrayList.addAll(services);
        return Collections.unmodifiableList(arrayList);
    }

    public List<ServerServiceDefinition> getImmutableServices() {
        return this.registry.getServices();
    }

    public List<ServerServiceDefinition> getMutableServices() {
        return Collections.unmodifiableList(this.fallbackRegistry.getServices());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0016, code lost:
        if (r2 == false) goto L_0x001d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
        r3.transportServer.shutdown();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        return r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public io.grpc.internal.ServerImpl shutdown() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.lock
            monitor-enter(r0)
            boolean r1 = r3.shutdown     // Catch:{ all -> 0x001e }
            if (r1 == 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x001e }
            return r3
        L_0x0009:
            r1 = 1
            r3.shutdown = r1     // Catch:{ all -> 0x001e }
            boolean r2 = r3.started     // Catch:{ all -> 0x001e }
            if (r2 != 0) goto L_0x0015
            r3.transportServersTerminated = r1     // Catch:{ all -> 0x001e }
            r3.checkForTermination()     // Catch:{ all -> 0x001e }
        L_0x0015:
            monitor-exit(r0)     // Catch:{ all -> 0x001e }
            if (r2 == 0) goto L_0x001d
            io.grpc.internal.InternalServer r0 = r3.transportServer
            r0.shutdown()
        L_0x001d:
            return r3
        L_0x001e:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x001e }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.ServerImpl.shutdown():io.grpc.internal.ServerImpl");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0022, code lost:
        r1 = r2.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002a, code lost:
        if (r1.hasNext() == false) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002c, code lost:
        ((io.grpc.internal.ServerTransport) r1.next()).shutdownNow(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0036, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0020, code lost:
        if (r3 == false) goto L_0x0036;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public io.grpc.internal.ServerImpl shutdownNow() {
        /*
            r4 = this;
            r4.shutdown()
            io.grpc.Status r0 = io.grpc.Status.UNAVAILABLE
            java.lang.String r1 = "Server shutdownNow invoked"
            io.grpc.Status r0 = r0.withDescription(r1)
            java.lang.Object r1 = r4.lock
            monitor-enter(r1)
            io.grpc.Status r2 = r4.shutdownNowStatus     // Catch:{ all -> 0x0037 }
            if (r2 == 0) goto L_0x0014
            monitor-exit(r1)     // Catch:{ all -> 0x0037 }
            return r4
        L_0x0014:
            r4.shutdownNowStatus = r0     // Catch:{ all -> 0x0037 }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x0037 }
            java.util.Set<io.grpc.internal.ServerTransport> r3 = r4.transports     // Catch:{ all -> 0x0037 }
            r2.<init>(r3)     // Catch:{ all -> 0x0037 }
            boolean r3 = r4.serverShutdownCallbackInvoked     // Catch:{ all -> 0x0037 }
            monitor-exit(r1)     // Catch:{ all -> 0x0037 }
            if (r3 == 0) goto L_0x0036
            java.util.Iterator r1 = r2.iterator()
        L_0x0026:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0036
            java.lang.Object r2 = r1.next()
            io.grpc.internal.ServerTransport r2 = (io.grpc.internal.ServerTransport) r2
            r2.shutdownNow(r0)
            goto L_0x0026
        L_0x0036:
            return r4
        L_0x0037:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0037 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.ServerImpl.shutdownNow():io.grpc.internal.ServerImpl");
    }

    public boolean isShutdown() {
        boolean z;
        synchronized (this.lock) {
            z = this.shutdown;
        }
        return z;
    }

    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        boolean z;
        synchronized (this.lock) {
            long nanoTime = System.nanoTime() + timeUnit.toNanos(j);
            while (!this.terminated) {
                long nanoTime2 = nanoTime - System.nanoTime();
                if (nanoTime2 <= 0) {
                    break;
                }
                TimeUnit.NANOSECONDS.timedWait(this.lock, nanoTime2);
            }
            z = this.terminated;
        }
        return z;
    }

    public void awaitTermination() throws InterruptedException {
        synchronized (this.lock) {
            while (!this.terminated) {
                this.lock.wait();
            }
        }
    }

    public boolean isTerminated() {
        boolean z;
        synchronized (this.lock) {
            z = this.terminated;
        }
        return z;
    }

    /* access modifiers changed from: private */
    public void transportClosed(ServerTransport serverTransport) {
        synchronized (this.lock) {
            if (this.transports.remove(serverTransport)) {
                this.channelz.removeServerSocket(this, serverTransport);
                checkForTermination();
            } else {
                throw new AssertionError("Transport already removed");
            }
        }
    }

    /* access modifiers changed from: private */
    public void checkForTermination() {
        synchronized (this.lock) {
            if (this.shutdown && this.transports.isEmpty() && this.transportServersTerminated) {
                if (!this.terminated) {
                    this.terminated = true;
                    this.channelz.removeServer(this);
                    Executor executor2 = this.executor;
                    if (executor2 != null) {
                        this.executor = (Executor) this.executorPool.returnObject(executor2);
                    }
                    this.lock.notifyAll();
                } else {
                    throw new AssertionError("Server already terminated");
                }
            }
        }
    }

    private final class ServerListenerImpl implements ServerListener {
        private ServerListenerImpl() {
        }

        public ServerTransportListener transportCreated(ServerTransport serverTransport) {
            synchronized (ServerImpl.this.lock) {
                ServerImpl.this.transports.add(serverTransport);
            }
            ServerTransportListenerImpl serverTransportListenerImpl = new ServerTransportListenerImpl(serverTransport);
            serverTransportListenerImpl.init();
            return serverTransportListenerImpl;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0031, code lost:
            if (r0.hasNext() == false) goto L_0x0043;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0033, code lost:
            r1 = (io.grpc.internal.ServerTransport) r0.next();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0039, code lost:
            if (r2 != null) goto L_0x003f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x003b, code lost:
            r1.shutdown();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x003f, code lost:
            r1.shutdownNow(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0043, code lost:
            r1 = io.grpc.internal.ServerImpl.access$200(r5.this$0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0049, code lost:
            monitor-enter(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            io.grpc.internal.ServerImpl.access$602(r5.this$0, true);
            io.grpc.internal.ServerImpl.access$700(r5.this$0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0054, code lost:
            monitor-exit(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0055, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0029, code lost:
            r0 = r1.iterator();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void serverShutdown() {
            /*
                r5 = this;
                io.grpc.internal.ServerImpl r0 = io.grpc.internal.ServerImpl.this
                java.lang.Object r0 = r0.lock
                monitor-enter(r0)
                io.grpc.internal.ServerImpl r1 = io.grpc.internal.ServerImpl.this     // Catch:{ all -> 0x0059 }
                boolean r1 = r1.serverShutdownCallbackInvoked     // Catch:{ all -> 0x0059 }
                if (r1 == 0) goto L_0x0011
                monitor-exit(r0)     // Catch:{ all -> 0x0059 }
                return
            L_0x0011:
                java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0059 }
                io.grpc.internal.ServerImpl r2 = io.grpc.internal.ServerImpl.this     // Catch:{ all -> 0x0059 }
                java.util.Set r2 = r2.transports     // Catch:{ all -> 0x0059 }
                r1.<init>(r2)     // Catch:{ all -> 0x0059 }
                io.grpc.internal.ServerImpl r2 = io.grpc.internal.ServerImpl.this     // Catch:{ all -> 0x0059 }
                io.grpc.Status r2 = r2.shutdownNowStatus     // Catch:{ all -> 0x0059 }
                io.grpc.internal.ServerImpl r3 = io.grpc.internal.ServerImpl.this     // Catch:{ all -> 0x0059 }
                r4 = 1
                boolean unused = r3.serverShutdownCallbackInvoked = r4     // Catch:{ all -> 0x0059 }
                monitor-exit(r0)     // Catch:{ all -> 0x0059 }
                java.util.Iterator r0 = r1.iterator()
            L_0x002d:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L_0x0043
                java.lang.Object r1 = r0.next()
                io.grpc.internal.ServerTransport r1 = (io.grpc.internal.ServerTransport) r1
                if (r2 != 0) goto L_0x003f
                r1.shutdown()
                goto L_0x002d
            L_0x003f:
                r1.shutdownNow(r2)
                goto L_0x002d
            L_0x0043:
                io.grpc.internal.ServerImpl r0 = io.grpc.internal.ServerImpl.this
                java.lang.Object r1 = r0.lock
                monitor-enter(r1)
                io.grpc.internal.ServerImpl r0 = io.grpc.internal.ServerImpl.this     // Catch:{ all -> 0x0056 }
                boolean unused = r0.transportServersTerminated = r4     // Catch:{ all -> 0x0056 }
                io.grpc.internal.ServerImpl r0 = io.grpc.internal.ServerImpl.this     // Catch:{ all -> 0x0056 }
                r0.checkForTermination()     // Catch:{ all -> 0x0056 }
                monitor-exit(r1)     // Catch:{ all -> 0x0056 }
                return
            L_0x0056:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x0056 }
                throw r0
            L_0x0059:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0059 }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.ServerImpl.ServerListenerImpl.serverShutdown():void");
        }
    }

    private final class ServerTransportListenerImpl implements ServerTransportListener {
        private Attributes attributes;
        private Future<?> handshakeTimeoutFuture;
        /* access modifiers changed from: private */
        public final ServerTransport transport;

        ServerTransportListenerImpl(ServerTransport serverTransport) {
            this.transport = serverTransport;
        }

        public void init() {
            if (ServerImpl.this.handshakeTimeoutMillis != Long.MAX_VALUE) {
                this.handshakeTimeoutFuture = this.transport.getScheduledExecutorService().schedule(new Runnable() {
                    public void run() {
                        ServerTransportListenerImpl.this.transport.shutdownNow(Status.CANCELLED.withDescription("Handshake timeout exceeded"));
                    }
                }, ServerImpl.this.handshakeTimeoutMillis, TimeUnit.MILLISECONDS);
            } else {
                this.handshakeTimeoutFuture = new FutureTask(new Runnable() {
                    public void run() {
                    }
                }, (Object) null);
            }
            ServerImpl.this.channelz.addServerSocket(ServerImpl.this, this.transport);
        }

        public Attributes transportReady(Attributes attributes2) {
            this.handshakeTimeoutFuture.cancel(false);
            this.handshakeTimeoutFuture = null;
            for (ServerTransportFilter serverTransportFilter : ServerImpl.this.transportFilters) {
                attributes2 = (Attributes) Preconditions.checkNotNull(serverTransportFilter.transportReady(attributes2), "Filter %s returned null", (Object) serverTransportFilter);
            }
            this.attributes = attributes2;
            return attributes2;
        }

        public void transportTerminated() {
            Future<?> future = this.handshakeTimeoutFuture;
            if (future != null) {
                future.cancel(false);
                this.handshakeTimeoutFuture = null;
            }
            for (ServerTransportFilter transportTerminated : ServerImpl.this.transportFilters) {
                transportTerminated.transportTerminated(this.attributes);
            }
            ServerImpl.this.transportClosed(this.transport);
        }

        public void streamCreated(ServerStream serverStream, String str, Metadata metadata) {
            Tag createTag = PerfMark.createTag(str, (long) serverStream.streamId());
            TaskCloseable traceTask = PerfMark.traceTask("ServerTransportListener.streamCreated");
            try {
                PerfMark.attachTag(createTag);
                streamCreatedInternal(serverStream, str, metadata, createTag);
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

        private void streamCreatedInternal(ServerStream serverStream, String str, Metadata metadata, Tag tag) {
            Executor executor;
            ServerStream serverStream2 = serverStream;
            Metadata metadata2 = metadata;
            if (ServerImpl.this.executorSupplier == null && ServerImpl.this.executor == MoreExecutors.directExecutor()) {
                executor = new SerializeReentrantCallsDirectExecutor();
                serverStream.optimizeForDirectExecutor();
            } else {
                executor = new SerializingExecutor(ServerImpl.this.executor);
            }
            Executor executor2 = executor;
            if (metadata2.containsKey(GrpcUtil.MESSAGE_ENCODING_KEY)) {
                String str2 = (String) metadata2.get(GrpcUtil.MESSAGE_ENCODING_KEY);
                Decompressor lookupDecompressor = ServerImpl.this.decompressorRegistry.lookupDecompressor(str2);
                if (lookupDecompressor == null) {
                    serverStream2.setListener(ServerImpl.NOOP_LISTENER);
                    serverStream2.close(Status.UNIMPLEMENTED.withDescription(String.format("Can't find decompressor for %s", new Object[]{str2})), new Metadata());
                    return;
                }
                serverStream2.setDecompressor(lookupDecompressor);
            }
            StatsTraceContext statsTraceContext = (StatsTraceContext) Preconditions.checkNotNull(serverStream.statsTraceContext(), "statsTraceCtx not present from stream");
            Context.CancellableContext createContext = createContext(metadata2, statsTraceContext);
            Link linkOut = PerfMark.linkOut();
            JumpToApplicationThreadServerStreamListener jumpToApplicationThreadServerStreamListener = new JumpToApplicationThreadServerStreamListener(executor2, ServerImpl.this.executor, serverStream, createContext, tag);
            serverStream2.setListener(jumpToApplicationThreadServerStreamListener);
            SettableFuture create = SettableFuture.create();
            Context.CancellableContext cancellableContext = createContext;
            AnonymousClass1MethodLookup r12 = r0;
            JumpToApplicationThreadServerStreamListener jumpToApplicationThreadServerStreamListener2 = jumpToApplicationThreadServerStreamListener;
            AnonymousClass1MethodLookup r0 = new ContextRunnable(cancellableContext, tag, linkOut, str, serverStream, jumpToApplicationThreadServerStreamListener, create, statsTraceContext, metadata, executor2) {
                final /* synthetic */ Context.CancellableContext val$context;
                final /* synthetic */ SettableFuture val$future;
                final /* synthetic */ Metadata val$headers;
                final /* synthetic */ JumpToApplicationThreadServerStreamListener val$jumpListener;
                final /* synthetic */ Link val$link;
                final /* synthetic */ String val$methodName;
                final /* synthetic */ StatsTraceContext val$statsTraceCtx;
                final /* synthetic */ ServerStream val$stream;
                final /* synthetic */ Tag val$tag;
                final /* synthetic */ Executor val$wrappedExecutor;

                {
                    this.val$context = r2;
                    this.val$tag = r3;
                    this.val$link = r4;
                    this.val$methodName = r5;
                    this.val$stream = r6;
                    this.val$jumpListener = r7;
                    this.val$future = r8;
                    this.val$statsTraceCtx = r9;
                    this.val$headers = r10;
                    this.val$wrappedExecutor = r11;
                }

                public void runInContext() {
                    TaskCloseable traceTask = PerfMark.traceTask("ServerTransportListener$MethodLookup.startCall");
                    try {
                        PerfMark.attachTag(this.val$tag);
                        PerfMark.linkIn(this.val$link);
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
                    try {
                        ServerMethodDefinition<?, ?> lookupMethod = ServerImpl.this.registry.lookupMethod(this.val$methodName);
                        if (lookupMethod == null) {
                            lookupMethod = ServerImpl.this.fallbackRegistry.lookupMethod(this.val$methodName, this.val$stream.getAuthority());
                        }
                        if (lookupMethod == null) {
                            Status withDescription = Status.UNIMPLEMENTED.withDescription("Method not found: " + this.val$methodName);
                            this.val$jumpListener.setListener(ServerImpl.NOOP_LISTENER);
                            this.val$stream.close(withDescription, new Metadata());
                            this.val$context.cancel((Throwable) null);
                            this.val$future.cancel(false);
                            return;
                        }
                        this.val$future.set(maySwitchExecutor(ServerTransportListenerImpl.this.wrapMethod(this.val$stream, lookupMethod, this.val$statsTraceCtx), this.val$stream, this.val$headers, this.val$context, this.val$tag));
                    } catch (Throwable th) {
                        this.val$jumpListener.setListener(ServerImpl.NOOP_LISTENER);
                        this.val$stream.close(Status.fromThrowable(th), new Metadata());
                        this.val$context.cancel((Throwable) null);
                        this.val$future.cancel(false);
                        throw th;
                    }
                }

                private <ReqT, RespT> ServerCallParameters<ReqT, RespT> maySwitchExecutor(ServerMethodDefinition<ReqT, RespT> serverMethodDefinition, ServerStream serverStream, Metadata metadata, Context.CancellableContext cancellableContext, Tag tag) {
                    Executor executor;
                    ServerCallImpl serverCallImpl = new ServerCallImpl(serverStream, serverMethodDefinition.getMethodDescriptor(), metadata, cancellableContext, ServerImpl.this.decompressorRegistry, ServerImpl.this.compressorRegistry, ServerImpl.this.serverCallTracer, tag);
                    if (!(ServerImpl.this.executorSupplier == null || (executor = ServerImpl.this.executorSupplier.getExecutor(serverCallImpl, metadata)) == null)) {
                        ((SerializingExecutor) this.val$wrappedExecutor).setExecutor(executor);
                    }
                    return new ServerCallParameters<>(serverCallImpl, serverMethodDefinition.getServerCallHandler());
                }
            };
            executor2.execute(r12);
            executor2.execute(new ContextRunnable(cancellableContext, linkOut, tag, create, str, metadata, serverStream, jumpToApplicationThreadServerStreamListener2) {
                final /* synthetic */ Context.CancellableContext val$context;
                final /* synthetic */ SettableFuture val$future;
                final /* synthetic */ Metadata val$headers;
                final /* synthetic */ JumpToApplicationThreadServerStreamListener val$jumpListener;
                final /* synthetic */ Link val$link;
                final /* synthetic */ String val$methodName;
                final /* synthetic */ ServerStream val$stream;
                final /* synthetic */ Tag val$tag;

                {
                    this.val$context = r2;
                    this.val$link = r3;
                    this.val$tag = r4;
                    this.val$future = r5;
                    this.val$methodName = r6;
                    this.val$headers = r7;
                    this.val$stream = r8;
                    this.val$jumpListener = r9;
                }

                public void runInContext() {
                    TaskCloseable traceTask = PerfMark.traceTask("ServerTransportListener$HandleServerCall.startCall");
                    try {
                        PerfMark.linkIn(this.val$link);
                        PerfMark.attachTag(this.val$tag);
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
                    ServerStreamListener access$1600 = ServerImpl.NOOP_LISTENER;
                    if (!this.val$future.isCancelled()) {
                        try {
                            this.val$jumpListener.setListener(ServerTransportListenerImpl.this.startWrappedCall(this.val$methodName, (ServerCallParameters) Futures.getDone(this.val$future), this.val$headers));
                            this.val$context.addListener(new Context.CancellationListener() {
                                public void cancelled(Context context) {
                                    Status statusFromCancelled = Contexts.statusFromCancelled(context);
                                    if (Status.DEADLINE_EXCEEDED.getCode().equals(statusFromCancelled.getCode())) {
                                        AnonymousClass1HandleServerCall.this.val$stream.cancel(statusFromCancelled);
                                    }
                                }
                            }, MoreExecutors.directExecutor());
                        } catch (Throwable th) {
                            this.val$jumpListener.setListener(access$1600);
                            throw th;
                        }
                    }
                }
            });
        }

        private Context.CancellableContext createContext(Metadata metadata, StatsTraceContext statsTraceContext) {
            Long l = (Long) metadata.get(GrpcUtil.TIMEOUT_KEY);
            Context withValue = statsTraceContext.serverFilterContext(ServerImpl.this.rootContext).withValue(InternalServer.SERVER_CONTEXT_KEY, ServerImpl.this);
            if (l == null) {
                return withValue.withCancellation();
            }
            return withValue.withDeadline(Deadline.after(l.longValue(), TimeUnit.NANOSECONDS, ServerImpl.this.ticker), this.transport.getScheduledExecutorService());
        }

        /* access modifiers changed from: private */
        public <ReqT, RespT> ServerMethodDefinition<?, ?> wrapMethod(ServerStream serverStream, ServerMethodDefinition<ReqT, RespT> serverMethodDefinition, StatsTraceContext statsTraceContext) {
            statsTraceContext.serverCallStarted(new ServerCallInfoImpl(serverMethodDefinition.getMethodDescriptor(), serverStream.getAttributes(), serverStream.getAuthority()));
            ServerCallHandler<ReqT, RespT> serverCallHandler = serverMethodDefinition.getServerCallHandler();
            for (ServerInterceptor interceptCallHandlerCreate : ServerImpl.this.interceptors) {
                serverCallHandler = InternalServerInterceptors.interceptCallHandlerCreate(interceptCallHandlerCreate, serverCallHandler);
            }
            ServerMethodDefinition<ReqT, RespT> withServerCallHandler = serverMethodDefinition.withServerCallHandler(serverCallHandler);
            return ServerImpl.this.binlog == null ? withServerCallHandler : ServerImpl.this.binlog.wrapMethodDefinition(withServerCallHandler);
        }

        private final class ServerCallParameters<ReqT, RespT> {
            ServerCallImpl<ReqT, RespT> call;
            ServerCallHandler<ReqT, RespT> callHandler;

            public ServerCallParameters(ServerCallImpl<ReqT, RespT> serverCallImpl, ServerCallHandler<ReqT, RespT> serverCallHandler) {
                this.call = serverCallImpl;
                this.callHandler = serverCallHandler;
            }
        }

        /* access modifiers changed from: private */
        public <WReqT, WRespT> ServerStreamListener startWrappedCall(String str, ServerCallParameters<WReqT, WRespT> serverCallParameters, Metadata metadata) {
            ServerCall.Listener<ReqT> startCall = serverCallParameters.callHandler.startCall(serverCallParameters.call, metadata);
            if (startCall != null) {
                return serverCallParameters.call.newServerStreamListener(startCall);
            }
            throw new NullPointerException("startCall() returned a null listener for method " + str);
        }
    }

    public InternalLogId getLogId() {
        return this.logId;
    }

    public ListenableFuture<InternalChannelz.ServerStats> getStats() {
        InternalChannelz.ServerStats.Builder builder = new InternalChannelz.ServerStats.Builder();
        List<InternalInstrumented<InternalChannelz.SocketStats>> listenSocketStatsList = this.transportServer.getListenSocketStatsList();
        if (listenSocketStatsList != null) {
            builder.addListenSockets(listenSocketStatsList);
        }
        this.serverCallTracer.updateBuilder(builder);
        SettableFuture create = SettableFuture.create();
        create.set(builder.build());
        return create;
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("logId", this.logId.getId()).add("transportServer", (Object) this.transportServer).toString();
    }

    private static final class NoopListener implements ServerStreamListener {
        public void closed(Status status) {
        }

        public void halfClosed() {
        }

        public void onReady() {
        }

        private NoopListener() {
        }

        public void messagesAvailable(StreamListener.MessageProducer messageProducer) {
            while (true) {
                InputStream next = messageProducer.next();
                if (next != null) {
                    try {
                        next.close();
                    } catch (IOException e) {
                        while (true) {
                            InputStream next2 = messageProducer.next();
                            if (next2 != null) {
                                try {
                                    next2.close();
                                } catch (IOException e2) {
                                    ServerImpl.log.log(Level.WARNING, "Exception closing stream", e2);
                                }
                            } else {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                } else {
                    return;
                }
            }
        }
    }

    static final class JumpToApplicationThreadServerStreamListener implements ServerStreamListener {
        private final Executor callExecutor;
        private final Executor cancelExecutor;
        /* access modifiers changed from: private */
        public final Context.CancellableContext context;
        private ServerStreamListener listener;
        private final ServerStream stream;
        /* access modifiers changed from: private */
        public final Tag tag;

        public JumpToApplicationThreadServerStreamListener(Executor executor, Executor executor2, ServerStream serverStream, Context.CancellableContext cancellableContext, Tag tag2) {
            this.callExecutor = executor;
            this.cancelExecutor = executor2;
            this.stream = serverStream;
            this.context = cancellableContext;
            this.tag = tag2;
        }

        /* access modifiers changed from: private */
        public ServerStreamListener getListener() {
            ServerStreamListener serverStreamListener = this.listener;
            if (serverStreamListener != null) {
                return serverStreamListener;
            }
            throw new IllegalStateException("listener unset");
        }

        /* access modifiers changed from: package-private */
        public void setListener(ServerStreamListener serverStreamListener) {
            Preconditions.checkNotNull(serverStreamListener, "listener must not be null");
            Preconditions.checkState(this.listener == null, "Listener already set");
            this.listener = serverStreamListener;
        }

        /* access modifiers changed from: private */
        public void internalClose(Throwable th) {
            this.stream.close(Status.UNKNOWN.withDescription("Application error processing RPC").withCause(th), new Metadata());
        }

        public void messagesAvailable(final StreamListener.MessageProducer messageProducer) {
            TaskCloseable traceTask = PerfMark.traceTask("ServerStreamListener.messagesAvailable");
            try {
                PerfMark.attachTag(this.tag);
                final Link linkOut = PerfMark.linkOut();
                this.callExecutor.execute(new ContextRunnable() {
                    public void runInContext() {
                        TaskCloseable traceTask;
                        try {
                            traceTask = PerfMark.traceTask("ServerCallListener(app).messagesAvailable");
                            PerfMark.attachTag(JumpToApplicationThreadServerStreamListener.this.tag);
                            PerfMark.linkIn(linkOut);
                            JumpToApplicationThreadServerStreamListener.this.getListener().messagesAvailable(messageProducer);
                            if (traceTask != null) {
                                traceTask.close();
                                return;
                            }
                            return;
                        } catch (Throwable th) {
                            JumpToApplicationThreadServerStreamListener.this.internalClose(th);
                            throw th;
                        }
                        throw th;
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

        public void halfClosed() {
            TaskCloseable traceTask = PerfMark.traceTask("ServerStreamListener.halfClosed");
            try {
                PerfMark.attachTag(this.tag);
                final Link linkOut = PerfMark.linkOut();
                this.callExecutor.execute(new ContextRunnable() {
                    public void runInContext() {
                        TaskCloseable traceTask;
                        try {
                            traceTask = PerfMark.traceTask("ServerCallListener(app).halfClosed");
                            PerfMark.attachTag(JumpToApplicationThreadServerStreamListener.this.tag);
                            PerfMark.linkIn(linkOut);
                            JumpToApplicationThreadServerStreamListener.this.getListener().halfClosed();
                            if (traceTask != null) {
                                traceTask.close();
                                return;
                            }
                            return;
                        } catch (Throwable th) {
                            JumpToApplicationThreadServerStreamListener.this.internalClose(th);
                            throw th;
                        }
                        throw th;
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

        public void closed(Status status) {
            TaskCloseable traceTask = PerfMark.traceTask("ServerStreamListener.closed");
            try {
                PerfMark.attachTag(this.tag);
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

        private void closedInternal(final Status status) {
            if (!status.isOk()) {
                Throwable cause = status.getCause();
                if (cause == null) {
                    cause = InternalStatus.asRuntimeException(Status.CANCELLED.withDescription("RPC cancelled"), (Metadata) null, false);
                }
                this.cancelExecutor.execute(new ContextCloser(this.context, cause));
            }
            final Link linkOut = PerfMark.linkOut();
            this.callExecutor.execute(new ContextRunnable() {
                public void runInContext() {
                    TaskCloseable traceTask = PerfMark.traceTask("ServerCallListener(app).closed");
                    try {
                        PerfMark.attachTag(JumpToApplicationThreadServerStreamListener.this.tag);
                        PerfMark.linkIn(linkOut);
                        JumpToApplicationThreadServerStreamListener.this.getListener().closed(status);
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
            });
        }

        public void onReady() {
            TaskCloseable traceTask = PerfMark.traceTask("ServerStreamListener.onReady");
            try {
                PerfMark.attachTag(this.tag);
                final Link linkOut = PerfMark.linkOut();
                this.callExecutor.execute(new ContextRunnable() {
                    public void runInContext() {
                        TaskCloseable traceTask;
                        try {
                            traceTask = PerfMark.traceTask("ServerCallListener(app).onReady");
                            PerfMark.attachTag(JumpToApplicationThreadServerStreamListener.this.tag);
                            PerfMark.linkIn(linkOut);
                            JumpToApplicationThreadServerStreamListener.this.getListener().onReady();
                            if (traceTask != null) {
                                traceTask.close();
                                return;
                            }
                            return;
                        } catch (Throwable th) {
                            JumpToApplicationThreadServerStreamListener.this.internalClose(th);
                            throw th;
                        }
                        throw th;
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
    }

    static final class ContextCloser implements Runnable {
        private final Throwable cause;
        private final Context.CancellableContext context;

        ContextCloser(Context.CancellableContext cancellableContext, Throwable th) {
            this.context = cancellableContext;
            this.cause = th;
        }

        public void run() {
            this.context.cancel(this.cause);
        }
    }
}
