package io.grpc.okhttp;

import io.grpc.ServerCredentials;
import io.grpc.ServerProvider;
import io.grpc.okhttp.OkHttpServerBuilder;
import java.net.InetSocketAddress;

public final class OkHttpServerProvider extends ServerProvider {
    /* access modifiers changed from: protected */
    public boolean isAvailable() {
        return true;
    }

    /* access modifiers changed from: protected */
    public int priority() {
        return 4;
    }

    /* access modifiers changed from: protected */
    public OkHttpServerBuilder builderForPort(int i) {
        throw new UnsupportedOperationException("Use Grpc.newServerBuilderForPort() instead");
    }

    /* access modifiers changed from: protected */
    public ServerProvider.NewServerBuilderResult newServerBuilderForPort(int i, ServerCredentials serverCredentials) {
        OkHttpServerBuilder.HandshakerSocketFactoryResult handshakerSocketFactoryFrom = OkHttpServerBuilder.handshakerSocketFactoryFrom(serverCredentials);
        if (handshakerSocketFactoryFrom.error != null) {
            return ServerProvider.NewServerBuilderResult.error(handshakerSocketFactoryFrom.error);
        }
        return ServerProvider.NewServerBuilderResult.serverBuilder(new OkHttpServerBuilder(new InetSocketAddress(i), handshakerSocketFactoryFrom.factory));
    }
}
