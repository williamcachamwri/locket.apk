package io.grpc.internal;

import com.google.common.base.Preconditions;
import com.google.common.io.ByteStreams;
import io.grpc.Codec;
import io.grpc.Compressor;
import io.grpc.Drainable;
import io.grpc.KnownLength;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;

public class MessageFramer implements Framer {
    private static final byte COMPRESSED = 1;
    private static final int HEADER_LENGTH = 5;
    private static final int NO_MAX_OUTBOUND_MESSAGE_SIZE = -1;
    private static final byte UNCOMPRESSED = 0;
    private WritableBuffer buffer;
    /* access modifiers changed from: private */
    public final WritableBufferAllocator bufferAllocator;
    private boolean closed;
    private Compressor compressor = Codec.Identity.NONE;
    private int currentMessageSeqNo = -1;
    private long currentMessageWireSize;
    private final ByteBuffer headerScratch = ByteBuffer.allocate(5);
    private int maxOutboundMessageSize = -1;
    private boolean messageCompression = true;
    private int messagesBuffered;
    private final OutputStreamAdapter outputStreamAdapter = new OutputStreamAdapter();
    private final Sink sink;
    private final StatsTraceContext statsTraceCtx;

    public interface Sink {
        void deliverFrame(@Nullable WritableBuffer writableBuffer, boolean z, boolean z2, int i);
    }

    public MessageFramer(Sink sink2, WritableBufferAllocator writableBufferAllocator, StatsTraceContext statsTraceContext) {
        this.sink = (Sink) Preconditions.checkNotNull(sink2, "sink");
        this.bufferAllocator = (WritableBufferAllocator) Preconditions.checkNotNull(writableBufferAllocator, "bufferAllocator");
        this.statsTraceCtx = (StatsTraceContext) Preconditions.checkNotNull(statsTraceContext, "statsTraceCtx");
    }

    public MessageFramer setCompressor(Compressor compressor2) {
        this.compressor = (Compressor) Preconditions.checkNotNull(compressor2, "Can't pass an empty compressor");
        return this;
    }

    public MessageFramer setMessageCompression(boolean z) {
        this.messageCompression = z;
        return this;
    }

    public void setMaxOutboundMessageSize(int i) {
        Preconditions.checkState(this.maxOutboundMessageSize == -1, "max size already set");
        this.maxOutboundMessageSize = i;
    }

    public void writePayload(InputStream inputStream) {
        int i;
        verifyNotClosed();
        boolean z = true;
        this.messagesBuffered++;
        int i2 = this.currentMessageSeqNo + 1;
        this.currentMessageSeqNo = i2;
        this.currentMessageWireSize = 0;
        this.statsTraceCtx.outboundMessage(i2);
        if (!this.messageCompression || this.compressor == Codec.Identity.NONE) {
            z = false;
        }
        try {
            int knownLength = getKnownLength(inputStream);
            if (knownLength == 0 || !z) {
                i = writeUncompressed(inputStream, knownLength);
            } else {
                i = writeCompressed(inputStream, knownLength);
            }
            if (knownLength == -1 || i == knownLength) {
                long j = (long) i;
                this.statsTraceCtx.outboundUncompressedSize(j);
                this.statsTraceCtx.outboundWireSize(this.currentMessageWireSize);
                this.statsTraceCtx.outboundMessageSent(this.currentMessageSeqNo, this.currentMessageWireSize, j);
                return;
            }
            throw Status.INTERNAL.withDescription(String.format("Message length inaccurate %s != %s", new Object[]{Integer.valueOf(i), Integer.valueOf(knownLength)})).asRuntimeException();
        } catch (IOException e) {
            throw Status.INTERNAL.withDescription("Failed to frame message").withCause(e).asRuntimeException();
        } catch (StatusRuntimeException e2) {
            throw e2;
        } catch (RuntimeException e3) {
            throw Status.INTERNAL.withDescription("Failed to frame message").withCause(e3).asRuntimeException();
        }
    }

    private int writeUncompressed(InputStream inputStream, int i) throws IOException {
        if (i != -1) {
            this.currentMessageWireSize = (long) i;
            return writeKnownLengthUncompressed(inputStream, i);
        }
        BufferChainOutputStream bufferChainOutputStream = new BufferChainOutputStream();
        int writeToOutputStream = writeToOutputStream(inputStream, bufferChainOutputStream);
        writeBufferChain(bufferChainOutputStream, false);
        return writeToOutputStream;
    }

    /* JADX INFO: finally extract failed */
    private int writeCompressed(InputStream inputStream, int i) throws IOException {
        BufferChainOutputStream bufferChainOutputStream = new BufferChainOutputStream();
        OutputStream compress = this.compressor.compress(bufferChainOutputStream);
        try {
            int writeToOutputStream = writeToOutputStream(inputStream, compress);
            compress.close();
            int i2 = this.maxOutboundMessageSize;
            if (i2 < 0 || writeToOutputStream <= i2) {
                writeBufferChain(bufferChainOutputStream, true);
                return writeToOutputStream;
            }
            throw Status.RESOURCE_EXHAUSTED.withDescription(String.format(Locale.US, "message too large %d > %d", new Object[]{Integer.valueOf(writeToOutputStream), Integer.valueOf(this.maxOutboundMessageSize)})).asRuntimeException();
        } catch (Throwable th) {
            compress.close();
            throw th;
        }
    }

    private int getKnownLength(InputStream inputStream) throws IOException {
        if ((inputStream instanceof KnownLength) || (inputStream instanceof ByteArrayInputStream)) {
            return inputStream.available();
        }
        return -1;
    }

    private int writeKnownLengthUncompressed(InputStream inputStream, int i) throws IOException {
        int i2 = this.maxOutboundMessageSize;
        if (i2 < 0 || i <= i2) {
            this.headerScratch.clear();
            this.headerScratch.put((byte) 0).putInt(i);
            if (this.buffer == null) {
                this.buffer = this.bufferAllocator.allocate(this.headerScratch.position() + i);
            }
            writeRaw(this.headerScratch.array(), 0, this.headerScratch.position());
            return writeToOutputStream(inputStream, this.outputStreamAdapter);
        }
        throw Status.RESOURCE_EXHAUSTED.withDescription(String.format(Locale.US, "message too large %d > %d", new Object[]{Integer.valueOf(i), Integer.valueOf(this.maxOutboundMessageSize)})).asRuntimeException();
    }

    private void writeBufferChain(BufferChainOutputStream bufferChainOutputStream, boolean z) {
        int access$200 = bufferChainOutputStream.readableBytes();
        int i = this.maxOutboundMessageSize;
        if (i < 0 || access$200 <= i) {
            this.headerScratch.clear();
            this.headerScratch.put(z ? (byte) 1 : 0).putInt(access$200);
            WritableBuffer allocate = this.bufferAllocator.allocate(5);
            allocate.write(this.headerScratch.array(), 0, this.headerScratch.position());
            if (access$200 == 0) {
                this.buffer = allocate;
                return;
            }
            this.sink.deliverFrame(allocate, false, false, this.messagesBuffered - 1);
            this.messagesBuffered = 1;
            List access$300 = bufferChainOutputStream.bufferList;
            for (int i2 = 0; i2 < access$300.size() - 1; i2++) {
                this.sink.deliverFrame((WritableBuffer) access$300.get(i2), false, false, 0);
            }
            this.buffer = (WritableBuffer) access$300.get(access$300.size() - 1);
            this.currentMessageWireSize = (long) access$200;
            return;
        }
        throw Status.RESOURCE_EXHAUSTED.withDescription(String.format(Locale.US, "message too large %d > %d", new Object[]{Integer.valueOf(access$200), Integer.valueOf(this.maxOutboundMessageSize)})).asRuntimeException();
    }

    private static int writeToOutputStream(InputStream inputStream, OutputStream outputStream) throws IOException {
        if (inputStream instanceof Drainable) {
            return ((Drainable) inputStream).drainTo(outputStream);
        }
        long copy = ByteStreams.copy(inputStream, outputStream);
        Preconditions.checkArgument(copy <= 2147483647L, "Message size overflow: %s", copy);
        return (int) copy;
    }

    /* access modifiers changed from: private */
    public void writeRaw(byte[] bArr, int i, int i2) {
        while (i2 > 0) {
            WritableBuffer writableBuffer = this.buffer;
            if (writableBuffer != null && writableBuffer.writableBytes() == 0) {
                commitToSink(false, false);
            }
            if (this.buffer == null) {
                this.buffer = this.bufferAllocator.allocate(i2);
            }
            int min = Math.min(i2, this.buffer.writableBytes());
            this.buffer.write(bArr, i, min);
            i += min;
            i2 -= min;
        }
    }

    public void flush() {
        WritableBuffer writableBuffer = this.buffer;
        if (writableBuffer != null && writableBuffer.readableBytes() > 0) {
            commitToSink(false, true);
        }
    }

    public boolean isClosed() {
        return this.closed;
    }

    public void close() {
        if (!isClosed()) {
            this.closed = true;
            WritableBuffer writableBuffer = this.buffer;
            if (writableBuffer != null && writableBuffer.readableBytes() == 0) {
                releaseBuffer();
            }
            commitToSink(true, true);
        }
    }

    public void dispose() {
        this.closed = true;
        releaseBuffer();
    }

    private void releaseBuffer() {
        WritableBuffer writableBuffer = this.buffer;
        if (writableBuffer != null) {
            writableBuffer.release();
            this.buffer = null;
        }
    }

    private void commitToSink(boolean z, boolean z2) {
        WritableBuffer writableBuffer = this.buffer;
        this.buffer = null;
        this.sink.deliverFrame(writableBuffer, z, z2, this.messagesBuffered);
        this.messagesBuffered = 0;
    }

    private void verifyNotClosed() {
        if (isClosed()) {
            throw new IllegalStateException("Framer already closed");
        }
    }

    private class OutputStreamAdapter extends OutputStream {
        private OutputStreamAdapter() {
        }

        public void write(int i) {
            write(new byte[]{(byte) i}, 0, 1);
        }

        public void write(byte[] bArr, int i, int i2) {
            MessageFramer.this.writeRaw(bArr, i, i2);
        }
    }

    private final class BufferChainOutputStream extends OutputStream {
        /* access modifiers changed from: private */
        public final List<WritableBuffer> bufferList;
        private WritableBuffer current;

        private BufferChainOutputStream() {
            this.bufferList = new ArrayList();
        }

        public void write(int i) throws IOException {
            WritableBuffer writableBuffer = this.current;
            if (writableBuffer == null || writableBuffer.writableBytes() <= 0) {
                write(new byte[]{(byte) i}, 0, 1);
                return;
            }
            this.current.write((byte) i);
        }

        public void write(byte[] bArr, int i, int i2) {
            if (this.current == null) {
                WritableBuffer allocate = MessageFramer.this.bufferAllocator.allocate(i2);
                this.current = allocate;
                this.bufferList.add(allocate);
            }
            while (i2 > 0) {
                int min = Math.min(i2, this.current.writableBytes());
                if (min == 0) {
                    WritableBuffer allocate2 = MessageFramer.this.bufferAllocator.allocate(Math.max(i2, this.current.readableBytes() * 2));
                    this.current = allocate2;
                    this.bufferList.add(allocate2);
                } else {
                    this.current.write(bArr, i, min);
                    i += min;
                    i2 -= min;
                }
            }
        }

        /* access modifiers changed from: private */
        public int readableBytes() {
            int i = 0;
            for (WritableBuffer readableBytes : this.bufferList) {
                i += readableBytes.readableBytes();
            }
            return i;
        }
    }
}
