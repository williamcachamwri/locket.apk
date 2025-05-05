package io.grpc;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.ClientStreamTracer;
import io.grpc.NameResolver;
import io.sentry.Session;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class LoadBalancer {
    public static final Attributes.Key<Map<String, ?>> ATTR_HEALTH_CHECKING_CONFIG = Attributes.Key.create("internal:health-checking-config");
    @Deprecated
    public static final SubchannelPicker EMPTY_PICKER = new SubchannelPicker() {
        public String toString() {
            return "EMPTY_PICKER";
        }

        public PickResult pickSubchannel(PickSubchannelArgs pickSubchannelArgs) {
            return PickResult.withNoResult();
        }
    };
    public static final Attributes.Key<Boolean> HAS_HEALTH_PRODUCER_LISTENER_KEY = Attributes.Key.create("internal:has-health-check-producer-listener");
    public static final CreateSubchannelArgs.Key<SubchannelStateListener> HEALTH_CONSUMER_LISTENER_ARG_KEY = CreateSubchannelArgs.Key.create("internal:health-check-consumer-listener");
    public static final Attributes.Key<Boolean> IS_PETIOLE_POLICY = Attributes.Key.create("io.grpc.IS_PETIOLE_POLICY");
    private int recursionCount;

    public static abstract class Factory {
        public abstract LoadBalancer newLoadBalancer(Helper helper);
    }

    public static abstract class PickSubchannelArgs {
        public abstract CallOptions getCallOptions();

        public abstract Metadata getHeaders();

        public abstract MethodDescriptor<?, ?> getMethodDescriptor();
    }

    public static abstract class SubchannelPicker {
        public abstract PickResult pickSubchannel(PickSubchannelArgs pickSubchannelArgs);

        @Deprecated
        public void requestConnection() {
        }
    }

    public interface SubchannelStateListener {
        void onSubchannelState(ConnectivityStateInfo connectivityStateInfo);
    }

    public boolean canHandleEmptyAddressListFromNameResolution() {
        return false;
    }

    public abstract void handleNameResolutionError(Status status);

    @Deprecated
    public void handleSubchannelState(Subchannel subchannel, ConnectivityStateInfo connectivityStateInfo) {
    }

    public void requestConnection() {
    }

    public abstract void shutdown();

    public void handleResolvedAddresses(ResolvedAddresses resolvedAddresses) {
        int i = this.recursionCount;
        this.recursionCount = i + 1;
        if (i == 0) {
            acceptResolvedAddresses(resolvedAddresses);
        }
        this.recursionCount = 0;
    }

    public Status acceptResolvedAddresses(ResolvedAddresses resolvedAddresses) {
        if (!resolvedAddresses.getAddresses().isEmpty() || canHandleEmptyAddressListFromNameResolution()) {
            int i = this.recursionCount;
            this.recursionCount = i + 1;
            if (i == 0) {
                handleResolvedAddresses(resolvedAddresses);
            }
            this.recursionCount = 0;
            return Status.OK;
        }
        Status withDescription = Status.UNAVAILABLE.withDescription("NameResolver returned no usable address. addrs=" + resolvedAddresses.getAddresses() + ", attrs=" + resolvedAddresses.getAttributes());
        handleNameResolutionError(withDescription);
        return withDescription;
    }

    public static final class ResolvedAddresses {
        private final List<EquivalentAddressGroup> addresses;
        private final Attributes attributes;
        @Nullable
        private final Object loadBalancingPolicyConfig;

        private ResolvedAddresses(List<EquivalentAddressGroup> list, Attributes attributes2, Object obj) {
            this.addresses = Collections.unmodifiableList(new ArrayList((Collection) Preconditions.checkNotNull(list, "addresses")));
            this.attributes = (Attributes) Preconditions.checkNotNull(attributes2, "attributes");
            this.loadBalancingPolicyConfig = obj;
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder toBuilder() {
            return newBuilder().setAddresses(this.addresses).setAttributes(this.attributes).setLoadBalancingPolicyConfig(this.loadBalancingPolicyConfig);
        }

        public List<EquivalentAddressGroup> getAddresses() {
            return this.addresses;
        }

        public Attributes getAttributes() {
            return this.attributes;
        }

        @Nullable
        public Object getLoadBalancingPolicyConfig() {
            return this.loadBalancingPolicyConfig;
        }

        public static final class Builder {
            private List<EquivalentAddressGroup> addresses;
            private Attributes attributes = Attributes.EMPTY;
            @Nullable
            private Object loadBalancingPolicyConfig;

            Builder() {
            }

            public Builder setAddresses(List<EquivalentAddressGroup> list) {
                this.addresses = list;
                return this;
            }

            public Builder setAttributes(Attributes attributes2) {
                this.attributes = attributes2;
                return this;
            }

            public Builder setLoadBalancingPolicyConfig(@Nullable Object obj) {
                this.loadBalancingPolicyConfig = obj;
                return this;
            }

            public ResolvedAddresses build() {
                return new ResolvedAddresses(this.addresses, this.attributes, this.loadBalancingPolicyConfig);
            }
        }

        public String toString() {
            return MoreObjects.toStringHelper((Object) this).add("addresses", (Object) this.addresses).add("attributes", (Object) this.attributes).add("loadBalancingPolicyConfig", this.loadBalancingPolicyConfig).toString();
        }

        public int hashCode() {
            return Objects.hashCode(this.addresses, this.attributes, this.loadBalancingPolicyConfig);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ResolvedAddresses)) {
                return false;
            }
            ResolvedAddresses resolvedAddresses = (ResolvedAddresses) obj;
            if (!Objects.equal(this.addresses, resolvedAddresses.addresses) || !Objects.equal(this.attributes, resolvedAddresses.attributes) || !Objects.equal(this.loadBalancingPolicyConfig, resolvedAddresses.loadBalancingPolicyConfig)) {
                return false;
            }
            return true;
        }
    }

    public static final class PickResult {
        private static final PickResult NO_RESULT = new PickResult((Subchannel) null, (ClientStreamTracer.Factory) null, Status.OK, false);
        private final boolean drop;
        private final Status status;
        @Nullable
        private final ClientStreamTracer.Factory streamTracerFactory;
        @Nullable
        private final Subchannel subchannel;

        private PickResult(@Nullable Subchannel subchannel2, @Nullable ClientStreamTracer.Factory factory, Status status2, boolean z) {
            this.subchannel = subchannel2;
            this.streamTracerFactory = factory;
            this.status = (Status) Preconditions.checkNotNull(status2, "status");
            this.drop = z;
        }

        public static PickResult withSubchannel(Subchannel subchannel2, @Nullable ClientStreamTracer.Factory factory) {
            return new PickResult((Subchannel) Preconditions.checkNotNull(subchannel2, "subchannel"), factory, Status.OK, false);
        }

        public static PickResult withSubchannel(Subchannel subchannel2) {
            return withSubchannel(subchannel2, (ClientStreamTracer.Factory) null);
        }

        public static PickResult withError(Status status2) {
            Preconditions.checkArgument(!status2.isOk(), "error status shouldn't be OK");
            return new PickResult((Subchannel) null, (ClientStreamTracer.Factory) null, status2, false);
        }

        public static PickResult withDrop(Status status2) {
            Preconditions.checkArgument(!status2.isOk(), "drop status shouldn't be OK");
            return new PickResult((Subchannel) null, (ClientStreamTracer.Factory) null, status2, true);
        }

        public static PickResult withNoResult() {
            return NO_RESULT;
        }

        @Nullable
        public Subchannel getSubchannel() {
            return this.subchannel;
        }

        @Nullable
        public ClientStreamTracer.Factory getStreamTracerFactory() {
            return this.streamTracerFactory;
        }

        public Status getStatus() {
            return this.status;
        }

        public boolean isDrop() {
            return this.drop;
        }

        public String toString() {
            return MoreObjects.toStringHelper((Object) this).add("subchannel", (Object) this.subchannel).add("streamTracerFactory", (Object) this.streamTracerFactory).add("status", (Object) this.status).add("drop", this.drop).toString();
        }

        public int hashCode() {
            return Objects.hashCode(this.subchannel, this.status, this.streamTracerFactory, Boolean.valueOf(this.drop));
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof PickResult)) {
                return false;
            }
            PickResult pickResult = (PickResult) obj;
            if (!Objects.equal(this.subchannel, pickResult.subchannel) || !Objects.equal(this.status, pickResult.status) || !Objects.equal(this.streamTracerFactory, pickResult.streamTracerFactory) || this.drop != pickResult.drop) {
                return false;
            }
            return true;
        }
    }

    public static final class CreateSubchannelArgs {
        private final List<EquivalentAddressGroup> addrs;
        private final Attributes attrs;
        private final Object[][] customOptions;

        private CreateSubchannelArgs(List<EquivalentAddressGroup> list, Attributes attributes, Object[][] objArr) {
            this.addrs = (List) Preconditions.checkNotNull(list, "addresses are not set");
            this.attrs = (Attributes) Preconditions.checkNotNull(attributes, Session.JsonKeys.ATTRS);
            this.customOptions = (Object[][]) Preconditions.checkNotNull(objArr, "customOptions");
        }

        public List<EquivalentAddressGroup> getAddresses() {
            return this.addrs;
        }

        public Attributes getAttributes() {
            return this.attrs;
        }

        public <T> T getOption(Key<T> key) {
            Preconditions.checkNotNull(key, "key");
            int i = 0;
            while (true) {
                Object[][] objArr = this.customOptions;
                if (i >= objArr.length) {
                    return key.defaultValue;
                }
                if (key.equals(objArr[i][0])) {
                    return this.customOptions[i][1];
                }
                i++;
            }
        }

        public Builder toBuilder() {
            return newBuilder().setAddresses(this.addrs).setAttributes(this.attrs).copyCustomOptions(this.customOptions);
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public String toString() {
            return MoreObjects.toStringHelper((Object) this).add("addrs", (Object) this.addrs).add(Session.JsonKeys.ATTRS, (Object) this.attrs).add("customOptions", (Object) Arrays.deepToString(this.customOptions)).toString();
        }

        public static final class Builder {
            private List<EquivalentAddressGroup> addrs;
            private Attributes attrs = Attributes.EMPTY;
            private Object[][] customOptions = ((Object[][]) Array.newInstance(Object.class, new int[]{0, 2}));

            Builder() {
            }

            /* access modifiers changed from: private */
            public Builder copyCustomOptions(Object[][] objArr) {
                Object[][] objArr2 = (Object[][]) Array.newInstance(Object.class, new int[]{objArr.length, 2});
                this.customOptions = objArr2;
                System.arraycopy(objArr, 0, objArr2, 0, objArr.length);
                return this;
            }

            public <T> Builder addOption(Key<T> key, T t) {
                Preconditions.checkNotNull(key, "key");
                Preconditions.checkNotNull(t, "value");
                int i = 0;
                while (true) {
                    Object[][] objArr = this.customOptions;
                    if (i >= objArr.length) {
                        i = -1;
                        break;
                    } else if (key.equals(objArr[i][0])) {
                        break;
                    } else {
                        i++;
                    }
                }
                if (i == -1) {
                    Object[][] objArr2 = (Object[][]) Array.newInstance(Object.class, new int[]{this.customOptions.length + 1, 2});
                    Object[][] objArr3 = this.customOptions;
                    System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
                    this.customOptions = objArr2;
                    i = objArr2.length - 1;
                }
                this.customOptions[i] = new Object[]{key, t};
                return this;
            }

            public Builder setAddresses(EquivalentAddressGroup equivalentAddressGroup) {
                this.addrs = Collections.singletonList(equivalentAddressGroup);
                return this;
            }

            public Builder setAddresses(List<EquivalentAddressGroup> list) {
                Preconditions.checkArgument(!list.isEmpty(), "addrs is empty");
                this.addrs = Collections.unmodifiableList(new ArrayList(list));
                return this;
            }

            public Builder setAttributes(Attributes attributes) {
                this.attrs = (Attributes) Preconditions.checkNotNull(attributes, Session.JsonKeys.ATTRS);
                return this;
            }

            public CreateSubchannelArgs build() {
                return new CreateSubchannelArgs(this.addrs, this.attrs, this.customOptions);
            }
        }

        public static final class Key<T> {
            private final String debugString;
            /* access modifiers changed from: private */
            public final T defaultValue;

            private Key(String str, T t) {
                this.debugString = str;
                this.defaultValue = t;
            }

            public static <T> Key<T> create(String str) {
                Preconditions.checkNotNull(str, "debugString");
                return new Key<>(str, (Object) null);
            }

            public static <T> Key<T> createWithDefault(String str, T t) {
                Preconditions.checkNotNull(str, "debugString");
                return new Key<>(str, t);
            }

            public T getDefault() {
                return this.defaultValue;
            }

            public String toString() {
                return this.debugString;
            }
        }
    }

    public static abstract class Helper {
        public abstract ManagedChannel createOobChannel(EquivalentAddressGroup equivalentAddressGroup, String str);

        public abstract String getAuthority();

        @Deprecated
        public void ignoreRefreshNameResolutionCheck() {
        }

        public abstract void updateBalancingState(@Nonnull ConnectivityState connectivityState, @Nonnull SubchannelPicker subchannelPicker);

        public Subchannel createSubchannel(CreateSubchannelArgs createSubchannelArgs) {
            throw new UnsupportedOperationException();
        }

        public ManagedChannel createOobChannel(List<EquivalentAddressGroup> list, String str) {
            throw new UnsupportedOperationException();
        }

        public void updateOobChannelAddresses(ManagedChannel managedChannel, EquivalentAddressGroup equivalentAddressGroup) {
            throw new UnsupportedOperationException();
        }

        public void updateOobChannelAddresses(ManagedChannel managedChannel, List<EquivalentAddressGroup> list) {
            throw new UnsupportedOperationException();
        }

        public ManagedChannel createResolvingOobChannel(String str) {
            return createResolvingOobChannelBuilder(str).build();
        }

        @Deprecated
        public ManagedChannelBuilder<?> createResolvingOobChannelBuilder(String str) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public ManagedChannelBuilder<?> createResolvingOobChannelBuilder(String str, ChannelCredentials channelCredentials) {
            throw new UnsupportedOperationException();
        }

        public void refreshNameResolution() {
            throw new UnsupportedOperationException();
        }

        public SynchronizationContext getSynchronizationContext() {
            throw new UnsupportedOperationException();
        }

        public ScheduledExecutorService getScheduledExecutorService() {
            throw new UnsupportedOperationException();
        }

        public ChannelCredentials getChannelCredentials() {
            return getUnsafeChannelCredentials().withoutBearerTokens();
        }

        public ChannelCredentials getUnsafeChannelCredentials() {
            throw new UnsupportedOperationException();
        }

        public ChannelLogger getChannelLogger() {
            throw new UnsupportedOperationException();
        }

        public NameResolver.Args getNameResolverArgs() {
            throw new UnsupportedOperationException();
        }

        public NameResolverRegistry getNameResolverRegistry() {
            throw new UnsupportedOperationException();
        }
    }

    public static abstract class Subchannel {
        public abstract Attributes getAttributes();

        public abstract void requestConnection();

        public abstract void shutdown();

        public void start(SubchannelStateListener subchannelStateListener) {
            throw new UnsupportedOperationException("Not implemented");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x000c, code lost:
            if (r0.size() == 1) goto L_0x0010;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final io.grpc.EquivalentAddressGroup getAddresses() {
            /*
                r4 = this;
                java.util.List r0 = r4.getAllAddresses()
                r1 = 0
                if (r0 == 0) goto L_0x000f
                int r2 = r0.size()
                r3 = 1
                if (r2 != r3) goto L_0x000f
                goto L_0x0010
            L_0x000f:
                r3 = r1
            L_0x0010:
                java.lang.String r2 = "%s does not have exactly one group"
                com.google.common.base.Preconditions.checkState((boolean) r3, (java.lang.String) r2, (java.lang.Object) r0)
                java.lang.Object r0 = r0.get(r1)
                io.grpc.EquivalentAddressGroup r0 = (io.grpc.EquivalentAddressGroup) r0
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.LoadBalancer.Subchannel.getAddresses():io.grpc.EquivalentAddressGroup");
        }

        public List<EquivalentAddressGroup> getAllAddresses() {
            throw new UnsupportedOperationException();
        }

        public Channel asChannel() {
            throw new UnsupportedOperationException();
        }

        public ChannelLogger getChannelLogger() {
            throw new UnsupportedOperationException();
        }

        public void updateAddresses(List<EquivalentAddressGroup> list) {
            throw new UnsupportedOperationException();
        }

        public Object getInternalSubchannel() {
            throw new UnsupportedOperationException();
        }
    }

    @Deprecated
    public static final class ErrorPicker extends SubchannelPicker {
        private final Status error;

        public ErrorPicker(Status status) {
            this.error = (Status) Preconditions.checkNotNull(status, "error");
        }

        public PickResult pickSubchannel(PickSubchannelArgs pickSubchannelArgs) {
            return PickResult.withError(this.error);
        }

        public String toString() {
            return MoreObjects.toStringHelper((Object) this).add("error", (Object) this.error).toString();
        }
    }

    public static final class FixedResultPicker extends SubchannelPicker {
        private final PickResult result;

        public FixedResultPicker(PickResult pickResult) {
            this.result = (PickResult) Preconditions.checkNotNull(pickResult, "result");
        }

        public PickResult pickSubchannel(PickSubchannelArgs pickSubchannelArgs) {
            return this.result;
        }

        public String toString() {
            return "FixedResultPicker(" + this.result + ")";
        }
    }
}
