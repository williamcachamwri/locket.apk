package androidx.camera.video.internal.encoder;

import android.media.MediaCodec;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

class InputBufferImpl implements InputBuffer {
    private final int mBufferIndex;
    private final ByteBuffer mByteBuffer;
    private boolean mIsEndOfStream = false;
    private final MediaCodec mMediaCodec;
    private long mPresentationTimeUs = 0;
    private final AtomicBoolean mTerminated = new AtomicBoolean(false);
    private final CallbackToFutureAdapter.Completer<Void> mTerminationCompleter;
    private final ListenableFuture<Void> mTerminationFuture;

    InputBufferImpl(MediaCodec mediaCodec, int i) throws MediaCodec.CodecException {
        this.mMediaCodec = (MediaCodec) Preconditions.checkNotNull(mediaCodec);
        this.mBufferIndex = Preconditions.checkArgumentNonnegative(i);
        this.mByteBuffer = mediaCodec.getInputBuffer(i);
        AtomicReference atomicReference = new AtomicReference();
        this.mTerminationFuture = CallbackToFutureAdapter.getFuture(new InputBufferImpl$$ExternalSyntheticLambda0(atomicReference));
        this.mTerminationCompleter = (CallbackToFutureAdapter.Completer) Preconditions.checkNotNull((CallbackToFutureAdapter.Completer) atomicReference.get());
    }

    public ByteBuffer getByteBuffer() {
        throwIfTerminated();
        return this.mByteBuffer;
    }

    public void setPresentationTimeUs(long j) {
        throwIfTerminated();
        Preconditions.checkArgument(j >= 0);
        this.mPresentationTimeUs = j;
    }

    public void setEndOfStream(boolean z) {
        throwIfTerminated();
        this.mIsEndOfStream = z;
    }

    public boolean submit() {
        if (this.mTerminated.getAndSet(true)) {
            return false;
        }
        try {
            this.mMediaCodec.queueInputBuffer(this.mBufferIndex, this.mByteBuffer.position(), this.mByteBuffer.limit(), this.mPresentationTimeUs, this.mIsEndOfStream ? 4 : 0);
            this.mTerminationCompleter.set(null);
            return true;
        } catch (IllegalStateException e) {
            this.mTerminationCompleter.setException(e);
            return false;
        }
    }

    public boolean cancel() {
        if (this.mTerminated.getAndSet(true)) {
            return false;
        }
        try {
            this.mMediaCodec.queueInputBuffer(this.mBufferIndex, 0, 0, 0, 0);
            this.mTerminationCompleter.set(null);
        } catch (IllegalStateException e) {
            this.mTerminationCompleter.setException(e);
        }
        return true;
    }

    public ListenableFuture<Void> getTerminationFuture() {
        return Futures.nonCancellationPropagating(this.mTerminationFuture);
    }

    private void throwIfTerminated() {
        if (this.mTerminated.get()) {
            throw new IllegalStateException("The buffer is submitted or canceled.");
        }
    }
}
