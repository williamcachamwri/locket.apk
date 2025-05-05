package io.grpc.okhttp;

import io.grpc.ChannelCredentials;
import io.grpc.InternalServiceProviders;
import io.grpc.ManagedChannelProvider;
import io.grpc.okhttp.OkHttpChannelBuilder;
import java.net.SocketAddress;
import java.util.Collection;

public final class OkHttpChannelProvider extends ManagedChannelProvider {
    public boolean isAvailable() {
        return true;
    }

    public int priority() {
        return InternalServiceProviders.isAndroid(getClass().getClassLoader()) ? 8 : 3;
    }

    public OkHttpChannelBuilder builderForAddress(String str, int i) {
        return OkHttpChannelBuilder.forAddress(str, i);
    }

    public OkHttpChannelBuilder builderForTarget(String str) {
        return OkHttpChannelBuilder.forTarget(str);
    }

    public ManagedChannelProvider.NewChannelBuilderResult newChannelBuilder(String str, ChannelCredentials channelCredentials) {
        OkHttpChannelBuilder.SslSocketFactoryResult sslSocketFactoryFrom = OkHttpChannelBuilder.sslSocketFactoryFrom(channelCredentials);
        if (sslSocketFactoryFrom.error != null) {
            return ManagedChannelProvider.NewChannelBuilderResult.error(sslSocketFactoryFrom.error);
        }
        return ManagedChannelProvider.NewChannelBuilderResult.channelBuilder(new OkHttpChannelBuilder(str, channelCredentials, sslSocketFactoryFrom.callCredentials, sslSocketFactoryFrom.factory));
    }

    /* access modifiers changed from: protected */
    public Collection<Class<? extends SocketAddress>> getSupportedSocketAddressTypes() {
        return OkHttpChannelBuilder.getSupportedSocketAddressTypes();
    }
}
