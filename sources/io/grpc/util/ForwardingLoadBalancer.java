package io.grpc.util;

import com.google.common.base.MoreObjects;
import io.grpc.ConnectivityStateInfo;
import io.grpc.LoadBalancer;
import io.grpc.Status;

public abstract class ForwardingLoadBalancer extends LoadBalancer {
    /* access modifiers changed from: protected */
    public abstract LoadBalancer delegate();

    public void handleResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
        delegate().handleResolvedAddresses(resolvedAddresses);
    }

    public void handleNameResolutionError(Status status) {
        delegate().handleNameResolutionError(status);
    }

    @Deprecated
    public void handleSubchannelState(LoadBalancer.Subchannel subchannel, ConnectivityStateInfo connectivityStateInfo) {
        delegate().handleSubchannelState(subchannel, connectivityStateInfo);
    }

    public void shutdown() {
        delegate().shutdown();
    }

    public boolean canHandleEmptyAddressListFromNameResolution() {
        return delegate().canHandleEmptyAddressListFromNameResolution();
    }

    public void requestConnection() {
        delegate().requestConnection();
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("delegate", (Object) delegate()).toString();
    }
}
