package com.facebook.react.modules.network;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

public class ProgressResponseBody extends ResponseBody {
    private BufferedSource mBufferedSource;
    /* access modifiers changed from: private */
    public final ProgressListener mProgressListener;
    /* access modifiers changed from: private */
    public final ResponseBody mResponseBody;
    /* access modifiers changed from: private */
    public long mTotalBytesRead = 0;

    public ProgressResponseBody(ResponseBody responseBody, ProgressListener progressListener) {
        this.mResponseBody = responseBody;
        this.mProgressListener = progressListener;
    }

    public MediaType contentType() {
        return this.mResponseBody.contentType();
    }

    public long contentLength() {
        return this.mResponseBody.contentLength();
    }

    public long totalBytesRead() {
        return this.mTotalBytesRead;
    }

    public BufferedSource source() {
        if (this.mBufferedSource == null) {
            this.mBufferedSource = Okio.buffer(source(this.mResponseBody.source()));
        }
        return this.mBufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            public long read(Buffer buffer, long j) throws IOException {
                long read = super.read(buffer, j);
                ProgressResponseBody progressResponseBody = ProgressResponseBody.this;
                int i = (read > -1 ? 1 : (read == -1 ? 0 : -1));
                progressResponseBody.mTotalBytesRead = progressResponseBody.mTotalBytesRead + (i != 0 ? read : 0);
                ProgressResponseBody.this.mProgressListener.onProgress(ProgressResponseBody.this.mTotalBytesRead, ProgressResponseBody.this.mResponseBody.contentLength(), i == 0);
                return read;
            }
        };
    }
}
