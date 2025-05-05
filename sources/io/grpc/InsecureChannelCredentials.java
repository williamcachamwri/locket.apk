package io.grpc;

public final class InsecureChannelCredentials extends ChannelCredentials {
    public ChannelCredentials withoutBearerTokens() {
        return this;
    }

    public static ChannelCredentials create() {
        return new InsecureChannelCredentials();
    }

    private InsecureChannelCredentials() {
    }
}
