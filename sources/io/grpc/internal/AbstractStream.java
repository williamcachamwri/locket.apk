package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.Codec;
import io.grpc.Compressor;
import io.grpc.Decompressor;
import io.grpc.internal.ApplicationThreadDeframer;
import io.grpc.internal.MessageDeframer;
import io.grpc.internal.StreamListener;
import io.perfmark.Link;
import io.perfmark.PerfMark;
import io.perfmark.TaskCloseable;
import java.io.Closeable;
import java.io.InputStream;

public abstract class AbstractStream implements Stream {
    /* access modifiers changed from: protected */
    public abstract Framer framer();

    /* access modifiers changed from: protected */
    public abstract TransportState transportState();

    public void optimizeForDirectExecutor() {
        transportState().optimizeForDirectExecutor();
    }

    public final void setMessageCompression(boolean z) {
        framer().setMessageCompression(z);
    }

    public final void request(int i) {
        transportState().requestMessagesFromDeframer(i);
    }

    public final void writeMessage(InputStream inputStream) {
        Preconditions.checkNotNull(inputStream, "message");
        try {
            if (!framer().isClosed()) {
                framer().writePayload(inputStream);
            }
        } finally {
            GrpcUtil.closeQuietly((Closeable) inputStream);
        }
    }

    public final void flush() {
        if (!framer().isClosed()) {
            framer().flush();
        }
    }

    /* access modifiers changed from: protected */
    public final void endOfMessages() {
        framer().close();
    }

    public final void setCompressor(Compressor compressor) {
        framer().setCompressor((Compressor) Preconditions.checkNotNull(compressor, "compressor"));
    }

    public boolean isReady() {
        return transportState().isReady();
    }

    /* access modifiers changed from: protected */
    public final void onSendingBytes(int i) {
        transportState().onSendingBytes(i);
    }

    public static abstract class TransportState implements ApplicationThreadDeframer.TransportExecutor, MessageDeframer.Listener {
        public static final int DEFAULT_ONREADY_THRESHOLD = 32768;
        private boolean allocated;
        private boolean deallocated;
        /* access modifiers changed from: private */
        public Deframer deframer;
        private int numSentBytesQueued;
        private final Object onReadyLock = new Object();
        private final MessageDeframer rawDeframer;
        private final StatsTraceContext statsTraceCtx;
        private final TransportTracer transportTracer;

        /* access modifiers changed from: protected */
        public abstract StreamListener listener();

        protected TransportState(int i, StatsTraceContext statsTraceContext, TransportTracer transportTracer2) {
            this.statsTraceCtx = (StatsTraceContext) Preconditions.checkNotNull(statsTraceContext, "statsTraceCtx");
            this.transportTracer = (TransportTracer) Preconditions.checkNotNull(transportTracer2, "transportTracer");
            MessageDeframer messageDeframer = new MessageDeframer(this, Codec.Identity.NONE, i, statsTraceContext, transportTracer2);
            this.rawDeframer = messageDeframer;
            this.deframer = messageDeframer;
        }

        /* access modifiers changed from: package-private */
        public final void optimizeForDirectExecutor() {
            this.rawDeframer.setListener(this);
            this.deframer = this.rawDeframer;
        }

        /* access modifiers changed from: protected */
        public void setFullStreamDecompressor(GzipInflatingBuffer gzipInflatingBuffer) {
            this.rawDeframer.setFullStreamDecompressor(gzipInflatingBuffer);
            this.deframer = new ApplicationThreadDeframer(this, this, this.rawDeframer);
        }

        /* access modifiers changed from: package-private */
        public final void setMaxInboundMessageSize(int i) {
            this.deframer.setMaxInboundMessageSize(i);
        }

        public void messagesAvailable(StreamListener.MessageProducer messageProducer) {
            listener().messagesAvailable(messageProducer);
        }

        /* access modifiers changed from: protected */
        public final void closeDeframer(boolean z) {
            if (z) {
                this.deframer.close();
            } else {
                this.deframer.closeWhenComplete();
            }
        }

        /* access modifiers changed from: protected */
        public final void deframe(ReadableBuffer readableBuffer) {
            try {
                this.deframer.deframe(readableBuffer);
            } catch (Throwable th) {
                deframeFailed(th);
            }
        }

        /* access modifiers changed from: private */
        public void requestMessagesFromDeframer(final int i) {
            if (this.deframer instanceof ThreadOptimizedDeframer) {
                TaskCloseable traceTask = PerfMark.traceTask("AbstractStream.request");
                try {
                    this.deframer.request(i);
                    if (traceTask != null) {
                        traceTask.close();
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            } else {
                final Link linkOut = PerfMark.linkOut();
                runOnTransportThread(new Runnable() {
                    public void run() {
                        TaskCloseable traceTask;
                        try {
                            traceTask = PerfMark.traceTask("AbstractStream.request");
                            PerfMark.linkIn(linkOut);
                            TransportState.this.deframer.request(i);
                            if (traceTask != null) {
                                traceTask.close();
                                return;
                            }
                            return;
                        } catch (Throwable th) {
                            TransportState.this.deframeFailed(th);
                            return;
                        }
                        throw th;
                    }
                });
                return;
            }
            throw th;
        }

        public final void requestMessagesFromDeframerForTesting(int i) {
            requestMessagesFromDeframer(i);
        }

        public final StatsTraceContext getStatsTraceContext() {
            return this.statsTraceCtx;
        }

        /* access modifiers changed from: protected */
        public final void setDecompressor(Decompressor decompressor) {
            this.deframer.setDecompressor(decompressor);
        }

        /* access modifiers changed from: private */
        public boolean isReady() {
            boolean z;
            synchronized (this.onReadyLock) {
                z = this.allocated && this.numSentBytesQueued < 32768 && !this.deallocated;
            }
            return z;
        }

        /* access modifiers changed from: protected */
        public void onStreamAllocated() {
            boolean z = false;
            Preconditions.checkState(listener() != null);
            synchronized (this.onReadyLock) {
                if (!this.allocated) {
                    z = true;
                }
                Preconditions.checkState(z, "Already allocated");
                this.allocated = true;
            }
            notifyIfReady();
        }

        /* access modifiers changed from: protected */
        public final void onStreamDeallocated() {
            synchronized (this.onReadyLock) {
                this.deallocated = true;
            }
        }

        /* access modifiers changed from: private */
        public void onSendingBytes(int i) {
            synchronized (this.onReadyLock) {
                this.numSentBytesQueued += i;
            }
        }

        public final void onSentBytes(int i) {
            boolean z;
            synchronized (this.onReadyLock) {
                Preconditions.checkState(this.allocated, "onStreamAllocated was not called, but it seems the stream is active");
                int i2 = this.numSentBytesQueued;
                z = true;
                boolean z2 = i2 < 32768;
                int i3 = i2 - i;
                this.numSentBytesQueued = i3;
                boolean z3 = i3 < 32768;
                if (z2 || !z3) {
                    z = false;
                }
            }
            if (z) {
                notifyIfReady();
            }
        }

        /* access modifiers changed from: protected */
        public TransportTracer getTransportTracer() {
            return this.transportTracer;
        }

        private void notifyIfReady() {
            boolean isReady;
            synchronized (this.onReadyLock) {
                isReady = isReady();
            }
            if (isReady) {
                listener().onReady();
            }
        }
    }
}
