package io.grpc.stub;

import javax.annotation.Nullable;

public abstract class ClientCallStreamObserver<ReqT> extends CallStreamObserver<ReqT> {
    public abstract void cancel(@Nullable String str, @Nullable Throwable th);

    public abstract boolean isReady();

    public abstract void request(int i);

    public abstract void setMessageCompression(boolean z);

    public abstract void setOnReadyHandler(Runnable runnable);

    public void disableAutoRequestWithInitial(int i) {
        throw new UnsupportedOperationException();
    }
}
