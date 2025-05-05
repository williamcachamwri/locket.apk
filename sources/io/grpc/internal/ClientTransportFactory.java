package io.grpc.internal;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.CallCredentials;
import io.grpc.ChannelCredentials;
import io.grpc.ChannelLogger;
import io.grpc.HttpConnectProxiedSocketAddress;
import java.io.Closeable;
import java.net.SocketAddress;
import java.util.Collection;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

public interface ClientTransportFactory extends Closeable {
    void close();

    ScheduledExecutorService getScheduledExecutorService();

    Collection<Class<? extends SocketAddress>> getSupportedSocketAddressTypes();

    ConnectionClientTransport newClientTransport(SocketAddress socketAddress, ClientTransportOptions clientTransportOptions, ChannelLogger channelLogger);

    @CheckReturnValue
    @Nullable
    SwapChannelCredentialsResult swapChannelCredentials(ChannelCredentials channelCredentials);

    public static final class ClientTransportOptions {
        private String authority = "unknown-authority";
        private ChannelLogger channelLogger;
        @Nullable
        private HttpConnectProxiedSocketAddress connectProxiedSocketAddr;
        private Attributes eagAttributes = Attributes.EMPTY;
        @Nullable
        private String userAgent;

        public ChannelLogger getChannelLogger() {
            return this.channelLogger;
        }

        public ClientTransportOptions setChannelLogger(ChannelLogger channelLogger2) {
            this.channelLogger = channelLogger2;
            return this;
        }

        public String getAuthority() {
            return this.authority;
        }

        public ClientTransportOptions setAuthority(String str) {
            this.authority = (String) Preconditions.checkNotNull(str, "authority");
            return this;
        }

        public Attributes getEagAttributes() {
            return this.eagAttributes;
        }

        public ClientTransportOptions setEagAttributes(Attributes attributes) {
            Preconditions.checkNotNull(attributes, "eagAttributes");
            this.eagAttributes = attributes;
            return this;
        }

        @Nullable
        public String getUserAgent() {
            return this.userAgent;
        }

        public ClientTransportOptions setUserAgent(@Nullable String str) {
            this.userAgent = str;
            return this;
        }

        @Nullable
        public HttpConnectProxiedSocketAddress getHttpConnectProxiedSocketAddress() {
            return this.connectProxiedSocketAddr;
        }

        public ClientTransportOptions setHttpConnectProxiedSocketAddress(@Nullable HttpConnectProxiedSocketAddress httpConnectProxiedSocketAddress) {
            this.connectProxiedSocketAddr = httpConnectProxiedSocketAddress;
            return this;
        }

        public int hashCode() {
            return Objects.hashCode(this.authority, this.eagAttributes, this.userAgent, this.connectProxiedSocketAddr);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ClientTransportOptions)) {
                return false;
            }
            ClientTransportOptions clientTransportOptions = (ClientTransportOptions) obj;
            if (!this.authority.equals(clientTransportOptions.authority) || !this.eagAttributes.equals(clientTransportOptions.eagAttributes) || !Objects.equal(this.userAgent, clientTransportOptions.userAgent) || !Objects.equal(this.connectProxiedSocketAddr, clientTransportOptions.connectProxiedSocketAddr)) {
                return false;
            }
            return true;
        }
    }

    public static final class SwapChannelCredentialsResult {
        @Nullable
        final CallCredentials callCredentials;
        final ClientTransportFactory transportFactory;

        public SwapChannelCredentialsResult(ClientTransportFactory clientTransportFactory, @Nullable CallCredentials callCredentials2) {
            this.transportFactory = (ClientTransportFactory) Preconditions.checkNotNull(clientTransportFactory, "transportFactory");
            this.callCredentials = callCredentials2;
        }
    }
}
