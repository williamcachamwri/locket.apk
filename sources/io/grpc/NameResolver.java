package io.grpc;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.Nullable;

public abstract class NameResolver {

    public static abstract class Factory {
        public abstract String getDefaultScheme();

        public abstract NameResolver newNameResolver(URI uri, Args args);
    }

    public interface Listener {
        void onAddresses(List<EquivalentAddressGroup> list, Attributes attributes);

        void onError(Status status);
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface ResolutionResultAttr {
    }

    public static abstract class ServiceConfigParser {
        public abstract ConfigOrError parseServiceConfig(Map<String, ?> map);
    }

    public abstract String getServiceAuthority();

    public void refresh() {
    }

    public abstract void shutdown();

    public void start(final Listener listener) {
        if (listener instanceof Listener2) {
            start((Listener2) listener);
        } else {
            start((Listener2) new Listener2() {
                public void onError(Status status) {
                    listener.onError(status);
                }

                public void onResult(ResolutionResult resolutionResult) {
                    listener.onAddresses(resolutionResult.getAddresses(), resolutionResult.getAttributes());
                }
            });
        }
    }

    public void start(Listener2 listener2) {
        start((Listener) listener2);
    }

    public static abstract class Listener2 implements Listener {
        public abstract void onError(Status status);

        public abstract void onResult(ResolutionResult resolutionResult);

        @Deprecated
        public final void onAddresses(List<EquivalentAddressGroup> list, Attributes attributes) {
            onResult(ResolutionResult.newBuilder().setAddresses(list).setAttributes(attributes).build());
        }
    }

    public static final class Args {
        @Nullable
        private final ChannelLogger channelLogger;
        private final int defaultPort;
        @Nullable
        private final Executor executor;
        @Nullable
        private final String overrideAuthority;
        private final ProxyDetector proxyDetector;
        @Nullable
        private final ScheduledExecutorService scheduledExecutorService;
        private final ServiceConfigParser serviceConfigParser;
        private final SynchronizationContext syncContext;

        private Args(Integer num, ProxyDetector proxyDetector2, SynchronizationContext synchronizationContext, ServiceConfigParser serviceConfigParser2, @Nullable ScheduledExecutorService scheduledExecutorService2, @Nullable ChannelLogger channelLogger2, @Nullable Executor executor2, @Nullable String str) {
            this.defaultPort = ((Integer) Preconditions.checkNotNull(num, "defaultPort not set")).intValue();
            this.proxyDetector = (ProxyDetector) Preconditions.checkNotNull(proxyDetector2, "proxyDetector not set");
            this.syncContext = (SynchronizationContext) Preconditions.checkNotNull(synchronizationContext, "syncContext not set");
            this.serviceConfigParser = (ServiceConfigParser) Preconditions.checkNotNull(serviceConfigParser2, "serviceConfigParser not set");
            this.scheduledExecutorService = scheduledExecutorService2;
            this.channelLogger = channelLogger2;
            this.executor = executor2;
            this.overrideAuthority = str;
        }

        public int getDefaultPort() {
            return this.defaultPort;
        }

        public ProxyDetector getProxyDetector() {
            return this.proxyDetector;
        }

        public SynchronizationContext getSynchronizationContext() {
            return this.syncContext;
        }

        public ScheduledExecutorService getScheduledExecutorService() {
            ScheduledExecutorService scheduledExecutorService2 = this.scheduledExecutorService;
            if (scheduledExecutorService2 != null) {
                return scheduledExecutorService2;
            }
            throw new IllegalStateException("ScheduledExecutorService not set in Builder");
        }

        public ServiceConfigParser getServiceConfigParser() {
            return this.serviceConfigParser;
        }

        public ChannelLogger getChannelLogger() {
            ChannelLogger channelLogger2 = this.channelLogger;
            if (channelLogger2 != null) {
                return channelLogger2;
            }
            throw new IllegalStateException("ChannelLogger is not set in Builder");
        }

        @Nullable
        public Executor getOffloadExecutor() {
            return this.executor;
        }

        @Nullable
        public String getOverrideAuthority() {
            return this.overrideAuthority;
        }

        public String toString() {
            return MoreObjects.toStringHelper((Object) this).add("defaultPort", this.defaultPort).add("proxyDetector", (Object) this.proxyDetector).add("syncContext", (Object) this.syncContext).add("serviceConfigParser", (Object) this.serviceConfigParser).add("scheduledExecutorService", (Object) this.scheduledExecutorService).add("channelLogger", (Object) this.channelLogger).add("executor", (Object) this.executor).add("overrideAuthority", (Object) this.overrideAuthority).toString();
        }

        public Builder toBuilder() {
            Builder builder = new Builder();
            builder.setDefaultPort(this.defaultPort);
            builder.setProxyDetector(this.proxyDetector);
            builder.setSynchronizationContext(this.syncContext);
            builder.setServiceConfigParser(this.serviceConfigParser);
            builder.setScheduledExecutorService(this.scheduledExecutorService);
            builder.setChannelLogger(this.channelLogger);
            builder.setOffloadExecutor(this.executor);
            builder.setOverrideAuthority(this.overrideAuthority);
            return builder;
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public static final class Builder {
            private ChannelLogger channelLogger;
            private Integer defaultPort;
            private Executor executor;
            private String overrideAuthority;
            private ProxyDetector proxyDetector;
            private ScheduledExecutorService scheduledExecutorService;
            private ServiceConfigParser serviceConfigParser;
            private SynchronizationContext syncContext;

            Builder() {
            }

            public Builder setDefaultPort(int i) {
                this.defaultPort = Integer.valueOf(i);
                return this;
            }

            public Builder setProxyDetector(ProxyDetector proxyDetector2) {
                this.proxyDetector = (ProxyDetector) Preconditions.checkNotNull(proxyDetector2);
                return this;
            }

            public Builder setSynchronizationContext(SynchronizationContext synchronizationContext) {
                this.syncContext = (SynchronizationContext) Preconditions.checkNotNull(synchronizationContext);
                return this;
            }

            public Builder setScheduledExecutorService(ScheduledExecutorService scheduledExecutorService2) {
                this.scheduledExecutorService = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService2);
                return this;
            }

            public Builder setServiceConfigParser(ServiceConfigParser serviceConfigParser2) {
                this.serviceConfigParser = (ServiceConfigParser) Preconditions.checkNotNull(serviceConfigParser2);
                return this;
            }

            public Builder setChannelLogger(ChannelLogger channelLogger2) {
                this.channelLogger = (ChannelLogger) Preconditions.checkNotNull(channelLogger2);
                return this;
            }

            public Builder setOffloadExecutor(Executor executor2) {
                this.executor = executor2;
                return this;
            }

            public Builder setOverrideAuthority(String str) {
                this.overrideAuthority = str;
                return this;
            }

            public Args build() {
                return new Args(this.defaultPort, this.proxyDetector, this.syncContext, this.serviceConfigParser, this.scheduledExecutorService, this.channelLogger, this.executor, this.overrideAuthority);
            }
        }
    }

    public static final class ResolutionResult {
        private final List<EquivalentAddressGroup> addresses;
        private final Attributes attributes;
        @Nullable
        private final ConfigOrError serviceConfig;

        ResolutionResult(List<EquivalentAddressGroup> list, Attributes attributes2, ConfigOrError configOrError) {
            this.addresses = Collections.unmodifiableList(new ArrayList(list));
            this.attributes = (Attributes) Preconditions.checkNotNull(attributes2, "attributes");
            this.serviceConfig = configOrError;
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder toBuilder() {
            return newBuilder().setAddresses(this.addresses).setAttributes(this.attributes).setServiceConfig(this.serviceConfig);
        }

        public List<EquivalentAddressGroup> getAddresses() {
            return this.addresses;
        }

        public Attributes getAttributes() {
            return this.attributes;
        }

        @Nullable
        public ConfigOrError getServiceConfig() {
            return this.serviceConfig;
        }

        public String toString() {
            return MoreObjects.toStringHelper((Object) this).add("addresses", (Object) this.addresses).add("attributes", (Object) this.attributes).add("serviceConfig", (Object) this.serviceConfig).toString();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ResolutionResult)) {
                return false;
            }
            ResolutionResult resolutionResult = (ResolutionResult) obj;
            if (!Objects.equal(this.addresses, resolutionResult.addresses) || !Objects.equal(this.attributes, resolutionResult.attributes) || !Objects.equal(this.serviceConfig, resolutionResult.serviceConfig)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return Objects.hashCode(this.addresses, this.attributes, this.serviceConfig);
        }

        public static final class Builder {
            private List<EquivalentAddressGroup> addresses = Collections.emptyList();
            private Attributes attributes = Attributes.EMPTY;
            @Nullable
            private ConfigOrError serviceConfig;

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

            public Builder setServiceConfig(@Nullable ConfigOrError configOrError) {
                this.serviceConfig = configOrError;
                return this;
            }

            public ResolutionResult build() {
                return new ResolutionResult(this.addresses, this.attributes, this.serviceConfig);
            }
        }
    }

    public static final class ConfigOrError {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final Object config;
        private final Status status;

        static {
            Class<NameResolver> cls = NameResolver.class;
        }

        public static ConfigOrError fromConfig(Object obj) {
            return new ConfigOrError(obj);
        }

        public static ConfigOrError fromError(Status status2) {
            return new ConfigOrError(status2);
        }

        private ConfigOrError(Object obj) {
            this.config = Preconditions.checkNotNull(obj, "config");
            this.status = null;
        }

        private ConfigOrError(Status status2) {
            this.config = null;
            this.status = (Status) Preconditions.checkNotNull(status2, "status");
            Preconditions.checkArgument(!status2.isOk(), "cannot use OK status: %s", (Object) status2);
        }

        @Nullable
        public Object getConfig() {
            return this.config;
        }

        @Nullable
        public Status getError() {
            return this.status;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ConfigOrError configOrError = (ConfigOrError) obj;
            if (!Objects.equal(this.status, configOrError.status) || !Objects.equal(this.config, configOrError.config)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return Objects.hashCode(this.status, this.config);
        }

        public String toString() {
            if (this.config != null) {
                return MoreObjects.toStringHelper((Object) this).add("config", this.config).toString();
            }
            return MoreObjects.toStringHelper((Object) this).add("error", (Object) this.status).toString();
        }
    }
}
