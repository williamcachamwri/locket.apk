package io.grpc;

import com.google.common.base.Preconditions;

public final class CompositeChannelCredentials extends ChannelCredentials {
    private final CallCredentials callCredentials;
    private final ChannelCredentials channelCredentials;

    public static ChannelCredentials create(ChannelCredentials channelCredentials2, CallCredentials callCredentials2) {
        return new CompositeChannelCredentials(channelCredentials2, callCredentials2);
    }

    private CompositeChannelCredentials(ChannelCredentials channelCredentials2, CallCredentials callCredentials2) {
        this.channelCredentials = (ChannelCredentials) Preconditions.checkNotNull(channelCredentials2, "channelCreds");
        this.callCredentials = (CallCredentials) Preconditions.checkNotNull(callCredentials2, "callCreds");
    }

    public ChannelCredentials getChannelCredentials() {
        return this.channelCredentials;
    }

    public CallCredentials getCallCredentials() {
        return this.callCredentials;
    }

    public ChannelCredentials withoutBearerTokens() {
        return this.channelCredentials.withoutBearerTokens();
    }
}
