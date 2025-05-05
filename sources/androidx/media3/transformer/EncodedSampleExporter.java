package androidx.media3.transformer;

import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.decoder.DecoderInputBuffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

final class EncodedSampleExporter extends SampleExporter implements GraphInput {
    static final long ALLOCATION_SIZE_TARGET_BYTES = 2097152;
    private static final ByteBuffer EMPTY_BUFFER = ByteBuffer.allocateDirect(0).order(ByteOrder.nativeOrder());
    static final int MAX_INPUT_BUFFER_COUNT = 200;
    static final int MIN_INPUT_BUFFER_COUNT = 10;
    private final Queue<DecoderInputBuffer> availableInputBuffers = new ConcurrentLinkedQueue();
    private final Format format;
    private boolean hasReachedAllocationTarget;
    private final long initialTimestampOffsetUs;
    private volatile boolean inputEnded;
    private long mediaItemOffsetUs;
    private DecoderInputBuffer nextInputBuffer;
    private final AtomicLong nextMediaItemOffsetUs = new AtomicLong();
    private final Queue<DecoderInputBuffer> pendingInputBuffers = new ConcurrentLinkedQueue();
    private long totalBufferSizeBytes;

    public GraphInput getInput(EditedMediaItem editedMediaItem, Format format2, int i) {
        return this;
    }

    public void release() {
    }

    public EncodedSampleExporter(Format format2, TransformationRequest transformationRequest, MuxerWrapper muxerWrapper, FallbackListener fallbackListener, long j) {
        super(format2, muxerWrapper);
        this.format = format2;
        this.initialTimestampOffsetUs = j;
        fallbackListener.onTransformationRequestFinalized(transformationRequest);
    }

    public void onMediaItemChanged(EditedMediaItem editedMediaItem, long j, Format format2, boolean z) {
        this.mediaItemOffsetUs = this.nextMediaItemOffsetUs.get();
        this.nextMediaItemOffsetUs.addAndGet(j);
    }

    public DecoderInputBuffer getInputBuffer() {
        if (this.nextInputBuffer == null) {
            DecoderInputBuffer poll = this.availableInputBuffers.poll();
            this.nextInputBuffer = poll;
            if (!this.hasReachedAllocationTarget) {
                if (poll == null) {
                    DecoderInputBuffer decoderInputBuffer = new DecoderInputBuffer(2);
                    this.nextInputBuffer = decoderInputBuffer;
                    decoderInputBuffer.data = EMPTY_BUFFER;
                } else {
                    this.totalBufferSizeBytes -= (long) ((ByteBuffer) Assertions.checkNotNull(poll.data)).capacity();
                }
            }
        }
        return this.nextInputBuffer;
    }

    public boolean queueInputBuffer() {
        DecoderInputBuffer decoderInputBuffer = (DecoderInputBuffer) Assertions.checkNotNull(this.nextInputBuffer);
        this.nextInputBuffer = null;
        if (decoderInputBuffer.isEndOfStream()) {
            this.inputEnded = true;
        } else {
            decoderInputBuffer.timeUs += this.mediaItemOffsetUs + this.initialTimestampOffsetUs;
            this.pendingInputBuffers.add(decoderInputBuffer);
        }
        if (!this.hasReachedAllocationTarget) {
            int size = this.availableInputBuffers.size() + this.pendingInputBuffers.size();
            long capacity = this.totalBufferSizeBytes + ((long) ((ByteBuffer) Assertions.checkNotNull(decoderInputBuffer.data)).capacity());
            this.totalBufferSizeBytes = capacity;
            this.hasReachedAllocationTarget = size >= 10 && (size >= 200 || capacity >= 2097152);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public Format getMuxerInputFormat() {
        return this.format;
    }

    /* access modifiers changed from: protected */
    public DecoderInputBuffer getMuxerInputBuffer() {
        return this.pendingInputBuffers.peek();
    }

    /* access modifiers changed from: protected */
    public void releaseMuxerInputBuffer() {
        DecoderInputBuffer remove = this.pendingInputBuffers.remove();
        remove.clear();
        remove.timeUs = 0;
        this.availableInputBuffers.add(remove);
    }

    /* access modifiers changed from: protected */
    public boolean isMuxerInputEnded() {
        return this.inputEnded && this.pendingInputBuffers.isEmpty();
    }
}
