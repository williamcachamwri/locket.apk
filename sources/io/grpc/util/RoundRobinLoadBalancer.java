package io.grpc.util;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.grpc.ConnectivityState;
import io.grpc.LoadBalancer;
import io.grpc.util.MultiChildLoadBalancer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobinLoadBalancer extends MultiChildLoadBalancer {
    protected LoadBalancer.SubchannelPicker currentPicker = new EmptyPicker();
    private final AtomicInteger sequence = new AtomicInteger(new Random().nextInt());

    public RoundRobinLoadBalancer(LoadBalancer.Helper helper) {
        super(helper);
    }

    /* access modifiers changed from: protected */
    public LoadBalancer.SubchannelPicker getSubchannelPicker(Map<Object, LoadBalancer.SubchannelPicker> map) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x002c A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:5:0x0018  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateOverallBalancingState() {
        /*
            r3 = this;
            java.util.List r0 = r3.getReadyChildren()
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L_0x0048
            java.util.Collection r0 = r3.getChildLbStates()
            java.util.Iterator r0 = r0.iterator()
        L_0x0012:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x002c
            java.lang.Object r1 = r0.next()
            io.grpc.util.MultiChildLoadBalancer$ChildLbState r1 = (io.grpc.util.MultiChildLoadBalancer.ChildLbState) r1
            io.grpc.ConnectivityState r1 = r1.getCurrentState()
            io.grpc.ConnectivityState r2 = io.grpc.ConnectivityState.CONNECTING
            if (r1 == r2) goto L_0x002a
            io.grpc.ConnectivityState r2 = io.grpc.ConnectivityState.IDLE
            if (r1 != r2) goto L_0x0012
        L_0x002a:
            r0 = 1
            goto L_0x002d
        L_0x002c:
            r0 = 0
        L_0x002d:
            if (r0 == 0) goto L_0x003a
            io.grpc.ConnectivityState r0 = io.grpc.ConnectivityState.CONNECTING
            io.grpc.util.RoundRobinLoadBalancer$EmptyPicker r1 = new io.grpc.util.RoundRobinLoadBalancer$EmptyPicker
            r1.<init>()
            r3.updateBalancingState(r0, r1)
            goto L_0x0051
        L_0x003a:
            io.grpc.ConnectivityState r0 = io.grpc.ConnectivityState.TRANSIENT_FAILURE
            java.util.Collection r1 = r3.getChildLbStates()
            io.grpc.LoadBalancer$SubchannelPicker r1 = r3.createReadyPicker(r1)
            r3.updateBalancingState(r0, r1)
            goto L_0x0051
        L_0x0048:
            io.grpc.ConnectivityState r1 = io.grpc.ConnectivityState.READY
            io.grpc.LoadBalancer$SubchannelPicker r0 = r3.createReadyPicker(r0)
            r3.updateBalancingState(r1, r0)
        L_0x0051:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.util.RoundRobinLoadBalancer.updateOverallBalancingState():void");
    }

    private void updateBalancingState(ConnectivityState connectivityState, LoadBalancer.SubchannelPicker subchannelPicker) {
        if (connectivityState != this.currentConnectivityState || !subchannelPicker.equals(this.currentPicker)) {
            getHelper().updateBalancingState(connectivityState, subchannelPicker);
            this.currentConnectivityState = connectivityState;
            this.currentPicker = subchannelPicker;
        }
    }

    /* access modifiers changed from: protected */
    public LoadBalancer.SubchannelPicker createReadyPicker(Collection<MultiChildLoadBalancer.ChildLbState> collection) {
        ArrayList arrayList = new ArrayList();
        for (MultiChildLoadBalancer.ChildLbState currentPicker2 : collection) {
            arrayList.add(currentPicker2.getCurrentPicker());
        }
        return new ReadyPicker(arrayList, this.sequence);
    }

    static class ReadyPicker extends LoadBalancer.SubchannelPicker {
        private final int hashCode;
        private final AtomicInteger index;
        private final List<LoadBalancer.SubchannelPicker> subchannelPickers;

        public ReadyPicker(List<LoadBalancer.SubchannelPicker> list, AtomicInteger atomicInteger) {
            Preconditions.checkArgument(!list.isEmpty(), "empty list");
            this.subchannelPickers = list;
            this.index = (AtomicInteger) Preconditions.checkNotNull(atomicInteger, FirebaseAnalytics.Param.INDEX);
            int i = 0;
            for (LoadBalancer.SubchannelPicker hashCode2 : list) {
                i += hashCode2.hashCode();
            }
            this.hashCode = i;
        }

        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            return this.subchannelPickers.get(nextIndex()).pickSubchannel(pickSubchannelArgs);
        }

        public String toString() {
            return MoreObjects.toStringHelper((Class<?>) ReadyPicker.class).add("subchannelPickers", (Object) this.subchannelPickers).toString();
        }

        private int nextIndex() {
            return (this.index.getAndIncrement() & Integer.MAX_VALUE) % this.subchannelPickers.size();
        }

        /* access modifiers changed from: package-private */
        public List<LoadBalancer.SubchannelPicker> getSubchannelPickers() {
            return this.subchannelPickers;
        }

        public int hashCode() {
            return this.hashCode;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ReadyPicker)) {
                return false;
            }
            ReadyPicker readyPicker = (ReadyPicker) obj;
            if (readyPicker == this) {
                return true;
            }
            if (this.hashCode == readyPicker.hashCode && this.index == readyPicker.index && this.subchannelPickers.size() == readyPicker.subchannelPickers.size() && new HashSet(this.subchannelPickers).containsAll(readyPicker.subchannelPickers)) {
                return true;
            }
            return false;
        }
    }

    static final class EmptyPicker extends LoadBalancer.SubchannelPicker {
        EmptyPicker() {
        }

        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            return LoadBalancer.PickResult.withNoResult();
        }

        public int hashCode() {
            return getClass().hashCode();
        }

        public boolean equals(Object obj) {
            return obj instanceof EmptyPicker;
        }
    }
}
