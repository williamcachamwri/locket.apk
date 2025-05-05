package io.grpc.okhttp;

public final class InternalOkHttpChannelBuilder {
    public static void setStatsEnabled(OkHttpChannelBuilder okHttpChannelBuilder, boolean z) {
        okHttpChannelBuilder.setStatsEnabled(z);
    }

    public static void disableCheckAuthority(OkHttpChannelBuilder okHttpChannelBuilder) {
        okHttpChannelBuilder.disableCheckAuthority();
    }

    public static void enableCheckAuthority(OkHttpChannelBuilder okHttpChannelBuilder) {
        okHttpChannelBuilder.enableCheckAuthority();
    }

    private InternalOkHttpChannelBuilder() {
    }
}
