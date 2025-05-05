package io.grpc;

import com.google.common.base.Preconditions;
import io.grpc.ManagedChannelProvider;

public abstract class ServerProvider {
    /* access modifiers changed from: protected */
    public abstract ServerBuilder<?> builderForPort(int i);

    /* access modifiers changed from: protected */
    public abstract boolean isAvailable();

    /* access modifiers changed from: protected */
    public abstract int priority();

    public static ServerProvider provider() {
        ServerProvider provider = ServerRegistry.getDefaultRegistry().provider();
        if (provider != null) {
            return provider;
        }
        throw new ManagedChannelProvider.ProviderNotFoundException("No functional server found. Try adding a dependency on the grpc-netty or grpc-netty-shaded artifact");
    }

    /* access modifiers changed from: protected */
    public NewServerBuilderResult newServerBuilderForPort(int i, ServerCredentials serverCredentials) {
        return NewServerBuilderResult.error("ServerCredentials are unsupported");
    }

    public static final class NewServerBuilderResult {
        private final String error;
        private final ServerBuilder<?> serverBuilder;

        private NewServerBuilderResult(ServerBuilder<?> serverBuilder2, String str) {
            this.serverBuilder = serverBuilder2;
            this.error = str;
        }

        public static NewServerBuilderResult serverBuilder(ServerBuilder<?> serverBuilder2) {
            return new NewServerBuilderResult((ServerBuilder) Preconditions.checkNotNull(serverBuilder2), (String) null);
        }

        public static NewServerBuilderResult error(String str) {
            return new NewServerBuilderResult((ServerBuilder<?>) null, (String) Preconditions.checkNotNull(str));
        }

        public ServerBuilder<?> getServerBuilder() {
            return this.serverBuilder;
        }

        public String getError() {
            return this.error;
        }
    }
}
