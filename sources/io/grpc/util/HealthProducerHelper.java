package io.grpc.util;

import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.ConnectivityStateInfo;
import io.grpc.LoadBalancer;

public final class HealthProducerHelper extends ForwardingLoadBalancerHelper {
    private final LoadBalancer.Helper delegate;

    public HealthProducerHelper(LoadBalancer.Helper helper) {
        this.delegate = (LoadBalancer.Helper) Preconditions.checkNotNull(helper, "helper");
    }

    public LoadBalancer.Subchannel createSubchannel(LoadBalancer.CreateSubchannelArgs createSubchannelArgs) {
        LoadBalancer.SubchannelStateListener subchannelStateListener = (LoadBalancer.SubchannelStateListener) createSubchannelArgs.getOption(LoadBalancer.HEALTH_CONSUMER_LISTENER_ARG_KEY);
        LoadBalancer.Subchannel createSubchannel = super.createSubchannel(createSubchannelArgs);
        if (!(subchannelStateListener != null && createSubchannel.getAttributes().get(LoadBalancer.HAS_HEALTH_PRODUCER_LISTENER_KEY) == null)) {
            return createSubchannel;
        }
        return new HealthProducerSubchannel(createSubchannel, subchannelStateListener);
    }

    /* access modifiers changed from: protected */
    public LoadBalancer.Helper delegate() {
        return this.delegate;
    }

    static final class HealthProducerSubchannel extends ForwardingSubchannel {
        private final LoadBalancer.Subchannel delegate;
        /* access modifiers changed from: private */
        public final LoadBalancer.SubchannelStateListener healthListener;

        HealthProducerSubchannel(LoadBalancer.Subchannel subchannel, LoadBalancer.SubchannelStateListener subchannelStateListener) {
            this.delegate = (LoadBalancer.Subchannel) Preconditions.checkNotNull(subchannel, "delegate");
            this.healthListener = (LoadBalancer.SubchannelStateListener) Preconditions.checkNotNull(subchannelStateListener, "healthListener");
        }

        public LoadBalancer.Subchannel delegate() {
            return this.delegate;
        }

        public void start(final LoadBalancer.SubchannelStateListener subchannelStateListener) {
            this.delegate.start(new LoadBalancer.SubchannelStateListener() {
                public void onSubchannelState(ConnectivityStateInfo connectivityStateInfo) {
                    subchannelStateListener.onSubchannelState(connectivityStateInfo);
                    HealthProducerSubchannel.this.healthListener.onSubchannelState(connectivityStateInfo);
                }
            });
        }

        public Attributes getAttributes() {
            return super.getAttributes().toBuilder().set(LoadBalancer.HAS_HEALTH_PRODUCER_LISTENER_KEY, Boolean.TRUE).build();
        }
    }
}
