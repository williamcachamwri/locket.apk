package io.grpc.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import io.grpc.Attributes;
import io.grpc.ChannelLogger;
import io.grpc.ClientStreamTracer;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.EquivalentAddressGroup;
import io.grpc.LoadBalancer;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.SynchronizationContext;
import io.grpc.internal.ServiceConfigUtil;
import io.grpc.internal.TimeProvider;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import okhttp3.internal.connection.RealConnection;

public final class OutlierDetectionLoadBalancer extends LoadBalancer {
    /* access modifiers changed from: private */
    public static final Attributes.Key<AddressTracker> ADDRESS_TRACKER_ATTR_KEY = Attributes.Key.create("addressTrackerKey");
    private final LoadBalancer.Helper childHelper;
    private SynchronizationContext.ScheduledHandle detectionTimerHandle;
    /* access modifiers changed from: private */
    public Long detectionTimerStartNanos;
    private final ChannelLogger logger;
    private final GracefulSwitchLoadBalancer switchLb;
    private final SynchronizationContext syncContext;
    /* access modifiers changed from: private */
    public TimeProvider timeProvider;
    private final ScheduledExecutorService timeService;
    final AddressTrackerMap trackerMap = new AddressTrackerMap();

    public OutlierDetectionLoadBalancer(LoadBalancer.Helper helper, TimeProvider timeProvider2) {
        ChannelLogger channelLogger = helper.getChannelLogger();
        this.logger = channelLogger;
        ChildHelper childHelper2 = new ChildHelper((LoadBalancer.Helper) Preconditions.checkNotNull(helper, "helper"));
        this.childHelper = childHelper2;
        this.switchLb = new GracefulSwitchLoadBalancer(childHelper2);
        this.syncContext = (SynchronizationContext) Preconditions.checkNotNull(helper.getSynchronizationContext(), "syncContext");
        this.timeService = (ScheduledExecutorService) Preconditions.checkNotNull(helper.getScheduledExecutorService(), "timeService");
        this.timeProvider = timeProvider2;
        channelLogger.log(ChannelLogger.ChannelLogLevel.DEBUG, "OutlierDetection lb created.");
    }

    public Status acceptResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
        Long l;
        this.logger.log(ChannelLogger.ChannelLogLevel.DEBUG, "Received resolution result: {0}", resolvedAddresses);
        OutlierDetectionLoadBalancerConfig outlierDetectionLoadBalancerConfig = (OutlierDetectionLoadBalancerConfig) resolvedAddresses.getLoadBalancingPolicyConfig();
        ArrayList arrayList = new ArrayList();
        for (EquivalentAddressGroup addresses : resolvedAddresses.getAddresses()) {
            arrayList.addAll(addresses.getAddresses());
        }
        this.trackerMap.keySet().retainAll(arrayList);
        this.trackerMap.updateTrackerConfigs(outlierDetectionLoadBalancerConfig);
        this.trackerMap.putNewTrackers(outlierDetectionLoadBalancerConfig, arrayList);
        this.switchLb.switchTo(outlierDetectionLoadBalancerConfig.childPolicy.getProvider());
        if (outlierDetectionLoadBalancerConfig.outlierDetectionEnabled()) {
            if (this.detectionTimerStartNanos == null) {
                l = outlierDetectionLoadBalancerConfig.intervalNanos;
            } else {
                l = Long.valueOf(Math.max(0, outlierDetectionLoadBalancerConfig.intervalNanos.longValue() - (this.timeProvider.currentTimeNanos() - this.detectionTimerStartNanos.longValue())));
            }
            SynchronizationContext.ScheduledHandle scheduledHandle = this.detectionTimerHandle;
            if (scheduledHandle != null) {
                scheduledHandle.cancel();
                this.trackerMap.resetCallCounters();
            }
            this.detectionTimerHandle = this.syncContext.scheduleWithFixedDelay(new DetectionTimer(outlierDetectionLoadBalancerConfig, this.logger), l.longValue(), outlierDetectionLoadBalancerConfig.intervalNanos.longValue(), TimeUnit.NANOSECONDS, this.timeService);
        } else {
            SynchronizationContext.ScheduledHandle scheduledHandle2 = this.detectionTimerHandle;
            if (scheduledHandle2 != null) {
                scheduledHandle2.cancel();
                this.detectionTimerStartNanos = null;
                this.trackerMap.cancelTracking();
            }
        }
        this.switchLb.handleResolvedAddresses(resolvedAddresses.toBuilder().setLoadBalancingPolicyConfig(outlierDetectionLoadBalancerConfig.childPolicy.getConfig()).build());
        return Status.OK;
    }

    public void handleNameResolutionError(Status status) {
        this.switchLb.handleNameResolutionError(status);
    }

    public void shutdown() {
        this.switchLb.shutdown();
    }

    class DetectionTimer implements Runnable {
        OutlierDetectionLoadBalancerConfig config;
        ChannelLogger logger;

        DetectionTimer(OutlierDetectionLoadBalancerConfig outlierDetectionLoadBalancerConfig, ChannelLogger channelLogger) {
            this.config = outlierDetectionLoadBalancerConfig;
            this.logger = channelLogger;
        }

        public void run() {
            OutlierDetectionLoadBalancer outlierDetectionLoadBalancer = OutlierDetectionLoadBalancer.this;
            Long unused = outlierDetectionLoadBalancer.detectionTimerStartNanos = Long.valueOf(outlierDetectionLoadBalancer.timeProvider.currentTimeNanos());
            OutlierDetectionLoadBalancer.this.trackerMap.swapCounters();
            for (OutlierEjectionAlgorithm ejectOutliers : OutlierEjectionAlgorithm.forConfig(this.config, this.logger)) {
                ejectOutliers.ejectOutliers(OutlierDetectionLoadBalancer.this.trackerMap, OutlierDetectionLoadBalancer.this.detectionTimerStartNanos.longValue());
            }
            OutlierDetectionLoadBalancer.this.trackerMap.maybeUnejectOutliers(OutlierDetectionLoadBalancer.this.detectionTimerStartNanos);
        }
    }

    class ChildHelper extends ForwardingLoadBalancerHelper {
        private LoadBalancer.Helper delegate;

        ChildHelper(LoadBalancer.Helper helper) {
            this.delegate = new HealthProducerHelper(helper);
        }

        /* access modifiers changed from: protected */
        public LoadBalancer.Helper delegate() {
            return this.delegate;
        }

        public LoadBalancer.Subchannel createSubchannel(LoadBalancer.CreateSubchannelArgs createSubchannelArgs) {
            OutlierDetectionSubchannel outlierDetectionSubchannel = new OutlierDetectionSubchannel(createSubchannelArgs, this.delegate);
            List<EquivalentAddressGroup> addresses = createSubchannelArgs.getAddresses();
            if (OutlierDetectionLoadBalancer.hasSingleAddress(addresses) && OutlierDetectionLoadBalancer.this.trackerMap.containsKey(addresses.get(0).getAddresses().get(0))) {
                AddressTracker addressTracker = (AddressTracker) OutlierDetectionLoadBalancer.this.trackerMap.get(addresses.get(0).getAddresses().get(0));
                addressTracker.addSubchannel(outlierDetectionSubchannel);
                if (addressTracker.ejectionTimeNanos != null) {
                    outlierDetectionSubchannel.eject();
                }
            }
            return outlierDetectionSubchannel;
        }

        public void updateBalancingState(ConnectivityState connectivityState, LoadBalancer.SubchannelPicker subchannelPicker) {
            this.delegate.updateBalancingState(connectivityState, new OutlierDetectionPicker(subchannelPicker));
        }
    }

    class OutlierDetectionSubchannel extends ForwardingSubchannel {
        private AddressTracker addressTracker;
        private final LoadBalancer.Subchannel delegate;
        /* access modifiers changed from: private */
        public boolean ejected;
        /* access modifiers changed from: private */
        public ConnectivityStateInfo lastSubchannelState;
        private final ChannelLogger logger;
        private LoadBalancer.SubchannelStateListener subchannelStateListener;

        OutlierDetectionSubchannel(LoadBalancer.CreateSubchannelArgs createSubchannelArgs, LoadBalancer.Helper helper) {
            LoadBalancer.SubchannelStateListener subchannelStateListener2 = (LoadBalancer.SubchannelStateListener) createSubchannelArgs.getOption(LoadBalancer.HEALTH_CONSUMER_LISTENER_ARG_KEY);
            if (subchannelStateListener2 != null) {
                this.subchannelStateListener = subchannelStateListener2;
                this.delegate = helper.createSubchannel(createSubchannelArgs.toBuilder().addOption(LoadBalancer.HEALTH_CONSUMER_LISTENER_ARG_KEY, new OutlierDetectionSubchannelStateListener(subchannelStateListener2)).build());
            } else {
                this.delegate = helper.createSubchannel(createSubchannelArgs);
            }
            this.logger = this.delegate.getChannelLogger();
        }

        public void start(LoadBalancer.SubchannelStateListener subchannelStateListener2) {
            if (this.subchannelStateListener != null) {
                super.start(subchannelStateListener2);
                return;
            }
            this.subchannelStateListener = subchannelStateListener2;
            super.start(new OutlierDetectionSubchannelStateListener(subchannelStateListener2));
        }

        public void shutdown() {
            AddressTracker addressTracker2 = this.addressTracker;
            if (addressTracker2 != null) {
                addressTracker2.removeSubchannel(this);
            }
            super.shutdown();
        }

        public Attributes getAttributes() {
            if (this.addressTracker != null) {
                return this.delegate.getAttributes().toBuilder().set(OutlierDetectionLoadBalancer.ADDRESS_TRACKER_ATTR_KEY, this.addressTracker).build();
            }
            return this.delegate.getAttributes();
        }

        public void updateAddresses(List<EquivalentAddressGroup> list) {
            if (OutlierDetectionLoadBalancer.hasSingleAddress(getAllAddresses()) && OutlierDetectionLoadBalancer.hasSingleAddress(list)) {
                if (OutlierDetectionLoadBalancer.this.trackerMap.containsValue(this.addressTracker)) {
                    this.addressTracker.removeSubchannel(this);
                }
                SocketAddress socketAddress = list.get(0).getAddresses().get(0);
                if (OutlierDetectionLoadBalancer.this.trackerMap.containsKey(socketAddress)) {
                    ((AddressTracker) OutlierDetectionLoadBalancer.this.trackerMap.get(socketAddress)).addSubchannel(this);
                }
            } else if (!OutlierDetectionLoadBalancer.hasSingleAddress(getAllAddresses()) || OutlierDetectionLoadBalancer.hasSingleAddress(list)) {
                if (!OutlierDetectionLoadBalancer.hasSingleAddress(getAllAddresses()) && OutlierDetectionLoadBalancer.hasSingleAddress(list)) {
                    SocketAddress socketAddress2 = list.get(0).getAddresses().get(0);
                    if (OutlierDetectionLoadBalancer.this.trackerMap.containsKey(socketAddress2)) {
                        ((AddressTracker) OutlierDetectionLoadBalancer.this.trackerMap.get(socketAddress2)).addSubchannel(this);
                    }
                }
            } else if (OutlierDetectionLoadBalancer.this.trackerMap.containsKey(getAddresses().getAddresses().get(0))) {
                AddressTracker addressTracker2 = (AddressTracker) OutlierDetectionLoadBalancer.this.trackerMap.get(getAddresses().getAddresses().get(0));
                addressTracker2.removeSubchannel(this);
                addressTracker2.resetCallCounters();
            }
            this.delegate.updateAddresses(list);
        }

        /* access modifiers changed from: package-private */
        public void setAddressTracker(AddressTracker addressTracker2) {
            this.addressTracker = addressTracker2;
        }

        /* access modifiers changed from: package-private */
        public void clearAddressTracker() {
            this.addressTracker = null;
        }

        /* access modifiers changed from: package-private */
        public void eject() {
            this.ejected = true;
            this.subchannelStateListener.onSubchannelState(ConnectivityStateInfo.forTransientFailure(Status.UNAVAILABLE));
            this.logger.log(ChannelLogger.ChannelLogLevel.INFO, "Subchannel ejected: {0}", this);
        }

        /* access modifiers changed from: package-private */
        public void uneject() {
            this.ejected = false;
            ConnectivityStateInfo connectivityStateInfo = this.lastSubchannelState;
            if (connectivityStateInfo != null) {
                this.subchannelStateListener.onSubchannelState(connectivityStateInfo);
                this.logger.log(ChannelLogger.ChannelLogLevel.INFO, "Subchannel unejected: {0}", this);
            }
        }

        /* access modifiers changed from: package-private */
        public boolean isEjected() {
            return this.ejected;
        }

        /* access modifiers changed from: protected */
        public LoadBalancer.Subchannel delegate() {
            return this.delegate;
        }

        class OutlierDetectionSubchannelStateListener implements LoadBalancer.SubchannelStateListener {
            private final LoadBalancer.SubchannelStateListener delegate;

            OutlierDetectionSubchannelStateListener(LoadBalancer.SubchannelStateListener subchannelStateListener) {
                this.delegate = subchannelStateListener;
            }

            public void onSubchannelState(ConnectivityStateInfo connectivityStateInfo) {
                ConnectivityStateInfo unused = OutlierDetectionSubchannel.this.lastSubchannelState = connectivityStateInfo;
                if (!OutlierDetectionSubchannel.this.ejected) {
                    this.delegate.onSubchannelState(connectivityStateInfo);
                }
            }
        }

        public String toString() {
            return "OutlierDetectionSubchannel{addresses=" + this.delegate.getAllAddresses() + AbstractJsonLexerKt.END_OBJ;
        }
    }

    class OutlierDetectionPicker extends LoadBalancer.SubchannelPicker {
        private final LoadBalancer.SubchannelPicker delegate;

        OutlierDetectionPicker(LoadBalancer.SubchannelPicker subchannelPicker) {
            this.delegate = subchannelPicker;
        }

        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            LoadBalancer.PickResult pickSubchannel = this.delegate.pickSubchannel(pickSubchannelArgs);
            LoadBalancer.Subchannel subchannel = pickSubchannel.getSubchannel();
            return subchannel != null ? LoadBalancer.PickResult.withSubchannel(subchannel, new ResultCountingClientStreamTracerFactory((AddressTracker) subchannel.getAttributes().get(OutlierDetectionLoadBalancer.ADDRESS_TRACKER_ATTR_KEY), pickSubchannel.getStreamTracerFactory())) : pickSubchannel;
        }

        class ResultCountingClientStreamTracerFactory extends ClientStreamTracer.Factory {
            @Nullable
            private final ClientStreamTracer.Factory delegateFactory;
            /* access modifiers changed from: private */
            public final AddressTracker tracker;

            ResultCountingClientStreamTracerFactory(AddressTracker addressTracker, @Nullable ClientStreamTracer.Factory factory) {
                this.tracker = addressTracker;
                this.delegateFactory = factory;
            }

            public ClientStreamTracer newClientStreamTracer(ClientStreamTracer.StreamInfo streamInfo, Metadata metadata) {
                ClientStreamTracer.Factory factory = this.delegateFactory;
                if (factory == null) {
                    return new ClientStreamTracer() {
                        public void streamClosed(Status status) {
                            ResultCountingClientStreamTracerFactory.this.tracker.incrementCallCount(status.isOk());
                        }
                    };
                }
                final ClientStreamTracer newClientStreamTracer = factory.newClientStreamTracer(streamInfo, metadata);
                return new ForwardingClientStreamTracer() {
                    /* access modifiers changed from: protected */
                    public ClientStreamTracer delegate() {
                        return newClientStreamTracer;
                    }

                    public void streamClosed(Status status) {
                        ResultCountingClientStreamTracerFactory.this.tracker.incrementCallCount(status.isOk());
                        delegate().streamClosed(status);
                    }
                };
            }
        }
    }

    static class AddressTracker {
        private volatile CallCounter activeCallCounter = new CallCounter();
        private OutlierDetectionLoadBalancerConfig config;
        private int ejectionTimeMultiplier;
        /* access modifiers changed from: private */
        public Long ejectionTimeNanos;
        private CallCounter inactiveCallCounter = new CallCounter();
        private final Set<OutlierDetectionSubchannel> subchannels = new HashSet();

        AddressTracker(OutlierDetectionLoadBalancerConfig outlierDetectionLoadBalancerConfig) {
            this.config = outlierDetectionLoadBalancerConfig;
        }

        /* access modifiers changed from: package-private */
        public void setConfig(OutlierDetectionLoadBalancerConfig outlierDetectionLoadBalancerConfig) {
            this.config = outlierDetectionLoadBalancerConfig;
        }

        /* access modifiers changed from: package-private */
        public boolean addSubchannel(OutlierDetectionSubchannel outlierDetectionSubchannel) {
            if (subchannelsEjected() && !outlierDetectionSubchannel.isEjected()) {
                outlierDetectionSubchannel.eject();
            } else if (!subchannelsEjected() && outlierDetectionSubchannel.isEjected()) {
                outlierDetectionSubchannel.uneject();
            }
            outlierDetectionSubchannel.setAddressTracker(this);
            return this.subchannels.add(outlierDetectionSubchannel);
        }

        /* access modifiers changed from: package-private */
        public boolean removeSubchannel(OutlierDetectionSubchannel outlierDetectionSubchannel) {
            outlierDetectionSubchannel.clearAddressTracker();
            return this.subchannels.remove(outlierDetectionSubchannel);
        }

        /* access modifiers changed from: package-private */
        public boolean containsSubchannel(OutlierDetectionSubchannel outlierDetectionSubchannel) {
            return this.subchannels.contains(outlierDetectionSubchannel);
        }

        /* access modifiers changed from: package-private */
        public Set<OutlierDetectionSubchannel> getSubchannels() {
            return ImmutableSet.copyOf(this.subchannels);
        }

        /* access modifiers changed from: package-private */
        public void incrementCallCount(boolean z) {
            if (this.config.successRateEjection != null || this.config.failurePercentageEjection != null) {
                if (z) {
                    this.activeCallCounter.successCount.getAndIncrement();
                } else {
                    this.activeCallCounter.failureCount.getAndIncrement();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public long activeVolume() {
            return this.activeCallCounter.successCount.get() + this.activeCallCounter.failureCount.get();
        }

        /* access modifiers changed from: package-private */
        public long inactiveVolume() {
            return this.inactiveCallCounter.successCount.get() + this.inactiveCallCounter.failureCount.get();
        }

        /* access modifiers changed from: package-private */
        public double successRate() {
            return ((double) this.inactiveCallCounter.successCount.get()) / ((double) inactiveVolume());
        }

        /* access modifiers changed from: package-private */
        public double failureRate() {
            return ((double) this.inactiveCallCounter.failureCount.get()) / ((double) inactiveVolume());
        }

        /* access modifiers changed from: package-private */
        public void resetCallCounters() {
            this.activeCallCounter.reset();
            this.inactiveCallCounter.reset();
        }

        /* access modifiers changed from: package-private */
        public void decrementEjectionTimeMultiplier() {
            int i = this.ejectionTimeMultiplier;
            this.ejectionTimeMultiplier = i == 0 ? 0 : i - 1;
        }

        /* access modifiers changed from: package-private */
        public void resetEjectionTimeMultiplier() {
            this.ejectionTimeMultiplier = 0;
        }

        /* access modifiers changed from: package-private */
        public void swapCounters() {
            this.inactiveCallCounter.reset();
            CallCounter callCounter = this.activeCallCounter;
            this.activeCallCounter = this.inactiveCallCounter;
            this.inactiveCallCounter = callCounter;
        }

        /* access modifiers changed from: package-private */
        public void ejectSubchannels(long j) {
            this.ejectionTimeNanos = Long.valueOf(j);
            this.ejectionTimeMultiplier++;
            for (OutlierDetectionSubchannel eject : this.subchannels) {
                eject.eject();
            }
        }

        /* access modifiers changed from: package-private */
        public void unejectSubchannels() {
            Preconditions.checkState(this.ejectionTimeNanos != null, "not currently ejected");
            this.ejectionTimeNanos = null;
            for (OutlierDetectionSubchannel uneject : this.subchannels) {
                uneject.uneject();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean subchannelsEjected() {
            return this.ejectionTimeNanos != null;
        }

        public boolean maxEjectionTimeElapsed(long j) {
            return j > this.ejectionTimeNanos.longValue() + Math.min(this.config.baseEjectionTimeNanos.longValue() * ((long) this.ejectionTimeMultiplier), Math.max(this.config.baseEjectionTimeNanos.longValue(), this.config.maxEjectionTimeNanos.longValue()));
        }

        private static class CallCounter {
            AtomicLong failureCount;
            AtomicLong successCount;

            private CallCounter() {
                this.successCount = new AtomicLong();
                this.failureCount = new AtomicLong();
            }

            /* access modifiers changed from: package-private */
            public void reset() {
                this.successCount.set(0);
                this.failureCount.set(0);
            }
        }

        public String toString() {
            return "AddressTracker{subchannels=" + this.subchannels + AbstractJsonLexerKt.END_OBJ;
        }
    }

    static class AddressTrackerMap extends ForwardingMap<SocketAddress, AddressTracker> {
        private final Map<SocketAddress, AddressTracker> trackerMap = new HashMap();

        AddressTrackerMap() {
        }

        /* access modifiers changed from: protected */
        public Map<SocketAddress, AddressTracker> delegate() {
            return this.trackerMap;
        }

        /* access modifiers changed from: package-private */
        public void updateTrackerConfigs(OutlierDetectionLoadBalancerConfig outlierDetectionLoadBalancerConfig) {
            for (AddressTracker config : this.trackerMap.values()) {
                config.setConfig(outlierDetectionLoadBalancerConfig);
            }
        }

        /* access modifiers changed from: package-private */
        public void putNewTrackers(OutlierDetectionLoadBalancerConfig outlierDetectionLoadBalancerConfig, Collection<SocketAddress> collection) {
            for (SocketAddress next : collection) {
                if (!this.trackerMap.containsKey(next)) {
                    this.trackerMap.put(next, new AddressTracker(outlierDetectionLoadBalancerConfig));
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void resetCallCounters() {
            for (AddressTracker resetCallCounters : this.trackerMap.values()) {
                resetCallCounters.resetCallCounters();
            }
        }

        /* access modifiers changed from: package-private */
        public void cancelTracking() {
            for (AddressTracker next : this.trackerMap.values()) {
                if (next.subchannelsEjected()) {
                    next.unejectSubchannels();
                }
                next.resetEjectionTimeMultiplier();
            }
        }

        /* access modifiers changed from: package-private */
        public void swapCounters() {
            for (AddressTracker swapCounters : this.trackerMap.values()) {
                swapCounters.swapCounters();
            }
        }

        /* access modifiers changed from: package-private */
        public void maybeUnejectOutliers(Long l) {
            for (AddressTracker next : this.trackerMap.values()) {
                if (!next.subchannelsEjected()) {
                    next.decrementEjectionTimeMultiplier();
                }
                if (next.subchannelsEjected() && next.maxEjectionTimeElapsed(l.longValue())) {
                    next.unejectSubchannels();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public double ejectionPercentage() {
            if (this.trackerMap.isEmpty()) {
                return 0.0d;
            }
            int i = 0;
            int i2 = 0;
            for (AddressTracker subchannelsEjected : this.trackerMap.values()) {
                i2++;
                if (subchannelsEjected.subchannelsEjected()) {
                    i++;
                }
            }
            return (((double) i) / ((double) i2)) * 100.0d;
        }
    }

    interface OutlierEjectionAlgorithm {
        void ejectOutliers(AddressTrackerMap addressTrackerMap, long j);

        @Nullable
        static List<OutlierEjectionAlgorithm> forConfig(OutlierDetectionLoadBalancerConfig outlierDetectionLoadBalancerConfig, ChannelLogger channelLogger) {
            ImmutableList.Builder builder = ImmutableList.builder();
            if (outlierDetectionLoadBalancerConfig.successRateEjection != null) {
                builder.add((Object) new SuccessRateOutlierEjectionAlgorithm(outlierDetectionLoadBalancerConfig, channelLogger));
            }
            if (outlierDetectionLoadBalancerConfig.failurePercentageEjection != null) {
                builder.add((Object) new FailurePercentageOutlierEjectionAlgorithm(outlierDetectionLoadBalancerConfig, channelLogger));
            }
            return builder.build();
        }
    }

    static class SuccessRateOutlierEjectionAlgorithm implements OutlierEjectionAlgorithm {
        private final OutlierDetectionLoadBalancerConfig config;
        private final ChannelLogger logger;

        SuccessRateOutlierEjectionAlgorithm(OutlierDetectionLoadBalancerConfig outlierDetectionLoadBalancerConfig, ChannelLogger channelLogger) {
            Preconditions.checkArgument(outlierDetectionLoadBalancerConfig.successRateEjection != null, "success rate ejection config is null");
            this.config = outlierDetectionLoadBalancerConfig;
            this.logger = channelLogger;
        }

        public void ejectOutliers(AddressTrackerMap addressTrackerMap, long j) {
            List<AddressTracker> access$900 = OutlierDetectionLoadBalancer.trackersWithVolume(addressTrackerMap, this.config.successRateEjection.requestVolume.intValue());
            if (access$900.size() >= this.config.successRateEjection.minimumHosts.intValue() && access$900.size() != 0) {
                ArrayList arrayList = new ArrayList();
                for (AddressTracker successRate : access$900) {
                    arrayList.add(Double.valueOf(successRate.successRate()));
                }
                double mean = mean(arrayList);
                double standardDeviation = standardDeviation(arrayList, mean);
                double intValue = mean - (((double) (((float) this.config.successRateEjection.stdevFactor.intValue()) / 1000.0f)) * standardDeviation);
                for (AddressTracker addressTracker : access$900) {
                    if (addressTrackerMap.ejectionPercentage() < ((double) this.config.maxEjectionPercent.intValue())) {
                        if (addressTracker.successRate() < intValue) {
                            this.logger.log(ChannelLogger.ChannelLogLevel.DEBUG, "SuccessRate algorithm detected outlier: {0}. Parameters: successRate={1}, mean={2}, stdev={3}, requiredSuccessRate={4}", addressTracker, Double.valueOf(addressTracker.successRate()), Double.valueOf(mean), Double.valueOf(standardDeviation), Double.valueOf(intValue));
                            if (new Random().nextInt(100) < this.config.successRateEjection.enforcementPercentage.intValue()) {
                                addressTracker.ejectSubchannels(j);
                            }
                        }
                        long j2 = j;
                    } else {
                        return;
                    }
                }
            }
        }

        static double mean(Collection<Double> collection) {
            double d = 0.0d;
            for (Double doubleValue : collection) {
                d += doubleValue.doubleValue();
            }
            return d / ((double) collection.size());
        }

        static double standardDeviation(Collection<Double> collection, double d) {
            double d2 = 0.0d;
            for (Double doubleValue : collection) {
                double doubleValue2 = doubleValue.doubleValue() - d;
                d2 += doubleValue2 * doubleValue2;
            }
            return Math.sqrt(d2 / ((double) collection.size()));
        }
    }

    static class FailurePercentageOutlierEjectionAlgorithm implements OutlierEjectionAlgorithm {
        private final OutlierDetectionLoadBalancerConfig config;
        private final ChannelLogger logger;

        FailurePercentageOutlierEjectionAlgorithm(OutlierDetectionLoadBalancerConfig outlierDetectionLoadBalancerConfig, ChannelLogger channelLogger) {
            this.config = outlierDetectionLoadBalancerConfig;
            this.logger = channelLogger;
        }

        public void ejectOutliers(AddressTrackerMap addressTrackerMap, long j) {
            List<AddressTracker> access$900 = OutlierDetectionLoadBalancer.trackersWithVolume(addressTrackerMap, this.config.failurePercentageEjection.requestVolume.intValue());
            if (access$900.size() >= this.config.failurePercentageEjection.minimumHosts.intValue() && access$900.size() != 0) {
                for (AddressTracker addressTracker : access$900) {
                    if (addressTrackerMap.ejectionPercentage() < ((double) this.config.maxEjectionPercent.intValue())) {
                        if (addressTracker.inactiveVolume() >= ((long) this.config.failurePercentageEjection.requestVolume.intValue())) {
                            if (addressTracker.failureRate() > ((double) this.config.failurePercentageEjection.threshold.intValue()) / 100.0d) {
                                this.logger.log(ChannelLogger.ChannelLogLevel.DEBUG, "FailurePercentage algorithm detected outlier: {0}, failureRate={1}", addressTracker, Double.valueOf(addressTracker.failureRate()));
                                if (new Random().nextInt(100) < this.config.failurePercentageEjection.enforcementPercentage.intValue()) {
                                    addressTracker.ejectSubchannels(j);
                                }
                            }
                        }
                    } else {
                        return;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static List<AddressTracker> trackersWithVolume(AddressTrackerMap addressTrackerMap, int i) {
        ArrayList arrayList = new ArrayList();
        for (AddressTracker addressTracker : addressTrackerMap.values()) {
            if (addressTracker.inactiveVolume() >= ((long) i)) {
                arrayList.add(addressTracker);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public static boolean hasSingleAddress(List<EquivalentAddressGroup> list) {
        int i = 0;
        for (EquivalentAddressGroup addresses : list) {
            i += addresses.getAddresses().size();
            if (i > 1) {
                return false;
            }
        }
        return true;
    }

    public static final class OutlierDetectionLoadBalancerConfig {
        public final Long baseEjectionTimeNanos;
        public final ServiceConfigUtil.PolicySelection childPolicy;
        public final FailurePercentageEjection failurePercentageEjection;
        public final Long intervalNanos;
        public final Integer maxEjectionPercent;
        public final Long maxEjectionTimeNanos;
        public final SuccessRateEjection successRateEjection;

        private OutlierDetectionLoadBalancerConfig(Long l, Long l2, Long l3, Integer num, SuccessRateEjection successRateEjection2, FailurePercentageEjection failurePercentageEjection2, ServiceConfigUtil.PolicySelection policySelection) {
            this.intervalNanos = l;
            this.baseEjectionTimeNanos = l2;
            this.maxEjectionTimeNanos = l3;
            this.maxEjectionPercent = num;
            this.successRateEjection = successRateEjection2;
            this.failurePercentageEjection = failurePercentageEjection2;
            this.childPolicy = policySelection;
        }

        public static class Builder {
            Long baseEjectionTimeNanos = 30000000000L;
            ServiceConfigUtil.PolicySelection childPolicy;
            FailurePercentageEjection failurePercentageEjection;
            Long intervalNanos = Long.valueOf(RealConnection.IDLE_CONNECTION_HEALTHY_NS);
            Integer maxEjectionPercent = 10;
            Long maxEjectionTimeNanos = 300000000000L;
            SuccessRateEjection successRateEjection;

            public Builder setIntervalNanos(Long l) {
                Preconditions.checkArgument(l != null);
                this.intervalNanos = l;
                return this;
            }

            public Builder setBaseEjectionTimeNanos(Long l) {
                Preconditions.checkArgument(l != null);
                this.baseEjectionTimeNanos = l;
                return this;
            }

            public Builder setMaxEjectionTimeNanos(Long l) {
                Preconditions.checkArgument(l != null);
                this.maxEjectionTimeNanos = l;
                return this;
            }

            public Builder setMaxEjectionPercent(Integer num) {
                Preconditions.checkArgument(num != null);
                this.maxEjectionPercent = num;
                return this;
            }

            public Builder setSuccessRateEjection(SuccessRateEjection successRateEjection2) {
                this.successRateEjection = successRateEjection2;
                return this;
            }

            public Builder setFailurePercentageEjection(FailurePercentageEjection failurePercentageEjection2) {
                this.failurePercentageEjection = failurePercentageEjection2;
                return this;
            }

            public Builder setChildPolicy(ServiceConfigUtil.PolicySelection policySelection) {
                Preconditions.checkState(policySelection != null);
                this.childPolicy = policySelection;
                return this;
            }

            public OutlierDetectionLoadBalancerConfig build() {
                Preconditions.checkState(this.childPolicy != null);
                return new OutlierDetectionLoadBalancerConfig(this.intervalNanos, this.baseEjectionTimeNanos, this.maxEjectionTimeNanos, this.maxEjectionPercent, this.successRateEjection, this.failurePercentageEjection, this.childPolicy);
            }
        }

        public static class SuccessRateEjection {
            public final Integer enforcementPercentage;
            public final Integer minimumHosts;
            public final Integer requestVolume;
            public final Integer stdevFactor;

            SuccessRateEjection(Integer num, Integer num2, Integer num3, Integer num4) {
                this.stdevFactor = num;
                this.enforcementPercentage = num2;
                this.minimumHosts = num3;
                this.requestVolume = num4;
            }

            public static final class Builder {
                Integer enforcementPercentage = 100;
                Integer minimumHosts = 5;
                Integer requestVolume = 100;
                Integer stdevFactor = 1900;

                public Builder setStdevFactor(Integer num) {
                    Preconditions.checkArgument(num != null);
                    this.stdevFactor = num;
                    return this;
                }

                public Builder setEnforcementPercentage(Integer num) {
                    boolean z = true;
                    Preconditions.checkArgument(num != null);
                    if (num.intValue() < 0 || num.intValue() > 100) {
                        z = false;
                    }
                    Preconditions.checkArgument(z);
                    this.enforcementPercentage = num;
                    return this;
                }

                public Builder setMinimumHosts(Integer num) {
                    boolean z = true;
                    Preconditions.checkArgument(num != null);
                    if (num.intValue() < 0) {
                        z = false;
                    }
                    Preconditions.checkArgument(z);
                    this.minimumHosts = num;
                    return this;
                }

                public Builder setRequestVolume(Integer num) {
                    boolean z = true;
                    Preconditions.checkArgument(num != null);
                    if (num.intValue() < 0) {
                        z = false;
                    }
                    Preconditions.checkArgument(z);
                    this.requestVolume = num;
                    return this;
                }

                public SuccessRateEjection build() {
                    return new SuccessRateEjection(this.stdevFactor, this.enforcementPercentage, this.minimumHosts, this.requestVolume);
                }
            }
        }

        public static class FailurePercentageEjection {
            public final Integer enforcementPercentage;
            public final Integer minimumHosts;
            public final Integer requestVolume;
            public final Integer threshold;

            FailurePercentageEjection(Integer num, Integer num2, Integer num3, Integer num4) {
                this.threshold = num;
                this.enforcementPercentage = num2;
                this.minimumHosts = num3;
                this.requestVolume = num4;
            }

            public static class Builder {
                Integer enforcementPercentage = 100;
                Integer minimumHosts = 5;
                Integer requestVolume = 50;
                Integer threshold = 85;

                public Builder setThreshold(Integer num) {
                    boolean z = true;
                    Preconditions.checkArgument(num != null);
                    if (num.intValue() < 0 || num.intValue() > 100) {
                        z = false;
                    }
                    Preconditions.checkArgument(z);
                    this.threshold = num;
                    return this;
                }

                public Builder setEnforcementPercentage(Integer num) {
                    boolean z = true;
                    Preconditions.checkArgument(num != null);
                    if (num.intValue() < 0 || num.intValue() > 100) {
                        z = false;
                    }
                    Preconditions.checkArgument(z);
                    this.enforcementPercentage = num;
                    return this;
                }

                public Builder setMinimumHosts(Integer num) {
                    boolean z = true;
                    Preconditions.checkArgument(num != null);
                    if (num.intValue() < 0) {
                        z = false;
                    }
                    Preconditions.checkArgument(z);
                    this.minimumHosts = num;
                    return this;
                }

                public Builder setRequestVolume(Integer num) {
                    boolean z = true;
                    Preconditions.checkArgument(num != null);
                    if (num.intValue() < 0) {
                        z = false;
                    }
                    Preconditions.checkArgument(z);
                    this.requestVolume = num;
                    return this;
                }

                public FailurePercentageEjection build() {
                    return new FailurePercentageEjection(this.threshold, this.enforcementPercentage, this.minimumHosts, this.requestVolume);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean outlierDetectionEnabled() {
            return (this.successRateEjection == null && this.failurePercentageEjection == null) ? false : true;
        }
    }
}
