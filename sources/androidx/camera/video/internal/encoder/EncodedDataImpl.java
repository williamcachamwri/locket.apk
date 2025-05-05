package androidx.camera.video.internal.encoder;

import android.media.MediaCodec;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class EncodedDataImpl implements EncodedData {
    private final int mBufferIndex;
    private final MediaCodec.BufferInfo mBufferInfo;
    private final ByteBuffer mByteBuffer;
    private final AtomicBoolean mClosed = new AtomicBoolean(false);
    private final CallbackToFutureAdapter.Completer<Void> mClosedCompleter;
    private final ListenableFuture<Void> mClosedFuture;
    private final MediaCodec mMediaCodec;

    EncodedDataImpl(MediaCodec mediaCodec, int i, MediaCodec.BufferInfo bufferInfo) throws MediaCodec.CodecException {
        this.mMediaCodec = (MediaCodec) Preconditions.checkNotNull(mediaCodec);
        this.mBufferIndex = i;
        this.mByteBuffer = mediaCodec.getOutputBuffer(i);
        this.mBufferInfo = (MediaCodec.BufferInfo) Preconditions.checkNotNull(bufferInfo);
        AtomicReference atomicReference = new AtomicReference();
        this.mClosedFuture = CallbackToFutureAdapter.getFuture(new EncodedDataImpl$$ExternalSyntheticLambda0(atomicReference));
        this.mClosedCompleter = (CallbackToFutureAdapter.Completer) Preconditions.checkNotNull((CallbackToFutureAdapter.Completer) atomicReference.get());
    }

    public ByteBuffer getByteBuffer() {
        throwIfClosed();
        this.mByteBuffer.position(this.mBufferInfo.offset);
        this.mByteBuffer.limit(this.mBufferInfo.offset + this.mBufferInfo.size);
        return this.mByteBuffer;
    }

    public MediaCodec.BufferInfo getBufferInfo() {
        return this.mBufferInfo;
    }

    public long getPresentationTimeUs() {
        return this.mBufferInfo.presentationTimeUs;
    }

    public long size() {
        return (long) this.mBufferInfo.size;
    }

    public boolean isKeyFrame() {
        return (this.mBufferInfo.flags & 1) != 0;
    }

    public void close() {
        if (!this.mClosed.getAndSet(true)) {
            try {
                this.mMediaCodec.releaseOutputBuffer(this.mBufferIndex, false);
                this.mClosedCompleter.set(null);
            } catch (IllegalStateException e) {
                this.mClosedCompleter.setException(e);
            }
        }
    }

    public ListenableFuture<Void> getClosedFuture() {
        return Futures.nonCancellationPropagating(this.mClosedFuture);
    }

    private void throwIfClosed() {
        if (this.mClosed.get()) {
            throw new IllegalStateException("encoded data is closed.");
        }
    }
}
