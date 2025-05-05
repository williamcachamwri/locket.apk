package io.grpc.internal;

import androidx.core.app.NotificationCompat;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import io.grpc.BinaryLog;
import io.grpc.BindableService;
import io.grpc.CompressorRegistry;
import io.grpc.Context;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.HandlerRegistry;
import io.grpc.InternalChannelz;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerCallExecutorSupplier;
import io.grpc.ServerInterceptor;
import io.grpc.ServerMethodDefinition;
import io.grpc.ServerServiceDefinition;
import io.grpc.ServerStreamTracer;
import io.grpc.ServerTransportFilter;
import io.grpc.internal.CallTracer;
import io.grpc.internal.InternalHandlerRegistry;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import javax.annotation.Nullable;

public final class ServerImplBuilder extends ServerBuilder<ServerImplBuilder> {
    private static final CompressorRegistry DEFAULT_COMPRESSOR_REGISTRY = CompressorRegistry.getDefaultInstance();
    private static final DecompressorRegistry DEFAULT_DECOMPRESSOR_REGISTRY = DecompressorRegistry.getDefaultInstance();
    private static final ObjectPool<? extends Executor> DEFAULT_EXECUTOR_POOL = SharedResourcePool.forResource(GrpcUtil.SHARED_CHANNEL_EXECUTOR);
    private static final HandlerRegistry DEFAULT_FALLBACK_REGISTRY = new DefaultFallbackRegistry();
    private static final long DEFAULT_HANDSHAKE_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(120);
    private static final Logger log = Logger.getLogger(ServerImplBuilder.class.getName());
    @Nullable
    BinaryLog binlog;
    CallTracer.Factory callTracerFactory = CallTracer.getDefaultFactory();
    InternalChannelz channelz = InternalChannelz.instance();
    private final ClientTransportServersBuilder clientTransportServersBuilder;
    CompressorRegistry compressorRegistry = DEFAULT_COMPRESSOR_REGISTRY;
    DecompressorRegistry decompressorRegistry = DEFAULT_DECOMPRESSOR_REGISTRY;
    ObjectPool<? extends Executor> executorPool = DEFAULT_EXECUTOR_POOL;
    @Nullable
    ServerCallExecutorSupplier executorSupplier;
    HandlerRegistry fallbackRegistry = DEFAULT_FALLBACK_REGISTRY;
    long handshakeTimeoutMillis = DEFAULT_HANDSHAKE_TIMEOUT_MILLIS;
    final List<ServerInterceptor> interceptors = new ArrayList();
    private boolean recordFinishedRpcs = true;
    private boolean recordRealTimeMetrics = false;
    private boolean recordStartedRpcs = true;
    final InternalHandlerRegistry.Builder registryBuilder = new InternalHandlerRegistry.Builder();
    private boolean statsEnabled = true;
    private final List<ServerStreamTracer.Factory> streamTracerFactories = new ArrayList();
    Deadline.Ticker ticker = Deadline.getSystemTicker();
    private boolean tracingEnabled = true;
    final List<ServerTransportFilter> transportFilters = new ArrayList();

    public interface ClientTransportServersBuilder {
        InternalServer buildClientTransportServers(List<? extends ServerStreamTracer.Factory> list);
    }

    public static ServerBuilder<?> forPort(int i) {
        throw new UnsupportedOperationException("ClientTransportServersBuilder is required, use a constructor");
    }

    public ServerImplBuilder(ClientTransportServersBuilder clientTransportServersBuilder2) {
        this.clientTransportServersBuilder = (ClientTransportServersBuilder) Preconditions.checkNotNull(clientTransportServersBuilder2, "clientTransportServersBuilder");
    }

    public ServerImplBuilder directExecutor() {
        return executor(MoreExecutors.directExecutor());
    }

    public ServerImplBuilder executor(@Nullable Executor executor) {
        this.executorPool = executor != null ? new FixedObjectPool<>(executor) : DEFAULT_EXECUTOR_POOL;
        return this;
    }

    public ServerImplBuilder callExecutor(ServerCallExecutorSupplier serverCallExecutorSupplier) {
        this.executorSupplier = (ServerCallExecutorSupplier) Preconditions.checkNotNull(serverCallExecutorSupplier);
        return this;
    }

    public ServerImplBuilder addService(ServerServiceDefinition serverServiceDefinition) {
        this.registryBuilder.addService((ServerServiceDefinition) Preconditions.checkNotNull(serverServiceDefinition, NotificationCompat.CATEGORY_SERVICE));
        return this;
    }

    public ServerImplBuilder addService(BindableService bindableService) {
        return addService(((BindableService) Preconditions.checkNotNull(bindableService, "bindableService")).bindService());
    }

    public ServerImplBuilder addTransportFilter(ServerTransportFilter serverTransportFilter) {
        this.transportFilters.add((ServerTransportFilter) Preconditions.checkNotNull(serverTransportFilter, "filter"));
        return this;
    }

    public ServerImplBuilder intercept(ServerInterceptor serverInterceptor) {
        this.interceptors.add((ServerInterceptor) Preconditions.checkNotNull(serverInterceptor, "interceptor"));
        return this;
    }

    public ServerImplBuilder addStreamTracerFactory(ServerStreamTracer.Factory factory) {
        this.streamTracerFactories.add((ServerStreamTracer.Factory) Preconditions.checkNotNull(factory, "factory"));
        return this;
    }

    public ServerImplBuilder fallbackHandlerRegistry(@Nullable HandlerRegistry handlerRegistry) {
        if (handlerRegistry == null) {
            handlerRegistry = DEFAULT_FALLBACK_REGISTRY;
        }
        this.fallbackRegistry = handlerRegistry;
        return this;
    }

    public ServerImplBuilder decompressorRegistry(@Nullable DecompressorRegistry decompressorRegistry2) {
        if (decompressorRegistry2 == null) {
            decompressorRegistry2 = DEFAULT_DECOMPRESSOR_REGISTRY;
        }
        this.decompressorRegistry = decompressorRegistry2;
        return this;
    }

    public ServerImplBuilder compressorRegistry(@Nullable CompressorRegistry compressorRegistry2) {
        if (compressorRegistry2 == null) {
            compressorRegistry2 = DEFAULT_COMPRESSOR_REGISTRY;
        }
        this.compressorRegistry = compressorRegistry2;
        return this;
    }

    public ServerImplBuilder handshakeTimeout(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j > 0, "handshake timeout is %s, but must be positive", j);
        this.handshakeTimeoutMillis = ((TimeUnit) Preconditions.checkNotNull(timeUnit, "unit")).toMillis(j);
        return this;
    }

    public ServerImplBuilder setBinaryLog(@Nullable BinaryLog binaryLog) {
        this.binlog = binaryLog;
        return this;
    }

    public void setStatsEnabled(boolean z) {
        this.statsEnabled = z;
    }

    public void setStatsRecordStartedRpcs(boolean z) {
        this.recordStartedRpcs = z;
    }

    public void setStatsRecordFinishedRpcs(boolean z) {
        this.recordFinishedRpcs = z;
    }

    public void setStatsRecordRealTimeMetrics(boolean z) {
        this.recordRealTimeMetrics = z;
    }

    public void setTracingEnabled(boolean z) {
        this.tracingEnabled = z;
    }

    public void setDeadlineTicker(Deadline.Ticker ticker2) {
        this.ticker = (Deadline.Ticker) Preconditions.checkNotNull(ticker2, "ticker");
    }

    public Server build() {
        return new ServerImpl(this, this.clientTransportServersBuilder.buildClientTransportServers(getTracerFactories()), Context.ROOT);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00cc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<? extends io.grpc.ServerStreamTracer.Factory> getTracerFactories() {
        /*
            r12 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.List r1 = io.grpc.InternalGlobalInterceptors.getServerInterceptors()
            java.util.List r2 = io.grpc.InternalGlobalInterceptors.getServerStreamTracerFactories()
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L_0x001b
            r0.addAll(r2)
            java.util.List<io.grpc.ServerInterceptor> r2 = r12.interceptors
            r2.addAll(r1)
            r1 = r3
            goto L_0x001c
        L_0x001b:
            r1 = r4
        L_0x001c:
            java.lang.String r2 = "getServerStreamTracerFactory"
            r5 = 0
            java.lang.String r6 = "Unable to apply census stats"
            if (r1 != 0) goto L_0x008b
            boolean r7 = r12.statsEnabled
            if (r7 == 0) goto L_0x008b
            java.lang.String r7 = "io.grpc.census.InternalCensusStatsAccessor"
            java.lang.Class r7 = java.lang.Class.forName(r7)     // Catch:{ ClassNotFoundException -> 0x007d, NoSuchMethodException -> 0x0074, IllegalAccessException -> 0x006b, InvocationTargetException -> 0x0062 }
            r8 = 3
            java.lang.Class[] r9 = new java.lang.Class[r8]     // Catch:{ ClassNotFoundException -> 0x007d, NoSuchMethodException -> 0x0074, IllegalAccessException -> 0x006b, InvocationTargetException -> 0x0062 }
            java.lang.Class r10 = java.lang.Boolean.TYPE     // Catch:{ ClassNotFoundException -> 0x007d, NoSuchMethodException -> 0x0074, IllegalAccessException -> 0x006b, InvocationTargetException -> 0x0062 }
            r9[r4] = r10     // Catch:{ ClassNotFoundException -> 0x007d, NoSuchMethodException -> 0x0074, IllegalAccessException -> 0x006b, InvocationTargetException -> 0x0062 }
            java.lang.Class r10 = java.lang.Boolean.TYPE     // Catch:{ ClassNotFoundException -> 0x007d, NoSuchMethodException -> 0x0074, IllegalAccessException -> 0x006b, InvocationTargetException -> 0x0062 }
            r9[r3] = r10     // Catch:{ ClassNotFoundException -> 0x007d, NoSuchMethodException -> 0x0074, IllegalAccessException -> 0x006b, InvocationTargetException -> 0x0062 }
            java.lang.Class r10 = java.lang.Boolean.TYPE     // Catch:{ ClassNotFoundException -> 0x007d, NoSuchMethodException -> 0x0074, IllegalAccessException -> 0x006b, InvocationTargetException -> 0x0062 }
            r11 = 2
            r9[r11] = r10     // Catch:{ ClassNotFoundException -> 0x007d, NoSuchMethodException -> 0x0074, IllegalAccessException -> 0x006b, InvocationTargetException -> 0x0062 }
            java.lang.reflect.Method r7 = r7.getDeclaredMethod(r2, r9)     // Catch:{ ClassNotFoundException -> 0x007d, NoSuchMethodException -> 0x0074, IllegalAccessException -> 0x006b, InvocationTargetException -> 0x0062 }
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ ClassNotFoundException -> 0x007d, NoSuchMethodException -> 0x0074, IllegalAccessException -> 0x006b, InvocationTargetException -> 0x0062 }
            boolean r9 = r12.recordStartedRpcs     // Catch:{ ClassNotFoundException -> 0x007d, NoSuchMethodException -> 0x0074, IllegalAccessException -> 0x006b, InvocationTargetException -> 0x0062 }
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)     // Catch:{ ClassNotFoundException -> 0x007d, NoSuchMethodException -> 0x0074, IllegalAccessException -> 0x006b, InvocationTargetException -> 0x0062 }
            r8[r4] = r9     // Catch:{ ClassNotFoundException -> 0x007d, NoSuchMethodException -> 0x0074, IllegalAccessException -> 0x006b, InvocationTargetException -> 0x0062 }
            boolean r9 = r12.recordFinishedRpcs     // Catch:{ ClassNotFoundException -> 0x007d, NoSuchMethodException -> 0x0074, IllegalAccessException -> 0x006b, InvocationTargetException -> 0x0062 }
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)     // Catch:{ ClassNotFoundException -> 0x007d, NoSuchMethodException -> 0x0074, IllegalAccessException -> 0x006b, InvocationTargetException -> 0x0062 }
            r8[r3] = r9     // Catch:{ ClassNotFoundException -> 0x007d, NoSuchMethodException -> 0x0074, IllegalAccessException -> 0x006b, InvocationTargetException -> 0x0062 }
            boolean r3 = r12.recordRealTimeMetrics     // Catch:{ ClassNotFoundException -> 0x007d, NoSuchMethodException -> 0x0074, IllegalAccessException -> 0x006b, InvocationTargetException -> 0x0062 }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ ClassNotFoundException -> 0x007d, NoSuchMethodException -> 0x0074, IllegalAccessException -> 0x006b, InvocationTargetException -> 0x0062 }
            r8[r11] = r3     // Catch:{ ClassNotFoundException -> 0x007d, NoSuchMethodException -> 0x0074, IllegalAccessException -> 0x006b, InvocationTargetException -> 0x0062 }
            java.lang.Object r3 = r7.invoke(r5, r8)     // Catch:{ ClassNotFoundException -> 0x007d, NoSuchMethodException -> 0x0074, IllegalAccessException -> 0x006b, InvocationTargetException -> 0x0062 }
            io.grpc.ServerStreamTracer$Factory r3 = (io.grpc.ServerStreamTracer.Factory) r3     // Catch:{ ClassNotFoundException -> 0x007d, NoSuchMethodException -> 0x0074, IllegalAccessException -> 0x006b, InvocationTargetException -> 0x0062 }
            goto L_0x0086
        L_0x0062:
            r3 = move-exception
            java.util.logging.Logger r7 = log
            java.util.logging.Level r8 = java.util.logging.Level.FINE
            r7.log(r8, r6, r3)
            goto L_0x0085
        L_0x006b:
            r3 = move-exception
            java.util.logging.Logger r7 = log
            java.util.logging.Level r8 = java.util.logging.Level.FINE
            r7.log(r8, r6, r3)
            goto L_0x0085
        L_0x0074:
            r3 = move-exception
            java.util.logging.Logger r7 = log
            java.util.logging.Level r8 = java.util.logging.Level.FINE
            r7.log(r8, r6, r3)
            goto L_0x0085
        L_0x007d:
            r3 = move-exception
            java.util.logging.Logger r7 = log
            java.util.logging.Level r8 = java.util.logging.Level.FINE
            r7.log(r8, r6, r3)
        L_0x0085:
            r3 = r5
        L_0x0086:
            if (r3 == 0) goto L_0x008b
            r0.add(r3)
        L_0x008b:
            if (r1 != 0) goto L_0x00cf
            boolean r1 = r12.tracingEnabled
            if (r1 == 0) goto L_0x00cf
            java.lang.String r1 = "io.grpc.census.InternalCensusTracingAccessor"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00b9, IllegalAccessException -> 0x00b0, InvocationTargetException -> 0x00a7 }
            java.lang.Class[] r3 = new java.lang.Class[r4]     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00b9, IllegalAccessException -> 0x00b0, InvocationTargetException -> 0x00a7 }
            java.lang.reflect.Method r1 = r1.getDeclaredMethod(r2, r3)     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00b9, IllegalAccessException -> 0x00b0, InvocationTargetException -> 0x00a7 }
            java.lang.Object[] r2 = new java.lang.Object[r4]     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00b9, IllegalAccessException -> 0x00b0, InvocationTargetException -> 0x00a7 }
            java.lang.Object r1 = r1.invoke(r5, r2)     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00b9, IllegalAccessException -> 0x00b0, InvocationTargetException -> 0x00a7 }
            io.grpc.ServerStreamTracer$Factory r1 = (io.grpc.ServerStreamTracer.Factory) r1     // Catch:{ ClassNotFoundException -> 0x00c2, NoSuchMethodException -> 0x00b9, IllegalAccessException -> 0x00b0, InvocationTargetException -> 0x00a7 }
            r5 = r1
            goto L_0x00ca
        L_0x00a7:
            r1 = move-exception
            java.util.logging.Logger r2 = log
            java.util.logging.Level r3 = java.util.logging.Level.FINE
            r2.log(r3, r6, r1)
            goto L_0x00ca
        L_0x00b0:
            r1 = move-exception
            java.util.logging.Logger r2 = log
            java.util.logging.Level r3 = java.util.logging.Level.FINE
            r2.log(r3, r6, r1)
            goto L_0x00ca
        L_0x00b9:
            r1 = move-exception
            java.util.logging.Logger r2 = log
            java.util.logging.Level r3 = java.util.logging.Level.FINE
            r2.log(r3, r6, r1)
            goto L_0x00ca
        L_0x00c2:
            r1 = move-exception
            java.util.logging.Logger r2 = log
            java.util.logging.Level r3 = java.util.logging.Level.FINE
            r2.log(r3, r6, r1)
        L_0x00ca:
            if (r5 == 0) goto L_0x00cf
            r0.add(r5)
        L_0x00cf:
            java.util.List<io.grpc.ServerStreamTracer$Factory> r1 = r12.streamTracerFactories
            r0.addAll(r1)
            r0.trimToSize()
            java.util.List r0 = java.util.Collections.unmodifiableList(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.ServerImplBuilder.getTracerFactories():java.util.List");
    }

    public InternalChannelz getChannelz() {
        return this.channelz;
    }

    private static final class DefaultFallbackRegistry extends HandlerRegistry {
        @Nullable
        public ServerMethodDefinition<?, ?> lookupMethod(String str, @Nullable String str2) {
            return null;
        }

        private DefaultFallbackRegistry() {
        }

        public List<ServerServiceDefinition> getServices() {
            return Collections.emptyList();
        }
    }

    public ObjectPool<? extends Executor> getExecutorPool() {
        return this.executorPool;
    }

    public ServerImplBuilder useTransportSecurity(File file, File file2) {
        throw new UnsupportedOperationException("TLS not supported in ServerImplBuilder");
    }
}
