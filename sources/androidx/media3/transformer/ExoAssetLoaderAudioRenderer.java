package androidx.media3.transformer;

import android.media.MediaCodec;
import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.transformer.AssetLoader;
import androidx.media3.transformer.Codec;
import java.nio.ByteBuffer;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

final class ExoAssetLoaderAudioRenderer extends ExoAssetLoaderBaseRenderer {
    private static final String TAG = "ExoAssetLoaderAudioRenderer";
    private final Codec.DecoderFactory decoderFactory;
    private boolean hasPendingConsumerInput;

    public String getName() {
        return TAG;
    }

    public ExoAssetLoaderAudioRenderer(Codec.DecoderFactory decoderFactory2, TransformerMediaClock transformerMediaClock, AssetLoader.Listener listener) {
        super(1, transformerMediaClock, listener);
        this.decoderFactory = decoderFactory2;
    }

    /* access modifiers changed from: protected */
    public void initDecoder(Format format) throws ExportException {
        this.decoder = this.decoderFactory.createForAudioDecoding(format);
    }

    /* access modifiers changed from: protected */
    public boolean shouldDropInputBuffer(DecoderInputBuffer decoderInputBuffer) {
        if (decoderInputBuffer.isEndOfStream()) {
            return false;
        }
        decoderInputBuffer.timeUs -= this.streamStartPositionUs;
        if (this.decoder == null || decoderInputBuffer.timeUs >= 0) {
            return false;
        }
        decoderInputBuffer.clear();
        return true;
    }

    /* access modifiers changed from: protected */
    @RequiresNonNull({"sampleConsumer", "decoder"})
    public boolean feedConsumerFromDecoder() throws ExportException {
        DecoderInputBuffer inputBuffer = this.sampleConsumer.getInputBuffer();
        if (inputBuffer == null) {
            return false;
        }
        if (!this.hasPendingConsumerInput) {
            if (this.decoder.isEnded()) {
                ((ByteBuffer) Assertions.checkNotNull(inputBuffer.data)).limit(0);
                inputBuffer.addFlag(4);
                this.isEnded = this.sampleConsumer.queueInputBuffer();
                return false;
            }
            ByteBuffer outputBuffer = this.decoder.getOutputBuffer();
            if (outputBuffer == null) {
                return false;
            }
            inputBuffer.ensureSpaceForWrite(outputBuffer.limit());
            inputBuffer.data.put(outputBuffer).flip();
            MediaCodec.BufferInfo bufferInfo = (MediaCodec.BufferInfo) Assertions.checkNotNull(this.decoder.getOutputBufferInfo());
            inputBuffer.timeUs = bufferInfo.presentationTimeUs;
            inputBuffer.setFlags(bufferInfo.flags);
            this.decoder.releaseOutputBuffer(false);
            this.hasPendingConsumerInput = true;
        }
        if (!this.sampleConsumer.queueInputBuffer()) {
            return false;
        }
        this.hasPendingConsumerInput = false;
        return true;
    }
}
