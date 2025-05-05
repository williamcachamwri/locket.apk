package androidx.media3.extractor.text;

import androidx.media3.common.util.Assertions;
import androidx.media3.decoder.SimpleDecoder;
import java.nio.ByteBuffer;

public abstract class SimpleSubtitleDecoder extends SimpleDecoder<SubtitleInputBuffer, SubtitleOutputBuffer, SubtitleDecoderException> implements SubtitleDecoder {
    private final String name;

    /* access modifiers changed from: protected */
    public abstract Subtitle decode(byte[] bArr, int i, boolean z) throws SubtitleDecoderException;

    public void setPositionUs(long j) {
    }

    protected SimpleSubtitleDecoder(String str) {
        super(new SubtitleInputBuffer[2], new SubtitleOutputBuffer[2]);
        this.name = str;
        setInitialInputBufferSize(1024);
    }

    public final String getName() {
        return this.name;
    }

    /* access modifiers changed from: protected */
    public final SubtitleInputBuffer createInputBuffer() {
        return new SubtitleInputBuffer();
    }

    /* access modifiers changed from: protected */
    public final SubtitleOutputBuffer createOutputBuffer() {
        return new SubtitleOutputBuffer() {
            public void release() {
                SimpleSubtitleDecoder.this.releaseOutputBuffer(this);
            }
        };
    }

    /* access modifiers changed from: protected */
    public final SubtitleDecoderException createUnexpectedDecodeException(Throwable th) {
        return new SubtitleDecoderException("Unexpected decode error", th);
    }

    /* access modifiers changed from: protected */
    public final SubtitleDecoderException decode(SubtitleInputBuffer subtitleInputBuffer, SubtitleOutputBuffer subtitleOutputBuffer, boolean z) {
        try {
            ByteBuffer byteBuffer = (ByteBuffer) Assertions.checkNotNull(subtitleInputBuffer.data);
            SubtitleOutputBuffer subtitleOutputBuffer2 = subtitleOutputBuffer;
            subtitleOutputBuffer2.setContent(subtitleInputBuffer.timeUs, decode(byteBuffer.array(), byteBuffer.limit(), z), subtitleInputBuffer.subsampleOffsetUs);
            subtitleOutputBuffer.shouldBeSkipped = false;
            return null;
        } catch (SubtitleDecoderException e) {
            return e;
        }
    }
}
