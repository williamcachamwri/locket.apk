package io.grpc.okhttp;

import com.google.common.base.Preconditions;
import com.squareup.okhttp.ConnectionSpec;
import javax.net.ssl.SSLSocketFactory;

public final class SslSocketFactoryServerCredentials {
    private SslSocketFactoryServerCredentials() {
    }

    public static io.grpc.ServerCredentials create(SSLSocketFactory sSLSocketFactory) {
        return new ServerCredentials(sSLSocketFactory);
    }

    public static io.grpc.ServerCredentials create(SSLSocketFactory sSLSocketFactory, ConnectionSpec connectionSpec) {
        return new ServerCredentials(sSLSocketFactory, Utils.convertSpec(connectionSpec));
    }

    static final class ServerCredentials extends io.grpc.ServerCredentials {
        private final io.grpc.okhttp.internal.ConnectionSpec connectionSpec;
        private final SSLSocketFactory factory;

        ServerCredentials(SSLSocketFactory sSLSocketFactory) {
            this(sSLSocketFactory, OkHttpChannelBuilder.INTERNAL_DEFAULT_CONNECTION_SPEC);
        }

        ServerCredentials(SSLSocketFactory sSLSocketFactory, io.grpc.okhttp.internal.ConnectionSpec connectionSpec2) {
            this.factory = (SSLSocketFactory) Preconditions.checkNotNull(sSLSocketFactory, "factory");
            this.connectionSpec = (io.grpc.okhttp.internal.ConnectionSpec) Preconditions.checkNotNull(connectionSpec2, "connectionSpec");
        }

        public SSLSocketFactory getFactory() {
            return this.factory;
        }

        public io.grpc.okhttp.internal.ConnectionSpec getConnectionSpec() {
            return this.connectionSpec;
        }
    }
}
