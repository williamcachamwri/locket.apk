package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.ChannelLogger;
import io.grpc.ClientStreamTracer;
import io.grpc.ClientTransportFilter;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.EquivalentAddressGroup;
import io.grpc.HttpConnectProxiedSocketAddress;
import io.grpc.InternalChannelz;
import io.grpc.InternalInstrumented;
import io.grpc.InternalLogId;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.SynchronizationContext;
import io.grpc.internal.BackoffPolicy;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.ClientTransportFactory;
import io.grpc.internal.ManagedClientTransport;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

final class InternalSubchannel implements InternalInstrumented<InternalChannelz.ChannelStats>, TransportProvider {
    /* access modifiers changed from: private */
    @Nullable
    public volatile ManagedClientTransport activeTransport;
    /* access modifiers changed from: private */
    public volatile List<EquivalentAddressGroup> addressGroups;
    /* access modifiers changed from: private */
    public final Index addressIndex;
    private final String authority;
    private final BackoffPolicy.Provider backoffPolicyProvider;
    /* access modifiers changed from: private */
    public final Callback callback;
    /* access modifiers changed from: private */
    public final CallTracer callsTracer;
    /* access modifiers changed from: private */
    public final ChannelLogger channelLogger;
    /* access modifiers changed from: private */
    public final ChannelTracer channelTracer;
    /* access modifiers changed from: private */
    public final InternalChannelz channelz;
    private final Stopwatch connectingTimer;
    /* access modifiers changed from: private */
    public final InUseStateAggregator<ConnectionClientTransport> inUseStateAggregator = new InUseStateAggregator<ConnectionClientTransport>() {
        /* access modifiers changed from: protected */
        public void handleInUse() {
            InternalSubchannel.this.callback.onInUse(InternalSubchannel.this);
        }

        /* access modifiers changed from: protected */
        public void handleNotInUse() {
            InternalSubchannel.this.callback.onNotInUse(InternalSubchannel.this);
        }
    };
    private final InternalLogId logId;
    /* access modifiers changed from: private */
    @Nullable
    public ConnectionClientTransport pendingTransport;
    /* access modifiers changed from: private */
    public BackoffPolicy reconnectPolicy;
    /* access modifiers changed from: private */
    @Nullable
    public SynchronizationContext.ScheduledHandle reconnectTask;
    /* access modifiers changed from: private */
    public final ScheduledExecutorService scheduledExecutor;
    /* access modifiers changed from: private */
    @Nullable
    public SynchronizationContext.ScheduledHandle shutdownDueToUpdateTask;
    /* access modifiers changed from: private */
    @Nullable
    public ManagedClientTransport shutdownDueToUpdateTransport;
    /* access modifiers changed from: private */
    public Status shutdownReason;
    /* access modifiers changed from: private */
    public volatile ConnectivityStateInfo state = ConnectivityStateInfo.forNonError(ConnectivityState.IDLE);
    /* access modifiers changed from: private */
    public final SynchronizationContext syncContext;
    private final ClientTransportFactory transportFactory;
    /* access modifiers changed from: private */
    public final List<ClientTransportFilter> transportFilters;
    /* access modifiers changed from: private */
    public final Collection<ConnectionClientTransport> transports = new ArrayList();
    private final String userAgent;

    InternalSubchannel(List<EquivalentAddressGroup> list, String str, String str2, BackoffPolicy.Provider provider, ClientTransportFactory clientTransportFactory, ScheduledExecutorService scheduledExecutorService, Supplier<Stopwatch> supplier, SynchronizationContext synchronizationContext, Callback callback2, InternalChannelz internalChannelz, CallTracer callTracer, ChannelTracer channelTracer2, InternalLogId internalLogId, ChannelLogger channelLogger2, List<ClientTransportFilter> list2) {
        List<EquivalentAddressGroup> list3 = list;
        Preconditions.checkNotNull(list, "addressGroups");
        Preconditions.checkArgument(!list.isEmpty(), "addressGroups is empty");
        checkListHasNoNulls(list, "addressGroups contains null entry");
        List<EquivalentAddressGroup> unmodifiableList = Collections.unmodifiableList(new ArrayList(list));
        this.addressGroups = unmodifiableList;
        this.addressIndex = new Index(unmodifiableList);
        this.authority = str;
        this.userAgent = str2;
        this.backoffPolicyProvider = provider;
        this.transportFactory = clientTransportFactory;
        this.scheduledExecutor = scheduledExecutorService;
        this.connectingTimer = supplier.get();
        this.syncContext = synchronizationContext;
        this.callback = callback2;
        this.channelz = internalChannelz;
        this.callsTracer = callTracer;
        this.channelTracer = (ChannelTracer) Preconditions.checkNotNull(channelTracer2, "channelTracer");
        this.logId = (InternalLogId) Preconditions.checkNotNull(internalLogId, "logId");
        this.channelLogger = (ChannelLogger) Preconditions.checkNotNull(channelLogger2, "channelLogger");
        this.transportFilters = list2;
    }

    /* access modifiers changed from: package-private */
    public ChannelLogger getChannelLogger() {
        return this.channelLogger;
    }

    public ClientTransport obtainActiveTransport() {
        ManagedClientTransport managedClientTransport = this.activeTransport;
        if (managedClientTransport != null) {
            return managedClientTransport;
        }
        this.syncContext.execute(new Runnable() {
            public void run() {
                if (InternalSubchannel.this.state.getState() == ConnectivityState.IDLE) {
                    InternalSubchannel.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "CONNECTING as requested");
                    InternalSubchannel.this.gotoNonErrorState(ConnectivityState.CONNECTING);
                    InternalSubchannel.this.startNewTransport();
                }
            }
        });
        return null;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public ClientTransport getTransport() {
        return this.activeTransport;
    }

    /* access modifiers changed from: package-private */
    public String getAuthority() {
        return this.authority;
    }

    /* access modifiers changed from: private */
    public void startNewTransport() {
        SocketAddress socketAddress;
        HttpConnectProxiedSocketAddress httpConnectProxiedSocketAddress;
        this.syncContext.throwIfNotInThisSynchronizationContext();
        Preconditions.checkState(this.reconnectTask == null, "Should have no reconnectTask scheduled");
        if (this.addressIndex.isAtBeginning()) {
            this.connectingTimer.reset().start();
        }
        SocketAddress currentAddress = this.addressIndex.getCurrentAddress();
        if (currentAddress instanceof HttpConnectProxiedSocketAddress) {
            httpConnectProxiedSocketAddress = (HttpConnectProxiedSocketAddress) currentAddress;
            socketAddress = httpConnectProxiedSocketAddress.getTargetAddress();
        } else {
            socketAddress = currentAddress;
            httpConnectProxiedSocketAddress = null;
        }
        Attributes currentEagAttributes = this.addressIndex.getCurrentEagAttributes();
        String str = (String) currentEagAttributes.get(EquivalentAddressGroup.ATTR_AUTHORITY_OVERRIDE);
        ClientTransportFactory.ClientTransportOptions clientTransportOptions = new ClientTransportFactory.ClientTransportOptions();
        if (str == null) {
            str = this.authority;
        }
        ClientTransportFactory.ClientTransportOptions httpConnectProxiedSocketAddress2 = clientTransportOptions.setAuthority(str).setEagAttributes(currentEagAttributes).setUserAgent(this.userAgent).setHttpConnectProxiedSocketAddress(httpConnectProxiedSocketAddress);
        TransportLogger transportLogger = new TransportLogger();
        transportLogger.logId = getLogId();
        CallTracingTransport callTracingTransport = new CallTracingTransport(this.transportFactory.newClientTransport(socketAddress, httpConnectProxiedSocketAddress2, transportLogger), this.callsTracer);
        transportLogger.logId = callTracingTransport.getLogId();
        this.channelz.addClientSocket(callTracingTransport);
        this.pendingTransport = callTracingTransport;
        this.transports.add(callTracingTransport);
        Runnable start = callTracingTransport.start(new TransportListener(callTracingTransport));
        if (start != null) {
            this.syncContext.executeLater(start);
        }
        this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Started transport {0}", transportLogger.logId);
    }

    /* access modifiers changed from: private */
    public void scheduleBackoff(Status status) {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        gotoState(ConnectivityStateInfo.forTransientFailure(status));
        if (this.reconnectPolicy == null) {
            this.reconnectPolicy = this.backoffPolicyProvider.get();
        }
        long nextBackoffNanos = this.reconnectPolicy.nextBackoffNanos() - this.connectingTimer.elapsed(TimeUnit.NANOSECONDS);
        this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "TRANSIENT_FAILURE ({0}). Will reconnect after {1} ns", printShortStatus(status), Long.valueOf(nextBackoffNanos));
        Preconditions.checkState(this.reconnectTask == null, "previous reconnectTask is not done");
        this.reconnectTask = this.syncContext.schedule(new Runnable() {
            public void run() {
                SynchronizationContext.ScheduledHandle unused = InternalSubchannel.this.reconnectTask = null;
                InternalSubchannel.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "CONNECTING after backoff");
                InternalSubchannel.this.gotoNonErrorState(ConnectivityState.CONNECTING);
                InternalSubchannel.this.startNewTransport();
            }
        }, nextBackoffNanos, TimeUnit.NANOSECONDS, this.scheduledExecutor);
    }

    /* access modifiers changed from: package-private */
    public void resetConnectBackoff() {
        this.syncContext.execute(new Runnable() {
            public void run() {
                if (InternalSubchannel.this.state.getState() == ConnectivityState.TRANSIENT_FAILURE) {
                    InternalSubchannel.this.cancelReconnectTask();
                    InternalSubchannel.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "CONNECTING; backoff interrupted");
                    InternalSubchannel.this.gotoNonErrorState(ConnectivityState.CONNECTING);
                    InternalSubchannel.this.startNewTransport();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void gotoNonErrorState(ConnectivityState connectivityState) {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        gotoState(ConnectivityStateInfo.forNonError(connectivityState));
    }

    private void gotoState(ConnectivityStateInfo connectivityStateInfo) {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        if (this.state.getState() != connectivityStateInfo.getState()) {
            Preconditions.checkState(this.state.getState() != ConnectivityState.SHUTDOWN, "Cannot transition out of SHUTDOWN to " + connectivityStateInfo);
            this.state = connectivityStateInfo;
            this.callback.onStateChange(this, connectivityStateInfo);
        }
    }

    public void updateAddresses(List<EquivalentAddressGroup> list) {
        Preconditions.checkNotNull(list, "newAddressGroups");
        checkListHasNoNulls(list, "newAddressGroups contains null entry");
        Preconditions.checkArgument(!list.isEmpty(), "newAddressGroups is empty");
        final List unmodifiableList = Collections.unmodifiableList(new ArrayList(list));
        this.syncContext.execute(new Runnable() {
            /* JADX WARNING: Removed duplicated region for block: B:12:0x0096  */
            /* JADX WARNING: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r7 = this;
                    io.grpc.internal.InternalSubchannel r0 = io.grpc.internal.InternalSubchannel.this
                    io.grpc.internal.InternalSubchannel$Index r0 = r0.addressIndex
                    java.net.SocketAddress r0 = r0.getCurrentAddress()
                    io.grpc.internal.InternalSubchannel r1 = io.grpc.internal.InternalSubchannel.this
                    io.grpc.internal.InternalSubchannel$Index r1 = r1.addressIndex
                    java.util.List r2 = r3
                    r1.updateGroups(r2)
                    io.grpc.internal.InternalSubchannel r1 = io.grpc.internal.InternalSubchannel.this
                    java.util.List r2 = r3
                    java.util.List unused = r1.addressGroups = r2
                    io.grpc.internal.InternalSubchannel r1 = io.grpc.internal.InternalSubchannel.this
                    io.grpc.ConnectivityStateInfo r1 = r1.state
                    io.grpc.ConnectivityState r1 = r1.getState()
                    io.grpc.ConnectivityState r2 = io.grpc.ConnectivityState.READY
                    r3 = 0
                    if (r1 == r2) goto L_0x0039
                    io.grpc.internal.InternalSubchannel r1 = io.grpc.internal.InternalSubchannel.this
                    io.grpc.ConnectivityStateInfo r1 = r1.state
                    io.grpc.ConnectivityState r1 = r1.getState()
                    io.grpc.ConnectivityState r2 = io.grpc.ConnectivityState.CONNECTING
                    if (r1 != r2) goto L_0x0093
                L_0x0039:
                    io.grpc.internal.InternalSubchannel r1 = io.grpc.internal.InternalSubchannel.this
                    io.grpc.internal.InternalSubchannel$Index r1 = r1.addressIndex
                    boolean r0 = r1.seekTo(r0)
                    if (r0 != 0) goto L_0x0093
                    io.grpc.internal.InternalSubchannel r0 = io.grpc.internal.InternalSubchannel.this
                    io.grpc.ConnectivityStateInfo r0 = r0.state
                    io.grpc.ConnectivityState r0 = r0.getState()
                    io.grpc.ConnectivityState r1 = io.grpc.ConnectivityState.READY
                    if (r0 != r1) goto L_0x006f
                    io.grpc.internal.InternalSubchannel r0 = io.grpc.internal.InternalSubchannel.this
                    io.grpc.internal.ManagedClientTransport r0 = r0.activeTransport
                    io.grpc.internal.InternalSubchannel r1 = io.grpc.internal.InternalSubchannel.this
                    io.grpc.internal.ManagedClientTransport unused = r1.activeTransport = r3
                    io.grpc.internal.InternalSubchannel r1 = io.grpc.internal.InternalSubchannel.this
                    io.grpc.internal.InternalSubchannel$Index r1 = r1.addressIndex
                    r1.reset()
                    io.grpc.internal.InternalSubchannel r1 = io.grpc.internal.InternalSubchannel.this
                    io.grpc.ConnectivityState r2 = io.grpc.ConnectivityState.IDLE
                    r1.gotoNonErrorState(r2)
                    goto L_0x0094
                L_0x006f:
                    io.grpc.internal.InternalSubchannel r0 = io.grpc.internal.InternalSubchannel.this
                    io.grpc.internal.ConnectionClientTransport r0 = r0.pendingTransport
                    io.grpc.Status r1 = io.grpc.Status.UNAVAILABLE
                    java.lang.String r2 = "InternalSubchannel closed pending transport due to address change"
                    io.grpc.Status r1 = r1.withDescription(r2)
                    r0.shutdown(r1)
                    io.grpc.internal.InternalSubchannel r0 = io.grpc.internal.InternalSubchannel.this
                    io.grpc.internal.ConnectionClientTransport unused = r0.pendingTransport = r3
                    io.grpc.internal.InternalSubchannel r0 = io.grpc.internal.InternalSubchannel.this
                    io.grpc.internal.InternalSubchannel$Index r0 = r0.addressIndex
                    r0.reset()
                    io.grpc.internal.InternalSubchannel r0 = io.grpc.internal.InternalSubchannel.this
                    r0.startNewTransport()
                L_0x0093:
                    r0 = r3
                L_0x0094:
                    if (r0 == 0) goto L_0x00e3
                    io.grpc.internal.InternalSubchannel r1 = io.grpc.internal.InternalSubchannel.this
                    io.grpc.SynchronizationContext$ScheduledHandle r1 = r1.shutdownDueToUpdateTask
                    if (r1 == 0) goto L_0x00c2
                    io.grpc.internal.InternalSubchannel r1 = io.grpc.internal.InternalSubchannel.this
                    io.grpc.internal.ManagedClientTransport r1 = r1.shutdownDueToUpdateTransport
                    io.grpc.Status r2 = io.grpc.Status.UNAVAILABLE
                    java.lang.String r4 = "InternalSubchannel closed transport early due to address change"
                    io.grpc.Status r2 = r2.withDescription(r4)
                    r1.shutdown(r2)
                    io.grpc.internal.InternalSubchannel r1 = io.grpc.internal.InternalSubchannel.this
                    io.grpc.SynchronizationContext$ScheduledHandle r1 = r1.shutdownDueToUpdateTask
                    r1.cancel()
                    io.grpc.internal.InternalSubchannel r1 = io.grpc.internal.InternalSubchannel.this
                    io.grpc.SynchronizationContext.ScheduledHandle unused = r1.shutdownDueToUpdateTask = r3
                    io.grpc.internal.InternalSubchannel r1 = io.grpc.internal.InternalSubchannel.this
                    io.grpc.internal.ManagedClientTransport unused = r1.shutdownDueToUpdateTransport = r3
                L_0x00c2:
                    io.grpc.internal.InternalSubchannel r1 = io.grpc.internal.InternalSubchannel.this
                    io.grpc.internal.ManagedClientTransport unused = r1.shutdownDueToUpdateTransport = r0
                    io.grpc.internal.InternalSubchannel r0 = io.grpc.internal.InternalSubchannel.this
                    io.grpc.SynchronizationContext r1 = r0.syncContext
                    io.grpc.internal.InternalSubchannel$4$1 r2 = new io.grpc.internal.InternalSubchannel$4$1
                    r2.<init>()
                    r3 = 5
                    java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.SECONDS
                    io.grpc.internal.InternalSubchannel r6 = io.grpc.internal.InternalSubchannel.this
                    java.util.concurrent.ScheduledExecutorService r6 = r6.scheduledExecutor
                    io.grpc.SynchronizationContext$ScheduledHandle r1 = r1.schedule(r2, r3, r5, r6)
                    io.grpc.SynchronizationContext.ScheduledHandle unused = r0.shutdownDueToUpdateTask = r1
                L_0x00e3:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.InternalSubchannel.AnonymousClass4.run():void");
            }
        });
    }

    public void shutdown(final Status status) {
        this.syncContext.execute(new Runnable() {
            public void run() {
                if (InternalSubchannel.this.state.getState() != ConnectivityState.SHUTDOWN) {
                    Status unused = InternalSubchannel.this.shutdownReason = status;
                    ManagedClientTransport access$1000 = InternalSubchannel.this.activeTransport;
                    ConnectionClientTransport access$1100 = InternalSubchannel.this.pendingTransport;
                    ManagedClientTransport unused2 = InternalSubchannel.this.activeTransport = null;
                    ConnectionClientTransport unused3 = InternalSubchannel.this.pendingTransport = null;
                    InternalSubchannel.this.gotoNonErrorState(ConnectivityState.SHUTDOWN);
                    InternalSubchannel.this.addressIndex.reset();
                    if (InternalSubchannel.this.transports.isEmpty()) {
                        InternalSubchannel.this.handleTermination();
                    }
                    InternalSubchannel.this.cancelReconnectTask();
                    if (InternalSubchannel.this.shutdownDueToUpdateTask != null) {
                        InternalSubchannel.this.shutdownDueToUpdateTask.cancel();
                        InternalSubchannel.this.shutdownDueToUpdateTransport.shutdown(status);
                        SynchronizationContext.ScheduledHandle unused4 = InternalSubchannel.this.shutdownDueToUpdateTask = null;
                        ManagedClientTransport unused5 = InternalSubchannel.this.shutdownDueToUpdateTransport = null;
                    }
                    if (access$1000 != null) {
                        access$1000.shutdown(status);
                    }
                    if (access$1100 != null) {
                        access$1100.shutdown(status);
                    }
                }
            }
        });
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("logId", this.logId.getId()).add("addressGroups", (Object) this.addressGroups).toString();
    }

    /* access modifiers changed from: private */
    public void handleTermination() {
        this.syncContext.execute(new Runnable() {
            public void run() {
                InternalSubchannel.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "Terminated");
                InternalSubchannel.this.callback.onTerminated(InternalSubchannel.this);
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleTransportInUseState(final ConnectionClientTransport connectionClientTransport, final boolean z) {
        this.syncContext.execute(new Runnable() {
            public void run() {
                InternalSubchannel.this.inUseStateAggregator.updateObjectInUse(connectionClientTransport, z);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void shutdownNow(final Status status) {
        shutdown(status);
        this.syncContext.execute(new Runnable() {
            public void run() {
                for (ManagedClientTransport shutdownNow : new ArrayList(InternalSubchannel.this.transports)) {
                    shutdownNow.shutdownNow(status);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public List<EquivalentAddressGroup> getAddressGroups() {
        return this.addressGroups;
    }

    /* access modifiers changed from: private */
    public void cancelReconnectTask() {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        SynchronizationContext.ScheduledHandle scheduledHandle = this.reconnectTask;
        if (scheduledHandle != null) {
            scheduledHandle.cancel();
            this.reconnectTask = null;
            this.reconnectPolicy = null;
        }
    }

    public InternalLogId getLogId() {
        return this.logId;
    }

    public ListenableFuture<InternalChannelz.ChannelStats> getStats() {
        final SettableFuture create = SettableFuture.create();
        this.syncContext.execute(new Runnable() {
            public void run() {
                InternalChannelz.ChannelStats.Builder builder = new InternalChannelz.ChannelStats.Builder();
                List<EquivalentAddressGroup> groups = InternalSubchannel.this.addressIndex.getGroups();
                ArrayList arrayList = new ArrayList(InternalSubchannel.this.transports);
                builder.setTarget(groups.toString()).setState(InternalSubchannel.this.getState());
                builder.setSockets(arrayList);
                InternalSubchannel.this.callsTracer.updateBuilder(builder);
                InternalSubchannel.this.channelTracer.updateBuilder(builder);
                create.set(builder.build());
            }
        });
        return create;
    }

    /* access modifiers changed from: package-private */
    public ConnectivityState getState() {
        return this.state.getState();
    }

    private static void checkListHasNoNulls(List<?> list, String str) {
        for (Object checkNotNull : list) {
            Preconditions.checkNotNull(checkNotNull, str);
        }
    }

    private class TransportListener implements ManagedClientTransport.Listener {
        boolean shutdownInitiated = false;
        final ConnectionClientTransport transport;

        TransportListener(ConnectionClientTransport connectionClientTransport) {
            this.transport = connectionClientTransport;
        }

        public Attributes filterTransport(Attributes attributes) {
            for (ClientTransportFilter clientTransportFilter : InternalSubchannel.this.transportFilters) {
                attributes = (Attributes) Preconditions.checkNotNull(clientTransportFilter.transportReady(attributes), "Filter %s returned null", (Object) clientTransportFilter);
            }
            return attributes;
        }

        public void transportReady() {
            InternalSubchannel.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "READY");
            InternalSubchannel.this.syncContext.execute(new Runnable() {
                public void run() {
                    BackoffPolicy unused = InternalSubchannel.this.reconnectPolicy = null;
                    if (InternalSubchannel.this.shutdownReason != null) {
                        Preconditions.checkState(InternalSubchannel.this.activeTransport == null, "Unexpected non-null activeTransport");
                        TransportListener.this.transport.shutdown(InternalSubchannel.this.shutdownReason);
                    } else if (InternalSubchannel.this.pendingTransport == TransportListener.this.transport) {
                        ManagedClientTransport unused2 = InternalSubchannel.this.activeTransport = TransportListener.this.transport;
                        ConnectionClientTransport unused3 = InternalSubchannel.this.pendingTransport = null;
                        InternalSubchannel.this.gotoNonErrorState(ConnectivityState.READY);
                    }
                }
            });
        }

        public void transportInUse(boolean z) {
            InternalSubchannel.this.handleTransportInUseState(this.transport, z);
        }

        public void transportShutdown(final Status status) {
            InternalSubchannel.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "{0} SHUTDOWN with {1}", this.transport.getLogId(), InternalSubchannel.this.printShortStatus(status));
            this.shutdownInitiated = true;
            InternalSubchannel.this.syncContext.execute(new Runnable() {
                public void run() {
                    if (InternalSubchannel.this.state.getState() != ConnectivityState.SHUTDOWN) {
                        if (InternalSubchannel.this.activeTransport == TransportListener.this.transport) {
                            ManagedClientTransport unused = InternalSubchannel.this.activeTransport = null;
                            InternalSubchannel.this.addressIndex.reset();
                            InternalSubchannel.this.gotoNonErrorState(ConnectivityState.IDLE);
                        } else if (InternalSubchannel.this.pendingTransport == TransportListener.this.transport) {
                            Preconditions.checkState(InternalSubchannel.this.state.getState() == ConnectivityState.CONNECTING, "Expected state is CONNECTING, actual state is %s", (Object) InternalSubchannel.this.state.getState());
                            InternalSubchannel.this.addressIndex.increment();
                            if (!InternalSubchannel.this.addressIndex.isValid()) {
                                ConnectionClientTransport unused2 = InternalSubchannel.this.pendingTransport = null;
                                InternalSubchannel.this.addressIndex.reset();
                                InternalSubchannel.this.scheduleBackoff(status);
                                return;
                            }
                            InternalSubchannel.this.startNewTransport();
                        }
                    }
                }
            });
        }

        public void transportTerminated() {
            Preconditions.checkState(this.shutdownInitiated, "transportShutdown() must be called before transportTerminated().");
            InternalSubchannel.this.channelLogger.log(ChannelLogger.ChannelLogLevel.INFO, "{0} Terminated", this.transport.getLogId());
            InternalSubchannel.this.channelz.removeClientSocket(this.transport);
            InternalSubchannel.this.handleTransportInUseState(this.transport, false);
            for (ClientTransportFilter transportTerminated : InternalSubchannel.this.transportFilters) {
                transportTerminated.transportTerminated(this.transport.getAttributes());
            }
            InternalSubchannel.this.syncContext.execute(new Runnable() {
                public void run() {
                    InternalSubchannel.this.transports.remove(TransportListener.this.transport);
                    if (InternalSubchannel.this.state.getState() == ConnectivityState.SHUTDOWN && InternalSubchannel.this.transports.isEmpty()) {
                        InternalSubchannel.this.handleTermination();
                    }
                }
            });
        }
    }

    static abstract class Callback {
        /* access modifiers changed from: package-private */
        public void onInUse(InternalSubchannel internalSubchannel) {
        }

        /* access modifiers changed from: package-private */
        public void onNotInUse(InternalSubchannel internalSubchannel) {
        }

        /* access modifiers changed from: package-private */
        public void onStateChange(InternalSubchannel internalSubchannel, ConnectivityStateInfo connectivityStateInfo) {
        }

        /* access modifiers changed from: package-private */
        public void onTerminated(InternalSubchannel internalSubchannel) {
        }

        Callback() {
        }
    }

    static final class CallTracingTransport extends ForwardingConnectionClientTransport {
        /* access modifiers changed from: private */
        public final CallTracer callTracer;
        private final ConnectionClientTransport delegate;

        private CallTracingTransport(ConnectionClientTransport connectionClientTransport, CallTracer callTracer2) {
            this.delegate = connectionClientTransport;
            this.callTracer = callTracer2;
        }

        /* access modifiers changed from: protected */
        public ConnectionClientTransport delegate() {
            return this.delegate;
        }

        public ClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions, ClientStreamTracer[] clientStreamTracerArr) {
            final ClientStream newStream = super.newStream(methodDescriptor, metadata, callOptions, clientStreamTracerArr);
            return new ForwardingClientStream() {
                /* access modifiers changed from: protected */
                public ClientStream delegate() {
                    return newStream;
                }

                public void start(final ClientStreamListener clientStreamListener) {
                    CallTracingTransport.this.callTracer.reportCallStarted();
                    super.start(new ForwardingClientStreamListener() {
                        /* access modifiers changed from: protected */
                        public ClientStreamListener delegate() {
                            return clientStreamListener;
                        }

                        public void closed(Status status, ClientStreamListener.RpcProgress rpcProgress, Metadata metadata) {
                            CallTracingTransport.this.callTracer.reportCallEnded(status.isOk());
                            super.closed(status, rpcProgress, metadata);
                        }
                    });
                }
            };
        }
    }

    static final class Index {
        private List<EquivalentAddressGroup> addressGroups;
        private int addressIndex;
        private int groupIndex;

        public Index(List<EquivalentAddressGroup> list) {
            this.addressGroups = list;
        }

        public boolean isValid() {
            return this.groupIndex < this.addressGroups.size();
        }

        public boolean isAtBeginning() {
            return this.groupIndex == 0 && this.addressIndex == 0;
        }

        public void increment() {
            int i = this.addressIndex + 1;
            this.addressIndex = i;
            if (i >= this.addressGroups.get(this.groupIndex).getAddresses().size()) {
                this.groupIndex++;
                this.addressIndex = 0;
            }
        }

        public void reset() {
            this.groupIndex = 0;
            this.addressIndex = 0;
        }

        public SocketAddress getCurrentAddress() {
            return this.addressGroups.get(this.groupIndex).getAddresses().get(this.addressIndex);
        }

        public Attributes getCurrentEagAttributes() {
            return this.addressGroups.get(this.groupIndex).getAttributes();
        }

        public List<EquivalentAddressGroup> getGroups() {
            return this.addressGroups;
        }

        public void updateGroups(List<EquivalentAddressGroup> list) {
            this.addressGroups = list;
            reset();
        }

        public boolean seekTo(SocketAddress socketAddress) {
            int i = 0;
            while (i < this.addressGroups.size()) {
                int indexOf = this.addressGroups.get(i).getAddresses().indexOf(socketAddress);
                if (indexOf == -1) {
                    i++;
                } else {
                    this.groupIndex = i;
                    this.addressIndex = indexOf;
                    return true;
                }
            }
            return false;
        }
    }

    /* access modifiers changed from: private */
    public String printShortStatus(Status status) {
        StringBuilder sb = new StringBuilder();
        sb.append(status.getCode());
        if (status.getDescription() != null) {
            sb.append("(").append(status.getDescription()).append(")");
        }
        if (status.getCause() != null) {
            sb.append("[").append(status.getCause()).append("]");
        }
        return sb.toString();
    }

    static final class TransportLogger extends ChannelLogger {
        InternalLogId logId;

        TransportLogger() {
        }

        public void log(ChannelLogger.ChannelLogLevel channelLogLevel, String str) {
            ChannelLoggerImpl.logOnly(this.logId, channelLogLevel, str);
        }

        public void log(ChannelLogger.ChannelLogLevel channelLogLevel, String str, Object... objArr) {
            ChannelLoggerImpl.logOnly(this.logId, channelLogLevel, str, objArr);
        }
    }
}
