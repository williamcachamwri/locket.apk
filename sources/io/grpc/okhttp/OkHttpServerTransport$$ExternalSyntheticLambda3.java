package io.grpc.okhttp;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class OkHttpServerTransport$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ OkHttpServerTransport f$0;

    public /* synthetic */ OkHttpServerTransport$$ExternalSyntheticLambda3(OkHttpServerTransport okHttpServerTransport) {
        this.f$0 = okHttpServerTransport;
    }

    public final void run() {
        this.f$0.shutdown();
    }
}
