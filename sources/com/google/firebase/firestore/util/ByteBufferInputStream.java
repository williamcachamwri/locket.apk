package com.google.firebase.firestore.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class ByteBufferInputStream extends InputStream {
    ByteBuffer buffer;

    public ByteBufferInputStream(ByteBuffer byteBuffer) {
        this.buffer = byteBuffer;
    }

    public int available() throws IOException {
        return this.buffer.remaining();
    }

    public int read() {
        if (!this.buffer.hasRemaining()) {
            return -1;
        }
        return this.buffer.get() & 255;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (!this.buffer.hasRemaining()) {
            return -1;
        }
        int min = Math.min(i2, this.buffer.remaining());
        this.buffer.get(bArr, i, min);
        return min;
    }
}
