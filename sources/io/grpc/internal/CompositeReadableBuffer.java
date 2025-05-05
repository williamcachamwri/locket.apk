package io.grpc.internal;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.InvalidMarkException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import javax.annotation.Nullable;

public class CompositeReadableBuffer extends AbstractReadableBuffer {
    private static final NoThrowReadOperation<byte[]> BYTE_ARRAY_OP = new NoThrowReadOperation<byte[]>() {
        public int read(ReadableBuffer readableBuffer, int i, byte[] bArr, int i2) {
            readableBuffer.readBytes(bArr, i2, i);
            return i2 + i;
        }
    };
    private static final NoThrowReadOperation<ByteBuffer> BYTE_BUF_OP = new NoThrowReadOperation<ByteBuffer>() {
        public int read(ReadableBuffer readableBuffer, int i, ByteBuffer byteBuffer, int i2) {
            int limit = byteBuffer.limit();
            byteBuffer.limit(byteBuffer.position() + i);
            readableBuffer.readBytes(byteBuffer);
            byteBuffer.limit(limit);
            return 0;
        }
    };
    private static final NoThrowReadOperation<Void> SKIP_OP = new NoThrowReadOperation<Void>() {
        public int read(ReadableBuffer readableBuffer, int i, Void voidR, int i2) {
            readableBuffer.skipBytes(i);
            return 0;
        }
    };
    private static final ReadOperation<OutputStream> STREAM_OP = new ReadOperation<OutputStream>() {
        public int read(ReadableBuffer readableBuffer, int i, OutputStream outputStream, int i2) throws IOException {
            readableBuffer.readBytes(outputStream, i);
            return 0;
        }
    };
    private static final NoThrowReadOperation<Void> UBYTE_OP = new NoThrowReadOperation<Void>() {
        public int read(ReadableBuffer readableBuffer, int i, Void voidR, int i2) {
            return readableBuffer.readUnsignedByte();
        }
    };
    private final Queue<ReadableBuffer> buffers;
    private boolean marked;
    private final Deque<ReadableBuffer> readableBuffers;
    private int readableBytes;
    private Deque<ReadableBuffer> rewindableBuffers;

    private interface NoThrowReadOperation<T> extends ReadOperation<T> {
        int read(ReadableBuffer readableBuffer, int i, T t, int i2);
    }

    private interface ReadOperation<T> {
        int read(ReadableBuffer readableBuffer, int i, T t, int i2) throws IOException;
    }

    public CompositeReadableBuffer(int i) {
        this.buffers = new ArrayDeque(2);
        this.readableBuffers = new ArrayDeque(i);
    }

    public CompositeReadableBuffer() {
        this.buffers = new ArrayDeque(2);
        this.readableBuffers = new ArrayDeque();
    }

    public void addBuffer(ReadableBuffer readableBuffer) {
        boolean z = this.marked && this.readableBuffers.isEmpty();
        enqueueBuffer(readableBuffer);
        if (z) {
            this.readableBuffers.peek().mark();
        }
    }

    private void enqueueBuffer(ReadableBuffer readableBuffer) {
        if (!(readableBuffer instanceof CompositeReadableBuffer)) {
            this.readableBuffers.add(readableBuffer);
            this.readableBytes += readableBuffer.readableBytes();
            return;
        }
        CompositeReadableBuffer compositeReadableBuffer = (CompositeReadableBuffer) readableBuffer;
        while (!compositeReadableBuffer.readableBuffers.isEmpty()) {
            this.readableBuffers.add(compositeReadableBuffer.readableBuffers.remove());
        }
        this.readableBytes += compositeReadableBuffer.readableBytes;
        compositeReadableBuffer.readableBytes = 0;
        compositeReadableBuffer.close();
    }

    public int readableBytes() {
        return this.readableBytes;
    }

    public int readUnsignedByte() {
        return executeNoThrow(UBYTE_OP, 1, (Object) null, 0);
    }

    public void skipBytes(int i) {
        executeNoThrow(SKIP_OP, i, (Object) null, 0);
    }

    public void readBytes(byte[] bArr, int i, int i2) {
        executeNoThrow(BYTE_ARRAY_OP, i2, bArr, i);
    }

    public void readBytes(ByteBuffer byteBuffer) {
        executeNoThrow(BYTE_BUF_OP, byteBuffer.remaining(), byteBuffer, 0);
    }

    public void readBytes(OutputStream outputStream, int i) throws IOException {
        execute(STREAM_OP, i, outputStream, 0);
    }

    public void readBytes(CompositeReadableBuffer compositeReadableBuffer, int i) {
        checkReadable(i);
        this.readableBytes -= i;
        while (i > 0) {
            ReadableBuffer peek = this.buffers.peek();
            if (peek.readableBytes() > i) {
                compositeReadableBuffer.addBuffer(peek.readBytes(i));
                i = 0;
            } else {
                compositeReadableBuffer.addBuffer(this.buffers.poll());
                i -= peek.readableBytes();
            }
        }
    }

    public ReadableBuffer readBytes(int i) {
        ReadableBuffer readableBuffer;
        int i2;
        ReadableBuffer readableBuffer2;
        if (i <= 0) {
            return ReadableBuffers.empty();
        }
        checkReadable(i);
        this.readableBytes -= i;
        CompositeReadableBuffer compositeReadableBuffer = null;
        CompositeReadableBuffer compositeReadableBuffer2 = null;
        while (true) {
            ReadableBuffer peek = this.readableBuffers.peek();
            int readableBytes2 = peek.readableBytes();
            if (readableBytes2 > i) {
                readableBuffer = peek.readBytes(i);
                i2 = 0;
            } else {
                if (this.marked) {
                    readableBuffer2 = peek.readBytes(readableBytes2);
                    advanceBuffer();
                } else {
                    readableBuffer2 = this.readableBuffers.poll();
                }
                ReadableBuffer readableBuffer3 = readableBuffer2;
                i2 = i - readableBytes2;
                readableBuffer = readableBuffer3;
            }
            if (compositeReadableBuffer == null) {
                compositeReadableBuffer = readableBuffer;
            } else {
                if (compositeReadableBuffer2 == null) {
                    int i3 = 2;
                    if (i2 != 0) {
                        i3 = Math.min(this.readableBuffers.size() + 2, 16);
                    }
                    compositeReadableBuffer2 = new CompositeReadableBuffer(i3);
                    compositeReadableBuffer2.addBuffer(compositeReadableBuffer);
                    compositeReadableBuffer = compositeReadableBuffer2;
                }
                compositeReadableBuffer2.addBuffer(readableBuffer);
            }
            if (i2 <= 0) {
                return compositeReadableBuffer;
            }
            i = i2;
        }
    }

    public boolean markSupported() {
        for (ReadableBuffer markSupported : this.readableBuffers) {
            if (!markSupported.markSupported()) {
                return false;
            }
        }
        return true;
    }

    public void mark() {
        if (this.rewindableBuffers == null) {
            this.rewindableBuffers = new ArrayDeque(Math.min(this.readableBuffers.size(), 16));
        }
        while (!this.rewindableBuffers.isEmpty()) {
            this.rewindableBuffers.remove().close();
        }
        this.marked = true;
        ReadableBuffer peek = this.readableBuffers.peek();
        if (peek != null) {
            peek.mark();
        }
    }

    public void reset() {
        if (this.marked) {
            ReadableBuffer peek = this.readableBuffers.peek();
            if (peek != null) {
                int readableBytes2 = peek.readableBytes();
                peek.reset();
                this.readableBytes += peek.readableBytes() - readableBytes2;
            }
            while (true) {
                ReadableBuffer pollLast = this.rewindableBuffers.pollLast();
                if (pollLast != null) {
                    pollLast.reset();
                    this.readableBuffers.addFirst(pollLast);
                    this.readableBytes += pollLast.readableBytes();
                } else {
                    return;
                }
            }
        } else {
            throw new InvalidMarkException();
        }
    }

    public boolean byteBufferSupported() {
        for (ReadableBuffer byteBufferSupported : this.readableBuffers) {
            if (!byteBufferSupported.byteBufferSupported()) {
                return false;
            }
        }
        return true;
    }

    @Nullable
    public ByteBuffer getByteBuffer() {
        if (this.readableBuffers.isEmpty()) {
            return null;
        }
        return this.readableBuffers.peek().getByteBuffer();
    }

    public void close() {
        while (!this.readableBuffers.isEmpty()) {
            this.readableBuffers.remove().close();
        }
        if (this.rewindableBuffers != null) {
            while (!this.rewindableBuffers.isEmpty()) {
                this.rewindableBuffers.remove().close();
            }
        }
    }

    private <T> int execute(ReadOperation<T> readOperation, int i, T t, int i2) throws IOException {
        checkReadable(i);
        if (!this.readableBuffers.isEmpty()) {
            advanceBufferIfNecessary();
        }
        while (i > 0 && !this.readableBuffers.isEmpty()) {
            ReadableBuffer peek = this.readableBuffers.peek();
            int min = Math.min(i, peek.readableBytes());
            i2 = readOperation.read(peek, min, t, i2);
            i -= min;
            this.readableBytes -= min;
            advanceBufferIfNecessary();
        }
        if (i <= 0) {
            return i2;
        }
        throw new AssertionError("Failed executing read operation");
    }

    private <T> int executeNoThrow(NoThrowReadOperation<T> noThrowReadOperation, int i, T t, int i2) {
        try {
            return execute(noThrowReadOperation, i, t, i2);
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    private void advanceBufferIfNecessary() {
        if (this.readableBuffers.peek().readableBytes() == 0) {
            advanceBuffer();
        }
    }

    private void advanceBuffer() {
        if (this.marked) {
            this.rewindableBuffers.add(this.readableBuffers.remove());
            ReadableBuffer peek = this.readableBuffers.peek();
            if (peek != null) {
                peek.mark();
                return;
            }
            return;
        }
        this.readableBuffers.remove().close();
    }
}
