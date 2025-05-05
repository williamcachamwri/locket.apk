package io.grpc.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import io.grpc.Attributes;
import io.grpc.ConnectivityState;
import io.grpc.EquivalentAddressGroup;
import io.grpc.LoadBalancer;
import io.grpc.LoadBalancerProvider;
import io.grpc.Status;
import io.grpc.internal.PickFirstLoadBalancerProvider;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

public abstract class MultiChildLoadBalancer extends LoadBalancer {
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(MultiChildLoadBalancer.class.getName());
    /* access modifiers changed from: private */
    public final Map<Object, ChildLbState> childLbStates = new LinkedHashMap();
    protected ConnectivityState currentConnectivityState;
    /* access modifiers changed from: private */
    public final LoadBalancer.Helper helper;
    protected final LoadBalancerProvider pickFirstLbProvider = new PickFirstLoadBalancerProvider();
    protected boolean resolvingAddresses;

    /* access modifiers changed from: protected */
    public abstract LoadBalancer.SubchannelPicker getSubchannelPicker(Map<Object, LoadBalancer.SubchannelPicker> map);

    /* access modifiers changed from: protected */
    public boolean reactivateChildOnReuse() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean reconnectOnIdle() {
        return true;
    }

    protected MultiChildLoadBalancer(LoadBalancer.Helper helper2) {
        this.helper = (LoadBalancer.Helper) Preconditions.checkNotNull(helper2, "helper");
        logger.log(Level.FINE, "Created");
    }

    /* access modifiers changed from: protected */
    public LoadBalancer.SubchannelPicker getInitialPicker() {
        return new LoadBalancer.FixedResultPicker(LoadBalancer.PickResult.withNoResult());
    }

    /* access modifiers changed from: protected */
    public LoadBalancer.SubchannelPicker getErrorPicker(Status status) {
        return new LoadBalancer.FixedResultPicker(LoadBalancer.PickResult.withError(status));
    }

    /* access modifiers changed from: protected */
    public ImmutableMap<Object, ChildLbState> getImmutableChildMap() {
        return ImmutableMap.copyOf(this.childLbStates);
    }

    /* access modifiers changed from: protected */
    public Collection<ChildLbState> getChildLbStates() {
        return this.childLbStates.values();
    }

    /* access modifiers changed from: protected */
    public ChildLbState getChildLbState(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof EquivalentAddressGroup) {
            obj = new Endpoint((EquivalentAddressGroup) obj);
        }
        return this.childLbStates.get(obj);
    }

    /* access modifiers changed from: protected */
    public ChildLbState getChildLbStateEag(EquivalentAddressGroup equivalentAddressGroup) {
        return getChildLbState(new Endpoint(equivalentAddressGroup));
    }

    /* access modifiers changed from: protected */
    public Map<Object, ChildLbState> createChildLbMap(LoadBalancer.ResolvedAddresses resolvedAddresses) {
        HashMap hashMap = new HashMap();
        for (EquivalentAddressGroup endpoint : resolvedAddresses.getAddresses()) {
            Endpoint endpoint2 = new Endpoint(endpoint);
            ChildLbState childLbState = this.childLbStates.get(endpoint2);
            if (childLbState != null) {
                hashMap.put(endpoint2, childLbState);
            } else {
                hashMap.put(endpoint2, createChildLbState(endpoint2, (Object) null, getInitialPicker(), resolvedAddresses));
            }
        }
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public ChildLbState createChildLbState(Object obj, Object obj2, LoadBalancer.SubchannelPicker subchannelPicker, LoadBalancer.ResolvedAddresses resolvedAddresses) {
        return new ChildLbState(this, obj, this.pickFirstLbProvider, obj2, subchannelPicker);
    }

    /* JADX INFO: finally extract failed */
    public Status acceptResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
        try {
            this.resolvingAddresses = true;
            AcceptResolvedAddressRetVal acceptResolvedAddressesInternal = acceptResolvedAddressesInternal(resolvedAddresses);
            if (!acceptResolvedAddressesInternal.status.isOk()) {
                Status status = acceptResolvedAddressesInternal.status;
                this.resolvingAddresses = false;
                return status;
            }
            updateOverallBalancingState();
            shutdownRemoved(acceptResolvedAddressesInternal.removedChildren);
            Status status2 = acceptResolvedAddressesInternal.status;
            this.resolvingAddresses = false;
            return status2;
        } catch (Throwable th) {
            this.resolvingAddresses = false;
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public LoadBalancer.ResolvedAddresses getChildAddresses(Object obj, LoadBalancer.ResolvedAddresses resolvedAddresses, Object obj2) {
        Endpoint endpoint;
        EquivalentAddressGroup equivalentAddressGroup;
        if (obj instanceof EquivalentAddressGroup) {
            endpoint = new Endpoint((EquivalentAddressGroup) obj);
        } else {
            Preconditions.checkArgument(obj instanceof Endpoint, "key is wrong type");
            endpoint = (Endpoint) obj;
        }
        Iterator<EquivalentAddressGroup> it = resolvedAddresses.getAddresses().iterator();
        while (true) {
            if (!it.hasNext()) {
                equivalentAddressGroup = null;
                break;
            }
            equivalentAddressGroup = it.next();
            if (endpoint.equals(new Endpoint(equivalentAddressGroup))) {
                break;
            }
        }
        Preconditions.checkNotNull(equivalentAddressGroup, obj + " no longer present in load balancer children");
        return resolvedAddresses.toBuilder().setAddresses(Collections.singletonList(equivalentAddressGroup)).setAttributes(Attributes.newBuilder().set(IS_PETIOLE_POLICY, true).build()).setLoadBalancingPolicyConfig(obj2).build();
    }

    /* access modifiers changed from: protected */
    public AcceptResolvedAddressRetVal acceptResolvedAddressesInternal(LoadBalancer.ResolvedAddresses resolvedAddresses) {
        logger.log(Level.FINE, "Received resolution result: {0}", resolvedAddresses);
        Map<Object, ChildLbState> createChildLbMap = createChildLbMap(resolvedAddresses);
        if (createChildLbMap.isEmpty()) {
            Status withDescription = Status.UNAVAILABLE.withDescription("NameResolver returned no usable address. " + resolvedAddresses);
            handleNameResolutionError(withDescription);
            return new AcceptResolvedAddressRetVal(withDescription, (List<ChildLbState>) null);
        }
        for (Map.Entry next : createChildLbMap.entrySet()) {
            Object key = next.getKey();
            LoadBalancerProvider policyProvider = ((ChildLbState) next.getValue()).getPolicyProvider();
            Object config = ((ChildLbState) next.getValue()).getConfig();
            if (!this.childLbStates.containsKey(key)) {
                this.childLbStates.put(key, (ChildLbState) next.getValue());
            } else {
                ChildLbState childLbState = this.childLbStates.get(key);
                if (childLbState.isDeactivated() && reactivateChildOnReuse()) {
                    childLbState.reactivate(policyProvider);
                }
            }
            ChildLbState childLbState2 = this.childLbStates.get(key);
            LoadBalancer.ResolvedAddresses childAddresses = getChildAddresses(key, resolvedAddresses, config);
            this.childLbStates.get(key).setResolvedAddresses(childAddresses);
            if (!childLbState2.deactivated) {
                childLbState2.lb.handleResolvedAddresses(childAddresses);
            }
        }
        ArrayList arrayList = new ArrayList();
        UnmodifiableIterator<Object> it = ImmutableList.copyOf(this.childLbStates.keySet()).iterator();
        while (it.hasNext()) {
            Object next2 = it.next();
            if (!createChildLbMap.containsKey(next2)) {
                ChildLbState childLbState3 = this.childLbStates.get(next2);
                childLbState3.deactivate();
                arrayList.add(childLbState3);
            }
        }
        return new AcceptResolvedAddressRetVal(Status.OK, arrayList);
    }

    /* access modifiers changed from: protected */
    public void shutdownRemoved(List<ChildLbState> list) {
        for (ChildLbState shutdown : list) {
            shutdown.shutdown();
        }
    }

    public void handleNameResolutionError(Status status) {
        if (this.currentConnectivityState != ConnectivityState.READY) {
            this.helper.updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, getErrorPicker(status));
        }
    }

    /* access modifiers changed from: protected */
    public void handleNameResolutionError(ChildLbState childLbState, Status status) {
        childLbState.lb.handleNameResolutionError(status);
    }

    public void shutdown() {
        logger.log(Level.FINE, "Shutdown");
        for (ChildLbState shutdown : this.childLbStates.values()) {
            shutdown.shutdown();
        }
        this.childLbStates.clear();
    }

    /* access modifiers changed from: protected */
    public void updateOverallBalancingState() {
        HashMap hashMap = new HashMap();
        ConnectivityState connectivityState = null;
        for (ChildLbState next : getChildLbStates()) {
            if (!next.deactivated) {
                hashMap.put(next.key, next.currentPicker);
                connectivityState = aggregateState(connectivityState, next.currentState);
            }
        }
        if (connectivityState != null) {
            this.helper.updateBalancingState(connectivityState, getSubchannelPicker(hashMap));
            this.currentConnectivityState = connectivityState;
        }
    }

    @Nullable
    protected static ConnectivityState aggregateState(@Nullable ConnectivityState connectivityState, ConnectivityState connectivityState2) {
        if (connectivityState == null) {
            return connectivityState2;
        }
        if (connectivityState == ConnectivityState.READY || connectivityState2 == ConnectivityState.READY) {
            return ConnectivityState.READY;
        }
        if (connectivityState == ConnectivityState.CONNECTING || connectivityState2 == ConnectivityState.CONNECTING) {
            return ConnectivityState.CONNECTING;
        }
        if (connectivityState == ConnectivityState.IDLE || connectivityState2 == ConnectivityState.IDLE) {
            return ConnectivityState.IDLE;
        }
        return connectivityState;
    }

    /* access modifiers changed from: protected */
    public LoadBalancer.Helper getHelper() {
        return this.helper;
    }

    /* access modifiers changed from: protected */
    public void removeChild(Object obj) {
        this.childLbStates.remove(obj);
    }

    /* access modifiers changed from: protected */
    public List<ChildLbState> getReadyChildren() {
        ArrayList arrayList = new ArrayList();
        for (ChildLbState next : getChildLbStates()) {
            if (!next.isDeactivated() && next.getCurrentState() == ConnectivityState.READY) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public class ChildLbState {
        private final Object config;
        /* access modifiers changed from: private */
        public LoadBalancer.SubchannelPicker currentPicker;
        /* access modifiers changed from: private */
        public ConnectivityState currentState;
        /* access modifiers changed from: private */
        public boolean deactivated;
        /* access modifiers changed from: private */
        public final Object key;
        /* access modifiers changed from: private */
        public final GracefulSwitchLoadBalancer lb;
        private final LoadBalancerProvider policyProvider;
        private LoadBalancer.ResolvedAddresses resolvedAddresses;

        public ChildLbState(MultiChildLoadBalancer multiChildLoadBalancer, Object obj, LoadBalancerProvider loadBalancerProvider, Object obj2, LoadBalancer.SubchannelPicker subchannelPicker) {
            this(obj, loadBalancerProvider, obj2, subchannelPicker, (LoadBalancer.ResolvedAddresses) null, false);
        }

        public ChildLbState(Object obj, LoadBalancerProvider loadBalancerProvider, Object obj2, LoadBalancer.SubchannelPicker subchannelPicker, LoadBalancer.ResolvedAddresses resolvedAddresses2, boolean z) {
            this.key = obj;
            this.policyProvider = loadBalancerProvider;
            this.deactivated = z;
            this.currentPicker = subchannelPicker;
            this.config = obj2;
            GracefulSwitchLoadBalancer gracefulSwitchLoadBalancer = new GracefulSwitchLoadBalancer(new ChildLbStateHelper());
            this.lb = gracefulSwitchLoadBalancer;
            this.currentState = z ? ConnectivityState.IDLE : ConnectivityState.CONNECTING;
            this.resolvedAddresses = resolvedAddresses2;
            if (!z) {
                gracefulSwitchLoadBalancer.switchTo(loadBalancerProvider);
            }
        }

        public String toString() {
            return "Address = " + this.key + ", state = " + this.currentState + ", picker type: " + this.currentPicker.getClass() + ", lb: " + this.lb.delegate().getClass() + (this.deactivated ? ", deactivated" : "");
        }

        public Object getKey() {
            return this.key;
        }

        /* access modifiers changed from: package-private */
        public Object getConfig() {
            return this.config;
        }

        /* access modifiers changed from: protected */
        public GracefulSwitchLoadBalancer getLb() {
            return this.lb;
        }

        public LoadBalancerProvider getPolicyProvider() {
            return this.policyProvider;
        }

        /* access modifiers changed from: protected */
        public LoadBalancer.Subchannel getSubchannels(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            if (getCurrentPicker() == null) {
                return null;
            }
            return getCurrentPicker().pickSubchannel(pickSubchannelArgs).getSubchannel();
        }

        public ConnectivityState getCurrentState() {
            return this.currentState;
        }

        public LoadBalancer.SubchannelPicker getCurrentPicker() {
            return this.currentPicker;
        }

        public EquivalentAddressGroup getEag() {
            LoadBalancer.ResolvedAddresses resolvedAddresses2 = this.resolvedAddresses;
            if (resolvedAddresses2 == null || resolvedAddresses2.getAddresses().isEmpty()) {
                return null;
            }
            return this.resolvedAddresses.getAddresses().get(0);
        }

        public boolean isDeactivated() {
            return this.deactivated;
        }

        /* access modifiers changed from: protected */
        public void setDeactivated() {
            this.deactivated = true;
        }

        /* access modifiers changed from: protected */
        public void markReactivated() {
            this.deactivated = false;
        }

        /* access modifiers changed from: protected */
        public void setResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses2) {
            Preconditions.checkNotNull(resolvedAddresses2, "Missing address list for child");
            this.resolvedAddresses = resolvedAddresses2;
        }

        /* access modifiers changed from: protected */
        public void deactivate() {
            if (!this.deactivated) {
                MultiChildLoadBalancer.this.childLbStates.remove(this.key);
                this.deactivated = true;
                MultiChildLoadBalancer.logger.log(Level.FINE, "Child balancer {0} deactivated", this.key);
            }
        }

        /* access modifiers changed from: protected */
        public void reactivate(LoadBalancerProvider loadBalancerProvider) {
            this.deactivated = false;
        }

        /* access modifiers changed from: protected */
        public void shutdown() {
            this.lb.shutdown();
            this.currentState = ConnectivityState.SHUTDOWN;
            MultiChildLoadBalancer.logger.log(Level.FINE, "Child balancer {0} deleted", this.key);
        }

        public LoadBalancer.ResolvedAddresses getResolvedAddresses() {
            return this.resolvedAddresses;
        }

        private final class ChildLbStateHelper extends ForwardingLoadBalancerHelper {
            private ChildLbStateHelper() {
            }

            public void updateBalancingState(ConnectivityState connectivityState, LoadBalancer.SubchannelPicker subchannelPicker) {
                if (MultiChildLoadBalancer.this.childLbStates.containsKey(ChildLbState.this.key)) {
                    ConnectivityState unused = ChildLbState.this.currentState = connectivityState;
                    LoadBalancer.SubchannelPicker unused2 = ChildLbState.this.currentPicker = subchannelPicker;
                    if (!ChildLbState.this.deactivated && !MultiChildLoadBalancer.this.resolvingAddresses) {
                        if (connectivityState == ConnectivityState.IDLE && MultiChildLoadBalancer.this.reconnectOnIdle()) {
                            ChildLbState.this.lb.requestConnection();
                        }
                        MultiChildLoadBalancer.this.updateOverallBalancingState();
                    }
                }
            }

            /* access modifiers changed from: protected */
            public LoadBalancer.Helper delegate() {
                return MultiChildLoadBalancer.this.helper;
            }
        }
    }

    protected static class Endpoint {
        final String[] addrs;
        final int hashCode;

        public Endpoint(EquivalentAddressGroup equivalentAddressGroup) {
            Preconditions.checkNotNull(equivalentAddressGroup, "eag");
            this.addrs = new String[equivalentAddressGroup.getAddresses().size()];
            int i = 0;
            for (SocketAddress obj : equivalentAddressGroup.getAddresses()) {
                this.addrs[i] = obj.toString();
                i++;
            }
            Arrays.sort(this.addrs);
            this.hashCode = Arrays.hashCode(this.addrs);
        }

        public int hashCode() {
            return this.hashCode;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !(obj instanceof Endpoint)) {
                return false;
            }
            Endpoint endpoint = (Endpoint) obj;
            if (endpoint.hashCode == this.hashCode) {
                String[] strArr = endpoint.addrs;
                int length = strArr.length;
                String[] strArr2 = this.addrs;
                if (length == strArr2.length) {
                    return Arrays.equals(strArr, strArr2);
                }
            }
            return false;
        }

        public String toString() {
            return Arrays.toString(this.addrs);
        }
    }

    protected static class AcceptResolvedAddressRetVal {
        public final List<ChildLbState> removedChildren;
        public final Status status;

        public AcceptResolvedAddressRetVal(Status status2, List<ChildLbState> list) {
            this.status = status2;
            this.removedChildren = list;
        }
    }
}
