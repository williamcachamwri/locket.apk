package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

public abstract class ForwardingReadableBuffer implements ReadableBuffer {
    private final ReadableBuffer buf;

    protected ForwardingReadableBuffer(ReadableBuffer readableBuffer) {
        this.buf = (ReadableBuffer) Preconditions.checkNotNull(readableBuffer, "buf");
    }

    public int readableBytes() {
        return this.buf.readableBytes();
    }

    public int readUnsignedByte() {
        return this.buf.readUnsignedByte();
    }

    public int readInt() {
        return this.buf.readInt();
    }

    public void skipBytes(int i) {
        this.buf.skipBytes(i);
    }

    public void readBytes(byte[] bArr, int i, int i2) {
        this.buf.readBytes(bArr, i, i2);
    }

    public void readBytes(ByteBuffer byteBuffer) {
        this.buf.readBytes(byteBuffer);
    }

    public void readBytes(OutputStream outputStream, int i) throws IOException {
        this.buf.readBytes(outputStream, i);
    }

    public ReadableBuffer readBytes(int i) {
        return this.buf.readBytes(i);
    }

    public boolean hasArray() {
        return this.buf.hasArray();
    }

    public byte[] array() {
        return this.buf.array();
    }

    public int arrayOffset() {
        return this.buf.arrayOffset();
    }

    public void touch() {
        this.buf.touch();
    }

    public boolean markSupported() {
        return this.buf.markSupported();
    }

    public void mark() {
        this.buf.mark();
    }

    public void reset() {
        this.buf.reset();
    }

    public boolean byteBufferSupported() {
        return this.buf.byteBufferSupported();
    }

    @Nullable
    public ByteBuffer getByteBuffer() {
        return this.buf.getByteBuffer();
    }

    public void close() {
        this.buf.close();
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("delegate", (Object) this.buf).toString();
    }
}
