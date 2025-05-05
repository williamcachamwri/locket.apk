package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import io.grpc.Attributes;
import io.grpc.CallCredentials;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ChannelCredentials;
import io.grpc.ChannelLogger;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ClientInterceptors;
import io.grpc.ClientStreamTracer;
import io.grpc.ClientTransportFilter;
import io.grpc.CompressorRegistry;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.Context;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.EquivalentAddressGroup;
import io.grpc.ForwardingChannelBuilder2;
import io.grpc.ForwardingClientCall;
import io.grpc.Grpc;
import io.grpc.InternalChannelz;
import io.grpc.InternalConfigSelector;
import io.grpc.InternalInstrumented;
import io.grpc.InternalLogId;
import io.grpc.LoadBalancer;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.NameResolver;
import io.grpc.NameResolverProvider;
import io.grpc.NameResolverRegistry;
import io.grpc.ProxyDetector;
import io.grpc.Status;
import io.grpc.SynchronizationContext;
import io.grpc.internal.AutoConfiguredLoadBalancerFactory;
import io.grpc.internal.BackoffPolicy;
import io.grpc.internal.CallTracer;
import io.grpc.internal.ClientCallImpl;
import io.grpc.internal.ClientTransportFactory;
import io.grpc.internal.ExponentialBackoffPolicy;
import io.grpc.internal.InternalSubchannel;
import io.grpc.internal.ManagedChannelImplBuilder;
import io.grpc.internal.ManagedChannelServiceConfig;
import io.grpc.internal.ManagedClientTransport;
import io.grpc.internal.RetriableStream;
import io.grpc.internal.RetryingNameResolver;
import java.lang.Thread;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.annotation.Nullable;

final class ManagedChannelImpl extends ManagedChannel implements InternalInstrumented<InternalChannelz.ChannelStats> {
    /* access modifiers changed from: private */
    public static final ManagedChannelServiceConfig EMPTY_SERVICE_CONFIG = ManagedChannelServiceConfig.empty();
    static final long IDLE_TIMEOUT_MILLIS_DISABLE = -1;
    /* access modifiers changed from: private */
    public static final InternalConfigSelector INITIAL_PENDING_SELECTOR = new InternalConfigSelector() {
        public InternalConfigSelector.Result selectConfig(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            throw new IllegalStateException("Resolution is pending");
        }
    };
    /* access modifiers changed from: private */
    public static final ClientCall<Object, Object> NOOP_CALL = new ClientCall<Object, Object>() {
        public void cancel(String str, Throwable th) {
        }

        public void halfClose() {
        }

        public boolean isReady() {
            return false;
        }

        public void request(int i) {
        }

        public void sendMessage(Object obj) {
        }

        public void start(ClientCall.Listener<Object> listener, Metadata metadata) {
        }
    };
    static final Status SHUTDOWN_NOW_STATUS = Status.UNAVAILABLE.withDescription("Channel shutdownNow invoked");
    static final Status SHUTDOWN_STATUS = Status.UNAVAILABLE.withDescription("Channel shutdown invoked");
    static final long SUBCHANNEL_SHUTDOWN_DELAY_SECONDS = 5;
    static final Status SUBCHANNEL_SHUTDOWN_STATUS = Status.UNAVAILABLE.withDescription("Subchannel shutdown invoked");
    static final Pattern URI_PATTERN = Pattern.compile("[a-zA-Z][a-zA-Z0-9+.-]*:/.*");
    static final Logger logger = Logger.getLogger(ManagedChannelImpl.class.getName());
    /* access modifiers changed from: private */
    @Nullable
    public final String authorityOverride;
    /* access modifiers changed from: private */
    public final BackoffPolicy.Provider backoffPolicyProvider;
    /* access modifiers changed from: private */
    public final ExecutorHolder balancerRpcExecutorHolder;
    /* access modifiers changed from: private */
    public final ObjectPool<? extends Executor> balancerRpcExecutorPool;
    /* access modifiers changed from: private */
    public final CallTracer.Factory callTracerFactory;
    /* access modifiers changed from: private */
    public final long channelBufferLimit;
    /* access modifiers changed from: private */
    public final RetriableStream.ChannelBufferMeter channelBufferUsed = new RetriableStream.ChannelBufferMeter();
    /* access modifiers changed from: private */
    public final CallTracer channelCallTracer;
    /* access modifiers changed from: private */
    public final ChannelLogger channelLogger;
    /* access modifiers changed from: private */
    public final ConnectivityStateManager channelStateManager = new ConnectivityStateManager();
    /* access modifiers changed from: private */
    public final ChannelTracer channelTracer;
    /* access modifiers changed from: private */
    public final InternalChannelz channelz;
    /* access modifiers changed from: private */
    public final CompressorRegistry compressorRegistry;
    /* access modifiers changed from: private */
    public final DecompressorRegistry decompressorRegistry;
    /* access modifiers changed from: private */
    @Nullable
    public final ManagedChannelServiceConfig defaultServiceConfig;
    /* access modifiers changed from: private */
    public final DelayedClientTransport delayedTransport;
    private final ManagedClientTransport.Listener delayedTransportListener;
    /* access modifiers changed from: private */
    public final Executor executor;
    private final ObjectPool<? extends Executor> executorPool;
    /* access modifiers changed from: private */
    public boolean fullStreamDecompression;
    private final long idleTimeoutMillis;
    private final Rescheduler idleTimer;
    final InUseStateAggregator<Object> inUseStateAggregator;
    private final Channel interceptorChannel;
    /* access modifiers changed from: private */
    public ResolutionState lastResolutionState = ResolutionState.NO_RESOLUTION;
    /* access modifiers changed from: private */
    public ManagedChannelServiceConfig lastServiceConfig = EMPTY_SERVICE_CONFIG;
    /* access modifiers changed from: private */
    @Nullable
    public LbHelperImpl lbHelper;
    private final AutoConfiguredLoadBalancerFactory loadBalancerFactory;
    private final InternalLogId logId;
    /* access modifiers changed from: private */
    public final boolean lookUpServiceConfig;
    /* access modifiers changed from: private */
    public final int maxTraceEvents;
    /* access modifiers changed from: private */
    public NameResolver nameResolver;
    /* access modifiers changed from: private */
    public final NameResolver.Args nameResolverArgs;
    /* access modifiers changed from: private */
    public final NameResolverRegistry nameResolverRegistry;
    /* access modifiers changed from: private */
    public boolean nameResolverStarted;
    /* access modifiers changed from: private */
    public final ExecutorHolder offloadExecutorHolder;
    /* access modifiers changed from: private */
    public final Set<OobChannel> oobChannels = new HashSet(1, 0.75f);
    /* access modifiers changed from: private */
    public final ClientTransportFactory oobTransportFactory;
    /* access modifiers changed from: private */
    @Nullable
    public final ChannelCredentials originalChannelCreds;
    /* access modifiers changed from: private */
    public final ClientTransportFactory originalTransportFactory;
    private boolean panicMode;
    /* access modifiers changed from: private */
    @Nullable
    public Collection<RealChannel.PendingCall<?, ?>> pendingCalls;
    /* access modifiers changed from: private */
    public final Object pendingCallsInUseObject = new Object();
    /* access modifiers changed from: private */
    public final long perRpcBufferLimit;
    /* access modifiers changed from: private */
    public final RealChannel realChannel;
    /* access modifiers changed from: private */
    public final boolean retryEnabled;
    /* access modifiers changed from: private */
    public final RestrictedScheduledExecutor scheduledExecutor;
    /* access modifiers changed from: private */
    public boolean serviceConfigUpdated = false;
    /* access modifiers changed from: private */
    public final AtomicBoolean shutdown = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public boolean shutdownNowed;
    /* access modifiers changed from: private */
    public final Supplier<Stopwatch> stopwatchSupplier;
    /* access modifiers changed from: private */
    @Nullable
    public volatile LoadBalancer.SubchannelPicker subchannelPicker;
    /* access modifiers changed from: private */
    public final Set<InternalSubchannel> subchannels = new HashSet(16, 0.75f);
    final SynchronizationContext syncContext;
    /* access modifiers changed from: private */
    public final String target;
    /* access modifiers changed from: private */
    public volatile boolean terminated;
    private final CountDownLatch terminatedLatch = new CountDownLatch(1);
    /* access modifiers changed from: private */
    public boolean terminating;
    /* access modifiers changed from: private */
    public final Deadline.Ticker ticker = Deadline.getSystemTicker();
    /* access modifiers changed from: private */
    public final TimeProvider timeProvider;
    /* access modifiers changed from: private */
    public final ClientTransportFactory transportFactory;
    /* access modifiers changed from: private */
    public final List<ClientTransportFilter> transportFilters;
    /* access modifiers changed from: private */
    public final ChannelStreamProvider transportProvider;
    /* access modifiers changed from: private */
    public final UncommittedRetriableStreamsRegistry uncommittedRetriableStreamsRegistry = new UncommittedRetriableStreamsRegistry();
    /* access modifiers changed from: private */
    @Nullable
    public final String userAgent;

    enum ResolutionState {
        NO_RESOLUTION,
        SUCCESS,
        ERROR
    }

    /* access modifiers changed from: private */
    public void maybeShutdownNowSubchannels() {
        if (this.shutdownNowed) {
            for (InternalSubchannel shutdownNow : this.subchannels) {
                shutdownNow.shutdownNow(SHUTDOWN_NOW_STATUS);
            }
            for (OobChannel internalSubchannel : this.oobChannels) {
                internalSubchannel.getInternalSubchannel().shutdownNow(SHUTDOWN_NOW_STATUS);
            }
        }
    }

    public ListenableFuture<InternalChannelz.ChannelStats> getStats() {
        final SettableFuture create = SettableFuture.create();
        this.syncContext.execute(new Runnable() {
            public void run() {
                InternalChannelz.ChannelStats.Builder builder = new InternalChannelz.ChannelStats.Builder();
                ManagedChannelImpl.this.channelCallTracer.updateBuilder(builder);
                ManagedChannelImpl.this.channelTracer.updateBuilder(builder);
                builder.setTarget(ManagedChannelImpl.this.target).setState(ManagedChannelImpl.this.channelStateManager.getState());
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(ManagedChannelImpl.this.subchannels);
                arrayList.addAll(ManagedChannelImpl.this.oobChannels);
                builder.setSubchannels(arrayList);
                create.set(builder.build());
            }
        });
        return create;
    }

    public InternalLogId getLogId() {
        return this.logId;
    }

    private class IdleModeTimer implements Runnable {
        private IdleModeTimer() {
        }

        public void run() {
            if (ManagedChannelImpl.this.lbHelper != null) {
                ManagedChannelImpl.this.enterIdleMode();
            }
        }
    }

    /* access modifiers changed from: private */
    public void shutdownNameResolverAndLoadBalancer(boolean z) {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        if (z) {
            Preconditions.checkState(this.nameResolverStarted, "nameResolver is not started");
            Preconditions.checkState(this.lbHelper != null, "lbHelper is null");
        }
        NameResolver nameResolver2 = this.nameResolver;
        if (nameResolver2 != null) {
            nameResolver2.shutdown();
            this.nameResolverStarted = false;
            if (z) {
                this.nameResolver = getNameResolver(this.target, this.authorityOverride, this.nameResolverRegistry, this.nameResolverArgs, this.transportFactory.getSupportedSocketAddressTypes());
            } else {
                this.nameResolver = null;
            }
        }
        LbHelperImpl lbHelperImpl = this.lbHelper;
        if (lbHelperImpl != null) {
            lbHelperImpl.lb.shutdown();
            this.lbHelper = null;
        }
        this.subchannelPicker = null;
    }

    /* access modifiers changed from: package-private */
    public void exitIdleMode() {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        if (!this.shutdown.get() && !this.panicMode) {
            if (this.inUseStateAggregator.isInUse()) {
                cancelIdleTimer(false);
            } else {
                rescheduleIdleTimer();
            }
            if (this.lbHelper == null) {
                this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Exiting idle mode");
                LbHelperImpl lbHelperImpl = new LbHelperImpl();
                lbHelperImpl.lb = this.loadBalancerFactory.newLoadBalancer(lbHelperImpl);
                this.lbHelper = lbHelperImpl;
                this.nameResolver.start((NameResolver.Listener2) new NameResolverListener(lbHelperImpl, this.nameResolver));
                this.nameResolverStarted = true;
            }
        }
    }

    /* access modifiers changed from: private */
    public void enterIdleMode() {
        shutdownNameResolverAndLoadBalancer(true);
        this.delayedTransport.reprocess((LoadBalancer.SubchannelPicker) null);
        this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Entering IDLE state");
        this.channelStateManager.gotoState(ConnectivityState.IDLE);
        if (this.inUseStateAggregator.anyObjectInUse(this.pendingCallsInUseObject, this.delayedTransport)) {
            exitIdleMode();
        }
    }

    /* access modifiers changed from: private */
    public void cancelIdleTimer(boolean z) {
        this.idleTimer.cancel(z);
    }

    /* access modifiers changed from: private */
    public void rescheduleIdleTimer() {
        long j = this.idleTimeoutMillis;
        if (j != -1) {
            this.idleTimer.reschedule(j, TimeUnit.MILLISECONDS);
        }
    }

    /* access modifiers changed from: private */
    public void refreshNameResolution() {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        if (this.nameResolverStarted) {
            this.nameResolver.refresh();
        }
    }

    private final class ChannelStreamProvider implements ClientCallImpl.ClientStreamProvider {
        volatile RetriableStream.Throttle throttle;

        private ChannelStreamProvider() {
        }

        /* access modifiers changed from: private */
        public ClientTransport getTransport(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            LoadBalancer.SubchannelPicker access$1200 = ManagedChannelImpl.this.subchannelPicker;
            if (ManagedChannelImpl.this.shutdown.get()) {
                return ManagedChannelImpl.this.delayedTransport;
            }
            if (access$1200 == null) {
                ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                    public void run() {
                        ManagedChannelImpl.this.exitIdleMode();
                    }
                });
                return ManagedChannelImpl.this.delayedTransport;
            }
            ClientTransport transportFromPickResult = GrpcUtil.getTransportFromPickResult(access$1200.pickSubchannel(pickSubchannelArgs), pickSubchannelArgs.getCallOptions().isWaitForReady());
            if (transportFromPickResult != null) {
                return transportFromPickResult;
            }
            return ManagedChannelImpl.this.delayedTransport;
        }

        public ClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, CallOptions callOptions, Metadata metadata, Context context) {
            RetryPolicy retryPolicy;
            if (!ManagedChannelImpl.this.retryEnabled) {
                ClientTransport transport = getTransport(new PickSubchannelArgsImpl(methodDescriptor, metadata, callOptions));
                Context attach = context.attach();
                try {
                    return transport.newStream(methodDescriptor, metadata, callOptions, GrpcUtil.getClientStreamTracers(callOptions, metadata, 0, false));
                } finally {
                    context.detach(attach);
                }
            } else {
                ManagedChannelServiceConfig.MethodInfo methodInfo = (ManagedChannelServiceConfig.MethodInfo) callOptions.getOption(ManagedChannelServiceConfig.MethodInfo.KEY);
                HedgingPolicy hedgingPolicy = null;
                if (methodInfo == null) {
                    retryPolicy = null;
                } else {
                    retryPolicy = methodInfo.retryPolicy;
                }
                if (methodInfo != null) {
                    hedgingPolicy = methodInfo.hedgingPolicy;
                }
                return new RetriableStream<ReqT>(this, methodDescriptor, metadata, callOptions, retryPolicy, hedgingPolicy, context) {
                    final /* synthetic */ ChannelStreamProvider this$1;
                    final /* synthetic */ CallOptions val$callOptions;
                    final /* synthetic */ Context val$context;
                    final /* synthetic */ Metadata val$headers;
                    final /* synthetic */ HedgingPolicy val$hedgingPolicy;
                    final /* synthetic */ MethodDescriptor val$method;
                    final /* synthetic */ RetryPolicy val$retryPolicy;

                    {
                        ChannelStreamProvider channelStreamProvider = r16;
                        CallOptions callOptions = r19;
                        this.this$1 = channelStreamProvider;
                        this.val$method = r17;
                        this.val$headers = r18;
                        this.val$callOptions = callOptions;
                        RetryPolicy retryPolicy = r20;
                        this.val$retryPolicy = retryPolicy;
                        HedgingPolicy hedgingPolicy = r21;
                        this.val$hedgingPolicy = hedgingPolicy;
                        this.val$context = r22;
                        RetriableStream.ChannelBufferMeter access$1600 = ManagedChannelImpl.this.channelBufferUsed;
                        long access$1700 = ManagedChannelImpl.this.perRpcBufferLimit;
                        long access$1800 = ManagedChannelImpl.this.channelBufferLimit;
                        Executor access$1900 = ManagedChannelImpl.this.getCallExecutor(callOptions);
                        ScheduledExecutorService scheduledExecutorService = ManagedChannelImpl.this.transportFactory.getScheduledExecutorService();
                        RetriableStream.Throttle throttle = channelStreamProvider.throttle;
                    }

                    /* access modifiers changed from: package-private */
                    public Status prestart() {
                        return ManagedChannelImpl.this.uncommittedRetriableStreamsRegistry.add(this);
                    }

                    /* access modifiers changed from: package-private */
                    public void postCommit() {
                        ManagedChannelImpl.this.uncommittedRetriableStreamsRegistry.remove(this);
                    }

                    /* access modifiers changed from: package-private */
                    public ClientStream newSubstream(Metadata metadata, ClientStreamTracer.Factory factory, int i, boolean z) {
                        CallOptions withStreamTracerFactory = this.val$callOptions.withStreamTracerFactory(factory);
                        ClientStreamTracer[] clientStreamTracers = GrpcUtil.getClientStreamTracers(withStreamTracerFactory, metadata, i, z);
                        ClientTransport access$2200 = this.this$1.getTransport(new PickSubchannelArgsImpl(this.val$method, metadata, withStreamTracerFactory));
                        Context attach = this.val$context.attach();
                        try {
                            return access$2200.newStream(this.val$method, metadata, withStreamTracerFactory, clientStreamTracers);
                        } finally {
                            this.val$context.detach(attach);
                        }
                    }
                };
            }
        }
    }

    ManagedChannelImpl(ManagedChannelImplBuilder managedChannelImplBuilder, ClientTransportFactory clientTransportFactory, BackoffPolicy.Provider provider, ObjectPool<? extends Executor> objectPool, Supplier<Stopwatch> supplier, List<ClientInterceptor> list, TimeProvider timeProvider2) {
        AnonymousClass1 r2;
        ManagedChannelImplBuilder managedChannelImplBuilder2 = managedChannelImplBuilder;
        ClientTransportFactory clientTransportFactory2 = clientTransportFactory;
        ObjectPool<? extends Executor> objectPool2 = objectPool;
        TimeProvider timeProvider3 = timeProvider2;
        SynchronizationContext synchronizationContext = new SynchronizationContext(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread thread, Throwable th) {
                ManagedChannelImpl.logger.log(Level.SEVERE, "[" + ManagedChannelImpl.this.getLogId() + "] Uncaught exception in the SynchronizationContext. Panic!", th);
                ManagedChannelImpl.this.panic(th);
            }
        });
        this.syncContext = synchronizationContext;
        DelayedTransportListener delayedTransportListener2 = new DelayedTransportListener();
        this.delayedTransportListener = delayedTransportListener2;
        this.inUseStateAggregator = new IdleModeStateAggregator();
        this.transportProvider = new ChannelStreamProvider();
        String str = (String) Preconditions.checkNotNull(managedChannelImplBuilder2.target, "target");
        this.target = str;
        InternalLogId allocate = InternalLogId.allocate("Channel", str);
        this.logId = allocate;
        this.timeProvider = (TimeProvider) Preconditions.checkNotNull(timeProvider3, "timeProvider");
        ObjectPool<? extends Executor> objectPool3 = (ObjectPool) Preconditions.checkNotNull(managedChannelImplBuilder2.executorPool, "executorPool");
        this.executorPool = objectPool3;
        Executor executor2 = (Executor) Preconditions.checkNotNull((Executor) objectPool3.getObject(), "executor");
        this.executor = executor2;
        this.originalChannelCreds = managedChannelImplBuilder2.channelCredentials;
        this.originalTransportFactory = clientTransportFactory2;
        ExecutorHolder executorHolder = new ExecutorHolder((ObjectPool) Preconditions.checkNotNull(managedChannelImplBuilder2.offloadExecutorPool, "offloadExecutorPool"));
        this.offloadExecutorHolder = executorHolder;
        CallCredentialsApplyingTransportFactory callCredentialsApplyingTransportFactory = new CallCredentialsApplyingTransportFactory(clientTransportFactory2, managedChannelImplBuilder2.callCredentials, executorHolder);
        this.transportFactory = callCredentialsApplyingTransportFactory;
        this.oobTransportFactory = new CallCredentialsApplyingTransportFactory(clientTransportFactory2, (CallCredentials) null, executorHolder);
        RestrictedScheduledExecutor restrictedScheduledExecutor = new RestrictedScheduledExecutor(callCredentialsApplyingTransportFactory.getScheduledExecutorService());
        this.scheduledExecutor = restrictedScheduledExecutor;
        this.maxTraceEvents = managedChannelImplBuilder2.maxTraceEvents;
        ChannelTracer channelTracer2 = r12;
        CallCredentialsApplyingTransportFactory callCredentialsApplyingTransportFactory2 = callCredentialsApplyingTransportFactory;
        ExecutorHolder executorHolder2 = executorHolder;
        ChannelTracer channelTracer3 = new ChannelTracer(allocate, managedChannelImplBuilder2.maxTraceEvents, timeProvider2.currentTimeNanos(), "Channel for '" + str + "'");
        this.channelTracer = channelTracer2;
        ChannelLoggerImpl channelLoggerImpl = new ChannelLoggerImpl(channelTracer2, timeProvider3);
        this.channelLogger = channelLoggerImpl;
        ProxyDetector proxyDetector = managedChannelImplBuilder2.proxyDetector != null ? managedChannelImplBuilder2.proxyDetector : GrpcUtil.DEFAULT_PROXY_DETECTOR;
        boolean z = managedChannelImplBuilder2.retryEnabled;
        this.retryEnabled = z;
        AutoConfiguredLoadBalancerFactory autoConfiguredLoadBalancerFactory = new AutoConfiguredLoadBalancerFactory(managedChannelImplBuilder2.defaultLbPolicy);
        this.loadBalancerFactory = autoConfiguredLoadBalancerFactory;
        NameResolverRegistry nameResolverRegistry2 = managedChannelImplBuilder2.nameResolverRegistry;
        this.nameResolverRegistry = nameResolverRegistry2;
        ScParser scParser = new ScParser(z, managedChannelImplBuilder2.maxRetryAttempts, managedChannelImplBuilder2.maxHedgedAttempts, autoConfiguredLoadBalancerFactory);
        String str2 = managedChannelImplBuilder2.authorityOverride;
        this.authorityOverride = str2;
        NameResolver.Args build = NameResolver.Args.newBuilder().setDefaultPort(managedChannelImplBuilder.getDefaultPort()).setProxyDetector(proxyDetector).setSynchronizationContext(synchronizationContext).setScheduledExecutorService(restrictedScheduledExecutor).setServiceConfigParser(scParser).setChannelLogger(channelLoggerImpl).setOffloadExecutor(executorHolder2).setOverrideAuthority(str2).build();
        this.nameResolverArgs = build;
        this.nameResolver = getNameResolver(str, str2, nameResolverRegistry2, build, callCredentialsApplyingTransportFactory2.getSupportedSocketAddressTypes());
        this.balancerRpcExecutorPool = (ObjectPool) Preconditions.checkNotNull(objectPool2, "balancerRpcExecutorPool");
        this.balancerRpcExecutorHolder = new ExecutorHolder(objectPool2);
        DelayedClientTransport delayedClientTransport = new DelayedClientTransport(executor2, synchronizationContext);
        this.delayedTransport = delayedClientTransport;
        delayedClientTransport.start(delayedTransportListener2);
        this.backoffPolicyProvider = provider;
        if (managedChannelImplBuilder2.defaultServiceConfig != null) {
            NameResolver.ConfigOrError parseServiceConfig = scParser.parseServiceConfig(managedChannelImplBuilder2.defaultServiceConfig);
            Preconditions.checkState(parseServiceConfig.getError() == null, "Default config is invalid: %s", (Object) parseServiceConfig.getError());
            ManagedChannelServiceConfig managedChannelServiceConfig = (ManagedChannelServiceConfig) parseServiceConfig.getConfig();
            this.defaultServiceConfig = managedChannelServiceConfig;
            this.lastServiceConfig = managedChannelServiceConfig;
            r2 = null;
        } else {
            r2 = null;
            this.defaultServiceConfig = null;
        }
        boolean z2 = managedChannelImplBuilder2.lookUpServiceConfig;
        this.lookUpServiceConfig = z2;
        RealChannel realChannel2 = new RealChannel(this.nameResolver.getServiceAuthority());
        this.realChannel = realChannel2;
        this.interceptorChannel = ClientInterceptors.intercept(managedChannelImplBuilder2.binlog != null ? managedChannelImplBuilder2.binlog.wrapChannel(realChannel2) : realChannel2, (List<? extends ClientInterceptor>) list);
        this.transportFilters = new ArrayList(managedChannelImplBuilder2.transportFilters);
        this.stopwatchSupplier = (Supplier) Preconditions.checkNotNull(supplier, "stopwatchSupplier");
        if (managedChannelImplBuilder2.idleTimeoutMillis == -1) {
            this.idleTimeoutMillis = managedChannelImplBuilder2.idleTimeoutMillis;
        } else {
            Preconditions.checkArgument(managedChannelImplBuilder2.idleTimeoutMillis >= ManagedChannelImplBuilder.IDLE_MODE_MIN_TIMEOUT_MILLIS, "invalid idleTimeoutMillis %s", managedChannelImplBuilder2.idleTimeoutMillis);
            this.idleTimeoutMillis = managedChannelImplBuilder2.idleTimeoutMillis;
        }
        this.idleTimer = new Rescheduler(new IdleModeTimer(), synchronizationContext, callCredentialsApplyingTransportFactory2.getScheduledExecutorService(), supplier.get());
        this.fullStreamDecompression = managedChannelImplBuilder2.fullStreamDecompression;
        this.decompressorRegistry = (DecompressorRegistry) Preconditions.checkNotNull(managedChannelImplBuilder2.decompressorRegistry, "decompressorRegistry");
        this.compressorRegistry = (CompressorRegistry) Preconditions.checkNotNull(managedChannelImplBuilder2.compressorRegistry, "compressorRegistry");
        this.userAgent = managedChannelImplBuilder2.userAgent;
        this.channelBufferLimit = managedChannelImplBuilder2.retryBufferSize;
        this.perRpcBufferLimit = managedChannelImplBuilder2.perRpcBufferLimit;
        final TimeProvider timeProvider4 = timeProvider2;
        AnonymousClass1ChannelCallTracerFactory r22 = new CallTracer.Factory() {
            public CallTracer create() {
                return new CallTracer(timeProvider4);
            }
        };
        this.callTracerFactory = r22;
        this.channelCallTracer = r22.create();
        InternalChannelz internalChannelz = (InternalChannelz) Preconditions.checkNotNull(managedChannelImplBuilder2.channelz);
        this.channelz = internalChannelz;
        internalChannelz.addRootChannel(this);
        if (!z2) {
            if (this.defaultServiceConfig != null) {
                channelLoggerImpl.log(ChannelLogger.ChannelLogLevel.INFO, "Service config look-up disabled, using default service config");
            }
            this.serviceConfigUpdated = true;
        }
    }

    private static NameResolver getNameResolver(String str, NameResolverRegistry nameResolverRegistry2, NameResolver.Args args, Collection<Class<? extends SocketAddress>> collection) {
        URI uri;
        StringBuilder sb = new StringBuilder();
        try {
            uri = new URI(str);
        } catch (URISyntaxException e) {
            sb.append(e.getMessage());
            uri = null;
        }
        NameResolverProvider providerForScheme = uri != null ? nameResolverRegistry2.getProviderForScheme(uri.getScheme()) : null;
        String str2 = "";
        if (providerForScheme == null && !URI_PATTERN.matcher(str).matches()) {
            try {
                uri = new URI(nameResolverRegistry2.getDefaultScheme(), str2, "/" + str, (String) null);
                providerForScheme = nameResolverRegistry2.getProviderForScheme(uri.getScheme());
            } catch (URISyntaxException e2) {
                throw new IllegalArgumentException(e2);
            }
        }
        if (providerForScheme == null) {
            Object[] objArr = new Object[2];
            objArr[0] = str;
            if (sb.length() > 0) {
                str2 = " (" + sb + ")";
            }
            objArr[1] = str2;
            throw new IllegalArgumentException(String.format("Could not find a NameResolverProvider for %s%s", objArr));
        } else if (collection == null || collection.containsAll(providerForScheme.getProducedSocketAddressTypes())) {
            NameResolver newNameResolver = providerForScheme.newNameResolver(uri, args);
            if (newNameResolver != null) {
                return newNameResolver;
            }
            Object[] objArr2 = new Object[2];
            objArr2[0] = str;
            if (sb.length() > 0) {
                str2 = " (" + sb + ")";
            }
            objArr2[1] = str2;
            throw new IllegalArgumentException(String.format("cannot create a NameResolver for %s%s", objArr2));
        } else {
            throw new IllegalArgumentException(String.format("Address types of NameResolver '%s' for '%s' not supported by transport", new Object[]{uri.getScheme(), str}));
        }
    }

    static NameResolver getNameResolver(String str, @Nullable final String str2, NameResolverRegistry nameResolverRegistry2, NameResolver.Args args, Collection<Class<? extends SocketAddress>> collection) {
        RetryingNameResolver retryingNameResolver = new RetryingNameResolver(getNameResolver(str, nameResolverRegistry2, args, collection), new BackoffPolicyRetryScheduler(new ExponentialBackoffPolicy.Provider(), args.getScheduledExecutorService(), args.getSynchronizationContext()), args.getSynchronizationContext());
        if (str2 == null) {
            return retryingNameResolver;
        }
        return new ForwardingNameResolver(retryingNameResolver) {
            public String getServiceAuthority() {
                return str2;
            }
        };
    }

    /* access modifiers changed from: package-private */
    public InternalConfigSelector getConfigSelector() {
        return (InternalConfigSelector) this.realChannel.configSelector.get();
    }

    public ManagedChannelImpl shutdown() {
        this.channelLogger.log(ChannelLogger.ChannelLogLevel.DEBUG, "shutdown() called");
        if (!this.shutdown.compareAndSet(false, true)) {
            return this;
        }
        this.syncContext.execute(new Runnable() {
            public void run() {
                ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Entering SHUTDOWN state");
                ManagedChannelImpl.this.channelStateManager.gotoState(ConnectivityState.SHUTDOWN);
            }
        });
        this.realChannel.shutdown();
        this.syncContext.execute(new Runnable() {
            public void run() {
                ManagedChannelImpl.this.cancelIdleTimer(true);
            }
        });
        return this;
    }

    public ManagedChannelImpl shutdownNow() {
        this.channelLogger.log(ChannelLogger.ChannelLogLevel.DEBUG, "shutdownNow() called");
        shutdown();
        this.realChannel.shutdownNow();
        this.syncContext.execute(new Runnable() {
            public void run() {
                if (!ManagedChannelImpl.this.shutdownNowed) {
                    boolean unused = ManagedChannelImpl.this.shutdownNowed = true;
                    ManagedChannelImpl.this.maybeShutdownNowSubchannels();
                }
            }
        });
        return this;
    }

    /* access modifiers changed from: package-private */
    public void panic(Throwable th) {
        if (!this.panicMode) {
            this.panicMode = true;
            cancelIdleTimer(true);
            shutdownNameResolverAndLoadBalancer(false);
            updateSubchannelPicker(new LoadBalancer.SubchannelPicker(th) {
                private final LoadBalancer.PickResult panicPickResult;
                final /* synthetic */ Throwable val$t;

                {
                    this.val$t = r3;
                    this.panicPickResult = LoadBalancer.PickResult.withDrop(Status.INTERNAL.withDescription("Panic! This is a bug!").withCause(r3));
                }

                public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
                    return this.panicPickResult;
                }

                public String toString() {
                    return MoreObjects.toStringHelper((Class<?>) AnonymousClass1PanicSubchannelPicker.class).add("panicPickResult", (Object) this.panicPickResult).toString();
                }
            });
            this.realChannel.updateConfigSelector((InternalConfigSelector) null);
            this.channelLogger.log(ChannelLogger.ChannelLogLevel.ERROR, "PANIC! Entering TRANSIENT_FAILURE");
            this.channelStateManager.gotoState(ConnectivityState.TRANSIENT_FAILURE);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isInPanicMode() {
        return this.panicMode;
    }

    /* access modifiers changed from: private */
    public void updateSubchannelPicker(LoadBalancer.SubchannelPicker subchannelPicker2) {
        this.subchannelPicker = subchannelPicker2;
        this.delayedTransport.reprocess(subchannelPicker2);
    }

    public boolean isShutdown() {
        return this.shutdown.get();
    }

    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.terminatedLatch.await(j, timeUnit);
    }

    public boolean isTerminated() {
        return this.terminated;
    }

    public <ReqT, RespT> ClientCall<ReqT, RespT> newCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions) {
        return this.interceptorChannel.newCall(methodDescriptor, callOptions);
    }

    public String authority() {
        return this.interceptorChannel.authority();
    }

    /* access modifiers changed from: private */
    public Executor getCallExecutor(CallOptions callOptions) {
        Executor executor2 = callOptions.getExecutor();
        return executor2 == null ? this.executor : executor2;
    }

    private class RealChannel extends Channel {
        /* access modifiers changed from: private */
        public final String authority;
        private final Channel clientCallImplChannel;
        /* access modifiers changed from: private */
        public final AtomicReference<InternalConfigSelector> configSelector;

        private RealChannel(String str) {
            this.configSelector = new AtomicReference<>(ManagedChannelImpl.INITIAL_PENDING_SELECTOR);
            this.clientCallImplChannel = new Channel() {
                public <RequestT, ResponseT> ClientCall<RequestT, ResponseT> newCall(MethodDescriptor<RequestT, ResponseT> methodDescriptor, CallOptions callOptions) {
                    return new ClientCallImpl(methodDescriptor, ManagedChannelImpl.this.getCallExecutor(callOptions), callOptions, ManagedChannelImpl.this.transportProvider, ManagedChannelImpl.this.terminated ? null : ManagedChannelImpl.this.transportFactory.getScheduledExecutorService(), ManagedChannelImpl.this.channelCallTracer, (InternalConfigSelector) null).setFullStreamDecompression(ManagedChannelImpl.this.fullStreamDecompression).setDecompressorRegistry(ManagedChannelImpl.this.decompressorRegistry).setCompressorRegistry(ManagedChannelImpl.this.compressorRegistry);
                }

                public String authority() {
                    return RealChannel.this.authority;
                }
            };
            this.authority = (String) Preconditions.checkNotNull(str, "authority");
        }

        public <ReqT, RespT> ClientCall<ReqT, RespT> newCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions) {
            if (this.configSelector.get() != ManagedChannelImpl.INITIAL_PENDING_SELECTOR) {
                return newClientCall(methodDescriptor, callOptions);
            }
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    ManagedChannelImpl.this.exitIdleMode();
                }
            });
            if (this.configSelector.get() != ManagedChannelImpl.INITIAL_PENDING_SELECTOR) {
                return newClientCall(methodDescriptor, callOptions);
            }
            if (ManagedChannelImpl.this.shutdown.get()) {
                return new ClientCall<ReqT, RespT>() {
                    public void cancel(@Nullable String str, @Nullable Throwable th) {
                    }

                    public void halfClose() {
                    }

                    public void request(int i) {
                    }

                    public void sendMessage(ReqT reqt) {
                    }

                    public void start(ClientCall.Listener<RespT> listener, Metadata metadata) {
                        listener.onClose(ManagedChannelImpl.SHUTDOWN_STATUS, new Metadata());
                    }
                };
            }
            final PendingCall pendingCall = new PendingCall(Context.current(), methodDescriptor, callOptions);
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    if (RealChannel.this.configSelector.get() == ManagedChannelImpl.INITIAL_PENDING_SELECTOR) {
                        if (ManagedChannelImpl.this.pendingCalls == null) {
                            Collection unused = ManagedChannelImpl.this.pendingCalls = new LinkedHashSet();
                            ManagedChannelImpl.this.inUseStateAggregator.updateObjectInUse(ManagedChannelImpl.this.pendingCallsInUseObject, true);
                        }
                        ManagedChannelImpl.this.pendingCalls.add(pendingCall);
                        return;
                    }
                    pendingCall.reprocess();
                }
            });
            return pendingCall;
        }

        /* access modifiers changed from: package-private */
        public void updateConfigSelector(@Nullable InternalConfigSelector internalConfigSelector) {
            InternalConfigSelector internalConfigSelector2 = this.configSelector.get();
            this.configSelector.set(internalConfigSelector);
            if (internalConfigSelector2 == ManagedChannelImpl.INITIAL_PENDING_SELECTOR && ManagedChannelImpl.this.pendingCalls != null) {
                for (PendingCall reprocess : ManagedChannelImpl.this.pendingCalls) {
                    reprocess.reprocess();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void onConfigError() {
            if (this.configSelector.get() == ManagedChannelImpl.INITIAL_PENDING_SELECTOR) {
                updateConfigSelector((InternalConfigSelector) null);
            }
        }

        /* access modifiers changed from: package-private */
        public void shutdown() {
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    if (ManagedChannelImpl.this.pendingCalls == null) {
                        if (RealChannel.this.configSelector.get() == ManagedChannelImpl.INITIAL_PENDING_SELECTOR) {
                            RealChannel.this.configSelector.set((Object) null);
                        }
                        ManagedChannelImpl.this.uncommittedRetriableStreamsRegistry.onShutdown(ManagedChannelImpl.SHUTDOWN_STATUS);
                    }
                }
            });
        }

        /* access modifiers changed from: package-private */
        public void shutdownNow() {
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    if (RealChannel.this.configSelector.get() == ManagedChannelImpl.INITIAL_PENDING_SELECTOR) {
                        RealChannel.this.configSelector.set((Object) null);
                    }
                    if (ManagedChannelImpl.this.pendingCalls != null) {
                        for (PendingCall cancel : ManagedChannelImpl.this.pendingCalls) {
                            cancel.cancel("Channel is forcefully shutdown", (Throwable) null);
                        }
                    }
                    ManagedChannelImpl.this.uncommittedRetriableStreamsRegistry.onShutdownNow(ManagedChannelImpl.SHUTDOWN_NOW_STATUS);
                }
            });
        }

        public String authority() {
            return this.authority;
        }

        private final class PendingCall<ReqT, RespT> extends DelayedClientCall<ReqT, RespT> {
            private final long callCreationTime;
            final CallOptions callOptions;
            final Context context;
            final MethodDescriptor<ReqT, RespT> method;

            PendingCall(Context context2, MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions2) {
                super(ManagedChannelImpl.this.getCallExecutor(callOptions2), ManagedChannelImpl.this.scheduledExecutor, callOptions2.getDeadline());
                this.context = context2;
                this.method = methodDescriptor;
                this.callOptions = callOptions2;
                this.callCreationTime = ManagedChannelImpl.this.ticker.nanoTime();
            }

            /* JADX INFO: finally extract failed */
            /* access modifiers changed from: package-private */
            public void reprocess() {
                Context attach = this.context.attach();
                try {
                    ClientCall access$4300 = RealChannel.this.newClientCall(this.method, this.callOptions.withOption(ClientStreamTracer.NAME_RESOLUTION_DELAYED, Long.valueOf(ManagedChannelImpl.this.ticker.nanoTime() - this.callCreationTime)));
                    this.context.detach(attach);
                    final Runnable call = setCall(access$4300);
                    if (call == null) {
                        ManagedChannelImpl.this.syncContext.execute(new PendingCallRemoval());
                    } else {
                        ManagedChannelImpl.this.getCallExecutor(this.callOptions).execute(new Runnable() {
                            public void run() {
                                call.run();
                                ManagedChannelImpl.this.syncContext.execute(new PendingCallRemoval());
                            }
                        });
                    }
                } catch (Throwable th) {
                    this.context.detach(attach);
                    throw th;
                }
            }

            /* access modifiers changed from: protected */
            public void callCancelled() {
                super.callCancelled();
                ManagedChannelImpl.this.syncContext.execute(new PendingCallRemoval());
            }

            final class PendingCallRemoval implements Runnable {
                PendingCallRemoval() {
                }

                public void run() {
                    if (ManagedChannelImpl.this.pendingCalls != null) {
                        ManagedChannelImpl.this.pendingCalls.remove(PendingCall.this);
                        if (ManagedChannelImpl.this.pendingCalls.isEmpty()) {
                            ManagedChannelImpl.this.inUseStateAggregator.updateObjectInUse(ManagedChannelImpl.this.pendingCallsInUseObject, false);
                            Collection unused = ManagedChannelImpl.this.pendingCalls = null;
                            if (ManagedChannelImpl.this.shutdown.get()) {
                                ManagedChannelImpl.this.uncommittedRetriableStreamsRegistry.onShutdown(ManagedChannelImpl.SHUTDOWN_STATUS);
                            }
                        }
                    }
                }
            }
        }

        /* access modifiers changed from: private */
        public <ReqT, RespT> ClientCall<ReqT, RespT> newClientCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions) {
            InternalConfigSelector internalConfigSelector = this.configSelector.get();
            if (internalConfigSelector == null) {
                return this.clientCallImplChannel.newCall(methodDescriptor, callOptions);
            }
            if (!(internalConfigSelector instanceof ManagedChannelServiceConfig.ServiceConfigConvertedSelector)) {
                return new ConfigSelectingClientCall(internalConfigSelector, this.clientCallImplChannel, ManagedChannelImpl.this.executor, methodDescriptor, callOptions);
            }
            ManagedChannelServiceConfig.MethodInfo methodConfig = ((ManagedChannelServiceConfig.ServiceConfigConvertedSelector) internalConfigSelector).config.getMethodConfig(methodDescriptor);
            if (methodConfig != null) {
                callOptions = callOptions.withOption(ManagedChannelServiceConfig.MethodInfo.KEY, methodConfig);
            }
            return this.clientCallImplChannel.newCall(methodDescriptor, callOptions);
        }
    }

    static final class ConfigSelectingClientCall<ReqT, RespT> extends ForwardingClientCall<ReqT, RespT> {
        private final Executor callExecutor;
        private CallOptions callOptions;
        private final Channel channel;
        private final InternalConfigSelector configSelector;
        /* access modifiers changed from: private */
        public final Context context;
        private ClientCall<ReqT, RespT> delegate;
        private final MethodDescriptor<ReqT, RespT> method;

        ConfigSelectingClientCall(InternalConfigSelector internalConfigSelector, Channel channel2, Executor executor, MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions2) {
            this.configSelector = internalConfigSelector;
            this.channel = channel2;
            this.method = methodDescriptor;
            executor = callOptions2.getExecutor() != null ? callOptions2.getExecutor() : executor;
            this.callExecutor = executor;
            this.callOptions = callOptions2.withExecutor(executor);
            this.context = Context.current();
        }

        /* access modifiers changed from: protected */
        public ClientCall<ReqT, RespT> delegate() {
            return this.delegate;
        }

        public void start(ClientCall.Listener<RespT> listener, Metadata metadata) {
            InternalConfigSelector.Result selectConfig = this.configSelector.selectConfig(new PickSubchannelArgsImpl(this.method, metadata, this.callOptions));
            Status status = selectConfig.getStatus();
            if (!status.isOk()) {
                executeCloseObserverInContext(listener, GrpcUtil.replaceInappropriateControlPlaneStatus(status));
                this.delegate = ManagedChannelImpl.NOOP_CALL;
                return;
            }
            ClientInterceptor interceptor = selectConfig.getInterceptor();
            ManagedChannelServiceConfig.MethodInfo methodConfig = ((ManagedChannelServiceConfig) selectConfig.getConfig()).getMethodConfig(this.method);
            if (methodConfig != null) {
                this.callOptions = this.callOptions.withOption(ManagedChannelServiceConfig.MethodInfo.KEY, methodConfig);
            }
            if (interceptor != null) {
                this.delegate = interceptor.interceptCall(this.method, this.callOptions, this.channel);
            } else {
                this.delegate = this.channel.newCall(this.method, this.callOptions);
            }
            this.delegate.start(listener, metadata);
        }

        private void executeCloseObserverInContext(final ClientCall.Listener<RespT> listener, final Status status) {
            this.callExecutor.execute(new ContextRunnable() {
                public void runInContext() {
                    listener.onClose(status, new Metadata());
                }
            });
        }

        public void cancel(@Nullable String str, @Nullable Throwable th) {
            ClientCall<ReqT, RespT> clientCall = this.delegate;
            if (clientCall != null) {
                clientCall.cancel(str, th);
            }
        }
    }

    /* access modifiers changed from: private */
    public void maybeTerminateChannel() {
        if (!this.terminated && this.shutdown.get() && this.subchannels.isEmpty() && this.oobChannels.isEmpty()) {
            this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Terminated");
            this.channelz.removeRootChannel(this);
            this.executorPool.returnObject(this.executor);
            this.balancerRpcExecutorHolder.release();
            this.offloadExecutorHolder.release();
            this.transportFactory.close();
            this.terminated = true;
            this.terminatedLatch.countDown();
        }
    }

    /* access modifiers changed from: private */
    public void handleInternalSubchannelState(ConnectivityStateInfo connectivityStateInfo) {
        if (connectivityStateInfo.getState() == ConnectivityState.TRANSIENT_FAILURE || connectivityStateInfo.getState() == ConnectivityState.IDLE) {
            refreshNameResolution();
        }
    }

    public ConnectivityState getState(boolean z) {
        ConnectivityState state = this.channelStateManager.getState();
        if (z && state == ConnectivityState.IDLE) {
            this.syncContext.execute(new Runnable() {
                public void run() {
                    ManagedChannelImpl.this.exitIdleMode();
                    if (ManagedChannelImpl.this.subchannelPicker != null) {
                        ManagedChannelImpl.this.subchannelPicker.requestConnection();
                    }
                    if (ManagedChannelImpl.this.lbHelper != null) {
                        ManagedChannelImpl.this.lbHelper.lb.requestConnection();
                    }
                }
            });
        }
        return state;
    }

    public void notifyWhenStateChanged(final ConnectivityState connectivityState, final Runnable runnable) {
        this.syncContext.execute(new Runnable() {
            public void run() {
                ManagedChannelImpl.this.channelStateManager.notifyWhenStateChanged(runnable, ManagedChannelImpl.this.executor, connectivityState);
            }
        });
    }

    public void resetConnectBackoff() {
        this.syncContext.execute(new Runnable() {
            public void run() {
                if (!ManagedChannelImpl.this.shutdown.get()) {
                    if (ManagedChannelImpl.this.nameResolverStarted) {
                        ManagedChannelImpl.this.refreshNameResolution();
                    }
                    for (InternalSubchannel resetConnectBackoff : ManagedChannelImpl.this.subchannels) {
                        resetConnectBackoff.resetConnectBackoff();
                    }
                    for (OobChannel resetConnectBackoff2 : ManagedChannelImpl.this.oobChannels) {
                        resetConnectBackoff2.resetConnectBackoff();
                    }
                }
            }
        });
    }

    public void enterIdle() {
        this.syncContext.execute(new Runnable() {
            public void run() {
                if (!ManagedChannelImpl.this.shutdown.get() && ManagedChannelImpl.this.lbHelper != null) {
                    ManagedChannelImpl.this.cancelIdleTimer(false);
                    ManagedChannelImpl.this.enterIdleMode();
                }
            }
        });
    }

    private final class UncommittedRetriableStreamsRegistry {
        final Object lock;
        Status shutdownStatus;
        Collection<ClientStream> uncommittedRetriableStreams;

        private UncommittedRetriableStreamsRegistry() {
            this.lock = new Object();
            this.uncommittedRetriableStreams = new HashSet();
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0014, code lost:
            io.grpc.internal.ManagedChannelImpl.access$1400(r2.this$0).shutdown(r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
            if (r1 == false) goto L_?;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onShutdown(io.grpc.Status r3) {
            /*
                r2 = this;
                java.lang.Object r0 = r2.lock
                monitor-enter(r0)
                io.grpc.Status r1 = r2.shutdownStatus     // Catch:{ all -> 0x001e }
                if (r1 == 0) goto L_0x0009
                monitor-exit(r0)     // Catch:{ all -> 0x001e }
                return
            L_0x0009:
                r2.shutdownStatus = r3     // Catch:{ all -> 0x001e }
                java.util.Collection<io.grpc.internal.ClientStream> r1 = r2.uncommittedRetriableStreams     // Catch:{ all -> 0x001e }
                boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x001e }
                monitor-exit(r0)     // Catch:{ all -> 0x001e }
                if (r1 == 0) goto L_0x001d
                io.grpc.internal.ManagedChannelImpl r0 = io.grpc.internal.ManagedChannelImpl.this
                io.grpc.internal.DelayedClientTransport r0 = r0.delayedTransport
                r0.shutdown(r3)
            L_0x001d:
                return
            L_0x001e:
                r3 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x001e }
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.ManagedChannelImpl.UncommittedRetriableStreamsRegistry.onShutdown(io.grpc.Status):void");
        }

        /* access modifiers changed from: package-private */
        public void onShutdownNow(Status status) {
            ArrayList<ClientStream> arrayList;
            onShutdown(status);
            synchronized (this.lock) {
                arrayList = new ArrayList<>(this.uncommittedRetriableStreams);
            }
            for (ClientStream cancel : arrayList) {
                cancel.cancel(status);
            }
            ManagedChannelImpl.this.delayedTransport.shutdownNow(status);
        }

        /* access modifiers changed from: package-private */
        @Nullable
        public Status add(RetriableStream<?> retriableStream) {
            synchronized (this.lock) {
                Status status = this.shutdownStatus;
                if (status != null) {
                    return status;
                }
                this.uncommittedRetriableStreams.add(retriableStream);
                return null;
            }
        }

        /* access modifiers changed from: package-private */
        public void remove(RetriableStream<?> retriableStream) {
            Status status;
            synchronized (this.lock) {
                this.uncommittedRetriableStreams.remove(retriableStream);
                if (this.uncommittedRetriableStreams.isEmpty()) {
                    status = this.shutdownStatus;
                    this.uncommittedRetriableStreams = new HashSet();
                } else {
                    status = null;
                }
            }
            if (status != null) {
                ManagedChannelImpl.this.delayedTransport.shutdown(status);
            }
        }
    }

    private final class LbHelperImpl extends LoadBalancer.Helper {
        AutoConfiguredLoadBalancerFactory.AutoConfiguredLoadBalancer lb;

        private LbHelperImpl() {
        }

        public AbstractSubchannel createSubchannel(LoadBalancer.CreateSubchannelArgs createSubchannelArgs) {
            ManagedChannelImpl.this.syncContext.throwIfNotInThisSynchronizationContext();
            Preconditions.checkState(!ManagedChannelImpl.this.terminating, "Channel is being terminated");
            return new SubchannelImpl(createSubchannelArgs);
        }

        public void updateBalancingState(final ConnectivityState connectivityState, final LoadBalancer.SubchannelPicker subchannelPicker) {
            ManagedChannelImpl.this.syncContext.throwIfNotInThisSynchronizationContext();
            Preconditions.checkNotNull(connectivityState, "newState");
            Preconditions.checkNotNull(subchannelPicker, "newPicker");
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    LbHelperImpl lbHelperImpl = LbHelperImpl.this;
                    if (lbHelperImpl == ManagedChannelImpl.this.lbHelper) {
                        ManagedChannelImpl.this.updateSubchannelPicker(subchannelPicker);
                        if (connectivityState != ConnectivityState.SHUTDOWN) {
                            ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Entering {0} state with picker: {1}", connectivityState, subchannelPicker);
                            ManagedChannelImpl.this.channelStateManager.gotoState(connectivityState);
                        }
                    }
                }
            });
        }

        public void refreshNameResolution() {
            ManagedChannelImpl.this.syncContext.throwIfNotInThisSynchronizationContext();
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    ManagedChannelImpl.this.refreshNameResolution();
                }
            });
        }

        public ManagedChannel createOobChannel(EquivalentAddressGroup equivalentAddressGroup, String str) {
            return createOobChannel((List<EquivalentAddressGroup>) Collections.singletonList(equivalentAddressGroup), str);
        }

        public ManagedChannel createOobChannel(List<EquivalentAddressGroup> list, String str) {
            List<EquivalentAddressGroup> list2 = list;
            Preconditions.checkState(!ManagedChannelImpl.this.terminated, "Channel is terminated");
            long currentTimeNanos = ManagedChannelImpl.this.timeProvider.currentTimeNanos();
            InternalLogId allocate = InternalLogId.allocate("OobChannel", (String) null);
            InternalLogId allocate2 = InternalLogId.allocate("Subchannel-OOB", str);
            ChannelTracer channelTracer = new ChannelTracer(allocate, ManagedChannelImpl.this.maxTraceEvents, currentTimeNanos, "OobChannel for " + list2);
            OobChannel oobChannel = new OobChannel(str, ManagedChannelImpl.this.balancerRpcExecutorPool, ManagedChannelImpl.this.oobTransportFactory.getScheduledExecutorService(), ManagedChannelImpl.this.syncContext, ManagedChannelImpl.this.callTracerFactory.create(), channelTracer, ManagedChannelImpl.this.channelz, ManagedChannelImpl.this.timeProvider);
            ManagedChannelImpl.this.channelTracer.reportEvent(new InternalChannelz.ChannelTrace.Event.Builder().setDescription("Child OobChannel created").setSeverity(InternalChannelz.ChannelTrace.Event.Severity.CT_INFO).setTimestampNanos(currentTimeNanos).setChannelRef(oobChannel).build());
            final OobChannel oobChannel2 = oobChannel;
            ChannelTracer channelTracer2 = new ChannelTracer(allocate2, ManagedChannelImpl.this.maxTraceEvents, currentTimeNanos, "Subchannel for " + list2);
            ChannelLoggerImpl channelLoggerImpl = new ChannelLoggerImpl(channelTracer2, ManagedChannelImpl.this.timeProvider);
            String access$5900 = ManagedChannelImpl.this.userAgent;
            BackoffPolicy.Provider access$6000 = ManagedChannelImpl.this.backoffPolicyProvider;
            ClientTransportFactory access$5400 = ManagedChannelImpl.this.oobTransportFactory;
            ScheduledExecutorService scheduledExecutorService = ManagedChannelImpl.this.oobTransportFactory.getScheduledExecutorService();
            Supplier access$6100 = ManagedChannelImpl.this.stopwatchSupplier;
            SynchronizationContext synchronizationContext = ManagedChannelImpl.this.syncContext;
            AnonymousClass1ManagedOobChannelCallback r3 = new InternalSubchannel.Callback() {
                /* access modifiers changed from: package-private */
                public void onTerminated(InternalSubchannel internalSubchannel) {
                    ManagedChannelImpl.this.oobChannels.remove(oobChannel2);
                    ManagedChannelImpl.this.channelz.removeSubchannel(internalSubchannel);
                    oobChannel2.handleSubchannelTerminated();
                    ManagedChannelImpl.this.maybeTerminateChannel();
                }

                /* access modifiers changed from: package-private */
                public void onStateChange(InternalSubchannel internalSubchannel, ConnectivityStateInfo connectivityStateInfo) {
                    ManagedChannelImpl.this.handleInternalSubchannelState(connectivityStateInfo);
                    oobChannel2.handleSubchannelStateChange(connectivityStateInfo);
                }
            };
            InternalChannelz access$5600 = ManagedChannelImpl.this.channelz;
            CallTracer create = ManagedChannelImpl.this.callTracerFactory.create();
            ChannelTracer channelTracer3 = channelTracer;
            InternalSubchannel internalSubchannel = r1;
            InternalSubchannel internalSubchannel2 = new InternalSubchannel(list, str, access$5900, access$6000, access$5400, scheduledExecutorService, access$6100, synchronizationContext, r3, access$5600, create, channelTracer2, allocate2, channelLoggerImpl, ManagedChannelImpl.this.transportFilters);
            ChannelTracer channelTracer4 = channelTracer3;
            channelTracer4.reportEvent(new InternalChannelz.ChannelTrace.Event.Builder().setDescription("Child Subchannel created").setSeverity(InternalChannelz.ChannelTrace.Event.Severity.CT_INFO).setTimestampNanos(currentTimeNanos).setSubchannelRef(internalSubchannel).build());
            final OobChannel oobChannel3 = oobChannel2;
            ManagedChannelImpl.this.channelz.addSubchannel(oobChannel3);
            ManagedChannelImpl.this.channelz.addSubchannel(internalSubchannel);
            oobChannel3.setSubchannel(internalSubchannel);
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    if (ManagedChannelImpl.this.terminating) {
                        oobChannel3.shutdown();
                    }
                    if (!ManagedChannelImpl.this.terminated) {
                        ManagedChannelImpl.this.oobChannels.add(oobChannel3);
                    }
                }
            });
            return oobChannel3;
        }

        @Deprecated
        public ManagedChannelBuilder<?> createResolvingOobChannelBuilder(String str) {
            return createResolvingOobChannelBuilder(str, new DefaultChannelCreds()).overrideAuthority(getAuthority());
        }

        public ManagedChannelBuilder<?> createResolvingOobChannelBuilder(String str, ChannelCredentials channelCredentials) {
            Preconditions.checkNotNull(channelCredentials, "channelCreds");
            Preconditions.checkState(!ManagedChannelImpl.this.terminated, "Channel is terminated");
            return ((AnonymousClass1ResolvingOobChannelBuilder) ((AnonymousClass1ResolvingOobChannelBuilder) ((AnonymousClass1ResolvingOobChannelBuilder) ((AnonymousClass1ResolvingOobChannelBuilder) new ForwardingChannelBuilder2<AnonymousClass1ResolvingOobChannelBuilder>(channelCredentials, str) {
                final ManagedChannelBuilder<?> delegate;
                final /* synthetic */ ChannelCredentials val$channelCreds;
                final /* synthetic */ String val$target;

                {
                    CallCredentials callCredentials;
                    final ClientTransportFactory clientTransportFactory;
                    this.val$channelCreds = r9;
                    this.val$target = r10;
                    if (r9 instanceof DefaultChannelCreds) {
                        clientTransportFactory = ManagedChannelImpl.this.originalTransportFactory;
                        callCredentials = null;
                    } else {
                        ClientTransportFactory.SwapChannelCredentialsResult swapChannelCredentials = ManagedChannelImpl.this.originalTransportFactory.swapChannelCredentials(r9);
                        if (swapChannelCredentials == null) {
                            this.delegate = Grpc.newChannelBuilder(r10, r9);
                            return;
                        }
                        ClientTransportFactory clientTransportFactory2 = swapChannelCredentials.transportFactory;
                        callCredentials = swapChannelCredentials.callCredentials;
                        clientTransportFactory = clientTransportFactory2;
                    }
                    this.delegate = new ManagedChannelImplBuilder(r10, r9, callCredentials, new ManagedChannelImplBuilder.ClientTransportFactoryBuilder(LbHelperImpl.this) {
                        public ClientTransportFactory buildClientTransportFactory() {
                            return clientTransportFactory;
                        }
                    }, new ManagedChannelImplBuilder.FixedPortProvider(ManagedChannelImpl.this.nameResolverArgs.getDefaultPort())).nameResolverRegistry(ManagedChannelImpl.this.nameResolverRegistry);
                }

                /* access modifiers changed from: protected */
                public ManagedChannelBuilder<?> delegate() {
                    return this.delegate;
                }
            }.executor(ManagedChannelImpl.this.executor)).offloadExecutor(ManagedChannelImpl.this.offloadExecutorHolder.getExecutor())).maxTraceEvents(ManagedChannelImpl.this.maxTraceEvents)).proxyDetector(ManagedChannelImpl.this.nameResolverArgs.getProxyDetector())).userAgent(ManagedChannelImpl.this.userAgent);
        }

        public ChannelCredentials getUnsafeChannelCredentials() {
            if (ManagedChannelImpl.this.originalChannelCreds == null) {
                return new DefaultChannelCreds();
            }
            return ManagedChannelImpl.this.originalChannelCreds;
        }

        public void updateOobChannelAddresses(ManagedChannel managedChannel, EquivalentAddressGroup equivalentAddressGroup) {
            updateOobChannelAddresses(managedChannel, (List<EquivalentAddressGroup>) Collections.singletonList(equivalentAddressGroup));
        }

        public void updateOobChannelAddresses(ManagedChannel managedChannel, List<EquivalentAddressGroup> list) {
            Preconditions.checkArgument(managedChannel instanceof OobChannel, "channel must have been returned from createOobChannel");
            ((OobChannel) managedChannel).updateAddresses(list);
        }

        public String getAuthority() {
            return ManagedChannelImpl.this.authority();
        }

        public SynchronizationContext getSynchronizationContext() {
            return ManagedChannelImpl.this.syncContext;
        }

        public ScheduledExecutorService getScheduledExecutorService() {
            return ManagedChannelImpl.this.scheduledExecutor;
        }

        public ChannelLogger getChannelLogger() {
            return ManagedChannelImpl.this.channelLogger;
        }

        public NameResolver.Args getNameResolverArgs() {
            return ManagedChannelImpl.this.nameResolverArgs;
        }

        public NameResolverRegistry getNameResolverRegistry() {
            return ManagedChannelImpl.this.nameResolverRegistry;
        }

        final class DefaultChannelCreds extends ChannelCredentials {
            public ChannelCredentials withoutBearerTokens() {
                return this;
            }

            DefaultChannelCreds() {
            }
        }
    }

    final class NameResolverListener extends NameResolver.Listener2 {
        final LbHelperImpl helper;
        final NameResolver resolver;

        NameResolverListener(LbHelperImpl lbHelperImpl, NameResolver nameResolver) {
            this.helper = (LbHelperImpl) Preconditions.checkNotNull(lbHelperImpl, "helperImpl");
            this.resolver = (NameResolver) Preconditions.checkNotNull(nameResolver, "resolver");
        }

        public void onResult(final NameResolver.ResolutionResult resolutionResult) {
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    ManagedChannelServiceConfig managedChannelServiceConfig;
                    if (ManagedChannelImpl.this.nameResolver == NameResolverListener.this.resolver) {
                        List<EquivalentAddressGroup> addresses = resolutionResult.getAddresses();
                        ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.DEBUG, "Resolved address: {0}, config={1}", addresses, resolutionResult.getAttributes());
                        if (ManagedChannelImpl.this.lastResolutionState != ResolutionState.SUCCESS) {
                            ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Address resolved: {0}", addresses);
                            ResolutionState unused = ManagedChannelImpl.this.lastResolutionState = ResolutionState.SUCCESS;
                        }
                        NameResolver.ConfigOrError serviceConfig = resolutionResult.getServiceConfig();
                        RetryingNameResolver.ResolutionResultListener resolutionResultListener = (RetryingNameResolver.ResolutionResultListener) resolutionResult.getAttributes().get(RetryingNameResolver.RESOLUTION_RESULT_LISTENER_KEY);
                        InternalConfigSelector internalConfigSelector = (InternalConfigSelector) resolutionResult.getAttributes().get(InternalConfigSelector.KEY);
                        ManagedChannelServiceConfig managedChannelServiceConfig2 = (serviceConfig == null || serviceConfig.getConfig() == null) ? null : (ManagedChannelServiceConfig) serviceConfig.getConfig();
                        Status error = serviceConfig != null ? serviceConfig.getError() : null;
                        if (!ManagedChannelImpl.this.lookUpServiceConfig) {
                            if (managedChannelServiceConfig2 != null) {
                                ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Service config from name resolver discarded by channel settings");
                            }
                            managedChannelServiceConfig = ManagedChannelImpl.this.defaultServiceConfig == null ? ManagedChannelImpl.EMPTY_SERVICE_CONFIG : ManagedChannelImpl.this.defaultServiceConfig;
                            if (internalConfigSelector != null) {
                                ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Config selector from name resolver discarded by channel settings");
                            }
                            ManagedChannelImpl.this.realChannel.updateConfigSelector(managedChannelServiceConfig.getDefaultConfigSelector());
                        } else {
                            if (managedChannelServiceConfig2 != null) {
                                if (internalConfigSelector != null) {
                                    ManagedChannelImpl.this.realChannel.updateConfigSelector(internalConfigSelector);
                                    if (managedChannelServiceConfig2.getDefaultConfigSelector() != null) {
                                        ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.DEBUG, "Method configs in service config will be discarded due to presence ofconfig-selector");
                                    }
                                } else {
                                    ManagedChannelImpl.this.realChannel.updateConfigSelector(managedChannelServiceConfig2.getDefaultConfigSelector());
                                }
                            } else if (ManagedChannelImpl.this.defaultServiceConfig != null) {
                                managedChannelServiceConfig2 = ManagedChannelImpl.this.defaultServiceConfig;
                                ManagedChannelImpl.this.realChannel.updateConfigSelector(managedChannelServiceConfig2.getDefaultConfigSelector());
                                ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Received no service config, using default service config");
                            } else if (error == null) {
                                managedChannelServiceConfig2 = ManagedChannelImpl.EMPTY_SERVICE_CONFIG;
                                ManagedChannelImpl.this.realChannel.updateConfigSelector((InternalConfigSelector) null);
                            } else if (!ManagedChannelImpl.this.serviceConfigUpdated) {
                                ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Fallback to error due to invalid first service config without default config");
                                NameResolverListener.this.onError(serviceConfig.getError());
                                if (resolutionResultListener != null) {
                                    resolutionResultListener.resolutionAttempted(serviceConfig.getError());
                                    return;
                                }
                                return;
                            } else {
                                managedChannelServiceConfig2 = ManagedChannelImpl.this.lastServiceConfig;
                            }
                            if (!managedChannelServiceConfig2.equals(ManagedChannelImpl.this.lastServiceConfig)) {
                                ChannelLogger access$2800 = ManagedChannelImpl.this.channelLogger;
                                ChannelLogger.ChannelLogLevel channelLogLevel = ChannelLogger.ChannelLogLevel.INFO;
                                Object[] objArr = new Object[1];
                                objArr[0] = managedChannelServiceConfig2 == ManagedChannelImpl.EMPTY_SERVICE_CONFIG ? " to empty" : "";
                                access$2800.log(channelLogLevel, "Service config changed{0}", objArr);
                                ManagedChannelServiceConfig unused2 = ManagedChannelImpl.this.lastServiceConfig = managedChannelServiceConfig2;
                                ManagedChannelImpl.this.transportProvider.throttle = managedChannelServiceConfig2.getRetryThrottling();
                            }
                            try {
                                boolean unused3 = ManagedChannelImpl.this.serviceConfigUpdated = true;
                            } catch (RuntimeException e) {
                                ManagedChannelImpl.logger.log(Level.WARNING, "[" + ManagedChannelImpl.this.getLogId() + "] Unexpected exception from parsing service config", e);
                            }
                            managedChannelServiceConfig = managedChannelServiceConfig2;
                        }
                        Attributes attributes = resolutionResult.getAttributes();
                        if (NameResolverListener.this.helper == ManagedChannelImpl.this.lbHelper) {
                            Attributes.Builder discard = attributes.toBuilder().discard(InternalConfigSelector.KEY);
                            Map<String, ?> healthCheckingConfig = managedChannelServiceConfig.getHealthCheckingConfig();
                            if (healthCheckingConfig != null) {
                                discard.set(LoadBalancer.ATTR_HEALTH_CHECKING_CONFIG, healthCheckingConfig).build();
                            }
                            Status tryAcceptResolvedAddresses = NameResolverListener.this.helper.lb.tryAcceptResolvedAddresses(LoadBalancer.ResolvedAddresses.newBuilder().setAddresses(addresses).setAttributes(discard.build()).setLoadBalancingPolicyConfig(managedChannelServiceConfig.getLoadBalancingConfig()).build());
                            if (resolutionResultListener != null) {
                                resolutionResultListener.resolutionAttempted(tryAcceptResolvedAddresses);
                            }
                        }
                    }
                }
            });
        }

        public void onError(final Status status) {
            Preconditions.checkArgument(!status.isOk(), "the error status must not be OK");
            ManagedChannelImpl.this.syncContext.execute(new Runnable() {
                public void run() {
                    NameResolverListener.this.handleErrorInSyncContext(status);
                }
            });
        }

        /* access modifiers changed from: private */
        public void handleErrorInSyncContext(Status status) {
            ManagedChannelImpl.logger.log(Level.WARNING, "[{0}] Failed to resolve name. status={1}", new Object[]{ManagedChannelImpl.this.getLogId(), status});
            ManagedChannelImpl.this.realChannel.onConfigError();
            if (ManagedChannelImpl.this.lastResolutionState != ResolutionState.ERROR) {
                ManagedChannelImpl.this.channelLogger.log(ChannelLogger.ChannelLogLevel.WARNING, "Failed to resolve name: {0}", status);
                ResolutionState unused = ManagedChannelImpl.this.lastResolutionState = ResolutionState.ERROR;
            }
            if (this.helper == ManagedChannelImpl.this.lbHelper) {
                this.helper.lb.handleNameResolutionError(status);
            }
        }
    }

    private final class SubchannelImpl extends AbstractSubchannel {
        List<EquivalentAddressGroup> addressGroups;
        final LoadBalancer.CreateSubchannelArgs args;
        SynchronizationContext.ScheduledHandle delayedShutdownTask;
        boolean shutdown;
        boolean started;
        InternalSubchannel subchannel;
        final InternalLogId subchannelLogId;
        final ChannelLoggerImpl subchannelLogger;
        final ChannelTracer subchannelTracer;

        SubchannelImpl(LoadBalancer.CreateSubchannelArgs createSubchannelArgs) {
            Preconditions.checkNotNull(createSubchannelArgs, "args");
            this.addressGroups = createSubchannelArgs.getAddresses();
            if (ManagedChannelImpl.this.authorityOverride != null) {
                createSubchannelArgs = createSubchannelArgs.toBuilder().setAddresses(stripOverrideAuthorityAttributes(createSubchannelArgs.getAddresses())).build();
            }
            this.args = createSubchannelArgs;
            InternalLogId allocate = InternalLogId.allocate("Subchannel", ManagedChannelImpl.this.authority());
            this.subchannelLogId = allocate;
            ChannelTracer channelTracer = new ChannelTracer(allocate, ManagedChannelImpl.this.maxTraceEvents, ManagedChannelImpl.this.timeProvider.currentTimeNanos(), "Subchannel for " + createSubchannelArgs.getAddresses());
            this.subchannelTracer = channelTracer;
            this.subchannelLogger = new ChannelLoggerImpl(channelTracer, ManagedChannelImpl.this.timeProvider);
        }

        public void start(LoadBalancer.SubchannelStateListener subchannelStateListener) {
            ManagedChannelImpl.this.syncContext.throwIfNotInThisSynchronizationContext();
            Preconditions.checkState(!this.started, "already started");
            Preconditions.checkState(!this.shutdown, "already shutdown");
            Preconditions.checkState(!ManagedChannelImpl.this.terminating, "Channel is being terminated");
            this.started = true;
            List<EquivalentAddressGroup> addresses = this.args.getAddresses();
            String authority = ManagedChannelImpl.this.authority();
            String access$5900 = ManagedChannelImpl.this.userAgent;
            BackoffPolicy.Provider access$6000 = ManagedChannelImpl.this.backoffPolicyProvider;
            ClientTransportFactory access$2000 = ManagedChannelImpl.this.transportFactory;
            ScheduledExecutorService scheduledExecutorService = ManagedChannelImpl.this.transportFactory.getScheduledExecutorService();
            Supplier access$6100 = ManagedChannelImpl.this.stopwatchSupplier;
            SynchronizationContext synchronizationContext = ManagedChannelImpl.this.syncContext;
            final LoadBalancer.SubchannelStateListener subchannelStateListener2 = subchannelStateListener;
            AnonymousClass1ManagedInternalSubchannelCallback r13 = new InternalSubchannel.Callback() {
                /* access modifiers changed from: package-private */
                public void onTerminated(InternalSubchannel internalSubchannel) {
                    ManagedChannelImpl.this.subchannels.remove(internalSubchannel);
                    ManagedChannelImpl.this.channelz.removeSubchannel(internalSubchannel);
                    ManagedChannelImpl.this.maybeTerminateChannel();
                }

                /* access modifiers changed from: package-private */
                public void onStateChange(InternalSubchannel internalSubchannel, ConnectivityStateInfo connectivityStateInfo) {
                    Preconditions.checkState(subchannelStateListener2 != null, "listener is null");
                    subchannelStateListener2.onSubchannelState(connectivityStateInfo);
                }

                /* access modifiers changed from: package-private */
                public void onInUse(InternalSubchannel internalSubchannel) {
                    ManagedChannelImpl.this.inUseStateAggregator.updateObjectInUse(internalSubchannel, true);
                }

                /* access modifiers changed from: package-private */
                public void onNotInUse(InternalSubchannel internalSubchannel) {
                    ManagedChannelImpl.this.inUseStateAggregator.updateObjectInUse(internalSubchannel, false);
                }
            };
            InternalChannelz access$5600 = ManagedChannelImpl.this.channelz;
            CallTracer create = ManagedChannelImpl.this.callTracerFactory.create();
            ChannelTracer channelTracer = this.subchannelTracer;
            InternalLogId internalLogId = this.subchannelLogId;
            ChannelLoggerImpl channelLoggerImpl = this.subchannelLogger;
            InternalSubchannel internalSubchannel = new InternalSubchannel(addresses, authority, access$5900, access$6000, access$2000, scheduledExecutorService, access$6100, synchronizationContext, r13, access$5600, create, channelTracer, internalLogId, channelLoggerImpl, ManagedChannelImpl.this.transportFilters);
            ManagedChannelImpl.this.channelTracer.reportEvent(new InternalChannelz.ChannelTrace.Event.Builder().setDescription("Child Subchannel started").setSeverity(InternalChannelz.ChannelTrace.Event.Severity.CT_INFO).setTimestampNanos(ManagedChannelImpl.this.timeProvider.currentTimeNanos()).setSubchannelRef(internalSubchannel).build());
            this.subchannel = internalSubchannel;
            ManagedChannelImpl.this.channelz.addSubchannel(internalSubchannel);
            ManagedChannelImpl.this.subchannels.add(internalSubchannel);
        }

        /* access modifiers changed from: package-private */
        public InternalInstrumented<InternalChannelz.ChannelStats> getInstrumentedInternalSubchannel() {
            Preconditions.checkState(this.started, "not started");
            return this.subchannel;
        }

        public void shutdown() {
            SynchronizationContext.ScheduledHandle scheduledHandle;
            ManagedChannelImpl.this.syncContext.throwIfNotInThisSynchronizationContext();
            if (this.subchannel == null) {
                this.shutdown = true;
                return;
            }
            if (!this.shutdown) {
                this.shutdown = true;
            } else if (ManagedChannelImpl.this.terminating && (scheduledHandle = this.delayedShutdownTask) != null) {
                scheduledHandle.cancel();
                this.delayedShutdownTask = null;
            } else {
                return;
            }
            if (!ManagedChannelImpl.this.terminating) {
                this.delayedShutdownTask = ManagedChannelImpl.this.syncContext.schedule(new LogExceptionRunnable(new Runnable() {
                    public void run() {
                        SubchannelImpl.this.subchannel.shutdown(ManagedChannelImpl.SUBCHANNEL_SHUTDOWN_STATUS);
                    }
                }), 5, TimeUnit.SECONDS, ManagedChannelImpl.this.transportFactory.getScheduledExecutorService());
            } else {
                this.subchannel.shutdown(ManagedChannelImpl.SHUTDOWN_STATUS);
            }
        }

        public void requestConnection() {
            ManagedChannelImpl.this.syncContext.throwIfNotInThisSynchronizationContext();
            Preconditions.checkState(this.started, "not started");
            this.subchannel.obtainActiveTransport();
        }

        public List<EquivalentAddressGroup> getAllAddresses() {
            ManagedChannelImpl.this.syncContext.throwIfNotInThisSynchronizationContext();
            Preconditions.checkState(this.started, "not started");
            return this.addressGroups;
        }

        public Attributes getAttributes() {
            return this.args.getAttributes();
        }

        public String toString() {
            return this.subchannelLogId.toString();
        }

        public Channel asChannel() {
            Preconditions.checkState(this.started, "not started");
            return new SubchannelChannel(this.subchannel, ManagedChannelImpl.this.balancerRpcExecutorHolder.getExecutor(), ManagedChannelImpl.this.transportFactory.getScheduledExecutorService(), ManagedChannelImpl.this.callTracerFactory.create(), new AtomicReference((Object) null));
        }

        public Object getInternalSubchannel() {
            Preconditions.checkState(this.started, "Subchannel is not started");
            return this.subchannel;
        }

        public ChannelLogger getChannelLogger() {
            return this.subchannelLogger;
        }

        public void updateAddresses(List<EquivalentAddressGroup> list) {
            ManagedChannelImpl.this.syncContext.throwIfNotInThisSynchronizationContext();
            this.addressGroups = list;
            if (ManagedChannelImpl.this.authorityOverride != null) {
                list = stripOverrideAuthorityAttributes(list);
            }
            this.subchannel.updateAddresses(list);
        }

        private List<EquivalentAddressGroup> stripOverrideAuthorityAttributes(List<EquivalentAddressGroup> list) {
            ArrayList arrayList = new ArrayList();
            for (EquivalentAddressGroup next : list) {
                arrayList.add(new EquivalentAddressGroup(next.getAddresses(), next.getAttributes().toBuilder().discard(EquivalentAddressGroup.ATTR_AUTHORITY_OVERRIDE).build()));
            }
            return Collections.unmodifiableList(arrayList);
        }
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("logId", this.logId.getId()).add("target", (Object) this.target).toString();
    }

    private final class DelayedTransportListener implements ManagedClientTransport.Listener {
        public Attributes filterTransport(Attributes attributes) {
            return attributes;
        }

        public void transportReady() {
        }

        private DelayedTransportListener() {
        }

        public void transportShutdown(Status status) {
            Preconditions.checkState(ManagedChannelImpl.this.shutdown.get(), "Channel must have been shut down");
        }

        public void transportInUse(boolean z) {
            ManagedChannelImpl.this.inUseStateAggregator.updateObjectInUse(ManagedChannelImpl.this.delayedTransport, z);
        }

        public void transportTerminated() {
            Preconditions.checkState(ManagedChannelImpl.this.shutdown.get(), "Channel must have been shut down");
            boolean unused = ManagedChannelImpl.this.terminating = true;
            ManagedChannelImpl.this.shutdownNameResolverAndLoadBalancer(false);
            ManagedChannelImpl.this.maybeShutdownNowSubchannels();
            ManagedChannelImpl.this.maybeTerminateChannel();
        }
    }

    private final class IdleModeStateAggregator extends InUseStateAggregator<Object> {
        private IdleModeStateAggregator() {
        }

        /* access modifiers changed from: protected */
        public void handleInUse() {
            ManagedChannelImpl.this.exitIdleMode();
        }

        /* access modifiers changed from: protected */
        public void handleNotInUse() {
            if (!ManagedChannelImpl.this.shutdown.get()) {
                ManagedChannelImpl.this.rescheduleIdleTimer();
            }
        }
    }

    static final class ExecutorHolder implements Executor {
        private Executor executor;
        private final ObjectPool<? extends Executor> pool;

        ExecutorHolder(ObjectPool<? extends Executor> objectPool) {
            this.pool = (ObjectPool) Preconditions.checkNotNull(objectPool, "executorPool");
        }

        /* access modifiers changed from: package-private */
        public synchronized Executor getExecutor() {
            if (this.executor == null) {
                this.executor = (Executor) Preconditions.checkNotNull((Executor) this.pool.getObject(), "%s.getObject()", (Object) this.executor);
            }
            return this.executor;
        }

        /* access modifiers changed from: package-private */
        public synchronized void release() {
            Executor executor2 = this.executor;
            if (executor2 != null) {
                this.executor = (Executor) this.pool.returnObject(executor2);
            }
        }

        public void execute(Runnable runnable) {
            getExecutor().execute(runnable);
        }
    }

    private static final class RestrictedScheduledExecutor implements ScheduledExecutorService {
        final ScheduledExecutorService delegate;

        private RestrictedScheduledExecutor(ScheduledExecutorService scheduledExecutorService) {
            this.delegate = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "delegate");
        }

        public <V> ScheduledFuture<V> schedule(Callable<V> callable, long j, TimeUnit timeUnit) {
            return this.delegate.schedule(callable, j, timeUnit);
        }

        public ScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
            return this.delegate.schedule(runnable, j, timeUnit);
        }

        public ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
            return this.delegate.scheduleAtFixedRate(runnable, j, j2, timeUnit);
        }

        public ScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
            return this.delegate.scheduleWithFixedDelay(runnable, j, j2, timeUnit);
        }

        public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
            return this.delegate.awaitTermination(j, timeUnit);
        }

        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) throws InterruptedException {
            return this.delegate.invokeAll(collection);
        }

        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException {
            return this.delegate.invokeAll(collection, j, timeUnit);
        }

        public <T> T invokeAny(Collection<? extends Callable<T>> collection) throws InterruptedException, ExecutionException {
            return this.delegate.invokeAny(collection);
        }

        public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
            return this.delegate.invokeAny(collection, j, timeUnit);
        }

        public boolean isShutdown() {
            return this.delegate.isShutdown();
        }

        public boolean isTerminated() {
            return this.delegate.isTerminated();
        }

        public void shutdown() {
            throw new UnsupportedOperationException("Restricted: shutdown() is not allowed");
        }

        public List<Runnable> shutdownNow() {
            throw new UnsupportedOperationException("Restricted: shutdownNow() is not allowed");
        }

        public <T> Future<T> submit(Callable<T> callable) {
            return this.delegate.submit(callable);
        }

        public Future<?> submit(Runnable runnable) {
            return this.delegate.submit(runnable);
        }

        public <T> Future<T> submit(Runnable runnable, T t) {
            return this.delegate.submit(runnable, t);
        }

        public void execute(Runnable runnable) {
            this.delegate.execute(runnable);
        }
    }
}
