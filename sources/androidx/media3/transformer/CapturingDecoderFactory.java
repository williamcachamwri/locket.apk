package androidx.media3.transformer;

import android.view.Surface;
import androidx.media3.common.Format;
import androidx.media3.transformer.Codec;

final class CapturingDecoderFactory implements Codec.DecoderFactory {
    private String audioDecoderName;
    private final Codec.DecoderFactory decoderFactory;
    private String videoDecoderName;

    public CapturingDecoderFactory(Codec.DecoderFactory decoderFactory2) {
        this.decoderFactory = decoderFactory2;
    }

    public Codec createForAudioDecoding(Format format) throws ExportException {
        Codec createForAudioDecoding = this.decoderFactory.createForAudioDecoding(format);
        this.audioDecoderName = createForAudioDecoding.getName();
        return createForAudioDecoding;
    }

    public Codec createForVideoDecoding(Format format, Surface surface, boolean z) throws ExportException {
        Codec createForVideoDecoding = this.decoderFactory.createForVideoDecoding(format, surface, z);
        this.videoDecoderName = createForVideoDecoding.getName();
        return createForVideoDecoding;
    }

    public String getAudioDecoderName() {
        return this.audioDecoderName;
    }

    public String getVideoDecoderName() {
        return this.videoDecoderName;
    }
}
