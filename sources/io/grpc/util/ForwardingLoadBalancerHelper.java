package io.grpc.util;

import com.google.common.base.MoreObjects;
import io.grpc.ChannelCredentials;
import io.grpc.ChannelLogger;
import io.grpc.ConnectivityState;
import io.grpc.EquivalentAddressGroup;
import io.grpc.LoadBalancer;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.NameResolver;
import io.grpc.NameResolverRegistry;
import io.grpc.SynchronizationContext;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

public abstract class ForwardingLoadBalancerHelper extends LoadBalancer.Helper {
    /* access modifiers changed from: protected */
    public abstract LoadBalancer.Helper delegate();

    public LoadBalancer.Subchannel createSubchannel(LoadBalancer.CreateSubchannelArgs createSubchannelArgs) {
        return delegate().createSubchannel(createSubchannelArgs);
    }

    public ManagedChannel createOobChannel(EquivalentAddressGroup equivalentAddressGroup, String str) {
        return delegate().createOobChannel(equivalentAddressGroup, str);
    }

    public ManagedChannel createOobChannel(List<EquivalentAddressGroup> list, String str) {
        return delegate().createOobChannel(list, str);
    }

    public void updateOobChannelAddresses(ManagedChannel managedChannel, EquivalentAddressGroup equivalentAddressGroup) {
        delegate().updateOobChannelAddresses(managedChannel, equivalentAddressGroup);
    }

    public void updateOobChannelAddresses(ManagedChannel managedChannel, List<EquivalentAddressGroup> list) {
        delegate().updateOobChannelAddresses(managedChannel, list);
    }

    @Deprecated
    public ManagedChannelBuilder<?> createResolvingOobChannelBuilder(String str) {
        return delegate().createResolvingOobChannelBuilder(str);
    }

    public ManagedChannelBuilder<?> createResolvingOobChannelBuilder(String str, ChannelCredentials channelCredentials) {
        return delegate().createResolvingOobChannelBuilder(str, channelCredentials);
    }

    public ManagedChannel createResolvingOobChannel(String str) {
        return delegate().createResolvingOobChannel(str);
    }

    public void updateBalancingState(ConnectivityState connectivityState, LoadBalancer.SubchannelPicker subchannelPicker) {
        delegate().updateBalancingState(connectivityState, subchannelPicker);
    }

    public void refreshNameResolution() {
        delegate().refreshNameResolution();
    }

    @Deprecated
    public void ignoreRefreshNameResolutionCheck() {
        delegate().ignoreRefreshNameResolutionCheck();
    }

    public String getAuthority() {
        return delegate().getAuthority();
    }

    public ChannelCredentials getChannelCredentials() {
        return delegate().getChannelCredentials();
    }

    public ChannelCredentials getUnsafeChannelCredentials() {
        return delegate().getUnsafeChannelCredentials();
    }

    public SynchronizationContext getSynchronizationContext() {
        return delegate().getSynchronizationContext();
    }

    public ScheduledExecutorService getScheduledExecutorService() {
        return delegate().getScheduledExecutorService();
    }

    public ChannelLogger getChannelLogger() {
        return delegate().getChannelLogger();
    }

    public NameResolver.Args getNameResolverArgs() {
        return delegate().getNameResolverArgs();
    }

    public NameResolverRegistry getNameResolverRegistry() {
        return delegate().getNameResolverRegistry();
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("delegate", (Object) delegate()).toString();
    }
}
