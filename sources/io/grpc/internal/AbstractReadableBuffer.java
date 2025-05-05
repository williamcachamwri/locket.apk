package io.grpc.internal;

import java.nio.ByteBuffer;

public abstract class AbstractReadableBuffer implements ReadableBuffer {
    public boolean byteBufferSupported() {
        return false;
    }

    public void close() {
    }

    public boolean hasArray() {
        return false;
    }

    public void mark() {
    }

    public boolean markSupported() {
        return false;
    }

    public final int readInt() {
        checkReadable(4);
        return (readUnsignedByte() << 24) | (readUnsignedByte() << 16) | (readUnsignedByte() << 8) | readUnsignedByte();
    }

    public byte[] array() {
        throw new UnsupportedOperationException();
    }

    public int arrayOffset() {
        throw new UnsupportedOperationException();
    }

    public void reset() {
        throw new UnsupportedOperationException();
    }

    public ByteBuffer getByteBuffer() {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public final void checkReadable(int i) {
        if (readableBytes() < i) {
            throw new IndexOutOfBoundsException();
        }
    }
}
