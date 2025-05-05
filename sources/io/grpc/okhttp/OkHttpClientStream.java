package io.grpc.okhttp;

import com.google.common.base.Preconditions;
import com.google.common.io.BaseEncoding;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.internal.AbstractClientStream;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.Http2ClientStreamTransportState;
import io.grpc.internal.StatsTraceContext;
import io.grpc.internal.TransportTracer;
import io.grpc.internal.WritableBuffer;
import io.grpc.okhttp.OutboundFlowController;
import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.Header;
import io.perfmark.PerfMark;
import io.perfmark.Tag;
import io.perfmark.TaskCloseable;
import io.sentry.protocol.SentryStackFrame;
import java.util.List;
import okio.Buffer;

class OkHttpClientStream extends AbstractClientStream {
    public static final int ABSENT_ID = -1;
    /* access modifiers changed from: private */
    public static final Buffer EMPTY_BUFFER = new Buffer();
    private final Attributes attributes;
    /* access modifiers changed from: private */
    public String authority;
    /* access modifiers changed from: private */
    public final MethodDescriptor<?, ?> method;
    private final Sink sink;
    /* access modifiers changed from: private */
    public final TransportState state;
    /* access modifiers changed from: private */
    public final StatsTraceContext statsTraceCtx;
    /* access modifiers changed from: private */
    public boolean useGet;
    /* access modifiers changed from: private */
    public final String userAgent;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OkHttpClientStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, ExceptionHandlingFrameWriter exceptionHandlingFrameWriter, OkHttpClientTransport okHttpClientTransport, OutboundFlowController outboundFlowController, Object obj, int i, int i2, String str, String str2, StatsTraceContext statsTraceContext, TransportTracer transportTracer, CallOptions callOptions, boolean z) {
        super(new OkHttpWritableBufferAllocator(), statsTraceContext, transportTracer, metadata, callOptions, z && methodDescriptor.isSafe());
        this.sink = new Sink();
        this.useGet = false;
        StatsTraceContext statsTraceContext2 = statsTraceContext;
        this.statsTraceCtx = (StatsTraceContext) Preconditions.checkNotNull(statsTraceContext2, "statsTraceCtx");
        this.method = methodDescriptor;
        this.authority = str;
        this.userAgent = str2;
        this.attributes = okHttpClientTransport.getAttributes();
        this.state = new TransportState(i, statsTraceContext2, obj, exceptionHandlingFrameWriter, outboundFlowController, okHttpClientTransport, i2, methodDescriptor.getFullMethodName());
    }

    /* access modifiers changed from: protected */
    public TransportState transportState() {
        return this.state;
    }

    /* access modifiers changed from: protected */
    public Sink abstractClientStreamSink() {
        return this.sink;
    }

    public MethodDescriptor.MethodType getType() {
        return this.method.getType();
    }

    /* access modifiers changed from: package-private */
    public boolean useGet() {
        return this.useGet;
    }

    public void setAuthority(String str) {
        this.authority = (String) Preconditions.checkNotNull(str, "authority");
    }

    public Attributes getAttributes() {
        return this.attributes;
    }

    class Sink implements AbstractClientStream.Sink {
        Sink() {
        }

        public void writeHeaders(Metadata metadata, byte[] bArr) {
            TaskCloseable traceTask = PerfMark.traceTask("OkHttpClientStream$Sink.writeHeaders");
            try {
                String str = "/" + OkHttpClientStream.this.method.getFullMethodName();
                if (bArr != null) {
                    boolean unused = OkHttpClientStream.this.useGet = true;
                    str = str + "?" + BaseEncoding.base64().encode(bArr);
                }
                synchronized (OkHttpClientStream.this.state.lock) {
                    OkHttpClientStream.this.state.streamReady(metadata, str);
                }
                if (traceTask != null) {
                    traceTask.close();
                }
            } catch (Throwable th) {
                if (traceTask != null) {
                    try {
                        traceTask.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }

        public void writeFrame(WritableBuffer writableBuffer, boolean z, boolean z2, int i) {
            Buffer buffer;
            TaskCloseable traceTask = PerfMark.traceTask("OkHttpClientStream$Sink.writeFrame");
            if (writableBuffer == null) {
                try {
                    buffer = OkHttpClientStream.EMPTY_BUFFER;
                } catch (Throwable th) {
                    if (traceTask != null) {
                        try {
                            traceTask.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            } else {
                buffer = ((OkHttpWritableBuffer) writableBuffer).buffer();
                int size = (int) buffer.size();
                if (size > 0) {
                    OkHttpClientStream.this.onSendingBytes(size);
                }
            }
            synchronized (OkHttpClientStream.this.state.lock) {
                OkHttpClientStream.this.state.sendBuffer(buffer, z, z2);
                OkHttpClientStream.this.getTransportTracer().reportMessageSent(i);
            }
            if (traceTask != null) {
                traceTask.close();
            }
        }

        public void cancel(Status status) {
            TaskCloseable traceTask = PerfMark.traceTask("OkHttpClientStream$Sink.cancel");
            try {
                synchronized (OkHttpClientStream.this.state.lock) {
                    OkHttpClientStream.this.state.cancel(status, true, (Metadata) null);
                }
                if (traceTask != null) {
                    traceTask.close();
                }
            } catch (Throwable th) {
                if (traceTask != null) {
                    try {
                        traceTask.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
    }

    class TransportState extends Http2ClientStreamTransportState implements OutboundFlowController.Stream {
        private boolean canStart = true;
        private boolean cancelSent = false;
        private boolean flushPendingData = false;
        private final ExceptionHandlingFrameWriter frameWriter;
        private int id = -1;
        private final int initialWindowSize;
        /* access modifiers changed from: private */
        public final Object lock;
        private final OutboundFlowController outboundFlow;
        private OutboundFlowController.StreamState outboundFlowState;
        private Buffer pendingData = new Buffer();
        private boolean pendingDataHasEndOfStream = false;
        private int processedWindow;
        private List<Header> requestHeaders;
        private final Tag tag;
        private final OkHttpClientTransport transport;
        private int window;

        public TransportState(int i, StatsTraceContext statsTraceContext, Object obj, ExceptionHandlingFrameWriter exceptionHandlingFrameWriter, OutboundFlowController outboundFlowController, OkHttpClientTransport okHttpClientTransport, int i2, String str) {
            super(i, statsTraceContext, OkHttpClientStream.this.getTransportTracer());
            this.lock = Preconditions.checkNotNull(obj, SentryStackFrame.JsonKeys.LOCK);
            this.frameWriter = exceptionHandlingFrameWriter;
            this.outboundFlow = outboundFlowController;
            this.transport = okHttpClientTransport;
            this.window = i2;
            this.processedWindow = i2;
            this.initialWindowSize = i2;
            this.tag = PerfMark.createTag(str);
        }

        public void start(int i) {
            Preconditions.checkState(this.id == -1, "the stream has been started with id %s", i);
            this.id = i;
            this.outboundFlowState = this.outboundFlow.createState(this, i);
            OkHttpClientStream.this.state.onStreamAllocated();
            if (this.canStart) {
                this.frameWriter.synStream(OkHttpClientStream.this.useGet, false, this.id, 0, this.requestHeaders);
                OkHttpClientStream.this.statsTraceCtx.clientOutboundHeaders();
                this.requestHeaders = null;
                if (this.pendingData.size() > 0) {
                    this.outboundFlow.data(this.pendingDataHasEndOfStream, this.outboundFlowState, this.pendingData, this.flushPendingData);
                }
                this.canStart = false;
            }
        }

        /* access modifiers changed from: protected */
        public void onStreamAllocated() {
            super.onStreamAllocated();
            getTransportTracer().reportLocalStreamStarted();
        }

        /* access modifiers changed from: protected */
        public void http2ProcessingFailed(Status status, boolean z, Metadata metadata) {
            cancel(status, z, metadata);
        }

        public void deframeFailed(Throwable th) {
            http2ProcessingFailed(Status.fromThrowable(th), true, new Metadata());
        }

        public void bytesRead(int i) {
            int i2 = this.processedWindow - i;
            this.processedWindow = i2;
            int i3 = this.initialWindowSize;
            if (((float) i2) <= ((float) i3) * 0.5f) {
                int i4 = i3 - i2;
                this.window += i4;
                this.processedWindow = i2 + i4;
                this.frameWriter.windowUpdate(id(), (long) i4);
            }
        }

        public void deframerClosed(boolean z) {
            onEndOfStream();
            super.deframerClosed(z);
        }

        public void runOnTransportThread(Runnable runnable) {
            synchronized (this.lock) {
                runnable.run();
            }
        }

        public void transportHeadersReceived(List<Header> list, boolean z) {
            if (z) {
                transportTrailersReceived(Utils.convertTrailers(list));
            } else {
                transportHeadersReceived(Utils.convertHeaders(list));
            }
        }

        public void transportDataReceived(Buffer buffer, boolean z, int i) {
            int size = this.window - (((int) buffer.size()) + i);
            this.window = size;
            this.processedWindow -= i;
            if (size < 0) {
                this.frameWriter.rstStream(id(), ErrorCode.FLOW_CONTROL_ERROR);
                this.transport.finishStream(id(), Status.INTERNAL.withDescription("Received data size exceeded our receiving window size"), ClientStreamListener.RpcProgress.PROCESSED, false, (ErrorCode) null, (Metadata) null);
                return;
            }
            super.transportDataReceived(new OkHttpReadableBuffer(buffer), z);
        }

        private void onEndOfStream() {
            if (!isOutboundClosed()) {
                this.transport.finishStream(id(), (Status) null, ClientStreamListener.RpcProgress.PROCESSED, false, ErrorCode.CANCEL, (Metadata) null);
            } else {
                this.transport.finishStream(id(), (Status) null, ClientStreamListener.RpcProgress.PROCESSED, false, (ErrorCode) null, (Metadata) null);
            }
        }

        /* access modifiers changed from: private */
        public void cancel(Status status, boolean z, Metadata metadata) {
            if (!this.cancelSent) {
                this.cancelSent = true;
                if (this.canStart) {
                    this.transport.removePendingStream(OkHttpClientStream.this);
                    this.requestHeaders = null;
                    this.pendingData.clear();
                    this.canStart = false;
                    if (metadata == null) {
                        metadata = new Metadata();
                    }
                    transportReportStatus(status, true, metadata);
                    return;
                }
                this.transport.finishStream(id(), status, ClientStreamListener.RpcProgress.PROCESSED, z, ErrorCode.CANCEL, metadata);
            }
        }

        /* access modifiers changed from: private */
        public void sendBuffer(Buffer buffer, boolean z, boolean z2) {
            if (!this.cancelSent) {
                if (this.canStart) {
                    this.pendingData.write(buffer, (long) ((int) buffer.size()));
                    this.pendingDataHasEndOfStream |= z;
                    this.flushPendingData |= z2;
                    return;
                }
                Preconditions.checkState(id() != -1, "streamId should be set");
                this.outboundFlow.data(z, this.outboundFlowState, buffer, z2);
            }
        }

        /* access modifiers changed from: private */
        public void streamReady(Metadata metadata, String str) {
            this.requestHeaders = Headers.createRequestHeaders(metadata, str, OkHttpClientStream.this.authority, OkHttpClientStream.this.userAgent, OkHttpClientStream.this.useGet, this.transport.isUsingPlaintext());
            this.transport.streamReadyToStart(OkHttpClientStream.this);
        }

        /* access modifiers changed from: package-private */
        public Tag tag() {
            return this.tag;
        }

        /* access modifiers changed from: package-private */
        public int id() {
            return this.id;
        }

        /* access modifiers changed from: package-private */
        public OutboundFlowController.StreamState getOutboundFlowState() {
            OutboundFlowController.StreamState streamState;
            synchronized (this.lock) {
                streamState = this.outboundFlowState;
            }
            return streamState;
        }
    }
}
