package io.grpc.util;

import com.google.common.base.Preconditions;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.LoadBalancer;
import io.grpc.Status;
import javax.annotation.Nullable;

public final class GracefulSwitchLoadBalancer extends ForwardingLoadBalancer {
    static final LoadBalancer.SubchannelPicker BUFFER_PICKER = new LoadBalancer.SubchannelPicker() {
        public String toString() {
            return "BUFFER_PICKER";
        }

        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            return LoadBalancer.PickResult.withNoResult();
        }
    };
    @Nullable
    private LoadBalancer.Factory currentBalancerFactory;
    /* access modifiers changed from: private */
    public LoadBalancer currentLb;
    /* access modifiers changed from: private */
    public boolean currentLbIsReady;
    /* access modifiers changed from: private */
    public final LoadBalancer defaultBalancer;
    /* access modifiers changed from: private */
    public final LoadBalancer.Helper helper;
    @Nullable
    private LoadBalancer.Factory pendingBalancerFactory;
    /* access modifiers changed from: private */
    public LoadBalancer pendingLb;
    /* access modifiers changed from: private */
    public LoadBalancer.SubchannelPicker pendingPicker;
    /* access modifiers changed from: private */
    public ConnectivityState pendingState;

    public GracefulSwitchLoadBalancer(LoadBalancer.Helper helper2) {
        AnonymousClass1 r0 = new LoadBalancer() {
            public void shutdown() {
            }

            public void handleResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
                throw new IllegalStateException("GracefulSwitchLoadBalancer must switch to a load balancing policy before handling ResolvedAddresses");
            }

            public void handleNameResolutionError(Status status) {
                GracefulSwitchLoadBalancer.this.helper.updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, new LoadBalancer.FixedResultPicker(LoadBalancer.PickResult.withError(status)));
            }
        };
        this.defaultBalancer = r0;
        this.currentLb = r0;
        this.pendingLb = r0;
        this.helper = (LoadBalancer.Helper) Preconditions.checkNotNull(helper2, "helper");
    }

    public void switchTo(LoadBalancer.Factory factory) {
        Preconditions.checkNotNull(factory, "newBalancerFactory");
        if (!factory.equals(this.pendingBalancerFactory)) {
            this.pendingLb.shutdown();
            this.pendingLb = this.defaultBalancer;
            this.pendingBalancerFactory = null;
            this.pendingState = ConnectivityState.CONNECTING;
            this.pendingPicker = BUFFER_PICKER;
            if (!factory.equals(this.currentBalancerFactory)) {
                AnonymousClass1PendingHelper r0 = new ForwardingLoadBalancerHelper() {
                    LoadBalancer lb;

                    /* access modifiers changed from: protected */
                    public LoadBalancer.Helper delegate() {
                        return GracefulSwitchLoadBalancer.this.helper;
                    }

                    public void updateBalancingState(ConnectivityState connectivityState, LoadBalancer.SubchannelPicker subchannelPicker) {
                        if (this.lb == GracefulSwitchLoadBalancer.this.pendingLb) {
                            Preconditions.checkState(GracefulSwitchLoadBalancer.this.currentLbIsReady, "there's pending lb while current lb has been out of READY");
                            ConnectivityState unused = GracefulSwitchLoadBalancer.this.pendingState = connectivityState;
                            LoadBalancer.SubchannelPicker unused2 = GracefulSwitchLoadBalancer.this.pendingPicker = subchannelPicker;
                            if (connectivityState == ConnectivityState.READY) {
                                GracefulSwitchLoadBalancer.this.swap();
                            }
                        } else if (this.lb == GracefulSwitchLoadBalancer.this.currentLb) {
                            boolean unused3 = GracefulSwitchLoadBalancer.this.currentLbIsReady = connectivityState == ConnectivityState.READY;
                            if (GracefulSwitchLoadBalancer.this.currentLbIsReady || GracefulSwitchLoadBalancer.this.pendingLb == GracefulSwitchLoadBalancer.this.defaultBalancer) {
                                GracefulSwitchLoadBalancer.this.helper.updateBalancingState(connectivityState, subchannelPicker);
                            } else {
                                GracefulSwitchLoadBalancer.this.swap();
                            }
                        }
                    }
                };
                r0.lb = factory.newLoadBalancer(r0);
                this.pendingLb = r0.lb;
                this.pendingBalancerFactory = factory;
                if (!this.currentLbIsReady) {
                    swap();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void swap() {
        this.helper.updateBalancingState(this.pendingState, this.pendingPicker);
        this.currentLb.shutdown();
        this.currentLb = this.pendingLb;
        this.currentBalancerFactory = this.pendingBalancerFactory;
        this.pendingLb = this.defaultBalancer;
        this.pendingBalancerFactory = null;
    }

    /* access modifiers changed from: protected */
    public LoadBalancer delegate() {
        LoadBalancer loadBalancer = this.pendingLb;
        return loadBalancer == this.defaultBalancer ? this.currentLb : loadBalancer;
    }

    @Deprecated
    public void handleSubchannelState(LoadBalancer.Subchannel subchannel, ConnectivityStateInfo connectivityStateInfo) {
        throw new UnsupportedOperationException("handleSubchannelState() is not supported by " + getClass().getName());
    }

    public void shutdown() {
        this.pendingLb.shutdown();
        this.currentLb.shutdown();
    }

    public String delegateType() {
        return delegate().getClass().getSimpleName();
    }
}
