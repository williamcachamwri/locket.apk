package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;
import io.grpc.Attributes;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.EquivalentAddressGroup;
import io.grpc.LoadBalancer;
import io.grpc.Status;
import io.grpc.SynchronizationContext;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

final class PickFirstLeafLoadBalancer extends LoadBalancer {
    static final int CONNECTION_DELAY_INTERVAL_MS = 250;
    public static final String GRPC_EXPERIMENTAL_XDS_DUALSTACK_ENDPOINTS = "GRPC_EXPERIMENTAL_XDS_DUALSTACK_ENDPOINTS";
    /* access modifiers changed from: private */
    public static final Logger log = Logger.getLogger(PickFirstLeafLoadBalancer.class.getName());
    /* access modifiers changed from: private */
    public Index addressIndex;
    private ConnectivityState concludedState = ConnectivityState.IDLE;
    private final boolean enableHappyEyeballs = GrpcUtil.getFlag(GRPC_EXPERIMENTAL_XDS_DUALSTACK_ENDPOINTS, false);
    private boolean firstPass = true;
    /* access modifiers changed from: private */
    public final LoadBalancer.Helper helper;
    private int numTf = 0;
    private ConnectivityState rawConnectivityState = ConnectivityState.IDLE;
    /* access modifiers changed from: private */
    @Nullable
    public SynchronizationContext.ScheduledHandle scheduleConnectionTask;
    /* access modifiers changed from: private */
    public final Map<SocketAddress, SubchannelData> subchannels = new HashMap();

    PickFirstLeafLoadBalancer(LoadBalancer.Helper helper2) {
        this.helper = (LoadBalancer.Helper) Preconditions.checkNotNull(helper2, "helper");
    }

    public Status acceptResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
        if (this.rawConnectivityState == ConnectivityState.SHUTDOWN) {
            return Status.FAILED_PRECONDITION.withDescription("Already shut down");
        }
        List<EquivalentAddressGroup> addresses = resolvedAddresses.getAddresses();
        if (addresses.isEmpty()) {
            Status withDescription = Status.UNAVAILABLE.withDescription("NameResolver returned no usable address. addrs=" + resolvedAddresses.getAddresses() + ", attrs=" + resolvedAddresses.getAttributes());
            handleNameResolutionError(withDescription);
            return withDescription;
        }
        for (EquivalentAddressGroup equivalentAddressGroup : addresses) {
            if (equivalentAddressGroup == null) {
                Status withDescription2 = Status.UNAVAILABLE.withDescription("NameResolver returned address list with null endpoint. addrs=" + resolvedAddresses.getAddresses() + ", attrs=" + resolvedAddresses.getAttributes());
                handleNameResolutionError(withDescription2);
                return withDescription2;
            }
        }
        this.firstPass = true;
        if (resolvedAddresses.getLoadBalancingPolicyConfig() instanceof PickFirstLeafLoadBalancerConfig) {
            PickFirstLeafLoadBalancerConfig pickFirstLeafLoadBalancerConfig = (PickFirstLeafLoadBalancerConfig) resolvedAddresses.getLoadBalancingPolicyConfig();
            if (pickFirstLeafLoadBalancerConfig.shuffleAddressList != null && pickFirstLeafLoadBalancerConfig.shuffleAddressList.booleanValue()) {
                ArrayList arrayList = new ArrayList(addresses);
                Collections.shuffle(arrayList, pickFirstLeafLoadBalancerConfig.randomSeed != null ? new Random(pickFirstLeafLoadBalancerConfig.randomSeed.longValue()) : new Random());
                addresses = arrayList;
            }
        }
        ImmutableList build = ImmutableList.builder().addAll((Iterable) addresses).build();
        if (this.addressIndex == null) {
            this.addressIndex = new Index(build);
        } else if (this.rawConnectivityState == ConnectivityState.READY) {
            SocketAddress currentAddress = this.addressIndex.getCurrentAddress();
            this.addressIndex.updateGroups(build);
            if (this.addressIndex.seekTo(currentAddress)) {
                return Status.OK;
            }
            this.addressIndex.reset();
        } else {
            this.addressIndex.updateGroups(build);
        }
        HashSet<SocketAddress> hashSet = new HashSet<>(this.subchannels.keySet());
        HashSet hashSet2 = new HashSet();
        UnmodifiableIterator it = build.iterator();
        while (it.hasNext()) {
            hashSet2.addAll(((EquivalentAddressGroup) it.next()).getAddresses());
        }
        for (SocketAddress socketAddress : hashSet) {
            if (!hashSet2.contains(socketAddress)) {
                this.subchannels.remove(socketAddress).getSubchannel().shutdown();
            }
        }
        if (hashSet.size() == 0 || this.rawConnectivityState == ConnectivityState.CONNECTING || this.rawConnectivityState == ConnectivityState.READY) {
            this.rawConnectivityState = ConnectivityState.CONNECTING;
            updateBalancingState(ConnectivityState.CONNECTING, new Picker(LoadBalancer.PickResult.withNoResult()));
            cancelScheduleTask();
            requestConnection();
        } else if (this.rawConnectivityState == ConnectivityState.IDLE) {
            updateBalancingState(ConnectivityState.IDLE, new RequestConnectionPicker(this));
        } else if (this.rawConnectivityState == ConnectivityState.TRANSIENT_FAILURE) {
            cancelScheduleTask();
            requestConnection();
        }
        return Status.OK;
    }

    public void handleNameResolutionError(Status status) {
        for (SubchannelData subchannel : this.subchannels.values()) {
            subchannel.getSubchannel().shutdown();
        }
        this.subchannels.clear();
        updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, new Picker(LoadBalancer.PickResult.withError(status)));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: processSubchannelState */
    public void m2328lambda$createNewSubchannel$0$iogrpcinternalPickFirstLeafLoadBalancer(LoadBalancer.Subchannel subchannel, ConnectivityStateInfo connectivityStateInfo) {
        ConnectivityState state = connectivityStateInfo.getState();
        SubchannelData subchannelData = this.subchannels.get(getAddress(subchannel));
        if (subchannelData != null && subchannelData.getSubchannel() == subchannel && state != ConnectivityState.SHUTDOWN) {
            if (state == ConnectivityState.IDLE) {
                this.helper.refreshNameResolution();
            }
            subchannelData.updateState(state);
            if (this.rawConnectivityState == ConnectivityState.TRANSIENT_FAILURE || this.concludedState == ConnectivityState.TRANSIENT_FAILURE) {
                if (state != ConnectivityState.CONNECTING) {
                    if (state == ConnectivityState.IDLE) {
                        requestConnection();
                        return;
                    }
                } else {
                    return;
                }
            }
            int i = AnonymousClass1.$SwitchMap$io$grpc$ConnectivityState[state.ordinal()];
            if (i == 1) {
                this.addressIndex.reset();
                this.rawConnectivityState = ConnectivityState.IDLE;
                updateBalancingState(ConnectivityState.IDLE, new RequestConnectionPicker(this));
            } else if (i == 2) {
                this.rawConnectivityState = ConnectivityState.CONNECTING;
                updateBalancingState(ConnectivityState.CONNECTING, new Picker(LoadBalancer.PickResult.withNoResult()));
            } else if (i == 3) {
                shutdownRemaining(subchannelData);
                this.addressIndex.seekTo(getAddress(subchannel));
                this.rawConnectivityState = ConnectivityState.READY;
                updateHealthCheckedState(subchannelData);
            } else if (i == 4) {
                if (this.addressIndex.isValid() && this.subchannels.get(this.addressIndex.getCurrentAddress()).getSubchannel() == subchannel && this.addressIndex.increment()) {
                    cancelScheduleTask();
                    requestConnection();
                }
                if (isPassComplete()) {
                    this.rawConnectivityState = ConnectivityState.TRANSIENT_FAILURE;
                    updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, new Picker(LoadBalancer.PickResult.withError(connectivityStateInfo.getStatus())));
                    int i2 = this.numTf + 1;
                    this.numTf = i2;
                    if (i2 >= this.addressIndex.size() || this.firstPass) {
                        this.firstPass = false;
                        this.numTf = 0;
                        this.helper.refreshNameResolution();
                    }
                }
            } else {
                throw new IllegalArgumentException("Unsupported state:" + state);
            }
        }
    }

    /* renamed from: io.grpc.internal.PickFirstLeafLoadBalancer$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$ConnectivityState;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                io.grpc.ConnectivityState[] r0 = io.grpc.ConnectivityState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$grpc$ConnectivityState = r0
                io.grpc.ConnectivityState r1 = io.grpc.ConnectivityState.IDLE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$io$grpc$ConnectivityState     // Catch:{ NoSuchFieldError -> 0x001d }
                io.grpc.ConnectivityState r1 = io.grpc.ConnectivityState.CONNECTING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$io$grpc$ConnectivityState     // Catch:{ NoSuchFieldError -> 0x0028 }
                io.grpc.ConnectivityState r1 = io.grpc.ConnectivityState.READY     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$io$grpc$ConnectivityState     // Catch:{ NoSuchFieldError -> 0x0033 }
                io.grpc.ConnectivityState r1 = io.grpc.ConnectivityState.TRANSIENT_FAILURE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$io$grpc$ConnectivityState     // Catch:{ NoSuchFieldError -> 0x003e }
                io.grpc.ConnectivityState r1 = io.grpc.ConnectivityState.SHUTDOWN     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.PickFirstLeafLoadBalancer.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    public void updateHealthCheckedState(SubchannelData subchannelData) {
        if (subchannelData.state == ConnectivityState.READY) {
            if (subchannelData.getHealthState() == ConnectivityState.READY) {
                updateBalancingState(ConnectivityState.READY, new LoadBalancer.FixedResultPicker(LoadBalancer.PickResult.withSubchannel(subchannelData.subchannel)));
            } else if (subchannelData.getHealthState() == ConnectivityState.TRANSIENT_FAILURE) {
                updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, new Picker(LoadBalancer.PickResult.withError(subchannelData.healthListener.healthStateInfo.getStatus())));
            } else if (this.concludedState != ConnectivityState.TRANSIENT_FAILURE) {
                updateBalancingState(subchannelData.getHealthState(), new Picker(LoadBalancer.PickResult.withNoResult()));
            }
        }
    }

    private void updateBalancingState(ConnectivityState connectivityState, LoadBalancer.SubchannelPicker subchannelPicker) {
        if (connectivityState != this.concludedState || (connectivityState != ConnectivityState.IDLE && connectivityState != ConnectivityState.CONNECTING)) {
            this.concludedState = connectivityState;
            this.helper.updateBalancingState(connectivityState, subchannelPicker);
        }
    }

    public void shutdown() {
        log.log(Level.FINE, "Shutting down, currently have {} subchannels created", Integer.valueOf(this.subchannels.size()));
        this.rawConnectivityState = ConnectivityState.SHUTDOWN;
        this.concludedState = ConnectivityState.SHUTDOWN;
        cancelScheduleTask();
        for (SubchannelData subchannel : this.subchannels.values()) {
            subchannel.getSubchannel().shutdown();
        }
        this.subchannels.clear();
    }

    private void shutdownRemaining(SubchannelData subchannelData) {
        cancelScheduleTask();
        for (SubchannelData next : this.subchannels.values()) {
            if (!next.getSubchannel().equals(subchannelData.subchannel)) {
                next.getSubchannel().shutdown();
            }
        }
        this.subchannels.clear();
        subchannelData.updateState(ConnectivityState.READY);
        this.subchannels.put(getAddress(subchannelData.subchannel), subchannelData);
    }

    public void requestConnection() {
        LoadBalancer.Subchannel subchannel;
        Index index = this.addressIndex;
        if (index != null && index.isValid() && this.rawConnectivityState != ConnectivityState.SHUTDOWN) {
            SocketAddress currentAddress = this.addressIndex.getCurrentAddress();
            if (this.subchannels.containsKey(currentAddress)) {
                subchannel = this.subchannels.get(currentAddress).getSubchannel();
            } else {
                subchannel = createNewSubchannel(currentAddress);
            }
            int i = AnonymousClass1.$SwitchMap$io$grpc$ConnectivityState[this.subchannels.get(currentAddress).getState().ordinal()];
            if (i == 1) {
                subchannel.requestConnection();
                this.subchannels.get(currentAddress).updateState(ConnectivityState.CONNECTING);
                scheduleNextConnection();
            } else if (i != 2) {
                if (i == 3) {
                    log.warning("Requesting a connection even though we have a READY subchannel");
                } else if (i == 4) {
                    this.addressIndex.increment();
                    requestConnection();
                }
            } else if (this.enableHappyEyeballs) {
                scheduleNextConnection();
            } else {
                subchannel.requestConnection();
            }
        }
    }

    private void scheduleNextConnection() {
        if (this.enableHappyEyeballs) {
            SynchronizationContext.ScheduledHandle scheduledHandle = this.scheduleConnectionTask;
            if (scheduledHandle == null || !scheduledHandle.isPending()) {
                this.scheduleConnectionTask = this.helper.getSynchronizationContext().schedule(new Runnable() {
                    public void run() {
                        SynchronizationContext.ScheduledHandle unused = PickFirstLeafLoadBalancer.this.scheduleConnectionTask = null;
                        if (PickFirstLeafLoadBalancer.this.addressIndex.increment()) {
                            PickFirstLeafLoadBalancer.this.requestConnection();
                        }
                    }
                }, 250, TimeUnit.MILLISECONDS, this.helper.getScheduledExecutorService());
            }
        }
    }

    private void cancelScheduleTask() {
        SynchronizationContext.ScheduledHandle scheduledHandle = this.scheduleConnectionTask;
        if (scheduledHandle != null) {
            scheduledHandle.cancel();
            this.scheduleConnectionTask = null;
        }
    }

    private LoadBalancer.Subchannel createNewSubchannel(SocketAddress socketAddress) {
        HealthListener healthListener = new HealthListener(this, (AnonymousClass1) null);
        LoadBalancer.Subchannel createSubchannel = this.helper.createSubchannel(LoadBalancer.CreateSubchannelArgs.newBuilder().setAddresses((List<EquivalentAddressGroup>) Lists.newArrayList((E[]) new EquivalentAddressGroup[]{new EquivalentAddressGroup(socketAddress)})).addOption(HEALTH_CONSUMER_LISTENER_ARG_KEY, healthListener).build());
        if (createSubchannel != null) {
            SubchannelData subchannelData = new SubchannelData(createSubchannel, ConnectivityState.IDLE, healthListener);
            SubchannelData unused = healthListener.subchannelData = subchannelData;
            this.subchannels.put(socketAddress, subchannelData);
            if (createSubchannel.getAttributes().get(LoadBalancer.HAS_HEALTH_PRODUCER_LISTENER_KEY) == null) {
                ConnectivityStateInfo unused2 = healthListener.healthStateInfo = ConnectivityStateInfo.forNonError(ConnectivityState.READY);
            }
            createSubchannel.start(new PickFirstLeafLoadBalancer$$ExternalSyntheticLambda0(this, createSubchannel));
            return createSubchannel;
        }
        log.warning("Was not able to create subchannel for " + socketAddress);
        throw new IllegalStateException("Can't create subchannel");
    }

    private boolean isPassComplete() {
        Index index = this.addressIndex;
        if (index == null || index.isValid() || this.subchannels.size() < this.addressIndex.size()) {
            return false;
        }
        for (SubchannelData isCompletedConnectivityAttempt : this.subchannels.values()) {
            if (!isCompletedConnectivityAttempt.isCompletedConnectivityAttempt()) {
                return false;
            }
        }
        return true;
    }

    private final class HealthListener implements LoadBalancer.SubchannelStateListener {
        /* access modifiers changed from: private */
        public ConnectivityStateInfo healthStateInfo;
        /* access modifiers changed from: private */
        public SubchannelData subchannelData;

        private HealthListener() {
            this.healthStateInfo = ConnectivityStateInfo.forNonError(ConnectivityState.IDLE);
        }

        /* synthetic */ HealthListener(PickFirstLeafLoadBalancer pickFirstLeafLoadBalancer, AnonymousClass1 r2) {
            this();
        }

        public void onSubchannelState(ConnectivityStateInfo connectivityStateInfo) {
            PickFirstLeafLoadBalancer.log.log(Level.FINE, "Received health status {0} for subchannel {1}", new Object[]{connectivityStateInfo, this.subchannelData.subchannel});
            this.healthStateInfo = connectivityStateInfo;
            if (PickFirstLeafLoadBalancer.this.addressIndex.isValid() && ((SubchannelData) PickFirstLeafLoadBalancer.this.subchannels.get(PickFirstLeafLoadBalancer.this.addressIndex.getCurrentAddress())).healthListener == this) {
                PickFirstLeafLoadBalancer.this.updateHealthCheckedState(this.subchannelData);
            }
        }
    }

    private SocketAddress getAddress(LoadBalancer.Subchannel subchannel) {
        return subchannel.getAddresses().getAddresses().get(0);
    }

    /* access modifiers changed from: package-private */
    public ConnectivityState getConcludedConnectivityState() {
        return this.concludedState;
    }

    private static final class Picker extends LoadBalancer.SubchannelPicker {
        private final LoadBalancer.PickResult result;

        Picker(LoadBalancer.PickResult pickResult) {
            this.result = (LoadBalancer.PickResult) Preconditions.checkNotNull(pickResult, "result");
        }

        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            return this.result;
        }

        public String toString() {
            return MoreObjects.toStringHelper((Class<?>) Picker.class).add("result", (Object) this.result).toString();
        }
    }

    private final class RequestConnectionPicker extends LoadBalancer.SubchannelPicker {
        private final AtomicBoolean connectionRequested = new AtomicBoolean(false);
        private final PickFirstLeafLoadBalancer pickFirstLeafLoadBalancer;

        RequestConnectionPicker(PickFirstLeafLoadBalancer pickFirstLeafLoadBalancer2) {
            this.pickFirstLeafLoadBalancer = (PickFirstLeafLoadBalancer) Preconditions.checkNotNull(pickFirstLeafLoadBalancer2, "pickFirstLeafLoadBalancer");
        }

        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            if (this.connectionRequested.compareAndSet(false, true)) {
                SynchronizationContext synchronizationContext = PickFirstLeafLoadBalancer.this.helper.getSynchronizationContext();
                PickFirstLeafLoadBalancer pickFirstLeafLoadBalancer2 = this.pickFirstLeafLoadBalancer;
                Objects.requireNonNull(pickFirstLeafLoadBalancer2);
                synchronizationContext.execute(new PickFirstLeafLoadBalancer$RequestConnectionPicker$$ExternalSyntheticLambda0(pickFirstLeafLoadBalancer2));
            }
            return LoadBalancer.PickResult.withNoResult();
        }
    }

    static final class Index {
        private List<EquivalentAddressGroup> addressGroups;
        private int addressIndex;
        private int groupIndex;

        public Index(List<EquivalentAddressGroup> list) {
            this.addressGroups = list == null ? Collections.emptyList() : list;
        }

        public boolean isValid() {
            return this.groupIndex < this.addressGroups.size();
        }

        public boolean isAtBeginning() {
            return this.groupIndex == 0 && this.addressIndex == 0;
        }

        public boolean increment() {
            if (!isValid()) {
                return false;
            }
            int i = this.addressIndex + 1;
            this.addressIndex = i;
            if (i < this.addressGroups.get(this.groupIndex).getAddresses().size()) {
                return true;
            }
            int i2 = this.groupIndex + 1;
            this.groupIndex = i2;
            this.addressIndex = 0;
            if (i2 < this.addressGroups.size()) {
                return true;
            }
            return false;
        }

        public void reset() {
            this.groupIndex = 0;
            this.addressIndex = 0;
        }

        public SocketAddress getCurrentAddress() {
            if (isValid()) {
                return this.addressGroups.get(this.groupIndex).getAddresses().get(this.addressIndex);
            }
            throw new IllegalStateException("Index is past the end of the address group list");
        }

        public Attributes getCurrentEagAttributes() {
            if (isValid()) {
                return this.addressGroups.get(this.groupIndex).getAttributes();
            }
            throw new IllegalStateException("Index is off the end of the address group list");
        }

        /* JADX WARNING: Failed to insert additional move for type inference */
        /* JADX WARNING: Incorrect type for immutable var: ssa=com.google.common.collect.ImmutableList<io.grpc.EquivalentAddressGroup>, code=java.util.List, for r1v0, types: [com.google.common.collect.ImmutableList<io.grpc.EquivalentAddressGroup>] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void updateGroups(java.util.List r1) {
            /*
                r0 = this;
                if (r1 == 0) goto L_0x0003
                goto L_0x0007
            L_0x0003:
                java.util.List r1 = java.util.Collections.emptyList()
            L_0x0007:
                r0.addressGroups = r1
                r0.reset()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.PickFirstLeafLoadBalancer.Index.updateGroups(com.google.common.collect.ImmutableList):void");
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

        public int size() {
            List<EquivalentAddressGroup> list = this.addressGroups;
            if (list != null) {
                return list.size();
            }
            return 0;
        }
    }

    private static final class SubchannelData {
        private boolean completedConnectivityAttempt = false;
        /* access modifiers changed from: private */
        public final HealthListener healthListener;
        /* access modifiers changed from: private */
        public ConnectivityState state;
        /* access modifiers changed from: private */
        public final LoadBalancer.Subchannel subchannel;

        public SubchannelData(LoadBalancer.Subchannel subchannel2, ConnectivityState connectivityState, HealthListener healthListener2) {
            this.subchannel = subchannel2;
            this.state = connectivityState;
            this.healthListener = healthListener2;
        }

        public LoadBalancer.Subchannel getSubchannel() {
            return this.subchannel;
        }

        public ConnectivityState getState() {
            return this.state;
        }

        public boolean isCompletedConnectivityAttempt() {
            return this.completedConnectivityAttempt;
        }

        /* access modifiers changed from: private */
        public void updateState(ConnectivityState connectivityState) {
            this.state = connectivityState;
            if (connectivityState == ConnectivityState.READY || connectivityState == ConnectivityState.TRANSIENT_FAILURE) {
                this.completedConnectivityAttempt = true;
            } else if (connectivityState == ConnectivityState.IDLE) {
                this.completedConnectivityAttempt = false;
            }
        }

        /* access modifiers changed from: private */
        public ConnectivityState getHealthState() {
            return this.healthListener.healthStateInfo.getState();
        }
    }

    public static final class PickFirstLeafLoadBalancerConfig {
        @Nullable
        final Long randomSeed;
        @Nullable
        public final Boolean shuffleAddressList;

        public PickFirstLeafLoadBalancerConfig(@Nullable Boolean bool) {
            this(bool, (Long) null);
        }

        PickFirstLeafLoadBalancerConfig(@Nullable Boolean bool, @Nullable Long l) {
            this.shuffleAddressList = bool;
            this.randomSeed = l;
        }
    }
}
