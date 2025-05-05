package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientStreamTracer;
import io.grpc.Context;
import io.grpc.InternalConfigSelector;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.internal.ClientCallImpl;
import io.grpc.internal.ClientStreamListener;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicReference;

final class SubchannelChannel extends Channel {
    static final Status NOT_READY_ERROR;
    static final Status WAIT_FOR_READY_ERROR = Status.UNAVAILABLE.withDescription("wait-for-ready RPC is not supported on Subchannel.asChannel()");
    /* access modifiers changed from: private */
    public static final FailingClientTransport notReadyTransport;
    private final CallTracer callsTracer;
    private final AtomicReference<InternalConfigSelector> configSelector;
    private final ScheduledExecutorService deadlineCancellationExecutor;
    private final Executor executor;
    /* access modifiers changed from: private */
    public final InternalSubchannel subchannel;
    private final ClientCallImpl.ClientStreamProvider transportProvider = new ClientCallImpl.ClientStreamProvider() {
        public ClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, CallOptions callOptions, Metadata metadata, Context context) {
            ClientTransport transport = SubchannelChannel.this.subchannel.getTransport();
            if (transport == null) {
                transport = SubchannelChannel.notReadyTransport;
            }
            ClientStreamTracer[] clientStreamTracers = GrpcUtil.getClientStreamTracers(callOptions, metadata, 0, false);
            Context attach = context.attach();
            try {
                return transport.newStream(methodDescriptor, metadata, callOptions, clientStreamTracers);
            } finally {
                context.detach(attach);
            }
        }
    };

    static {
        Status withDescription = Status.UNAVAILABLE.withDescription("Subchannel is NOT READY");
        NOT_READY_ERROR = withDescription;
        notReadyTransport = new FailingClientTransport(withDescription, ClientStreamListener.RpcProgress.MISCARRIED);
    }

    SubchannelChannel(InternalSubchannel internalSubchannel, Executor executor2, ScheduledExecutorService scheduledExecutorService, CallTracer callTracer, AtomicReference<InternalConfigSelector> atomicReference) {
        this.subchannel = (InternalSubchannel) Preconditions.checkNotNull(internalSubchannel, "subchannel");
        this.executor = (Executor) Preconditions.checkNotNull(executor2, "executor");
        this.deadlineCancellationExecutor = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "deadlineCancellationExecutor");
        this.callsTracer = (CallTracer) Preconditions.checkNotNull(callTracer, "callsTracer");
        this.configSelector = (AtomicReference) Preconditions.checkNotNull(atomicReference, "configSelector");
    }

    public <RequestT, ResponseT> ClientCall<RequestT, ResponseT> newCall(MethodDescriptor<RequestT, ResponseT> methodDescriptor, CallOptions callOptions) {
        final Executor executor2 = callOptions.getExecutor() == null ? this.executor : callOptions.getExecutor();
        if (callOptions.isWaitForReady()) {
            return new ClientCall<RequestT, ResponseT>() {
                public void cancel(String str, Throwable th) {
                }

                public void halfClose() {
                }

                public void request(int i) {
                }

                public void sendMessage(RequestT requestt) {
                }

                public void start(final ClientCall.Listener<ResponseT> listener, Metadata metadata) {
                    executor2.execute(new Runnable() {
                        public void run() {
                            listener.onClose(SubchannelChannel.WAIT_FOR_READY_ERROR, new Metadata());
                        }
                    });
                }
            };
        }
        return new ClientCallImpl(methodDescriptor, executor2, callOptions.withOption(GrpcUtil.CALL_OPTIONS_RPC_OWNED_BY_BALANCER, Boolean.TRUE), this.transportProvider, this.deadlineCancellationExecutor, this.callsTracer, this.configSelector.get());
    }

    public String authority() {
        return this.subchannel.getAuthority();
    }
}
