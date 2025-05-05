package androidx.media3.exoplayer.mediacodec;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.HandlerThread;
import androidx.collection.CircularIntArray;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.mediacodec.MediaCodecAdapter;
import java.util.ArrayDeque;

final class AsynchronousMediaCodecCallback extends MediaCodec.Callback {
    private final CircularIntArray availableInputBuffers;
    private final CircularIntArray availableOutputBuffers;
    private final ArrayDeque<MediaCodec.BufferInfo> bufferInfos;
    private final HandlerThread callbackThread;
    private MediaFormat currentFormat;
    private final ArrayDeque<MediaFormat> formats;
    private Handler handler;
    private IllegalStateException internalException;
    private final Object lock = new Object();
    private MediaCodec.CryptoException mediaCodecCryptoException;
    private MediaCodec.CodecException mediaCodecException;
    private MediaCodecAdapter.OnBufferAvailableListener onBufferAvailableListener;
    private long pendingFlushCount;
    private MediaFormat pendingOutputFormat;
    private boolean shutDown;

    AsynchronousMediaCodecCallback(HandlerThread handlerThread) {
        this.callbackThread = handlerThread;
        this.availableInputBuffers = new CircularIntArray();
        this.availableOutputBuffers = new CircularIntArray();
        this.bufferInfos = new ArrayDeque<>();
        this.formats = new ArrayDeque<>();
    }

    public void initialize(MediaCodec mediaCodec) {
        Assertions.checkState(this.handler == null);
        this.callbackThread.start();
        Handler handler2 = new Handler(this.callbackThread.getLooper());
        mediaCodec.setCallback(this, handler2);
        this.handler = handler2;
    }

    public void shutdown() {
        synchronized (this.lock) {
            this.shutDown = true;
            this.callbackThread.quit();
            flushInternal();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001f, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int dequeueInputBufferIndex() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.lock
            monitor-enter(r0)
            r3.maybeThrowException()     // Catch:{ all -> 0x0020 }
            boolean r1 = r3.isFlushingOrShutdown()     // Catch:{ all -> 0x0020 }
            r2 = -1
            if (r1 == 0) goto L_0x000f
            monitor-exit(r0)     // Catch:{ all -> 0x0020 }
            return r2
        L_0x000f:
            androidx.collection.CircularIntArray r1 = r3.availableInputBuffers     // Catch:{ all -> 0x0020 }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0020 }
            if (r1 == 0) goto L_0x0018
            goto L_0x001e
        L_0x0018:
            androidx.collection.CircularIntArray r1 = r3.availableInputBuffers     // Catch:{ all -> 0x0020 }
            int r2 = r1.popFirst()     // Catch:{ all -> 0x0020 }
        L_0x001e:
            monitor-exit(r0)     // Catch:{ all -> 0x0020 }
            return r2
        L_0x0020:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0020 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecCallback.dequeueInputBufferIndex():int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0049, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int dequeueOutputBufferIndex(android.media.MediaCodec.BufferInfo r10) {
        /*
            r9 = this;
            java.lang.Object r0 = r9.lock
            monitor-enter(r0)
            r9.maybeThrowException()     // Catch:{ all -> 0x004a }
            boolean r1 = r9.isFlushingOrShutdown()     // Catch:{ all -> 0x004a }
            r2 = -1
            if (r1 == 0) goto L_0x000f
            monitor-exit(r0)     // Catch:{ all -> 0x004a }
            return r2
        L_0x000f:
            androidx.collection.CircularIntArray r1 = r9.availableOutputBuffers     // Catch:{ all -> 0x004a }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x004a }
            if (r1 == 0) goto L_0x0019
            monitor-exit(r0)     // Catch:{ all -> 0x004a }
            return r2
        L_0x0019:
            androidx.collection.CircularIntArray r1 = r9.availableOutputBuffers     // Catch:{ all -> 0x004a }
            int r1 = r1.popFirst()     // Catch:{ all -> 0x004a }
            if (r1 < 0) goto L_0x003b
            android.media.MediaFormat r2 = r9.currentFormat     // Catch:{ all -> 0x004a }
            androidx.media3.common.util.Assertions.checkStateNotNull(r2)     // Catch:{ all -> 0x004a }
            java.util.ArrayDeque<android.media.MediaCodec$BufferInfo> r2 = r9.bufferInfos     // Catch:{ all -> 0x004a }
            java.lang.Object r2 = r2.remove()     // Catch:{ all -> 0x004a }
            android.media.MediaCodec$BufferInfo r2 = (android.media.MediaCodec.BufferInfo) r2     // Catch:{ all -> 0x004a }
            int r4 = r2.offset     // Catch:{ all -> 0x004a }
            int r5 = r2.size     // Catch:{ all -> 0x004a }
            long r6 = r2.presentationTimeUs     // Catch:{ all -> 0x004a }
            int r8 = r2.flags     // Catch:{ all -> 0x004a }
            r3 = r10
            r3.set(r4, r5, r6, r8)     // Catch:{ all -> 0x004a }
            goto L_0x0048
        L_0x003b:
            r10 = -2
            if (r1 != r10) goto L_0x0048
            java.util.ArrayDeque<android.media.MediaFormat> r10 = r9.formats     // Catch:{ all -> 0x004a }
            java.lang.Object r10 = r10.remove()     // Catch:{ all -> 0x004a }
            android.media.MediaFormat r10 = (android.media.MediaFormat) r10     // Catch:{ all -> 0x004a }
            r9.currentFormat = r10     // Catch:{ all -> 0x004a }
        L_0x0048:
            monitor-exit(r0)     // Catch:{ all -> 0x004a }
            return r1
        L_0x004a:
            r10 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x004a }
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecCallback.dequeueOutputBufferIndex(android.media.MediaCodec$BufferInfo):int");
    }

    public MediaFormat getOutputFormat() {
        MediaFormat mediaFormat;
        synchronized (this.lock) {
            mediaFormat = this.currentFormat;
            if (mediaFormat == null) {
                throw new IllegalStateException();
            }
        }
        return mediaFormat;
    }

    public void flush() {
        synchronized (this.lock) {
            this.pendingFlushCount++;
            ((Handler) Util.castNonNull(this.handler)).post(new AsynchronousMediaCodecCallback$$ExternalSyntheticLambda0(this));
        }
    }

    public void onInputBufferAvailable(MediaCodec mediaCodec, int i) {
        synchronized (this.lock) {
            this.availableInputBuffers.addLast(i);
            MediaCodecAdapter.OnBufferAvailableListener onBufferAvailableListener2 = this.onBufferAvailableListener;
            if (onBufferAvailableListener2 != null) {
                onBufferAvailableListener2.onInputBufferAvailable();
            }
        }
    }

    public void onOutputBufferAvailable(MediaCodec mediaCodec, int i, MediaCodec.BufferInfo bufferInfo) {
        synchronized (this.lock) {
            MediaFormat mediaFormat = this.pendingOutputFormat;
            if (mediaFormat != null) {
                addOutputFormat(mediaFormat);
                this.pendingOutputFormat = null;
            }
            this.availableOutputBuffers.addLast(i);
            this.bufferInfos.add(bufferInfo);
            MediaCodecAdapter.OnBufferAvailableListener onBufferAvailableListener2 = this.onBufferAvailableListener;
            if (onBufferAvailableListener2 != null) {
                onBufferAvailableListener2.onOutputBufferAvailable();
            }
        }
    }

    public void onError(MediaCodec mediaCodec, MediaCodec.CodecException codecException) {
        synchronized (this.lock) {
            this.mediaCodecException = codecException;
        }
    }

    public void onCryptoError(MediaCodec mediaCodec, MediaCodec.CryptoException cryptoException) {
        synchronized (this.lock) {
            this.mediaCodecCryptoException = cryptoException;
        }
    }

    public void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) {
        synchronized (this.lock) {
            addOutputFormat(mediaFormat);
            this.pendingOutputFormat = null;
        }
    }

    public void setOnBufferAvailableListener(MediaCodecAdapter.OnBufferAvailableListener onBufferAvailableListener2) {
        synchronized (this.lock) {
            this.onBufferAvailableListener = onBufferAvailableListener2;
        }
    }

    /* access modifiers changed from: private */
    public void onFlushCompleted() {
        synchronized (this.lock) {
            if (!this.shutDown) {
                long j = this.pendingFlushCount - 1;
                this.pendingFlushCount = j;
                if (j <= 0) {
                    if (j < 0) {
                        setInternalException(new IllegalStateException());
                    } else {
                        flushInternal();
                    }
                }
            }
        }
    }

    private void flushInternal() {
        if (!this.formats.isEmpty()) {
            this.pendingOutputFormat = this.formats.getLast();
        }
        this.availableInputBuffers.clear();
        this.availableOutputBuffers.clear();
        this.bufferInfos.clear();
        this.formats.clear();
    }

    private boolean isFlushingOrShutdown() {
        return this.pendingFlushCount > 0 || this.shutDown;
    }

    private void addOutputFormat(MediaFormat mediaFormat) {
        this.availableOutputBuffers.addLast(-2);
        this.formats.add(mediaFormat);
    }

    private void maybeThrowException() {
        maybeThrowInternalException();
        maybeThrowMediaCodecException();
        maybeThrowMediaCodecCryptoException();
    }

    private void maybeThrowInternalException() {
        IllegalStateException illegalStateException = this.internalException;
        if (illegalStateException != null) {
            this.internalException = null;
            throw illegalStateException;
        }
    }

    private void maybeThrowMediaCodecException() {
        MediaCodec.CodecException codecException = this.mediaCodecException;
        if (codecException != null) {
            this.mediaCodecException = null;
            throw codecException;
        }
    }

    private void maybeThrowMediaCodecCryptoException() {
        MediaCodec.CryptoException cryptoException = this.mediaCodecCryptoException;
        if (cryptoException != null) {
            this.mediaCodecCryptoException = null;
            throw cryptoException;
        }
    }

    private void setInternalException(IllegalStateException illegalStateException) {
        synchronized (this.lock) {
            this.internalException = illegalStateException;
        }
    }
}
