package io.grpc.internal;

import io.grpc.Attributes;
import io.grpc.Status;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

public interface ManagedClientTransport extends ClientTransport {

    public interface Listener {
        Attributes filterTransport(Attributes attributes) {
            return attributes;
        }

        void transportInUse(boolean z);

        void transportReady();

        void transportShutdown(Status status);

        void transportTerminated();
    }

    void shutdown(Status status);

    void shutdownNow(Status status);

    @CheckReturnValue
    @Nullable
    Runnable start(Listener listener);
}
