package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.EquivalentAddressGroup;
import io.grpc.LoadBalancer;
import io.grpc.Status;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

final class PickFirstLoadBalancer extends LoadBalancer {
    private ConnectivityState currentState = ConnectivityState.IDLE;
    /* access modifiers changed from: private */
    public final LoadBalancer.Helper helper;
    private LoadBalancer.Subchannel subchannel;

    PickFirstLoadBalancer(LoadBalancer.Helper helper2) {
        this.helper = (LoadBalancer.Helper) Preconditions.checkNotNull(helper2, "helper");
    }

    public Status acceptResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
        List<EquivalentAddressGroup> addresses = resolvedAddresses.getAddresses();
        if (addresses.isEmpty()) {
            Status withDescription = Status.UNAVAILABLE.withDescription("NameResolver returned no usable address. addrs=" + resolvedAddresses.getAddresses() + ", attrs=" + resolvedAddresses.getAttributes());
            handleNameResolutionError(withDescription);
            return withDescription;
        }
        if (resolvedAddresses.getLoadBalancingPolicyConfig() instanceof PickFirstLoadBalancerConfig) {
            PickFirstLoadBalancerConfig pickFirstLoadBalancerConfig = (PickFirstLoadBalancerConfig) resolvedAddresses.getLoadBalancingPolicyConfig();
            if (pickFirstLoadBalancerConfig.shuffleAddressList != null && pickFirstLoadBalancerConfig.shuffleAddressList.booleanValue()) {
                ArrayList arrayList = new ArrayList(addresses);
                Collections.shuffle(arrayList, pickFirstLoadBalancerConfig.randomSeed != null ? new Random(pickFirstLoadBalancerConfig.randomSeed.longValue()) : new Random());
                addresses = arrayList;
            }
        }
        LoadBalancer.Subchannel subchannel2 = this.subchannel;
        if (subchannel2 == null) {
            final LoadBalancer.Subchannel createSubchannel = this.helper.createSubchannel(LoadBalancer.CreateSubchannelArgs.newBuilder().setAddresses(addresses).build());
            createSubchannel.start(new LoadBalancer.SubchannelStateListener() {
                public void onSubchannelState(ConnectivityStateInfo connectivityStateInfo) {
                    PickFirstLoadBalancer.this.processSubchannelState(createSubchannel, connectivityStateInfo);
                }
            });
            this.subchannel = createSubchannel;
            updateBalancingState(ConnectivityState.CONNECTING, new Picker(LoadBalancer.PickResult.withSubchannel(createSubchannel)));
            createSubchannel.requestConnection();
        } else {
            subchannel2.updateAddresses(addresses);
        }
        return Status.OK;
    }

    public void handleNameResolutionError(Status status) {
        LoadBalancer.Subchannel subchannel2 = this.subchannel;
        if (subchannel2 != null) {
            subchannel2.shutdown();
            this.subchannel = null;
        }
        updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, new Picker(LoadBalancer.PickResult.withError(status)));
    }

    /* access modifiers changed from: private */
    public void processSubchannelState(LoadBalancer.Subchannel subchannel2, ConnectivityStateInfo connectivityStateInfo) {
        LoadBalancer.SubchannelPicker subchannelPicker;
        LoadBalancer.SubchannelPicker subchannelPicker2;
        ConnectivityState state = connectivityStateInfo.getState();
        if (state != ConnectivityState.SHUTDOWN) {
            if (state == ConnectivityState.TRANSIENT_FAILURE || state == ConnectivityState.IDLE) {
                this.helper.refreshNameResolution();
            }
            if (this.currentState == ConnectivityState.TRANSIENT_FAILURE) {
                if (state != ConnectivityState.CONNECTING) {
                    if (state == ConnectivityState.IDLE) {
                        requestConnection();
                        return;
                    }
                } else {
                    return;
                }
            }
            int i = AnonymousClass2.$SwitchMap$io$grpc$ConnectivityState[state.ordinal()];
            if (i != 1) {
                if (i == 2) {
                    subchannelPicker = new Picker(LoadBalancer.PickResult.withNoResult());
                } else if (i == 3) {
                    subchannelPicker2 = new Picker(LoadBalancer.PickResult.withSubchannel(subchannel2));
                } else if (i == 4) {
                    subchannelPicker = new Picker(LoadBalancer.PickResult.withError(connectivityStateInfo.getStatus()));
                } else {
                    throw new IllegalArgumentException("Unsupported state:" + state);
                }
                updateBalancingState(state, subchannelPicker);
            }
            subchannelPicker2 = new RequestConnectionPicker(subchannel2);
            subchannelPicker = subchannelPicker2;
            updateBalancingState(state, subchannelPicker);
        }
    }

    /* renamed from: io.grpc.internal.PickFirstLoadBalancer$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$ConnectivityState;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
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
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.PickFirstLoadBalancer.AnonymousClass2.<clinit>():void");
        }
    }

    private void updateBalancingState(ConnectivityState connectivityState, LoadBalancer.SubchannelPicker subchannelPicker) {
        this.currentState = connectivityState;
        this.helper.updateBalancingState(connectivityState, subchannelPicker);
    }

    public void shutdown() {
        LoadBalancer.Subchannel subchannel2 = this.subchannel;
        if (subchannel2 != null) {
            subchannel2.shutdown();
        }
    }

    public void requestConnection() {
        LoadBalancer.Subchannel subchannel2 = this.subchannel;
        if (subchannel2 != null) {
            subchannel2.requestConnection();
        }
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
        /* access modifiers changed from: private */
        public final LoadBalancer.Subchannel subchannel;

        RequestConnectionPicker(LoadBalancer.Subchannel subchannel2) {
            this.subchannel = (LoadBalancer.Subchannel) Preconditions.checkNotNull(subchannel2, "subchannel");
        }

        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            if (this.connectionRequested.compareAndSet(false, true)) {
                PickFirstLoadBalancer.this.helper.getSynchronizationContext().execute(new Runnable() {
                    public void run() {
                        RequestConnectionPicker.this.subchannel.requestConnection();
                    }
                });
            }
            return LoadBalancer.PickResult.withNoResult();
        }
    }

    public static final class PickFirstLoadBalancerConfig {
        @Nullable
        final Long randomSeed;
        @Nullable
        public final Boolean shuffleAddressList;

        public PickFirstLoadBalancerConfig(@Nullable Boolean bool) {
            this(bool, (Long) null);
        }

        PickFirstLoadBalancerConfig(@Nullable Boolean bool, @Nullable Long l) {
            this.shuffleAddressList = bool;
            this.randomSeed = l;
        }
    }
}
