package io.grpc.okhttp;

import com.google.common.base.Preconditions;
import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.FrameWriter;
import io.grpc.okhttp.internal.framed.Header;
import io.grpc.okhttp.internal.framed.Settings;
import java.io.IOException;
import java.util.List;
import okio.Buffer;

abstract class ForwardingFrameWriter implements FrameWriter {
    private final FrameWriter delegate;

    public ForwardingFrameWriter(FrameWriter frameWriter) {
        this.delegate = (FrameWriter) Preconditions.checkNotNull(frameWriter, "delegate");
    }

    public void close() throws IOException {
        this.delegate.close();
    }

    public void connectionPreface() throws IOException {
        this.delegate.connectionPreface();
    }

    public void ackSettings(Settings settings) throws IOException {
        this.delegate.ackSettings(settings);
    }

    public void pushPromise(int i, int i2, List<Header> list) throws IOException {
        this.delegate.pushPromise(i, i2, list);
    }

    public void flush() throws IOException {
        this.delegate.flush();
    }

    public void synStream(boolean z, boolean z2, int i, int i2, List<Header> list) throws IOException {
        this.delegate.synStream(z, z2, i, i2, list);
    }

    public void synReply(boolean z, int i, List<Header> list) throws IOException {
        this.delegate.synReply(z, i, list);
    }

    public void headers(int i, List<Header> list) throws IOException {
        this.delegate.headers(i, list);
    }

    public void rstStream(int i, ErrorCode errorCode) throws IOException {
        this.delegate.rstStream(i, errorCode);
    }

    public int maxDataLength() {
        return this.delegate.maxDataLength();
    }

    public void data(boolean z, int i, Buffer buffer, int i2) throws IOException {
        this.delegate.data(z, i, buffer, i2);
    }

    public void settings(Settings settings) throws IOException {
        this.delegate.settings(settings);
    }

    public void ping(boolean z, int i, int i2) throws IOException {
        this.delegate.ping(z, i, i2);
    }

    public void goAway(int i, ErrorCode errorCode, byte[] bArr) throws IOException {
        this.delegate.goAway(i, errorCode, bArr);
    }

    public void windowUpdate(int i, long j) throws IOException {
        this.delegate.windowUpdate(i, j);
    }
}
