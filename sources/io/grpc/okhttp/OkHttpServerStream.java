package io.grpc.okhttp;

import androidx.core.app.NotificationCompat;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.AbstractServerStream;
import io.grpc.internal.StatsTraceContext;
import io.grpc.internal.TransportTracer;
import io.grpc.internal.WritableBuffer;
import io.grpc.okhttp.OkHttpServerTransport;
import io.grpc.okhttp.OutboundFlowController;
import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.Header;
import io.perfmark.PerfMark;
import io.perfmark.Tag;
import io.perfmark.TaskCloseable;
import io.sentry.protocol.SentryStackFrame;
import java.util.List;
import okio.Buffer;

class OkHttpServerStream extends AbstractServerStream {
    private final Attributes attributes;
    private final String authority;
    private final Sink sink = new Sink();
    /* access modifiers changed from: private */
    public final TransportState state;
    /* access modifiers changed from: private */
    public final TransportTracer transportTracer;

    public OkHttpServerStream(TransportState transportState, Attributes attributes2, String str, StatsTraceContext statsTraceContext, TransportTracer transportTracer2) {
        super(new OkHttpWritableBufferAllocator(), statsTraceContext);
        this.state = (TransportState) Preconditions.checkNotNull(transportState, "state");
        this.attributes = (Attributes) Preconditions.checkNotNull(attributes2, "transportAttrs");
        this.authority = str;
        this.transportTracer = (TransportTracer) Preconditions.checkNotNull(transportTracer2, "transportTracer");
    }

    /* access modifiers changed from: protected */
    public TransportState transportState() {
        return this.state;
    }

    /* access modifiers changed from: protected */
    public Sink abstractServerStreamSink() {
        return this.sink;
    }

    public int streamId() {
        return this.state.streamId;
    }

    public String getAuthority() {
        return this.authority;
    }

    public Attributes getAttributes() {
        return this.attributes;
    }

    class Sink implements AbstractServerStream.Sink {
        Sink() {
        }

        public void writeHeaders(Metadata metadata, boolean z) {
            TaskCloseable traceTask = PerfMark.traceTask("OkHttpServerStream$Sink.writeHeaders");
            try {
                List<Header> createResponseHeaders = Headers.createResponseHeaders(metadata);
                synchronized (OkHttpServerStream.this.state.lock) {
                    OkHttpServerStream.this.state.sendHeaders(createResponseHeaders);
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

        public void writeFrame(WritableBuffer writableBuffer, boolean z, int i) {
            TaskCloseable traceTask = PerfMark.traceTask("OkHttpServerStream$Sink.writeFrame");
            try {
                Buffer buffer = ((OkHttpWritableBuffer) writableBuffer).buffer();
                int size = (int) buffer.size();
                if (size > 0) {
                    OkHttpServerStream.this.onSendingBytes(size);
                }
                synchronized (OkHttpServerStream.this.state.lock) {
                    OkHttpServerStream.this.state.sendBuffer(buffer, z);
                    OkHttpServerStream.this.transportTracer.reportMessageSent(i);
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

        public void writeTrailers(Metadata metadata, boolean z, Status status) {
            TaskCloseable traceTask = PerfMark.traceTask("OkHttpServerStream$Sink.writeTrailers");
            try {
                List<Header> createResponseTrailers = Headers.createResponseTrailers(metadata, z);
                synchronized (OkHttpServerStream.this.state.lock) {
                    OkHttpServerStream.this.state.sendTrailers(createResponseTrailers);
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

        public void cancel(Status status) {
            TaskCloseable traceTask = PerfMark.traceTask("OkHttpServerStream$Sink.cancel");
            try {
                synchronized (OkHttpServerStream.this.state.lock) {
                    OkHttpServerStream.this.state.cancel(ErrorCode.CANCEL, status);
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

    static class TransportState extends AbstractServerStream.TransportState implements OutboundFlowController.Stream, OkHttpServerTransport.StreamState {
        private boolean cancelSent = false;
        private final ExceptionHandlingFrameWriter frameWriter;
        private final int initialWindowSize;
        /* access modifiers changed from: private */
        public final Object lock;
        private final OutboundFlowController outboundFlow;
        private final OutboundFlowController.StreamState outboundFlowState;
        private int processedWindow;
        private boolean receivedEndOfStream;
        /* access modifiers changed from: private */
        public final int streamId;
        private final Tag tag;
        private final OkHttpServerTransport transport;
        private int window;

        public TransportState(OkHttpServerTransport okHttpServerTransport, int i, int i2, StatsTraceContext statsTraceContext, Object obj, ExceptionHandlingFrameWriter exceptionHandlingFrameWriter, OutboundFlowController outboundFlowController, int i3, TransportTracer transportTracer, String str) {
            super(i2, statsTraceContext, transportTracer);
            this.transport = (OkHttpServerTransport) Preconditions.checkNotNull(okHttpServerTransport, NotificationCompat.CATEGORY_TRANSPORT);
            this.streamId = i;
            this.lock = Preconditions.checkNotNull(obj, SentryStackFrame.JsonKeys.LOCK);
            this.frameWriter = exceptionHandlingFrameWriter;
            this.outboundFlow = outboundFlowController;
            this.window = i3;
            this.processedWindow = i3;
            this.initialWindowSize = i3;
            this.tag = PerfMark.createTag(str);
            this.outboundFlowState = outboundFlowController.createState(this, i);
        }

        public void deframeFailed(Throwable th) {
            cancel(ErrorCode.INTERNAL_ERROR, Status.fromThrowable(th));
        }

        public void bytesRead(int i) {
            int i2 = this.processedWindow - i;
            this.processedWindow = i2;
            int i3 = this.initialWindowSize;
            if (((float) i2) <= ((float) i3) * 0.5f) {
                int i4 = i3 - i2;
                this.window += i4;
                this.processedWindow = i2 + i4;
                this.frameWriter.windowUpdate(this.streamId, (long) i4);
                this.frameWriter.flush();
            }
        }

        public void runOnTransportThread(Runnable runnable) {
            synchronized (this.lock) {
                runnable.run();
            }
        }

        public void inboundDataReceived(Buffer buffer, int i, int i2, boolean z) {
            synchronized (this.lock) {
                PerfMark.event("OkHttpServerTransport$FrameHandler.data", this.tag);
                if (z) {
                    this.receivedEndOfStream = true;
                }
                this.window -= i + i2;
                this.processedWindow -= i2;
                super.inboundDataReceived(new OkHttpReadableBuffer(buffer), z);
            }
        }

        public void inboundRstReceived(Status status) {
            PerfMark.event("OkHttpServerTransport$FrameHandler.rstStream", this.tag);
            transportReportStatus(status);
        }

        public boolean hasReceivedEndOfStream() {
            boolean z;
            synchronized (this.lock) {
                z = this.receivedEndOfStream;
            }
            return z;
        }

        public int inboundWindowAvailable() {
            int i;
            synchronized (this.lock) {
                i = this.window;
            }
            return i;
        }

        /* access modifiers changed from: private */
        public void sendBuffer(Buffer buffer, boolean z) {
            if (!this.cancelSent) {
                this.outboundFlow.data(false, this.outboundFlowState, buffer, z);
            }
        }

        /* access modifiers changed from: private */
        public void sendHeaders(List<Header> list) {
            this.frameWriter.synReply(false, this.streamId, list);
            this.frameWriter.flush();
        }

        /* access modifiers changed from: private */
        public void sendTrailers(List<Header> list) {
            this.outboundFlow.notifyWhenNoPendingData(this.outboundFlowState, new OkHttpServerStream$TransportState$$ExternalSyntheticLambda0(this, list));
        }

        /* access modifiers changed from: private */
        /* renamed from: sendTrailersAfterFlowControlled */
        public void m2331lambda$sendTrailers$0$iogrpcokhttpOkHttpServerStream$TransportState(List<Header> list) {
            synchronized (this.lock) {
                this.frameWriter.synReply(true, this.streamId, list);
                if (!this.receivedEndOfStream) {
                    this.frameWriter.rstStream(this.streamId, ErrorCode.NO_ERROR);
                }
                this.transport.streamClosed(this.streamId, true);
                complete();
            }
        }

        /* access modifiers changed from: private */
        public void cancel(ErrorCode errorCode, Status status) {
            if (!this.cancelSent) {
                this.cancelSent = true;
                this.frameWriter.rstStream(this.streamId, errorCode);
                transportReportStatus(status);
                this.transport.streamClosed(this.streamId, true);
            }
        }

        public OutboundFlowController.StreamState getOutboundFlowState() {
            return this.outboundFlowState;
        }
    }
}
