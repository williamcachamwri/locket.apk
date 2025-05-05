package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.CallCredentials;
import io.grpc.CallOptions;
import io.grpc.ClientStreamTracer;
import io.grpc.Context;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import javax.annotation.Nullable;

final class MetadataApplierImpl extends CallCredentials.MetadataApplier {
    private final CallOptions callOptions;
    private final Context ctx;
    DelayedStream delayedStream;
    boolean finalized;
    private final MetadataApplierListener listener;
    private final Object lock = new Object();
    private final MethodDescriptor<?, ?> method;
    private final Metadata origHeaders;
    @Nullable
    private ClientStream returnedStream;
    private final ClientStreamTracer[] tracers;
    private final ClientTransport transport;

    public interface MetadataApplierListener {
        void onComplete();
    }

    MetadataApplierImpl(ClientTransport clientTransport, MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions2, MetadataApplierListener metadataApplierListener, ClientStreamTracer[] clientStreamTracerArr) {
        this.transport = clientTransport;
        this.method = methodDescriptor;
        this.origHeaders = metadata;
        this.callOptions = callOptions2;
        this.ctx = Context.current();
        this.listener = metadataApplierListener;
        this.tracers = clientStreamTracerArr;
    }

    /* JADX INFO: finally extract failed */
    public void apply(Metadata metadata) {
        Preconditions.checkState(!this.finalized, "apply() or fail() already called");
        Preconditions.checkNotNull(metadata, "headers");
        this.origHeaders.merge(metadata);
        Context attach = this.ctx.attach();
        try {
            ClientStream newStream = this.transport.newStream(this.method, this.origHeaders, this.callOptions, this.tracers);
            this.ctx.detach(attach);
            finalizeWith(newStream);
        } catch (Throwable th) {
            this.ctx.detach(attach);
            throw th;
        }
    }

    public void fail(Status status) {
        Preconditions.checkArgument(!status.isOk(), "Cannot fail with OK status");
        Preconditions.checkState(!this.finalized, "apply() or fail() already called");
        finalizeWith(new FailingClientStream(GrpcUtil.replaceInappropriateControlPlaneStatus(status), this.tracers));
    }

    private void finalizeWith(ClientStream clientStream) {
        boolean z;
        boolean z2 = true;
        Preconditions.checkState(!this.finalized, "already finalized");
        this.finalized = true;
        synchronized (this.lock) {
            if (this.returnedStream == null) {
                this.returnedStream = clientStream;
                z = true;
            } else {
                z = false;
            }
        }
        if (z) {
            this.listener.onComplete();
            return;
        }
        if (this.delayedStream == null) {
            z2 = false;
        }
        Preconditions.checkState(z2, "delayedStream is null");
        Runnable stream = this.delayedStream.setStream(clientStream);
        if (stream != null) {
            stream.run();
        }
        this.listener.onComplete();
    }

    /* access modifiers changed from: package-private */
    public ClientStream returnStream() {
        synchronized (this.lock) {
            ClientStream clientStream = this.returnedStream;
            if (clientStream != null) {
                return clientStream;
            }
            DelayedStream delayedStream2 = new DelayedStream();
            this.delayedStream = delayedStream2;
            this.returnedStream = delayedStream2;
            return delayedStream2;
        }
    }
}
