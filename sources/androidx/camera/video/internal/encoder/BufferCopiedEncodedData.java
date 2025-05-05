package androidx.camera.video.internal.encoder;

import android.media.MediaCodec;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicReference;

public class BufferCopiedEncodedData implements EncodedData {
    private final MediaCodec.BufferInfo mBufferInfo;
    private final ByteBuffer mByteBuffer;
    private final CallbackToFutureAdapter.Completer<Void> mClosedCompleter;
    private final ListenableFuture<Void> mClosedFuture;

    public BufferCopiedEncodedData(EncodedData encodedData) {
        this.mBufferInfo = generateCopiedByteInfo(encodedData);
        this.mByteBuffer = generateCopiedByteBuffer(encodedData);
        AtomicReference atomicReference = new AtomicReference();
        this.mClosedFuture = CallbackToFutureAdapter.getFuture(new BufferCopiedEncodedData$$ExternalSyntheticLambda0(atomicReference));
        this.mClosedCompleter = (CallbackToFutureAdapter.Completer) Preconditions.checkNotNull((CallbackToFutureAdapter.Completer) atomicReference.get());
    }

    private MediaCodec.BufferInfo generateCopiedByteInfo(EncodedData encodedData) {
        MediaCodec.BufferInfo bufferInfo = encodedData.getBufferInfo();
        MediaCodec.BufferInfo bufferInfo2 = new MediaCodec.BufferInfo();
        bufferInfo2.set(0, bufferInfo.size, bufferInfo.presentationTimeUs, bufferInfo.flags);
        return bufferInfo2;
    }

    private ByteBuffer generateCopiedByteBuffer(EncodedData encodedData) {
        ByteBuffer byteBuffer = encodedData.getByteBuffer();
        MediaCodec.BufferInfo bufferInfo = encodedData.getBufferInfo();
        byteBuffer.position(bufferInfo.offset);
        byteBuffer.limit(bufferInfo.offset + bufferInfo.size);
        ByteBuffer allocate = ByteBuffer.allocate(bufferInfo.size);
        allocate.order(byteBuffer.order());
        allocate.put(byteBuffer);
        allocate.flip();
        return allocate;
    }

    public ByteBuffer getByteBuffer() {
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
        this.mClosedCompleter.set(null);
    }

    public ListenableFuture<Void> getClosedFuture() {
        return Futures.nonCancellationPropagating(this.mClosedFuture);
    }
}
